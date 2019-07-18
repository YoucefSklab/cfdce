package com.cfdce.FormationCoalitions;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Random;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;

import com.cfdce.Control.Constants;
import com.cfdce.Control.Traitment;
import com.cfdce.DB.BDConnect;
import com.cfdce.Plan.AgentPlan;
import com.cfdce.Plan.PlanManagment;

public class Discussion {
	
	
	
	public int discussionId;
	public static int discussionCounter;
	public String agentOwner;
	public ArrayList coalitionList= new ArrayList(); // une coalition par tache.
	public Graph discussionPath;  // le chemin de la discussion au format Graph
	public float discussionIndividualCost; // le co�t individuel de la discussion 
	public float discussionEstimatedCost; // le co�t estim� de la discussion
	public float avgEstimetedCostByAgent; // le co�t estim� moyen de la discussion
	//public float discussionRealCost;  // le co�t r�el de la discussion. (en fonction des acceptation re�ues)
	public float discussionFinalCost;  // le co�t final apr�s les confirmation des agents
	public float avgDiscussionRealCost;  // le co�t r�el moyen de la discussion agent	
	
	public float profitability = 0;      // le degr� de profitabilit� de la discussion (en fonnction des autres discussions)
	public float probability = 0;      // la Probabilit� que la discussions soit accept�e par les autres agents
	
	public float acceptaceIndicator = 0;      // la Probabilit� que la discussions soit accept�e par les autres agents
	public ArrayList acceptaceIndicatorHistory = new ArrayList();      // liste des valeurs de l'indice d'acceptation 
	
	public boolean isWithResumption = false;
	
	// les param�tres de caract�ristiques  :
	public float  w= 0;
	public ArrayList wH = new ArrayList();
	public ArrayList wV = new ArrayList();
	public ArrayList wValue = new ArrayList();
	
	public float eCost = 0;
	public ArrayList eCostH = new ArrayList();
	public ArrayList eCostV = new ArrayList();
	public ArrayList eCostValue = new ArrayList();
	
	public float rF = 0;
	public ArrayList rFH = new ArrayList();
	public ArrayList rFV = new ArrayList();
	public ArrayList rFValue = new ArrayList();
	
	public float sF = 0;
	public ArrayList sFH = new ArrayList();
	public ArrayList sFV = new ArrayList();
	public ArrayList sFValue = new ArrayList();
	
	public float ConvInd = 0;
	public ArrayList ConvIndH = new ArrayList();
	public ArrayList ConvIndV = new ArrayList();
	public ArrayList ConvIndValue = new ArrayList();
	
	public float ConvDegree = 0;
	public ArrayList ConvDegreeH = new ArrayList();
	
	public ArrayList discRank = new ArrayList();
 
	public int totalNodes;// le nombre total des Noeuds dans la discussion.
	public int totalTasks;    // le nombre total de taches dans la discussion.
	public int totalInvolvedAgents;   // le nombre total d'agents impliqu�s
	public int totalInvolvedAcceptedAgents;   // le nombre total d'agents impliqu�s et ayant accept�s
	public int totalInvolvedTasks;   // le nombre total de taches impliqu�es.
	public int totalNotInvolvedTasks;// le npmbre total de taches non impliqu�es.
	public int totalBifurcationNodes;// le nombre total des Noeuds de bifurcation.
	
	
	public ArrayList discussionProfil = new ArrayList(); // le profil de la discussion
	
	public ArrayList agentsList = new ArrayList(); // Liste des agents Impliqu�s. (� garder)
	public ArrayList involvedAgentsList = new ArrayList(); // liste des agents impliqu�s dans la discussion
	public ArrayList involvedAcceptedAgentsList = new ArrayList(); // liste des agents impliqu�s dans la discussion et ayants accept�s 
	public ArrayList tasksList = new ArrayList(); // liste des taches.
	public ArrayList nodesList = new ArrayList(); // liste des taches.
	public ArrayList involvedTasksList = new ArrayList(); // liste des taches impliqu�es dans la discussion
	public ArrayList bifurcationNodesList = new ArrayList(); // liste des taches en bifurcation
	
	// Liste des agents impliqu�s pour une discussion donn�e
	public Hashtable<String, ArrayList> neededAgentsByAction=new Hashtable<String, ArrayList>();   // <Action, Agents>
	
	// Liste totale des agents qui ont envoy�s une proposition concernant une action donn�e (sans compter l'agent courant)
	public Hashtable<String, ArrayList> agentsProposalsByAction=new Hashtable<String, ArrayList>();   // <Action, Agents>
	
	// liste totale de toutes acceptations possibles pour une discussion (Sans compter l'agent couran.)
	public Hashtable<String, ArrayList> maxPossibleAgentsByAction=new Hashtable<String, ArrayList>();   // <Action, Agents>
	
	
	public ArrayList involvedAgentsPathsListInTables = new ArrayList(); // une liste de tables � deux dimensions [Alternative][Coalitions] pour indiquer quelle alternative est impliqu� sur quelle coalition
	
	public ArrayList countInvolvedAlternativesByAgentList = new ArrayList() ; // le nombre de fois ou chaque alternative est impliqu�es dans la discussion par chaque agent 
	// Element de la liste de type Tab[Alternative][3] la position de l'alternative, et le nombre de fois ou elle est impliqu�es
		
	public ArrayList countInvolvedAlternativesNodesAndEdgeByAgent = new ArrayList() ; // le nombre de Noeuds des chaque alternative impliqu�e dans la discussion par chaque agent 
	// Des Elements de la liste de type Tab[Alternative][Nombre de Noeuds]la position de l'alternative, et le nombre de Noeuds qu'elle comporte 
	
	public ArrayList bifurcationNodesListByAgentByAlternative = new ArrayList() ; // une liste (de taille du nombre des agents impliqu�s) 
	// de tableaux (de taille du nombre d'alternative pour chaque agent impliqu�)
	// avec des listes de noeuds en bifurcation comme �l�ments des tableaux
	
	public ArrayList totalBifurcationNodesPerAgentList = new ArrayList() ; // (Liste de Liste) une liste des noeuds en bifurcation de toutes les alternative selon l'ordre des agents.  

	private long maxAcceptValidity;
	public ArrayList actionCostList = new ArrayList();
	BDConnect db;
	
	Traitment Tr = new Traitment();
	
	//  les listes : 
	// 				involvedAgentsPathsListInTables
	//				countInvolvedAlternativesNodesAndEdgeByAgent
	//				involvedAgentsList
	// sont de m�me taille .
	
		
	public float averageAgentsPerTasks;  // la moyenne d'agents par taches.
	public float averageAgentsPerInvolvedTasks;  // la moyenne d'agents par taches impliqu�es.
	public float averageTasksPerInvolvedAgents;  // la moyenne des taches par agents impliqu�s.
	public float averageInvolvedTasksPerInvolvedAgents;  // la moyenne des taches impliqu�es par agents impliqu�s.
	
	public float averageTotalTasksPerBifurcations;
	public float averageInvolvedTasksPerBifurcations;
	public float averageAgentPerBifurcations;
	
	
	public Discussion(String agOwner, Graph discPath, ArrayList actCostList, long maxAcptValidity, BDConnect db1){
		discussionCounter++;
		discussionId = discussionCounter;
		this.agentOwner = agOwner;
		this.discussionPath = discPath;
		this.actionCostList = actCostList;
		this.maxAcceptValidity = maxAcptValidity;
		db = db1;
	} // fin du constructeur Discussion
	
	
	public Discussion(String agOwner, Graph discPath, ArrayList actCostList){
			discussionCounter++;
			discussionId = discussionCounter;
			this.agentOwner = agOwner;
			this.discussionPath = discPath;
			this.actionCostList = actCostList;
		} // fin du constructeur Discussion
		
	
	
public float getDiscussionRealCostInTime(Graph agPlan) throws IOException{
	return discussionEstimatedCost;
}
	
	
 
/**
 * M�thode pour �valuer le cout estim� de la discussion	
 * @throws IOException 
 */
public void evaluateEstimatedCostForPropose(Graph agPlan) throws IOException{
	float cost = 0;
	
	
	for(int i=0; i<this.coalitionList.size(); i++){
		Coalition c = (Coalition) this.coalitionList.get(i);
		c.evaluateEstimatedCostForPropose(agPlan);
		cost+= c.coalitionEstimatedCost;
	}
	
	this.discussionEstimatedCost = cost;
	
	// evaluer le cout moyen par agent
	//computeAvgEstimetedCostByAgent();
	
} // fin de evaluateEstimatedCost()
//-------------------------------------

/**
 * M�thode pour �valuer le cout estim� de la discussion	
 * @throws IOException 
 */
public void evaluateEstimatedCostForAccept(Graph agPlan) throws IOException{
	float cost = 0;
	
	for(int i=0; i<this.coalitionList.size(); i++){
		Coalition c = (Coalition) this.coalitionList.get(i);
		c.evaluateEstimatedForAccept(agPlan);
		cost+= c.coalitionEstimatedCost;
	}
	
	this.discussionEstimatedCost = cost;
	
	// evaluer le cout moyen par agent
	//computeAvgEstimetedCostByAgent();
	
} // fin de evaluateEstimatedCost()
	  // -------------------------------------



