package com.chainsys.PayrollApp.daoimplements;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
			logger.error(e);
			return null;
		}
	}

	public static String login(int EmpId, String password) {
		String designation = null;
		try (Connection con = UserLogin.connect();
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("select * from user_login where emp_id =" + EmpId);) {
			String res = "activate";
			String res1 = "wrong password";
			String res2 = "Not a user";
			if (rs.next()) {
				String dbPassword = rs.getString("passwd");
				int active = rs.getInt("active");
				designation = rs.getString("designation").toUpperCase();

				if (password.equals(dbPassword)) {
					if (active == 1)
						return designation;
					else
						return res;

				} else
					return res1;// if password not matched

			} else
				return res2;// if user not found return 3
		} catch (SQLException e) {
			logger.error(e);
			return null;
		}
	}

	/*
	 * public static void goToDesignation(String designation, String[] arg) throws
	 * Exception { switch(designation) { case "ADMIN": AdminWorkSpace.main(arg);
	 * break; case "CONSULTANT": ConsultantWorkspace.main(arg); break; case
	 * "DEVELOPER": DeveloperWorkspace.main(arg); break; case "ACCOUNTANT":
	 * AccountantWorkSpace.main(arg); break; case "HR": HrWorkSpace.main(arg);
	 * break; } }return 4;
	 * 
	 * }
	 */
	public static boolean UpdatePassword(String newPassword, String conPassword, int EmpId) throws Exception {
		try (Connection con = UserLogin.connect(); Statement stmt = con.createStatement();) {
			if (newPassword.equals(conPassword))
				if (!stmt.execute(
						"update user_login set passwd = '" + newPassword + "',active = 1 where emp_id = " + EmpId)) {
					return true;
				}
		} catch (SQLException e) {
			logger.error(e);
		}
		return false;

	}
}
