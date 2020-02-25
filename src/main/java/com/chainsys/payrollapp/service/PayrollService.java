package com.chainsys.payrollapp.service;

import java.util.ArrayList;

import com.chainsys.payrollapp.dao.*;
import com.chainsys.payrollapp.daoimplements.*;
import com.chainsys.payrollapp.exceptions.DBExceptions;
import com.chainsys.payrollapp.model.AdminModel;
import com.chainsys.payrollapp.model.HrModel;
import com.chainsys.payrollapp.model.LeaveApplicationModel;
import com.chainsys.payrollapp.util.ErrorMessages;

import validation.UserDetailValidation;

public class PayrollService {
	private AdminDAO ado = new AdminOperations();
	private AccountantDAO aco = new AccountantOperations();
	private HrDAO hdo = new HrOperations();
	private LogMonitor lm = new LogMonitor();
	private LeaveApplication leave = new LeaveApplication();
	
	//Admin Services
	public int addEmployeeDetails(AdminModel a) throws DBExceptions 
	{
		UserDetailValidation user = new UserDetailValidation();
		int rows = 0;
		boolean result = false;
		try {
			result = user.emailValidation(a.getEmail());
			result = user.panValidation(a.getPan());
		} catch (DBExceptions e) {
			throw new DBExceptions(ErrorMessages.Error); 
		}
		if(result)
		{
			rows = ado.addUsers(a);
			return rows;
		}
		else
		{
			return 0;
		}
		
	}
	public int deleteEmployee(int id)
	{
		int rows = ado.removeUsers(id);
		return rows;
	}
	public int calculateLOP() throws ClassNotFoundException
	{
		int rows = ado.calculateLOP();
		return rows;
	}
	public int unlockUserAccount(int id)
	{
		int rows = ado.resetPassword(id);
		return rows;
	}
	public ArrayList<AdminModel> display() throws Exception
	{
		ArrayList<AdminModel> list = new ArrayList<>();
		list = ado.viewDetails();
		return list;
	}
	
	
	//Accountant Services
	
	public int calculatePF() throws Exception
	{
		int rows = aco.calculatePF();
		return rows;
	}
	public int calculateIncrement() throws Exception
	{
		int rows = aco.calculateIncrement();
		return rows;
	}
	public int  markAttandence() throws Exception
	{
		int rows = aco.markAttendance();
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
	
	public int addGrade(int id,int grade) throws Exception
	{
		int rows = hdo.addGrade(id, grade);
		return rows;
	}
	public int addBasepay(int id,int basepay) throws Exception
	{
		int rows = hdo.addBasepay(id,basepay);
		return rows;
	}
	public int addCredit(int id,int allowance) throws Exception
	{
		int rows = hdo.addCredit(allowance,id);
		return rows;
	}
	public ArrayList<HrModel> viewLeaveApplication() 
	{
		ArrayList<HrModel> list = new ArrayList<HrModel>(); 
		list = hdo.viewLeaveApplication();
		return list;
	}
	
	//Swipe
	
	public int swipe(int id)
	{
		int rows = 0;
		try {
			rows = lm.swipe(id);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
		return rows;
	}
	
	//Leaveform
	
	public int applyLeave(int id,LeaveApplicationModel leavemodel)
	{
		int rows = leave.applyLeave(id, leavemodel);
		return rows;
	}
	
	//Leave
	
	public int acceptLeave(int id)
	{
		int rows = 0;
		rows = LeaveApplication.Status(id,1);
		return rows;
	}
	public int RejectLeave(int id)
	{
		int rows = 0;
		rows = LeaveApplication.Status(id,2);
		return rows;
	}

}
