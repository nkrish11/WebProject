/**
 * 
 */
package com.hazards.management;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Himanshu Mishra (homi1388@gmail.com)
 * @author Nikhil Krishnamurthy
 * 
 *
 */
@XmlRootElement
public class TableHazardObject {
	int id;
	String name;
	String synonym;
	String cas_number;
	String NFPA1;
	String NFPA2;
	String NFPA3;
	String NFPA4;
	String primary_hazard;
	String secondary_hazard;
	
	
	public TableHazardObject(){
/*		name=new String();
		synonym=new String();
		cas_number=new String();
		NFPA1=new String();
		NFPA2=new String();
		NFPA3=new String();
		NFPA4=new String();*/
	}
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}
	
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getSynonym() {
		return synonym;
	}


	public void setSynonym(String synonym) {
		this.synonym = synonym;
	}


	public String getCas_number() {
		return cas_number;
	}


	public void setCas_number(String cas_number) {
		this.cas_number = cas_number;
	}


	public String getNFPA1() {
		return NFPA1;
	}


	public void setNFPA1(String nFPA1) {
		NFPA1 = nFPA1;
	}


	public String getNFPA2() {
		return NFPA2;
	}


	public void setNFPA2(String nFPA2) {
		NFPA2 = nFPA2;
	}


	public String getNFPA3() {
		return NFPA3;
	}


	public void setNFPA3(String nFPA3) {
		NFPA3 = nFPA3;
	}


	public String getNFPA4() {
		return NFPA4;
	}


	public void setNFPA4(String nFPA4) {
		NFPA4 = nFPA4;
	}


	public String getPrimary_hazard() {
		return primary_hazard;
	}


	public void setPrimary_hazard(String primary_hazard) {
		this.primary_hazard = primary_hazard;
	}


	public String getSecondary_hazard() {
		return secondary_hazard;
	}


	public void setSecondary_hazard(String secondary_hazard) {
		this.secondary_hazard = secondary_hazard;
	}
	
	
}
