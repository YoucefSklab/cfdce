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
import java.util.concurrent.ThreadLocalRandom;
import com.cfdce.FormationCoalitions.Coalition;
import com.cfdce.FormationCoalitions.Discussion;

import jade.domain.FIPAException;

/**
 * @author Djo
 */
public class ResolverTimeConsuming {

	public static int maxExperiment = 200;
	public static int maxRounds = 31;
	public static int roundCounter = 1;
	public static boolean displayComments = false;

	// needed parameters per round
	public static long totalNbrAgentInSystem = 0;
	public static long initialNeededSteps = 1;
	public static int nbrV = 0;
	public static long totalTeT = 1;
	public static long indicePlan = 0;
	public static long indiceDep = 0;
	public static long finalNeededSteps = 1;

	public static long[] exhaustifMean = new long[30];
	public static long[] uTotalMean = new long[30];
	public static long[] uMean = new long[30];
	public static long[] elapsedTimeMean = new long[30];

	public static Random rand = new Random();
	public static ArrayList<AgentModel> agentList = new ArrayList<AgentModel>();
	public static MethodesCollection MethColl = new MethodesCollection();
	public static ArrayList<Task> allTasksList = new ArrayList<Task>();
	public static ArrayList<String> tasksList = new ArrayList<String>();
	public static ArrayList<ArrayList> exclusiveList = new ArrayList<ArrayList>();
	public static ArrayList<Task> selectedCommonTasks = new ArrayList<Task>(); // dans un round, la liste des taches

	public static ArrayList<String> exploredTasks = new ArrayList<String>(); // les taches explor�es � un instant donn�.

	public static int limit = 8962624;
	public static int[] agentTab = { 6, 2, 5 };
	public static int[] agentUtilityTime = new int[agentTab.length];
	public static int totalAgentUtilityTime = 0;

	public static float sysEffeciency = 0;
	public static String agentNames = "";
	public static long startingTime = System.currentTimeMillis();

	// liste des indice
	public static ArrayList<Integer> tempIndiceMin = new ArrayList<Integer>();
	public static ArrayList<Integer> tempIndiceMax = new ArrayList<Integer>();

	public static ArrayList<Integer> indiceMin = new ArrayList<Integer>();
	public static ArrayList<Integer> indiceMax = new ArrayList<Integer>();

	public static long globalElapsedTime = 0;
	public static long uConsumedTime = 0;
	public static long currentTime = 0;
	public static long lastTime = 0;

	public static int minVal = 50;
	public static int maxVal = 500;

	public static long exhaustifMeanvalue = 0;
	public static long uTotalMeanValue = 0;
	public static long uMeanValue = 0;
	public static long elapsedTimeMeanValue = 0;

