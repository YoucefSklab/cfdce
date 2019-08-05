package com.cfdce.generator;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

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

public class Generator4 {

	public static int agentNbr = 24; // le nombre total d'agents 

	
	
	public static int minStartTasks = 1; // La tache minimale comme d�but
	public static int maxStartTasks = 10; // la tache maximale comme d�but
	
	
	public static int minEndTasks = 20; // la tache minimale comme fin
	public static int maxEndTasks = 30; // la tache maximale comme fin
	
	
	
	public static int startTasks = 0; // la tache de d�but
	public static int endTasks = 0; // la tache de fin
	
	public static int maxBifurcation = 4; // le maximum de bifurcation dans un plan
	public static int minBifurcation = 1; // le minimum de bifurcation dans un plan
	
	public static int alternativeNbr = 0; // le nombre d'alternatives � mettre dans le plan
	
	public static ArrayList alternativeList = new ArrayList(); // la liste des taches sur lesquelles faire une bifurcation 
	

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println("Bonjour, ceci est un g�n�rateur de Plans : ");

	
		
		

		//-----------------------------------------------------------------------------------------------
		// G�n�ration d'un Plan :
		//----------------------
	    
		 // G�n�rer un graph  
		 Graph g = new SingleGraph("grid");
		 GridGenerator gen = new GridGenerator();

		 gen.addSink(g);
		 gen.begin();
		  
		 gen.setDirectedEdges(true, false);
		 
		 for(int i1=0; i1<7; i1++) {
		    	gen.nextEvents();
		 }

		 gen.end();
		    
		 int j=0;
		 for (Node node : g){
			node.setAttribute("ui.label", getTask(j));
			j++;
		 }
						
		 for(Edge e:g.getEachEdge()) {
			 e.setAttribute("ui.label", e.getSourceNode().getLabel("ui.label")+""+e.getTargetNode().getLabel("ui.label"));
		 }
						
		
		 Graph g3 = new SingleGraph("grid3");
		 g3.setStrict(false);
		 g3.setAutoCreate( true ); // cr�ation automatique des noeuds.
				
