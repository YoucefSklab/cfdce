package com.cfdce.Control;
import java.io.IOException;
//import jade.util.leap.ArrayList;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;

import com.cfdce.FormationCoalitions.Coalition;
import com.cfdce.FormationCoalitions.Discussion;
import com.cfdce.Plan.ActionsCost;
import com.cfdce.Plan.AgentPlan;
import com.cfdce.Plan.PlanManagment;
import com.cfdce.Plan.subSets;


public class Traitment {

	// ------------------------------------------------------------------------------------

	/**
	 * Selectionner la liste des sous-ensembles d'actions en commun avec une
	 * alternative donn�e en param�tre.
	 * 
	 * @param subSetsList
	 *            Liste des sous ensemble en commun
	 * @param Myalternative
	 *            L'alternative � consid�rer
	 * @param altNbr
	 *            Le nombre (position) de l'alternative dans le plan local de
	 *            l'agent en cours
	 * @return Liste des sous-ensembles des actions en commun avec le restes des
	 *         plans des agents.
	 */
	public ArrayList getDiscussionsList(ArrayList subSetsList,
	        Graph Myalternative, int altNbr) {
		// TODO Auto-generated method stub

		// System.out.println(" <<<<<<<<<<<<<<<<<<<< getDiscussionsList
		// >>>>>>>>>>>>>>>>>>>>>");
		// System.out.println(" <<<<<<<<<<<<<<<<<<<< getDiscussionsList
		// >>>>>>>>>>>>>>>>>>>>>");

		ArrayList allAddedAction = new ArrayList();

		// System.out.println(" La taille de subSetsList est " +
		// subSetsList.size());

		// System.out.println(" --> getDiscussionsList : La taille de
		// subSetsList est " + subSetsList.size());

		ArrayList discussionsSetsList = new ArrayList();

		// --- � mettre ce passage sous forme d'une m�thode dans la classe
		// Algorithm

		ArrayList altSubSetsList = this.getAltSubSetsList(altNbr, subSetsList); // les
		                                                                        // sous
		                                                                        // ensembles
		                                                                        // de
		                                                                        // l'alt�rnative
		                                                                        // i

		// System.out.println(" -->> getDiscussionsList : La taille de
		// altSubSetsList est " + altSubSetsList.size());

		// System.out.println(" -->> >> >>> >>> Sub sets list for alt :" +
		// altNbr);
		// System.out.println(" -->> "+altSubSetsList.size());

		for( int j = 0; j < altSubSetsList.size(); j++ ) {

			subSets set = (subSets) altSubSetsList.get(j);

			// System.out.println("
			// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
			// ");
			// System.out.println(" --> "+set.agent);
			// System.out.println(" --> "+set.edgeList.toString());
			// System.out.println("
			// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
			// ");

			for( Edge e : Myalternative.getEachEdge() ) {
				// System.out.println(" -->>> Edge "+e.toString());
				// System.out.println(" -->>> Set "+set.edgeList.toString());

				for( int f = 0; f < set.edgeList.size(); f++ ) {
					String ed = (String) set.edgeList.get(f);
					if (ed.equals(e.getId())) {
						// System.out.println(" -->>> EEEEE");

						for( int y = 0; y < set.edgeList.size(); y++ ) {
							String act = (String) set.edgeList.get(y);

							if (!allAddedAction.contains(act)) {
								ArrayList tempArray = new ArrayList();
								tempArray.add(act);
								subSets tempSet = new subSets(set.agent,
								        set.myAlternative, 0, tempArray);
								allAddedAction.add(act);

								if (!discussionsSetsList.contains(tempSet)) {
									discussionsSetsList.add(tempSet);
									// System.out.println(" -->>> XXXXXXX");
								} // fin de if
							}
						}

						// subSets(String ag, int myAlt, int agentAlt, ArrayList
						// eList)
						/*
						 * if(!discussionsSetsList.contains(set)){
						 * discussionsSetsList.add(set);
						 * System.out.println(" -->>> XXXXXXX");
						 * 
						 * } // fin de if
						 */
					} // fin de if

				} // fin de for
			} // fin de for

		} // fin de for

		// System.out.println(" -->> getDiscussionsList : La taille de
		// discussionsSetsList au final est " + discussionsSetsList.size());

		// System.out.println(" <<<<<<<<<<<<<<<<<<<< getDiscussionsList
		// >>>>>>>>>>>>>>>>>>>>>");
		// System.out.println(" <<<<<<<<<<<<<<<<<<<< getDiscussionsList
		// >>>>>>>>>>>>>>>>>>>>>");

		return discussionsSetsList;
	} // fin de getDiscussionsList



	// ------------------------------------------------------------------------------------

	/**
	 * Selectioner tous les sous-ensembles des actions en commun pour une
	 * alternative donn�e.
	 * 
	 * @param i
	 *            Le nombre (position) de l'alternative � consid�rer
	 * @param subSetsList
	 *            L'ensemble de tous les sous-ensembles des actions en commun
	 *            avec les autres agents.
	 * @return Liste des sous-ensembles d'actions en commun avec l'alternative
	 *         introduite en param�tre.
	 */
	private ArrayList getAltSubSetsList(int i, ArrayList subSetsList) {
		// TODO Auto-generated method stub

		ArrayList altSubSetsList = new ArrayList();

		for( int j = 0; j < subSetsList.size(); j++ ) {

			subSets set = (subSets) subSetsList.get(j);

			if (set.myAlternative == i)
				altSubSetsList.add(set);

		} // fin de for

		return altSubSetsList;
	} // fin de getAltSubSetsList
	  // ------------------------------------------------------------------------------------



	/**
	 * Calculer la liste des coalitions possibles, � base des ensembles des
	 * actions en commun avec le restes des agents du syst�me.
	 * 
	 * @param discussionSetsList
	 *            Ensemble des actions en commun avec les agents du syst�me.
	 * @param MyAlternative
	 *            Une alternative donn�e, parmi les alternatives possibles pour
	 *            l'agent en cours
	 * @param pathNbr
	 *            La position (nombre) de l'aternative en param�tre, dans le
	 *            plan de l'agent en cours
	 * @param agOwner
	 *            Agent propri�taire
	 * @return Liste des coalitions possibles (� proposer) pour l'aternative
	 *         introduite en param�tre.
	 */
	public ArrayList getDiscussionCoalitionsList(ArrayList discussionSetsList,
	        Graph MyAlternative, int pathNbr, String agOwner) {

		// System.out.println(" -->>>>>>>>>>>>>>>> getDiscussionCoalitionsList
		// <<<<<<<<<<<<<<<<<<<<<<----");
		// System.out.println(" -->>>>>>>>>>>>>>>> getDiscussionCoalitionsList
		// <<<<<<<<<<<<<<<<<<<<<<----");

		ArrayList discussionCoalitionsList = new ArrayList();
		ArrayList discussionCoalitionsListFinal = new ArrayList();
		ArrayList coalitionList = new ArrayList();

		for( Edge e : MyAlternative.getEachEdge() ) {
			ArrayList setsList = new ArrayList(); // liste de tous les sous
			                                      // ensemble avec la tache e

			// System.out.println("
			// --------------------------------------------------------------------------->>>
			// Edge " + e.getId());
			// System.out.println(" -->>>> La taille de discussionSetsList est "
			// + discussionSetsList.size());

			for( int j = 0; j < discussionSetsList.size(); j++ ) {
				subSets set = (com.cfdce.Plan.subSets) discussionSetsList.get(j);

				for( int f = 0; f < set.edgeList.size(); f++ ) {

					String ed = (String) set.edgeList.get(f);
					// System.out.println("
					// --------------------------------------------------------------------------->>>
					// Action " + ed);

					if (ed.equals(e.getId())) {
						if (!setsList.contains(set)) {
							setsList.add(set);
							// System.out.println("
							// --------------------------------------------------------------------------->>>
							// Ajout de " + ed);
							// System.out.println("
							// --------------------------------------------------------------------------->>>
							// Agent " + set.agent);
						}
					} // fin de if
				} // fin de for

			} // fin de for

			// System.out.println(" -->>> setsList.add(set) Je suis sur ajout
			// des coalitions sur la liste des coalitions. Taille ->
			// "+setsList.size());
			// System.out.println(" -->>> setsList.add(set) :
			// "+setsList.toString());

			ArrayList agentList = new ArrayList(); // liste des agents de la
			                                       // coalitions
			ArrayList edgeList = new ArrayList(); // liste des edges de la
			                                      // coalitions

			// System.out.println(" -->> getDiscussionCoalitionsList La taille
			// de setsList est : "+setsList.size());

			if (!setsList.isEmpty()) {
				// Raffinnement des sous ensembles
				for( int j = 0; j < setsList.size(); j++ ) {

					subSets set = (com.cfdce.Plan.subSets) setsList.get(j);

					if (!agentList.contains(set.agent))
						agentList.add(set.agent);

				} // fin de for

				edgeList.add(e.getId());

				Coalition c = new Coalition(agentList, edgeList, agOwner);
				discussionCoalitionsList.add(c);
				// c.minimalDisplay(c);
				// System.out.println(" --> Je suis sur ajout des coalitions sur
				// la liste des coalitions");
			} // fin de if

		} // fin de for

		// System.out.println(" Je suis sur discussionCoalitionsList de
		// Traitement");

		// ajout un raffinement de l'ensemble des coalitions

		for( int j = 0; j < discussionCoalitionsList.size(); j++ ) {
			Coalition c1 = (Coalition) discussionCoalitionsList.get(j);

			for( int k = j + 1; k < discussionCoalitionsList.size(); k++ ) {
				Coalition c2 = (Coalition) discussionCoalitionsList.get(k);

				if ((c1.agentList.containsAll(c2.agentList))
				        && (c1.edgeList.containsAll(c2.edgeList))) {

					for( Iterator iterator = c2.agentList.iterator(); iterator
					        .hasNext(); ) {
						String agent = (String) iterator.next();
						if (!c1.agentList.contains(agent)) {
							c1.agentList.add(agent);
						}
					}

					// System.out.println("Remove");
					c2.minimalDisplay(c2);
					discussionCoalitionsList.remove(c2);
					j = 0;
					k = j + 1;
				} // fin de if

			} // fin de for

		} // fin de for

		return discussionCoalitionsList;
	} // fin de getDiscussionCoalitionsList
	  // ------------------------------------------------------------------------------------



	/**
	 * R�ordonner les discussions par le co�t estim�
	 * 
	 * @param list
	 *            Liste des discussions
	 * @return vrai si nouvel ordre trouv�, faux sinon
	 * @throws IOException
	 * 
	 */
	public boolean reorderDiscussionListByEstimatedCost(Graph agPlan,
	        ArrayList list) throws IOException {

		boolean hasBeenChanged = false;

		updateAllDiscussionsEstimatedCost(agPlan, list);

		for( int i = 0; i < list.size(); i++ ) {
			int position = i;
			Discussion dis1 = (Discussion) list.get(i);

			for( int j = i + 1; j < list.size(); j++ ) {
				Discussion dis2 = (Discussion) list.get(j);
				if (dis1.discussionEstimatedCost > dis2.discussionEstimatedCost) {
					Discussion disInter = (Discussion) list.get(i);
					dis1 = dis2;
					list.set(i, dis2);
					list.set(j, disInter);

					hasBeenChanged = true;
				}
			}
		}

		// System.out.println(" ++++++++++++>>>> hasBeenChanged =
		// "+hasBeenChanged);
		return hasBeenChanged;
	} // fin de reorderDiscussionListByEstimatedCost
	  // ------------------------------------------------------------------------------------



	/**
	 * R�ordonner la liste des discussion en fonction des propositions r��ues
	 * 
	 * @param receivedProposals
	 *            Liste des propositions re�ues
	 * @param discussionList
	 *            Liste des discussions
	 */
	public boolean reorderAfterProposition(
	        ArrayList<Coalition> receivedProposals, ArrayList discussionList) {

		boolean hasBeedChanged = false;
		System.out.println(" ");
		System.out.println("		--->    reorderAfterProposition   <----");
		System.out.println(
		        " R�ordonner par nombre d'acceptations n�cessaire (Estimation)");

		ArrayList orderedDiscList = new ArrayList();
		ArrayList neededAcceptCount = new ArrayList();

		for( Iterator iterator = discussionList.iterator(); iterator
		        .hasNext(); ) {
			Discussion disc = (Discussion) iterator.next();
			neededAcceptCount
			        .add(this.getNeededAcceptToGet(receivedProposals, disc));
			orderedDiscList.add(disc.discussionId);

		}

		System.out.println("Old Order : ");
		int nbr3 = 0;
		for( Iterator iterator = discussionList.iterator(); iterator
		        .hasNext(); ) {
			Discussion disc = (Discussion) iterator.next();
			System.out.println(
			        "Path ID " + disc.discussionId + "  (needed Accept : "
			                + ((float) neededAcceptCount.get(nbr3)) + ")--> "
			                + disc.discussionPath.getEdgeSet().toString());
			nbr3++;
		}

		for( int i = 0; i < neededAcceptCount.size(); i++ ) {
			int position = i;
			float nbr1 = (float) neededAcceptCount.get(i);

			for( int j = i + 1; j < neededAcceptCount.size(); j++ ) {
				float nbr2 = (float) neededAcceptCount.get(j);
				if (nbr1 > nbr2) {
					float Inter = (float) neededAcceptCount.get(i);
					nbr1 = nbr2;
					neededAcceptCount.set(i, nbr2);
					neededAcceptCount.set(j, Inter);
					int Inter2 = (int) orderedDiscList.get(i);
					orderedDiscList.set(i, (int) orderedDiscList.get(j));
					orderedDiscList.set(j, Inter2);
				}
			}
		}

		System.out.println("New Order : from -->  reorderAfterProposition");
		int nbr4 = 0;
		ArrayList newList = new ArrayList();

		for( int i = 0; i < orderedDiscList.size(); i++ ) {
			int discID = (int) orderedDiscList.get(i);
			Discussion disc = null;
			for( int y = 0; y < discussionList.size(); y++ ) {
				Discussion di = (Discussion) discussionList.get(y);
				if (di.discussionId == discID) {
					disc = (Discussion) discussionList.get(y);
					break;
				}
			}
			newList.add(disc);
			hasBeedChanged = true;
		}

		for( int i = 0; i < orderedDiscList.size(); i++ ) {

			int discId = (int) orderedDiscList.get(i);
			int indice = 0;
			Discussion dis1 = null;

			for( int y = 0; y < discussionList.size(); y++ ) {
				Discussion di = (Discussion) discussionList.get(y);
				if (di.discussionId == discId) {
					dis1 = (Discussion) discussionList.get(y);
					break;
				}
			}

			Discussion dis2 = (Discussion) discussionList.get(i);
			indice = discussionList.indexOf(dis1);
			discussionList.set(i, dis1);
			discussionList.set(indice, dis2);
		}

		// discussionList = newList;

		for( int i = 0; i < orderedDiscList.size(); i++ ) {
			Discussion disc = (Discussion) discussionList.get(i);

			System.out.println("Path ID " + disc.discussionId + "  is "
			        + disc.discussionPath.getEdgeSet().toString());

		}

		return hasBeedChanged;
	}



	/**
	 * Mise � jour des co�ts estim�s des discussions
	 * 
	 * @param discussionList
	 *            Liste des discussions
	 * @throws IOException
	 */
	public void updateAllDiscussionsEstimatedCost(Graph agPlan,
	        ArrayList discussionList) throws IOException {
		System.out.println(
		        " --> Traitment.updateAllDiscussionsEstimatedCost()     - Mise � jour des co�ts exprim�s des discussions");
		for( int i = 0; i < discussionList.size(); i++ ) {
			Discussion dis1 = (Discussion) discussionList.get(i);
			dis1.evaluateEstimatedCostForPropose(agPlan);
		}
	}



	/**
	 * R�ordonner les discussions par le co�t r�el
	 * 
	 * @param list
	 *            Liste des discussions
	 */
	public void reorderDiscussionListByRealCost(ArrayList list) {

		for( int i = 0; i < list.size(); i++ ) {
			int position = i;
			Discussion dis1 = (Discussion) list.get(i);

			for( int j = i + 1; j < list.size(); j++ ) {
				Discussion dis2 = (Discussion) list.get(j);
				if (dis1.discussionEstimatedCost > dis2.discussionEstimatedCost) {
					Discussion disInter = (Discussion) list.get(i);
					dis1 = dis2;
					list.set(i, dis2);
					list.set(j, disInter);
				}
			}
		}
	} // fin de reorderDiscussionListByRealCost
	  // ------------------------------------------------------------------------------------



	/**
	 * R�vision de la liste des d�scussions pour enlever celles qui d�passent le
	 * cout individuel () co�t de r�f�rence
	 * 
	 * @param list
	 * @param referenceCost
	 */
	public void removeCostlyDiscussionsFromDiscussionList(ArrayList list,
	        float referenceCost) {

		ArrayList listToDelete = new ArrayList();

		for( int i = 0; i < list.size(); i++ ) {

			Discussion disc = (Discussion) list.get(i);
			if (disc.discussionEstimatedCost >= referenceCost) {
				listToDelete.add(disc);
			}

		} // fin de for

		for( int i = 0; i < listToDelete.size(); i++ ) {

			Discussion disc = (Discussion) listToDelete.get(i);
			list.remove(disc);

		} // fin de for

	} // fin de removeCostlyDiscussionsFromDiscussionList
	  // ------------------------------------------------------------------------------------



	/**
	 * Calculer le cout de r�f�rence des discussions
	 * 
	 * @param list
	 *            Liste des discussions
	 * @return Le co�t de r�f�rence (Le plus faible co�t en r�alisation
	 *         individuelle des alternatives)
	 * @throws IOException
	 */
	public float getReferenceCost(Graph agPlan, ArrayList list)
	        throws IOException {

		float referenceCost = 100000000;

		for( int i = 0; i < list.size(); i++ ) {

			Discussion disc = (Discussion) list.get(i);
			disc.evaluateIndividualCost(agPlan);
			if (referenceCost > disc.discussionIndividualCost) {
				referenceCost = disc.discussionIndividualCost;
			}

		} // fin de for

		return referenceCost;
	} // fin de getReferenceCost
	  // ------------------------------------------------------------------------------------



