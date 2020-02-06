package com.chainsys.PayrollApp.DataAccessObject;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.awt.List;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

public class ReadExcel 
{
	private static final String FILE_NAME = "credits.xlsx";
    public static void main(String[] args) 
    {
        try 
        {
            FileInputStream excelFile = new FileInputStream(new File(FILE_NAME));
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet datatypeSheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = datatypeSheet.iterator();
            while (iterator.hasNext()) 
            {
            	Row currentRow = iterator.next();
            	List data = new List();
            	Object[] p = {};
                Iterator<Cell> cellIterator = currentRow.iterator();
                while (cellIterator.hasNext()) 
                {
                	Cell currentCell = cellIterator.next();
                    if (currentCell.getCellType() == Cell.CELL_TYPE_STRING) {
                        System.out.print(currentCell.getStringCellValue() + "--");
                        data.add(currentCell.getStringCellValue());
                        
                    } else if (currentCell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                    	int a = (int)currentCell.getNumericCellValue();
                        System.out.print(a + "--");
                    }

                }
                System.out.println(data);

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

