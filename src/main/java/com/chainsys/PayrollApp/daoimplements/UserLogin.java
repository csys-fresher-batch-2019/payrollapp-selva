package com.chainsys.PayrollApp.daoimplements;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.chainsys.PayrollApp.*;


public class UserLogin 
{
	public static Connection connect() throws Exception
	{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connections = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","oracle");
		return connections;
	}
	public static int login(int EmpId,String password) throws Exception
	{
		Connection con = UserLogin.connect();
		Statement stmt = con.createStatement();
		if(stmt.execute("select emp_id from user_login where emp_id ="+EmpId))
		{
			ResultSet rs = stmt.executeQuery("select * from user_login where emp_id ="+EmpId);
			if(rs.next())
				if(password.equals(rs.getString("passwd")) && rs.getInt("active")==1)
				{
					String designation = rs.getString("designation").toUpperCase();
					String [] arg = { ""+EmpId};
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
					return 1;
				}
				else if(password.equals(rs.getString("passwd")) && rs.getInt("active")==0)
					return 2;
				else
					return 3;
		}
		return 4;
	}
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
