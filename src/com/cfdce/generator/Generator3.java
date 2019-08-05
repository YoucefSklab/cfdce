package com.cfdce.generator;



import java.util.ArrayList;
import java.util.Iterator;

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

public class Generator3 {

	public static int agentNbr = 4; // le nombre total d'agents 
	public static int maxTasks = 10; // le maximum de taches � consid�rer pour le plans d'un agents
	public static int minTasks = 4; // le minimum de taches � consid�rer pour le plans d'un agents
	public static int totalTasksNbr = 26; // le nombre total de taches possibles
	
	
	public static int minStartTasks = 1; // La tache minimale comme d�but
	public static int maxStartTasks = 7; // la tache maximale comme d�but
	
	
	public static int minEndTasks = 13; // la tache minimale comme fin
	public static int maxEndTasks = 20; // la tache maximale comme fin
	
	
	
	public static int startTasks = 0; // la tache de d�but
	public static int endTasks = 0; // la tache de fin
	
	public static int milieu = 0;

	public static int maxBifurcation = 4; // le maximum de bifurcation dans un plan
	public static int minBifurcation = 1; // le minimum de bifurcation dans un plan
	
	public static int bifurcationNbr = 0; // le nombre de bifurcation dans un plan
	
	public static ArrayList bifurcationTask = new ArrayList(); // la liste des taches sur lesquelles faire une bifurcation 
	
	
	public static int currentTasksNbr = 0; // le nombre de tache � mettre dans le plan d'un agent.
	
	public static int levelNbr = 0; // le nombre de niveaux entre la tache d�but et fin 

	public static boolean finished =false;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println("Bonjour, ceci est un g�n�rateur de Plans : ");
		totalTasksNbr=totalTasksNbr+2;
		
		totalTasksNbr = 20;
		System.out.println("La liste des Taches est : ");
		
		String mat[][] = new String[totalTasksNbr][totalTasksNbr];
		
		
		
		
		currentTasksNbr = ThreadLocalRandom.current().nextInt(minTasks, maxTasks + 1);
		System.out.println("Le nombre total des taches � consid�rer pour ce plan : "+currentTasksNbr);
		
		startTasks = ThreadLocalRandom.current().nextInt(minStartTasks, maxStartTasks + 1);
		System.out.println("La tache de d�but est : "+startTasks);
		
		
		endTasks = ThreadLocalRandom.current().nextInt(minEndTasks, maxEndTasks + 1);
		System.out.println("La tache de fin est : "+endTasks);
		
		levelNbr = endTasks - startTasks ; 
		
		System.out.println("Le nombre de niveaux entre la tache de d�but et la tache de fin est : "+levelNbr);
		
		milieu = (startTasks + endTasks) / 2;
		System.out.println("Le Milieu est : "+milieu);
		
		
		bifurcationNbr = ThreadLocalRandom.current().nextInt(minBifurcation, maxBifurcation + 1);
		System.out.println("Le Nombre de bifurcation � consid�rer est : "+bifurcationNbr);
		
		
		// g�n�ration des taches sur lesquelles appliquer les bifurcations : 
		int i=1;
		while(i<=bifurcationNbr){
			int bif =  ThreadLocalRandom.current().nextInt(startTasks, endTasks);
			if(!bifurcationTask.contains(bif)) {
				bifurcationTask.add(bif);
				i++;
			}
		} // fin de while
		
		
		System.out.println("Les taches sur lesquelles appliquer les bifurcations : "+bifurcationTask.toString());

		
		//displayTasksMatrix();
		
		//-----------------------------------------------------------------------------------------------
		// G�n�ration d'un Plan :
		//----------------------
		
		//System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
		Graph graph = new SingleGraph("Plan 1");
		
		graph.setStrict(false);
		graph.setAutoCreate( true ); // cr�ation automatique des noeuds.
		graph.addAttribute("ui.quality");
		graph.addAttribute("ui.antialias");
		
		graph.addAttribute("ui.stylesheet", "url('file:C:\\Planning/Modele1.css')");
		//Viewer viewer = graph.display();
		
	

		    
		    
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
			
		 //g.display(false);
			
		
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
		 
