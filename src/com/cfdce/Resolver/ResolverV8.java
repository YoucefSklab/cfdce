/**
 * 
 */
package com.cfdce.Resolver;

import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

import com.cfdce.FormationCoalitions.Coalition;
import com.cfdce.FormationCoalitions.Discussion;

import jade.domain.FIPAException;


/**
 * @author Djo
 *
 */
public class ResolverV8 {

	public static ArrayList				agentList			= new ArrayList();
	public static ArrayList				selectedDiscList	= new ArrayList();
	public static ArrayList				allCoalitionList	= new ArrayList();
	public static MethodesCollection	MethColl			= new MethodesCollection();
	public static ArrayList				allTasksList		= new ArrayList();
	public static ArrayList				tasksList			= new ArrayList();
	public static ArrayList				exclusiveList		= new ArrayList();
	public static ArrayList				selectedCommonTasks = new ArrayList(); // dans un round, la liste des taches ayant plusieurs agents.
	public static ArrayList				exploredTasks = new ArrayList(); // les taches explor�es � un instant donn�.
	// public static int limit = ThreadLocalRandom.current().nextInt(100, 105);
	public static int					limit				= 8962624;
	public static int					sleepTime			= 1;						// 50;
	public static int[]					agentTab			= {1, 2, 3, 4};
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
		// TODO Auto-generated method stub

		// Afficher sur fichier Log
		//PrintStream out = new PrintStream(new FileOutputStream("logs/" + System.currentTimeMillis()+ "_Resolver_Log" + agentNames + ".txt"));
		//System.setOut(out);

		// Logger logger = LoggerFactory.getLogger(Resolver.class);

		// logger.info("Hello World");

		// logger.info("Welcome ..... This is a central resolver for the
		// OCFEA");

		// Charger les plans des agents
		agentList = MethColl.loadPlans(agentTab);

		// System.out.println(" ");
		// System.out.println("----------------------");
		for( int i = 0; i < agentList.size(); i++ ) {
			AgentModel agent = (AgentModel) agentList.get(i);
			agentNames += "_" + agent.plan.getId();
			// System.out.println(" Agent ID is: "+agent.plan.getId()+ " Its
			// Alternatives Number is: "+agent.allPaths.size());
		}
		// System.out.println("--------------- ");

		allTasksList = MethColl.getTasksList(agentList);
		MethColl.formCoalitionsProposal(false, agentList);
		MethColl.computeExclusiveTasks(false, agentList);
		MethColl.setTasksExclusiveTasks(allTasksList, agentList);


		// System.out.println();//SysttempIndiceMinintln();//System.out.println();

		// System.out.println(" Traitement par tache:");

		

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
			
			
			// System.out.println(" -> The task: "+task.task);
			// System.out.println(" -> The agents having the task:
			// "+task.agentList.toString());

			task.combPossibilities = new MethodesCollection().formCoalitionStr(task.agentList, true);

			//task.tempCombPossibilities = (ArrayList) task.combPossibilities.clone();
			// System.out.println(" -> Liste des possibilit�s (taille):
			// "+task.combPossibilities.size());
			indiceMin.add(0);
			indiceMax.add(task.combPossibilities.size());

