package com.cfdce.Plan;

import java.io.IOException;
//import jade.util.leap.ArrayList;
import java.util.ArrayList;
//import java.util.ArrayList;
import java.util.Iterator;

import org.graphstream.algorithm.Dijkstra;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.Path;
import org.graphstream.graph.implementations.SingleGraph;

import com.cfdce.Control.Traitment;
import com.cfdce.FormationCoalitions.Coalition;
import com.cfdce.FormationCoalitions.coalitionProposalMessage;

public class PlanManagment {
	
	public String agent = "";
	public int counter = 0;
	public static int counter1 = 0;
	
	//extraActionCost extraC = new extraActionCost("");
	
	public PlanManagment(String ag){
		this.agent = ag;
		counter = counter1;
		this.counter1++;
		
		//extraActionCost.agent =ag;
	}
	
	static ArrayList totalEdgeList = new ArrayList();
	
	static ArrayList actionCostsList = new ArrayList();
	
	static int pathNbr = 2;
public Node addNode(Graph g, String nodeLabel, int x, int y, int z){
	
			g.addNode(nodeLabel); 
			Node nd = g.getNode(nodeLabel);
			nd.setAttribute("ui.label", nodeLabel);	
			nd.setAttribute("xyz", x,y,z);
			
		return nd;
	} // fin de addNode
	
	
public Node addNode(Graph g, String nodeLabel, String nodeClass, int x, int y, int z){

		g.addNode(nodeLabel); 
		Node nd = g.getNode(nodeLabel);
		nd.setAttribute("ui.label", nodeLabel);	
		nd.addAttribute("ui.class", nodeClass);
		nd.setAttribute("xyz", x,y,z);
		
		return nd;
} // fin de addNode avec sp�cification de classe 
	
	
//----------------------------------------------------------

	
	

	
//-------------------------------------------------------------
// Conversion d'un graphe en un objet planMessage
public planMessage convertToPlanMessageObject(Graph g){
	String name = g.getId();
	int taskNumber = g.getEdgeCount();
	String[] taskList = new String[taskNumber];
	String[] ListA = new String[taskNumber];
	String[] ListB = new String[taskNumber];
	
	int i = 0;
	for(Edge e:g.getEachEdge()) {
		taskList[i] = e.getId();
		ListA[i] = e.getSourceNode().getId();
		ListB[i] = e.getTargetNode().getId();
		
		i++;
	}
	planMessage planMsg = new planMessage(name, taskNumber, taskList, ListA, ListB);
	return planMsg;
} // fin de convertToPlanMessageObject
	
//-------------------------------------------------------------



//-------------------------------------------------------------
//Conversion d'un objet plan en message en un graph 
public Graph convertToGraph(planMessage planMsg){
	
	Graph g = new SingleGraph(planMsg.planName);
	
	g.addAttribute("ui.stylesheet", "url('file:config/Modele1.css')");
	g.setStrict(false);
	g.setAutoCreate( true ); // cr�ation automatique des noeuds.
	
	/*
	for(int i=0; i<planMsg.taskList.length; i++){
		String edge = planMsg.taskList[i];
		g.addEdge( edge, edge.substring(0, 1), edge.substring(1, 2), true  );
	}
	*/
	
	for(int i=0; i<planMsg.listA.length; i++){
		String edge = planMsg.listA[i]+planMsg.listB[i];
		g.addEdge( edge, planMsg.listA[i], planMsg.listB[i], true  );
	}
	
	
	for(Node n:g) {
		n.setAttribute("ui.label", "City "+n.getId());	
	}
	
	return g;
} // fin de convertToGraph
	
//-------------------------------------------------------------

//-------------------------------------------------------------
//Conversion d'une proposition de coalition en un objet coalitionProposalMessage
public coalitionProposalMessage convertToCoalitionProposalMessageObject(int discId , Coalition c){
	

	
	String[] agList = new String[c.agentList.size()];
	String[] actList = new String[c.edgeList.size()];
	
	for(int i=0; i<c.agentList.size(); i++){
		String ag = (String) c.agentList.get(i);	
		agList[i] = ag;
	} // fin de for
	
	for(int i=0; i<c.edgeList.size(); i++){
		String e = (String) c.edgeList.get(i);	
		actList[i] = e;
	} // fin de for
		
	coalitionProposalMessage cpMsg = new coalitionProposalMessage(discId, c.coalitionId, agList, actList);
	
	
	return cpMsg;
} // fin de convertToCoalitionProposalMessageObject
	
//-------------------------------------------------------------


//-------------------------------------------------------------
//Conversion d'un message de proposition de type Objet en une coalition 
public Coalition convertToCoalitionProposal(String agent,coalitionProposalMessage cPmsg){
	
	
	String cId = cPmsg.coalitionId;
	ArrayList agList = new ArrayList();
	ArrayList edList = new ArrayList();
	
	for(int i=0; i<cPmsg.agentList.length; i++){
		agList.add(cPmsg.agentList[i]);
		
	} // fin de for
	
	
	for(int i=0; i<cPmsg.actionList.length; i++){
		edList.add(cPmsg.actionList[i]);
				
	} // fin de for
	
	
	Coalition c = new Coalition(cId, agList, edList, agent);
	c.discussionId = cPmsg.discussionId;
		

	return c;
} // fin de convertToCoalitionProposal
	
//-------------------------------------------------------------


//----------------------------------------------------------
//- collecter la tache debut  
public Node getRoot(Graph g){
			
	Node root = null;
	
	for(Node n:g) {
		if(n.getInDegree()==0){
			root = n;
			return n;
		}
	}
	 
	return root;
} // fin de getRoot
//----------------------------------------------------------


//----------------------------------------------------------
//- collecter la tache but  
public Node getGoal(Graph g){
			
	Node root = null;
	
	for(Node n:g) {
		if(n.getOutDegree()==0){
			root = n;
			return n;
		}
	}
	 
	return root;
} // fin de getGoal
//----------------------------------------------------------


//-------------------------------------------------------------
//Chercher le nombre d'alternatives dans un plan  

public Iterator<Path> getDijkstraAllPaths(Graph g){
	Iterator<Path> allPaths = null;

//	Dijkstra dijkstra = new Dijkstra(Dijkstra.Element.EDGE, null, costName);
//	dijkstra.init(graph);
//	dijkstra.setSource(graph.getNode(sourceId));
//	dijkstra.compute();
	
	Dijkstra Dj = new Dijkstra(Dijkstra.Element.NODE, "result", "length");
	Dj.init(g);
	Dj.setSource(this.getRoot(g));
	
	Dj.compute();
	
	//Dijkstra Dj = new Dijkstra();
	allPaths = Dj.getAllPathsIterator(this.getGoal(g));
	
	Iterable<Path>  allPaths3 = Dj.getAllPaths(this.getGoal(g));
	
//-------------------------------------------
	//---------------------------------------
	
	// Edge lengths are stored in an attribute called "length"
	// The length of a path is the number of its edges
	Dijkstra dijkstra = new Dijkstra(Dijkstra.Element.EDGE, null, null);

	// Compute the shortest paths in g from A to all nodes
	dijkstra.init(g);
	dijkstra.setSource(this.getRoot(g));
	dijkstra.compute();
	
	// Print the lengths of all the shortest paths
	for (Node node : g)
			System.out.printf("%s->%s:%10.2f%n", dijkstra.getSource(), node, dijkstra.getPathLength(node));
	
	// Color in yellow all the nodes on the shortest path form Root to Goal
	for (Node node : dijkstra.getPathNodes(this.getGoal(g)))
		node.addAttribute("ui.style", "fill-color: yellow;");
	
	// Color in yellow all the edges in the shortest path form Root to Goal
	for (Edge edge : dijkstra.getTreeEdges())
		edge.addAttribute("ui.style", "fill-color: yellow;");
	
	// Print the shortest path from Root to Goal
	System.out.println(dijkstra.getPath(this.getGoal(g)));
	
	
	// Build a list containing the nodes in the shortest path from A to B
	// Note that nodes are added at the beginning of the list
	// because the iterator traverses them in reverse order, from B to A
/**
	List<Node> list1 = new ArrayList<Node>();
		for (Node node : dijkstra.getPathNodes(this.getGoal(g)))
			list1.add(0, node);	
	**/
	
	// A shorter but less efficient way to do the same thing
	//List<Node> list2 = dijkstra.getPath(this.getGoal(g)).getNodePath();

	// cleanup to save memory if solutions are no longer needed
	dijkstra.clear();
	
	
	
	// Now compute the shortest path from A to all the other nodes
	// but taking the number of nodes in the path as its length
	dijkstra = new Dijkstra(Dijkstra.Element.NODE, null, null);
	dijkstra.init(g);
	dijkstra.setSource(this.getRoot(g));
	dijkstra.compute();
	
	// Print the lengths of the new shortest paths
	for (Node node : g)
		System.out.printf("%s->%s:%10.2f%n", dijkstra.getSource(), node,
				dijkstra.getPathLength(node));
	
	// Print all the shortest paths between Root and Goal
	Iterator<Path> pathIterator = dijkstra.getAllPathsIterator(this.getGoal(g));
	
	System.out.println(" Print all the shortest paths between Root and Goal ");
	
	int j = 0;
	while (pathIterator.hasNext()){
		
		System.out.println(" n : "+j+"  "+pathIterator.next());
	} // fin de while
	
//-------------------------------------------
//---------------------------------------

	
	
	return allPaths;
} // fin de getDijkstraAllPaths
//-------------------------------------------------------------



//-------------------------------------------------------------
//Chercher le nombre d'alternatives dans un plan  

public void getAllPaths(Graph g, Graph path1, ArrayList allPaths, Node lastN, Node goal){
	
	Node lastNode = lastN; 
	Node currentNode = lastN;
	path1.addAttribute("ui.stylesheet", "url('file:config/Modele1.css')");
	path1.setStrict(false);
	path1.setAutoCreate( true ); // cr�ation automatique des noeuds.
	path1.addAttribute("ui.quality");
	path1.addAttribute("ui.antialias");
	
	
	while(!(currentNode.getId().equals(goal.getId()))) {
		
		if(lastNode.getOutDegree()==1){  // le cas ou on  a une seule sortie sur le noeud
			
			Edge e1=null;
			for(Edge e:g.getEachEdge()){
				if(e.getSourceNode().getId().equals(lastNode.getId()))	
					e1 = e;
			}
			
	  //	System.out.println(" lastNode.getOutDegree()==1 ---> Edge --> "+e1.getId());
				
			Node n2 = e1.getTargetNode(); //n2.addAttribute("label", n2.getId());
			path1.addNode(n2.getId());
			path1.addEdge(e1.getId(), lastNode.getId() , n2.getId(), true);
			
			
			path1.getNode( lastNode.getId()).addAttribute("label", lastNode.getId());
			path1.getNode( n2.getId()).addAttribute("label", n2.getId());
			
		
			lastNode = n2;
			currentNode = n2;			
			
			//e1=null;
			
		} else{
			
		//	int eNumber = lastNode.getOutDegree();
			
		//	System.out.println("--->--->--->--->---> Le Degree de "+lastNode.getId()+" est: "+ lastNode.getOutDegree());
			ArrayList listEdgeOut = new ArrayList();
			
			for(Edge e:g.getEachEdge()){
				if(e.getSourceNode().getId().equals(lastNode.getId()))	{
					listEdgeOut.add(e);
		//			System.out.println("lastNode.getId() => "+lastNode.getId()+" listEdgeOut --> "+e.getId());
				}
				
					
				
			}
			
			Node oldLastNode = lastNode;  // Garder l'ancienne valeur lastNode puisque il sera chang� 
			
			for(int l=1; l<listEdgeOut.size();l++){
				
				// cas de plusieurs alternatives, donc cr�ation d'un nouveau chemin
				Graph path2 = new SingleGraph("PathBy"+lastNode.getId()+"_"+pathNbr);
				pathNbr++;
				path2.setStrict(false);
				path2.setAutoCreate( true ); // cr�ation automatique des noeuds.
				
		
				for (Node node: path1){	
						path2.addNode(node.getId());
				}
				
				for (Edge edge: path1.getEachEdge()){	
					
					Node n1 = edge.getSourceNode(); //n1.addAttribute("label", n1.getId());
					Node n2 = edge.getTargetNode(); //n2.addAttribute("label", n2.getId());
					path2.addEdge(edge.getId(), n1.getId() , n2.getId(), true);
				
					path2.getNode( n1.getId()).addAttribute("label", n1.getId());
					path2.getNode( n2.getId()).addAttribute("label", n2.getId());
					
				}
				
				
				Edge e1=(Edge) listEdgeOut.get(l);
				Node n2 = e1.getTargetNode(); n2.addAttribute("label", n2.getId());
				
				path2.addNode(n2.getId());
				path2.addEdge(e1.getId(), lastNode.getId() , n2.getId(), true);
				
				path2.getNode( lastNode.getId()).addAttribute("label", lastNode.getId());
				path2.getNode( n2.getId()).addAttribute("label", n2.getId());
				
			//	System.out.println(" lastNode.getOutDegree()>1 ---> Edge --> "+e1.getId());
				
				lastNode = n2;
				currentNode = n2;		
				getAllPaths(g, path2, allPaths, lastNode, goal);
			
			} // fin de for
			
			
			// ajouter l'arc au premier chemin
			Edge ee1=(Edge) listEdgeOut.get(0);
			Node nn2 = ee1.getTargetNode(); nn2.addAttribute("label", nn2.getId());
			
			path1.addNode(nn2.getId());
			path1.addEdge(ee1.getId(), oldLastNode.getId() , nn2.getId(), true);
			
			path1.getNode( oldLastNode.getId()).addAttribute("label", oldLastNode.getId());
			path1.getNode( nn2.getId()).addAttribute("label", nn2.getId());
			
			
			lastNode = nn2;
			currentNode = nn2;	
			
		} // fin de else
		
	} // fin de while

	allPaths.add(path1);

} // fin de getAllPaths2
//-------------------------------------------------------------


//--------------------------------------------------
public ArrayList getPlanNodesArrayList(Graph g){
	ArrayList list = new ArrayList();
		for(Node n:g) {
			list.add(n);
		}
	return list;
} // fin de getPlanNodesArrayList
//-------------------------------------------------



//--------------------------------------------------
public ArrayList getPlanEdgesArrayList(Graph g){
	ArrayList list = new ArrayList();
		for(Edge e:g.getEachEdge()) {
			list.add(e);
		}
	return list;
} // fin de getPlanNodesArrayList
//-------------------------------------------------


//--------------------------------------------------
public void displayAlternatives(ArrayList list){
	for(int nbr=0; nbr<list.size(); nbr++){
		Graph gg = (Graph) list.get(nbr); 
	
		for (Node ne:gg){
			ne.addAttribute("label", ne.getId());
		}// fin de for
	//	this.prepaireDisplay(gg);
		gg.display();
	} // Fin de for
	
} // fin de displayAlternatives
//-------------------------------------------------


//--------------------------------------------------
public void displayAlternatives(ArrayList list, int Nbr){
	
	Graph gg = (Graph) list.get(Nbr); 
	
		for (Node ne:gg){
			ne.addAttribute("label", ne.getId());
		}// fin de for
		this.prepaireDisplay(gg);
		gg.display();
} // fin de displayAlternatives
//-------------------------------------------------



//--------------------------------------------------
public void prepaireDisplay(Graph g){
	Node root = this.getRoot(g);
	Node goal = this.getGoal(g);
	
	g.addAttribute("ui.stylesheet", "url('file:config/Modele1.css')");
	g.addAttribute("ui.quality");
	g.addAttribute("ui.antialias");
		
	root.addAttribute("ui.class", "start");
	root.addAttribute("ui.class", "goal");
		
}// fin de prepaireDisplay
//-------------------------------------------------



//--------------------------------------------------
public ArrayList constructSubSets(Graph plan, Graph path){
		ArrayList subSetsList = new ArrayList();
		
		for(Edge ePlan:plan.getEachEdge()) {
			
			for(Edge ePath:path.getEachEdge()) {
				
				if(ePlan.getSourceNode().getId().equals(ePath.getSourceNode().getId()))
					if(ePlan.getTargetNode().getId().equals(ePath.getTargetNode().getId()))
						subSetsList.add(ePlan.getId());
				
				
			} // fin de for
			
			
		} // fin de for
		
		return subSetsList;
}// fin de constructSubSets
//-------------------------------------------------


//------------------------------------------------------

public void putCostsOnGraph(Graph g) throws IOException{
	
	for(Edge ed:g.getEachEdge()) {
		
		ed.addAttribute("oldLabel", getActionCost(ed.getId()) +""+" + "+getActionExtraCost(g, ed.getId()) );
		ed.setAttribute("ui.label", getActionCost(ed.getId()) +""+" + "+getActionExtraCost(g, ed.getId())  );
		ed.setAttribute("cost", getActionCost(ed.getId()) +""+" + "+getActionExtraCost(g, ed.getId())  );
	} // fin de for
}

//------------------------------------------------------


/*
//--------------------------------------------------
public void initializeActionCostsList(){
		
	Action AB = new Action(this.agent,"AB", 30, extraActionCost.getExtraCostForAgent(agent, "AB")); actionCostsList.add(AB); 
	  Action AC = new Action(this.agent,"AC", 30, extraActionCost.getExtraCostForAgent(agent, "AC")); actionCostsList.add(AC); 
	  Action AD = new Action(this.agent,"AD", 30, extraActionCost.getExtraCostForAgent(agent, "AD")); actionCostsList.add(AD); 
	  Action BE = new Action(this.agent,"BE", 30, extraActionCost.getExtraCostForAgent(agent, "BE")); actionCostsList.add(BE); 
	  Action BG = new Action(this.agent,"BG", 30, extraActionCost.getExtraCostForAgent(agent, "BG")); actionCostsList.add(BG); 
	  Action CE = new Action(this.agent,"CE", 30, extraActionCost.getExtraCostForAgent(agent, "CE")); actionCostsList.add(CE); 
	  Action CG = new Action(this.agent,"CG", 30, extraActionCost.getExtraCostForAgent(agent, "CG")); actionCostsList.add(CG); 
	  Action DE = new Action(this.agent,"DE", 30, extraActionCost.getExtraCostForAgent(agent, "DE")); actionCostsList.add(DE); 
	  Action DG = new Action(this.agent,"DG", 30, extraActionCost.getExtraCostForAgent(agent, "DG")); actionCostsList.add(DG); 
	  Action EI = new Action(this.agent,"EI", 30, extraActionCost.getExtraCostForAgent(agent, "EI")); actionCostsList.add(EI); 
	  Action EK = new Action(this.agent,"EK", 30, extraActionCost.getExtraCostForAgent(agent, "EK")); actionCostsList.add(EK); 
	  Action FI = new Action(this.agent,"FI", 30, extraActionCost.getExtraCostForAgent(agent, "FI")); actionCostsList.add(FI); 
	  Action FK = new Action(this.agent,"FK", 30, extraActionCost.getExtraCostForAgent(agent, "FK")); actionCostsList.add(FK); 
	  Action GI = new Action(this.agent,"GI", 30, extraActionCost.getExtraCostForAgent(agent, "GI")); actionCostsList.add(GI); 
	  Action GK = new Action(this.agent,"GK", 30, extraActionCost.getExtraCostForAgent(agent, "GK")); actionCostsList.add(GK); 
	  Action HI = new Action(this.agent,"HI", 30, extraActionCost.getExtraCostForAgent(agent, "HI")); actionCostsList.add(HI); 
	  Action HK = new Action(this.agent,"HK", 30, extraActionCost.getExtraCostForAgent(agent, "HK")); actionCostsList.add(HK); 
	  Action IM = new Action(this.agent,"IM", 30, extraActionCost.getExtraCostForAgent(agent, "IM")); actionCostsList.add(IM); 
	  Action IO = new Action(this.agent,"IO", 30, extraActionCost.getExtraCostForAgent(agent, "IO")); actionCostsList.add(IO); 
	  Action JN = new Action(this.agent,"JN", 30, extraActionCost.getExtraCostForAgent(agent, "JN")); actionCostsList.add(JN); 
	  Action KM = new Action(this.agent,"KM", 30, extraActionCost.getExtraCostForAgent(agent, "KM")); actionCostsList.add(KM); 
	  Action KO = new Action(this.agent,"KO", 30, extraActionCost.getExtraCostForAgent(agent, "KO")); actionCostsList.add(KO); 
	  Action LN = new Action(this.agent,"LN", 30, extraActionCost.getExtraCostForAgent(agent, "LN")); actionCostsList.add(LN); 
	  Action MP = new Action(this.agent,"MP", 30, extraActionCost.getExtraCostForAgent(agent, "MP")); actionCostsList.add(MP); 
	  Action NP = new Action(this.agent,"NP", 30, extraActionCost.getExtraCostForAgent(agent, "NP")); actionCostsList.add(NP); 
	  Action OP = new Action(this.agent,"OP", 30, extraActionCost.getExtraCostForAgent(agent, "OP")); actionCostsList.add(OP); 
	  Action PR = new Action(this.agent,"PR", 30, extraActionCost.getExtraCostForAgent(agent, "PR")); actionCostsList.add(PR); 
	  Action PS = new Action(this.agent,"PS", 30, extraActionCost.getExtraCostForAgent(agent, "PS")); actionCostsList.add(PS); 
	  Action PT = new Action(this.agent,"PT", 30, extraActionCost.getExtraCostForAgent(agent, "PT")); actionCostsList.add(PT); 
	  Action QR = new Action(this.agent,"QR", 30, extraActionCost.getExtraCostForAgent(agent, "QR")); actionCostsList.add(QR); 
	  Action QS = new Action(this.agent,"QS", 30, extraActionCost.getExtraCostForAgent(agent, "QS")); actionCostsList.add(QS); 
	  Action QT = new Action(this.agent,"QT", 30, extraActionCost.getExtraCostForAgent(agent, "QT")); actionCostsList.add(QT); 
	  Action RU = new Action(this.agent,"RU", 30, extraActionCost.getExtraCostForAgent(agent, "RU")); actionCostsList.add(RU); 
	  Action RW = new Action(this.agent,"RW", 30, extraActionCost.getExtraCostForAgent(agent, "RW")); actionCostsList.add(RW); 
	  Action SV = new Action(this.agent,"SV", 30, extraActionCost.getExtraCostForAgent(agent, "SV")); actionCostsList.add(SV); 
	  Action TU = new Action(this.agent,"TU", 30, extraActionCost.getExtraCostForAgent(agent, "TU")); actionCostsList.add(TU); 
	  Action TW = new Action(this.agent,"TW", 30, extraActionCost.getExtraCostForAgent(agent, "TW")); actionCostsList.add(TW); 
	  Action UY = new Action(this.agent,"UY", 30, extraActionCost.getExtraCostForAgent(agent, "UY")); actionCostsList.add(UY); 
	  Action VX = new Action(this.agent,"VX", 30, extraActionCost.getExtraCostForAgent(agent, "VX")); actionCostsList.add(VX); 
	  Action VZ = new Action(this.agent,"VZ", 30, extraActionCost.getExtraCostForAgent(agent, "VZ")); actionCostsList.add(VZ); 
	  Action WY = new Action(this.agent,"WY", 30, extraActionCost.getExtraCostForAgent(agent, "WY")); actionCostsList.add(WY); 
	  
	Action AE = new Action(this.agent,"AE", 45, extraActionCost.getExtraCostForAgent(agent, "AE")); actionCostsList.add(AE); 
	  Action AG = new Action(this.agent,"AG", 45, extraActionCost.getExtraCostForAgent(agent, "AG")); actionCostsList.add(AG); 
	  Action EM = new Action(this.agent,"EM", 45, extraActionCost.getExtraCostForAgent(agent, "EM")); actionCostsList.add(EM); 
	  Action EO = new Action(this.agent,"EO", 45, extraActionCost.getExtraCostForAgent(agent, "EO")); actionCostsList.add(EO); 
	  Action FN = new Action(this.agent,"FN", 45, extraActionCost.getExtraCostForAgent(agent, "FN")); actionCostsList.add(FN); 
	  Action GM = new Action(this.agent,"GM", 45, extraActionCost.getExtraCostForAgent(agent, "GM")); actionCostsList.add(GM); 
	  Action GO = new Action(this.agent,"GO", 45, extraActionCost.getExtraCostForAgent(agent, "GO")); actionCostsList.add(GO); 
	  Action HN = new Action(this.agent,"HN", 45, extraActionCost.getExtraCostForAgent(agent, "HN")); actionCostsList.add(HN); 
	  Action MR = new Action(this.agent,"MR", 45, extraActionCost.getExtraCostForAgent(agent, "MR")); actionCostsList.add(MR); 
	  Action MT = new Action(this.agent,"MT", 45, extraActionCost.getExtraCostForAgent(agent, "MT")); actionCostsList.add(MT); 
	  Action NS = new Action(this.agent,"NS", 45, extraActionCost.getExtraCostForAgent(agent, "NS")); actionCostsList.add(NS); 
	  Action OR = new Action(this.agent,"OR", 45, extraActionCost.getExtraCostForAgent(agent, "OR")); actionCostsList.add(OR); 
	  Action OT = new Action(this.agent,"OT", 45, extraActionCost.getExtraCostForAgent(agent, "OT")); actionCostsList.add(OT); 
	  Action RY = new Action(this.agent,"RY", 45, extraActionCost.getExtraCostForAgent(agent, "RY")); actionCostsList.add(RY); 
	  Action SX = new Action(this.agent,"SX", 45, extraActionCost.getExtraCostForAgent(agent, "SX")); actionCostsList.add(SX); 
	  Action SZ = new Action(this.agent,"SZ", 45, extraActionCost.getExtraCostForAgent(agent, "SZ")); actionCostsList.add(SZ); 
	  Action TY = new Action(this.agent,"TY", 45, extraActionCost.getExtraCostForAgent(agent, "TY")); actionCostsList.add(TY); 
	  Action BI = new Action(this.agent,"BI", 45, extraActionCost.getExtraCostForAgent(agent, "BI")); actionCostsList.add(BI); 
	  Action BK = new Action(this.agent,"BK", 45, extraActionCost.getExtraCostForAgent(agent, "BK")); actionCostsList.add(BK); 
	  Action CI = new Action(this.agent,"CI", 45, extraActionCost.getExtraCostForAgent(agent, "CI")); actionCostsList.add(CI); 
	  Action CK = new Action(this.agent,"CK", 45, extraActionCost.getExtraCostForAgent(agent, "CK")); actionCostsList.add(CK); 
	  Action DI = new Action(this.agent,"DI", 45, extraActionCost.getExtraCostForAgent(agent, "DI")); actionCostsList.add(DI); 
	  Action DK = new Action(this.agent,"DK", 45, extraActionCost.getExtraCostForAgent(agent, "DK")); actionCostsList.add(DK); 
	  Action IP = new Action(this.agent,"IP", 45, extraActionCost.getExtraCostForAgent(agent, "IP")); actionCostsList.add(IP); 
	  Action JP = new Action(this.agent,"JP", 45, extraActionCost.getExtraCostForAgent(agent, "JP")); actionCostsList.add(JP); 
	  Action KP = new Action(this.agent,"KP", 45, extraActionCost.getExtraCostForAgent(agent, "KP")); actionCostsList.add(KP); 
	  Action LP = new Action(this.agent,"LP", 45, extraActionCost.getExtraCostForAgent(agent, "LP")); actionCostsList.add(LP); 
	  Action PU = new Action(this.agent,"PU", 45, extraActionCost.getExtraCostForAgent(agent, "PU")); actionCostsList.add(PU); 
	  Action PW = new Action(this.agent,"PW", 45, extraActionCost.getExtraCostForAgent(agent, "PW")); actionCostsList.add(PW); 
	  Action QV = new Action(this.agent,"QV", 45, extraActionCost.getExtraCostForAgent(agent, "QV")); actionCostsList.add(QV);  
	
}// fin de initializeActionCostsList
//-------------------------------------------------

*/
//--------------------------------------------------
public int getActionCost(String name){
		return ActionsCost.getActionCost(name);
}// fin de getActionCost
//-------------------------------------------------

//--------------------------------------------------
public int getActionExtraCost(Graph agPlan, String name) throws IOException{
	//actionCostsList
	return Traitment.getExtraCostForAgent(agPlan, name);

}// fin de getActionCost
//-------------------------------------------------








public ArrayList getActionCostsList(){
	return this.actionCostsList;
} // fin de 







}
