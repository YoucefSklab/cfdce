package com.cfdce.Agents;




import java.awt.Component;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.JPanel;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.view.View;
import org.graphstream.ui.view.Viewer;

import com.cfdce.Control.Constants;
import com.cfdce.Control.Traitment;
import com.cfdce.DB.BDConnect;
import com.cfdce.FormationCoalitions.Coalition;
import com.cfdce.FormationCoalitions.Discussion;
import com.cfdce.FormationCoalitions.coalitionProposalMessage;
import com.cfdce.InterfaceGraphique.plansOverview;
import com.cfdce.Plan.AgentPlan;
import com.cfdce.Plan.PlanManagment;
import com.cfdce.Plan.planMessage;
import com.cfdce.Plan.planSet;
import com.cfdce.Plan.subSets;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
//import java.util.ArrayList;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;
//import jade.util.leap.ArrayList;

/**
 * Impl�mentation des comportemens d'un agent.
 * @author SKLAB Youcef
 * @version 0.3
 * 
 *  
 */
public class Agent2 extends Agent {
		
		// Les plans Complexes : 7 -- 10 -- 12 -- 14 -- 26
	
		/** 
		 * Tableau contenant les agents qui viennent de rejoindre le syst�me Format AID (remise � vide aprs�s chaque it�ration)
		 */
		public AID[] agentsList; 	
		
		/** 
		 * Liste contenant les agents qui viennent de rejoindre le syst�me Format String (remise � vide aprs�s chaque it�ration)
		 */
		public ArrayList agentsListStr = new ArrayList(); 	
		
		/**
		 * Liste de tous les agents au Format AID
		 */
		ArrayList allAgentAid = new ArrayList();
		
		/** 
		 * Liste des agents ayant le plan de l'agent en cours. Cette liste repr�sente la liste de tous les agents qui ont apparus dans le syst�me. 
		 * (Historique des agents ayant re�us le plan de l'agent en cours)
		 */
		public ArrayList agentsHaveMyPlan = new ArrayList();
		
		/**
		 * Liste de liste contenant les fr�quences de demandes formul�es par un agents donn� pour une action donn�e.
		 * Les �l�ments de la liste (qui sont des listes des fr�quences des actions) sont dispos�s selon l'ordre de la liste agentsHaveMyPlan
		 */
		public ArrayList actionsFrequencyByAgent = new ArrayList();

		/** 
		 * Liste des agent qui viennent de rejoindre le syst�me (Format String) (remise � vide apr�s chaque it�ration)
		 */
		public ArrayList agentsHaveJoined = new ArrayList(); 		
		
		/** 
		 * Liste des agent qui ont quitt�s le syst�me (Historique de tous les agents). Si un agent quitte le syst�me, obligation de mettre � jour toutes 
		 * les autres listes d�pendantes 
		 */
		public ArrayList agentsHaveLeft = new ArrayList(); 		// les agents qui ont quitt�s le syst�me.

		/** 
		 * Liste des agents qui ont envoy�s leurs plans (parmi les agents qui ont rejoint le syst�me) 
		 */
		public ArrayList agentsHaveSentPlans = new ArrayList();
		
		/** 
		 * Liste des agents qui ont rejoint le syst�me mais qui n'ont toujours pas envoy�s leurs plans. 
		 */
		public ArrayList agentsWaitingTheirPlans = new ArrayList();
		
		/** 
		 * Liste des agents pour lesquels il est attendu de recevoir des propositions de leurs part.
		 */
		public ArrayList agentsExpectedProposals = new ArrayList();
		
		/** 
		 * Liste des agents sollicit�s (par l'agent en cours) par l'envoi de propositions 
		 */
		public ArrayList agentsSolicitedList = new ArrayList(); 	 
		
		/** 
		 * Liste des agents � qui l'agent en cours a envoy� des propositions 
		 */
		public ArrayList agentsHaveSentProposals = new ArrayList();
		
		/** 
		 * Liste des agents pour lesquels l'agent en cours a envoy� des acceptations de coalitions
		 */
		public ArrayList agentsForWhichSentAccept = new ArrayList();  
		
		/** 
		 * liste des agents qui ont envoy�s des acceptations en retour aux propositions de l'agent en cours 
		 */
		public ArrayList agentsHaveSentAccept = new ArrayList();
		
		/** 
		 * Liste des agents pour lesquels l'agent en cours a envoy� des confirmattions 
		 */
		public ArrayList agentsForWhichSentConfirm = new ArrayList();
		
		/**
		 * Liste des agents qui ont envoy�s des confirmations en retour aux confirmations de l'agent en cours 
		 */
		public ArrayList agentsHaveSentConfirm = new ArrayList();	 
		
		/**
		 * Liste des agents ayant envoy� une proposition dans le round courant.
		 */
		public ArrayList agentRoundList = new ArrayList();
		/**
		 * les anciens ordres des proposition des agents au round (Round i-1)
		 */
		public ArrayList oldAgentsOrder = new ArrayList();
		
		/**
		 * l'ordre des proposition des agents au round (Round i)
		 */
		public ArrayList newAgentsOrder = new ArrayList();
		
		/**
		 * les profils des propositions des agents (Round i)
		 */
		public ArrayList newAgentsPropoProfile = new ArrayList();
		
		
		/**
		 * les profils des propositions des agents (Round i-1)
		 */
		public ArrayList oldAgentsPropoProfile = new ArrayList();
		
		
		/**
		 * Liste Totale des actions pr�sentes dans le syst�me
		 */
		public ArrayList totalActionsList = new ArrayList();
		
		public ArrayList costTraking = new ArrayList();
		
		/**
		* Liste des discussions envoy�es
		*/
		public ArrayList sentDiscussionList = new ArrayList();
		
		/**
		* La frequence des envoies des discussions // les discussions qui ne donnent pas lieu � des convergence ne seront pas comptabilis�es.
		*/
		public ArrayList sentDiscussionFrequency = new ArrayList();
		
		public ArrayList sentDiscussionEstimatedCostList = new ArrayList(); // les couts estim� des discussions envoy�es
		public ArrayList sentDiscEstAccCostList = new ArrayList(); // les couts estim� acceot�s des discussions envoy�es
		
	//	public Hashtable discussionsProbability=new Hashtable(); 
		public Hashtable<String, String> discussionsProbability=new Hashtable<String, String>(); 
		
		/**
		 * Liste compteur des instances des actions locales.
		 */
		public int[] totalLocalActionsInstances;
		
		

			
		/** 
		 * Le Plan local d'un agent
		 *
		 */
		public AgentPlan localPlan = new AgentPlan(); 
		
		public Node rootNode = null;
		
		public boolean isSolWithResumption = false;
		
		// l'�tape courante
		public String currentStep = ""; 
		
		/**
		 * 
		 */
		
		public String experimentType = "Simulation avec poid des agents, Limite sur le cout dynamique, et augmentation de la limite en fonctions des negotiations";
		// Param�tres d'ex�cution : 
		//public int val[] = {5,2,40,3,1}; 
		//public int val[] = {5,2,10,10,2}; 
		// public int val[] ={2,3,10,7,4};  // apr�s 15 round
		// {w, eCost, rf, sf, conv}
		//public int val[] = {2,20,5,7,15}; 
		public int val[] = {2,6,3,4,9}; 
		// valorisationWeight(w) ; // valeur de valorisation du nombre des proposition re�ues par rapport aux propositions possibles.
		// valorisationRequestFrequency (rf) ; // 1 < frequence < 4 valeur de valorisation des fr�quences de reception des propositions par les agents.
		// valorisationConvergence (conv); // valeur de valorisation de l'indice des convergence des discussions.
		// valorisationCost (eCost) ; // valeur de valorisation du cout estim� des discussion.
		// valorisationSendFrequency (sf) ; // valeur du nombre d'envoie des propositions.
		public int discToSend = 0;//7; // L'identificateur de la discussion � envoyer. Si 0, alors envoyer celle selectionn�e par l'algorithme. si >0 alors envoy�e celle discToSend
		private int totalAgent = 2;
		private int maxRound = 10;  // nombre max de round � ex�cuter 
		private float costLimitPercentage = 90;
		
		private int nbrAgentsInSystem = 0; // total des agents dans le syst�me. Premi�re instance du processus uniquement.
		//private int GlobalStep = 41;  // l'etape globale  //12-1-2-3-6-7-15-19-25-32-33
		private int needPlansNbr = 1; // nombre de plan n�cessaires
		private int maxSendPlanCounter = 8; // le nombre max d'envoie de tentative d'envoie du plan, sinon, arreter l'agent.
		private int mainWaitingTime = 6000; //9000
		private int roundWaitingTime = 1000; //1000
		private int timeToWait = roundWaitingTime;
		private int sendProposalsWaitingTime = 7*roundWaitingTime; 
		private int maxAcceptMessageValidity = 0000 ;//+ sendProposalsWaitingTime; // 10000 // cette valeur doit �tre en fonction de roundWaitingTime
		private long lastSentAcceptMessageTimer = 0;
		private int timeWaitUpdateAgentsRelatedData = 100; //1000
		private int confirmationWaitingTime = 1000; //2000        // cette valeur doit aussi �tre en fonction de roundWaitingTime
		private int totalTimeIncrementationValue = 300;
		public static long allProcessNeededTime = 40000;//40000; // 50000
		private long newDiscussionWaitingTime = 0;
		
		
		private long timeLastOccuredEvent     = 0;
		private boolean loadStrategyFromFile = false;
		private int orderingStrategy = 1;   // 1 par Cout et 2 par distance, 3 Nbr Bifurcation d�croissant, 4 Nbr Bifrucation croissant
											// 5 : degr� d�croissant, 6 : degr� croissant
		
		private int startingTime = 0;
		
		// r�sultats discussions
		private int totalTacheTest = 0;
		private int totalAlternativeTest = 0;
		private float indiceTest = 0;
		private float indiceTestParTotalTache = 0;
		
		private float refCost = 0; 
		private float limitCost = 0; 
		private int selectedDisc = -1; 
		private float estimatedSelectedDiscCost = -1;  
		private float estimatedCostAfterAccept = -1; 
		private float selectedFinalCost = -1;   
		private float indiGainedCost = -1;      
		private float discLostGaint = -1;        
			
		private boolean updateDB = false; // si mise � jour sur base de donn�es.
		private boolean sysExit = true; // si quitter le syst�me apr�s FC
		private boolean displayComment = true; // si affichage des param�tres de la classe Constants
		private boolean printOnLogFile = true; // Si sauvegarde du Log sur un fichier.
		private boolean budgetLimit = true; // Si le bud0.get est limit�
		private boolean isActionRandomCost = false; // si le co�t des actions est g�n�r� de mani�re al�atoire.
		private boolean isActionWithExtraCost = true; // si le co�t des actions est g�n�r� avec un cout extra.
		private int resultType  = 0; // Type des r�sultats, 0 pas de solution, 1 r�alisation seul, 2 r�alisation collective
		private boolean excludeResultType1 = true; // si aexlusion des r�sultats de type 1 (confirmation envoy�es, mais aucune n'est re�ues en retour)
		private boolean reorderDiscussionByState = true; // r�ordonner la liste des doscussion pour chaque �tat ou juste au moment de la formation initiale.
		private boolean reorderDiscussionByState2 = true; // R�ordonner en fonction des couts
		private boolean preCoalition = true; // si les agents se comporteront avec une strat�gie de preCoalition pour forcer les autres a converger...
			
		
		private boolean colorSimulationTracking = true;
		
		// autres variables d'affichage:
		private boolean times = false;
		private boolean isVariables = false;
		private boolean needVariables = false;
		private boolean counters = false;
		private boolean discussionRelated = false;
		//12-1-2-3-6-7-15-19-25-32-33
		// ATTENTION, A REVOIR, Je fais augmenter le cout limite au niveau de la m�thode Update after proposal reception ...
		private float initialCostLimitPercentage = 0;
		private float percentageRate = (float) 0.5; 	// indice d'augmentation du  costLimitPercentage
		private int GlobalStep = 2;  // l etape globale  //12-1-2-3-6-7-15-19-25-32-33
	
		private int globalRound = 1;  // le round global du processus de formation de coalitions
		
		private int StepNbr = 0;  // le Nbr de l'exp�rience en cours
		private String agentCourant = "Agent1"; // l'agent qui m�ne les enregistrement globaux.
		
		private int altSentCounter = 0; // le nombre de fois ou la derni�re alternative etait envoy�e
		private int currentAltSent = 0; // l'identifiant de la derniere alternative envoy�e
		
		private float finalRealCost = 100000;
		private ArrayList finalRealCostListReceivedConfirmProposal = new ArrayList(); // liste des couts finaux voulus avoir
		private ArrayList estimatedCostListReceivedConfirmProposal = new ArrayList(); // liste des couts estim�s correspondants aux couts finaux voulus
	
		private ArrayList finalRealCostListUpdateAfterAcceptProposals = new ArrayList(); // liste des couts finaux voulus avoir
		private ArrayList estimatedCostListUpdateAfterAcceptProposals = new ArrayList(); // liste des couts estim�s correspondants aux couts finaux voulus
		
		//public Graph localPlan = null;
		private int step = 0;
		private int oldStep = 0;
		private int currentSentDiscId = 1000;  // identifiant de la discussion courante
		
		// la derni�re proposition envoy�e
		public int lastSentDiscId = 0;
		public Discussion lastSolDisc = null; // derni�re version trouv�e de la solution
		public ArrayList lastSendDiscProfile = new ArrayList();

		private int roundWhatToDoNext = 0; // compteur du nombre de passage sur What to Do next.
		private int globalNegotiationRound = 1; // Compteur des discussions � relancer (Proposition � envoyer)
		private AID[] friendAgents; // The agents whith who we have candidate coalitions 
		private MessageTemplate mtPlan; // The template to receive a plan
		private MessageTemplate mtCoalitionProposal; // The template to receive a proposition
		private MessageTemplate mtAcceptProposal; // The template to receive a proposal accept
		private MessageTemplate mtConfirm; // The template to receive a proposal confirmation
		private MessageTemplate mtRefusal; // The template to receive a refusal

		
		private ArrayList receivedRoundProposals = new ArrayList(); // les propositions re�ues � chaque round
		private ArrayList visitedActions = new ArrayList(); // Utilis�e pour le calcul des fr�quences des actions 
		
		private ArrayList receivedRoundProposalsTimes = new ArrayList(); // les propositions re�ues � chaque round - meme taille que receivedRoundProposals
		private ArrayList receivedProposals = new ArrayList(); // le total des propositions re�ues.
		

		private ArrayList receivedRoundAcceptsTimes  = new ArrayList();
		
		private ArrayList allMyPaths = new ArrayList(); // la liste de tous les sous ensembles de taches comunues entre agents.
		private ArrayList allPaths = new ArrayList(); 	// le nombre de paths dans le plan local : 
		private ArrayList agentsPlansList = new ArrayList(); // La liste de tous les plans des agents
		private ArrayList discussionList = new ArrayList(); // la liste des discussions
		private ArrayList<Integer> orderedDiscussionList = new ArrayList<Integer>(); // une liste ordon�e des position des discussions � engager dans les discussions
		private ArrayList subSetsList = new ArrayList(); // la liste des sous ensembles de tache comunes avec les agents.
		
		private ArrayList allCoalitionsList = new ArrayList(); // Liste de toutes les coalitions
		
		private File fileDiscussionProbability;
		public int globalDiscussionNbr = 1; // le nombre de fois ou un agent s'est engag� dans des negociations.
		public ArrayList firstDiscOrder = new ArrayList(); // le premier ordre des discussions
		public ArrayList firstDiscOrderCosts = new ArrayList(); // le premier ordre des couts des discussions
		
		private JPanel panel = new JPanel();

		
		// Variables sur les d�tails d'affichage des �tapes
	
		
		private Discussion lastValidDisc;
		
		PlanManagment planMgmt = new PlanManagment(""); // Classe gestion des plan
		BDConnect db = new BDConnect();
		Traitment Tr = new Traitment();
		Constants Ct = new Constants();

		private int existPanel = 0;
		
