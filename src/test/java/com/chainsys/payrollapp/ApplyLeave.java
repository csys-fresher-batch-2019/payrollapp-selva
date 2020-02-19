package com.chainsys.payrollapp;

import java.util.*;

import com.chainsys.payrollapp.daoimplements.LeaveApplication;
import com.chainsys.payrollapp.model.LeaveApplicationModel;

public class ApplyLeave {
	static 	Scanner scan = new Scanner(System.in);
	public int testApplyLeave(String args) throws Exception 
	{
		int rows = 0;
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
		rows = leave.applyLeave(empId,l);
		return rows; 
	}
}
