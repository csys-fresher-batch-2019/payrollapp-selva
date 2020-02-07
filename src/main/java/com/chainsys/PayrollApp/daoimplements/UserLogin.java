package com.chainsys.PayrollApp.daoimplements;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

<<<<<<< HEAD
import com.chainsys.PayrollApp.util.Logger;
=======
import com.chainsys.PayrollApp.*;
>>>>>>> e799ebf5f2956fa0463c22559ca0643d41bfeed6

public class UserLogin 
{
	static Logger logger = Logger.getInstance();
	public static Connection connect() throws SQLException 
	{
		try 
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}
		catch(ClassNotFoundException e) 
		{
			logger.error(e);
		}
		Connection connections = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "system", "oracle");
		return connections;
				
	}

	public static String login(int EmpId, String password) throws Exception 
	{
		String designation = null;
		Connection con = UserLogin.connect();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("select * from user_login where emp_id =" + EmpId);
		String res = "activate";
		String res1 = "wrong password";
		String res2 = "Not a user";
		if (rs.next()) 
		{
			String dbPassword = rs.getString("passwd");
			int active = rs.getInt("active");
			designation = rs.getString("designation").toUpperCase();

			if (password.equals(dbPassword)) 
			{
				if (active == 1) 
					return designation;
				else 
					return res;

			}
			else 
				return res1;// if password not matched

		}
		else 
			return res2; // if user not found return 3
	}
/*
	public static void goToDesignation(String designation, String[] arg) throws Exception {
		switch(designation) 
		{
			case "ADMIN":
				AdminWorkSpace.main(arg);
				break;
			case "CONSULTANT":
				ConsultantWorkspace.main(arg);
				break;
			case "DEVELOPER":
				DeveloperWorkspace.main(arg);
				break;
			case "ACCOUNTANT":
				AccountantWorkSpace.main(arg);
				break;
			case "HR":
				HrWorkSpace.main(arg);
				break;
		}
	}return 4;

	}
*/
	public static boolean UpdatePassword(String newPassword,String conPassword,int EmpId) throws Exception
	{
		Connection con = UserLogin.connect();
		Statement stmt = con.createStatement();
		if(newPassword.equals(conPassword))
			if(!stmt.execute( "update user_login set passwd = '"+newPassword+"',active = 1 where emp_id = "+EmpId))
			{
				return true;
			}
		return false;
		
			
	}
}
