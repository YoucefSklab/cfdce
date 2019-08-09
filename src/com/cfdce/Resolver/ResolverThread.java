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
public class ResolverThread implements Runnable {

	private Thread t;
	private String threadName;
	// needed parameters per round
	public  long MaxAgentForTask = 0;
	public ArrayList<Integer> negotiatingAgentsList = new ArrayList<Integer>();
	public  long WPAg = 0;
	public  long WMAg = 1;
	public  long initialNeededSteps = 1;
	public  int nbrV = 0;
	public  double totalTeT = 1;
	public  double indicePlan = 0;
	public  double indiceDep = 0;
	public  double finalNeededSteps = 1;
	public  double dependanceDistance = 0;

	public  int maxRounds = 2;
	
	public  double[] exhaustifMean = new double[maxRounds - 1 ];
	public  double[] uTotalMean = new double[maxRounds - 1];
	public  double[] uMean = new double[maxRounds - 1];
	public  double[] elapsedTimeMean = new double[maxRounds - 1];

	public  Random rand = new Random();
	public  ArrayList<AgentModel> agentList = new ArrayList<AgentModel>();
	public  MethodesCollection MethColl = new MethodesCollection();
	public  ArrayList<Task> allTasksList = new ArrayList<Task>();
	public  ArrayList<String> tasksList = new ArrayList<String>();
	public  ArrayList<ArrayList> exclusiveList = new ArrayList<ArrayList>();
	public  ArrayList<Task> selectedCommonTasks = new ArrayList<Task>(); // dans un round, la liste des taches

	public  ArrayList<String> exploredTasks = new ArrayList<String>(); // les taches explor�es � un instant donn�.

	public  int limit = 8962624;
	public  int[] agentTab = { 4, 5,7};
	public  double[] agentUtilityTime = new double[agentTab.length];
	public  double utilityFactor = 100000000;
	public  double totalAgentUtilityTime = 0;

	public  double sysEffeciency = 0;
	public  String agentNames = "";
	public  long startingTime = System.currentTimeMillis();

	// liste des indice
	public  ArrayList<Integer> tempIndiceMin = new ArrayList<Integer>();
	public  ArrayList<Integer> tempIndiceMax = new ArrayList<Integer>();

	public  ArrayList<Integer> indiceMin = new ArrayList<Integer>();
	public  ArrayList<Integer> indiceMax = new ArrayList<Integer>();

	public  double globalElapsedTime = 0;
	public  double uConsumedTime = 0;
	public  long currentTime = 0;
	public  long lastTime = 0;

	public  int minVal = 0;
	public  int maxVal = 10;

	public  double exhaustifMeanvalue = 0;
	public  double uTotalMeanValue = 0;
	public  double uMeanValue = 0;
	public  double elapsedTimeMeanValue = 0;
	public  String filePath = "logs/Centralized/SimReasults.txt";
	public  String filePathMeanValues = "logs/Centralized/SimReasultsMeanValues.txt";
	
	public  int nbrAgents = 2;
	public  int totalPlans = 11;
	public  int[] indiceTab = null;
	
	public  int maxExperiment = 90000;
	
	public  int roundCounter = 1;
	public  boolean displayComments = true;
	public  int experimentCounter = 1;
	public  int experimentRoundCounter = 1;
	public int agNbr = 3;

	
	
	ResolverThread(String name, int nbrAgent, int totalPlans, int[] indiceTab){
		this.nbrAgents = nbrAgent;
		this.totalPlans = totalPlans;
		this.threadName = agNbr+"_Accumu_Good_Ag_"+name;
		this.indiceTab = indiceTab;
		this.agentTab = indiceTab;
	}