		 Dijkstra dijkstra = new Dijkstra(Dijkstra.Element.EDGE, null, "lenth");
		    
		   
		 // Compute the shortest paths in g from A to all nodes
		 dijkstra.init(g3);
		 dijkstra.setSource(g3.getNode(getTask(startTasks)));
		 dijkstra.compute();
			
		 // Print the lengths of all the shortest paths
		 for (Node node : g3)
				System.out.printf("Print the lengths of all the shortest paths :  %s->%s:%10.2f%n", dijkstra.getSource(), node,
						dijkstra.getPathLength(node));

		 // Color in blue all the nodes on the shortest path form A to Y
		 for (Node node : dijkstra.getPathNodes(g3.getNode(getTask(endTasks))))
				node.addAttribute("ui.style", "fill-color: blue;");
		    
			
		 // Color in red all the edges in the shortest path tree
		 for (Edge edge : dijkstra.getTreeEdges())
				edge.addAttribute("ui.style", "fill-color: red;");
		 

		 // Print the shortest path from A to B
		 System.out.println(" ||--> Print the shortest path from "+getTask(startTasks)+" to "+getTask(endTasks)+" "+dijkstra.getPath(g3.getNode(getTask(endTasks))));

		
			/*
			
			// Build a list containing the nodes in the shortest path from A to B
			// Note that nodes are added at the beginning of the list
			// because the iterator traverses them in reverse order, from B to A
			List<Node> list1 = new ArrayList<Node>();
			for (Node node : dijkstra.getPathNodes(g3.getNode("H")))
				list1.add(0, node);
			*/
			
			/*
			// A shorter but less efficient way to do the same thing
			List<Node> list2 = dijkstra.getPath(g3.getNode("M")).getNodePath();
			 */
			// cleanup to save memory if solutions are no longer needed
			dijkstra.clear();
			
			
			
			
			
			// Now compute the shortest path from A to all the other nodes
			// but taking the number of nodes in the path as its length
			dijkstra = new Dijkstra(Dijkstra.Element.NODE, null, null);
			dijkstra.init(g3);
			dijkstra.setSource(g3.getNode(getTask(startTasks)));
			dijkstra.compute();

			// Print the lengths of the new shortest paths
			for (Node node : g3)
				System.out.printf("Print the lengths of the new shortest paths : %s->%s:%10.2f%n", dijkstra.getSource(), node,
						dijkstra.getPathLength(node));

			// Print all the shortest paths between A and F
			System.out.println(" Print all the shortest paths between "+getTask(startTasks)+" and "+getTask(endTasks)+" ");
			Iterator<Path> pathIterator = dijkstra.getAllPathsIterator(g3.getNode(endTasks));
			
			
			Graph finalGraph = new SingleGraph("FinalGraph");
			finalGraph.setStrict(false);
			finalGraph.setAutoCreate( true ); // cr�ation automatique des noeuds.
			finalGraph.addAttribute("ui.quality");
			finalGraph.addAttribute("ui.antialias");
			
			ArrayList allPaths = new ArrayList();
			
			
			while (pathIterator.hasNext()){				
				allPaths.add(pathIterator.next());
			} // fin de while
			
			//bifurcationNbr = pathIterator.
			// g�n�ration des taches sur lesquelles appliquer les bifurcations : 
			
