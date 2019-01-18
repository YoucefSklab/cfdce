package com.cfdce.Control;

import java.util.ArrayList;
import java.util.Hashtable;

import jade.core.Agent;


/**
 * Cette classe comporte un ensemble de param�tres qui sauvgarde l'etat globale d'un agent. 
 * @author SKLAB Youcef
 * @version 0.3
 * 
 *  
 */
public class Constants {
	

	// Param�tres sur les codes des �tapes de l'algorithme
	//----------------------------------------------------
	public final int sendPlanStep;
	public final int receivePlansStep;
	public final int processPlansStep;
	public final int formCoalitionsProposalsStep;
	public final int sendProposalsStep;
	public final int startNewDiscussionStep;	
	public final int receiveProposalsStep;
	public final int evaluateReceivedProposalsStep;
	public final int updateAfterProposalsReceptionStep;
	public final int receiveAcceptProposalsStep_NoteActive;
	public final int updateAfterAcceptProposalsStep;
	public final int sendAcceptProposalsStep;
	public final int receiveAcceptProposalsStep;
	public final int receivedConfirmationsStep;
	public final int whatToDoNextStep;
	public final int sendConfirmationsStep;
	public final int endCoalitionForlmationProcessStep;
	public final int formNewCoalitionsProposalsStep;
	public final int restartProcessStep;
	public final int roundEnd;
	
	
	
	// Param�tres sur les compteurs de passage sur les �tapes
	//----------------------------------------------------
	public int sendPlanCounter;
	public int receivePlansCounter;
	public int processPlansCounter;
	public int formCoalitionsProposalsCounter;
	public int sendProposalsCounter;
	public int receiveProposalsCounter;
	public int evaluateReceivedProposalsCounter;
	public int updateAfterProposalsReceptionCounter;
	public int receiveAcceptProposalsCounter;
	public int updateAfterAcceptProposalsCounter;
	public int sendAcceptProposalsCounter;
	public int processReceivedAcceptProposalsCounter;
	public int receiveConfirmationsCounter;
	public int sendConfirmationsCounter;
	public int endCoalitionForlmationProcessCounter;
	public int formNewCoalitionsProposalsCounter;
	public int restartProcessCounter;

	
	// Param�tres temporels
	//---------------------------------------------
	// Les temps d'attente
	public float waitingTimeToReceiveProposals = 1000; // le temps � attendre pour recevoir les propositions
	public float waitingTimeToReceiveAcceptProposals = 7000; // le temps � attendre pour recevoir les acceptations
	public float waitingTimeToProduceEvent = 1000; // le temps � attendre pour r�agir et faire de nouvelle propositions
	
	// les temps depuis un �v�nement
	public float timeSinceLastEvent = 0; // le temps �coul� depuis le dernier �venement (pour �viter les interblocage -- deadlock en non pas livelock--)
	public float timeSinceLastProposalSent = 0; // le temps �coul� depuis la derni�re proposition envoy�e
	public float timeSinceLastAcceptProposalSent = 0; // le temps �coul� depuis le derni�r accept de proposition envoy�
	
	public float timeSinceLastProposalReceptionCheck = 0; // le temps depuis le dernier check
	
	
	private Agent myAgent;
	
	
	
	// Boolean Variables
	
	public boolean hasDiscListOrderBeenChanged = false;   // si l'ordre des discussions dans la liste des discussions etait chang� 
	
	public boolean isSolutionFound = false;
	public boolean isNoInterestingAlternative = false;
	public boolean isNewProposalsToForm = false;
	public boolean isNewProposalToSend = false;
	public boolean isNewReceivedAcceptProposals = false;
	public boolean isNewAcceptProposalsToForm = false;
	public boolean isNewAcceptProposalsToSend = false;
	
	public boolean isOkayToSendProposals = false;   // si Ok pour envoyer les propositions
	
	public boolean isNewConfirmProposalsToForm = false;
	public boolean isNewConfirmProposalsToSend = false;
	public boolean isHavingConfirmProposalToWaitFor = false; // si l'agent attend de recevoir des confirmations 


	
	
	public boolean isHavingPlansToLookFor = false;  // si l'agent attend des plans 
	public boolean isHavingProposalToWaitFor = false;  // si l'agent attend de recevoir des propositions 
	public boolean isHavingReceivedNewCoalitionProposal = false; // true si reception de nouvelle proposition, false sinon.
	public boolean isHavingNewProposalsToEvaluate = false; // true si reception de nouvelles proposition et besoin de les traiter 
	public boolean isHavingAcceptProposalToWaitFor = false;  // si l'agent a envoy� des propositions pour lesquelles il attend des r�ceptions
	
	
	public boolean isNewDiscussionToRun = false; // si besoin de relancer une nouvele discussion

	
	
	public boolean isHaveSolutionCandidate = false; // si on a d�j� une solution candidate.
	public boolean isHaveSolutionToConfirm = false; // si on a une solution � confirmer 
	
	public boolean isTherePlansToProcess = false; // si on a re�u des plans.
	
