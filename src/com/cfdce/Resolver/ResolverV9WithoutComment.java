/**
 * 
 */
package com.cfdce.Resolver;

import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Random;

import com.cfdce.FormationCoalitions.Coalition;
import com.cfdce.FormationCoalitions.Discussion;

import jade.domain.FIPAException;


/**
 * @author Djo
 *
 */
public class ResolverV9WithoutComment {

	public static Random rand = new Random();

	public static ArrayList<AgentModel> agentList = new ArrayList<AgentModel>();
	public static MethodesCollection MethColl = new MethodesCollection();
	public static ArrayList<Task> allTasksList = new ArrayList<Task>();
	public static ArrayList<String> tasksList = new ArrayList<String>();
	public static ArrayList<ArrayList> exclusiveList = new ArrayList<ArrayList>();
	public static ArrayList<Task> selectedCommonTasks = new ArrayList<Task>(); // dans un round, la liste des taches
																				// ayant plusieurs agents.
	public static ArrayList<String> exploredTasks = new ArrayList<String>(); // les taches explor�es � un instant donn�.

	public static int limit = 8962624;
	public static int sleepTime = 1; 
	public static int[] agentTab = { 1, 2, 3, 4, 5, 6 };

	public static float sysEffeciency = 0;
	public static String agentNames = "";
	public static long startingTime = System.currentTimeMillis();

	// liste des indice
	public static ArrayList<Integer> tempIndiceMin = new ArrayList<Integer>();
	public static ArrayList<Integer> tempIndiceMax = new ArrayList<Integer>();

	public static ArrayList<Integer> indiceMin = new ArrayList<Integer>();
	public static ArrayList<Integer> indiceMax = new ArrayList<Integer>();

	public static long globalElapsedTime = 0;
	public static long currentTime = 0;
	public static long lastTime = 0;