			bifurcationTask.clear();
			bifurcationNbr = ThreadLocalRandom.current().nextInt(0, allPaths.size()+1);
			int i1=1;
			while(i1<=bifurcationNbr){
				int bif =  ThreadLocalRandom.current().nextInt(0, bifurcationNbr);
				if(!bifurcationTask.contains(bif)) {
					bifurcationTask.add(bif);
					i1++;
				}
			} // fin de while
			
			
			for(int j1=0;  j1<bifurcationTask.size(); j1++){
				
				Path p = (Path) allPaths.get( (Integer) bifurcationTask.get(j1));
				
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
			
			
			/*
			
			//----------------------------------------
			while (pathIterator.hasNext()){
			
				//System.out.println("-->"+pathIterator.next());
				
				Path p = pathIterator.next();
				
				 for(Edge e:p.getEachEdge()) {
					 String label = e.getLabel("ui.label").toString();
					 String st1 = e.getSourceNode().getLabel("ui.label").toString();
				     String st2 = e.getTargetNode().getLabel("ui.label").toString();
				     finalGraph.addEdge(label, e.getSourceNode().getLabel("ui.label").toString(), e.getTargetNode().getLabel("ui.label").toString(), true  );
				 } // fin de for
					
			} // fin de while
		
			for(Node n:finalGraph) {
				n.setAttribute("ui.label", n.getId());
			 }
				
			for(Edge e:finalGraph.getEachEdge()) {
				 e.setAttribute("ui.label", e.getId());
				 e.setAttribute("lenth", 1);
			 }
			
			*/
			//---------------------------------------
			
			
			finalGraph.display();
			
			
			
			
			
			
			
			
			
			
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
	
	
	
//----------------------------------------------------
// --- Methode Affichage de la Matrice : 
public static void displayTasksMatrix(){

    
String FinalResultText="";
    FinalResultText+=" \n \n \n \n ";
// affichage de la premi�re ligne
    
FinalResultText+=NewLine(3*totalTasksNbr);

for(int i=0; i<totalTasksNbr-1; i++){
        FinalResultText+=("| "+getTask(i)+"");
}

    FinalResultText+=("|\n");

    // AFFICHAGE DE LA suite de la matrice
for(int i=1; i<totalTasksNbr-1; i++){
    FinalResultText+=NewLine(3*totalTasksNbr);
        FinalResultText+=("|"+getTask(i)+"");
		
        for(int j=1; j<totalTasksNbr-1; j++){
            // le cas d'une relation ||
            if(i!=j){
            	FinalResultText+=("|  ");
            } // fin de if	
            
            if(i==j){
            	if(i==startTasks) FinalResultText+=("| S");
            	else
            		if(i==endTasks) FinalResultText+=("| E");
            			else FinalResultText+=("| *");
            } // fin de if	
      
        } // fin du premier for

        FinalResultText+=("|\n");
    } // fin du premier for
			
FinalResultText+=NewLine(3*totalTasksNbr);

System.out.println(FinalResultText);

} // fin de ma m�thode displayTasksMatrix
//-----------------------------------------------------



//-----------------------------------------------------
public static String NewLine(int Nbr){
	String text ="";
	for(int i = 0; i<Nbr; i++)
		text+=("-");
	text+=("\n");
	return text;
} //fin de la m�thode
//-----------------------------------------------------




//-----------------------------------------------------


public static void explore(Graph graph, int tasksPosition){
	String firstTask = getTask(tasksPosition);
	String secondTask = "";
	int nextMoveForI = 0;
	int nextMoveForJ = 0;
	int newPosition1 = 0;
	int newPosition2 = 0;
	

	
	if(tasksPosition<=milieu){
		
		nextMoveForI = ThreadLocalRandom.current().nextInt(0, 2);
		nextMoveForJ = ThreadLocalRandom.current().nextInt(0, 2);
		
		if((nextMoveForI==0) && (nextMoveForJ==0)) nextMoveForJ = 1;
		
		if(nextMoveForI==0) newPosition1 = tasksPosition;
		if(nextMoveForI==1) newPosition1 = tasksPosition+1;
		
		if(nextMoveForJ==0) newPosition1 = tasksPosition;
		if(nextMoveForJ==1) newPosition1 = tasksPosition+1;
		
		if(bifurcationTask.contains(tasksPosition)){
			
			graph.addEdge( getTask(tasksPosition)+getTask(tasksPosition), getTask(tasksPosition), getTask(tasksPosition), true  );
			graph.addEdge( getTask(tasksPosition)+getTask(tasksPosition), getTask(tasksPosition), getTask(tasksPosition), true  );
			
		} else{
			graph.addEdge( getTask(tasksPosition)+getTask(tasksPosition), getTask(tasksPosition), getTask(tasksPosition), true  );
		}
		
	}else{
		
		nextMoveForI++;
		nextMoveForJ = ThreadLocalRandom.current().nextInt(0, 2);
		
		if(bifurcationTask.contains(tasksPosition)){
			
		}else{
			
		}
		
	}
	
	
	graph.addEdge( "BF", "B", "F", true  );
	
}// fin de explorre

//-----------------------------------------------------


} // fin de la classe
