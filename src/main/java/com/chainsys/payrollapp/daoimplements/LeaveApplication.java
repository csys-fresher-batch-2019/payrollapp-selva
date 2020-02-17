package com.chainsys.payrollapp.daoimplements;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.chainsys.payrollapp.model.LeaveApplicationModel;
import com.chainsys.payrollapp.model.LeaveApplicationModel.LeaveStatus;
import com.chainsys.payrollapp.util.Connections;
import com.chainsys.payrollapp.util.JdbcUtil;
import com.chainsys.payrollapp.util.Logger;
import com.chainsys.payrollapp.util.SendMailSSL;


public class LeaveApplication {
	static Logger logger = Logger.getInstance();
	public String applyLeave(int empId, LeaveApplicationModel l)
	{
		String sql = "insert into leave_info(emp_id,from_leave_date,to_leave_date,reason)values(?,to_date(?,'yyyy/MM/dd'),to_date(?,'yyyy/MM/dd'),?)";
		Object[] params = {empId,l.getFromDate(),l.getToDate(),l.getReasonForLeave()};
		try
		{
			JdbcUtil.executeUpdate(sql,params);
		}
		catch(Exception e)
		{
			logger.error(e);
		}
		return "Applied";
	}

	public static String Status(int eid, int option) {
		String sql = "update leave_info set status = ? where emp_id = ? and status = 'PENDING'";
		String query = "update employee set leaves_taken = leaves_taken+1,total_leaves = total_leaves-1 where emp_id = ?";
		String sql1 = "select email from employee where emp_id = ?";
		String status = "";
		String email = "";
		try(Connection con = Connections.connect();
			PreparedStatement pst = con.prepareStatement(sql1);)
		{
			pst.setInt(1,eid);
			ResultSet rs = pst.executeQuery();
			if(rs.next())
			{
				email = rs.getString("email");
			}
			if(option==1) 
				status = LeaveStatus.APPROVED.toString();
			else 
				status = LeaveStatus.NOT_APPROVED.toString();
			JdbcUtil.executeUpdate(sql,status,eid);
			JdbcUtil.executeUpdate(query,eid);
			SendMailSSL.send("payrollmavenproject@gmail.com","Pass1234*",email,"Leave Application "+status,status,eid);
		}
		catch(Exception e)
		{
			logger.error(e);
		}
		return "Updated";
	}
	

}