	public static void main(String[] args) throws IOException, FIPAException, InterruptedException {

		agentList = MethColl.loadPlans(agentTab);

		for (int i = 0; i < agentList.size(); i++) {
			AgentModel agent = agentList.get(i);
			agentNames += "_" + agent.plan.getId();
		}
		
		allTasksList = MethColl.getTasksList(agentList);
		
		setTimeCounter();		
		MethColl.formCoalitionsProposal(false, agentList);
		MethColl.computeExclusiveTasks(false, agentList);
		MethColl.setTasksExclusiveTasks(allTasksList, agentList);
		updateElapsedTime();

	
		
		int nbrAgent = 0;

		for (int i = 0; i < allTasksList.size(); i++) {
			Task task = allTasksList.get(i);
			tasksList.add(task.task);

			ArrayList<String> itsExclusive = new ArrayList<String>();

			for (int t = 0; t < agentList.size(); t++) {
				AgentModel agent = agentList.get(t);
				for (int u = 0; u < agent.tasksList.size(); u++) {
					String tache = (String) agent.tasksList.get(u);
					if (tache.equals(task.task)) {
						ArrayList list = (ArrayList) agent.exclusiveTasks.get(u);
						for (int o = 0; o < list.size(); o++) {
							String tache2 = (String) list.get(o);
							if ((!itsExclusive.contains(tache2)) && (!tache2.equals(task.task))) {
								itsExclusive.add(tache2);
							}
						}
					}
				}
			}

			exclusiveList.add(itsExclusive);

			task.combPossibilities = new MethodesCollection().formCoalitionStr(task.agentList, true);

			if (nbrAgent < task.agentList.size())
				nbrAgent = task.agentList.size();

			indiceMin.add(0);
			indiceMax.add(task.combPossibilities.size());

			for (int l = 0; l < task.combPossibilities.size(); l++) {
				ArrayList liste = task.getPossibilitiesFromStr(l);
			}
		}

		tempIndiceMax = (ArrayList<Integer>) indiceMax.clone();
		tempIndiceMin = (ArrayList<Integer>) indiceMin.clone();

		System.out.println(" -*-> The tasks list and thier respective exclusive tasks set: ");
		for (int t = 0; t < tasksList.size(); t++) {
			System.out.println("    	-*-> The task: " + tasksList.get(t));
			System.out.println("    	-*-> Has exclusive: " + exclusiveList.get(t).toString());
		}

		System.out.println(" 	-> Initial indice Max :  " + tempIndiceMax.toString());

		int neededSteps = 1;
		int nbrV = 0;
		for (int h = 0; h < tempIndiceMax.size(); h++) {
			int ind = tempIndiceMax.get(h);
			neededSteps = neededSteps * ind;
			if (ind >= 2)
				nbrV++;
		}
		System.out.println(" 	-> Initial needed steps: " + neededSteps + " and total needed time is "
				+ (neededSteps * rand.nextLong()));

		long totalTeT = 1;
		double indicePlan = 0;
		double indiceDep = 0;

		currentTime = System.currentTimeMillis();
		lastTime = currentTime;

		for (int t = 0; t < tasksList.size(); t++) {
			int ind = tempIndiceMax.get(t);
			if (ind >= 2) {
				System.out.println("|---------> " + tasksList.get(t));
				ArrayList list = exclusiveList.get(t);
				int nbtTasksV = 0; // Calculer le nombre de tache dans TeT ayant plus de deux agents possible
				for (int tt = 0; tt < list.size(); tt++) {
					String task = (String) list.get(tt);
					int index = tasksList.indexOf(task);
					int indexV = tempIndiceMax.get(index);
					if (indexV >= 2)
						nbtTasksV++;
				}
				if (nbtTasksV > 0)
					totalTeT = totalTeT * nbtTasksV;
				indicePlan += (float) (list.size() / Math.pow(nbrV, nbrV));
			}

		}

		indiceDep = totalTeT / Math.pow(nbrV, nbrV);

		boolean end = false;
		int step = 1;
		currentTime = System.currentTimeMillis();
		globalElapsedTime += currentTime - lastTime;
		lastTime = currentTime;
		while (!end) {

			System.out.println();// System.out.println();System.out.println();System.out.println();
			System.out.println(" - Step:" + step);
			System.out.println(" 	-> Tache:  " + tasksList.toString());
			System.out.println(" 	-> Indice Max:  " + tempIndiceMax.toString() + "				- Step:" + step);
			System.out.println(" 	-> Indice Min:  " + tempIndiceMin.toString() + "				- Step:" + step);

			// vider les coalitions
			// ---------------------
			for (int h = 0; h < agentList.size(); h++) {
				AgentModel agent = agentList.get(h);
				agent.agentsExclusiveTasks.clear();
				for (int k = 0; k < agent.discussionList.size(); k++) {
					Discussion disc = (Discussion) agent.discussionList.get(k);
					for (int m = 0; m < disc.coalitionList.size(); m++) {
						Coalition coal = (Coalition) disc.coalitionList.get(m);
						coal.agentForCoalitionList.clear();
					}
				}
			}

			selectedCommonTasks.clear();

			exploredTasks.clear();
			for (int i = 0; i < allTasksList.size(); i++) {

				Task task = allTasksList.get(i);
				exploredTasks.add(task.task);

				if (1 == 1) {
					// if(!exist){
					task.tasksExclusiveAgents.clear();

					ArrayList liste = task.getPossibilitiesFromStr(tempIndiceMin.get(i));

					MethColl.updateTaskExclusiveAgents(task.task, agentList, allTasksList, liste);

					if (liste.size() >= 2) { // ex�cution collective
						MethColl.setCollectiveExecution(agentList, liste, task.task, allTasksList, selectedCommonTasks,
								exploredTasks);
					} else { // Ex�cution individuelle
						MethColl.setIndividualExecution(agentList, task.task);
					}
				}
			}

			for (int h = 0; h < agentList.size(); h++) {
				AgentModel agent = agentList.get(h);
				for (int k = 0; k < agent.discussionList.size(); k++) {
					Discussion disc = (Discussion) agent.discussionList.get(k);
					for (int m = 0; m < disc.coalitionList.size(); m++) {
						Coalition coal = (Coalition) disc.coalitionList.get(m);
						if (coal.agentForCoalitionList.size() <= 0) {
							coal.agentForCoalitionList.add(Integer.parseInt(coal.agentOwner.substring(5, coal.agentOwner.length())));
						}
					}
				}
			}

			// Evaluation des couts
			for (int i = 0; i < agentList.size(); i++) {
				AgentModel agent = agentList.get(i);

				for (int j = 0; j < agent.discussionList.size(); j++) {
					Discussion disc = (Discussion) agent.discussionList.get(j);
					disc.getFinalCost(agent.plan);
					globalElapsedTime += rand.nextInt();
				}
			}

			MethColl.computeAgentGainedCost(agentList);

			float newEffeciency = MethColl.computeSystemEffeciency(agentList);

			if (newEffeciency > sysEffeciency) {
				sysEffeciency = newEffeciency;
				MethColl.setBestAlternative(agentList);
			}

			// R�initilisation des param�tres du parcour.
			int nbrTask = allTasksList.size() - 1;
			tempIndiceMin.set(nbrTask, tempIndiceMin.get(nbrTask) + 1);

			int cmp = allTasksList.size() - 1;
			int indice = cmp + 1;
			while (cmp > 0) {

				if (tempIndiceMin.get(cmp) >= tempIndiceMax.get(cmp)) {
					Task t = allTasksList.get(cmp - 1);
					tempIndiceMin.set(cmp, 0);
					tempIndiceMin.set(cmp - 1, tempIndiceMin.get(cmp - 1) + 1);
	
					if (t.combPossibilities.size() > (tempIndiceMin.get(cmp - 1))) {
						// System.out.println(" -> Ici changement d'alternative de la tache N:
						// "+(cmp-1)+" qui est: "+t.task +" pour une nouvelle alternative: "+((String)
						// t.combPossibilities.get((int) tempIndiceMin.get(cmp-1)) ) );
					} else {
						// System.out.println(" -> Ici changement d'alternative de la tache N:
						// "+(cmp-1)+" qui est: "+t.task +" pour une nouvelle alternative: "+((String)
						// t.combPossibilities.get(0)));
					}

					if (indice >= (cmp - 1)) {
						indice = cmp - 1;
					}

				}
				cmp--;
			}

			// Appliquer les changement n�cessaires
			if (indice < (allTasksList.size() - 1)) {
				indice = indice + 1;

				for (int h = indice; h < allTasksList.size(); h++) {
					Task task = allTasksList.get(h);
					// System.out.println(" -> La tache: "+task.task +". Ses taches exclusives sont:
					// ");
					ArrayList<Integer> updatedAgentList = new ArrayList<Integer>();
					for (int y = 0; y < indice; y++) {
						Task task2 = allTasksList.get(y);

						if (task2.combPossibilities.size() > tempIndiceMin.get(y))
							if (task2.exclusiveTasks.contains(task.task)) {
								ArrayList liste = task2.getPossibilitiesFromStr(tempIndiceMin.get(y));
								for (int p = 0; p < liste.size(); p++) {
									int ag = (int) liste.get(p);
									if (!updatedAgentList.contains(ag)) {
										updatedAgentList.add(ag);
									}
								}
							}
					}
					
					ArrayList<Integer> newAgentList = new ArrayList<Integer>();
					for (int q = 0; q < task.agentList.size(); q++) {
						int agent = (int) task.agentList.get(q);
						if (!updatedAgentList.contains(agent)) {
							newAgentList.add(agent);
						}

					}
					
					task.combPossibilities = new MethodesCollection().formCoalitionStr(task.agentList, true);
					
					tempIndiceMax.set(h, task.combPossibilities.size());

				}

			}

			if (tempIndiceMin.get(0) == tempIndiceMax.get(0)) {
				end = true;

			}

			System.gc();

			MethColl.printAgentOnGraphs(agentList, step);
			Thread.sleep(sleepTime);

			step++;
			if (step >= limit || end) {
				end = true;
				System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
			}
		}

		long different = System.currentTimeMillis() - lastTime + globalElapsedTime;
		long finalNeededTime = (System.currentTimeMillis() - lastTime) + globalElapsedTime;
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

		System.out.printf(" --> Elapsed Time: " + "%d days, %d hours, %d minutes, %d seconds%n", elapsedDays,
				elapsedHours, elapsedMinutes, elapsedSeconds);

		System.out.println(" -------------------------------------------------------------------- ");

		System.out.println(" --> Starting time: " + startingTime);
		System.out.println(" --> Ending Time: " + System.currentTimeMillis());
		System.out.println(" Elapsed Time: " + (System.currentTimeMillis() - startingTime));
		System.out.println(" --> Initial needed steps: " + neededSteps);
		System.out.println(" --> Final Steps: " + step);
		System.out.println(" 	-> setV: " + nbrV);
		System.out.println(" 	-> Total TeT: " + totalTeT);
		System.out.println(" 	-> Indice du plan : " + indiceDep);
		System.out.println(" 	-> nbr Agent : " + nbrAgent);
		System.out.println("    -> Initial Max Vector: " + indiceMax.toString());
		System.out.println(" --> Final System Effeciency: " + sysEffeciency);
		System.out.println(" --> Les coalitions retenues: ");

		System.out.println(" 	-> Initial needed steps: " + neededSteps + " and total needed time is "
				+ (neededSteps * rand.nextInt()));

		System.out.println(" 	-> Final needed steps: " + step + " and final total needed time is " + finalNeededTime);

		System.out.println("Initial needed steps, Final Steps, setV, Total TeT, Indice du plan, nbr Agent");

		System.out.println("" + neededSteps + " , " + step + " , " + nbrV + " , " + totalTeT + " , " + indiceDep + " , "
				+ nbrAgent);

		System.out.println("Fin .....!!");

		MethColl.printAgentOnGraphs(agentList, step);

		long allValue = 0;
/*
		for (int i = 0; i < neededSteps; i++) {
			System.out.println(neededSteps + " ---> " + i + " reste: " + (neededSteps - i));
			allValue += rand.nextInt();
		}
*/
		System.out.println(" All value is " + allValue);

		MethColl.printSelectedDisc(agentList);
	}
	
	
	public static void updateElapsedTime() {
			currentTime = System.currentTimeMillis();
			globalElapsedTime += (currentTime - lastTime);
			lastTime = currentTime;
	}
	
	public static void setTimeCounter() {
		currentTime = System.currentTimeMillis();
		lastTime = currentTime;
	}

}