	/**
	 * Retourner la discussion ayant le cout de r�f�rence
	 * 
	 * @param list
	 *            Liste des discussions
	 * @return Le co�t de r�f�rence (Le plus faible co�t en r�alisation
	 *         individuelle des alternatives)
	 * @throws IOException
	 */
	public int getReferenceDiscussionIdCost(Graph agPlan, ArrayList list)
	        throws IOException {

		float referenceCost = 100000000;
		int discussionId = 0;
		for( int i = 0; i < list.size(); i++ ) {

			Discussion disc = (Discussion) list.get(i);
			disc.evaluateIndividualCost(agPlan);
			if (referenceCost > disc.discussionIndividualCost) {
				referenceCost = disc.discussionIndividualCost;
				discussionId = disc.discussionId;
			}

		} // fin de for

		return discussionId;
	} // fin de getReferenceDiscussionIdCost
	  // ------------------------------------------------------------------------------------



	/**
	 * Calculer le cout de r�f�rence des discussions, apr�s relacement
	 * 
	 * @param list
	 *            Liste des discussions
	 * @return Le co�t de r�f�rence (Le plus faible co�t en r�alisation
	 *         individuelle des alternatives)
	 * @throws IOException
	 */
	public float getReferenceCostForResumption(ArrayList list, Graph agPlan)
	        throws IOException {

		float referenceCost = 100000000;

		for( int i = 0; i < list.size(); i++ ) {

			Discussion disc = (Discussion) list.get(i);

			for( int j = 0; j < disc.coalitionList.size(); j++ ) {
				Coalition c = (Coalition) disc.coalitionList.get(j);
				if (c.isFinal) {
					c.individualCost = c.coalitionRealCost;
				}
			}

			disc.evaluateEstimatedCostForPropose(agPlan);;

		} // fin de for

		for( int i = 0; i < list.size(); i++ ) {

			Discussion disc = (Discussion) list.get(i);

			if (referenceCost > disc.discussionIndividualCost) {
				referenceCost = disc.discussionIndividualCost;
			}

		} // fin de for

		return referenceCost;
	} // fin de getReferenceCost
	  // ------------------------------------------------------------------------------------



	/**
	 * Calculer la limite du budget en pourcentage de la moyenne entre le cout
	 * max et min
	 * 
	 * @param list
	 *            Liste des discussions
	 * @return Le co�t de r�f�rence (Le plus faible co�t en r�alisation
	 *         individuelle des alternatives)
	 */
	public float getCostLimit(ArrayList list, float pourcentage) {

		float costLimit = 100000000;
		float minCost = 1000000;
		float maxCost = 0;

		for( int i = 0; i < list.size(); i++ ) {
			Discussion disc = (Discussion) list.get(i);
			if (minCost >= disc.discussionIndividualCost) {
				minCost = disc.discussionIndividualCost;
			}
			if (maxCost <= disc.discussionIndividualCost) {
				maxCost = disc.discussionIndividualCost;
			}

		} // fin de for
		return ((((minCost + minCost) / 2) * pourcentage) / 100);
		//return ((((minCost + maxCost) / 2) * pourcentage) / 100);
	} // fin de getCostLimit
	  // ------------------------------------------------------------------------------------



	/**
	 * Afficher le Cout individuel Max et Min
	 * 
	 * @param list
	 *            Liste des discussions
	 */
	public void displayMinMaxIndividualCost(ArrayList list) {

		float costLimit = 100000000;
		float minCost = 1000000;
		float maxCost = 0;

		for( int i = 0; i < list.size(); i++ ) {
			Discussion disc = (Discussion) list.get(i);
			if (minCost >= disc.discussionIndividualCost) {
				minCost = disc.discussionIndividualCost;
			}
			if (maxCost <= disc.discussionIndividualCost) {
				maxCost = disc.discussionIndividualCost;
			}
		} // fin de for

		System.out.println("min individual Cost : " + minCost);
		System.out.println("max individual Cost : " + maxCost);

	} // fin de getCostLimit
	  // ------------------------------------------------------------------------------------



	/**
	 * Calculer la distance entre deux alternatives. Cette distance repr�sente
	 * le nombre d'actions en commun.
	 * 
	 * @param alt1
	 *            Premi�re alternative
	 * @param alt2
	 *            Deuxi�me alternative
	 * @return Le nombre d'actions en commun (Distance)
	 */
	public int computeAlternativesDIstance(Graph alt1, Graph alt2) {

		int dis = 0;

		for( Edge e1 : alt1.getEachEdge() ) {
			for( Edge e2 : alt2.getEachEdge() ) {
				if (e1.getId().equals(e2.getId())) {
					dis++;
				}
			} // fin de for
		} // fin de for

		return dis;
	} // fin de computeAlternativesDIstance
	  // -----------------------------------------------------------------------------------



	/**
	 * R�ordonner les discussions par Distance
	 * 
	 * @param discList
	 *            La liste des discussions
	 * @param agPlan
	 *            Le plan de l'agent en question
	 * @return Une liste de discussions, ordonn�es en fonction des distances.
	 */
	public ArrayList reorderDiscussionListByDistance(ArrayList discList,
	        ArrayList agPlan) {

		ArrayList distanceList = new ArrayList();

		for( int i = 0; i < discList.size(); i++ ) {
			int distanceGlobal = 0;
			Discussion disc = (Discussion) discList.get(i);

			for( int j = 0; j < agPlan.size(); j++ ) {
				AgentPlan aPlan = (AgentPlan) agPlan.get(j);
				for( int t = 0; t < aPlan.pathList.size(); t++ ) {
					distanceGlobal += computeAlternativesDIstance(
					        disc.discussionPath, (Graph) aPlan.pathList.get(t));
				} // fin de for
			} // fin de for
			distanceList.add(distanceGlobal);
		} // fin de for

		boolean finished = false;
		ArrayList newList = new ArrayList();
		ArrayList newdistanceList = new ArrayList();

		System.out.println(
		        "----->  Ordonnancement de la liste des discussions :  reorderDiscussionListByDistance");

		while (!finished) {
			int currentDist = 0;
			int position = 0;
			for( int i = 0; i < distanceList.size(); i++ ) {
				if (currentDist < (int) distanceList.get(i)) {
					currentDist = (int) distanceList.get(i);
					position = i;
				} // fin de if
			} // fin de for

			newList.add(discList.get(position));
			Discussion dis = (Discussion) discList.get(position);
			System.out.println("Discussion : "
			        + dis.discussionPath.getEdgeSet().toString());
			System.out.println("Distance  : " + distanceList.get(position));
			distanceList.remove(position);
			if (distanceList.isEmpty()) {
				finished = true;
			}
		} // fin de while

		return newList;
	} // fin de reorderDiscussionListByDistance
	  // ------------------------------------------------------------------------------------



	/**
	 * Calculer le nombre total d'instances des actions d'un plan, dans les
	 * autres plans des agents pr�sents dans le syst�me.
	 * 
	 * @param tab
	 *            Un tableau contenant le nombre d'instances des actions du plan
	 *            de l'agent courant, dispos�es dans l'ordre des actions dans le
	 *            plan.
	 * @return Le nombre Total des instances.
	 */
	public int getTotalTaskInstances(int[] tab) {
		int total = 0;
		for( int i = 0; i < tab.length; i++ ) {
			if (tab[i] >= 1)
				total += 1;
		}
		return total;
	}
	// ------------------------------------------------------------------------------------



	/**
	 * R�ordonner les discussions par nombre d�croissant des bifurcation dans
	 * les alternatives des agents.
	 * 
	 * @param discList
	 *            La liste des discussions
	 * @param agentsPlansList
	 *            Liste des tous les plans des agents
	 * @param ordre
	 *            L'ordre d'ordonnancement. 1-> D�croissant et 2 -> Croissant
	 * @return Une liste de discussions, ordonn�es en fonction des distances.
	 */
	public ArrayList reorderDiscussionListByBifurcation(ArrayList discList,
	        ArrayList agentsPlansList, int ordre) {

		ArrayList bifNbrList = new ArrayList();

		for( int i = 0; i < discList.size(); i++ ) {
			Discussion disc = (Discussion) discList.get(i);
			// System.out.println(" ------------->>> Nombre de Noeuds en
			// bifurcation : ");
			// System.out.println(" My Alternative :
			// "+disc.discussionPath.getEdgeSet().toString());
			int bifNbr = 0;
			for( int j = 0; j < agentsPlansList.size(); j++ ) {
				AgentPlan planAgent = (AgentPlan) agentsPlansList.get(j);
				bifNbr += disc.computeBifurcationNodesForAlternative(planAgent,
				        disc.discussionPath);

				// System.out.println(" Agent Plan :
				// "+planAgent.agentOwner.getLocalName());
				// System.out.println(" Nbr (Total tous les agents jusqu'� ici)
				// : "+bifNbr);
			}
			// System.out.println(" Le Total pour le Path est : "+bifNbr);
			bifNbrList.add(bifNbr);
		} // fin de for

		boolean finished = false;
		ArrayList newList = new ArrayList();

		while (!finished) {
			int currentBifNbr = 0;
			int position = 0;
			for( int i = 0; i < bifNbrList.size(); i++ ) {
				if (ordre == 1) {
					if (currentBifNbr < (int) bifNbrList.get(i)) {
						currentBifNbr = (int) bifNbrList.get(i);
						position = i;
					} // fin de if
				}
				if (ordre == 2) {
					if (currentBifNbr > (int) bifNbrList.get(i)) {
						currentBifNbr = (int) bifNbrList.get(i);
						position = i;
					} // fin de if
				}
			} // fin de for

			newList.add(discList.get(position));
			bifNbrList.remove(position);
			if (bifNbrList.isEmpty()) {
				finished = true;
			}
		} // fin de while

		return newList;
	} // fin de reorderDiscussionListByBifurcationDec
	  // ------------------------------------------------------------------------------------



	/**
	 * R�ordonner les discussions par nombre d�croissant de la somme des degr�s
	 * des actions.
	 * 
	 * @param discList
	 *            La liste des discussions
	 * @param ordre
	 *            L'ordre d'ordonnancement. 1-> D�croissant et 2 -> Croissant
	 * @return Une liste de discussions, ordonn�es en fonction des distances.
	 */
	public ArrayList reorderDiscussionListByDegree(ArrayList discList,
	        int ordre) {
		ArrayList discussionsIntDegreeList = new ArrayList();
		ArrayList discussionsFloatDegreeList = new ArrayList();
		// System.out.println("Ici La m�thode ---->
		// reorderDiscussionListByDegree");

		for( int i = 0; i < discList.size(); i++ ) {
			Discussion disc = (Discussion) discList.get(i);
			int degree = getDiscussionDegree(disc);
			if (ordre <= 2) // 1 ou 2
				discussionsIntDegreeList.add(degree);
			if (ordre >= 2) // 3 ou 4
				discussionsFloatDegreeList.add((float) ((float) degree
				        / (float) disc.discussionPath.getEdgeCount()));
			// System.out.println("Le degr�e de cette discussion est "+degree);
			// System.out.println(" ----> Degree Is "+degree);
			// System.out.println(" ----> Edge Count Is
			// "+disc.discussionPath.getEdgeCount());
			// System.out.println(" ----> Average Degree Is "+((float)((float)
			// degree/ (float) disc.discussionPath.getEdgeCount())));
		} // fin de for

		boolean finished = false;
		ArrayList newDiscList = new ArrayList();

		while (!finished) {
			int currentDiscNbr = 0;
			float currentFloatDiscNbr = 0;
			int position = 0;

			if (ordre <= 2) // le degr� en Int
				for( int i = 0; i < discussionsIntDegreeList.size(); i++ ) {
					if (ordre == 1) {
						if (currentDiscNbr < (int) discussionsIntDegreeList
						        .get(i)) {
							currentDiscNbr = (int) discussionsIntDegreeList
							        .get(i);
							position = i;
						} // fin de if
					}
					if (ordre == 2) {
						if (currentDiscNbr > (int) discussionsIntDegreeList
						        .get(i)) {
							currentDiscNbr = (int) discussionsIntDegreeList
							        .get(i);
							position = i;
						} // fin de if
					}
				} // fin de for

			if (ordre >= 2) // le degr� en Moyenne
				for( int i = 0; i < discussionsFloatDegreeList.size(); i++ ) {
					if (ordre == 1) {
						if (currentFloatDiscNbr < (float) discussionsFloatDegreeList
						        .get(i)) {
							currentFloatDiscNbr = (float) discussionsFloatDegreeList
							        .get(i);
							position = i;
						} // fin de if
					}
					if (ordre == 2) {
						if (currentFloatDiscNbr > (float) discussionsFloatDegreeList
						        .get(i)) {
							currentFloatDiscNbr = (float) discussionsFloatDegreeList
							        .get(i);
							position = i;
						} // fin de if
					}
				} // fin de for

			newDiscList.add(discList.get(position));
			if (ordre <= 2) // 1 ou 2
				discussionsIntDegreeList.remove(position);
			if (ordre >= 2) // 3 ou 4
				discussionsFloatDegreeList.remove(position);
			if (discussionsIntDegreeList.isEmpty()) {
				finished = true;
			}
		} // fin de while

		return newDiscList;
	} // fin de reorderDiscussionListByDegree
	  // ------------------------------------------------------------------------------------



	public int getDiscussionDegree(Discussion disc) {
		int degree = 0;
		// System.out.println("The action degree ----> ");
		for( Edge ed : disc.discussionPath.getEachEdge() ) {
			boolean exist = false;
			for( int i = 0; i < disc.coalitionList.size(); i++ ) {
				Coalition c = (Coalition) disc.coalitionList.get(i);

				if (c.edgeList.contains(ed.getId())) {
					// System.out.println("The action degree ---->
					// "+ed.getId()+" Is "+c.agentList.size());
					degree += (c.agentList.size() - 1);
					exist = true;
				}
			}
			// if(!exist) degree+=1;
		}

		return degree;
	}



	/**
	 * Mise � jour de l'�tat de l'agent en cours par la suppression des donn�es
	 * relatives � un agent qui vient de quitter le syst�me.
	 * 
	 * @param receivedProposals
	 *            Liste de toutes les propoistions r��ues
	 * @param discussionList
	 *            Liste des discussions
	 * @param actionCostList
	 *            liste des couts des actions (Les couts Publiques)
	 * @param maxValidityTime
	 *            Le temps de validit� maximum d'une proposition
	 * @param ag
	 *            L'agent sortant.
	 * @throws IOException
	 */
	public void removeAgentAndRelatedData(Graph agPlan,
	        ArrayList receivedProposals, ArrayList discussionList,
	        ArrayList actionCostList, long maxValidityTime, String ag)
	        throws IOException {
		System.out.println(
		        "X revoir peut etre faut mettre � jour la list -- agentAcceptedCoalitionIdList --");
		System.out.println(
		        "x>--------------------->>>>> Mise � jour des diff�rentes Liste apres suppression de l'agent : "
		                + ag);

		for( int i = 0; i < discussionList.size(); i++ ) {
			Discussion disc = (Discussion) discussionList.get(i);
			for( int j = 0; j < disc.coalitionList.size(); j++ ) {
				Coalition c = (Coalition) disc.coalitionList.get(j);

				if (c.agentList.contains(ag)) {
					c.agentList.remove(ag);
					c.sent = false; // Marquer la coalition comme non envoy�e.
				}

				if (c.agentAcceptedList.contains(ag)) {
					int index = c.agentAcceptedList.indexOf(ag);
					c.agentAcceptedList.remove(ag);
					c.acceptanceReceivedTime.remove(index);
					c.acceptanceStatus.remove(index);

				}

				if (c.agentRecievedAcceptedProposalList.contains(ag)) {
					int index = c.agentRecievedAcceptedProposalList.indexOf(ag);
					c.agentRecievedAcceptedProposalList.remove(ag);
					c.agentAcceptedAcceptCoalitionIdList.remove(index); // ICI
					// c.agentAcceptedProposalCoalitionIdList.remove(index); //
					// ICI � Confirmer
				}
				/*
				 * if(c.agentRecievedAcceptedProposalList.contains(ag)) { int
				 * index = c.agentRecievedAcceptedProposalList.indexOf(ag);
				 * c.agentRecievedAcceptedProposalList.remove(ag); //
				 * c.agentAcceptedProposalCoalitionIdList.remove(index);
				 * c.agentAcceptedAcceptCoalitionIdList.remove(index); }
				 */
				if (c.agentRecievedConfirmationList.contains(ag))
					c.agentRecievedConfirmationList.remove(ag);
				//
				// c.evaluateCoalitionEstimatedCost(agPlan);
				// c.evaluateCoalitionRealCost(agPlan, actionCostList,
				// maxValidityTime);
			}

			// supprimer les propositions re�ues de l'agent sortant
			for( int j = 0; j < receivedProposals.size(); j++ ) {
				Coalition c = (Coalition) receivedProposals.get(j);
				if (c.agentOwner.equals(ag)) {
					receivedProposals.remove(j);
					j--;
				}
			}

		}

	}



	/**
	 * Mise � jour des co�ts r�els de toutes les coalitions, selon les
	 * discussions correspondantes
	 * 
	 * @param discussionList
	 *            Liste des discussions
	 * @param maxValidityTime
	 *            La dur�e maximale de validit� des messages d"acceptation des
	 *            coalitions
	 * @throws IOException
	 */
	public void updateAllCoalitionsRealCosts(Graph agPlan,
	        ArrayList discussionList, long maxValidityTime) throws IOException {
		for( int i = 0; i < discussionList.size(); i++ ) {
			Discussion disc = (Discussion) discussionList.get(i);
			for( int j = 0; j < disc.coalitionList.size(); j++ ) {
				Coalition c = (Coalition) disc.coalitionList.get(j);
				c.updateCoalitionAcceptanceValidity(maxValidityTime);
				c.evaluateEstimatedForConfirm(agPlan);
			}
		}
	}



	/**
	 * Afficher l'�tat de validit� des acceptations sur les coalitions
	 * 
	 * @param discussionList
	 */
	public void displayCoalitionsAcceptValidity(ArrayList discussionList) {
		for( int i = 0; i < discussionList.size(); i++ ) {
			Discussion disc = (Discussion) discussionList.get(i);
			for( int j = 0; j < disc.coalitionList.size(); j++ ) {
				System.out.println("------------------------------------");
				Coalition c = (Coalition) disc.coalitionList.get(j);
				for( int k = 0; k < c.agentAcceptedList.size(); k++ ) {
					System.out.println("Coalition Id " + c.coalitionId);
					System.out.println("Coalition agent "
					        + ((String) c.agentAcceptedList.get(k)));
					System.out
					        .println("Coalition act " + c.edgeList.toString());
					System.out.println("Coalition time "
					        + c.acceptanceReceivedTime.get(k));
					System.out.println(
					        "Coalition Status " + c.acceptanceStatus.get(k));
				}
			}
		}
	}



