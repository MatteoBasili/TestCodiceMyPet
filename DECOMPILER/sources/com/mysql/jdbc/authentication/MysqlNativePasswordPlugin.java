package com.mysql.jdbc.authentication;

import com.mysql.jdbc.AuthenticationPlugin;
import com.mysql.jdbc.Buffer;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.ExceptionInterceptor;
import com.mysql.jdbc.Messages;
import com.mysql.jdbc.SQLError;
import com.mysql.jdbc.Security;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

public class MysqlNativePasswordPlugin implements AuthenticationPlugin {
    private Connection connection;
    private String password = null;

    public void init(Connection conn, Properties props) throws SQLException {
        this.connection = conn;
    }

    public void destroy() {
        this.password = null;
    }

    public String getProtocolPluginName() {
        return "mysql_native_password";
    }

    public boolean requiresConfidentiality() {
        return false;
    }

    public boolean isReusable() {
        return true;
    }

    public void setAuthenticationParameters(String user, String password2) {
        this.password = password2;
    }

    public boolean nextAuthenticationStep(Buffer fromServer, List<Buffer> toServer) throws SQLException {
        Buffer bresp;
        try {
            toServer.clear();
            String pwd = this.password;
            if (!(fromServer == null || pwd == null)) {
                if (pwd.length() != 0) {
                    bresp = new Buffer(Security.scramble411(pwd, fromServer.readString(), this.connection.getPasswordCharacterEncoding()));
                    toServer.add(bresp);
                    return true;
                }
            }
            bresp = new Buffer(new byte[0]);
            toServer.add(bresp);
            return true;
        } catch (NoSuchAlgorithmException e) {
            throw SQLError.createSQLException(Messages.getString("MysqlIO.91") + Messages.getString("MysqlIO.92"), SQLError.SQL_STATE_GENERAL_ERROR, (ExceptionInterceptor) null);
        } catch (UnsupportedEncodingException e2) {
            throw SQLError.createSQLException(Messages.getString("MysqlNativePasswordPlugin.1", new Object[]{this.connection.getPasswordCharacterEncoding()}), SQLError.SQL_STATE_GENERAL_ERROR, (ExceptionInterceptor) null);
        }
    }

    public void reset() {
    }
}
