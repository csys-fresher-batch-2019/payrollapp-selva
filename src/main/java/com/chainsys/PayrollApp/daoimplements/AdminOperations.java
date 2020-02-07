package com.chainsys.PayrollApp.daoimplements;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.chainsys.PayrollApp.dao.*;
import com.chainsys.PayrollApp.model.*;
import com.chainsys.PayrollApp.util.JdbcUtil;

public class AdminOperations implements AdminDAO 
{
	public String addUsers(AdminModel a) throws Exception 
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

	public void insertSalaryDetails(AdminModel a) throws Exception 
	{
		String sql = "insert into final_salary(emp_id)values(?)";
		JdbcUtil.executeUpdate(sql,a.getEmpId());
	}

	public void insertBoiDetails(AdminModel a) throws Exception 
	{
		String sql = "insert into biometrices(emp_id,swipe_coun)values(?,0)";
		JdbcUtil.executeUpdate(sql,a.getEmpId());
	}

	public void insertCreditDetails(AdminModel a) throws Exception {
		String sql = "insert into credits(emp_id)values(?)";
		JdbcUtil.executeUpdate(sql,a.getEmpId());
	}

	public void insertLoginDetails(AdminModel a) throws Exception {
		String sql = "insert into user_login(emp_id,passwd,designation)values(?,'pass123',?)";
		JdbcUtil.executeUpdate(sql, a.getEmpId() , a.getDesignation() );
	}

	public void insertDeductionDetails(AdminModel a ) throws Exception {
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

	public String removeUsers(int empId) throws Exception {
		String sql = "update employee set active = 0 where emp_id = ?";
		JdbcUtil.executeUpdate(sql,empId);
		return "Sucessfully Removed" ;
	}
	public String calculateLOP() throws Exception 
	{
		Connection con = UserLogin.connect();
		String sql = "select emp_id from deductions";
		PreparedStatement pst = con.prepareStatement(sql);
		ResultSet rs = pst.executeQuery();
		while(rs.next())
		{
			String sql1 = "select leaves_taken,salary from employee where emp_id = ?";
			PreparedStatement pst1 = con.prepareStatement(sql1);
			pst1.setInt(1,rs.getInt("emp_id"));
			ResultSet prs = pst1.executeQuery();
			while(prs.next())
			{
				int salary = prs.getInt("salary");
				int lossOfPay = (int)(prs.getInt("leaves_taken")*(salary/30));
				String sql2 = "update deductions set loss_of_pay = ? where emp_id = ?";
				PreparedStatement pst2 = con.prepareStatement(sql2);
				pst2.setInt(1,lossOfPay);
				pst2.setInt(2,rs.getInt("emp_id"));
				pst2.executeUpdate();
			}
		}
		con.close();
		return "Successfully Updated";
	}

	public void resetPassword(int empId) throws Exception {
		String sql = "update user_login set passwd = 'pass123',active = 0 where emp_id = ?";
		JdbcUtil.executeUpdate(sql,empId);
	}


}