		private Viewer viewer;
		private View view;
		
		
		// Put agent initializations here
		protected void setup() {
			
			try {
				writeStatut(1); } catch (IOException e5) { e5.printStackTrace(); }
			
			try {
				Thread.sleep(1000);
				//Thread.sleep(wait);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			newDiscussionWaitingTime =  allProcessNeededTime;
			timeLastOccuredEvent = (long) System.currentTimeMillis();

			
			// Chargement de l'exp�rince en cours
			try {
				//resetStepNbr(planMgmt.agent);
				//StepNbr = loadStepNbr(planMgmt.agent);
				GlobalStep = loadGlobalStep();
			//	writeGlobalStep(planMgmt.agent, this.GlobalStep);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(loadStrategyFromFile)
				try {
					orderingStrategy =  loadStrategy();
				} catch (FileNotFoundException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				}
			
			
		
			
			int wait = ThreadLocalRandom.current().nextInt(1000, 10000);
			
			
			try {
				//Thread.sleep(mainWaitingTime);
				//Thread.sleep(wait);
				Thread.sleep(0);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			// Afficher le Log sur un fichier Log
			//-----------------------------------------
			if(printOnLogFile){
				PrintStream out = null;
				try { out = new PrintStream(new FileOutputStream("logs/"+GlobalStep+"_"+this.getLocalName()+"_Log.txt"));
					} catch (FileNotFoundException e2) { e2.printStackTrace();}
				System.setOut(out);
			}
			//-----------------------------------------
			
			// connection � la base de donn�es
			//db.Connect();
			
			// deux ex�cution possibles des agents. via le launcher ou la class run.
			//---------------------------------------------------------------------
			// Get the plan of the agent as a start-up argument
			Object[] args = getArguments();
			if (args != null && args.length == 2) { // cas d'une ex�cution par agentsContainer
				localPlan.graphPlan = (Graph) args[0];
				System.out.println("---------------->   Case 1");
			}else if(args != null && args.length >= 3){
				int agNbr = Integer.parseInt(((String) args[1]));
				planSet pSet = new planSet();
				
				try {
					localPlan.graphPlan = pSet.getPlanByNbr(agNbr);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				startingTime = Integer.parseInt(((String) args[2]));
				costLimitPercentage = Integer.parseInt(((String) args[3]));
				
				existPanel =  Integer.parseInt(((String) args[4]));
				
				maxRound = Integer.parseInt(((String) args[5]));
				
				GlobalStep = Integer.parseInt(((String) args[6]));
				
				if(existPanel == 1){ 
					panel = (JPanel) args[5];
				}
				
				//costLimitPercentage = 35;
				System.out.println( agNbr+ " ---------------->   Case 2   maxRound is "+maxRound);
			}else { // cas d'une ex�cution par run
					int agNbr = Integer.parseInt(((String) args[0]));
					planSet pSet = new planSet();
					
					
					try {
						localPlan.graphPlan = pSet.getPlanByNbr(agNbr);
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					System.out.println("---------------->   Case 3");
				}
			
					
			
						
				localPlan.agentOwner = 	this.getAID().getLocalName();
				
				//fileDiscussionProbability = new File("logs/"+GlobalStep+"_"+localPlan.agentOwner+"_"+globalDiscussionNbr+"_discussionsProbability.txt");
				fileDiscussionProbability = new File("logs/"+GlobalStep+"_"+localPlan.agentOwner+"_"+globalNegotiationRound+"_discussionsProbability.txt");
			
				
				// Ecraser l'ancien fichier pour r�initiliser l'ecriture. 
				try {
					PrintWriter fileProbaPrint = new PrintWriter(new FileWriter(fileDiscussionProbability));
				} catch (IOException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				}
				
				
				/*for(Node n:localPlan.graphPlan){
					n.setAttribute("ui.class", arg1);
				}*/
				
				this.rootNode = planMgmt.getRoot(localPlan.graphPlan);
				System.out.println("Node ID: ---------->"+rootNode.getId());
				rootNode.addAttribute("oldLabel", (String) rootNode.getAttribute("ui.label"));
				rootNode.setAttribute("ui.class", "negotiation");
				
				addPlanActionToList(localPlan.graphPlan); // ajout des actions du plan local � la liste globale des actions
				
				// Initialisation de la liste des instances des actions locales.
				totalLocalActionsInstances = new int[localPlan.graphPlan.getEdgeCount()];
				
				System.out.print("Profile Structure : <");
				int eNbr = 0;
				for(Edge e:localPlan.graphPlan.getEachEdge()){
					totalLocalActionsInstances[eNbr] = 1;
					System.out.print(e.getId()+", ");
				}
				System.out.println(">");
				planMgmt.agent = this.getAID().getLocalName();
					
		
				
			//	System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
			
				File f = new File("config/displayPlan.txt");
				Scanner sc;
				String display = "non";
				try {
					sc = new Scanner(f);
					display = sc.nextLine();
				} catch (FileNotFoundException e4) {
					// TODO Auto-generated catch block
					e4.printStackTrace();
				}
					
				if(display.equals("oui"))
				viewer = localPlan.graphPlan.display();
				
				// initialisation des co�ts des actions
				//planMgmt.initializeActionCostsList();
				try {
					planMgmt.putCostsOnGraph(localPlan.graphPlan);
				} catch (IOException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				}
		//		viewer.disableAutoLayout(); // d�sactiver Positionnement automatique des noeuds
				
				
				if(existPanel == 1){					
					viewer = new Viewer(localPlan.graphPlan, Viewer.ThreadingModel.GRAPH_IN_GUI_THREAD);
					viewer.enableAutoLayout();
					// ...
					view = viewer.addDefaultView(true);   // false indicates "no JFrame".
					
					view.getCamera().setViewCenter(10, 1, 100);
					view.getCamera().setViewPercent(5.5);
					view.getCamera().resetView();
					//view.getCamera().setAutoFitView(true);
					panel.add((Component) view);
					//view.getCamera().resetView();
					
					
				}
				
				
				
			  // Enregistrement du service dans les pages jaunes
			  // -----------------------------------------------
				
				
			
				
				DFAgentDescription dfd = new DFAgentDescription();
				dfd.setName(getAID());
				ServiceDescription sd = new ServiceDescription();
				sd.setType("form-coalition");
				sd.setName("JADE-form-coalition");
				dfd.addServices(sd);
				try {
					System.out.println("Agent registered"+this.getAID());
					
					DFService.register(this, dfd);
					
					
				}
				catch (FIPAException fe) {
					fe.printStackTrace();
				}
				
				
				
				// Calculer les Alternatives
				planMgmt.getAllPaths(localPlan.graphPlan, new SingleGraph("PathByStart_1"), allPaths, planMgmt.getRoot(localPlan.graphPlan),  planMgmt.getGoal(localPlan.graphPlan));
				
				
			
				
				
				
				System.out.println("The total number of alternatives in my local plan is "+allPaths.size() );
				//planMgmt.displayAlternatives(allPaths, 2);
				//planMgmt.displayAlternatives(allPaths);
					
				localPlan.pathList = allPaths; 
				localPlan.altCount  = 	allPaths.size();			
			
				Ct.timeSinceLastEvent = System.currentTimeMillis();
			
				
				wait = ThreadLocalRandom.current().nextInt(1000, 6000);
				
				printSubStepOnRoot("Attente Initialisation");
				
				try {
					Thread.sleep(mainWaitingTime+startingTime);
					//Thread.sleep(wait);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				timeLastOccuredEvent = (long) System.currentTimeMillis() ;
				
				
				
		// Add a TickerBehaviour that schedules a check for present agents in the system every minute
		//-------------------------------------------------------------------------------------------
			
		addBehaviour(new CyclicBehaviour(this) {
	
		public void action() {
					
					
		switch (step) {
					
		//----------------------------------------------------------------------------------------------------------------------
			
			case 0: // Envoyer le Plan aux agents. 
					//timeLastOccuredEvent = (long) System.currentTimeMillis() ;
					currentStep = "Envoyer les plans " ;
					printStepOnRoot();
					displayStep(0, getAgent().getLocalName()+" -*> "+" Envoyer le Plan aux agents - sendPlan()");
					Ct.resetParameters();
				try {
					sendPlan(myAgent);
				} catch (IOException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				}
					oldStep = 0;
					
					timeToWait = roundWaitingTime;
				
					timeToWait = 5000;
					//timeToWait = 50000;
					step = Ct.whatToDoNextStep;
				break;
				
		//----------------------------------------------------------------------------------------------------------------------
		
			case 1:  // Reception des plans 
					//timeLastOccuredEvent = (long) System.currentTimeMillis() ;
					currentStep = "R�ception des plans" ;
					printStepOnRoot();
					displayStep(1, getAgent().getLocalName()+" -*> "+"Reception des plans - planReception()");
					planReception (myAgent);
					oldStep = 1;
					timeToWait = roundWaitingTime+1000;
					step = Ct.whatToDoNextStep;
				break;
		//----------------------------------------------------------------------------------------------------------------------

			case 2: // Traiter les plans  -- former les propositions. 
					//timeLastOccuredEvent = (long) System.currentTimeMillis() ;
					currentStep = "Traitement des plans" ;
					printStepOnRoot();
					displayStep(2, getAgent().getLocalName()+" -*> "+"Traiter les plans  -- Former les propositions - formCoalitionsProposal()");	
					try {
						
						formCoalitionsProposal(myAgent);
					
					} catch (FileNotFoundException e2) {e2.printStackTrace();} catch (FIPAException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					oldStep = 2;
					timeToWait = roundWaitingTime;
					step = Ct.whatToDoNextStep;
				break;
		
		//----------------------------------------------------------------------------------------------------------------------
		
			case 3: // Envoyer les premi�res propositions
					//timeLastOccuredEvent = (long) System.currentTimeMillis() ;
					currentStep = "Envoyer les Propositions" ;
					System.out.println("->> Le test est: "+getAgent().getLocalName()+" -*>  counter : "+planMgmt.counter+" - "+planMgmt.agent); 
					printStepOnRoot();
					displayStep(3, getAgent().getLocalName()+" -*> "+"Envoyer les propositions pour une alternative donn�e - sendAllProposalsForAlternative()");	
	
					//try { DFService.deregister(myAgent); } catch (FIPAException e2) { e2.printStackTrace(); }
					
				try {
					
					sendAllProposalsForAlternative(myAgent);
				} catch (FileNotFoundException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					oldStep = 3;
					timeToWait = sendProposalsWaitingTime;
					step = Ct.whatToDoNextStep;
				break;

		//----------------------------------------------------------------------------------------------------------------------
	
			case 4: // r�ception des propositions  
					//timeLastOccuredEvent = (long) System.currentTimeMillis() ;
					currentStep = "R�ception des propositions" ;
					printStepOnRoot();
					displayStep(4, getAgent().getLocalName()+" -*> "+"r�ception des propositions - receiveProposals() ");
				
					receiveProposals(myAgent);
					oldStep = 4;
					timeToWait = roundWaitingTime;
					step = Ct.whatToDoNextStep;
				break;

						
		//----------------------------------------------------------------------------------------------------------------------
		
			case 5: // Enregistrement des propositions re�ues.    
					//timeLastOccuredEvent = (long) System.currentTimeMillis() ;
					currentStep = "Enregistrement des propositions" ;
					printStepOnRoot();
					displayStep(5, getAgent().getLocalName()+" -*> "+"Enregistrement des propositions re�ues - updateStateAfterProposalsReception() ");
					
					updateStateAfterProposalsReception(myAgent);
					oldStep = 5;			
					timeToWait = roundWaitingTime;
					step = Ct.whatToDoNextStep;
				break;

							
		//----------------------------------------------------------------------------------------------------------------------
							
			case 7: // Evaluer les propositions re�ues  
					//timeLastOccuredEvent = (long) System.currentTimeMillis() ;
					currentStep = "Evaluer les propositions" ;
					printStepOnRoot();
					displayStep(7, getAgent().getLocalName()+" -*> "+"Evaluer les propositions re�ues - evaluateReceivedProposals() ");
				
							try {
								evaluateReceivedProposals(myAgent);
							} catch (IOException e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
							}
					oldStep = step;
					timeToWait = roundWaitingTime;
					step = Ct.whatToDoNextStep;

				break;
		
		//----------------------------------------------------------------------------------------------------------------------
							
			case 8: // What to do  // Evaluation de la prochaine �tape � suivre
				/*
					if(roundWhatToDoNext>105)
						myAgent.doSuspend();
					*/
				//	printStepOnRoot("Quoi faire?");
					//timeLastOccuredEvent = (long) System.currentTimeMillis() ;
					displayStep(8, getAgent().getLocalName()+" -*> "+" What to do  Next - whatToDoNext() ");
					//oldStep = 8;
					//timeToWait = roundWaitingTime;
					step = whatToDoNext(myAgent);
					
					if(displayComment)				
					Ct.printState("Etat -> "+roundWhatToDoNext, times, isVariables, needVariables, counters, discussionRelated);
					
					roundWhatToDoNext++;
					
				
					
					if(globalNegotiationRound>=maxRound ) step = Ct.endCoalitionForlmationProcessStep;
					System.out.println(" --------->>  Negotiation Round : --->>>   "+globalNegotiationRound);
					System.out.println(" --------->>  roundWhatToDoNext Round : --->>>   "+roundWhatToDoNext);
				break;
		
		//----------------------------------------------------------------------------------------------------------------------
											
			case 9: // sending accept proposal to the agents, and asking them the same.
					//timeLastOccuredEvent = (long) System.currentTimeMillis() ;
					currentStep = "Envoyer les acceptations" ;
					printStepOnRoot();
					displayStep(9, getAgent().getLocalName()+" -*> "+"sending accept proposal - sendAcceptProposal()");
					
				try {
					sendAcceptProposal (myAgent);
				} catch (IOException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				}
					oldStep = 9;
					timeToWait = roundWaitingTime;
					step = Ct.whatToDoNextStep;
				break;
		
		//----------------------------------------------------------------------------------------------------------------------
		
			case 10: // Traitement des acceptations re�ues.  
					//timeLastOccuredEvent = (long) System.currentTimeMillis() ;
					currentStep = "Traitement des acc�ptations" ;
					printStepOnRoot();
					displayStep(10, getAgent().getLocalName()+" -*> "+"Traitement des acceptations re�ues - receiveAcceptProposal()");
					
					receiveAcceptProposal(myAgent);
					oldStep = 10;
					timeToWait = roundWaitingTime;
					step = Ct.whatToDoNextStep;
			break;
		
		//----------------------------------------------------------------------------------------------------------------------
		
			case 16: // Envoyer les messages de Confirmation
					//timeLastOccuredEvent = (long) System.currentTimeMillis() ;
					currentStep = "Envoyer les confirmations" ;
					printStepOnRoot();
					displayStep(16, getAgent().getLocalName()+" -*> "+" Envoyer les Confirmations - sendConfirmProposal()");
				
				try {
					sendConfirmProposal(myAgent);
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
					
					
				//	try { Thread.sleep(confirmationWaitingTime); } catch (InterruptedException e1) { e1.printStackTrace(); }
					oldStep = 16;
					timeToWait = roundWaitingTime;
					step = Ct.whatToDoNextStep;
					
				break;
			
		//----------------------------------------------------------------------------------------------------------------------
		
			case 17: // R�ception et traitement des Confirmations 
					//timeLastOccuredEvent = (long) System.currentTimeMillis() ;
					currentStep = "R�ception des confirmations" ;
					printStepOnRoot();
					displayStep(17, getAgent().getLocalName()+" -*> "+"R�ception et Traitement des Confirmations - receivedConfirmProposal() ");
					// � la place de cette instruction, il faut impl�menter une boucle qui s'assurera de la reception des confirmations attendu.
					
					// try { Thread.sleep(1000); } catch (InterruptedException e1) { e1.printStackTrace(); }
					System.out.println(" --> Attente des confirmation Time : "+confirmationWaitingTime);
					
					// if((myAgent.getAID().getLocalName().equals("Agent1")) || (myAgent.getAID().getLocalName().equals("Agent4")) )
					
					printSubStepOnRoot("Attente r�ception confirm");
					
					try { Thread.sleep(confirmationWaitingTime); } catch (InterruptedException e1) { e1.printStackTrace(); }
					
					
					
				try {
					receivedConfirmProposal(myAgent);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
					oldStep = 17;
					timeToWait = roundWaitingTime + confirmationWaitingTime;
					step = Ct.whatToDoNextStep;
				
				break;
							
		//----------------------------------------------------------------------------------------------------------------------
		
			case 11: // Fin du process de formation de coalitions
				
					//timeLastOccuredEvent = (long) System.currentTimeMillis() ;
					currentStep = "Fin du processus" ;	
					printStepOnRoot();
					displayStep(11, getAgent().getLocalName()+" -*> "+"Fin du process de formation de coalitions - endingCoalitionFormationProcess () ");
					
					try {
							//endingCoalitionFormationProcess(myAgent);
							endingCoalitionFormationProcessWithResumption(myAgent);
						} catch (IOException | SQLException | FIPAException | InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					oldStep = 11;
					timeToWait = roundWaitingTime;
					step = Ct.whatToDoNextStep;
				break;
		
		//----------------------------------------------------------------------------------------------------------------------
																	
			case 12: // R�ception des messages ACCEPT_PROPOSAL receiveAcceptProposalsStep
					
					//timeLastOccuredEvent = (long) System.currentTimeMillis() ;
					currentStep = "";
					displayStep(12, getAgent().getLocalName()+" -*> "+" To review !!!??? R�ception des messages ACCEPT_PROPOSAL receiveAcceptProposalsStep");
					oldStep = 12;
					timeToWait = roundWaitingTime ;
					step = Ct.whatToDoNextStep;												
				break;
					
		//----------------------------------------------------------------------------------------------------------------------
																				
			case 13: // Mise � ajour apr�s reception des messages ACCEPT_PROPOSAL 
					
					//timeLastOccuredEvent = (long) System.currentTimeMillis() ;
					currentStep = "Update Acceptations" ;		
					printStepOnRoot();
					displayStep(13, getAgent().getLocalName()+" -*> "+" Mise � ajour apr�s reception des messages ACCEPT_PROPOSAL - updateAfterAcceptProposals() (Not yet completed)");
					
				try {
					updateAfterAcceptProposals(myAgent);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					oldStep = 13;
					timeToWait = roundWaitingTime ;
					step = Ct.whatToDoNextStep;
					
				break;

		//----------------------------------------------------------------------------------------------------------------------
																		
			case 14: // Traitement des Plans re�us 
					//timeLastOccuredEvent = (long) System.currentTimeMillis() ;
					currentStep = "";													
					displayStep(14, getAgent().getLocalName()+" -*> "+"Traitement des Plans re�us !! (Not yet completed)");
					step = Ct.roundEnd;
					oldStep = 14;
					timeToWait = roundWaitingTime ;
					step = Ct.whatToDoNextStep;
				break;
		//----------------------------------------------------------------------------------------------------------------------
				
			case 18: // relancer le process de formation de coalitions 
					//timeLastOccuredEvent = (long) System.currentTimeMillis() ;
					currentStep = "Relancer le processus";	
					printStepOnRoot();
					displayStep(18, getAgent().getLocalName()+" -*> "+"Relancer le process de formation de coalitions - restartTheProcess() ");
					
					
				try {
					restartTheProcess(myAgent);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					oldStep = 18;
					timeToWait = roundWaitingTime ;
					step = Ct.whatToDoNextStep;
				break;
		
		//----------------------------------------------------------------------------------------------------------------------
		
			case 19: // Entamer une nouvelle discussion en envoyant des propositions  
				//timeLastOccuredEvent = (long) System.currentTimeMillis() ;
				currentStep = "Entamer nouvelle discussion" ;
				printStepOnRoot();
				displayStep(19, getAgent().getLocalName()+" -*> "+"Entamer une nouvelle discussion, et envoyer de nouvelles propositions - startNewDiscussion()");
				
				startNewDiscussion(myAgent);
				oldStep = 19;
				timeToWait = 600 ;
				step = Ct.whatToDoNextStep;
			break;
	
		//----------------------------------------------------------------------------------------------------------------------
			
				
				
			case 25: // Traitement des Plans re�us 
				//	timeLastOccuredEvent = (long) System.currentTimeMillis() ;	
					currentStep = "Fin" ;													
					displayStep(25, getAgent().getLocalName()+" -*> "+"Fin du Round "+globalRound);
					step = Ct.sendPlanStep; // qui n'existe pas. donc, fin de la boucle.
					oldStep = 25;
					timeToWait = roundWaitingTime ;
					step = Ct.whatToDoNextStep;
				break;
			}
						
		} // fin de action
									
		});
	
	}

	// Put agent clean-up operations here
	protected void takeDown() {
		// Printout a dismissal message
		System.out.println("Agent "+getAID().getName()+" terminating.");
	}

	
		
//-------------------------------------------------------------------------		
//*************************************************************************
//-------------------------------------------------------------------------
	/**
   Inner methode senPlan.
   This is the methode used by agents to send the plan.
	 * @throws IOException 
 */
private void sendPlan (Agent myAgent) throws IOException{

		Ct.sendPlanCounter++;
		
		// System.out.println("Looking for agents and Trying to form coalitions to perform my plan "+localPlan.graphPlan.getId());
		
		
		String agentsStrList[] = null;
		
		
		agentsStrList = getAgentsInSystem(myAgent);
		// mise � jour des donn�es relatives aux ensemble des agents pr�sents dans le syst�me (si des agents on quitt� le syst�me.)
		if(agentsStrList.length>0){
			printSubStepOnRoot("Update Data agents sortants");
			boolean hasBeenChanged = updateAgentsRelatedData(agentsStrList);
			// r�initialiser la liste des discusion envoy�es des agents ont quitt�s le syst�mes.
			if(hasBeenChanged) {
				System.out.println("des agents ont quitt�s le syst�me ... vider la liste des discussions envoy�es (R�initialisation)");
				
				System.out.println("A remettre sur vider la liste des alternatives envoy�es. ne pas vider est juste un test");
				
				//sentDiscussionList.clear();
				 
			}
		}
		
		
		
	if(nbrAgentsInSystem == 0) nbrAgentsInSystem = 	agentsList.length;
		
	// Send the (Plan) to all agents
	int NewAgent= 0;
	ACLMessage sendPlan = new ACLMessage(ACLMessage.PROPAGATE);
	System.out.print("Found the agents : ");
	
	for (int i = 0; i < agentsList.length; ++i) {
		if((!agentsHaveMyPlan.contains(agentsList[i].getLocalName())) && (!agentsList[i].getName().equals(myAgent.getName()))){
				sendPlan.addReceiver(agentsList[i]);
				agentsHaveMyPlan.add(agentsList[i].getLocalName());
				System.out.print(" - "+agentsList[i].getLocalName()+" ");
				NewAgent = 1;
			} // fin de if
		
		// Ajouter une liste d'actions � la liste : actionsFrequencyByAgent
		ArrayList actionList =  new ArrayList();
		
		for(Edge e:localPlan.graphPlan.getEachEdge()){
			actionList.add(0);
		}
		
		actionsFrequencyByAgent.add(actionList);
		
	} // fin de for
	
	
	
	System.out.println(" -> let's send them my Local plan ");
		if(NewAgent ==1){ // if there is an agent who hasn't my plan
			
			try {						
				planMessage  pMsg = planMgmt.convertToPlanMessageObject(localPlan.graphPlan); // convert the plan on a suitable message to send
				sendPlan.setContentObject(pMsg);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			sendPlan.setConversationId("send-plan");
			sendPlan.setReplyWith("propagate"+System.currentTimeMillis()); // Unique value
			myAgent.send(sendPlan);
		
			//System.out.println("Plan has been sent to : "+ agentsHaveMyPlan.toString());
		
			// Prepare the template to get proposals
			mtPlan = MessageTemplate.and(MessageTemplate.MatchConversationId("send-plan"),
					MessageTemplate.MatchInReplyTo(sendPlan.getReplyWith()));
			
			// Mise � jour des param�tres d�tat : 
			//-----------------------------------
			Ct.isHavingPlansToLookFor = true;
			Ct.isHavingProposalToWaitFor = true;
			Ct.updateAfterAcceptProposalsCounter = 0;
			Ct.timeSinceLastEvent = System.currentTimeMillis();
			
		} // fin de if sur l'existance d'un nouvel agent.
		
}  // End of inner methode sendPlan
		
//-------------------------------------------------------------------------		
//*************************************************************************
//-------------------------------------------------------------------------




//-------------------------------------------------------------------------		
//*************************************************************************
//-------------------------------------------------------------------------
/**
	   Inner methode planReception.
	   This is the methode used by the agents to recieve incoming  messages  
	   containing a plan from other agents.
	   
	 */
private void planReception (Agent myAgent){
	Ct.receivePlansCounter++;
	
	MessageTemplate mtPlan = MessageTemplate.MatchPerformative(ACLMessage.PROPAGATE);
	ACLMessage msg = myAgent.receive(mtPlan);
	ArrayList totalTasks = new ArrayList();
	ArrayList totalAgentPlanList = new ArrayList();
	
	for(Edge ed:localPlan.graphPlan.getEdgeSet()){
		if(!ed.getId().equals("startK"))
		if(!totalTasks.contains(ed.getId())){
			totalTasks.add(ed.getId());
		}			
	}
	
	while(msg != null) {
		// Plan PROPAGATION Message received. Process it
		try {
			planMessage receviedPlanMsg = (planMessage) msg.getContentObject();
			Graph receviedPlan  =  planMgmt.convertToGraph(receviedPlanMsg);
			
			for(Edge ed:receviedPlan.getEdgeSet()){
				if(!ed.getId().equals("startK"))
				if(!totalTasks.contains(ed.getId())){
					totalTasks.add(ed.getId());
				}			
			}
			
			// ajout des actions du plan re�u � la liste globale des actions
			addPlanActionToList(receviedPlan); 
			// mise � jour compteur des instances
			countLocalActionsInstances(receviedPlan);
			
			System.out.println("A new Plan ("+receviedPlan+") received from agent : "+msg.getSender().getLocalName());
			//System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
			receviedPlan.addAttribute("ui.stylesheet", "url('file:config/Modele1.css')");
			
			// Viewer viewer = receviedPlan.display();
			//------------------------------------
			
			// le nombre de paths dans le plan Re�u : 
			ArrayList allPathsReceviedPlan = new ArrayList();
			// Collecter les Alternatives
			
			//System.out.println("--> Root du plan Re�u est : " + planMgmt.getRoot(receviedPlan).getId());
		
			// Calcul des chemins possibles sur le plan re�u.
			planMgmt.getAllPaths(receviedPlan, new SingleGraph("ReceivedPathByStart_1"), allPathsReceviedPlan, planMgmt.getRoot(receviedPlan),  planMgmt.getGoal(receviedPlan));
			
			
			// Stokage du plan re�u dans la liste des plans de tous les agents.
			AgentPlan agPlan = new AgentPlan(msg.getSender().getLocalName(), receviedPlan, allPathsReceviedPlan);
				
			agentsPlansList.add(agPlan); // Ajout � la liste des plans
			totalAgentPlanList.addAll(agPlan.pathList);
								
			//System.out.println("the total number of alternatives in the received plan from agent : "+agPlan.agentOwner.getLocalName()+" is "+allPathsReceviedPlan.size() );
			//planMgmt.displayAlternatives(allPathsReceviedPlan, 2);
			//planMgmt.displayAlternatives(allPathsReceviedPlan);
			
		
			// calcul de toutes les alternative du plan local
			planMgmt.getAllPaths(localPlan.graphPlan, new SingleGraph("PathByStart_1"), allMyPaths, planMgmt.getRoot(localPlan.graphPlan),  planMgmt.getGoal(localPlan.graphPlan));
			

		
		// Remplissage de la liste des sous ensembles de taches communes entre MyAgent et les autres agents
		
			
			//totalPaths.addAll(allPathsReceviedPlan);
			
			for(int i=0; i<allMyPaths.size(); i++){
				for(int j=0; j<allPathsReceviedPlan.size();j++){
					ArrayList list = planMgmt.constructSubSets((Graph) allMyPaths.get(i), (Graph) allPathsReceviedPlan.get(j));
					
				//	System.out.println("ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZ");
				//	System.out.println("allPathsReceviedPlan.get : "+j);
				//	System.out.println("Agent  "+msg.getSender().getLocalName());
				//	System.out.println("My Alt "+i);
				//	System.out.println("it's Alt "+j);
				//	System.out.println("List "+list.toString());
					
					subSets sub = new subSets(msg.getSender().getLocalName(), i, j, list);
					if(!sub.edgeList.isEmpty())subSetsList.add(sub);
					
					
					
				} // fin de for
			} // fin de for
			
			
			
		 /** do Not Delete	
			System.out.println(" --> Il existe "+subSetsList.size()+" sous ensembles avec "+msg.getSender() );
			for(int i=0; i<subSetsList.size(); i++){
				
				subSets sub = (subSets) subSetsList.get(i);
				System.out.println(" Agent : "+sub.agent);
				System.out.println(" My Alternative : "+sub.myAlternative);
				System.out.println(" Agent Alternative : "+sub.agentAlternative);
				System.out.println(" List des edges : ");
				
				for(int j=0; j<sub.edgeList.size(); j++){
					System.out.println(" Edge : "+sub.edgeList.get(j).toString());
				} // fin de for
			
			} // fin de for
			
			**/
			//------------------------------------
			
			msg = myAgent.receive(mtPlan); //  r�ception d'un nouveau message.
			
	} catch (UnreadableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		// Mise � jour des param�tres d�tat : 
		//-----------------------------------
		Ct.isHavingPlansToLookFor = false;
		Ct.isNewProposalsToForm = true;
		Ct.isTherePlansToProcess = true;
		Ct.timeSinceLastEvent = System.currentTimeMillis();
			
		
	} // fin de if
	
	
	


	//--------------------------------------------------------------------------
	//--------------------------------------------------------------------------
	System.out.println("-----> Calcul de l'indice de concordance .... ");
	
	ArrayList equivalentTasks = new ArrayList();
	
	

	float freq = 0;
	for(int i=0; i<localPlan.pathList.size(); i++){
		Graph path1 = (Graph) localPlan.pathList.get(i);
		float freq1 = 0;
		
		for(int j=0; j<totalAgentPlanList.size();j++){
				Graph path2 = (Graph) totalAgentPlanList.get(j);
				ArrayList unionTask = new ArrayList();
				float freq2 = 0;
				for(Edge ed:path1.getEdgeSet()){
					if(!ed.getId().equals("start")){
						if(!unionTask.contains(ed.getId())){
							unionTask.add(ed.getId());
						}
						for(Edge ed2:path2.getEdgeSet()){
							if(!ed2.getId().equals("start")){
								if(!unionTask.contains(ed2.getId())){
									unionTask.add(ed2.getId());
								}
								if(ed.getId().equals(ed2.getId())){
									freq2++;
								}
							}
						}
					}
					
				}
		
			//System.out.println("-----> freq2 : "+freq2);
			if(unionTask.size()>0){
				freq1 += (float) freq2 / unionTask.size();  
			}			
		} // fin de for
		//System.out.println("-----> freq1 : "+freq1);
		freq += (float) freq1; 
	} // fin de for
	
	System.out.println("-----> Le total des taches est : "+totalTasks.size());
	System.out.println("-----> Le total des agents : "+totalAgent);
	System.out.println("-----> Le total des alternative Locales : "+localPlan.pathList.size());
	System.out.println("-----> Le total des autres alternatives (sans localles) : "+(totalAgentPlanList.size()+localPlan.pathList.size()));
	
	System.out.println("-----> Le total totalAgentPlanList : "+totalAgentPlanList.size());
	
	
	System.out.println("-----> L'indice est : "+freq);
	indiceTest = freq;
	//freq = (float) freq / (float) (totalTasks.size() * totalAgent); 
	freq = (float) freq / (float) (totalTasks.size()); 
	System.out.println("-----> (float) freq / totalTasks.size() : "+freq);
	
	
	//private int totalAgent = 4;
	if(totalTacheTest == 0) totalTacheTest = totalTasks.size();
	if(totalAlternativeTest == 0) totalAlternativeTest = (totalAgentPlanList.size()+localPlan.pathList.size());
	if(indiceTestParTotalTache == 0)  indiceTestParTotalTache = freq;

	
	//---------------------------------------------------------------------------
	//--------------------------------------------------------------------------



		
	
}  // End of inner methode planReception
//-------------------------------------------------------------------------		
//*************************************************************************
//-------------------------------------------------------------------------	









	
//-------------------------------------------------------------------------		
//*************************************************************************
//-------------------------------------------------------------------------
/**
*  This methode is used by agents to send all the Proposals.
 * @throws IOException 
*/
private void sendAllProposalsForAlternative(Agent myAgent) throws IOException{
	Toolkit.getDefaultToolkit().beep();
	
	
	boolean needResumption = true; 
	Ct.sendProposalsCounter++;
	
	if(globalNegotiationRound < maxRound){
		globalNegotiationRound++;
	}
	
if(globalNegotiationRound < maxRound){
	
	 String agentsStrList[] = getAgentsInSystem(myAgent);
	// mise � jour des donn�es relatives aux ensemble des agents pr�sents dans le syst�me (si des agents on quitt� le syst�me.)
	if(agentsStrList.length>0){
		printSubStepOnRoot("Update Data agents sortants");
		
		boolean hasBeenChanged = updateAgentsRelatedData(agentsStrList);
		// r�initialiser la liste des discusion envoy�es des agents ont quitt�s le syst�mes.
		if(hasBeenChanged){ 
			System.out.println("des agents ont quitt�s le syst�me ... vider la liste des discussions envoy�es (R�initialisation)");
			sentDiscussionList.clear();
			sentDiscussionFrequency.clear();
			sentDiscussionEstimatedCostList.clear();
			sentDiscEstAccCostList.clear();
			
		}
	}
	
	
	//r�ordonner l'ordre des discussions dans la liste discussionList
	if(roundWhatToDoNext>5)  // ne pas r�ordonner par priorit� durant le premier round
	 reorderDiscussionsPriority(reorderDiscussionByState, reorderDiscussionByState2);
		
	// mise � jour de l'ordre des discussions et param�tres sur les param�tres  Ct.currentAlternative et Ct.currentAlternativeSent
	// updateDiscussionOrder(); 
	// TODO -------------------------------------

	
	Ct.currentAlternative = 0;
	Ct.currentAlternativeSent = 0;

/////////////////////////////////////////
	/////////////////////////////
//	if(myAgent.getAID().getLocalName().equals("Agent28")  ) Ct.currentAlternative = 1;
//	if(myAgent.getAID().getLocalName().equals("Agent33")  ) Ct.currentAlternative = 3;
	
	/**	
	System.out.println("Before ====================================== ");
	System.out.println("areAllAlternativesSent : " + areAllAlternativesSent);
	System.out.println("alternativesCount : "+alternativesCount);
	System.out.println(" currentAlternativeSent : "+currentAlternativeSent);
	System.out.println("currentAlternative : "+currentAlternative);
	System.out.println("=======================================");
*/	
	
	
	// V�rification de la convergence parfaite 
	//----------------------------------------
	//----------------------------------------
	
	// si exactement la meme entre les deux agents : 
	//----------------------------------------------
	// R�cup�rer le nouveau profil
	
	if(preCoalition){
		boolean existPreCoalition = false; // si l'agent courant est en convergence parfaite avec au moin un des agents
		// � consid�rer apr�s, deux, trois, ... , n agents.
		for(int p=0; p<newAgentsOrder.size(); p++){
			String agent3 = (String) newAgentsOrder.get(p);
			ArrayList newProfile1 = (ArrayList) newAgentsPropoProfile.get(p);
			float lastAltSentFprint = Tr.computeAgentFootPrint(lastSendDiscProfile, newProfile1);
			
		//	System.out.println("-> -> lastAltSentFprint : "+lastAltSentFprint);
			
			if((float) (lastAltSentFprint - (Math.floor(lastAltSentFprint))) == 0){
				existPreCoalition = true;
		//		System.out.println("-> -> Existane de convergence parfaite avec agent : "+agent3);
				break;
			}	
			
		}
		
		if(existPreCoalition){
			if(sentDiscussionList.size()>=3){
				int tailleFile = sentDiscussionList.size(); // � d�finir apres en fonction du nombre d'agents dans le syst�me
				if(!( (sentDiscussionList.get(tailleFile-1) == sentDiscussionList.get(tailleFile-2) 
						&&
						(sentDiscussionList.get(tailleFile-1) == sentDiscussionList.get(tailleFile-3))
						//&&
						//(sentDiscussionList.get(tailleFile-1) == sentDiscussionList.get(tailleFile-4))
						))){
					
					System.out.println(" Mais Existance de convergence parfaite, Modification de la valeur de indConv : indConv = 1");
					for(int u=0; u<discussionList.size(); u++){
						Discussion di = (Discussion) discussionList.get(u);
						if(di.discussionId == lastSentDiscId ){
							Ct.currentAlternative = u;
							break;
						}
					}
					
				}
					
			}
			
		}
	}
	//------------------------------------------
	//------------------------------------------
	
	
	//System.out.println(" Time to see if I have new proposals to send !! ");
   // if((Ct.isOkayToSendProposals) && !(Ct.areAllAlternativesSent) && (discussionList.size()>Ct.currentAlternative)){
    	
    	if(Ct.isOkayToSendProposals){
	
	    if(Ct.alternativesCount>0){ // si au moins une alternatives peut �tre profitable.
	    	

				System.out.println(" Yes I have proposals to send !!!! ");
							
				// Send all the (Proposals) to the agents
				
			
				// TO Delete
				
			//	if(roundWhatToDoNext>1)
				
				if(discToSend!=0)
				for(int h=0; h<discussionList.size(); h++){
					Discussion dd = (Discussion) discussionList.get(h);
					if(dd.discussionId == discToSend)
						Ct.currentAlternative =h;
				}
				
				
				for (int ii = 0; ii < discussionList.size(); ii++) {
					Discussion disci = (Discussion) discussionList.get(ii);
					disci.discRank.add(ii);
				}
				
				Discussion disc = (Discussion) discussionList.get(Ct.currentAlternative);
				Ct.currentAlternativeSent = Ct.currentAlternative;
				
				needResumption = disc.checkNeedResumption();
				
				currentSentDiscId =  disc.discussionId;
								
				if(sentDiscussionList.size()>=1){
					int id1 = (int) sentDiscussionList.get((sentDiscussionList.size()-1));
					
					if(id1 == disc.discussionId) sentDiscussionFrequency.add(disc.discussionId);
										
				}
				
				sentDiscussionList.add(disc.discussionId);
				
				
				
				lastSendDiscProfile.clear();
				
				//if(sentDiscussionList.size()==2) myAgent.doSuspend();
				
				for(int f=0; f<disc.discussionProfil.size(); f++){
					int el = (int) disc.discussionProfil.get(f);
					if(el == 1){
						lastSendDiscProfile.add(1);
					}else{
						lastSendDiscProfile.add(0);
					}
				}
				
			//	lastSendDiscProfile = (ArrayList) disc.discussionProfil.clone(); // garder le profil de la derni�re proposition envoy�e.
				
				lastSentDiscId = disc.discussionId;
				
				System.out.println(" 1---->> Sending All the Proposals at Alternative  ID ("+disc.discussionId+") : "+Ct.currentAlternative);
				System.out.println(" ---->> Alternative Profile : "+disc.discussionProfil.toArray());
				
				disc.evaluateEstimatedCostForPropose(localPlan.graphPlan);
				System.out.println("Estimated Cost: "+disc.discussionEstimatedCost);
				System.out.println("Reference Cost: "+Ct.referenceCost);
				
				sentDiscussionEstimatedCostList.add(disc.discussionEstimatedCost);
				
				if(colorSimulationTracking)
				disc.printPathInBlue(localPlan.graphPlan);
				
			
				
				
				//-------------------------
				// Incr�mentation du nombre d'alternatives trait�es. cela doit �tre revu pour permettre de faire le parcours de toutes les alternatives.
				
				//-----------------------
				for(int i=0; i<disc.coalitionList.size(); i++){
						
					Coalition c = (Coalition) disc.coalitionList.get(i);
					
					// il faut envoyer la propoisition � chaque fois, sinon, avec le temps, l'agent recepteur ne pourra pas savoir qu'elle est
					// la coalition que l'agent encours veut r�aliser
					if(c.sent){ // le cas true, peut �tre qu'il faut voir ce qu'il peut �tre fait en plus ???
						c.sent = false;
					}
					
					
							ACLMessage sendAllProposals = new ACLMessage(ACLMessage.PROPOSE);
						
							// ajout de la list des agents au messge
							String receivers = "";
							ArrayList receiverTempList = new ArrayList();
							for(int j=0; j<c.agentList.size(); j++){
								String ag = (String) c.agentList.get(j);
								
								if(!receiverTempList.contains(ag))
								if(!c.agentList.get(j).equals(myAgent.getAID().getLocalName())){
									sendAllProposals.addReceiver(getAgentAIDfromString(ag));
									
									receiverTempList.add(ag);
									
									receivers+= ag+ " - ";
								} // fin de if
							}// fin de for
							
							c.sent = true; // marquer la coalition comme envoy�e.
								
							boolean succesAdd = false;
							
							coalitionProposalMessage  cpMsg = planMgmt.convertToCoalitionProposalMessageObject(disc.discussionId, c); // convert the coalition proposal to a suitable message to send
							
							try {
								sendAllProposals.setContentObject(cpMsg);
								succesAdd = true;
								} catch (IOException e) {
							
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							
							sendAllProposals.setPostTimeStamp(System.currentTimeMillis());
							// lastSentAcceptMessageTimer = System.currentTimeMillis();
							
							System.out.println(" Sending the coalition proposal ("+c.edgeList.toString()+") ID : " + c.coalitionId+ "  To agents  : " + receivers );
							sendAllProposals.setConversationId("send-coalition-proposal");
							sendAllProposals.setReplyWith("coalitionProposal"+System.currentTimeMillis()); // Unique value
									
							if(succesAdd){ // si un objet etait ajout� au message.
								myAgent.send(sendAllProposals);
								}
									
							mtCoalitionProposal = MessageTemplate.and(MessageTemplate.MatchConversationId("send-coalition-proposal"),
							MessageTemplate.MatchInReplyTo(sendAllProposals.getReplyWith()));
									
							//System.out.println(" Proposals were sent !!!");
				
				} // fin de for
					
				lastValidDisc = disc;
				
				if(Ct.alternativesCount == Ct.currentAlternative){
					Ct.areAllAlternativesSent = true;
				} //
				// incr�mentation de l'indice de l'alternative � envoyer prochainnement
			
				// marqu� l'alternative comme envoy�e
				//Ct.isCurrentAlternativeSent = true;
				// si toutes les alternatives on �taient envoy�es, marquer l'indicateur.
			
				
				// Mise � jour des param�tres d�tat : 
				//-----------------------------------
					Ct.isNewProposalToSend = false;
					Ct.isHavingProposalToWaitFor = true;
					Ct.isHaveSolutionToConfirm = false;
					Ct.isHaveSolutionCandidate = false;
					Ct.isHavingAcceptProposalToWaitFor = true;
					Ct.timeSinceLastEvent = System.currentTimeMillis();
					Ct.timeSinceLastProposalSent = System.currentTimeMillis();
					Ct.isOkayToSendProposals = false;
					//Ct.isCurrentAlternativeSent = true;
					Ct.isNewDiscussionToRun = false;
					Ct.isHaveSolutionCandidate = false;
		
			
			
    	}else{
    		System.out.println("Sorry, No profitable alternative for me. No need to discuss !!");
    		Ct.isNewProposalToSend = false; // to check
    	}
		
	}else{
		System.out.println(" No need to send Proposals at this stage,!!! may be another time...");
	}
	
/**
	System.out.println(" After ====================================== ");
	System.out.println("areAllAlternativesSent : " + areAllAlternativesSent);
	System.out.println("alternativesCount : "+alternativesCount);
	System.out.println(" currentAlternativeSent : "+currentAlternativeSent);
	System.out.println("currentAlternative : "+currentAlternative);
	System.out.println("=======================================");
	*/
	
    if(!needResumption) step = Ct.endCoalitionForlmationProcessStep;
	}  	
}  // End of inner methode sendAllProposalsForAlternative
			
//-------------------------------------------------------------------------		
//*************************************************************************
//-------------------------------------------------------------------------





//-------------------------------------------------------------------------		
//*************************************************************************
//-------------------------------------------------------------------------



/**
  * This is the methode used by the agents to recieve incoming  messages  
  * containing a proposition from other agents.
  * 
 */
private void  receiveProposals(Agent myAgent){
	Ct.receiveProposalsCounter++;
	agentRoundList.clear();
	receivedRoundProposals.clear();
	receivedRoundProposalsTimes.clear();
	
	MessageTemplate mtCoalitionProposal = MessageTemplate.MatchPerformative(ACLMessage.PROPOSE);
	ACLMessage msgProposal = myAgent.receive(mtCoalitionProposal);
	
	if (msgProposal != null) {
		
		while (msgProposal != null) { 
			// proposal message, Process it
				try {
						coalitionProposalMessage proposalMessage = (coalitionProposalMessage) msgProposal.getContentObject();
						Coalition cProp = planMgmt.convertToCoalitionProposal(msgProposal.getSender().getLocalName(), proposalMessage);
						receivedRoundProposals.add(cProp);
						receivedRoundProposalsTimes.add(msgProposal.getPostTimeStamp());
						
						System.out.println("-->> Proposal Received -> ID ("+proposalMessage.coalitionId+") Actions ("+cProp.actionList.toString()+") from : "+msgProposal.getSender().getLocalName()+ "");
						
						if(!agentRoundList.contains(msgProposal.getSender().getLocalName()))
							agentRoundList.add(msgProposal.getSender().getLocalName());
						
						msgProposal = myAgent.receive(mtCoalitionProposal);
				} catch (NullPointerException | UnreadableException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		} // fin de while
		
		// Mise � jour des param�tres d�tat : 
		//-----------------------------------
			Ct.isHavingProposalToWaitFor = false;
			Ct.isHavingAcceptProposalToWaitFor = true;
			Ct.isHavingReceivedNewCoalitionProposal = true; // indiquer reception de nouvelles propositions
			Ct.isHavingNewProposalsToEvaluate = true;
			Ct.timeSinceLastEvent = System.currentTimeMillis();
	} // fin de if
	else {
		System.out.println(" no !!!! I've got nothing !!! May be another time...");
		
		Ct.isNewDiscussionToRun = true;
		Ct.isOkayToSendProposals = true;
		Ct.isHaveSolutionCandidate = false;
		Ct.isTherePlansToProcess =false;
		Ct.isHavingProposalToWaitFor = false;
		Ct.areAllAcceptProposalSent = false;
		//Ct.isCurrentAlternativeSent = false;
		
	}
	
	
	if(Ct.receiveProposalsCounter>=2){
	     Ct.isHaveSolutionCandidate = true; //**********************************************************************************************
		 Ct.isNewAcceptProposalsToForm = true;
		 Ct.isNewAcceptProposalsToSend = true;
	}
	
}  // End of inner methode receiveProposals
//-------------------------------------------------------------------------		
//*************************************************************************
//-------------------------------------------------------------------------







/**
*
* This is the methode used by the agents to recieve incoming  messages  
* containing a ACCEPT_PROPOSAL from other agents.
*
*/
private void  updateStateAfterProposalsReception(Agent myAgent){
	
		Ct.updateAfterProposalsReceptionCounter++;
		
		ArrayList visitedCoalitions = new ArrayList();
	
		System.out.println(" Update My proposals concerning the agents who accepted them");
		
		if(discussionList.size()==0)  
			System.out.println(" No need to check. All the discussions estimated cost is expensive then my reference cost.");
		
				
		// Mise a jour des fr�quences:
		//----------------------------------------------------------------------------------------------------------------------------------------------------
		//----------------------------------------------------------------------------------------------------------------------------------------------------
		visitedActions.clear();
		for(int i=0; i<receivedRoundProposals.size(); i++){
			Coalition cPr = (Coalition) receivedRoundProposals.get(i);
				
			for(int r=0; r<agentsListStr.size(); r++){
				String agent = (String) agentsListStr.get(r);
				
				if(agent.equals(cPr.agentOwner)){
				
					ArrayList liste = (ArrayList) actionsFrequencyByAgent.get(r);
					
						for(int t=0; t<cPr.edgeList.size(); t++){
							String action = (String) cPr.edgeList.get(t);
							if(!visitedActions.contains(action+cPr.agentOwner)){
								int indi = 0;
								for(Edge e:localPlan.graphPlan.getEachEdge()){
									if(e.getId().equals(action)){
										int fre = (int)liste.get(indi);
										fre++;
										liste.set(indi, fre);
										
									//	System.out.println("333===>> Action : "+action + " est demand�e par l'agent  "+ cPr.agentOwner + "  nbr = "+fre);
									}
									indi++;
								}
								visitedActions.add(action+cPr.agentOwner);
							}
						}
				}
			}	
		}
		
		//----------------------------------------------------------------------------------------------------------------------------------------------------
		//----------------------------------------------------------------------------------------------------------------------------------------------------
		
		
		
		
		// v�rification s'il y a des discussions. si aucune discussion n'a un cout estim� > cout de reference, pas de n�gociations
		if(discussionList.size()>0){
			
			
			Discussion disc = (Discussion) discussionList.get(Ct.currentAlternativeSent);
			
			System.out.println(" ");
			System.out.println(" (Affichage des propos re�ues par alternative) (Disc ID "+disc.discussionId+") Alternative Nbr : "+ Ct.currentAlternativeSent);
			
			for(int i=0; i<receivedRoundProposals.size(); i++){
				
				Coalition cPr = (Coalition) receivedRoundProposals.get(i);
				long timecPr = (long) receivedRoundProposalsTimes.get(i);
			/**
				System.out.println(" *********************************************** ");
				System.out.println("Received Coalition ID : "+cPr.coalitionId);
				System.out.println("Received Coalition Owner : "+cPr.agentOwner.getLocalName());
				System.out.println("It's acttion(s) list : "+cPr.getActionListLocalNames());
				System.out.println(" Agents list : "+cPr.getAgentStrListLocalNames());
				System.out.println(" *********************************************** ");
			*/	
				for(int j=0; j<disc.coalitionList.size(); j++){
					
					Coalition c = (Coalition) disc.coalitionList.get(j);
					
					// Vider les liste d'une discussion si Ct.updateAfterProposalsReceptionCounter == 1. // R�initialisation des listes.
					if((Ct.updateAfterProposalsReceptionCounter == 1) && (!visitedCoalitions.contains(c))){
						c.agentHaveProposedList.clear(); 
						c.agentProposedCoalitionIdList.clear(); 
						c.acceptanceReceivedTime.clear(); 
						c.acceptanceStatus.clear();
						
						visitedCoalitions.add(c);
					}	
					
					if((c.compareTwoCoalition(c, cPr))){
						
						if(!(c.agentHaveProposedList.contains(cPr.agentOwner))){
							c.agentHaveProposedList.add(cPr.agentOwner); 
							c.agentProposedCoalitionIdList.add(cPr.coalitionOriginalId);
							c.acceptanceReceivedTime.add(timecPr);
							c.acceptanceStatus.add(1);
							
							
							System.out.println(" *********************************************** ");
							System.out.println("Received Coalition ID : "+cPr.coalitionId);
							System.out.println("Received Coalition Owner : "+cPr.agentOwner);
							System.out.println("It's acttion(s) list : "+cPr.getActionListLocalNames());
							System.out.println(" Agents list : "+cPr.getAgentStrListLocalNames());
							System.out.println(" *********************************************** ");
							
						}
					} // fin de if
				} // fin de for
			} // fin de for
			
					
			receivedProposals.addAll(receivedRoundProposals);
		} // fin de for
		
	/*	
		Ct.hasDiscListOrderBeenChanged = Tr.updateDiscussionsState(receivedProposals, discussionList);
		Tr.updateDiscussionsParameters(receivedProposals, discussionList, localPlan.graphPlan);
		*/
		
		// Mise � jour des param�tres d�tat : 
		//-----------------------------------
			Ct.isHavingProposalToWaitFor = false;
			//Ct.isHavingAcceptProposalToWaitFor = true;
			Ct.isHavingReceivedNewCoalitionProposal = false;
			Ct.isHavingNewProposalsToEvaluate = true;
			Ct.timeSinceLastEvent = System.currentTimeMillis();
			
			// calcul des views
			//----------------
			
			oldAgentsOrder = newAgentsOrder;
			oldAgentsPropoProfile = newAgentsPropoProfile;
			newAgentsOrder = new ArrayList(); 
			newAgentsPropoProfile = new ArrayList();
		
			// Calcul des profils
			for(int j=0; j<agentRoundList.size(); j++){
				String agent = (String) agentRoundList.get(j);
				ArrayList agentPropositionProfile = new ArrayList();
				
				boolean agentStillExist = false; // si l'agent existe toujours par rapport au round pr�cedent
				for(Edge e:localPlan.graphPlan.getEachEdge()){
					boolean exist = false;
					for(int k1=0; k1<receivedRoundProposals.size(); k1++){
						Coalition co = (Coalition) receivedRoundProposals.get(k1);
						if(co.agentOwner.equals(agent)){
							agentStillExist = true;
							if(co.edgeList.contains(e.getId())){
								exist = true;
								break;
							}
						}
					}
					
					if(exist){
						agentPropositionProfile.add(1);
					}else{
						agentPropositionProfile.add(0);
					}
				}
				
			//	System.out.println("Profil de la discussion "+disc.discussionId+" est                     "+disc.discussionProfil.toString());
			//	System.out.println("Profil de la proposition de l'agent "+agent+" est "+agentPropositionProfile.toString());
				
				
				
			//	if(!newAgentsOrder.contains(agent)){
			
					newAgentsOrder.add(agent);
					newAgentsPropoProfile.add(agentPropositionProfile);
			
				
				//	}
				
				
			//	System.out.println("l'empreinte atuelle de la proposition de de l'agent "+agent+" est "+footPrint);
			
				/*
				 
				for(int y=0; y<oldAgentsOrder.size(); y++){
					String ag = (String) oldAgentsOrder.get(y);
					
					if(ag.equals(agent)){
						System.out.println("l'empreinte anciennes de la proposition de de l'agent "+agent+" est "+ (float)oldAgentsFootPrint.get(y));
					}
				}
				
				*/
				
			}
			
		
}  // End of inner methode updateStateAfterProposalsReception




//-------------------------------------------------------------------------		




/**
*
* This is the methode used by the agents to recieve incoming  messages  
* containing a ACCEPT_PROPOSAL from other agents.
*
*/
private void  updateStateAfterProposalsReceptionOld(Agent myAgent){
	
		Ct.updateAfterProposalsReceptionCounter++;
		
		ArrayList visitedCoalitions = new ArrayList();
	
		System.out.println(" Update My proposals concerning the agents who accepted them");
		
		if(discussionList.size()==0)  
			System.out.println(" No need to check. All the discussions estimated cost is expensive then my reference cost.");
		
				
		// Mise a jour des fr�quences:
		//----------------------------------------------------
		visitedActions.clear();
		for(int i=0; i<receivedRoundProposals.size(); i++){
			Coalition cPr = (Coalition) receivedRoundProposals.get(i);
				
			for(int r=0; r<agentsListStr.size(); r++){
				String agent = (String) agentsListStr.get(r);
				
				if(agent.equals(cPr.agentOwner)){
				
					ArrayList liste = (ArrayList) actionsFrequencyByAgent.get(r);
					
						for(int t=0; t<cPr.edgeList.size(); t++){
							String action = (String) cPr.edgeList.get(t);
							if(!visitedActions.contains(action+cPr.agentOwner)){
								int indi = 0;
								for(Edge e:localPlan.graphPlan.getEachEdge()){
									if(e.getId().equals(action)){
										int fre = (int)liste.get(indi);
										fre++;
										liste.set(indi, fre);
										
									//	System.out.println("333===>> Action : "+action + " est demand�e par l'agent  "+ cPr.agentOwner + "  nbr = "+fre);
									}
									indi++;
								}
								visitedActions.add(action+cPr.agentOwner);
							}
						}
				}
			}	
		}
		
		//-----------------------------------------------------
		
		
		
		
		// v�rification s'il y a des discussions. si aucune discussion n'a un cout estim� > cout de reference, pas de n�gociations
		if(discussionList.size()>0) 
		//for(int k = 0; k<=Ct.currentAlternativeSent; k++){
		for(int k = 0; k<discussionList.size(); k++){ // parcourir toutes les discussions, non seulement la liste des discussions envoy�es
			
			
			Discussion disc = (Discussion) discussionList.get(k);
			
			System.out.println(" ");
			System.out.println(" (Affichage des propos re�ues par alternative) (Disc ID "+disc.discussionId+") Alternative Nbr : "+ k);
			
			
			
			for(int i=0; i<receivedRoundProposals.size(); i++){
				
				Coalition cPr = (Coalition) receivedRoundProposals.get(i);
				long timecPr = (long) receivedRoundProposalsTimes.get(i);
			/**
				System.out.println(" *********************************************** ");
				System.out.println("Received Coalition ID : "+cPr.coalitionId);
				System.out.println("Received Coalition Owner : "+cPr.agentOwner.getLocalName());
				System.out.println("It's acttion(s) list : "+cPr.getActionListLocalNames());
				System.out.println(" Agents list : "+cPr.getAgentStrListLocalNames());
				System.out.println(" *********************************************** ");
			*/	
				for(int j=0; j<disc.coalitionList.size(); j++){
					
					Coalition c = (Coalition) disc.coalitionList.get(j);
					
					// Vider les liste d'une discussion si Ct.updateAfterProposalsReceptionCounter == 1. // R�initialisation des listes.
					if((Ct.updateAfterProposalsReceptionCounter == 1) && (!visitedCoalitions.contains(c))){
						c.agentHaveProposedList.clear(); 
						c.agentProposedCoalitionIdList.clear(); 
						c.acceptanceReceivedTime.clear(); 
						c.acceptanceStatus.clear();
						
						visitedCoalitions.add(c);
					}	
					
					if((c.compareTwoCoalition(c, cPr))){
									
						if(!(c.agentHaveProposedList.contains(cPr.agentOwner))){
							c.agentHaveProposedList.add(cPr.agentOwner); 
							c.agentProposedCoalitionIdList.add(cPr.coalitionOriginalId);
							c.acceptanceReceivedTime.add(timecPr);
							c.acceptanceStatus.add(1);
							
						//	System.out.println(" -> ------------------------------------------");
							System.out.println(" -> Received C ID : "+cPr.coalitionId+ "  -> It's Original Id is : "+cPr.coalitionOriginalId);
						//	System.out.println(" -> My equivalent coalition ID : "+c.coalitionId);
							/*
							System.out.println(" -> Coalition's Task list : "+c.edgeList.toString());
							System.out.println(" -> Received Coalition Owner : "+cPr.agentOwner);
							System.out.println(" -> It's acttion(s) list : "+cPr.getActionListLocalNames());
							System.out.println(" -> My Equivalent acttion(s) list : "+c.getEdgeListLocalNames());
							System.out.println(" -> It's Agents list : "+cPr.getAgentStrListLocalNames());
							System.out.println(" -> My Equivalent Agents list : "+c.agentList.toString());
							System.out.println(" -> Was Accepted by : "+c.getAcceptedAgentListLocalNames());
							*/
													
		
							
							
						}
						
						/*
						
						else{
							
			//				System.out.println(" ---------------> Proposition concernant l'action est d�j� re�ue : Mise � jour de ses donn�es relatives");
							
						
							boolean coalitionUpdated = false;
							for(int h=0; h<c.agentAcceptedList.size(); h++){
								String ag = (String) c.agentAcceptedList.get(h);
								
								if(ag.equals(cPr.agentOwner)){
									
							//		System.out.println(" Update "+cPr.agentOwner+" in the coalition agent accepted List (Update status and received time)");
									int status = (int ) c.acceptanceStatus.get(h);
									if(status == 0){ // si la validit� a expir�e
										
										
										c.agentAcceptedList.remove(h);
										c.agentAcceptedProposalCoalitionIdList.remove(h);
										c.acceptanceReceivedTime.remove(h);
										c.acceptanceStatus.remove(h);
										h--;	
									
									}else{
										String coId  =  (String) c.agentAcceptedProposalCoalitionIdList.get(h);
										if(coId.equals(cPr.coalitionOriginalId)){
											//c.agentAcceptedList.add(cPr.agentOwner); 
											//c.agentAcceptedProposalCoalitionIdList.add(cPr.coalitionOriginalId);
											c.acceptanceReceivedTime.set(h, timecPr);
											c.acceptanceStatus.set(h, 1);
											coalitionUpdated = true;
										}
									
									}
									
									
								}
							}
							
							if(!coalitionUpdated){
								c.agentAcceptedList.add(cPr.agentOwner); 
								c.agentAcceptedProposalCoalitionIdList.add(cPr.coalitionOriginalId);
								c.acceptanceReceivedTime.add(timecPr);
								c.acceptanceStatus.add(1);
							}
						
						}
						
						*/
										
				
					} // fin de if
					
				} // fin de for
			} // fin de for
			
		
			
			
			// fin calcul des profil
			
			
					
			receivedProposals.addAll(receivedRoundProposals);
		} // fin de for
		
	/*	
		Ct.hasDiscListOrderBeenChanged = Tr.updateDiscussionsState(receivedProposals, discussionList);
		Tr.updateDiscussionsParameters(receivedProposals, discussionList, localPlan.graphPlan);
		*/
		
		// Mise � jour des param�tres d�tat : 
		//-----------------------------------
			Ct.isHavingProposalToWaitFor = false;
			//Ct.isHavingAcceptProposalToWaitFor = true;
			Ct.isHavingReceivedNewCoalitionProposal = false;
			Ct.isHavingNewProposalsToEvaluate = true;
			Ct.timeSinceLastEvent = System.currentTimeMillis();
			
			/*
			
	System.out.println("------------------------             MISE A JOUR              -------------------------------");
	
	discussionsProbability.clear();
	discussionsProbability = Tr.computeDiscussionsProbability(discussionList, planMgmt, agentsHaveMyPlan.size(), myAgent.getLocalName());
	
	System.out.println(" ----------------  Probabilit�s des Discussions --------------------- ");
	//Enumeration names;
	int key;
	
	//names = discussionsProbability.keys();
	
	for(int i=0; i<discussionList.size(); i++){
		Discussion disc = (Discussion) discussionList.get(i);
		int discId = disc.discussionId;
		System.out.println("Discussion en position "+i+" avec un Id = "+discId+"  --> "+ (long)discussionsProbability.get(discId) );
	}
		*/
			
			oldAgentsOrder = newAgentsOrder;
			oldAgentsPropoProfile = newAgentsPropoProfile;
			newAgentsOrder = new ArrayList(); 
			newAgentsPropoProfile = new ArrayList();
		
			// Calcul des profils
			for(int j=0; j<agentRoundList.size(); j++){
				String agent = (String) agentRoundList.get(j);
				ArrayList agentPropositionProfile = new ArrayList();
				
				boolean agentStillExist = false; // si l'agent existe toujours par rapport au round pr�cedent
				for(Edge e:localPlan.graphPlan.getEachEdge()){
					boolean exist = false;
					for(int k1=0; k1<receivedRoundProposals.size(); k1++){
						Coalition co = (Coalition) receivedRoundProposals.get(k1);
						if(co.agentOwner.equals(agent)){
							agentStillExist = true;
							if(co.edgeList.contains(e.getId())){
								exist = true;
								break;
							}
						}
					}
					
					if(exist){
						agentPropositionProfile.add(1);
					}else{
						agentPropositionProfile.add(0);
					}
				}
				
			//	System.out.println("Profil de la discussion "+disc.discussionId+" est                     "+disc.discussionProfil.toString());
			//	System.out.println("Profil de la proposition de l'agent "+agent+" est "+agentPropositionProfile.toString());
				
				
				
			//	if(!newAgentsOrder.contains(agent)){
			
					newAgentsOrder.add(agent);
					newAgentsPropoProfile.add(agentPropositionProfile);
			
				
				//	}
				
				
			//	System.out.println("l'empreinte atuelle de la proposition de de l'agent "+agent+" est "+footPrint);
			
				/*
				 
				for(int y=0; y<oldAgentsOrder.size(); y++){
					String ag = (String) oldAgentsOrder.get(y);
					
					if(ag.equals(agent)){
						System.out.println("l'empreinte anciennes de la proposition de de l'agent "+agent+" est "+ (float)oldAgentsFootPrint.get(y));
					}
				}
				
				*/
				
			}
			
		
}  // End of inner methode updateStateAfterProposalsReception




//-------------------------------------------------------------------------		




/**
*
* This methode is used by agents to update the local state after receiving accept for proposals.
 * @throws IOException 
*
**/
private void updateAfterAcceptProposals(Agent myAgent) throws IOException {

	System.out.println("Reference Cost: "+Ct.referenceCost);
	
	Ct.updateAfterAcceptProposalsCounter++;
	
	// Mise � jour de des co�ts des coalitions en fonction de la validit� des acceptation recues
	Tr.updateAllCoalitionsRealCosts(localPlan.graphPlan, discussionList, maxAcceptMessageValidity);
	
	if((Ct.currentAlternativeSent>=0) && (discussionList.size()>=1)) {
		Discussion disc = (Discussion) discussionList.get(Ct.currentAlternativeSent);
		disc.evaluateEstimatedCostForConfirm(localPlan.graphPlan);
		Ct.currentReferenceCost = disc.discussionEstimatedCost;
		
		System.out.println("Current Estimated Cost: "+disc.discussionEstimatedCost);
		
		//System.out.println(" Checking after receiving Accept Proposal Messages.");
		disc.evaluateEstimatedCostForConfirm(localPlan.graphPlan);
		finalRealCost = disc.discussionEstimatedCost; //=disc.getDiscussionRealCostInTime(localPlan.graphPlan);
		
		finalRealCostListUpdateAfterAcceptProposals.add(finalRealCost);
		estimatedCostListUpdateAfterAcceptProposals.add(disc.discussionEstimatedCost);
	
			sentDiscEstAccCostList.add(Ct.currentReferenceCost);
		
			if(Ct.currentReferenceCost < Ct.costLimit){
				
				if(budgetLimit) System.out.println(" The budget is not exceeded : "+ Ct.costLimit);
				
				
			
				
				if(Ct.currentReferenceCost < Ct.referenceCost){
					
					System.out.println(" Current Cost is : "+ Ct.currentReferenceCost);
					System.out.println(" reference Cost is : "+ Ct.referenceCost);
					System.out.println(" So, let's try to get the confirmation !!!! ");
					
					Ct.isHaveSolutionToConfirm = true;
				//	Ct.sendConfirmationsCounter = 0;
					Ct.areAllConfirmProposalSent = false;
					
					Ct.isNewConfirmProposalsToSend = true;
					
					
				}else{
					
					System.out.println(" Current Cost is : "+ Ct.currentReferenceCost);
					System.out.println(" reference Cost is : "+ Ct.referenceCost);
					Ct.discussionParameter.put(Ct.IndividualCost, Float.toString(Ct.referenceCost));
					Ct.discussionParameter.put(Ct.RealFinalCost, Float.toString(Ct.referenceCost));
					System.out.println(" So, I need to try again !!!!");
					System.out.println(" finalRealCost 2 ="+finalRealCost);
					
					if(Ct.alternativesCount <= (Ct.currentAlternative))
					Ct.needRestardProcess = true;
					Ct.isNewAcceptProposalsToForm = false;
					Ct.isNewAcceptProposalsToSend = false;
					Ct.isHavingProposalToWaitFor = false;
					
					Ct.isNewDiscussionToRun = true;
					Ct.isTherePlansToProcess =false;
					Ct.isHavingProposalToWaitFor = false;
					Ct.areAllAcceptProposalSent = false;
				//	Ct.isCurrentAlternativeSent = false;
					Ct.isOkayToSendProposals = true;
					
				}
		
			}else{
				if(budgetLimit) System.out.println(" The budget is exceeded (Budget) Current Cost = "+Ct.currentReferenceCost+"  Cost limit = "+ Ct.costLimit +"   ref Cost = "+Ct.currentReferenceCost);
				// Relancer
				System.out.println(" finalRealCost ="+finalRealCost);
				Ct.isNewDiscussionToRun = true;
				Ct.isOkayToSendProposals = true;
				Ct.isHaveSolutionCandidate = false;
				Ct.isTherePlansToProcess =false;
				Ct.isHavingProposalToWaitFor = false;
				Ct.areAllAcceptProposalSent = false;
			//	Ct.isCurrentAlternativeSent = false;
				
			}
		}
	
	Ct.isHavingReceivedNewCoalitionProposal = false;
	
	
	
	// To see
	System.out.println("--> Old Cost Limit is : "+ Ct.costLimit);
	System.out.println("--> Old Cost pPercentage : "+costLimitPercentage);
	if(initialCostLimitPercentage == 0 ) initialCostLimitPercentage =  costLimitPercentage;
	
	//if(costLimitPercentage < 100)   // � revoir
	//	costLimitPercentage+=(percentageRate*globalNegotiationRound);

	// v�rification si la derni�re structure de coalition envoy�e n'est pas celle d'avant derni�re
	
	if(1==2){	
		int sc1 = 1001,sc2 = 1000; 
		
		if(sentDiscussionList.size()>=2){
			sc1 = (int) sentDiscussionList.get(sentDiscussionList.size()-2);
			sc2 = (int) sentDiscussionList.get(sentDiscussionList.size()-1);
		}
		
		
		
		if(sc1 != sc2){ // v�rification si la derni�re structure de coalition envoy�e n'est pas celle d'avant derni�re
			
			float mintCost = Tr.getMinEstimatedCost(discussionList);		
			float percentageValue = (float)(  (100 - costLimitPercentage) /  (maxRound - globalNegotiationRound) );
			
			if((sentDiscussionList.size()>=2)  && (maxRound > globalNegotiationRound) && (globalNegotiationRound > 8)) {   // TODO a revoir  globalNegotiationRound > 15
				float estimatedCost1 = Tr.getEstimatedCost(discussionList, (int) sentDiscussionList.get(sentDiscussionList.size()-2));
				float estimatedCost2 = Tr.getEstimatedCost(discussionList, (int) sentDiscussionList.get(sentDiscussionList.size()-1));
				
				percentageValue *=  (float) (mintCost / estimatedCost2);
				System.out.println("--> mintCost : "+mintCost);
				System.out.println("--> estimatedCost2 : "+estimatedCost2);
				System.out.println("--> percentageValue : "+percentageValue);
				costLimitPercentage+= percentageValue;
			}
		}
		
		if(costLimitPercentage > 100)   // � revoir
			costLimitPercentage = 100;
		
		//costLimitPercentage =100;
		
	}
	
	
//	Ct.costLimit = Tr.getCostLimit(discussionList, costLimitPercentage); 
	
	System.out.println("--> New Cost pPercentage : "+costLimitPercentage);
	System.out.println("--> New Cost Limit is : "+ Ct.costLimit);
	System.out.println("--> globalNegotiationRound is : "+ globalNegotiationRound);
	
	

}



/**
*
* This is the methode used by the agents to recieve incoming  messages  
* containing a ACCEPT_PROPOSAL from other agents.
 * @throws IOException 
*
*/
private void  evaluateReceivedProposals(Agent myAgent) throws IOException{
	Ct.evaluateReceivedProposalsCounter++;
	Ct.isHavingNewProposalsToEvaluate = false;
	
	System.out.println("> Reference Cost: "+Ct.referenceCost);
	
	if(discussionList.size()>0){ // s'il existe des discussions 
				
		//	for(int t=0; t<discussionList.size(); t++){ // �valuation de toutes les discussions, non pas seulement celle envoy�e
			
				//System.out.println("Evaluating the situation !!!!!!!");
				Discussion disc = (Discussion) discussionList.get(Ct.currentAlternativeSent);
				//Discussion disc = (Discussion) discussionList.get(t);
				
				
				
			//	System.out.println("Discussion  (position : "+t+") ID : "+ disc.discussionId);
			//	System.out.println("> Path : "+ disc.discussionPath.getEdgeSet().toString());
			//	System.out.println("> Estimated Cost is : "+ disc.discussionEstimatedCost);
			/*	
				for(int i=0; i<disc.coalitionList.size(); i++){
					Coalition c = (Coalition) disc.coalitionList.get(i);
					c.evaluateCoalitionRealCost(localPlan.graphPlan, planMgmt.getActionCostsList(), maxAcceptMessageValidity);
				} // fin de for
				*/
				disc.evaluateEstimatedCostForAccept(localPlan.graphPlan); // evaluation du co�t r�el d'une discussion
				float realCost  = disc.discussionEstimatedCost; //=   disc.getDiscussionRealCostInTime(localPlan.graphPlan);
				System.out.println("> Estimated Cost till know is for discussion ID ("+disc.discussionId+") is : "+realCost );
				costTraking.add(realCost);
		
		
		
		//disc.evaluateRealCost(); // evaluation du co�t r�el d'une discussion
		
		
		//dfg reorderDiscussionListByEstimatedCost
		
	
		
		// Mise � jour des param�tres d�tat : 
		//-----------------------------------
		    
			
			
			if(disc.discussionEstimatedCost < Ct.referenceCost){
				Ct.isHaveSolutionCandidate = true;
				Ct.isNewAcceptProposalsToForm = true;
				Ct.isNewAcceptProposalsToSend = true; // puisque pour l'instant j'ai une seule m�thode, pour la formation et l'envoie des acceptations
				Ct.isHavingProposalToWaitFor = false;
			}else{
				Ct.isHavingProposalToWaitFor = false;
				Ct.isNewProposalToSend = true;
			}
			
	//	}
		
		Ct.isHavingNewProposalsToEvaluate = false;
		Ct.timeSinceLastEvent = System.currentTimeMillis();
	//	disc.minimalDisplayWithRealCost(disc);
	} // fin de if
	
}  // End of inner methode evaluateReceivedProposals
//-------------------------------------------------------------------------		
//*************************************************************************
//-------------------------------------------------------------------------















//-------------------------------------------------------------------------		
//*************************************************************************
//-------------------------------------------------------------------------
/**
 Inner methode sendAcceptProposal.
 This is the methode used by agents to send a proposal accept.
 * @throws IOException 
 * @throws InterruptedException 
*/
private void sendAcceptProposal (Agent myAgent) throws IOException  {
			
	Ct.sendAcceptProposalsCounter++;
	
	boolean isAcceptancesSent = false;
		
	long time = (System.currentTimeMillis() - lastSentAcceptMessageTimer);
	
	// attendre le temps que le dernier message d'acceptation soit exp�r�
	if(lastSentAcceptMessageTimer>0)
	if((time) < maxAcceptMessageValidity){
		System.out.println("Attente d'expiration du dernier message d'acceptation envoy�e");
		System.out.println("Time : "+time);
		System.out.println("lastSentAcceptMessageTimer "+lastSentAcceptMessageTimer);
		System.out.println(" maxAcceptMessageValidity " +maxAcceptMessageValidity);
		System.out.println("System.currentTimeMillis() "+System.currentTimeMillis() );
		System.out.println("Time to sleep "+ (maxAcceptMessageValidity - time) );
			
		printSubStepOnRoot("Attente expiration dernier accept");
		
		try {
			Thread.sleep( maxAcceptMessageValidity - time); } catch (InterruptedException e) { e.printStackTrace();}
		}

	
	// Evaluer les propositions des autres agents : .....
	//---------------------------------------------------
	
	
	// r�ordonner l'ordre des discussions dans la liste discussionList
	//reorderDiscussionsPriority(reorderDiscussionByState, reorderDiscussionByState2);
	
	// mise � jour de l'ordre des discussions et param�tres sur les param�tres  Ct.currentAlternative et Ct.currentAlternativeSent
	//updateDiscussionOrder();
	
	
	//if((Ct.isCurrentAlternativeSent) && (!Ct.areAllAcceptProposalSent)){

			
			Discussion disc = (Discussion) discussionList.get(Ct.currentAlternativeSent);
			disc.evaluateEstimatedCostForAccept(localPlan.graphPlan);
			
			System.out.println("discussion Id  = "+disc.discussionId);
			System.out.println("disc.discussionEstimatedCost = "+disc.discussionEstimatedCost);
			System.out.println("Ct.referenceCost = "+Ct.referenceCost);
			
		if(disc.discussionEstimatedCost < Ct.referenceCost){
		
			isAcceptancesSent = true;
			System.out.println(" ->->- Sending Accept Proposal Messages to (Alternative ID "+disc.discussionId+": "+Ct.currentAlternativeSent+"):");
				
			//-----------------------
			
			
			
			for(int i=0; i<disc.coalitionList.size(); i++){
						
				Coalition c = (Coalition) disc.coalitionList.get(i);
				
				ArrayList sentAcceptCoalitionID = new ArrayList();
			//	int tailleListe = c.agentAcceptedList.size()-1;
				for(int j=0; j<c.agentHaveProposedList.size(); j++){
					String ag = (String) c.agentHaveProposedList.get(j);
					
					String act = (String) c.agentProposedCoalitionIdList.get(j);
					
					// il faut � chaque acceptation l'agent renouvele son accepation. 
					// Puisque avec le temps, un agent ne peut pas savoir quelle acceptation � consid�rer.
					
							if(!c.agentHaveProposedList.get(j).equals(myAgent.getAID().getLocalName())  ){
									sentAcceptCoalitionID.add(act);
									System.out.println(" ->->-------------> to the agent : "+ag+ " Coalition ID : "+c.agentProposedCoalitionIdList.get(j));
									ACLMessage sendAcceptProposal = new ACLMessage(ACLMessage.ACCEPT_PROPOSAL);
									sendAcceptProposal.addReceiver(getAgentAIDfromString(ag));
									
										
										sendAcceptProposal.setContent(act); // � revoir
										sendAcceptProposal.setInReplyTo(c.coalitionId);
										sendAcceptProposal.setConversationId("send_ACCEPT_PROPOSAL");
										sendAcceptProposal.setReplyWith("coalitionProposal"+System.currentTimeMillis()); // Unique value
										
										sendAcceptProposal.setPostTimeStamp(System.currentTimeMillis());
										lastSentAcceptMessageTimer = System.currentTimeMillis();
										myAgent.send(sendAcceptProposal);
										
										
										
										mtAcceptProposal = MessageTemplate.and(MessageTemplate.MatchConversationId("send_ACCEPT_PROPOSAL"),
												MessageTemplate.MatchInReplyTo(sendAcceptProposal.getReplyWith()));
										System.out.println(" ACCEPT_PROPOSAL message was sent to :"+ag+ " about it's proposal : "+act+ "  ("+c.edgeList.toString()+")");
									
								} // fin de if
				
				}// fin de for
			} // fin de for
					
			Ct.areAllAcceptProposalSent = true;
		
			step = Ct.receiveAcceptProposalsStep;
			
			// Mise � jour des param�tres d�tat : 
			//-----------------------------------
			//Ct.isHavingProposalToWaitFor = true;
			Ct.isHavingAcceptProposalToWaitFor = true;
			Ct.isNewAcceptProposalsToForm = false;
			Ct.isNewAcceptProposalsToSend = false;
			
			Ct.timeSinceLastAcceptProposalSent = System.currentTimeMillis();
			Ct.timeSinceLastEvent = System.currentTimeMillis();
		}

		
		
	// TODO, to check
		
	if(!isAcceptancesSent){
		System.out.println(" I'am not ready to send accept proposal messages !!!! waiting ... ");
			
		
			
		for(int i=0; i<disc.coalitionList.size(); i++){
			Coalition c = (Coalition) disc.coalitionList.get(i);
			c.acceptanceStatus.clear();
			c.agentHaveProposedList.clear();
			c.agentProposedCoalitionIdList.clear();
			c.acceptanceReceivedTime.clear();
		} // fin de for
			
		//step = Ct.sendPlanStep;   // to confirm
		if(globalNegotiationRound < maxRound){
			Ct.isNewDiscussionToRun = true;
			Ct.isOkayToSendProposals = true;
			Ct.isHaveSolutionCandidate = false;
			Ct.isTherePlansToProcess =false;
			Ct.isHavingProposalToWaitFor = false;
			Ct.areAllAcceptProposalSent = false;
			//Ct.isCurrentAlternativeSent = false;
		}
		
	}
	

	
}  // End of methode class sendAcceptProposal
//-------------------------------------------------------------------------		
//*************************************************************************
//-------------------------------------------------------------------------





//-------------------------------------------------------------------------		





/**
*
* This methode is used by agents to process ACCEPT_PROPOSAL messages.
* Plusieurs strat�gies peuvent �tre impl�ment�es.
*
**/
private void receiveAcceptProposal(Agent myAgent) {
	
	Ct.receiveAcceptProposalsCounter++;
	

		for(int j=0; j<allCoalitionsList.size(); j++){			
			Coalition c = (Coalition) allCoalitionsList.get(j);
				c.agentRecievedAcceptedProposalList.clear(); 
				c.acceptanceAcceptReceivedTime.clear(); 
				c.acceptanceAcceptStatus.clear(); 
				c.agentAcceptedAcceptCoalitionIdList.clear(); 
		}

	
	
	if(Ct.areAllAcceptProposalSent){
		
		MessageTemplate mtAcceptProposal = MessageTemplate.MatchPerformative(ACLMessage.ACCEPT_PROPOSAL);
		ACLMessage msgAcceptProposal = myAgent.receive(mtAcceptProposal);
		
							
		if (msgAcceptProposal != null) {
			
			
			
			while (msgAcceptProposal != null) {
				try {
					String CoalitionId = (String) msgAcceptProposal.getContent();
					String agentCoalitionId = (String) msgAcceptProposal.getInReplyTo();
						
					System.out.println("-> Accept Proposal Received for the coalition -> ID "+CoalitionId+"  from : "+
										msgAcceptProposal.getSender().getLocalName()+ " at time "+msgAcceptProposal.getPostTimeStamp());
					
											
					Discussion disc = (Discussion) discussionList.get(Ct.currentAlternativeSent);
					
					//-----------------------
					for(int i=0; i<disc.coalitionList.size(); i++){
						Coalition c = (Coalition) disc.coalitionList.get(i);
						
						if(c.coalitionId.equals(CoalitionId)){
							if(!c.agentRecievedAcceptedProposalList.contains(msgAcceptProposal.getSender().getLocalName())){
								System.out.println("   --> Actions Accepted: "+c.edgeList.toString());
								//	System.out.println("-->> adding the agent : "+ msgAcceptProposal.getSender().getLocalName() + " to the coalition ID "+c.coalitionId);
								
							}else{ // fin de if
								System.out.println(" ---------------> Accept Proposal concernant la coalition "+CoalitionId+" est d�j� re�ue de l'agent : "+msgAcceptProposal.getSender().getLocalName());
							}
							
							if(c.coalitionId.equals(CoalitionId)){
								long time = msgAcceptProposal.getPostTimeStamp();
								c.agentRecievedAcceptedProposalList.add(msgAcceptProposal.getSender().getLocalName());
								c.acceptanceAcceptReceivedTime.add(time);
								c.acceptanceAcceptStatus.add(1);
								c.agentAcceptedAcceptCoalitionIdList.add(agentCoalitionId);
								
								
							} // fin de if
						}
					} // fin de for
				
				} catch (NullPointerException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// r�cup�ration du prochain �l�ment. 
				msgAcceptProposal = myAgent.receive(mtAcceptProposal);
				
			} // fin de while
			
			// Mise � jour des param�tres d�tat : 
			//-----------------------------------
				
			Ct.timeSinceLastEvent = System.currentTimeMillis();
			
			Ct.isHavingAcceptProposalToWaitFor = false;	
			
		} // fin de if
		else {
			System.out.println(" no !!!! I've got nothing !!! Maybe another time...");
			Ct.isHavingAcceptProposalToWaitFor = false;	
			}
		
		
	} // fin de if

	if(Ct.receiveAcceptProposalsCounter ==2){
	//	Ct.isCurrentAlternativeSent = false;
		Ct.isNewDiscussionToRun = true;
		Ct.isOkayToSendProposals = true;
	}
	


}  // End of methode class receiveAcceptProposal
//-------------------------------------------------------------------------		
//*************************************************************************
//-------------------------------------------------------------------------






//-------------------------------------------------------------------------		
//*************************************************************************
//-------------------------------------------------------------------------
/**
Inner methode receivedConfirmProposal.
This is the methode used by agents to send a.
 * @throws IOException 
*/
private void receivedConfirmProposal(Agent myAgent) throws IOException {
	
	Ct.receiveConfirmationsCounter++;
	
	ArrayList receivedFromAgent = new ArrayList();
	ArrayList confirmedCoalitions = new ArrayList();
	
	if(Ct.areAllAcceptProposalSent){
		
		MessageTemplate mtConfirm = MessageTemplate.MatchPerformative(ACLMessage.CONFIRM);
		ACLMessage msgConfirm = myAgent.receive(mtConfirm);
									
		if (msgConfirm != null) {
			
			boolean candidateSolutionFound = false;
			Discussion disc = (Discussion) discussionList.get(Ct.currentAlternativeSent);
			
				while (msgConfirm != null) {
					
					if(agentsHaveMyPlan.contains(msgConfirm.getSender().getLocalName())){  // Si l'agent existe toujours dans le syst�me
						// Puisque parfois, il y a des pertes de messages
						
						if(!receivedFromAgent.contains(msgConfirm.getSender().getLocalName()))
							receivedFromAgent.add(msgConfirm.getSender().getLocalName());
						
						try {
							String CoalitionId = (String) msgConfirm.getContent();
								
							System.out.println("-->> Confirm Acceptance Received for the coalition -> ID "+CoalitionId+" from : "+msgConfirm.getSender().getLocalName()+ "  ");
								
						
							//-----------------------
							for(int i=0; i<disc.coalitionList.size(); i++){
								Coalition c = (Coalition) disc.coalitionList.get(i);
								if(c.coalitionId.equals(CoalitionId)){
									if(!c.agentRecievedConfirmationList.contains(msgConfirm.getSender().getLocalName())){
										confirmedCoalitions.add(c);
										String agentName = msgConfirm.getSender().getLocalName();
										c.agentRecievedConfirmationList.add(msgConfirm.getSender().getLocalName());
										c.isFinal = true;
										for(int h=0; h<c.agentList.size(); h++){
											String agentN = (String) c.agentList.get(h);
											if(agentN.equals(agentName)){
												c.agentList.remove(h);
												break;
											}
										}
										
										System.out.println("   --> Actions : "+c.edgeList.toString());
									} // fin de if
									break;
								} // fin de if
							} // fin de for
							
							
							
							disc.evaluateFinalCost(localPlan.graphPlan);
							
							if(disc.discussionFinalCost<Ct.referenceCost) {
								candidateSolutionFound = true; 
								} // fin de if
							
							
							
						} catch (NullPointerException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				}	
					// r�cup�ration du prochain �l�ment. 
					msgConfirm = myAgent.receive(mtConfirm);
			} // fin de while
				
			
			
			// Mise � jour des param�tres d�tat : 
			//-----------------------------------
				
			//--------------------------------------------------------------------
			//--------------------------------------------------------------------
			if(receivedFromAgent.size()>=(totalAgent-1))	{
				
			
				Ct.isSolutionFound = true;
			
				Ct.isHavingProposalToWaitFor = false;
				Ct.isHavingAcceptProposalToWaitFor = false;
				Ct.isNewAcceptProposalsToForm = false;
				Ct.isNewAcceptProposalsToSend = false;
				
				
				resultType = disc.getResultType();
				if(Ct.isSolutionFound && resultType == 0){
					resultType =1;
					finalRealCost = disc.getDiscussionRealCostInTime(localPlan.graphPlan);
					finalRealCostListReceivedConfirmProposal.add(finalRealCost);
					estimatedCostListReceivedConfirmProposal.add(disc.discussionEstimatedCost);
				}   
				
				if(excludeResultType1){
					if(resultType == 1){
						Ct.isSolutionFound = false;
						Ct.isNewDiscussionToRun = true;
						//Ct.isCurrentAlternativeSent = false;
						Ct.isOkayToSendProposals = true;
						System.out.println(" --->  Sorry, I have to try again. this solution is not possible !!");
						
					}
					if(resultType == 2){
						System.out.println(" --->  Goood , Coorect Solution Found !!");
						
						System.out.println("Reference Cost: "+Ct.referenceCost);
						System.out.println("Current Estimated Cost: "+disc.discussionEstimatedCost);
						
						
					}
				}
				
				
				if(candidateSolutionFound){  // si on a une discussion condidate
					Ct.isHaveSolutionCandidate = false;
					Ct.isHavingProposalToWaitFor = false;
					Ct.isHavingAcceptProposalToWaitFor = false;
					} // fin de if
				
				Ct.timeSinceLastEvent = System.currentTimeMillis();
				
				
			}else{ // fin de if(receivedFromAgent.size()==3)
				// Annuler les confirmation
				for(int r=0; r<confirmedCoalitions.size(); r++){
					Coalition c = (Coalition) confirmedCoalitions.get(r);
				//	c.agentRecievedConfirmationList.clear();
				}
			}
			//--------------------------------------------------------------------
			//--------------------------------------------------------------------
				
				
			
			
		} // fin de if
		else {
				System.out.println(" no !!!! I've got nothing !!! Maybe another time...");
				if(globalNegotiationRound < maxRound){
					Ct.isNewDiscussionToRun = true;
					Ct.isOkayToSendProposals = true;
					Ct.isHaveSolutionCandidate = false;
					Ct.isTherePlansToProcess =false;
					Ct.isHavingProposalToWaitFor = false;
					Ct.areAllAcceptProposalSent = false;
				//	Ct.isCurrentAlternativeSent = false;
				}
				
			
			}
		
		System.out.println(" ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^ ");
		
		if(Ct.receiveConfirmationsCounter == 2){
			Ct.isSolutionFound = true;
			Ct.isHavingProposalToWaitFor = false;
			Ct.isHavingAcceptProposalToWaitFor = false;
			Ct.isNewAcceptProposalsToForm = false;
			Ct.isNewAcceptProposalsToSend = false;
			
			Discussion disc = (Discussion) discussionList.get(Ct.currentAlternativeSent);
			resultType = disc.getResultType();
			if(Ct.isSolutionFound && resultType == 0) resultType =1;  
			
			if(excludeResultType1){
				if(resultType == 1){
					System.out.println(" Sorry I have to try again. this solution is not possible !!");
					Ct.isSolutionFound = false;
					Ct.isNewDiscussionToRun = true;
					//Ct.isCurrentAlternativeSent = false;
					Ct.isOkayToSendProposals = true;
				}
				if(resultType == 2){
					System.out.println(" --->  Goood , Coorect Solution Found !!");
				}
			}
		}
	} // fin de if
	
	
}  // End of methode class receivedConfirmProposal
//-------------------------------------------------------------------------		
//*************************************************************************
//-------------------------------------------------------------------------


//-------------------------------------------------------------------------		
//*************************************************************************
//-------------------------------------------------------------------------



/**
*
* This methode is used by agents to send a proposal confirm.
 * @throws IOException 
*/
private void sendConfirmProposal (Agent myAgent) throws IOException {

	Ct.sendConfirmationsCounter++;
	
	// Mise � jour de des co�ts des coalitions en fonction de la validit� des acceptation recues
	Tr.updateAllCoalitionsRealCosts(localPlan.graphPlan, discussionList, maxAcceptMessageValidity);
		
	if( (!Ct.areAllConfirmProposalSent)){
				
		System.out.println(" ->->- Sending Confirmation Messages to the agents : Alternative "+Ct.currentAlternativeSent);
						
		Discussion disc = (Discussion) discussionList.get(Ct.currentAlternativeSent);
		disc.evaluateEstimatedCostForConfirm(localPlan.graphPlan);
		System.out.println(" Discussions Real cost in Time is "+disc.discussionEstimatedCost);
		System.out.println(" Current Estimated Cost: "+disc.discussionEstimatedCost);
		System.out.println(" Reference Cost is: "+Ct.referenceCost);
		
		
		if(disc.discussionEstimatedCost < Ct.referenceCost){
				//-----------------------
			
			System.out.println(" 2 ->->- Sending Confirmation Messages to the agents");
			//System.out.println(" -> Coalitions List size : "+disc.coalitionList.size());
				for(int i=0; i<disc.coalitionList.size(); i++){
							
					Coalition c = (Coalition) disc.coalitionList.get(i);
					
					ArrayList sentConfirmCoalitionID = new ArrayList();
										
					for(int j=0; j<c.agentRecievedAcceptedProposalList.size(); j++){
						
						String ag = (String) c.agentRecievedAcceptedProposalList.get(j);
										
						String act = (String) c.agentAcceptedAcceptCoalitionIdList.get(j);
						
						// agentAcceptedProposalCoalitionIdList
						
						if(!sentConfirmCoalitionID.contains(act)){
						
								if(!c.agentRecievedAcceptedProposalList.get(j).equals(myAgent.getAID().getLocalName())){
									
									sentConfirmCoalitionID.add(act);
									
									System.out.println(" ->->-------------> to the agent : "+ag);
									System.out.println(" ->->-------------> Coalition ID : "+c.agentAcceptedAcceptCoalitionIdList.get(j));
									System.out.println(" ->->-------------> Coalition Tasks : "+c.edgeList.toString());
									
									ACLMessage sendConfirmation = new ACLMessage(ACLMessage.CONFIRM);
									sendConfirmation.addReceiver(getAgentAIDfromString(ag));
									
									sendConfirmation.setContent(act); // � revoir
									sendConfirmation.setConversationId("send_CONFIRM");
									sendConfirmation.setReplyWith("coalitionProposal"+System.currentTimeMillis()); // Unique value
									myAgent.send(sendConfirmation);
									
									mtConfirm = MessageTemplate.and(MessageTemplate.MatchConversationId("send_CONFIRM"),
											MessageTemplate.MatchInReplyTo(sendConfirmation.getReplyWith()));
								
									System.out.println(" CONFIRM message was sent to :"+ag+ " about it's proposal : "+act);
								} // fin de if
															
						}else{
							System.out.println("   --->   Confirm for "+act+" already sent !!");
						}		
					}// fin de for
				} // fin de for
						
				Ct.areAllConfirmProposalSent = true;
			
				step = Ct.receivedConfirmationsStep;
			
				// Mise � jour des param�tres d�tat : 
				//-----------------------------------
				Ct.isHavingProposalToWaitFor = false;
				Ct.isHavingAcceptProposalToWaitFor = false;
				Ct.isNewAcceptProposalsToForm = false;
				Ct.isNewConfirmProposalsToSend = false;
				Ct.isHaveSolutionToConfirm = false;
				Ct.isHavingConfirmProposalToWaitFor = true;
				Ct.timeSinceLastAcceptProposalSent = System.currentTimeMillis();
				Ct.timeSinceLastEvent = System.currentTimeMillis();
			}else{ // fin de if
				System.out.println("Evaluation temps r�el, cout de r�f�rence d�pass� !! Alors, entamer nouvelle discussion");
				Ct.isNewDiscussionToRun = true;
				Ct.isOkayToSendProposals = true;
				Ct.isHaveSolutionCandidate = false;
				Ct.isTherePlansToProcess =false;
				Ct.isHavingProposalToWaitFor = false;
				Ct.areAllAcceptProposalSent = false;
				//Ct.isCurrentAlternativeSent = false;
			}
		} else{
			System.out.println(" I'am not ready to send Confirm proposal messages !!!! waiting ... ");
			
		}
	
	if(Ct.areAllConfirmProposalSent){
		step = Ct.receivedConfirmationsStep;  // to confirm
	}			

}  // End of  methode sendConfirmProposal



/**
*
* This methode is used by agents to send a proposal confirm.
 * @throws IOException 
*/
private void sendConfirmProposalOld (Agent myAgent) throws IOException {

	Ct.sendConfirmationsCounter++;
	
	// Mise � jour de des co�ts des coalitions en fonction de la validit� des acceptation recues
	Tr.updateAllCoalitionsRealCosts(localPlan.graphPlan, discussionList, maxAcceptMessageValidity);
		
	if( (!Ct.areAllConfirmProposalSent)){
				
		System.out.println(" ->->- Sending Confirmation Messages to the agents : Alternative "+Ct.currentAlternativeSent);
						
		Discussion disc = (Discussion) discussionList.get(Ct.currentAlternativeSent);
		
		System.out.println(" Discussions Real cost in Time is "+disc.getDiscussionRealCostInTime(localPlan.graphPlan));
		System.out.println("Reference Coust is "+Ct.referenceCost);
		
		
		if(disc.getDiscussionRealCostInTime(localPlan.graphPlan) < Ct.referenceCost){
				//-----------------------
			
			System.out.println(" 2 ->->- Sending Confirmation Messages to the agents");
			System.out.println(" -> Coalitions List size : "+disc.coalitionList.size());
				for(int i=0; i<disc.coalitionList.size(); i++){
							
					Coalition c = (Coalition) disc.coalitionList.get(i);
					
					ArrayList sentConfirmCoalitionID = new ArrayList();
					
					System.out.println(" -> agentRecievedAcceptedProposalList size (C Id = "+c.coalitionId+") : "+c.agentRecievedAcceptedProposalList.size());
					
				
					System.out.println("----   Les confirmation de la coalition "+c.coalitionId);
					
					for(int j=c.agentRecievedAcceptedProposalList.size()-1; j>=0; j--){
						String act = (String) c.agentAcceptedAcceptCoalitionIdList.get(j);
						String ag = (String) c.agentRecievedAcceptedProposalList.get(j);
						System.out.println("               --> Information List "+act+" from "+ag+"  is expired !!! (act : "+c.edgeList.toString()+")");
					}
					
					
					
					for(int j=c.agentRecievedAcceptedProposalList.size()-1; j>=0; j--){
						
						String ag = (String) c.agentRecievedAcceptedProposalList.get(j);
						
						
						if((System.currentTimeMillis() - ((long) c.acceptanceAcceptReceivedTime.get(j))) < maxAcceptMessageValidity){
							c.acceptanceAcceptStatus.set(j, 1);
						}else{
							c.acceptanceAcceptStatus.set(j, 0);
						}
						
				//		System.out.println("QQQQQQQQQQQQQQQQQQQQQQQQQ -> "+ (System.currentTimeMillis() - ((long) c.acceptanceAcceptReceivedTime.get(j))));
						
						/*
						// r�cup�ration de l'identifiant de la coalition correpondante � celle de l'agent en cours (acceptation )
						//-------------------------------------------------------------------
						String act1 = (String) c.agentAcceptedAcceptCoalitionIdList.get(j);
						String act2 = act1;
						for(int y=0; y<c.agentAcceptedProposalCoalitionIdList.size(); y++){
							act2 = (String) c.agentAcceptedProposalCoalitionIdList.get(y);
							
							if((System.currentTimeMillis() - (long) c.acceptanceReceivedTime.get(y)) < maxAcceptMessageValidity){
								c.acceptanceStatus.set(y, 1);
							}else{
								c.acceptanceStatus.set(y, 0);
							}
							
							if(((int) c.acceptanceStatus.get(j)) == 1){

								for(int k=0; k<receivedProposals.size(); k++){
									Coalition c2 = (Coalition) receivedProposals.get(k);
									if(c2.)
								}
							}
						}
						//---------------------------------------------------------------------
						
						*/
						
						String act = (String) c.agentAcceptedAcceptCoalitionIdList.get(j);
						
						// agentAcceptedProposalCoalitionIdList
						
						if(!sentConfirmCoalitionID.contains(act)){
							if((int) c.acceptanceAcceptStatus.get(j) == 1){
								if(!c.agentRecievedAcceptedProposalList.get(j).equals(myAgent.getAID().getLocalName())){
									
									sentConfirmCoalitionID.add(act);
									
									System.out.println(" ->->-------------> to the agent : "+ag);
									System.out.println(" ->->-------------> Coalition ID : "+c.agentAcceptedAcceptCoalitionIdList.get(j));
									System.out.println(" ->->-------------> Coalition Tasks : "+c.edgeList.toString());
									
									ACLMessage sendConfirmation = new ACLMessage(ACLMessage.CONFIRM);
									sendConfirmation.addReceiver(getAgentAIDfromString(ag));
									
									sendConfirmation.setContent(act); // � revoir
									sendConfirmation.setConversationId("send_CONFIRM");
									sendConfirmation.setReplyWith("coalitionProposal"+System.currentTimeMillis()); // Unique value
									myAgent.send(sendConfirmation);
									
									mtConfirm = MessageTemplate.and(MessageTemplate.MatchConversationId("send_CONFIRM"),
											MessageTemplate.MatchInReplyTo(sendConfirmation.getReplyWith()));
								
									System.out.println(" CONFIRM message was sent to :"+ag+ " about it's proposal : "+act);
								} // fin de if
							}else{
								if(!c.agentRecievedAcceptedProposalList.get(j).equals(myAgent.getAID().getLocalName())  ){
									System.out.println("               --> Validity of the coalition "+act+" from "+ag+"  is expired !!! (act : "+c.edgeList.toString()+")");
								}
							}								
						}else{
							System.out.println("   --->   Confirm for "+act+" already sent !!");
						}		
					}// fin de for
				} // fin de for
						
				Ct.areAllConfirmProposalSent = true;
			
				step = Ct.receivedConfirmationsStep;
			
				// Mise � jour des param�tres d�tat : 
				//-----------------------------------
				Ct.isHavingProposalToWaitFor = false;
				Ct.isHavingAcceptProposalToWaitFor = false;
				Ct.isNewAcceptProposalsToForm = false;
				Ct.isNewConfirmProposalsToSend = false;
				Ct.isHaveSolutionToConfirm = false;
				Ct.isHavingConfirmProposalToWaitFor = true;
				Ct.timeSinceLastAcceptProposalSent = System.currentTimeMillis();
				Ct.timeSinceLastEvent = System.currentTimeMillis();
			}else{ // fin de if
				System.out.println("Evaluation temps r�el, cout de r�f�rence d�pass� !! Alors, entamer nouvelle discussion");
				Ct.isNewDiscussionToRun = true;
				Ct.isOkayToSendProposals = true;
				Ct.isHaveSolutionCandidate = false;
				Ct.isTherePlansToProcess =false;
				Ct.isHavingProposalToWaitFor = false;
				Ct.areAllAcceptProposalSent = false;
				//Ct.isCurrentAlternativeSent = false;
			}
		} else{
			System.out.println(" I'am not ready to send Confirm proposal messages !!!! waiting ... ");
			
		}
	
	if(Ct.areAllConfirmProposalSent){
		step = Ct.receivedConfirmationsStep;  // to confirm
	}			

}  // End of  methode sendConfirmProposal







//-------------------------------------------------------------------------		
//*************************************************************************
//-------------------------------------------------------------------------
/**
Inner methode sendRejectProposal.
This is the methode used by agents to send a proposal reject.
*/
private void sendRejectProposal() {
		
}  // End of methode class sendRejectProposal
//-------------------------------------------------------------------------		
//*************************************************************************
//-------------------------------------------------------------------------




//-------------------------------------------------------------------------		



		
//-------------------------------------------------------------------------		
//*************************************************************************
//-------------------------------------------------------------------------


/**
 * Relancer le processus de formation de coalition par relancement d'une nouvelle discussion
 * @param myAgent
 */
private void startNewDiscussion(Agent myAgent){
	
	//myAgent.doSuspend();
	
	long time = (System.currentTimeMillis() - lastSentAcceptMessageTimer);
	// attendre le temps que le dernier message d'acceptation soit exp�r�
	if(lastSentAcceptMessageTimer>0)
	if((time) < maxAcceptMessageValidity){
		System.out.println("Attente d'expiration du dernier message d'acceptation envoy�e");
		System.out.println("Time : "+time);
		System.out.println("lastSentAcceptMessageTimer "+lastSentAcceptMessageTimer);
		System.out.println(" maxAcceptMessageValidity " +maxAcceptMessageValidity);
		System.out.println("System.currentTimeMillis() "+System.currentTimeMillis() );
		System.out.println("Time to sleep "+ (maxAcceptMessageValidity - time) );
		
		printSubStepOnRoot("Attente expiration dernier Accept");
		
		try {
			Thread.sleep(maxAcceptMessageValidity - time); } catch (InterruptedException e) { e.printStackTrace();} 
			
		}
	
	
		// Vider la liste des confirmation non prises en compte:
		// les confirmations qui peuvent �tre re�ues pendant que l'agent courant n'etait pas interess� par la discussion et voulait entamer une nouvelle
	
		//-----------------------------------------------------------------
	
			MessageTemplate mtConfirm = MessageTemplate.MatchPerformative(ACLMessage.CONFIRM);
			ACLMessage msgConfirm = myAgent.receive(mtConfirm);
										
			if (msgConfirm != null) {
					while (msgConfirm != null) {
						msgConfirm = myAgent.receive(mtConfirm);
					} // fin de while
				
			} // fin de if
		//------------------------------------------------------------------
	
		// Attendre la sortie des autres agents ayant trouv� une solution
		// si le compteur a d�j� �t� incr�ment�. Donc, possibilit� qu'il y est des agents qui ont quitt�s le syst�mes 
		if(Ct.sendProposalsCounter >0) {
			System.out.println(" Attendre la sortie des autres agents ayant trouv�s une solution (� am�liorer) -> " + timeWaitUpdateAgentsRelatedData);
			
			printSubStepOnRoot("Attente sortie agents");
			try {Thread.sleep(timeWaitUpdateAgentsRelatedData); } catch (InterruptedException e) { e.printStackTrace();}
		}
			
		
	
	// R�initialisation des compteurs
	Ct.resetStepsCounters();
	
	System.out.println(" --> Relancer les discussions par envoie de nouvelles propositions "+sentDiscussionList.toString());
	
	//--------------------------------------------------------------------------------------------------------------------------------
	/*
	System.out.println(" ");
	System.out.println(" ");
	System.out.println(" ---->>>>>>              -->   Etat des n�gociations : ");
	for(int i=0; i<discussionList.size(); i++){
		Discussion disc = (Discussion) discussionList.get(i);
		System.out.println("Profil de la discussion "+disc.discussionId+" est                     "+disc.discussionProfil.toString());
		
		for(int j=0; j<newAgentsOrder.size(); j++){
			String agent  = (String) newAgentsOrder.get(j);
			System.out.println("l'empreinte atuelle de la proposition de l'agent "+agent+" est "+(float) newAgentsFootPrint.get(j));
			
			for(int y=0; y<oldAgentsOrder.size(); y++){
				String ag = (String) oldAgentsOrder.get(y);
				
				if(ag.equals(agent)){
					System.out.println("l'empreinte anciennes de la proposition de de l'agent "+agent+" est "+ (float)oldAgentsFootPrint.get(y));
				}
			}
		}
		
	}
	*/
	//----------------------------------------------------------------------------------------------------------------------------------
	
	System.out.println(" Liste des discussion envoy�es : ");
	
	Ct.isNewProposalToSend = true;
	Ct.isOkayToSendProposals = true;
	
	if(Ct.isNewDiscussionToRun){
		
		// Ct.hasDiscListOrderBeenChanged = true;
		
	//	if(Ct.alternativesCount >= (Ct.currentAlternative +1)){ //Old
		if(Ct.alternativesCount >= (Ct.currentAlternative +1)){
			// Mise � jour de l'ordre et des param�tres des discussiossions
			//------------------------------------------------------------
		//	this.updateDiscussionOrder();
			
		//	Ct.isCurrentAlternativeSent = false;
			Ct.isNewProposalToSend = true;
			
			
			
			
		}else{
			
			if(Ct.alternativesCount <= Ct.currentAlternative){
				Ct.needRestardProcess = true; // si pas d'alternative � envoyer, boucler sur la reception de d'acceptation et de confirmation 2 fois.
			}
			/*
			if((Ct.receiveProposalsCounter >=2) && (Ct.receiveAcceptProposalsCounter >=2)){ 
				Ct.needRestardProcess = true; // si pas d'alternative � envoyer, boucler sur la reception de d'acceptation et de confirmation 2 fois.
			}
			*/
		}
	}

	/*
	newDiscussionWaitingTime -= (System.currentTimeMillis() - timeLastOccuredEvent);
	timeLastOccuredEvent = (long) System.currentTimeMillis() ;
	*/
	
	
	
	System.out.println(" avant ==>  Le restant du temps principal -------->>> " + newDiscussionWaitingTime);
		
	
		
	newDiscussionWaitingTime	=  newDiscussionWaitingTime -  ((long) System.currentTimeMillis() - timeLastOccuredEvent);
	timeLastOccuredEvent = (long) System.currentTimeMillis() ;
	System.out.println(" apres ==>  Le restant du temps principal -------->>> " + newDiscussionWaitingTime);
	try {
		Thread.sleep(newDiscussionWaitingTime); } catch (InterruptedException e) { e.printStackTrace();}
	
	// TODO � enlever ou � am�liorer ..!!!
	//allProcessNeededTime+= totalTimeIncrementationValue;
	//sendProposalsWaitingTime+= totalTimeIncrementationValue;
	
	System.out.println(" apres ==>  Nouvelle valeur de allProcessNeededTime -------->>> " + allProcessNeededTime);
	System.out.println(" apres ==>  Nouvelle valeur de sendProposalsWaitingTime -------->>> " + sendProposalsWaitingTime);
	
	newDiscussionWaitingTime = (allProcessNeededTime);
	timeLastOccuredEvent = (long) System.currentTimeMillis();
}



//-------------------------------------------------------------------------		
//*************************************************************************
//-------------------------------------------------------------------------







//-------------------------------------------------------------------------		


/**
*
*This methode is used by agents to form the first coalitions.
 * @throws FIPAException 
 * @throws IOException 
*
**/
private void formCoalitionsProposal(Agent myAgent) throws FIPAException, IOException {

	Ct.formNewCoalitionsProposalsCounter++;
	// Perform the Coalition Formation Process
	//System.out.println("It's time to look if I have all the plans in order to start the coalition formation process ....");
	//System.out.println("agentsPlansList --> "+agentsPlansList.size());

	if(agentsPlansList.size()>=needPlansNbr){
		System.out.println(" Good, I have recevied all the Plans, let's get started .....");
		System.out.println(" Start ....  Looking for possible coalitions ...");
	
		int localPlanPathsNbr  = localPlan.pathList.size();
		
		boolean hasNewProposalFormed = false;
		//System.out.println(" My Total Alternatives Number is : "+localPlanPathsNbr);
		
		// parcours des alternatives possibles et calcul des coalitions possibles, puis des discussions possibles
		//--------------------------------------------
		
		for(int i=0; i<localPlan.pathList.size(); i++){
			Graph path = (Graph) localPlan.pathList.get(i);
			
			Discussion disc = new Discussion(myAgent.getAID().getLocalName(), path, planMgmt.getActionCostsList(), maxAcceptMessageValidity, db);
			
			for(Edge act:path.getEachEdge()){
				ArrayList agList = new ArrayList();
				ArrayList edgList = new ArrayList();
				
				edgList.add(act.getId());
				
				
				for(int j=0; j< agentsPlansList.size(); j++){
					AgentPlan agPlan = (AgentPlan) agentsPlansList.get(j);
					
					for(Edge ed:agPlan.graphPlan.getEachEdge()){
						if(ed.getId().equals(act.getId())){
							if(!agList.contains(agPlan.agentOwner)){
								agList.add(agPlan.agentOwner);
							}
						}
					}
				}
					
				Coalition c = new Coalition(agList, edgList, myAgent.getLocalName());
				//c.evaluateCoalitionEstimatedCost(localPlan.graphPlan);
				c.discussionId = disc.discussionId;
				allCoalitionsList.add(c);
			}

			
		//	disc.evaluateEstimatedCost(localPlan.graphPlan); // evaluation du co�t d'une discussion
		//	disc.evaluateIndividualCost(localPlan.graphPlan); // evaluation du co�t individuel d'une discussion
			discussionList.add(disc);
			
			
			// calcul du profil de la discussion
			for(Edge e1:localPlan.graphPlan.getEachEdge()){
				boolean exist  = false;
				for(Edge e2:disc.discussionPath.getEachEdge()){
					if(e1.getId().equals(e2.getId())){
						exist = true;
						break;
					}
				}
				if(exist){
					disc.discussionProfil.add(1);
				}else{
					disc.discussionProfil.add(0);
				}
			}
			
			//System.out.println("**> Plan : "+localPlan.graphPlan.getEdgeSet().toString());
			//System.out.println("**> Disc Path : "+disc.discussionPath.getEdgeSet().toString());
			//System.out.println("**> Disc profil : "+disc.discussionProfil.toString());
			
			hasNewProposalFormed = true;
		}
		
	
		System.out.println(" --->> Total Possibles Discussions : "+discussionList.size());
		
		
		
		
		// Mise � jour de la liste des coalitions
		

		/*
		System.out.println("--- Total Coalitions before: "+allCoalitionsList.size());
		for(int i =0; i<allCoalitionsList.size(); i++){
			Coalition c = (Coalition) allCoalitionsList.get(i);
			System.out.println("- Coalition Id "+c.coalitionId);
			System.out.println("		with tasks: "+c.edgeList.toString());
		}
		*/
		// Netoyer totalCoalitionList
		//----------------------------
		
		for(int i =0; i<allCoalitionsList.size(); i++){
			Coalition c = (Coalition) allCoalitionsList.get(i);
			
			for(int j=(i+1); j<allCoalitionsList.size(); j++){
				Coalition c1 = (Coalition) allCoalitionsList.get(j);
				
				if(c.edgeList.containsAll(c1.edgeList)){
					allCoalitionsList.remove(c1);
					if(j>0)
					 j--;					 				
				}
			}
		}
		
		/*
		System.out.println("--- Total Coalitions After: "+allCoalitionsList.size());
		for(int i =0; i<allCoalitionsList.size(); i++){
			Coalition c = (Coalition) allCoalitionsList.get(i);
			System.out.println(" - Coalition Id "+c.coalitionId);
			System.out.println("		with tasks: "+c.edgeList.toString());
		}
		*/
		
		for(int i=0; i<discussionList.size(); i++){
			Discussion d1 = (Discussion) discussionList.get(i);
			
			for(int j=0; j<allCoalitionsList.size(); j++){
				Coalition c = (Coalition) allCoalitionsList.get(j);
				
				for(Edge ed:d1.discussionPath.getEachEdge()){
					boolean exist = false;
					
					for(int t=0; t<c.edgeList.size(); t++){
						String task = (String) c.edgeList.get(t);
						if(ed.getId().equals(task)){
							exist = true;
						}
					}
					
					if(exist){
						d1.coalitionList.add(c);
						break;
					}
				}
			}
			
			d1.evaluateEstimatedCostForPropose(localPlan.graphPlan); // evaluation du co�t d'une discussion
			d1.evaluateIndividualCost(localPlan.graphPlan); // evaluation du co�t individuel d'une discussion
			
		}
		
		
		
		
		
		
		
		
		
		/*
		System.out.print(" ---> Liste des discussions Avant r�ordonnancement: ");
		for(int t=0; t<discussionList.size(); t++){
			Discussion d = (Discussion) discussionList.get(t);
			System.out.println(" --->> Discuccion (Id : "+d.discussionId+") :"+t+" est "+d.discussionPath.getEdgeSet().toString());
			System.out.println(" --->> Cout indi :"+d.discussionIndividualCost);
			System.out.println(" --->> Cout Est :"+d.discussionEstimatedCost);
		}
		*/
		
		// R�ordonner les discussion en fonction du cout estim� pour d�finir l'ordre des propositions � faire
		// Selection al�atoire des strat�gies de comportment
		
		
		
		ArrayList tempDiscussionList = discussionList;
		
		// r�ordonner la liste des discussions.
		Ct.hasDiscListOrderBeenChanged = reorderDiscussionsList();
		
		
		if(Ct.hasDiscListOrderBeenChanged){  // si nouvel ordre trouv�, r�initialis� les compteur d'envoie des discussions
		//	Ct.currentAlternative = 0;   // to see A VOIR A VOIR A VOIR A VOIR
			System.out.println("Nouvel Order de discussions -**************************************************");
		}
		
		discussionList.sort(DESCENDING_COMPARATOR_byIndiCost);
		tempDiscussionList.sort(DESCENDING_COMPARATOR_byIndiCost);
		
		System.out.println(" --->> Total Potential Discussions  : "+tempDiscussionList.size());
		
		System.out.println();
		System.out.println(" Liste des discussions Apres r�ordonnancement: ");
		if(firstDiscOrder.isEmpty())
			for(int t22=0; t22<discussionList.size(); t22++){
				Discussion d2 = (Discussion) discussionList.get(t22);
				System.out.println(" --->> Discussion (Id : "+d2.discussionId+") :"+t22+" est "+d2.discussionPath.getEdgeSet().toString());
				System.out.println(" --->> Cout indi :"+d2.discussionIndividualCost);
				System.out.println(" --->> Cout Est :"+d2.discussionEstimatedCost);
				firstDiscOrder.add(d2.discussionId);
				firstDiscOrderCosts.add(d2.discussionIndividualCost);
			}
		
		
		//  mise � jour du nombre d'alt�rnative � discuter. 
		Ct.alternativesCount = discussionList.size();
		
		// le cout de r�f�rence selon les discussions possibles
		Ct.referenceCost = Tr.getReferenceCost(localPlan.graphPlan, discussionList); 
		if(refCost == 0)
		refCost = Ct.referenceCost;
		if(budgetLimit){
			Ct.costLimit = Tr.getCostLimit(discussionList, costLimitPercentage);  // il faut calculer le costLimit uniquement � ce niveau. apres, c'est le reference cost qui verifie les conditions
			if(limitCost == 0){
				limitCost = Ct.costLimit;
				Ct.referenceCost = limitCost;
			}
			
		}
		
		Ct.referenceCost = Tr.getReferenceCost(localPlan.graphPlan, discussionList);
		//Ct.referenceCost = (float) ((float) ( (float) Tr.getMinEstimatedCost(discussionList) + (float) Tr.getMaxEstimatedCost(discussionList)) / 2);
		
		System.out.println(" --->> Reference Cost : "+Ct.referenceCost);
		
		/*
		 
		System.out.println(" -- Bellow : More details  -- ");
		System.out.println(" My reference Cost is : " + Ct.referenceCost);	
		for(int i=0; i<discussionList.size(); i++){
			Discussion dis = (Discussion) discussionList.get(i);
			dis.minimalDisplayWithCosts(dis);
		} // fin de for
		
		*/
		
		/*
		// si la candition sur la limite du  
		if(budgetLimit){
			if(Ct.costLimit < Ct.referenceCost){
				Ct.referenceCost  = Ct.costLimit;
			}
		}
		*/
		
		// Revoir la liste des d�scussion pour enlever celles qui d�passent le cout individuel
		Tr.removeCostlyDiscussionsFromDiscussionList(discussionList, Ct.referenceCost);
		
		//  mise � jour du nombre d'alt�rnative � discuter. 
		Ct.alternativesCount = discussionList.size();
		
		System.out.println(" -----|>>>- After updating, my new Alternatives Number is : "+ Ct.alternativesCount);
		
		
		float minCost = Tr.getMinEstimatedCost(discussionList);
		float maxCost = Tr.getMaxEstimatedCost(discussionList);
	if(1==2){	
		costLimitPercentage = (float) ((100 * minCost) / Ct.referenceCost);
	}	
		System.out.println(" -----|>>>- initialisation .... ! ");
		System.out.println(" -----|>>>- Ref Cost: "+ Ct.referenceCost);
		System.out.println(" -----|>>>- minEstimated Cost: "+ minCost);
		System.out.println(" -----|>>>- costLimitPercentage: "+ costLimitPercentage);

		
		// Mise � jour des param�tres d�tat : 
		//-----------------------------------
			Ct.isNewProposalsToForm = false;
			Ct.isNewProposalToSend = false;
			
			if(hasNewProposalFormed){
				Ct.isNewProposalToSend = true;
				Ct.isHavingProposalToWaitFor = true;
				Ct.isOkayToSendProposals =  true;
				Ct.isHavingAcceptProposalToWaitFor = true;
			}else{
				Ct.isNewProposalToSend = false;
				Ct.isHavingProposalToWaitFor = false;
				Ct.isOkayToSendProposals =  false;
				Ct.isHavingAcceptProposalToWaitFor = false;
			}
			
			Ct.timeSinceLastEvent = System.currentTimeMillis();
			Ct.isTherePlansToProcess = false;
			
			
			// si pas d'alternatives interessantes, l'agent va quitter les syst�me
			if(Ct.alternativesCount==0) {
				Toolkit.getDefaultToolkit().beep();
				Ct.isNoInterestingAlternative = true;
				Ct.isSolutionFound = false;
				}
			
			
	}else{
		
		System.out.println(" Ohh no, I have not received all the plans yet. Still waiting ......");
		System.out.println(" Let's see if ther is need to send my Plan.");
		Ct.isNewProposalToSend = false;
		sendPlan(myAgent);
	}
	
	/*
	if(Ct.formNewCoalitionsProposalsCounter>=2) {
		Ct.resetParameters();
		sendPlan(myAgent);
		Ct.formNewCoalitionsProposalsCounter = 0;
	}
	*/
		
}  // End of inner methode formCoalitionsProposal









/**
 *
 * This methode is used by agents to end the coalitions formation process.
 * @throws IOException 
 * @throws SQLException 
 * @throws FIPAException 
 * @throws InterruptedException 
*/
private void endingCoalitionFormationProcess(Agent myAgent) throws IOException, SQLException, FIPAException, InterruptedException {
	
	//Viewer viewer = localPlan.graphPlan.display();
	Node planRoot =  planMgmt.getRoot(localPlan.graphPlan);
	planRoot.setAttribute("ui.class", "start");
	
	Node planGoal =  planMgmt.getGoal(localPlan.graphPlan);
	planGoal.setAttribute("ui.class", "goal");
		
		if((Ct.currentAlternativeSent>=0) && (discussionList.size()>=1)) {
			if(Ct.isSolutionFound){	
					Discussion disc = (Discussion) discussionList.get(Ct.currentAlternativeSent);
				//	System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
					//disc.printAcceptedPathOnInitialPlan(localPlan.graphPlan, globalRound);
					
					
					disc.printConfirmedFinalPathOnInitialPlan(localPlan.graphPlan, Ct.currentAlternativeSent);
					
					
					
					
				//	disc.computeDiscussionParameters(receivedProposals, discussionList ,localPlan.graphPlan);
				//	disc.computeInvolvedAgentsPathsList(agentsPlansList);
				//	disc.computeMostInvolvedAlternativesList(agentsPlansList);
				//	disc.countInvolvedAlternativesNodesByAgent(agentsPlansList);
				//	disc.computeTotalBifurcationNodesList(agentsPlansList);
					
				//	disc.displayInvolvedAlternatives(agentsPlansList);
				//	disc.displayNumberInvolvedAlternatives(agentsPlansList);
				//	disc.displayMostInvolvedAlternativesList(agentsPlansList);
					
					
					
				//	System.out.println(" ========================================================");
				//	System.out.println(" involvedAgentsList siez : "+disc.involvedAgentsList.size());
				//	System.out.println(" countInvolvedAlternativesByAgentList : "+disc.countInvolvedAlternativesByAgentList.size());
				//	System.out.println(" involvedAgentsPathsListInTables : "+disc.involvedAgentsPathsListInTables.size());
				//	System.out.println(" ========================================================");
					
				//	disc.displayInvolvedAlternativesNodesAndEdgesByAgent(agentsPlansList);
				//	disc.displayBifurcationNodesListByAgentByAlternative(agentsPlansList);
				//	disc.displayTotalBifurcationNodesPerAgentList(agentsPlansList);
					
					
		
					
					
				//	disc.displayDiscussionParametersByAgent(localPlan, agentsPlansList);
					
				//	disc.displayDistanceToInvolvedAlternatives(agentsPlansList);
					
				//	disc.displayDiscussionParameters();
					disc.putDiscussionParametersInHashTable(Ct);
	
					
					System.out.println(" ========================================================");
					System.out.println(" FINAL RESULTS : ");
					System.out.println(" ---------------");
					System.out.println(" Individual Cost : "+Ct.referenceCost);
					
					System.out.println(" ----> the alternative sent is :"+Ct.currentAlternativeSent);
					System.out.println(" ----> globalNegotiationRound :"+globalNegotiationRound);
					
					if(budgetLimit) System.out.println(" --------------------------->  Cost Limit : "+Ct.costLimit);
					System.out.println(" Discussion Number : "+disc.discussionId);
					System.out.println(" Selected Alternative : "+Ct.currentAlternativeSent);
					System.out.println(" Estimated Cost : "+disc.discussionEstimatedCost);
					disc.evaluateFinalCost(localPlan.graphPlan);
					//System.out.println(" --------------------------->  Real (After acceptation) Cost : "+disc.discussionFinalCost);
					//disc.evaluateFinalCost(localPlan.graphPlan);
					System.out.println(" --------------------------->  Real Final Cost (After Confirmation) : "+disc.discussionFinalCost);
					
					
					System.out.println(" Individual Gained Cost : "+ (Ct.referenceCost - disc.discussionFinalCost));
					disc.evaluateFinalCost(localPlan.graphPlan);
					finalRealCost = disc.discussionFinalCost;
					System.out.println(" Discussion lost gain : "+ (disc.discussionFinalCost - disc.discussionEstimatedCost));
					
					
					if(budgetLimit)
					if(Ct.currentReferenceCost < Ct.costLimit){
						System.out.println(" The budget is not exceeded : "+ Ct.costLimit);
						if(Ct.currentReferenceCost < Ct.referenceCost){
							System.out.println(" R�alisation collective ");
						}else{
							System.out.println(" R�alisation Individuelle");
						}
					}else{
						System.out.println(" The budget is exceeded (budget) "+ Ct.costLimit+" (final Cost) "+disc.discussionFinalCost);
					} 
					
					//refCost =  Ct.referenceCost;
					selectedDisc = disc.discussionId;
					estimatedSelectedDiscCost = disc.discussionEstimatedCost;
					//estimatedCostAfterAccept = disc.discussionFinalCost;
					selectedFinalCost = disc.discussionFinalCost;
					indiGainedCost = Ct.referenceCost - disc.discussionFinalCost;
					discLostGaint =    disc.discussionFinalCost - disc.discussionEstimatedCost;
							
			//	Ct.discussionParameter.put(Ct.GlobalStep, Integer.toString(this.GlobalStep));
			//	Ct.discussionParameter.put(Ct.StepNbr, Integer.toString(this.StepNbr));
			//	Ct.discussionParameter.put(Ct.OrderingStrategy, Integer.toString(this.orderingStrategy));
			//	Ct.discussionParameter.put(Ct.Round, Integer.toString(globalRound));
			//	Ct.discussionParameter.put(Ct.TotalTaskInSystem, Integer.toString(this.totalActionsList.size()));
			//	Ct.discussionParameter.put(Ct.TotalTaskInstances, Integer.toString(Tr.getTotalTaskInstances(totalLocalActionsInstances)));
				
				// insertion des valeurs dans la base de donn�es
				// Insertion des valeurs Globals
			//	if(updateDB) 
			//	db.addNewRow("discussionParameters", Ct.getDiscussionParameterInTableField(), Ct.getDiscussionParameterInTableValue());
		
					
			//TODO � revoir si n�cessit� de garder la capture du plan ou pas !!
			//	localPlan.graphPlan.addAttribute("ui.screenshot", "images/plan_"+myAgent.getLocalName()+".png");
				
				// Insertion des valeurs propres � chaque agents :
			//	if(updateDB)
			//	disc.putDiscussionParametersByAgentInDataBase(localPlan, agentsPlansList, Ct, this.StepNbr, this.GlobalStep);
				
				// Enregistrement de l'exp�rince en cours
			//	writeStepNbr(planMgmt.agent, this.StepNbr);
			//	resetStepNbr(planMgmt.agent);
			//	if(planMgmt.agent.equals("Agent2001"))
			//	writeGlobalStep(agentCourant, this.GlobalStep);
			//	writeGlobalStep(planMgmt.agent, this.GlobalStep);
				
				
				System.out.println("Liste Totale des actions :"+totalActionsList.toString());
				System.out.println("Nombre Total des actions :"+totalActionsList.size());
				System.out.println("Nombre des Instances des Actions Locales :");
			/*	
				int nbrInst = 0;
				for(Edge edg:localPlan.graphPlan.getEachEdge()){
					System.out.println("-->   "+edg.getId()+"    Nbr : "+totalLocalActionsInstances[nbrInst]);
					nbrInst++;
				}
			*/
				
				disc.displayConfirmedFinalPathOnConsole(localPlan.graphPlan, Ct.currentAlternativeSent);
			}
		}else{
			
			planRoot.setAttribute("ui.class", "fail");
			
			
			Ct.discussionParameter.put(Ct.agentMe, myAgent.getLocalName());
			Ct.discussionParameter.put(Ct.GlobalStep, Integer.toString(this.GlobalStep));
			Ct.discussionParameter.put(Ct.StepNbr, Integer.toString(this.StepNbr));
			Ct.discussionParameter.put(Ct.OrderingStrategy, Integer.toString(this.orderingStrategy));
			Ct.discussionParameter.put(Ct.Round, Integer.toString(0));
			Ct.discussionParameter.put(Ct.IndividualCost, Float.toString(Ct.referenceCost));
			Ct.discussionParameter.put(Ct.RealFinalCost, Float.toString(Ct.referenceCost));
			Ct.discussionParameter.put(Ct.IndividualGainedCost, Float.toString(0));
			
			Ct.discussionParameter.put(Ct.TotalTaskInSystem, Integer.toString(this.totalActionsList.size()));
			Ct.discussionParameter.put(Ct.TotalTaskInstances, Integer.toString(Tr.getTotalTaskInstances(totalLocalActionsInstances)));
		
			if(updateDB) 
			db.addNewRow("discussionParameters", Ct.getDiscussionParameterInTableField(), Ct.getDiscussionParameterInTableValue());
			
			// Enregistrement de l'exp�rince en cours
			//	writeStepNbr(planMgmt.agent, this.StepNbr);
			//	resetStepNbr(planMgmt.agent);
				// � faire par l'agent mainAgent
				//if(planMgmt.agent.equals("Agent2001"))
				//writeGlobalStep(agentCourant, this.GlobalStep);
			//	writeGlobalStep(planMgmt.agent, this.GlobalStep);   
				
				
				
				
				System.out.println("Liste Totale des actions :"+totalActionsList.toString());
				System.out.println("Nombre Total des actions :"+totalActionsList.size());
				System.out.println("Nombre des Instances des Actions Locales :");
			/*
			  	int nbrInst = 0;
			 
				for(Edge edg:localPlan.graphPlan.getEachEdge()){
					System.out.println("-->   "+edg.getId()+"    Nbr : "+totalLocalActionsInstances[nbrInst]);
					nbrInst++;
				}
			*/	
		
			
				
				System.out.println(" ========================================================");
				System.out.println(" FINAL RESULTS : ");
				System.out.println(" ---------------");
				System.out.println(" ----> the alternative sent is :"+Ct.currentAlternativeSent);
				System.out.println(" ----> globalNegotiationRound :"+globalNegotiationRound);
				
				//Discussion disc = (Discussion) discussionList.get(Ct.currentAlternativeSent);
				// System.out.println(" ----> the path wanted is :"+lastValidDisc.discussionPath.getEdgeSet().toString());
			
				System.out.println(" Individual Cost : "+Ct.referenceCost);
				if(budgetLimit) System.out.println(" --------------------------->  Cost Limit : "+Ct.costLimit);
				
				
				System.out.println(" Selected Alternative : "+Ct.currentAlternativeSent);
				
				
				
				
				if(Ct.alternativesCount!=0) {
					System.out.println(" Last Valid discussion Number : "+lastValidDisc.discussionId);
					System.out.println(" Last valid discussion Estimated Cost : "+lastValidDisc.discussionEstimatedCost);
					System.out.println(" --------------------------->  LMast valid discussion Real (Final) Cost  : "+lastValidDisc.discussionFinalCost);
					System.out.println(" Last valid discussion Individual Gained Cost : "+ (Ct.referenceCost - lastValidDisc.discussionFinalCost));
					System.out.println(" Last Valid discussion Discussion lost gain : "+ (lastValidDisc.discussionFinalCost - lastValidDisc.discussionEstimatedCost));
				}
				
			
				Tr.allGraphEdgeInInitialColor(localPlan.graphPlan);
		}
		
		if(Ct.currentAlternativeSent==0) System.out.println("No alternative sent !!!!!");
		if(discussionList.size()==0) System.out.println("No Discussion !!!!!");
		
	//	Graph finalAgreedPath = disc.getFinalPathToDisplay();
	//	finalAgreedPath.addAttribute("ui.quality");
	//	finalAgreedPath.addAttribute("ui.antialias");
	//	Viewer viewer = finalAgreedPath.display();
	//  Bloquer les agent apr�s 100 round.
		
		planRoot.setAttribute("ui.label", " Alt("+Ct.currentAlternativeSent+") - Round("+globalNegotiationRound+") "+" "+myAgent.getLocalName());

		
		
		
		//printAllPlans(myAgent.getLocalName());
		
		
		// Affichage de toutes les propositions re�ues : 
		//	Tr.diplayReceivedProposalsList(receivedProposals, " de tous les agents");
		
		/*
		for (int i = 0; i < agentsList.length; i++) {
			System.out.println("Les propositions envoy�es par l'agent : "+agentsList[i].getLocalName());
			Tr.diplayReceivedProposalsList(Tr.getReceivedProposalsFromAgent(receivedProposals, agentsList[i].getLocalName()), " de L'agent "+agentsList[i].getLocalName());
		}
		*/
		
		/*
		for (int i = 0; i < agentsList.length; i++) {
			System.out.println("Les propositions re�ues avec la pr�sence de l'agent : "+agentsList[i].getLocalName());
			Tr.diplayReceivedProposalsList(Tr.getReceivedProposalsWhereAgentIn(receivedProposals, agentsList[i].getLocalName()), " avec l'agent pr�sent : "+agentsList[i].getLocalName());
		}
		*/
		/*
		for (int i = 0; i < agentsList.length; i++) {
			System.out.println("Les propositions envoy�es par l'agent : "+agentsList[i].getLocalName() + " Avec la pr�sence de l'agent Agent2" );
			Tr.diplayReceivedProposalsList(Tr.getReceivedProposalsFromAgentWhereAgentIn(receivedProposals, agentsList[i].getLocalName(), "Agent2"), " avec l'agent pr�sence: Agent2");
		}
		*/
		
		
		/*
		for (Edge edge : localPlan.graphPlan.getEachEdge()) {
			System.out.println("Les propositions re�ues concentnant l'action  : "+edge.getId() );
			Tr.diplayReceivedProposalsList(Tr.getReceivedProposalsConcerningAction(receivedProposals, edge.getId()), " Concernant l'action : "+edge.getId());
		}
		*/
		
		/*
		for (Iterator iterator = discussionList.iterator(); iterator.hasNext();) {
			Discussion disc = (Discussion) iterator.next();
			
			System.out.println("The needed Accepts for the discussion : "+disc.discussionPath.getEdgeSet().toString());
			System.out.println(" Is : "+Tr.getNeededAcceptToGet(receivedProposals, disc));
			
		}
		*/
		
		
		
		
		
		
		
		
		
		Tr.updateDiscussionsState(receivedProposals, discussionList);
		Tr.updateDiscussionsParameters(receivedProposals, discussionList, localPlan.graphPlan);
	 
		//	Tr.displayAllDiscussionsParameters(receivedProposals, discussionList);
		
		discussionsProbability.clear();
	
		
		/*
		discussionsProbability = Tr.computeDiscussionsProbability(discussionList, sentDiscussionFrequency, actionsFrequencyByAgent, agentsListStr, 
				agentsHaveMyPlan, localPlan, planMgmt,	oldAgentsOrder,  newAgentsOrder, 
				val, newAgentsPropoProfile, oldAgentsPropoProfile, lastSendDiscProfile, globalNegotiationRound, maxRound, agentsPlansList);
		*/
		discussionsProbability = Tr.computeDiscussionsProbability(discussionList, sentDiscussionList, actionsFrequencyByAgent, agentsListStr, 
				agentsHaveMyPlan, localPlan, planMgmt,	oldAgentsOrder,  newAgentsOrder, 
				val, newAgentsPropoProfile, oldAgentsPropoProfile, lastSendDiscProfile, globalNegotiationRound, maxRound, agentsPlansList);
		

		
		System.out.println(" ----------------  Probabilit�s des Discussions --------------------- ");
		//Enumeration names;
		int key;
		
		//names = discussionsProbability.keys();
		
		for(int i=0; i<discussionList.size(); i++){
			Discussion disc = (Discussion) discussionList.get(i);
			int discId = disc.discussionId;
			System.out.println("Discussion en position 2--> "+i+" avec un Id = "+discId+"  --> "+ Float.parseFloat((String) discussionsProbability.get(Integer.toString(discId))) );
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		System.out.println("");
		System.out.println("");
		
		
		//------------------------------------------------------------------------
		System.out.println(" InitialCostLimitPercentage : "+ initialCostLimitPercentage);
		System.out.println(" CostLimitPercentage : "+ costLimitPercentage);
		System.out.println(" Final Result Type : "+ resultType);
		System.out.println(" -- Blocking the Agent --");
		System.out.println(" -- Displaying All Plans suspended !!! ");
		
		// Affichage de l'�tat de validit� des coalitions
		//	Tr.displayCoalitionsAcceptValidity(discussionList);
		//	if(Ct.isSolutionFound)
		 DFService.deregister(myAgent);
		
		
		//Thread.sleep(90000);
	//	Viewer viewer = localPlan.graphPlan.display();
				
		if((sysExit) && (Ct.currentAlternativeSent == 0) && (resultType == 2)){
			
			//myAgent.doDelete();
			//viewer.close();
			try {
				writeStatut(0); } catch (IOException e5) { e5.printStackTrace(); }
			
			//System.exit(0);
			this.takeDown();
			this.doDelete();
			this.doSuspend();
		} 	
		
		
		
		
		// ----------------------------
		// Sonner la fin
		for(int i = 0 ; i < 4; i++){
			Toolkit.getDefaultToolkit().beep();
				try{
					Thread.sleep(130);
				}catch(Exception e){
			}
		}
		//-----------------------------
		
	//	System.exit(0);
		myAgent.doSuspend();
	
	System.out.println(" Cost evolution :"+costTraking.toString());
	System.out.println(" Final tried to confirm costs receivedConfirmProposal Step: "+finalRealCostListReceivedConfirmProposal.toString());
	System.out.println(" Their estimated costs receivedConfirmProposal Step :"+estimatedCostListReceivedConfirmProposal.toString());
	
	
	System.out.println(" Final tried to confirm costs UpdateAfterAcceptProposals Step: "+finalRealCostListUpdateAfterAcceptProposals.toString());
	System.out.println(" Their estimated costs UpdateAfterAcceptProposals Step :"+estimatedCostListUpdateAfterAcceptProposals.toString());
	
	Tr.displayMinMaxIndividualCost(discussionList);
	System.out.println(" Final Cost : "+finalRealCost);
	
	
	System.out.println(" ==>  Le restant du temps principal -------->>> " + newDiscussionWaitingTime);
	
	System.out.println("Suite des Propositions enviy�es : "+sentDiscussionList.toString());
	System.out.println("Frequence des envoies des Propositions : "+sentDiscussionFrequency.toString());
	System.out.println("Suite des couts respectifs : "+sentDiscussionEstimatedCostList.toString());
	System.out.println("Suite des couts estim�s accept�s respectifs : "+sentDiscEstAccCostList.toString());
	saveDiscussionProbabiloities(0);
	
	// TODO � enlever ou � am�liorer
	resetSaveedDiscussionProbabiloities();
	
	if((resultType != 2) && (globalNegotiationRound==maxRound) )
			localPlan.printFailledNegotiation(globalNegotiationRound);
	
		
}  // End of  methode endingCoalitionFormationProcess














/**
 *
 * This methode is used by agents to end the coalitions formation process, mais si possibilit� de continuer les n�gociation, l'agent relance....
 * @throws IOException 
 * @throws SQLException 
 * @throws FIPAException 
 * @throws InterruptedException 
*/
private void endingCoalitionFormationProcessWithResumption(Agent myAgent) throws IOException, SQLException, FIPAException, InterruptedException {
	
	boolean needResumption = false;
	
	//Viewer viewer = localPlan.graphPlan.display();
	Node planRoot =  planMgmt.getRoot(localPlan.graphPlan);
	planRoot.setAttribute("ui.class", "start");
	
	Node planGoal =  planMgmt.getGoal(localPlan.graphPlan);
	planGoal.setAttribute("ui.class", "goal");
		
		if((Ct.currentAlternativeSent>=0) && (discussionList.size()>=1)) {
			if((Ct.isSolutionFound) || (isSolWithResumption)){	
				
				int discSentId = 0;
				
				if(discToSend!=0)
				for(int h=0; h<discussionList.size(); h++){
					Discussion dd = (Discussion) discussionList.get(h);
					if(dd.discussionId == discToSend)
						discSentId =h;
				}
				
				
					Discussion disc;
					disc = (Discussion) discussionList.get(0);
					
					if(discToSend!=0)
					disc = (Discussion) discussionList.get(discSentId);
					
					
					/*
					if((isSolWithResumption)){ // si une solution est d�j� trouv�, et pas de solution courante, alors charger l'ancienne
						//disc = lastSolDisc; 
						disc = (Discussion) discussionList.get(Ct.currentAlternativeSent);
						//lastSolDisc = disc;
					}else{
						// TODO Check for better Alternative
						if(discussionList.size()>1){
							System.out.println("Check for better Alternative............");
						}
						
					//lastSolDisc = disc;
					}
					*/
					
					isSolWithResumption = true; // si une solution est trouv�e une premi�re fois alors, mettre isSolWithResumption � true;
					
					
					// Mise � jour de la liste des discussions restantes possibles : 
					//--------------------------------------------------------------
					Tr.updateRemainingPossibleDiscussion(discussionList, allCoalitionsList);
					
					 disc = Tr.getFinalBestDiscussion(localPlan.graphPlan,discussionList);
					
					lastSolDisc = disc;
					
					disc.evaluateFinalCost(localPlan.graphPlan);			
					
					disc.printConfirmedFinalPathOnInitialPlan(localPlan.graphPlan, Ct.currentAlternativeSent);
									
					disc.putDiscussionParametersInHashTable(Ct);
	
					
					System.out.println(" ========================================================");
					System.out.println(" FINAL RESULTS : ");
					System.out.println(" ---------------");
					System.out.println(" Individual Cost : "+Ct.referenceCost);
					
					System.out.println(" ----> the alternative sent is :"+Ct.currentAlternativeSent);
					System.out.println(" ----> globalNegotiationRound :"+globalNegotiationRound);
					
					if(budgetLimit) System.out.println(" --------------------------->  Cost Limit : "+Ct.costLimit);
					System.out.println(" Discussion Number : "+disc.discussionId);
					System.out.println(" Selected Alternative : "+Ct.currentAlternativeSent);
					System.out.println(" Estimated Cost : "+disc.discussionEstimatedCost);
					//disc.evaluateRealCost(localPlan.graphPlan);
					//System.out.println(" --------------------------->  Real (After acceptation) Cost : "+disc.discussionRealCost);
					disc.evaluateFinalCost(localPlan.graphPlan);
					System.out.println(" --------------------------->  Real Final Cost (After Confirmation) : "+disc.discussionFinalCost);
					
					
					System.out.println(" Individual Gained Cost : "+ (Ct.referenceCost - disc.discussionFinalCost));
					finalRealCost = disc.getDiscussionRealCostInTime(localPlan.graphPlan);
					System.out.println(" Discussion lost gain : "+ (disc.discussionFinalCost - disc.discussionEstimatedCost));
					
					
					if(budgetLimit)
					if(Ct.currentReferenceCost < Ct.costLimit){
						System.out.println(" The budget is not exceeded : "+ Ct.costLimit);
						if(Ct.currentReferenceCost < Ct.referenceCost){
							System.out.println(" R�alisation collective ");
						}else{
							System.out.println(" R�alisation Individuelle");
						}
					}else{
						System.out.println(" The budget is exceeded (budget) "+ Ct.costLimit+" (final Cost) "+disc.discussionFinalCost);
					} 
					
					//refCost =  Ct.referenceCost;
					selectedDisc = disc.discussionId;
					estimatedSelectedDiscCost = disc.discussionEstimatedCost;
					//estimatedCostAfterAccept = disc.discussionFinalCost;
					disc.evaluateFinalCost(localPlan.graphPlan);
					selectedFinalCost = disc.discussionFinalCost;
					indiGainedCost = Ct.referenceCost - disc.discussionFinalCost;
					discLostGaint =    disc.discussionFinalCost - disc.discussionEstimatedCost;
							
			
	
			//	writeStepNbr(planMgmt.agent, this.StepNbr);
		
				
				disc.displayConfirmedFinalPathOnConsole(localPlan.graphPlan, Ct.currentAlternativeSent);
				
				
				
				
				
				System.out.println("Liste des discussion restantes possibles sont : ");
				for(int i=0; i<discussionList.size(); i++){
					Discussion remainingDisc = (Discussion) discussionList.get(i);
					System.out.println("Disc Id:" +remainingDisc.discussionId+ " with path: "+remainingDisc.discussionPath.getEdgeSet().toString() );
				}
				
				// TODO : � v�rifier s'il y a possibilit� d'am�liorer la solution. Si par exemple, uniquement 
				// update for resumption...
				// v�rification s'il est int�ressant de relancer // Done
				// mise � jour des diff�rents cout.
				// Mise � jour du cout de reference
				
				// Checking if there is need to proceed to a resumption in order to improve the solution.
				needResumption = disc.checkNeedResumption();
				if(globalNegotiationRound>=maxRound) needResumption = false;
				
				
			
					
				
			}
		}else{
			
			planRoot.setAttribute("ui.class", "fail");
			
			
			Ct.discussionParameter.put(Ct.agentMe, myAgent.getLocalName());
			Ct.discussionParameter.put(Ct.GlobalStep, Integer.toString(this.GlobalStep));
			Ct.discussionParameter.put(Ct.StepNbr, Integer.toString(this.StepNbr));
			Ct.discussionParameter.put(Ct.OrderingStrategy, Integer.toString(this.orderingStrategy));
			Ct.discussionParameter.put(Ct.Round, Integer.toString(0));
			Ct.discussionParameter.put(Ct.IndividualCost, Float.toString(Ct.referenceCost));
			Ct.discussionParameter.put(Ct.RealFinalCost, Float.toString(Ct.referenceCost));
			Ct.discussionParameter.put(Ct.IndividualGainedCost, Float.toString(0));
			
			Ct.discussionParameter.put(Ct.TotalTaskInSystem, Integer.toString(this.totalActionsList.size()));
			Ct.discussionParameter.put(Ct.TotalTaskInstances, Integer.toString(Tr.getTotalTaskInstances(totalLocalActionsInstances)));
		
			if(updateDB) 
			db.addNewRow("discussionParameters", Ct.getDiscussionParameterInTableField(), Ct.getDiscussionParameterInTableValue());
			
		
			//	writeStepNbr(planMgmt.agent, this.StepNbr);
			
				
				
				
				System.out.println("Liste Totale des actions :"+totalActionsList.toString());
				System.out.println("Nombre Total des actions :"+totalActionsList.size());
				System.out.println("Nombre des Instances des Actions Locales :");
		
		
			
				
				System.out.println(" ========================================================");
				System.out.println(" FINAL RESULTS : ");
				System.out.println(" ---------------");
				System.out.println(" ----> the alternative sent is :"+Ct.currentAlternativeSent);
				System.out.println(" ----> globalNegotiationRound :"+globalNegotiationRound);
				
			
				System.out.println(" Individual Cost : "+Ct.referenceCost);
				if(budgetLimit) System.out.println(" --------------------------->  Cost Limit : "+Ct.costLimit);
				
				
				System.out.println(" Selected Alternative : "+Ct.currentAlternativeSent);
				
				
				
				
				if(Ct.alternativesCount!=0) {
					System.out.println(" Last Valid discussion Number : "+lastValidDisc.discussionId);
					System.out.println(" Last valid discussion Estimated Cost : "+lastValidDisc.discussionEstimatedCost);
					System.out.println(" --------------------------->  LMast valid discussion Real (Final) Cost  : "+lastValidDisc.discussionFinalCost);
					System.out.println(" Last valid discussion Individual Gained Cost : "+ (Ct.referenceCost - lastValidDisc.discussionFinalCost));
					System.out.println(" Last Valid discussion Discussion lost gain : "+ (lastValidDisc.discussionFinalCost - lastValidDisc.discussionEstimatedCost));
				}
				
			
				Tr.allGraphEdgeInInitialColor(localPlan.graphPlan);
		}
		
		if(Ct.currentAlternativeSent==0) System.out.println("No alternative sent !!!!!");
		if(discussionList.size()==0) System.out.println("No Discussion !!!!!");
		
		
		planRoot.setAttribute("ui.label", " Alt("+Ct.currentAlternativeSent+") - Round("+globalNegotiationRound+") "+" "+myAgent.getLocalName());

			
		
		Tr.updateDiscussionsState(receivedProposals, discussionList);
		Tr.updateDiscussionsParameters(receivedProposals, discussionList, localPlan.graphPlan);
	 
		//	Tr.displayAllDiscussionsParameters(receivedProposals, discussionList);
		
		discussionsProbability.clear();
	
		/*
		discussionsProbability = Tr.computeDiscussionsProbability(discussionList, sentDiscussionList, actionsFrequencyByAgent, agentsListStr, 
				agentsHaveMyPlan, localPlan, planMgmt,	oldAgentsOrder,  newAgentsOrder, 
				val, newAgentsPropoProfile, oldAgentsPropoProfile, lastSendDiscProfile, roundWhatToDoNext, agentsPlansList);
		*/
		System.out.println(" ----------------  Probabilit�s des Discussions --------------------- ");
		
		int key;
		
	
	/*	
		for(int i=0; i<discussionList.size(); i++){
			Discussion disc = (Discussion) discussionList.get(i);
			int discId = disc.discussionId;
			System.out.println("Discussion en position 2--> "+i+" avec un Id = "+discId+"  --> "+ Float.parseFloat((String) discussionsProbability.get(Integer.toString(discId))) );
		}
		
	*/	
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		if( ( (!Ct.isSolutionFound) && (!isSolWithResumption) )      ){
			//refCost =  Tr.getMinEstimatedCost(discussionList);
			selectedDisc = -1;
			estimatedSelectedDiscCost = -1;
			estimatedCostAfterAccept = -1;
			selectedFinalCost = -1;
			indiGainedCost = -1;
			discLostGaint = -1;
			}
			
			
		
		System.out.println("");
		
		
		
		
		//------------------------------------------------------------------------
		System.out.println(" InitialCostLimitPercentage : "+ initialCostLimitPercentage);
		System.out.println(" CostLimitPercentage : "+ costLimitPercentage);
		System.out.println(" Final Result Type : "+ resultType);
		System.out.println(" -- Blocking the Agent --");
		System.out.println(" -- Displaying All Plans suspended !!! ");
		
		// Affichage de l'�tat de validit� des coalitions
		//	Tr.displayCoalitionsAcceptValidity(discussionList);
		//	if(Ct.isSolutionFound)
	
		
		
		//Thread.sleep(90000);
	//	Viewer viewer = localPlan.graphPlan.display();
		
		// Si besoin de relancer les n�gotiations:
		// TODO : � v�rifier s'il y a possibilit� d'am�liorer la solution. Si par exemple, uniquement 
		// update for resumption...
		// v�rification s'il est int�ressant de relancer // Done
		// mise � jour des diff�rents cout. // Done
		// r�initialisation des indicateurs.
		if(needResumption){	
				saveDiscussionProbabiloities(globalNegotiationRound);
				globalDiscussionNbr++;
				//fileDiscussionProbability = new File("logs/"+GlobalStep+"_"+localPlan.agentOwner+"_"+globalDiscussionNbr+"_discussionsProbability.txt");
				fileDiscussionProbability = new File("logs/"+GlobalStep+"_"+localPlan.agentOwner+"_"+globalNegotiationRound+"_discussionsProbability.txt");
				
				//update the relaining possible discussions related data. 
				Tr.updateForResumption(localPlan.graphPlan,discussionList);
				// update the agent negotiation rounds related date.
				updateForResumption();
			
		}else{
			
			DFService.deregister(myAgent);
			
			
			if((sysExit) && (Ct.currentAlternativeSent == 0) && (resultType == 2)){
				
				// myAgent.doDelete();
				//this.takeDown();
				//this.doDelete();
				//viewer.close();
				
				try {
					writeStatut(0); } catch (IOException e5) { e5.printStackTrace(); }
				//System.exit(0);
				//this.doSuspend();
				this.takeDown();
				this.doDelete();
				
			} 
			
			// ----------------------------
			// Sonner la fin
			for(int i = 0 ; i < 4; i++){
				Toolkit.getDefaultToolkit().beep();
					try{
						Thread.sleep(130);
					}catch(Exception e){
				}
			}
			//-----------------------------
			
//			System.exit(0);
//			myAgent.doSuspend();
		
			
			
			
			System.out.println(" Cost evolution :"+costTraking.toString());
			System.out.println(" Final tried to confirm costs receivedConfirmProposal Step: "+finalRealCostListReceivedConfirmProposal.toString());
			System.out.println(" Their estimated costs receivedConfirmProposal Step :"+estimatedCostListReceivedConfirmProposal.toString());
			
			
			System.out.println(" Final tried to confirm costs UpdateAfterAcceptProposals Step: "+finalRealCostListUpdateAfterAcceptProposals.toString());
			System.out.println(" Their estimated costs UpdateAfterAcceptProposals Step :"+estimatedCostListUpdateAfterAcceptProposals.toString());
			
			Tr.displayMinMaxIndividualCost(discussionList);
			System.out.println(" Final Cost : "+finalRealCost);
			
			
			System.out.println(" ==>  Le restant du temps principal -------->>> " + newDiscussionWaitingTime);
			
			System.out.println("Suite des Propositions enviy�es : "+sentDiscussionList.toString());
			System.out.println("Frequences des Propositions enviy�es : "+sentDiscussionFrequency.toString());
			
			System.out.println("Suite des couts respectifs : "+sentDiscussionEstimatedCostList.toString());
			System.out.println("Suite des couts estim�s accept�s respectifs : "+sentDiscEstAccCostList.toString());
			saveDiscussionProbabiloities(globalNegotiationRound);
			
			// TODO � enlever ou � am�liorer
			//resetSaveedDiscussionProbabiloities();
			
			
		}  // fin de if(!needResumption)
		//-----------------------------------
		
		
		
		
		

		

	
	if((resultType != 2) && (globalNegotiationRound==maxRound) )
			localPlan.printFailledNegotiation(globalNegotiationRound);
	
		
}  // End of  methode endingCoalitionFormationProcessWithResumption










//-------------------------------------------------------------------------		
//*************************************************************************
//-------------------------------------------------------------------------
/**
 * Dans cette m�thode, on peut impl�menter plusieurs strat�gies.
 * This is the methode used by agents to send a proposal reject.
 * @return the next Step index
 */
private int whatToDoNext(Agent myAgent) {
	
	try {
		Thread.sleep(10); } catch (InterruptedException e) { e.printStackTrace();}

	System.out.println("");
	System.out.println(" --> From :whatToDoNext -- Temps �coul� de la derni�re etape est : "+((long) System.currentTimeMillis() - timeLastOccuredEvent));
	
	

	try {

			System.out.println(" --> Old step: "+oldStep+" Alors attente: "+timeToWait+" - "+((long) System.currentTimeMillis() - timeLastOccuredEvent)+" === "+(timeToWait - ((long) System.currentTimeMillis() - timeLastOccuredEvent)));
			Thread.sleep(timeToWait - ((long) System.currentTimeMillis() - timeLastOccuredEvent));
		
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	/*
	try {
		if(oldStep!= Ct.sendProposalsStep){
			System.out.println(" --> Old step: "+oldStep+" Alors attente: "+(timeToWait - ((long) System.currentTimeMillis() - timeLastOccuredEvent)));
			Thread.sleep(timeToWait - ((long) System.currentTimeMillis() - timeLastOccuredEvent));
		}else{
			System.out.println(" --> Old step: "+oldStep+" Alors attente: "+(sendProposalsWaitingTime - ((long) System.currentTimeMillis() - timeLastOccuredEvent)));
			Thread.sleep(sendProposalsWaitingTime - ((long) System.currentTimeMillis() - timeLastOccuredEvent));
		}
		
		
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
 */
	
	newDiscussionWaitingTime	=  newDiscussionWaitingTime -  ((long) System.currentTimeMillis() - timeLastOccuredEvent);
	timeLastOccuredEvent = (long) System.currentTimeMillis() ;
	
	System.out.println("New Waiting time  newDiscussionWaitingTime :     "+newDiscussionWaitingTime);
	System.out.println("Current Time :     "+System.currentTimeMillis());
	
	if( isNeedToRestartProcess() )
		return Ct.restartProcessStep;
	    else
			if( isNeedToEndTheProcess() )
				return Ct.endCoalitionForlmationProcessStep;
			    else
			    	if( isNeedToLookForPlans() )
			    		return Ct.receivePlansStep;
			    		else
							if( isNeedToReceiveAcceptProposals() )
								return Ct.receiveAcceptProposalsStep; 
								else
									if(isNeedToUpdateAfterAcceptProposals())
										return Ct.updateAfterAcceptProposalsStep;
										else
											if( isNeedToSendAcceptProposals() )
												return Ct.sendAcceptProposalsStep;
												else
													if( isNeedToReceiveProposals() )
														return Ct.receiveProposalsStep;
														else
															if(isNeedToUpdateStateAfterProposalsReception())
																return Ct.updateAfterProposalsReceptionStep;
																else
																	if(isNeedToEvaluateReceivedProposals())
																		return Ct.evaluateReceivedProposalsStep;
																		else
																			if( isNeedToSendProposals() )
																				return Ct.sendProposalsStep;
																				else
																					if( isNeedToStartNewDiscussion() )
																						return Ct.startNewDiscussionStep;
																						else
																							if(isNeedToReceiveConfirmProposals())			
																								return Ct.receivedConfirmationsStep;
																								else
																									if(isNeedToSendConfirmProposals())
																										return Ct.sendConfirmationsStep;	
																										else
																											if( isNeedToFormProposals() )
																												return Ct.formCoalitionsProposalsStep;
																												else
																													//return Ct.sendPlanStep;
																													return Ct.startNewDiscussionStep;
		
	
	
	}  







/**
 * Cette m�thode permet de v�rifier s'il y a besoin de terminer le processus de formation de coalitions.
 * Deux cas peuvent se pr�senter : 
 * 	1 - Le cas ou on a une solution
 * 	2 - Le cas ou on a un echec d'ex�cution. c'est � dire, l'agent n'arrive pas � former des coalitions int�ressantes, meme apres plusieurs rounds. 
 * @return vrai s'il y a besoin, et faux sinon.
 */
public boolean isNeedToEndTheProcess(){
	

	if(Ct.isSolutionFound) return true;
	
	if(Ct.isNoInterestingAlternative) return true;  // aucune alternative n'est interessante
	
	/*
	if((!Ct.isHavingReceivedNewCoalitionProposal) &&
			(!Ct.isNewProposalsToForm) && 
			(!Ct.isNewProposalToSend) && 
			(!Ct.isNewReceivedAcceptProposals) && 
			(!Ct.isNewAcceptProposalsToForm) &&
			(!Ct.isNewAcceptProposalsToSend) &&
			(!Ct.isNewConfirmProposalsToSend) &&
			(!Ct.isOkayToSendProposals) &&
			(!Ct.isHavingProposalToWaitFor) &&
			(!Ct.isHavingAcceptProposalToWaitFor) 
			&& (!Ct.isHavingPlansToLookFor)
			&& (!Ct.isHaveSolutionCandidate)
			&& (!Ct.isHavingConfirmProposalToWaitFor)
			&& (!Ct.isNewConfirmProposalsToForm)
			&& (!Ct.isHavingNewProposalsToEvaluate)
			&& (!Ct.isHaveSolutionToConfirm)
			

	){
		return true;
	} // fin de if
				
*/
	
	if(Ct.sendPlanCounter >maxSendPlanCounter){
		System.out.println("---------------->>  Ending by absence of agents !!!!");
		System.out.println("---------------->>  sendPlanCounter = " +Ct.sendPlanCounter);
		
		return true;
	}
	
	return false;
} // fin de la m�thode 




/**
 * Cette m�thode permet de v�rifier s'il y a besoin de red�marrer le processus de formation de coalitions.
 * @return vrai s'il y a besoin, et faux sinon.
 */
public boolean isNeedToRestartProcess(){
	
	if( 1!=2 ) return false;
	
	if(Ct.needRestardProcess) return true;
	
	if(Ct.receivePlansCounter>2) return true;
	
	if(Ct.receiveProposalsCounter>4) return true;
	
	if(Ct.formNewCoalitionsProposalsCounter>1) return true; // deux passages max sur cette �tapes, sinon, r�initialisation du compteur (probleme du cas des plans qui n'ont pas de taches en commun)  

	return false;
} // fin de la m�thode 







/**
 * Dans cette m�thode, on peut impl�menter plusieurs strat�gies
 * @return vrai ou faux
 */
public boolean isNeedToLookForPlans(){
	
	if((Ct.isHavingPlansToLookFor)
		&& ( Ct.receivePlansCounter<2 )
			
	
			){
		return true;
	} // fin de if
	return false;
} 








/**
 * Dans cette m�thode, on peut impl�menter plusieurs strat�gies
 * @return vrai ou faux
 */
public boolean isNeedToReceiveAcceptProposals(){
	
	if((Ct.isHavingAcceptProposalToWaitFor)
			&&(!Ct.isHavingNewProposalsToEvaluate)
			&&(!isNeedToSendProposals())
			&& (!isNeedToSendAcceptProposals())
			&&(!isNeedToReceiveProposals())
			&&(Ct.areAllAcceptProposalSent)
			&&(Ct.receiveAcceptProposalsCounter<1)
			)
	
		return true;
	
	// if(Ct.receiveAcceptProposalsCounter>=2) Ct.receiveAcceptProposalsCounter=0;
		
	return false;
} 




/**
 * Dans cette m�thode, on peut impl�menter plusieurs strat�gies
 * @return vrai ou faux
 */
public boolean isNeedToUpdateAfterAcceptProposals(){
	if((Ct.isHaveSolutionCandidate)
			&& (!Ct.isHavingAcceptProposalToWaitFor)
			&& (!Ct.isHavingNewProposalsToEvaluate)
			&& (!Ct.isHaveSolutionToConfirm)
			&& (!Ct.isNewAcceptProposalsToSend)
			&&(Ct.updateAfterAcceptProposalsCounter<1)
			&& (!Ct.isNewConfirmProposalsToSend)
			// && (!Ct.isNewAcceptProposalsToForm)
			&&(!isNeedToSendProposals())
			&&(!isNeedToReceiveProposals())
			)
		return true;
	return false;
} 







/**
 * Dans cette m�thode, on peut impl�menter plusieurs strat�gies
 * @return vrai ou faux
 */
public boolean isNeedToSendAcceptProposals(){
	
	if((Ct.isHaveSolutionCandidate)
			&& (Ct.isNewAcceptProposalsToSend)
			&& (Ct.sendAcceptProposalsCounter<1)
			)  // cette form est la plus simple. � revoir.
			return true;

	return false;
} 






/**
 * Dans cette m�thode, on peut impl�menter plusieurs strat�gies
 * @return vrai ou faux
 */
public boolean isNeedToReceiveProposals(){
	
	if((Ct.isHavingProposalToWaitFor) 
		 
		 && (!isNeedToFormProposals())
		 && (!isNeedToSendProposals())
		 && (!Ct.isHaveSolutionToConfirm)
		 && (!Ct.isHaveSolutionCandidate)
		 && (!Ct.isHaveSolutionToConfirm)
		 && (Ct.receiveProposalsCounter <1)
			)
	
		return true;
			
	
	return false;
} 




/**
 * Dans cette m�thode, on peut impl�menter plusieurs strat�gies
 * @return vrai ou faux
 */
public boolean isNeedToUpdateStateAfterProposalsReception(){
	
	if((Ct.isHavingReceivedNewCoalitionProposal) 
		 && (!isNeedToFormProposals())
		 && (!isNeedToSendProposals())
		 && (Ct.updateAfterProposalsReceptionCounter<1)
			)
			return true;
	
	return false;
} 



/**
 * Dans cette m�thode, on peut impl�menter plusieurs strat�gies
 * @return vrai ou faux
 */
public boolean isNeedToEvaluateReceivedProposals(){
	
	if((Ct.isHavingNewProposalsToEvaluate)
		&& (Ct.evaluateReceivedProposalsCounter <1)
			//&&(!isNeedToSendProposals())
			//&&(!isNeedToReceiveProposals())
		)
		
		return true;
	
	return false;
} 


/**
 * Dans cette m�thode, on peut impl�menter plusieurs strat�gies
 * @return vrai ou faux
 */
public boolean isNeedToSendProposals(){

	if((Ct.isNewProposalToSend)
		 && (Ct.sendProposalsCounter < 1)
			//&& (!Ct.isHaveSolutionCondidate) 
			//&& (Ct.isOkayToSendProposals) 
			
			)

	return true;		

	return false;
} 

/**
 * Rmk : On peut impl�menter plusieurs strat�gies sur cette m�thode.
 * @return vrai ou faux
 */
public boolean isNeedToStartNewDiscussion(){
		
	if((!Ct.areAllAlternativesSent)
			//&& (!Ct.isCurrentAlternativeSent) // � d�sactiver peut etre???
			&& (Ct.alternativesCount >= Ct.currentAlternativeSent)
			&& (Ct.isNewDiscussionToRun)
			)
		return true;
	
	return false;
}






/**
 * Dans cette m�thode, on peut impl�menter plusieurs strat�gies
 * @return vrai ou faux
 */
public boolean isNeedToReceiveConfirmProposals(){
	
	if((Ct.isHavingConfirmProposalToWaitFor) 
		&&(Ct.receiveConfirmationsCounter < 1)
		&& (!isNeedToSendConfirmProposals())

	)
			return true;
	
	return false;
} 








/**
 * Dans cette m�thode, on peut impl�menter plusieurs strat�gies
 * @return vrai ou faux
 */
public boolean isNeedToSendConfirmProposals(){
	
	if((Ct.isHaveSolutionToConfirm) 
		&& (Ct.sendConfirmationsCounter < 1)
		)
			return true;
	
	return false;
} 






/**
 * Dans cette m�thode, on peut impl�menter plusieurs strat�gies
 * @return vrai ou faux
 */
public boolean isNeedToFormProposals(){  // � revoir dans le future.
	
	if((Ct.isNewProposalsToForm)
			&& (Ct.isTherePlansToProcess)    // 
			)
			
			return true;
	return false;
}





//-------------------------------------------------------------------------		
/**
 * Afficher l'�tape en cours d'ex�cution
 * @param stepId : Identificateur de l'�tape
 * @param stepName : Descriptif de l'�tape
 */
public void displayStep(int stepId, String stepName){  
	
	System.out.println("");
	System.out.println(" |-  -- --   --------------------------------       -- -- -- -- -|");
	System.out.println("            (GR : "+globalRound+" )   -  Step "+stepId+" : "+stepName+" - ");
	System.out.println(" |-  -- --   --------------------------------       -- -- -- -- -|");

} // fin de la m�thode 

//-------------------------------------------------------------------------		
//*************************************************************************
//-------------------------------------------------------------------------
	

/**
 * R�-rdonner la liste des discussion en fonction de la strat�gie choisie
 * @return vrai si nouvel ordre trouv�, faux sinon.
 * @throws IOException 
 */
public boolean reorderDiscussionsList() throws IOException{
	//orderingStrategy = ThreadLocalRandom.current().nextInt(1, 5);
	
	boolean haseBeenChanged = false;
	
		
			
			// � mettre � jour les cas restants
			
		//	discussionList.clear();
			switch(orderingStrategy){
			
			case 1 :  // Ordonner les alternatives par le co�t estim�
				haseBeenChanged = Tr.reorderDiscussionListByEstimatedCost(localPlan.graphPlan,discussionList);
				break;
			case 2 : // Ordonner les alternatives par la distance entre les alternatives 
				Tr.reorderDiscussionListByDistance(discussionList, this.agentsPlansList);
				break;
			case 3 : // Ordonner les alternatives par ordre d�croissant du nombre Total des noeuds en Bifurcations sur les alternatives 
				discussionList = Tr.reorderDiscussionListByBifurcation(discussionList, this.agentsPlansList, 1);
				break;
			case 4 : // Ordonner les alternatives par ordre croissant du nombre Total des noeuds en Bifurcations sur les alternatives 
				discussionList = Tr.reorderDiscussionListByBifurcation(discussionList, this.agentsPlansList, 2);
				break;
			case 5 : // Ordonner les alternatives par ordre d�croissant de la somme des degr�s des actions (degr� = nombre d'agents estim� sur l'actions)
				discussionList = Tr.reorderDiscussionListByDegree(discussionList, 1);
				break;
			case 6 : // Ordonner les alternatives par ordre croissant de la somme des degr�s des actions (degr� = nombre d'agents estim� sur l'actions)
				discussionList = Tr.reorderDiscussionListByDegree(discussionList, 2);
			break;
			case 7 : // Ordonner les alternatives par ordre croissant de la moyenne des degr�s des actions (degr� = nombre d'agents estim� sur l'actions)
				discussionList = Tr.reorderDiscussionListByDegree(discussionList, 3);
			break;
			case 8 : // Ordonner les alternatives par ordre croissant de la moyenne des degr�s des actions (degr� = nombre d'agents estim� sur l'actions)
				discussionList = Tr.reorderDiscussionListByDegree(discussionList, 4);
			break;
			
			}
			
			
			
			
			
	return haseBeenChanged;
	
	
	
}

/**
 * Mise � jour apr�s qu'un ensemble d'agents ont quitt�s le syst�me (� Completer).
 * R�initialisation des donn�es sans prise en compte du plan de ces agents
 * @param agentsList list des agents pr�sents dans le syst�me (les anciens et nouveaux, sans compter ceux qui ont quitt�s)
 * @return vrai s'il y a eu des agents qui ont quitt�s le syst�me, faux sinon. 
 * @throws IOException 
 */
public boolean updateAgentsRelatedData(String [] agentsList) throws IOException{
	
	ArrayList newAgentList = new ArrayList();
	
	boolean hasBeenchanged = false;
	
	for (int i = 0; i < agentsList.length; ++i) {
		newAgentList.add(agentsList[i]);
	} // fin de for
	if(agentsHaveMyPlan.size()>0){
		for(int i=0; i<agentsHaveMyPlan.size(); i++){
			String ag = (String) agentsHaveMyPlan.get(i);
			if(!newAgentList.contains(ag)){
				System.out.println(" ---> "
						+ " L'agent "+ ag + " n'existe plus dans le syst�me");
				System.out.println(" Suppression de l'agent et des donn�es relative � son plan, ");
				Tr.removeAgentAndRelatedData(localPlan.graphPlan,receivedProposals, discussionList, planMgmt.getActionCostsList(), maxAcceptMessageValidity, ag);
				agentsHaveMyPlan.remove(ag);
				i--;
				hasBeenchanged = true;
				
				// supression du plan de l'agent sortant de la liste des plans
				for(int y=0; y<agentsPlansList.size(); y++){
					AgentPlan agPlan = (AgentPlan) agentsPlansList.get(y);
					if(agPlan.agentOwner.equals(ag)){
						agentsPlansList.remove(agPlan);
						y--;
					}
					
				}
				
			}
		}
	}else{
		System.out.println(" ---->  La liste agentsHaveMyPlan est vide");
	}
	
	return hasBeenchanged;
} 


/**
 * Retourne la liste des agents pr�sents dans le syst�me � l'instant voulu.
 * @param myAgent L'agent en cours
 * @return Tableau de type chaine de caract�re, contenant les agents pr�sents dans le syst�me.
 */
public String[] getAgentsInSystem(Agent myAgent){
	
	String agentsStrList[] = null;
	
	// Update the list of agents
	DFAgentDescription template = new DFAgentDescription();
	ServiceDescription sd = new ServiceDescription();
	sd.setType("form-coalition");
	template.addServices(sd);
	
	try {
		DFAgentDescription[] result = DFService.search(myAgent, template); 
		agentsList = new AID[result.length];
		agentsListStr = new ArrayList();
		
		agentsStrList = new String[result.length];
		for (int i = 0; i < result.length; ++i) {
			agentsList[i] = result[i].getName();
			agentsStrList[i]= result[i].getName().getLocalName();
			agentsListStr.add(result[i].getName().getLocalName());
			if(!allAgentAid.contains(agentsList[i])){
				allAgentAid.add(agentsList[i]);
			}
		}
	}
	catch (FIPAException fe) {
		fe.printStackTrace();
	}
	return agentsStrList;
}




/**
 * Chargement du compteur sur l'�tape locale � un agent.
 * @param agent le nom de l'agent en question
 * @return le compteur (entier)
 * @throws FileNotFoundException
 */
public int loadStepNbr(String agent) throws FileNotFoundException{
	int step = 0;
	File f = new File("config/"+agent+".txt");
	Scanner sc = new Scanner(f);
	while(sc.hasNextInt()) {
		step = sc.nextInt();
		}
	return step;
} // fin de la M�thode


/**
 * Chargement de la strat�gie d'ordonnancement sur l'�tape locale � un agent.
 * @return la strat�gie d'ordonnancement � utiliser
 * @throws FileNotFoundException
 */
public int loadStrategy() throws FileNotFoundException{
	int strategy = 0;
	File f = new File("config/OrderingStrategy.txt");
	Scanner sc = new Scanner(f);
	while(sc.hasNextInt()) {
		strategy = sc.nextInt();
		}
	return strategy;
} // fin de la M�thode

/**
 * Sauvegarde du compteur sur l'�tape locale � un agent
 * @param agent Le nom de l'agent
 * @param step Le compteur � sauvegarder
 * @throws IOException
 */
public void  writeStepNbr(String agent, int step) throws IOException{
	PrintWriter f = new PrintWriter(new FileWriter("config/"+agent+".txt"));
	step++;
	f.write(step+"");
//	f.write(" dsfgdfg  ");
	f.close();
} // fin de la M�thode

/**
 * R�initialisation du compteur de sur l'�tape locale � un agent
 * @param agent Nom de l'agent en question
 * @throws IOException
 */
public void  resetStepNbr(String agent) throws IOException{
	PrintWriter f = new PrintWriter(new FileWriter("config/"+agent+".txt"));
	f.write("1");
	f.close();
} // fin de la M�thode


/**
 * Chargement du compteur sur l'�tape  local � un agent
 * @return un nembre entier (le compteur) 
 * @throws FileNotFoundException
 */
public int loadGlobalStep() throws FileNotFoundException{
	File f = new File("config/GlobalStep.txt");
	Scanner sc = new Scanner(f);
		return sc.nextInt();
} // fin de la M�thode

/**
 * Sauvegarde du compteur des �tapes globales.
 * @param agent Agent en charge de la sauvegarde (un seul parmi les agents)
 * @param GlobalStep Le compteur sur l'�tape globale
 * @throws IOException
 */
public void  writeGlobalStep(String agent, int GlobalStep) throws IOException{
	
	
	if(agent.equals(agentCourant)){
		PrintWriter f = new PrintWriter(new FileWriter("config/GlobalStep.txt"));
		f.write((GlobalStep+1)+"");
		f.close();
	}
} // fin de la M�thode



public void printAllPlans(String agent){
	if(agent.equals("Agent1")){
		Graph[] tab = new Graph[agentsPlansList.size()+1];
		tab[0]=localPlan.graphPlan;
		for(int i=0; i<agentsPlansList.size(); i++){
			AgentPlan ag = (AgentPlan) agentsPlansList.get(i);
			tab[i+1] = ag.graphPlan;
		}
		plansOverview pO = new plansOverview(tab);
		pO.main(tab);
	}
}


























/**
 * Relancer le processus de formation de coalitions.
 * R�initialisation des variables.
 * @param myAgent
 * @throws IOException 
 */
private void restartTheProcess(Agent myAgent) throws IOException {
	// R�initialisaton des variables :
	
	Ct.restartProcessCounter++;
	if(globalNegotiationRound<maxRound){
		globalNegotiationRound = maxRound;
	}

	globalRound++;
	
	displayGlobalRound();
	
	
	Ct.resetParameters();
	//agentsHaveMyPlan.clear();
	agentsPlansList.clear();
	discussionList.clear();
	subSetsList.clear();
	receivedRoundProposals.clear();
	receivedRoundProposalsTimes.clear();
	receivedRoundAcceptsTimes.clear();
	receivedProposals.clear();
	allMyPaths.clear();
	
	//Ct = new Constants();
		
	sendPlan(myAgent);
	// G�n�rer un nouveau fichier.
	//----------------------------

	firstDiscOrder.clear();
	firstDiscOrderCosts.clear();
	sentDiscussionList.clear();
	sentDiscussionFrequency.clear();
	sentDiscussionEstimatedCostList.clear();
	//totalAgent = 1; // � ne pas toucher, �a concerne le nombre d'agents requis pour accepter les coalitions
	//totalTacheTest=0;  // TODO  � revoir si on veut plusieurs processus de n�gociations
	// totalAlternativeTest=0;  // TODO   //� revoir si on veut plusieurs processus de n�gociations
	// indiceTestParTotalTache=0; // TODO   //� revoir si on veut plusieurs processus de n�gociations
	
	globalDiscussionNbr++;
	
	//fileDiscussionProbability = new File("logs/"+GlobalStep+"_"+localPlan.agentOwner+"_"+globalDiscussionNbr+"_discussionsProbability.txt");
	fileDiscussionProbability = new File("logs/"+GlobalStep+"_"+localPlan.agentOwner+"_"+globalNegotiationRound+"_discussionsProbability.txt");
	
} // fin de restartTheProcess


/**
 *  Afficher le round Global.
 *  
 */
public void displayGlobalRound(){
	
	System.out.println("");
	System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
	System.out.println("");
	System.out.println(" -->               Global Round : "+globalRound);
	System.out.println("");
	System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
	System.out.println("");
	
}


/**
 * Ajouter les actions contenues dans un plan � la liste globale de toutes les actions de tous les plans re�us (y compris le plan local)
 * @param localP Plan contenant les actions � ajouter (pas de double)
 */
public void addPlanActionToList(Graph localP){
	for(Edge ed:localP.getEachEdge()){
		if(!totalActionsList.contains(ed.getId())){
			totalActionsList.add(ed.getId());
		}
	}
}

/**
 * Compter les instances des actions locale, par rapport aux autres plans re�us. 
 * @param plan Plan re�u, contenant les actions � v�rifier
 */
public void countLocalActionsInstances(Graph plan){
	int nbr = 0;
	for(Edge edg:localPlan.graphPlan.getEachEdge()){
		for(Edge ed:plan.getEachEdge()){
			if(edg.getId().equals(ed.getId())){
				totalLocalActionsInstances[nbr]+=1;
			}
		}
		nbr++;
	}
	
}


public AID getAgentAIDfromString(String agent){
	// 
	
	for (int i = 0; i < allAgentAid.size(); i++) {
		AID ag = (AID) allAgentAid.get(i);
		if(ag.getLocalName().equals(agent))
			return ag;
	}
	
	return null;
}

/**
 * Remise du label initial du Noeud racine
 */
public void resetInitialRootLabel(){
	if(colorSimulationTracking)
	rootNode.setAttribute("ui.label", (String) rootNode.getAttribute("oldLabel"));
}

/**
 * Mise du label de l'�tape 
 */
public void printStepOnRoot(){
	if(colorSimulationTracking){
		resetInitialRootLabel();
		rootNode.setAttribute("ui.label", localPlan.agentOwner+" ("+currentSentDiscId+") -> "+currentStep);
	}
	
}

/**
 * Ajout du label de de sous �tape 
 * @param subStep L'�tape � afficher sur le noeud racine
 */
public void printSubStepOnRoot(String subStep){
	if(colorSimulationTracking){
		rootNode.setAttribute("ui.label", localPlan.agentOwner+" ("+currentSentDiscId+") -> "+currentStep+" --> "+subStep);
	}
	
}

/**
 * R�ordonner l'ordre des discussions en fonction des param�tres de l'�volution des discussions
 * @param reorderDiscussionByState  Si r�ordonner par �tat d'acceptation et acceptation potentielles
 * @param reorderDiscussionByState2 Si r�ordonner par estimation des co�ts (� revoir)
 * @throws IOException 
 */
public void reorderDiscussionsPriority(boolean reorderDiscussionByState, boolean reorderDiscussionByState2) throws IOException{
	
	// Mise � jour de l'ordre des discussions
	
		if(reorderDiscussionByState){
			Ct.hasDiscListOrderBeenChanged = Tr.reorderAfterProposition(receivedProposals, discussionList);
			Tr.updateDiscussionsParameters(receivedProposals, discussionList, localPlan.graphPlan);
			//Ct.hasDiscListOrderBeenChanged = reorderDiscussionsList();
		}
		
		
		// Mise � jour de l'ordre des discussions par probabilit�s
		//---------------------------------------------------------------
		//--------------------------------------------------------------------
		//--------------------------------------------------------------------
		if(reorderDiscussionByState2){
			
		
			System.out.println(" --> Old Order : Before  -->  computeDiscussionsProbability ");
			for (int i = 0; i < discussionList.size(); i++) {
				Discussion disc = (Discussion) discussionList.get(i);
				System.out.println("Path ID "+disc.discussionId+ "  is " +disc.discussionPath.getEdgeSet().toString());
			}
			
			// Ct.hasDiscListOrderBeenChanged = Tr.updateDiscussionsState(receivedProposals, discussionList);
			Tr.updateDiscussionsParameters(receivedProposals, discussionList, localPlan.graphPlan);
			// Tr.displayAllDiscussionsParameters(receivedProposals, discussionList);
			
	
				
			
			
			discussionsProbability.clear();
			/*
			discussionsProbability = Tr.computeDiscussionsProbability(discussionList, sentDiscussionFrequency , actionsFrequencyByAgent, agentsListStr,
					agentsHaveMyPlan, localPlan, planMgmt, oldAgentsOrder, newAgentsOrder, 
					val, newAgentsPropoProfile, oldAgentsPropoProfile, lastSendDiscProfile, globalNegotiationRound, maxRound, agentsPlansList);
			*/
		discussionsProbability = Tr.computeDiscussionsProbability(discussionList, sentDiscussionList, actionsFrequencyByAgent, agentsListStr, 
				agentsHaveMyPlan, localPlan, planMgmt,	oldAgentsOrder,  newAgentsOrder, 
				val, newAgentsPropoProfile, oldAgentsPropoProfile, lastSendDiscProfile, globalNegotiationRound, maxRound, agentsPlansList);
			
			

			
			
			// sauvegarder les probabilit�s : =
			//---------------------------------
			if(!discussionsProbability.isEmpty()){			
				saveDiscussionProbabiloities(0);
			}
				
						
						
						
			
			
		
			System.out.println(" ----------------  Probabilit�s des Discussions --------------------- ");
			//Enumeration names;
			int key;
			
			//names = discussionsProbability.keys();
			
			for(int i=0; i<discussionList.size(); i++){
				Discussion disc = (Discussion) discussionList.get(i);
				int discId = disc.discussionId;
				
				//String res = (String) discussionsProbability.get(Integer.toString(discId));
				
			//	float t = Float.parseFloat(res);
			//	System.out.println(" long " +t);
//				System.out.println("Discussion prob "+Long.parseLong((String) discussionsProbability.get(Integer.toString(discId))).longValue() );

				System.out.println("Discussion en position 3--> "+i+" avec un Id = "+discId+"  --> "+Float.parseFloat((String) discussionsProbability.get(Integer.toString(discId))) );
			}
			
			
			
			// r�ordonner la liste des disussion par ordre ascendant des indicateur d'acceptation.
			discussionList.sort(ASCENDING_COMPARATOR_byAccInd);
			/*
			for (int i = 0; i < discussionList.size(); i++) {
				
				Discussion disc1 = (Discussion) discussionList.get(i);
				float disc1Prob = Float.parseFloat((String) discussionsProbability.get(Integer.toString(disc1.discussionId))) ;
				
				
				
				for(int y=0; y<discussionList.size(); y++){
					Discussion disc2 = (Discussion) discussionList.get(y);
					float disc2Prob = 	Float.parseFloat((String) discussionsProbability.get(Integer.toString(disc2.discussionId))) ;
					
					
					if(disc1Prob > disc2Prob){
						Discussion discInter =  disc1; 
						int disc1Indice = discussionList.indexOf(disc1);
						int disc2Indice = discussionList.indexOf(disc2); 
						discussionList.set(disc1Indice, disc2);
						discussionList.set(disc2Indice, discInter);
						break;
					}
				}
				
			
			}
			*/
			
			
			System.out.println(" --> New Order 1111111: After  -->  computeDiscussionsProbability ");
			for (int i = 0; i < discussionList.size(); i++) {
				Discussion disc = (Discussion) discussionList.get(i);
				
				System.out.println("Path ID "+disc.discussionId+ "  Accp Indicator  " +disc.acceptaceIndicator);
			}
			
		
			//Ct.hasDiscListOrderBeenChanged = reorderDiscussionsList();
		}
		
		
		//--------------------------------------------------------------------
		//--------------------------------------------------------------------
		//--------------------------------------------------------------------
		
		
}



/**
 * Mise � jour de l'ordre et des param�tres des discussiossions
 */
public void updateDiscussionOrder(){
	
	System.out.println(" -->  updateDiscussionOrder ");
	boolean isNewAlt = false;
	
//	if(!Ct.hasDiscListOrderBeenChanged) {  // si pas de changement dans l'ordre. alors incr�menter le compteur des alternatives � envoyer
		
	// r�initialisation de la liste si boucle : 
	//-----------------------------------------

/*		
	if(sentDiscussionList.size()>3){
		int taille = sentDiscussionList.size();
		if( ((int) sentDiscussionList.get(taille-1) ==   (int) sentDiscussionList.get(taille-2)) 
				&&( (int) sentDiscussionList.get(taille-1) ==  (int) sentDiscussionList.get(taille-3) )) {
			sentDiscussionList.clear();
		}
	}
*/
	
		
	for(int t=0; t<discussionList.size(); t++){
		
		Discussion disc = (Discussion) discussionList.get(t);
		
		// une alternative ne peut etre envoy�e trois fois de suite sans changement
		//if( (sentDiscussionList.contains(disc.discussionId)) && (currentAltSent == disc.discussionId ) ){
		if( (sentDiscussionList.contains(disc.discussionId)) ){
			
			int instanceNbr = 0;
			for(int y=0; y<sentDiscussionList.size(); y++){
				int diId = (int) sentDiscussionList.get(y);
				if(diId == disc.discussionId)
					instanceNbr++;
			}
			
			System.out.println(" Nbr Envoie de l'alternative : "+ disc.discussionId + " est --> "+instanceNbr);
			//dfgdfg
			if(instanceNbr<=300){ // � revoir . � mettre un truc de probabilit�
				Ct.currentAlternative = t;
				Ct.currentAlternativeSent = t;
				//Ct.isCurrentAlternativeSent = true; // to check
				
				System.out.println("--> instanceNbr>=2");
				isNewAlt = true;
				break;		
			}
		}
		
		if(!sentDiscussionList.contains(disc.discussionId)){
			System.out.println("--> !sentDiscussionList.contains(disc.discussionId)");
			   // altSentCounter++;
				Ct.currentAlternative = t;
				Ct.currentAlternativeSent = t;
			//	altSentCounter++;
				currentAltSent =  disc.discussionId;
				isNewAlt = true;
				break;		
		}
	}
					
		//	Ct.currentAlternative++;
//	}
	
	if(!isNewAlt){
		boolean changed = false;
		int indice = 0;
		for(int t=0; t<discussionList.size(); t++){
			System.out.println("--> !isNewAlt");
			Discussion disc = (Discussion) discussionList.get(t);
			if(disc.discussionId != currentAltSent){
				Ct.currentAlternative = t;
				Ct.currentAlternativeSent = t;
				currentAltSent =  disc.discussionId;
				changed = true;
			}
		}
	
		if((!changed) && (discussionList.size()>0)){
			Discussion disc = (Discussion) discussionList.get(0);
			System.out.println("--> !changed");
			Ct.currentAlternative = 0;
			Ct.currentAlternativeSent = 0;
			currentAltSent =  disc.discussionId;
			sentDiscussionList.clear(); // le remplissage dans sendAllAlternatives...
			sentDiscussionEstimatedCostList.clear();
			sentDiscEstAccCostList.clear();

			
		}
		
	}
		/*
			else{ // pas d'incr�mentation (garder la position 0). remise � faux de l'indicateur 
			
			Discussion dis = (Discussion) discussionList.get(Ct.currentAlternative);
			if(sentDiscussionList.contains(dis.discussionId)){
				Ct.currentAlternative++;
			}
			Ct.hasDiscListOrderBeenChanged = false;
		}
		*/
	
	
	//----------------
	
	

	
}

/**
 * Save the discussion probabilities in a file. 
 */
public void saveDiscussionProbabiloities(int round){
	//resetSaveedDiscussionProbabiloities();
	if(round!=0)
	fileDiscussionProbability = new File("logs/"+GlobalStep+"_"+localPlan.agentOwner+"_"+globalNegotiationRound+"_discussionsProbability.txt"); 
	
	try {
		PrintWriter fileProbaPrint = new PrintWriter(new FileWriter(fileDiscussionProbability));
		//discussionList.sort(DESCENDING_COMPARATOR_byID);
		discussionList.sort(ASCENDING_COMPARATOR_byAccInd);
		
		if(selectedFinalCost==-1) selectedFinalCost = refCost;
		
		
		
		fileProbaPrint.println((float)firstDiscOrderCosts.get(0)+","+selectedFinalCost+","+indiceTestParTotalTache);
		fileProbaPrint.println("ref Cost (First), Final Cost, Indice");
		
		fileProbaPrint.println(refCost+","+selectedFinalCost+","+indiceTestParTotalTache);
		fileProbaPrint.println("ref Cost (First), Final Cost, Indice");
		
		fileProbaPrint.println(limitCost+","+selectedFinalCost+","+indiceTestParTotalTache);
		fileProbaPrint.println("Limit Cost, Final Cost, Indice");
		

		fileProbaPrint.println("ref Cost (Current): "+Ct.referenceCost);
		fileProbaPrint.println("-----------------------");
		fileProbaPrint.println("-----------------------");
		fileProbaPrint.println(" -> "+experimentType);
		fileProbaPrint.println(" ->  maxRound "+maxRound);
		if(lastSolDisc!=null){
			fileProbaPrint.println(" ->  Disc Id is:  "+lastSolDisc.discussionId);
			fileProbaPrint.println(" ->  Path is:  "+lastSolDisc.discussionPath.getEdgeSet().toString());
		}
		
		fileProbaPrint.println("");
		fileProbaPrint.println("	{w, eCost, rf, sf, conv} = "+Arrays.toString(val));
		fileProbaPrint.println("	First Discussions Order = "+firstDiscOrder.toString());
		fileProbaPrint.println("	First Discussions Order Costs (indi) = "+firstDiscOrderCosts.toString());
		
		fileProbaPrint.println("");
		fileProbaPrint.println("	Suite des Propositions envoyees  = "+sentDiscussionList.toString());
		fileProbaPrint.println("	Fr�quences des Propositions envoyees  = "+sentDiscussionFrequency.toString());
		fileProbaPrint.println("	Nombre de round = "+sentDiscussionList.size());
		
		fileProbaPrint.println("	Suite des couts respectifs : "+sentDiscussionEstimatedCostList.toString());
		fileProbaPrint.println("	globalNegotiationRound = "+globalNegotiationRound);
		
		fileProbaPrint.println("");
		fileProbaPrint.println("Details : ");
		fileProbaPrint.println("	Nombre d'agents dans le syst�me (Premi�re instance du processus) = "+nbrAgentsInSystem);
		fileProbaPrint.println("	Nombre requis d'agents pour une alternative = "+totalAgent);
		fileProbaPrint.println("	totalTacheTest = "+totalTacheTest);
		fileProbaPrint.println("	totalAlternativeTest = "+totalAlternativeTest);
		fileProbaPrint.println("	indiceTestParTotalTache = "+indiceTestParTotalTache);
		fileProbaPrint.println("	costLimitPercentage = "+costLimitPercentage);
		
		fileProbaPrint.println("");
		fileProbaPrint.println("Details on selected alternative  : ");
	
		fileProbaPrint.println("	Reference Cost: "+ refCost); 
		fileProbaPrint.println("	Selected discussion ID: "+ selectedDisc);
		fileProbaPrint.println("	Selected discussion estimated cost: "+ estimatedSelectedDiscCost);  
		fileProbaPrint.println("	Selected discussion estimated cost after acceptance: "+ estimatedCostAfterAccept); 
		fileProbaPrint.println("	Selected discussion final cost: "+ selectedFinalCost);
		fileProbaPrint.println("	Individual gained cost: "+ indiGainedCost);
		fileProbaPrint.println("	Discussion lost cost: "+ discLostGaint); 

		

		
		for(int i=0; i<discussionList.size(); i++){
			fileProbaPrint.println("");fileProbaPrint.println("");fileProbaPrint.println("");
			Discussion disc = (Discussion) discussionList.get(i);
			fileProbaPrint.println("Disc  ID  = "+disc.discussionId);			
			fileProbaPrint.println("		probability  = "+disc.acceptaceIndicatorHistory.toString());
			fileProbaPrint.println("");
			fileProbaPrint.println("		Convergence Degree  = "+disc.ConvDegreeH.toString());
			fileProbaPrint.println("");
			fileProbaPrint.println("		Ranking History  = "+disc.discRank.toString());
			fileProbaPrint.println("");
			fileProbaPrint.println("	Parametres caracteristiques ");
			fileProbaPrint.println("		W = "+disc.wH.toString());
			fileProbaPrint.println("		eCost = "+disc.eCostH.toString());
			fileProbaPrint.println("		rf = "+disc.rFH.toString());
			fileProbaPrint.println("		sf = "+disc.sFH.toString());
			fileProbaPrint.println("		Conv = "+disc.ConvIndH.toString());
			fileProbaPrint.println("");
			fileProbaPrint.println("	Parametres Valorisation ");
			fileProbaPrint.println("		WV = "+disc.wV.toString());
			fileProbaPrint.println("		eCostV = "+disc.eCostV.toString());
			fileProbaPrint.println("		rfV = "+disc.rFV.toString());
			fileProbaPrint.println("		sfV = "+disc.sFV.toString());
			fileProbaPrint.println("		ConvV = "+disc.ConvIndV.toString());
			fileProbaPrint.println("	Valeurs dans Indicateur d'acceptance ");
			fileProbaPrint.println("		WValue = "+disc.wValue.toString());
			fileProbaPrint.println("		eCostValue = "+disc.eCostValue.toString());
			fileProbaPrint.println("		rfValue = "+disc.rFValue.toString());
			fileProbaPrint.println("		sfValue = "+disc.sFValue.toString());
			fileProbaPrint.println("		ConvValue = "+disc.ConvIndValue.toString());

			
			
		}
		fileProbaPrint.close();

	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}	
}


/**
 * Reset the discussion probabilities variables to gain memory. 
 */
public void resetSaveedDiscussionProbabiloities(){

		//sentDiscussionList.clear();
		sentDiscussionEstimatedCostList.clear();
		for(int i=0; i<discussionList.size(); i++){
			Discussion disc = (Discussion) discussionList.get(i);
			disc.restHistoricalList();
		}
	

	
}



public static final Comparator<Discussion> DESCENDING_COMPARATOR_byID = new Comparator<Discussion>() {
    // Overriding the compare method to sort the age
	@Override
	public int compare(Discussion o1, Discussion o2) {
		// TODO Auto-generated method stub
		return o1.discussionId - o2.discussionId;
	}
};


public static final Comparator<Discussion> ASCENDING_COMPARATOR_byAccInd = new Comparator<Discussion>() {
    // Overriding the compare method to sort the age
	@Override
	public int compare(Discussion o1, Discussion o2) {
		// TODO Auto-generated method stub
		return (int)( o2.acceptaceIndicator *(1000) -  o1.acceptaceIndicator*1000);
	}
};


public static final Comparator<Discussion> ASCENDING_COMPARATOR_byIndiCost = new Comparator<Discussion>() {
    // Overriding the compare method to sort the age
	@Override
	public int compare(Discussion o1, Discussion o2) {
		// TODO Auto-generated method stub
		return (int)( o2.discussionIndividualCost -  o1.discussionIndividualCost);
	}
};


public static final Comparator<Discussion> DESCENDING_COMPARATOR_byIndiCost = new Comparator<Discussion>() {
    // Overriding the compare method to sort the age
	@Override
	public int compare(Discussion o1, Discussion o2) {
		// TODO Auto-generated method stub
		return (int) (o1.discussionIndividualCost - o2.discussionIndividualCost);
	}
};

/**
 * Comparator pour afficher une arraylist tri�e.
 * @param id1 Identificateur de la discusion 1
 * @param id2 Identificateur de la discussion 2
 * @return
 *//*
public int compare(int id1, int id2) {
	  return id1 - id2;
	}*/



/**
 * do the needed updates to proceed to a resumption in order to improve the solution.
 * @throws IOException 
 */

public void updateForResumption() throws IOException{
	

		Ct.alternativesCount = discussionList.size();
		
		Ct.referenceCost = Tr.getReferenceCostForResumption(discussionList, localPlan.graphPlan); 
		
		//Ct.referenceCost = 10000;
		//refCost = Ct.referenceCost;
		//if(budgetLimit)
		//	Ct.costLimit = Tr.getCostLimit(discussionList, costLimitPercentage); 
		
		System.out.println(" --->> New Reference Cost for resumption: "+Ct.referenceCost);
		
		// Revoir la liste des d�scussion pour enlever celles qui d�passent le cout individuel
		Tr.removeCostlyDiscussionsFromDiscussionList(discussionList, Ct.referenceCost);
		
		//  mise � jour du nombre d'alt�rnative � discuter. 
		Ct.alternativesCount = discussionList.size();
		
		Ct.isSolutionFound = false;
		Ct.isNewDiscussionToRun = true;
		Ct.isOkayToSendProposals = true;
		Ct.isHaveSolutionCandidate = false;
		Ct.isTherePlansToProcess =false;
		Ct.isHavingProposalToWaitFor = false;
		Ct.areAllAcceptProposalSent = false;
		//Ct.isCurrentAlternativeSent = false;
		Ct.isNewAcceptProposalsToForm = false;
		Ct.isNewAcceptProposalsToSend = false;


}



public void  writeStatut(int st) throws IOException{
	File statutFile = new File("logs/"+this.getLocalName()+".txt"); 
	PrintWriter f = new PrintWriter(new FileWriter(statutFile));
	f.write(st+"");
	f.close();
} 

} // Fin de classe. 