	/**
	 * Affichage de la liste des Proposition re�ues par tous les agents.
	 * 
	 * @param receivedProposals
	 *            liste des propositions re�ues.
	 */
	public void diplayReceivedProposalsList(
	        ArrayList<Coalition> receivedProposals, String titre) {
		System.out.println("--> List of received Proposals : " + titre);
		System.out.println(" - --> Total des propositions re�ues : "
		        + receivedProposals.size());
		for( Iterator iterator = receivedProposals.iterator(); iterator
		        .hasNext(); ) {
			Coalition c = (Coalition) iterator.next();
			System.out.println("-------------------- ");
			System.out.println(" Coalition Id :" + c.coalitionId);
			System.out.println("From Agent : " + c.agentOwner);
			System.out.println("It's discussion : " + c.discussionId);
			System.out.println("c.actionList  : " + c.actionList.toString());
			System.out.println("c.agentList : " + c.agentList.toString());

		}
	}



	/**
	 * Une Selection de propositions depuis la liste des propositions re�ues,
	 * envoy�es par un agent donn�es
	 * 
	 * @param receivedProposals
	 *            Liste de toutes les propositions re�ues
	 * @param agent
	 *            l'agent pour lequel on veut s�lectionner les propostions
	 * @return Une liste de propositions envoy�es par un agent donn�.
	 */
	public ArrayList getReceivedProposalsFromAgent(
	        ArrayList<Coalition> receivedProposals, String agent) {
		ArrayList proposalsList = new ArrayList();
		for( Iterator iterator = receivedProposals.iterator(); iterator
		        .hasNext(); ) {
			Coalition c = (Coalition) iterator.next();
			if (c.agentOwner.equals(agent)) {
				proposalsList.add(c);
			}
		}
		return proposalsList;
	}



	/**
	 * Une s�lection de propositions depuis la liste des propositions re�ues,
	 * dont un agent donn� figure sur la liste des agents concern�s.
	 * 
	 * @param receivedProposals
	 *            Liste de toutes les propositions re�ues
	 * @param agent
	 *            L'agent � consid�rer dans la liste des agents concern�s
	 * @return Une liste de propositions dont un agent donn� fait partie
	 */
	public ArrayList getReceivedProposalsWhereAgentIn(
	        ArrayList<Coalition> receivedProposals, String agent) {
		ArrayList proposalsList = new ArrayList();
		for( Iterator iterator = receivedProposals.iterator(); iterator
		        .hasNext(); ) {
			Coalition c = (Coalition) iterator.next();
			if (c.agentList.contains(agent)) {
				proposalsList.add(c);
			}
		}
		return proposalsList;
	}



	/**
	 * R�cup�ration de la liste des propositions envoy�es par un agent donn�es,
	 * et qui comporte un autre agent donn�
	 * 
	 * @param receivedProposals
	 *            Liste de toutes les propisitons re�ues
	 * @param fromAgent
	 *            L'agent (emmeteur) � selectionner, des propositions �
	 *            selectionner
	 * @param agentIn
	 *            L'agent qui doit figurer sur la liste des agents des
	 *            propositions � selectionner
	 * @return Liste de propositions
	 */
	public ArrayList getReceivedProposalsFromAgentWhereAgentIn(
	        ArrayList<Coalition> receivedProposals, String fromAgent,
	        String agentIn) {
		ArrayList proposalsList = new ArrayList();
		for( Iterator iterator = receivedProposals.iterator(); iterator
		        .hasNext(); ) {
			Coalition c = (Coalition) iterator.next();
			if (c.agentOwner.equals(fromAgent))
				if (c.agentList.contains(agentIn)) {
					proposalsList.add(c);
				}
		}
		return proposalsList;
	}



	/**
	 * R�cup�ration de la liste des propostions contenant une actions donn�e.
	 * 
	 * @param receivedProposals
	 *            Liste de toutes les propisitons re�ues
	 * @param action
	 *            L'action � consid�rer
	 * @return Liste des propositions
	 */
	public ArrayList getReceivedProposalsConcerningAction(
	        ArrayList<Coalition> receivedProposals, String action) {
		ArrayList proposalsList = new ArrayList();
		for( Iterator iterator = receivedProposals.iterator(); iterator
		        .hasNext(); ) {
			Coalition c = (Coalition) iterator.next();

			if (c.edgeList.contains(action)) {
				proposalsList.add(c);
			}
		}
		return proposalsList;
	}



	/**
	 * R�cup�ration de la liste des Agents ayant envoy�s de proposition sur une
	 * actions donn�e. Un agent par action, (si un agent envoie plusieurs fois
	 * une proposition, on ne competra l'agent qu'une seule fois)
	 * 
	 * @param receivedProposals
	 *            Liste de toutes les propisitons re�ues
	 * @param action
	 *            L'action � consid�rer
	 * @return Liste des agents ayant envoy�s une proposition concernant une
	 *         action donn�e
	 */
	public ArrayList getAgentsWhoSentProposalsForAction(
	        ArrayList<Coalition> receivedProposals, String action) {
		ArrayList agentsList = new ArrayList();
		for( Iterator iterator = receivedProposals.iterator(); iterator
		        .hasNext(); ) {
			Coalition c = (Coalition) iterator.next();

			if (c.edgeList.contains(action)) {
				if (!agentsList.contains(c.agentOwner))
					agentsList.add(c.agentOwner);
			}
		}
		return agentsList;
	}



	/**
	 * Retourne le nombre (potentiel) d'acceptations � collecter pour
	 * concr�tiser une alt�rnative donn�e sous forme de discussion (� base des
	 * propositions re�ues. Un prposition re�ue est consid�rer comme une
	 * acceptation)
	 * 
	 * @param receivedProposals
	 *            Liste de toutes les Propositions
	 * @param disc
	 *            Une discussion
	 * @return nombre r�el. La division du nombre d'acceptation requis, par le
	 *         total du nombre des propositins des agents impliqu�s
	 */
	public float getNeededAcceptToGet(ArrayList<Coalition> receivedProposals,
	        Discussion disc) {
		float neededAcceptNbr = 0;
		ArrayList neededAgentList = disc.getNeededAgentListForDiscussion();

		// System.out.println("for the discussion "+disc.discussionId+" We need
		// accepts from : " + neededAgentList.toString());

		for( Edge ed : disc.discussionPath.getEachEdge() ) {

			ArrayList proposals = this.getReceivedProposalsConcerningAction(
			        receivedProposals, ed.getId());

			int tempNeededAcceptNbr1 = 0;
			for( Iterator iterator = disc.coalitionList.iterator(); iterator
			        .hasNext(); ) {
				Coalition c = (Coalition) iterator.next();
				if (c.edgeList.contains(ed.getId())) {
					tempNeededAcceptNbr1 = c.agentList.size();
					tempNeededAcceptNbr1--; // enlever l'agent courant
				}
			}

			int tempNeededAcceptNbr2 = 0;

			// System.out.println("For the action : "+ed.getId()+" we need
			// accepts from : " +
			// disc.getNeededAgentListForDiscussionByAction(ed.getId()).toString());

			for( Iterator iterator = neededAgentList.iterator(); iterator
			        .hasNext(); ) {
				String agent = (String) iterator.next();

				ArrayList proposalsSentByAgent = this
				        .getReceivedProposalsFromAgent(receivedProposals,
				                agent);

				for( Iterator iterator2 = proposalsSentByAgent
				        .iterator(); iterator2.hasNext(); ) {
					Coalition c = (Coalition) iterator2.next();

					if (c.edgeList.contains(ed.getId())) {
						tempNeededAcceptNbr2++;
					}
				}
			}

			if (tempNeededAcceptNbr2 > 0) {
				neededAcceptNbr += (float) tempNeededAcceptNbr1
				        / (float) tempNeededAcceptNbr2;
			}
			else {
				neededAcceptNbr += (float) tempNeededAcceptNbr1;
			}

			// System.out.println("The action "+ed.getId()+" needs
			// "+tempNeededAcceptNbr1+" Agents acceptance, and till now, we have
			// got "+tempNeededAcceptNbr2+" potentiel acceptances");
		}

		return neededAcceptNbr;
	}



	/**
	 * Mise � jour de l'�tat des discussions apr�s la reception de propositions
	 * 
	 * @param receivedProposals
	 *            Liste des propositions re�ues
	 * @param discussionList
	 *            Liste des discussions
	 */
	public boolean updateDiscussionsState(
	        ArrayList<Coalition> receivedProposals, ArrayList discussionList) {

		boolean hasBeedChanged = false;
		System.out.println(
		        "--->>>>>>>>>>>    updateDiscussionsState   <<<<<<<<<<<<----");

		ArrayList orderedDiscList = new ArrayList();
		ArrayList neededAcceptCount = new ArrayList();
		int nbr = 0;
		for( Iterator iterator = discussionList.iterator(); iterator
		        .hasNext(); ) {
			Discussion disc = (Discussion) iterator.next();
			neededAcceptCount
			        .add(this.getNeededAcceptToGet(receivedProposals, disc));
			orderedDiscList.add(nbr);
			nbr++;
		}

		System.out.println("Old Order : from --> updateDiscussionsState");
		int nbr3 = 0;
		for( Iterator iterator = discussionList.iterator(); iterator
		        .hasNext(); ) {
			Discussion disc = (Discussion) iterator.next();
			System.out.println(
			        "Path ID " + disc.discussionId + "  (needed Accept : "
			                + ((float) neededAcceptCount.get(nbr3)) + ")--> "
			                + disc.discussionPath.getEdgeSet().toString());
			nbr3++;
		}

		for( int i = 0; i < neededAcceptCount.size(); i++ ) {
			int position = i;
			float nbr1 = (float) neededAcceptCount.get(i);

			for( int j = i + 1; j < neededAcceptCount.size(); j++ ) {
				float nbr2 = (float) neededAcceptCount.get(j);
				if (nbr1 > nbr2) {
					float Inter = (float) neededAcceptCount.get(i);
					nbr1 = nbr2;
					neededAcceptCount.set(i, nbr2);
					neededAcceptCount.set(j, Inter);
					int Inter2 = (int) orderedDiscList.get(i);
					orderedDiscList.set(i, orderedDiscList.get(j));
					orderedDiscList.set(j, Inter2);
				}
			}
		}

		System.out.println("New Order : from --> updateDiscussionsState ");
		int nbr4 = 0;
		ArrayList newList = new ArrayList();

		for( int i = 0; i < orderedDiscList.size(); i++ ) {
			int position = (int) orderedDiscList.get(i);
			Discussion disc = (Discussion) discussionList.get(position);
			newList.add(disc);
			hasBeedChanged = true;
			System.out.println("Path ID " + disc.discussionId
			        + " (needed Accept : " + ((float) neededAcceptCount.get(i))
			        + ")--> " + disc.discussionPath.getEdgeSet().toString());

		}

		discussionList = newList;

		System.out.println("New Discussion List");
		for( int i = 0; i < orderedDiscList.size(); i++ ) {

			Discussion disc = (Discussion) discussionList.get(i);

			System.out.println("Path ID " + disc.discussionId + "  is "
			        + disc.discussionPath.getEdgeSet().toString());

		}

		return hasBeedChanged;
	}



	/**
	 * Mise � jour des param�tres de toutes les discussions
	 * 
	 * @param discussionList
	 * @param plan
	 *            Le plan de l'agent en cours
	 */
	public void updateDiscussionsParameters(ArrayList receivedProposals,
	        ArrayList discussionList, Graph plan) {

		System.out.println(
		        "         ---->    updateDiscussionsParameters   <----");

		for( Iterator iterator = discussionList.iterator(); iterator
		        .hasNext(); ) {
			Discussion disc = (Discussion) iterator.next();
			disc.computeDiscussionParameters(receivedProposals, discussionList,
			        plan);
		}

	}



