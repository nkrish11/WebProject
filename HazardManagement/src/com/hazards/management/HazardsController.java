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
		
		String filelocation = "HazardManagement.xls";
		String outputlocation = "Output.xls";
		String rejectlocation = "Reject.xls";
		
		ExcelSheetAPI object = new ExcelSheetAPI();
		
		ArrayList<String> chemicallist = object.parse_Excel(filelocation);
		ArrayList<TableHazardObject> output = new ArrayList<TableHazardObject>();
		ArrayList<String> rejects = new ArrayList<String>();
		
		
		for(int i=0; i<chemicallist.size();i++){
			
			String[] temp = chemicallist.get(i).split(";");
			TableHazardObject row = dao.getOneRecord(temp[0],temp[1],temp[2]);
			if(row == null){
				rejects.add(chemicallist.get(i));
			}else{
				output.add(row);
			}
			
		}
		
		int c1=object.writeOutput(output,outputlocation);
		int c2=object.writeReject(rejects,rejectlocation);
		
		
		
		
		
	
	
		if(c1==0 && c2==0)
		return "Successful";
		else
			return "Unsuccessful";
	}
	
	
}