		 for(Edge e:g.getEachEdge()) {
				 String label = e.getLabel("ui.label").toString();
				 String st1 = e.getSourceNode().getLabel("ui.label").toString();
			     String st2 = e.getTargetNode().getLabel("ui.label").toString();
				 g3.addEdge(label, e.getSourceNode().getLabel("ui.label").toString(), e.getTargetNode().getLabel("ui.label").toString(), true  );
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
		 

		 
			
	
		  ArrayList allPaths = new ArrayList();
			
		  Dijkstra dijkstra = new Dijkstra(Dijkstra.Element.EDGE, null, "lenth");
//--------------------------------------------------------------
//--------------------------------------------------------------
//--------------------------------------------------------------
//--------------------------------------------------------------			
			int planNbr = 0;
			
			while(planNbr<agentNbr) {
				
				// initialisation
				//---------------
				
				System.out.println("La g�n�ration du Plan n :"+planNbr);
				
				//-----*-*-********************************************
				
				dijkstra = new Dijkstra(Dijkstra.Element.EDGE, null, "lenth");
				
				startTasks = ThreadLocalRandom.current().nextInt(minStartTasks, maxStartTasks + 1);
				System.out.println("La tache de d�but est : "+startTasks);
				
				
				endTasks = ThreadLocalRandom.current().nextInt(minEndTasks, maxEndTasks + 1);
				System.out.println("La tache de fin est : "+endTasks);
				
					
				
				
				
				
				
				 dijkstra.init(g3);
			//	 dijkstra.
				 dijkstra.setSource(g3.getNode(getTask(startTasks)));
				 dijkstra.compute();

				 /*
				 // Print the lengths of the new shortest paths
				 for (Node node : g3)
				 	System.out.printf(" -> Print the lengths of the new shortest paths : %s->%s:%10.2f%n", dijkstra.getSource(), node,
							dijkstra.getPathLength(node));
				  */
					
				   // Print all the shortest paths between A and F
					System.out.println(" Print all the shortest paths between "+getTask(startTasks)+" and "+getTask(endTasks)+" ");
					
					Iterator<Path> pathIterator = dijkstra.getAllPathsIterator(g3.getNode(endTasks));
					
					Graph finalGraph = new SingleGraph("FinalGraph");
					finalGraph.clear();
					finalGraph.setStrict(false);
					finalGraph.setAutoCreate( true ); // cr�ation automatique des noeuds.
					finalGraph.addAttribute("ui.quality");
					finalGraph.addAttribute("ui.antialias");
					
					
					
					
					while (pathIterator.hasNext()){				
						allPaths.add(pathIterator.next());
					} // fin de while
				
					System.out.println("La nombre des paths est : "+allPaths.size());
				//-----*-*-********************************************
				
					//alternativeNbr = ThreadLocalRandom.current().nextInt(1, (allPaths.size()) + 1);
					//System.out.println("Le Nombre de bifurcation � consid�rer est : "+alternativeNbr);
				
			
					if(allPaths.size()>2){			
						alternativeList.clear();
						//alternativeNbr = ThreadLocalRandom.current().nextInt(2, allPaths.size()+1);
						alternativeNbr = ThreadLocalRandom.current().nextInt(2, ((allPaths.size() % 6 )+3));
						
						
						System.out.println(" Le nombre d'alternatives � mettre dans le plan est :"+alternativeNbr);
						
						int i1=1;
						while(i1<=alternativeNbr){
							int altNbr =  ThreadLocalRandom.current().nextInt(1, allPaths.size());
							if(!alternativeList.contains(altNbr)) {
								alternativeList.add(altNbr);
								i1++;
							}
						} // fin de while
						
						
						for(int j1=0;  j1<alternativeList.size(); j1++){
							
							Path p = (Path) allPaths.get((Integer) alternativeList.get(j1));
							
							System.out.println(" Le path N :"+j1+" est : "+p.toString());
							
							for(Edge e:p.getEachEdge()) {
								 String label = e.getLabel("ui.label").toString();
								 String st1 = e.getSourceNode().getLabel("ui.label").toString();
							     String st2 = e.getTargetNode().getLabel("ui.label").toString();
							     finalGraph.addEdge(label, e.getSourceNode().getLabel("ui.label").toString(), e.getTargetNode().getLabel("ui.label").toString(), true  );
							 } // fin de for
							
						} // fin de for
						
						
						for(Node n:finalGraph) {
							n.setAttribute("ui.label", n.getId());
						 }
							
						for(Edge e:finalGraph.getEachEdge()) {
							 e.setAttribute("ui.label", e.getId());
							 e.setAttribute("lenth", 1);
						 }
						
						
						
						//---------------------------------------
						
						
						
						boolean clean = false;
						
						while(!clean){
							boolean nodeDeleted = false;
							for(Node n:finalGraph) {
								
								String noId = n.getId().toString();
								
								if((n.getInDegree()==0) && (!noId.equals(getTask(startTasks)))){
									//System.out.println("Tache : "+getTask(startTasks));
									finalGraph.removeNode(n.getId());
									nodeDeleted = true;
									break;
								}else								
								if((n.getOutDegree()==0) && (!noId.equals(getTask(endTasks))) ){
									finalGraph.removeNode(n);
									nodeDeleted = true;
									break;
								}
							}
							
							
							if(!nodeDeleted){
								clean = true;
							}
														
						} // fin de while
						
						if(finalGraph.getNodeCount()>=5){
							finalGraph.display();
							
							System.out.println("Le Plan est : ");
							String agColor = getAgentColor("Agent "+agentNbr);
							System.out.println(saveAsMethode(finalGraph, "Plan_"+planNbr, planNbr, "file:C:\\Planning/Modele1.css", agColor));
							//(Graph g, String Name, int planNbr,String cssPath)
							planNbr++;
						}
						
						
					
						
						dijkstra.clear();  	
						
					} // fin de if
					else
					{
						System.out.println("Number of Paths too small");
					}
					/*
					System.out.println(" Passons � l'autre : ");
					Scanner sc = new Scanner(System.in);
					String reponse = "";
					reponse = sc.nextLine();
					*/
			} // fin de while	
			
			
			
			 Graph Plan_9 = new SingleGraph("Plan_9"); 
			 Plan_9.setStrict(false); 
			  Plan_9.setAutoCreate( true ); 
			  Plan_9.addEdge("FI", "F", "I", true ); 
			 Plan_9.addEdge("IL", "I", "L", true ); 
			 Plan_9.addEdge("LP", "L", "P", true ); 
			 Plan_9.addEdge("PT", "P", "T", true ); 
			 Plan_9.addEdge("TY", "T", "Y", true ); 
			 Plan_9.addEdge("LS", "L", "S", true ); 
			 Plan_9.addEdge("SB#", "S", "B#", true ); 
			 Plan_9.addEdge("B#C#", "B#", "C#", true ); 
			 Plan_9.addEdge("FK", "F", "K", true ); 
			 Plan_9.addEdge("KL", "K", "L", true ); 
			 Plan_9.getNode("F").setAttribute("ui.label", "F"); 
			 Plan_9.getNode("I").setAttribute("ui.label", "I"); 
			 Plan_9.getNode("L").setAttribute("ui.label", "L"); 
			 Plan_9.getNode("P").setAttribute("ui.label", "P"); 
			 Plan_9.getNode("T").setAttribute("ui.label", "T"); 
			 Plan_9.getNode("Y").setAttribute("ui.label", "Y"); 
			 Plan_9.getNode("S").setAttribute("ui.label", "S"); 
			 Plan_9.getNode("B#").setAttribute("ui.label", "B#"); 
			 Plan_9.getNode("C#").setAttribute("ui.label", "C#"); 
			 Plan_9.getNode("K").setAttribute("ui.label", "K"); 
			 Plan_9.getEdge("FI").setAttribute("ui.label", "FI"); 
			 Plan_9.getEdge("IL").setAttribute("ui.label", "IL"); 
			 Plan_9.getEdge("LP").setAttribute("ui.label", "LP"); 
			 Plan_9.getEdge("PT").setAttribute("ui.label", "PT"); 
			 Plan_9.getEdge("TY").setAttribute("ui.label", "TY"); 
			 Plan_9.getEdge("LS").setAttribute("ui.label", "LS"); 
			 Plan_9.getEdge("SB#").setAttribute("ui.label", "SB#"); 
			 Plan_9.getEdge("B#C#").setAttribute("ui.label", "B#C#"); 
			 Plan_9.getEdge("FK").setAttribute("ui.label", "FK"); 
			 Plan_9.getEdge("KL").setAttribute("ui.label", "KL"); 
			 
		//	 Plan_9.display();
			
//--------------------------------------------------------------
//--------------------------------------------------------------
//--------------------------------------------------------------
//--------------------------------------------------------------			
			
			
		//	afficherTache(100);
		//-----------------------------------------------------------------------------------------------
		
		
	}  // fin de main
//-------------------------------------------------------------------------------