	/**
	 * M�thode pour �valuer le cout estim� de la discussion
	 * 
	 * @throws IOException
	 */
	public void evaluateEstimatedCostForConfirm(Graph agPlan)
	        throws IOException {
		float cost = 0;

		for( int i = 0; i < this.coalitionList.size(); i++ ) {
			Coalition c = (Coalition) this.coalitionList.get(i);
			c.evaluateEstimatedForConfirm(agPlan);
			cost += c.coalitionEstimatedCost;
		}

		this.discussionEstimatedCost = cost;

		// evaluer le cout moyen par agent
		// computeAvgEstimetedCostByAgent();

	} // fin de evaluateEstimatedCost()
//-------------------------------------




	
/**
 * M�thode pour �valuer le cout r�el (� pr�sent) de la discussion
 * @throws IOException 
 */
public void evaluateFinalCost(Graph agPlan) throws IOException{
	float cost = 0;
	
	// calcul des couts des coalitions collective
	for(int i=0; i<coalitionList.size(); i++){
		Coalition c = (Coalition) coalitionList.get(i);
		c.evaluateCoalitionFinalCost(agPlan, actionCostList, maxAcceptValidity);
		cost += c.coalitionFinalCost;
	} // fin de for

	this.discussionFinalCost = cost;
} // fin de evaluateRealCost()
//-------------------------------------------


/**
* M�thode pour �valuer le cout r�el (� pr�sent) de la discussion (solution centralis�e)
* @throws IOException 
*/
public void getFinalCost(Graph agPlan) throws IOException{
float cost = 0;

// calcul des couts des coalitions collective
for(int i=0; i<coalitionList.size(); i++){
	Coalition c = (Coalition) coalitionList.get(i);
	c.getCoalitionFinalCost(agPlan, actionCostList);
	cost += c.coalitionFinalCost;
} // fin de for

this.discussionFinalCost = cost;


// -----------------------


	


// -----------------------



} // fin de evaluateRealCost()
//-------------------------------------------









/**
 * M�thode pour �valuer le cout individuel de la discussion	
 * @throws IOException 
 */
public void evaluateIndividualCost(Graph agPlan) throws IOException{
	float cost = 0;

	for(int i=0; i<this.coalitionList.size(); i++){
		Coalition c = (Coalition) this.coalitionList.get(i);
		c.evaluateIndividualCostForPropose(agPlan);
		cost+=c.individualCost;	
	}
	this.discussionIndividualCost = cost;
} // fin de evaluateIndividualCost()
//-------------------------------------------







/**
 * M�thode pour affichage une discussion 
 * @param disc
 */
	public void minimalDisplay(Discussion disc){
		
		String AgentList = " "+agentOwner+" - ";
		String discPathString = "";
		
		for(Edge e:disc.discussionPath.getEachEdge()) discPathString+= e.toString();
		
		System.out.println(" ========================================================");
		System.out.println(" Discussion Number : "+ disc.discussionId);
		System.out.println(" Discussion Path : "+ discPathString);
		System.out.println(" Discussion Estimated Cost : "+ disc.discussionEstimatedCost);
		
		for(int i=0; i<disc.coalitionList.size(); i++){ // pour changer la position de l'agent owner et le mettre en premier.
			Coalition c = (Coalition) disc.coalitionList.get(i);
			System.out.println(" -- -- -- -- -- -- ");
			System.out.println(" -- Coalition Id : "+c.coalitionId);
			System.out.println(" -- Owner ------ : "+c.agentOwner);
			System.out.println(" -- Edges ------ : "+c.edgeList.toString());
			System.out.println(" -- Agents ----- : "+c.agentList.toString());
			System.out.println(" -- Estimated Cost ------- : "+c.coalitionEstimatedCost);
			System.out.println(" -- -- -- -- -- --  ");
		} // fin de for
		
		System.out.println(" ========================================================");
		
		
	} // fin de minimalDisplay
//-----------------------------------------------------------------
	


/**
 * Affichage minimaliste d'une discussion (coalitions possibles)
 * @param disc La discussion � afficher
 */
public void minimalDisplayWithCosts(Discussion disc){
		
		String AgentList = " "+agentOwner+" - ";
		String discPathString = "";
		
		for(Edge e:disc.discussionPath.getEachEdge()) discPathString+= e.toString();
		
		System.out.println(" ========================================================");
		System.out.println(" Discussion Path ( Nbr : "+disc.discussionId+" ) "+ discPathString);
		System.out.println(" Discussion Individual Cost : "+ disc.discussionIndividualCost);
		System.out.println(" Discussion Estimated Cost : "+ disc.discussionEstimatedCost);
		//System.out.println(" Discussion Real Cost : "+ disc.discussionRealCost);
		
		/*
		for(int i=0; i<disc.coalitionList.size(); i++){ // pour changer la position de l'agent owner et le mettre en premier.
			Coalition c = (Coalition) disc.coalitionList.get(i);
			System.out.println(" -- -- -- -- -- -- ");
			System.out.println(" -- Coalition Id : "+c.coalitionId);
			System.out.println(" -- Owner ------ : "+c.agentOwner.toString());
			System.out.println(" -- Edges ------ : "+c.edgeList.toString());
			System.out.println(" -- Agents ----- : "+c.agentList.toString());
			System.out.println(" -- Estimated Cost ------- : "+c.coalitionEstimatedCost);
			System.out.println(" -- Real Cost ------- : "+c.coalitionRealCost);
		} // fin de for
		*/
	} // fin de minimalDisplay
//-----------------------------------------------------------------
		



/**
 * Construire le chemin final sur lequel un accord est trouv�
 * @return
 */
	public Graph getFinalPathToDisplay(){
		Graph g = new SingleGraph("Final agreed path for agent - "+this.agentOwner);
		PlanManagment pMgt = new PlanManagment(agentOwner);
		g.addAttribute("ui.stylesheet", "url('file:config/Modele1.css')");
		
		g.setStrict(false);
		g.setAutoCreate( true ); // cr�ation automatique des noeuds.
	
		g.addAttribute("ui.quality");
		g.addAttribute("ui.antialias");
		
		int i= 0;
		for(Edge e:this.discussionPath.getEachEdge()) {
			
			String st1 = e.getId().substring(0, 1);
			String st2 = e.getId().substring(1, 2);
			g.addEdge( e.getId(), st1, st2, true  );
			
			Node n1 = g.getNode(st1);
			n1.setAttribute("xyz", 20, 100-(i*5), 10);
			n1.setAttribute("ui.label", st1);
			n1.addAttribute("Type", "normal");
			
			Node n2 = g.getNode(st2);
			n2.setAttribute("xyz", 20, 100-(i*10), 10);
			n2.setAttribute("ui.label", st2);
			n2.addAttribute("Type", "normal");
			
			e.setAttribute("ui.label", this.getAcceptedProposalAgentStrList(e.getId()));
			e.addAttribute("label", this.getAcceptedProposalAgentStrList(e.getId()));
			
			i++;
		}
		
		if(this.agentOwner.equals("Agent 1")){
			for (Edge edge : g.getEachEdge())
				g.addAttribute("ui.style", "fill-color: red;");
		}
		
		Node n = pMgt.getRoot(g);
		n.setAttribute("Type", "start");
		n.setAttribute("ui.label", n.getId()+" - " +this.agentOwner);
		n = pMgt.getGoal(g);
		n.setAttribute("Type", "goal");

			
		
		//g.addAttribute("ui.stylesheet", "url('file:config/Modele1.css')");
			
		return g;	
	} // fin de getFinalPathToDisplay
//----------------------------------------------------------

	


/**
 * Afficher le chemin accept� sur le plan initial 
 * @param g : le chemin sous forme de  graphe
 * @param globalRound : le round global du processus de formation de coalitions.
 */
	public void printAcceptedPathOnInitialPlan(Graph g, int globalRound){
		
		PlanManagment pM = new PlanManagment(agentOwner);
	
		Node e = pM.getRoot(g);
		e.setAttribute("ui.label", " ("+globalRound+") "+"_"+g.getId());
		for(Edge e1:this.discussionPath.getEachEdge()) {
			
			for(Edge e2:g.getEachEdge()) {
				
				if(e1.getId().equals(e2.getId())){
					e2.setAttribute("ui.class", "red");
					//e1.setAttribute("ui.class", "red");
					//e1.addAttribute("ui.class", "red");
					e1.addAttribute("ui.style", "fill-color: red;");
					e2.addAttribute("ui.style", "fill-color: red;");
					e2.addAttribute("ui.label", this.getAcceptedProposalAgentStrList(e2.getId())+" ("+(String) e2.getAttribute("ui.label")+")");
				}
			
			} // fin de for
		} // fin de for
		
		g.addAttribute("ui.quality");
		g.addAttribute("ui.antialias");
		
	
		
	} // fin de printAcceptedPathOnInitialPlan
//----------------------------------------------------------


	/**
	 * Afficher le chemin confirm� sur le plan initial 
	 * @param g : Le chemin sous forme de graphe
	 * @param globalRound : le round global du processus de formation de coalitions.
	 */
	public void printConfirmedFinalPathOnInitialPlan(Graph g, int globalRound){
		
		this.allGraphEdgeInInitialColor(g);
		PlanManagment pM = new PlanManagment(agentOwner);
	
		Node e = pM.getRoot(g);
		e.setAttribute("ui.label", " ("+globalRound+") "+"_"+g.getId());
		
		for(Edge e1:this.discussionPath.getEachEdge()) {
		
			boolean isFinal = false;
			
			int nbrAgent = 0;
			int agentListNbr = 0;
			for(int i=0; i<this.coalitionList.size(); i++){
				Coalition c = (Coalition) this.coalitionList.get(i);
				if(c.edgeList.contains(e1.getId())){
					nbrAgent = c.agentRecievedConfirmationList.size() ;
					agentListNbr = c.agentList.size();
					isFinal = c.isFinal;
					break;
				}
			}
			
			
			for(Edge e2:g.getEachEdge()) {
				
				if(e1.getId().equals(e2.getId())){
					
					//e2.addAttribute("ui.label", this.getConfirmedProposalAgentStrList(e2.getId())+" ("+e2.getAttribute("ui.label")+")");
					e2.addAttribute("ui.label", this.getConfirmedProposalAgentStrList(e2.getId())+" ("+ (String) e2.getAttribute("oldLabel")+")");
					
					if(nbrAgent==0){
						e2.setAttribute("ui.class", "green");
						e2.addAttribute("ui.style", "fill-color: green;");
					}else{
						e2.setAttribute("ui.class", "sharedFinalPath");
						if(isFinal) e2.setAttribute("ui.class", "confirmedFinalWithResumption");
						
						if((agentListNbr==1) && (isFinal) && (nbrAgent >=1)) e2.setAttribute("ui.class", "closedSharedNegotiation");
					}
					break;
				}
			
			} // fin de for
		} // fin de for
		
		g.addAttribute("ui.quality");
		g.addAttribute("ui.antialias");
		
	} // fin de printConfirmedFinalPathOnInitialPlan
//----------------------------------------------------------
	
	
	
	
	

/**
 * Afficher le chemin confirm� sur la Console (Fichier) 
 * @param g : Le chemin sous forme de graphe
 * @param globalRound : le round global du processus de formation de coalitions.
 */
public void displayConfirmedFinalPathOnConsole(Graph g, int globalRound){
	
	System.out.println(" ");
	System.out.println(" --------------------------    Solution Trouv�e : ");
	System.out.println(" globalRound  = "+globalRound);
	
	for(Edge e1:this.discussionPath.getEachEdge()) {
	
		for(Edge e2:g.getEachEdge()) {
			if(e1.getId().equals(e2.getId())){
				System.out.println(" -- ");
				System.out.println(" Action "+e2.getId());
				System.out.println(" Les Co�ts : "+ (String) e2.getAttribute("ui.label"));
				System.out.println(" Coalition = "+this.getConfirmedProposalAgentStrList(e2.getId()));
				}
			} // fin de for
		} // fin de for
	} // fin de displayConfirmedFinalPathOnConsole
//----------------------------------------------------------
	
/**
 * Afficher le chemin � n�gocier en jaune  
 * @param g : Le chemin sous forme de graphe
 * @param globalRound : le round global du processus de formation de coalitions.
 */
public void printPathInBlue(Graph g){
	
	this.allGraphEdgeInInitialColor(g);
	
	for(Edge e1:this.discussionPath.getEachEdge()) {
		int nbrAgent = 0;
		int agentConfListNbr = 0;
		boolean isFinal = false;
		for(int i=0; i<this.coalitionList.size(); i++){
			Coalition c = (Coalition) this.coalitionList.get(i);
			if(c.edgeList.contains(e1.getId())){
				nbrAgent = c.agentList.size();
				agentConfListNbr = c.agentRecievedConfirmationList.size();
				isFinal = c.isFinal;
				break;
			}
		}
			
		for(Edge e2:g.getEachEdge()) {
			if(e1.getId().equals(e2.getId())){
				
				e2.setAttribute("ui.label", this.getInvolvedProposalAgentStrList(e2.getId())+" ("+ (String) e2.getAttribute("ui.label")+")");
				
				if(nbrAgent==1){
					e2.setAttribute("ui.class", "red");
					e2.addAttribute("ui.style", "fill-color: red;");
					
					if(isFinal) e2.setAttribute("ui.class", "closedSharedNegotiation");
									
				}else{
					//e2.setAttribute("ui.class", "yellow");
					e2.setAttribute("ui.class", "sharedNegotiation");
					//e2.addAttribute("ui.style", "fill-color: yellow;");
					if(isFinal) e2.setAttribute("ui.class", "sharedFinalWithResumption");
					
					if((agentConfListNbr>=1) && (isFinal) && (nbrAgent ==1)) e2.setAttribute("ui.class", "closedSharedNegotiation");
				}
				break;
			}
		} // fin de for
	} // fin de for
	} // fin de displayConfirmedFinalPathOnConsole
//----------------------------------------------------------

/**
 * Rendre Tous les arcs du graphe � leur couleur d'origine 
 * @param g Le graphe � mettre en couleur d'origine
 */
public void allGraphEdgeInInitialColor(Graph g){
	
	for(Edge e2:g.getEachEdge()) {
		e2.setAttribute("ui.label", (String)  e2.getAttribute("oldLabel"));
		e2.setAttribute("ui.class", "black");
		e2.addAttribute("ui.style", "fill-color: black;");
	}
}
//----------------------------------------------------------
	

