package com.chainsys.PayrollApp.Model;

import java.time.LocalDate;


public class LeaveApplicationModel {
	public enum LeaveStatus
	{
	APPROVED,NOT_APPROVED;	
	}
	public int empId;
	public LocalDate fromDate;
	public LocalDate toDate;
	public String reasonForLeave;
	public LeaveStatus status;	
}