	/**
	 * Calculer la probabilit� de r�alisation d'une discussion
	 * 
	 * @param discussionList
	 *            Liste des discussions
	 * @param sentDiscussionList
	 *            Liste des discussions envoy�es.
	 * @param actionsFrequencyByAgent
	 *            Liste des fr�quences des actions envoy�es par les autres
	 *            agents.
	 * @param agentsHaveMyPlan
	 *            Liste des agents ayant le plan de l'agent courant
	 * @param agentsList
	 *            Liste des agents du syst�me.
	 * @param plMgt
	 *            Instance de la classe PlanManagment
	 * @param oldAgentsOrder
	 *            Liste refletant l'ancien ordre des agents
	 * @param newAgentsOrder
	 *            Liste du nouvel ordre des agents
	 * @param val
	 *            tableau des valeurs des indices de valorisation des param�tres
	 * @param newAgentsPropoProfile
	 *            les nouveaux profils des propositions des agents
	 * @param oldAgentsPropoProfile
	 *            anciens profils des propositions des agents
	 * @param lastSendDiscProfile
	 *            Profil de la derni�re proposition envoy�e aux agents.
	 * @return roundWhatToDoNext Le round
	 * @throws IOException
	 * @agentsPlansList Liste des plans des agents.
	 */
	public Hashtable computeDiscussionsProbability(ArrayList discussionList,
	        ArrayList sentDiscussionList, ArrayList actionsFrequencyByAgent,
	        ArrayList agentsList, ArrayList agentsHaveMyPlan,
	        AgentPlan localPlan, PlanManagment plMgt, ArrayList oldAgentsOrder,
	        ArrayList newAgentsOrder, int val[],
	        ArrayList newAgentsPropoProfile, ArrayList oldAgentsPropoProfile,
	        ArrayList lastSendDiscProfile, int globalNegotiationRound,
	        int maxRound, ArrayList agentsPlansList) throws IOException {

		// {w, eCost, rf, sf, conv}
		float valorisationWeight = val[0]; // valeur de valorisation du nombre
		                                   // des proposition re�ues par rapport
		                                   // aux propositions possibles.
		float valorisationCost = val[1]; // valeur de valorisation du cout
		                                 // estim� des discussion.
		float valorisationRequestFrequency = val[2]; // 1 < frequence < 4 valeur
		                                             // de valorisation des
		                                             // fr�quences de reception
		                                             // des propositions par les
		                                             // agents.
		float valorisationSendFrequency = val[3]; // valeur du nombre d'envoie
		                                          // des propositions.
		float valorisationConvergence = val[4]; // valeur de valorisation de
		                                        // l'indice des convergence des
		                                        // discussions.
		// valorisationWeight(w) ---------------> Alternative weight; // valeur de valorisation du nombre des proposition re�ues par rapport aux propositions possibles.
		// valorisationRequestFrequency (rf) ---> Tasks weight ; // 1 < frequence < 4 valeur de valorisation des fr�quences de reception des propositions par les agents.
		// valorisationConvergence (conv) ------> Distance weight ; // valeur de valorisation de l'indice des convergence des discussions.
		// valorisationCost (eCost) ------------> Estimated utility weight ; // valeur de valorisation du cout estim� des discussion.
		// valorisationSendFrequency (sf) ------> Sent weight; // valeur du nombre d'envoie des propositions.


		int TotalAgentSys = agentsHaveMyPlan.size();
		String agName = localPlan.agentOwner;
		// extraActionCost extraCost = new extraActionCost(agName);

		Hashtable discussionsProbability = new Hashtable();

		System.out.println("");
		System.out.println("");
		System.out.println(
		        "==========================================================================================================================");
		System.out.println("");
		System.out.println("");
		System.out.println(
		        "                -->  computeDiscussionsProbability   (Affichage de Nouvelle Version)<--            ");
		System.out.println("");
		System.out.println("");
		System.out.println(
		        "==========================================================================================================================");

		float globalFrequ = 0;

		// -----------------------------------------------------------------------------------------------------------------------------------------------
		// Calculer la fr�quence globale de toutes les discussions
		// ----------------------------------------------------------------------------------------------------------------------------------------------
		for( int i = 0; i < discussionList.size(); i++ ) {
			Discussion disc = (Discussion) discussionList.get(i);

			for( int y = 0; y < disc.coalitionList.size(); y++ ) {
				Coalition c = (Coalition) disc.coalitionList.get(y);

				float reqFrequencyCaolition = 0;

				for( int k = 0; k < c.agentList.size(); k++ ) {
					String agent = (String) c.agentList.get(k);

					// le calcul de la fr�quence ne concerne que les agents
					// ayant re�us le plan de l'agent courant
					if (agentsHaveMyPlan.contains(agent)) {
						int indice;
						indice = agentsHaveMyPlan.indexOf(agent);

						ArrayList liste = (ArrayList) actionsFrequencyByAgent
						        .get(indice);

						int localFre = 0; // la fr�quence de l'action en cours
						                  // (une seule action)

						for( int t = 0; t < c.edgeList.size(); t++ ) {
							String action = (String) c.edgeList.get(t);
							// System.out.println(" - Action : "+ action );
							int indi = 0;
							for( Edge e : localPlan.graphPlan.getEachEdge() ) {
								if (e.getId().equals(action)) {
									localFre += (int) liste.get(indi);
									// globalFrequ += (int)liste.get(indi);
									// System.out.println(" Demand�e par l'agent
									// "+ agent + " nbr =
									// "+(int)liste.get(indi));
								}
								indi++;
							}
						}

						// System.out.println(" Il faut revoir le calcul de la
						// fr�quence !!!!! ");

						// System.out.println("--> La somme des fr�quence pour
						// "+c.coalitionId+" pour l'agent "+agent+" est
						// "+localFre);
						reqFrequencyCaolition += (float) localFre;

						// System.out.println(" ");
					}
				}

				reqFrequencyCaolition = (float) reqFrequencyCaolition
				        / (float) c.agentList.size();
				globalFrequ += reqFrequencyCaolition;
			}

		}
		// --------------------------------------------------------------------------------------------------------------------------------------------------
		// --------------------------------------------------------------------------------------------------------------------------------------------------

		// Calcul des param�tres caract�ristiques :
		// -----------------------------------------
		// ------------------------------------------------------------------------------------------------------------------------------------------------------------------
		for( int i = 0; i < discussionList.size(); i++ ) {

			// System.out.println("");

			Discussion disc = (Discussion) discussionList.get(i);

			ArrayList discActionList = disc.getActionsInList();

			// Calculer les Poids des agents :
			// -------------------------------
			ArrayList agentsHaveMyPlanWeight = new ArrayList();

			// --------------------------------------------------------------------------------
			// Ici Ajouter le poids des agents
			// ...............................................
			// System.out.println(" ");System.out.println(" ");
			// System.out.println(" ---------------------------- ");
			// System.out.println(" ");
			// System.out.println("Calcul des poids des agents : ");

			// System.out.println(" Discussion Id : "+disc.discussionId);
			// System.out.println(" Discussion Size:
			// "+disc.discussionPath.getEdgeSet().size());
			// System.out.println(" Discussion Path:
			// "+disc.discussionPath.getEdgeSet().toString());

			int discussionSize = disc.discussionPath.getEdgeSet().size();

			for( int t = 0; t < agentsHaveMyPlan.size(); t++ ) {
				String agent = (String) agentsHaveMyPlan.get(t);
				float discWeightLocalAg = 0;
				for( int k = 0; k < agentsPlansList.size(); k++ ) {
					AgentPlan agPlan = (AgentPlan) agentsPlansList.get(k);

					if (agent.equals(agPlan.agentOwner)) {

						// System.out.println(" Agent "+agPlan.agentOwner);

						for( int u = 0; u < agPlan.pathList.size(); u++ ) {
							Graph path = (Graph) agPlan.pathList.get(u);

							// System.out.println(" Agent Path
							// "+path.getEdgeSet().toString());
							// System.out.println(" Agent Path Size:
							// "+path.getEdgeSet().size());

							int intersection = 0;
							for( Edge e1 : disc.discussionPath.getEachEdge() ) {
								// System.out.print(" Path -> ");
								for( Edge e2 : path.getEachEdge() ) {
									// System.out.print(" -> "+e2.getId());
									if (e1.getId().equals(e2.getId())) {
										intersection++;
									}
								}

								// System.out.println(" -> intersection ->
								// "+intersection);
							}

							if (discussionSize > intersection) {
								float log = ((float) (discussionSize
								        - intersection) / discussionSize);
								float logValue = (float) Math.log(log);
								float expValue = (float) Math.exp(logValue);

								// System.out.println(" Log = "+log);
								// System.out.println(" LogValue = "+logValue);
								// System.out.println(" expValue = "+expValue);

								// System.out.println(" logarithme de (
								// (discussionSize - intersection) /
								// discussionSize ) = "+Math.log((
								// (float)(discussionSize - intersection) /
								// discussionSize )) );

								// System.out.println(" exponentiel de Math.log(
								// ( (discussionSize - intersection) /
								// discussionSize ) ) = "+(Math.exp(Math.log((
								// (float) (discussionSize - intersection) /
								// discussionSize ))) ) );

								// System.out.println(" : valeur interm�diaire :
								// -> "+ (float) (1- Math.exp(Math.log( (float)
								// ( (float) (discussionSize - intersection) /
								// discussionSize ) ))));

								discWeightLocalAg += (float) (1 - Math.exp(Math
								        .log((float) ((float) (discussionSize
								                - intersection)
								                / discussionSize))));
							}
							else {
								// System.out.println(" Chemins identiques, ->
								// incr�mentation de discWeightLocalAg de 1");
								// System.out.println(" : valeur interm�diaire :
								// -> "+ 1);
								discWeightLocalAg++;
							}

						}
						// System.out.println("========-----> le poid de la
						// discussion "+disc.discussionId+" par rapport �
						// l'agent "+agPlan.agentOwner+" Est
						// :"+discWeightLocalAg);

						break;
					} // fin de if
				}
				agentsHaveMyPlanWeight.add(discWeightLocalAg);
			}

			// ---------------------------------------------------------------------------------
			// ---------------------------------------------------------------------------------

			// System.out.println("");
			// System.out.println(" --> Traitement du cas de la discussion ID :
			// "+disc.discussionId+"
			// ------------------------------------------------------------------------");
			// System.out.println(" --> Profil de la discussion : ( Taille :
			// "+disc.discussionProfil.size()+" )
			// "+disc.discussionProfil.toString());
			// System.out.println(" --> Les profils des agents (Anciens et
			// nouveaux): ");

			float totalOldFprint = 0;
			float totalNewFprint = 0;
			float indConv = 0;
			int profileSize = disc.getDiscussionProfileSize();
			int actionNbrInDisc = disc.getActionsNumberInProfile();

			boolean existPreCoalition = false; // si l'agent courant est en
			                                   // convergence parfaite avec au
			                                   // moin un des agents
			// � consid�rer apr�s, deux, trois, ... , n agents.

			// Calcul de l'indice de convergence.
			// ---------------------------------------------
			for( int g = 0; g < oldAgentsOrder.size(); g++ ) {
				String agent1 = (String) oldAgentsOrder.get(g);
				ArrayList oldProfile = (ArrayList) oldAgentsPropoProfile.get(g);
				ArrayList newProfile = new ArrayList();

				// R�cup�rer le nouveau profil
				for( int h = 0; h < newAgentsOrder.size(); h++ ) {
					String agent2 = (String) newAgentsOrder.get(h);
					if (agent1.equals(agent2)) {
						newProfile = (ArrayList) newAgentsPropoProfile.get(h);
						// System.out.println("Profil New Collect� ");
						// System.out.println(" Agent probl�me -> "+agent2);
						break;
					}
				}

				// Si l'agent n'a pas propos� a cette it�rative (Round)
				if (newProfile.size() == 0)
					newProfile = disc.getEmptyDiscussionProfile(); //

				float fprintOld = computeAgentFootPrint(disc.discussionProfil,
				        oldProfile);
				float fprintNew = computeAgentFootPrint(disc.discussionProfil,
				        newProfile);
				totalOldFprint += fprintOld;
				totalNewFprint += fprintNew;

				// System.out.println("");
				// System.out.println("------");
				// System.out.println("");
				// System.out.println(" --> Discussion Profile :
				// "+disc.discussionProfil.toString());
				// System.out.println(" --> Agent : "+agent1+" Ancien Profil
				// "+oldProfile.toString()+" fprint "+fprintOld);
				// System.out.println(" --> Agent : "+agent1+" Nouveau Profil
				// "+newProfile.toString()+" fprint "+ fprintNew);

				String resultatConv = "";
				float dif_fprint = (float) (fprintNew - fprintOld);

				/*
				 * // si exactement la meme entre les deux agents :
				 * //---------------------------------------------- // R�cup�rer
				 * le nouveau profil for(int p=0; p<newAgentsOrder.size(); p++){
				 * String agent3 = (String) newAgentsOrder.get(p); ArrayList
				 * newProfile1 = (ArrayList) newAgentsPropoProfile.get(p); float
				 * lastAltSentFprint =
				 * computeAgentFootPrint(lastSendDiscProfile, newProfile1);
				 * if((float) (lastAltSentFprint -
				 * (Math.floor(lastAltSentFprint))) == 0){ existPreCoalition =
				 * true; System.out.println(
				 * "-> -> Existane de convergence parfaite avec agent : "+agent3
				 * ); break; }
				 * 
				 * }
				 */

				// ----------------------------------------------

				if (dif_fprint == 0) {
					resultatConv = " pas de changement  !!!!";
				}
				if (dif_fprint >= 1) {
					resultatConv = " Convergence Forte";
				}
				if ((dif_fprint > 0) && (dif_fprint < 1)) {
					resultatConv = " Divergence faible";
				}
				if ((dif_fprint < 0) && (dif_fprint > -1)) {
					resultatConv = " Convergence faible";
				}
				if ((dif_fprint < -1)) {
					resultatConv = " Divergence forte";
				}
				// System.out.print(" --> fprintNew - fprintOld = "+(float)
				// (fprintNew - fprintOld)+" Donc : "+resultatConv);

				// � Garder Imp�rativement
				float comm = (float) (Math.floor(fprintNew));
				// System.out.println("Le nombre d'actions en commun est floor :
				// "+ (int) comm);
				float dif = (float) (fprintNew - (Math.floor(fprintNew)));
				dif *= disc.discussionProfil.size();
				dif = (float) (Math.rint(dif));
				// System.out.println("Le nombre d'actions diff�rentes : "+
				// (int) dif);
				// System.out.println("Le nombre d'actions ingnor�es :
				// "+(profileSize - dif - comm));
				// if((dif_fprint < 0.0001) && (dif_fprint > 0.00000001))
				// dif_fprint = 0;
				// System.out.println("newFootPrint - oldFootPrint :
				// "+indiceConvergence);
			}

			// System.out.println(" "); System.out.println(" ");

			// System.out.println(" totalNewFprint : "+totalNewFprint);
			// System.out.println(" totalOldFprint : "+totalOldFprint);

			indConv = (float) totalNewFprint - totalOldFprint;

			// System.out.println(" ");System.out.println(" ");
			// System.out.println(" Indice de convergence (indConv) sur tous les
			// agents : "+indConv);

			String resultatConvT = "";

			if (indConv == 0) {
				resultatConvT = " pas de changement  !!!!";
			}
			if (indConv >= 1) {
				resultatConvT = " Convergence Forte";
			}
			if ((indConv > 0) && (indConv < 1)) {
				resultatConvT = " Divergence faible";
			}
			if ((indConv < 0) && (indConv > -1)) {
				resultatConvT = " Convergence faible";
			}
			if ((indConv < -1)) {
				resultatConvT = " Divergence forte";
			}
			// System.out.print(" --> totalNewFprint - totalOldFprint =
			// "+(float) (totalNewFprint - totalOldFprint)+" Donc ->
			// "+resultatConvT);

			float requestFrequency = 0;
			float avgGlobalFrequency = 0;
			float localFrequency = 0;

			float cost = 0;

			Enumeration names;
			String key;
			Enumeration names1;
			String key1;

			names = disc.neededAgentsByAction.keys();
			names1 = disc.agentsProposalsByAction.keys();
			int totalReceived = 0;
			int totalSent = 0;
			int totalMaxPossible = 0;
			int totalCommun = 0;
			float receivedParMaxPossible = 0;
			while (names.hasMoreElements()) {
				key = (String) names.nextElement();

				if (discActionList.contains(key)) { // si l'action en question
				                                    // est dans la liste des
				                                    // actions de la discussion
					// System.out.println("Action
					// -------------------------------------> " +key);
					key1 = (String) names1.nextElement();
					ArrayList sent = (ArrayList) disc.neededAgentsByAction
					        .get(key);
					ArrayList recevied = (ArrayList) disc.agentsProposalsByAction
					        .get(key);
					ArrayList maxPossible = (ArrayList) disc.maxPossibleAgentsByAction
					        .get(key);

					totalSent += sent.size();
					totalReceived += recevied.size();
					totalMaxPossible += maxPossible.size();

					if (maxPossible.size() > 0) {
						// System.out.println("Possibilit� de r�alisation
						// collective ");
						// cost += (plMgt.getActionCost(key) /
						// (maxPossible.size()+1)) / (plMgt.getActionCost(key) /
						// TotalAgentSys) ;
						cost += (float) ((float) plMgt.getActionCost(key)
						        / ((float) maxPossible.size() + 1));
						cost += this.getExtraCostForAgent(localPlan.graphPlan,
						        key);
						receivedParMaxPossible += ((float) ((float) recevied
						        .size() / (float) maxPossible.size()));

					}
					else {
						cost += ActionsCost.getActionCost(key);
						// Ajouter l'extra cost
						cost += this.getExtraCostForAgent(localPlan.graphPlan,
						        key);
					}

					for( Iterator iterator = recevied.iterator(); iterator
					        .hasNext(); ) {
						String ag1 = (String) iterator.next();
						for( Iterator iterator2 = sent.iterator(); iterator2
						        .hasNext(); ) {
							String ag2 = (String) iterator2.next();
							if (ag1.equals(ag2))
								totalCommun++;
						}
					}

					// System.out.println(" --> Sent for Action ("+key+") :
					// "+sent.toString());
					// System.out.println(" --> Received for Action ("+key+") :
					// "+recevied.toString());
					// System.out.println(" --> max Possible for Action
					// ("+key+") : "+maxPossible.toString());
					// System.out.println(" --> totalSent : "+totalSent);
					// System.out.println(" --> totalReceived :
					// "+totalReceived);
					// System.out.println(" --> totalMaxPossible :
					// "+totalMaxPossible);
				}
			}

			// -------------------------
			// les fr�quences par agent
			// -------------------------
			int globalFre = 0; // la somme des fr�quences de toutes les actions
			                   // du plans, par rapport � une alternative.
			int TotalCoalitionAgentNbr = 0;

			for( int y = 0; y < disc.coalitionList.size(); y++ ) {
				Coalition c = (Coalition) disc.coalitionList.get(y);

				TotalCoalitionAgentNbr += c.agentList.size();
				float reqFrequencyCaolition = 0;
				for( int k = 0; k < c.agentList.size(); k++ ) {
					String agent = (String) c.agentList.get(k);

					// le calcul de la fr�quence ne concerne que les agents
					// ayant re�us le plan de l'agent courant
					if (agentsHaveMyPlan.contains(agent)) {
						int indice;
						indice = agentsHaveMyPlan.indexOf(agent);

						ArrayList liste = (ArrayList) actionsFrequencyByAgent
						        .get(indice);

						int localFre = 0; // la fr�quence de l'action en cours
						                  // (une seule action)

						for( int t = 0; t < c.edgeList.size(); t++ ) {
							String action = (String) c.edgeList.get(t);
							// System.out.println(" - Action : "+ action );
							int indi = 0;
							for( Edge e : localPlan.graphPlan.getEachEdge() ) {
								if (e.getId().equals(action)) {
									localFre += (int) liste.get(indi);
									globalFre += (int) liste.get(indi);
									// System.out.println(" Demand�e par l'agent
									// "+ agent + " nbr =
									// "+(int)liste.get(indi));
								}
								indi++;
							}
						}

						// System.out.println(" Il faut revoir le calcul de la
						// fr�quence !!!!! ");

						// System.out.println("--> Agent "+agent);
						// System.out.println("--> Coalition "+c.coalitionId);
						// System.out.println("--> Actions
						// "+c.edgeList.toString());
						// System.out.println("--> Agents List
						// "+c.agentList.toString());
						// System.out.println("--> localFre "+localFre);
						// System.out.println("--> globalFre "+globalFre);
						// System.out.println("--> Old requestFrequency
						// (localFre / globalFre) = "+requestFrequency);
						// System.out.println("--> localFre / globalFre = "+
						// (float) localFre / globalFre );
						// System.out.println("--> requestFrequency (localFre /
						// globalFre) = "+requestFrequency);
						// System.out.println(" ");

						// System.out.println("--> La somme des fr�quence pour
						// "+c.coalitionId+" pour l'agent "+agent+" est
						// "+localFre);
						float agWeight = (float) agentsHaveMyPlanWeight
						        .get(indice);
						agWeight = 1;
						// System.out.println(agent+"'s old frequency is -->
						// "+localFre);
						// System.out.println(agent+"'s New frequency is -->
						// "+((float) localFre * agWeight));
						reqFrequencyCaolition += (float) localFre * agWeight;

						// System.out.println(" ");
					}
				}

				// System.out.println(" ");
				// System.out.println("--> reqFrequencyCaolition pour tous les
				// agents = "+reqFrequencyCaolition);
				// System.out.println("--> globalFre = "+globalFre);

				reqFrequencyCaolition = (float) reqFrequencyCaolition
				        / (float) c.agentList.size();
				// System.out.println("--> La moyenne de requestFrequency de la
				// coalition ((localFre / c.agentList.size()) ) =
				// "+reqFrequencyCaolition);
				// System.out.println(" ");
				requestFrequency += reqFrequencyCaolition;
			}

			// System.out.println(" ");
			// System.out.println("--> La requestFrequency de disc
			// "+disc.discussionId+" est : "+(requestFrequency));
			// System.out.println("--> globalFre = "+globalFre);

			// System.out.println("--> The real All is +requestFrequency (some
			// de localFre) "+globalFrequ );
			// requestFrequency = (float) requestFrequency / globalFre; //
			// false, to delete

			requestFrequency = (float) requestFrequency / (float) globalFrequ;
			// System.out.println("--> requestFrequency = (float)
			// requestFrequency / globalFrequ = "+requestFrequency );

			// System.out.println(" ");

			/*
			 * //---------------------------------------------------------------
			 * ----------------- // Ici Ajouter le poids des agents
			 * ...............................................
			 * System.out.println("  ");System.out.println("  ");
			 * System.out.println(" ---------------------------- ");
			 * System.out.println("  ");
			 * System.out.println("Calcul des poids des agents : ");
			 * 
			 * System.out.println(" Discussion Id : "+disc.discussionId);
			 * System.out.println(" Discussion Size: "+disc.discussionPath.
			 * getEdgeSet().size());
			 * System.out.println(" Discussion Path: "+disc.discussionPath.
			 * getEdgeSet().toString());
			 * 
			 * double discWeight = 0; int discSize =
			 * disc.discussionPath.getEdgeSet().size(); for(int k=0;
			 * k<agentsPlansList.size(); k++){ AgentPlan agPlan = (AgentPlan)
			 * agentsPlansList.get(k);
			 * 
			 * System.out.println(" Agent  "+agPlan.agentOwner);
			 * 
			 * double discWeightLocalAg = 0; for(int u=0;
			 * u<agPlan.pathList.size(); u++){ Graph path = (Graph)
			 * agPlan.pathList.get(u);
			 * 
			 * System.out.println(" Agent Path "+path.getEdgeSet().toString());
			 * System.out.println(" Agent Path Size: "+path.getEdgeSet().size())
			 * ;
			 * 
			 * int intersection = 0; for(Edge
			 * e1:disc.discussionPath.getEachEdge()){
			 * System.out.print(" Path -> "); for(Edge e2:path.getEachEdge()){
			 * System.out.print(" -> "+e2.getId());
			 * if(e1.getId().equals(e2.getId())){ intersection++; } }
			 * 
			 * System.out.println(" -> intersection -> "+intersection); }
			 * System.out.println(" exponentiel de 5  = "+Math.exp(5.0) );
			 * System.out.println(" logarithme de 5  = "+Math.log10(5.0));
			 * System.out.println(" logarithme de 0.8  = "+Math.log10(0.8));
			 * System.out.println(" exponentiel de -0.09691001  = "+Math.exp(-0.
			 * 09691001) );
			 * 
			 * if(discSize > intersection){ float log = ( (float) (discSize -
			 * intersection) / discSize ); float logValue = (float)
			 * Math.log(log); float expValue = (float) Math.exp(logValue);
			 * 
			 * System.out.println(" Log = "+log);
			 * System.out.println(" LogValue = "+logValue);
			 * System.out.println(" expValue = "+expValue);
			 * 
			 * System.out.println(
			 * " logarithme de ( (discussionSize - intersection) / discussionSize )  = "
			 * +Math.log(( (float)(discSize - intersection) / discSize )) );
			 * 
			 * System.out.println(
			 * " exponentiel de Math.log( ( (discussionSize - intersection) / discussionSize )  )  = "
			 * +(Math.exp(Math.log(( (float) (discSize - intersection) /
			 * discSize ))) ) );
			 * 
			 * System.out.println("  : valeur interm�diaire : -> "+ (float) (1-
			 * Math.exp(Math.log( (float) ( (float) (discSize - intersection) /
			 * discSize ) ))));
			 * 
			 * 
			 * 
			 * discWeightLocalAg += (float) (1- Math.exp( Math.log( (float) (
			 * (float) (discSize - intersection) / discSize ) ))); }else {
			 * System.out.println(
			 * " Chemins identiques, -> incr�mentation de discWeightLocalAg de 1"
			 * ); System.out.println("  : valeur interm�diaire : -> "+ 1);
			 * discWeightLocalAg++; }
			 * 
			 * 
			 * } System.out.println("-----> le poid de la discussion "+disc.
			 * discussionId+" par rapport � l'agent "+agPlan.agentOwner+" Est :"
			 * +discWeightLocalAg); discWeight += discWeightLocalAg; }
			 * System.out.println("-----> le poid de la discussion "+disc.
			 * discussionId+" par rapport � tous les agents Est :"+discWeight);
			 * 
			 * //---------------------------------------------------------------
			 * ------------------
			 * //---------------------------------------------------------------
			 * ------------------
			 */

			// -----------------------------------------------------

			// System.out.println(" --> Total Sent : "+totalSent);
			// System.out.println(" --> Total Received : "+totalReceived);
			// System.out.println(" --> Total Max Possible :
			// "+totalMaxPossible);
			// System.out.println(" --> Total Common : "+totalCommun);
			// System.out.println(" --> Total Involved Agents :
			// "+disc.agentsList.size());
			// System.out.println(" --> Total Involved Tasks :
			// "+disc.totalInvolvedTasks);
			// System.out.println(" --> Total Tasks : "+disc.totalTasks);
			// System.out.println(" --> Received / MaxPossible :
			// "+receivedParMaxPossible);
			// System.out.println(" --> Total Bifurcation :
			// "+disc.totalBifurcationNodes);
			// System.out.println(" --> Cost (Relative Global) : "+cost);
			// System.out.println(" --> requestFrequency (somme de (localFre /
			// globalFre) / Nbr Agents) = "+((float) requestFrequency /
			// agentsHaveMyPlan.size()));

			// System.out.println(" ");

			float proba = 0;

			// FAIRE ATTENTION
			// Nombre d'envoie de la discussion
			// --------------------
			System.out.println("");

			int instanceNbr = 0;
			for( int y = 0; y < sentDiscussionList.size(); y++ ) {
				int diId = (int) sentDiscussionList.get(y);
				if (diId == disc.discussionId) {
					instanceNbr++;
				}
			}

			// System.out.println(" -> La discussion "+disc.discussionId+" est
			// envoy�e comme proposition "+instanceNbr+ " fois");

			float sentFrequency = 0;
			if (instanceNbr == 0) {
				sentFrequency = 0;
			}
			else {
				sentFrequency = (float) instanceNbr
				        / (float) sentDiscussionList.size();
			}

			// System.out.println("");
			// System.out.println(" Le Poids (Weight) ");
			float weight = new Float(0);

			if (totalMaxPossible == 0) {
				weight = 0;
			}
			else {
				weight = (float) totalReceived / totalMaxPossible;
				// System.out.println(" totalReceived /totalMaxPossible = "+
				// (float) totalReceived /totalMaxPossible);
			}
			// System.out.println(" Weight ( totalReceived /totalMaxPossible) =
			// "+weight+" totalReceived = "+totalReceived+" totalMaxPossible =
			// "+totalMaxPossible);

			// --------------------------------------------------------------------------------------------------------------------------------

			// System.out.println("");

			// System.out.println("Calcul Estimated Cost ");

			float estCost = new Float(
			        1 - (float) ((float) disc.discussionEstimatedCost
			                / (float) disc.discussionIndividualCost));

			
			float refUtility = 0;
			float estUtility = 0;
			float costAllowed = 0;
			
			
			// System.out.println(" Estimated Cost = "+estCost);

			// float ConvInd = (float) ((float) indConv / (float)
			// disc.discussionProfil.size()) ;

			// System.out.println("");
			// System.out.println(" Indice de convergence ");
			// System.out.println(" indConv / disc.discussionProfil.size() = :
			// "+ConvInd);
			float ConvInd = 0;
			if ((indConv > 0) && (indConv < 1)) { // Divergence faible
				ConvInd = (float) (((float) (disc.discussionPath.getEdgeCount())
				        * (float) indConv * (-1))
				        / (float) Math.sqrt(disc.discussionProfil.size()));
			}
			else if ((indConv < 0) && (indConv > -1)) { // Convergence Faible
				ConvInd = (float) (((float) (disc.discussionPath.getEdgeCount())
				        * (float) indConv * (-1))
				        / (float) Math.sqrt(disc.discussionProfil.size()));
			}
			else {
				ConvInd = (float) (((float) (disc.discussionPath.getEdgeCount())
				        * (float) indConv)
				        / (float) Math.sqrt(disc.discussionProfil.size()));
			}

			// System.out.println("(((float)disc.discussionProfil.size() /
			// disc.discussionPath.getEdgeCount()) * ConvInd ) = : "+ConvInd);

			// R�initialisation des param�tres aux valeurs initiales :

			// {w, eCost, rf, sf, conv}
			valorisationWeight = val[0];
			valorisationCost = val[1];
			valorisationRequestFrequency = val[2];
			valorisationSendFrequency = val[3];
			valorisationConvergence = val[4];

			/*
			 * if(indConv >= 1){ // Convergence Forte valorisationConvergence =
			 * (int) Math.pow(valorisationConvergence, ConvInd);
			 * valorisationConvergence+= Math.pow(valorisationWeight, weight);
			 * valorisationConvergence+= Math.pow(valorisationCost, estCost);
			 * valorisationConvergence+= Math.pow(valorisationRequestFrequency,
			 * requestFrequency); // Prendre consid�ration de la valeur de la
			 * convergence ..... 2 plus important que 1. }else if((indConv >0)
			 * && (indConv <1 ) ){ // Divergence faible valorisationConvergence
			 * = (int) Math.pow(valorisationConvergence, (-ConvInd));
			 * valorisationRequestFrequency+=
			 * Math.pow(valorisationRequestFrequency, ConvInd); } else
			 * if(indConv == 0){ // Pas d'evolution valorisationSendFrequency+=
			 * Math.pow(valorisationWeight, weight); valorisationSendFrequency+=
			 * Math.pow(valorisationCost, estCost); }else if((indConv <0) &&
			 * (indConv >-1 ) ){ // Convergence Faible valorisationConvergence+=
			 * Math.pow(valorisationWeight, weight); valorisationConvergence+=
			 * Math.pow(valorisationCost, estCost); valorisationConvergence+=
			 * Math.pow(valorisationRequestFrequency, requestFrequency); }else
			 * if((indConv < -1) ){ // Divergence forte
			 * valorisationRequestFrequency+= Math.pow(valorisationWeight,
			 * weight); valorisationRequestFrequency+=
			 * Math.pow(valorisationCost, estCost);
			 * valorisationRequestFrequency+= Math.pow(valorisationConvergence,
			 * ConvInd);
			 * 
			 * valorisationSendFrequency+= Math.pow(valorisationWeight, weight);
			 * valorisationSendFrequency+= Math.pow(valorisationCost, estCost);
			 * valorisationSendFrequency+= Math.pow(valorisationConvergence,
			 * ConvInd); valorisationConvergence = 1; }
			 */

			// Compute increase decrease factore

			float increaseFactore = ((float) (Math.exp(
			        indConv * (float) (globalNegotiationRound / maxRound)) - 1)
			        / ((float) (Math.exp(indConv) - 1)));

			float decreaseFactore = 1 - ((float) (Math.exp(
			        indConv * (float) (globalNegotiationRound / maxRound)) - 1)
			        / ((float) (Math.exp(indConv) - 1)));

			/*
			 * // Nouvelle Version : //------------------ if(indConv >= 1){ //
			 * Convergence Forte //valorisationConvergence +=
			 * valorisationConvergence * indConv; //valorisationSendFrequency*=
			 * Math.pow(valorisationConvergence, ConvInd);
			 * valorisationConvergence *= increaseFactore;
			 * valorisationSendFrequency *= increaseFactore; }else if((indConv
			 * >0) && (indConv <1 ) ){ // Divergence faible
			 * valorisationRequestFrequency*= increaseFactore;
			 * valorisationConvergence *= decreaseFactore;
			 * valorisationSendFrequency *= decreaseFactore;
			 * //valorisationRequestFrequency+=
			 * Math.pow(valorisationConvergence, ConvInd);
			 * //valorisationConvergence += valorisationConvergence *
			 * (-indConv); } else if(indConv == 0){ // Pas d'evolution
			 * //if(valorisationConvergence>1) valorisationConvergence = 1;
			 * //valorisationSendFrequency+= valorisationSendFrequency *
			 * (Math.log10(globalNegotiationRound)); valorisationSendFrequency+=
			 * valorisationConvergence; //valorisationRequestFrequency*=
			 * increaseFactore; }else if((indConv <0) && (indConv >-1 ) ){ //
			 * Convergence Faible //valorisationConvergence +=
			 * valorisationConvergence * indConv *(-1);
			 * //valorisationConvergence *= decreaseFactore;
			 * //valorisationSendFrequency*= increaseFactore;
			 * valorisationRequestFrequency*= increaseFactore; }else if((indConv
			 * < -1) ){ // Divergence forte //valorisationRequestFrequency+=
			 * Math.pow(valorisationConvergence, ConvInd);
			 * //valorisationSendFrequency+= Math.pow(valorisationConvergence,
			 * ConvInd); //valorisationConvergence = 1;
			 * 
			 * valorisationRequestFrequency*= decreaseFactore;
			 * valorisationSendFrequency*= increaseFactore; }
			 */

			// Nouvelle Version :
			// ------------------
			if (indConv >= 1) { // Convergence Forte
				// valorisationRequestFrequency+=
				// Math.pow(valorisationConvergence, ConvInd);

				valorisationConvergence += valorisationConvergence * indConv;

				// valorisationConvergence *= increaseFactore;
				// valorisationSendFrequency*= Math.pow(valorisationConvergence,
				// ConvInd);

				// valorisationSendFrequency+= valorisationSendFrequency *
				// (Math.log10(roundWhatToDoNext));
				// valorisationSendFrequency = 1000+indConv;
				// valorisationRequestFrequency = 1000;

			}
			else if ((indConv > 0) && (indConv < 1)) { // Divergence faible

				// valorisationRequestFrequency*= increaseFactore;
				valorisationRequestFrequency += Math
				        .pow(valorisationConvergence, ConvInd);
				// valorisationConvergence += valorisationConvergence *
				// (-indConv);
				// valorisationConvergence-= (5 *
				// (Math.log10(globalNegotiationRound)));
				// valorisationConvergence-= (float) ( (float)(100 *
				// globalNegotiationRound) / (float) maxRound);

				// valorisationSendFrequency = 2000+indConv;
				// valorisationRequestFrequency = 2000;

			}
			else if (indConv == 0) { // Pas d'evolution
				if (valorisationConvergence > 1)
					valorisationConvergence = 1;
				// valorisationConvergence-= (5 *
				// (Math.log10(globalNegotiationRound)));
				// valorisationConvergence-= (float) (
				// (float)(valorisationConvergence * globalNegotiationRound) /
				// (float) maxRound);

				// valorisationSendFrequency*= increaseFactore;
				valorisationSendFrequency += valorisationSendFrequency
				        * (Math.log10(globalNegotiationRound));

				// valorisationSendFrequency = 3000+indConv;
				// valorisationRequestFrequency = 3000;
			}
			else if ((indConv < 0) && (indConv > -1)) { // Convergence Faible
				valorisationConvergence += valorisationConvergence * indConv
				        * (-1);
				// valorisationConvergence *= decreaseFactore;;

				// valorisationSendFrequency = 4000+(-1*indConv);
				// valorisationRequestFrequency = 4000;
			}
			else if ((indConv < -1)) { // Divergence forte
				valorisationRequestFrequency += Math
				        .pow(valorisationConvergence, ConvInd);
				valorisationSendFrequency += Math.pow(valorisationConvergence,
				        ConvInd);

				// valorisationRequestFrequency*= decreaseFactore;
				// valorisationSendFrequency*= decreaseFactore;

				valorisationConvergence = 1;

				// valorisationConvergence-= (5 *
				// (Math.log10(globalNegotiationRound)));
				// valorisationConvergence-= (float) (
				// (float)(valorisationConvergence * globalNegotiationRound) /
				// (float) maxRound);

				// valorisationSendFrequency+= valorisationSendFrequency *
				// (Math.log10(roundWhatToDoNext));
				// valorisationConvergence = 1;
				// valorisationSendFrequency = 5000+(-1*indConv);
				// valorisationRequestFrequency = 5000;

			}

			/*
			 * 
			 * // Nouvelle Version : //------------------ if(indConv >= 1){ //
			 * Convergence Forte //valorisationRequestFrequency+=
			 * Math.pow(valorisationConvergence, ConvInd);
			 * valorisationSendFrequency*= (float) (valorisationConvergence *
			 * indConv); valorisationConvergence *= (float) (Math.exp(indConv));
			 * //valorisationSendFrequency+= valorisationSendFrequency *
			 * (Math.log10(roundWhatToDoNext)); //valorisationSendFrequency =
			 * 1000+indConv; // valorisationRequestFrequency = 1000;
			 * 
			 * }else if((indConv >0) && (indConv <1 ) ){ // Divergence faible
			 * valorisationRequestFrequency+= Math.pow(valorisationConvergence,
			 * ConvInd); valorisationConvergence += valorisationConvergence *
			 * (-indConv); //valorisationSendFrequency = 2000+indConv;
			 * //valorisationRequestFrequency = 2000;
			 * 
			 * } else if(indConv == 0){ // Pas d'evolution
			 * if(valorisationConvergence>1) valorisationConvergence = 1;
			 * valorisationSendFrequency+= valorisationSendFrequency *
			 * (Math.log10(roundWhatToDoNext)); //valorisationSendFrequency =
			 * 3000+indConv; //valorisationRequestFrequency = 3000; }else
			 * if((indConv <0) && (indConv >-1 ) ){ // Convergence Faible
			 * valorisationConvergence += valorisationConvergence * indConv
			 * *(-1); //valorisationSendFrequency = 4000+(-1*indConv);
			 * //valorisationRequestFrequency = 4000; }else if((indConv < -1) ){
			 * // Divergence forte valorisationRequestFrequency*= (float)
			 * (Math.exp(indConv)); valorisationSendFrequency*= (float)
			 * (Math.exp(indConv)); //valorisationSendFrequency+=
			 * valorisationSendFrequency * (Math.log10(roundWhatToDoNext));
			 * valorisationConvergence = 1; //valorisationSendFrequency =
			 * 5000+(-1*indConv); //valorisationRequestFrequency = 5000;
			 * 
			 * }
			 * 
			 * 
			 */

			// ----------------------------------------------------------------------------------------------------------------------------------

			// System.out.println(" ");
			// System.out.println(" ");
			// System.out.println(" Eolution du calcul de la probabilit� ");
			// System.out.println(" ");
			// System.out.println(" Le weight : ");

			// System.out.println(" weight = totalReceived /totalMaxPossible :
			// "+ weight);
			// System.out.println(" valorisationWeight : "+ valorisationWeight);
			// System.out.println(" (valorisationWeight, weight) : "+(float)
			// ((Math.pow(valorisationWeight, weight)) ));

			proba = (float) ((Math.pow(valorisationWeight, weight)));

			// System.out.println(" proba : "+ proba);

			// System.out.println(" ");
			// System.out.println(" Le Cout : ");

			// System.out.println(" estCost = (1 - (float) (
			// disc.discussionEstimatedCost / disc.discussionIndividualCost) ):
			// "+estCost);
			// System.out.println(" valorisationCost : "+ valorisationCost);
			// System.out.println(" ( Math.pow(valorisationCost, estCost) :
			// "+(float) ( Math.pow(valorisationCost, estCost) ));

			proba += (float) (Math.pow(valorisationCost, estCost));

			// System.out.println(" proba : "+ proba);

			// System.out.println(" ");
			// System.out.println(" Fr�quence des demandes sur les actions : ");

			// System.out.println(" requestFrequency: "+ requestFrequency);
			// System.out.println(" valorisationRequestFrequency : "+
			// valorisationRequestFrequency);
			// System.out.println(" (valorisationRequestFrequency,
			// requestFrequency) : "+(float)
			// ((Math.pow(valorisationRequestFrequency, requestFrequency))));

			proba += (float) ((Math.pow(valorisationRequestFrequency,
			        requestFrequency)));

			// System.out.println(" proba : "+ proba);

			// System.out.println(" ");
			// System.out.println(" La fr�quence des envoies des alternatives :
			// ");

			// System.out.println("-- sentFrequency : "+ sentFrequency);
			// System.out.println(" valorisationSendFrequency : "+
			// valorisationSendFrequency);
			// System.out.println(" valorisationSendFrequency, sentFrequency) :
			// "+(float) ((Math.pow(valorisationSendFrequency,
			// sentFrequency))));

			proba -= (float) ((Math.pow(valorisationSendFrequency,
			        sentFrequency)));

			// System.out.println(" proba : "+ proba);

			// System.out.println(" ");
			// System.out.println(" La Convergence des propositions : ");

			// System.out.println("valorisationConvergence : "+
			// valorisationConvergence);
			// System.out.println(" (Agent1.valorisationConvergence,
			// indConvTotale) : "+(float) ((Math.pow(valorisationConvergence,
			// ConvInd))));

			proba += (float) ((Math.pow(valorisationConvergence, ConvInd)));

			// System.out.println(" proba : "+ proba );

			// System.out.println(" " );
			// System.out.println(" -------------- > Tous les param�tres : " );
			// System.out.println(" " );
			// System.out.println(" weight : " + weight );
			// System.out.println(" estCost : " + estCost );
			// System.out.println(" requestFrequency : " + requestFrequency );
			// System.out.println(" sentFrequency : " +sentFrequency );
			// System.out.println(" ConvInd : "+ConvInd );

			disc.w = weight; // checked
			disc.wH.add(weight);
			disc.wV.add(valorisationWeight);
			disc.wValue.add((float) ((Math.pow(valorisationWeight, weight))));

			disc.eCost = estCost; // Checked
			disc.eCostH.add(estCost);
			disc.eCostV.add(valorisationCost);
			disc.eCostValue.add((float) (Math.pow(valorisationCost, estCost)));

			disc.rF = requestFrequency; // Checked.
			disc.rFH.add(requestFrequency);
			disc.rFV.add(valorisationRequestFrequency);
			disc.rFValue.add((float) ((Math.pow(valorisationRequestFrequency,
			        requestFrequency))));

			disc.sF = sentFrequency; // checked
			disc.sFH.add(sentFrequency);
			disc.sFV.add(valorisationSendFrequency);
			disc.sFValue.add((float) ((Math.pow(valorisationSendFrequency,
			        sentFrequency))));

			disc.ConvInd = ConvInd;
			disc.ConvIndH.add(ConvInd);
			disc.ConvIndV.add(valorisationConvergence);
			disc.ConvIndValue.add(
			        (float) ((Math.pow(valorisationConvergence, ConvInd))));

			disc.ConvDegree = indConv;
			disc.ConvDegreeH.add(indConv);

			/*
			 * if(requestFrequency!=0) proba*= requestFrequency;
			 */

			discussionsProbability.put("" + disc.discussionId, "" + proba);
			disc.acceptaceIndicator = proba;
			disc.acceptaceIndicatorHistory.add(proba);

			// System.out.println("Probability V3 disc.discussionId
			// ("+disc.discussionId+") ---------> "+proba);

		}

		// Fin du calcul des parametres charact�ristiques des alternatives
		// ---------------------------------------------------------------------------------------------------

		return discussionsProbability;
	}



