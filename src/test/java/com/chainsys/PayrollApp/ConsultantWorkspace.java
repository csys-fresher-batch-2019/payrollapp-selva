package com.chainsys.payrollapp;

import java.util.Scanner;

import com.chainsys.payrollapp.daoimplements.LogMonitor;
import com.chainsys.payrollapp.util.Logger;

public class ConsultantWorkspace {
	static Logger logger = Logger.getInstance();
	static Scanner scan = new Scanner(System.in);
	public static void main(String[] arg) throws Exception {

		while(true)
		{
			logger.info("Press 1 to Apply Leave \nPress 2 Swipe  \nPress 3 to exit ");
			logger.getInput("Enter Your choice : ");
			int choice = scan.nextInt();
			switch(choice)
			{
				case 1 :ApplyLeave leave = new ApplyLeave();
						logger.info(leave.testApplyLeave(arg[0]));
						break;
				case 2 :LogMonitor lm = new LogMonitor();
						int id = Integer.parseInt(arg[0]);
						logger.info(lm.swipe(id));
						break;
				case 3 :return;
				default : logger.info("Invalid input");
						break;
			}
		}
	}
}
