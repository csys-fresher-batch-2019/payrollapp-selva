package com.chainsys.payrollapp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.chainsys.payrollapp.daoimplements.*;
import com.chainsys.payrollapp.model.*;
import com.chainsys.payrollapp.util.Logger;
import com.chainsys.payrollapp.util.ReadExcel;
import com.chainsys.payrollapp.util.SelectIdUtil;

public class AdminWorkSpace {
	static Scanner scan = new Scanner(System.in);
	static AdminModel a = new AdminModel();
	static AdminOperations ado = new AdminOperations();
	static Logger logger = Logger.getInstance();

	public static void main(String[] arg) throws Exception 
	{
		AdminOperations ado1 = new AdminOperations();
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
				case 7 :display();
						break;
				case 8: selectId();
						break;
				default : logger.info("Invalid input");
						break;
			}
	}
	private static void selectId() throws SQLException {
		
		SelectIdUtil s = new SelectIdUtil(); 
		ArrayList<Integer> idlist = new ArrayList<>();
		idlist = s.selectUserId();
		for(Integer obj:idlist)
		{
			System.out.println(obj);
		}
		
	}
	private static void unlockUserAccount() throws Exception {
		logger.getInput("Enter the id :");
		a.setEmpId(scan.nextInt());
		ado.resetPassword(a.getEmpId());
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
			a.setEmpName(scan.next());
			logger.getInput("Enter the Designation :");
			a.setDesignation(scan.next());
			logger.getInput("Enter the Email id :");
			a.setEmail(scan.next());
			logger.getInput("Enter the PAN number ");
			a.setPan(scan.next());
			logger.getInput("Select option for food Y-Yes  N-No");
			a.setFoodFacility(scan.next());
			logger.getInput("Select option for cab Y-Yes  N-No");
			a.setCabFacility(scan.next());
			logger.getInput("Your Employee id :"+ado.addUsers(a));
		}
	}
	private static void testRemoveUser() throws Exception {
		logger.getInput("Enter the Employee id :");
		a.setEmpId(scan.nextInt());
		logger.info(ado.removeUsers(a.getEmpId()));
	}
	private static void display() throws Exception
	{
		ArrayList<AdminModel> list = ado.viewDetails();
		for(AdminModel a:list)
		{
			System.out.print(a.getEmpId()+"\t");
			System.out.print(a.getEmpName());
			System.out.print(a.getEmail());
			System.out.print(a.getPan());
			System.out.println();
		}
	}
}
