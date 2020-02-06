package com.chainsys.PayrollApp;

import java.util.Scanner;

import com.chainsys.PayrollApp.daoimplements.*;
import com.chainsys.PayrollApp.model.*;
import com.chainsys.PayrollApp.util.Logger;

public class AdminWorkSpace {
	static Scanner scan = new Scanner(System.in);
	static AdminModel a = new AdminModel();
	static AdminOperations ado = new AdminOperations();
	static Logger logger = Logger.getInstance();

	public static void main(String[] arg) throws Exception 
	{
			logger.getInput("Press 1 to Add users \nPress 2 to Remove users \nPress 3 to Calculate LOP to users \nPress 4 to Apply Leave \nPress 5 Swipe \nPress 6 to Unlock user Account \nPress 7 to exit ");
			logger.info("Enter Your choice : ");
			int choice = scan.nextInt();
			switch(choice)
			{
				case 1 :testAddUser();
						break;
				case 2 :testRemoveUser();
						break;
				case 3 :AdminOperations ado = new AdminOperations();
						logger.info(ado.calculateLOP());
						break;
				case 4 :ApplyLeave leave = new ApplyLeave();
						logger.info(leave.testApplyLeave(arg[0]));
						break;
				case 5 :LogMonitor lm = new LogMonitor();
						int id = Integer.parseInt(arg[0]);
						logger.info(lm.swipe(id));
						break;
				case 6 : unlockUserAccount();
						break;
				case 7 :return;
				default : logger.info("Invalid input");
						break;
			}
	}
	private static void unlockUserAccount() throws Exception {
		logger.getInput("Enter the id :");
		a.empId = scan.nextInt();
		ado.resetPassword(a.empId);
	}
	private static void testAddUser() throws Exception
	{
		logger.info("To Import File Press 1 \nTo Enter The Deatails Press 2");
		int option = scan.nextInt();
		if(option == 1)
			ReadExcel.AdminData();
		else
		{
			logger.getInput("Enter the name :");
			a.empName = scan.next();
			logger.getInput("Enter the Designation :");
			a.designation = scan.next();
			logger.getInput("Enter the Employee id :");
			a.empId = scan.nextInt();
			logger.getInput("Enter the mail ID");
			a.email = scan.next();
			logger.getInput("Select option for food Y-Yes  N-No");
			a.foodFacility = scan.next();
			logger.getInput("Select option for cab Y-Yes  N-No");
			a.cabFacility = scan.next();
			logger.getInput(ado.addUsers(a));
		}
	}
	private static void testRemoveUser() throws Exception {
		logger.getInput("Enter the Employee id :");
		a.empId = scan.nextInt();
		logger.getInput(ado.removeUsers(a.empId));
	}
}