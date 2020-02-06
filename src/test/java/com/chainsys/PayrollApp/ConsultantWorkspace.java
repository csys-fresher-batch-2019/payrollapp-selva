package com.chainsys.PayrollApp;

import java.util.Scanner;

import com.chainsys.PayrollApp.daoimplements.ApplyLeave;
import com.chainsys.PayrollApp.daoimplements.LogMonitor;

public class ConsultantWorkspace {

	public static void main(String[] arg) throws Exception {
		Scanner scan = new Scanner(System.in);
		while(true)
		{
			System.out.println("===========================CONSULTANT====================================");
			System.out.println("Press 1 to Apply Leave \nPress 2 Swipe  \nPress 3 to exit ");
			System.out.print("Enter Your choice : ");
			int choice = scan.nextInt();
			switch(choice)
			{
				case 1 :ApplyLeave leave = new ApplyLeave();
						System.out.print(leave.testApplyLeave(arg[0]));
						break;
				case 2 :LogMonitor lm = new LogMonitor();
						int id = Integer.parseInt(arg[0]);
						System.out.println(lm.swipe(id));
						break;
				case 3 :return;
				default : System.out.println("Invalid input");
						break;
			}
		}	
	}
}
