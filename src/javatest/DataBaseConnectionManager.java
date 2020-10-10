package javatest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


import javatest.BMSException;

public class DataBaseConnectionManager 
{
	static
	{
		try
	{
		Class.forName("com.mysql.jdbc.Driver"); // class-keyword Class-package
	}
		catch(ClassNotFoundException e)
	{
		e.printStackTrace();
	}
	
	}
	public static Connection getConnection()
	{
		Connection con=null;
		try{
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/javatest","root","dummy");
		}catch(SQLException e)
		{
			e.printStackTrace();
			throw new BMSException("database error ,please contact system admin");
		}
	
	return con;
	}
}