			for( int l = 0; l < task.combPossibilities.size(); l++ ) {
				ArrayList liste = task.getPossibilitiesFromStr(l);
				// System.out.println(" > Liste N "+l+" : "+liste.toString());
			}
			// System.out.println();//System.out.println();
		}
		
		tempIndiceMax = (ArrayList) indiceMax.clone();
		tempIndiceMin = (ArrayList) indiceMin.clone();

		System.out.println(" -*-> The tasks list and thier respective exclusive tasks set: ");
		for(int t=0; t<tasksList.size(); t++){
			System.out.println("    	-*-> The task: "+tasksList.get(t));
			System.out.println("    	-*-> Has exclusive: "+exclusiveList.get(t).toString());
		}
		
		// System.out.println();//System.out.println();//System.out.println();

		// System.out.println(" Parcour de tous les cas possibles: ");

		boolean end = false;
		int step = 1;

		while (!end) {
			
		///	System.out.println("tempIndiceMin.get(0) "+tempIndiceMin.get(0));
		///	System.out.println("tempIndiceMax.get(0) "+tempIndiceMax.get(0));

			System.out.println();//System.out.println();System.out.println();System.out.println();
			System.out.println(" - Step:" + step);
			//System.out.println();System.out.println();System.out.println();System.out.println();
			//System.out.println(" 	-");
			System.out.println(" 	-> Tache:  " + tasksList.toString());
			System.out.println(" 	-> Indice Max:  " + tempIndiceMax.toString()+ "				- Step:" + step);
			System.out.println(" 	-> Indice Min:  " + tempIndiceMin.toString()+ "				- Step:" + step);
			
		///	System.out.print("$V^{max}_{"+step+"}$");
		///	for(int y=0; y<tempIndiceMax.size(); y++){
		///		System.out.print(" & "+(int) tempIndiceMax.get(y));
		///	}
		///	System.out.println(" \\\\ \\hline");
			
		///	System.out.print("$V^{min}_{"+step+"}$");
		///	for(int y=0; y<tempIndiceMin.size(); y++){
		///		int value = (int) tempIndiceMin.get(y);
		///		value++;
		///		System.out.print(" & "+value);
		///	}
		///	System.out.println(" \\\\  \\hline \\hline");
			
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
			
			// Projeter sur les coalitions des agents Version 2

			// for(int i=allTasksList.size()-1; i>0; i--){//
			// //System.out.println(" - 1");
			exploredTasks.clear();
			for( int i = 0; i < allTasksList.size(); i++ ) {
			
				Task task = (Task) allTasksList.get(i);
				exploredTasks.add(task.task);
				// to check this bloc
				/*
				
				boolean exist = false;
				for(int m=0; m<selectedCommonTasks.size(); m++){
					String t1 = (String) selectedCommonTasks.get(m);
					int posi  = tasksList.indexOf(t1);
					ArrayList lis = (ArrayList) exclusiveList.get(posi);
					if(lis.contains(task.task))
						exist = true;
				}
				*/
				// end of the bloc to check.
				if(1==1){
			//	if(!exist){
					task.tasksExclusiveAgents.clear();
					
					 System.out.println((int) tempIndiceMin.get(i));
					ArrayList liste = task.getPossibilitiesFromStr((int) tempIndiceMin.get(i));
					// System.out.println(" --> The task: "+task.task+" with
					// selected list: "+liste.toString());
					/*
					System.out.println("  		--> The task: " + task.task
							+ "  with selected list(" + (int) tempIndiceMin.get(i)
							+ "): " + liste.toString()
							+ "  --> The task possibilites:"
							+ task.combPossibilities.toString());*/
	
					MethColl.updateTaskExclusiveAgents(task.task, agentList, allTasksList, liste);
					
					// mise � jour des coalitions des agents, par ordre d'apparution
					// dans la liste
					if (liste.size() >= 2) { // ex�cution collective
	
						MethColl.setCollectiveExecution(agentList, liste, task.task, allTasksList, selectedCommonTasks, exploredTasks);
	
					}
					else { // Ex�cution individuelle
							// ------------------------------------------------------------
	
						MethColl.setIndividualExecution(agentList, task.task);
	
						// Fin Ex�cution individuelle
						// ------------------------------------------------------------
					}
				}else{
					//System.out.println("        this task is not processed: "+task.task);
				}	
					
			}

			for( int h = 0; h < agentList.size(); h++ ) {// //System.out.println("
															// - 2");
				AgentModel agent = (AgentModel) agentList.get(h);
				for( int k = 0; k < agent.discussionList.size(); k++ ) {// //System.out.println("
																		// -
																		// 3");
					// System.out.println(" -----> Agent: "+agent.plan.getId());
					Discussion disc = (Discussion) agent.discussionList.get(k);
					for( int m = 0; m < disc.coalitionList.size(); m++ ) {// //System.out.println("
																			// -
																			// 4");
						Coalition coal = (Coalition) disc.coalitionList.get(m);
						if (coal.agentForCoalitionList.size() <= 0) {
							coal.agentForCoalitionList.add(
									Integer.parseInt(coal.agentOwner.substring(
											5, coal.agentOwner.length())));
						}
					}
				}
			}

			// System.out.println("-");//System.out.println("-");//System.out.println("-");
			// System.out.println("-");//System.out.println("-");//System.out.println("-");
			// System.out.println("-");//System.out.println("-");//System.out.println("-");

			// System.out.println(" Evaluation des couts (Step): "+step);

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

			//System.out.println("		--> Old Effeciency: " + sysEffeciency
			//		+ "  New Effeciency: " + newEffeciency);

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

				//	System.out.println(); System.out.println();
					if(t.combPossibilities.size()> ((int) tempIndiceMin.get(cmp-1))){
				//		System.out.println("			-> Ici changement d'alternative de la tache N: "+(cmp-1)+" qui est: "+t.task +" pour une nouvelle alternative: "+((String) t.combPossibilities.get((int) tempIndiceMin.get(cmp-1)) ) );
					}else{
				//		System.out.println("			-> Ici changement d'alternative de la tache N: "+(cmp-1)+" qui est: "+t.task +" pour une nouvelle alternative: "+((String) t.combPossibilities.get(0)));
					}
					
					if(indice>= (cmp-1)){
						indice = cmp-1;
					}
					
						
					
				}
				cmp--;
			}
			
		
			// Appliquer les changement n�cessaires
			if(indice<(allTasksList.size()-1)){
				indice = indice+1;
		//		System.out.println("			 -> Les changemeent seront alors � partir de : "+(indice));
		//		System.out.println("				-> Donc il faut revoir la liste des alternatives de "+(indice)+" � "+(allTasksList.size()-1)+".");
		//		System.out.println("				-> Les t�ches concernc�es sont: ");
					
					for(int h=indice; h<allTasksList.size(); h++){
						Task task = (Task) allTasksList.get(h);
		//				System.out.println("					-> La tache: "+task.task +". Ses taches exclusives sont: ");
						ArrayList updatedAgentList = new ArrayList();
						for(int y=0; y<indice; y++){
							Task task2 = (Task) allTasksList.get(y);
						
							
							if(task2.combPossibilities.size()>(int) tempIndiceMin.get(y))
							if(task2.exclusiveTasks.contains(task.task)){
					//			System.out.println("						-> "+task2.task+ " Sa position est "+y+  " l'alternative s�lectionn�e est la :"+(int) tempIndiceMin.get(y)+" qui est: "+task2.combPossibilities.get((int) tempIndiceMin.get(y))+" avec Total de ses alternatives: "+task2.combPossibilities.size());
								ArrayList liste = task2.getPossibilitiesFromStr((int) tempIndiceMin.get(y));
								for(int p=0; p<liste.size(); p++){
									int ag = (int) liste.get(p);
									if(!updatedAgentList.contains(ag)){
										updatedAgentList.add(ag);
									}
								}
							}
						}
				//		System.out.println("					-> "+task.task +" : Exclure les agents: "+updatedAgentList.toString());
				//		System.out.println("						-> : Ses anciennes possibilit�s: "+task.combPossibilities.toString());
						
						ArrayList newAgentList = new ArrayList();
						for(int q=0; q<task.agentList.size(); q++){
							int agent = (int) task.agentList.get(q);
							if(!updatedAgentList.contains(agent)){
								newAgentList.add(agent);
							}
							
						}
						task.combPossibilities = new MethodesCollection().formCoalitionStr(task.agentList, true);
						tempIndiceMax.set(h, task.combPossibilities.size());
					
						//System.out.println("						-> : Ses nouvelles possibiolit�s: "+task.combPossibilities.toString());
							
					}
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
			
	
			}

	///		System.out.println("tempIndiceMin.get(0) "+tempIndiceMin.get(0));
	///		System.out.println("tempIndiceMax.get(0) "+tempIndiceMax.get(0));
			// arreter la boucle si on a fait toutes les possibilit�s