	/**
	 * R�cup�ration de la liste des agents ayant envoy�s leurs messages d'acceptation au format chaine de caract�res
	 * @param act : la tache concenr�e par la coalition en question. 
	 * @return La lisete des agents ayant accept�s la coalition
	 */
	public String getAcceptedProposalAgentStrList(String act){
		String Str = "";
		
		for(int i=0; i<this.coalitionList.size(); i++ ){
			Coalition c = (Coalition) this.coalitionList.get(i);
			ArrayList edgList = c.edgeList;
			for(int j=0; j<edgList.size(); j++){
				String e = (String) edgList.get(j);
				if(act.equals(e)){
					for(int h=0; h<c.agentAcceptedList.size(); h++){ // � revoir cette boucle. elle n'est pas toujours correcte.
						String agent = (String) c.agentAcceptedList.get(h);
						Str+=agent+" - ";
					}
				}
			} // fin de for
		} // fin de for
		
		return Str;
	} // fin de la m�thode getAcceptedProposalAgentStrList
//----------------------------------------------------------
	
	

/**
 * R�cup�ration de la liste des agents ayant envoy�s leurs messages de confirmation au format chaine de caract�res
 * @param act : la tache concenr�e par la coalition en question. 
 * @return La lisete des agents ayant accept�s la coalition
 */
public String getConfirmedProposalAgentStrList(String act){
	String Str = " Cnf -> "+this.agentOwner+" - ";
	
	for(int i=0; i<this.coalitionList.size(); i++ ){
		Coalition c = (Coalition) this.coalitionList.get(i);
		ArrayList edgList = c.edgeList;
		for(int j=0; j<edgList.size(); j++){
			String e = (String) edgList.get(j);
			if(act.equals(e)){
				for(int h=0; h<c.agentRecievedConfirmationList.size(); h++){ // � revoir cette boucle. elle n'est pas toujours correcte.
					String agent = (String) c.agentRecievedConfirmationList.get(h);
					Str+=agent+" - ";
				}
				
			}
		} // fin de for
	} // fin de for
	
	return Str;
} // fin de la m�thode getConfirmedProposalAgentStrList
//----------------------------------------------------------

/**
 * Retourne la liste des agents impliqu�s dans les propositions de l'action en cours
 * @param act : la tache concenr�e par la coalition en question. 
 * @return La lisete des agents impliqu�s
 */
public String getInvolvedProposalAgentStrList(String act){
	String Str = "Pr -> ";
	
	for(int i=0; i<this.coalitionList.size(); i++ ){
		Coalition c = (Coalition) this.coalitionList.get(i);
		ArrayList edgList = c.edgeList;
		for(int j=0; j<edgList.size(); j++){
			String e = (String) edgList.get(j);
			if(act.equals(e)){
				for(int h=0; h<c.agentList.size(); h++){ // � revoir cette boucle. elle n'est pas toujours correcte.
					String agent = (String) c.agentList.get(h);
					Str+=agent+" - ";
				}
				
				Str+=" - Fn - ";
				
				for(int h=0; h<c.agentRecievedConfirmationList.size(); h++){ // � revoir cette boucle. elle n'est pas toujours correcte.
					String agent = (String) c.agentRecievedConfirmationList.get(h);
					Str+=agent+" - ";
				}
			}
		} // fin de for
	} // fin de for
	
	return Str;
} // fin de la m�thode getConfirmedProposalAgentStrList
//----------------------------------------------------------


/**
 * Calculer les param�tres d'une discussion	
 * @param plan
 */
  public void computeDiscussionParameters(ArrayList receivedProposals, ArrayList discussionList, Graph plan){
	
	  	// liste des agents
	    this.agentsList.clear();
	  	this.agentsList = getNeededAgentListForDiscussion();
	  	
	  	this.totalTasks = 0;
		this.totalTasks = discussionPath.getEdgeCount();
		
		
		// Ajout � la liste des taches, les taches individuelles		
		for(Edge ed:discussionPath.getEachEdge()){
			if(!this.tasksList.contains(ed.getId()))
				this.tasksList.add(ed.getId());
		} // fin de for
		
		
		// Parcour de la liste des coalitions et mise � jour des liste �quivalentes
		for(int i=0; i<this.coalitionList.size(); i++){
			Coalition c = (Coalition) this.coalitionList.get(i);
			
			// liste des agents impliqu�s dans la discussion
			for(int j=0; j<c.agentList.size(); j++){
				String ag = (String) c.agentList.get(j);
				
				if((!this.involvedAgentsList.contains(ag)) && (!ag.equals(agentOwner))) 
					this.involvedAgentsList.add(ag);
			}
			
			
			// liste des agents ayant accept�s la coalition 
			for(int j=0; j<c.agentAcceptedList.size(); j++){
				String ag = (String) c.agentAcceptedList.get(j);
		
				if((!this.involvedAcceptedAgentsList.contains(ag)) && (!ag.equals(agentOwner))) 
					this.involvedAcceptedAgentsList.add(ag);
			}
			
			// liste des taches impliqu�es
			for(int j=0; j<c.edgeList.size(); j++){
				String ed = (String) c.edgeList.get(j);
				
				if(!this.involvedTasksList.contains(ed)) 
					this.involvedTasksList.add(ed);
			}
			
		} // fin de for
		
		
		
		
		
		
		
	  
		this.totalInvolvedAgents = this.involvedAgentsList.size();
		this.totalInvolvedAcceptedAgents = this.involvedAcceptedAgentsList.size();
		this.totalInvolvedTasks  = this.involvedTasksList.size();
		this.totalNotInvolvedTasks = this.totalTasks - this.totalInvolvedTasks; 
		
		this.averageAgentsPerTasks =  (float) this.totalInvolvedAgents / this.totalTasks ;
		this.averageAgentsPerInvolvedTasks = (float) this.totalInvolvedAgents / this.totalInvolvedTasks;
		this.averageTasksPerInvolvedAgents = (float) this.totalTasks / this.totalInvolvedAgents;
		this.averageInvolvedTasksPerInvolvedAgents =   (float) this.totalInvolvedTasks  / this.totalInvolvedAgents;
		
		//--- Bifurcation --
		this.totalNodes = this.discussionPath.getNodeCount();
		
		// liste des noeuds
		for(Node n1:discussionPath){
			if(!nodesList.contains(n1.getId()))
				nodesList.add(n1.getId());
		}
		
		// liste des noeuds en bifurcation
		for(Node n2:plan){
			if(n2.getOutDegree()>1){
				if((this.nodesList.contains(n2.getId())) && (!this.bifurcationNodesList.contains(n2.getId()))){
					this.bifurcationNodesList.add(n2.getId());
				}
			}
		} // fin de for
		
			
	    this.totalBifurcationNodes = bifurcationNodesList.size();
	    
		if( this.totalBifurcationNodes >0){
			
			this.averageTotalTasksPerBifurcations = (float) this.totalTasks  / this.totalBifurcationNodes;
			this.averageInvolvedTasksPerBifurcations = (float) this.totalInvolvedTasks /this.totalBifurcationNodes;
			this.averageAgentPerBifurcations = (float) this.totalInvolvedAgents /this.totalBifurcationNodes;
	
		} else{
			this.averageTotalTasksPerBifurcations = 0;
			this.averageInvolvedTasksPerBifurcations = 0;
			this.averageAgentPerBifurcations = 0;
		}
	
		//--- Bifurcation
		
		
		// Liste des agents requis pour chaque actions
		// Hashtable<String, ArrayList> neededAgentsByAction=new Hashtable<String, ArrayList>();   // <Action, Agents>
		neededAgentsByAction.clear();
		for(Edge n2:plan.getEachEdge()){
			neededAgentsByAction.put(""+n2.getId()+"", getNeededAgentListForDiscussionByAction(n2.getId()));
		} // fin de for
		
		// Liste des agents ayant envoy�s une proposition concernant une action dans le plan local d'un agent
		agentsProposalsByAction.clear();
		for(Edge n2:plan.getEachEdge()){
			agentsProposalsByAction.put(""+n2.getId()+"", Tr.getAgentsWhoSentProposalsForAction(receivedProposals, n2.getId()));
		} // fin de for
		
		// le maximum des acceptations possibles pour une discussion
		maxPossibleAgentsByAction.clear();
		for(Edge n2:plan.getEachEdge()){
			maxPossibleAgentsByAction.put(""+n2.getId()+"", Tr.getMaxPossibleAgentListForActionInAllDiscussions(discussionList, n2.getId()));
		} // fin de for
		
		
  } // fin de computeDiscussionParameters
//----------------------------------------------------------------
	

  
  

