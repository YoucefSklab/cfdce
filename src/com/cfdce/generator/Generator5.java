package com.cfdce.generator;


import java.awt.Toolkit;
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

public class Generator5 {

	public static int agentNbr = 100; // le nombre total d'agents 

	
	
	public static int minStartTasks = 1; // La tache minimale comme d�but
	public static int maxStartTasks = 8; // la tache maximale comme d�but
	
	
	public static int minEndTasks = 40; // la tache minimale comme fin
	public static int maxEndTasks = 48; // la tache maximale comme fin
	
	
	
	public static int startTasks = 0; // la tache de d�but
	public static int endTasks = 0; // la tache de fin
	
	public static int maxBifurcation = 20; // le maximum de bifurcation dans un plan
	public static int minBifurcation = 10; // le minimum de bifurcation dans un plan
	
	public static int alternativeNbr = 0; // le nombre d'alternatives � mettre dans le plan
	
	public static ArrayList alternativeList = new ArrayList(); // la liste des taches sur lesquelles faire une bifurcation 
	

	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		//System.out.println("Bonjour, ceci est un g�n�rateur de Plans : ");

	
		
		

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
		 
		 	g.setStrict(false); 
			g.setAutoCreate( true ); 
		//	g.addAttribute("ui.stylesheet", "url('file:config/Modele1.css')"); 
			g.addAttribute("ui.quality"); 
			g.addAttribute("ui.antialias"); 
			
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
		  	agentNbr= 82;
		  	
		  	
		  	
			int planNbr =48;
			planNbr =agentNbr;
			