	//public  void main(int[] args) throws IOException, FIPAException, InterruptedException {
//	public void run(int[] args) throws IOException, FIPAException, InterruptedException {
	@Override
	public void run() {
		// ------------------------------------------------------------------------------------------
		// ------------------------------------------------------------------------------------------
		filePath = "logs/Centralized/"+threadName+"_SimReasults.txt";
		filePathMeanValues = "logs/Centralized/"+threadName+"_SimReasultsMeanValues.txt";
		
		System.out.println("Let's begin ... "+threadName);
		//System.out.println(getExperimentTitle());
		try {
			resetFile(filePath);
			resetFile(filePathMeanValues);
			writeToFile(filePath, getExperimentTitle());
			writeToFile(filePathMeanValues, getExperimentMeanValuesTitle());
		} catch (IOException e) { e.printStackTrace(); }
		
		System.out.println(getExperimentMeanValuesTitle());
		while (experimentCounter < maxExperiment) {
			minVal =ThreadLocalRandom.current().nextInt(20, 50);
			maxVal =ThreadLocalRandom.current().nextInt(60, 500);
			utilityFactor = 20000;
			
			experimentRoundCounter = 1;
			//agentTab = getNewAgentsSet(totalPlans, nbrAgents);//getNewAgentsSet(1, 11, 3) ;
			agentTab = new int[ThreadLocalRandom.current().nextInt(5, 11)];
			agentTab = new int[agNbr];
			agentUtilityTime = new double[agentTab.length];
			agentTab = getNewRundomAgentsSet(agentTab, 2, 800);
			//System.out.println("Agents set: "+Arrays.toString(agentTab));
			System.out.println(getExperimentTitle());
			
			// ------------------------------------------------------------------------------------------
			while (experimentRoundCounter < maxRounds) {
				//System.out.println("- Experiment: " + experimentCounter + " - Round: " + experimentRoundCounter);
				resetExperimentParameters();

				try {
					
					agentList = new MethodesCollection().loadPlans(agentTab);
				} catch (IOException e) {e.printStackTrace();}
				allTasksList = new MethodesCollection().getTasksList(agentList);

				for (int i = 0; i < agentUtilityTime.length; i++) {
					agentUtilityTime[i] = (double) ((double) ThreadLocalRandom.current().nextInt(minVal, maxVal) /  (double) utilityFactor);
					totalAgentUtilityTime += agentUtilityTime[i];
				}
				
				
				// Initialisation
				setTimeCounter();
				try {
					new MethodesCollection().formCoalitionsProposal(false, agentList);
				} catch (FIPAException | IOException e) {
					e.printStackTrace();
				}
				new MethodesCollection().computeExclusiveTasks(false, agentList);
				new MethodesCollection().setTasksExclusiveTasks(allTasksList, agentList);
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
					displayRoundHeader();
					setTimeCounter();

					resetAgentsCoalitions();

					// Evaluation des couts
					for (int i = 0; i < agentList.size(); i++) {
						AgentModel agent = agentList.get(i);
						for (int j = 0; j < agent.discussionList.size(); j++) {
							Discussion disc = (Discussion) agent.discussionList.get(j);
							try {
								disc.getFinalCost(agent.plan);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						if(i+1<agentUtilityTime.length)
						uConsumedTime += agentUtilityTime[i];
					}

					new MethodesCollection().computeAgentGainedCost(agentList);

					float newEffeciency = new MethodesCollection().computeSystemEffeciency(agentList);

					if (newEffeciency > sysEffeciency) {
						sysEffeciency = newEffeciency;
						new MethodesCollection().setBestAlternative(agentList);
					}

					updateIndiceVectors();

					if (tempIndiceMin.get(0) == tempIndiceMax.get(0)) {
						end = true;
					}

					// new MethodesCollection().printAgentOnGraphs(agentList, finalNeededSteps);

					finalNeededSteps++;

					updateElapsedTime();

					System.gc();
					
				}

				//displayExperimentParameters();
				//displayMiniRoundReasults();
				getDependenceDistance();
				String values = getRoundResultValues();
				try {
					writeToFile(filePath, values);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
			try {
				writeToFile(filePathMeanValues, meanValues);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			resetExperimentMeansVariables();
			experimentCounter++;
		}
		// ------------------------------------------------------------------------------------------
		// ------------------------------------------------------------------------------------------

		System.out.println("Fin .....!!");

		// new MethodesCollection().printSelectedDisc(agentList);
	}

	public  void updateElapsedTime() {
		currentTime = System.currentTimeMillis();
		globalElapsedTime += (currentTime - lastTime);
		lastTime = currentTime;
	}

	public  void setTimeCounter() {
		currentTime = System.currentTimeMillis();
		lastTime = currentTime;
	}

	public  void computeExclusiveSets() {

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
			
			task.combPossibilities = new MethodesCollection().formCoalitionStr(task.agentList, true);
			
			if (task.agentList.size() > 0) {
				for(int m=0; m<task.agentList.size(); m++) {
					int agent = (int) task.agentList.get(m);
					if(!negotiatingAgentsList.contains(agent))
					{
						negotiatingAgentsList.add(agent);
					}				
				}
				MaxAgentForTask = negotiatingAgentsList.size();
			}
				
			
			if(task.agentList.size() > 1){
				WPAg+= task.agentList.size();
				WMAg*= task.agentList.size();
			}

			depIndice += task.agentList.size() * task.exclusiveTasks.size();
			//System.out.println("task : "+task.task + " task.agentList.size()  : "+task.agentList.size() + " task.exclusiveTasks.size() : "+task.exclusiveTasks.size());
			
			indiceMin.add(0);
			indiceMax.add(task.combPossibilities.size());
			//indiceMax.add(new MethodesCollection().formCoalition(task.agentList).size());
		}
		
		dependanceDistance = depIndice;
	}

	public  void displayTasksExclusiveTasksSets() {
		if (!displayComments)
			return;
		System.out.println(" -*-> The tasks list and thier respective exclusive tasks set: ");
		for (int t = 0; t < tasksList.size(); t++) {
			System.out.println("    	-*-> The task: " + tasksList.get(t));
			System.out.println("    	-*-> Has exclusive: " + exclusiveList.get(t).toString());
		}
		System.out.println(" 	-> Initial indice Max :  " + tempIndiceMax.toString());

	}
	
	public  void getDependenceDistance() {
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

	public  void computeInitialNeededSteps() {
		for (int h = 0; h < tempIndiceMax.size(); h++) {
			int ind = tempIndiceMax.get(h);
			initialNeededSteps = initialNeededSteps * ind;
			if (ind >= 2) {
				nbrV++;
			}
				
		}
	}

	
	public  void computeExperimentParameters() {
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

	public  void displayExperimentParameters() {
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
		System.out.println(" 	-> Max Agent : " + MaxAgentForTask);
		System.out.println("    -> Initial Max Vector: " + indiceMax.toString());
		System.out.println(" --> Final System Effeciency: " + sysEffeciency);
		System.out.println(" --> Les coalitions retenues: ");
	}

	public  void displayRoundHeader() {
		if (!displayComments)
			return;
		System.out.println();//
		System.out.println();
		System.out.println();
		System.out.println("-- "+threadName );
		System.out.println(" - Step:" + finalNeededSteps);
		System.out.println(" -> Tache: " + tasksList.toString());
		System.out.println(" -> Indice Max: " + tempIndiceMax.toString() + " - Step:" + finalNeededSteps);
		System.out.println(" -> Indice Min: " + tempIndiceMin.toString() + " - Step:" + finalNeededSteps);
	}

	public  void resetAgentsCoalitions() {
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
			new MethodesCollection().updateTaskExclusiveAgents(task.task, agentList, allTasksList, liste);
			if (liste.size() >= 2) { // ex�cution collective
				new MethodesCollection().setCollectiveExecution(agentList, liste, task.task, allTasksList, selectedCommonTasks,
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

	public  void updateIndiceVectors() {
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
				task.combPossibilities = new MethodesCollection().formUpdateCoalitionStr(newAgentList, true);
				tempIndiceMax.set(h, task.combPossibilities.size());
				
			}
		}
	}

	public  void displayRoundParameters() {
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

	public  void displayRoundReasults() {
		System.out.println(
				"Initial needed steps, Final Steps, setV, Total TeT, Indice du plan, Max Agent, WPAg, WMAg, consumed Time (ms), total agent utility time, utility consumed time (ms), time exhaustif search ");

		System.out.println("" + initialNeededSteps + " , " + finalNeededSteps + " , " + nbrV + " , " + totalTeT + " , "
				+ indiceDep + " , " + MaxAgentForTask + " , " + WPAg + " , " + WMAg+ " , " + (double) (globalElapsedTime) + " , "
				+ totalAgentUtilityTime + " , " + uConsumedTime + " , "
				+ (double) (initialNeededSteps * totalAgentUtilityTime));
	}

	public  String getExperimentTitle() {
		return "Exp, Rnd, InitialSteps, FinalSteps, nbrV, MaxAg, WPAg, WMAg, dependanceDistance, T (ms), Total ag Utility Time, Util consumed T (ms), Time exhaustif search, difference in time ";
	}
	public  String getRoundResultValues() {
		return String.format("%3s, %3s, %12s, %10s, %4s, %5s,, %5s, %5s %18s, %6s, %21s, %20s, %20s, %20s", 
				experimentCounter, experimentRoundCounter, (double) initialNeededSteps, finalNeededSteps, nbrV, MaxAgentForTask, WPAg, WMAg, (double) dependanceDistance,
				(double) (globalElapsedTime), (double) totalAgentUtilityTime,  (double) uConsumedTime, (double) (initialNeededSteps * totalAgentUtilityTime),
				(double) ((double) (initialNeededSteps * totalAgentUtilityTime) - (double) ((double) (globalElapsedTime) + (double) uConsumedTime) ));
	}
	
	
	
	
	
	
	
	public  String getExperimentMeanValuesTitle() {
		return "Exp, InitialSteps, FinalSteps, nbrV, nbrAg, WPAg, WMAg, minVal, maxVal, Dependance  Distance, utilityFactor, exhaustifMeanValue, utility TotalMeanValue, utility  Mean  Value, elapsedTimeMeanValue, All T(MeanV+elapsed), difference in time";
	}
	public  String getExperimentMeanValues() {
		return String.format("%3s, %12s, %10s, %4s, %5s, %5s, %5s, %6s, %6s, %20s, %13s, %18s, %22s, %20s, %20s, %20s, %20s", 
				experimentCounter, (double) initialNeededSteps, finalNeededSteps, nbrV, MaxAgentForTask, WPAg, WMAg,   
				minVal, maxVal,(double) dependanceDistance, (int) utilityFactor, (double) exhaustifMeanvalue, (double) uTotalMeanValue, (double) uMeanValue, (double) elapsedTimeMeanValue, (double)(uMeanValue + elapsedTimeMeanValue), (double) (exhaustifMeanvalue - (uMeanValue + elapsedTimeMeanValue))  );
	}
	
	public  void displayMiniRoundReasults() {
		System.out.println("Exp, Rnd, IniSteps, FinSteps, V, MaxAg, WPAg, WMAg, T (ms), Tot U T, U consumed T (ms), T exhaustif search ");

		System.out.println("" + experimentCounter  + " , " + experimentRoundCounter + " , " + (double) initialNeededSteps + " , " + finalNeededSteps + " , " + nbrV + " , "
				+ MaxAgentForTask + " , " + WPAg +" , " + WMAg + " , " + (double) (globalElapsedTime) + " , " + (double) totalAgentUtilityTime
				+ " , " + (double) uConsumedTime + " , " + (double) (initialNeededSteps * totalAgentUtilityTime));
	}

	public  void resetExperimentParameters() {
		MaxAgentForTask = 0;
		WPAg = 0;
		WMAg = 1;
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

	public  void resetExperimentMeansVariables() {
		exhaustifMean = new double[maxRounds - 1];
		uTotalMean = new double[maxRounds - 1];
		uMean = new double[maxRounds - 1];
		elapsedTimeMean = new double[maxRounds - 1];
	}

	public  void collectMeansValueses(int round) {
		exhaustifMean[round] = (double) (initialNeededSteps * totalAgentUtilityTime);
		uTotalMean[round] = (double ) totalAgentUtilityTime;
		uMean[round] = (double) uConsumedTime;
		elapsedTimeMean[round] = (double) globalElapsedTime;
	}

	public  void computeExperimentMeansVariables() {
		for (int i = 0; i < maxRounds - 1; i++) {
			exhaustifMeanvalue += exhaustifMean[i];
			uTotalMeanValue += uTotalMean[i];
			uMeanValue += uMean[i];
			elapsedTimeMeanValue += elapsedTimeMean[i];
		}

		exhaustifMeanvalue = (double) ((double)  exhaustifMeanvalue / (double)  maxRounds - 1);
		uTotalMeanValue = (double) ((double)  uTotalMeanValue / (double)  maxRounds - 1);
		uMeanValue = (double) ((double)  uMeanValue / (double)  maxRounds - 1);
		elapsedTimeMeanValue = (double) ((double)  elapsedTimeMeanValue / (double)  maxRounds - 1);
	}
	
	
	public  int[] getNewRundomAgentsSet(int[] tabIndice, int minPlans, int maxPlans) {
		
				
		for(int i=0; i<tabIndice.length; i++) {
			tabIndice[i] = ThreadLocalRandom.current().nextInt(minPlans, maxPlans);
		}
		
		return tabIndice;
	}
	
	
	public  int[] getNewAgentsSet(int maxAgent, int tabSize) {
		
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
	
	
	public  void writeToFile(String fileName, String text) throws IOException {
		FileWriter fStream = new FileWriter(fileName, true);
		PrintWriter out = new PrintWriter(fStream);
		out.println(text);
		out.flush();
		out.close();
		fStream.close();
	}
	
	public  void resetFile(String fileName) throws FileNotFoundException {
		PrintWriter pw = new PrintWriter(fileName);
		pw.close();
	}
	
	 public void start () {
	      System.out.println("Starting " + threadName);
	      if (t == null) {
	         t = new Thread (this, threadName);
	         t.start ();
	      }
	   }



}
