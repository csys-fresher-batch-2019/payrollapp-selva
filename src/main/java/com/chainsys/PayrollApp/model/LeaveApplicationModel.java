package com.chainsys.payrollapp.model;

public class LeaveApplicationModel {
	public enum LeaveStatus
	{
	APPROVED,NOT_APPROVED;	
	}
	private int empId;
	private String fromDate;
	private String toDate;
	private String reasonForLeave;
	
	public String getFromDate() {
		return fromDate;
	}
	
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	
	public String getToDate() {
		return toDate;
	}
	
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	
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
