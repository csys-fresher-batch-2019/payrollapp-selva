package com.chainsys.PayrollApp.DAOImplements;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.chainsys.PayrollApp.SendMailSSL;
import com.chainsys.PayrollApp.Model.LeaveApplicationModel;
import com.chainsys.PayrollApp.Model.LeaveApplicationModel.LeaveStatus;

public class LeaveApplication {
	
	public String applyLeave(int empId, LeaveApplicationModel l) throws Exception
	{
		Connection con = UserLogin.connect();
		String sql = "insert into leave_info(emp_id,from_leave_date,to_leave_date,reason)values(?,?,?,?)";
		PreparedStatement pst = con.prepareStatement(sql);
		pst.setInt(1,empId);
		java.sql.Date date = java.sql.Date.valueOf(l.fromDate);
		pst.setDate(2,date);
		java.sql.Date dat = java.sql.Date.valueOf(l.toDate);
		pst.setDate(3, dat);
		pst.setString(4,l.reasonForLeave);
		int rows = pst.executeUpdate();
		con.close();
		if(rows!=0)
			return "Leave Applied";
		else
			return "Application Failed";
		
	}

	public static String Status(int eid, int option) throws Exception {
		Connection con = UserLogin.connect();
		String sql = "update leave_info set status = ? where emp_id = ?";
		PreparedStatement pst = con.prepareStatement(sql);		
		if(option==1) {
			pst.setString(1,LeaveStatus.APPROVED.toString());
			SendMailSSL.send("payrollmavenproject@gmail.com","Pass1234*","selvamanikandan.ks@gmail.com","Leave Application Accepcted",LeaveStatus.APPROVED.toString(),eid);
		}
		else {
			pst.setString(1,LeaveStatus.NOT_APPROVED.toString());
			SendMailSSL.send("payrollmavenproject@gmail.com","Pass1234*","selvamanikandan.ks@gmail.com","Leave Application Rejected",LeaveStatus.NOT_APPROVED.toString(),eid);
		}
		pst.setInt(2,eid);
		pst.executeQuery();
		con.close();
		return "Updated";
	}
	

}
