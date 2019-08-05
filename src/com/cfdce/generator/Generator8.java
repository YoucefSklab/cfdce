package com.cfdce.generator;


import java.awt.Toolkit;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.DefaultComboBoxModel;

import org.graphstream.algorithm.Dijkstra;
import org.graphstream.algorithm.generator.DorogovtsevMendesGenerator;
import org.graphstream.algorithm.generator.GridGenerator;
import org.graphstream.algorithm.generator.RandomEuclideanGenerator;
import org.graphstream.algorithm.generator.RandomGenerator;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.Path;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.view.Viewer;

public class Generator8 {

	public static int agentNbr = 100; // le nombre total d'agents 

	
	
	public static int minStartTasks = 0; // La tache minimale comme d�but
	public static int maxStartTasks = 4; // la tache maximale comme d�but
	
	
	public static int minEndTasks = 7; // la tache minimale comme fin
	public static int maxEndTasks = 11; // la tache maximale comme fin
	
	public static boolean comboBox = false;
	public static boolean plan = true;
	public static boolean cost = true;
	public static boolean extraCost = false;
	public static boolean casePlan = false;
	public static boolean agentsContainers = false;
	
	
	public static int startTasks = 0; // la tache de d�but
	public static int endTasks = 0; // la tache de fin
	
	public static int maxBifurcation = 15; // le maximum de bifurcation dans un plan
	public static int minBifurcation = 3; // le minimum de bifurcation dans un plan
	
	public static int alternativeNbr = 0; // le nombre d'alternatives � mettre dans le plan
	
	public static ArrayList alternativeList = new ArrayList(); // la liste des taches sur lesquelles faire une bifurcation 
	

	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		//System.out.println("Bonjour, ceci est un g�n�rateur de Plans : ");

	
		
		

		//-----------------------------------------------------------------------------------------------
		// G�n�ration d'un Plan :
		//----------------------
	    
		 // G�n�rer un graph  
		 Graph gra = new SingleGraph("grid");
		 
		 int sourceCounter = 0;
		 int destinationCounter = 60;
		 
		 int indice = 1;
		 ArrayList tasksOneShot= new ArrayList(); 
		 ArrayList tasksTwoShot= new ArrayList(); 
		 ArrayList List = new ArrayList();

		 ArrayList n1 = new ArrayList(); 
		 ArrayList n2 = new ArrayList();
		 ArrayList n3 = new ArrayList();
		 ArrayList n4 = new ArrayList();
		 ArrayList n5 = new ArrayList();
		 ArrayList n6 = new ArrayList();
		 ArrayList n7 = new ArrayList();
		 ArrayList n8 = new ArrayList();
		 ArrayList n9 = new ArrayList();
		 
	/*
		 n1.add("A"); 
		 
		 n2.add("B"); n2.add("C"); n2.add("D"); 
		 		 
		 n3.add("E"); n3.add("F"); n3.add("G"); n3.add("H"); 
		 
		 n4.add("I"); n4.add("J"); n4.add("K"); n4.add("L"); 
		 
		 n5.add("M"); n5.add("N"); n5.add("O"); n5.add("P");
		 
		 n6.add("Q"); n6.add("R"); n6.add("S"); n6.add("T"); 
		 
		 n7.add("U"); n7.add("V"); n7.add("W"); 
		 
		 n8.add("X"); n8.add("Y"); n8.add("Z");
	
	 */
		 
	
		 
		 
		 
		 n1.add("A1"); n1.add("A2"); n1.add("A3"); n1.add("A4"); n1.add("A5"); n1.add("A6"); // n1.add("A7"); n1.add("A8");  n1.add("A9"); n1.add("A10"); n1.add("A11"); n1.add("A12"); n1.add("A13"); n1.add("A14");n1.add("A15");  
		 
		 n2.add("B"); n2.add("C"); n2.add("D");  n2.add("E"); //  n2.add("F");      n2.add("B1"); n2.add("C1"); n2.add("D1");  n3.add("Q1");
		 
		  n3.add("G"); n3.add("H"); n3.add("I");  n3.add("J"); // n3.add("K");     n3.add("N1"); n3.add("O1"); n3.add("P1");  n3.add("U1");
		 
		 n4.add("L"); n4.add("M"); n4.add("N");  n4.add("O"); // n4.add("P");       n4.add("H1"); n4.add("I1"); n4.add("J1");  n4.add("R1");  
		  
		 n5.add("Q"); n5.add("R"); n5.add("S");  n5.add("T"); // n5.add("U");       n5.add("V1");  n5.add("W1");  n5.add("X1");  n5.add("Y1"); 
		 
		 n6.add("V"); n6.add("W"); n6.add("X");  n6.add("Y"); // n6.add("Z");   
		 
		 n7.add("E1"); n7.add("F1"); n7.add("G1");  n7.add("S1"); //              n7.add("E2"); n7.add("F2"); n7.add("G2"); n7.add("S2");
		 
