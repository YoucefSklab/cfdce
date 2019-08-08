/**
 * 
 */
package com.cfdce.Resolver;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;

import com.cfdce.FormationCoalitions.Coalition;
import com.cfdce.FormationCoalitions.Discussion;

import jade.domain.FIPAException;


/**
 * @author Djo
 *
 */
public class ResolverV10WithoutComment {

	public static ArrayList				agentList			= new ArrayList();
	public static ArrayList 			plansLenth			= new ArrayList();
	public static ArrayList				selectedDiscList	= new ArrayList();
	public static ArrayList				allCoalitionList	= new ArrayList();
	public static MethodesCollection	MethColl			= new MethodesCollection();
	public static ArrayList				allTasksList		= new ArrayList();
	public static ArrayList				tasksList			= new ArrayList();
	public static ArrayList				exclusiveList		= new ArrayList();
	public static ArrayList				selectedCommonTasks = new ArrayList(); // dans un round, la liste des taches ayant plusieurs agents.
	public static ArrayList				exploredTasks 		= new ArrayList(); // les taches explor�es � un instant donn�.
	public static ArrayList				commonTasks 		= new ArrayList(); 
	// public static int limit = ThreadLocalRandom.current().nextInt(100, 105);
	public static int					limit				= 8962624;
	public static int					sleepTime			= 1;						// 50;
	public static int[]					agentTab			= {1, 2, 3,4};
	// public static Logger logger = LoggerFactory.getLogger(ResolverV3.class);
	public static float					sysEffeciency		= 0;
	public static String				agentNames			= "";
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

		agentTab= new int[] {1,2,3,4};
		listeAgent.add(agentTab);
		agentTab= new int[] {1,2,3,4,5};
		listeAgent.add(agentTab);
		agentTab= new int[] {1,2,3,4,5,6,7,8,9,10};
		listeAgent.add(agentTab);
		agentTab= new int[] {1,2,3,4,5,6,7,8,9,10};
		listeAgent.add(agentTab);
		
		
		int indPlans = 0;
		int indSimulation = 0;
		
		System.out.println(" Tapez l'indice des plans: ");
		
		Scanner sc = new Scanner(System.in);
		
		indPlans = sc.nextInt();
		
		System.out.println(" Tapez le num�ro de simulation: ");
		
		Scanner sc1 = new Scanner(System.in);
		
