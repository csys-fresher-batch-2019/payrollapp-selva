package com.chainsys.PayrollApp;

import java.util.*;

import com.chainsys.PayrollApp.daoimplements.LeaveApplication;
import com.chainsys.PayrollApp.model.LeaveApplicationModel;

public class ApplyLeave {
	static 	Scanner scan = new Scanner(System.in);
	public String testApplyLeave(String args) throws Exception 
	{
		LeaveApplication leave = new LeaveApplication();
		LeaveApplicationModel l = new LeaveApplicationModel();
		int empId = Integer.parseInt(args);
		System.out.println("Enter the From Date :");
		l.setFromDate(scan.nextLine());
		System.out.println("Enter the To Date :");
		l.setToDate(scan.nextLine());
		System.out.println("Enter the Reason for leave :");
		l.setReasonForLeave(scan.nextLine());
		scan.close();
		return leave.applyLeave(empId,l);
	}
}
