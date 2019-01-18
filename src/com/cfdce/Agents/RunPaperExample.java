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

public class RunPaperExample {

	public static void main(String[] args) throws InterruptedException, ControllerException {
		
	// Pr�paration du plan de l'agent
		
		planSet pSet = new planSet();
	
		ArrayList listAgent = new ArrayList();
		int nbrAgent = 4;
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

			listAgent.clear();
		
			listAgent.add(1);
			listAgent.add(2);
			listAgent.add(3);
			listAgent.add(4);
			
		
		
	
		
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
				agentController = agentContainer.createNewAgent("Agent"+agNbr, "Agents.AgentPaperExample", new Object[]{"Agent"+agNbr, ""+agNbr, ""+totalWatingTime, ""+poucentage, ""+0});
				agentController.start();
				
				agentContainerList.add(agentController);
				
				
				
			//     Process p2  = runtime.exec(new String[]{"cmd.exe","/c","start  java jade.Boot -agents "+agentName+":Agents.Agent1("+agentName+","+agNbr+","+watingTime+","+poucentage+") -container -host localhost"});
			 	
				
			} catch (InterruptedException e) { e.printStackTrace(); }
			
		} // fin de for
		
		f.close();
		System.out.println("-->  : "+listAgent.toString());
		
			
		
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