	/**
	 * Retourner la liste des agents max possibles pour une action donn�e
	 * 
	 * @return Liste d'agents.
	 */
	public ArrayList getMaxPossibleAgentListForActionInAllDiscussions(
	        ArrayList discussionList, String action) {

		ArrayList agentList = new ArrayList();

		for( int i = 0; i < discussionList.size(); i++ ) {
			Discussion disc = (Discussion) discussionList.get(i);

			for( Iterator iterator = disc.coalitionList.iterator(); iterator
			        .hasNext(); ) {
				Coalition c = (Coalition) iterator.next();

				if (c.edgeList.contains(action))
					for( Iterator iterator2 = c.agentList.iterator(); iterator2
					        .hasNext(); ) {
						String agent = (String) iterator2.next();
						if ((!agentList.contains(agent))
						        && (!agent.equals(c.agentOwner))) {
							agentList.add(agent);
						}
					}
			}
		}

		return agentList;
	}



	/**
	 * Calculer l'enpreinte de la proposition par rapport au profil de la
	 * discussion
	 * 
	 * @param discussionProfile
	 *            Le profil de la discussion
	 * @param agentPropProfile
	 *            Le profil de la proposition de l'agent courant.
	 * @return L'empreinte calcul�e
	 */
	public float computeAgentFootPrint(ArrayList discussionProfile,
	        ArrayList agentPropProfile) {
		float footPrint = 0;

		// System.out.println(" Taille du Profil Agent : agentPropProfile " +
		// agentPropProfile.size());
		// System.out.println(" Taille du Profil Discu : discussionProfile " +
		// discussionProfile.size());

		for( int i = 0; i < discussionProfile.size(); i++ ) {
			int val1 = (int) discussionProfile.get(i);
			int val2 = (int) agentPropProfile.get(i);

			if ((val1 == 1) && (val2 == 1)) {
				footPrint += 1;
			}
			else

			if ((val1 == 1) && (val2 == 0)) {
				footPrint += (float) 1 / discussionProfile.size();
			}
			else

			if ((val1 == 0) && (val2 == 1)) {
				footPrint += (float) 1 / discussionProfile.size();
			}
			else

			if ((val1 == 0) && (val2 == 0)) {
				footPrint += 0;
			}
		}

		return footPrint;
	}



