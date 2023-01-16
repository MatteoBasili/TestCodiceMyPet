package com.mysql.jdbc.interceptors;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.ResultSetInternalMethods;
import com.mysql.jdbc.Statement;
import com.mysql.jdbc.StatementInterceptor;
import com.mysql.jdbc.Util;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ServerStatusDiffInterceptor implements StatementInterceptor {
    private Map<String, String> postExecuteValues = new HashMap();
    private Map<String, String> preExecuteValues = new HashMap();

    public void init(Connection conn, Properties props) throws SQLException {
    }

    public ResultSetInternalMethods postProcess(String sql, Statement interceptedStatement, ResultSetInternalMethods originalResultSet, Connection connection) throws SQLException {
        if (!connection.versionMeetsMinimum(5, 0, 2)) {
            return null;
        }
        populateMapWithSessionStatusValues(connection, this.postExecuteValues);
        connection.getLog().logInfo("Server status change for statement:\n" + Util.calculateDifferences(this.preExecuteValues, this.postExecuteValues));
        return null;
    }

    private void populateMapWithSessionStatusValues(Connection connection, Map<String, String> toPopulate) throws SQLException {
        java.sql.Statement stmt = null;
        ResultSet rs = null;
        try {
            toPopulate.clear();
            stmt = connection.createStatement();
            rs = stmt.executeQuery("SHOW SESSION STATUS");
            while (rs.next()) {
                toPopulate.put(rs.getString(1), rs.getString(2));
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    public ResultSetInternalMethods preProcess(String sql, Statement interceptedStatement, Connection connection) throws SQLException {
        if (!connection.versionMeetsMinimum(5, 0, 2)) {
            return null;
        }
        populateMapWithSessionStatusValues(connection, this.preExecuteValues);
        return null;
    }

    public boolean executeTopLevelOnly() {
        return true;
    }

    public void destroy() {
    }
}
