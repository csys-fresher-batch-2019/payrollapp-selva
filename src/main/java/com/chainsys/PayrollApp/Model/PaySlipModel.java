package com.chainsys.PayrollApp.model;

public class PaySlipModel {
	private int empId;
	private String empName;
	private int basePay;
	private int performanceGrade;
	private int salaryIncrement;
	private int allowance;
	private int leavesTaken;
	private int lossOfPay;
	private int foodDeduction;
	private int cabDeduction;
	private int providentFund;
	private int salaryToBeCredited;

	public int setId(int id)
	{
		this.empId = id;
		return empId;
	}
	public int getId()
	{
		return empId;
	}
	public String setName(String name)
	{
		this.empName = name;
		return empName;
	}
	public String getName()
	{
		return empName;
	}
	public int setBasePay(int BasePay)
	{
		this.basePay = BasePay;
		return basePay;
	}

	public int getSalary()
	{
		return basePay;
	}
	public int setPerformanceGrade(int grade)
	{
		this.performanceGrade = grade;
		return performanceGrade;
	}
	public int getPerformanceGrade()
	{
		return performanceGrade;
	}
	public int setSalaryIncrement(int increment)
	{
		this.salaryIncrement = increment;
		return salaryIncrement;
	}
	public int getSalaryIncrement()
	{
		return salaryIncrement;
	}
	public int setAllowance(int allowance)
	{
		this.allowance = allowance;
		return allowance;
	}
	public int getAllowance()
	{
		return allowance;
	}
	public int setLeavesTaken(int leaves)
	{
		this.leavesTaken = leaves;
		return leavesTaken;
	}
	public int getLeavesTaken()
	{
		return leavesTaken;
	}
	public int setLossOfPay(int lop)
	{
		this.lossOfPay = lop;
		return lossOfPay;
	}

	public int getLossOfPay()
	{
		return lossOfPay;
	}
	public int setFoodDeduction(int food)
	{
		this.foodDeduction = food;
		return foodDeduction;
	}
	public int getFoodDeduction()
	{
		return foodDeduction;
	}
	public int setCabDeduction(int cab)
	{
		this.cabDeduction = cab;
		return cabDeduction;
	}
	public int getCabDeduction()
	{
		return cabDeduction;
	}
	public int setProvidentFund(int pf)
	{
		this.providentFund = pf;
		return providentFund;
	}
	public int getProvidentFund()
	{
		return providentFund;
	}
	public int setSalaryToBeCredited(int salary)
	{
		this.salaryToBeCredited = salary;
		return salaryToBeCredited;
	}
	public int getSalaryToBeCredited()
	{
		return salaryToBeCredited;
	}

}