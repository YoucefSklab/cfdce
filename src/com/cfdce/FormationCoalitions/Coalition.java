package com.cfdce.FormationCoalitions;

import java.io.IOException;
//import jade.util.leap.ArrayList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import org.graphstream.graph.Graph;

import com.cfdce.Control.Traitment;
import com.cfdce.Plan.ActionsCost;

/*
 * Am�liorations � Apporter : 
 * -	Utiliser trois Classes : 
 * 		1 - Une classe Coalition : qui est la principale.
 * 		2 - Une classe LocalCoalition : pour les coalitions form�es localement
 * 		3 - Une classe ExternalCoalition : pour les coalitions re�ues sous forme de propositions
 * 
 * -	Enlever les Format String sur les coalitions
 */

/**
 * Cette classe Impl�mente une coalition 
 * @author SKLAB Youcef
 * @version 0.3
 *  
 */
public class Coalition {
	
	public String agentOwner;
	public ArrayList agentList = new ArrayList(); // des agents sous forme d'String
	public ArrayList agentNbrList = new ArrayList(); // des agents sous forme de num�ro
	
	public ArrayList edgeList = new ArrayList(); // des actions sous forme d'arc de graphe
	public ArrayList actionList = new ArrayList(); // des actions sous forme de chaine de caract�res
	public int discussionId ; // l'identifiant de la discussion dans lequelle cette coalitions est pr�sente
	public ArrayList agentAcceptedList = new ArrayList();  // liste des agents ayant accept�s la coalitions (Format String)
	public ArrayList agentHaveProposedList = new ArrayList();  // liste des agents ayant envoy�s des propositions
	public ArrayList acceptanceStatus = new ArrayList();  // (meme taille que agentAcceptedList )�tat des acceptations (1 valide / 0 invalide)
	public ArrayList acceptanceReceivedTime = new ArrayList(); // (meme taille que agentAcceptedList ) temps de receptionn des acceptations
	public ArrayList agentRecievedAcceptedProposalList = new ArrayList();  // liste des agents ayant envoy�s leurs messages d'acceptation (Format String)
	public ArrayList acceptanceAcceptStatus = new ArrayList();  // (meme taille que agentRecievedAcceptedProposalList )�tat des acceptations (1 valide / 0 invalide)
	public ArrayList acceptanceAcceptReceivedTime = new ArrayList(); // (meme taille que agentRecievedAcceptedProposalList ) temps de receptionn des acceptations
	
	
	public ArrayList agentRecievedConfirmationList = new ArrayList();  // liste des agents ayant envoy�s leurs messages de confirmation (Format String)
	
	public ArrayList agentForCoalitionList = new ArrayList();  // liste des agents participant dans la coalition
	
	
	public ArrayList agentProposedCoalitionIdList = new ArrayList();   // 
	
	public ArrayList agentAcceptedProposalCoalitionIdList = new ArrayList();   // (meme taille que agentRecievedConfirmationList ) liste des codes des coalitions des agents qui ont accept�s par message PROPOSE
	public ArrayList agentAcceptedAcceptCoalitionIdList = new ArrayList();  // liste des codes des coalitions des agents qui ont accept�s par message ACEPT
	
	public ArrayList agentRejectedList = new ArrayList();
	public float individualCost; // le co�t individual de la coalition
	public float coalitionRealCost; // le co�t courant selon les acceptations collect�es
	public float coalitionFinalCost; // le co�t Final, apr�s reception des confirmations
	public float coalitionEstimatedCost; //  le co�t attendu selon la composition de la coalition
	public static int coalitionCounter; // le compteur des proposition form�es localement.
	public static int coalitionProposalCounter; // le compteur des propositions re�ues
	public String coalitionId;
	public String coalitionOriginalId; // le code progonnal de la coalition 
	public int status = 1;  // Statut d'une coalition. 1 �a veut dire qu'elle est � prendre en consid�ration.  0 : veut dire les actions qu'elle comporte ne sont pas consid�r�es. 
	public boolean sent = false; // faux si elle n'est pas envoy�e, et true si elle est envoy�e.
	
