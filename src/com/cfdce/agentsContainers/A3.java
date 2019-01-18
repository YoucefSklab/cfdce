package com.cfdce.agentsContainers;
import java.io.FileNotFoundException;

import org.graphstream.graph.Graph;

import com.cfdce.Plan.planSet;

import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
public class A3 {
	public static void main(String[] args )throws FileNotFoundException {
	planSet pSet = new planSet();
	Graph Plan3 = pSet.getPlanByNbr(3);
		try {
			Runtime runtime=Runtime.instance();
			ProfileImpl profileImpl=new ProfileImpl(false);
			profileImpl.setParameter(ProfileImpl.MAIN_HOST,"localhost");
			AgentContainer agentContainer=runtime.createAgentContainer(profileImpl);
			AgentController agentController=agentContainer.createNewAgent("Agent3",
			"Agents.AgentPaperExample", new Object[]{Plan3, "Agent3"});
			agentController.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
 
