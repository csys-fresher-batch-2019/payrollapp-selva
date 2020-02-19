package com.chainsys.payrollapp.daoimplements;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.chainsys.payrollapp.dao.AccountantDAO;
import com.chainsys.payrollapp.util.Connections;
import com.chainsys.payrollapp.util.JdbcUtil;
import com.chainsys.payrollapp.util.Logger;


public class AccountantOperations implements AccountantDAO {
	static Logger logger = Logger.getInstance();

	public int calculatePF() throws ClassNotFoundException  {
		String sql = "select emp_id from deductions";
		int rows = 0;
		try (Connection con = Connections.connect(); PreparedStatement pst = con.prepareStatement(sql)) {
			try (ResultSet rs = pst.executeQuery();) {
				while (rs.next()) {
					String sql1 = "select basepay from employee where emp_id = ?";
					try (PreparedStatement pst1 = con.prepareStatement(sql1);) {
						pst1.setInt(1, rs.getInt("emp_id"));
						try (ResultSet prs = pst1.executeQuery();) {
							while (prs.next()) {
								int salary = prs.getInt("basepay");
								int PFund = (int) (salary * (0.15));
								String query = "update deductions set provident_fund = ? where emp_id = ?";
								rows = JdbcUtil.executeUpdate(query, PFund, rs.getInt("emp_id"));
							}
						}
						catch(SQLException e)
						{
							throw new RuntimeException("Unable to get connection");						}
					}
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException("Unable to get connection");
		}

		return rows;
	}

	public int calculateIncrement() throws Exception {
		String sql = "select emp_id from credits";
		int rows = 0;
		try (Connection con = Connections.connect(); PreparedStatement pst = con.prepareStatement(sql)) {
			try (ResultSet rs = pst.executeQuery();) {
				while (rs.next()) {
					String sql1 = "select performance_grade,basepay from employee where emp_id = ?";
					try (PreparedStatement pst1 = con.prepareStatement(sql1);) {
						int empId = rs.getInt("emp_id");
						pst1.setInt(1, empId);
						try (ResultSet prs = pst1.executeQuery();) {
							while (prs.next()) {
								int salary = prs.getInt("basepay");
								int inc = (int)(salary * (0.2));
								int increment = (prs.getInt("performance_grade") * inc);
								String query = "update credits set salary_increment = ? where emp_id = ?";
								rows = JdbcUtil.executeUpdate(query, increment, empId);

							}
						}
					}
				}
			}
		}catch (SQLException e) {
			throw new RuntimeException("Unable to get connection");		
			}

		return rows;
	}
	public int calculatesalary() throws Exception 
	{
		String sql = "select emp_id from final_salary";
		int rows = 0;
		try(Connection con = Connections.connect();
		PreparedStatement pst = con.prepareStatement(sql);)
		{
			try(ResultSet rs = pst.executeQuery();)
			{
				while (rs.next()) 
				{
					try(CallableStatement statement = con.prepareCall("{call calculate_salary(?)}");)
					{
						statement.setInt(1, rs.getInt("emp_id"));
						rows = statement.executeUpdate();
					}
				}
			}
		}
		catch(SQLException e)
		{
			throw new RuntimeException("Unable to get connection");		
			}
		return rows;
	}

	public int markAttendance() throws Exception  {
		String sql = "select emp_id from biometrices";
		int rows = 0;
		try(Connection con = Connections.connect();
		PreparedStatement pst = con.prepareStatement(sql);)
		{
			try(ResultSet rs = pst.executeQuery();)
			{
				while (rs.next()) {
					try(CallableStatement stmt = con.prepareCall("{call attendance_check(?)}");)
					{
						stmt.setInt(1, rs.getInt("emp_id"));
						rows = stmt.executeUpdate();
					}
				}
			}
		}
		catch(SQLException e)
		{
			throw new RuntimeException("Unable to get connection");
		}
		return rows;
	}
	
	public int GeneratePaySlip() throws Exception {
		PaySlip gp = new PaySlip();
		int workDone = gp.EmployeeDetails();
		return workDone;
	}


	public int sendPayslip() {
		
		return 1;
	}
}
