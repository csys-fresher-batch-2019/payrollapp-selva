package com.chainsys.PayrollApp.DAOImplements;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.chainsys.PayrollApp.util.JdbcUtil;


public class SaveChanges {
	public static void doCommit() throws Exception
	{
		Connection con = UserLogin.connect();
		String sql1 = "commit";
		PreparedStatement pst = con.prepareStatement(sql1);
		pst.executeUpdate();
		con.close();

	}
	public static void main(String[] args) throws Exception {
		String sql  = "insert into account_details values(?,?)";
		System.out.println(sql);
		JdbcUtil.executeUpdate(sql, 1001,12345678);
	}

}
