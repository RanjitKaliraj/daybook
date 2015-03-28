package com.daybook.util;

//importing the sql connection and driver manager
//which are the java library package for mysql database connection.
import java.sql.Connection;
import java.sql.DriverManager;


public class DbConnection {
	
	private static String user = "root";
	private static String password = "";
	
	//why constructor can not be used when static method is called.
	//also variable need to be class. is instance can not be made so.
	
	/*
	 * this is a static method for stablishing database connection
	 * this method establish a new connection and return the connection
	 * Exception error is displayed if error occured during connection
	 */
	public static Connection getConnection(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection db_connect = DriverManager.getConnection("jdbc:mysql://127.0.0.1/day_book_dbfl",user,password);
			return db_connect;
		}		
		catch (Exception ex){
			System.out.println("Database connection error : "+ ex.getMessage());
			return null;
		}
	}
	
	/*
	 * this is a static method and this method is used to closed 
	 * the current active database connection
	 */
	public static void close_Connection(Connection con){
		try{
			con.close();			
		}
		catch (Exception e){			
		}
	}
	

}