	public static void afficherTache(int nb){
		
		for(int i=1; i<nb; i++){
			System.out.println("case "+(i-1)+":  task = \""+getTask(i % 26)+"$\";");
			System.out.println("break;");
		}
		
	}  // fin 

	
//-------------------------------------------------------------------------------	
public static String getTask(int nbr){
	
		String task = "";
	
		switch (nbr) {
		case 0:  task = "A";
		break;
		case 1:  task = "B";
		break;
		case 2:  task = "C";
		break;
		case 3:  task = "D";
		break;
		case 4:  task = "E";
		break;
		case 5:  task = "F";
		break;
		case 6:  task = "G";
		break;
		case 7:  task = "H";
		break;
		case 8:  task = "I";
		break;
		case 9:  task = "J";
		break;
		case 10:  task = "K";
		break;
		case 11:  task = "L";
		break;
		case 12:  task = "M";
		break;
		case 13:  task = "N";
		break;
		case 14:  task = "O";
		break;
		case 15:  task = "P";
		break;
		case 16:  task = "Q";
		break;
		case 17:  task = "R";
		break;
		case 18:  task = "S";
		break;
		case 19:  task = "T";
		break;
		case 20:  task = "U";
		break;
		case 21:  task = "V";
		break;
		case 22:  task = "W";
		break;
		case 23:  task = "X";
		break;
		case 24:  task = "Y";
		break;
		case 25:  task = "Z";
		break;
		case 26:  task = "A#";
		break;
		case 27:  task = "B#";
		break;
		case 28:  task = "C#";
		break;
		case 29:  task = "D#";
		break;
		case 30:  task = "E#";
		break;
		case 31:  task = "F#";
		break;
		case 32:  task = "G#";
		break;
		case 33:  task = "H#";
		break;
		case 34:  task = "I#";
		break;
		case 35:  task = "J#";
		break;
		case 36:  task = "K#";
		break;
		case 37:  task = "L#";
		break;
		case 38:  task = "M#";
		break;
		case 39:  task = "N#";
		break;
		case 40:  task = "O#";
		break;
		case 41:  task = "P#";
		break;
		case 42:  task = "Q#";
		break;
		case 43:  task = "R#";
		break;
		case 44:  task = "S#";
		break;
		case 45:  task = "T#";
		break;
		case 46:  task = "U#";
		break;
		case 47:  task = "V#";
		break;
		case 48:  task = "W#";
		break;
		case 49:  task = "X#";
		break;
		case 50:  task = "Y#";
		break;
		case 51:  task = "Y#";
		break;
		case 52:  task = "A$";
		break;
		case 53:  task = "B$";
		break;
		case 54:  task = "C$";
		break;
		case 55:  task = "D$";
		break;
		case 56:  task = "E$";
		break;
		case 57:  task = "F$";
		break;
		case 58:  task = "G$";
		break;
		case 59:  task = "H$";
		break;
		case 60:  task = "I$";
		break;
		case 61:  task = "J$";
		break;
		case 62:  task = "K$";
		break;
		case 63:  task = "L$";
		break;
		case 64:  task = "M$";
		break;
		case 65:  task = "N$";
		break;
		case 66:  task = "O$";
		break;
		case 67:  task = "P$";
		break;
		case 68:  task = "Q$";
		break;
		case 69:  task = "R$";
		break;
		case 70:  task = "S$";
		break;
		case 71:  task = "T$";
		break;
		case 72:  task = "U$";
		break;
		case 73:  task = "V$";
		break;
		case 74:  task = "W$";
		break;
		case 75:  task = "X$";
		break;
		case 76:  task = "Y$";
		break;
		case 77:  task = "Z$";
		break;
		default : task = "";
			break;
		}
	
		return task;
	} // 
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
	