		 n8.add("K1"); n8.add("L1"); n8.add("M1");  n8.add("T1"); //             n8.add("K2"); n8.add("L2"); n8.add("M2");  n8.add("T2"); 
		 
		 n9.add("Z1"); n9.add("Z2"); n9.add("Z3"); n9.add("Z4"); n9.add("Z5"); n9.add("Z6");
		 
		 
	


		 
		
		 
		 
		 // Generer le plan global
		 //-----------------------------------------------------------------------------------------
		 //-----------------------------------------------------------------------------------------
		 
		
		
		 gra.setStrict(false); 
		 gra.setAutoCreate( true ); 
		// gra.addAttribute("ui.stylesheet", "url('file:config/Modele1.css')"); 
		 gra.addAttribute("ui.quality"); 
		 gra.addAttribute("ui.antialias"); 
		 
		
		 
		 gra.display();
		
			for(int i=0; i<n1.size(); i++){
				String node1 = (String) n1.get(i);
				for(int j=0; j<n2.size(); j++){
					//if(indice>=0){
						String node2 = (String) n2.get(j);
						gra.addEdge(node1+node2, node1, node2, true );
						tasksOneShot.add(node1+node2);
						
					//}
					//indice = indice * (-1);
				}
			}
			indice = indice * (-1);
			for(int i=0; i<n2.size(); i++){
				String node1 = (String) n2.get(i);
				for(int j=0; j<n3.size(); j++){
				//	if(indice>=0){
						String node2 = (String) n3.get(j);
						gra.addEdge(node1+node2, node1, node2, true );
						tasksOneShot.add(node1+node2);
						
				//	}
					indice = indice * (-1);
				}
			}
		//	indice = indice * (-1);
			for(int i=0; i<n3.size(); i++){
				String node1 = (String) n3.get(i);
				for(int j=0; j<n4.size(); j++){
				//	if(indice>=0){
						String node2 = (String) n4.get(j);
						gra.addEdge(node1+node2, node1, node2, true );
						tasksOneShot.add(node1+node2);
						
				//	}
					indice = indice * (-1);
				}
			}
			indice = indice * (-1);
			for(int i=0; i<n4.size(); i++){
				String node1 = (String) n4.get(i);
				for(int j=0; j<n5.size(); j++){
				//	if(indice>=0){
						String node2 = (String) n5.get(j);
						gra.addEdge(node1+node2, node1, node2, true );
						tasksOneShot.add(node1+node2);
						
			//		}
					indice = indice * (-1);
					
				}
			}
			indice = indice * (-1);
			for(int i=0; i<n5.size(); i++){
				String node1 = (String) n5.get(i);
				for(int j=0; j<n6.size(); j++){
				//	if(indice>=0){
						String node2 = (String) n6.get(j);
						gra.addEdge(node1+node2, node1, node2, true );
						tasksOneShot.add(node1+node2);
						
				//	}
					indice = indice * (-1);
				}
			}
			indice = indice * (-1);
			
			//indice = 1;
			for(int i=0; i<n6.size(); i++){
				String node1 = (String) n6.get(i);
				for(int j=0; j<n7.size(); j++){
				//	if(indice>=0){
						String node2 = (String) n7.get(j);
						gra.addEdge(node1+node2, node1, node2, true );
						tasksOneShot.add(node1+node2);
						
				//	}
					indice = indice * (-1);
				}
			}
		
		//	indice = indice * (-1);
			
			
			for(int i=0; i<n7.size(); i++){
				String node1 = (String) n7.get(i);
				for(int j=0; j<n8.size(); j++){
				//	if(indice>=0){
						String node2 = (String) n8.get(j);
						gra.addEdge(node1+node2, node1, node2, true );
						tasksOneShot.add(node1+node2);
					
				//	}
					indice = indice * (-1);
				}
			}
			
			indice = indice * (-1);
			for(int i=0; i<n8.size(); i++){
				String node1 = (String) n8.get(i);
				for(int j=0; j<n9.size(); j++){
				//	if(indice>=0){
						String node2 = (String) n9.get(j);
						gra.addEdge(node1+node2, node1, node2, true );
						tasksOneShot.add(node1+node2);
						
				//	}
					indice = indice * (-1);
				}
			}
			
