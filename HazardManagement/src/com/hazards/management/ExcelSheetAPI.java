package com.hazards.management;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @author Himanshu Mishra (homi1388@gmail.com)
 * @author Nikhil Krishnamurthy
 */

public class ExcelSheetAPI {
	
	final String location = "C:/Hazard Management/";
	
	public ExcelSheetAPI(){

	}

	public ArrayList<String> parse_Excel(String name){

		try{
			ArrayList<String> output = new ArrayList<String>();

			FileInputStream file = new FileInputStream(new File(location+name));
			//Create Workbook instance holding reference to .xlsx file
			XSSFWorkbook workbook = new XSSFWorkbook(file);

			//Get first/desired sheet from the workbook
			XSSFSheet sheet = workbook.getSheetAt(0);

			//Iterate through each rows one by one
			Iterator<Row> rowIterator = sheet.iterator();
			while (rowIterator.hasNext())
			{

				Row row = rowIterator.next();
				
				Cell n = row.getCell(0,Row.CREATE_NULL_AS_BLANK);
				Cell s = row.getCell(1,Row.CREATE_NULL_AS_BLANK);
				Cell cn = row.getCell(2,Row.CREATE_NULL_AS_BLANK);
				n.setCellType(Cell.CELL_TYPE_STRING);
				s.setCellType(Cell.CELL_TYPE_STRING);
				cn.setCellType(Cell.CELL_TYPE_STRING);
				
				String n1 = new String();
				String s1=new String();
				String cn1=new String();
				
				if(n.getStringCellValue().equals(""))
					n1=" ";
				else
					n1=n.getStringCellValue();
				if(s.getStringCellValue().equals(""))
					s1=" ";
				else
					s1=s.getStringCellValue();
				if(cn.getStringCellValue().equals(""))
					cn1=" ";
				else
					cn1=cn.getStringCellValue();
				
				StringBuilder str = new StringBuilder();
				str.append(n1).append(";").append(s1)
					.append(";").append(cn1);
			System.out.println(str.toString());
				output.add(str.toString());



			}
			workbook.close();
			file.close();
			return output;

		}catch(Exception e){
			System.out.println("parseExcel : problem in reading the excel");
			
			return null;
		}


	}
	
	public int writeOutput(ArrayList<TableHazardObject> output, String outputlocation){
		 	XSSFWorkbook workbook = new XSSFWorkbook();
         
	        //Create a blank sheet
	        XSSFSheet sheet = workbook.createSheet("Hazard Management");
	          
	        //This data needs to be written (Object[])
	        Map<String, Object[]> data = new TreeMap<String, Object[]>();
	        for(int i=0;i<output.size();i++){
	        	Object[] temp = {output.get(i).getId(),output.get(i).getName(),output.get(i).getSynonym(),
	        			output.get(i).getCas_number(), output.get(i).getNFPA1(), output.get(i).getNFPA2(),
	        			output.get(i).getNFPA3(),output.get(i).getNFPA4(),output.get(i).getPrimary_hazard(),
	        			output.get(i).getSecondary_hazard()};
	        	
	        	data.put(Integer.toString(i+1),temp);
	        }
	          
	        //Iterate over data and write to sheet
	        Set<String> keyset = data.keySet();
	        int rownum = 0;
	        for (String key : keyset)
	        {
	            Row row = sheet.createRow(rownum++);
	            Object [] objArr = data.get(key);
	            int cellnum = 0;
	            for (Object obj : objArr)
	            {
	               Cell cell = row.createCell(cellnum++);
	               
	               if(obj instanceof String)
	                    cell.setCellValue((String)obj);
	                else if(obj instanceof Integer)
	                    cell.setCellValue((Integer)obj);
	               
	            }
	        }
	        try
	        {
	            //Write the workbook in file system
	            FileOutputStream out = new FileOutputStream(new File(location+outputlocation));
	            workbook.write(out);
	            workbook.close();
	            out.close();
	           
	            return 0;
	        }
	        catch (Exception e)
	        {
	            e.printStackTrace();
	            return 1;
	        }
		
		
		
		
	}
	
