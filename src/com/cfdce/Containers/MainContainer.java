package com.cfdce.Containers;

import java.io.IOException;


public class MainContainer {
	public static void main(String args[]){

		// Lancement de la platforme Jade : 
		
		try {
		     Runtime runtime = Runtime.getRuntime();
	       // Process p2  = runtime.exec(new String[]{"cmd.exe","/c","start java jade.Boot -gui -container -host localhost"});
	        Process p2  = runtime.exec(new String[]{"cmd.exe","/c","start java jade.Boot -gui -host localhost"});
		      
	        //  p2.waitFor();
	 	} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		// Lancement des agents : 
		try {
		     Runtime runtime = Runtime.getRuntime();
	        //Process p2  = runtime.exec(new String[]{"cmd.exe","/c","start  java C:\\Users/Djo/workspace/Projet2/bin/test2/  jade.Boot Agent1:containerAgent1"});
	        
	        Process p2  = runtime.exec(new String[]{"cmd.exe","/c","start  java C:\\Users/Djo/workspace/Projet2/bin/test2/containerAgent1"});
		    
	      //  p2.waitFor();
	 	} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	} // fin de main

}