	public boolean isFinal = false;

	/**
	 * Constructeur pour une coalition � envoyer en propositions
	 * @param agList : liste des agents dans la coalition
	 * @param edgList : liste des actions de la coalition
	 * @param agOwner : l'agent propri�taire de la coalition
	 */
	public Coalition(ArrayList agList, ArrayList edgList, String agOwner){
		this.coalitionCounter++;
		this.coalitionId = agOwner+"_cN_"+coalitionCounter;
		this.coalitionOriginalId = this.coalitionId; // le Id original sera utilis� pour not� les coalitions re�ues afin d'inclure le ID dans les messages d'engagements.
		this.agentOwner = agOwner;
		this.agentList = agList;
		this.agentList.add(agOwner);
		
		ArrayList tempAgent = new ArrayList();
		for(int i=0; i<agList.size(); i++){
			String st = (String) agList.get(i);
			int nbr = Integer.parseInt(st.substring(5, st.length()));
			tempAgent.add(nbr);
		}
		
		 //Collections.sort(tempAgent, Collections.reverseOrder());
		// r�ordonner : ordre croissant
		 Collections.sort(tempAgent);

		agentNbrList = tempAgent;
					
		//this.agentAcceptedList.add(agOwner); // d�s la cr�ation d'une coalition, l'agent Owner est d�j� dans la liste des agents qui acceptent
		//this.acceptanceStatus.add(1);
		//this.acceptanceReceivedTime.add(System.currentTimeMillis());
		
		//this.acceptanceAcceptStatus.add(1);
		//this.acceptanceAcceptReceivedTime.add(System.currentTimeMillis());
		
		//this.agentRecievedAcceptedProposalList.add(agOwner);  // d�s la cr�ation d'une coalition, l'agent Owner est d�j� dans la liste des agents qui acceptent
		//this.agentRecievedConfirmationList.add(agOwner);// d�s la cr�ation d'une coalition, l'agent Owner est d�j� dans la liste des agents qui acceptent
		//this.agentAcceptedProposalCoalitionIdList.add(coalitionId); // ajout de la coalition elle meme
		//this.agentAcceptedAcceptCoalitionIdList.add(coalitionId); // ajout de la coalition elle meme
		
		this.edgeList  = edgList; // dans le cas d'une coalition en local, (proposition � envoyer) les actions sont au format edge.
		
	} // fin du constructeur
	

	/**
	 * Constructeur pour une proposition re�ue (coalitions)
	 * @param PropNbr : Identificateur de la proposition tel qu'il est envoy� par l'agent emetteur. 
	 * @param agList : liste des agents dans la coalition
	 * @param edgList : liste des actions de la coalition
	 * @param agOwner : l'agent propri�taire de la coalition
	 */
	public Coalition(String PropNbr, ArrayList agList, ArrayList edgList, String agOwner){ 
		
		this.coalitionProposalCounter++;
		this.coalitionId = "Prop_"+coalitionProposalCounter+"_"+PropNbr;
		this.coalitionOriginalId = PropNbr;
		this.agentOwner = agOwner;
		this.agentList = agList;
		
		//this.agentStrList.add(agOwner); 
		// on n'ajoute pas l'agent propri�taire � cette liste avec ce constructeur, puisque,
		// comme il est utilis� pour pour contenir la coalition re�ue (donc initialement cr�e par un autre agent) elle contient d�j� l'agent
		// propri�taire dans cette liste.
		
		this.actionList = edgList; // dans les cas d'une proposition re�ue, les actions sont au format String
		this.edgeList  = edgList;
	} // fin du constructeur
	


