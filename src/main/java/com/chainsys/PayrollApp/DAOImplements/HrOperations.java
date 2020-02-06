package com.chainsys.PayrollApp.DAOImplements;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.chainsys.PayrollApp.DataAccessObject.HrDAO;
import com.chainsys.PayrollApp.Model.HrModel;
import com.chainsys.PayrollApp.util.JdbcUtil;

public class HrOperations implements HrDAO
{
	public String addGrade(int id,int grade) throws Exception
	{
		String str;
		int rows = JdbcUtil.executeUpdate("update employee set performance_grade = ? where emp_id = ?",grade,id);
		if(rows!=0)
			str = "Sucessfully updated";
		else
			str = "Update Failure";
		return str;
	}	
	public String addBasepay(int id,int basepay) throws Exception
	{
		String str;
		int rows = JdbcUtil.executeUpdate("update employee set salary = ? where emp_id = ?",basepay,id);
		if(rows!=0)
			str = "Sucessfully updated";
		else
			str = "Update Failure";
		return str;	
	}
	public String addCredit(int id,int allowance) throws Exception
	{
		String str;
		int rows = JdbcUtil.executeUpdate("update credits set allowance = ? where emp_id = ?", allowance,id);
		if(rows!=0)
			str = "Sucessfully updated";
		else
			str = "Update Failure";
		return str;
	}
	public ArrayList<HrModel> viewLeaveApplication() throws Exception
	{
		Connection con = UserLogin.connect();
		String sql = "select emp_id,from_leave_date,to_leave_date,reason from leave_info where  status = 'PENDING' ";
		PreparedStatement pst = con.prepareStatement(sql);
		ResultSet rs = pst.executeQuery();
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
		con.close();
		return leaveDetails;
	}
}