	/**
	 * Afficher les param�tres de toutes les discussions, par ordre
	 * 
	 * @param discussionList
	 *            Liste de toutes les discussions de l'agent en cours
	 */
	public void displayAllDiscussionsParameters(ArrayList receivedProposals,
	        ArrayList discussionList) {

		for( int j = 0; j < discussionList.size(); j++ ) {
			Discussion disc = (Discussion) discussionList.get(j);
			System.out.println(" ");
			System.out.println(" ");
			System.out.println(" ");
			System.out.println(
			        " -------------------------------------------------------------------------------------");
			System.out.println(
			        " -------------------------------------------------------------------------------------");
			System.out.println(" ");
			System.out.println(" ---->    Les param�tres : ");
			disc.displayDiscussionParameters();

			System.out.println(" ");
			System.out.println(" ");

			System.out.println(" ---->    Les Autres param�tres : ");
			System.out.println(" -----------------------------------");

			System.out.println(" ");
			System.out.println(" Methode : getReceivedProposalsFromAgent ");
			System.out.println(" ");
			for( int i = 0; i < disc.agentsList.size(); i++ ) {
				System.out.println(" Les propositions envoy�es par l'agent : "
				        + (String) disc.agentsList.get(i));
				diplayReceivedProposalsList(
				        getReceivedProposalsFromAgent(receivedProposals,
				                (String) disc.agentsList.get(i)),
				        " de L'agent " + (String) disc.agentsList.get(i));
			}

			System.out.println(" ");
			System.out.println(" Methode : getReceivedProposalsWhereAgentIn ");
			System.out.println(" ");
			for( int i = 0; i < disc.agentsList.size(); i++ ) {
				System.out.println(
				        "Les propositions re�ues avec la pr�sence de l'agent : "
				                + disc.agentsList.get(i));
				diplayReceivedProposalsList(
				        getReceivedProposalsWhereAgentIn(receivedProposals,
				                (String) disc.agentsList.get(i)),
				        " avec l'agent pr�sent : "
				                + (String) disc.agentsList.get(i));
			}

			System.out.println(" ");
			System.out.println(
			        " Methode : getReceivedProposalsFromAgentWhereAgentIn ");
			System.out.println(" ");
			for( int i = 0; i < disc.agentsList.size(); i++ ) {
				System.out.println("Les propositions envoy�es par l'agent : "
				        + (String) disc.agentsList.get(i)
				        + " Avec la pr�sence de l'agent Agent2");
				diplayReceivedProposalsList(
				        getReceivedProposalsFromAgentWhereAgentIn(
				                receivedProposals,
				                (String) disc.agentsList.get(i), "Agent2"),
				        " avec l'agent pr�sence: Agent2");
			}

			System.out.println(" ");
			System.out.println(
			        " Methode : getReceivedProposalsConcerningAction ");
			System.out.println(" ");
			for( Edge edge : disc.discussionPath.getEachEdge() ) {
				System.out.println(
				        "Les propositions re�ues concentnant l'action  : "
				                + edge.getId());
				diplayReceivedProposalsList(
				        getReceivedProposalsConcerningAction(receivedProposals,
				                edge.getId()),
				        " Concernant l'action : " + edge.getId());
			}

			System.out.println(" ");
			System.out.println(" Methode : getNeededAcceptToGet ");
			System.out.println(" ");
			System.out.println("The needed Accepts for the discussion : "
			        + disc.discussionPath.getEdgeSet().toString());
			System.out.println(
			        " Is : " + getNeededAcceptToGet(receivedProposals, disc));

			System.out.println(" ");
			System.out.println(" ");
			System.out.println(" ");
		}
	}



	/**
	 * Rendre Tous les arcs du graphe � leur couleur d'origine
	 * 
	 * @param g
	 *            Le graphe � mettre en couleur d'origine
	 */
	public void allGraphEdgeInInitialColor(Graph g) {

		for( Edge e2 : g.getEachEdge() ) {
			e2.setAttribute("ui.label", (String)  e2.getAttribute("oldLabel"));
			e2.setAttribute("ui.class", "black");
			e2.addAttribute("ui.style", "fill-color: black;");
		}
	}



	/**
	 * Retourne le minimum des couts estim�s
	 * 
	 * @param discussionList
	 *            Liste des discussion
	 * @return cout min estim�
	 */
	public static float getMinEstimatedCost(ArrayList discussionList) {

		float minEstimatedCost = 10000000;

		for( int i = 0; i < discussionList.size(); i++ ) {
			Discussion disc = (Discussion) discussionList.get(i);
			// disc.evaluateEstimatedCost();
			if (minEstimatedCost > disc.discussionEstimatedCost) {
				minEstimatedCost = disc.discussionEstimatedCost;
			}
		}
		return minEstimatedCost;
	}



	/**
	 * Retourne le cout estim� d'une discussion donn�e
	 * 
	 * @param discussionList
	 *            Liste des discussion
	 * @return cout estim�
	 */
	public static float getEstimatedCost(ArrayList discussionList, int discID) {

		for( int i = 0; i < discussionList.size(); i++ ) {
			Discussion disc = (Discussion) discussionList.get(i);
			if (disc.discussionId == discID) {
				return disc.discussionEstimatedCost;
			}
		}
		return 0;
	}



	/**
	 * Retourne le maximum des couts estim�s
	 * 
	 * @param discussionList
	 *            Liste des discussion
	 * @return cout max estim�
	 * @throws IOException
	 */
	public static float getMaxEstimatedCost(ArrayList discussionList)
	        throws IOException {

		float maxEstimatedCost = 0;

		for( int i = 0; i < discussionList.size(); i++ ) {
			Discussion disc = (Discussion) discussionList.get(i);
			// disc.evaluateEstimatedCost(agPlan);
			if (maxEstimatedCost < disc.discussionEstimatedCost) {
				maxEstimatedCost = disc.discussionEstimatedCost;
			}
		}
		return maxEstimatedCost;
	}



	/**
	 * Update the discussion list by removing the discussion that are no longer
	 * possible after getting comitment about certain coalitions.
	 * 
	 * @param discussionList
	 *            discussion list
	 * @param slectedDiscussion
	 *            the selected discussion index
	 */

	public void updateRemainingPossibleDiscussion(ArrayList discussionList,
	        ArrayList allCoalitionsList) {
		ArrayList coalitionList = new ArrayList();

		for( int i = 0; i < allCoalitionsList.size(); i++ ) {
			Coalition c = (Coalition) allCoalitionsList.get(i);
			if (c.isFinal) {
				coalitionList.add(c);
			}
		}

		for( int i = 0; i < discussionList.size(); i++ ) {
			Discussion disc = (Discussion) discussionList.get(i);
			boolean exist = true;

			for( int j = 0; j < coalitionList.size(); j++ ) {
				Coalition c = (Coalition) coalitionList.get(j);
				if (!disc.coalitionList.contains(c)) {
					exist = false;
				}
			}

			if (!exist) {
				discussionList.remove(i);
				i--;
			}

		}
	}



	/**
	 * Update the remaining possible discussions in order to do resumption
	 * 
	 * @param remainingDiscussionList
	 *            remaining possible discussion list.
	 * @throws IOException
	 */

	public void updateForResumption(Graph agPlan,
	        ArrayList remainingDiscussionList) throws IOException {

		for( int i = 0; i < remainingDiscussionList.size(); i++ ) {
			Discussion disc = (Discussion) remainingDiscussionList.get(i);
			disc.evaluateIndividualCost(agPlan);
			disc.evaluateEstimatedCostForPropose(agPlan);
		}

	}



	public Discussion getFinalBestDiscussion(Graph agPlan,
	        ArrayList discussionList) throws IOException {

		Discussion disc = null;
		float refCost = 10000000;
		if (discussionList.size() == 1) {
			return (Discussion) discussionList.get(0);
		}
		else {
			for( int i = 0; i < discussionList.size(); i++ ) {
				Discussion disc2 = (Discussion) discussionList.get(i);
				disc2.evaluateFinalCost(agPlan);

				if (refCost > disc2.discussionFinalCost) {
					disc = disc2;
				}

			}
		}

		return disc;
	}



