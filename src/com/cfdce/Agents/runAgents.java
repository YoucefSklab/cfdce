package com.cfdce.Agents;

import java.io.IOException;


public class runAgents {
	public static void main(String args[]){

	// Lancement de la platforme Jade : 
	/*	
		try {
		     Runtime runtime = Runtime.getRuntime();
	       // Process p2  = runtime.exec(new String[]{"cmd.exe","/c","start java jade.Boot -gui -container -host localhost"});
	        Process p2  = runtime.exec(new String[]{"cmd.exe","/c","start java jade.Boot -gui -host localhost"});
		      
	        //  p2.waitFor();
	 	} catch (IOException e) {
			e.printStackTrace();
		}
		
		*/
		// Lancement des agents : 
		
			for(int i=1; i<2; i++){
				
				newAgent("Agent"+i, i);
			
			} // fin de for
	
	} // fin de main
	
	
public static void newAgent(String agentName, int agNbr){
	
	try {
	     Runtime runtime = Runtime.getRuntime();
       //Process p2  = runtime.exec(new String[]{"cmd.exe","/c","start  java C:\\Users/Djo/workspace/Projet2/bin/test2/  jade.Boot Agent1:containerAgent1"});
       //Process p2  = runtime.exec(new String[]{"cmd.exe","/c","start  java jade.Boot -agents "+agentName+":Agents.AgentT("+agNbr+") -container -host localhost"});
       //Process p2  = runtime.exec(new String[]{"java -cp jade.jar jade.Boot -gui -agents mainAgent:Agents.mainAgent2 -container -host localhost"});
      // Process p2  = runtime.exec(new String[]{"java -cp /Users/sklab/git/ocfa/bin/jade.jar jade.Boot -gui"});
      
       String[] command = { "java", "-cp", "/Users/sklab/git/ocfa/bin/jade.jar", "jade.Boot", "-gui" };
       Process proc = new ProcessBuilder(command).start();
       
       
       
       //String[] command2 = { "java", "-cp", "/Users/sklab/git/ocfa/bin/jade.jar", "jade.Boot", "-gui",
    	//	   "-agents", "mainAgent1:Agents.Agent1;mainAgent2:Agents.Agent2", "-container", "-host", "localhost"};
       
       
       String[] command2 = { "java", "-cp", "/Users/sklab/git/ocfa/bin/jade.jar", "jade.Boot", "-gui",
    		   "-agents", "Agent1:Agents.HelloWorldAgent", "-container", "-host", "localhost"};
       //Process proc2 = new ProcessBuilder(command2).start();
       
       String[] command3 = { "java", "-cp", "/Users/sklab/git/ocfa/bin/jade.jar", "jade.Boot", "-gui",
    		   "-agents", "SellerAgent:Examples.BookSellerAgent;BuyerAgent:Examples.BookBuyerAgent;", "-container", "-host", "localhost"};
       //Process proc3 = new ProcessBuilder(command3).start();
       
       
       
       // Process proc = runtime.exec("ls /Users/sklab/Desktop");
      // Process proc1 = runtime.exec("mkdir /Users/sklab/git/ocfa/bin/test2");

       //  p2.waitFor();
	} catch (IOException e) {
		e.printStackTrace();
	}

	
}

}
