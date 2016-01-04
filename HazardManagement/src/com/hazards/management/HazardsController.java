/**
 * 
 */
package com.hazards.management;


import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import javax.ws.rs.core.MediaType;




/**
 * @author Himanshu Mishra (homi1388@gmail.com)
 * @author Nikhil Krishnamurthy
 */

@Path("/test")
public class HazardsController {
	HazardDAO dao=new HazardDAO();
	
	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<TableHazardObject> getList(){
		/*ArrayList<TableHazardObject> temp=new ArrayList<TableHazardObject>(dao.getAllRecords());
		GenericEntity<ArrayList<TableHazardObject>> entity = new GenericEntity<ArrayList<TableHazardObject>>(temp) {};

		return Responseer.ok(entity).build();*/
		
		return dao.getAllRecords();
		
	}
	
	
	@GET
	@Path("/pexcl")
	@Produces(MediaType.TEXT_PLAIN)
	public String parseExcel(){
		
		String filelocation = "C:/Users/nikhil/Desktop/HazardManagement.xlsx";
		String outputlocation = "Output.xlsx";
		String rejectlocation = "Reject.xlsx";
		
		ExcelSheetAPI object = new ExcelSheetAPI();
		
		ArrayList<String> chemicallist =object.parse_Excel(filelocation);
		ArrayList<TableHazardObject> output = new ArrayList<TableHazardObject>();
		ArrayList<String> rejects = new ArrayList<String>();
		
		
		for(int i=0; i<chemicallist.size();i++){
			
			String[] temp = chemicallist.get(i).split(";");
			TableHazardObject row = dao.getOneRecord(temp[0],temp[1],temp[2]);
			if(row.getId() == 0){
				rejects.add(chemicallist.get(i));
			}else{
				output.add(row);
			}
			
		}
		
		int c1=object.writeOutput(output,outputlocation);
		int c2=object.writeReject(rejects,rejectlocation);
		
		
		
		
		
	
	
		if(c1==0 && c2==0)
		return "I have successfully created the two excel files for you. Output.xlsx has all the data I could gather from the database "
				+ " and Reject.xlsx is a list of those chemicals that I could not find.";
		else
			return "I was Unsuccessful in finishing the task that you gave me.";
	}
	
	
	
	@GET
	@Path("/dataentry")
	@Produces(MediaType.TEXT_PLAIN)
	public String dataEntry(){
		String filelocation = "C:/Users/nikhil/Desktop/DataEntry.xlsx";
		
		ExcelSheetAPI object = new ExcelSheetAPI();
		ArrayList<TableHazardObject> output = new ArrayList<TableHazardObject>();
		output = object.dataEntry(filelocation); 
		
		int c = dao.dataEntry(output);
		
		if(c == 0){
			return "I have successfully stored the data in the database";
			
		}else{
			return "I was Unsuccessful in storing the data in the database";
		}
		
		
		
	}
	
}