/*
			if ((int) tempIndiceMin.get(0) == (int) tempIndiceMax.get(0)) {
				end = true;
				
	///			System.out.println("tempIndiceMin.get(0) "+tempIndiceMin.get(0));
	///			System.out.println("tempIndiceMax.get(0) "+tempIndiceMax.get(0));
			}
*/
			
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

		/*
		 * //System.out.println("----> Les solution s�lectionn�es ::::");
		 * 
		 * for(int i=0; i<agentList.size(); i++){ AgentModel agent =
		 * (AgentModel) agentList.get(i); Discussion discussion =null; int t=0;
		 * for(int j=0; j<agent.discussionList.size(); j++){ Discussion disc1 =
		 * (Discussion) agent.discussionList.get(j);
		 * System.out.println("Discussion: "+disc1.discussionId+
		 * " total agents is: "+disc1.getTotalAgent());
		 * if(disc1.getTotalAgent()>=t){ discussion = disc1; t =
		 * disc1.getTotalAgent(); } }
		 * 
		 * selectedDiscList.add(discussion); }
		 * 
		 * 
		 * for(int i=0; i<agentList.size(); i++){ AgentModel agent =
		 * (AgentModel) agentList.get(i);
		 * System.out.println("Discussion selection�e pour l'agent "+agent.plan.
		 * getId()+" : (with ref cost): "+agent.refCost);
		 * 
		 * Discussion disc = (Discussion) selectedDiscList.get(i);
		 * System.out.println("  Discussion Id "+disc.discussionId);
		 * System.out.println("  Discussion Final Cost "+disc.
		 * discussionFinalCost);
		 * System.out.println("  Discussion individual Cost "+disc.
		 * discussionIndividualCost);
		 * 
		 * for(int j=0; j<disc.coalitionList.size(); j++){ Coalition c =
		 * (Coalition) disc.coalitionList.get(j);
		 * System.out.println("  	--> Coalition Id "+c.coalitionId);
		 * System.out.println("  	--> Coalition task "+c.edgeList.toString());
		 * System.out.println("  	--> Coalition Agents "+c.
		 * agentForCoalitionList.toString()); } }
		 * 
		 */

		/*		 
 		 PrintStream out = new PrintStream(new
		 FileOutputStream("logs/"+System.currentTimeMillis()+"_Resolver_Log"+agentNames+".txt"));
		 System.setOut(out);
		 
		 */

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
		System.out.println(" --> Final Steps: " + step);
		System.out.println(" --> Final System Effeciency: " + sysEffeciency);
		System.out.println(" --> Les coalitions retenues: ");

		for( int i = 0; i < agentList.size(); i++ ) {
			AgentModel agent = (AgentModel) agentList.get(i);
			System.out.println("  -------------------------------------> ");
			System.out.println("  -> Agent: " + agent.plan.getId());
			System.out.println(
					"  		-> Agent reference Cost: " + agent.refCost);
			System.out.println("  		-> Agent selected  final Cost: "
					+ agent.selectedFinalCost);
			System.out.println(
					"  		-> Agent Gained Cost: " + agent.gainedCost);
			for( int j = 0; j < agent.bestCoalitions.size(); j++ ) {
				ArrayList tache = (ArrayList) agent.bestCoalitions.get(j);
				System.out
						.println("			-> La tache: " + tache.toString());
				System.out.println("			-> Les agents: "
						+ agent.bestCoalitionsAgents.get(j).toString());
				System.out.println("			-> Le Cout: "
						+ agent.bestCoalitionsCosts.get(j).toString());
			}
		}

		System.out.println("Fin .....!!");

		MethColl.printAgentOnGraphs(agentList, step);

		/*
		 * // Fomrer liste des taches exclusives for(int i=0;
		 * i<agentList.size(); i++){ AgentModel agent = (AgentModel)
		 * agentList.get(i);
		 * 
		 * //System.out.println("  	--> Agent Id "+agent.plan.getId());
		 * 
		 * for(int j=0; j<agent.tasksList.size(); j++){ String task = (String)
		 * agent.tasksList.get(j); ArrayList listeExclusive = (ArrayList)
		 * agent.exclusiveTasks.get(j);
		 * 
		 * //System.out.println("  		--> Task Id: "+task+
		 * " has exclusive: "+listeExclusive.toString());
		 * 
		 * }
		 * 
		 * }
		 */

		MethColl.printSelectedDisc(agentList);

		// System.out.println("Fin ===========================!!"+limit);
	}

}
