package com.cfdce.App;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;

public class RumSimulation {

	private static Process pro;
	// public static String jadePathOld = "/Users/sklab/git/ocfa/bin/jade.jar";
	public static String jadePath = "/Users/sklab/GamaCloudWorkigDirectory/EclipseOxy3A/GamaFrom09-18/cfdce/jade.jar";
	// public static String jadePath = "/Utilisateurs/djo/git/cfdce/jade.jar";
	// public static String classPathOld =
	// "Users.sklab.GamaCloudWorkigDirectory.EclipseOxy3A.GamaFrom09-18.cfdce.target.classes.com.cfdce";
	public static String classPath = "target.classes.com.cfdce";

	public static int costLimitPercentage = 70;
	public static int maxRound = 15;
	public static String prefix = "";
	
	public static int costLimitPercentageBAA = 68;
	public static int maxRoundBAA = 3;
	public static String prefixBAA = "BAA";
	
	public static int costLimitPercentageCFDCE = 70;
	public static int maxRoundCFDCE = 15;
	public static String prefixCFDC = "CFDE";
	
	
	public static long currentTime = (long) System.currentTimeMillis();
	public static long roundTime = (long) System.currentTimeMillis();
	public static long timeInterval = 1800000;

	
	public static changeExtraCost chgExtraCost = new changeExtraCost();
	
	public static int nbrAgents = 10;
	public static int simRound = 30;
	public static int round = 0;
	public static boolean isSimulation = true;
	public static boolean isNewRound = true;

	public static void main(String args[]) throws InterruptedException, StaleProxyException, IOException {

		int agentTab[] = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};

		int globalStep = 1;
		int globalStepCounter = 1;
		globalStep = loadGlobalStep();

		AgentContainer agentContainer = null;
		AgentController agentController = null;
	
