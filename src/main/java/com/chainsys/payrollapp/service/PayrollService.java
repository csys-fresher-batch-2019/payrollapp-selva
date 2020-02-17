package com.chainsys.payrollapp.service;

import com.chainsys.payrollapp.dao.*;
import com.chainsys.payrollapp.daoimplements.*;
import com.chainsys.payrollapp.model.AdminModel;

public class PayrollService {
	private AdminDAO ado = new AdminOperations();
	private AccountantDAO aco = new AccountantOperations();
	private HrDAO hdo = new HrOperations();
	
	//Admin Services
	public void addEmployeeDetails(AdminModel a)
	{
		ado.addUsers(a);
	}
	public void deleteEmployee(AdminModel a)
	{
		ado.removeUsers(9001);
	}
	public void calculateLOP(AdminModel a) throws ClassNotFoundException
	{
		ado.calculateLOP();
	}
	public void unlockUserAccount(AdminModel a)
	{
		ado.resetPassword(9001);
	}
	
	
	//Accountant Services
	
	public int calculatePF() throws Exception
	{
		int rows = hdo.addBasepay(1, 2);
		return rows;
	}
	public int calculateIncrement() throws Exception
	{
		int rows = hdo.addCredit(1, 2);
		return rows;
	}
	public int  markAttandence() throws Exception
	{
		int rows = hdo.addGrade(1, 2);
		return rows;
	}
	public int calculateSalary() throws Exception
	{
		int rows = aco.calculatesalary();
		return rows;
	}
	public int generatePaySlip() throws Exception
	{
		int rows = aco.GeneratePaySlip();
		return rows;
	}

	// HR Services
	
	public int addGrade() throws Exception
	{
		int rows = aco.GeneratePaySlip();
		return rows;
	}
	public int addBasepay() throws Exception
	{
		int rows = aco.GeneratePaySlip();
		return rows;
	}
	public int addCredit() throws Exception
	{
		int rows = aco.GeneratePaySlip();
		return rows;
	}
	public int viewLeaveApplication()
	{
		return 0;
	}
	
	

}
