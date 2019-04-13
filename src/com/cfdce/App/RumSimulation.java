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

	public static int costLimitPercentage = 100;
	public static int maxRound = 3;

	public static int round = 0;
	public static boolean isSimulation = true;
	public static boolean isNewRound = true;

	public static void main(String args[]) throws InterruptedException, StaleProxyException, IOException {

		int agentTab[] = new int[] { 1, 2, 3 };

		int globalStep = 1;
		globalStep = loadGlobalStep();

		AgentContainer agentContainer = null;
		AgentController agentController = null;

		while (isSimulation) {

			// premier passage
			if (round == 0) {
				for (int i = 0; i < agentTab.length; i++) {
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

			for (int i = 0; i < agentTab.length; i++) {
				int nbr = agentTab[i];
				agentController = agentContainer.createNewAgent("Agent" + nbr, "com.cfdce.Agents.Agent2",
						new Object[] { "Agent" + nbr, "" + nbr, "1000", "" + costLimitPercentage, "" + 0, "" + maxRound,
								"" + globalStep });
				agentController.start();
			}

			try {
				Thread.sleep(4000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			isNewRound = true;
			
			while (isNewRound) {
				
				isNew = true;
				for (int i = 0; i < agentTab.length; i++) {
					int st = 1;
					st = getStatut("Agent" + agentTab[i]);
					if (st == 1) {
						isNew = false;
					}
				}

				if (isNew) {
					agentController.kill();
					agentContainer.kill();
					globalStep++;
					writeGlobalStep(globalStep);
					System.out.println("Launch another simulation");
					isNewRound = false;
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

	public static void writeGlobalStep(int GlobalStep) throws IOException {
		PrintWriter f = new PrintWriter(new FileWriter("config/GlobalStep.txt"));
		f.write((GlobalStep + 1) + "");
		f.close();
	}

}
