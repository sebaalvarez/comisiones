package com.agpro.controles;


import java.sql.*;
import static java.lang.System.*;


public class ConexionReload {

	    private Connection connection;
	    private Statement statement;

	    public ConexionReload(String url, String dbName, String username, String password) 
	            throws SQLException {

	            connection = DriverManager.getConnection(
	                            "jdbc:mysql://" + url + ":3306/" + dbName, 
	                             username, 
	                             password);      
	    }

	    public String select(String tableName, String fields[]) {
	        return select(tableName, fields, "1 = 1");
	    }

	    public String select(String tableName, String fields[], String crits) {
	        String selectStatement = "SELECT * "
	                               + "FROM " + tableName + " "
	                               + "WHERE " + crits;
	        String ret = "";        

	        try {
	            statement = connection.createStatement();
	            ResultSet result = statement.executeQuery(selectStatement);

	            while (result.next()) {
	                for (String field : fields) {
	                    String currentFieldValue = result.getString(field);

	                    if (currentFieldValue != null) {
	                        ret += result.getString(field) + "\t";
	                    }
	                }

	                ret = ret.substring(0, ret.length() - 1) + "\n";
	            }

	        } catch (SQLException e) {
	            err.println(createSqlExceptionInfo(e));
	        } finally {
	            resetStatement();
	        }

	        return ret;
	    }

	    public void insert(String sqlInsert) {
	        try {
	            statement = connection.createStatement();

	            statement.executeUpdate(sqlInsert);
	        } catch (SQLException e) {
	            err.println(createSqlExceptionInfo(e));
	        } finally {
	            resetStatement();
	        }
	    }

	    public void update(String sqlUpdate) {
	        try {
	            statement = connection.createStatement();

	            statement.executeUpdate(sqlUpdate);
	        } catch (SQLException e) {
	            err.println(createSqlExceptionInfo(e));
	        } finally {
	            resetStatement();
	        }
	    }

	    public void delete(String sqlDelete) {
	        try {
	            statement = connection.createStatement();

	            statement.executeUpdate(sqlDelete);
	        } catch (SQLException e) {
	            err.println(createSqlExceptionInfo(e));
	        } finally {
	            resetStatement();
	        }
	    }

	    public boolean closeConnection() {
	        try {
	            connection.close();

	            return true;
	        } catch (SQLException e) {
	            err.println(createSqlExceptionInfo(e));
	        }

	        return false;
	    }

	    public static String createSqlExceptionInfo(SQLException e) {
	        String ret   = "SQL-State:\t"            + e.getSQLState()  + "\n";
	        ret         += "SQL error-code:\t"       + e.getErrorCode() + "\n";
	        ret         += "Description:\t"          + e.getMessage();

	        return ret;
	    }

	    private void resetStatement() {
	        if (statement != null) {
	            statement = null;
	        }
	    }

	
	
	
}
