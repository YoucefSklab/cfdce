package com.cfdce.Resolver;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

import org.graphstream.algorithm.Dijkstra;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.Path;
import org.graphstream.graph.implementations.SingleGraph;

import com.cfdce.Control.Traitment;
import com.cfdce.FormationCoalitions.Coalition;
import com.cfdce.FormationCoalitions.Discussion;
import com.cfdce.Plan.PlanManagment;

import jade.domain.FIPAException;


public class MethodesCollection {

	// public  Logger logger =
	// LoggerFactory.getLogger(MethodesCollection.class);
	 int pathNbr = 2;
	

	/**
	 * Charger les plans des agents dans une liste
	 * 
	 * @param agentTab
	 *            Tbaleau contenant les identifiants des agents a charger
	 * @return Une liste contenant les agents sous forme d'objet AgentModel
	 * @throws IOException
	 */
	public ArrayList loadPlans(int agentTab[]) throws IOException {
		ArrayList agentList = new ArrayList();

		for( int i = 0; i < agentTab.length; i++ ) {
			AgentModel agent = new AgentModel(getPlanByNbr(agentTab[i],i));
			for( Edge ed : agent.plan.getEachEdge() ) {
				if (!agent.tasksList.contains(ed.getId()))
					agent.tasksList.add(ed.getId());
			}
			agentList.add(agent);
		}
		return agentList;
	}

	/**
	 * Calculer les longueur des plans
	 * 
	 * @param agentTab
	 *            Tbaleau contenant les identifiants des agents a charger
	 * @return Une liste contenant les agents sous forme d'objet AgentModel
	 * @throws IOException
	 */
	public ArrayList computePathsLenth(ArrayList agentList, ArrayList commonTasks) throws IOException {
		ArrayList plansLenth = new ArrayList();
		
		for( int i = 0; i < agentList.size(); i++ ) {
			
			AgentModel agent = (AgentModel) agentList.get(i);
						
			ArrayList lisPath = new ArrayList();
			this.getAllPaths(agent.plan, new SingleGraph("PathByStart_1"), lisPath, getRoot(agent.plan),  getGoal(agent.plan));
			
			agent.allPaths = lisPath;
			
			int lenth = 1;
			for(int h=0; h<lisPath.size(); h++){
				Graph g = (Graph) lisPath.get(h);
				int pathLenth = 0;
				for(Edge ed:g.getEachEdge()){
					if(commonTasks.contains(ed.getId())){
						pathLenth++;
					}
					
				}
				//System.out.println("Plan: "+agentTab[i]+" -----> "+g.getEdgeSet().toString()+" with lenth -> "+g.getEdgeCount());
				if(pathLenth>0)
				lenth = lenth*pathLenth;
			}
			plansLenth.add(lenth);
		}
		return plansLenth;
	}
	
	
	
	
	/**
	 * Charger les plans des agents dans une liste (Plans al�atoire)
	 * 
	 * @param agentTab
	 *            Tbaleau contenant les identifiants des agents a charger
	 * @return Une liste contenant les agents sous forme d'objet AgentModel
	 * @throws IOException
	 */
	public ArrayList loadPlansRandom(int agentTab[]) throws IOException {
		ArrayList agentList = new ArrayList();

		for( int i = 0; i < agentTab.length; i++ ) {
			AgentModel agent = new AgentModel(getPlanByNbr(ThreadLocalRandom.current().nextInt(1, 900),i));
			for( Edge ed : agent.plan.getEachEdge() ) {
				if (!agent.tasksList.contains(ed.getId()))
					agent.tasksList.add(ed.getId());
			}
			agentList.add(agent);
		}
		return agentList;
	}



	/**
	 * 
	 * @param planId
	 * @return
	 * @throws FileNotFoundException
	 */
	public Graph constructPlan(boolean condition, int planId, int plNbr )
			throws FileNotFoundException {
		Graph Plan = new SingleGraph("Agent" + plNbr);
		Plan.setStrict(false);
		Plan.setAutoCreate(true);
		Plan.addAttribute("ui.stylesheet", "url('file:config/Modele1.css')");
		Plan.addAttribute("ui.quality");
		Plan.addAttribute("ui.antialias");

		ArrayList addedNodes = new ArrayList();

		File f1 = new File("AgentsPlans/Plan_Agent_" + planId + ".txt");

		if (f1.exists()) {

			Scanner sc = new Scanner(f1);
			while (sc.hasNextLine()) {
				String text = sc.nextLine();
				String N1 = "";
				String N2 = "";
				String costV = "";
				boolean edge1 = true;
				boolean cost = false;
				boolean escape = false;

				for( int i = 0; i < text.length(); i++ ) {
					char c = text.charAt(i);

					if (c == '/') {
						escape = true;
						break;
					}

					if (c == ',') {
						edge1 = false;
					}

					if (c == ';') {
						cost = true;
					}

					if ((c != ',') && (c != ';')) {
						if (edge1) {
							N1 += c;
						}
						else if (!cost) {
							N2 += c;
						}
						if (cost) {
							costV += c;
						}
					}
				}

				if (!escape) {
					if (!addedNodes.contains(N1)) {
						Plan.addNode(N1);
						addedNodes.add(N1);
					}

					if (!addedNodes.contains(N2)) {
						Plan.addNode(N2);
						addedNodes.add(N2);
					}

					Plan.addEdge(N1 + N2 + "", N1, N2, true);
					// Plan.addEdge(N1+N2+"", N1, N2);

					// pOnScreen(condition,"++++++++> Adding edge: "+N1+N2+"");

					Edge e = Plan.getEdge(N1 + N2 + "");
					e.addAttribute("extraCost", "0" + costV);
					e.setAttribute("extraCost", "0" + costV);
				}
			}

			for( Node n : Plan.getEachNode() ) {
				n.setAttribute("ui.label", n.getId());
				// pOnScreen(condition,"-----------> Node Label: "+n.getId());
			}

			for( Edge e : Plan.getEachEdge() ) {
				e.setAttribute("ui.label", e.getId());
				// pOnScreen(condition,"-----------> Edge Label: "+e.getId());

			}

		}
		
		return Plan;
	}



	/**
	 * R�cup�ration d'un plan par son nombre
	 * 
	 * @param nbr:
	 *            le num�ro de l'agent
	 * @return
	 * @throws FileNotFoundException
	 */
	public Graph getPlanByNbr(int nbr, int plNbr) throws FileNotFoundException {
		return constructPlan(false, nbr, plNbr);
	}
	
	public Graph getOriginalPlanByNbr(int nbr, int plNbr) throws FileNotFoundException {
		return constructOriginalPlan(false, nbr, plNbr);
	}

	


	/**
	 * Former la liste de toutes les partitions possible d'un ensemble sous
	 * forme d'une chaine de caract�re, les cas regroupants r�alisations seules
	 * etant repr�sent�s par une seule possibilit�. Avec oneInstance = true,
	 * cela permettera de r�duire consid�rablement le nombre total des
	 * possibilit�s. Par Exemple, avec 3 agents ayant un plan commun de 7
	 * taches; on aura au total 7 puissance 5 possibilit�s: soit: 117649
	 * possibilit�s. Avec oneInstance = true, on aura 5 puissance 6, soit: 15625
	 * possibilit�s.
	 * 
	 * @param listeElement
	 *            Liste des �l�ments de l'ensemble
	 * @return Liste des partitions
	 */
	public  ArrayList formCoalitionStr(ArrayList listeElement, boolean oneInstance) {
		ArrayList listeCoalition = new ArrayList();
		ArrayList listeFinale = new ArrayList();

		int partSize = 3;
		int listSize = listeElement.size();

		// Ajouter les coalition individuelles (une seule suffit...) Les autres
		// vont �tre d�duites des autres combinaisons
		if (oneInstance) {
			
			ArrayList next = new ArrayList();
			next.add(0);
			String result = next.toString();
			result = result.replace("[", "");
			result = result.replace("]", "");
			result = result.replace(" ", "");
			listeCoalition.add(result);
			
		/*	next = new ArrayList();
			next.add(1);
			result = next.toString();
			result = result.replace("[", "");
			result = result.replace("]", "");
			result = result.replace(" ", "");
			listeCoalition.add(result);
			*/
		}
		else {
			for( int i = 0; i < (listeElement.size()); i++ ) {
				ArrayList next = new ArrayList();
				next.add(listeElement.get(i));

				String result = next.toString();
				result = result.replace("[", "");
				result = result.replace("]", "");
				result = result.replace(" ", "");
				listeCoalition.add(result);
			}
		}

		// Round 1
		for( int i = 0; i < (listeElement.size() - 1); i++ ) {
			for( int j = (i + 1); j < listeElement.size(); j++ ) {
				ArrayList next = new ArrayList();
				next.add(listeElement.get(i));
				next.add(listeElement.get(j));

				String result = next.toString();
				result = result.replace("[", "");
				result = result.replace("]", "");
				result = result.replace(" ", "");
				listeCoalition.add(result);
			}
		}

		while (partSize < listSize) {

			for( int o = 0; o < listeCoalition.size(); o++ ) {
				ArrayList listEl = getArrayListFromStr((String) listeCoalition.get(o));

				int posi1 = listeElement.indexOf(listEl.get(0));
				int posi2 = listeElement.indexOf(listEl.get(listEl.size() - 1));

				if ((posi2 + 1 < listeElement.size()) && (posi1 >=0))
					for( int i = posi1; i < listeElement.size(); i++ ) {
						if (!listEl.contains(listeElement.get(i))) {
							ArrayList next = new ArrayList();
							next.addAll(listEl);
							next.add(listeElement.get(i));

							if (!(next.size() == listeElement.size())) {
								if ((int) next.get(next.size() - 1) > (int) next
										.get(next.size() - 2)) {
									String result = next.toString();
									result = result.replace("[", "");
									result = result.replace("]", "");
									result = result.replace(" ", "");
									listeCoalition.add(result);
								}

							}
							else {
								partSize = listSize;
								break;
							}
						}
					}
			}
			partSize++;
		}

	if(listeElement.size()>2){
		String result = listeElement.toString();
		result = result.replace("[", "");
		result = result.replace("]", "");
		result = result.replace(" ", "");
		listeCoalition.add(result);
	}
		// listeCoalition.add(listeElement);

		// Conversion en une chaine de caract�re

		return listeCoalition;
	}



