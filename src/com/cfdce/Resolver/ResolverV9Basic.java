/**
 * 
 */
package com.cfdce.Resolver;

import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import com.cfdce.FormationCoalitions.Coalition;
import com.cfdce.FormationCoalitions.Discussion;

import jade.domain.FIPAException;


/**
 * @author Djo
 *
 */
public class ResolverV9Basic {

	public static ArrayList				agentList			= new ArrayList();
	public static MethodesCollection	MethColl			= new MethodesCollection();
	public static ArrayList				allTasksList		= new ArrayList();
	public static ArrayList				tasksList			= new ArrayList();
	public static int[]					agentTab			= {1, 2, 3};
	public static float					sysEffeciency		= 0;
	public static long					startingTime		= System.currentTimeMillis();
	
	
	// liste des indice
	public static ArrayList tempIndiceMin = new ArrayList();
	public static ArrayList tempIndiceMax = new ArrayList();

	public static ArrayList indiceMin = new ArrayList();
	public static ArrayList indiceMax = new ArrayList();


	/**
	 * @param args
	 * @throws IOException
	 * @throws FIPAException
	 * @throws InterruptedException
	 */
	public static void main(String[] args)
			throws IOException, FIPAException, InterruptedException {


		ArrayList listeAgent = new ArrayList();

		agentTab= new int[] {1,1,1};
		listeAgent.add(agentTab);
		agentTab= new int[] {2,2,2};
		listeAgent.add(agentTab);
		agentTab= new int[] {3,3,3};
		listeAgent.add(agentTab);
		agentTab= new int[] {4,4,4};
		listeAgent.add(agentTab);
		agentTab= new int[] {5,5,5};
		listeAgent.add(agentTab);
		agentTab= new int[] {6,6,6};
		listeAgent.add(agentTab);
		agentTab= new int[] {7,7,7};
		listeAgent.add(agentTab);
		agentTab= new int[] {8,8,8};
		listeAgent.add(agentTab);
		agentTab= new int[] {9,9,9};
		listeAgent.add(agentTab);
		agentTab= new int[] {10,10,10};
		listeAgent.add(agentTab);
		agentTab= new int[] {1,3,7};
		listeAgent.add(agentTab);
		agentTab= new int[] {1,3,8};
		listeAgent.add(agentTab);
		agentTab= new int[] {1,3,9};
		listeAgent.add(agentTab);
		agentTab= new int[] {1,4,5};
		listeAgent.add(agentTab);
		agentTab= new int[] {1,4,6};
		listeAgent.add(agentTab);
		agentTab= new int[] {1,4,7};
		listeAgent.add(agentTab);
		agentTab= new int[] {1,4,8};
		listeAgent.add(agentTab);
		agentTab= new int[] {1,4,9};
		listeAgent.add(agentTab);
		agentTab= new int[] {1,5,6};
		listeAgent.add(agentTab);
		agentTab= new int[] {1,5,7};
		listeAgent.add(agentTab);
		agentTab= new int[] {1,5,8};
		listeAgent.add(agentTab);
		agentTab= new int[] {1,5,9};
		listeAgent.add(agentTab);
		agentTab= new int[] {1,6,7};
		listeAgent.add(agentTab);
		agentTab= new int[] {1,6,8};
		listeAgent.add(agentTab);
		agentTab= new int[] {1,6,9};
		listeAgent.add(agentTab);
		agentTab= new int[] {1,7,8};
		listeAgent.add(agentTab);
		agentTab= new int[] {1,7,9};
		listeAgent.add(agentTab);
		agentTab= new int[] {1,8,9};
		listeAgent.add(agentTab);
		agentTab= new int[] {2,3,4};
		listeAgent.add(agentTab);
		agentTab= new int[] {2,3,5};
		listeAgent.add(agentTab);
		agentTab= new int[] {2,3,6};
		listeAgent.add(agentTab);
		agentTab= new int[] {2,3,7};
		listeAgent.add(agentTab);
		agentTab= new int[] {2,3,8};
		listeAgent.add(agentTab);
		agentTab= new int[] {2,3,9};
		listeAgent.add(agentTab);
		agentTab= new int[] {2,4,5};
		listeAgent.add(agentTab);
		agentTab= new int[] {2,4,6};
		listeAgent.add(agentTab);
		agentTab= new int[] {2,4,7};
		listeAgent.add(agentTab);
		agentTab= new int[] {2,4,8};
		listeAgent.add(agentTab);
		agentTab= new int[] {2,4,9};
		listeAgent.add(agentTab);
		agentTab= new int[] {2,5,6};
		listeAgent.add(agentTab);
		agentTab= new int[] {2,5,7};
		listeAgent.add(agentTab);
		agentTab= new int[] {2,5,8};
		listeAgent.add(agentTab);
		agentTab= new int[] {2,5,9};
		listeAgent.add(agentTab);
		agentTab= new int[] {2,6,7};
		listeAgent.add(agentTab);
		agentTab= new int[] {2,6,8};
		listeAgent.add(agentTab);
		agentTab= new int[] {2,6,9};
		listeAgent.add(agentTab);
		agentTab= new int[] {2,7,8};
		listeAgent.add(agentTab);
		agentTab= new int[] {2,7,9};
		listeAgent.add(agentTab);
		agentTab= new int[] {2,8,9};
		listeAgent.add(agentTab);
		agentTab= new int[] {3,4,5};
		listeAgent.add(agentTab);
		agentTab= new int[] {3,4,6};
		listeAgent.add(agentTab);
		agentTab= new int[] {3,4,7};
		listeAgent.add(agentTab);
		agentTab= new int[] {3,4,8};
		listeAgent.add(agentTab);
		agentTab= new int[] {3,4,9};
		listeAgent.add(agentTab);
		agentTab= new int[] {3,5,6};
		listeAgent.add(agentTab);
		agentTab= new int[] {3,5,7};
		listeAgent.add(agentTab);
		agentTab= new int[] {3,5,8};
		listeAgent.add(agentTab);
		agentTab= new int[] {3,5,9};
		listeAgent.add(agentTab);
		agentTab= new int[] {3,6,7};
		listeAgent.add(agentTab);
		agentTab= new int[] {3,6,8};
		listeAgent.add(agentTab);
		agentTab= new int[] {3,6,9};
		listeAgent.add(agentTab);
		agentTab= new int[] {3,7,8};
		listeAgent.add(agentTab);
		agentTab= new int[] {3,7,9};
		listeAgent.add(agentTab);
		agentTab= new int[] {3,8,9};
		listeAgent.add(agentTab);
		agentTab= new int[] {4,5,6};
		listeAgent.add(agentTab);
		agentTab= new int[] {4,5,7};
		listeAgent.add(agentTab);
		agentTab= new int[] {4,5,8};
		listeAgent.add(agentTab);
		agentTab= new int[] {4,5,9};
		listeAgent.add(agentTab);
		agentTab= new int[] {4,6,7};
		listeAgent.add(agentTab);
		agentTab= new int[] {4,6,8};
		listeAgent.add(agentTab);
		agentTab= new int[] {4,6,9};
		listeAgent.add(agentTab);
		agentTab= new int[] {4,7,8};
		listeAgent.add(agentTab);
		agentTab= new int[] {4,7,9};
		listeAgent.add(agentTab);
		agentTab= new int[] {4,8,9};
		listeAgent.add(agentTab);
		agentTab= new int[] {5,6,7};
		listeAgent.add(agentTab);
		agentTab= new int[] {5,6,8};
		listeAgent.add(agentTab);
		agentTab= new int[] {5,6,9};
		listeAgent.add(agentTab);
		agentTab= new int[] {5,7,8};
		listeAgent.add(agentTab);
		agentTab= new int[] {5,7,9};
		listeAgent.add(agentTab);
		agentTab= new int[] {5,8,9};
		listeAgent.add(agentTab);
		agentTab= new int[] {6,7,8};
		listeAgent.add(agentTab);
		agentTab= new int[] {6,7,9};
		listeAgent.add(agentTab);
		agentTab= new int[] {6,8,9};
		listeAgent.add(agentTab);
		agentTab= new int[] {7,8,9};
		listeAgent.add(agentTab);
		
	
		System.out.println("Initial Steps, Final Steps, nbr Agent, Time");
for(int con = 1; con<2; con++){	
//for(int con = 1; con<listeAgent.size(); con++){
//for(int con = 9; con<10; con++){
			int ag1 =  ThreadLocalRandom.current().nextInt(1, 894);
			int ag2 =  ThreadLocalRandom.current().nextInt(1, 894);
			int ag3 =  ThreadLocalRandom.current().nextInt(1, 894);
			int ag4 =  ThreadLocalRandom.current().nextInt(1, 894);
			//int ag5 =  ThreadLocalRandom.current().nextInt(1, 894);
			int[] agentTab1 = (int[]) listeAgent.get(con);
//			int[] agentTab1 = new int[] {ag1,ag2,ag3};
//			int[] agentTab1 = new int[] {con,con,con};
//			int[] agentTab1 = new int[] {1,2,3};
			//---------------------------------------------
			agentList.clear();
			MethColl			= new MethodesCollection();
			allTasksList.clear();
			tasksList.clear();
			sysEffeciency		= 0;
			startingTime		= System.currentTimeMillis();
			tempIndiceMin.clear();
			tempIndiceMax.clear();
			indiceMin.clear();
			indiceMax.clear();
			//---------------------------------------------

		// Charger les plans des agents
		//agentList = MethColl.loadPlansRandom(agentTab);
		agentList = MethColl.loadPlans(agentTab1);


		allTasksList = MethColl.getTasksList(agentList);
		MethColl.formCoalitionsProposal(false, agentList);


		int nbrAgent = 0;
		
		for( int i = 0; i < allTasksList.size(); i++ ) {
			Task task = (Task) allTasksList.get(i);
			tasksList.add(task.task);
			
	
	
			task.combPossibilities = MethodesCollection.formCoalitionStr(task.agentList, true);

			if(nbrAgent <task.agentList.size())
				nbrAgent = task.agentList.size();
			
			indiceMin.add(0);
			indiceMax.add(task.combPossibilities.size());

			for( int l = 0; l < task.combPossibilities.size(); l++ ) {
				ArrayList liste = task.getPossibilitiesFromStr(l);
			}
		}
		
		tempIndiceMax = (ArrayList) indiceMax.clone();
		tempIndiceMin = (ArrayList) indiceMin.clone();

		
			
		long neededSteps = 1;
		int nbrV=0;
		for( int h = 0; h < tempIndiceMax.size(); h++ ) {
			int ind = (int) tempIndiceMax.get(h);
			neededSteps = neededSteps *  ind;
			if(ind >= 2)
				nbrV++;
		}
		
		

		boolean end = false;
		int step = 1;

		while (!end) {
			System.out.println(" - Step:" + step);
			System.out.println(" 	-> Tache:  " + tasksList.toString());
			System.out.println(" 	-> Indice Max:  " + tempIndiceMax.toString()+ "				- Step:" + step);
			System.out.println(" 	-> Indice Min:  " + tempIndiceMin.toString()+ "				- Step:" + step);
			// vider les coalitions
			//---------------------
			for( int h = 0; h < agentList.size(); h++ ) {
				AgentModel agent = (AgentModel) agentList.get(h);
				agent.agentsExclusiveTasks.clear();
				for( int k = 0; k < agent.discussionList.size(); k++ ) {
					Discussion disc = (Discussion) agent.discussionList.get(k);
					for( int m = 0; m < disc.coalitionList.size(); m++ ) {
						Coalition coal = (Coalition) disc.coalitionList.get(m);
						coal.agentForCoalitionList.clear();
					}
				}
			}
			
			
		
			
			// Projeter sur les coalitions des agents Version 2

			// for(int i=allTasksList.size()-1; i>0; i--){//
			// //System.out.println(" - 1");
			for( int i = 0; i < allTasksList.size(); i++ ) {
			
				Task task = (Task) allTasksList.get(i);
			
				
				if(1==1){
					task.tasksExclusiveAgents.clear();
					ArrayList liste = task.getPossibilitiesFromStr((int) tempIndiceMin.get(i));
					if (liste.size() >= 2) { // ex�cution collective
						// MethColl.setCollectiveExecution(agentList, liste, task.task);
						
					}
					else { // Ex�cution individuelle
						// MethColl.setIndividualExecution(agentList, task.task);
					}
				}else{
					//System.out.println("        this task is not processed: "+task.task);
				}	
					
			}

			for( int h = 0; h < agentList.size(); h++ ) {// //System.out.println("
															// - 2");
				AgentModel agent = (AgentModel) agentList.get(h);
				for( int k = 0; k < agent.discussionList.size(); k++ ) {
					Discussion disc = (Discussion) agent.discussionList.get(k);
					for( int m = 0; m < disc.coalitionList.size(); m++ ) {
						Coalition coal = (Coalition) disc.coalitionList.get(m);
						if (coal.agentForCoalitionList.size() <= 0) {
							coal.agentForCoalitionList.add(
									Integer.parseInt(coal.agentOwner.substring(
											5, coal.agentOwner.length())));
						}
					}
				}
			}

			
			// Evaluation des couts
			// --------------------
			for( int i = 0; i < agentList.size(); i++ ) {
				AgentModel agent = (AgentModel) agentList.get(i);

				for( int j = 0; j < agent.discussionList.size(); j++ ) {
					Discussion disc = (Discussion) agent.discussionList.get(j);
					disc.getFinalCost(agent.plan);
					
				}
			}

			MethColl.computeAgentGainedCost(agentList);

			float newEffeciency = MethColl.computeSystemEffeciency(agentList);

			
			if (newEffeciency > sysEffeciency) {
				sysEffeciency = newEffeciency;
			//	MethColl.setBestAlternative(agentList);
			}
			
		

			// R�initilisation des param�tres du parcour.
			// -------------------------------------------

			int nbrTask = allTasksList.size() - 1;
			tempIndiceMin.set(nbrTask, (int) tempIndiceMin.get(nbrTask) + 1);

			int cmp = allTasksList.size() - 1;
			int indice = cmp+1;
			while (cmp > 0) {
				
				if ((int)tempIndiceMin.get(cmp) >= (int) tempIndiceMax.get(cmp)) {
					Task t = (Task) (Task) allTasksList.get(cmp-1);
					tempIndiceMin.set(cmp, 0);
					tempIndiceMin.set(cmp - 1, (int) tempIndiceMin.get(cmp - 1) + 1);	
								
					if(indice>= (cmp-1)){
						indice = cmp-1;
					}
				}
				cmp--;
			}
			

			if ((int) tempIndiceMin.get(0) == (int) tempIndiceMax.get(0)) {
				end = true;
			}

			// Garbage collector
			System.gc();

			MethColl.printAgentOnGraphs(agentList, step);
			
			step++;			
			if (end) {
				end = true;
				System.setOut(new PrintStream(
						new FileOutputStream(FileDescriptor.out)));
			}
		}

		

		long different = System.currentTimeMillis() - startingTime;
		long secondsInMilli = 1000;
		long minutesInMilli = secondsInMilli * 60;
		long hoursInMilli = minutesInMilli * 60;
		long daysInMilli = hoursInMilli * 24;

		long elapsedDays = different / daysInMilli;
		different = different % daysInMilli;

		long elapsedHours = different / hoursInMilli;
		different = different % hoursInMilli;

		long elapsedMinutes = different / minutesInMilli;
		different = different % minutesInMilli;

		long elapsedSeconds = different / secondsInMilli;
/*
		System.out.printf(
				" --> Elapsed Time: "
						+ "%d days, %d hours, %d minutes, %d seconds%n",
				elapsedDays, elapsedHours, elapsedMinutes, elapsedSeconds);

		System.out.println(
				" -------------------------------------------------------------------- ");

		System.out.println(" --> Starting time: " + startingTime);
		System.out.println(" --> Ending Time: " + System.currentTimeMillis());
		System.out.println(" Elapsed Time: "
				+ (System.currentTimeMillis() - startingTime));
		System.out.println(" 	-> nbr Agent : " + nbrAgent);
		System.out.println("    -> Initial Max Vector: " + indiceMax.toString());
		System.out.println(" --> Final System Effeciency: " + sysEffeciency);
		System.out.println(" --> Les coalitions retenues: ");
		
		System.out.println("Initial Steps, Final Steps, nbr Agent, Time");
	*/	
		System.out.println(neededSteps+" , "+(step-1)+" , "+nbrAgent+" , "+different);
	
		}



		System.out.println("Fin .....!!");

	//	MethColl.printAgentOnGraphs(agentList, step);

		

	//	MethColl.printSelectedDisc(agentList);


	}

}
