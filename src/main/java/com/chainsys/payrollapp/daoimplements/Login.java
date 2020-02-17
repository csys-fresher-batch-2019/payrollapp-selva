package com.chainsys.payrollapp.daoimplements;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.chainsys.payrollapp.util.Connections;
import com.chainsys.payrollapp.util.JdbcUtil;
import com.chainsys.payrollapp.util.Logger;

public class Login 
{
	static Logger logger = Logger.getInstance();


	public static String login(int EmpId, String password) throws ClassNotFoundException 
	{
		String designation = null;
		String sql = "select * from user_login where emp_id = ?";
		try (Connection con = Connections.connect();
				PreparedStatement stmt = con.prepareStatement(sql);) 
		{
			stmt.setInt(1,EmpId);
			try(ResultSet rs = stmt.executeQuery();)
			{
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
					return res2;// if user not found return 3
			}
			catch(Exception e)
			{
				throw new RuntimeException(e);
			}
		} 
		catch (SQLException e) 
		{
			throw new RuntimeException(e);
		}
	}
	public static int UpdatePassword(String newPassword, String conPassword, int EmpId) {
		String sql = "update user_login set passwd = ?,active = 1 where emp_id = ?";
		int rows = JdbcUtil.executeUpdate(sql, newPassword,EmpId);
		return rows;
	}

}