	/**
	 * Affichage des d�tails sur une coalition
	 * 
	 * @param c
	 *            : Coalition
	 */
	public void minimalDisplay(Coalition c) {

		String AgentList = " " + agentOwner + " - ";
		for( int i = 0; i < c.agentList.size() - 1; i++ ) { // pour changer la
		                                                    // position de
		                                                    // l'agent owner et
		                                                    // le mettre en
		                                                    // premier.
			String agent = (String) c.agentList.get(i);
			AgentList = AgentList + agent + " - ";
		} // fin de for

		System.out.println(
		        " -- -- -- -- -- -- -- -- -- -- -- -- -- --- -- -- -- -- -- -- -- -- -- -- -- ");
		System.out.println(" -- Coalition Id : " + c.coalitionId);
		System.out.println(" -- Owner ------ : " + c.agentOwner);
		System.out.println(" -- Edges ------ : " + c.edgeList.toString());
		System.out.println(" -- Agents ----- : " + AgentList);
		System.out.println(
		        " -- Estimated Cost ------- : " + coalitionEstimatedCost);
		System.out.println(
		        " -- -- -- -- -- -- -- -- -- -- -- -- -- --- -- -- -- -- -- -- -- -- -- -- -- ");

	}



/**
 * Evaluer le cout individuel sur une coalition
 * @param actionCostList : liste des co�ts de toutes les actions
 * @throws IOException 
 */
public void evaluateIndividualCostForPropose(Graph agentPlan) throws IOException{

	float cost = 0;
	float extraCost = 0;
	
	for(int i=0; i<edgeList.size(); i++) {
		String e = (String) edgeList.get(i);
		cost+=ActionsCost.getActionCost(e);
		extraCost+= Traitment.getExtraCostForAgent(agentPlan, e);
	}
	
	if(this.isFinal){
		this.individualCost =(float)  (cost/ (float) (1+this.agentRecievedConfirmationList.size())) +  extraCost ;
	}
	else{
		this.individualCost = cost+extraCost;
	}
}
	
	
/**
 * Evaluer le cout estim� sur une coalition
 * @param actionCostList : liste des co�ts de toutes les actions
 * @throws IOException 
 */
public void evaluateEstimatedCostForPropose(Graph agentPlan) throws IOException{

	float cost = 0;
	float extraCost = 0;
	
	for(int i=0; i<edgeList.size(); i++) {
		String e = (String) edgeList.get(i);
		cost+=ActionsCost.getActionCost(e);
		extraCost+= Traitment.getExtraCostForAgent(agentPlan, e);
	}
	
	if(this.isFinal){
		this.coalitionEstimatedCost = (float) (cost/ (float)(this.agentList.size()+this.agentRecievedConfirmationList.size())) +  extraCost ;
		//System.out.println("CoalitionId: "+this.coalitionId+" Cost: "+(float) (cost/ (float)(this.agentList.size()+this.agentRecievedConfirmationList.size())) +  extraCost );
	}
	else{
		this.coalitionEstimatedCost = (float)  (cost/ (float)  this.agentList.size()) +  extraCost ;
	}
	//System.out.println("Cost = "+cost+" Extra Cost = "+extraCost);
	//System.out.println("Evaluation Coalition : "+edgeList.toString()+ "  Liste agent -> " +this.agentList.toString() +"  It's cost is : "+this.coalitionEstimatedCost);
}



/**
 * Evaluer le cout estim� sur une coalition apr�s reception de acceptations
 * @param actionCostList : liste des co�ts de toutes les actions
 * @throws IOException 
 */
public void evaluateEstimatedForAccept(Graph agentPlan) throws IOException{

	float cost = 0;
	float extraCost = 0;
	
	for(int i=0; i<edgeList.size(); i++) {
		String e = (String) edgeList.get(i);
		
		cost+=ActionsCost.getActionCost(e);
		extraCost+= Traitment.getExtraCostForAgent(agentPlan, e);
	}
		
	if(this.isFinal){
		this.coalitionEstimatedCost = (float) (cost/ (float)(1+this.agentHaveProposedList.size()+this.agentRecievedConfirmationList.size())) +  extraCost ;
	}
	else{
		this.coalitionEstimatedCost = (float)  (cost/ (float)  (1+ this.agentHaveProposedList.size() + this.agentRecievedAcceptedProposalList.size())) +  extraCost ;
	}
}




/**
 * Evaluer le cout estim� sur une coalition apr�s reception des confirmations
 * @param actionCostList : liste des co�ts de toutes les actions
 * @throws IOException 
 */
public void evaluateEstimatedForConfirm(Graph agentPlan) throws IOException{


	float cost = 0;
	float extraCost = 0;
	
	for(int i=0; i<edgeList.size(); i++) {
		String e = (String) edgeList.get(i);
		
		cost+=ActionsCost.getActionCost(e);
		extraCost+= Traitment.getExtraCostForAgent(agentPlan, e);
	}
		
	if(this.isFinal){
		this.coalitionEstimatedCost = (float) (cost/ (float)(1+this.agentRecievedAcceptedProposalList.size()+this.agentRecievedConfirmationList.size())) +  extraCost ;
	}
	else{
		this.coalitionEstimatedCost = (float)  (cost/ (float) ( 1+this.agentRecievedAcceptedProposalList.size()+ this.agentRecievedConfirmationList.size())) +  extraCost ;
	}
}



/**
 * Evaluer le co�t Final, apr�s reception des confirmation des agents
 * @param actionCostList : liste des co�ts de toutes les actions
 * @throws IOException 
 */
public void evaluateCoalitionFinalCost(Graph agentPlan, ArrayList actionCostList, long maxValidityTime) throws IOException{

	float cost = 0;
	float extraCost = 0;

	for(int i=0; i<edgeList.size(); i++) {
		String e = (String) edgeList.get(i);
		
		cost+=ActionsCost.getActionCost(e);
		extraCost+=Traitment.getExtraCostForAgent(agentPlan, e);

	} 
		
	if(this.isFinal){
		this.coalitionFinalCost = (float) (cost/ (float)(1+this.agentRecievedConfirmationList.size())) +  extraCost ;
	}
	else{
		this.coalitionFinalCost = (float)  cost+ extraCost ;
	}
	
}


/**
 * Evaluer le co�t Final, apr�s apres generation des solution (solution centralis�e)
 * @param actionCostList : liste des co�ts de toutes les actions
 * @throws IOException 
 */
public void getCoalitionFinalCost(Graph agentPlan, ArrayList actionCostList) throws IOException{

	float cost = 0;
	float extraCost = 0;

	for(int i=0; i<edgeList.size(); i++) {
		String e = (String) edgeList.get(i);
		
		cost+=ActionsCost.getActionCost(e);
		extraCost+=Traitment.getExtraCostForAgent(agentPlan, e);

	} 
		
	this.coalitionFinalCost = (float) (cost/ (float)(this.agentForCoalitionList.size())) +  extraCost ;
		
}

//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------







/**
 * Mettre � jour la validit� des acceptations re�ues; en fonction du temps de validit� d�fini
 * @param maxValidityTime Le dur�e maximale de validit� d'une acceptation re�ue
 */
public void updateCoalitionAcceptanceValidity(long maxValidityTime){
	if(this.agentAcceptedList.size()>1)
	for(int i=1; i<this.agentAcceptedList.size(); i++){
	//	if((System.currentTimeMillis() - (long)this.acceptanceReceivedTime.get(i)) < maxValidityTime){
			this.acceptanceStatus.set(i, 1);
	//	}else{
	//		this.acceptanceStatus.set(i, 0);
	//	}
	}
}




/**
 * Evaluer le co�t r�el d'une coalition
 * @param actionCostList : liste des co�ts de toutes les actions
 * @throws IOException 
 */
/*
public void evaluateCoalitionRealCost(Graph agentPlan, ArrayList actionCostList, long maxValidityTime) throws IOException{

	float cost = 0;
	float extraCost = 0;
	// extraCostClass = new extraActionCost(this.agentOwner);
	for(int i=0; i<edgeList.size(); i++) {
		String e = (String) edgeList.get(i);
		cost+=ActionsCost.getActionCost(e);
		extraCost+=Traitment.getExtraCostForAgent(agentPlan, e);
	} 
	
	//this.updateCoalitionAcceptanceValidity(maxValidityTime);
	
	// parcour de la list � commencer par 1. la premi�re poosition de la liste etant celle de l'agent propri�taire.
	int agentAcceptedNbr = 1;
	for(int i=1; i<this.agentAcceptedList.size(); i++){
			agentAcceptedNbr++;
	}
		
	this.coalitionRealCost = (float) (cost/(agentAcceptedNbr + this.agentRecievedConfirmationList.size())) + extraCost;
	
}
*/




/**
 * Evaluer le co�t r�el d'une coalition
 * @param actionCostList : liste des co�ts de toutes les actions
 * @throws IOException 
 */
/*
public void evaluateCoalitionRealCostAccept(Graph agentPlan, ArrayList actionCostList, long maxValidityTime) throws IOException{

	float cost = 0;
	float extraCost = 0;
	// extraCostClass = new extraActionCost(this.agentOwner);
	for(int i=0; i<edgeList.size(); i++) {
		String e = (String) edgeList.get(i);
		cost+=ActionsCost.getActionCost(e);
		extraCost+=Traitment.getExtraCostForAgent(agentPlan, e);
	} 
	
	//this.updateCoalitionAcceptanceValidity(maxValidityTime);
	
	// parcour de la list � commencer par 1. la premi�re poosition de la liste etant celle de l'agent propri�taire.
	int agentAcceptedNbr = 1;
	for(int i=1; i<this.agentAcceptedList.size(); i++){
			agentAcceptedNbr++;
	}
		
	this.coalitionRealCost = (float) (cost/(agentAcceptedNbr + this.agentRecievedConfirmationList.size())) + extraCost;
	
}
*/



/**
 * Evaluer le co�t Final, apr�s reception des confirmation des agents
 * @param actionCostList : liste des co�ts de toutes les actions
 * @throws IOException 
 */
/*
public void evaluateCoalitionFinalCost(Graph agentPlan, ArrayList actionCostList, long maxValidityTime) throws IOException{

	float cost = 0;
	float extraCost = 0;
	//extraActionCost extraCostClass = new extraActionCost(this.agentOwner);
	for(int i=0; i<edgeList.size(); i++) {
		String e = (String) edgeList.get(i);
		
		cost+=ActionsCost.getActionCost(e);
		extraCost+=Traitment.getExtraCostForAgent(agentPlan, e);

	} 
	
//	this.updateCoalitionAcceptanceValidity(maxValidityTime);
	
	// parcour de la list � commencer par 1. la premi�re poosition de la liste etant celle de l'agent propri�taire.
	int agentConfirmedNbr = this.agentRecievedConfirmationList.size()+1;
	

	this.coalitionFinalCost = (float)(cost/agentConfirmedNbr) + extraCost;
	
	//System.out.println("Coalition :-->  "+edgeList.toString()+"  is Confirmed by : "+agentRecievedConfirmationList.toString());
	//System.out.println("Cost = "+cost+ " ExtraCost = "+extraCost+ " Final Cost is "+ this.coalitionFinalCost );
}
*/










/**
 * Retourner la liste des agents (contenus dans la liste agentStrList qui sont au format String) de la coalition sous forme d'une chaine de caract�re. 
 * @return :  Liste des agents de la coalition en chaine de caract�res (Au stade Proposition).
 */
public String getAgentStrListLocalNames(){
	String agentListString = " ";
	
	for(int i=0; i<agentList.size(); i++){
		String agent = (String) agentList.get(i);
		agentListString+= " - "+agent;
	}
	return agentListString;
}


/**
 * Retourner la liste des agents (contenus dans la liste agentAcceptedList qui sont au format String) de la coalition sous forme d'une chaine de caract�re. 
 * @return : Liste des agents ayant encoy�s des messages d'acceptation pour la coalition en chaine de caract�res (Au stade Acceptation).
 */
public String getAcceptedAgentListLocalNames(){
	String agentListString = " ";
	
	for(int i=0; i<agentAcceptedList.size(); i++){
		String agent = (String) agentAcceptedList.get(i);
		agentListString+= " - "+agent;
	}
	return agentListString;
}





/**
 * Retourner la liste des Edges (Actions) (contenus dans la liste edgeList qui sont au format Edge) de la coalition sous forme d'une chaine de caract�re. 
 * @return : Liste des actions de la coalitions en chaine de caract�res.
 */
public String getEdgeListLocalNames(){
	String edgeListString = " ";
	
	for(int i=0; i<edgeList.size(); i++){
		String edge = (String) edgeList.get(i);
		edgeListString+= " - "+edge;
	} // fin de for
	
	return edgeListString;
}






/**
 * Retourner la liste des actions (contenus dans la liste actionList qui sont au format String) de la coalition sous forme d'une chaine de caract�re. 
 * @return : Liste des actions de la coalitions en chaine de caract�res.
 */
public String getActionListLocalNames(){
	String actionListString = " ";
	
	for(int i=0; i<actionList.size(); i++){
		String action = (String) actionList.get(i);
		actionListString+= " - "+action;
	}
	return actionListString;
}




/**
 * Methode qui retourne true si deux coalitions sont �quivalentes (m�mes actions, memes agents).
 * Une premi�re coalition de l'agent en cours, et une deuxi�me coalition qui celle re�ue d'un autre agent.
 * @param cWithEdges :  Une coalition local � l'agent, dont les actions sont exprim�es en Edges (Format Graphique)
 * @param cWithActions : Une coalition re�ue comme proposition par un autre agent, dont les actions sont exprim�es en String.
 * @return : Vrai si equivalentes (m�mes actions, memes agents), faux sinon. 
 */
public boolean compareTwoCoalition(Coalition localCoalition, Coalition proposedCoalition){
	boolean sameEdges = true;
	boolean sameAgents = true; 

	if((localCoalition.edgeList.size()) == (proposedCoalition.actionList.size())){ // si les deux listes ont le m�me nombre d'action
		for(int i=0; i<localCoalition.edgeList.size(); i++){
			String e = (String) localCoalition.edgeList.get(i);
			
			boolean existInProposed = false;
			
			for(int j=0; j<proposedCoalition.actionList.size(); j++){
				String eProposed = (String) proposedCoalition.actionList.get(j);
				if(e.equals(eProposed)) existInProposed = true;
			}
			
			if(!existInProposed){
				sameEdges = false;
			}
		} 
	}
	else
		sameEdges = false;
	
	
	//System.out.println("From         -->  c.compareTwoCoalition");
	
	/*
	
	if((localCoalition.agentList.size()) == (proposedCoalition.agentList.size())){ // si les deux listes ont le m�me nombre d'agents
		for(int i=0; i<localCoalition.agentList.size(); i++){
			String ag = (String) localCoalition.agentList.get(i);
			
			if(!proposedCoalition.agentList.contains(ag)){
				sameAgents = false;
			} 
		}
	}
	else
		sameAgents = false;
	
	*/
	
	// v�rification sur les owner s'il existe dans les listes : 
	if(!localCoalition.agentList.contains(proposedCoalition.agentOwner)){
		sameAgents = false;
	}
	
	if(!proposedCoalition.agentList.contains(localCoalition.agentOwner)){
		sameAgents = false;
	}
	
	return (sameEdges && sameAgents);
	}


public static final Comparator<Integer> DESCENDING_COMPARATOR_int = new Comparator<Integer>() {
    // Overriding the compare method to sort the age
	public int compare(int o1, int o2) {
		// TODO Auto-generated method stub
		return (int) (o1 - o2);
	}

	@Override
	public int compare(Integer arg0, Integer arg1) {
		// TODO Auto-generated method stub
		return 0;
	}
};

} // fin de la classe
