package com.cfdce.agentsContainers;
import java.io.FileNotFoundException;

import org.graphstream.graph.Graph;

import com.cfdce.Plan.planSet;

import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
public class A2 {
	public static void main(String[] args )throws FileNotFoundException {
	planSet pSet = new planSet();
	Graph Plan2 = pSet.getPlanByNbr(2);
		try {
			Runtime runtime=Runtime.instance();
			ProfileImpl profileImpl=new ProfileImpl(false);
			profileImpl.setParameter(ProfileImpl.MAIN_HOST,"localhost");
			AgentContainer agentContainer=runtime.createAgentContainer(profileImpl);
			AgentController agentController=agentContainer.createNewAgent("Agent2",
			"Agents.AgentPaperExample", new Object[]{Plan2, "Agent2"});
			agentController.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
 
