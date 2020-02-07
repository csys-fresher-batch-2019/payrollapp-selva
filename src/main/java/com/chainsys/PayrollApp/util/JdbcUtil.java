package com.chainsys.PayrollApp.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import com.chainsys.PayrollApp.daoimplements.UserLogin;

public class JdbcUtil {
	static Logger logger = Logger.getInstance();
	public static void executeUpdate(String sql, Object... params) {
		try(Connection con = UserLogin.connect();
				PreparedStatement pst1 = con.prepareStatement(sql);)
		{
			int i = 1;
			for (Object param : params) {
				pst1.setObject(i, param);
				i++;
			}
			pst1.executeUpdate();
		}
		catch(Exception e)
		{
			logger.error(e);
		}
	}
/*	public static void main(String[] args) throws Exception {
		
	String sql2 = "insert into user_login(emp_id,passwd,designation)values(?,'pass123',?)";
	int rows1 = JdbcUtil.executeUpdate(sql2 );
	}*/
}