		int sim = 1;
		while (isSimulation) {
			
			if(globalStep > simRound) {
				globalStep = 1;
				nbrAgents = nbrAgents + 1;
			}
			
			if((globalStepCounter % 2) == 1) {
				int cost = loadCostLimit();
				costLimitPercentage = costLimitPercentageCFDCE;
				
				if(cost > 40 && cost <= 100)
				costLimitPercentage = cost;
				
				maxRound = maxRoundCFDCE;
				prefix = prefixCFDC;
				sim = 1;
			}else {
				costLimitPercentage = costLimitPercentageBAA;
				maxRound = maxRoundBAA;
				prefix = prefixBAA;
				sim = 2;
			}
			// premier passage
			if (round == 0) {
				for (int i = 0; i < nbrAgents; i++) {
					writeStatut(0, "Agent" + agentTab[i]);
				}
			}

			try {
				Thread.sleep(4000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			
			
			boolean isNew = true;

			Runtime runtimeAgentsCreator = Runtime.instance();
			ProfileImpl profileImplAgentsCreator = new ProfileImpl(false);
			profileImplAgentsCreator.setParameter(ProfileImpl.MAIN_HOST, "localhost");
			agentContainer = runtimeAgentsCreator.createAgentContainer(profileImplAgentsCreator);

			
			
			for (int i = 0; i < nbrAgents; i++) {
				int nbr = agentTab[i];
				agentController = agentContainer.createNewAgent("Agent" + nbr, "com.cfdce.Agents.Agent2",
						new Object[] { "Agent" + nbr, "" + nbr, "1000", "" + costLimitPercentage, "" + 0, "" + maxRound,
								"" + globalStep, prefix , ""+nbrAgents});
				agentController.start();
			}

			try {
				Thread.sleep(4000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			isNewRound = true;
			
			while (isNewRound) {
				System.out.println();System.out.println();
				System.out.println(" ------------------------" );
				System.out.println(" ----->>>>   Current simulation is with global: "+globalStep+ "   and prefix: "+prefix);
				System.out.println(" ----->>>>   Elepsed time is : "+((long) System.currentTimeMillis() - roundTime));
				System.out.println(" ----->>>>   Interval remaining time is : "+ (timeInterval - ((long) System.currentTimeMillis() - roundTime)));
				
				System.out.println(" ------------------------" );
				System.out.println();System.out.println();
				
				isNew = true;
				for (int i = 0; i < nbrAgents; i++) {
					int st = 1;
					st = getStatut("Agent" + agentTab[i]);
					if (st == 1) {
						isNew = false;
					}
				}

				if (isNew) {
					try { agentController.kill();} catch (Exception e) { e.printStackTrace();	}
					try { agentContainer.kill();} catch (Exception e) { e.printStackTrace();	}
					globalStepCounter++;
					writeSimStatut(globalStep, prefix,"Succeded");
					
					if(sim == 2) {
						globalStep++;
						writeGlobalStep(globalStep);
						chgExtraCost.main(args);
					}
					System.out.println("Launch another simulation");
					isNewRound = false;
					roundTime = (long) System.currentTimeMillis();
				}else {
					
					currentTime = (long) System.currentTimeMillis();
					if((currentTime - roundTime ) > timeInterval) {
						
						writeSimStatut(globalStep, prefix, "Failed");
						try { agentController.kill();} catch (Exception e) { e.printStackTrace();	}
						try { agentContainer.kill();} catch (Exception e) { e.printStackTrace();	}
						globalStepCounter++;
						globalStep++;
						writeGlobalStep(globalStep);
						chgExtraCost.main(args);
						
						for (int i = 0; i < nbrAgents; i++) {
							writeStatut(0, "Agent"+agentTab[i]); 
						}
						
					}
					
					
				}

				try {
					Thread.sleep(4000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				
			}

		}

		/**
			
		*/
		// int Tab[] = new int[] {1,2,3};
		// newContainerAgent(Tab);

	} // fin de main

	public static void newContainerAgent(int Tab[]) throws StaleProxyException {
		/*
		 * Runtime runtime=Runtime.instance(); ProfileImpl profileImpl=new
		 * ProfileImpl(false);
		 * profileImpl.setParameter(ProfileImpl.MAIN_HOST,"localhost"); AgentContainer
		 * agentContainer=runtime.createAgentContainer(profileImpl); AgentController
		 * agentController;
		 * 
		 * agentController = agentContainer.createNewAgent("Ordonnanceur",
		 * "com.cfdce.Agents.Ordonnanceur", new Object[]{}); agentController.start();
		 */

		Runtime runtimeAgentsCreator = Runtime.instance();
		ProfileImpl profileImplAgentsCreator = new ProfileImpl(false);
		profileImplAgentsCreator.setParameter(ProfileImpl.MAIN_HOST, "localhost");
		AgentContainer agentContainerAgentsCreator = runtimeAgentsCreator
				.createAgentContainer(profileImplAgentsCreator);
		AgentController agentControllerAgentsCreator;
		agentControllerAgentsCreator = agentContainerAgentsCreator.createNewAgent("AgentsCreator",
				"com.cfdce.Agents.AgentsCreator", new Object[] {});
		agentControllerAgentsCreator.start();

		File f = new File("config/GlobalStep.txt");
		Scanner sc;
		int globalStep = 1;

		try {
			sc = new Scanner(f);
			globalStep = sc.nextInt();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (int i = 0; i < Tab.length; i++) {
			int nbr = Tab[i];
			agentControllerAgentsCreator = agentContainerAgentsCreator.createNewAgent("Agent" + nbr,
					"com.cfdce.Agents.Agent2", new Object[] { "Agent" + nbr, "" + nbr, "1000", "" + costLimitPercentage,
							"" + 0, "" + maxRound, "" + globalStep });
			agentControllerAgentsCreator.start();
		}

	}

	public static void writeStatut(int st, String name) throws IOException {
		File statutFile = new File("logs/" + name + ".txt");
		PrintWriter f = new PrintWriter(new FileWriter(statutFile));
		f.write(st + "");
		f.close();
	}

	public static int getStatut(String name) throws IOException {
		File f = new File("logs/" + name + ".txt");
		Scanner sc = new Scanner(f);
		return sc.nextInt();
	}

	public static int loadGlobalStep() throws FileNotFoundException {
		File f = new File("config/GlobalStep.txt");
		Scanner sc = new Scanner(f);
		return sc.nextInt();
	}

	public static int loadCostLimit() throws FileNotFoundException {
		File f = new File("config/CostLimit.txt");
		Scanner sc = new Scanner(f);
		return sc.nextInt();
	}
	
	public static void writeGlobalStep(int GlobalStep) throws IOException {
		PrintWriter f = new PrintWriter(new FileWriter("config/GlobalStep.txt"));
		f.write((GlobalStep + 1) + "");
		f.close();
	}
	
	public static void writeSimStatut(int step, String pref,  String statut) throws IOException {
		File statutFile = new File("logs/Sim_"+step+"_"+pref+".txt");
		PrintWriter f = new PrintWriter(new FileWriter(statutFile));
		f.write(step+" simulation is "+statut);
		f.close();
	}
	
	

}