	/**
	 * Former la liste de toutes les partitions possible d'un ensemble sous
	 * forme d'une chaine de caract�re
	 * 
	 * @param listeElement
	 *            Liste des �l�ments de l'ensemble
	 * @return Liste des partitions
	 */
	public  ArrayList formCoalitionStr(ArrayList listeElement) {
		ArrayList listeCoalition = new ArrayList();
		ArrayList listeFinale = new ArrayList();
		/*
		 * if(listeElement.size()==2) { listeCoalition.add((ArrayList)
		 * listeElement); return listeElement; }
		 */
		int partSize = 3;
		int listSize = listeElement.size();

		// Ajouter les coalition individuelles (une seule suffit...) Les autres
		// vont �tre d�duites des autres combinaisons
		for( int i = 0; i < (listeElement.size()); i++ ) {
			ArrayList next = new ArrayList();
			next.add(listeElement.get(i));

			String result = next.toString();
			result = result.replace("[", "");
			result = result.replace("]", "");
			result = result.replace(" ", "");
			listeCoalition.add(result);
		}

		// Round 1
		for( int i = 0; i < (listeElement.size() - 1); i++ ) {
			for( int j = (i + 1); j < listeElement.size(); j++ ) {
				ArrayList next = new ArrayList();
				next.add(listeElement.get(i));
				next.add(listeElement.get(j));

				String result = next.toString();
				result = result.replace("[", "");
				result = result.replace("]", "");
				result = result.replace(" ", "");
				listeCoalition.add(result);
			}
		}

		while (partSize < listSize) {

			for( int o = 0; o < listeCoalition.size(); o++ ) {
				ArrayList listEl = getArrayListFromStr(
						(String) listeCoalition.get(o));

				int posi1 = listeElement.indexOf(listEl.get(0));
				int posi2 = listeElement.indexOf(listEl.get(listEl.size() - 1));

				if (posi2 + 1 < listeElement.size())
					for( int i = posi1; i < listeElement.size(); i++ ) {

						if (!listEl.contains(listeElement.get(i))) {
							ArrayList next = new ArrayList();
							next.addAll(listEl);
							next.add(listeElement.get(i));

							if (!(next.size() == listeElement.size())) {
								if ((int) next.get(next.size() - 1) > (int) next
										.get(next.size() - 2)) {
									String result = next.toString();
									result = result.replace("[", "");
									result = result.replace("]", "");
									result = result.replace(" ", "");
									listeCoalition.add(result);
								}

							}
							else {
								partSize = listSize;
								break;
							}
						}
					}
			}
			partSize++;
		}

	if(listeElement.size()>2){
		String result = listeElement.toString();
		result = result.replace("[", "");
		result = result.replace("]", "");
		result = result.replace(" ", "");
		listeCoalition.add(result);
	}
		// listeCoalition.add(listeElement);

		// Conversion en une chaine de caract�re

		return listeCoalition;
	}

	
	
	/**
	 * Cette m�thode est une mise � jour des possibilit�s form�es par la m�thode formUpdateCoalitionStr
	 * 
	 * @param listeElement
	 *            Liste des �l�ments de l'ensemble
	 * @return Liste des partitions
	 */
	public ArrayList formUpdateCoalitionStr(ArrayList listeElement,
			boolean oneInstance) {
		ArrayList listeCoalition = new ArrayList();
		ArrayList listeFinale = new ArrayList();

		int partSize = 3;
		int listSize = listeElement.size();

		// Ajouter les coalition individuelles (une seule suffit...) Les autres
		// vont �tre d�duites des autres combinaisons
		if (oneInstance) {
			ArrayList next = new ArrayList();
			next.add(1);
			String result = next.toString();
			result = result.replace("[", "");
			result = result.replace("]", "");
			result = result.replace(" ", "");
			listeCoalition.add(result);
		}
		else {
			for( int i = 0; i < (listeElement.size()); i++ ) {
				ArrayList next = new ArrayList();
				next.add(listeElement.get(i));

				String result = next.toString();
				result = result.replace("[", "");
				result = result.replace("]", "");
				result = result.replace(" ", "");
				listeCoalition.add(result);
			}
		}

		// Round 1
		for( int i = 0; i < (listeElement.size() - 1); i++ ) {
			for( int j = (i + 1); j < listeElement.size(); j++ ) {
				ArrayList next = new ArrayList();
				next.add(listeElement.get(i));
				next.add(listeElement.get(j));

				String result = next.toString();
				result = result.replace("[", "");
				result = result.replace("]", "");
				result = result.replace(" ", "");
				listeCoalition.add(result);
			}
		}

		while (partSize < listSize) {

			for( int o = 0; o < listeCoalition.size(); o++ ) {
				ArrayList listEl = getArrayListFromStr((String) listeCoalition.get(o));
			//	System.out.println("-> |->"+(String) listeCoalition.get(o).toString());
			//	System.out.println("-> |->listeCoalition "+listeCoalition.toString());
			//	System.out.println("-> |->listEl "+listEl.toString());
				int posi1 = listeElement.indexOf(listEl.get(0));
				int posi2 = listeElement.indexOf(listEl.get(listEl.size() - 1));
			//	System.out.println("-> ->"+posi1);
				if (posi2 + 1 < listeElement.size()) 
					if (posi1 > 0)
					for( int i = posi1; i < listeElement.size(); i++ ) {

						if (!listEl.contains(listeElement.get(i))) {
							ArrayList next = new ArrayList();
							next.addAll(listEl);
							next.add(listeElement.get(i));

							if (!(next.size() == listeElement.size())) {
								if ((int) next.get(next.size() - 1) > (int) next
										.get(next.size() - 2)) {
									String result = next.toString();
									result = result.replace("[", "");
									result = result.replace("]", "");
									result = result.replace(" ", "");
									listeCoalition.add(result);
								}

							}
							else {
								partSize = listSize;
								break;
							}
						}
					}
			}
			partSize++;
		}

		if(listeElement.size()>2){
			String result = listeElement.toString();
			result = result.replace("[", "");
			result = result.replace("]", "");
			result = result.replace(" ", "");
			listeCoalition.add(result);
		}

		// listeCoalition.add(listeElement);

		// Conversion en une chaine de caract�re

		return listeCoalition;
	}