		indSimulation = sc1.nextInt();
		
		
		
		
		PrintWriter fToSave = new PrintWriter(new FileWriter("logs/centralSolutionResults_"+indSimulation+".txt"));
		fToSave.println("Case, Ini Steps, Final Steps, setV, Tot TeT, Tot TeT Adition, Nbr Ag, Tot Alt, Tot Nbr Ag,  Time, sysEffeciency");
		fToSave.flush();
	
		
		System.out.println("Case, Ini Steps, Final Steps, setV, Tot TeT, Tot TeT Adition, Nbr Ag, Tot Alt, Tot Nbr Ag, Time, sysEffeciency");
		


for(int con = indPlans; con<29; con++){
//for(int con = 1; con<listeAgent.size(); con++){

	/*
	 * int ag1 =  ThreadLocalRandom.current().nextInt(1, 894);
	
	int ag2 =  ThreadLocalRandom.current().nextInt(1, 894);
	int ag3 =  ThreadLocalRandom.current().nextInt(1, 894);
	int ag4 =  ThreadLocalRandom.current().nextInt(1, 894);
	
	//int ag5 =  ThreadLocalRandom.current().nextInt(1, 894);
	 * 
	 * */
	 
	int[] agentTab1 = (int[]) listeAgent.get(con);
///	System.out.println("Agents list is: "+agentTab1[0]);
	
	//---------------------------------------------
	agentList.clear();
	plansLenth.clear();
	selectedDiscList.clear();
	allCoalitionList.clear();
	MethColl			= new MethodesCollection();
	allTasksList.clear();
	tasksList.clear();
	exclusiveList.clear();
	selectedCommonTasks.clear();
	exploredTasks.clear();
	sleepTime			= 1;						// 50;
	sysEffeciency		= 0;
	agentNames			= "";
	startingTime		= System.currentTimeMillis();
	tempIndiceMin.clear();
	tempIndiceMax.clear();
	indiceMin.clear();
	indiceMax.clear();
	commonTasks.clear();
	//---------------------------------------------
	
	//agentList = MethColl.loadPlansRandom(agentTab);
	agentList = MethColl.loadPlans(agentTab1);

	for( int i = 0; i < agentList.size(); i++ ) {
		AgentModel agent = (AgentModel) agentList.get(i);
		agentNames += "_" + agent.plan.getId();
	}
		


		allTasksList = MethColl.getTasksList(agentList);
		MethColl.formCoalitionsProposal(false, agentList);
		MethColl.computeExclusiveTasks(false, agentList);
		MethColl.setTasksExclusiveTasks(allTasksList, agentList);


		int nbrAgent = 0;
		

		for( int i = 0; i < allTasksList.size(); i++ ) {
			Task task = (Task) allTasksList.get(i);
			tasksList.add(task.task);
			
			ArrayList itsExclusive = new ArrayList();
			
			// construction de la liste des taches exclusives
			//-----------------------------------------------
			for(int t=0; t<agentList.size(); t++){
				AgentModel agent = (AgentModel) agentList.get(t);
				for(int u=0; u<agent.tasksList.size(); u++){
					String tache = (String) agent.tasksList.get(u);
					if(tache.equals(task.task)){
						ArrayList list = (ArrayList) agent.exclusiveTasks.get(u);
						for(int o=0; o<list.size(); o++) {
							String tache2 = (String) list.get(o);
								if((!itsExclusive.contains(tache2)) && (!tache2.equals(task.task))){
									itsExclusive.add(tache2);
								}
						}
					}
				}
			}
			
			
			
			exclusiveList.add(itsExclusive);
			
	
			task.combPossibilities = new MethodesCollection().formCoalitionStr(task.agentList, true);

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
		
	///	System.out.println(" -*-> The tasks list: "+tasksList.toString());
		
		/*
		System.out.println(" -*-> The tasks list and thier respective exclusive tasks set: ");
		for(int t=0; t<tasksList.size(); t++){
			System.out.println("    	-*-> The task: "+tasksList.get(t));
			System.out.println("    	-*-> Has exclusive: "+exclusiveList.get(t).toString());
		}		
	*/
	///	System.out.println(" 	-> Initial indice Max :  " + tempIndiceMax.toString());
		
		long neededSteps = 1;
		int nbrV=0;
		for( int h = 0; h < tempIndiceMax.size(); h++ ) {
			int ind = (int) tempIndiceMax.get(h);
			neededSteps = neededSteps *  ind;
			if(ind >= 2){
				String ta = (String) tasksList.get(h);
				commonTasks.add(ta);
			//	nbrV++;
			}
				
		}
		
	///	plansLenth = MethColl.computePathsLenth(agentList, commonTasks);
	nbrV = commonTasks.size();
		
	///	System.out.println(" 	-> Initial needed steps: " + neededSteps);
		
		long totalTeT=1;
		int totAlternatives = 0;
		for( int agNbr = 0; agNbr < agentList.size(); agNbr++ ) {
			AgentModel agent = (AgentModel) agentList.get(agNbr);
			int nbPaths = 0;
			for(int y=0; y<agent.allPaths.size(); y++){
				Graph path = (Graph) agent.allPaths.get(y);
				boolean exist= false;
				for(Edge ed:path.getEachEdge()){
					if(commonTasks.contains(ed.getId())){
						exist = true;
						break;
					}
				}
				if(exist)
					nbPaths++;
			}
			
	///		System.out.println("	--> Agent :"+agent.plan.getId()+" Alternatives is: "+nbPaths);
			
			totAlternatives+= nbPaths;
		} 
		
		int taskFr = 0;
		
		for( int agNbr = 0; agNbr < agentList.size(); agNbr++ ) {
			AgentModel agent = (AgentModel) agentList.get(agNbr);
			
			for(int u=0; u<agent.allPaths.size(); u++){
				Graph path = (Graph) agent.allPaths.get(u);
				
				for(int p=0; p<agent.allPaths.size(); p++){
					Graph path2 = (Graph) agent.allPaths.get(p);
					for(Edge ed:path.getEachEdge()){
						for(Edge ed2:path2.getEachEdge()){
							if(ed.getId().equals(ed2.getId())){
								if(commonTasks.contains(ed.getId()))
									taskFr++;
							}
						}
					}
				}
			}
		} 
		
		long totalTeTadition=0;
		for(int t=0; t<tasksList.size(); t++){
			String ta = (String) tasksList.get(t);
			if(commonTasks.contains(ta)){
				int ind = (int) tempIndiceMax.get(t);
			///	System.out.println("|---------> "+(String) tasksList.get(t));
				ArrayList list = (ArrayList) exclusiveList.get(t);
											
				int nbtTasksV = 0;  // Calculer le nombre de tache dans TeT ayant plus de deux agents possible
				for(int tt=0; tt<list.size(); tt++){
					String task = (String) list.get(tt);
					if(commonTasks.contains(task))
						nbtTasksV++;
				}
				if(nbtTasksV > 0)
					totalTeT =  totalTeT * nbtTasksV;
				
				totalTeTadition = totalTeTadition+ nbtTasksV;
			}
		}
		
		//totalTeT = totalTeT
		float totalTeTLong = 0;
		totalTeTLong = (float) ((float) totalTeTadition / ( (float) taskFr )  );

		boolean end = false;
		int step = 1;

		while (!end) {
	
			System.out.println();System.out.println();
			System.out.println(" - Step:" + step + " For case --> "+con);
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
			
			
			selectedCommonTasks.clear();
			
			exploredTasks.clear();
			for( int i = 0; i < allTasksList.size(); i++ ) {
			
				Task task = (Task) allTasksList.get(i);
				exploredTasks.add(task.task);
					if(1==1){
					task.tasksExclusiveAgents.clear();
					
					ArrayList liste = task.getPossibilitiesFromStr((int) tempIndiceMin.get(i));
	
					MethColl.updateTaskExclusiveAgents(task.task, agentList, allTasksList, liste);
		
					if (liste.size() >= 2) { // ex�cution collective
	
						MethColl.setCollectiveExecution(agentList, liste, task.task, allTasksList, selectedCommonTasks, exploredTasks);
	
					}
					else { // Ex�cution individuelle
							// ------------------------------------------------------------
	
						MethColl.setIndividualExecution(agentList, task.task);
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
				MethColl.setBestAlternative(agentList);
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
			
			// Appliquer les changement n�cessaires
			if(indice<(allTasksList.size()-1)){
				indice = indice+1;
					
					for(int h=indice; h<allTasksList.size(); h++){
						Task task = (Task) allTasksList.get(h);
						ArrayList updatedAgentList = new ArrayList();
						for(int y=0; y<indice; y++){
							Task task2 = (Task) allTasksList.get(y);
						
							
							if(task2.combPossibilities.size()>(int) tempIndiceMin.get(y))
							if(task2.exclusiveTasks.contains(task.task)){
								ArrayList liste = task2.getPossibilitiesFromStr((int) tempIndiceMin.get(y));
								for(int p=0; p<liste.size(); p++){
									int ag = (int) liste.get(p);
									if(!updatedAgentList.contains(ag)){
										updatedAgentList.add(ag);
									}
								}
							}
						}
						
						ArrayList newAgentList = new ArrayList();
						for(int q=0; q<task.agentList.size(); q++){
							int agent = (int) task.agentList.get(q);
							if(!updatedAgentList.contains(agent)){
								newAgentList.add(agent);
							}
							
						}
						task.combPossibilities = new MethodesCollection().formUpdateCoalitionStr(newAgentList, true);
						tempIndiceMax.set(h, task.combPossibilities.size());
							
					}
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
			
	
			}

			// arreter la boucle si on a fait toutes les possibilit�s

			if ((int) tempIndiceMin.get(0) == (int) tempIndiceMax.get(0)) {
				end = true;
				
			}

			// Garbage collector
			System.gc();

			MethColl.printAgentOnGraphs(agentList, step);
			Thread.sleep(sleepTime);
			
			step++;			
			if (step >= limit || end) {
				end = true;
				System.setOut(new PrintStream(
						new FileOutputStream(FileDescriptor.out)));
			}
		}

	

		long different = System.currentTimeMillis() - startingTime;
	/*	
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
*/
				
	///		System.out.println("Initial needed steps, Final Steps, setV, Total TeT, Indice du plan, nbr Agent");
	///     System.out.println("Ini Steps, Final Steps, setV, Tot TeT, Tot TeT Adition, Ind plan, Nbr Ag, Tot Alt, Tot Nbr Ag, Avg Alt");		
		System.out.println(con+" , "+ neededSteps+" , " +step+" , "+nbrV+" , "+ totalTeTLong+" , "+ totalTeTadition+" , "+nbrAgent+" , "+totAlternatives+" , "+agentList.size()+" , "+different+" , "+sysEffeciency);
		
		fToSave.println(con+" , "+ neededSteps+" , " +step+" , "+nbrV+" , "+ totalTeTLong+" , "+ totalTeTadition+" , "+nbrAgent+" , "+totAlternatives+" , "+agentList.size()+" , "+different+" , "+sysEffeciency);
	fToSave.flush();
		MethColl.printAgentOnGraphs(agentList, step);

		

		MethColl.printSelectedDisc(agentList);

	}

	
	System.out.println(" -> The End ....!");
	
	fToSave.close();

	}
	
///	totalTeTadition indiceDep nbrAgent totAlternatives different sysEffeciency


}
