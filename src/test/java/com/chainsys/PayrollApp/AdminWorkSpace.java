package com.chainsys.PayrollApp;

import java.util.Scanner;
import com.chainsys.PayrollApp.DAOImplements.*;
import com.chainsys.PayrollApp.DataAccessObject.ReadExcel;
import com.chainsys.PayrollApp.Model.*;

public class AdminWorkSpace {
	static Scanner scan = new Scanner(System.in);
	static AdminModel a = new AdminModel();
	static AdminOperations ado = new AdminOperations();


	public static void main(String[] arg) throws Exception {
		
		while(true)
		{
			System.out.println("===========================ADMIN====================================");
			System.out.println("Press 1 to Add users \nPress 2 to Remove users \nPress 3 to Calculate LOP to users \nPress 4 to Apply Leave \nPress 5 Swipe \nPress 6 to Unlock user Account \nPress 7 to exit ");
			System.out.print("Enter Your choice : ");
			int choice = scan.nextInt();
			switch(choice)
			{
				case 1 :testAddUser();
						break;
				case 2 :testRemoveUser();
						break;
				case 3 :AdminOperations ado = new AdminOperations();
						System.out.println(ado.calculateLOP());
						break;
				case 4 :ApplyLeave leave = new ApplyLeave();
						System.out.print(leave.testApplyLeave(arg[0]));
						break;
				case 5 :LogMonitor lm = new LogMonitor();
						int id = Integer.parseInt(arg[0]);
						System.out.println(lm.swipe(id));
						break;
				case 6 : unlockUserAccount();
						break;
				case 7 :return;
				default : System.out.println("Invalid input");
						break;
			}
		}
	}
	private static void unlockUserAccount() throws Exception {
		System.out.print("Enter the id :");
		a.empId = scan.nextInt();
		ado.resetPassword(a.empId);
	}
	private static void testAddUser() throws Exception
	{
		System.out.println("To Import File Press 1 \nTo Enter The Deatails Press 2");
		int option = scan.nextInt();
		if(option == 1)
		{
			ReadExcel re = new ReadExcel();
			//re.AdminData();
		}
		else
		{
			System.out.println("=========INSERTING DETAILS OF EMPLOYEE==========");
			System.out.print("Enter the name :");
			a.empName = scan.next();
			System.out.print("Enter the Designation :");
			a.designation = scan.next();
			System.out.println("Enter the Employee id :");
			a.empId = scan.nextInt();
			System.out.println("Enter the mail ID");
			a.email = scan.next();
			System.out.println("Select option for food Y-Yes  N-No");
			a.foodFacility = scan.next();
			System.out.println("Select option for cab Y-Yes  N-No");
			a.cabFacility = scan.next();
			System.out.println(ado.addUsers(a));
		}
	}
	private static void testRemoveUser() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("=========REMOVE EMPLOYEE==========");
		System.out.println("Enter the Employee id :");
		a.empId = scan.nextInt();
		System.out.println(ado.removeUsers(a.empId));
	}
}