	/**
	 * Former la liste de toutes les partitions possible d'un ensemble
	 * 
	 * @param listeElement
	 *            Liste des �l�ments de l'ensemble
	 * @return Liste des partitions
	 */
	public ArrayList formCoalition(ArrayList listeElement) {
		ArrayList listeCoalition = new ArrayList();
		/*
		 * if(listeElement.size()==2) { listeCoalition.add((ArrayList)
		 * listeElement); return listeElement; }
		 */
		int partSize = 3;
		int listSize = listeElement.size();

		// Round 1
		for( int i = 0; i < (listeElement.size() - 1); i++ ) {
			for( int j = (i + 1); j < listeElement.size(); j++ ) {
				ArrayList next = new ArrayList();
				next.add(listeElement.get(i));
				next.add(listeElement.get(j));
				listeCoalition.add(next);
			}
		}

		while (partSize < listSize) {

			for( int o = 0; o < listeCoalition.size(); o++ ) {
				ArrayList listEl = (ArrayList) listeCoalition.get(o);

				int posi1 = listeElement.indexOf(listEl.get(0));
				int posi2 = listeElement.indexOf(listEl.get(listEl.size() - 1));

				if (posi2 + 1 < listeElement.size())
					for( int i = posi1; i < listeElement.size(); i++ ) {

						if (!listEl.contains(listeElement.get(i))) {
							ArrayList next = new ArrayList();
							next.addAll(listEl);
							next.add(listeElement.get(i));

							if (!(next.size() == listeElement.size())) {
								if ((int) next.get(next.size() - 1) > (int) next
										.get(next.size() - 2))
									listeCoalition.add(next);
							}
							else {
								partSize = listSize;
								break;
							}
						}
					}
			}
			partSize++;
		}
		listeCoalition.add(listeElement);
		return listeCoalition;
	}

	

	/**
	 * 
	 * Former la liste de toutes les partitions possible d'un ensemble,
	 * comportant un �l�ment donn�.
	 * 
	 * @param fixedElement
	 *            l'�l�ment en question
	 * @param listeElement
	 *            Liste des �l�ments de l'ensemble
	 * @return Liste des partitions
	 */
	public ArrayList formCoalition(ArrayList listeElement,
			int fixedElement) {
		ArrayList listeCoalition = new ArrayList();
		ArrayList finalList = new ArrayList();
		/*
		 * if(listeElement.size()==2) { listeCoalition.add((ArrayList)
		 * listeElement); return listeElement; }
		 */

		int elePosition = listeElement.indexOf(fixedElement);
		int partSize = 3;
		int listSize = listeElement.size();

		// Round 1
		for( int i = 0; i < (listeElement.size() - 1); i++ ) {
			for( int j = (i + 1); j < listeElement.size(); j++ ) {
				ArrayList next = new ArrayList();
				next.add(listeElement.get(i));
				next.add(listeElement.get(j));

				if (next.contains(fixedElement))
					listeCoalition.add(next);
			}
		}

		while (partSize < listSize) {

			for( int o = 0; o < listeCoalition.size(); o++ ) {
				ArrayList listEl = (ArrayList) listeCoalition.get(o);

				int posi1 = listeElement.indexOf(listEl.get(0));
				int posi2 = listeElement.indexOf(listEl.get(listEl.size() - 1));

				if (posi2 + 1 < listeElement.size())
					for( int i = posi1; i < listeElement.size(); i++ ) {

						if (!listEl.contains(listeElement.get(i))) {
							ArrayList next = new ArrayList();
							next.addAll(listEl);
							next.add(listeElement.get(i));

							if (!(next.size() == listeElement.size())) {
								if ((int) next.get(next.size() - 1) > (int) next
										.get(next.size() - 2))
									if (next.contains(fixedElement))
										listeCoalition.add(next);
							}
							else {
								partSize = listSize;
								break;
							}
						}
					}
			}
			partSize++;
		}
		listeCoalition.add(listeElement);

		for( int i = 0; i < listeCoalition.size(); i++ ) {
			ArrayList li = (ArrayList) listeCoalition.get(i);
			if (li.contains(fixedElement))
				finalList.add(li);
		}

		return finalList;

	}



	/**
	 *
	 * This methode is used by agents to form the first coalitions.
	 * 
	 * @throws FIPAException
	 * @throws IOException
	 *
	 **/
	public void formCoalitionsProposal(boolean condition, ArrayList agentlist)
			throws FIPAException, IOException {

		Traitment Tr = new Traitment();

		// Perform the Coalition Formation Process
		// ----------------------------------------------------------------------
		for( int agNbr = 0; agNbr < agentlist.size(); agNbr++ ) {
			AgentModel agent1 = (AgentModel) agentlist.get(agNbr);
			int localPlanPathsNbr = agent1.allPaths.size();

			pOnScreen(condition, " ");
			pOnScreen(condition, " ");
			pOnScreen(condition, " ");
			pOnScreen(condition,
					"|+++++++++++++++++++++++++++++++++++++++++++++++++++++++|");
			pOnScreen(condition, "| ");
			pOnScreen(condition, "| ");
			pOnScreen(condition, "  - Agent ID is: " + agent1.plan.getId());
			pOnScreen(condition, "| ");
			pOnScreen(condition, "| ");
			pOnScreen(condition,
					"|+++++++++++++++++++++++++++++++++++++++++++++++++++++++|");

			for( int agNbr2 = 0; agNbr2 < agentlist.size(); agNbr2++ ) {
				AgentModel agent2 = (AgentModel) agentlist.get(agNbr2);
				if (!agent2.plan.getId().equals(agent1.plan.getId())) {
					agent1.agentsPlansList.add(agent2.plan);
				}
			} // Fin de for(int agNbr2=0; agNbr2<agentlist.size(); agNbr2++)

			// parcours des alternatives possibles et calcul des coalitions
			// possibles, puis des discussions possibles
			// --------------------------------------------

			for( int i = 0; i < agent1.allPaths.size(); i++ ) {
				Graph path = (Graph) agent1.allPaths.get(i);
				
				Discussion disc = new Discussion(agent1.plan.getId(), path,
						agent1.planMgmt.getActionCostsList());

				for( Edge act : path.getEachEdge() ) {
					ArrayList agList = new ArrayList();
					ArrayList edgList = new ArrayList();

					edgList.add(act.getId());

					for( int j = 0; j < agent1.agentsPlansList.size(); j++ ) {
						Graph agPlan = (Graph) agent1.agentsPlansList.get(j);

						for( Edge ed : agPlan.getEachEdge() ) {
							if (ed.getId().equals(act.getId())) {
								if (!agList.contains(agPlan.getId())) {
									agList.add(agPlan.getId());
								}
							}
						}
					}

					Coalition c = new Coalition(agList, edgList,
							agent1.plan.getId());
					// c.evaluateCoalitionEstimatedCost(localPlan.graphPlan);
					c.discussionId = disc.discussionId;
					agent1.allCoalitionsList.add(c);
				}

				// disc.evaluateEstimatedCost(localPlan.graphPlan); //
				// evaluation du co�t d'une discussion
				// disc.evaluateIndividualCost(localPlan.graphPlan); //
				// evaluation du co�t individuel d'une discussion
				agent1.discussionList.add(disc);

				// calcul du profil de la discussion
				for( Edge e1 : agent1.plan.getEachEdge() ) {
					boolean exist = false;
					for( Edge e2 : disc.discussionPath.getEachEdge() ) {
						if (e1.getId().equals(e2.getId())) {
							exist = true;
							break;
						}
					}
					if (exist) {
						disc.discussionProfil.add(1);
					}
					else {
						disc.discussionProfil.add(0);
					}
				}

			}

			pOnScreen(condition, " --->> Total Possibles Discussions : "
					+ agent1.discussionList.size());

			// Netoyer totalCoalitionList
			// ----------------------------
			for( int i = 0; i < agent1.allCoalitionsList.size(); i++ ) {
				Coalition c = (Coalition) agent1.allCoalitionsList.get(i);

				for( int j = (i + 1); j < agent1.allCoalitionsList
						.size(); j++ ) {
					Coalition c1 = (Coalition) agent1.allCoalitionsList.get(j);

					if (c.edgeList.containsAll(c1.edgeList)) {
						agent1.allCoalitionsList.remove(c1);
						if (j > 0)
							j--;
					}
				}
			}

			for( int i = 0; i < agent1.discussionList.size(); i++ ) {
				Discussion d1 = (Discussion) agent1.discussionList.get(i);

				for( int j = 0; j < agent1.allCoalitionsList.size(); j++ ) {
					Coalition c = (Coalition) agent1.allCoalitionsList.get(j);

					for( Edge ed : d1.discussionPath.getEachEdge() ) {
						boolean exist = false;

						for( int t = 0; t < c.edgeList.size(); t++ ) {
							String task = (String) c.edgeList.get(t);
							if (ed.getId().equals(task)) {
								exist = true;
							}
						}

						if (exist) {
							d1.coalitionList.add(c);
							break;
						}
					}
				}

				d1.evaluateEstimatedCostForPropose(agent1.plan); // evaluation
																	// du co�t
																	// d'une
																	// discussion
				d1.evaluateIndividualCost(agent1.plan); // evaluation du co�t
														// individuel d'une
														// discussion
			}

			ArrayList tempDiscussionList = agent1.discussionList;
			// Utilisation des collections pour utiliser Grid 5000. java 1.7 et non 1.8
			Collections.sort(agent1.discussionList, DESCENDING_COMPARATOR_byIndiCost) ;
			Collections.sort(tempDiscussionList, DESCENDING_COMPARATOR_byIndiCost) ;

			// Utilisation avec java 1.8
			//agent1.discussionList.sort(DESCENDING_COMPARATOR_byIndiCost);
			//tempDiscussionList.sort(DESCENDING_COMPARATOR_byIndiCost);

			pOnScreen(condition, " --->> Total Potential Discussions  : "
					+ tempDiscussionList.size());

			pOnScreen(condition,
					" Liste des discussions Apres r�ordonnancement: ");
			if (agent1.firstDiscOrder.isEmpty())
				for( int t22 = 0; t22 < agent1.discussionList.size(); t22++ ) {
					Discussion d2 = (Discussion) agent1.discussionList.get(t22);
					pOnScreen(condition, " --->> Discussion (Id : "
							+ d2.discussionId + ") :" + t22 + " est "
							+ d2.discussionPath.getEdgeSet().toString());
					pOnScreen(condition,
							" --->> Cout indi :" + d2.discussionIndividualCost);
					pOnScreen(condition,
							" --->> Cout Est :" + d2.discussionEstimatedCost);
					pOnScreen(condition, " --->> Involved Agents: "
							+ d2.getAllInvolvedAgents().toString());

					for( int k = 0; k < d2.coalitionList.size(); k++ ) {
						Coalition c = (Coalition) d2.coalitionList.get(k);
						pOnScreen(condition,
								" -----------------> Coalition ID: "
										+ c.coalitionId);
						pOnScreen(condition,
								" -----------------> Coalition Owner: "
										+ c.agentOwner);
						pOnScreen(condition,
								" -----------------> Coalition agents: "
										+ c.agentList.toString());
						pOnScreen(condition,
								" -----------------> Coalition tasks: "
										+ c.edgeList.toString());
						pOnScreen(condition,
								" -----------------> Coalition agents Nbr: "
										+ c.agentNbrList.toString());
						ArrayList possibilities = new ArrayList();
						int nbr = Integer.parseInt(c.agentOwner.substring(5,
								c.agentOwner.length()));
						possibilities = formCoalition(c.agentNbrList, nbr);

						pOnScreen(condition,
								" -----------------> Liste des possibilit�s: "
										+ possibilities.size());
						for( int l = 0; l < possibilities.size(); l++ ) {
							ArrayList liste = (ArrayList) possibilities.get(l);
							pOnScreen(condition,
									" --------------------> Liste N " + l
											+ " : " + liste.toString());
						}

					}

					agent1.firstDiscOrder.add(d2.discussionId);
					agent1.firstDiscOrderCosts.add(d2.discussionIndividualCost);
				}

			// le cout de r�f�rence selon les discussions possibles
			agent1.Ct.referenceCost = Tr.getReferenceCost(agent1.plan,
					agent1.discussionList);
			if (agent1.refCost == 0) {
				agent1.refCost = agent1.Ct.referenceCost;
				agent1.discussionRef = Tr.getReferenceDiscussionIdCost(
						agent1.plan, agent1.discussionList);
			}

			/*
			 * if(budgetLimit){ Ct.costLimit = Tr.getCostLimit(discussionList,
			 * costLimitPercentage); // il faut calculer le costLimit uniquement
			 * � ce niveau. apres, c'est le reference cost qui verifie les
			 * conditions if(limitCost == 0){ limitCost = Ct.costLimit;
			 * Ct.referenceCost = limitCost; }
			 * 
			 * }
			 */

			agent1.Ct.referenceCost = Tr.getReferenceCost(agent1.plan,
					agent1.discussionList);
			// Ct.referenceCost = (float) ((float) ( (float)
			// Tr.getMinEstimatedCost(discussionList) + (float)
			// Tr.getMaxEstimatedCost(discussionList)) / 2);

			pOnScreen(condition,
					" --->> Reference Cost : " + agent1.Ct.referenceCost);

			agent1.Ct.alternativesCount = agent1.discussionList.size();

			pOnScreen(condition,
					" -----|>>>- After updating, my new Alternatives Number is : "
							+ agent1.Ct.alternativesCount);

			float minCost = Traitment.getMinEstimatedCost(agent1.discussionList);
			float maxCost = Traitment.getMaxEstimatedCost(agent1.discussionList);
			// if(1==2){
			// costLimitPercentage = (float) ((100 * minCost) /
			// Ct.referenceCost);
			// }
			pOnScreen(condition, " -----|>>>- initialisation .... ! ");
			pOnScreen(condition, " -----|>>>- minEstimated Cost: " + minCost);

		} // Fin de for(int agNbr=0; agNbr<agentlist.size(); agNbr++)
		// ----------------------------------------------------------------------

	}

