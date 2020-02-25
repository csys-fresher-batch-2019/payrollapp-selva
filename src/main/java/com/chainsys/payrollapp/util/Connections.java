package com.chainsys.payrollapp.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connections 
{
	public Connections() 
	{
		throw new IllegalStateException("Utility class");
	}

	public static Connection connect() 
	{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "system", "oracle");
			return DriverManager.getConnection("jdbc:oracle:thin:@ 13.235.147.120:1521:XE", "selva", "selva");
	} 
		catch (Exception e) 
		{
			throw new RuntimeException("Unable to get connection");
		}
	}
}
	