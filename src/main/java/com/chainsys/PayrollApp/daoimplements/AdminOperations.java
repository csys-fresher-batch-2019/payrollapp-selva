package com.chainsys.PayrollApp.daoimplements;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.chainsys.PayrollApp.dao.*;
import com.chainsys.PayrollApp.model.*;
import com.chainsys.PayrollApp.util.JdbcUtil;
import com.chainsys.PayrollApp.util.Logger;

public class AdminOperations implements AdminDAO 
{
	static Logger logger = Logger.getInstance();
	public String addUsers(AdminModel a)
	{
		String sql = "insert into employee(emp_id,emp_name,designation,"+
		"leaves_taken,salary,total_leaves,food,cab_facility,email,experience)"+
		"values(?,?,?,0,0,12,?,?,?,0)";
		Object[] params = { a.getEmpId() , a.getEmpName(), a.getDesignation(),a.getFoodFacility(),a.getCabFacility(),a.getEmail()};
		JdbcUtil.executeUpdate(sql,params);
		
		insertDeductionDetails(a);
		
		insertLoginDetails(a);
		
		insertCreditDetails(a);
		
		insertBoiDetails(a);
		
		insertSalaryDetails(a);
		
		return "Sucessfully Inserted";
	}

	public void insertSalaryDetails(AdminModel a)
	{
		String sql = "insert into final_salary(emp_id)values(?)";
		JdbcUtil.executeUpdate(sql,a.getEmpId());
	}

	public void insertBoiDetails(AdminModel a)
	{
		String sql = "insert into biometrices(emp_id,swipe_coun)values(?,0)";
		JdbcUtil.executeUpdate(sql,a.getEmpId());
	}

	public void insertCreditDetails(AdminModel a)
	{
		String sql = "insert into credits(emp_id)values(?)";
		JdbcUtil.executeUpdate(sql,a.getEmpId());
	}

	public void insertLoginDetails(AdminModel a) 
	{
		String sql = "insert into user_login(emp_id,passwd,designation)values(?,'pass123',?)";
		JdbcUtil.executeUpdate(sql, a.getEmpId() , a.getDesignation() );
	}

	public void insertDeductionDetails(AdminModel a ) 
	{
		String sql = "insert into deductions(emp_id,food_deduction,cab_deduction,loss_of_pay,provident_fund)values(?,?,?,0,0)";
		int foodDeduction = 0;
		int cabDeduction = 0;
		if(a.getFoodFacility().equals("Y") && a.getCabFacility().equals("Y"))
		{
			foodDeduction = 500;
			cabDeduction = 2000;
		}
		else if(a.getFoodFacility().equals("Y") && a.getCabFacility().equals("N"))
		{
			foodDeduction = 500;
			cabDeduction = 0;		}
		else if(a.getFoodFacility().equals("N") && a.getCabFacility().equals("Y"))
		{
			foodDeduction = 0;
			cabDeduction = 2000;
		}
		JdbcUtil.executeUpdate(sql,a.getEmpId(),foodDeduction,cabDeduction);
	}

	public String removeUsers(int empId) 
	{
		String sql = "update employee set active = 0 where emp_id = ?";
		JdbcUtil.executeUpdate(sql,empId);
		return "Sucessfully Removed" ;
	}
	public String calculateLOP()
	{
		String sql = "select emp_id from deductions";
		try(Connection con = UserLogin.connect();
		PreparedStatement pst = con.prepareStatement(sql);
		ResultSet rs = pst.executeQuery();)
		{
			while(rs.next())
			{
				String sql1 = "select leaves_taken,salary from employee where emp_id = ?";
				try(PreparedStatement pst1 = con.prepareStatement(sql1);)
				{
					pst1.setInt(1,rs.getInt("emp_id"));
					try(ResultSet prs = pst1.executeQuery();)
					{
						while(prs.next())
						{
							int salary = prs.getInt("salary");
							int lossOfPay = (int)(prs.getInt("leaves_taken")*(salary/30));
							String sql2 = "update deductions set loss_of_pay = ? where emp_id = ?";
							JdbcUtil.executeUpdate(sql2,lossOfPay,rs.getInt("emp_id"));
						}
					}
				}
			}
		}
		catch(SQLException e)
		{
			logger.error(e);
		}
		return "Successfully Updated";
	}

	public void resetPassword(int empId) 
	{
		String sql = "update user_login set passwd = 'pass123',active = 0 where emp_id = ?";
		JdbcUtil.executeUpdate(sql,empId);
	}


}
