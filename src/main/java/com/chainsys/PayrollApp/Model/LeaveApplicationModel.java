package com.chainsys.PayrollApp.model;

import java.time.LocalDate;


public class LeaveApplicationModel {
	public enum LeaveStatus
	{
	APPROVED,NOT_APPROVED;	
	}
	 int empId;
	 public LocalDate fromDate;
	 public LocalDate toDate;
	 String reasonForLeave;
	 public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public String getReasonForLeave() {
		return reasonForLeave;
	}
	public void setReasonForLeave(String reasonForLeave) {
		this.reasonForLeave = reasonForLeave;
	}
	public LeaveStatus getStatus() {
		return status;
	}
	public void setStatus(LeaveStatus status) {
		this.status = status;
	}
	LeaveStatus status;	
}
