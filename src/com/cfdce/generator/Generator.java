package com.cfdce.generator;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.view.Viewer;

public class Generator {

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

		
		displayTasksMatrix();
		
		//-----------------------------------------------------------------------------------------------
		// G�n�ration d'un Plan :
		//----------------------
		
		System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
		Graph graph = new SingleGraph("Plan 1");
		
		graph.setStrict(false);
		graph.setAutoCreate( true ); // cr�ation automatique des noeuds.
		
		graph.addAttribute("ui.quality");
		graph.addAttribute("ui.antialias");
		
		
		graph.addNode(getTask(startTasks)); 
		Node start = graph.getNode(getTask(startTasks));
		start.setAttribute("ui.label", "Ville "+getTask(startTasks));	
		start.addAttribute("ui.class", "start");
		
		
	
		
		while(!finished){
			
			explore(graph, startTasks);
			
			finished = true;
		} // fin de while
		
		

	
		graph.addNode(getTask(endTasks)); 
		Node goal = graph.getNode(getTask(endTasks));
		goal.setAttribute("ui.label", "Ville "+getTask(endTasks));	
		goal.addAttribute("ui.class", "goal");
		
		
		
		
		
		graph.addAttribute("ui.stylesheet", "url('file:C:\\Planning/Modele1.css')");
		Viewer viewer = graph.display();
		
		//-----------------------------------------------------------------------------------------------
		
		
	}  // fin de main
//-------------------------------------------------------------------------------



	
//-------------------------------------------------------------------------------	
public static String getTask(int nbr){
		String task = "";
		
		if(nbr==01) task="A"; if(nbr==02) task="B"; if(nbr==03) task="C"; if(nbr==04) task="D"; if(nbr==05) task="E"; if(nbr==06) task="F";
		if(nbr==07) task="G"; if(nbr==8) task="H"; if(nbr==9) task="I"; if(nbr==10) task="J"; if(nbr==11) task="K"; if(nbr==12) task="L"; 
		if(nbr==13) task="M"; if(nbr==14) task="N"; if(nbr==15) task="O"; if(nbr==16) task="P"; if(nbr==17) task="Q"; if(nbr==18) task="R"; 
		if(nbr==19) task="S"; if(nbr==20) task="T"; if(nbr==21) task="U"; if(nbr==22) task="V"; if(nbr==23) task="W"; if(nbr==24) task="X"; 
		if(nbr==25) task="Y"; if(nbr==26) task="Z";  
		
		
		
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
