package com.chainsys.payrollapp.daoimplements;
/*
import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import com.chainsys.PayrollApp.util.JdbcUtil;
import java.sql.SQLException;

import com.chainsys.PayrollApp.util.Logger;
*/

public class SaveChanges {
	/*static Logger logger = Logger.getInstance();
	public static void doCommit()
	{
		String sql1 = "commit";
		try{
			Connection con = UserLogin.connect();
			PreparedStatement pst = con.prepareStatement(sql1);
			pst.executeUpdate();
		}
		catch(SQLException e)
		{
			logger.error(e);
		}
	}
	public static void main(String[] args) throws Exception {
		//String sql  = "insert into account_details values(?,?)";
		//System.out.println(sql);
		//JdbcUtil.executeUpdate(sql, 1001,12345678);
		SaveChanges s = new SaveChanges();
		s.BloodGroup();
	}
	
	public void BloodGroup() throws SQLException
	{
		Connection con = Connections.connect();
		String sql = "select email from blood_group_details where blood_group = 'x' ";
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		String email = "";
		while(rs.next())
		{
			//System.out.println("!!");
			email = rs.getString("email");
			System.out.println(email);
			SendMailSSL.send("payrollmavenproject@gmail.com","Pass1234*", email,"Update your Blood group","Submit your blood group datails", 0);
		}
		
	}*/

}
