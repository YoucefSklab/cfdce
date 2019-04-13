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

public class RumMyAgent {

	private static Process pro;
	//public static String jadePathOld = "/Users/sklab/git/ocfa/bin/jade.jar";
	public static String jadePath = "/Users/sklab/GamaCloudWorkigDirectory/EclipseOxy3A/GamaFrom09-18/cfdce/jade.jar";
	//public static String jadePath = "/Utilisateurs/djo/git/cfdce/jade.jar";
	//public static String classPathOld = "Users.sklab.GamaCloudWorkigDirectory.EclipseOxy3A.GamaFrom09-18.cfdce.target.classes.com.cfdce";
	public static String classPath = "target.classes.com.cfdce";
	
	public static int costLimitPercentage = 100;
	public static int maxRound = 3;
	
	
	
	
	public static void main(String args[]) throws InterruptedException, StaleProxyException {
	
		Runtime runtime=Runtime.instance();
		ProfileImpl profileImpl=new ProfileImpl(false);
		profileImpl.setParameter(ProfileImpl.MAIN_HOST,"localhost");
		AgentContainer agentContainer=runtime.createAgentContainer(profileImpl);
		AgentController agentController;
		
		agentController = agentContainer.createNewAgent("AgentTest", "com.cfdce.Agents.MyAgent", new Object[]{});
		agentController.start();


	
	} 
	
}