	public final Comparator<Discussion>	ASCENDING_COMPARATOR_byIndiCost		= new Comparator<Discussion>() {

																						// Overriding
																						// the
																						// compare
																						// method
																						// to
																						// sort
																						// the
																						// age
																						@Override
																						public int compare(
																								Discussion o1,
																								Discussion o2) {
																							// TODO
																							// Auto-generated
																							// method
																							// stub
																							return (int) (o2.discussionIndividualCost
																									- o1.discussionIndividualCost);
																						}
																					};

	public final Comparator<Discussion>	DESCENDING_COMPARATOR_byIndiCost	= new Comparator<Discussion>() {

																						// Overriding
																						// the
																						// compare
																						// method
																						// to
																						// sort
																						// the
																						// age
																						@Override
																						public int compare(
																								Discussion o1,
																								Discussion o2) {
																							// TODO
																							// Auto-generated
																							// method
																							// stub
																							return (int) (o1.discussionIndividualCost
																									- o2.discussionIndividualCost);
																						}
																					};



	/**
	 * R�cup�rer la list des taches.
	 * 
	 * @param agentlist
	 * @return Liste de toutes les taches
	 */
																					/*
	public ArrayList getTasksList(ArrayList agentlist) {

		ArrayList visitedTasks = new ArrayList();
		ArrayList taskList = new ArrayList();
		for( int agNbr = 0; agNbr < agentlist.size(); agNbr++ ) {
			AgentModel agent = (AgentModel) agentlist.get(agNbr);

			for( Edge ed : agent.plan.getEachEdge() ) {
		//		if((ed.getId().equals("AG")) || (ed.getId().equals("GH")) || (ed.getId().equals("GI")) || (ed.getId().equals("GK")))
				if (!visitedTasks.contains(ed.getId())) {
					Task task = new Task(ed.getId());
					visitedTasks.add(ed.getId());
					task.agentList = this.getAllAgents(agentlist, ed.getId());
					taskList.add(task);
				}

			}
		}

		return taskList;
	}
*/
	
	/**
	 * R�cup�rer la list des taches.
	 * 
	 * @param agentlist
	 * @return Liste de toutes les taches
	 */
	public ArrayList getTasksList(ArrayList agentlist) {

		ArrayList visitedTasks = new ArrayList();
		ArrayList taskList = new ArrayList();
		int limit=0;
		ArrayList totalTasks = new ArrayList();
		for( int agNbr = 0; agNbr < agentlist.size(); agNbr++ ) {
			AgentModel agent = (AgentModel) agentlist.get(agNbr);
			for(int h=0; h<agent.allPaths.size(); h++){
				Graph path = (Graph) agent.allPaths.get(h);
				if(path.getEdgeSet().size()> limit){
					limit = path.getEdgeSet().size();
				}
			}
		}
		
		boolean end =false;
		int cmp = 0;
		while(!end){
			 boolean changed = false;
			 
			for( int agNbr = 0; agNbr < agentlist.size(); agNbr++ ) {
				AgentModel agent = (AgentModel) agentlist.get(agNbr);
				
				for(int h=0; h<agent.allPaths.size(); h++){
					Graph path = (Graph) agent.allPaths.get(h);
					if(path.getEdgeSet().size()> cmp){
						changed = true;
						String tache = "";
						int i=0;
						for( Edge ed : path.getEachEdge() ) {
							if(!totalTasks.contains(ed.getId())) totalTasks.add(ed.getId());
							if (i==cmp){
								tache =ed.getId();
								if (!visitedTasks.contains(tache)) {
									Task task = new Task(tache);
									visitedTasks.add(tache);
									task.agentList = this.getAllAgents(agentlist, tache);
									taskList.add(task);
								}
								break;
							}
							i++;
						}
						
				
					}
				}
			}
			
			cmp++;
			if(cmp > limit){
				end = true;
			}
		}
	
		//System.out.println("Total Tasks: : "+totalTasks.size());
		//System.out.println("All are: "+visitedTasks);
		return taskList;
	}


