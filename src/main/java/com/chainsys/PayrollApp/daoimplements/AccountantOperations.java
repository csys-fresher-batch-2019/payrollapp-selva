package com.chainsys.PayrollApp.daoimplements;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.chainsys.PayrollApp.util.DBExceptions;
import com.chainsys.PayrollApp.util.ErrorMessages;
import com.chainsys.PayrollApp.util.JdbcUtil;
import com.chainsys.PayrollApp.util.Logger;

public class AccountantOperations {
	static Logger logger = Logger.getInstance();

	public String calculatePF() throws DBExceptions {
		String sql = "select emp_id from deductions";
		try (Connection con = UserLogin.connect(); PreparedStatement pst = con.prepareStatement(sql)) {
			try (ResultSet rs = pst.executeQuery();) {
				while (rs.next()) {
					String sql1 = "select salary from employee where emp_id = ?";
					try (PreparedStatement pst1 = con.prepareStatement(sql1);) {
						pst1.setInt(1, rs.getInt("emp_id"));
						try (ResultSet prs = pst1.executeQuery();) {
							while (prs.next()) {
								int salary = prs.getInt("salary");
								int PFund = (int) (salary * (0.15));
								String query = "update deductions set provident_fund = ? where emp_id = ?";
								JdbcUtil.executeUpdate(query, PFund, rs.getInt("emp_id"));
							}
						}
					}
				}
			}
		} catch (SQLException e) {
			throw new DBExceptions(ErrorMessages.Error);
		}

		return "Calculated";
	}

	public String calculateIncrement() throws DBExceptions {
		String sql = "select emp_id from credits";
		try (Connection con = UserLogin.connect(); PreparedStatement pst = con.prepareStatement(sql)) {
			try (ResultSet rs = pst.executeQuery();) {
				while (rs.next()) {
					String sql1 = "select performance_grade,salary from employee where emp_id = ?";
					try (PreparedStatement pst1 = con.prepareStatement(sql1);) {
						pst1.setInt(1, rs.getInt("emp_id"));
						try (ResultSet prs = pst1.executeQuery();) {
							while (prs.next()) {
								int salary = prs.getInt("salary");
								int increment = (int) (prs.getInt("performance_grade") * (salary * (0.2)));
								String query = "update credits set salary_increment = ? where emp_id = ?";
								JdbcUtil.executeUpdate(query, increment, rs.getInt("emp_id"));

							}
						}
					}
				}
			}
		}catch (SQLException e) {
			throw new DBExceptions(ErrorMessages.Error);
		}

		return "Calculated";
	}
	public String calculatesalary() throws DBExceptions 
	{
		String sql = "select emp_id from final_salary";
		try(Connection con = UserLogin.connect();
		PreparedStatement pst = con.prepareStatement(sql);)
		{
			try(ResultSet rs = pst.executeQuery();)
			{
				while (rs.next()) 
				{
					CallableStatement statement = con.prepareCall("{call calculate_salary(?)}");
					statement.setInt(1, rs.getInt("emp_id"));
					statement.execute();
				}
			}
		}
		catch(SQLException e)
		{
			throw new DBExceptions(ErrorMessages.Error);
		}
		return "Calculated";
	}

	public String markAttendance() throws Exception {
		Connection con = UserLogin.connect();
		String sql = "select emp_id from biometrices";
		PreparedStatement pst = con.prepareStatement(sql);
		ResultSet rs = pst.executeQuery();
		while (rs.next()) {

			CallableStatement stmt = con.prepareCall("{call attendance_check(?)}");
			stmt.setInt(1, rs.getInt("emp_id"));
			stmt.execute();
		}
		String query = "update biometrices set swipe_coun = 0";
		PreparedStatement ps = con.prepareStatement(query);
		ps.execute();
		con.close();
		return "Attandance Noted";
	}
}