			//**************
			/*
			for(int i=0; i<n1.size(); i++){
				String node1 = (String) n1.get(i);
				for(int j=0; j<n3.size(); j++){
					if(indice>=0){
			
						String node2 = (String) n3.get(j);
						gra.addEdge(node1+node2, node1, node2, true );
						tasksTwoShot.add(node1+node2);
						
					}
					indice = indice * (-1);
				}
			}
			
			for(int i=0; i<n3.size(); i++){
				String node1 = (String) n3.get(i);
				for(int j=0; j<n5.size(); j++){
					
					if(indice>=0){					
						String node2 = (String) n5.get(j);
						gra.addEdge(node1+node2, node1, node2, true );
						tasksTwoShot.add(node1+node2);
						
					}
					indice = indice * (-1);
				}
			}
			
			for(int i=0; i<n5.size(); i++){
				String node1 = (String) n5.get(i);
				for(int j=0; j<n7.size(); j++){
					
					if(indice>=0){
						String node2 = (String) n7.get(j);
						gra.addEdge(node1+node2, node1, node2, true );
						tasksTwoShot.add(node1+node2);
						
					}
					indice = indice * (-1);
				}
			}
			*/
			/*
			for(int i=0; i<n7.size(); i++){
				String node1 = (String) n7.get(i);
				for(int j=0; j<n9.size(); j++){
					
					if(indice>=0){
						String node2 = (String) n9.get(j);
						gra.addEdge(node1+node2, node1, node2, true );
						tasksTwoShot.add(node1+node2);
						
					}
					indice = indice * (-1);
				}
			}
			*/
			//********
			/*
			
			for(int i=0; i<n2.size(); i++){
				String node1 = (String) n2.get(i);
				for(int j=0; j<n4.size(); j++){
					
					if(indice>=0){
						String node2 = (String) n4.get(j);
						gra.addEdge(node1+node2, node1, node2, true );
						tasksTwoShot.add(node1+node2);
						
					}
					indice = indice * (-1);
				}
			}
			
			for(int i=0; i<n4.size(); i++){
				String node1 = (String) n4.get(i);
				for(int j=0; j<n6.size(); j++){
					
					if(indice>=0){
						String node2 = (String) n6.get(j);
						gra.addEdge(node1+node2, node1, node2, true );
						tasksTwoShot.add(node1+node2);
						
					}
					indice = indice * (-1);
				}
			}
			
			for(int i=0; i<n6.size(); i++){
				String node1 = (String) n6.get(i);
				for(int j=0; j<n8.size(); j++){
					
					if(indice>=0){
						String node2 = (String) n8.get(j);
						gra.addEdge(node1+node2, node1, node2, true );
						tasksTwoShot.add(node1+node2);
						
					}
					indice = indice * (-1);
				}
			}
			
			*/
		
			
			
		 
			 System.out.println("-------------------- Ici");
		 //-----------------------------------------------------------------------------------------
		 //-----------------------------------------------------------------------------------------
		 
			gra.setStrict(false); 
			gra.setAutoCreate( true ); 
		//	g.addAttribute("ui.stylesheet", "url('file:config/Modele1.css')"); 
			gra.addAttribute("ui.quality"); 
			gra.addAttribute("ui.antialias"); 
			
			
		 int j=0;
		 for (Node node : gra){
			node.setAttribute("ui.label", node.getId());
			j++;
		 }	    

						
		 for(Edge e:gra.getEachEdge()) {
			 e.setAttribute("ui.label", e.getSourceNode().getLabel("ui.label")+""+e.getTargetNode().getLabel("ui.label"));
			 e.addAttribute("extraCost", ThreadLocalRandom.current().nextInt(100, 150));
		}
						
		
		 Graph g3 = new SingleGraph("grid3");
		 g3.setStrict(false);
		 g3.setAutoCreate(true); // cr�ation automatique des noeuds.
		 
	
				
		 for(Edge e:gra.getEachEdge()) {
				 String label = e.getLabel("ui.label").toString();
				 String st1 = e.getSourceNode().getLabel("ui.label").toString();
			     String st2 = e.getTargetNode().getLabel("ui.label").toString();
				 g3.addEdge(label, e.getSourceNode().getLabel("ui.label").toString(), e.getTargetNode().getLabel("ui.label").toString(), true  );
				 Edge ed = g3.getEdge(label);
				 ed.addAttribute("extraCost", e.getAttribute("extraCost"));
		 }
	  	
	
		
		 for(Node n:g3) {
			n.setAttribute("ui.label", n.getId());
		 }
		
		 for(Edge e:g3.getEachEdge()) {
			 e.setAttribute("ui.label", e.getId());
			 e.setAttribute("lenth", 1);
		 }
					
			
		 

		 g3.addAttribute("ui.quality");
		 g3.addAttribute("ui.antialias");
		 g3.display();
		 

		 String sourceNode = null;
			for(Node n:g3) {
					if(n.getInDegree()==0){
					sourceNode = n.getId();
				}
		  }
				
		  ArrayList allPaths = new ArrayList();
			
		  Dijkstra dijkstra = new Dijkstra(Dijkstra.Element.EDGE, null, "lenth");
//--------------------------------------------------------------
//--------------------------------------------------------------
//--------------------------------------------------------------
//--------------------------------------------------------------
		  
		  	agentNbr= 900;
			  	
			int planNbr =1;
			//planNbr =agentNbr;
			
			 ArrayList T = new ArrayList();
			 T.add("A1"); T.add("A2"); T.add("A3"); T.add("A4"); T.add("A5"); T.add("A6"); // T.add("A7"); T.add("A8"); T.add("A9"); T.add("A10"); 
			// T.add("A11"); T.add("A12"); T.add("A13"); T.add("A14"); T.add("A15"); 
			
