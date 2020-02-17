package com.chainsys.payrollapp;


import java.util.Scanner;

import com.chainsys.payrollapp.daoimplements.Login;
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
			String result = Login.login(EmpId, password);
			String[] arg = {""+EmpId};
			if(result.equals("activate"))
			{
				logger.getInput("Enter the new Password : ");
				String newPassword = obj.next();
				logger.getInput("Confirm new Password : ");
				String conPassword = obj.next();
				Login.UpdatePassword(newPassword,conPassword,EmpId);
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

/*

public boolean updateWallet(long mobileNumber, float amount) throws DBException {

String sql = "update kyc set kyc_wallet=? where mobile_no=?";
try (Connection conn = Connect.connect(); PreparedStatement stmt = conn.prepareStatement(sql);) {
stmt.setFloat(1, amount);
stmt.setLong(2, mobileNumber);
int rows = stmt.executeUpdate();
boolean result = false;
if (rows == 0) {
return result;
} else {
result = true;
}
return result;
} catch (Exception e) {
throw new DBException(ErrorMessages.CONNECTION_FAILURE);
}
}*/