	public static void main(String[] args) throws IOException, FIPAException, InterruptedException {

		// ------------------------------------------------------------------------------------------
		// ------------------------------------------------------------------------------------------
		int experimentCounter = 1;
		while (experimentCounter < maxExperiment) {
			int experimentRoundCounter = 1;
			// ------------------------------------------------------------------------------------------
			while (experimentRoundCounter < maxRounds) {
				System.out.println("- Experiment: " + experimentCounter + " - Round: " + experimentRoundCounter);
				resetExperimentParameters();

				agentList = MethColl.loadPlans(agentTab);
				allTasksList = MethColl.getTasksList(agentList);

				for (int i = 0; i < agentUtilityTime.length; i++) {
					agentUtilityTime[i] = ThreadLocalRandom.current().nextInt(minVal, maxVal);
					totalAgentUtilityTime += agentUtilityTime[i];
				}

				// Initialisation
				setTimeCounter();
				MethColl.formCoalitionsProposal(false, agentList);
				MethColl.computeExclusiveTasks(false, agentList);
				MethColl.setTasksExclusiveTasks(allTasksList, agentList);
				updateElapsedTime();

				// --------------------------------
				setTimeCounter();
				computeExclusiveSets();
				updateElapsedTime();
				// ---------------------------------

				tempIndiceMax = (ArrayList<Integer>) indiceMax.clone();
				tempIndiceMin = (ArrayList<Integer>) indiceMin.clone();

				displayTasksExclusiveTasksSets();

				computeInitialNeededSteps();

				computeExperimentParameters();

				boolean end = false;
				while (!end) {
					displayRoundHeader();
					setTimeCounter();

					resetAgentsCoalitions();

					// Evaluation des couts
					for (int i = 0; i < agentList.size(); i++) {
						AgentModel agent = agentList.get(i);
						for (int j = 0; j < agent.discussionList.size(); j++) {
							Discussion disc = (Discussion) agent.discussionList.get(j);
							disc.getFinalCost(agent.plan);
						}
						uConsumedTime += agentUtilityTime[i];
					}

					MethColl.computeAgentGainedCost(agentList);

					float newEffeciency = MethColl.computeSystemEffeciency(agentList);

					if (newEffeciency > sysEffeciency) {
						sysEffeciency = newEffeciency;
						MethColl.setBestAlternative(agentList);
					}

					updateIndiceVectors();

					if (tempIndiceMin.get(0) == tempIndiceMax.get(0)) {
						end = true;
					}

					// MethColl.printAgentOnGraphs(agentList, finalNeededSteps);

					finalNeededSteps++;

					updateElapsedTime();

					System.gc();
					if (finalNeededSteps >= limit || end) {
						end = true;
						System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
					}
				}

				displayExperimentParameters();
				displayMiniRoundReasults();
				collectMeansValueses(experimentRoundCounter - 1);

				experimentRoundCounter++;
				// displayRoundReasults();
			}
			// ------------------------------------------------------------------------------------------
			computeExperimentMeansVariables();

			System.out.println();
			System.out.println(
					"Exp Values are: exhaustifMeanvalue " + exhaustifMeanvalue + " uTotalMeanValue: " + uTotalMeanValue
							+ " uMeanValue: " + uMeanValue + " elapsedTimeMeanValue: " + elapsedTimeMeanValue);
			System.out.println();

			resetExperimentMeansVariables();
			experimentCounter++;
		}
		// ------------------------------------------------------------------------------------------
		// ------------------------------------------------------------------------------------------

		System.out.println("Fin .....!!");

		// MethColl.printSelectedDisc(agentList);
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

	public static void computeExclusiveSets() {

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

			task.combPossibilities = MethodesCollection.formCoalitionStr(task.agentList, true);

			if (totalNbrAgentInSystem < task.agentList.size())
				totalNbrAgentInSystem = task.agentList.size();

			indiceMin.add(0);
			indiceMax.add(task.combPossibilities.size());
		}
	}

	public static void displayTasksExclusiveTasksSets() {
		if (!displayComments)
			return;
		System.out.println(" -*-> The tasks list and thier respective exclusive tasks set: ");
		for (int t = 0; t < tasksList.size(); t++) {
			System.out.println("    	-*-> The task: " + tasksList.get(t));
			System.out.println("    	-*-> Has exclusive: " + exclusiveList.get(t).toString());
		}
		System.out.println(" 	-> Initial indice Max :  " + tempIndiceMax.toString());

	}

	public static void computeInitialNeededSteps() {
		for (int h = 0; h < tempIndiceMax.size(); h++) {
			int ind = tempIndiceMax.get(h);
			initialNeededSteps = initialNeededSteps * ind;
			if (ind >= 2)
				nbrV++;
		}
	}

