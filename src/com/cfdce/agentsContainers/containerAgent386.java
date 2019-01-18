package com.cfdce.agentsContainers;
import java.io.FileNotFoundException;

import org.graphstream.graph.Graph;

import com.cfdce.Plan.planSet;

import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
public class containerAgent386 {
	public static void main(String[] args )throws FileNotFoundException {
	planSet pSet = new planSet();
	Graph PlanA = pSet.getPlanByNbr(386);
		try {
			Runtime runtime=Runtime.instance();
			ProfileImpl profileImpl=new ProfileImpl(false);
			profileImpl.setParameter(ProfileImpl.MAIN_HOST,"localhost");
			AgentContainer agentContainer=runtime.createAgentContainer(profileImpl);
			AgentController agentController=agentContainer.createNewAgent("Agent386",
			"Agents.Agent1", new Object[]{PlanA, "Agent386"});
			agentController.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
 
