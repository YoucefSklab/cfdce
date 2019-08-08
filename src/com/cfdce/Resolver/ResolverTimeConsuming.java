/**
 * 
 */
package com.cfdce.Resolver;

import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import com.cfdce.FormationCoalitions.Coalition;
import com.cfdce.FormationCoalitions.Discussion;

import jade.domain.FIPAException;

/**
 * @author Djo
 */
public class ResolverTimeConsuming {

	// needed parameters per round
	public static long totalNbrAgentInSystem = 0;
	public static long initialNeededSteps = 1;
	public static int nbrV = 0;
	public static double totalTeT = 1;
	public static double indicePlan = 0;
	public static double indiceDep = 0;
	public static double finalNeededSteps = 1;
	public static double dependanceDistance = 0;

	public static double[] exhaustifMean = new double[30];
	public static double[] uTotalMean = new double[30];
	public static double[] uMean = new double[30];
	public static double[] elapsedTimeMean = new double[30];

	public static Random rand = new Random();
	public static ArrayList<AgentModel> agentList = new ArrayList<AgentModel>();
	public static MethodesCollection MethColl = new MethodesCollection();
	public static ArrayList<Task> allTasksList = new ArrayList<Task>();
	public static ArrayList<String> tasksList = new ArrayList<String>();
	public static ArrayList<ArrayList> exclusiveList = new ArrayList<ArrayList>();
	public static ArrayList<Task> selectedCommonTasks = new ArrayList<Task>(); // dans un round, la liste des taches

	public static ArrayList<String> exploredTasks = new ArrayList<String>(); // les taches explor�es � un instant donn�.

	public static int limit = 8962624;
	public static int[] agentTab = { 4, 5,7};
	public static double[] agentUtilityTime = new double[agentTab.length];
	public static double utilityFactor = 100000000;
	public static double totalAgentUtilityTime = 0;

	public static double sysEffeciency = 0;
	public static String agentNames = "";
	public static long startingTime = System.currentTimeMillis();

	// liste des indice
	public static ArrayList<Integer> tempIndiceMin = new ArrayList<Integer>();
	public static ArrayList<Integer> tempIndiceMax = new ArrayList<Integer>();

	public static ArrayList<Integer> indiceMin = new ArrayList<Integer>();
	public static ArrayList<Integer> indiceMax = new ArrayList<Integer>();

	public static double globalElapsedTime = 0;
	public static double uConsumedTime = 0;
	public static long currentTime = 0;
	public static long lastTime = 0;

	public static int minVal = 0;
	public static int maxVal = 10;

	public static double exhaustifMeanvalue = 0;
	public static double uTotalMeanValue = 0;
	public static double uMeanValue = 0;
	public static double elapsedTimeMeanValue = 0;
	public static String filePath = "logs/Centralized/SimReasults.txt";
	public static String filePathMeanValues = "logs/Centralized/SimReasultsMeanValues.txt";
	
	public static int nbrAgents = 2;
	public static int totalPlans = 11;
	public static int[] indiceTab = null;
	
	public static int maxExperiment = 90000;
	public static int maxRounds = 31;
	public static int roundCounter = 1;
	public static boolean displayComments = false;
	public static int experimentCounter = 1;
	public static int experimentRoundCounter = 1;
	
	public static MethodesCollection methodesCollection = new MethodesCollection();
	
