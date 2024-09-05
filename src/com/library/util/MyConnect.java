package com.library.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class MyConnect {
	

		private MyConnect() {
			
		}
		static private MyConnect connection=new MyConnect();
		
		public  static  MyConnect getMyConnection() {
			return connection;
			}
			public static Connection connect()  {
			try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			return DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "root");
			} 
			catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
			return null;
			}

		
		

	        
	}



	
}
