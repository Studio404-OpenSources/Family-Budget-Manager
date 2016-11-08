package database;

import java.sql.*;

public class connectDb {
	private static Connection conn = null;
	public static Connection db(){
		if(conn==null){
			try {
				Class.forName("org.sqlite.JDBC");
				conn = DriverManager.getConnection("jdbc:sqlite:src\\finance.sqlite");
			} catch (Exception e) {
				System.out.println("Connection error ! " + e);
			}		
		}
		return conn;
	}
}
