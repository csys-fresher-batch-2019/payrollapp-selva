package com.chainsys.payrollapp;

import java.util.ArrayList;
import java.util.Scanner;
import com.chainsys.payrollapp.daoimplements.HrOperations;
import com.chainsys.payrollapp.daoimplements.LeaveApplication;
import com.chainsys.payrollapp.daoimplements.LogMonitor;
import com.chainsys.payrollapp.model.HrModel;
public class HrWorkSpace {
	static Scanner scan = new Scanner(System.in);
	static HrModel hr = new HrModel();
	static HrOperations hro = new HrOperations();
	//static Jdbi jdbi = Connections.getJdbi();
	//static HrDAO hrdao = jdbi.onDemand(HrDAO.class);

	public static void main(String[] arg)throws Exception 
	{
		
		while(true)
		{
			System.out.println("===========================HR==========================================");
			System.out.println("Press 1 to Add Grade to user \nPress 2 to Add Base pay to user \nPress 3 to Add Allowance to users \nPress 4 to Apply Leave \nPress to 5 Swipe \nPress to 6 View Leave Applications \nPress to 7 exit ");
			System.out.print("Enter Your choice : ");
			int choice = scan.nextInt();
			int id = Integer.parseInt(arg[0]);
			switch(choice)
			{
				
				case 1 :testAddGrade();
						break;
				case 2 :testAddBasepay();
						break;
				case 3 :testAddCredits();
						break;
				case 4 :ApplyLeave leave = new ApplyLeave();
						leave.testApplyLeave(arg[0]);
						break;
				case 5 :LogMonitor lm = new LogMonitor();
						lm.swipe(id);
						break;
				case 6 :viewLeaveApplication();
						break;
				case 7 : return;
				default : System.out.println("Invalid input");
						break;
			}
		}
	}

	private static void viewLeaveApplication() throws Exception {
			ArrayList<HrModel> list = new ArrayList<>();
			list = hro.viewLeaveApplication();
	         System.out.print("ID\tFrom Date\tTo Date\tReason\n");
			for (HrModel obj : list) {
		         System.out.print(obj.getID()+"\t");
		         System.out.print(obj.getFromDate()+"\t");
		         System.out.print(obj.getToDate()+"\t");
		         System.out.print(obj.getReason()+"\n");
			}
			System.out.println(" 1 for Accept Leave \n 2 for Reject Leave");
			int option = scan.nextInt();
			System.out.println("Enter Id :");
			hr.setId(scan.nextInt());
			System.out.println(LeaveApplication.Status(hr.getID(),option));
	}

	private static int testAddCredits() throws Exception {
		int rows = 0;
		System.out.print("Enter the employee id : ");
		hr.setId(scan.nextInt());
		System.out.print("Enter the allowance employee "+hr.getID()+" : ");
		hr.setAllowance(scan.nextInt());
		rows = hro.addCredit(hr.getAllowance(), hr.getID());
		return rows;
	}

	private static int testAddBasepay() throws Exception {
		System.out.print("Enter the employee id : ");
		hr.setId(scan.nextInt());
		System.out.print("Enter the basepay employee "+hr.getID()+" : ");
		hr.setBasePay(scan.nextInt());
		System.out.println(hro.addBasepay(hr.getID(),hr.getBasePay()));
		return 1;
	}
	private static int testAddGrade() throws Exception {
		System.out.print("Enter the employee id : ");
		hr.setId(scan.nextInt());
		System.out.print("Enter the performance grade for employee "+hr.getID()+" : ");
		hr.setPerformanceGrade(scan.nextInt());
		System.out.println(hro.addGrade(hr.getID(),hr.getPerformanceGrade()));
		return 1;
	}
}