	/**
	 * Retourne la liste des agents ayant une tache task dans leur plan.
	 * 
	 * @param agentlist
	 *            liste des agents
	 * @param task
	 *            La tache � v�rifier
	 * @return liste des agents contenant la tache donn�e dans leur plan.
	 */
	public ArrayList getAllAgents(ArrayList agentlist, String task) {
		ArrayList listeAgent = new ArrayList();

		for( int agNbr = 0; agNbr < agentlist.size(); agNbr++ ) {
			AgentModel agent = (AgentModel) agentlist.get(agNbr);

			for( Edge ed : agent.plan.getEachEdge() ) {

				if (ed.getId().equals(task)) {
					String agName = agent.plan.getId();
					int nbr = Integer
							.parseInt(agName.substring(5, agName.length()));
					listeAgent.add(nbr);
					break;
				}
			}
		}

		return listeAgent;
	}



	/**
	 * Construire une liste d'entiers � partir d'une chaine de caract�res
	 * (s�par�s par des ,)
	 * 
	 * @param str
	 * @return
	 */
	public ArrayList getArrayListFromStr(String str) {

		ArrayList Arraylist = new ArrayList();

		String[] arr = str.split(",");

		// To ArrayList
		ArrayList l = new ArrayList(Arrays.asList(str.split(",")));

		for( int i = 0; i < l.size(); i++ ) {
			int t = Integer.parseInt((String) l.get(i));
			Arraylist.add(t);
		}

		return Arraylist;
	}



	/**
	 * Afficher les agents des coalitions sur les taches.
	 * 
	 * @param agentlist
	 */
	public void printAgentOnGraphs(ArrayList agentlist, int step) {

		for( int agNbr = 0; agNbr < agentlist.size(); agNbr++ ) {
			AgentModel agent = (AgentModel) agentlist.get(agNbr);

			for( Edge ed : agent.plan.getEachEdge() ) {

				for( int i = 0; i < agent.allCoalitionsList.size(); i++ ) {
					Coalition c = (Coalition) agent.allCoalitionsList.get(i);

					if (c.edgeList.contains(ed.getId())) {
						ed.setAttribute("label",
								c.agentForCoalitionList.toString());

						if (c.agentForCoalitionList.size() >= 2) {

							if ((step % 2) == 0) {
								ed.addAttribute("ui.class", "sharedFinalPath");
							}
							else {
								ed.addAttribute("ui.class",
										"closedSharedNegotiation");
							}

						}
						else {
							ed.addAttribute("ui.style", "fill-color: black;");
							ed.addAttribute("ui.class", "Initial");
						}
					}
				}

			}
		}

	}



	public void computeExclusiveTasks(boolean condition, ArrayList agentList) {

		for( int i = 0; i < agentList.size(); i++ ) {
			AgentModel agent = (AgentModel) agentList.get(i);

			for( int j = 0; j < agent.tasksList.size(); j++ ) {
				String task = (String) agent.tasksList.get(j);

				
				// Collecter les discussions contenant la tache
				ArrayList disLis = new ArrayList();
				for( int k = 0; k < agent.discussionList.size(); k++ ) {
					Discussion disc = (Discussion) agent.discussionList.get(k);
					for( Edge ed : disc.discussionPath.getEachEdge() ) {
						if (ed.getId().equals(task)) {
							disLis.add(disc);
							break;
						}
					}
				}

				// Construire l'ensemble des taches exclusives
				// -------------------------------------------
				ArrayList exclusiveTasks = new ArrayList();

				for( int k = 0; k < agent.discussionList.size(); k++ ) {
					Discussion disc = (Discussion) agent.discussionList.get(k);
					for( Edge ed : disc.discussionPath.getEachEdge() ) {
						if (!ed.getId().equals(task)) {
							// pOnScreen(condition," two not equals tasks: ed
							// is: "+ed.getId()+ " And task is: "+task);
							boolean exist = false;

							for( int l = 0; l < disLis.size(); l++ ) {
								Discussion d = (Discussion) disLis.get(l);
								if (d.agentOwner.equals(agent.plan.getId()))
									for( Edge edg : d.discussionPath
											.getEachEdge() ) {
										if (edg.getId().equals(ed.getId())) {
											// pOnScreen(condition," Task exist:
											// edg: "+edg.getId()+" and ed:
											// "+ed.getId()+" Agent:
											// "+agent.plan.getId() +" In disc
											// path
											// "+d.discussionPath.getEdgeSet().toString());
											exist = true;
											break;
										}
									}
							}

							if (!exist) {
								// pOnScreen(condition," ********|--> task
								// "+task);
								if (!exclusiveTasks.contains(ed.getId()))
									exclusiveTasks.add(ed.getId());
							}
						}
					}
				}

				agent.exclusiveTasks.add(exclusiveTasks);
			//	System.out.println(" ++> THE TASK: "+task);
			//	System.out.println(" ++> ITS EXCLUSIVE : "+exclusiveTasks.toString());

			}
		}
	
	}

	
public void	setTasksExclusiveTasks(ArrayList tasksList, ArrayList agentList){
	
	for(int i=0; i<tasksList.size(); i++){
		Task task = (Task) tasksList.get(i);
		ArrayList exclusiveTasks = new ArrayList();
		for(int j=0; j<agentList.size(); j++){
			AgentModel agent = (AgentModel) agentList.get(j);
			
			if(agent.tasksList.contains(task.task)){
				int pos = agent.tasksList.indexOf(task.task);
				ArrayList list = (ArrayList) agent.exclusiveTasks.get(pos);
				
				for(int t=0; t<list.size(); t++){
					String tache =(String) list.get(t);
					if(!exclusiveTasks.contains(tache)){
						exclusiveTasks.add(tache);
					}
				}
			}
		}
		task.exclusiveTasks = (ArrayList) exclusiveTasks.clone();
	}
}


	public void pOnScreen(boolean condition, String text) {
		if (condition)
			System.out.println(text);
	}



	/**
	 * Configurer l'ex�cution individuelle
	 * 
	 * @param agentList
	 */
	public void setIndividualExecution(ArrayList agentList, String task) {

		for( int h = 0; h < agentList.size(); h++ ) {
			AgentModel agent = (AgentModel) agentList.get(h);
			for( int k = 0; k < agent.discussionList.size(); k++ ) {
				Discussion disc = (Discussion) agent.discussionList.get(k);
				for( int m = 0; m < disc.coalitionList.size(); m++ ) {
					Coalition coal = (Coalition) disc.coalitionList.get(m);
					int ag = Integer.parseInt(coal.agentOwner.substring(5,
							coal.agentOwner.length()));
					if (coal.edgeList.contains(task)) {
						coal.agentForCoalitionList.clear();
						coal.agentForCoalitionList.add(ag);
					}
				}
			}
		}
	}
	
	