			 T.add("B"); T.add("C"); T.add("D");  T.add("E"); // T.add("F");
			 //T.add("B1"); T.add("C1"); T.add("D1"); T.add("Q1");
			 T.add("G"); T.add("H"); T.add("I"); T.add("J"); // T.add("K");
			// T.add("N1"); T.add("O1"); T.add("P1"); T.add("U1");
			 T.add("L"); T.add("M"); T.add("N"); T.add("O"); //T.add("P");
			 //T.add("H1"); T.add("I1"); T.add("J1"); T.add("R1");
			 T.add("Q"); T.add("R"); T.add("S");  T.add("T"); //T.add("U");
			 //T.add("V1"); T.add("W1"); T.add("X1"); T.add("Y1");
			 T.add("V"); T.add("W"); T.add("X");  T.add("Y");// T.add("Z");
			 T.add("E1"); T.add("F1"); T.add("G1");  T.add("S1");
			 // T.add("E2"); T.add("F2"); T.add("G2"); T.add("S2");
			 T.add("K1"); T.add("L1"); T.add("M1");  T.add("T1");
			 //T.add("K2"); T.add("L2"); T.add("M2"); T.add("T2");
			 
			 T.add("Z1"); T.add("Z2"); T.add("Z3"); T.add("Z4"); T.add("Z5"); T.add("Z6");
			
