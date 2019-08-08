package com.cfdce.Resolver;

import java.io.FileNotFoundException;
import java.io.IOException;

public class SelectPlans {

	public static MethodesCollection	MethColl			= new MethodesCollection();
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		
		int nbrPlan = 1;
		for(int i=1; i<900; i++){
			AgentModel agent = new AgentModel(MethColl.getOriginalPlanByNbr(i,i));
			int nbrTasks = agent.plan.getEdgeCount();
			
			
			if(nbrTasks == 7){
				MethColl.savePlan(i, nbrPlan);
				System.out.println(" --> The agent: "+agent.plan.getId()+" has : "+nbrTasks +"   tasks! it is saved as: Agent"+nbrPlan);
				nbrPlan++;
			}
			
		}

	}

}