	public static void main(String[] args) throws IOException, FIPAException, InterruptedException {

		// ------------------------------------------------------------------------------------------
		// ------------------------------------------------------------------------------------------
		System.out.println("Let's begin ... ");
		//System.out.println(getExperimentTitle());
		resetFile(filePath);
		resetFile(filePathMeanValues);
		writeToFile(filePath, getExperimentTitle());
		writeToFile(filePathMeanValues, getExperimentMeanValuesTitle());
		System.out.println(getExperimentMeanValuesTitle());
		while (experimentCounter < maxExperiment) {
			minVal =50;
			maxVal =500;
			utilityFactor = 20000;
			
			experimentRoundCounter = 1;
			agentTab = getNewAgentsSet(totalPlans, nbrAgents);//getNewAgentsSet(1, 11, 3) ;
			agentTab =  new int[] {1,2,1,999};
			//System.out.println("Agents set: "+Arrays.toString(agentTab));
			System.out.println(getExperimentTitle());
			
			// ------------------------------------------------------------------------------------------
			while (experimentRoundCounter < maxRounds) {
				//System.out.println("- Experiment: " + experimentCounter + " - Round: " + experimentRoundCounter);
				resetExperimentParameters();

				agentList = MethColl.loadPlans(agentTab);
				allTasksList = MethColl.getTasksList(agentList);

				for (int i = 0; i < agentUtilityTime.length; i++) {
					agentUtilityTime[i] = (double) ((double) ThreadLocalRandom.current().nextInt(minVal, maxVal) /  (double) utilityFactor);
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

				//displayTasksExclusiveTasksSets();

				computeInitialNeededSteps();

				computeExperimentParameters();
				
				
				
				boolean end = false;
				while (!end) {
					// display the experiment header
					//displayRoundHeader();
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
					
				}

				//displayExperimentParameters();
				//displayMiniRoundReasults();
				getDependenceDistance();
				String values = getRoundResultValues();
				writeToFile(filePath, values);
				System.out.println(values);
				
				collectMeansValueses(experimentRoundCounter - 1);

				experimentRoundCounter++;
				// displayRoundReasults();
			}
			// ------------------------------------------------------------------------------------------
			computeExperimentMeansVariables();
			String meanValues = getExperimentMeanValues();
			
			System.out.println();
			System.out.println(getExperimentMeanValuesTitle());
			System.out.println(meanValues);
			System.out.println();
			writeToFile(filePathMeanValues, meanValues);
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

		int depIndice = 0;
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

			task.combPossibilities = methodesCollection.formCoalitionStr(task.agentList, true);

			if (totalNbrAgentInSystem < task.agentList.size())
				totalNbrAgentInSystem = task.agentList.size();

			depIndice += task.agentList.size() * task.exclusiveTasks.size();
			//System.out.println("task : "+task.task + " task.agentList.size()  : "+task.agentList.size() + " task.exclusiveTasks.size() : "+task.exclusiveTasks.size());
			
			indiceMin.add(0);
			indiceMax.add(task.combPossibilities.size());
		}
		
		dependanceDistance = depIndice;
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
	
	public static void getDependenceDistance() {
		if(nbrV == 0) return ;
		
		int totalExclusive = 0;
		int totalSize = 0;
		
		for (int t = 0; t < exclusiveList.size(); t++) {
			
			ArrayList li = exclusiveList.get(t);
			if(li.size() > 0) {
				totalExclusive += (li.size());
				//System.out.println("totalExclusive : "+totalExclusive+ " li.size() : "+li.size()+" : exclusiveList.size() - t"+(exclusiveList.size() - t));
			}
		}
		if(totalExclusive == 0) return ;
		
		//dependanceDistance = (double) ( (double) nbrV /  (double) totalExclusive);
		//dependanceDistance = totalExclusive;
	}

	public static void computeInitialNeededSteps() {
		for (int h = 0; h < tempIndiceMax.size(); h++) {
			int ind = tempIndiceMax.get(h);
			initialNeededSteps = initialNeededSteps * ind;
			if (ind >= 2) {
				nbrV++;
			}
				
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
		
		getDependenceDistance();
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
				task.combPossibilities = methodesCollection.formUpdateCoalitionStr(newAgentList, true);
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

		System.out.println("Total consumed time in  milliseconds is: " + (double) (globalElapsedTime / 1));
		System.out.println("Total utility consumed time is: " + (uConsumedTime / 1));
		System.out.println("Total needed time all alternatives is " + (initialNeededSteps * totalAgentUtilityTime));
		System.out.println("Total agent utility time: " + (totalAgentUtilityTime));

	}

	public static void displayRoundReasults() {
		System.out.println(
				"Initial needed steps, Final Steps, setV, Total TeT, Indice du plan, nbr Agent, consumed Time (ms), total agent utility time, utility consumed time (ms), time exhaustif search ");

		System.out.println("" + initialNeededSteps + " , " + finalNeededSteps + " , " + nbrV + " , " + totalTeT + " , "
				+ indiceDep + " , " + totalNbrAgentInSystem + " , " + (double) (globalElapsedTime) + " , "
				+ totalAgentUtilityTime + " , " + uConsumedTime + " , "
				+ (double) (initialNeededSteps * totalAgentUtilityTime));
	}

	public static String getExperimentTitle() {
		return "Exp, Rnd, InitialSteps, FinalSteps, nbrV, nbrAg, dependanceDistance, T (ms), Total ag Utility Time, Util consumed T (ms), Time exhaustif search, difference in time ";
	}
	public static String getRoundResultValues() {
		return String.format("%3s, %3s, %12s, %10s, %4s, %5s, %18s, %6s, %21s, %20s, %20s, %20s", 
				experimentCounter, experimentRoundCounter, (double) initialNeededSteps, finalNeededSteps, nbrV, totalNbrAgentInSystem, (double) dependanceDistance,
				(double) (globalElapsedTime), (double) totalAgentUtilityTime,  (double) uConsumedTime, (double) (initialNeededSteps * totalAgentUtilityTime),
				(double) ((double) (initialNeededSteps * totalAgentUtilityTime) - (double) ((double) (globalElapsedTime) + (double) uConsumedTime) ));
	}
	
	
	
	
	
	
	
	public static String getExperimentMeanValuesTitle() {
		return "Exp, InitialSteps, FinalSteps, nbrV, nbrAg, minVal, maxVal, Dependance  Distance, utilityFactor, exhaustifMeanValue, utility TotalMeanValue, utility  Mean  Value, elapsedTimeMeanValue, All T(MeanV+elapsed), difference in time";
	}
	public static String getExperimentMeanValues() {
		return String.format("%3s, %12s, %10s, %4s, %5s, %6s, %6s, %20s, %13s, %18s, %22s, %20s, %20s, %20s, %20s", 
				experimentCounter, (double) initialNeededSteps, finalNeededSteps, nbrV, totalNbrAgentInSystem,  
				minVal, maxVal,(double) dependanceDistance, (int) utilityFactor, (double) exhaustifMeanvalue, (double) uTotalMeanValue, (double) uMeanValue, (double) elapsedTimeMeanValue, (double)(uMeanValue + elapsedTimeMeanValue), (double) (exhaustifMeanvalue - (uMeanValue + elapsedTimeMeanValue))  );
	}
	
	public static void displayMiniRoundReasults() {
		System.out.println("Exp, Rnd, IniSteps, FinSteps, V, nbrAg, T (ms), Tot U T, U consumed T (ms), T exhaustif search ");

		System.out.println("" + experimentCounter  + " , " + experimentRoundCounter + " , " + (double) initialNeededSteps + " , " + finalNeededSteps + " , " + nbrV + " , "
				+ totalNbrAgentInSystem + " , " + (double) (globalElapsedTime) + " , " + (double) totalAgentUtilityTime
				+ " , " + (double) uConsumedTime + " , " + (double) (initialNeededSteps * totalAgentUtilityTime));
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
		allTasksList.clear();
		tasksList.clear();
		exclusiveList.clear();
		selectedCommonTasks.clear();
		
		exploredTasks.clear();
		// agentTab = { 6, 2, 5 };
		agentUtilityTime = new double[agentTab.length];
		totalAgentUtilityTime = 0;

		sysEffeciency = 0;
		agentNames = "";
		startingTime = System.currentTimeMillis();

		// liste des indice
		tempIndiceMin.clear();
		tempIndiceMax .clear();

		indiceMin.clear();
		indiceMax.clear();

		globalElapsedTime = 0;
		uConsumedTime = 0;
		currentTime = 0;
		lastTime = 0;
		
		dependanceDistance = 0;

	}

	public static void resetExperimentMeansVariables() {
		exhaustifMean = new double[30];
		uTotalMean = new double[30];
		uMean = new double[30];
		elapsedTimeMean = new double[30];
	}

	public static void collectMeansValueses(int round) {
		exhaustifMean[round] = (double) (initialNeededSteps * totalAgentUtilityTime);
		uTotalMean[round] = (double ) totalAgentUtilityTime;
		uMean[round] = (double) uConsumedTime;
		elapsedTimeMean[round] = (double) globalElapsedTime;
	}

	public static void computeExperimentMeansVariables() {
		for (int i = 0; i < 30; i++) {
			exhaustifMeanvalue += exhaustifMean[i];
			uTotalMeanValue += uTotalMean[i];
			uMeanValue += uMean[i];
			elapsedTimeMeanValue += elapsedTimeMean[i];
		}

		exhaustifMeanvalue = (double) ((double)  exhaustifMeanvalue / (double)  30);
		uTotalMeanValue = (double) ((double)  uTotalMeanValue / (double)  30);
		uMeanValue = (double) ((double)  uMeanValue / (double)  30);
		elapsedTimeMeanValue = (double) ((double)  elapsedTimeMeanValue / (double)  30);
	}
	
	public static int[] getNewAgentsSet(int maxAgent, int tabSize) {
		
		if(indiceTab == null) {
			indiceTab = new int[tabSize];
			Arrays.fill(indiceTab,1);
		}
		
		int ind = indiceTab.length - 2;
		boolean icrement = false;
		
		indiceTab[tabSize-1] = indiceTab[tabSize-1] + 1;
		if(indiceTab[tabSize-1] >= maxAgent) {
			icrement = true;
			indiceTab[tabSize-1] = 1;
		}
		
		boolean endOfExperiment = false;
		
		if(icrement) {
			while(ind >= 0) {
				int i = indiceTab[ind];
				i++;
				indiceTab[ind] = i;
				
				if(i >= maxAgent) {
					if(indiceTab[0] == maxAgent ) {
						endOfExperiment = true;
					}
					i=1;
					icrement = true;
					indiceTab[ind] = i;
					ind --;
				}else {
					break;
				}
			}
		
			if(endOfExperiment) {
				System.out.println("agent incremented");
				nbrAgents++;
				indiceTab = null;
				indiceTab = getNewAgentsSet(maxAgent, nbrAgents);
			}		
		}
		return indiceTab;
	}
	
	
	public static void writeToFile(String fileName, String text) throws IOException {
		FileWriter fStream = new FileWriter(fileName, true);
		PrintWriter out = new PrintWriter(fStream);
		out.println(text);
		out.flush();
		out.close();
		fStream.close();
	}
	
	public static void resetFile(String fileName) throws FileNotFoundException {
		PrintWriter pw = new PrintWriter(fileName);
		pw.close();
	}

}