			if(plan) // si g�n�ration de plans
			while(planNbr<(agentNbr+1)) {
				
				// initialisation
				//---------s------
				
				// System.out.println("La g�n�ration du Plan n :"+planNbr);
				
				//-----*-*-********************************************
				
				dijkstra = new Dijkstra(Dijkstra.Element.EDGE, null, "lenth");
				
				//startTasks = ThreadLocalRandom.current().nextInt(minStartTasks, maxStartTasks + 1);
				
				if(sourceCounter>15){
					sourceCounter = 0;
					}
				startTasks = sourceCounter;
				sourceCounter++;
				
			 //	System.out.println("La tache de d�but est : "+startTasks);
				
				
				
				endTasks = ThreadLocalRandom.current().nextInt(50, 70);
			 //	System.out.println("La tache de fin est : "+endTasks);
				
				if(destinationCounter>39){
					destinationCounter = 30;
					}
				endTasks = destinationCounter;
				destinationCounter++;	
				
				
				System.out.println("The end is: "+(String) T.get(endTasks)+ "       at: "+destinationCounter);
				
			 //	 System.out.println("Test");
				 dijkstra.init(g3);
			 //	 dijkstra.

				 	String distinationNode = null;
				 	String beginingNode = null;
				 	for(int y=0; y<T.size(); y++) {
						if(y == endTasks) distinationNode = (String) T.get(y);
						if(y == startTasks) beginingNode = (String) T.get(y);
					}
				 	
				 	System.out.println("Begin at: "+beginingNode);
				 	System.out.println("End at: "+distinationNode);
				 	System.out.println("T: "+T.size());
				 	//beginingNode = "A";
				 
				 dijkstra.setSource(g3.getNode(beginingNode));
				 dijkstra.compute();
				 
				 /*
				 // Print the lengths of the new shortest paths
				 for (Node node : g3)
				 	System.out.printf(" -> Print the lengths of the new shortest paths : %s->%s:%10.2f%n", dijkstra.getSource(), node,
							dijkstra.getPathLength(node));
				  */
					
				   // Print all the shortest paths between A and F
			//		System.out.println(" Print all the shortest paths between "+getTask(startTasks)+" and "+getTask(endTasks)+" ");
				 
		
					
					Iterator<Path> pathIterator = dijkstra.getAllPathsIterator(g3.getNode(distinationNode));
					
					Graph finalGraph = new SingleGraph("FinalGraph");
					finalGraph.clear();
					finalGraph.setStrict(false);
					finalGraph.setAutoCreate( true ); // cr�ation automatique des noeuds.
					finalGraph.addAttribute("ui.quality");
					finalGraph.addAttribute("ui.antialias");
					
					
					//
					
					//---------------------------------
					allPaths.clear();
					
					
					while (pathIterator.hasNext()){				
						allPaths.add(pathIterator.next());
					} // fin de while
					
			//		System.out.println("La nombre des paths est : "+allPaths.size());
				//-----*-*-********************************************
				
					//alternativeNbr = ThreadLocalRandom.current().nextInt(1, (allPaths.size()) + 1);
					//System.out.println("Le Nombre de bifurcation � consid�rer est : "+alternativeNbr);
				
					 System.out.println(" ->All paths = :"+allPaths.size());
						
					if(allPaths.size()>20){			
						alternativeList.clear();
						//alternativeNbr = ThreadLocalRandom.current().nextInt(2, allPaths.size()+1);
						alternativeNbr = ThreadLocalRandom.current().nextInt(5, ((allPaths.size() % 5 )+15));
						
						
						
						 System.out.println(" ->Le nombre d'alternatives � mettre dans le plan est :"+alternativeNbr +"         ---> De: "+allPaths.size());
						
						int i1=1;
						int i2=1;
						
						while(i1<=alternativeNbr){
							i2++;
							int altNbr =  ThreadLocalRandom.current().nextInt(1, allPaths.size());
							if(!alternativeList.contains(altNbr)) {
								alternativeList.add(altNbr);
								i1++;
							}
						//	if(i2>200)
						//		i1++;
								
						//	System.out.println("Le Plan est : 2 ");
						} // fin de while
						
						
						/*
						for(int yy=0; yy<alternativeNbr; yy++){
							alternativeList.add(yy);
						}
						*/
						
						//System.out.println("Le Plan est :3 ");
						for(int j1=0;  j1<alternativeList.size(); j1++){
							
							Path p = (Path) allPaths.get((Integer) alternativeList.get(j1));
							
							System.out.println(" Le path N :"+j1+" est : "+p.toString());
							
							for(Edge e:p.getEachEdge()) {
								 String label = e.getLabel("ui.label").toString();
								 String st1 = e.getSourceNode().getLabel("ui.label").toString();
							     String st2 = e.getTargetNode().getLabel("ui.label").toString();
							     finalGraph.addEdge(label, e.getSourceNode().getLabel("ui.label").toString(), e.getTargetNode().getLabel("ui.label").toString(), true  );
							     Edge ed = finalGraph.getEdge(label);
							     ed.addAttribute("extraCost", e.getAttribute("extraCost"));
							 } // fin de for
							
						} // fin de for
						
						
						for(Node n:finalGraph) {
							n.setAttribute("ui.label", n.getId());
						 }
						
						for(Edge e:finalGraph.getEachEdge()) {
							 e.setAttribute("ui.label", e.getId());
							 e.setAttribute("lenth", 1);
						 }
						
						
						System.out.println("  Before clean  ------> "+finalGraph.getEdgeCount());
						//---------------------------------------
						
						
						
						boolean clean = false;
						
						while(!clean){
							boolean nodeDeleted = false;
							String endN = null;
							String startN = null;
							int cmp = 0;	
							
							for(Node n:gra) {
								if(cmp == endTasks){
									endN = n.getId();
								}
								
								if(cmp == startTasks){
									startN = n.getId();
								}
								cmp++;
							}
							
							for(Node n:finalGraph) {
								
								String noId = n.getId().toString();
								
								if((n.getInDegree()==0) && (!noId.equals(startN))){
									//System.out.println("Tache : "+getTask(startTasks));
									finalGraph.removeNode(n.getId());
									nodeDeleted = true;
									break;
								}else								
								if((n.getOutDegree()==0) && (!noId.equals(endN)) ){
									finalGraph.removeNode(n);
									nodeDeleted = true;
									break;
								}
							}
							
							
							if(!nodeDeleted){
								clean = true;
							}
														
						} // fin de while
						
						if((finalGraph.getEdgeCount()>=10) && (finalGraph.getEdgeCount()<=20)){
						//if(finalGraph.getNodeCount()>=6){
							//finalGraph.display();
							
							System.out.println(" Yes Good, It is a plan -------------------------------------------------------------------------->: "+finalGraph.getEdgeCount());
							String agColor = getAgentColor("Agent "+planNbr);
							//-----------------------------------------------------------------
							//--------------------------------------------------------------------
							//*********************************************************************
							//----------------------------------------------------------------------
							//--------------------------------------------------------------------- 
							System.out.println(saveAsMethode(finalGraph, "Plan_"+planNbr, planNbr, "url('file:config/Modele1.css')", agColor));
							//(Graph g, String Name, int planNbr,String cssPath)
							planNbr++;
							
							
							 for(Edge e:finalGraph.getEachEdge()) {
								 
								 if(!List.contains(e.getId()))
									 	List.add(e.getId());
								 
								 if(!tasksTwoShot.contains(e.getId()))
									 if(!tasksOneShot.contains(e.getId()))
										 tasksOneShot.add(e.getId());
							 }
						}else{
							System.out.println("  Not enought edges "+finalGraph.getEdgeCount());
						}
						
						
						// Garbage collector
						System.gc();

						
						dijkstra.clear();  	
						
					} // fin de if
					else
					{
						System.out.println("Number of tasks too small");
					}
					/*
					System.out.println(" Passons � l'autre : ");
					Scanner sc = new Scanner(System.in);
					String reponse = "";
					reponse = sc.nextLine();
					*/
			} // fin de while	
			
			
			
			System.out.println(" Les Couts : ");
			// G�n�ration de couts des taches
			//-------------------------------
			
			if(cost)
			System.out.println(generateRandomTasksCost(gra));
	
				 
			if(cost)
			System.out.println(generateRandomTasksCostFromList(tasksOneShot));
		
			if(cost)
			System.out.println(generateRandomTasksCostFromList2(tasksTwoShot));
			
			
			System.out.println(List.toString());
			
