package com.cfdce.App;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;


public class changeExtraCost {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub

		ArrayList agentList = new ArrayList();

		agentList.add("Plan_Agent_1.txt");
		agentList.add("Plan_Agent_2.txt");
		agentList.add("Plan_Agent_3.txt");
		agentList.add("Plan_Agent_4.txt");
		agentList.add("Plan_Agent_5.txt");
		agentList.add("Plan_Agent_6.txt");
		agentList.add("Plan_Agent_7.txt");
		agentList.add("Plan_Agent_8.txt");
		agentList.add("Plan_Agent_9.txt");
		agentList.add("Plan_Agent_10.txt");
		//agentList.add("Plan_Agent_5.txt");
		
		

		for( int i = 1; i < 11; i++ ) {
			String fichier = "Plan_Agent_" + i + ".txt";
			System.out.println("The file: " + fichier);
			File f1 = new File("AgentsPlans/" + fichier);

			int taskNbr = 0;
			if (f1.exists()) {

				ArrayList taskList = new ArrayList();

				Scanner sc = new Scanner(f1);
				while (sc.hasNextLine()) {
					taskNbr++;
					String text = sc.nextLine();
					String N1 = "";
					String N2 = "";
					String costV = "";
					boolean edge1 = true;
					boolean cost = false;
					boolean escape = false;
					for( int i1 = 0; i1 < text.length(); i1++ ) {
						char c = text.charAt(i1);

						if (c == '/') {
							escape = true;
						}

						if (c == ',') {
							edge1 = false;
						}

						if (c == ';') {
							cost = true;
						}

						if ((c != ',') && (c != ';') && (c != '/'))
							if (edge1) {
								N1 += c;
							}
							else if (!cost) {
								N2 += c;
							}
						if ((c != ',') && (c != ';') && (c != '/'))
							if (cost) {
								costV += c;
							}

					}

					if (!escape) {
					//	if (!N1.equals("start")) {
						int b1 = ThreadLocalRandom.current().nextInt(20, 30);
						int b2 = ThreadLocalRandom.current().nextInt(32, 60);
						String tasksLine = N1 + "," + N2 + ";" + ThreadLocalRandom.current().nextInt(b1, b2);
							taskList.add(tasksLine);
							System.out.println("The tasks string line is: "+tasksLine);
					//	}
					//	else {
					//		taskList.add(N1 + "," + N2);
					//	}

					}
					else {
						taskList.add("/" + N1 + "," + N2 + ";" + costV);
					}

				}

				PrintWriter f = null;
				try {
					f = new PrintWriter(
					        new FileWriter("AgentsPlans/" + fichier));
				} catch (IOException e1) {
					e1.printStackTrace();
				}

				for( int j = 0; j < taskList.size(); j++ ) {
					String task = (String) taskList.get(j);
					f.println(task);
				}
				
				
				
				/*
				if (taskNbr != 0) {
					String task = (String) taskList.get(taskList.size() - 1);
					String task2 = "";

					for( int i1 = 0; i1 < task.length(); i1++ ) {
						char c = task.charAt(i1);

						if (c == ';') {
							break;
						}
						{
							task2 += c;
						}

					}
					f.println(task2);
				}
				*/

				f.close();

			}

		}

	}

}
