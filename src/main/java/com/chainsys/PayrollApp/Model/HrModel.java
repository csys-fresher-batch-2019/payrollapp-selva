package com.chainsys.PayrollApp.model;

public class HrModel 
{

	private int empId;
	private int allowance;
	private int basepay;
	private int performanceGrade;
	private String fDate;
	private String reasonForLeave;
	private String tDate;

	public void setId(int id) throws IllegalAccessException
	{  
		if((id/1000)!=8)
			this.empId = id;
		else
			throw new IllegalAccessException("Invalid id");
	}
	public int getID() 
	{	
		return empId;
	}
	public void setAllowance(int allowance)
	{
		this.allowance = allowance;
	}
	public int getAllowance() 
	{
		return allowance;
	}
	public void setBasePay(int basePay)
	{
		this.basepay = basePay;
	}
	public int getBasePay() 
	{ 
		return basepay;
	}
	public void setPerformanceGrade(int grade)
	{
		this.performanceGrade = grade;
	}
    public int getPerformanceGrade()
    { 
    	return performanceGrade;
    }
    public void setFromDate(String fDate)
	{
		this.fDate = fDate;
	}
    public String getFromDate() 
    { 
    	return fDate.substring(0,10);
    }
    public void setToDate(String toDate)
	{
		this.tDate = toDate;
	}
    public String getToDate() 
    {
    	return tDate.substring(0,10);
    }
    public void setReason(String reason)
	{
		this.reasonForLeave = reason;
	}
    public String getReason() 
    {
    	return reasonForLeave;
    }
}