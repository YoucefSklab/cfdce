package com.cfdce.App;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;

public class RunAgents {

	private static Process pro;
	//public static String jadePathOld = "/Users/sklab/git/ocfa/bin/jade.jar";
	public static String jadePath = "/Users/sklab/GamaCloudWorkigDirectory/EclipseOxy3A/GamaFrom09-18/cfdce/jade.jar";
	//public static String jadePath = "/Utilisateurs/djo/git/cfdce/jade.jar";
	//public static String classPathOld = "Users.sklab.GamaCloudWorkigDirectory.EclipseOxy3A.GamaFrom09-18.cfdce.target.classes.com.cfdce";
	public static String classPath = "target.classes.com.cfdce";
	
	public static int costLimitPercentage = 100;
	public static int maxRound = 9;
	
	public static void main(String args[]) throws InterruptedException, StaleProxyException {
	
	/**
		Runtime runtime=Runtime.instance();
		ProfileImpl profileImpl=new ProfileImpl(false);
		profileImpl.setParameter(ProfileImpl.MAIN_HOST,"localhost");
		AgentContainer agentContainer=runtime.createAgentContainer(profileImpl);
		AgentController agentController;
		agentController = agentContainer.createNewAgent("Agent2", "com.cfdce.Agents.Agent2", new Object[]{"Agent2", "2", "1000", "2", ""+0});
		agentController.start();
	*/
		int Tab[] = new int[] {1,2,3};
		newContainerAgent(Tab);

	
	} // fin de main


	
	
	
	public static void newContainerAgent(int Tab[]) throws StaleProxyException{
		/*
		Runtime runtime=Runtime.instance();
		ProfileImpl profileImpl=new ProfileImpl(false);
		profileImpl.setParameter(ProfileImpl.MAIN_HOST,"localhost");
		AgentContainer agentContainer=runtime.createAgentContainer(profileImpl);
		AgentController agentController;
		
		agentController = agentContainer.createNewAgent("Ordonnanceur", "com.cfdce.Agents.Ordonnanceur", new Object[]{});
		agentController.start();
		*/
		
		
		Runtime runtimeAgentsCreator=Runtime.instance();
		ProfileImpl profileImplAgentsCreator=new ProfileImpl(false);
		profileImplAgentsCreator.setParameter(ProfileImpl.MAIN_HOST,"localhost");
		AgentContainer agentContainerAgentsCreator=runtimeAgentsCreator.createAgentContainer(profileImplAgentsCreator);
		AgentController agentControllerAgentsCreator;
		
		agentControllerAgentsCreator = agentContainerAgentsCreator.createNewAgent("AgentsCreator", "com.cfdce.Agents.AgentsCreator", new Object[]{});
		agentControllerAgentsCreator.start();
		
		File f = new File("config/GlobalStep.txt");
		Scanner sc;
		int globalStep = 1;
		try {
			sc = new Scanner(f);
			globalStep =  sc.nextInt();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		
		
		for (int i = 0; i < Tab.length; i++) {
			int nbr = Tab[i];
			agentControllerAgentsCreator = agentContainerAgentsCreator.createNewAgent("Agent"+nbr, "com.cfdce.Agents.Agent2", new Object[]{"Agent"+nbr, ""+nbr, "1000", 
					""+costLimitPercentage, ""+0, ""+maxRound, ""+globalStep});
			agentControllerAgentsCreator.start();
		}
	
	}
	
	
	
	
	
	
	
	
	public static void newAgent(String agentName, int agNbr) throws InterruptedException, StaleProxyException {
		try {
			Thread.sleep(1000);
			//String[] command2 = { "java", "-cp", "/Users/sklab/git/ocfa/bin/jade.jar", "jade.Boot", "-gui", "-agents",
			//		"Agent1:com.cfdce.Agents.HelloWorldAgent", "-container", "-host", "localhost" };
			String[] command2 = { "java", "-cp", jadePath, "-gui", "-agents",
					"Agent1:"+classPath+".Agents.HelloWorldAgent", "-container", "-host", "localhost" };
			pro = new ProcessBuilder(command2).start();

			System.out.println("Hello agent started!!!");
			String[] command3 = { "java", "-cp", "/Users/sklab/git/ocfa/bin/jade.jar", "jade.Boot", "-gui", "-agents",
					"SellerAgent:Examples.BookSellerAgent;BuyerAgent:Examples.BookBuyerAgent;", "-container", "-host",
					"localhost" };
			// Process proc3 = new ProcessBuilder(command3).start();
			PrintWriter writer = new PrintWriter("/Users/sklab/GamaCloudWorkigDirectory/EclipseOxy3A/GamaFrom09-18/cfdce/target/classes/com/cfdce/the-file-name.txt", "UTF-8");
			writer.println("The first line");
			writer.println("The second line");
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
