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
public class AgentsCreator extends Agent {
		
		public AID[] agentsList; 	
		public ArrayList agentsListStr = new ArrayList(); 	
		ArrayList allAgentAid = new ArrayList();
		
		int globalStep = 1;
		
		private int step = 0;	
		
		private MessageTemplate mtPlan; // The template to receive a plan
		
		
		protected void setup() {
			
			
			try {
				Thread.sleep(1000);
				//Thread.sleep(wait);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
			
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
			
				
		
		int totalAgent  = 1;
		int totalAgent1 = 1;
		int totalAgent2 = 1;
		
		try { 
			Thread.sleep(2000);
		} catch (InterruptedException e) { e.printStackTrace(); }
				
		totalAgent =  getAgentsInSystem(this.getAgent()).length;

		try { 
		Thread.sleep(3000);
		} catch (InterruptedException e) { e.printStackTrace(); }

		totalAgent1 =  getAgentsInSystem(this.getAgent()).length;
		
		try { 
		Thread.sleep(3000);
		} catch (InterruptedException e) { e.printStackTrace(); }

		totalAgent2 =  getAgentsInSystem(this.getAgent()).length;


		System.out.println("Total agent in the system is "+totalAgent);
		step++;	
		
		int maxRound = 3;
		int costLimitPercentage = 80;
		
		
		int Tab[] = new int[] {1,2,3};
		
		boolean isNew = true;
		
		for(int i=0; i<Tab.length; i++) {
			int st = 1;
			try {
				st = getStatut("Agent"+Tab[i]);
			} catch (IOException e) { e.printStackTrace() ; }
			
			if(st == 1 ) isNew = false;
		}
		
		
		
		
	if(isNew){
		System.exit(0);
		
		
		
		try {	
			globalStep++;
			//globalStep = loadGlobalStep(); 
			writeGlobalStep(globalStep);
			} catch (IOException e1) { e1.printStackTrace(); }
		try {
				Runtime runtimeAgentsCreator=Runtime.instance();
				ProfileImpl profileImplAgentsCreator=new ProfileImpl(false);
				profileImplAgentsCreator.setParameter(ProfileImpl.MAIN_HOST,"localhost");
				AgentContainer agentContainerAgentsCreator = runtimeAgentsCreator.createAgentContainer(profileImplAgentsCreator); 
				AgentController agentControllerAgentsCreator;
				
				// pour ne pas créer des boucles
				
				agentControllerAgentsCreator = agentContainerAgentsCreator.createNewAgent("Ordonnanceur"+globalStep, "com.cfdce.Agents.Ordonnanceur", new Object[]{});
				agentControllerAgentsCreator.start();
		
			// -------------------------------------	
			
			for (int i = 0; i < Tab.length; i++) {
				int nbr = Tab[i];
				try {				
					agentControllerAgentsCreator = agentContainerAgentsCreator.createNewAgent("Agent"+nbr, "com.cfdce.Agents.Agent2", new Object[]{"Agent"+nbr, ""+nbr, "1000", 
							""+costLimitPercentage, ""+0, ""+maxRound, ""+globalStep});
					agentControllerAgentsCreator.start();		
				} catch (StaleProxyException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			//--------------------------------------
			
		} catch (StaleProxyException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
			
			
		}
		
		
		
		
		
		switch (step){
					
		//----------------------------------------------------------------------------------------------------------------------
			
			case 0: // New round. 
			
			break;
				
		//----------------------------------------------------------------------------------------------------------------------
		
			case 1:  // wait 
					
			break;
		//----------------------------------------------------------------------------------------------------------------------
		
		}
						
		} // fin de action
									
		});
	
	}

	// Put agent clean-up operations here
	protected void takeDown() {
		// Printout a dismissal message
		System.out.println("Agent "+getAID().getName()+" terminating.");
	}

	
		
//-------------------------------------------------------------------------		
//*************************************************************************
//-------------------------------------------------------------------------
	/**
   Inner methode senPlan.
   This is the methode used by agents to send the plan.
	 * @throws IOException 
 */
private void sendPlan (Agent myAgent) throws IOException{

		
	String agentsStrList[] = null;
	agentsStrList = getAgentsInSystem(myAgent);
	ACLMessage sendPlan = new ACLMessage(ACLMessage.INFORM);
	
	for (int i = 0; i < agentsList.length; ++i) {
		sendPlan.addReceiver(agentsList[i]);
	} // fin de for
	
			sendPlan.setContentObject(1);
			sendPlan.setConversationId("send-plan");
			sendPlan.setReplyWith("propagate"+System.currentTimeMillis()); // Unique value
			myAgent.send(sendPlan);
			mtPlan = MessageTemplate.and(MessageTemplate.MatchConversationId("send-plan"),
					MessageTemplate.MatchInReplyTo(sendPlan.getReplyWith()));
		
		
}  // End of inner methode sendPlan
		




//-------------------------------------------------------------------------		
//*************************************************************************
//-------------------------------------------------------------------------
/**
	   Inner methode planReception.
	   This is the methode used by the agents to recieve incoming  messages  
	   containing a plan from other agents.
	   
	 */
private void messageReception (Agent myAgent){
	
	
	MessageTemplate mtPlan = MessageTemplate.MatchPerformative(ACLMessage.INFORM);
	ACLMessage msg = myAgent.receive(mtPlan);
	ArrayList totalAgents = new ArrayList();
	
	
	
	
	while(msg != null) {
	
		try {
			int answer = (int) msg.getContentObject();
			String senderName = msg.getSender().getLocalName(); 
			totalAgents.add(senderName);
			msg = myAgent.receive(mtPlan); 
			
	} catch (UnreadableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	} // fin de if
	
	
}  // End of inner methode planReception
//-------------------------------------------------------------------------		
//*************************************************************************
//-------------------------------------------------------------------------	

















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




/**
 * Chargement du compteur sur l'�tape  local � un agent
 * @return un nembre entier (le compteur) 
 * @throws FileNotFoundException
 */
public int loadGlobalStep() throws FileNotFoundException{
	File f = new File("config/GlobalStep.txt");
	Scanner sc = new Scanner(f);
		return sc.nextInt();
} // fin de la M�thode

/**
 * Sauvegarde du compteur des �tapes globales.
 * @param agent Agent en charge de la sauvegarde (un seul parmi les agents)
 * @param GlobalStep Le compteur sur l'�tape globale
 * @throws IOException
 */
public void  writeGlobalStep(int GlobalStep) throws IOException{
		PrintWriter f = new PrintWriter(new FileWriter("config/GlobalStep.txt"));
		f.write((GlobalStep+1)+"");
		f.close();
} // fin de la M�thode





public int getStatut(String name) throws IOException{
	File f = new File("logs/"+name+".txt");
	Scanner sc = new Scanner(f);
	return sc.nextInt();
} 



} // Fin de classe. 


