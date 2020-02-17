package com.chainsys.payrollapp.daoimplements;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import com.chainsys.payrollapp.util.Connections;


public class LogMonitor {
	
	public int swipe(int empId) throws ClassNotFoundException 
	{
		int rows = 0;
		try(Connection con = Connections.connect();
		CallableStatement stmt = con.prepareCall("{call entry_gate(?)}");)
		{
			stmt.setInt(1,empId);
			rows = stmt.executeUpdate();
		}
		catch(SQLException e)
		{
			throw new RuntimeException(e);
		}
		return rows;
	}
}
