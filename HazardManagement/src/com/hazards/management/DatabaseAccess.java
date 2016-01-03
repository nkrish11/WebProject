/**
 * 
 */
package com.hazards.management;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Himanshu Mishra (homi1388@gmail.com)
 * @author Nikhil Krishnamurthy
 *
 */
public class DatabaseAccess {
	
	
Connection connection=null;
	
	public Connection getConnection(){
		return this.connection;
		
	}
	 
	 public void connect() throws SQLException
	 {
	  String DRIVER="com.mysql.jdbc.Driver";
	  String URL = "jdbc:mysql://localhost:3306/places";
	  String UserName = "root";
	  String Password = "Gully@1410";
	  try
	  {
	   Class.forName(DRIVER);
	  }
	  catch (ClassNotFoundException e)
	  {
	   e.printStackTrace();
	  }

	  try
	  {
	   connection = DriverManager.getConnection(URL,UserName,Password);
	  }
	  catch (SQLException e)
	  {
	   e.printStackTrace();
	  }
	  
	 }
	 public void disconnect() throws SQLException
	 {
	  connection.close();
	 }

}
