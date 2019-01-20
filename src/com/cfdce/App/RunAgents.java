package com.cfdce.App;

import java.io.IOException;
import java.io.PrintWriter;

import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;

public class RunAgents {

	private static Process pro;
	public static String jadePathOld = "/Users/sklab/git/ocfa/bin/jade.jar";
	public static String jadePath = "/Users/sklab/GamaCloudWorkigDirectory/EclipseOxy3A/GamaFrom09-18/cfdce/jade.jar";
	public static String classPathOld = "Users.sklab.GamaCloudWorkigDirectory.EclipseOxy3A.GamaFrom09-18.cfdce.target.classes.com.cfdce";
	public static String classPath = "target.classes.com.cfdce";
	
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
		newContainerAgent();

	
	} // fin de main

	
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
	
	
	
	public static void newContainerAgent() throws StaleProxyException{
		
		Runtime runtime=Runtime.instance();
		ProfileImpl profileImpl=new ProfileImpl(false);
		profileImpl.setParameter(ProfileImpl.MAIN_HOST,"localhost");
		AgentContainer agentContainer=runtime.createAgentContainer(profileImpl);
		AgentController agentController;
		
		for (int i = 1; i < 4; i++) {
			agentController = agentContainer.createNewAgent("Agent"+i, "com.cfdce.Agents.Agent2", new Object[]{"Agent"+i, ""+i, "1000", ""+i, ""+0});
			agentController.start();
		}
	
	}
}
