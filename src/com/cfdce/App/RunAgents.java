package com.cfdce.App;

import java.io.IOException;

public class RunAgents {

	private static Process pro;
	public static String jadePathOld = "/Users/sklab/git/ocfa/bin/jade.jar";
	public static String jadePath = "/Users/sklab/GamaCloudWorkigDirectory/EclipseOxy3A/GamaFrom09-18/com.cf.cfdce/target/classes/jade.jar";
	public static String classPath = "Users.sklab.GamaCloudWorkigDirectory.EclipseOxy3A.GamaFrom09-18.com.cf.cfdce.target.classes";
	
	public static void main(String args[]) throws InterruptedException {

		for (int i = 1; i < 2; i++) {

			newAgent("Agent" + i, i);

		}

	} // fin de main

	
	public static void newAgent(String agentName, int agNbr) throws InterruptedException {

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

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
