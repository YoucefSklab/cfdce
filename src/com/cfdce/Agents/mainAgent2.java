package com.cfdce.Agents;




import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
//import java.util.ArrayList;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.util.leap.ArrayList;

/**
 * Impl�mentation des comportemens d'un agent.
 * @author SKLAB Youcef
 * @version 0.3
 * 
 *  
 */
public class mainAgent2 extends Agent {
		
		int agNbr = 1;
		int round = 0;
		private int GlobalStep = 1;  // l'etape globale 
		private int strategyFixe = 1; // La strat�gie fixe, � utiliser en ex�cution libre 
		private int strategy = 50; // La strat�gie � utiliser par les agents
		ArrayList listAgent = new ArrayList();
		ArrayList listAgentRestant = new ArrayList();
		
		int nbrAgent = 0;
		int initialNbrAgent = 0;
		int typeTest = 2; // 1 ex�cution libre, 2 ex�cution en boucle sur les strat�gies.
		boolean demanderReprise = true;
		
		// Put agent initializations here
		protected void setup() {
		
			int wait = ThreadLocalRandom.current().nextInt(1000, 3000);
			//agNbr = 1;
			
			try {
				//Thread.sleep(mainWaitingTime);
				//Thread.sleep(wait);
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
					
				DFAgentDescription dfd = new DFAgentDescription();
				dfd.setName(getAID());
				ServiceDescription sd = new ServiceDescription();
				sd.setType("mainAgent");
				sd.setName("JADE-mainAgent");
				dfd.addServices(sd);
				try {
					DFService.register(this, dfd);
				}
				catch (FIPAException fe) {
					fe.printStackTrace();
				}
				
				
			
				
				
	addBehaviour(new CyclicBehaviour(this) {
	
	public void action() {
			
			// si lecture de la liste d'agents � lancer sur fichier :
			/*
			try {
				lookInFile(myAgent); } catch (IOException e2) {e2.printStackTrace();}
			*/
			round++;
			int nbr = nbrAgentInSystemsendPlan (myAgent);
			System.out.println("Le nombre d'agents dans le syst�mes est : "+nbr);
			

			
		if(typeTest == 2 ){	
			
			if(nbr==0){
				
				try { GlobalStep = loadGlobalStep(); } catch (FileNotFoundException e1) {e1.printStackTrace();}
			//	strategy++;
				
				// Reconstruction des variables 
			//	if(strategy>10){
					strategy = 1;
					//nbrAgent  =  ThreadLocalRandom.current().nextInt(4, 6);
					nbrAgent = ThreadLocalRandom.current().nextInt(6, 13);
					nbrAgent =7;
					listAgent.clear();
					for(int j=0; j<nbrAgent; j++){
						int var = ThreadLocalRandom.current().nextInt(2, 100);
						if(!listAgent.contains(var)) {
							listAgent.add(var);
						}else{
							j--;
						}
					}
					listAgent.add(1);
			
			//	}
			// Mise � jour devariable  GlobalStep et  strategy
				try { 
					writeGlobalStep(GlobalStep);
					//writeStrategy(ThreadLocalRandom.current().nextInt(1, 5));
					//writeStrategy(strategy);
				}catch(IOException e1){e1.printStackTrace();}  
				
				System.out.println("La Liste est vide, Alors, Lancement de nouveaux agents !!!! ");
				System.out.println("");
				System.out.println("-->  Round : "+round);
				System.out.println("-->  GlobalStep : "+GlobalStep);
				//System.out.println("-->  Strategy : "+strategy);
				System.out.println("-->  Number of Agents to run : "+nbrAgent);
				int totalWatingTime = 800 * nbrAgent;
				long currentTime = (long) System.currentTimeMillis();
				long lastTime = 0;
				int poucentage = ThreadLocalRandom.current().nextInt(40, 60);
				
				System.out.println("- Limite budget : "+poucentage);
				
			
				PrintWriter f = null;
				try {
					f = new PrintWriter(new FileWriter("logs/"+(GlobalStep+1)+"_AgentsList.txt"));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				f.println(" ");
				
				
				for(int i=0; i<listAgent.size(); i++){
					agNbr = (int) listAgent.get(i);
					try {
						
						currentTime = (long) System.currentTimeMillis();
						newAgent2("Agent"+agNbr, agNbr, totalWatingTime, poucentage);
						System.out.println("Le temps "+ (700-(System.currentTimeMillis() - currentTime)));
						Thread.sleep(800-(System.currentTimeMillis() - currentTime));
						totalWatingTime-=800;
						f.println("Agent"+agNbr+"\n");
					} catch (InterruptedException e) { e.printStackTrace(); }
					
				} // fin de for
				
				f.close();
				System.out.println("-->  : "+listAgent.toString());
				
				
				Scanner sc = new Scanner(System.in);
				System.out.println("Veuillez taper une touche pour continuer !!!");
				String reponse = sc.nextLine();
			
			}else{
				System.out.println("Pr�sence d'agents, attente la fin du round !!!");
				System.out.println("-->  Round : "+round);
			}
			
		} // fin de if
		
			
		
		//-----------------------------------------------------------------------------------------------
		
		if(typeTest==3){
			
			try { 
				writeGlobalStep(GlobalStep);
				//strategyFixe = ThreadLocalRandom.current().nextInt(1, 5);
				//writeStrategy(ThreadLocalRandom.current().nextInt(1, 5));
				//writeStrategy(strategyFixe);
			}catch(IOException e1){e1.printStackTrace();} 
			
			try {
				lookInFile(myAgent); } catch (IOException | InterruptedException e2) {e2.printStackTrace();}
			myAgent.doSuspend();
			typeTest=2;
		}
	
		//------------------------------------------------------------------------------------------------

		
			try {
				//Thread.sleep(mainWaitingTime);
				//Thread.sleep(wait);
				Thread.sleep(20000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		
		} // fin de action
									
		});
	
	}

		
		
		
		
	// Put agent clean-up operations here
	protected void takeDown() {
		// Printout a dismissal message
		System.out.println("Agent "+getAID().getName()+" terminating.");
	}






/**
 * Le nombre d'agents pr�sent dans le system.
 * @myAgent agent propri�taire
 */
private int nbrAgentInSystemsendPlan (Agent myAgent){

		DFAgentDescription template = new DFAgentDescription();
		ServiceDescription sd = new ServiceDescription();
		sd.setType("form-coalition");
		sd.setName("JADE-form-coalition");
		template.addServices(sd);
			try {
				DFAgentDescription[] result = DFService.search(myAgent, template); 
				//System.out.println("Existance d'agents -->    "+result.length);
				return result.length;
			}
			catch (FIPAException fe) {
				//System.out.println("Pas d'existance d'agents");
				fe.printStackTrace();
			}
		return 0;	
} 


public static void newAgent(String agentName, int agNbr, int watingTime) throws InterruptedException{
	
	try {
	     Runtime runtime = Runtime.getRuntime();
	     Process p2  = runtime.exec(new String[]{"cmd.exe","/c","start  java jade.Boot -agents "+agentName+":Agents.Agent1("+agNbr+","+watingTime+") -container -host localhost"});
	    } catch (IOException e) {
		e.printStackTrace();
	    }
}

public static void newAgent2(String agentName, int agNbr, int watingTime, int poucentage) throws InterruptedException{
	
	try {
	     Runtime runtime = Runtime.getRuntime();
	     Process p2  = runtime.exec(new String[]{"cmd.exe","/c","start  java jade.Boot -agents "+agentName+":Agents.Agent1("+agentName+","+agNbr+","+watingTime+","+poucentage+") -container -host localhost"});
	    } catch (IOException e) {
		e.printStackTrace();
	    }
}


			













/**
 * R�initialisation du compteur de sur l'�tape locale � un agent
 * @param agent Nom de l'agent en question
 * @throws IOException
 */
public void  resetStepNbr(String agent) throws IOException{
	PrintWriter f = new PrintWriter(new FileWriter("config/"+agent+".txt"));
	f.write("1");
	f.close();
} // fin de la M�thode


/**
 * Chargement du compteur sur l'�tape  local � un agent
 * @return un nembre entier (le compteur) 
 * @throws FileNotFoundException
 */
public int loadGlobalStep() throws FileNotFoundException{
	int GlobalStep = 0;
	File f = new File("config/GlobalStep.txt");
	Scanner sc = new Scanner(f);
	while(sc.hasNextInt()) {
		GlobalStep = sc.nextInt();
		}
	
	return GlobalStep;
} // fin de la M�thode

/**
 * Sauvegarde du compteur des �tapes globales.
 * @param GlobalStep Le compteur sur l'�tape globale
 * @throws IOException
 */
public void  writeGlobalStep(int GlobalStep) throws IOException{
		PrintWriter f = new PrintWriter(new FileWriter("config/GlobalStep.txt"));
		GlobalStep++;
		f.write(GlobalStep+"");
		f.close();

} // fin de la M�thode



/**
 * Sauvegarde de La strat�gie d'ordonnancement � adopter par les agents.
 * @param Strategy La strat�gie � sauvegarder
 * @throws IOException
 */
public void  writeStrategy(int Strategy) throws IOException{
		PrintWriter f = new PrintWriter(new FileWriter("config/OrderingStrategy.txt"));
		f.write(Strategy+"");
		f.close();

} // fin de la M�thode

/**
 * Chargement de la strat�gie d'ordonnancement sur l'�tape locale � un agent.
 * @return la strat�gie d'ordonnancement � utiliser
 * @throws FileNotFoundException
 */
public int loadStrategy() throws FileNotFoundException{
	int strategy = 0;
	File f = new File("config/OrderingStrategy.txt");
	Scanner sc = new Scanner(f);
	while(sc.hasNextInt()) {
		strategy = sc.nextInt();
		}
	return strategy;
} // fin de la M�thode



/**
 * Lecture depuis un fichier la liste des agents � ex�cuter.
 * @param myAgent L'agent en main
 * @throws IOException
 * @throws InterruptedException 
 */
public void lookInFile(Agent myAgent) throws IOException, InterruptedException{
	
	File f = new File("config/AgentsListToRun.txt");
	Scanner sc = new Scanner(f);
	
	int totalWatingTime = 800 * nbrAgent;
	long currentTime = (long) System.currentTimeMillis();
	long lastTime = 0;
	
	if(sc.hasNextLine()){
		String option = sc.nextLine();
		
		if(option.equals("YES")){
			int poucentage = ThreadLocalRandom.current().nextInt(40, 60);
			System.out.println("Lecture depuis un fichier de la liste des agents � lancer !!!....");
			System.out.println("- Limite budget : "+poucentage);
			System.out.println("- Liste des agents : ");
			
			while(sc.hasNextLine()) {
				String agent  = sc.nextLine();
				int agNbr = Integer.parseInt((agent.substring(5, agent.length())));
				
				
			//	Runtime runtime = Runtime.getRuntime();
			//	Process p2  = runtime.exec(new String[]{"cmd.exe","/c","start  java jade.Boot -agents "+agent+":Agents.Agent1("+agNbr+") -container -host localhost"});
				
					
			//	System.out.println("- "+agent);
				
				
				currentTime = (long) System.currentTimeMillis();
				newAgent2("Agent"+agNbr, agNbr, totalWatingTime, poucentage);
				System.out.println("Le temps "+ (700-(System.currentTimeMillis() - currentTime)));
				Thread.sleep(800-(System.currentTimeMillis() - currentTime));
				totalWatingTime-=800;
			}
		  
		}
	}
	
	
	
	
	

	
	
	
} // fin de la M�thode


} // Fin de classe. 


