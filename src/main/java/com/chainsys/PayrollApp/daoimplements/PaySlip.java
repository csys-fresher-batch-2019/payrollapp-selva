package com.chainsys.payrollapp.daoimplements;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.chainsys.payrollapp.model.PaySlipModel;
import com.chainsys.payrollapp.util.Logger;

/*import org.apache.pdfbox.pdmodel.PDPage;

import com.itextpdf.kernel.pdf.PdfDocument; 
import com.itextpdf.kernel.pdf.PdfWriter; 
 
import com.itextpdf.layout.Document; 
import com.itextpdf.layout.element.Cell; 
import com.itextpdf.layout.element.Table;
import com.itextpdf.text.pdf.PdfPage; 

public class PaySlip {

	public static void main(String args[]) throws Exception 
	{ 
	      String file = "sal"+201+".pdf"; 
	      PdfDocument pdfDoc = new PdfDocument(new PdfWriter(file)); 
	      Document doc = new Document(pdfDoc);
	      Table table = new Table(2);    
	      table.addCell("Raju"); 
	      table.addCell("Raju"); 
	      table.addCell("Raju"); 
	      table.addCell("Raju"); 
	      table.addCell("Raju"); 
	      table.addCell("Raju"); 
	      doc.add(table);
	      doc.close();  
	      System.out.println("Table created successfully.."); 
	 } 
}*/


public class PaySlip
{
	static Logger logger = Logger.getInstance();
	public ArrayList<PaySlipModel> EmployeeDetails()
	{
		String sql = "select emp_id,emp_name,salary,performance_grade,salary_increment,allowance,leaves_taken,loss_of_pay,food_deduction,cab_deduction,provident_fund,salary_to_be_credited from deductions d inner join employee e  on e.emp_id=6001 and d.emp_id=6001 inner join credits c on c.emp_id=6001 inner join final_salary s on s.emp_id=6001";
		try(Connection con  = UserLogin.connect();
		PreparedStatement pst = con.prepareStatement(sql);
		ResultSet rs = pst.executeQuery();)
		{
			ArrayList<PaySlipModel> list = new ArrayList<>();
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
				list.add(pm);
			}
			return list;
		}
		catch(SQLException e)
		{
			logger.error(e);
			return null;
		}
		
	}
}