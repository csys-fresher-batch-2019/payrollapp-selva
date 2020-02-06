package com.chainsys.PayrollApp.daoimplements;
import java.util.ArrayList;

import com.chainsys.PayrollApp.model.PaySlipModel;
import com.itextpdf.kernel.pdf.PdfDocument; 
import com.itextpdf.kernel.pdf.PdfWriter; 
 
import com.itextpdf.layout.Document; 
import com.itextpdf.layout.element.Table;

public class GeneratePaySlip 
{
	public  void paySlip() throws Exception 
	{ 
		PaySlip ps = new PaySlip();
		ArrayList<PaySlipModel> salaryDetails = new ArrayList<>();
		salaryDetails = ps.EmployeeDetails();
		String file = "sal"+6001+".pdf"; 
	    PdfDocument pdfDoc = new PdfDocument(new PdfWriter(file)); 
	    Document doc = new Document(pdfDoc);
	    
		for(PaySlipModel obj : salaryDetails)
		{
			Table table = new Table(2);  
			table.addHeaderCell("-CATEGORY-");
			table.addHeaderCell("-DETAILS-");
		    table.addCell("Employee ID "); 
		    table.addCell(""+obj.getId()); 
		    table.addCell("Employee Name"); 
		    table.addCell(obj.getName()); 
		    table.addCell("BasePay");
		    table.addCell("Rs. "+obj.getSalary());
		    table.addCell("Performance_grade");
		    table.addCell("Rs. "+obj.getPerformanceGrade());
		    table.addCell("Salary Increment");
		    table.addCell("Rs. "+obj.getSalaryIncrement());
		    table.addCell("Allowance");
		    table.addCell("Rs. "+obj.getAllowance());
		    table.addCell("Leaves Taken");
		    table.addCell(""+obj.getLeavesTaken());
		    table.addCell("Loss Of Pay");
		    table.addCell("Rs. "+obj.getLossOfPay());
		    table.addCell("Food_detection");
		    table.addCell("Rs. "+obj.getFoodDeduction());
		    table.addCell("Cab Deduction");
		    table.addCell("Rs. "+obj.getCabDeduction());
		    table.addCell("Provident Fund");
		    table.addCell("Rs. "+obj.getProvidentFund());
		    table.addCell("Salary Take Home"); 
		    table.addCell("Rs. "+obj.getSalaryToBeCredited());
		    doc.add(table);

		}
	     /* Table table = new Table(2);    
	      table.addCell("Raju"); 
	      table.addCell("Raju"); 
	      table.addCell("Raju"); 
	      table.addCell("Raju"); 
	      table.addCell("Raju"); 
	      table.addCell("Raju"); 
	      doc.add(table);*/
	    doc.close(); 
	    return ;
	} 
}
