package com.cfdce.agentsContainers;
import java.io.FileNotFoundException;

import org.graphstream.graph.Graph;

import com.cfdce.Plan.planSet;

import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
public class A4 {
	public static void main(String[] args )throws FileNotFoundException {
	planSet pSet = new planSet();
	Graph Plan4 = pSet.getPlanByNbr(4);
		try {
			Runtime runtime=Runtime.instance();
			ProfileImpl profileImpl=new ProfileImpl(false);
			profileImpl.setParameter(ProfileImpl.MAIN_HOST,"localhost");
			AgentContainer agentContainer=runtime.createAgentContainer(profileImpl);
			AgentController agentController=agentContainer.createNewAgent("Agent4",
			"Agents.AgentPaperExample", new Object[]{Plan4, "Agent4"});
			agentController.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
 
