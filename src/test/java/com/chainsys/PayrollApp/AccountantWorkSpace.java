package com.chainsys.PayrollApp;

import java.util.Scanner;
import com.chainsys.PayrollApp.DAOImplements.*;

public class AccountantWorkSpace {
	static 	Scanner scan = new Scanner(System.in);

	public static void main(String[] arg) throws Exception {
		
		AccountantOperations ao = new  AccountantOperations();
		while(true)
		{
			System.out.println("===========================ACCOUNTANT====================================");
			System.out.println("Press 1 to Calculate PF \nPress 2 to Calculate Increment \nPress 3 to Attandance marking \nPress 4 to Calculate salary\nPress 5 to Swipe \nPress 6 Apply Leave \nPress 7 for Payslip Generation \nPress 8 to exit ");
			System.out.print("Enter Your Choice : ");
			int choice = scan.nextInt();
			int id = Integer.parseInt(arg[0]);
			switch(choice)
			{
				case 1 :System.out.println(ao.calculatePF());
						break;
				case 2 :System.out.println(ao.calculateIncrement());;
						break;
				case 3 :System.out.println(ao.markAttendance());
						break;
				case 4 :System.out.println(ao.calculatesalary());
						break;
				case 5 :LogMonitor lm =new LogMonitor();
						System.out.println(lm.swipe(id));
						break;
				case 6 :ApplyLeave leave = new ApplyLeave();
						System.out.println(leave.testApplyLeave(arg[0]));
						break;
				case 7 : testGeneratePaySlip();
							break;
				case 8 :return;
				default :System.out.println("Invalid input");
						break;
	
			}
		}
	}

	private static void testGeneratePaySlip() throws Exception {
		GeneratePaySlip gp = new GeneratePaySlip();
		gp.paySlip();
		return ;
	}
}
