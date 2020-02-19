package com.chainsys.payrollapp.daoimplements;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.chainsys.payrollapp.dao.HrDAO;
import com.chainsys.payrollapp.model.HrModel;
import com.chainsys.payrollapp.util.Connections;
import com.chainsys.payrollapp.util.JdbcUtil;
public class HrOperations implements HrDAO
{
	public int addGrade(int id,int grade)
	{
		int rows = 0;
		rows = JdbcUtil.executeUpdate("update employee set performance_grade = ? where emp_id = ?",grade,id);
		return rows;
	}	
	public int addBasepay(int id,int basepay)
	{
		int rows = 0;
		rows = JdbcUtil.executeUpdate("update employee set basepay = ? where emp_id = ?",basepay,id);
		return rows;	
	}
	public int addCredit(int allowance,int id) 
	{
		int rows = 0;
		rows = JdbcUtil.executeUpdate("update credits set allowance = ? where emp_id = ?", allowance,id);
		return rows;
	}
	public ArrayList<HrModel> viewLeaveApplication() 
	{
		String sql = "select li.emp_id,from_leave_date,to_leave_date,reason from leave_info li " + 
				" inner join employee e on e.emp_id=li.emp_id and e.designation !='HR'and li.status='PENDING' ";
		try(Connection con = Connections.connect();
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
			throw new RuntimeException(e);
		}
	}
}