			while(planNbr<(agentNbr+1)) {
				
				// initialisation
				//---------s------
				
			//	System.out.println("La g�n�ration du Plan n :"+planNbr);
				
				//-----*-*-********************************************
				
				dijkstra = new Dijkstra(Dijkstra.Element.EDGE, null, "lenth");
				
				startTasks = ThreadLocalRandom.current().nextInt(minStartTasks, maxStartTasks + 1);
			//	System.out.println("La tache de d�but est : "+startTasks);
				
				
				endTasks = ThreadLocalRandom.current().nextInt(minEndTasks, maxEndTasks + 1);
			//	System.out.println("La tache de fin est : "+endTasks);
				
					
				
				
				
				
				
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
			//		System.out.println(" Print all the shortest paths between "+getTask(startTasks)+" and "+getTask(endTasks)+" ");
					
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
				
			//		System.out.println("La nombre des paths est : "+allPaths.size());
				//-----*-*-********************************************
				
					//alternativeNbr = ThreadLocalRandom.current().nextInt(1, (allPaths.size()) + 1);
					//System.out.println("Le Nombre de bifurcation � consid�rer est : "+alternativeNbr);
				
			
					if(allPaths.size()>5){			
						alternativeList.clear();
						//alternativeNbr = ThreadLocalRandom.current().nextInt(2, allPaths.size()+1);
						alternativeNbr = ThreadLocalRandom.current().nextInt(4, ((allPaths.size() % 9 )+5));
						
						
						// System.out.println(" Le nombre d'alternatives � mettre dans le plan est :"+alternativeNbr);
						
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
							
				//			System.out.println(" Le path N :"+j1+" est : "+p.toString());
							
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
						
						if(finalGraph.getNodeCount()>=10){
							finalGraph.display();
							
							//System.out.println("Le Plan est : ");
							String agColor = getAgentColor("Agent "+planNbr);
							//-----------------------------------------------------------------
							//--------------------------------------------------------------------
							//*********************************************************************
							//----------------------------------------------------------------------
							//--------------------------------------------------------------------- 
							System.out.println(saveAsMethode(finalGraph, "Plan_"+planNbr, planNbr, "url('file:config/Modele1.css')", agColor));
							//(Graph g, String Name, int planNbr,String cssPath)
							planNbr++;
						}
						
						
					
						
						dijkstra.clear();  	
						
					} // fin de if
					else
					{
				//		System.out.println("Number of Paths too small");
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
			
			System.out.println(generateRandomTasksCost(g3));
			ArrayList List = new ArrayList();

				List.add("AB");
				List.add("AC");
				List.add("BD");
				List.add("CD");
				List.add("DU");
				List.add("QR");
				List.add("QS");
				List.add("RT");
				List.add("ST");
				List.add("TU");
				List.add("DI");
				List.add("TM");
				List.add("UM");
				List.add("MN");
				List.add("MO");
				List.add("NP");
				List.add("OP");
				List.add("UE");
				List.add("EF");
				List.add("EG");
				List.add("FH");
				List.add("GH");
				List.add("UI");
				List.add("IJ");
				List.add("IK");
				List.add("KL");
				List.add("JL");
				List.add("LZ");
				List.add("HZ");
				List.add("PY");
				List.add("HY");
			System.out.println(generateRandomTasksCostFromList(List));
			
			for(int i=4; i<=8; i++){
				System.out.println("//------------------------------------------");
				System.out.println("public float extraCostAgent"+i+"(String actName){");
				System.out.println(generateRandomExtraTasksCostFromList(List));
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
			
		

			
			for(int i=1; i<=99; i++){
				System.out.println("		if(agent.equals(\"Agent"+i+"\")) return extraCostAgent"+i+"(actName); \n");
			}
			
			System.out.println("		");
			System.out.println("		");
			System.out.println("		");
			System.out.println("	appercuPlan1 ----------------	");
			System.out.println("		");
			System.out.println("		");
			
			
			
			for(int i=1; i<=99; i++){
				System.out.println("case \"Plan "+i+"\" :");
				System.out.println("	g2 = pSet.getPlanByNbr("+i+");	");
				System.out.println("	break;");

			}
			
			
			System.out.println("		");
			System.out.println("		");
			System.out.println("		");
			
			for(int i=1; i<=99; i++){
				System.out.print("\"Plan "+i+"\",");
			

			}

			
		*/
			
			
			
			
			
			
System.out.println("		");
System.out.println("		");
System.out.println("		");
System.out.println("	agentsContainers	");
			
			//--------------------------------
			
			for(int i=1; i<=99; i++){
				
				
				
				PrintWriter f = new PrintWriter(new FileWriter("containerAgent"+i+".java"));
				f.println("package agentsContainers;");
				f.println("import org.graphstream.graph.Graph;");
				f.println("import org.graphstream.graph.implementations.SingleGraph;");
				f.println("import org.graphstream.ui.view.Viewer;");
				f.println("import Plan.PlanManagment;");
				f.println("import Plan.planSet;");
				f.println("import jade.core.ProfileImpl;");
				f.println("import jade.core.Runtime;");
				f.println("import jade.wrapper.AgentContainer;");
				f.println("import jade.wrapper.AgentController;");
				f.println("public class containerAgent"+i+" {");
				f.println("	public static void main(String[] args) {");
				
					
				f.println("	planSet pSet = new planSet();");
				f.println("	Graph PlanA = pSet.getPlan"+i+"(\"Plan_"+i+"\");");
				
				
				
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
		case 26:  task = "A_";
		break;
		case 27:  task = "B_";
		break;
		case 28:  task = "C_";
		break;
		case 29:  task = "D_";
		break;
		case 30:  task = "E_";
		break;
		case 31:  task = "F_";
		break;
		case 32:  task = "G_";
		break;
		case 33:  task = "H_";
		break;
		case 34:  task = "I_";
		break;
		case 35:  task = "J_";
		break;
		case 36:  task = "K_";
		break;
		case 37:  task = "L_";
		break;
		case 38:  task = "M_";
		break;
		case 39:  task = "N_";
		break;
		case 40:  task = "O_";
		break;
		case 41:  task = "P_";
		break;
		case 42:  task = "Q_";
		break;
		case 43:  task = "R_";
		break;
		case 44:  task = "S_";
		break;
		case 45:  task = "T_";
		break;
		case 46:  task = "U_";
		break;
		case 47:  task = "V_";
		break;
		case 48:  task = "W_";
		break;
		case 49:  task = "X_";
		break;
		case 50:  task = "Y_";
		break;
		case 51:  task = "Y_";
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
	

	


	//methode+="		"+Name+".addEdge(\"start\", \"start\", \"XXXX\", true ); \n";

	for(Edge e:g.getEachEdge()) {
			
		 String edgeLabel = e.getId();
		 String n1 = e.getSourceNode().getId();
		 String n2 = e.getTargetNode().getId();
		 methode+="		"+Name+".addEdge(\""+edgeLabel+"\", \""+n1+"\", \""+n2+"\", true ); \n";
			
	 }
	
	Node nnnn = g.getNode(0);
	methode+="		"+Name+".addEdge(\"start"+nnnn.getId()+"\", \"start\", \""+nnnn.getId()+"\", true ); \n";
	
	
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
		 int cout =  ThreadLocalRandom.current().nextInt(25, 35);
		costs+= "Action "+e.getId()+" = new Action(this.agent,\""+e.getId()+"\", "+cout+"); actionCostsList.add("+e.getId()+"); \n  ";
	 }
	
		
	return costs;
} //fin de generateRandomTasksCost



public static String generateRandomTasksCostFromList(ArrayList List){
	
	String costs = "";
	
	
	
	 for(int i=0; i<List.size(); i++) {
		 String e = (String) List.get(i);
		 int cout =  ThreadLocalRandom.current().nextInt(25, 35);
		costs+= "Action "+e+" = new Action(this.agent,\""+e+"\", "+cout+"); actionCostsList.add("+e+"); \n  ";
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
		 int cout =  ThreadLocalRandom.current().nextInt(0, 20);
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
		 int cout =  ThreadLocalRandom.current().nextInt(1, 10);
		 costs+= "		 if(actName.equals(\""+e+"\")) return "+cout+" ; \n";
		 
		// costs+= "String "+e.getId()+" = (\""+e.getId()+"\", "+cout+"); actionCostsList.add("+e.getId()+"); \n  ";
	 }
	
	 costs+= " return 0; \n";
		
	return costs;
} //fin de generateRandomExtraTasksCostFromList

//------------------------------------------------------------------------------------------------


//-----------------------------------------------------


} // fin de la classe
