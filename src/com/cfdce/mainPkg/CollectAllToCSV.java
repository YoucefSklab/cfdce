package com.cfdce.mainPkg;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class CollectAllToCSV {

	public static ArrayList listAgent = new ArrayList();
	public static ArrayList poucentageList = new ArrayList();

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub

		System.out.println(
				"Hello gents, this is the main program to collect experimental evaluation results from files !!");

		poucentageList.add(100);

		// set 5, 6

		int aNbr = 2;
		int GlobalStep = 1;
		int maxGlobal = 247;
		int totalFiles = 1;
		int pourcentage = 100;
		String agentTour = "_" + aNbr + "_ag";
		String complementPath = "";

		complementPath = aNbr + "_Agents/";

		String prefix = "CFDE";
		// String prefix = "BAA";

		String[] prefTab = { "CFDE", "BAA" };

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

		while (aNbr < 3) {
			aNbr++;
			for (int j = 0; j < prefTab.length; j++) {

				// String reasult = "";

				prefix = prefTab[j];

				PrintWriter f = null;
				try {
					f = new PrintWriter(
							new FileWriter("AllResults/" + prefix + "_All_Agents_" + aNbr + "_CollectedResults.txt"));
				} catch (IOException e1) {
					e1.printStackTrace();
				}

				int[] agentsTab = new int[aNbr];

				for (int k = 0; k < aNbr; k++) {
					agentsTab[k] = (int) listAgent.get(k);
				}

				File[] fileTab = new File[aNbr];
				Scanner[] scannerTab = new Scanner[aNbr];

				for (int k = 0; k < aNbr; k++) {
					fileTab[k] = new File("SimReasults/"+aNbr+"_"+ prefix + "_" + aNbr + "_ag_Agent_" + agentsTab[k]
							+ "_CollectedResultsAgentSet.txt");
				}

				for (int k = 0; k < aNbr; k++) {
					scannerTab[k] = new Scanner(fileTab[k]);
				}

				int step = 0;

				
				while (scannerTab[0].hasNextLine()) {
					step++;
					System.out.println(" Next step " + step);

					String line = "";
					boolean filed = true;

					for (int k = 0; k < aNbr; k++) {

						if (scannerTab[k].hasNextLine()) {
							line += scannerTab[k].nextLine() + ",   ,";
						} else {
							filed = false;
						}

						// line = line.substring(0, (line.length() - 1));
						//line += line + ;
					}

					if (filed) {
						f.println(line);
					}

				}

				f.close();
			}
		}
		System.out.println("End of the program !!");
	}

}
