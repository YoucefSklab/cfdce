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
public class AgentPrincipal2 extends Agent {
		
			int agNbr = 1;
			int round = 0;
			//int experimentNbr = 1;
			//int prcentageCounter = 0;
			
			int experimentNbr = 1;
			int prcentageCounter = 0;
			
			private int GlobalStep = 1;  // l'etape globale 
			private int strategyFixe = 1; // La strat�gie fixe, � utiliser en ex�cution libre 
			private int strategy = 50; // La strat�gie � utiliser par les agents
			ArrayList listAgent = new ArrayList();
			ArrayList listAgentRestant = new ArrayList();
			ArrayList poucentageList = new ArrayList();
			
			int nbrAgent = 0;
			int initialNbrAgent = 0;
			int typeTest = 2; // 1 ex�cution libre, 2 ex�cution en boucle sur les strat�gies.
			boolean demanderReprise = true;
		
			ArrayList agentContainerList = new ArrayList();
		
		// Put agent initializations here
		protected void setup() {
			
			poucentageList.add(100);
			poucentageList.add(99);
			poucentageList.add(98);
			poucentageList.add(97);
			poucentageList.add(96);
			poucentageList.add(95);
			poucentageList.add(94);
			poucentageList.add(93);
			poucentageList.add(92);
			poucentageList.add(91);
			poucentageList.add(90);
			poucentageList.add(89);			
		
				poucentageList.add(88);
				poucentageList.add(87);
				poucentageList.add(86);
				poucentageList.add(85);
				poucentageList.add(84);
				poucentageList.add(83);
				poucentageList.add(82);
				poucentageList.add(81);
				poucentageList.add(80);
				poucentageList.add(79);
				poucentageList.add(78);
				poucentageList.add(77);
				poucentageList.add(76);
				poucentageList.add(75);
				poucentageList.add(74);
				poucentageList.add(73);
				poucentageList.add(72);
				poucentageList.add(71);
				poucentageList.add(70);
				poucentageList.add(69);
				poucentageList.add(68);
				poucentageList.add(67);
				poucentageList.add(66);
				poucentageList.add(65);
				poucentageList.add(64);
				poucentageList.add(63);
				poucentageList.add(62);
				poucentageList.add(61);
				poucentageList.add(60);
				
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
			
			
			
			
			
			if(experimentNbr > 2){
				doSuspend();
			}
			
			if(nbr==0){
				
				try {
					//Thread.sleep(mainWaitingTime);
					//Thread.sleep(wait);
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				// Garbage Collector
				
				System.gc ();
				System.runFinalization ();
				
				if(prcentageCounter>=41){
					experimentNbr++;
					prcentageCounter =0;
				}
				
				int poucentage = (int) poucentageList.get(prcentageCounter);
				prcentageCounter++;
				
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
			 //indice >=0.72
				/*

						if(experimentNbr  == 1){ 
							 //   1
								listAgent.add(32);
								listAgent.add(140);
								listAgent.add(230);
								listAgent.add(331);
								listAgent.add(416);
								listAgent.add(430);
								listAgent.add(872);
								listAgent.add(888);
								listAgent.add(891);
								listAgent.add(9);
							 }
							 if(experimentNbr  == 2){
							 		//   20
								listAgent.add(126);
								listAgent.add(240);
								listAgent.add(278);
								listAgent.add(302);
								listAgent.add(310);
								listAgent.add(370);
								listAgent.add(474);
								listAgent.add(561);
								listAgent.add(766);
								listAgent.add(785);
							 }
							 if(experimentNbr  == 3){
									
								listAgent.add(102);
								listAgent.add(242);
								listAgent.add(297);
								listAgent.add(358);
								listAgent.add(376);
								listAgent.add(397);
								listAgent.add(630);
								listAgent.add(641);
								listAgent.add(649);
								listAgent.add(740);
					 
							 }
							 if(experimentNbr  == 4){
								 
								listAgent.add(15);
								listAgent.add(30);
								listAgent.add(92);
								listAgent.add(93);
								listAgent.add(174);
								listAgent.add(179);
								listAgent.add(193);
								listAgent.add(226);
								listAgent.add(649);
								listAgent.add(765);
						 
							 }
							 if(experimentNbr  == 5){
							 
								 		//   50
								listAgent.add(132);
								listAgent.add(153);
								listAgent.add(214);
								listAgent.add(309);
								listAgent.add(425);
								listAgent.add(467);
								listAgent.add(538);
								listAgent.add(776);
								listAgent.add(811);
								listAgent.add(823);
						 
							 }
							 if(experimentNbr  == 6){			  
							 //   6

								listAgent.add(273);
								listAgent.add(279);
								listAgent.add(287);
								listAgent.add(345);
								listAgent.add(352);
								listAgent.add(388);
								listAgent.add(414);
								listAgent.add(421);
								listAgent.add(434);
								listAgent.add(565);

							 
							 }
							 if(experimentNbr  == 7){			  
							 //   7
								listAgent.add(47);
								listAgent.add(80);
								listAgent.add(167);
								listAgent.add(305);
								listAgent.add(354);
								listAgent.add(606);
								listAgent.add(660);
								listAgent.add(731);
								listAgent.add(875);
								listAgent.add(160);

							 }
							 if(experimentNbr  == 8){			  
							 //   8
								listAgent.add(167);
								listAgent.add(310);
								listAgent.add(364);
								listAgent.add(370);
								listAgent.add(474);
								listAgent.add(601);
								listAgent.add(615);
								listAgent.add(785);
								listAgent.add(791);
								listAgent.add(173);

							 
							 }
							 if(experimentNbr  == 9){			  
							 //   9
								listAgent.add(111);
								listAgent.add(115);
								listAgent.add(278);
								listAgent.add(302);
								listAgent.add(370);
								listAgent.add(561);
								listAgent.add(766);
								listAgent.add(785);
								listAgent.add(864);
								listAgent.add(240);
							 
							 }
							 
							 if(experimentNbr == 10){			  
							 //   10
								listAgent.add(388);
								listAgent.add(406);
								listAgent.add(414);
								listAgent.add(434);
								listAgent.add(565);
								listAgent.add(704);
								listAgent.add(705);
								listAgent.add(739);
								listAgent.add(893);
								listAgent.add(273);
							 } 
								 
					*/	
							 
				// Indice <=0.71 et >=0.62
					//---------------------------
					
					 
					// Indice <=0.61 et >=0.52
					//---------------------------
				 
				 
				 
					// Indice <=0.51 et >=0.42
					//---------------------------
				 
				 
					// Indice <=0.41 et >=0.32
					//---------------------------				 
				 
				 
					// Indice <=0.31 et >=0.22
					//---------------------------
				 
				 
					// Indice <=0.21 et >=0.12
					//---------------------------		
				 

				if(experimentNbr  == 1){
				listAgent.add(1);
				listAgent.add(2);
				listAgent.add(3);
				listAgent.add(4);
				
				}

				if(experimentNbr  == 2){
					listAgent.add(1);
					listAgent.add(2);
					listAgent.add(3);
					listAgent.add(4);
				}

					if(experimentNbr  == 3){
						listAgent.add(1);
						listAgent.add(2);
						listAgent.add(3);
						listAgent.add(4);
					}

				if(experimentNbr  == 4){
					listAgent.add(1);
					listAgent.add(2);
					listAgent.add(3);
					listAgent.add(4);
				}
				 
				if(experimentNbr  == 5){
					listAgent.add(1);
					listAgent.add(2);
					listAgent.add(3);
					listAgent.add(4);
				}

				if(experimentNbr  == 6){
					listAgent.add(1);
					listAgent.add(2);
					listAgent.add(3);
					listAgent.add(4);
				}

				if(experimentNbr  == 7){
					listAgent.add(1);
					listAgent.add(2);
					listAgent.add(3);
					listAgent.add(4);
				}
				 
				if(experimentNbr  == 8){
					listAgent.add(1);
					listAgent.add(2);
					listAgent.add(3);
					listAgent.add(4);
				}
				 
				if(experimentNbr  == 9){
					listAgent.add(1);
					listAgent.add(2);
					listAgent.add(3);
					listAgent.add(4);
				}
				 
				if(experimentNbr  == 10){
					listAgent.add(1);
					listAgent.add(2);
					listAgent.add(3);
					listAgent.add(4);
				}
							 
					// Indice <=0.11 et >=0.02
					//---------------------------	
					
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
								//profileImpl.setParameter(ProfileImpl.MAIN_HOST,"orion-4.lyon.grid5000.fr");
								
								AgentContainer agentContainer=runtime.createAgentContainer(profileImpl);
								
								
								AgentController agentController;
								//agentController = agentContainer.createNewAgent("Agent"+agNbr, "Agents.Agent2", new Object[]{"Agent"+agNbr, ""+agNbr, ""+totalWatingTime, ""+poucentage, ""+0});
								agentController = agentContainer.createNewAgent("Agent"+agNbr, "Agents.AgentPaperExample", new Object[]{"Agent"+agNbr, ""+agNbr, ""+totalWatingTime, ""+poucentage, ""+0});
								
								agentController.start();
								
								agentContainerList.add(agentController);
								
								
								/*
							     Runtime runtime = Runtime.getRuntime();
							     Process p2  = runtime.exec(new String[]{"cmd.exe","/c","start  java jade.Boot -agents Agent"+agNbr+":Agents.Agent1(Agent"+agNbr+","+agNbr+","+totalWatingTime+","+poucentage+",0) -container -host localhost"});
								*/
							   //  System.out.println("start  java jade.Boot -agents Agent"+agNbr+":Agents.Agent1(Agent"+agNbr+","+agNbr+","+totalWatingTime+","+poucentage+",0) -container -host localhost");
								
								
							 //   Process p2  = runtime.exec(new String[]{"cmd.exe","/c","start  java jade.Boot -agents "+agentName+":Agents.Agent1("+agentName+","+agNbr+","+watingTime+","+poucentage+") -container -host localhost"});
							 	
								
							} catch (InterruptedException | StaleProxyException e) { e.printStackTrace(); }
									
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


