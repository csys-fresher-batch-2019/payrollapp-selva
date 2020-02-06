package com.chainsys.PayrollApp;

import java.util.Scanner;

import com.chainsys.PayrollApp.util.Logger;


public class DeveloperWorkspace {
	static Logger logger = Logger.getInstance();
	public static void main(String[] arg) throws Exception {
		

		Scanner scan = new Scanner(System.in);
		while(true)
		{
			logger.getinput("Press 1 to Apply Leave \nPress 2 Swipe  \nPress 3 to exit ");
			logger.getinput("Enter Your choice : ");
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
