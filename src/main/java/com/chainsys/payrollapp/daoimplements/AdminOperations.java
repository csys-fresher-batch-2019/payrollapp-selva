package com.chainsys.payrollapp.daoimplements;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.chainsys.payrollapp.dao.*;
import com.chainsys.payrollapp.model.*;
import com.chainsys.payrollapp.model.AdminModel.FoodandCab;
import com.chainsys.payrollapp.util.Connections;
import com.chainsys.payrollapp.util.JdbcUtil;
import com.chainsys.payrollapp.util.Logger;

public class AdminOperations implements AdminDAO 
{
	static Logger logger = Logger.getInstance();
	public int addUsers(AdminModel a)
	{
		String sql = "insert into employee(emp_id,emp_name,designation,"+
		"leaves_taken,salary,total_leaves,food,cab_facility,email,experience)"+
		"values(?,?,?,0,0,12,?,?,?,0)";
		Object[] params = { a.getEmpId() , a.getEmpName(), a.getDesignation(),a.getFoodFacility(),a.getCabFacility(),a.getEmail()};
		int rows = JdbcUtil.executeUpdate(sql,params);
		
		insertDeductionDetails(a);
		
		insertLoginDetails(a);
		
		insertCreditDetails(a);
		
		insertBioDetails(a);
		
		insertSalaryDetails(a);
		
		return rows;
	}

	public void insertSalaryDetails(AdminModel a)
	{
		String sql = "insert into final_salary(emp_id)values(?)";
		JdbcUtil.executeUpdate(sql,a.getEmpId());
	}

	public void insertBioDetails(AdminModel a)
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

	public int insertDeductionDetails(AdminModel a ) 
	{
		String sql = "insert into deductions(emp_id,food_deduction,cab_deduction,loss_of_pay,provident_fund)values(?,?,?,0,0)";
		int foodDeduction = 0;
		int cabDeduction = 0;
		int rows = 0;
		if(a.getFoodFacility().contentEquals("Y")||a.getFoodFacility().contentEquals("y"))
		{
			a.setFoodFacility(FoodandCab.Y.toString()) ;
			foodDeduction = 500;
		}
		else
			a.setFoodFacility(FoodandCab.N.toString());
		if(a.getCabFacility().contentEquals("Y") || a.getCabFacility().contentEquals("y"))
		{
			a.setCabFacility(FoodandCab.Y.toString());
			cabDeduction = 2000;
		}
		else
			a.setCabFacility(FoodandCab.N.toString());
		try {
			rows = JdbcUtil.executeUpdate(sql,a.getEmpId(),foodDeduction,cabDeduction);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return rows;
	}

	public int removeUsers(int empId) 
	{
		String sql1 = "delete employee where emp_id = ?";
		String sql2 = "delete credits where emp_id = ?";
		String sql3 = "delete deductions where emp_id = ?";
		String sql4 = "delete user_login where emp_id = ?";
		String sql5 = "delete biometrices where emp_id = ?";
		String sql6 = "delete final_salary where emp_id = ?";
		String[] sql = {sql6,sql2,sql3,sql4,sql5,sql1};
		int rows = 0;
		try {
			for(String query:sql) {
				JdbcUtil.executeUpdate(query,empId);
				rows = rows+1;
			}
		} 
		catch (Exception e) {
			throw new RuntimeException(e);
		}
		return rows;
	}
	public int calculateLOP() throws ClassNotFoundException
	{
		String sql = "select emp_id from deductions";
		int rows = 0;
		try(Connection con = Connections.connect();
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
							int lossOfPay = 0;
							int leaves = prs.getInt("leaves_taken");
							int salary = prs.getInt("salary");
							if(leaves>12)
								lossOfPay = (int)(leaves*(salary/30));
							String sql2 = "update deductions set loss_of_pay = ? where emp_id = ?";
							rows = JdbcUtil.executeUpdate(sql2,lossOfPay,rs.getInt("emp_id"));
						}
					}
				}
			}
		}
		catch(SQLException e)
		{
			throw new RuntimeException(e);
		}
		return rows;
	}

	public int resetPassword(int empId) 
	{
		String sql = "update user_login set passwd = 'pass123',active = 0 where emp_id = ?";
		int rows = JdbcUtil.executeUpdate(sql,empId);
		return rows;
	}


}