	public static void computeExperimentParameters() {
		for (int t = 0; t < tasksList.size(); t++) {
			int ind = tempIndiceMax.get(t);
			if (ind >= 2) {
				ArrayList list = exclusiveList.get(t);
				int nbtTasksV = 0;
				// Calculer le nombre de tache dans TeT ayant plus de deux agents possible
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
		indiceDep = (long) (totalTeT / Math.pow(nbrV, nbrV));
	}

	public static void displayExperimentParameters() {
		if (!displayComments)
			return;
		System.out.println(" -------------------------------------------------------------------- ");
		System.out.println(" --> Starting time: " + startingTime);
		System.out.println(" --> Ending Time: " + System.currentTimeMillis());
		System.out.println(" Elapsed Time: " + (System.currentTimeMillis() - startingTime));
		System.out.println(" --> Initial needed steps: " + initialNeededSteps);
		System.out.println(" --> Final Steps: " + finalNeededSteps);
		System.out.println(" 	-> setV: " + nbrV);
		System.out.println(" 	-> Total TeT: " + totalTeT);
		System.out.println(" 	-> Indice du plan : " + indiceDep);
		System.out.println(" 	-> nbr Agent : " + totalNbrAgentInSystem);
		System.out.println("    -> Initial Max Vector: " + indiceMax.toString());
		System.out.println(" --> Final System Effeciency: " + sysEffeciency);
		System.out.println(" --> Les coalitions retenues: ");
	}

	public static void displayRoundHeader() {
		if (!displayComments)
			return;
		System.out.println();//
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println(" - Step:" + finalNeededSteps);
		System.out.println(" -> Tache: " + tasksList.toString());
		System.out.println(" -> Indice Max: " + tempIndiceMax.toString() + " - Step:" + finalNeededSteps);
		System.out.println(" -> Indice Min: " + tempIndiceMin.toString() + " - Step:" + finalNeededSteps);
	}

	public static void resetAgentsCoalitions() {
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
			task.tasksExclusiveAgents.clear();
			ArrayList liste = task.getPossibilitiesFromStr(tempIndiceMin.get(i));
			MethColl.updateTaskExclusiveAgents(task.task, agentList, allTasksList, liste);
			if (liste.size() >= 2) { // ex�cution collective
				MethColl.setCollectiveExecution(agentList, liste, task.task, allTasksList, selectedCommonTasks,
						exploredTasks);
			}
		}

		for (int h = 0; h < agentList.size(); h++) {
			AgentModel agent = agentList.get(h);
			for (int k = 0; k < agent.discussionList.size(); k++) {
				Discussion disc = (Discussion) agent.discussionList.get(k);
				for (int m = 0; m < disc.coalitionList.size(); m++) {
					Coalition coal = (Coalition) disc.coalitionList.get(m);
					if (coal.agentForCoalitionList.size() <= 0) {
						coal.agentForCoalitionList
								.add(Integer.parseInt(coal.agentOwner.substring(5, coal.agentOwner.length())));
					}
				}
			}
		}
	}

	public static void updateIndiceVectors() {
		int nbrTask = allTasksList.size() - 1;
		tempIndiceMin.set(nbrTask, tempIndiceMin.get(nbrTask) + 1);

		int cmp = allTasksList.size() - 1;
		int indice = cmp + 1;
		while (cmp > 0) {

			if (tempIndiceMin.get(cmp) >= tempIndiceMax.get(cmp)) {
				Task t = allTasksList.get(cmp - 1);
				tempIndiceMin.set(cmp, 0);
				tempIndiceMin.set(cmp - 1, tempIndiceMin.get(cmp - 1) + 1);

				if (indice >= (cmp - 1)) {
					indice = cmp - 1;
				}
			}
			cmp--;
		}

		if (indice < (allTasksList.size() - 1)) {
			indice = indice + 1;
			for (int h = indice; h < allTasksList.size(); h++) {
				Task task = allTasksList.get(h);
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
				task.combPossibilities = MethodesCollection.formUpdateCoalitionStr(newAgentList, true);
				tempIndiceMax.set(h, task.combPossibilities.size());
			}
		}
	}

	public static void displayRoundParameters() {
		if (!displayComments)
			return;
		System.out.println("	-> Initial needed steps: " + initialNeededSteps + " and total needed time is "
				+ (initialNeededSteps * totalAgentUtilityTime));

		System.out.println(" 	-> Final needed steps: " + finalNeededSteps + " and final total needed time is "
				+ (finalNeededSteps * totalAgentUtilityTime));

		displayRoundReasults();

		System.out.println("Total consumed time in  milliseconds is: " + (long) (globalElapsedTime / 1));
		System.out.println("Total utility consumed time is: " + (uConsumedTime / 1));
		System.out.println("Total needed time all alternatives is " + (initialNeededSteps * totalAgentUtilityTime));
		System.out.println("Total agent utility time: " + (totalAgentUtilityTime));

	}

	public static void displayRoundReasults() {
		System.out.println(
				"Initial needed steps, Final Steps, setV, Total TeT, Indice du plan, nbr Agent, consumed Time (ms), total agent utility time, utility consumed time (ms), time exhaustif search ");

		System.out.println("" + initialNeededSteps + " , " + finalNeededSteps + " , " + nbrV + " , " + totalTeT + " , "
				+ indiceDep + " , " + totalNbrAgentInSystem + " , " + (long) (globalElapsedTime) + " , "
				+ totalAgentUtilityTime + " , " + uConsumedTime + " , "
				+ (long) (initialNeededSteps * totalAgentUtilityTime));
	}

	public static void displayMiniRoundReasults() {
		System.out.println(
				"Ini steps, Final Steps, V, nbr Ag, consumed T (ms), total agent u T, u consumed T (ms), T exhaustif search ");

		System.out.println("" + (long) initialNeededSteps + " , " + finalNeededSteps + " , " + nbrV + " , "
				+ totalNbrAgentInSystem + " , " + (long) (globalElapsedTime) + " , " + (long) totalAgentUtilityTime
				+ " , " + (long) uConsumedTime + " , " + (long) (initialNeededSteps * totalAgentUtilityTime));
	}

	public static void resetExperimentParameters() {
		totalNbrAgentInSystem = 0;
		initialNeededSteps = 1;
		nbrV = 0;
		totalTeT = 1;
		indicePlan = 0;
		indiceDep = 0;
		finalNeededSteps = 1;

		// agentList = new ArrayList<AgentModel>();
		MethColl = new MethodesCollection();
		allTasksList = new ArrayList<Task>();
		tasksList = new ArrayList<String>();
		exclusiveList = new ArrayList<ArrayList>();
		selectedCommonTasks = new ArrayList<Task>(); // dans un round, la liste des taches
														// ayant plusieurs agents.
		exploredTasks = new ArrayList<String>(); // les taches explor�es � un instant donn�.
		// agentTab = { 6, 2, 5 };
		agentUtilityTime = new int[agentTab.length];
		totalAgentUtilityTime = 0;

		sysEffeciency = 0;
		agentNames = "";
		startingTime = System.currentTimeMillis();

		// liste des indice
		tempIndiceMin = new ArrayList<Integer>();
		tempIndiceMax = new ArrayList<Integer>();

		indiceMin = new ArrayList<Integer>();
		indiceMax = new ArrayList<Integer>();

		globalElapsedTime = 0;
		uConsumedTime = 0;
		currentTime = 0;
		lastTime = 0;

	}

	public static void resetExperimentMeansVariables() {
		exhaustifMean = new long[30];
		uTotalMean = new long[30];
		uMean = new long[30];
		elapsedTimeMean = new long[30];
	}

	public static void collectMeansValueses(int round) {
		exhaustifMean[round] = (long) (initialNeededSteps * totalAgentUtilityTime);
		uTotalMean[round] = (long) totalAgentUtilityTime;
		uMean[round] = (long) uConsumedTime;
		elapsedTimeMean[round] = (long) globalElapsedTime;
	}

	public static void computeExperimentMeansVariables() {
		for (int i = 0; i < 30; i++) {
			exhaustifMeanvalue += exhaustifMean[i];
			uTotalMeanValue += uTotalMean[i];
			uMeanValue += uMean[i];
			elapsedTimeMeanValue += elapsedTimeMean[i];
		}

		exhaustifMeanvalue = (long) (exhaustifMeanvalue / 30);
		uTotalMeanValue = (long) (uTotalMeanValue / 30);
		uMeanValue = (long) (uMeanValue / 30);
		elapsedTimeMeanValue = (long) (elapsedTimeMeanValue / 30);
	}

}
