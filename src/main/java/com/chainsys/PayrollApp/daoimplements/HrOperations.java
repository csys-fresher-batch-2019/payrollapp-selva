package com.chainsys.PayrollApp.daoimplements;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.chainsys.PayrollApp.dao.HrDAO;
import com.chainsys.PayrollApp.model.HrModel;
import com.chainsys.PayrollApp.util.JdbcUtil;
import com.chainsys.PayrollApp.util.Logger;

public class HrOperations implements HrDAO
{
	static Logger logger = Logger.getInstance();
	public String addGrade(int id,int grade)
	{
		JdbcUtil.executeUpdate("update employee set performance_grade = ? where emp_id = ?",grade,id);
		return "Sucessfully updated";
	}	
	public String addBasepay(int id,int basepay)
	{
		JdbcUtil.executeUpdate("update employee set salary = ? where emp_id = ?",basepay,id);
		return "Sucessfully updated";	
	}
	public String addCredit(int id,int allowance) 
	{
		JdbcUtil.executeUpdate("update credits set allowance = ? where emp_id = ?", allowance,id);
		return "Sucessfully updated";
	}
	public ArrayList<HrModel> viewLeaveApplication() 
	{
		String sql = "select emp_id,from_leave_date,to_leave_date,reason from leave_info where  status = 'PENDING' ";
		try(Connection con = UserLogin.connect();
		PreparedStatement pst = con.prepareStatement(sql);
		ResultSet rs = pst.executeQuery();)
		{
			ArrayList<HrModel> leaveDetails = new ArrayList<>();
			while(rs.next())
			{
				HrModel h = new HrModel();
				h.setId(rs.getInt("emp_id"));
				h.setFromDate(rs.getString("from_leave_date"));
				h.setToDate(rs.getString("to_leave_date"));
				h.setReason(rs.getString("reason"));
				leaveDetails.add(h);
			}
			return leaveDetails;
		}
		
		catch(Exception e)
		{
			logger.error(e);
			return null;
		}
		
	}
}