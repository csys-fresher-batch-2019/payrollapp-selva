package com.chainsys.PayrollApp.DAOImplements;

import java.time.LocalDate;
import java.util.Scanner;

import com.chainsys.PayrollApp.Model.LeaveApplicationModel;

public class ApplyLeave {
	public String testApplyLeave(String args) throws Exception 
	{
		Scanner scan = new Scanner(System.in);
		LeaveApplication leave = new LeaveApplication();
		LeaveApplicationModel l = new LeaveApplicationModel();
		int empId = Integer.parseInt(args);
		System.out.println("Enter the From Date :");
		String fromDate = scan.next();
		l.fromDate = LocalDate.parse(fromDate);
		System.out.println("Enter the To Date :");
		String toDate = scan.next();
		l.toDate = LocalDate.parse(toDate);
		System.out.println("Enter the Reason for leave :");
		l.setReasonForLeave(scan.next());
		scan.close();
		return leave.applyLeave(empId,l);
	}
}