			System.out.println(tasksOneShot.toString()+tasksTwoShot.toString());
		
			
			if(extraCost)
			for(int i=1; i<=1000; i++){
				System.out.println("//------------------------------------------");
				System.out.println("public static float extraCostAgent"+i+"(String actName){");
				//System.out.println(generateRandomExtraTasksCostFromList(tasksOneShot));
				System.out.println(generateRandomExtraTasksCostFromList2(i,tasksOneShot, tasksTwoShot));
				System.out.println("}");
				System.out.println("//------------------------------------------");
				
			}
			
	
			

			
			/*
			
			for(int i=1; i<=99; i++){
				System.out.println("//------------------------------------------");
				System.out.println("public float extraCostAgent"+i+"(String actName){");
				System.out.println(generateRandomExtraTasksCost(g3));
				System.out.println("}");
				System.out.println("//------------------------------------------");
				
			}
			*/
		

			if(extraCost)
			for(int i=1; i<=1000; i++){
				System.out.println("		if(agent.equals(\"Agent"+i+"\")) return extraCostAgent("+i+",actName); \n");
			}
		
			
			System.out.println("		");
			System.out.println("		");
			System.out.println("		");
			System.out.println("	appercuPlan1 ----------------	");
			System.out.println("		");
			System.out.println("		");
			
			
			if(casePlan)
			for(int i=1; i<=100; i++){
				System.out.println("case \"Plan "+i+"\" :");
				System.out.println("try { 	g2 = pSet.getPlanByNbr("+i+");	} catch (FileNotFoundException e1) { e1.printStackTrace(); }	");
				System.out.println("	break;");

			}
			
			
			
			
			
			System.out.println("		");
			System.out.println("		");
			System.out.println("		");
			
			
		if(comboBox)
			for(int i=1; i<=1000; i++){
			System.out.print("\""+i+"\",");
			//	System.out.print(i+",");
			

			}
			
			
			
			
			System.out.println("		");
			System.out.println("		");
			System.out.println("		");
			
			// no need.
			/*
			for(int i=1; i<=1000; i++){
				System.out.println("case  "+i+": ");
				System.out.println("	return constructPlan("+i+");");
			}
			*/
			
		
			
			
			
			
			
			
System.out.println("		");
System.out.println("		");
System.out.println("		");
System.out.println("	agentsContainers	");
			
			//--------------------------------
			if(agentsContainers)
			for(int i=1; i<=1000; i++){
				
				
				
				PrintWriter f = new PrintWriter(new FileWriter("containerAgent"+i+".java"));
				f.println("package agentsContainers;");
				f.println("import org.graphstream.graph.Graph;");
				f.println("import java.io.FileNotFoundException;");				
				f.println("import org.graphstream.graph.implementations.SingleGraph;");
				f.println("import org.graphstream.ui.view.Viewer;");
				f.println("import Plan.PlanManagment;");
				f.println("import Plan.planSet;");
				f.println("import jade.core.ProfileImpl;");
				f.println("import jade.core.Runtime;");
				f.println("import jade.wrapper.AgentContainer;");
				f.println("import jade.wrapper.AgentController;");
				f.println("public class containerAgent"+i+" {");
				f.println("	public static void main(String[] args )throws FileNotFoundException {");
				
					
				f.println("	planSet pSet = new planSet();");
				f.println("	Graph PlanA = pSet.getPlanByNbr("+i+");");
				
			
				
				f.println("		try {");
				f.println("			Runtime runtime=Runtime.instance();");
				f.println("			ProfileImpl profileImpl=new ProfileImpl(false);");
				f.println("			profileImpl.setParameter(ProfileImpl.MAIN_HOST,\"localhost\");");
				f.println("			AgentContainer agentContainer=runtime.createAgentContainer(profileImpl);");
				f.println("			AgentController agentController=agentContainer.createNewAgent(\"Agent"+i+"\",");
				f.println("			\"Agents.Agent1\", new Object[]{PlanA, \"Agent"+i+"\"});");
				f.println("			agentController.start();");
				f.println("		} catch (Exception e) {");
				f.println("			e.printStackTrace();");
				f.println("		}");
				f.println("	}");
				f.println("}");
				f.println(" ");
			
				
			
				f.close();
			

			}

			
			//------------------------------------------------
			
			
			


	

					
				// Pr�paration du plan de l'agent
	
					//planMgmt.constructAgentAPlan(PlanA);
				
				

			
			
			
			
//--------------------------------------------------------------
//--------------------------------------------------------------
//--------------------------------------------------------------
//--------------------------------------------------------------			
			
			
		//	afficherTache(100);
		//-----------------------------------------------------------------------------------------------
		
		
	}  // fin de main
//-------------------------------------------------------------------------------




	
//-----------------------------------------------------
public static String NewLine(int Nbr){
	String text ="";
	for(int i = 0; i<Nbr; i++)
		text+=("-");
	text+=("\n");
	return text;
} //fin de la m�thode
//-----------------------------------------------------




//**---------------------------------------------------------------------



