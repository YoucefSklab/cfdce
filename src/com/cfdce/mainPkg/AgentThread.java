package com.cfdce.mainPkg;

import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;

public class AgentThread extends Thread {

	private int threadNbr = 1;
	AgentContainer agentContainer = null;

	
	
public AgentThread(int agNbr, AgentContainer agentContainer){
	this.threadNbr = agNbr;
	this.agentContainer = agentContainer;
}




public void run() {
		
		
				
		System.out.println("I am the Agent : "+this.threadNbr);
	
		AgentController agentController;
		try {
			
			System.out.println(" Lancement Agent "+threadNbr);
			agentController = agentContainer.createNewAgent("Agent"+threadNbr, "Agents.Agent1", new Object[]{""+threadNbr});
			agentController.start();
		
		} catch (StaleProxyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}
}
	