	/**
	 * Mettre � jour les coalitions des agents refletant une ex�cuttion
	 * collective en fonction des possibilit�s introduites.
	 * 
	 * @param agentList
	 *            Liste des agents
	 * @param selecteList
	 *            La lliste selection�e
	 * @param selectedTask
	 *            La tache selection�e
	 */
	public void setCollectiveExecution(ArrayList agentList,	ArrayList selecteList, String selectedTask) {
		
		for( int s = 0; s < selecteList.size(); s++ ) {
			String ag1 = "Agent" + (int) selecteList.get(s);

			for( int h = 0; h < agentList.size(); h++ ) {// //System.out.println("
															// - 2");
				AgentModel agent = (AgentModel) agentList.get(h);
				if (ag1.equals(agent.plan.getId())) {
					
					
					// System.out.println();
					// System.out.println(" -<->- "+agent.plan.getId()+"
					// -<->-");
					// System.out.println();
					for( int k = 0; k < agent.discussionList.size(); k++ ) {// //System.out.println("
																			// -
																			// 3");
						Discussion disc = (Discussion) agent.discussionList
								.get(k);
						// System.out.println(" -> Update Agent discussion: -
						// "+disc.discussionId+" - for the agent: ->
						// "+agent.plan.getId());
						for( int m = 0; m < disc.coalitionList.size(); m++ ) {// //System.out.println("
																				// -
																				// 4");
							Coalition coal = (Coalition) disc.coalitionList
									.get(m);

							int ag = Integer.parseInt(coal.agentOwner
									.substring(5, coal.agentOwner.length()));

							if (coal.edgeList.contains(selectedTask)) {

								// v�rification si pas tache exclusive ayant
								// dej� des agents affect�s
								boolean exist = false;

								for( int y = 0; y < agent.tasksList
										.size(); y++ ) {
									String t = (String) agent.tasksList.get(y);
									if (t.equals(selectedTask)) {
										ArrayList exclusive = (ArrayList) agent.exclusiveTasks
												.get(y);

										if (exclusive.size() >= 1)
											for( int u = 0; u < exclusive.size(); u++ ) {
												String t2 = (String) exclusive.get(u);
												for( int q = 0; q < agentList.size(); q++ ) {
													AgentModel agent2 = (AgentModel) agentList.get(q);
													for( int g = 0; g < agent2.discussionList.size(); g++ ) {
														Discussion d = (Discussion) agent2.discussionList.get(g);
														for( int r = 0; r < d.coalitionList.size(); r++ ) {
															Coalition c1 = (Coalition) d.coalitionList.get(r);
															if (c1.edgeList.contains(t2)) {
																if (c1.agentForCoalitionList.size() >= 2) {
																	exist = true;
																}
															}
														}
													}
												}
											}

										// break;
									}
								}
								if (!exist) {
									if (coal.agentForCoalitionList.size() <= 0) {
										if (selecteList.size() > 1) {
											coal.agentForCoalitionList = (ArrayList) selecteList.clone();
										}
										else {
											coal.agentForCoalitionList.clear();
											coal.agentForCoalitionList.add(ag);
										}

										// System.out.println(" -> updating the
										// coalition:
										// "+coal.edgeList.toString()+" ... >
										// Yes, Coalition Updated -> It will be
										// performed by agents:
										// "+liste.toString());
									}
									else {
										// System.out.println(" -> updating the
										// coalition:
										// "+coal.edgeList.toString()+" ... >
										// Yes, but already updated. It
										// contains:
										// "+coal.agentForCoalitionList.toString());
									}
								}
								else {
									coal.agentForCoalitionList.clear();
									coal.agentForCoalitionList.add(ag);
									// System.out.println(" -> updating the
									// coalition: "+coal.edgeList.toString()+"
									// ... > Yes, but exists conflict with
									// "+liste.toString()+" -> So, It will be
									// performed alone..!" );
								}

								// coal.agentForCoalitionList = liste;

							}
							else {
								// //System.out.println(" -> updating the
								// coalition: "+coal.edgeList.toString()+" ... >
								// Coalition not concerned");
							}
							/*
							 * if(coal.agentForCoalitionList.size()<=0){
							 * coal.agentForCoalitionList.add(e) }
							 */
						}
					}
				}

			}

		}

	}

	/**
	 * Mettre � jour les coalitions des agents refletant une ex�cuttion
	 * collective en fonction des possibilit�s introduites.
	 * 
	 * @param agentList
	 *            Liste des agents
	 * @param selecteList
	 *            La lliste selection�e
	 * @param selectedTask
	 *            La tache selection�e
	 * @param allTasksList Liste des taches
	 */
	public void setCollectiveExecution(ArrayList agentList,	ArrayList selecteList, String selectedTask, ArrayList allTasksList,
			ArrayList selectedCommonTasks) {
		
		for( int s = 0; s < selecteList.size(); s++ ) {
			String ag1 = "Agent" + (int) selecteList.get(s);

			for( int h = 0; h < agentList.size(); h++ ) {// //System.out.println("
															// - 2");
				AgentModel agent = (AgentModel) agentList.get(h);
				
				// to check
				//if(!agent.agentsExclusiveTasks.contains(selectedTask))
				if (ag1.equals(agent.plan.getId())) {
					
					// System.out.println();
					// System.out.println(" -<->- "+agent.plan.getId()+" -<->-");
					// System.out.println();
					for( int k = 0; k < agent.discussionList.size(); k++ ) {// //System.out.println("
																			// -
																			// 3");
						Discussion disc = (Discussion) agent.discussionList
								.get(k);
						// System.out.println(" -> Update Agent discussion: -
						// "+disc.discussionId+" - for the agent: ->
						// "+agent.plan.getId());
						for( int m = 0; m < disc.coalitionList.size(); m++ ) {// //System.out.println("
																				// -
																				// 4");
							Coalition coal = (Coalition) disc.coalitionList
									.get(m);

							int ag = Integer.parseInt(coal.agentOwner
									.substring(5, coal.agentOwner.length()));

							if (coal.edgeList.contains(selectedTask)) {

								// v�rification si pas tache exclusive ayant
								// dej� des agents affect�s
								boolean exist = false;

								for( int y = 0; y < agent.tasksList
										.size(); y++ ) {
									String t = (String) agent.tasksList.get(y);
									if (t.equals(selectedTask)) {
										ArrayList exclusive = (ArrayList) agent.exclusiveTasks
												.get(y);

										if (exclusive.size() >= 1)
											for( int u = 0; u < exclusive.size(); u++ ) {
												String t2 = (String) exclusive.get(u);
												for( int q = 0; q < agentList.size(); q++ ) {
													AgentModel agent2 = (AgentModel) agentList.get(q);
													for( int g = 0; g < agent2.discussionList.size(); g++ ) {
														Discussion d = (Discussion) agent2.discussionList.get(g);
														for( int r = 0; r < d.coalitionList.size(); r++ ) {
															Coalition c1 = (Coalition) d.coalitionList.get(r);
															if (c1.edgeList.contains(t2)) {
																if (c1.agentForCoalitionList.size() >= 2) {
																	exist = true;
																}
															}
														}
													}
												}
											}

										// break;
									}
								}
								if (!exist) {
									if (coal.agentForCoalitionList.size() <= 0) {
										if (selecteList.size() > 1) {
											coal.agentForCoalitionList = (ArrayList) selecteList.clone();
											if(!selectedCommonTasks.contains(selectedTask)){
												selectedCommonTasks.add(selectedTask);
											}
										}
										else {
											coal.agentForCoalitionList.clear();
											coal.agentForCoalitionList.add(ag);
										}

										// System.out.println(" -> updating the
										// coalition:
										// "+coal.edgeList.toString()+" ... >
										// Yes, Coalition Updated -> It will be
										// performed by agents:
										// "+liste.toString());
									}
									else {
										// System.out.println(" -> updating the
										// coalition:
										// "+coal.edgeList.toString()+" ... >
										// Yes, but already updated. It
										// contains:
										// "+coal.agentForCoalitionList.toString());
									}
								}
								else {
									coal.agentForCoalitionList.clear();
									coal.agentForCoalitionList.add(ag);
									// System.out.println(" -> updating the
									// coalition: "+coal.edgeList.toString()+"
									// ... > Yes, but exists conflict with
									// "+liste.toString()+" -> So, It will be
									// performed alone..!" );
								}

								// coal.agentForCoalitionList = liste;

							}
							else {
								// //System.out.println(" -> updating the
								// coalition: "+coal.edgeList.toString()+" ... >
								// Coalition not concerned");
							}
							/*
							 * if(coal.agentForCoalitionList.size()<=0){
							 * coal.agentForCoalitionList.add(e) }
							 */
						}
					}
					
				///	System.out.println("			*--*> Updating the Agent Exclusive Tasks for: "+agent.plan.getId());
					
					//this.updateAgentExclusiveTasks(agent, agentList, allTasksList);
		
				///	System.out.println(" 				- To this round, its exclusive tasks are (In Time):" );
				///	System.out.println(" 						--> "+agent.agentsExclusiveTasks);
					
				}

			}

		}

	}