public static String saveAsMethode(Graph g, String Name, int planNbr,String cssPath, String agColor){
	
	PrintWriter f = null;
	try {
		f = new PrintWriter(new FileWriter("Plans/Plan_Agent_"+planNbr+".txt"));
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	
	
	
	
	String methode = "";
	
	methode = "\n //------------------------------------------------------------------------------- \n ";
	methode = " //------------------------------------------------------------------------------- \n ";
	
	methode+="public Graph getPlan"+planNbr+"(String pName){ \n";
	
	methode+= "	Graph "+Name+" = new SingleGraph(\""+Name+"\"); \n";
	methode+="		"+Name+".setStrict(false); \n ";
	methode+="		"+Name+".setAutoCreate( true ); \n ";
	methode+="		"+Name+".addAttribute(\"ui.stylesheet\", \""+cssPath+"\"); \n";
	methode+="		"+Name+".addAttribute(\"ui.quality\"); \n";
	methode+="		"+Name+".addAttribute(\"ui.antialias\"); \n ";
	

	


	//methode+="		"+Name+".addEdge(\"start\", \"start\", \"XXXX\", true ); \n";

	for(Edge e:g.getEachEdge()) {
			
		 String edgeLabel = e.getId();
		 String n1 = e.getSourceNode().getId();
		 String n2 = e.getTargetNode().getId();
		 methode+="		"+Name+".addEdge(\""+edgeLabel+"\", \""+n1+"\", \""+n2+"\", true ); \n";
		 
		 f.println(n1+","+n2+";"+e.getAttribute("extraCost"));
			
	 }
	
	
	
	Node nnnn = g.getNode(0);
	
	
	for(Node n:g) {
		
		if(n.getInDegree()==0){
			nnnn = n;
		}
		
	 }
	
	
	methode+="		"+Name+".addEdge(\"start"+nnnn.getId()+"\", \"start\", \""+nnnn.getId()+"\", true ); \n";
	
	 f.println("start,"+nnnn.getId());
	
	methode+="		"+Name+".getNode(\"start\").setAttribute(\"ui.label\", \"start\"); \n"; 
	
	
	int t=0;
	for(Node n:g) {
		t++;
		methode+="		"+Name+".getNode(\""+n.getId()+"\").setAttribute(\"ui.label\", \""+n.getId()+"\"); \n"; 
		
	 }
	t=0;	
	
	for(Edge e:g.getEachEdge()) {
		t++;
			methode+="		"+Name+".getEdge(\""+e.getId()+"\").setAttribute(\"ui.label\", \""+e.getId()+"\"); \n"; 
			methode+="		"+Name+".getEdge(\""+e.getId()+"\").addAttribute(\"ui.class\", \""+agColor+"\"); \n"; 
			
	}
	
	
	methode+= "	return "+Name+"; \n";
	methode+="} // fin de getPlan"+planNbr+" \n";
	methode+="";
	
	methode += "\n // ------------------------------------------------------------------------------- \n ";
	methode += " // ------------------------------------------------------------------------------- \n ";
	
	
	f.close();
	
	methode = "Plan"+planNbr;
	return methode;
	
	
}

//***--------------------------------------------------------------------


public static String getAgentColor(String agentName){
	String Color= "White";
	
	
	
	if(agentName.equals("Agent 1")) return "Blue";
	
	if(agentName.equals("Agent 2")) return "Orange";
	
	if(agentName.equals("Agent 3")) return "Green";
	
	if(agentName.equals("Agent 4")) return "Red";
	
	if(agentName.equals("Agent 5")) return "Teal";
	
	if(agentName.equals("Agent 6")) return "Sangria";
	
	if(agentName.equals("Agent 7")) return "Ruby";
	
	if(agentName.equals("Agent 8")) return "Salmon";
	
	if(agentName.equals("Agent 9")) return "Purple";
	
	if(agentName.equals("Agent 10")) return "Plum";
	
	if(agentName.equals("Agent 11")) return "Pink";
	
	if(agentName.equals("Agent 12")) return "Pear";
	
	if(agentName.equals("Agent 13")) return "Orchid";
	
	if(agentName.equals("Agent 14")) return "Olive";
	
	if(agentName.equals("Agent 15")) return "Ocher";
	
	if(agentName.equals("Agent 16")) return "Magenta";
	
	if(agentName.equals("Agent 17")) return "Lime";
	
	if(agentName.equals("Agent 18")) return "Lavender";
	
	if(agentName.equals("Agent 19")) return "Jade";
	
	if(agentName.equals("Agent 20")) return "Cyan";
	
	if(agentName.equals("Agent 21")) return "Coral";
	
	if(agentName.equals("Agent 22")) return "Chocolate";
	
	if(agentName.equals("Agent 23")) return "Champagne";
	
	if(agentName.equals("Agent 24")) return "Blush";
	
	if(agentName.equals("Agent 25")) return "Bronze";
	
	
	return Color;
}

//-----------------------------------------------------


//------------------------------------------------------------------------------------------------
public static String generateRandomTasksCost(Graph g3){
	
	String costs = "";
	
	
	
	 for(Edge e:g3.getEachEdge()) {
		 int cout =  ThreadLocalRandom.current().nextInt(100, 150);
		 //costs+= "Action "+e.getId()+" = new Action(this.agent,\""+e.getId()+"\", "+cout+", extraActionCost.getExtraCostForAgent(agent,\""+e+"\")); actionCostsList.add("+e.getId()+"); \n  ";
		 costs+= "if(action.equals(\""+e+"\")) return "+cout+" ; \n  ";
	 }
	
		
	return costs;
} //fin de generateRandomTasksCost



public static String generateRandomTasksCostFromList(ArrayList List){
	
	String costs = "";
	
	
	
	 for(int i=0; i<List.size(); i++) {
		 String e = (String) List.get(i);
		 int cout =  ThreadLocalRandom.current().nextInt(100, 150);
		 cout = 70;
		//costs+= "Action "+e+" = new Action(this.agent,\""+e+"\", "+cout+", extraActionCost.getExtraCostForAgent(agent, \""+e+"\")); actionCostsList.add("+e+"); \n  ";
		costs+= "if(action.equals(\""+e+"\")) return "+cout+" ; \n  ";
		
	 }
	
		
	return costs;
} //fin de generateRandomTasksCost



public static String generateRandomTasksCostFromList2(ArrayList List2){
	
	String costs = "";
	
	
	
	 for(int i=0; i<List2.size(); i++) {
		 String e = (String) List2.get(i);
		 int cout =  ThreadLocalRandom.current().nextInt(130, 200);
		 cout = 70;
		 //costs+= "Action "+e+" = new Action(this.agent,\""+e+"\", "+cout+", extraActionCost.getExtraCostForAgent(agent, \""+e+"\")); actionCostsList.add("+e+"); \n  ";
		 costs+= "if(action.equals(\""+e+"\")) return "+cout+" ; \n  ";
	 }
	
		
	return costs;
} //fin de generateRandomTasksCost

//------------------------------------------------------------------------------------------------



//------------------------------------------------------------------------------------------------
public static String generateRandomExtraTasksCost(Graph g3){
	
	String costs = "";
	
	String actName = "ff";
	
	//if(actName.equals("")) return ; 
		
	
	 for(Edge e:g3.getEachEdge()) {
		 int cout =  ThreadLocalRandom.current().nextInt(20, 60);
		 costs+= "		 if(actName.equals(\""+e.getId()+"\")) return "+cout+" ; \n";
		 
		// costs+= "String "+e.getId()+" = (\""+e.getId()+"\", "+cout+"); actionCostsList.add("+e.getId()+"); \n  ";
	 }
	
	 costs+= " return 0; \n";
		
	return costs;
} //fin de generateRandomTasksCost


public static String generateRandomExtraTasksCostFromList(ArrayList list){
	
	String costs = "";
	
	String actName = "ff";
	
	//if(actName.equals("")) return ; 
		
	
	 for(int i=0; i<list.size(); i++) {
		 String e  = (String) list.get(i);
		 int cout =  ThreadLocalRandom.current().nextInt(30, 70);
		 costs+= "		 if(actName.equals(\""+e+"\")) return "+cout+" ; \n";
		 
		// costs+= "String "+e.getId()+" = (\""+e.getId()+"\", "+cout+"); actionCostsList.add("+e.getId()+"); \n  ";
	 }
	
	// costs+= " return 0; \n";
		
	return costs;
} //fin de generateRandomExtraTasksCostFromList




public static String generateRandomExtraTasksCostFromList2(int agNbr, ArrayList list1, ArrayList list2) throws IOException{
	
	String costs = "";
	
	PrintWriter f = new PrintWriter(new FileWriter("TasksExtraCost/TasksExtraCost_Agent_"+agNbr+".txt"));
	
	

		
	 for(int i=0; i<list1.size(); i++) {
		 String e  = (String) list1.get(i);
		 int cout =  ThreadLocalRandom.current().nextInt(30, 70);
		 costs+= "		 if(actName.equals(\""+e+"\")) return "+cout+" ; \n";
		 f.println(e+","+cout);
		// costs+= "String "+e.getId()+" = (\""+e.getId()+"\", "+cout+"); actionCostsList.add("+e.getId()+"); \n  ";
	 }
	
	
	 for(int i=0; i<list2.size(); i++) {
		 String e  = (String) list2.get(i);
		 int cout =  ThreadLocalRandom.current().nextInt(50,100);
		 costs+= "		 if(actName.equals(\""+e+"\")) return "+cout+" ; \n";
		 f.println(e+","+cout);
		// costs+= "String "+e.getId()+" = (\""+e.getId()+"\", "+cout+"); actionCostsList.add("+e.getId()+"); \n  ";
	 }
	
	 f.close();
	 
	 costs+= " return 0; \n";
	 costs="";
	return costs;
} //fin de generateRandomExtraTasksCostFromList

//------------------------------------------------------------------------------------------------


//-----------------------------------------------------


} // fin de la classe
