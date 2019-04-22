package com.cfdce.mainPkg;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class CollectResults {

	public static ArrayList listAgent = new ArrayList();
	public static ArrayList poucentageList = new ArrayList();

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub

		System.out.println(
				"Hello gents, this is the main program to collect experimental evaluation results from files !!");

		poucentageList.add(100);

		// set 5, 6

		int aNbr =7;
		int GlobalStep = 1;
		int maxGlobal = 61;
		int totalFiles = 1;
		int pourcentage = 100;
		String agentTour = "_"+aNbr+"_ag";
		//String prefix = "CFDE";
		String prefix = "BAA";
		
		
		String reasult = "";

		listAgent.clear();

		listAgent.add(1);
		listAgent.add(2);
		listAgent.add(3);
		listAgent.add(4);
		listAgent.add(5);
		listAgent.add(6);
		listAgent.add(7);
		listAgent.add(8);
		listAgent.add(9);
		listAgent.add(10);
		
		for (int k = 0; k < aNbr; k++) {
			int ag = (int) listAgent.get(k);
			reasult = "";
			GlobalStep = 0;
			totalFiles = 1;
			
			PrintWriter f = null;
			try {
				f = new PrintWriter(new FileWriter("SimReasults/"+aNbr+"_"+ prefix +agentTour+"_Agent_" + ag
						+ "_CollectedResultsAgentSet.txt"));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			//f.println(" ");
			//f.println("Agent, ref Cost (First), Final Cost, Indice");
			
			//reasult += " Agent Set: "+listAgent.toString()+"\n";
			//reasult += " Agent's Reasult: Agent"+ag+"\n";
			//reasult += " Poucentage : " + pourcentage+"\n";
			//reasult += " ------------------------------------"+"\n";
			
			//reasult += "Step, Agent, ref Cost (First), Final Cost, Indice"+"\n";
			
			//f.println(" Agent Set:" + listAgent.toString());
			//f.println(" Agent's Reasult: Agent"+ag);
			//f.println(" Poucentage :  " + pourcentage);
			//f.println(" --------------------------");

			while (GlobalStep <= maxGlobal) {

				System.out.println(
						"-------------------------------------------------------------------------------------------------");
				System.out.println(
						"-------------------------------------------------------------------------------------------------");
				System.out.println("---> Agent is " + ag + " and step is "+GlobalStep);
				System.out.println(
						"-------------------------------------------------------------------------------------------------");
				System.out.println(
						"-------------------------------------------------------------------------------------------------");
				

				int fileTest = 30;
				boolean filed = false;
				while (fileTest != 0) {
					fileTest--;

					File f1 = new File("logs/"+aNbr+"_"+ prefix + "_" + GlobalStep + "_Agent" + ag + "_" + fileTest
							+ "_discussionsProbability.txt");
					if (f1.exists()) {
						System.out.println(totalFiles + " -> " + GlobalStep + "_Agent" + ag + "_" + fileTest
								+ "_discussionsProbability.txt");
						Scanner sc = new Scanner(f1);
						if (sc.hasNextLine()) {
							String text = sc.nextLine();
							boolean first = false;
							boolean second = false;
							boolean thired = false;
							boolean forth = false;
							
							String value = "";
							float value1 = 0;
							float value2 = 0;
							float value3 = 0;
							System.out.println("Text  is "+text);
							for(int y=0; y<text.length(); y++) {
								System.out.println("value is "+value);
								
								if(text.charAt(y) == ',') {
									
									if(first && !second) { 
										second = true;
										System.out.println("value is 3:"+value);
										value2 = Float.parseFloat(value);
										value = "";
									}
									
									if(!first) { 
										first = true; 
										System.out.println("value is 2:"+value);
										value1 = Float.parseFloat(value);
										value = "";
									}
									
									
								}else {
									value+=text.charAt(y);
									//System.out.println("value is 3: "+value);
								}
								
							}
							
							value3 = Float.parseFloat(value);
							
							float eff = ( ( (float) (value1-value2) ) / value1 );
							
							if(eff > 0) {
								value3 = 1;
							}else {
								value3 = 0;
							}
 							
							reasult += GlobalStep+",Agent" + ag + "," + text+ ","+eff+","+value3+"\n";
							
							filed = true;
						
						
						}
						totalFiles++;
						break;
					}
				}
				GlobalStep++;
			}
			f.println(reasult);
			f.close();
		}
		System.out.println("End of the program !!");
	}

}
