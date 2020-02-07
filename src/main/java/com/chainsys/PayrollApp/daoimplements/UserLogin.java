package com.chainsys.PayrollApp.daoimplements;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.chainsys.PayrollApp.util.JdbcUtil;
import com.chainsys.PayrollApp.util.Logger;

public class UserLogin {
	private UserLogin() {
		throw new IllegalStateException("Utility class");
	}

	static Logger logger = Logger.getInstance();

	public static Connection connect() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "system", "oracle");
		} catch (Exception e) {
			throw new RuntimeException("Unaable to get connection");
		}
	}

	public static String login(int EmpId, String password) {
		String designation = null;
		String sql = "select * from user_login where emp_id = ?";
		try (Connection con = UserLogin.connect();
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
				logger.error(e);
				return null;
			}
		} 
		catch (SQLException e) {
			logger.error(e);
			return null;
		}
	}
	public static void UpdatePassword(String newPassword, String conPassword, int EmpId) {
		String sql = "update user_login set passwd = ?,active = 1 where emp_id = ?";
		JdbcUtil.executeUpdate(sql, newPassword,EmpId);
	}
}
