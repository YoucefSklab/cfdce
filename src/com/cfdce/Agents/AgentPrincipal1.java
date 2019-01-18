package com.cfdce.Agents;




import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

import jade.core.Agent;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.core.behaviours.CyclicBehaviour;
//import java.util.ArrayList;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
//import jade.util.leap.ArrayList;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;

/**
 * Impl�mentation des comportemens d'un agent.
 * @author SKLAB Youcef
 * @version 0.3
 * 
 *  
 */
public class AgentPrincipal1 extends Agent {
		
			int agNbr = 1;
			int round = 0;
			int experimentNbr = 0;
			private int GlobalStep = 1;  // l'etape globale 
			private int strategyFixe = 1; // La strat�gie fixe, � utiliser en ex�cution libre 
			private int strategy = 50; // La strat�gie � utiliser par les agents
			ArrayList listAgent = new ArrayList();
			ArrayList listAgentRestant = new ArrayList();
			
			int nbrAgent = 0;
			int initialNbrAgent = 0;
			int typeTest = 2; // 1 ex�cution libre, 2 ex�cution en boucle sur les strat�gies.
			boolean demanderReprise = true;
		
			ArrayList agentContainerList = new ArrayList();
		
		// Put agent initializations here
		protected void setup() {
			
			
		
			
				
				DFAgentDescription dfd = new DFAgentDescription();
				dfd.setName(getAID());
				ServiceDescription sd = new ServiceDescription();
				sd.setType("CollectAgentsInformation");
				sd.setName("CollectAgentInformation");
				dfd.addServices(sd);
				try {
					DFService.register(this, dfd);
				}
				catch (FIPAException fe) {
					fe.printStackTrace();
				}
				
				
		// Add a TickerBehaviour that schedules a check for present agents in the system every minute
		//-------------------------------------------------------------------------------------------
			
		addBehaviour(new CyclicBehaviour(this) {
	
		public void action() {
					
			System.out.println("Good morning !! I am back");
			
			
			
			
			
			round++;
			int nbr = nbrAgentInSystemsendPlan (myAgent);
			System.out.println("Le nombre d'agents dans le syst�mes est : "+nbr);
			
			
			
			
			
			if(experimentNbr >= 10){
				doSuspend();
			}
			
			if(nbr==0){
				
				experimentNbr++;
				
				// Fermer les Agent Container. 
				if(agentContainerList.size()>0){
					for(int y=0; y<agentContainerList.size(); y++){
						AgentController ag = (AgentController) agentContainerList.get(y);
						try {
							ag.kill();
						} catch (StaleProxyException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
				}
				
				agentContainerList.clear();
				
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
					
					
					
			 listAgent.clear();
			 
			 
			 if(experimentNbr == 1){ 
			 //   1
			 listAgent.add(23);
			 listAgent.add(54);
			 listAgent.add(97);
			 listAgent.add(82);
			 listAgent.add(88);
			 listAgent.add(73);
			 listAgent.add(29);
			 listAgent.add(1);
			 
			 }
			 if(experimentNbr == 2){
			 
			 //   2
			 listAgent.add(82);
			 listAgent.add(33);
			 listAgent.add(90);
			 listAgent.add(89);
			 listAgent.add(94);
			 listAgent.add(37);
			 listAgent.add(46);
			 listAgent.add(1);
			 
			 }
			 if(experimentNbr == 3){
			  
			 //   3
			 listAgent.add(97);
			 listAgent.add(15);
			 listAgent.add(25);
			 listAgent.add(40);
			 listAgent.add(99);
			 listAgent.add(50);
			 listAgent.add(81);
			 listAgent.add(1);
			 
			 }
			 if(experimentNbr == 4){
			  
			 //   4
			 listAgent.add(61);
			 listAgent.add(36);
			 listAgent.add(50);
			 listAgent.add(17);
			 listAgent.add(96);
			 listAgent.add(43);
			 listAgent.add(75);
			 listAgent.add(1);
			 
			 }
			 if(experimentNbr == 5){
			  
			 //   5
			 listAgent.add(70);
			 listAgent.add(6);
			 listAgent.add(91);
			 listAgent.add(68);
			 listAgent.add(92);
			 listAgent.add(88);
			 listAgent.add(47);
			 listAgent.add(1);
			 
			 }
			 if(experimentNbr == 6){			  
			 //   6
			 listAgent.add(52);
			 listAgent.add(41);
			 listAgent.add(83);
			 listAgent.add(33);
			 listAgent.add(75);
			 listAgent.add(13);
			 listAgent.add(38);
			 listAgent.add(1);
			 
			 }
			 if(experimentNbr == 7){			  
			 //   7
			 listAgent.add(37);
			 listAgent.add(19);
			 listAgent.add(16);
			 listAgent.add(70);
			 listAgent.add(74);
			 listAgent.add(53);
			 listAgent.add(48);
			 listAgent.add(1);
			 
			 }
			 if(experimentNbr == 8){			  
			 //   8
			 listAgent.add(31);
			 listAgent.add(96);
			 listAgent.add(58);
			 listAgent.add(95);
			 listAgent.add(16);
			 listAgent.add(82);
			 listAgent.add(4);
			 listAgent.add(1);
			 
			 }
			 if(experimentNbr == 9){			  
			 //   9
			 listAgent.add(66);
			 listAgent.add(14);
			 listAgent.add(95);
			 listAgent.add(76);
			 listAgent.add(80);
			 listAgent.add(54);
			 listAgent.add(30);
			 listAgent.add(1);
			 
			 }
			 
			 if(experimentNbr == 10){			  
			 //   10
			 listAgent.add(38);
			 listAgent.add(37);
			 listAgent.add(81);
			 listAgent.add(71);
			 listAgent.add(70);
			 listAgent.add(4);
			 listAgent.add(56);
			 listAgent.add(1);
			 } 
				
					
					
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
				int totalWatingTime = 1000 * nbrAgent;
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
				
				f.println(" /* ");
				f.println(" 		//   "+(GlobalStep+1));
				
				for(int i=0; i<listAgent.size(); i++){
					agNbr = (int) listAgent.get(i);
			
						
						currentTime = (long) System.currentTimeMillis();
												
							
						//------------------------------------------------------------
					
					
							try {
								
								currentTime = (long) System.currentTimeMillis();
								//newAgent2("Agent"+agNbr, agNbr, totalWatingTime, poucentage);
								System.out.println("Le temps "+ (1000-(System.currentTimeMillis() - currentTime)));
								System.out.println("Lancement de l'agent: Agent"+agNbr );
								Thread.sleep(1000-(System.currentTimeMillis() - currentTime));
								totalWatingTime-=1000;
								f.println("listAgent.add("+agNbr+");\n");
								
								
								Runtime runtime=Runtime.instance();
								ProfileImpl profileImpl=new ProfileImpl(false);
								profileImpl.setParameter(ProfileImpl.MAIN_HOST,"localhost");
								
								AgentContainer agentContainer=runtime.createAgentContainer(profileImpl);
								
								
								AgentController agentController;
								agentController = agentContainer.createNewAgent("Agent"+agNbr, "Agents.Agent1", new Object[]{"Agent"+agNbr, ""+agNbr, ""+totalWatingTime, ""+poucentage, ""+0});
								agentController.start();
								
								agentContainerList.add(agentController);
								
								
								
							//     Process p2  = runtime.exec(new String[]{"cmd.exe","/c","start  java jade.Boot -agents "+agentName+":Agents.Agent1("+agentName+","+agNbr+","+watingTime+","+poucentage+") -container -host localhost"});
							 	
								
							} catch (InterruptedException e) { e.printStackTrace(); } catch (StaleProxyException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
				//------------------------------------------------------------
									
				} // fin de for
				
				f.println(" */ ");
				f.close();
				System.out.println("-->  : "+listAgent.toString());
				
				/*
					Scanner sc = new Scanner(System.in);
					System.out.println("Veuillez taper une touche pour continuer !!!");
					String reponse = sc.nextLine();
				 
				*/
			}else{
				System.out.println("Pr�sence d'agents, attente la fin du round !!!");
				System.out.println("-->  Round : "+round);
			}
			
			
			try {
				//Thread.sleep(mainWaitingTime);
				//Thread.sleep(wait);
				Thread.sleep(10000);
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


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

} // Fin de classe. 


