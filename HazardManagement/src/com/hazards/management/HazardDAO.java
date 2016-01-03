/**
 * 
 */
package com.hazards.management;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.hazards.management.DatabaseAccess;
import com.hazards.management.TableHazardObject;

/**
 * @author Himanshu Mishra (homi1388@gmail.com)
 * @author Nikhil Krishnamurthy
 *
 */
public class HazardDAO {

	DatabaseAccess dtbsobj;
	Connection connection;

	public HazardDAO(){
		try{
			dtbsobj=new DatabaseAccess();
			dtbsobj.connect();
			connection = dtbsobj.getConnection();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}

	public ArrayList<TableHazardObject> getAllRecords(){
		PreparedStatement ps;
		try{
			ArrayList<TableHazardObject> arplaces = new ArrayList<TableHazardObject>();
			String sql="Select * from hazards;";
			ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				TableHazardObject pl=new TableHazardObject();
				pl.setId(rs.getInt(1));
				pl.setName(rs.getString(2));
				pl.setSynonym(rs.getString(3));
				pl.setCas_number(rs.getString(4));
				pl.setNFPA1(rs.getString(5));
				pl.setNFPA2(rs.getString(6));
				pl.setNFPA3(rs.getString(7));
				pl.setNFPA4(rs.getString(8));
				pl.setPrimary_hazard(rs.getString(9));
				pl.setSecondary_hazard(rs.getString(10));
				arplaces.add(pl);
			}
			return arplaces;
		}
		catch(SQLException e){
			e.printStackTrace();
			ps=null;
			return null;
		}
	}

	public TableHazardObject getOneRecord(String name, String synonym, String cas_number){

		TableHazardObject output = new TableHazardObject();
		PreparedStatement ps;
		int i=0;
		try{
			

			StringBuilder sql=new StringBuilder().append("Select * from hazards where");
			if(name!=" "){
				if (name!=" "){
					sql.append(" name = '").append(name).append("'");
				}
				if (synonym!=" "){
					sql.append(" and synonym = '").append(synonym).append("'");
				}
				if (cas_number!=" "){
					sql.append(" and cas_number = '").append(cas_number).append("'");
				}
			}else if(name==" " && synonym!=" "){
				if (synonym!=" "){
					sql.append(" synonym = '").append(synonym).append("'");
				}
				if (cas_number!=" "){
					sql.append(" and cas_number = '").append(cas_number).append("'");
				}
			}else if(name==" " && synonym==" " && cas_number!=" "){

				if (cas_number!=" "){
					sql.append(" cas_number = '").append(cas_number).append("'");
				}
			}
			sql.append(";");



			ps=connection.prepareStatement(sql.toString());
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				i++;
				output.setId(rs.getInt(1));
				output.setName(rs.getString(2));
				output.setSynonym(rs.getString(3));
				output.setCas_number(rs.getString(4));
				output.setNFPA1(rs.getString(5));
				output.setNFPA2(rs.getString(6));
				output.setNFPA3(rs.getString(7));
				output.setNFPA4(rs.getString(8));
				output.setPrimary_hazard(rs.getString(9));
				output.setSecondary_hazard(rs.getString(10));

				if(i>1){
					return null;
				}

			}

			return output;

		}
		catch(SQLException e){
			e.printStackTrace();
			ps=null;
			return null;
		}






	}


}
