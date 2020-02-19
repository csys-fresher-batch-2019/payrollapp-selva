package com.chainsys.payrollapp.util;

import java.io.FileNotFoundException;
import com.chainsys.payrollapp.model.PaySlipModel;
import com.itextpdf.kernel.pdf.PdfDocument; 
import com.itextpdf.kernel.pdf.PdfWriter; 
import com.itextpdf.layout.Document; 
import com.itextpdf.layout.element.Table;
//import java.io.OutputStream;
//import java.io.FileOutputStream;
//import java.io.File;

public class GeneratePaySlip 
{
	public  int paySlip(PaySlipModel obj,int id) throws Exception 
	{ 
		//OutputStream file = new FileOutputStream(new File("D:\\Test.pdf"));
		String salaryFile = "D:/eclipse/payroll-web/src/main/webapp/salary"+id+".pdf"; 
	    try
	    {
	    	PdfDocument pdfDoc = new PdfDocument(new PdfWriter(salaryFile)); 
		    Document doc = new Document(pdfDoc);
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
			    table.addCell(""+obj.getPerformanceGrade());
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
			    table.addCell("Total Salary"); 
			    table.addCell("Rs. "+obj.getSalaryToBeCredited());
			    doc.add(table);
			    doc.close();
	     /* Table table = new Table(2);    
	      table.addCell("Raju"); 
	      table.addCell("Raju"); 
	      table.addCell("Raju"); 
	      table.addCell("Raju"); 
	      table.addCell("Raju"); 
	      table.addCell("Raju"); 
	      doc.add(table);*/
	    }
	    catch(Exception e)
	    {
	    	throw new RuntimeException(e);
	    }
	    return 1;
	}
}
/*
public class GeneratePaySlip 
{
	static Logger logger = Logger.getInstance();
	public static void main(String[] args) throws Exception
	{
		  PaySlipModel p = new PaySlipModel();
		  p.setId(1001);
		  p.setName("Selva");
		  p.setAllowance(100);
		  p.setBasePay(2000);
		  p.setCabDeduction(200);
		  p.setFoodDeduction(20);
		  p.setLeavesTaken(1);
		  p.setLossOfPay(3);
		  p.setPerformanceGrade(2);
		  p.setPerformanceGrade(1200);
		  p.setSalaryIncrement(200);
		  createPdf(p);
	}
	
	public static void createPdf(PaySlipModel obj) throws Exception
	{
	   //Document document = new Document();
	  try
	   {
		  String file = "sal"+ obj.getName()+".pdf";
		  PdfWriter writer = new PdfWriter(file); 
		  PdfDocument pdfDoc = new PdfDocument(new PdfWriter(writer)); 
	     //PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file));
		  Document document = new Document(pdfDoc);
		  //document.open();
	      //document.setBackgroundColor(com.itextpdf.kernel.colors.Color.YELLOW);
	      //(new Paragraph("A Hello World PDF document."));
		//  System.out.println(salaryDetails); 
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
			    //document.add(new PdfPTable())

			    document.add(table);
			    //doc.close();
			
		    
	      document.close();
	      System.out.println(obj.getId());

	      //writer.close();
	      System.out.println("Done");
	   } catch (FileNotFoundException e)
	   {
	      e.printStackTrace();
	   }
		
	}
}*/