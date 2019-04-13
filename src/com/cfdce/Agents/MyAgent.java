package com.cfdce.Agents;




import java.awt.Component;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.JPanel;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.view.View;
import org.graphstream.ui.view.Viewer;

import com.cfdce.App.RunAgents;
import com.cfdce.Control.Constants;
import com.cfdce.Control.Traitment;
import com.cfdce.DB.BDConnect;
import com.cfdce.FormationCoalitions.Coalition;
import com.cfdce.FormationCoalitions.Discussion;
import com.cfdce.FormationCoalitions.coalitionProposalMessage;
import com.cfdce.InterfaceGraphique.plansOverview;
import com.cfdce.Plan.AgentPlan;
import com.cfdce.Plan.PlanManagment;
import com.cfdce.Plan.planMessage;
import com.cfdce.Plan.planSet;
import com.cfdce.Plan.subSets;

import jade.core.AID;
import jade.core.Agent;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.core.behaviours.CyclicBehaviour;
//import java.util.ArrayList;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
//import jade.util.leap.ArrayList;
import jade.wrapper.StaleProxyException;

/**
 * Impl�mentation des comportemens d'un agent.
 * @author SKLAB Youcef
 * @version 0.3
 * 
 *  
 */
public class MyAgent extends Agent {
		
		public AID[] agentsList; 	
		public ArrayList agentsListStr = new ArrayList(); 	
		ArrayList allAgentAid = new ArrayList();
		
		int globalStep = 1;
		
		private int step = 0;	
		
		private MessageTemplate mtPlan; // The template to receive a plan
		int statut = 0;
		
		protected void setup() {
			
			
			try {
				Thread.sleep(1000);
				//Thread.sleep(wait);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
			try {
				writeStatut(2);
				statut = getStatut();
			} catch (IOException e1) { e1.printStackTrace(); }
			
			System.out.println(" My statut is "+statut);
		// Enregistrement du service dans les pages jaunes
		// -----------------------------------------------
				
			DFAgentDescription dfd = new DFAgentDescription();
				dfd.setName(getAID());
				ServiceDescription sd = new ServiceDescription();
				sd.setType("form-coalition-Creator");
				sd.setName("JADE-form-coalition-Creator");
				dfd.addServices(sd);
				try {
					System.out.println("Agent registered"+this.getAID());
					
					DFService.register(this, dfd);
				
				}
				catch (FIPAException fe) {
					fe.printStackTrace();
				}
				
			
				
				// Afficher le Log sur un fichier Log
				//-----------------------------------------
				if(true){
					PrintStream out = null;
					try { out = new PrintStream(new FileOutputStream("logs/"+this.getLocalName()+"_Log.txt"));
						} catch (FileNotFoundException e2) { e2.printStackTrace();}
					System.setOut(out);
				}
				
		// Add a TickerBehaviour that schedules a check for present agents in the system every minute
		//-------------------------------------------------------------------------------------------
			
		addBehaviour(new CyclicBehaviour(this) {
	
		public void action() {
			
				
			try {
				writeStatut(3);
				statut = getStatut();
			} catch (IOException e1) { e1.printStackTrace(); }
		
			System.out.println(" My statut is "+statut);
		try { 
			Thread.sleep(3000);
		} catch (InterruptedException e) { e.printStackTrace(); }
				
	
		try {
			writeStatut(4);
			statut = getStatut();
		} catch (IOException e1) { e1.printStackTrace(); }
		
		System.out.println(" My statut is "+statut);
		
		try { 
		Thread.sleep(6000);
		} catch (InterruptedException e) { e.printStackTrace(); }

		
		try {
			writeStatut(5);
			statut = getStatut();
		} catch (IOException e1) { e1.printStackTrace(); }
		
		
		System.out.println(" My statut is "+statut);
		
		
		
		System.out.println("Total agent in the system is ");
		step++;	
		
		int maxRound = 3;
		int costLimitPercentage = 80;
		
		
		int Tab[] = new int[] {1,2,3};
		
		boolean isNew = true;
		
		
		
		
		
		
		
		
		
								
		} // fin de action
									
		});
	
	}

	

	
		















public String[] getAgentsInSystem(Agent myAgent){
	
	String agentsStrList[] = null;
	
	// Update the list of agents
	DFAgentDescription template = new DFAgentDescription();
	ServiceDescription sd = new ServiceDescription();
	sd.setType("form-coalition");
	template.addServices(sd);
	
	try {
		DFAgentDescription[] result = DFService.search(myAgent, template); 
		agentsList = new AID[result.length];
		agentsListStr = new ArrayList();
		
		agentsStrList = new String[result.length];
		for (int i = 0; i < result.length; ++i) {
			agentsList[i] = result[i].getName();
			agentsStrList[i]= result[i].getName().getLocalName();
			agentsListStr.add(result[i].getName().getLocalName());
			if(!allAgentAid.contains(agentsList[i])){
				allAgentAid.add(agentsList[i]);
			}
		}
	}
	
	catch (FIPAException fe) {
		fe.printStackTrace();
	}
	
	return agentsStrList;
}


public int getStatut() throws IOException{
	File f = new File("logs/"+this.getLocalName()+".txt");
	Scanner sc = new Scanner(f);
	return sc.nextInt();
}


public void  writeStatut(int st) throws IOException{
	File statutFile = new File("logs/"+this.getLocalName()+".txt"); 
	PrintWriter f = new PrintWriter(new FileWriter(statutFile));
	f.write(st+"");
	f.close();
} 






} // Fin de classe. 