	public int writeReject(ArrayList<String> rejects, String rejectlocation){
		
		XSSFWorkbook workbook = new XSSFWorkbook();
        
        //Create a blank sheet
        XSSFSheet sheet = workbook.createSheet("Hazard Management");
          
        //This data needs to be written (Object[])
        Map<String, Object[]> data = new TreeMap<String, Object[]>();
        for(int i=0;i<rejects.size();i++){
        	String[] t = rejects.get(i).split(";");
        	
        	Object[] temp = {t[0],t[1],t[2]};
        	
        	data.put(Integer.toString(i+1),temp);
        }
          
        //Iterate over data and write to sheet
        Set<String> keyset = data.keySet();
        int rownum = 0;
        for (String key : keyset)
        {
            Row row = sheet.createRow(rownum++);
            Object [] objArr = data.get(key);
            int cellnum = 0;
            for (Object obj : objArr)
            {
               Cell cell = row.createCell(cellnum++);
               if(obj instanceof String)
                    cell.setCellValue((String)obj);
                else if(obj instanceof Integer)
                    cell.setCellValue((Integer)obj);
            }
        }
        try
        {
            //Write the workbook in file system
            FileOutputStream out = new FileOutputStream(new File(location+rejectlocation));
            workbook.write(out);
            workbook.close();
            out.close();
          
            return 0;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return 1;
        }
	}

	public ArrayList<TableHazardObject> dataEntry(String name){
		ArrayList<TableHazardObject> output = new ArrayList<TableHazardObject>();
		try{
			

			FileInputStream file = new FileInputStream(new File(name));
			//Create Workbook instance holding reference to .xlsx file
			XSSFWorkbook workbook = new XSSFWorkbook(file);

			//Get first/desired sheet from the workbook
			XSSFSheet sheet = workbook.getSheetAt(0);

			//Iterate through each rows one by one
			Iterator<Row> rowIterator = sheet.iterator();
			while (rowIterator.hasNext())
			{
				
				Row row = rowIterator.next();
				
				
				TableHazardObject tho = new TableHazardObject();
				
				row.getCell(0,Row.CREATE_NULL_AS_BLANK).setCellType(Cell.CELL_TYPE_STRING);
				 tho.setName(row.getCell(0,Row.CREATE_NULL_AS_BLANK).getStringCellValue());
				 row.getCell(1,Row.CREATE_NULL_AS_BLANK).setCellType(Cell.CELL_TYPE_STRING);
				 tho.setSynonym(row.getCell(1,Row.CREATE_NULL_AS_BLANK).getStringCellValue());
				 row.getCell(2,Row.CREATE_NULL_AS_BLANK).setCellType(Cell.CELL_TYPE_STRING);
				 tho.setCas_number(row.getCell(2,Row.CREATE_NULL_AS_BLANK).getStringCellValue());
				 row.getCell(3,Row.CREATE_NULL_AS_BLANK).setCellType(Cell.CELL_TYPE_STRING);
				 tho.setNFPA1(row.getCell(3,Row.CREATE_NULL_AS_BLANK).getStringCellValue());
				 row.getCell(4,Row.CREATE_NULL_AS_BLANK).setCellType(Cell.CELL_TYPE_STRING);
				 tho.setNFPA2(row.getCell(4,Row.CREATE_NULL_AS_BLANK).getStringCellValue());
				 row.getCell(5,Row.CREATE_NULL_AS_BLANK).setCellType(Cell.CELL_TYPE_STRING);
				 tho.setNFPA3(row.getCell(5,Row.CREATE_NULL_AS_BLANK).getStringCellValue());
				 row.getCell(6,Row.CREATE_NULL_AS_BLANK).setCellType(Cell.CELL_TYPE_STRING);
				 tho.setNFPA4(row.getCell(6,Row.CREATE_NULL_AS_BLANK).getStringCellValue());
				 row.getCell(7,Row.CREATE_NULL_AS_BLANK).setCellType(Cell.CELL_TYPE_STRING);
				 tho.setPrimary_hazard(row.getCell(7,Row.CREATE_NULL_AS_BLANK).getStringCellValue());
				 row.getCell(8,Row.CREATE_NULL_AS_BLANK).setCellType(Cell.CELL_TYPE_STRING);
				 tho.setSecondary_hazard(row.getCell(8,Row.CREATE_NULL_AS_BLANK).getStringCellValue());
				output.add(tho);
				


			}
			workbook.close();
			file.close();
			return output;

		}catch(Exception e){
			System.out.println("parseExcel : problem in reading the excel");
			 e.printStackTrace();
			return null;
		}
		
	}

}
