package com.mysql.jdbc;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BlobFromLocator implements Blob {
    private String blobColumnName = null;
    private ResultSetImpl creatorResultSet;
    /* access modifiers changed from: private */
    public ExceptionInterceptor exceptionInterceptor;
    private int numColsInResultSet = 0;
    private int numPrimaryKeys = 0;
    private List<String> primaryKeyColumns = null;
    private List<String> primaryKeyValues = null;
    private String quotedId;
    private String tableName = null;

    BlobFromLocator(ResultSetImpl creatorResultSetToSet, int blobColumnIndex, ExceptionInterceptor exceptionInterceptor2) throws SQLException {
        this.exceptionInterceptor = exceptionInterceptor2;
        this.creatorResultSet = creatorResultSetToSet;
        this.numColsInResultSet = creatorResultSetToSet.fields.length;
        this.quotedId = this.creatorResultSet.connection.getMetaData().getIdentifierQuoteString();
        if (this.numColsInResultSet > 1) {
            this.primaryKeyColumns = new ArrayList();
            this.primaryKeyValues = new ArrayList();
            for (int i = 0; i < this.numColsInResultSet; i++) {
                if (this.creatorResultSet.fields[i].isPrimaryKey()) {
                    StringBuilder keyName = new StringBuilder();
                    keyName.append(this.quotedId);
                    String originalColumnName = this.creatorResultSet.fields[i].getOriginalName();
                    if (originalColumnName == null || originalColumnName.length() <= 0) {
                        keyName.append(this.creatorResultSet.fields[i].getName());
                    } else {
                        keyName.append(originalColumnName);
                    }
                    keyName.append(this.quotedId);
                    this.primaryKeyColumns.add(keyName.toString());
                    this.primaryKeyValues.add(this.creatorResultSet.getString(i + 1));
                }
            }
        } else {
            notEnoughInformationInQuery();
        }
        int size = this.primaryKeyColumns.size();
        this.numPrimaryKeys = size;
        if (size == 0) {
            notEnoughInformationInQuery();
        }
        if (this.creatorResultSet.fields[0].getOriginalTableName() != null) {
            StringBuilder tableNameBuffer = new StringBuilder();
            String databaseName = this.creatorResultSet.fields[0].getDatabaseName();
            if (databaseName != null && databaseName.length() > 0) {
                tableNameBuffer.append(this.quotedId);
                tableNameBuffer.append(databaseName);
                tableNameBuffer.append(this.quotedId);
                tableNameBuffer.append('.');
            }
            tableNameBuffer.append(this.quotedId);
            tableNameBuffer.append(this.creatorResultSet.fields[0].getOriginalTableName());
            tableNameBuffer.append(this.quotedId);
            this.tableName = tableNameBuffer.toString();
        } else {
            this.tableName = this.quotedId + this.creatorResultSet.fields[0].getTableName() + this.quotedId;
        }
        this.blobColumnName = this.quotedId + this.creatorResultSet.getString(blobColumnIndex) + this.quotedId;
    }

    private void notEnoughInformationInQuery() throws SQLException {
        throw SQLError.createSQLException("Emulated BLOB locators must come from a ResultSet with only one table selected, and all primary keys selected", SQLError.SQL_STATE_GENERAL_ERROR, this.exceptionInterceptor);
    }

    public OutputStream setBinaryStream(long indexToWriteAt) throws SQLException {
        throw SQLError.createSQLFeatureNotSupportedException();
    }

    public InputStream getBinaryStream() throws SQLException {
        return new BufferedInputStream(new LocatorInputStream(), this.creatorResultSet.connection.getLocatorFetchBufferSize());
    }

    public int setBytes(long writeAt, byte[] bytes, int offset, int length) throws SQLException {
        PreparedStatement pStmt = null;
        if (offset + length > bytes.length) {
            length = bytes.length - offset;
        }
        byte[] bytesToWrite = new byte[length];
        System.arraycopy(bytes, offset, bytesToWrite, 0, length);
        StringBuilder query = new StringBuilder("UPDATE ");
        query.append(this.tableName);
        query.append(" SET ");
        query.append(this.blobColumnName);
        query.append(" = INSERT(");
        query.append(this.blobColumnName);
        query.append(", ");
        query.append(writeAt);
        query.append(", ");
        query.append(length);
        query.append(", ?) WHERE ");
        query.append(this.primaryKeyColumns.get(0));
        query.append(" = ?");
        for (int i = 1; i < this.numPrimaryKeys; i++) {
            query.append(" AND ");
            query.append(this.primaryKeyColumns.get(i));
            query.append(" = ?");
        }
        try {
            pStmt = this.creatorResultSet.connection.prepareStatement(query.toString());
            pStmt.setBytes(1, bytesToWrite);
            for (int i2 = 0; i2 < this.numPrimaryKeys; i2++) {
                pStmt.setString(i2 + 2, this.primaryKeyValues.get(i2));
            }
            if (pStmt.executeUpdate() == 1) {
                return (int) length();
            }
            throw SQLError.createSQLException("BLOB data not found! Did primary keys change?", SQLError.SQL_STATE_GENERAL_ERROR, this.exceptionInterceptor);
        } finally {
            if (pStmt != null) {
                try {
                    pStmt.close();
                } catch (SQLException e) {
                }
            }
        }
    }

    public int setBytes(long writeAt, byte[] bytes) throws SQLException {
        return setBytes(writeAt, bytes, 0, bytes.length);
    }

    public byte[] getBytes(long pos, int length) throws SQLException {
        PreparedStatement pStmt = null;
        try {
            pStmt = createGetBytesStatement();
            return getBytesInternal(pStmt, pos, length);
        } finally {
            if (pStmt != null) {
                try {
                    pStmt.close();
                } catch (SQLException e) {
                }
            }
        }
    }

    public long length() throws SQLException {
        ResultSet blobRs = null;
        PreparedStatement pStmt = null;
        StringBuilder query = new StringBuilder("SELECT LENGTH(");
        query.append(this.blobColumnName);
        query.append(") FROM ");
        query.append(this.tableName);
        query.append(" WHERE ");
        query.append(this.primaryKeyColumns.get(0));
        query.append(" = ?");
        for (int i = 1; i < this.numPrimaryKeys; i++) {
            query.append(" AND ");
            query.append(this.primaryKeyColumns.get(i));
            query.append(" = ?");
        }
        try {
            pStmt = this.creatorResultSet.connection.prepareStatement(query.toString());
            for (int i2 = 0; i2 < this.numPrimaryKeys; i2++) {
                pStmt.setString(i2 + 1, this.primaryKeyValues.get(i2));
            }
            blobRs = pStmt.executeQuery();
            if (blobRs.next()) {
                return blobRs.getLong(1);
            }
            throw SQLError.createSQLException("BLOB data not found! Did primary keys change?", SQLError.SQL_STATE_GENERAL_ERROR, this.exceptionInterceptor);
        } finally {
            if (blobRs != null) {
                try {
                    blobRs.close();
                } catch (SQLException e) {
                }
            }
            if (pStmt != null) {
                try {
                    pStmt.close();
                } catch (SQLException e2) {
                }
            }
        }
    }

    public long position(Blob pattern, long start) throws SQLException {
        return position(pattern.getBytes(0, (int) pattern.length()), start);
    }

    public long position(byte[] pattern, long start) throws SQLException {
        ResultSet blobRs = null;
        PreparedStatement pStmt = null;
        StringBuilder query = new StringBuilder("SELECT LOCATE(");
        query.append("?, ");
        query.append(this.blobColumnName);
        query.append(", ");
        query.append(start);
        query.append(") FROM ");
        query.append(this.tableName);
        query.append(" WHERE ");
        query.append(this.primaryKeyColumns.get(0));
        query.append(" = ?");
        for (int i = 1; i < this.numPrimaryKeys; i++) {
            query.append(" AND ");
            query.append(this.primaryKeyColumns.get(i));
            query.append(" = ?");
        }
        try {
            pStmt = this.creatorResultSet.connection.prepareStatement(query.toString());
            pStmt.setBytes(1, pattern);
            for (int i2 = 0; i2 < this.numPrimaryKeys; i2++) {
                pStmt.setString(i2 + 2, this.primaryKeyValues.get(i2));
            }
            blobRs = pStmt.executeQuery();
            if (blobRs.next()) {
                return blobRs.getLong(1);
            }
            throw SQLError.createSQLException("BLOB data not found! Did primary keys change?", SQLError.SQL_STATE_GENERAL_ERROR, this.exceptionInterceptor);
        } finally {
            if (blobRs != null) {
                try {
                    blobRs.close();
                } catch (SQLException e) {
                }
            }
            if (pStmt != null) {
                try {
                    pStmt.close();
                } catch (SQLException e2) {
                }
            }
        }
    }

    public void truncate(long length) throws SQLException {
        PreparedStatement pStmt = null;
        StringBuilder query = new StringBuilder("UPDATE ");
        query.append(this.tableName);
        query.append(" SET ");
        query.append(this.blobColumnName);
        query.append(" = LEFT(");
        query.append(this.blobColumnName);
        query.append(", ");
        query.append(length);
        query.append(") WHERE ");
        query.append(this.primaryKeyColumns.get(0));
        query.append(" = ?");
        for (int i = 1; i < this.numPrimaryKeys; i++) {
            query.append(" AND ");
            query.append(this.primaryKeyColumns.get(i));
            query.append(" = ?");
        }
        try {
            pStmt = this.creatorResultSet.connection.prepareStatement(query.toString());
            for (int i2 = 0; i2 < this.numPrimaryKeys; i2++) {
                pStmt.setString(i2 + 1, this.primaryKeyValues.get(i2));
            }
            if (pStmt.executeUpdate() != 1) {
                throw SQLError.createSQLException("BLOB data not found! Did primary keys change?", SQLError.SQL_STATE_GENERAL_ERROR, this.exceptionInterceptor);
            }
        } finally {
            if (pStmt != null) {
                try {
                    pStmt.close();
                } catch (SQLException e) {
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public PreparedStatement createGetBytesStatement() throws SQLException {
        StringBuilder query = new StringBuilder("SELECT SUBSTRING(");
        query.append(this.blobColumnName);
        query.append(", ");
        query.append("?");
        query.append(", ");
        query.append("?");
        query.append(") FROM ");
        query.append(this.tableName);
        query.append(" WHERE ");
        query.append(this.primaryKeyColumns.get(0));
        query.append(" = ?");
        for (int i = 1; i < this.numPrimaryKeys; i++) {
            query.append(" AND ");
            query.append(this.primaryKeyColumns.get(i));
            query.append(" = ?");
        }
        return this.creatorResultSet.connection.prepareStatement(query.toString());
    }

    /* access modifiers changed from: package-private */
    public byte[] getBytesInternal(PreparedStatement pStmt, long pos, int length) throws SQLException {
        ResultSet blobRs = null;
        try {
            pStmt.setLong(1, pos);
            pStmt.setInt(2, length);
            for (int i = 0; i < this.numPrimaryKeys; i++) {
                pStmt.setString(i + 3, this.primaryKeyValues.get(i));
            }
            blobRs = pStmt.executeQuery();
            if (blobRs.next()) {
                return ((ResultSetImpl) blobRs).getBytes(1, true);
            }
            throw SQLError.createSQLException("BLOB data not found! Did primary keys change?", SQLError.SQL_STATE_GENERAL_ERROR, this.exceptionInterceptor);
        } finally {
            if (blobRs != null) {
                try {
                    blobRs.close();
                } catch (SQLException e) {
                }
            }
        }
    }

    class LocatorInputStream extends InputStream {
        long currentPositionInBlob = 0;
        long length = 0;
        PreparedStatement pStmt = null;

        LocatorInputStream() throws SQLException {
            this.length = BlobFromLocator.this.length();
            this.pStmt = BlobFromLocator.this.createGetBytesStatement();
        }

        LocatorInputStream(long pos, long len) throws SQLException {
            this.length = pos + len;
            this.currentPositionInBlob = pos;
            long blobLength = BlobFromLocator.this.length();
            if (pos + len > blobLength) {
                throw SQLError.createSQLException(Messages.getString("Blob.invalidStreamLength", new Object[]{Long.valueOf(blobLength), Long.valueOf(pos), Long.valueOf(len)}), SQLError.SQL_STATE_ILLEGAL_ARGUMENT, BlobFromLocator.this.exceptionInterceptor);
            } else if (pos < 1) {
                throw SQLError.createSQLException(Messages.getString("Blob.invalidStreamPos"), SQLError.SQL_STATE_ILLEGAL_ARGUMENT, BlobFromLocator.this.exceptionInterceptor);
            } else if (pos > blobLength) {
                throw SQLError.createSQLException(Messages.getString("Blob.invalidStreamPos"), SQLError.SQL_STATE_ILLEGAL_ARGUMENT, BlobFromLocator.this.exceptionInterceptor);
            }
        }

        public int read() throws IOException {
            long j = this.currentPositionInBlob;
            if (j + 1 > this.length) {
                return -1;
            }
            try {
                BlobFromLocator blobFromLocator = BlobFromLocator.this;
                PreparedStatement preparedStatement = this.pStmt;
                long j2 = j + 1;
                this.currentPositionInBlob = j2;
                byte[] asBytes = blobFromLocator.getBytesInternal(preparedStatement, j2, 1);
                if (asBytes == null) {
                    return -1;
                }
                return asBytes[0];
            } catch (SQLException sqlEx) {
                throw new IOException(sqlEx.toString());
            }
        }

        public int read(byte[] b, int off, int len) throws IOException {
            long j = this.currentPositionInBlob;
            if (j + 1 > this.length) {
                return -1;
            }
            try {
                byte[] asBytes = BlobFromLocator.this.getBytesInternal(this.pStmt, j + 1, len);
                if (asBytes == null) {
                    return -1;
                }
                System.arraycopy(asBytes, 0, b, off, asBytes.length);
                this.currentPositionInBlob += (long) asBytes.length;
                return asBytes.length;
            } catch (SQLException sqlEx) {
                throw new IOException(sqlEx.toString());
            }
        }

        public int read(byte[] b) throws IOException {
            long j = this.currentPositionInBlob;
            if (j + 1 > this.length) {
                return -1;
            }
            try {
                byte[] asBytes = BlobFromLocator.this.getBytesInternal(this.pStmt, j + 1, b.length);
                if (asBytes == null) {
                    return -1;
                }
                System.arraycopy(asBytes, 0, b, 0, asBytes.length);
                this.currentPositionInBlob += (long) asBytes.length;
                return asBytes.length;
            } catch (SQLException sqlEx) {
                throw new IOException(sqlEx.toString());
            }
        }

        public void close() throws IOException {
            PreparedStatement preparedStatement = this.pStmt;
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException sqlEx) {
                    throw new IOException(sqlEx.toString());
                }
            }
            super.close();
        }
    }

    public void free() throws SQLException {
        this.creatorResultSet = null;
        this.primaryKeyColumns = null;
        this.primaryKeyValues = null;
    }

    public InputStream getBinaryStream(long pos, long length) throws SQLException {
        return new LocatorInputStream(pos, length);
    }
}
