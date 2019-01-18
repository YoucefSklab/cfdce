package com.cfdce.Resolver;

import java.util.ArrayList;
import java.util.Arrays;


public class Task {

	public String		task;
	public ArrayList	agentList			 = new ArrayList();
	public ArrayList	combPossibilities	 = new ArrayList();
	public ArrayList	tempCombPossibilities= new ArrayList();
	public ArrayList	exclusiveTasks		 = new ArrayList();
	public ArrayList	tasksExclusiveAgents = new ArrayList();



	public Task(String taskName) {
		this.task = taskName;
	}



	public ArrayList getPossibilitiesFromStr(int indice) {

		ArrayList possibilitesList = new ArrayList();
		String poss = (String) combPossibilities.get(indice);

		String[] arr = poss.split(",");

		// To ArrayList
		ArrayList l = new ArrayList(Arrays.asList(poss.split(",")));

		for( int i = 0; i < l.size(); i++ ) {
			int t = Integer.parseInt((String) l.get(i));
			possibilitesList.add(t);
		}

		return possibilitesList;
	}

}