	public boolean needRestardProcess = false; // si besoin de relancer le process de formation de coalitions.
	
	



	
	
	
	// les variables sur les alternatives : 
	//----------------------------------------------
	public  int alternativesCount = 0;  //  le nombre d'alternatives du plan local. 
	public  int currentAlternative = 0;  //  l'alt�rnative courante
	public  int currentAlternativeSent = 0; // l'alternative courante envoy�e
	//public  boolean isCurrentAlternativeSent = false; // si toutes les proposition possible de l'alt�rnative courantes sont envoy�es.
	public  boolean areAllAlternativesSent = false; // si toutes les alternatives ont �t� envoy�es.

	
	// Les varaibles sur les discussions
	//---------------------------------------------
	// � mettre dans discussion
	public  boolean areAllAcceptProposalSent = false; // si tous les accpet proposal ont �t� �t� envoy�es.
	public  boolean areAllConfirmProposalSent = false; // si tous les CONFIRM proposal ont �t� �t� envoy�s.
	
	 
	
	
	
	// � revoir. initialement je le met � 100, mais il doit �tre calcul� pour chaque alternatives.
	// referenceCost = le cout le plus petit des couts des alternatives possibles, avec une r�alisation individuelle.
	public float referenceCost = 10000;
	public float currentReferenceCost = 100000;
	public float costLimit = 100000;
	
	
	
	public Hashtable<String,String> discussionParameter=new Hashtable<String,String>();  
	public Hashtable<String,String> agentParameter=new Hashtable<String,String>();  
	
	ArrayList  discussionParameterList = new ArrayList();
	ArrayList  agentParameterList = new ArrayList();
	

	
	
	
	// Pram�tres d'une discussion
	public  String GlobalStep = "GlobalStep";
	public  String OrderingStrategy = "OrderingStrategy";
	public  String Round = "Round";
	public  String IndividualCost = "IndividualCost";
	public  String DiscussionNumber = "DiscussionNumber";
	public  String SelectedAlternative = "SelectedAlternative";
	public  String EstimatedCost = "EstimatedCost";
	public  String RealFinalCost = "RealFinalCost";
	public  String IndividualGainedCost = "IndividualGainedCost";
	public  String DiscussionLostGain = "DiscussionLostGain";
	public  String TotalTasks = "TotalTasks";
	public  String TotalTaskInSystem = "TotalTaskInSystem";
	public  String TotalTaskInstances = "TotalTaskInstances";
	public  String TasksList = "TasksList";
	public  String InvolvedTasksList = "InvolvedTasksList";
	public  String TotalInvolvedTasks = "TotalInvolvedTasks";
	public  String TotalNotInvolvedTasks = "TotalNotInvolvedTasks";
	public  String InvolvedAgentsList = "InvolvedAgentsList";
	public  String InvolvedAcceptedAgentsList = "InvolvedAcceptedAgentsList";
	public  String TotalInvolvedAgents = "TotalInvolvedAgents";
	public  String TotalInvolvedAcceptedAgents = "TotalInvolvedAcceptedAgents";
	public  String AverageTasksPerInvolvedAgents = "AverageTasksPerInvolvedAgents";
	public  String AverageInvolvedTasksPerInvolvedAgents = "AverageInvolvedTasksPerInvolvedAgents";
	public  String AverageAgentsPerTasks = "AverageAgentsPerTasks";
	public  String AverageAgentsPerInvolvedTasks = "AverageAgentsPerInvolvedTasks";
	public  String NodesList = "NodesList";
	public  String TotalNodes = "TotalNodes";
	public  String BifurcationNodesList = "BifurcationNodesList";
	public  String AverageTotalTasksPerBifurcations = "AverageTotalTasksPerBifurcations";
	public  String AverageInvolvedTasksPerBifurcations = "AverageInvolvedTasksPerBifurcations";
	public  String AverageAgentPerBifurcations = "AverageAgentPerBifurcations";
	 
	
	// Param�tre d'une discussion, concenrnant un agent
	  
