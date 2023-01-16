package com.mysql.jdbc;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.Properties;

public class ReflectiveStatementInterceptorAdapter implements StatementInterceptorV2 {
    private final StatementInterceptor toProxy;
    final Method v2PostProcessMethod;

    public ReflectiveStatementInterceptorAdapter(StatementInterceptor toProxy2) {
        this.toProxy = toProxy2;
        this.v2PostProcessMethod = getV2PostProcessMethod(toProxy2.getClass());
    }

    public void destroy() {
        this.toProxy.destroy();
    }

    public boolean executeTopLevelOnly() {
        return this.toProxy.executeTopLevelOnly();
    }

    public void init(Connection conn, Properties props) throws SQLException {
        this.toProxy.init(conn, props);
    }

    public ResultSetInternalMethods postProcess(String sql, Statement interceptedStatement, ResultSetInternalMethods originalResultSet, Connection connection, int warningCount, boolean noIndexUsed, boolean noGoodIndexUsed, SQLException statementException) throws SQLException {
        try {
            Method method = this.v2PostProcessMethod;
            StatementInterceptor statementInterceptor = this.toProxy;
            Object[] objArr = new Object[8];
            objArr[0] = sql;
            objArr[1] = interceptedStatement;
            objArr[2] = originalResultSet;
            objArr[3] = connection;
            objArr[4] = Integer.valueOf(warningCount);
            objArr[5] = noIndexUsed ? Boolean.TRUE : Boolean.FALSE;
            objArr[6] = noGoodIndexUsed ? Boolean.TRUE : Boolean.FALSE;
            objArr[7] = statementException;
            return (ResultSetInternalMethods) method.invoke(statementInterceptor, objArr);
        } catch (IllegalArgumentException e) {
            SQLException sqlEx = new SQLException("Unable to reflectively invoke interceptor");
            sqlEx.initCause(e);
            throw sqlEx;
        } catch (IllegalAccessException e2) {
            SQLException sqlEx2 = new SQLException("Unable to reflectively invoke interceptor");
            sqlEx2.initCause(e2);
            throw sqlEx2;
        } catch (InvocationTargetException e3) {
            SQLException sqlEx3 = new SQLException("Unable to reflectively invoke interceptor");
            sqlEx3.initCause(e3);
            throw sqlEx3;
        }
    }

    public ResultSetInternalMethods preProcess(String sql, Statement interceptedStatement, Connection connection) throws SQLException {
        return this.toProxy.preProcess(sql, interceptedStatement, connection);
    }

    public static final Method getV2PostProcessMethod(Class<?> toProxyClass) {
        try {
            return toProxyClass.getMethod("postProcess", new Class[]{String.class, Statement.class, ResultSetInternalMethods.class, Connection.class, Integer.TYPE, Boolean.TYPE, Boolean.TYPE, SQLException.class});
        } catch (SecurityException e) {
            return null;
        } catch (NoSuchMethodException e2) {
            return null;
        }
    }
}