	/**
	 * Mettre � jour les coalitions des agents refletant une ex�cuttion
	 * collective en fonction des possibilit�s introduites.
	 * 
	 * @param agentList
	 *            Liste des agents
	 * @param selecteList
	 *            La lliste selection�e
	 * @param selectedTask
	 *            La tache selection�e
	 * @param allTasksList Liste des taches
	 */
	public void setCollectiveExecution(ArrayList agentList,	ArrayList selecteList, String selectedTask, ArrayList allTasksList,
			ArrayList selectedCommonTasks, ArrayList exploredTasks) {
		
		for( int s = 0; s < selecteList.size(); s++ ) {
			String ag1 = "Agent" + (int) selecteList.get(s);

			for( int h = 0; h < agentList.size(); h++ ) {// //System.out.println("
															// - 2");
				AgentModel agent = (AgentModel) agentList.get(h);
				
				// to check
				//if(!agent.agentsExclusiveTasks.contains(selectedTask))
				if (ag1.equals(agent.plan.getId())) {
					
					// System.out.println();
					// System.out.println(" -<->- "+agent.plan.getId()+" -<->-");
					// System.out.println();
					for( int k = 0; k < agent.discussionList.size(); k++ ) {// //System.out.println("
																			// -
																			// 3");
						Discussion disc = (Discussion) agent.discussionList
								.get(k);
						// System.out.println(" -> Update Agent discussion: -
						// "+disc.discussionId+" - for the agent: ->
						// "+agent.plan.getId());
						for( int m = 0; m < disc.coalitionList.size(); m++ ) {// //System.out.println("
																				// -
																				// 4");
							Coalition coal = (Coalition) disc.coalitionList
									.get(m);

							int ag = Integer.parseInt(coal.agentOwner
									.substring(5, coal.agentOwner.length()));

							if (coal.edgeList.contains(selectedTask)) {

								// v�rification si pas tache exclusive ayant
								// dej� des agents affect�s
								boolean exist = false;

								for( int y = 0; y < agent.tasksList
										.size(); y++ ) {
									String t = (String) agent.tasksList.get(y);
									if (t.equals(selectedTask)) {
										ArrayList exclusive = (ArrayList) agent.exclusiveTasks
												.get(y);

										if (exclusive.size() >= 1)
											for( int u = 0; u < exclusive.size(); u++ ) {
												String t2 = (String) exclusive.get(u);
												for( int q = 0; q < agentList.size(); q++ ) {
													AgentModel agent2 = (AgentModel) agentList.get(q);
													for( int g = 0; g < agent2.discussionList.size(); g++ ) {
														Discussion d = (Discussion) agent2.discussionList.get(g);
														for( int r = 0; r < d.coalitionList.size(); r++ ) {
															Coalition c1 = (Coalition) d.coalitionList.get(r);
															if (c1.edgeList.contains(t2)) {
																if (c1.agentForCoalitionList.size() >= 2) {
																	exist = true;
																}
															}
														}
													}
												}
											}

										// break;
									}
								}
								if (!exist) {
									if (coal.agentForCoalitionList.size() <= 0) {
										if (selecteList.size() > 1) {
											coal.agentForCoalitionList = (ArrayList) selecteList.clone();
											if(!selectedCommonTasks.contains(selectedTask)){
												selectedCommonTasks.add(selectedTask);
											}
										}
										else {
											coal.agentForCoalitionList.clear();
											coal.agentForCoalitionList.add(ag);
										}

										// System.out.println(" -> updating the
										// coalition:
										// "+coal.edgeList.toString()+" ... >
										// Yes, Coalition Updated -> It will be
										// performed by agents:
										// "+liste.toString());
									}
									else {
										// System.out.println(" -> updating the
										// coalition:
										// "+coal.edgeList.toString()+" ... >
										// Yes, but already updated. It
										// contains:
										// "+coal.agentForCoalitionList.toString());
									}
								}
								else {
									coal.agentForCoalitionList.clear();
									coal.agentForCoalitionList.add(ag);
									// System.out.println(" -> updating the
									// coalition: "+coal.edgeList.toString()+"
									// ... > Yes, but exists conflict with
									// "+liste.toString()+" -> So, It will be
									// performed alone..!" );
								}

								// coal.agentForCoalitionList = liste;

							}
							else {
								// //System.out.println(" -> updating the
								// coalition: "+coal.edgeList.toString()+" ... >
								// Coalition not concerned");
							}
							/*
							 * if(coal.agentForCoalitionList.size()<=0){
							 * coal.agentForCoalitionList.add(e) }
							 */
						}
					}
					
			///		System.out.println("			*--*> Updating the Agent Exclusive Tasks for: "+agent.plan.getId());
					
					//this.updateAgentExclusiveTasks(agent, agentList, allTasksList);
					this.updateAgentExclusiveTasksInTime(agent, agentList, allTasksList, exploredTasks);
					
			///		System.out.println(" 				- To this round, its exclusive tasks are (In Time):" );
			///		System.out.println(" 						--> "+agent.agentsExclusiveTasks);
					
				}

			}

		}

	}






	/**
	 * Calculer le cout gagn� par agent
	 * 
	 * @param agentList
	 */
	public void computeAgentGainedCost(ArrayList agentList) {

		for( int i = 0; i < agentList.size(); i++ ) {
			AgentModel agent = (AgentModel) agentList.get(i);
			Discussion discussion = null;
			int t = 0;
			boolean exist = false;
			for( int j = 0; j < agent.discussionList.size(); j++ ) {
				Discussion disc1 = (Discussion) agent.discussionList.get(j);
				// System.out.println("Discussion: "+disc1.discussionId+ " total
				// agents is: "+disc1.getTotalAgent());
				if (disc1.getTotalAgent() >= t) {
					exist = true;
					discussion = disc1;
					t = disc1.getTotalAgent();
				}
			}

			if (!exist) {
				for( int j = 0; j < agent.discussionList.size(); j++ ) {
					Discussion disc1 = (Discussion) agent.discussionList.get(j);
					if (disc1.discussionId == agent.discussionRef) {
						discussion = disc1;
						discussion.discussionFinalCost = discussion.discussionIndividualCost;
					}

				}
			}

			agent.gainedCost = agent.refCost - discussion.discussionFinalCost;
			agent.selectedFinalCost = discussion.discussionFinalCost;
		}
	}



	/**
	 * Caclculer l''eeficacit� totale dy syst�me
	 * 
	 * @param agentList
	 * @return
	 */
	public float computeSystemEffeciency(ArrayList agentList) {
		float sysEffeciency = 0;
		for( int i = 0; i < agentList.size(); i++ ) {
			AgentModel agent = (AgentModel) agentList.get(i);
			sysEffeciency += agent.gainedCost;
		}

		sysEffeciency = sysEffeciency / agentList.size();

		return sysEffeciency;
	}



	/**
	 * Sauvegarder la discussion selectionn� comme meilleur solution
	 * 
	 * @param agentList
	 *            Liste des agents
	 */
	public void setBestAlternative(ArrayList agentList) {
		
	///	System.out.println( "---------------   Setting the Best Alternatives ---------------");
		for( int i = 0; i < agentList.size(); i++ ) {
			AgentModel agent = (AgentModel) agentList.get(i);
			
	///		System.out.println( "---------------   Agent: "+agent.plan.getId());
			
			Discussion discussion = null;
			int t = 0;
			for( int j = 0; j < agent.discussionList.size(); j++ ) {
				Discussion disc1 = (Discussion) agent.discussionList.get(j);
				// System.out.println("Discussion: "+disc1.discussionId+ " total
				// agents is: "+disc1.getTotalAgent());
				if (disc1.getTotalAgent() >= t) {
					discussion = disc1;
					t = disc1.getTotalAgent();
				}
			}
			// System.out.println("Selected Updated ............> ");
			agent.bestCoalitions.clear();
			agent.bestCoalitionsAgents.clear();
			agent.bestCoalitionsCosts.clear();

			agent.selectedDisc = discussion;
			for( int h = 0; h < discussion.coalitionList.size(); h++ ) {
				Coalition c = (Coalition) discussion.coalitionList.get(h);
				agent.bestCoalitions.add(c.edgeList.clone());
				agent.bestCoalitionsAgents.add(c.agentForCoalitionList.clone());
				agent.bestCoalitionsCosts.add(c.coalitionFinalCost);
				
		///		System.out.println( "			Coalition: "+c.edgeList.toString());
		///		System.out.println( "			Agent List: "+c.agentForCoalitionList.toString());
		///		System.out.println( "			Coalition: "+c.coalitionFinalCost);
				
			}

		}
	}



