package com.chainsys.PayrollApp;


import java.util.Scanner;
import com.chainsys.PayrollApp.util.Logger;

import com.chainsys.PayrollApp.DAOImplements.UserLogin;

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
			int result = UserLogin.login(EmpId, password);
			if(result == 1)
				continue;
			else if(result == 2)
			{
				logger.getInput("Enter the new Password : ");
				String newPassword = obj.next();
				logger.getInput("Confirm new Password : ");
				String conPassword = obj.next();
				if(UserLogin.UpdatePassword(newPassword,conPassword,EmpId))
					logger.info("Updated !");
				else
					logger.info("Update Failure");
			}
			else if(result == 3)
				logger.info("Incorrect Password. If you Forgot your password Contact Your Admin ! ");
			else if(result == 4)
				logger.info("Access Denied! Contact Your Admin !");
		}
	}
}