  /**
   * Calculer les param�tres d'une discussion	
   */
 public void displayDiscussionParameters(){
	
		System.out.println("Discussion Prameters : ");
		System.out.println("---------------------");
		
		System.out.println("Discussion Id : "+this.discussionId);
		System.out.println("Discussion path : "+this.discussionPath.getEdgeSet().toString());
		
		System.out.println(" - discussionIndividualCost : "+this.discussionIndividualCost);
		System.out.println(" - discussionEstimatedCost : "+this.discussionEstimatedCost);
		System.out.println(" - avgEstimetedCostByAgent : "+this.avgEstimetedCostByAgent);
		//System.out.println(" - discussionRealCost : "+this.discussionRealCost);
		System.out.println(" - avgDiscussionRealCost : "+this.avgDiscussionRealCost);
		System.out.println(" - profitability : "+this.profitability);
		System.out.println(" - probability : "+this.probability);
		
		
		//System.out.println(" - : "+this.);
		//System.out.println(" - : "+this.);

		
		System.out.println(" - totalTasks : "+this.totalTasks);
		System.out.println(" - tasksList : "+this.tasksList.toString());
		System.out.println(" - agentsList : "+this.agentsList);
		System.out.println(" - involvedTasksList : "+this.involvedTasksList);
		System.out.println(" - totalInvolvedTasks : "+this.totalInvolvedTasks);
		System.out.println(" - totalNotInvolvedTasks : "+this.totalNotInvolvedTasks);
		System.out.println("       -      --         -   ");
		
		System.out.println(" - involvedAgentsList : "+this.involvedAgentsList.toString());
		System.out.println(" - involvedAcceptedAgentsList : "+this.involvedAcceptedAgentsList.toString());
		System.out.println(" - totalInvolvedAgents : "+this.totalInvolvedAgents);
		System.out.println(" - totalInvolvedAcceptedAgents : "+this.totalInvolvedAcceptedAgents);
		System.out.println("       -      --         -   ");		
		
		System.out.println(" - averageTasksPerInvolvedAgents : "+this.averageTasksPerInvolvedAgents);
		System.out.println(" - averageInvolvedTasksPerInvolvedAgents : "+this.averageInvolvedTasksPerInvolvedAgents);
		System.out.println("       -      --         -   ");
		
		System.out.println(" - averageAgentsPerTasks : "+this.averageAgentsPerTasks);
		System.out.println(" - averageAgentsPerInvolvedTasks : "+this.averageAgentsPerInvolvedTasks);
		System.out.println("       -      --         -   ");
		
		System.out.println(" - nodesList : "+this.nodesList.toString());
		System.out.println(" - totalNodes : "+this.totalNodes);
		System.out.println("       -      --         -   ");
		
		System.out.println(" - bifurcationNodesList : "+this.bifurcationNodesList.toString());
		System.out.println(" - averageTotalTasksPerBifurcations : "+this.averageTotalTasksPerBifurcations);
		System.out.println(" - averageInvolvedTasksPerBifurcations : "+this.averageInvolvedTasksPerBifurcations);
		System.out.println(" - averageAgentPerBifurcations : "+this.averageAgentPerBifurcations);
		
		System.out.println(" ------------------- Les autres listes --------------");
		
		System.out.println(" - neededAgentsByAction : "+this.neededAgentsByAction.size());
		System.out.println(" - involvedAgentsPathsListInTables : "+this.involvedAgentsPathsListInTables.size());
		System.out.println(" - countInvolvedAlternativesByAgentList  : "+this.countInvolvedAlternativesByAgentList.size());
		System.out.println(" - countInvolvedAlternativesNodesAndEdgeByAgent : "+this.countInvolvedAlternativesNodesAndEdgeByAgent.size());
		System.out.println(" - bifurcationNodesListByAgentByAlternative : "+this.bifurcationNodesListByAgentByAlternative.size());
		System.out.println(" - totalBifurcationNodesPerAgentList : "+this.totalBifurcationNodesPerAgentList.size());

		
		System.out.println(" ------------------- Contenu des listes --------------");
		System.out.println(" Envoy� :  neededAgentsByAction  ");
		System.out.println(" Re�u :  agentsProposalsByAction  ");
		Enumeration names;
		String key;
		Enumeration names1;
		String key1;
		Enumeration names2;
		String key2;
		
		names = neededAgentsByAction.keys();
		names1 = agentsProposalsByAction.keys();
		names2 = maxPossibleAgentsByAction.keys();
		
		int totalReceived = 0;
		int totalSent = 0;
		int totalCommun = 0;
		while(names.hasMoreElements()) {
		     key = (String) names.nextElement();
		     key1 = (String) names1.nextElement();
		     key2 = (String) names2.nextElement();
		     ArrayList sent =  (ArrayList) neededAgentsByAction.get(key);
		     ArrayList recevied =  (ArrayList) agentsProposalsByAction.get(key1);
		     ArrayList maxAgents =  (ArrayList) maxPossibleAgentsByAction.get(key2);
		     System.out.println(" ");
		     
		     System.out.println("- Envoy� comme proposition --> Key: " +key+ " & Value: " + sent.toString());
		     System.out.println("- Re�u Comme proposition --> Key: " +key1+ " & Value: " + recevied.toString());
		     System.out.println("- Max Agents Possibles --> Key: " +key2+ " & Value: " + maxAgents.toString());
		     
		     
		     totalSent+= sent.size();
		     totalReceived+=recevied.size() ;
		     
		     for (Iterator iterator = recevied.iterator(); iterator.hasNext();) {
				String ag1 = (String) iterator.next();
				for (Iterator iterator2 = sent.iterator(); iterator2.hasNext();) {
					String ag2 = (String) iterator2.next();
					
					if(ag1.equals(ag2)) totalCommun++;
					
				}
				
			}
		}
		
		
		System.out.println(" --> Total Sent : "+totalSent);
		System.out.println(" --> Total Received : "+totalReceived);
		System.out.println(" --> Total Common : "+totalCommun);
		
		
		System.out.println(" ");
	
	
		
	
 } // fin de displayDiscussionParameters
//----------------------------------------------------------------
 
//	
//----------------------------------------------------------------
 /**
  * Mettre les paramettes dans une HashTable
  * @param Ct Instance de la classe Constante
  */
public void putDiscussionParametersInHashTable(Constants Ct){

		Ct.discussionParameter.put( Ct.agentMe , agentOwner);
		Ct.discussionParameter.put( Ct.IndividualCost , Float.toString(Ct.referenceCost));
		Ct.discussionParameter.put( Ct.DiscussionNumber , Integer.toString(this.discussionId));
		Ct.discussionParameter.put( Ct.SelectedAlternative , Integer.toString(Ct.currentAlternativeSent));
		Ct.discussionParameter.put( Ct.EstimatedCost , Float.toString(this.discussionEstimatedCost));
		Ct.discussionParameter.put( Ct.RealFinalCost , Float.toString(this.discussionFinalCost));
		Ct.discussionParameter.put( Ct.IndividualGainedCost , Float.toString((Ct.referenceCost - this.discussionEstimatedCost)));
		Ct.discussionParameter.put( Ct.DiscussionLostGain , Float.toString((this.discussionFinalCost - this.discussionEstimatedCost)));
		
		Ct.discussionParameter.put( Ct.TotalTasks ,  Integer.toString(this.totalTasks));
		Ct.discussionParameter.put( Ct.TasksList , this.tasksList.toString());
		Ct.discussionParameter.put( Ct.InvolvedTasksList , this.involvedTasksList.toString());
		Ct.discussionParameter.put( Ct.TotalInvolvedTasks , Integer.toString(this.totalInvolvedTasks));
		Ct.discussionParameter.put( Ct.TotalNotInvolvedTasks , Integer.toString(this.totalNotInvolvedTasks));
	
		Ct.discussionParameter.put( Ct.InvolvedAgentsList , this.involvedAgentsList.toString());
		Ct.discussionParameter.put( Ct.InvolvedAcceptedAgentsList , this.involvedAcceptedAgentsList.toString());
		Ct.discussionParameter.put( Ct.TotalInvolvedAgents , Integer.toString(this.totalInvolvedAgents));
		Ct.discussionParameter.put( Ct.TotalInvolvedAcceptedAgents , Integer.toString(this.totalInvolvedAcceptedAgents));
	
		Ct.discussionParameter.put( Ct.AverageTasksPerInvolvedAgents , Float.toString(this.averageTasksPerInvolvedAgents));
		Ct.discussionParameter.put( Ct.AverageInvolvedTasksPerInvolvedAgents , Float.toString(this.averageInvolvedTasksPerInvolvedAgents));

		Ct.discussionParameter.put( Ct.AverageAgentsPerTasks , Float.toString(this.averageAgentsPerTasks));
		Ct.discussionParameter.put( Ct.AverageAgentsPerInvolvedTasks , Float.toString(this.averageAgentsPerInvolvedTasks));

		Ct.discussionParameter.put( Ct.NodesList , this.nodesList.toString());
		Ct.discussionParameter.put( Ct.TotalNodes , Integer.toString(this.totalNodes));
		
		Ct.discussionParameter.put( Ct.BifurcationNodesList , this.bifurcationNodesList.toString());
		Ct.discussionParameter.put( Ct.AverageTotalTasksPerBifurcations , Float.toString(this.averageTotalTasksPerBifurcations));
		Ct.discussionParameter.put( Ct.AverageInvolvedTasksPerBifurcations , Float.toString(this.averageInvolvedTasksPerBifurcations));
		Ct.discussionParameter.put( Ct.AverageAgentPerBifurcations , Float.toString(this.averageAgentPerBifurcations));

		
} // fin de putDiscussionParametersInHashTable
//----------------------------------------------------------------
 
 
 

 
/**
 * La m�thode qui construit la liste des nombres d'alternatives correspondantes aux agents impliqu�s dans la discussion
 * @param agPlanList Liste de tous les plans des agents
 */
 public void computeInvolvedAgentsPathsList(ArrayList agPlanList){
 
	involvedAgentsPathsListInTables.clear();

	 
	 for(int i=0; i<involvedAgentsList.size(); i++){
		 String agent = (String) involvedAgentsList.get(i);
		 
		 int table[][] = null; // d�claration d'une matrice de la taille des alternatives * coalitions
		
		 // R�cup�ration de l'objet AgentPlan correpodant � l'agent en question
		 for(int h=0; h<agPlanList.size(); h++){
			 AgentPlan aPlan = (AgentPlan) agPlanList.get(h);
			 
			 if(aPlan.agentOwner.equals(agent)){
				 
			
//				 System.out.println(" --------------------------------------------------");
//				 System.out.println("Analyse du Plan de l'agent "+agent);
				
				 // initialisation de la table
				 table  = new int[aPlan.pathList.size()][coalitionList.size()]; // d�claration d'une matrice de la taille des alternatives * coalitions
				
				 for(int ij=0; ij<aPlan.pathList.size(); ij++ ){
			 	 	for(int jk=0; jk<coalitionList.size(); jk++){
			 	 		table[ij][jk] = 0;
			 	 	}
			 	 } // fin de for
	
				 // Initialisation de la liste des valeurs booleennes pour voir si une alternative contient toutes les taches de toutes les coalitions.
				 ArrayList existInAgentPaths = new ArrayList();
				
				 //-------------------------------------------
		
				 for(int j=0; j<coalitionList.size(); j++){
					 Coalition c = (Coalition) coalitionList.get(j);
					 for(int k=0; k<c.agentList.size(); k++){
						 String ag = (String) c.agentList.get(k);
						 if(agent.equals(ag)){ // l'agent est impliqu� dans cette coalition
							
							 //---------------------------------------------------------
					//		 System.out.println(" 	Agent en cours est impliqu� dans la coalition : "+c.coalitionId);
							 for(int l=0; l<aPlan.pathList.size(); l++){
								 Graph agPath = (Graph) aPlan.pathList.get(l);
								 
								 for(int m=0; m<c.edgeList.size(); m++){
									 String e = (String) c.edgeList.get(m);
						//			 System.out.println(" 		V�rification de l'action  : "+e.getId());
									 
									 boolean exist = false;
									 for(Edge ed:agPath.getEachEdge()){
										 if(e.equals(ed.getId())){
											 exist = true;
										 }
									 } // fin de for
									 
									 if(exist){
										 //System.out.println(" 				Elle existe dans l'alternative : "+l);
										 table[l][j] = 1;  
									}
								 } // fin de for m
							 } // fin de for
							 //---------------------------------------------------------
						 }
					 } //fin de for
				 } // fin de for

				 involvedAgentsPathsListInTables.add(table);
			 } // fin de if
		 } // fin de for
	 } // fin de for
		 
 } // fin de la m�thode  computeInvolvedAgentsPathsList.
//----------------------------------------------------------------


 
 /**
  * M�thode pour afficher les alternatives ilpmiqu�es sur les coalitions
  * @param agPlanList Liste de tous les plans des agents
  */
 public void displayInvolvedAlternatives(ArrayList agPlanList){
	
System.out.println(" Involved Agent Alternatives whith the correspoding Coalitions :");
System.out.println(" -------------------------------------------------------------- ");
	 	
	 for(int i=0; i<involvedAgentsPathsListInTables.size(); i++){
	 	
		System.out.println(" Alternatives of the Agent : "+involvedAgentsList.get(i));
		int T[][] = (int[][]) involvedAgentsPathsListInTables.get(i);
			
		ArrayList agentPathList = new ArrayList();
		 for(int h=0; h<agPlanList.size(); h++){
			AgentPlan aPlan = (AgentPlan) agPlanList.get(h);
				if(aPlan.agentOwner.equals(involvedAgentsList.get(i))){
					agentPathList =  aPlan.pathList;
					for(int j=0; j<T.length; j++ ){
						//System.out.println("----> j  --> "+j);
						Graph path = (Graph) agentPathList.get(j);
						String st = path.getEdgeSet().toString();
						System.out.println(" 	It's Alternative in position  "+j+" (Size : "+path.getEdgeSet().size()+") ("+path.getEdgeSet().toString()+") is involved in these coalitions :");
						
							
						for(int k=0; k<T[j].length; k++){
								if(T[j][k] == 1){
									Coalition c = (Coalition) coalitionList.get(k);
									System.out.println(" 			Coalition : "+c.coalitionId+ "  -"+c.edgeList.toString());
					 			}
					 		}
					 } // fin de for
				}
	 		}
	 } // fin de for
	 
 } // fin de displayInvolvedAlternatives
 //------------------------------------------------------------------
 
 

 
 /**
  * M�thode pour afficher le nombre des alternatives impliqu�es sur les coalitions
  * @param agPlanList Liste de tous les plans des agents
  */
 public void displayNumberInvolvedAlternatives(ArrayList agPlanList){

System.out.println(" ");
System.out.println(" ");

System.out.println(" 2 -The number of Involved Agent Alternatives for each coalition ");
System.out.println(" -------------------------------------------------------------- ");
	 	
	 for(int i=0; i<involvedAgentsPathsListInTables.size(); i++){
	 	
		System.out.println(" Alternatives of the Agent : "+involvedAgentsList.get(i));
		int T[][] = (int[][]) involvedAgentsPathsListInTables.get(i);
		String agent = (String) involvedAgentsList.get(i);
		for(int j=0; j<coalitionList.size(); j++){
			Coalition c = (Coalition) coalitionList.get(j);
			System.out.println(" 			Total Alternatives involved in the coalitions : "+c.coalitionId+ "  is "+countInvolvedAlternativeForAgentAccordingCoalition(agPlanList, agent, c.coalitionId));
		}
	 } // fin de for
	 
 } // fin de displayNumberInvolvedAlternatives
 //------------------------------------------------------------------
 
 
 