	/**
	 * get the Extra Cost of an action
	 * 
	 * @param agentPlan
	 *            The agent Plan
	 * @param actName
	 *            The task name
	 * @return the extra cost
	 * @throws IOException
	 */
	public static int getExtraCostForAgent(Graph agentPlan, String actName)
	        throws IOException {

		for( Edge e : agentPlan.getEachEdge() ) {
			if (e.getId().equals(actName)) {
				return Integer.parseInt((String) e.getAttribute("extraCost"));
				//return 2;
			}
		}

		return 0;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


	/**
	 * Calculer la probabilit� de r�alisation d'une discussion
	 * 
	 * @param discussionList
	 *            Liste des discussions
	 * @param sentDiscussionList
	 *            Liste des discussions envoy�es.
	 * @param actionsFrequencyByAgent
	 *            Liste des fr�quences des actions envoy�es par les autres
	 *            agents.
	 * @param agentsHaveMyPlan
	 *            Liste des agents ayant le plan de l'agent courant
	 * @param agentsList
	 *            Liste des agents du syst�me.
	 * @param plMgt
	 *            Instance de la classe PlanManagment
	 * @param oldAgentsOrder
	 *            Liste refletant l'ancien ordre des agents
	 * @param newAgentsOrder
	 *            Liste du nouvel ordre des agents
	 * @param val
	 *            tableau des valeurs des indices de valorisation des param�tres
	 * @param newAgentsPropoProfile
	 *            les nouveaux profils des propositions des agents
	 * @param oldAgentsPropoProfile
	 *            anciens profils des propositions des agents
	 * @param lastSendDiscProfile
	 *            Profil de la derni�re proposition envoy�e aux agents.
	 * @return roundWhatToDoNext Le round
	 * @throws IOException
	 * @agentsPlansList Liste des plans des agents.
	 */
	public Hashtable computeDiscussionsProbability(ArrayList discussionList, 
			//Faire attention, cette version n'est que pour les tests
	        ArrayList sentDiscussionList, ArrayList actionsFrequencyByAgent,
	        ArrayList agentsList, ArrayList agentsHaveMyPlan,
	        AgentPlan localPlan, PlanManagment plMgt, ArrayList oldAgentsOrder,
	        ArrayList newAgentsOrder, int val[],
	        ArrayList newAgentsPropoProfile, ArrayList oldAgentsPropoProfile,
	        ArrayList lastSendDiscProfile, int globalNegotiationRound,
	        int maxRound, ArrayList agentsPlansList,
	        float refCost, float allowedCost) throws IOException {

		// {w, eCost, rf, sf, conv}
		float valorisationWeight = val[0]; // valeur de valorisation du nombre
		                                   // des proposition re�ues par rapport
		                                   // aux propositions possibles.
		float valorisationCost = val[1]; // valeur de valorisation du cout
		                                 // estim� des discussion.
		float valorisationRequestFrequency = val[2]; // 1 < frequence < 4 valeur
		                                             // de valorisation des
		                                             // fr�quences de reception
		                                             // des propositions par les
		                                             // agents.
		float valorisationSendFrequency = val[3]; // valeur du nombre d'envoie
		                                          // des propositions.
		float valorisationConvergence = val[4]; // valeur de valorisation de
		                                        // l'indice des convergence des
		                                        // discussions.
		// valorisationWeight(w) ---------------> Alternative weight; // valeur de valorisation du nombre des proposition re�ues par rapport aux propositions possibles.
		// valorisationRequestFrequency (rf) ---> Tasks weight ; // 1 < frequence < 4 valeur de valorisation des fr�quences de reception des propositions par les agents.
		// valorisationConvergence (conv) ------> Distance weight ; // valeur de valorisation de l'indice des convergence des discussions.
		// valorisationCost (eCost) ------------> Estimated utility weight ; // valeur de valorisation du cout estim� des discussion.
		// valorisationSendFrequency (sf) ------> Sent weight; // valeur du nombre d'envoie des propositions.


		int TotalAgentSys = agentsHaveMyPlan.size();
		String agName = localPlan.agentOwner;
		// extraActionCost extraCost = new extraActionCost(agName);

		Hashtable discussionsProbability = new Hashtable();

		System.out.println("");
		System.out.println("");
		System.out.println(
		        "==========================================================================================================================");
		System.out.println("");
		System.out.println("");
		System.out.println(
		        "                -->  computeDiscussionsProbability   (Affichage de Nouvelle Version)<--            ");
		System.out.println("");
		System.out.println("");
		System.out.println(
		        "==========================================================================================================================");

		float globalFrequ = 0;

		// -----------------------------------------------------------------------------------------------------------------------------------------------
		// Calculer la fr�quence globale de toutes les discussions
		// ----------------------------------------------------------------------------------------------------------------------------------------------
		for( int i = 0; i < discussionList.size(); i++ ) {
			Discussion disc = (Discussion) discussionList.get(i);

			for( int y = 0; y < disc.coalitionList.size(); y++ ) {
				Coalition c = (Coalition) disc.coalitionList.get(y);

				float reqFrequencyCaolition = 0;

				for( int k = 0; k < c.agentList.size(); k++ ) {
					String agent = (String) c.agentList.get(k);

					// le calcul de la fr�quence ne concerne que les agents
					// ayant re�us le plan de l'agent courant
					if (agentsHaveMyPlan.contains(agent)) {
						int indice;
						indice = agentsHaveMyPlan.indexOf(agent);

						ArrayList liste = (ArrayList) actionsFrequencyByAgent
						        .get(indice);

						int localFre = 0; // la fr�quence de l'action en cours
						                  // (une seule action)

						for( int t = 0; t < c.edgeList.size(); t++ ) {
							String action = (String) c.edgeList.get(t);
							// System.out.println(" - Action : "+ action );
							int indi = 0;
							for( Edge e : localPlan.graphPlan.getEachEdge() ) {
								if (e.getId().equals(action)) {
									localFre += (int) liste.get(indi);
									// globalFrequ += (int)liste.get(indi);
									// System.out.println(" Demand�e par l'agent
									// "+ agent + " nbr =
									// "+(int)liste.get(indi));
								}
								indi++;
							}
						}

						// System.out.println(" Il faut revoir le calcul de la
						// fr�quence !!!!! ");

						// System.out.println("--> La somme des fr�quence pour
						// "+c.coalitionId+" pour l'agent "+agent+" est
						// "+localFre);
						reqFrequencyCaolition += (float) localFre;

						// System.out.println(" ");
					}
				}

				reqFrequencyCaolition = (float) reqFrequencyCaolition
				        / (float) c.agentList.size();
				globalFrequ += reqFrequencyCaolition;
			}

		}
		// --------------------------------------------------------------------------------------------------------------------------------------------------
		// --------------------------------------------------------------------------------------------------------------------------------------------------

		// Calcul des param�tres caract�ristiques :
		// -----------------------------------------
		// ------------------------------------------------------------------------------------------------------------------------------------------------------------------
		for( int i = 0; i < discussionList.size(); i++ ) {

			// System.out.println("");

			Discussion disc = (Discussion) discussionList.get(i);

			ArrayList discActionList = disc.getActionsInList();

			// Calculer les Poids des agents :
			// -------------------------------
			ArrayList agentsHaveMyPlanWeight = new ArrayList();

			// --------------------------------------------------------------------------------
			// Ici Ajouter le poids des agents
			// ...............................................
			// System.out.println(" ");System.out.println(" ");
			// System.out.println(" ---------------------------- ");
			// System.out.println(" ");
			// System.out.println("Calcul des poids des agents : ");

			// System.out.println(" Discussion Id : "+disc.discussionId);
			// System.out.println(" Discussion Size:
			// "+disc.discussionPath.getEdgeSet().size());
			// System.out.println(" Discussion Path:
			// "+disc.discussionPath.getEdgeSet().toString());

			int discussionSize = disc.discussionPath.getEdgeSet().size();

			for( int t = 0; t < agentsHaveMyPlan.size(); t++ ) {
				String agent = (String) agentsHaveMyPlan.get(t);
				float discWeightLocalAg = 0;
				for( int k = 0; k < agentsPlansList.size(); k++ ) {
					AgentPlan agPlan = (AgentPlan) agentsPlansList.get(k);

					if (agent.equals(agPlan.agentOwner)) {

						// System.out.println(" Agent "+agPlan.agentOwner);

						for( int u = 0; u < agPlan.pathList.size(); u++ ) {
							Graph path = (Graph) agPlan.pathList.get(u);

							// System.out.println(" Agent Path
							// "+path.getEdgeSet().toString());
							// System.out.println(" Agent Path Size:
							// "+path.getEdgeSet().size());

							int intersection = 0;
							for( Edge e1 : disc.discussionPath.getEachEdge() ) {
								// System.out.print(" Path -> ");
								for( Edge e2 : path.getEachEdge() ) {
									// System.out.print(" -> "+e2.getId());
									if (e1.getId().equals(e2.getId())) {
										intersection++;
									}
								}

								// System.out.println(" -> intersection ->
								// "+intersection);
							}

							if (discussionSize > intersection) {
								float log = ((float) (discussionSize
								        - intersection) / discussionSize);
								float logValue = (float) Math.log(log);
								float expValue = (float) Math.exp(logValue);

								// System.out.println(" Log = "+log);
								// System.out.println(" LogValue = "+logValue);
								// System.out.println(" expValue = "+expValue);

								// System.out.println(" logarithme de (
								// (discussionSize - intersection) /
								// discussionSize ) = "+Math.log((
								// (float)(discussionSize - intersection) /
								// discussionSize )) );

								// System.out.println(" exponentiel de Math.log(
								// ( (discussionSize - intersection) /
								// discussionSize ) ) = "+(Math.exp(Math.log((
								// (float) (discussionSize - intersection) /
								// discussionSize ))) ) );

								// System.out.println(" : valeur interm�diaire :
								// -> "+ (float) (1- Math.exp(Math.log( (float)
								// ( (float) (discussionSize - intersection) /
								// discussionSize ) ))));

								discWeightLocalAg += (float) (1 - Math.exp(Math
								        .log((float) ((float) (discussionSize
								                - intersection)
								                / discussionSize))));
							}
							else {
								// System.out.println(" Chemins identiques, ->
								// incr�mentation de discWeightLocalAg de 1");
								// System.out.println(" : valeur interm�diaire :
								// -> "+ 1);
								discWeightLocalAg++;
							}

						}
						// System.out.println("========-----> le poid de la
						// discussion "+disc.discussionId+" par rapport �
						// l'agent "+agPlan.agentOwner+" Est
						// :"+discWeightLocalAg);

						break;
					} // fin de if
				}
				agentsHaveMyPlanWeight.add(discWeightLocalAg);
			}

			// ---------------------------------------------------------------------------------
			// ---------------------------------------------------------------------------------

			// System.out.println("");
			// System.out.println(" --> Traitement du cas de la discussion ID :
			// "+disc.discussionId+"
			// ------------------------------------------------------------------------");
			// System.out.println(" --> Profil de la discussion : ( Taille :
			// "+disc.discussionProfil.size()+" )
			// "+disc.discussionProfil.toString());
			// System.out.println(" --> Les profils des agents (Anciens et
			// nouveaux): ");

			float totalOldFprint = 0;
			float totalNewFprint = 0;
			float indConv = 0;
			int profileSize = disc.getDiscussionProfileSize();
			int actionNbrInDisc = disc.getActionsNumberInProfile();

			boolean existPreCoalition = false; // si l'agent courant est en
			                                   // convergence parfaite avec au
			                                   // moin un des agents
			// � consid�rer apr�s, deux, trois, ... , n agents.

			// Calcul de l'indice de convergence.
			// ---------------------------------------------
			for( int g = 0; g < oldAgentsOrder.size(); g++ ) {
				String agent1 = (String) oldAgentsOrder.get(g);
				ArrayList oldProfile = (ArrayList) oldAgentsPropoProfile.get(g);
				ArrayList newProfile = new ArrayList();

				// R�cup�rer le nouveau profil
				for( int h = 0; h < newAgentsOrder.size(); h++ ) {
					String agent2 = (String) newAgentsOrder.get(h);
					if (agent1.equals(agent2)) {
						newProfile = (ArrayList) newAgentsPropoProfile.get(h);
						// System.out.println("Profil New Collect� ");
						// System.out.println(" Agent probl�me -> "+agent2);
						break;
					}
				}

				// Si l'agent n'a pas propos� a cette it�rative (Round)
				if (newProfile.size() == 0)
					newProfile = disc.getEmptyDiscussionProfile(); //

				float fprintOld = computeAgentFootPrint(disc.discussionProfil,
				        oldProfile);
				float fprintNew = computeAgentFootPrint(disc.discussionProfil,
				        newProfile);
				totalOldFprint += fprintOld;
				totalNewFprint += fprintNew;

				// System.out.println("");
				// System.out.println("------");
				// System.out.println("");
				// System.out.println(" --> Discussion Profile :
				// "+disc.discussionProfil.toString());
				// System.out.println(" --> Agent : "+agent1+" Ancien Profil
				// "+oldProfile.toString()+" fprint "+fprintOld);
				// System.out.println(" --> Agent : "+agent1+" Nouveau Profil
				// "+newProfile.toString()+" fprint "+ fprintNew);

				String resultatConv = "";
				float dif_fprint = (float) (fprintNew - fprintOld);

				/*
				 * // si exactement la meme entre les deux agents :
				 * //---------------------------------------------- // R�cup�rer
				 * le nouveau profil for(int p=0; p<newAgentsOrder.size(); p++){
				 * String agent3 = (String) newAgentsOrder.get(p); ArrayList
				 * newProfile1 = (ArrayList) newAgentsPropoProfile.get(p); float
				 * lastAltSentFprint =
				 * computeAgentFootPrint(lastSendDiscProfile, newProfile1);
				 * if((float) (lastAltSentFprint -
				 * (Math.floor(lastAltSentFprint))) == 0){ existPreCoalition =
				 * true; System.out.println(
				 * "-> -> Existane de convergence parfaite avec agent : "+agent3
				 * ); break; }
				 * 
				 * }
				 */

				// ----------------------------------------------

				if (dif_fprint == 0) {
					resultatConv = " pas de changement  !!!!";
				}
				if (dif_fprint >= 1) {
					resultatConv = " Convergence Forte";
				}
				if ((dif_fprint > 0) && (dif_fprint < 1)) {
					resultatConv = " Divergence faible";
				}
				if ((dif_fprint < 0) && (dif_fprint > -1)) {
					resultatConv = " Convergence faible";
				}
				if ((dif_fprint < -1)) {
					resultatConv = " Divergence forte";
				}
				// System.out.print(" --> fprintNew - fprintOld = "+(float)
				// (fprintNew - fprintOld)+" Donc : "+resultatConv);

				// � Garder Imp�rativement
				float comm = (float) (Math.floor(fprintNew));
				// System.out.println("Le nombre d'actions en commun est floor :
				// "+ (int) comm);
				float dif = (float) (fprintNew - (Math.floor(fprintNew)));
				dif *= disc.discussionProfil.size();
				dif = (float) (Math.rint(dif));
				// System.out.println("Le nombre d'actions diff�rentes : "+
				// (int) dif);
				// System.out.println("Le nombre d'actions ingnor�es :
				// "+(profileSize - dif - comm));
				// if((dif_fprint < 0.0001) && (dif_fprint > 0.00000001))
				// dif_fprint = 0;
				// System.out.println("newFootPrint - oldFootPrint :
				// "+indiceConvergence);
			}

			// System.out.println(" "); System.out.println(" ");

			// System.out.println(" totalNewFprint : "+totalNewFprint);
			// System.out.println(" totalOldFprint : "+totalOldFprint);

			indConv = (float) totalNewFprint - totalOldFprint;

			// System.out.println(" ");System.out.println(" ");
			// System.out.println(" Indice de convergence (indConv) sur tous les
			// agents : "+indConv);

			String resultatConvT = "";

			if (indConv == 0) {
				resultatConvT = " pas de changement  !!!!";
			}
			if (indConv >= 1) {
				resultatConvT = " Convergence Forte";
			}
			if ((indConv > 0) && (indConv < 1)) {
				resultatConvT = " Divergence faible";
			}
			if ((indConv < 0) && (indConv > -1)) {
				resultatConvT = " Convergence faible";
			}
			if ((indConv < -1)) {
				resultatConvT = " Divergence forte";
			}
			// System.out.print(" --> totalNewFprint - totalOldFprint =
			// "+(float) (totalNewFprint - totalOldFprint)+" Donc ->
			// "+resultatConvT);

			float requestFrequency = 0;
			float avgGlobalFrequency = 0;
			float localFrequency = 0;

			float cost = 0;

			Enumeration names;
			String key;
			Enumeration names1;
			String key1;

			names = disc.neededAgentsByAction.keys();
			names1 = disc.agentsProposalsByAction.keys();
			int totalReceived = 0;
			int totalSent = 0;
			int totalMaxPossible = 0;
			int totalCommun = 0;
			float receivedParMaxPossible = 0;
			while (names.hasMoreElements()) {
				key = (String) names.nextElement();

				if (discActionList.contains(key)) { // si l'action en question
				                                    // est dans la liste des
				                                    // actions de la discussion
					// System.out.println("Action
					// -------------------------------------> " +key);
					key1 = (String) names1.nextElement();
					ArrayList sent = (ArrayList) disc.neededAgentsByAction
					        .get(key);
					ArrayList recevied = (ArrayList) disc.agentsProposalsByAction
					        .get(key);
					ArrayList maxPossible = (ArrayList) disc.maxPossibleAgentsByAction
					        .get(key);

					totalSent += sent.size();
					totalReceived += recevied.size();
					totalMaxPossible += maxPossible.size();

					if (maxPossible.size() > 0) {
						// System.out.println("Possibilit� de r�alisation
						// collective ");
						// cost += (plMgt.getActionCost(key) /
						// (maxPossible.size()+1)) / (plMgt.getActionCost(key) /
						// TotalAgentSys) ;
						cost += (float) ((float) plMgt.getActionCost(key)
						        / ((float) maxPossible.size() + 1));
						cost += this.getExtraCostForAgent(localPlan.graphPlan,
						        key);
						receivedParMaxPossible += ((float) ((float) recevied
						        .size() / (float) maxPossible.size()));

					}
					else {
						cost += ActionsCost.getActionCost(key);
						// Ajouter l'extra cost
						cost += this.getExtraCostForAgent(localPlan.graphPlan,
						        key);
					}

					for( Iterator iterator = recevied.iterator(); iterator
					        .hasNext(); ) {
						String ag1 = (String) iterator.next();
						for( Iterator iterator2 = sent.iterator(); iterator2
						        .hasNext(); ) {
							String ag2 = (String) iterator2.next();
							if (ag1.equals(ag2))
								totalCommun++;
						}
					}

					// System.out.println(" --> Sent for Action ("+key+") :
					// "+sent.toString());
					// System.out.println(" --> Received for Action ("+key+") :
					// "+recevied.toString());
					// System.out.println(" --> max Possible for Action
					// ("+key+") : "+maxPossible.toString());
					// System.out.println(" --> totalSent : "+totalSent);
					// System.out.println(" --> totalReceived :
					// "+totalReceived);
					// System.out.println(" --> totalMaxPossible :
					// "+totalMaxPossible);
				}
			}

			// -------------------------
			// les fr�quences par agent
			// -------------------------
			int globalFre = 0; // la somme des fr�quences de toutes les actions
			                   // du plans, par rapport � une alternative.
			int TotalCoalitionAgentNbr = 0;

			for( int y = 0; y < disc.coalitionList.size(); y++ ) {
				Coalition c = (Coalition) disc.coalitionList.get(y);

				TotalCoalitionAgentNbr += c.agentList.size();
				float reqFrequencyCaolition = 0;
				for( int k = 0; k < c.agentList.size(); k++ ) {
					String agent = (String) c.agentList.get(k);

					// le calcul de la fr�quence ne concerne que les agents
					// ayant re�us le plan de l'agent courant
					if (agentsHaveMyPlan.contains(agent)) {
						int indice;
						indice = agentsHaveMyPlan.indexOf(agent);

						ArrayList liste = (ArrayList) actionsFrequencyByAgent
						        .get(indice);

						int localFre = 0; // la fr�quence de l'action en cours
						                  // (une seule action)

						for( int t = 0; t < c.edgeList.size(); t++ ) {
							String action = (String) c.edgeList.get(t);
							// System.out.println(" - Action : "+ action );
							int indi = 0;
							for( Edge e : localPlan.graphPlan.getEachEdge() ) {
								if (e.getId().equals(action)) {
									localFre += (int) liste.get(indi);
									globalFre += (int) liste.get(indi);
									// System.out.println(" Demand�e par l'agent
									// "+ agent + " nbr =
									// "+(int)liste.get(indi));
								}
								indi++;
							}
						}

						// System.out.println(" Il faut revoir le calcul de la
						// fr�quence !!!!! ");

						// System.out.println("--> Agent "+agent);
						// System.out.println("--> Coalition "+c.coalitionId);
						// System.out.println("--> Actions
						// "+c.edgeList.toString());
						// System.out.println("--> Agents List
						// "+c.agentList.toString());
						// System.out.println("--> localFre "+localFre);
						// System.out.println("--> globalFre "+globalFre);
						// System.out.println("--> Old requestFrequency
						// (localFre / globalFre) = "+requestFrequency);
						// System.out.println("--> localFre / globalFre = "+
						// (float) localFre / globalFre );
						// System.out.println("--> requestFrequency (localFre /
						// globalFre) = "+requestFrequency);
						// System.out.println(" ");

						// System.out.println("--> La somme des fr�quence pour
						// "+c.coalitionId+" pour l'agent "+agent+" est
						// "+localFre);
						float agWeight = (float) agentsHaveMyPlanWeight
						        .get(indice);
						agWeight = 1;
						// System.out.println(agent+"'s old frequency is -->
						// "+localFre);
						// System.out.println(agent+"'s New frequency is -->
						// "+((float) localFre * agWeight));
						reqFrequencyCaolition += (float) localFre * agWeight;

						// System.out.println(" ");
					}
				}

				// System.out.println(" ");
				// System.out.println("--> reqFrequencyCaolition pour tous les
				// agents = "+reqFrequencyCaolition);
				// System.out.println("--> globalFre = "+globalFre);

				reqFrequencyCaolition = (float) reqFrequencyCaolition
				        / (float) c.agentList.size();
				// System.out.println("--> La moyenne de requestFrequency de la
				// coalition ((localFre / c.agentList.size()) ) =
				// "+reqFrequencyCaolition);
				// System.out.println(" ");
				requestFrequency += reqFrequencyCaolition;
			}

			// System.out.println(" ");
			// System.out.println("--> La requestFrequency de disc
			// "+disc.discussionId+" est : "+(requestFrequency));
			// System.out.println("--> globalFre = "+globalFre);

			// System.out.println("--> The real All is +requestFrequency (some
			// de localFre) "+globalFrequ );
			// requestFrequency = (float) requestFrequency / globalFre; //
			// false, to delete

			requestFrequency = (float) requestFrequency / (float) globalFrequ;
			// System.out.println("--> requestFrequency = (float)
			// requestFrequency / globalFrequ = "+requestFrequency );

			// System.out.println(" ");

			/*
			 * //---------------------------------------------------------------
			 * ----------------- // Ici Ajouter le poids des agents
			 * ...............................................
			 * System.out.println("  ");System.out.println("  ");
			 * System.out.println(" ---------------------------- ");
			 * System.out.println("  ");
			 * System.out.println("Calcul des poids des agents : ");
			 * 
			 * System.out.println(" Discussion Id : "+disc.discussionId);
			 * System.out.println(" Discussion Size: "+disc.discussionPath.
			 * getEdgeSet().size());
			 * System.out.println(" Discussion Path: "+disc.discussionPath.
			 * getEdgeSet().toString());
			 * 
			 * double discWeight = 0; int discSize =
			 * disc.discussionPath.getEdgeSet().size(); for(int k=0;
			 * k<agentsPlansList.size(); k++){ AgentPlan agPlan = (AgentPlan)
			 * agentsPlansList.get(k);
			 * 
			 * System.out.println(" Agent  "+agPlan.agentOwner);
			 * 
			 * double discWeightLocalAg = 0; for(int u=0;
			 * u<agPlan.pathList.size(); u++){ Graph path = (Graph)
			 * agPlan.pathList.get(u);
			 * 
			 * System.out.println(" Agent Path "+path.getEdgeSet().toString());
			 * System.out.println(" Agent Path Size: "+path.getEdgeSet().size())
			 * ;
			 * 
			 * int intersection = 0; for(Edge
			 * e1:disc.discussionPath.getEachEdge()){
			 * System.out.print(" Path -> "); for(Edge e2:path.getEachEdge()){
			 * System.out.print(" -> "+e2.getId());
			 * if(e1.getId().equals(e2.getId())){ intersection++; } }
			 * 
			 * System.out.println(" -> intersection -> "+intersection); }
			 * System.out.println(" exponentiel de 5  = "+Math.exp(5.0) );
			 * System.out.println(" logarithme de 5  = "+Math.log10(5.0));
			 * System.out.println(" logarithme de 0.8  = "+Math.log10(0.8));
			 * System.out.println(" exponentiel de -0.09691001  = "+Math.exp(-0.
			 * 09691001) );
			 * 
			 * if(discSize > intersection){ float log = ( (float) (discSize -
			 * intersection) / discSize ); float logValue = (float)
			 * Math.log(log); float expValue = (float) Math.exp(logValue);
			 * 
			 * System.out.println(" Log = "+log);
			 * System.out.println(" LogValue = "+logValue);
			 * System.out.println(" expValue = "+expValue);
			 * 
			 * System.out.println(
			 * " logarithme de ( (discussionSize - intersection) / discussionSize )  = "
			 * +Math.log(( (float)(discSize - intersection) / discSize )) );
			 * 
			 * System.out.println(
			 * " exponentiel de Math.log( ( (discussionSize - intersection) / discussionSize )  )  = "
			 * +(Math.exp(Math.log(( (float) (discSize - intersection) /
			 * discSize ))) ) );
			 * 
			 * System.out.println("  : valeur interm�diaire : -> "+ (float) (1-
			 * Math.exp(Math.log( (float) ( (float) (discSize - intersection) /
			 * discSize ) ))));
			 * 
			 * 
			 * 
			 * discWeightLocalAg += (float) (1- Math.exp( Math.log( (float) (
			 * (float) (discSize - intersection) / discSize ) ))); }else {
			 * System.out.println(
			 * " Chemins identiques, -> incr�mentation de discWeightLocalAg de 1"
			 * ); System.out.println("  : valeur interm�diaire : -> "+ 1);
			 * discWeightLocalAg++; }
			 * 
			 * 
			 * } System.out.println("-----> le poid de la discussion "+disc.
			 * discussionId+" par rapport � l'agent "+agPlan.agentOwner+" Est :"
			 * +discWeightLocalAg); discWeight += discWeightLocalAg; }
			 * System.out.println("-----> le poid de la discussion "+disc.
			 * discussionId+" par rapport � tous les agents Est :"+discWeight);
			 * 
			 * //---------------------------------------------------------------
			 * ------------------
			 * //---------------------------------------------------------------
			 * ------------------
			 */

			// -----------------------------------------------------

			// System.out.println(" --> Total Sent : "+totalSent);
			// System.out.println(" --> Total Received : "+totalReceived);
			// System.out.println(" --> Total Max Possible :
			// "+totalMaxPossible);
			// System.out.println(" --> Total Common : "+totalCommun);
			// System.out.println(" --> Total Involved Agents :
			// "+disc.agentsList.size());
			// System.out.println(" --> Total Involved Tasks :
			// "+disc.totalInvolvedTasks);
			// System.out.println(" --> Total Tasks : "+disc.totalTasks);
			// System.out.println(" --> Received / MaxPossible :
			// "+receivedParMaxPossible);
			// System.out.println(" --> Total Bifurcation :
			// "+disc.totalBifurcationNodes);
			// System.out.println(" --> Cost (Relative Global) : "+cost);
			// System.out.println(" --> requestFrequency (somme de (localFre /
			// globalFre) / Nbr Agents) = "+((float) requestFrequency /
			// agentsHaveMyPlan.size()));

			// System.out.println(" ");

			float proba = 0;

			// FAIRE ATTENTION
			// Nombre d'envoie de la discussion
			// --------------------
			System.out.println("");

			int instanceNbr = 0;
			for( int y = 0; y < sentDiscussionList.size(); y++ ) {
				int diId = (int) sentDiscussionList.get(y);
				if (diId == disc.discussionId) {
					instanceNbr++;
				}
			}

			// System.out.println(" -> La discussion "+disc.discussionId+" est
			// envoy�e comme proposition "+instanceNbr+ " fois");

			float sentFrequency = 0;
			if (instanceNbr == 0) {
				sentFrequency = 0;
			}
			else {
				sentFrequency = (float) instanceNbr
				        / (float) sentDiscussionList.size();
			}

			// System.out.println("");
			// System.out.println(" Le Poids (Weight) ");
			float weight = new Float(0);

			if (totalMaxPossible == 0) {
				weight = 0;
			}
			else {
				weight = (float) totalReceived / totalMaxPossible;
				// System.out.println(" totalReceived /totalMaxPossible = "+
				// (float) totalReceived /totalMaxPossible);
			}
			// System.out.println(" Weight ( totalReceived /totalMaxPossible) =
			// "+weight+" totalReceived = "+totalReceived+" totalMaxPossible =
			// "+totalMaxPossible);

			// --------------------------------------------------------------------------------------------------------------------------------

			// System.out.println("");

			// System.out.println("Calcul Estimated Cost ");

			float estCost = new Float(
			        1 - (float) ((float) disc.discussionEstimatedCost
			                / (float) disc.discussionIndividualCost));

			
			float refUtility = 0;
			float estUtility = 0;
			float costAllowed = 0;
			
			refUtility = (float) (refCost - allowedCost);
			estUtility = (float) (refCost - (float) disc.discussionEstimatedCost );
			
			
			float estCost2 = new Float(
			        1 - (float) ( (float) (refCost  -   (float) estUtility )
			                / (float) disc.discussionIndividualCost)  );
			System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
			System.out.println("Nouvelle version de eCost: ");
			System.out.println(" Reference cost is: "+refCost);
			System.out.println(" Allowed cost is: "+allowedCost);
			System.out.println(" ref Utility : "+refUtility);
			System.out.println(" estimated utility is: "+estUtility);
			System.out.println("estCost1: "+estCost);
			System.out.println("estCost2: "+estCost2);
			
			
			
			
			System.out.println("  ");
			System.out.println("  ");
			
			// System.out.println(" Estimated Cost = "+estCost);

			// float ConvInd = (float) ((float) indConv / (float)
			// disc.discussionProfil.size()) ;

			// System.out.println("");
			// System.out.println(" Indice de convergence ");
			// System.out.println(" indConv / disc.discussionProfil.size() = :
			// "+ConvInd);
			float ConvInd = 0;
			if ((indConv > 0) && (indConv < 1)) { // Divergence faible
				ConvInd = (float) (((float) (disc.discussionPath.getEdgeCount())
				        * (float) indConv * (-1))
				        / (float) Math.sqrt(disc.discussionProfil.size()));
			}
			else if ((indConv < 0) && (indConv > -1)) { // Convergence Faible
				ConvInd = (float) (((float) (disc.discussionPath.getEdgeCount())
				        * (float) indConv * (-1))
				        / (float) Math.sqrt(disc.discussionProfil.size()));
			}
			else {
				ConvInd = (float) (((float) (disc.discussionPath.getEdgeCount())
				        * (float) indConv)
				        / (float) Math.sqrt(disc.discussionProfil.size()));
			}

			// System.out.println("(((float)disc.discussionProfil.size() /
			// disc.discussionPath.getEdgeCount()) * ConvInd ) = : "+ConvInd);

			// R�initialisation des param�tres aux valeurs initiales :

			// {w, eCost, rf, sf, conv}
			valorisationWeight = val[0];
			valorisationCost = val[1];
			valorisationRequestFrequency = val[2];
			valorisationSendFrequency = val[3];
			valorisationConvergence = val[4];

			/*
			 * if(indConv >= 1){ // Convergence Forte valorisationConvergence =
			 * (int) Math.pow(valorisationConvergence, ConvInd);
			 * valorisationConvergence+= Math.pow(valorisationWeight, weight);
			 * valorisationConvergence+= Math.pow(valorisationCost, estCost);
			 * valorisationConvergence+= Math.pow(valorisationRequestFrequency,
			 * requestFrequency); // Prendre consid�ration de la valeur de la
			 * convergence ..... 2 plus important que 1. }else if((indConv >0)
			 * && (indConv <1 ) ){ // Divergence faible valorisationConvergence
			 * = (int) Math.pow(valorisationConvergence, (-ConvInd));
			 * valorisationRequestFrequency+=
			 * Math.pow(valorisationRequestFrequency, ConvInd); } else
			 * if(indConv == 0){ // Pas d'evolution valorisationSendFrequency+=
			 * Math.pow(valorisationWeight, weight); valorisationSendFrequency+=
			 * Math.pow(valorisationCost, estCost); }else if((indConv <0) &&
			 * (indConv >-1 ) ){ // Convergence Faible valorisationConvergence+=
			 * Math.pow(valorisationWeight, weight); valorisationConvergence+=
			 * Math.pow(valorisationCost, estCost); valorisationConvergence+=
			 * Math.pow(valorisationRequestFrequency, requestFrequency); }else
			 * if((indConv < -1) ){ // Divergence forte
			 * valorisationRequestFrequency+= Math.pow(valorisationWeight,
			 * weight); valorisationRequestFrequency+=
			 * Math.pow(valorisationCost, estCost);
			 * valorisationRequestFrequency+= Math.pow(valorisationConvergence,
			 * ConvInd);
			 * 
			 * valorisationSendFrequency+= Math.pow(valorisationWeight, weight);
			 * valorisationSendFrequency+= Math.pow(valorisationCost, estCost);
			 * valorisationSendFrequency+= Math.pow(valorisationConvergence,
			 * ConvInd); valorisationConvergence = 1; }
			 */

			// Compute increase decrease factore

			float increaseFactore = ((float) (Math.exp(
			        indConv * (float) (globalNegotiationRound / maxRound)) - 1)
			        / ((float) (Math.exp(indConv) - 1)));

			float decreaseFactore = 1 - ((float) (Math.exp(
			        indConv * (float) (globalNegotiationRound / maxRound)) - 1)
			        / ((float) (Math.exp(indConv) - 1)));

			/*
			 * // Nouvelle Version : //------------------ if(indConv >= 1){ //
			 * Convergence Forte //valorisationConvergence +=
			 * valorisationConvergence * indConv; //valorisationSendFrequency*=
			 * Math.pow(valorisationConvergence, ConvInd);
			 * valorisationConvergence *= increaseFactore;
			 * valorisationSendFrequency *= increaseFactore; }else if((indConv
			 * >0) && (indConv <1 ) ){ // Divergence faible
			 * valorisationRequestFrequency*= increaseFactore;
			 * valorisationConvergence *= decreaseFactore;
			 * valorisationSendFrequency *= decreaseFactore;
			 * //valorisationRequestFrequency+=
			 * Math.pow(valorisationConvergence, ConvInd);
			 * //valorisationConvergence += valorisationConvergence *
			 * (-indConv); } else if(indConv == 0){ // Pas d'evolution
			 * //if(valorisationConvergence>1) valorisationConvergence = 1;
			 * //valorisationSendFrequency+= valorisationSendFrequency *
			 * (Math.log10(globalNegotiationRound)); valorisationSendFrequency+=
			 * valorisationConvergence; //valorisationRequestFrequency*=
			 * increaseFactore; }else if((indConv <0) && (indConv >-1 ) ){ //
			 * Convergence Faible //valorisationConvergence +=
			 * valorisationConvergence * indConv *(-1);
			 * //valorisationConvergence *= decreaseFactore;
			 * //valorisationSendFrequency*= increaseFactore;
			 * valorisationRequestFrequency*= increaseFactore; }else if((indConv
			 * < -1) ){ // Divergence forte //valorisationRequestFrequency+=
			 * Math.pow(valorisationConvergence, ConvInd);
			 * //valorisationSendFrequency+= Math.pow(valorisationConvergence,
			 * ConvInd); //valorisationConvergence = 1;
			 * 
			 * valorisationRequestFrequency*= decreaseFactore;
			 * valorisationSendFrequency*= increaseFactore; }
			 * 
			 * 	
			 *  
			 *  */
		
			

			// Nouvelle Version :
			// ------------------
			if (indConv >= 1) { // Convergence Forte
				
				valorisationConvergence += valorisationConvergence * indConv;

			}
			else if ((indConv > 0) && (indConv < 1)) { // Divergence faible

				valorisationRequestFrequency += Math.pow(valorisationConvergence, ConvInd);
			}
			else if (indConv == 0) { // Pas d'evolution
				if (valorisationConvergence > 1)
					valorisationConvergence = 1;
				
				valorisationSendFrequency += valorisationSendFrequency * (Math.log10(globalNegotiationRound));

			}
			else if ((indConv < 0) && (indConv > -1)) { // Convergence Faible
				valorisationConvergence += valorisationConvergence * indConv * (-1);
				
			}
			else if ((indConv < -1)) { // Divergence forte
				valorisationRequestFrequency += Math.pow(valorisationConvergence, ConvInd);
				valorisationSendFrequency += Math.pow(valorisationConvergence, ConvInd);
				valorisationConvergence = 1;
			}

			/*
			 * 
			 * // Nouvelle Version : //------------------ if(indConv >= 1){ //
			 * Convergence Forte //valorisationRequestFrequency+=
			 * Math.pow(valorisationConvergence, ConvInd);
			 * valorisationSendFrequency*= (float) (valorisationConvergence *
			 * indConv); valorisationConvergence *= (float) (Math.exp(indConv));
			 * //valorisationSendFrequency+= valorisationSendFrequency *
			 * (Math.log10(roundWhatToDoNext)); //valorisationSendFrequency =
			 * 1000+indConv; // valorisationRequestFrequency = 1000;
			 * 
			 * }else if((indConv >0) && (indConv <1 ) ){ // Divergence faible
			 * valorisationRequestFrequency+= Math.pow(valorisationConvergence,
			 * ConvInd); valorisationConvergence += valorisationConvergence *
			 * (-indConv); //valorisationSendFrequency = 2000+indConv;
			 * //valorisationRequestFrequency = 2000;
			 * 
			 * } else if(indConv == 0){ // Pas d'evolution
			 * if(valorisationConvergence>1) valorisationConvergence = 1;
			 * valorisationSendFrequency+= valorisationSendFrequency *
			 * (Math.log10(roundWhatToDoNext)); //valorisationSendFrequency =
			 * 3000+indConv; //valorisationRequestFrequency = 3000; }else
			 * if((indConv <0) && (indConv >-1 ) ){ // Convergence Faible
			 * valorisationConvergence += valorisationConvergence * indConv
			 * *(-1); //valorisationSendFrequency = 4000+(-1*indConv);
			 * //valorisationRequestFrequency = 4000; }else if((indConv < -1) ){
			 * // Divergence forte valorisationRequestFrequency*= (float)
			 * (Math.exp(indConv)); valorisationSendFrequency*= (float)
			 * (Math.exp(indConv)); //valorisationSendFrequency+=
			 * valorisationSendFrequency * (Math.log10(roundWhatToDoNext));
			 * valorisationConvergence = 1; //valorisationSendFrequency =
			 * 5000+(-1*indConv); //valorisationRequestFrequency = 5000;
			 * 
			 * }
			 * 
			 * 
			 */

			// ----------------------------------------------------------------------------------------------------------------------------------

			// System.out.println(" ");
			// System.out.println(" ");
			// System.out.println(" Eolution du calcul de la probabilit� ");
			// System.out.println(" ");
			// System.out.println(" Le weight : ");

			// System.out.println(" weight = totalReceived /totalMaxPossible :
			// "+ weight);
			// System.out.println(" valorisationWeight : "+ valorisationWeight);
			// System.out.println(" (valorisationWeight, weight) : "+(float)
			// ((Math.pow(valorisationWeight, weight)) ));

			proba = (float) ((Math.pow(valorisationWeight, weight)));

			// System.out.println(" proba : "+ proba);

			// System.out.println(" ");
			// System.out.println(" Le Cout : ");

			// System.out.println(" estCost = (1 - (float) (
			// disc.discussionEstimatedCost / disc.discussionIndividualCost) ):
			// "+estCost);
			// System.out.println(" valorisationCost : "+ valorisationCost);
			// System.out.println(" ( Math.pow(valorisationCost, estCost) :
			// "+(float) ( Math.pow(valorisationCost, estCost) ));

			proba += (float) (Math.pow(valorisationCost, estCost));

			// System.out.println(" proba : "+ proba);

			// System.out.println(" ");
			// System.out.println(" Fr�quence des demandes sur les actions : ");

			// System.out.println(" requestFrequency: "+ requestFrequency);
			// System.out.println(" valorisationRequestFrequency : "+
			// valorisationRequestFrequency);
			// System.out.println(" (valorisationRequestFrequency,
			// requestFrequency) : "+(float)
			// ((Math.pow(valorisationRequestFrequency, requestFrequency))));

			proba += (float) ((Math.pow(valorisationRequestFrequency,
			        requestFrequency)));

			// System.out.println(" proba : "+ proba);

			// System.out.println(" ");
			// System.out.println(" La fr�quence des envoies des alternatives :
			// ");

			// System.out.println("-- sentFrequency : "+ sentFrequency);
			// System.out.println(" valorisationSendFrequency : "+
			// valorisationSendFrequency);
			// System.out.println(" valorisationSendFrequency, sentFrequency) :
			// "+(float) ((Math.pow(valorisationSendFrequency,
			// sentFrequency))));

			proba -= (float) ((Math.pow(valorisationSendFrequency,
			        sentFrequency)));

			// System.out.println(" proba : "+ proba);

			// System.out.println(" ");
			// System.out.println(" La Convergence des propositions : ");

			// System.out.println("valorisationConvergence : "+
			// valorisationConvergence);
			// System.out.println(" (Agent1.valorisationConvergence,
			// indConvTotale) : "+(float) ((Math.pow(valorisationConvergence,
			// ConvInd))));

			proba += (float) ((Math.pow(valorisationConvergence, ConvInd)));

			// System.out.println(" proba : "+ proba );

			// System.out.println(" " );
			// System.out.println(" -------------- > Tous les param�tres : " );
			// System.out.println(" " );
			// System.out.println(" weight : " + weight );
			// System.out.println(" estCost : " + estCost );
			// System.out.println(" requestFrequency : " + requestFrequency );
			// System.out.println(" sentFrequency : " +sentFrequency );
			// System.out.println(" ConvInd : "+ConvInd );

			disc.w = weight; // checked
			disc.wH.add(weight);
			disc.wV.add(valorisationWeight);
			disc.wValue.add((float) ((Math.pow(valorisationWeight, weight))));

			disc.eCost = estCost; // Checked
			disc.eCostH.add(estCost);
			disc.eCostV.add(valorisationCost);
			disc.eCostValue.add((float) (Math.pow(valorisationCost, estCost)));

			disc.rF = requestFrequency; // Checked.
			disc.rFH.add(requestFrequency);
			disc.rFV.add(valorisationRequestFrequency);
			disc.rFValue.add((float) ((Math.pow(valorisationRequestFrequency,
			        requestFrequency))));

			disc.sF = sentFrequency; // checked
			disc.sFH.add(sentFrequency);
			disc.sFV.add(valorisationSendFrequency);
			disc.sFValue.add((float) ((Math.pow(valorisationSendFrequency,
			        sentFrequency))));

			disc.ConvInd = ConvInd;
			disc.ConvIndH.add(ConvInd);
			disc.ConvIndV.add(valorisationConvergence);
			disc.ConvIndValue.add(
			        (float) ((Math.pow(valorisationConvergence, ConvInd))));

			disc.ConvDegree = indConv;
			disc.ConvDegreeH.add(indConv);

			/*
			 * if(requestFrequency!=0) proba*= requestFrequency;
			 */

			discussionsProbability.put("" + disc.discussionId, "" + proba);
			disc.acceptaceIndicator = proba;
			disc.acceptaceIndicatorHistory.add(proba);

			// System.out.println("Probability V3 disc.discussionId
			// ("+disc.discussionId+") ---------> "+proba);

		}

		// Fin du calcul des parametres charact�ristiques des alternatives
		// ---------------------------------------------------------------------------------------------------

		return discussionsProbability;
	}


	
	
	
	

}