	String methode = "";
	
	methode = "\n //------------------------------------------------------------------------------- \n ";
	methode = " //------------------------------------------------------------------------------- \n ";
	
	methode+="public Graph getPlan"+planNbr+"(String pName){ \n";
	
	methode+= "	Graph "+Name+" = new SingleGraph(\""+Name+"\"); \n";
	methode+="		"+Name+".setStrict(false); \n ";
	methode+="		"+Name+".setAutoCreate( true ); \n ";
	methode+="		"+Name+".addAttribute(\"ui.stylesheet\", \"url('"+cssPath+"')\"); \n";
	methode+="		"+Name+".addAttribute(\"ui.quality\"); \n";
	methode+="		"+Name+".addAttribute(\"ui.antialias\"); \n ";
	
	for(Edge e:g.getEachEdge()) {
			
		 String edgeLabel = e.getId();
		 String n1 = e.getSourceNode().getId();
		 String n2 = e.getTargetNode().getId();
		 methode+="		"+Name+".addEdge(\""+edgeLabel+"\", \""+n1+"\", \""+n2+"\", true ); \n";
			
	 }
	
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
	
	
	methode+= "	return g; \n";
	methode+="} // fin de getPlan"+planNbr+" \n";
	methode+="";
	
	methode += "\n // ------------------------------------------------------------------------------- \n ";
	methode += " // ------------------------------------------------------------------------------- \n ";
	
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


//-----------------------------------------------------


} // fin de la classe
