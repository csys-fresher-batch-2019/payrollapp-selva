package com.chainsys.PayrollApp.daoimplements;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.chainsys.PayrollApp.util.JdbcUtil;

public class AccountantOperations {
	
	public String calculatePF() throws Exception 
	{
		Connection con = UserLogin.connect();
		String sql = "select emp_id from deductions";
		PreparedStatement pst = con.prepareStatement(sql);
		ResultSet rs = pst.executeQuery();
		while(rs.next())
		{
			String sql1 = "select salary from employee where emp_id = ?";
			PreparedStatement pst1 = con.prepareStatement(sql1);
			pst1.setInt(1,rs.getInt("emp_id"));
			ResultSet prs = pst1.executeQuery();
			while(prs.next())
			{
				int salary = prs.getInt("salary");
				int PFund = (int)(salary *(0.15));
				JdbcUtil.executeUpdate("update deductions set provident_fund = ? where emp_id = ?",PFund,rs.getInt("emp_id"));
			}
		}
		con.close();
		return "Calculated";
	}
	public String calculateIncrement() throws Exception 
	{
		Connection con = UserLogin.connect();
		String sql = "select emp_id from credits";
		PreparedStatement pst = con.prepareStatement(sql);
		ResultSet rs = pst.executeQuery();
		while(rs.next())
		{
			String sql1 = "select performance_grade,salary from employee where emp_id = ?";
			PreparedStatement pst1 = con.prepareStatement(sql1);
			pst1.setInt(1,rs.getInt("emp_id"));
			ResultSet prs = pst1.executeQuery();
			while(prs.next())
			{
				int salary = prs.getInt("salary");
				int increment = (int)(prs.getInt("performance_grade")*(salary *(0.2)));
				JdbcUtil.executeUpdate("update credits set salary_increment = ? where emp_id = ?",increment,rs.getInt("emp_id"));
			}
		}
		con.close();
		return "Calculated";
	}
	public String calculatesalary() throws Exception 
	{
		Connection con = UserLogin.connect();
		String sql = "select emp_id from final_salary";
		PreparedStatement pst = con.prepareStatement(sql);
		ResultSet rs = pst.executeQuery();
		while(rs.next())
		{
			CallableStatement statement = con.prepareCall("{call calculate_salary(?)}");
			statement.setInt(1,rs.getInt("emp_id"));
			statement.execute();
		}
		con.close();
		return "Calculated";
	}
	public String markAttendance() throws Exception 
	{
		Connection con = UserLogin.connect();
		String sql = "select emp_id from biometrices";
		PreparedStatement pst = con.prepareStatement(sql);
		ResultSet rs = pst.executeQuery();
		while(rs.next())
		{

			CallableStatement stmt = con.prepareCall("{call attendance_check(?)}");
			stmt.setInt(1,rs.getInt("emp_id"));
			stmt.execute();
		}
		String query = "update biometrices set swipe_coun = 0";
		PreparedStatement ps = con.prepareStatement(query);
		ps.execute();
		con.close();
		return "Attandance Noted";
	}
}

