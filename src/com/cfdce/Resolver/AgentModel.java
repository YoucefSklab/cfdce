/**
 * 
 */
package com.cfdce.Resolver;

import java.io.IOException;
import java.util.ArrayList;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.view.View;
import org.graphstream.ui.view.Viewer;

import com.cfdce.Control.Constants;
import com.cfdce.FormationCoalitions.Discussion;
import com.cfdce.Plan.PlanManagment;

/**
 * @author Djo
 *
 */
public class AgentModel {
	
	public Graph plan = null;
	public PlanManagment planMgmt = new PlanManagment(""); // Classe gestion des plan
	public Node rootNode = null;

	
	public Viewer viewer;
	public View view;
	public ArrayList allPaths = new ArrayList(); 	// le nombre de paths dans le plan local : 
	public ArrayList agentsPlansList = new ArrayList();
	public ArrayList allCoalitionsList = new ArrayList();
	public ArrayList discussionList = new ArrayList();
	public ArrayList firstDiscOrder = new ArrayList(); // le premier ordre des discussions
	public ArrayList firstDiscOrderCosts = new ArrayList(); // le premier ordre des couts des discussions
	
	// liste des taches et la liste de leurs raches exclusives...
	public ArrayList tasksList = new ArrayList();
	public ArrayList exclusiveTasks = new ArrayList();
	
	public ArrayList bestCoalitions = new ArrayList();
	public ArrayList bestCoalitionsAgents = new ArrayList();
	public ArrayList bestCoalitionsCosts = new ArrayList();
	
	public ArrayList agentsExclusiveTasks = new ArrayList();
	
	
	public Discussion selectedDisc = null;
	public float selectecdCost = 0;
	public float selectedFinalCost=0;
	
	
	
	
	
	
	
	

	public float refCost = 0;
	public int discussionRef = 0;
	public float gainedCost = 0;
	public Constants Ct = new Constants();
	
	
	public AgentModel(Graph agentPlan) throws IOException{
		plan = agentPlan;
		
		
	///	System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
	///	plan.addAttribute("ui.stylesheet", "url('config/Modele1.css')");
		
		
	///	viewer = agentPlan.display();
		rootNode = planMgmt.getRoot(plan);
		rootNode.addAttribute("oldLabel", (String) rootNode.getAttribute("ui.label"));
		rootNode.addAttribute("label", plan.getId());
	///	rootNode.setAttribute("ui.class", "negotiation");
		planMgmt.agent = plan.getId();
	///	planMgmt.putCostsOnGraph(plan);
		
		//Calculer les alternatives
		planMgmt.getAllPaths(plan, new SingleGraph("PathByStart_1"), allPaths, planMgmt.getRoot(plan),  planMgmt.getGoal(plan));
		
		
	}
	
	public AgentModel(Graph agentPlan, int i) throws IOException{
		plan = agentPlan;
		
		
	//	System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
	//	plan.addAttribute("ui.stylesheet", "url('config/Modele1.css')");
		
		
		//viewer = agentPlan.display();
		rootNode = planMgmt.getRoot(plan);
		rootNode.addAttribute("oldLabel",  (String)  rootNode.getAttribute("ui.label"));
		rootNode.addAttribute("label", plan.getId());
	//	rootNode.setAttribute("ui.class", "negotiation");
		planMgmt.agent = plan.getId();
	//	planMgmt.putCostsOnGraph(plan);
		
		//Calculer les alternatives
		planMgmt.getAllPaths(plan, new SingleGraph("PathByStart_1"), allPaths, planMgmt.getRoot(plan),  planMgmt.getGoal(plan));
		
		
	}

}
