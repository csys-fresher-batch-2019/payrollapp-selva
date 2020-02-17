package com.chainsys.payrollapp.daoimplements;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.chainsys.payrollapp.model.PaySlipModel;
import com.chainsys.payrollapp.util.Connections;
import com.chainsys.payrollapp.util.GeneratePaySlip;
import com.chainsys.payrollapp.util.Logger;

public class PaySlip
{
	static Logger logger = Logger.getInstance();
	public int EmployeeDetails() throws Exception
	{
		GeneratePaySlip gen = new GeneratePaySlip();
		String query = "select emp_id from employee where active = 1";
		int workDone = 0;
		try(Connection con  = Connections.connect();
				PreparedStatement pst = con.prepareStatement(query);
				ResultSet prs = pst.executeQuery();)
		{
			while(prs.next()) 
			{
				int id = prs.getInt("emp_id");
				String sql = "select emp_id , emp_name , salary , performance_grade ,"+
				" salary_increment , allowance , leaves_taken , loss_of_pay ,"+
				" food_deduction , cab_deduction , provident_fund ,"+
				" salary_to_be_credited from deductions d"+
				"  inner join employee e  on e.emp_id= ?  and e.active = 1"+
				" inner join credits c on c.emp_id = ? and d.emp_id = ? "+
				"inner join final_salary s on  s.emp_id= ? ";
				try(PreparedStatement pstm = con.prepareStatement(sql);)
				{
					pstm.setInt(1, id);
					pstm.setInt(2, id);
					pstm.setInt(3, id);
					pstm.setInt(4, id);
					try(ResultSet rs = pstm.executeQuery();)
					{
						while(rs.next())
						{
							PaySlipModel pm = new PaySlipModel();
							pm.setId(rs.getInt("emp_id"));
							pm.setName(rs.getString("emp_name"));
							pm.setBasePay(rs.getInt("salary"));
							pm.setPerformanceGrade(rs.getInt("performance_grade"));
							pm.setSalaryIncrement(rs.getInt("salary_increment"));
							pm.setAllowance(rs.getInt("allowance"));
							pm.setLeavesTaken(rs.getInt("leaves_taken"));
							pm.setLossOfPay(rs.getInt("loss_of_pay"));
							pm.setFoodDeduction(rs.getInt("food_deduction"));
							pm.setCabDeduction(rs.getInt("cab_deduction"));
							pm.setProvidentFund(rs.getInt("provident_fund"));
							pm.setSalaryToBeCredited(rs.getInt("salary_to_be_credited"));
							workDone = gen.paySlip(pm,rs.getInt("emp_id"));
						}
					}
				}
				catch(Exception e)
				{
					throw new RuntimeException(e);
				}
		
			}
			return workDone;
		}
	}
}