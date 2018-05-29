package com.revature.rbconnect;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ReimAccess {
	
	private static ReimAccess cf = null;
	private static Boolean build = true;
	
	private ReimAccess() {
		build = false;
	}
	//Bank access singleton
	public static synchronized ReimAccess getInstance() {
		return (build) ? cf = new ReimAccess() : cf;
	}
	
	public Connection getConnection() {
		Connection conn = null;
		Properties prop = new Properties();
		
		try {
			prop.load(new FileReader("/home/tritonium/Documents/My Programs/git/1803-MAR26-Java/Joseph_Gonzales_Code/Project1/resources/application.properties"));
			//prop.load(new FileReader("C:\\Users\\tritonium\\Documents\\My Programs\\git\\1803-MAR26-Java\\Joseph_Gonzales_Code\\Project1\\resources\\application.properties"));
			
			Class.forName(prop.getProperty("driver"));
			System.out.println("driver loaded");
			conn = DriverManager.getConnection(
					prop.getProperty("url"), 
					prop.getProperty("usr"),
					prop.getProperty("pwd"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return conn;
	}
}