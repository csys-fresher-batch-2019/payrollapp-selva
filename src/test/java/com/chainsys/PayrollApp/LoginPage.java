package com.chainsys.payrollapp;


import java.util.Scanner;

import com.chainsys.payrollapp.daoimplements.UserLogin;
import com.chainsys.payrollapp.util.Logger;

public class LoginPage 
{
	static Logger logger = Logger.getInstance();
	static Scanner obj = new Scanner(System.in);
	public static void main(String[] args)throws Exception 
	{
		while(true) 
		{						
			logger.getInput("Enter your id : ");
			int EmpId = obj.nextInt();
			logger.getInput("Enter the Password : ");
			String password = obj.next();
			String result = UserLogin.login(EmpId, password);
			String[] arg = {""+EmpId};
			if(result.equals("activate"))
			{
				logger.getInput("Enter the new Password : ");
				String newPassword = obj.next();
				logger.getInput("Confirm new Password : ");
				String conPassword = obj.next();
				UserLogin.UpdatePassword(newPassword,conPassword,EmpId);
			}
			else if(result.equals("wrong password"))
				logger.info("Incorrect Password. If you Forgot your password Contact Your Admin ! ");
			else if(result.equals("Not a user"))
				logger.info("Access Denied! Contact Your Admin !");
			else
			{
				switch(result) 
				{
					case "ADMIN":
						AdminWorkSpace.main(arg);
						break;
					case "CONSULTANT":
						ConsultantWorkspace.main(arg);
						break;
					case "DEVELOPER":
						DeveloperWorkspace.main(arg);
						break;
					case "ACCOUNTANT":
						AccountantWorkSpace.main(arg);
						break;
					case "HR":
						HrWorkSpace.main(arg);
						break;
				}
			}
		}
	}
}