 /**
  * M�thode pour calculer le nombre d'alternatives impliqu�es pour un agent donn�es sur une coalition donn�e 
  * @param agPlanList Liste de tous les plans des agents
  * @param agent Un agent
  * @param coalitionId Identifiant d'une coalition
  * @return
  */
 public int countInvolvedAlternativeForAgentAccordingCoalition(ArrayList agPlanList, String agent, String coalitionId){

	 int nbr = 0;
	 int coalitionPosition = 0;
	 for(int i =0; i<coalitionList.size(); i++){
		 Coalition c = (Coalition) coalitionList.get(i);
		 if(c.coalitionId.equals(coalitionId)){
			 coalitionPosition = i;
		 }
	 } // fin de for
	 	
	 
	 for(int i=0; i<involvedAgentsPathsListInTables.size(); i++){
		if(involvedAgentsList.get(i).equals(agent)){
			
			int T[][] = (int[][]) involvedAgentsPathsListInTables.get(i);
						
			for(int j=0; j<T.length; j++){
				
				nbr+=T[j][coalitionPosition];
				
			} // fin de for
			
		} // fin de of
	} // fin de for
	 return nbr;
 } // fin de displayNumberInvolvedAlternatives
 //------------------------------------------------------------------
 
 
 
 

 
 /**
  * M�thode pour calculer la liste des alternatives les plus impliqu�es dans la discussion  
  * @param agPlanList Liste des plans de tous les agents
  * @param agent Un agent
  * @return
  */
 public int[][]countMostInvolvedAlternativeForAgent(ArrayList agPlanList, String agent){

	
	 int[][] mostInvolvedAlternativesList = null;
	
	 for(int i=0; i<involvedAgentsPathsListInTables.size(); i++){
		
		if(involvedAgentsList.get(i).equals(agent)){
			int T[][] = (int[][]) involvedAgentsPathsListInTables.get(i);
			
			 mostInvolvedAlternativesList = new int[T.length][2];

			
			for(int j=0; j<T.length; j++){
				 int tempValue = 0;
				for(int k=0; k<T[j].length; k++){
					tempValue+=T[j][k];
				} // fin de for
				
				mostInvolvedAlternativesList[j][0] = j; // l'alternative en question 
				mostInvolvedAlternativesList[j][1] = tempValue; // le nombre de fois ou elle est impliqu�es
			} // fin de for
			
			
			
			
		} // fin de if
	} // fin de for
	 
//	 System.out.println("Liste des alternatives les plus impliqu�es sont : " + mostInvolvedAlternativesList.toString());
		
	return mostInvolvedAlternativesList;
	 
 } // fin de countMostInvolvedAlternativeForAgent
 //------------------------------------------------------------------------------------

 
 
 
// 
//-------------------------------------------------------------------
 /**
  * M�thode pour calculer le nombre des alternatives impliqu�es sur les coalitions
  * @param agPlanList Liste des plans de tous les agents
  */
 public void computeMostInvolvedAlternativesList(ArrayList agPlanList){

System.out.println(" ");

System.out.println(" 1- The number of Involved Agent Alternatives for each coalition ");
System.out.println(" ---------------------------------------------------------------- ");
	 	
	 for(int i=0; i<involvedAgentsPathsListInTables.size(); i++){
	 	
		System.out.println(" Alternatives of the Agent : "+involvedAgentsList.get(i));
		
		String agent = (String) involvedAgentsList.get(i);
		
		int Tab[][] = countMostInvolvedAlternativeForAgent(agPlanList, agent);
		this.countInvolvedAlternativesByAgentList.add(Tab);
	 } // fin de for
	 
 } // fin de computeMostInvolvedAlternativesList
 //------------------------------------------------------------------
 
 

 

 
//
//-------------------------------------------------------------------
 /**
  * M�thode pour afficher les alternatives impliqu�es dans la discussion, par agent et le nombre d'implication de chacune d'elles 
  * @param agPlanList Liste des plans de tous les agents
  */
public void displayMostInvolvedAlternativesList(ArrayList agPlanList){

System.out.println(" ");

System.out.println(" The number tasks each alternative is involded in, per agent :");
System.out.println(" -------------------------------------------------------------- ");
	 	
	 for(int i=0; i<involvedAgentsPathsListInTables.size(); i++){
	 	
		System.out.println(" Alternatives of the Agent : "+involvedAgentsList.get(i));
		String agent = (String) involvedAgentsList.get(i);
		
		int Tab[][] = (int[][]) this.countInvolvedAlternativesByAgentList.get(i);
		
		for(int j=0; j<Tab.length; j++){
			System.out.println(" The alternative in position : "+Tab[j][0]+"  is involved -> "+Tab[j][1]+" times. ");
			
		}
		
	 } // fin de for
	 
} // fin de displayMostInvolvedAlternativesList
//------------------------------------------------------------------
 


/**
 * M�thode pour calculer le nombre de noeuds dans chaque alternative de chaque agents parmi les alternative impliqu�s dans la discussion
 * @param agPlanList Liste des plans de tous les agents
 */
public void countInvolvedAlternativesNodesByAgent(ArrayList agPlanList){
		
	 for(int i=0; i<this.involvedAgentsPathsListInTables.size(); i++){
	 	
		System.out.println(" Alternatives of the Agent : "+involvedAgentsList.get(i));
		
		String agent = (String) involvedAgentsList.get(i);
		
		int Tab[][] = countInvolvedAlternativeNodesAndEdgesForAgent(agPlanList, agent);
		this.countInvolvedAlternativesNodesAndEdgeByAgent.add(Tab);
		
		ArrayList list = computetInvolvedAlternativeBifurcationNodesList(agPlanList, agent);
		this.bifurcationNodesListByAgentByAlternative.add(list);
	 } // fin de for
	 
} // fin de countInvolvedAlternativesNodesByAgent
//------------------------------------------------------------------




// 
//---------------------------------------------------------------------------------------
/**
 * M�thode pour calculer pour chaque alternative des alternatives impliqu�es pour un agent, le nombre de noeuds qu'elle comporte   
 * @param agPlanList Liste de tous les plans de tous les agents
 * @param agent Un agent
 * @return
 */
public int[][]countInvolvedAlternativeNodesAndEdgesForAgent(ArrayList agPlanList, String agent){
	
	 int[][] involvedAlternativeNodesCountList = null;
	 System.out.println(" Ici > countInvolvedAlternativeNodesAndEdgesForAgent (� D�velopper une m�thode pour affichage) : ");
	 for(int i=0; i<involvedAgentsList.size(); i++){
		
		if(involvedAgentsList.get(i).equals(agent)){
	
		//	System.out.println(" ---> Agent : "+agent);
			
			// R�cup�ration de l'objet AgentPlan correpodant � l'agent en question
			 for(int h=0; h<agPlanList.size(); h++){
				 AgentPlan aPlan = (AgentPlan) agPlanList.get(h);
				 if(aPlan.agentOwner.equals(agent)){
					 
					 int T[][] = (int[][]) involvedAgentsPathsListInTables.get(i);
					
					 involvedAlternativeNodesCountList = new int[T.length][2];
						 
					 for(int j=0; j<T.length; j++){
						// int altNbr = T[j][0]; // l'alternative en question 
						// involvedAlternativeNodesCountList[j][0] = altNbr; // l'alternative en question 
						 
						 Graph path = (Graph) aPlan.pathList.get(j);
					//	 System.out.println("The Path :--> "+path.getEdgeSet().toString());
						 involvedAlternativeNodesCountList[j][0] =  path.getNodeCount(); // le nombre de Noeuds de l'alternative
						 involvedAlternativeNodesCountList[j][1] =  path.getEdgeCount(); // le nombre d'Edge de l'alternative
						 
					//	 System.out.println(" --------> Alt  : "+j);
					//	 System.out.println(" --------> Nodes  : "+ path.getNodeCount());
					//	 System.out.println(" --------> Edge  : "+path.getEdgeCount());
					 } // fin de for
					 
					 return involvedAlternativeNodesCountList;
					 
				 } // fin de if
			} // fin de for
			 break;
		} // fin de if
	} // fin de for
	 
//	 System.out.println("Liste des alternatives les plus impliqu�es sont : " + mostInvolvedAlternativesList.toString());
		
	return involvedAlternativeNodesCountList;
	 
} // fin de countInvolvedAlternativeNodesAndEdgesForAgent
//------------------------------------------------------------------------------------




/**
 * M�thode pour calculer pour chaque alternative des alternatives impliqu�es pour un agent, la liste des noeuds en bifurcation   
 * @param agPlanList Liste des plans de tous les agents
 * @param agent un agent
 * @return
 */
public ArrayList computetInvolvedAlternativeBifurcationNodesList(ArrayList agPlanList, String agent){
	
	ArrayList involvedAlternativeBifurcationList = new ArrayList();
	
	 for(int i=0; i<involvedAgentsPathsListInTables.size(); i++){
		
		if(involvedAgentsList.get(i).equals(agent)){
	
	//		System.out.println(" ||---> Agent : "+agent);
			
			// R�cup�ration de l'objet AgentPlan correpodant � l'agent en question
			 for(int h=0; h<agPlanList.size(); h++){
				 AgentPlan aPlan = (AgentPlan) agPlanList.get(h);
				 if(aPlan.agentOwner.equals(agent)){
					 
					 int T[][] = (int[][]) involvedAgentsPathsListInTables.get(i);
					 
					 for(int j=0; j<T.length; j++){
						
						 Graph path = (Graph) aPlan.pathList.get(j);
				//		 System.out.println("|| --> The Path :--> "+path.getEdgeSet().toString());
						 involvedAlternativeBifurcationList.add(aPlan.getBifurcationNodesList(aPlan.graphPlan, path)); // le nombre de Noeuds de l'alternative
						
						 ArrayList listAlt = aPlan.getBifurcationNodesList(aPlan.graphPlan, path); 
				//		 System.out.println(" -----> Sa taille est : "+listAlt.size());
				//		 System.out.println(" || it's bifurcation Nodes list -> Edge  : "+aPlan.getBifurcationNodesList(aPlan.graphPlan, path).toString());
					 } // fin de for
					 
					 return involvedAlternativeBifurcationList;
					 
				 } // fin de if
			} // fin de for
			 break;
		} // fin de if
	} // fin de for
	return involvedAlternativeBifurcationList;
} // fin de computetInvolvedAlternativeBifurcationNodesList
//------------------------------------------------------------------------------------

//
//-------------------------------------------------------------------

/**
 * M�thode pour afficher le nombre des Noeuds et des Edge pour chaque alternative impliqu�es dans la discution, par agent
 * @param agPlanList Liste des plans de tous les agents.
 */
public void displayInvolvedAlternativesNodesAndEdgesByAgent(ArrayList agPlanList){

System.out.println(" ");
System.out.println(" ");

System.out.println(" The number of Nodes and Edge of each Involved Alternatives for each Agent ");
System.out.println(" -------------------------------------------------------------- ");
	 	
	for(int i=0; i<this.countInvolvedAlternativesNodesAndEdgeByAgent.size(); i++){
	 	
		System.out.println(" Alternatives of the Agent : "+involvedAgentsList.get(i));
		
		String agent = (String) involvedAgentsList.get(i);
		
		int Tab[][] = (int[][])countInvolvedAlternativesNodesAndEdgeByAgent.get(i);
		
		for(int j=0; j<Tab.length; j++){
			System.out.println(" 	Alternatives at the Position : "+j);
			System.out.println(" 		--> Contains : "+Tab[j][0]+" Nodes");
			System.out.println(" 		--> Contains : "+Tab[j][1]+" Edges");
			
		} // fin de for
	
	} // fin de for
} // fin de displayInvolvedAlternativesNodesByAgent
//------------------------------------------------------------------


/**
 * M�thode pour afficher la liste des noeuds en bifurcation pour chaque alternative impliqu�es dans la discution, par agent
 * @param agPlanList Liste des plans de tous les agents
 */
public void displayBifurcationNodesListByAgentByAlternative(ArrayList agPlanList){

System.out.println(" ");
System.out.println(" ");

System.out.println(" The list of Nodes in Bifurcation of each Involved Alternatives for each Agent ");
System.out.println(" -------------------------------------------------------------- ");
	 	
	for(int i=0; i<this.bifurcationNodesListByAgentByAlternative.size(); i++){
	 	
		ArrayList totalBifurcationNodeForAgent = new ArrayList(); // la liste totale des noeuds en bifurcation
		System.out.println(" Alternatives of the Agent : "+involvedAgentsList.get(i));
		
		String agent = (String) involvedAgentsList.get(i);
		
		ArrayList list = (ArrayList)bifurcationNodesListByAgentByAlternative.get(i);
		
		for(int j=0; j<list.size(); j++){
			System.out.println(" 	Alternatives at the Position : "+j);
			ArrayList listAlt = (ArrayList) list.get(j); 
			
			System.out.println(" 	----> Nodes on bifurcation : "+listAlt.toString());
			Iterator it=listAlt.iterator();
			
			while (it.hasNext()) // tant que j'ai un element non parcouru
			{
			  Node n= (Node) it.next();
			  //System.out.println(" 		---> The Node : "+n.getId()+" ");
			  if(!totalBifurcationNodeForAgent.contains(n)){
				  totalBifurcationNodeForAgent.add(n); 
			  }
			}
			
		} // fin de for
	
	} // fin de for
} // fin de displayInvolvedAlternativesNodesByAgent
//------------------------------------------------------------------




/**
 * Calculer le nombre Total des noeuds en bifurcation dans tous les plans des agents.
 * @param agPlanList liste des Plans des agents.
 */
public void computeTotalBifurcationNodesList(ArrayList agPlanList){


	for(int i=0; i<this.bifurcationNodesListByAgentByAlternative.size(); i++){
	 	
		ArrayList totalBifurcationNodeForAgent = new ArrayList(); // la liste totale des noeuds en bifurcation
	
		String agent = (String) involvedAgentsList.get(i);
		
		ArrayList list = (ArrayList)bifurcationNodesListByAgentByAlternative.get(i);
		
		for(int j=0; j<list.size(); j++){
			
			ArrayList listAlt = (ArrayList) list.get(j); 
			
			Iterator it=listAlt.iterator();
			
			while (it.hasNext()) // tant que j'ai un element non parcouru
			{
			  Node n= (Node) it.next();
			  if(!totalBifurcationNodeForAgent.contains(n)){
				  totalBifurcationNodeForAgent.add(n); 
			  }
			}
			
		} // fin de for
	this.totalBifurcationNodesPerAgentList.add(totalBifurcationNodeForAgent);
	} // fin de for
} // fin de displayInvolvedAlternativesNodesByAgent
//------------------------------------------------------------------




// 
//-------------------------------------------------------------------
/**
 * M�thode pour afficher la liste totales des noeuds en bifurcation pour chaque agent
 * @param agPlanList Liste des plans de tous les agents
 */
public void displayTotalBifurcationNodesPerAgentList(ArrayList agPlanList){

System.out.println(" ");
System.out.println(" ");

System.out.println(" The Total list of Nodes in Bifurcation for each Agent ");
System.out.println(" -------------------------------------------------------------- ");
	 	
for(int i=0; i<this.totalBifurcationNodesPerAgentList.size(); i++){
	 	
		ArrayList totalBifurcationNodeForAgent = new ArrayList(); // la liste totale des noeuds en bifurcation
		System.out.println(" Alternatives of the Agent : "+involvedAgentsList.get(i));
		
		String agent = (String) involvedAgentsList.get(i);
		
		ArrayList list = (ArrayList) this.totalBifurcationNodesPerAgentList.get(i);
		
		System.out.println(" Nombre de Noeuds : "+list.size()+"  -> La liste des Noeuds en bifurcation sont : "+list.toString());
		
	} // fin de for
} // fin de displayTotalBifurcationNodesPerAgentList
//------------------------------------------------------------------





/**
 * Calculer le nombre Total des noeuds en bifurcation dans un plan donn�, pour une alternative donn�e du plan de l'agent en cours
 * @param planAgent Le Plan sur qui v�rifier
 * @param Alt L'aternative � v�rifier.
 * @return Nombre de noeuds en bifurcation dans l'atenative donn�e en param�tre du plan de l'agent en cours, en fonction du plan donn�e en param�tre..
 */
public int computeBifurcationNodesForAlternative(AgentPlan planAgent, Graph Alt){
	
	//System.out.println("-----> computeBifurcationNodesForAlternative    Plan Agent : "+planAgent.agentOwner);
	
	int bifNbr = 0;
	ArrayList visitedNode = new ArrayList();
	for(Edge ed:Alt.getEachEdge()){
		Node n1 = ed.getSourceNode();
		for(Node n:planAgent.graphPlan){
			if(n1.getId().equals(n.getId())){
				if(!visitedNode.contains(n)){
					if(n.getOutDegree()>=2){
						bifNbr++;
						//System.out.println("-----> Node (degree>=2) :"+n.getId());
					}
					visitedNode.add(n);
					//System.out.println("-----> Node :"+n.getId());
				}
			}
		}
	}
	
	return bifNbr;
} // fin de computeBifurcationNodesForAlternative
//------------------------------------------------------------------

//
//	
//----------------------------------------------------------------
/**
 * RESUME SUR TOUT LES AGENTS - (� Mettre les parametres par agent, dans une HashTable)
 * @param localPlan Le plan local de l'agent en cours
 * @param agPlanList  Liste des plans de tous les agents
 * @param Ct Instance de la classe Constante
 * @param stepNbr L'�tape locale
 * @param globalStep L'�tape Globale

 */
public void putDiscussionParametersByAgentInHashTable(AgentPlan localPlan, AgentPlan aPlan, int agPosition, Constants Ct, int stepNbr, int globalStep){
	
	 Ct.agentParameter.put( Ct.agentMe, agentOwner);
	 Ct.agentParameter.put( Ct.Agent, aPlan.agentOwner);
	 Ct.agentParameter.put( Ct.GlobalStep, Integer.toString(globalStep));
	 Ct.agentParameter.put( Ct.StepNbr, Integer.toString(stepNbr));
	 Ct.agentParameter.put( Ct.TotalDesActions, Integer.toString(aPlan.totalTasks));
	 Ct.agentParameter.put( Ct.TotalDesAlternatives, Integer.toString(aPlan.pathList.size()));
	 
	 Ct.agentParameter.put( Ct.ListeDesAlternativesImpliquees, getInvolvedAlternativeList(agPosition).toString());
	 Ct.agentParameter.put( Ct.TotalDesAlternativesImpliquees, Integer.toString(getInvolvedAlternativeList(agPosition).size()));
	 
	 Ct.agentParameter.put( Ct.ListeDesActionsEnCommun, getAllCommonActionList(localPlan, aPlan, agPosition).toString());
	 Ct.agentParameter.put( Ct.TotalDesActionsEnCommun, Integer.toString(getAllCommonActionList(localPlan, aPlan, agPosition).size()));
	 
	 Ct.agentParameter.put( Ct.ListeDesActionsImpliquees, getAllInvolvedActionsList(agPosition).toString());
	 Ct.agentParameter.put( Ct.TotalDesActionsImpliquees, Integer.toString(getAllInvolvedActionsList(agPosition).size()));
	 
	 Ct.agentParameter.put( Ct.TotalDesActionsNonImpliquees, "-");
	 
	 Ct.agentParameter.put( Ct.ListeDesAgentsImpliquesAvecLui, getAllAgentsInvolvedWith(agPosition).toString());
	 Ct.agentParameter.put( Ct.TotalDesAgentsImpliquesAvecLui, Integer.toString(getAllAgentsInvolvedWith(agPosition).size()));
	 
	 Ct.agentParameter.put( Ct.ListeDesAgentsQuiOntAcceptesAvecLui, getAllAgentsAccepteddWith(agPosition).toString());
	 Ct.agentParameter.put( Ct.TotalDesAgentsQuiOntAcceptesAvecLui, Integer.toString(getAllAgentsAccepteddWith(agPosition).size()));
	 
	 Ct.agentParameter.put( Ct.ListeDesActionsImpliquees_DesActionsDesAutresAgents_AvecLui, getAllActionsInvolvedWith(agPosition).toString());
	 Ct.agentParameter.put( Ct.TotalDesActionsImpliquees_DesActionsDesAutresAgents_AvecLui, Integer.toString(getAllActionsInvolvedWith(agPosition).size()));
	 
	 Ct.agentParameter.put( Ct.ListeDesActionsImpliqueesEtAcceptees_AutresAgents_AvecLui, getAllActionsAcceptedWith(agPosition).toString());
	 Ct.agentParameter.put( Ct.TotalDesActionsImpliqueesEtAcceptees_AutresAgents_AvecLui, Integer.toString(getAllActionsAcceptedWith(agPosition).size()));
	 
	 Ct.agentParameter.put( Ct.MoySesActionsImpliquee_ToutesLesAlternatives_SurTotalSesActions, "-");
	 Ct.agentParameter.put( Ct.MoySesActionsImpliquee_UneAlternativeDonnee_SurTotalSesActions, "-");
	 Ct.agentParameter.put( Ct.TotalDesAgentsQuiOntAcceptesLesPropositionsAvecSaPresence, "-");
	 Ct.agentParameter.put( Ct.TotalDesAgentsQuiNOntPasAcceptesLesPropositionsAvecSaPresence, "-");
	
	 Ct.agentParameter.put( Ct.TotalDeSesNoeuds, Integer.toString(aPlan.graphPlan.getNodeCount()));
	 
	 Ct.agentParameter.put( Ct.TotalDeSesNoeudsEnBifurcation, Integer.toString(getTotalBifurcationNodesPerAgentList(agPosition).size()));
	 
	
	 
	// System.out.println(" - Liste de ses n�uds en bifurcation : "+getTotalBifurcationNodesPerAgentList(agPlanList, agent));
	
	 Ct.agentParameter.put( Ct.TotalDeSesNoeudsEnBifurcationDansMesPropositions, "-");
	 Ct.agentParameter.put( Ct.TotalDeSesNoeudsEnbifurcationDansUneAlternativeDonnee, "-");
	 
	 Ct.agentParameter.put( Ct.MoyenneDeSesNoeudsEnBifurcationSurEnsembleDeSesNoeuds, Integer.toString(getTotalBifurcationNodesPerAgentList(agPosition).size() /aPlan.graphPlan.getNodeCount()));
	 Ct.agentParameter.put( Ct.MoyenneDeSesNoeudsEnBifurcationSurEnsembleDesNoeudsImpliques, "-");
	 Ct.agentParameter.put( Ct.MoyenneDeSesNoeudsEnBifurcationSurEnsembleDesActionsQuIlAccepte, "-");
	 Ct.agentParameter.put( Ct.LeGainAttenduDeLui, "-");
	 Ct.agentParameter.put( Ct.LeGainReelObtenuDeLui, "-");
	 Ct.agentParameter.put( Ct.LeGainPerduSurLui, "-");
		
} // fin de putDiscussionParametersByAgentInHashTable
//----------------------------------------------------------------

//
//---------------------------------------------------------------------------
/**
 * Enregistrement du RESUME SUR TOUT LES AGENTS dans la base de donn�es
 * @param localPlan Le plan local de l'agent en cours
 * @param agPlanList  Liste des plans de tous les agents
 * @param Ct Instance de la classe Constante
 * @param stepNbr L'�tape locale
 * @param globalStep L'�tape Globale
 * @throws SQLException 
 */
public void putDiscussionParametersByAgentInDataBase(AgentPlan localPlan, ArrayList agPlanList, Constants Ct, int stepNbr, int globalStep) throws SQLException{
	 
	for(int i=0; i<involvedAgentsList.size(); i++){
		String agent  = (String) involvedAgentsList.get(i);
		for(int h=0; h<agPlanList.size(); h++){
		
			AgentPlan aPlan = (AgentPlan) agPlanList.get(h);
			if(aPlan.agentOwner.equals(agent)){
				Ct.agentParameter.clear();
				putDiscussionParametersByAgentInHashTable(localPlan, aPlan, i, Ct, stepNbr, globalStep);
				db.addNewRow("agent", Ct.getAgentParameterInTableField(), Ct.getAgentParameterInTableValue());
				
			} // fin de if
		} // fin de for
	} // fin de for
} // fin de displayDiscussionParameters
//----------------------------------------------------------------



/**
 * RESUME SUR TOUT LES AGENTS
 * @param localPlan Plan de l'agent en cours
 * @param agPlanList Liste des plans de tous les agents
 */
public void displayDiscussionParametersByAgent(AgentPlan localPlan, ArrayList agPlanList){
	 
	for(int i=0; i<involvedAgentsList.size(); i++){
		String agent  = (String) involvedAgentsList.get(i);
		for(int h=0; h<agPlanList.size(); h++){
		
			AgentPlan aPlan = (AgentPlan) agPlanList.get(h);
			if(aPlan.agentOwner.equals(agent)){
		 
		
				System.out.println("----------------------------------------------------------------------------------------------");
				System.out.println("--------          Discussion Prameters To Complete: about the Agent : "+agent+"    -----------");
				System.out.println("----------------------------------------------------------------------------------------------");
				
				System.out.println(" - Total des actions : "+aPlan.totalTasks);
				System.out.println(" - Total des alternatives : "+aPlan.pathList.size());
				System.out.println(" ");
				
				System.out.println(" - Liste des alternatives impliqu�es : "+getInvolvedAlternativeList(i).toString());
				System.out.println(" - Total des alternatives impliqu�es : "+getInvolvedAlternativeList(i).size());
				System.out.println(" ");
				
				System.out.println(" - Liste des actions en commun : "+getAllCommonActionList(localPlan, aPlan, i).toString());
				System.out.println(" - Total des actions en commun : "+getAllCommonActionList(localPlan, aPlan, i).size());
				System.out.println(" ");
				
				System.out.println(" - Liste des actions impliqu�es : " + getAllInvolvedActionsList(i).toString());
				System.out.println(" - Total des actions impliqu�es : " + getAllInvolvedActionsList(i).size());
				System.out.println(" ");
				
				System.out.println(" - Total des actions non impliqu�es : ");
				System.out.println(" ");
				
				System.out.println(" - List des agents impliqu�s avec lui : "+getAllAgentsInvolvedWith(i));
				System.out.println(" - Total des agents impliqu�s avec lui : "+getAllAgentsInvolvedWith(i).size());
				System.out.println(" ");
				
				System.out.println(" - List des agents qui ont accept�s avec lui : "+getAllAgentsAccepteddWith(i));
				System.out.println(" - Total des agents qui ont accept�s avec lui : "+getAllAgentsAccepteddWith(i).size());
				System.out.println(" ");
				
				System.out.println(" - Liste des actions impliqu�es (des actions des autres agents) avec lui : "+getAllActionsInvolvedWith(i));
				System.out.println(" - Total des actions impliqu�es (des actions des autres agents) avec lui : "+getAllActionsInvolvedWith(i).size());
				System.out.println(" ");
				
				System.out.println(" - Liste des actions impliqu�es et accept�es (des actions des autres agents) avec lui : "+getAllActionsAcceptedWith(i));
				System.out.println(" - Total des actions impliqu�es et accept�es (des actions des autres agents) avec lui : "+getAllActionsAcceptedWith(i).size());
				System.out.println(" ");
				
				System.out.println(" - Moyenne de ses actions impliqu�es dans toutes les alternatives sur le total de ses actions : ");
				
				System.out.println(" - Moyenne de ses actions impliqu�e (dans une alternative donn�e) sur le total de ses actions : ");
				System.out.println(" - Total des agents qui ont accept�s les propositions avec sa pr�sence : ");
				System.out.println(" - Total des agents qui n�ont pas accept�s les propositions avec sa pr�sence : ");
				System.out.println(" - Total de ses n�uds : ");
				
				System.out.println(" - Liste de ses n�uds en bifurcation : "+getTotalBifurcationNodesPerAgentList( i));
				System.out.println(" - Total de ses n�uds en bifurcation : "+getTotalBifurcationNodesPerAgentList( i).size());
				
				System.out.println(" - Total de ses n�uds en bifurcation dans mes propositions : ");
				System.out.println(" - Total de ses n�uds en bifurcation dans une alternative donn�e : ");
				System.out.println(" - Moyenne de ses n�uds en bifurcation sur l�ensemble de ses n�uds : ");
				System.out.println(" - Moyenne de ses n�uds en bifurcation sur l�ensemble des n�uds impliqu�s : ");
				System.out.println(" - Moyenne de ses n�uds en bifurcation sur l�ensemble des actions qu�il accepte : ");
				System.out.println(" - ");
				System.out.println(" - ");
				System.out.println(" - Le gain attendu de lui : ");
				System.out.println(" - Le gain r�el obtenu de lui : ");
				System.out.println(" - Le gain perdu sur lui : ");
				System.out.println(" - ");
				System.out.println(" - ");
				
			} // fin de if
		} // fin de for
	} // fin de for
} // fin de displayDiscussionParameters
//----------------------------------------------------------------
	


/**
 * Retourner une liste des alternatives impliqu�es dans la discussion, pour un agent donn�es.
 * @param agentPosition la position de l'agent dans la liste des agents impliqu�s
 * @return
 */
public ArrayList getInvolvedAlternativeList(int agentPosition){
	ArrayList involvedAlternatives = new ArrayList();
	
	int Tab[][] = (int[][]) this.countInvolvedAlternativesByAgentList.get(agentPosition);
	
	for(int i=0; i<Tab.length; i++){
		if(Tab[i][1] > 0)
			involvedAlternatives.add(Tab[i][0]);
	} // fin de for
	
	return involvedAlternatives;
} // fin de getInvolvedAlternativeList
//------------------------------------------------------------------------------------------



/**
 * Retourner une liste de toutes les actions impliqu�s impliqu�es dans la discussion, pour un agent donn�es.
 * @param agentPosition la position de l'agent dans la liste des agents impliqu�s
 * @return
 */
public ArrayList getAllInvolvedActionsList(int agentPosition){
	ArrayList involvedActionsList = new ArrayList();
	
	String agent = (String) involvedAgentsList.get(agentPosition);
	
	for(int i=0; i<this.coalitionList.size(); i++){
		Coalition c = (Coalition) this.coalitionList.get(i);
		for(int j=0; j<c.agentList.size(); j++){
			String ag = (String) c.agentList.get(j);
			if(ag.equals(agent)){
				for(int t=0; t<c.edgeList.size(); t++){
					String e = (String) c.edgeList.get(t);
						involvedActionsList.add(e);
				}
			}
		}
	} // fin de for
	return involvedActionsList;
} // fin de getAllInvolvedActionsList
//------------------------------------------------------------------------------------------



/**
 * Retourner la liste de toutes les actions en commun entre les deux plans 
 * @param localPlan le plan local de l'agent
 * @param aPlan Le Plan de l'agent � explorer
 * @param agentPosition la position de l'agent dans la liste des agents impliqu�s
 * @return
 */
public ArrayList getAllCommonActionList(AgentPlan localPlan, AgentPlan aPlan, int agentPosition){
	ArrayList allCommonActionList = new ArrayList();
	
	String agent = (String) involvedAgentsList.get(agentPosition);
	
	for(Edge e1:aPlan.graphPlan.getEachEdge()){
		for(Edge e2:localPlan.graphPlan.getEachEdge()){
			if(e1.getId().equals(e2.getId())){
				allCommonActionList.add(e1.getId());
			}
		}
	}
	return allCommonActionList;
} // fin de getAllCommonActionList
//------------------------------------------------------------------------------------------


/**
 * Retourner la liste de tous les agents qui sont pr�sents si l'agent en entr�e est pr�sent lui aussi.
 * @param agentPosition La position de l'agent dans la liste des agents impliqu�s
 * @return
 */
public ArrayList getAllAgentsInvolvedWith(int agentPosition){
	
	ArrayList allAgentInvolvedWithList = new ArrayList();
	
	String agent = (String) involvedAgentsList.get(agentPosition);
	
	for(int i=0; i<this.coalitionList.size(); i++){
		Coalition  c = (Coalition) this.coalitionList.get(i);
		boolean exist = false;
		
		for(int j=0; j<c.agentList.size(); j++){
			String ag = (String) c.agentList.get(j);
			if(ag.equals(agent)) {
				exist = true;
			}
		} // fin de for
		
		if(exist)
		for(int j=0; j<c.agentList.size(); j++){
			String ag = (String) c.agentList.get(j);
			if((!ag.equals(agent)) && (! this.agentOwner.equals(ag))){
				if(!allAgentInvolvedWithList.contains(ag))
				allAgentInvolvedWithList.add(ag);
			}
		} // fin de for
		
	} // fin de for
	
	return allAgentInvolvedWithList;
} // fin de getAllAgentsInvolvedWith
//------------------------------------------------------------------------------------------




/**
 * Retourner la liste de tous les agents qui ont accept�s avec la pr�sence de l'agent en entr�e. 
 * @param agentPosition la position de l'agent dans la liste des agents impliqu�s
 * @return
 */
public ArrayList getAllAgentsAccepteddWith(int agentPosition){
	
	ArrayList allAgentAcceptedWithList = new ArrayList();
	
	String agent = (String) involvedAgentsList.get(agentPosition);
	
	for(int i=0; i<this.coalitionList.size(); i++){
		Coalition  c = (Coalition) this.coalitionList.get(i);
		boolean exist = false;
		
		for(int j=0; j<c.agentAcceptedList.size(); j++){
			String ag = (String) c.agentAcceptedList.get(j);
			if(ag.equals(agent)) {
				exist = true;
			}
		} // fin de for
		
		if(exist)
		for(int j=0; j<c.agentAcceptedList.size(); j++){
			String ag = (String) c.agentAcceptedList.get(j);
			if((!ag.equals(agent)) && (! this.agentOwner.equals(ag))){
				if(!allAgentAcceptedWithList.contains(ag))
					allAgentAcceptedWithList.add(ag);
			}
		} // fin de for
		
	} // fin de for
	
	return allAgentAcceptedWithList;
} // fin de getAllAgentsAccepteddWith
//------------------------------------------------------------------------------------------




/**
 * Retourner la liste de toutes les actions impliqu�es avec d'autres agents � la pr�sence de l'agent en entr�e
 * @param agentPosition La position de l'agent dans la liste des agents impliqu�s
 * @return
 */
public ArrayList getAllActionsInvolvedWith(int agentPosition){
	
	ArrayList allActionsInvolvedWithList = new ArrayList();
	
	String agent = (String) involvedAgentsList.get(agentPosition);
	
	for(int i=0; i<this.coalitionList.size(); i++){
		Coalition  c = (Coalition) this.coalitionList.get(i);
		boolean exist = false;
		
		for(int j=0; j<c.agentList.size(); j++){
			String ag = (String) c.agentList.get(j);
			if(ag.equals(agent)) {
				exist = true;
			}
		} // fin de for
		
		if(exist)
		for(int j=0; j<c.agentList.size(); j++){
			String ag = (String) c.agentList.get(j);
			if((!ag.equals(agent)) && (! this.agentOwner.equals(ag))){
				
				for(int h=0; h<c.edgeList.size(); h++){
					String e = (String) c.edgeList.get(h);
					if(!allActionsInvolvedWithList.contains(e))
						allActionsInvolvedWithList.add(e);
				}
			} // fin de if
		} // fin de for
		
	} // fin de for
	
	return allActionsInvolvedWithList;
} // fin de getAllActionsInvolvedWith
//------------------------------------------------------------------------------------------




/**
 * (M�thode � revoir) Retourner la liste de toutes les actions impliqu�es avec d'autres agents � la pr�sence de l'agent en entr�e 
 * @param agentPosition La position de l'agent dans la liste des agents impliqu�s
 * @return
 */
public ArrayList getAllActionsAcceptedWith(int agentPosition){
	
	ArrayList allActionsAcceptedWithList = new ArrayList();
	
	String agent = (String) involvedAgentsList.get(agentPosition);
	
	for(int i=0; i<this.coalitionList.size(); i++){
		Coalition  c = (Coalition) this.coalitionList.get(i);
		boolean exist = false;
		
		for(int j=0; j<c.agentAcceptedList.size(); j++){
			String ag = (String) c.agentAcceptedList.get(j);
			if(ag.equals(agent)) {
				exist = true;
			}
		} // fin de for
		
		if(exist)
		for(int j=0; j<c.agentAcceptedList.size(); j++){
			String ag = (String) c.agentAcceptedList.get(j);
			if((!ag.equals(agent)) && (! this.agentOwner.equals(ag))){
				
				for(int h=0; h<c.edgeList.size(); h++){
					String e = (String) c.edgeList.get(h);
					if(!allActionsAcceptedWithList.contains(e))
						allActionsAcceptedWithList.add(e);
				}
			} // fin de if
		} // fin de for
		
	} // fin de for
	
	return allActionsAcceptedWithList;
} // fin de getAllActionsAcceptedWith
//------------------------------------------------------------------------------------------


//M�thode calculer la liste totales des noeuds en bifurcation pour chaque un agent 
//-------------------------------------------------------------------
public ArrayList getTotalBifurcationNodesPerAgentList(int agPosition){
			return (ArrayList) this.totalBifurcationNodesPerAgentList.get(agPosition);
} // fin de getTotalBifurcationNodesPerAgentList
//------------------------------------------------------------------




// M�thode pour afficher les distances vers les alternatives impliqu�
//-------------------------------------------------------------------
public void displayDistanceToInvolvedAlternatives(ArrayList agPlanList){
	
System.out.println(" The distance to the Involved Alternatives, to the active discussion:");
System.out.println(" -------------------------------------------------------------- ");
	 	
	 for(int i=0; i<involvedAgentsPathsListInTables.size(); i++){
	 	
		System.out.println(" Alternatives of the Agent : "+involvedAgentsList.get(i));
		int T[][] = (int[][]) involvedAgentsPathsListInTables.get(i);
			
		ArrayList agentPathList = new ArrayList();
		 for(int h=0; h<agPlanList.size(); h++){
			AgentPlan aPlan = (AgentPlan) agPlanList.get(h);
				if(aPlan.agentOwner.equals(involvedAgentsList.get(i))){
					agentPathList =  aPlan.pathList;
					for(int j=0; j<T.length; j++ ){
						//System.out.println("----> j  --> "+j);
						Graph path = (Graph) agentPathList.get(j);
						System.out.println("    -------------");
						System.out.println(" 	Alternative : "+j);
						
						System.out.println(" 	Size : "+path.getEdgeSet().size());
						System.out.println(" 	"+path.getEdgeSet().toString());
						System.out.println("  	Distance : " + Tr.computeAlternativesDIstance(this.discussionPath, path));
					 } // fin de for
				}
	 		}
	 } // fin de for
	 
} // fin de displayDistanceToInvolvedAlternatives
//------------------------------------------------------------------

/**
 * retourne le Type de r�sultat. 2 si une solution est trouv�e. sinon, 0
 * @return 2 si solution trouv�e, sinon 0.
 */
public int getResultType(){
	int resultType = 0;
	
	for(int i=0; i<coalitionList.size(); i++){
		Coalition c = (Coalition) coalitionList.get(i);
		for(int j=0; j<c.agentRecievedConfirmationList.size(); j++){
			String ag = (String) c.agentRecievedConfirmationList.get(j);
			if(!ag.equals(agentOwner)){
				resultType = 2;
			}
		}
		
	}
	
	
	return resultType;
}


/**
 * Retourner la liste des agents impliqu�s dans la discussion, et dont leurs acceptations sont requises
 * @return Liste d'agents.
 */
public ArrayList getNeededAgentListForDiscussion(){
	ArrayList agentList = new ArrayList();
	for (Iterator iterator = coalitionList.iterator(); iterator.hasNext();) {
		Coalition c = (Coalition) iterator.next();
		
		for (Iterator iterator2 = c.agentList.iterator(); iterator2.hasNext();) {
			String agent = (String) iterator2.next();
			if((!agentList.contains(agent)) && (!agent.equals(agentOwner))){
				agentList.add(agent);
			}
		}
	}
	
	return agentList;
}



/**
 * Retourner la liste des agents impliqu�s dans la discussion pour une action donn�e
 * @return Liste d'agents.
 */
public ArrayList getNeededAgentListForDiscussionByAction(String action){
	ArrayList agentList = new ArrayList();
	for (Iterator iterator = coalitionList.iterator(); iterator.hasNext();) {
		Coalition c = (Coalition) iterator.next();
		
		if(c.edgeList.contains(action))
		for (Iterator iterator2 = c.agentList.iterator(); iterator2.hasNext();) {
			String agent = (String) iterator2.next();
			if((!agentList.contains(agent)) && (!agent.equals(agentOwner))){
				agentList.add(agent);
			}
		}
	}
	
	return agentList;
}



/**
 * Calculer le co�t estim� moyen par agents.
 */
public void computeAvgEstimetedCostByAgent(){
	
	ArrayList agentsList = this.getNeededAgentListForDiscussion();
	
	if(agentsList.size()>0){
		avgEstimetedCostByAgent  = this.discussionEstimatedCost / agentsList.size();
	}else{
		avgEstimetedCostByAgent =  this.discussionEstimatedCost ;
	}
	 
}

/**
 * Calculer le co�t r�el moyen par agent
 */
public void computeAvgDiscussionRealCostByAgent(){
	
	ArrayList agentsList = this.getNeededAgentListForDiscussion();
	
	if(agentsList.size()>0){
		avgDiscussionRealCost  = this.discussionFinalCost / agentsList.size();
	}else{
		avgDiscussionRealCost =  this.discussionFinalCost ;
	}
	 
}

/**
 * Retourner la liste des actions dans une liste
 * @return
 */
public ArrayList getActionsInList(){
	ArrayList actionList = new ArrayList();
	
	for(Edge e:discussionPath.getEachEdge()){
		actionList.add(e.getId());
	}
	return actionList;
}

/**
 * retourner la taille du profil de la discussion
 * @return la taille tu profil de la discussion
 */
public int getDiscussionProfileSize(){
	return this.discussionProfil.size();
}

/**
 * retourner un profil vide de la discussion (Traiter le cas ou un agent i n'a pas envoy� une proposition � une �tape e+1) 
 * @return un profil vide de la discussion 
 */
public ArrayList getEmptyDiscussionProfile(){
	ArrayList emptyProfil = new ArrayList();
	
	for(int e=0; e<discussionProfil.size(); e++){
		emptyProfil.add(0);
	}
	
	return emptyProfil;
}

/**
 * retourner le nombre d'actions pr�sentes dans le profil de la discussion
 * @return Le nombre d'actions pr�sentes dans le profil de la discussion
 */
public int getActionsNumberInProfile(){
	int nbr = 0;
	for(int i=0; i<this.discussionProfil.size(); i++){
		nbr+= (int) this.discussionProfil.get(i);
	}
	return nbr;
}



/**
 * Reset the lists containing conversations history, to gain memory space.
 */
public void restHistoricalList(){
	this.acceptaceIndicatorHistory.clear();
	this.ConvDegreeH.clear();
	this.discRank.clear();
	this.wH.clear();
	this.eCostH.clear();
	this.rFH.clear();
	this.sFH.clear();
	this.ConvIndH.clear();
	this.wV.clear();;
	this.eCostV.clear();
	this.rFV.clear();
	this.sFV.clear();
	this.ConvIndV.clear();
	this.wValue.clear();
	this.eCostValue.clear();
	this.rFValue.clear();
	this.sFValue.clear();
	this.ConvIndValue.clear();
}





/**
 * Check if there is need to proceed to resumption in order to improve the found solution.
 * @return
 */
public boolean checkNeedResumption(){
	boolean isNeed = false;
	
	for(int i=0; i<coalitionList.size(); i++){
		Coalition c =  (Coalition) coalitionList.get(i);
			if(c.agentList.size()>1)
				return true;
	}
	
	return isNeed;
}


public ArrayList getAllInvolvedAgents(){
	ArrayList involvedAgents = new ArrayList();
	
	for(int i=0; i<coalitionList.size(); i++){
		Coalition c = (Coalition) coalitionList.get(i);
		for(int j=0; j<c.agentList.size(); j++){
			String ag = (String) c.agentList.get(j);
			if(!involvedAgents.contains(ag))
				involvedAgents.add(ag);
		}
	}
	
	return involvedAgents;
}



	/**
	 * retourne le nombre d'instance des agents qui participe dans larealisation
	 * de la discussion (autre que l'agent propri�taire)
	 * 
	 * @return
	 */
	public int getTotalAgent() {

		ArrayList totalAgent = new ArrayList();
		int ag = Integer.parseInt(
		        this.agentOwner.substring(5, this.agentOwner.length()));

		for( int i = 0; i < coalitionList.size(); i++ ) {
			Coalition c = (Coalition) coalitionList.get(i);
			for( int j = 0; j < c.agentForCoalitionList.size(); j++ ) {
				int agent = (int) c.agentForCoalitionList.get(j);
				if (ag != agent) {
					totalAgent.add(agent);
				}
			}

			/*
			 * if(c.agentForCoalitionList.size()>1){ total+=
			 * c.agentForCoalitionList.size(); }
			 */
		}

		return totalAgent.size();
	}

} // fin de la calsse. 
