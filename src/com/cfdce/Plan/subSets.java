package com.cfdce.Plan;

import java.util.ArrayList;

public class subSets {
	
	public String agent;
	public int myAlternative;
	public int agentAlternative;
	public ArrayList edgeList = new ArrayList();
	
public subSets(String ag, int myAlt, int agentAlt, ArrayList eList){
	
		this.agent = ag;
		this.myAlternative = myAlt;
		this.agentAlternative = agentAlt;
		this.edgeList.addAll(eList);
		
	} // fin du constructeur

} // fin de la calsse
