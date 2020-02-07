package com.chainsys.PayrollApp.daoimplements;

import com.chainsys.PayrollApp.model.LeaveApplicationModel;
import com.chainsys.PayrollApp.model.LeaveApplicationModel.LeaveStatus;
import com.chainsys.PayrollApp.util.JdbcUtil;
import com.chainsys.PayrollApp.util.Logger;

public class LeaveApplication {
	static Logger logger = Logger.getInstance();
	public String applyLeave(int empId, LeaveApplicationModel l)
	{
		String sql = "insert into leave_info(emp_id,from_leave_date,to_leave_date,reason)values(?,to_date(?,'yyyy/MM/dd'),to_date(?,'yyyy/MM/dd'),?)";
		Object[] params = {l.getFromDate(),l.getToDate(),l.getReasonForLeave()};
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
		String sql = "update leave_info set status = ? where emp_id = ?";
		String status = "";
		if(option==1) {
			status = LeaveStatus.APPROVED.toString();
			SendMailSSL.send("payrollmavenproject@gmail.com","Pass1234*","selvamanikandan.ks@gmail.com","Leave Application Accepcted",status,eid);
		}
		else {
			status = LeaveStatus.NOT_APPROVED.toString();
			SendMailSSL.send("payrollmavenproject@gmail.com","Pass1234*","selvamanikandan.ks@gmail.com","Leave Application Rejected",status,eid);
		}
		try
		{
			JdbcUtil.executeUpdate(sql,eid,status);
		}
		catch(Exception e)
		{
			logger.error(e);
		}
		return "Updated";
	}
	

}
