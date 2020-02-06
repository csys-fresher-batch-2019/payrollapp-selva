package com.chainsys.PayrollApp.util;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.chainsys.PayrollApp.DAOImplements.UserLogin;

public class JdbcUtil {

	public static int executeUpdate(String sql, Object... params) throws Exception {

		Connection con = UserLogin.connect();
		PreparedStatement pst1 = con.prepareStatement(sql);
		int i = 1;
		for (Object param : params) {
			pst1.setObject(i, param);
			i++;
		}
		int rows = pst1.executeUpdate();
		return rows;
	}
	public static void main(String[] args) throws Exception {
		
	String sql2 = "insert into user_login(emp_id,passwd,designation)values(?,'pass123',?)";
	int rows1 = JdbcUtil.executeUpdate(sql2 );
	}
}
