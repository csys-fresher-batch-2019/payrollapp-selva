package com.chainsys.PayrollApp.DAOImplements;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.chainsys.PayrollApp.DataAccessObject.*;
import com.chainsys.PayrollApp.Model.*;
import com.chainsys.PayrollApp.util.JdbcUtil;

public class AdminOperations implements AdminDAO 
{
	public String addUsers(AdminModel a) throws Exception 
	{
		//Connection con = UserLogin.connect();
		int foodDetection = 0;
		int cabDeTection = 0;
		String sql = "insert into employee(emp_id,emp_name,designation,"+
		"leaves_taken,salary,total_leaves,food,cab_facility,email,experience)"+
		"values(?,?,?,0,0,12,?,?,?,0)";
		Object[] params = { a.empId , a.empName, a.designation,a.foodFacility,a.cabFacility,a.email};
		int rows = JdbcUtil.executeUpdate(sql,params);
		if(a.foodFacility.equals("Y") && a.cabFacility.equals("Y"))
		{
			foodDetection = 500;
			cabDeTection = 2000;
		}
		else if(a.foodFacility.equals("Y") && a.cabFacility.equals("N"))
		{
			foodDetection = 500;
			cabDeTection = 0;		}
		else if(a.foodFacility.equals("N") && a.cabFacility.equals("Y"))
		{
			foodDetection = 0;
			cabDeTection = 2000;
		}
		String sql1 = "insert into deductions(emp_id,food_deduction,cab_deduction,loss_of_pay,provident_fund)values(?,?,?,0,0)";
		int row = JdbcUtil.executeUpdate(sql1,a.empId,foodDetection,cabDeTection);
		String sql2 = "insert into user_login(emp_id,passwd,designation)values(?,'pass123',?)";
		int rows1 = JdbcUtil.executeUpdate(sql2, a.empId , a.designation );
		String sql3 = "insert into credits(emp_id)values(?)";
		int r = JdbcUtil.executeUpdate(sql3,a.empId);
		JdbcUtil.executeUpdate("insert into biometrices(emp_id,swipe_coun)values(?,0)",a.empId);
		JdbcUtil.executeUpdate("insert into final_salary(emp_id)values(?)",a.empId);
		return "Sucessfully Inserted";
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
