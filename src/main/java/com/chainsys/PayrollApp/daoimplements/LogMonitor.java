package com.chainsys.payrollapp.daoimplements;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import com.chainsys.payrollapp.util.Logger;

public class LogMonitor {
	static Logger logger = Logger.getInstance();
	public String swipe(int empId) 
	{
		try(Connection con = UserLogin.connect();
		CallableStatement stmt = con.prepareCall("{call entry_gate(?)}");)
		{
			stmt.setInt(1,empId);
			stmt.execute();
		}
		catch(SQLException e)
		{
			logger.error(e);
		}
		return "Updated";
	}
}