	/**
	 * Afficher les discussions avec la meilleur efficacit�.
	 * 
	 * @param agentlist
	 *            Liste des agents
	 * @throws IOException 
	 */
	public void printSelectedDisc(ArrayList agentlist) throws IOException {

		for( int agNbr = 0; agNbr < agentlist.size(); agNbr++ ) {
			AgentModel agent = (AgentModel) agentlist.get(agNbr);
			//Discussion disc = agent.selectedDisc;

			PlanManagment pM = new PlanManagment(agent.plan.getId());
			pM.putCostsOnGraph(agent.plan);
			
			for( Edge ed : agent.plan.getEachEdge() ) {
				ed.addAttribute("ui.class", "Initial");
				ed.addAttribute("ui.label", (String)  ed.getAttribute("cost"));
			}
			

			/*
			 * for(Edge ed1:disc.discussionPath.getEachEdge()){ for(Edge
			 * ed2:agent.plan.getEachEdge()){
			 * if(ed2.getId().equals(ed1.getId())){ ed2.addAttribute("ui.class",
			 * "finalBestEffeciency"); ed2.setAttribute("ui.label",
			 * ed2.getAttribute("label")+" : "+agent.selectecdCost); break; } }
			 * }
			 */
			for( int i = 0; i < agent.bestCoalitions.size(); i++ ) {
				ArrayList task = (ArrayList) agent.bestCoalitions.get(i);
				for( Edge ed2 : agent.plan.getEachEdge() ) {
					if (task.contains(ed2.getId())) {
						ed2.addAttribute("ui.class", "finalBestEffeciency");
						ed2.setAttribute("ui.label",
								(String)  ed2.getAttribute("cost") + " : "
										+ agent.bestCoalitionsAgents.get(i)
												.toString()
										+ " -> New Cost "
										+ agent.bestCoalitionsCosts.get(i));
						break;
					}
				}
			}

		}
	}


/**
 * Mettre � jour la liste des agents exclusifs pour une tache donn�e.
 * @param task
 * @param agentList
 * @param allTasksList
 * @param liste 
 */
	public void updateTaskExclusiveAgents(String task, ArrayList agentList, ArrayList allTasksList, ArrayList liste) {
		// TODO Auto-generated method stub
		
		//System.out.println("  	**> The task: "+task);
		

		
		
		
	}

/**
 *  Mettre � jour la liste des tasks exclusives pour un agent donn�.
 * @param agent
 * @param agentList
 * @param allTasksList
 */
	private void updateAgentExclusiveTasks(AgentModel agent, ArrayList agentList,
	        ArrayList allTasksList) {
		// TODO Auto-generated method stub
			
		for(int i=0; i<agent.discussionList.size(); i++){
			Discussion disc = (Discussion) agent.discussionList.get(i);
			for(int j=0; j<disc.coalitionList.size(); j++){
				Coalition c = (Coalition) disc.coalitionList.get(j);
				if(c.agentForCoalitionList.size()>=2){
					for(int k=0; k<c.edgeList.size(); k++){
						String tache = (String) c.edgeList.get(k);
						int posi = agent.tasksList.indexOf(tache);
						ArrayList list = (ArrayList) agent.exclusiveTasks.get(posi);
						for(int l=0; l<list.size(); l++){
							String tache2 = (String) list.get(l);
							if(!agent.agentsExclusiveTasks.contains(tache2)){
								agent.agentsExclusiveTasks.add(tache2);
							}
						}
						
					}
				}
				
			}
		}
	}
	
	/**
	 *  Mettre � jour la liste des tasks exclusives pour un agent donn�, impliquant que les taches non explor�es.
	 * @param agent
	 * @param agentList
	 * @param allTasksList
	 */
		private void updateAgentExclusiveTasksInTime(AgentModel agent, ArrayList agentList,
		        ArrayList allTasksList, ArrayList exploredTasks) {
			// TODO Auto-generated method stub
				
			for(int i=0; i<agent.discussionList.size(); i++){
				Discussion disc = (Discussion) agent.discussionList.get(i);
				for(int j=0; j<disc.coalitionList.size(); j++){
					Coalition c = (Coalition) disc.coalitionList.get(j);
					if(c.agentForCoalitionList.size()>=2){
						for(int k=0; k<c.edgeList.size(); k++){
							String tache = (String) c.edgeList.get(k);
							int posi = agent.tasksList.indexOf(tache);
							ArrayList list = (ArrayList) agent.exclusiveTasks.get(posi);
							for(int l=0; l<list.size(); l++){
								String tache2 = (String) list.get(l);
								if(!exploredTasks.contains(tache2))
								if(!agent.agentsExclusiveTasks.contains(tache2)){
									agent.agentsExclusiveTasks.add(tache2);
								}
							}
							
						}
					}
					
				}
			}
		}
		
		
		
		
		//-------------------------------------------------------------
		//Chercher le nombre d'alternatives dans un plan  

	public Iterator<Path> getDijkstraAllPaths(Graph g){
			Iterator<Path> allPaths = null;

			
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
	
	
	
	
	
	
	
	
	
	
	

public  void savePlan(int planToCopy, int planToSave) throws FileNotFoundException{
	
	PrintWriter fToSave = null;
	try {
		fToSave = new PrintWriter(new FileWriter("AgentsPlans/SelectedPlans/Plan_Agent_"+planToSave+".txt"));
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	
	
	File fToCopy = new File("AgentsPlans/OriginalPlans/Plan_Agent_" + planToCopy + ".txt");

	if (fToCopy.exists()) {

		Scanner sc = new Scanner(fToCopy);
		while (sc.hasNextLine()) {
			String text = sc.nextLine();
				fToSave.println(text);
			}
	}
	
	
	fToSave.close();
	//fToCopy.close();
		
}



/**
 * 
 * @param planId
 * @return
 * @throws FileNotFoundException
 */
public Graph constructOriginalPlan(boolean condition, int planId, int plNbr )
		throws FileNotFoundException {
	Graph Plan = new SingleGraph("Agent" + plNbr);
	Plan.setStrict(false);
	Plan.setAutoCreate(true);
	Plan.addAttribute("ui.stylesheet", "url('file:config/Modele1.css')");
	Plan.addAttribute("ui.quality");
	Plan.addAttribute("ui.antialias");

	ArrayList addedNodes = new ArrayList();

	File f1 = new File("AgentsPlans/OriginalPlans/Plan_Agent_" + planId + ".txt");

	if (f1.exists()) {

		Scanner sc = new Scanner(f1);
		while (sc.hasNextLine()) {
			String text = sc.nextLine();
			String N1 = "";
			String N2 = "";
			String costV = "";
			boolean edge1 = true;
			boolean cost = false;
			boolean escape = false;

			for( int i = 0; i < text.length(); i++ ) {
				char c = text.charAt(i);

				if (c == '/') {
					escape = true;
					break;
				}

				if (c == ',') {
					edge1 = false;
				}

				if (c == ';') {
					cost = true;
				}

				if ((c != ',') && (c != ';')) {
					if (edge1) {
						N1 += c;
					}
					else if (!cost) {
						N2 += c;
					}
					if (cost) {
						costV += c;
					}
				}
			}

			if (!escape) {
				if (!addedNodes.contains(N1)) {
					Plan.addNode(N1);
					addedNodes.add(N1);
				}

				if (!addedNodes.contains(N2)) {
					Plan.addNode(N2);
					addedNodes.add(N2);
				}

				Plan.addEdge(N1 + N2 + "", N1, N2, true);
				// Plan.addEdge(N1+N2+"", N1, N2);

				// pOnScreen(condition,"++++++++> Adding edge: "+N1+N2+"");

				Edge e = Plan.getEdge(N1 + N2 + "");
				e.addAttribute("extraCost", "0" + costV);
				e.setAttribute("extraCost", "0" + costV);
			}
		}

		for( Node n : Plan.getEachNode() ) {
			n.setAttribute("ui.label", n.getId());
			// pOnScreen(condition,"-----------> Node Label: "+n.getId());
		}

		for( Edge e : Plan.getEachEdge() ) {
			e.setAttribute("ui.label", e.getId());
			// pOnScreen(condition,"-----------> Edge Label: "+e.getId());

		}

	}
	
	return Plan;
}


/**
 * Calculer le poid en profondeur des taches en common dans un plan donn�
 * @param agent
 * @param commonTasks
 * @return
 */
public float computeTotalPoid(AgentModel agent, ArrayList commonTasks){
	float totPoid = 0;
	int maxLenth = 0;
	int totalLenth = 0;
	for(int i=0; i<agent.allPaths.size(); i++){
		Graph path = (Graph) agent.allPaths.get(i);
		if(maxLenth< path.getEdgeCount()) maxLenth = path.getEdgeCount();
		
		totalLenth = totalLenth + path.getEdgeCount();
	}
	
	int cmp = 0;
	ArrayList explortedTasks = new ArrayList();
	while(cmp <maxLenth){
		for(int i=0; i<agent.allPaths.size(); i++){
			Graph path = (Graph) agent.allPaths.get(i);
				int localCmp = 0;
				for(Edge ed:path.getEachEdge()){
					if(localCmp == cmp){
						if(commonTasks.contains(ed.getId())){
							if(!explortedTasks.contains(ed.getId())){
								totPoid = totPoid + (maxLenth - cmp);
								explortedTasks.add(ed.getId());
							}
							
							
						}
					}
					localCmp++;
				}
			
		}
		cmp++;
	}
//	totPoid = (float) ((float) totPoid / (float) ( maxLenth * agent.allPaths.size()));
	totPoid = (float) ((float) totPoid / (float) (totalLenth));
	
	return totPoid;
}

	
}
