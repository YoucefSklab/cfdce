package com.cfdce.Agents;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

import com.cfdce.Plan.planSet;

import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.ControllerException;

public class AgentPrincipal {

	public static void main(String[] args) throws InterruptedException, ControllerException {
		
	// Pr�paration du plan de l'agent
		
		planSet pSet = new planSet();
	
		ArrayList listAgent = new ArrayList();
		int nbrAgent = 5;
		int iterations = 3;
		int GlobalStep = 1;
		int agNbr = 1;

		try { GlobalStep = loadGlobalStep(); } catch (FileNotFoundException e1) {e1.printStackTrace();}
		
		System.out.println("La Liste est vide, Alors, Lancement de nouveaux agents !!!! ");
		System.out.println("");
		
		System.out.println("-->  GlobalStep : "+GlobalStep);
		//System.out.println("-->  Strategy : "+strategy);
		System.out.println("-->  Number of Agents to run : "+nbrAgent);
		int totalWatingTime = 800 * nbrAgent;
		long currentTime = (long) System.currentTimeMillis();
		long lastTime = 0;
		int poucentage = ThreadLocalRandom.current().nextInt(40, 60);
		
		poucentage = 80;
		System.out.println("- Limite budget : "+poucentage);
		
	
		PrintWriter f = null;
		try {
			f = new PrintWriter(new FileWriter("logs/"+(GlobalStep+1)+"_AgentsList.txt")); // � laisser avec +1
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//f.println(" ");
		
		
		
		totalWatingTime += 5000;
	//	for(int t=0; t<20; t++){
	//		listAgent.clear();
			//nbrAgent  =  ThreadLocalRandom.current().nextInt(4, 6);
			//nbrAgent = ThreadLocalRandom.current().nextInt(6, 13);
			//nbrAgent =7;
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
			
	//		System.out.println(" Liste des agents: "+listAgent.toString());
			
	//	}	
			
		//	listAgent.clear();
			
			
			
			


			//			1
/*
			listAgent.add(42);
			listAgent.add(60);
			listAgent.add(9);
			listAgent.add(17);
			listAgent.add(76);
			listAgent.add(82);
			listAgent.add(50);
			listAgent.add(1);
*/
			//			2
/*
			listAgent.add(11);
			listAgent.add(66);
			listAgent.add(18);
			listAgent.add(64);
			listAgent.add(52);
			listAgent.add(19);
			listAgent.add(78);
			listAgent.add(1);
*/

			//		3 
/*
			listAgent.add(46);
			listAgent.add(51);
			listAgent.add(76);
			listAgent.add(37);
			listAgent.add(39);
			listAgent.add(75);
			listAgent.add(21);
			listAgent.add(1);
*/
			//		4
/*
			listAgent.add(17);
			listAgent.add(79);
			listAgent.add(65);
			listAgent.add(21);
			listAgent.add(69);
			listAgent.add(64);
			listAgent.add(96);
			listAgent.add(1);
*/

			//		5
/*
			listAgent.add(49);
			listAgent.add(96);
			listAgent.add(18);
			listAgent.add(71);
			listAgent.add(21);
			listAgent.add(94);
			listAgent.add(36);
			listAgent.add(1);
*/
			//		6
/*
			listAgent.add(71);
			listAgent.add(46);
			listAgent.add(67);
			listAgent.add(63);
			listAgent.add(6);
			listAgent.add(61);
			listAgent.add(86);
			listAgent.add(1);
*/
			//		7
/*
			listAgent.add(60);
			listAgent.add(46);
			listAgent.add(42);
			listAgent.add(91);
			listAgent.add(17);
			listAgent.add(20);
			listAgent.add(88);
			listAgent.add(1);
*/
			//		8
/*
			listAgent.add(44);
			listAgent.add(95);
			listAgent.add(6);
			listAgent.add(96);
			listAgent.add(30);
			listAgent.add(80);
			listAgent.add(78);
			listAgent.add(1);
*/
			//		9
/*
			listAgent.add(67);
			listAgent.add(60);
			listAgent.add(93);
			listAgent.add(74);
			listAgent.add(17);
			listAgent.add(89);
			listAgent.add(40);
			listAgent.add(1);
*/
			//		10

			/*
			listAgent.add(79);
			listAgent.add(45);
			listAgent.add(42);
			listAgent.add(44);
			listAgent.add(50);
			listAgent.add(80);
			listAgent.add(47);
			listAgent.add(1);
*/


			
			
		
		
		
		
		//f.println(" ");
		
		ArrayList agentContainerList = new ArrayList();
		for(int i=0; i<listAgent.size(); i++){
			agNbr = (int) listAgent.get(i);
			try {
				
				currentTime = (long) System.currentTimeMillis();
				//newAgent2("Agent"+agNbr, agNbr, totalWatingTime, poucentage);
				System.out.println("Le temps "+ (800-(System.currentTimeMillis() - currentTime)));
				Thread.sleep(800-(System.currentTimeMillis() - currentTime));
				totalWatingTime-=800;
				f.println("listAgent.add("+agNbr+");\n");
				
				
				Runtime runtime=Runtime.instance();
				ProfileImpl profileImpl=new ProfileImpl(false);
				profileImpl.setParameter(ProfileImpl.MAIN_HOST,"localhost");
				
				AgentContainer agentContainer=runtime.createAgentContainer(profileImpl);
				
				
				AgentController agentController;
				agentController = agentContainer.createNewAgent("Agent"+agNbr, "Agents.Agent2", new Object[]{"Agent"+agNbr, ""+agNbr, ""+totalWatingTime, ""+poucentage, ""+0});
				agentController.start();
				
				agentContainerList.add(agentController);
				
				
				
			//     Process p2  = runtime.exec(new String[]{"cmd.exe","/c","start  java jade.Boot -agents "+agentName+":Agents.Agent1("+agentName+","+agNbr+","+watingTime+","+poucentage+") -container -host localhost"});
			 	
				
			} catch (InterruptedException e) { e.printStackTrace(); }
			
		} // fin de for
		
		f.close();
		System.out.println("-->  : "+listAgent.toString());
		
		
		
		
	/*	
		
		for (int i = 1; i <= 12; i++) {		
			Runtime runtime=Runtime.instance();
			ProfileImpl profileImpl=new ProfileImpl(false);
			profileImpl.setParameter(ProfileImpl.MAIN_HOST,"localhost");
			AgentContainer agentContainer=runtime.createAgentContainer(profileImpl);
			
			
			AgentController agentController;
			agentController = agentContainer.createNewAgent("Agent"+i, "Agents.Agent1", new Object[]{""+i});
			agentController.start();
			//AgentThread thread = new AgentThread(i, agentContainer);
			//thread.start();
		}
		
		*/
		
		
		// Mise � jour devariable  GlobalStep et  strategy
		try { 
			GlobalStep++;
			Thread.sleep(2000);
			writeGlobalStep(GlobalStep);
			//writeStrategy(ThreadLocalRandom.current().nextInt(1, 5));
			//writeStrategy(strategy);
		}catch(IOException e1){e1.printStackTrace();} 
		
	

		
		
	}
	
	
	
	
	/**
	 * Sauvegarde du compteur des �tapes globales.
	 * @param GlobalStep Le compteur sur l'�tape globale
	 * @throws IOException
	 */
	public static void  writeGlobalStep(int GlobalStep) throws IOException{
			PrintWriter f = new PrintWriter(new FileWriter("config/GlobalStep.txt"));
			f.write(GlobalStep+"");
			f.close();

	} // fin de la M�thode
	
	
	/**
	 * Chargement du compteur sur l'�tape  local � un agent
	 * @return un nembre entier (le compteur) 
	 * @throws FileNotFoundException
	 */
	public static int loadGlobalStep() throws FileNotFoundException{
		int GlobalStep = 0;
		File f = new File("config/GlobalStep.txt");
		Scanner sc = new Scanner(f);
		while(sc.hasNextInt()) {
			GlobalStep = sc.nextInt();
			}
		
		return GlobalStep;
	} // fin de la M�thode
	
} //  fin de la classe