	  public  String agentMe= "agentMe";
	  public  String Agent= "Agent";
	  public  String StepNbr= "StepNbr";
	  public  String TotalDesActions= "TotalDesActions";
	  public  String TotalDesAlternatives= "TotalDesAlternatives";
	  public  String ListeDesAlternativesImpliquees= "ListeDesAlternativesImpliquees";
	  public  String TotalDesAlternativesImpliquees= "TotalDesAlternativesImpliquees";
	  public  String ListeDesActionsEnCommun= "ListeDesActionsEnCommun";
	  public  String TotalDesActionsEnCommun= "TotalDesActionsEnCommun";
	  public  String ListeDesActionsImpliquees= "ListeDesActionsImpliquees";
	  public  String TotalDesActionsImpliquees= "TotalDesActionsImpliquees";
	  public  String TotalDesActionsNonImpliquees= "TotalDesActionsNonImpliquees";
	  public  String ListeDesAgentsImpliquesAvecLui= "ListeDesAgentsImpliquesAvecLui";
	  public  String TotalDesAgentsImpliquesAvecLui= "TotalDesAgentsImpliquesAvecLui";
	  public  String ListeDesAgentsQuiOntAcceptesAvecLui= "ListeDesAgentsQuiOntAcceptesAvecLui";
	  public  String TotalDesAgentsQuiOntAcceptesAvecLui= "TotalDesAgentsQuiOntAcceptesAvecLui";
	  public  String ListeDesActionsImpliquees_DesActionsDesAutresAgents_AvecLui= "ListeDesActionsImpliquees_DesActionsDesAutresAgents_AvecLui";
	  public  String TotalDesActionsImpliquees_DesActionsDesAutresAgents_AvecLui= "TotalDesActionsImpliquees_DesActionsDesAutresAgents_AvecLui";
	  public  String ListeDesActionsImpliqueesEtAcceptees_AutresAgents_AvecLui= "ListeDesActionsImpliqueesEtAcceptees_AutresAgents_AvecLui";
	  public  String TotalDesActionsImpliqueesEtAcceptees_AutresAgents_AvecLui= "TotalDesActionsImpliqueesEtAcceptees_AutresAgents_AvecLui";
	  public  String MoySesActionsImpliquee_ToutesLesAlternatives_SurTotalSesActions= "MoySesActionsImpliquee_ToutesLesAlternatives_SurTotalSesActions";
	  public  String MoySesActionsImpliquee_UneAlternativeDonnee_SurTotalSesActions= "MoySesActionsImpliquee_UneAlternativeDonnee_SurTotalSesActions";
	  public  String TotalDesAgentsQuiOntAcceptesLesPropositionsAvecSaPresence= "TotalDesAgentsQuiOntAcceptesLesPropositionsAvecSaPresence";
	  public  String TotalDesAgentsQuiNOntPasAcceptesLesPropositionsAvecSaPresence= "TotalDesAgentsQuiNOntPasAcceptesLesPropositionsAvecSaPresence";
	  public  String TotalDeSesNoeuds= "TotalDeSesNoeuds";
	  public  String TotalDeSesNoeudsEnBifurcation= "TotalDeSesNoeudsEnBifurcation";
	  public  String TotalDeSesNoeudsEnBifurcationDansMesPropositions= "TotalDeSesNoeudsEnBifurcationDansMesPropositions";
	  public  String TotalDeSesNoeudsEnbifurcationDansUneAlternativeDonnee= "TotalDeSesNoeudsEnbifurcationDansUneAlternativeDonnee";
	  public  String MoyenneDeSesNoeudsEnBifurcationSurEnsembleDeSesNoeuds= "MoyenneDeSesNoeudsEnBifurcationSurEnsembleDeSesNoeuds";
	  public  String MoyenneDeSesNoeudsEnBifurcationSurEnsembleDesNoeudsImpliques= "MoyenneDeSesNoeudsEnBifurcationSurEnsembleDesNoeudsImpliques";
	  public  String MoyenneDeSesNoeudsEnBifurcationSurEnsembleDesActionsQuIlAccepte= "MoyenneDeSesNoeudsEnBifurcationSurEnsembleDesActionsQuIlAccepte";
	  public  String LeGainAttenduDeLui= "LeGainAttenduDeLui";
	  public  String LeGainReelObtenuDeLui= "LeGainReelObtenuDeLui";
	  public  String LeGainPerduSurLui= "LeGainPerduSurLui";
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public Constants(){
		
		sendPlanStep = 0;
		receivePlansStep = 1;
		processPlansStep = 14;
		formCoalitionsProposalsStep = 2;
		sendProposalsStep = 3;
		receiveProposalsStep = 4;
		evaluateReceivedProposalsStep = 7;
		updateAfterProposalsReceptionStep = 5;
		receiveAcceptProposalsStep_NoteActive = 12;
		updateAfterAcceptProposalsStep = 13;
		sendAcceptProposalsStep = 9;
		receiveAcceptProposalsStep = 10;
		whatToDoNextStep =  8;
		endCoalitionForlmationProcessStep = 11;
		formNewCoalitionsProposalsStep = 15;
		sendConfirmationsStep = 16;
		receivedConfirmationsStep = 17;
		restartProcessStep = 18;
		startNewDiscussionStep = 19;
		roundEnd = 25;
		
		
		
		sendPlanCounter =0;
		receivePlansCounter = 0;
		processPlansCounter = 0;
		formCoalitionsProposalsCounter = 0;
		sendProposalsCounter = 0;
		receiveProposalsCounter = 0;
		evaluateReceivedProposalsCounter = 0;
		updateAfterProposalsReceptionCounter = 0;
		receiveAcceptProposalsCounter = 0;
		updateAfterAcceptProposalsCounter = 0;
		sendAcceptProposalsCounter = 0;
		processReceivedAcceptProposalsCounter = 0;
		receiveConfirmationsCounter = 0;
		sendConfirmationsCounter = 0;
		endCoalitionForlmationProcessCounter = 0;
		formNewCoalitionsProposalsCounter = 0;
		restartProcessCounter = 0;
		
		
		
		
		// intitialisation de la table discussionParameter
		discussionParameter.put( agentMe , "");
		discussionParameter.put( GlobalStep , "");
		discussionParameter.put( StepNbr , "");
		discussionParameter.put( OrderingStrategy , "");
		discussionParameter.put( Round , "");
		discussionParameter.put( IndividualCost , "");
		discussionParameter.put( DiscussionNumber , "");
		discussionParameter.put( SelectedAlternative , "");
		discussionParameter.put( EstimatedCost , "");
		discussionParameter.put( RealFinalCost , "");
		discussionParameter.put( IndividualGainedCost , "");
		discussionParameter.put( DiscussionLostGain , "");
		discussionParameter.put( TotalTasks , "");
		discussionParameter.put( TotalTaskInSystem , "");
		discussionParameter.put( TotalTaskInstances , "");
		discussionParameter.put( TasksList , "");
		discussionParameter.put( InvolvedTasksList , "");
		discussionParameter.put( TotalInvolvedTasks , "");
		discussionParameter.put( TotalNotInvolvedTasks , "");
		discussionParameter.put( InvolvedAgentsList , "");
		discussionParameter.put( InvolvedAcceptedAgentsList , "");
		discussionParameter.put( TotalInvolvedAgents , "");
		discussionParameter.put( TotalInvolvedAcceptedAgents , "");
		discussionParameter.put( AverageTasksPerInvolvedAgents , "");
		discussionParameter.put( AverageInvolvedTasksPerInvolvedAgents , "");
		discussionParameter.put( AverageAgentsPerTasks , "");
		discussionParameter.put( AverageAgentsPerInvolvedTasks , "");
		discussionParameter.put( NodesList , "");
		discussionParameter.put( TotalNodes , "");
		discussionParameter.put( BifurcationNodesList , "");
		discussionParameter.put( AverageTotalTasksPerBifurcations , "");
		discussionParameter.put( AverageInvolvedTasksPerBifurcations , "");
		discussionParameter.put( AverageAgentPerBifurcations , "");
		
		
		// Initialisation de la table agentParameter
		
		 agentParameter.put( agentMe, "");
		 agentParameter.put( Agent, "");
		 agentParameter.put( GlobalStep, "");
		 agentParameter.put( StepNbr, "");
		 agentParameter.put( TotalDesActions, "");
		 agentParameter.put( TotalDesAlternatives, "");
		 agentParameter.put( ListeDesAlternativesImpliquees, "");
		 agentParameter.put( TotalDesAlternativesImpliquees, "");
		 agentParameter.put( ListeDesActionsEnCommun, "");
		 agentParameter.put( TotalDesActionsEnCommun, "");
		 agentParameter.put( ListeDesActionsImpliquees, "");
		 agentParameter.put( TotalDesActionsImpliquees, "");
		 agentParameter.put( TotalDesActionsNonImpliquees, "");
		 agentParameter.put( ListeDesAgentsImpliquesAvecLui, "");
		 agentParameter.put( TotalDesAgentsImpliquesAvecLui, "");
		 agentParameter.put( ListeDesAgentsQuiOntAcceptesAvecLui, "");
		 agentParameter.put( TotalDesAgentsQuiOntAcceptesAvecLui, "");
		 agentParameter.put( ListeDesActionsImpliquees_DesActionsDesAutresAgents_AvecLui, "");
		 agentParameter.put( TotalDesActionsImpliquees_DesActionsDesAutresAgents_AvecLui, "");
		 agentParameter.put( ListeDesActionsImpliqueesEtAcceptees_AutresAgents_AvecLui, "");
		 agentParameter.put( TotalDesActionsImpliqueesEtAcceptees_AutresAgents_AvecLui, "");
		 agentParameter.put( MoySesActionsImpliquee_ToutesLesAlternatives_SurTotalSesActions, "");
		 agentParameter.put( MoySesActionsImpliquee_UneAlternativeDonnee_SurTotalSesActions, "");
		 agentParameter.put( TotalDesAgentsQuiOntAcceptesLesPropositionsAvecSaPresence, "");
		 agentParameter.put( TotalDesAgentsQuiNOntPasAcceptesLesPropositionsAvecSaPresence, "");
		 agentParameter.put( TotalDeSesNoeuds, "");
		 agentParameter.put( TotalDeSesNoeudsEnBifurcation, "");
		 agentParameter.put( TotalDeSesNoeudsEnBifurcationDansMesPropositions, "");
		 agentParameter.put( TotalDeSesNoeudsEnbifurcationDansUneAlternativeDonnee, "");
		 agentParameter.put( MoyenneDeSesNoeudsEnBifurcationSurEnsembleDeSesNoeuds, "");
		 agentParameter.put( MoyenneDeSesNoeudsEnBifurcationSurEnsembleDesNoeudsImpliques, "");
		 agentParameter.put( MoyenneDeSesNoeudsEnBifurcationSurEnsembleDesActionsQuIlAccepte, "");
		 agentParameter.put( LeGainAttenduDeLui, "");
		 agentParameter.put( LeGainReelObtenuDeLui, "");
		 agentParameter.put( LeGainPerduSurLui, "");
		 
		 
	
		 discussionParameterList.add(agentMe);
		 discussionParameterList.add(GlobalStep);
		 discussionParameterList.add(StepNbr);
		 discussionParameterList.add(OrderingStrategy);
		 discussionParameterList.add(Round);
		 discussionParameterList.add(IndividualCost);
		 discussionParameterList.add(DiscussionNumber);
		 discussionParameterList.add(SelectedAlternative);
		 discussionParameterList.add(EstimatedCost);
		 discussionParameterList.add(RealFinalCost);
		 discussionParameterList.add(IndividualGainedCost);
		 discussionParameterList.add(DiscussionLostGain);
		 discussionParameterList.add(TotalTasks);
		 discussionParameterList.add(TotalTaskInSystem);
		 discussionParameterList.add(TotalTaskInstances);
		 discussionParameterList.add(TasksList);
		 discussionParameterList.add(InvolvedTasksList);
		 discussionParameterList.add(TotalInvolvedTasks);
		 discussionParameterList.add(TotalNotInvolvedTasks);
		 discussionParameterList.add(InvolvedAgentsList);
		 discussionParameterList.add(InvolvedAcceptedAgentsList);
		 discussionParameterList.add(TotalInvolvedAgents);
		 discussionParameterList.add(TotalInvolvedAcceptedAgents);
		 discussionParameterList.add(AverageTasksPerInvolvedAgents);
		 discussionParameterList.add(AverageInvolvedTasksPerInvolvedAgents);
		 discussionParameterList.add(AverageAgentsPerTasks);
		 discussionParameterList.add(AverageAgentsPerInvolvedTasks);
		 discussionParameterList.add(NodesList);
		 discussionParameterList.add(TotalNodes);
		 discussionParameterList.add(BifurcationNodesList);
		 discussionParameterList.add(AverageTotalTasksPerBifurcations);
		 discussionParameterList.add(AverageInvolvedTasksPerBifurcations);
		 discussionParameterList.add(AverageAgentPerBifurcations);
		 
		 
		 agentParameterList.add(agentMe); // "Agent";
		 agentParameterList.add(Agent); // "Agent";
		 agentParameterList.add(GlobalStep); // "GlobalStep";
		 agentParameterList.add(StepNbr);
		 agentParameterList.add(TotalDesActions); // "TotalDesActions";
		 agentParameterList.add(TotalDesAlternatives); // "TotalDesAlternatives";
		 agentParameterList.add(ListeDesAlternativesImpliquees); // "ListeDesAlternativesImpliquees";
		 agentParameterList.add(TotalDesAlternativesImpliquees); // "TotalDesAlternativesImpliquees";
		 agentParameterList.add(ListeDesActionsEnCommun); // "ListeDesActionsEnCommun";
		 agentParameterList.add(TotalDesActionsEnCommun); // "TotalDesActionsEnCommun";
		 agentParameterList.add(ListeDesActionsImpliquees); // "ListeDesActionsImpliquees";
		 agentParameterList.add(TotalDesActionsImpliquees); // "TotalDesActionsImpliquees";
		 agentParameterList.add(TotalDesActionsNonImpliquees); // "TotalDesActionsNonImpliquees";
		 agentParameterList.add(ListeDesAgentsImpliquesAvecLui); // "ListeDesAgentsImpliquesAvecLui";
		 agentParameterList.add(TotalDesAgentsImpliquesAvecLui); // "TotalDesAgentsImpliquesAvecLui";
		 agentParameterList.add(ListeDesAgentsQuiOntAcceptesAvecLui); // "ListeDesAgentsQuiOntAcceptesAvecLui";
		 agentParameterList.add(TotalDesAgentsQuiOntAcceptesAvecLui); // "TotalDesAgentsQuiOntAcceptesAvecLui";
		 agentParameterList.add(ListeDesActionsImpliquees_DesActionsDesAutresAgents_AvecLui); // "ListeDesActionsImpliquees_DesActionsDesAutresAgents_AvecLui";
		 agentParameterList.add(TotalDesActionsImpliquees_DesActionsDesAutresAgents_AvecLui); // "TotalDesActionsImpliquees_DesActionsDesAutresAgents_AvecLui";
		 agentParameterList.add(ListeDesActionsImpliqueesEtAcceptees_AutresAgents_AvecLui); // "ListeDesActionsImpliqueesEtAcceptees_AutresAgents_AvecLui";
		 agentParameterList.add(TotalDesActionsImpliqueesEtAcceptees_AutresAgents_AvecLui); // "TotalDesActionsImpliqueesEtAcceptees_AutresAgents_AvecLui";
		 agentParameterList.add(MoySesActionsImpliquee_ToutesLesAlternatives_SurTotalSesActions); // "MoySesActionsImpliquee_ToutesLesAlternatives_SurTotalSesActions";
		 agentParameterList.add(MoySesActionsImpliquee_UneAlternativeDonnee_SurTotalSesActions); // "MoySesActionsImpliquee_UneAlternativeDonnee_SurTotalSesActions";
		 agentParameterList.add(TotalDesAgentsQuiOntAcceptesLesPropositionsAvecSaPresence); // "TotalDesAgentsQuiOntAcceptesLesPropositionsAvecSaPresence";
		 agentParameterList.add(TotalDesAgentsQuiNOntPasAcceptesLesPropositionsAvecSaPresence); // "TotalDesAgentsQuiNOntPasAcceptesLesPropositionsAvecSaPresence";
		 agentParameterList.add(TotalDeSesNoeuds); // "TotalDeSesNoeuds";
		 agentParameterList.add(TotalDeSesNoeudsEnBifurcation); // "TotalDeSesNoeudsEnBifurcation";
		 agentParameterList.add(TotalDeSesNoeudsEnBifurcationDansMesPropositions); // "TotalDeSesNoeudsEnBifurcationDansMesPropositions";
		 agentParameterList.add(TotalDeSesNoeudsEnbifurcationDansUneAlternativeDonnee); // "TotalDeSesNoeudsEnbifurcationDansUneAlternativeDonnee";
		 agentParameterList.add(MoyenneDeSesNoeudsEnBifurcationSurEnsembleDeSesNoeuds); // "MoyenneDeSesNoeudsEnBifurcationSurEnsembleDeSesNoeuds";
		 agentParameterList.add(MoyenneDeSesNoeudsEnBifurcationSurEnsembleDesNoeudsImpliques); // "MoyenneDeSesNoeudsEnBifurcationSurEnsembleDesNoeudsImpliques";
		 agentParameterList.add(MoyenneDeSesNoeudsEnBifurcationSurEnsembleDesActionsQuIlAccepte); // "MoyenneDeSesNoeudsEnBifurcationSurEnsembleDesActionsQuIlAccepte";
		 agentParameterList.add(LeGainAttenduDeLui); // "LeGainAttenduDeLui";
		 agentParameterList.add(LeGainReelObtenuDeLui); // "LeGainReelObtenuDeLui";
		 agentParameterList.add(LeGainPerduSurLui); // "LeGainPerduSurLui";
	}  // fin du constructeur

	
public void setAgent(Agent ag){
	this.myAgent = ag;
}	// fin de la m�thode


public String getAgentColor(String agentName){
	String Color= "Yellow";
	
	if(agentName.equals("Agent1")) return "Blue";
	
	if(agentName.equals("Agent2")) return "Orange";
	
	if(agentName.equals("Agent3")) return "Green";
	
	if(agentName.equals("Agent4")) return "Red";
	
	if(agentName.equals("Agent5")) return "Yellow";
	
	if(agentName.equals("Agent6")) return "aquamarine";
	
	if(agentName.equals("Agent7")) return "Ruby";
	
	if(agentName.equals("Agent8")) return "Salmon";
	
	if(agentName.equals("Agent9")) return "Purple";
	
	if(agentName.equals("Agent10")) return "Plum";
	
	if(agentName.equals("Agent11")) return "Pink";
	
	if(agentName.equals("Agent12")) return "Pear";
	
	if(agentName.equals("Agent13")) return "Orchid";
	
	if(agentName.equals("Agent14")) return "Olive";
	
	if(agentName.equals("Agent15")) return "Ocher";
	
	if(agentName.equals("Agent16")) return "Magenta";
	
	if(agentName.equals("Agent17")) return "Lime";
	
	if(agentName.equals("Agent18")) return "Lavender";
	
	if(agentName.equals("Agent19")) return "Jade";
	
	if(agentName.equals("Agent20")) return "Cyan";
	
	if(agentName.equals("Agent21")) return "Coral";
	
	if(agentName.equals("Agent22")) return "Chocolate";
	
	if(agentName.equals("Agent23")) return "Champagne";
	
	if(agentName.equals("Agent24")) return "Blush";
	
	if(agentName.equals("Agent25")) return "Bronze";
	
	if(agentName.equals("Agent26")) return "Orange";
	
	if(agentName.equals("Agent27")) return "Green";
	
	if(agentName.equals("Agent28")) return "Red";
	
	if(agentName.equals("Agent29")) return "Yellow";
	
	if(agentName.equals("Agent30")) return "aquamarine";
	
	if(agentName.equals("Agent31")) return "Cyan";
	
	if(agentName.equals("Agent32")) return "Coral";
	
	if(agentName.equals("Agent33")) return "Chocolate";
	
	if(agentName.equals("Agent34")) return "Champagne";
	
	if(agentName.equals("Agent35")) return "Blush";
	
	if(agentName.equals("Agent36")) return "Bronze";
	
	if(agentName.equals("Agent37")) return "Orange";
	
	if(agentName.equals("Agent38")) return "Green";
	
	if(agentName.equals("Agent39")) return "Red";
	
	if(agentName.equals("Agent40")) return "Yellow";
	
		
		return Color;
}


/**
 * Affichage des variables d'�tat caract�risant l'�tat de l'agent � une �tape donn�e du processus
 * @param atStage Intitul� de l'�tape
 * @param times Variable bool�enne pour indiquer si les compteurs temporels seront affich�s.
 * @param isVariables Variable bool�enne pour indiquer si les variables de type isXxxx seront affich�es
 * @param needVariables Variable bool�enne pour indiquer si les variables de type needXxxx seront affich�es
 * @param counters Variable bool�enne pour indiquer si les compteurs de passage sur les �tapes seront affich�s.
 * @param discussionRelated Variable bool�enne pour indiquer si les variables relatives aux discussions seront affich�s
 */
public void printState(String atStage, boolean times, boolean isVariables, boolean needVariables, boolean counters, boolean discussionRelated){
	
	
	
	if(times){
		System.out.println(" ");
		System.out.println(" --------------------------------------------------> Times");
		System.out.println("    >  timeSinceLastEvent : "+this.timeSinceLastEvent);
		System.out.println("    >  timeSinceLastProposalSent : "+this.timeSinceLastProposalSent);
		System.out.println("    >  timeSinceLastAcceptProposalSent : "+this.timeSinceLastAcceptProposalSent);
		System.out.println("    >  timeSinceLastProposalReceptionCheck : "+this.timeSinceLastProposalReceptionCheck);
		
	}
	
	if(isVariables){
		System.out.println(" ");
		System.out.println(" --------------------------------------------------> isVariables");
		System.out.println("    >  isHaveSolutionCandidate : "+this.isHaveSolutionCandidate);
		System.out.println("    >  isHavingPlansToLookFor : "+this.isHavingPlansToLookFor);
		System.out.println("    >  isHavingAcceptProposalToWaitFor : "+this.isHavingAcceptProposalToWaitFor);
		System.out.println("    >  isHavingProposalToWaitFor : "+this.isHavingProposalToWaitFor);
		System.out.println("    >  isHavingNewProposalsToEvaluate : "+this.isHavingNewProposalsToEvaluate);
		
		System.out.println("    >  isNewAcceptProposalsToForm : "+this.isNewAcceptProposalsToForm);
		System.out.println("    >  isNewAcceptProposalsToSend : "+this.isNewAcceptProposalsToSend);
		System.out.println("    >  isNewProposalsToForm : "+this.isNewProposalsToForm);
		System.out.println("    >  isNewProposalToSend : "+this.isNewProposalToSend);
		System.out.println("    >  isNewReceivedAcceptProposals : "+this.isNewReceivedAcceptProposals);
		System.out.println("    >  isHavingReceivedNewCoalitionProposal : "+this.isHavingReceivedNewCoalitionProposal);
		System.out.println("    >  isSolutionFound : "+this.isSolutionFound);
		
		System.out.println("    >  isHaveSolutionToConfirm : "+this.isHaveSolutionToConfirm);
		System.out.println("    >  isNewConfirmProposalsToForm : "+this.isNewConfirmProposalsToForm);
		System.out.println("    >  isNewConfirmProposalsToSend : "+this.isNewConfirmProposalsToSend);
		System.out.println("    >  isHavingConfirmProposalToWaitFor : "+this.isHavingConfirmProposalToWaitFor);
		System.out.println("    -->  isNewDiscussionToRun : "+this.isNewDiscussionToRun);

		
		System.out.println("    -->  isTherePlansToProcess : "+this.isTherePlansToProcess);
		
	
	}
	
	if(needVariables){
		System.out.println(" ");
		System.out.println(" --------------------------------------------------> needVariables");
		System.out.println("    -->  needRestardProcess : "+this.needRestardProcess);
	}

	
	
	if(counters){
		System.out.println(" ");
		System.out.println(" --------------------------------------------------> counters");
		System.out.println("    -->  sendPlanCounter :"+this.sendPlanCounter );
		System.out.println("    -->  receivePlansCounter :"+this.receivePlansCounter );
		System.out.println("    -->  processPlansCounter :"+this.processPlansCounter );
		System.out.println("    -->  formCoalitionsProposalsCounter :"+this.formCoalitionsProposalsCounter );
		System.out.println("    -->  sendProposalsCounter :"+this.sendProposalsCounter );
		System.out.println("    -->  receiveProposalsCounter :"+this.receiveProposalsCounter );
		System.out.println("    -->  evaluateReceivedProposalsCounter :"+this.evaluateReceivedProposalsCounter );
		System.out.println("    -->  updateAfterProposalsReceptionCounter :"+this.updateAfterProposalsReceptionCounter );
		System.out.println("    -->  receiveAcceptProposalsCounter :"+this.receiveAcceptProposalsCounter );
		System.out.println("    -->  updateAfterAcceptProposalsCounter :"+this.updateAfterAcceptProposalsCounter );
		System.out.println("    -->  sendAcceptProposalsCounter :"+this.sendAcceptProposalsCounter );
		System.out.println("    -->  processReceivedAcceptProposalsCounter :"+this.processReceivedAcceptProposalsCounter );
		System.out.println("    -->  receiveConfirmationsCounter :"+this.receiveConfirmationsCounter );
		System.out.println("    -->  sendConfirmationsCounter :"+this.sendConfirmationsCounter);
		System.out.println("    -->  endCoalitionForlmationProcessCounter :"+this.endCoalitionForlmationProcessCounter );
		System.out.println("    -->  formNewCoalitionsProposalsCounter :"+this.formNewCoalitionsProposalsCounter );
		System.out.println("    -->  restartProcessCounter :"+this.restartProcessCounter );
	}
	
	if(discussionRelated){
		System.out.println(" ");
		System.out.println(" --------------------------------------------------> discussionRelated");
		System.out.println("    -->  alternativesCount :"+this.alternativesCount );
		System.out.println("    -->  currentAlternative :"+this.currentAlternative );
		System.out.println("    -->  currentAlternativeSent :"+this.currentAlternativeSent );
		//System.out.println("    -->  isCurrentAlternativeSent :"+this.isCurrentAlternativeSent );
		System.out.println("    -->  areAllAlternativesSent :"+this.areAllAlternativesSent );
		System.out.println("    -->  areAllAcceptProposalSent :"+this.areAllAcceptProposalSent );
		System.out.println("    -->  areAllConfirmProposalSent :"+this.areAllConfirmProposalSent );
		System.out.println("    -->  referenceCost :"+this.referenceCost );
		System.out.println("    -->  currentReferenceCost :"+this.currentReferenceCost );
		System.out.println("    -->  costLimit :"+this.costLimit );
	}
	
}


//-------------------------------------------------------------------
public String[] getDiscussionParameterInTableField(){

	String [] tableField = new String[discussionParameter.size()] ;
	
	for(int i=0; i<discussionParameterList.size(); i++){
		tableField[i] = (String) discussionParameterList.get(i);
	}
	
	return tableField;
} // fin de la m�thode getDiscussionParameterInTableField
//-------------------------------------------------------------------
public String[] getDiscussionParameterInTableValue(){

	String [] tableValue = new String[discussionParameter.size()] ;
	
	for(int i=0; i<tableValue.length; i++){
		tableValue[i] = "";
	}

	for(int i=0; i<discussionParameterList.size(); i++){
		tableValue[i] = (String) discussionParameter.get(discussionParameterList.get(i));
	}

	return tableValue;
} // fin de la m�thode getDiscussionParameterInTableValue

//-------------------------------------------------------------------
public String[] getAgentParameterInTableField(){
	
	String [] tableField = new String[agentParameter.size()] ;
	
	for(int i=0; i<agentParameterList.size(); i++){
		tableField[i] = (String) agentParameterList.get(i);
	}
	
	return tableField;
} // fin de la m�thode getAgentParameterInTableField
//-------------------------------------------------------------------

//-------------------------------------------------------------------
public String[] getAgentParameterInTableValue(){
	
	String [] tableValue = new String[agentParameter.size()] ;
	
	for(int i=0; i<tableValue.length; i++){
		tableValue[i] = "";
	}
	
	for(int i=0; i<agentParameter.size(); i++){
		tableValue[i] = (String) agentParameter.get(agentParameterList.get(i));
	}

	return tableValue;
} // fin de la m�thode getAgentParameterInTableValue
//-------------------------------------------------------------------


/**
 * R�initialisation des param�tres pour une relance du processus de formation de coalitions
 */
public void resetParameters(){
	
	waitingTimeToReceiveProposals = 1000; // le temps � attendre pour recevoir les propositions
	waitingTimeToReceiveAcceptProposals = 7000; // le temps � attendre pour recevoir les acceptations
	waitingTimeToProduceEvent = 1000; // le temps � attendre pour r�agir et faire de nouvelle propositions
	
	timeSinceLastEvent = 0; // le temps �coul� depuis le dernier �venement (pour �viter les interblocage -- deadlock en non pas livelock--)
	timeSinceLastProposalSent = 0; // le temps �coul� depuis la derni�re proposition envoy�e
	timeSinceLastAcceptProposalSent = 0; // le temps �coul� depuis le derni�r accept de proposition envoy�
	
	isSolutionFound = false;
	isNewProposalsToForm = false;
	isNewProposalToSend = false;
	isNewReceivedAcceptProposals = false;
	isNewAcceptProposalsToForm = false;
	isNewAcceptProposalsToSend = false;
	
	isOkayToSendProposals = false;   // si Ok pour envoyer les propositions
	
	isNewConfirmProposalsToForm = false;
	isNewConfirmProposalsToSend = false;
	isHavingConfirmProposalToWaitFor = false; // si l'agent attend de recevoir des confirmations 

	isHavingPlansToLookFor = false;  // si l'agent attend des plans 
	isHavingProposalToWaitFor = false;  // si l'agent attend de recevoir des propositions 
	isHavingReceivedNewCoalitionProposal = false; // true si reception de nouvelle proposition, false sinon.
	isHavingNewProposalsToEvaluate = false; // true si reception de nouvelles proposition et besoin de les traiter 
	isHavingAcceptProposalToWaitFor = false;  // si l'agent a envoy� des propositions pour lesquelles il attend des r�ceptions
	
	isHaveSolutionCandidate = false; // si on a d�j� une solution candidate.
	isHaveSolutionToConfirm = false; // si on a une solution � confirmer 
	
	needRestardProcess = false; // si besoin de relancer le process de formation de coalitions.
	

	alternativesCount = 0;  //  le nombre d'alternatives du plan local. 
	currentAlternative = 0;  //  l'alt�rnative courante
	currentAlternativeSent = 0; // l'alternative courante envoy�e
	//isCurrentAlternativeSent = false; // si toutes les proposition possible de l'alt�rnative courantes sont envoy�es.
	areAllAlternativesSent = false; // si toutes les alternatives ont �t� envoy�es.

	areAllAcceptProposalSent = false; // si tous les accpet proposal ont �t� �t� envoy�es.
	areAllConfirmProposalSent = false; // si tous les CONFIRM proposal ont �t� �t� envoy�s.
	
	isTherePlansToProcess =  false;
	
	//referenceCost = 10000;
	//currentReferenceCost = 100000;
	
	//sendPlanCounter =0;
	receivePlansCounter = 0;
	processPlansCounter = 0;
	formCoalitionsProposalsCounter = 0;
	sendProposalsCounter = 0;
	receiveProposalsCounter = 0;
	evaluateReceivedProposalsCounter = 0;
	updateAfterProposalsReceptionCounter = 0;
	receiveAcceptProposalsCounter = 0;
	updateAfterAcceptProposalsCounter = 0;
	sendAcceptProposalsCounter = 0;
	processReceivedAcceptProposalsCounter = 0;
	receiveConfirmationsCounter = 0;
	sendConfirmationsCounter = 0;
	endCoalitionForlmationProcessCounter = 0;
	formNewCoalitionsProposalsCounter = 0;
	restartProcessCounter = 0;
	
}

/**
 * R�initialisation des compteurs sur les �tapes
 */
public void resetStepsCounters(){
	sendPlanCounter =0;
	receivePlansCounter = 0;
	processPlansCounter = 0;
	formCoalitionsProposalsCounter = 0;
	sendProposalsCounter = 0;
	receiveProposalsCounter = 0;
	evaluateReceivedProposalsCounter = 0;
	updateAfterProposalsReceptionCounter = 0;
	receiveAcceptProposalsCounter = 0;
	updateAfterAcceptProposalsCounter = 0;
	sendAcceptProposalsCounter = 0;
	processReceivedAcceptProposalsCounter = 0;
	receiveConfirmationsCounter = 0;
	sendConfirmationsCounter = 0;
	endCoalitionForlmationProcessCounter = 0;
	formNewCoalitionsProposalsCounter = 0;
	restartProcessCounter = 0;
}


} // fin de la classe
