package com.cfdce.mainPkg;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class CollectReasultsNew {

	public static ArrayList listAgent = new ArrayList();
	public static ArrayList poucentageList = new ArrayList();

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub

		System.out.println(
				"Hello gents, this is the main program to collect experimental evaluation results from files !!");

		poucentageList.add(100);

		// set 5, 6

	
		int GlobalStep = 1;
		int totalFiles = 1;
		int pourcentage = 100;
		// String prefix = "CFDE_4_ag";
		String prefix = "BAA_4_ag";
		
		String reasult = "";

		listAgent.clear();

		listAgent.add(1);
		listAgent.add(2);
		listAgent.add(3);
		listAgent.add(4);
		
		for (int k = 0; k < listAgent.size(); k++) {
			int ag = (int) listAgent.get(k);
			reasult = "";
			GlobalStep = 1;
			totalFiles = 1;
			
			PrintWriter f = null;
			try {
				f = new PrintWriter(new FileWriter("SimReasults/" + prefix + "_Agent_" + ag
						+ "_CollectedResultsAgentSet.txt"));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			f.println(" ");
			//f.println("Agent, ref Cost (First), Final Cost, Indice");
			
			reasult += " Agent Set: "+listAgent.toString()+"\n";
			reasult += " Agent's Reasult: Agent"+ag+"\n";
			reasult += " Poucentage : " + pourcentage+"\n";
			reasult += " --------------------------"+"\n";
			
			reasult += "Step, Agent, ref Cost (First), Final Cost, Indice"+"\n";
			

			//f.println(" Agent Set:" + listAgent.toString());
			//f.println(" Agent's Reasult: Agent"+ag);
			//f.println(" Poucentage :  " + pourcentage);
			//f.println(" --------------------------");

			while (GlobalStep <= 174) {

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

				while (fileTest != 0) {
					fileTest--;

					File f1 = new File("logs/"+ prefix + "_" + GlobalStep + "_Agent" + ag + "_" + fileTest
							+ "_discussionsProbability.txt");

					if (f1.exists()) {
						System.out.println(totalFiles + " -> " + GlobalStep + "_Agent" + ag + "_" + fileTest
								+ "_discussionsProbability.txt");
						Scanner sc = new Scanner(f1);
						if (sc.hasNextLine()) {
							String text = sc.nextLine();
							//f.println("Agent" + ag + "," + text);
							reasult += GlobalStep+",Agent" + ag + "," + text+"\n";
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
