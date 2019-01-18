package com.cfdce.Plan;

import java.io.Serializable;

public class planMessage implements Serializable {
	
	public String planName;
	public int taskNumber;
	public String[] taskList; 
	// un arc est de la forme AB
	public String[] listA;
	public String[] listB;
	
	
	public  planMessage(String name, int number, String[] list, String[] listeA, String[] listeB){
		planName = name;
		taskNumber = number;
		taskList = list;
		listA =  listeA;
		listB = listeB;
	} // fin du constructeur
	

}
