package com.cfdce.Plan;

//import jade.util.leap.ArrayList;
import java.util.ArrayList;
import java.util.Collection;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;


public class AgentPlan {

	public String agentOwner;   // l'agent propri�taire du plan
	public Graph graphPlan;   // le plan au format graphe
	public ArrayList pathList;  // la liste des alternative dans le plan
	public int altCount;      // le nombre d'alternative dans le plan de l'agent.
	
	
	public int totalTasks = 0; // le nombre Total des taches dans le Plan.
	public int totalBifurcationNodes = 0;// le nombre total des noeuds en bifurcation.
	public ArrayList totalBifurcationNodesList = new ArrayList(); // liste de tousles noeuds en bifurcation
	
	
	public AgentPlan(){

	} // fin du constructeur
	
	public AgentPlan(String agOwner, Graph agPlan, ArrayList agPathList){
		this.agentOwner = agOwner;
		this.graphPlan = agPlan;
		this.pathList = agPathList;
		this.altCount = agPathList.size();
		this.totalTasks = agPlan.getEdgeCount();
		computeTotalBifurcationNodes();
	} // fin du constructeur
	
	

// m�thode qui calcule le nombre total des noeuds en bifurcation
//-----------------------------------------------------------------------------
	public void computeTotalBifurcationNodes(){
			for(Node n:graphPlan){
			if(n.getOutDegree()>1){
				this.totalBifurcationNodes++;
				this.totalBifurcationNodesList.add(n);
			}
		} // fin de for
	} // fin de computeTotalBifurcationNodes
//------------------------------------------------------------------------------
	
// m�thode qui retourne le nombre de noeuds en bifurcation dans une alternative
//-----------------------------------------------------------------------------
	public int getBifurcationNodesNbr(Graph plan, Graph path){
			return getBifurcationNodesList(plan, path).size();
	} // fin de getBifurcationNodesNbr
//------------------------------------------------------------------------------

// m�thode qui retourne le nombre de noeuds en bifurcation dans une alternative // version avec position de l'alternative
//-----------------------------------------------------------------------------
	public int getBifurcationNodesNbr(Graph plan, int pathPosition){
		Graph path = (Graph) this.pathList.get(pathPosition);
			return getBifurcationNodesList(plan, path).size();
	} // fin de getBifurcationNodesNbr
//------------------------------------------------------------------------------
	

//-----------------------------------------------------------------------------
	public ArrayList getBifurcationNodesList(Graph plan, Graph path){
		
		ArrayList bifurcationNodesList1 = new ArrayList(); 
	//	System.out.println("1>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Plan "+plan.getEdgeSet().toString());
	//	System.out.println("2>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Path "+path.getEdgeSet().toString());
	//	System.out.println("3>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> "+this.getBifurcationNodesNbr(plan, path));
		int nbr = 0;
		for(Node n:plan){
			if(n.getOutDegree()>1){
				boolean exist = false;
				for(Node n2:path){
					if(n.getId().equals(n2.getId()))
						exist = true;
				}
				if(exist){
					bifurcationNodesList1.add(n);
					nbr++;
				}
			}
		} // fin de for
		
	//	Node start = path.getNode("start");
		
	//	bifurcationNodesList1.add(start);// � ne pas comptabiliser. juste pour �viter le probl�me de liste vides
		
		return bifurcationNodesList1;
	} // fin de getBifurcationNodesCollection
//------------------------------------------------------------------------------

// m�thode qui retourne la liste des noeuds en bifurcation dans une alternative  // version avec position de l'alternative
//-----------------------------------------------------------------------------
	public ArrayList getBifurcationNodesList(Graph plan, int pathPosition){
		Graph path = (Graph) this.pathList.get(pathPosition);
		ArrayList bifurcationNodesList1 = new ArrayList();
		
		Node start = path.getNode("start");
		bifurcationNodesList1.add(start); // � ne pas comptabiliser. juste pour �viter le probl�me de liste vides
		
		for(Node n:plan){
			if(n.getOutDegree()>1){
				boolean exist = false;
				for(Node n2:path){
					if(n.getId().equals(n2.getId()))
						exist = true;
				}
				if(exist){
					bifurcationNodesList1.add(n);
				}
			}
		} // fin de for
		return bifurcationNodesList1;
	} // fin de getBifurcationNodesList
//------------------------------------------------------------------------------
	
	
// m�thode qui retourne la liste des noeuds en bifurcation dans une alternative  // version avec position de l'alternative
//-----------------------------------------------------------------------------
	public String getBifurcationNodesStrList(Graph plan, int pathPosition){
		String result = " ";
		Graph path = (Graph) this.pathList.get(pathPosition);
		ArrayList bifurcationNodesList1 = new ArrayList();
		
		Node start = path.getNode("start");
		bifurcationNodesList1.add(start); // � ne pas comptabiliser. juste pour �viter le probl�me de liste vides
		
		for(Node n:plan){
			if(n.getOutDegree()>1){
				boolean exist = false;
				for(Node n2:path){
					if(n.getId().equals(n2.getId()))
						exist = true;
				}
				if(exist){
					bifurcationNodesList1.add(n);
				}
			}
		} // fin de for
		return result+bifurcationNodesList1.toString();
		
	} // fin de getBifurcationNodesStrList
//------------------------------------------------------------------------------
	
// m�thode qui retourne la liste des noeuds en bifurcation dans une alternative  // version avec position de l'alternative
//-----------------------------------------------------------------------------
	public String getBifurcationNodesStrList(Graph plan, Graph path){
		String result = " ";
		
		ArrayList bifurcationNodesList1 = new ArrayList();
		
		Node start = path.getNode("start");
		bifurcationNodesList1.add(start); // � ne pas comptabiliser. juste pour �viter le probl�me de liste vides
		
		for(Node n:plan){
			if(n.getOutDegree()>1){
				
				boolean exist = false;
				for(Node n2:path){
					if(n.getId().equals(n2.getId()))
						exist = true;
				}
				if(exist){
					bifurcationNodesList1.add(n);
				}
			}
		} // fin de for
		return result+bifurcationNodesList1.toString();
		
	} // fin de getBifurcationNodesStrList
//------------------------------------------------------------------------------
		
// m�thode qui retourne les noeuds d'une alternative dans une liste 
//-----------------------------------------------------------------------------
	public Collection<Node> getPathNodesInList(Graph path){
		
		return path.getNodeSet();
	} // fin de getPathNodesInList
//------------------------------------------------------------------------------
	
	
// m�thode qui retourne les noeuds d'une alternative dans une liste  // version avec position de l'alternative
//-----------------------------------------------------------------------------
	public Collection<Node> getPathNodesInList(int  pathPosition){
		Graph path = (Graph) this.pathList.get(pathPosition);

		return path.getNodeSet();
	} // fin de getPathNodesInList
//------------------------------------------------------------------------------
	
	
// m�thode qui retourne les noeuds d'un alternative dans une liste 
//-----------------------------------------------------------------------------
	public Collection<Edge> getPathEdgesInList(Graph path){
		
		return path.getEdgeSet();
	} // fin de getPathEdgesInList
//------------------------------------------------------------------------------
	
// m�thode qui retourne les noeuds d'un alternative dans une liste  // version avec position de l'alternative
//-----------------------------------------------------------------------------
	public Collection<Edge> getPathEdgesInList(int pathPosition){
		Graph path = (Graph) this.pathList.get(pathPosition);
		return path.getEdgeSet();
	} // fin de getPathEdgesInList
//------------------------------------------------------------------------------
	
	
	
	
	
	
	

/**
 * Afficher le graph en rouge, fin de temps des n�gociations 
 * @param g : Le graphe
 * @param globalRound : le round global du processus de formation de coalitions.
 */
public void printFailledNegotiation(int globalRound){
	

	PlanManagment pM = new PlanManagment(agentOwner);

	Node e = pM.getRoot(graphPlan);
	e.setAttribute("ui.label", " ("+globalRound+") "+"_"+ agentOwner);
		for(Edge e2:graphPlan.getEachEdge()) {
			e2.addAttribute("ui.label", e2.getId()+" ("+e2.getAttribute("oldLabel")+")");
			e2.setAttribute("ui.class", "red");
			e2.addAttribute("ui.style", "fill-color: red;");
		} // fin de for

	
	graphPlan.addAttribute("ui.quality");
	graphPlan.addAttribute("ui.antialias");
	
} // fin de printConfirmedFinalPathOnInitialPlan
//----------------------------------------------------------

	
	
	
	
	
	
} // Fin de la classe
