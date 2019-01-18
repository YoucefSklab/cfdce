package com.cfdce.Agents;

import java.io.IOException;


public class run {
	public static void main(String args[]) throws InterruptedException, IOException{
			//String agentsToRun = "";
			
			
			
			for(int i=1; i<5; i++){
				newAgent("Agent"+i, i);
				
				//agentsToRun+="Agent"+i+":Agents.Agent1("+i+");"; 
			} // fin de for
			
			//agentsToRun+="Agent"+2+":Agents.Agent1("+2+")";
			//System.out.println(agentsToRun);
			//Runtime runtime = Runtime.getRuntime();
		  //  Process p2  = runtime.exec(new String[]{"cmd.exe","/c","start  java jade.Boot -agents "+agentsToRun+" -container -host localhost"});
		  //  Process p2  = runtime.exec(new String[]{"cmd.exe","/c","start  java jade.Boot -host localhost -container -agents "+agentsToRun+"  "});
			    
	
	} // fin de main
	
	
public static void newAgent(String agentName, int agNbr) throws InterruptedException{
	
	try {
	     Runtime runtime = Runtime.getRuntime();
       Process p2  = runtime.exec(new String[]{"cmd.exe","/c","start  java jade.Boot -agents "+agentName+":Agents.Agent1("+agNbr+") -container -host localhost"});
	     
	   // Thread.sleep(2000);
 
	} catch (IOException e) {
		e.printStackTrace();
	}

	
}

}
