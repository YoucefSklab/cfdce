/**
 * 
 */
package com.cfdce.Resolver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import com.cfdce.FormationCoalitions.Coalition;
import com.cfdce.FormationCoalitions.Discussion;

import jade.domain.FIPAException;

/**
 * @author Djo
 *
 */
public class Resolver {
	
	public static ArrayList agentList = new ArrayList();
	public static ArrayList selectedDiscList = new ArrayList();
	public static ArrayList allCoalitionList = new ArrayList();
	public static MethodesCollection MethColl = new MethodesCollection();
	public static ArrayList allTasksList = new ArrayList();
	public static int limit = ThreadLocalRandom.current().nextInt(100, 105);
	public static int[] agentTab = {1,2,3};
	
	
	/**
	 * @param args
	 * @throws IOException 
	 * @throws FIPAException 
	 */
	public static void main(String[] args) throws IOException, FIPAException {
		// TODO Auto-generated method stub

	
		
		// Logger logger = LoggerFactory.getLogger(Resolver.class);
		
		
		 //-- G�n�ration de la liste des coalitions:
		 
		 ArrayList lis = new ArrayList();
			for(int i=1; i<6; i++){
				lis.add(i);
			}
		ArrayList partitionList = new ArrayList();
		//partitionList = MethColl.formCoalition(lis);
		partitionList = MethColl.formCoalition(lis,1);
		
		System.out.println("-------------------------- ");
		System.out.println("La taille est de: "+partitionList.size());
		
		for(int i=0; i<partitionList.size(); i++){
			ArrayList listEl = (ArrayList) partitionList.get(i);
			System.out.println("La liste N"+i+" est: "+listEl.toString());
		}
		 
		 
		 
		agentList = MethColl.loadPlans(agentTab);
		
		for(int i=0; i<agentList.size(); i++){
			AgentModel agent = (AgentModel) agentList.get(i);
			System.out.println(" "); System.out.println("----------------------");
			System.out.println(" Agent ID is: "+agent.plan.getId());
			System.out.println(" Agent Alternatives Number is: "+agent.allPaths.size());
		
			System.out.println("--------------- ");
		}
		
		allTasksList = MethColl.getTasksList(agentList);
		MethColl.formCoalitionsProposal(true,agentList);
		MethColl.computeExclusiveTasks(true, agentList);
		
		
		/*
		System.out.println("Discussions List per agent ----");
		
		
		for(int i=0; i<agentList.size(); i++){
			AgentModel agent = (AgentModel) agentList.get(i);
			System.out.println(" "); System.out.println("----------------------");
			System.out.println(" Agent ID is: "+agent.plan.getId());
			System.out.println(" Discussion list size is: "+agent.discussionList.size());
			
			for(int j=0; j<agent.discussionList.size(); j++){
				Discussion disc = (Discussion) agent.discussionList.get(j);
				System.out.println("discussion id: "+disc.discussionId);
				
				System.out.println("The discussion Coalition list: ");
				
				for(int k=0; k<disc.coalitionList.size(); k++){
					Coalition c = (Coalition) disc.coalitionList.get(k);
					System.out.println("--> Coalition id is: "+c.coalitionId);
					
					System.out.println("--> Coaition Task list: "+c.edgeList.toString());
					System.out.println("--> Coalition Agents list: "+c.agentList.toString());
					
					allCoalitionList.add(c);
				}
			}
			
			
			System.out.println("--------------- ");
		}
		
		*/
		
		
		
		System.out.println("-");System.out.println("-");System.out.println("-");
		
		System.out.println(" Traitement par tache:");
		
		
		// liste des indice
		ArrayList indiceMin = new ArrayList();
		ArrayList indiceMax = new ArrayList();
		
		for(int i=0; i<allTasksList.size(); i++){
			Task task = (Task) allTasksList.get(i);
			
			System.out.println(" ==> The task: "+task.task);
			System.out.println(" ==> The agents: "+task.agentList.toString());
		
			
			task.combPossibilities = MethColl.formCoalitionStr(task.agentList);
		
			System.out.println(" -----------------> Liste des possibilit�s: "+task.combPossibilities.size());
			indiceMin.add(0);
			indiceMax.add(task.combPossibilities.size());
			
			for(int l=0; l<task.combPossibilities.size(); l++){
				ArrayList liste = (ArrayList) task.getPossibilitiesFromStr(l); 
				System.out.println(" --------------------> Liste N "+l+" : "+liste.toString());
			}
			System.out.println("--------------- ");
		}
		
		
		
		
		
		System.out.println("-");System.out.println("-");System.out.println("-");
		
		System.out.println(" Parcour de tous les cas: ");
		
		for(int i=0; i<allTasksList.size(); i++){
			Task task = (Task) allTasksList.get(i);
			System.out.println(" ==> The task: "+task.task);
			System.out.println("------------- Indice Min:  "+indiceMin.get(i));
			System.out.println("------------- Indice Max:  "+indiceMax.get(i));
		}  
		
		boolean end = false;
		int step = 1;
		
while(!end){
			System.out.println("  -----> Etape N"+step);
			System.out.println("------------- Indice Min:  "+indiceMin.toString());
			System.out.println("------------- Indice Max:  "+indiceMax.toString());
			
			//vider les coalitions
					for(int h=0; h<agentList.size(); h++){
						AgentModel agent = (AgentModel) agentList.get(h);
						for(int k=0; k<agent.discussionList.size(); k++){
							Discussion disc = (Discussion) agent.discussionList.get(k);
							for(int m=0; m<disc.coalitionList.size(); m++){
								Coalition coal = (Coalition) disc.coalitionList.get(m);
								coal.agentForCoalitionList.clear();
								}
							}
						}
											
			 
//			// Projeter sur les coalitions des agents
//			for(int i=0; i<allTasksList.size(); i++){//	System.out.println(" 		- 1");
//				Task task = (Task) allTasksList.get(i);
//				System.out.println(" ==------> The task: "+task.task);		
//					ArrayList liste = (ArrayList) task.getPossibilitiesFromStr((int)indiceMin.get(i)); 
//					System.out.println(" 		- liste: "+liste.size() + " is "+liste.toString());
//					//if(liste.size()>=1)
//					for(int h=0; h<agentList.size(); h++){// 	System.out.println(" 		- 2");
//						AgentModel agent = (AgentModel) agentList.get(h);
//						for(int k=0; k<agent.discussionList.size(); k++){//	System.out.println(" 		- 3");
//							System.out.println("  -----> Agent: "+agent.plan.getId());
//							Discussion disc = (Discussion) agent.discussionList.get(k);
//							for(int m=0; m<disc.coalitionList.size(); m++){//	System.out.println(" 		- 4");
//								Coalition coal = (Coalition) disc.coalitionList.get(m);
//								
//								int ag = Integer.parseInt(coal.agentOwner.substring(5, coal.agentOwner.length()));
//								System.out.println("  -----> Its Coalition: "+coal.edgeList.toString());
//								if(coal.edgeList.contains(task.task)){
//									coal.agentForCoalitionList.clear();
//									if(liste.contains(ag)){
//										System.out.println(" yes --> Disc Owner: "+disc.agentOwner+" -> Add "+liste.toString()+" to: "+coal.agentOwner);
//										// v�rification si pas tache exclusive ayant dej� des agents affect�s
//										boolean exist = false;
//										
//										/*
//										for(int y=0; y<agent.tasksList.size(); y++){
//											String t = (String) agent.tasksList.get(y);
//											if(t.equals(task.task)){
//												ArrayList exclusive = (ArrayList) agent.exclusiveTasks.get(y);
//												
//												if(exclusive.size()>=2)
//													for(int u=0; u<exclusive.size(); u++){
//														String t2 = (String) exclusive.get(u);
//														for(int g=0; g<agent.discussionList.size(); g++){
//															Discussion d = (Discussion) agent.discussionList.get(g);
//															for(int r=0; r<d.coalitionList.size(); r++){//	System.out.println(" 		- 4");
//																Coalition c1 = (Coalition) d.coalitionList.get(r);
//																if(c1.edgeList.contains(t2)){
//																	if(c1.agentForCoalitionList.size()>=2){
//																		exist = true;
//																	}
//																}
//															}
//														}
//													}
//											
//												//	break;
//											}
//										}
//										if(!exist){
//											coal.agentForCoalitionList = liste;	
//										}else{
//											coal.agentForCoalitionList.add(ag);
//										}
//										*/
//										coal.agentForCoalitionList = liste;	
//										
//									}else{
//										coal.agentForCoalitionList.add(ag);
//										System.out.println(" ---> no Alone for  --> Disc Owner: "+disc.agentOwner+" -> liste  "+liste.toString()+" to: "+coal.agentOwner);
//									}
//		
//								}
//								/*if(coal.agentForCoalitionList.size()<=0){
//									coal.agentForCoalitionList.add(e)
//								}*/
//							}
//						}
//					}						
//			}  
			
			
			
			
			
			
			
			
			
			
			
			// // Projeter sur les coalitions des agents Version 2 
			
					for(int i=0; i<allTasksList.size(); i++){//	System.out.println(" 		- 1");
						Task task = (Task) allTasksList.get(i);
						System.out.println(" ==------> The task: "+task.task);		
							ArrayList liste = (ArrayList) task.getPossibilitiesFromStr((int)indiceMin.get(i)); 
							System.out.println(" 		- liste: "+liste.size() + " is "+liste.toString());
							
							
							for(int s=0; s<liste.size();s++){
								String ag1 = "Agent"+(int)liste.get(s);
								
								for(int h=0; h<agentList.size(); h++){// 	System.out.println(" 		- 2");
									AgentModel agent = (AgentModel) agentList.get(h);
									if(ag1.equals(agent.plan.getId()))
										for(int k=0; k<agent.discussionList.size(); k++){//	System.out.println(" 		- 3");
											System.out.println("  -----> Agent: "+agent.plan.getId());
											Discussion disc = (Discussion) agent.discussionList.get(k);
											for(int m=0; m<disc.coalitionList.size(); m++){//	System.out.println(" 		- 4");
												Coalition coal = (Coalition) disc.coalitionList.get(m);
												
												int ag = Integer.parseInt(coal.agentOwner.substring(5, coal.agentOwner.length()));
												System.out.println("  -----> Its Coalition: "+coal.edgeList.toString());
												if(coal.edgeList.contains(task.task)){
													coal.agentForCoalitionList.clear();
													
														System.out.println(" yes --> Disc Owner: "+disc.agentOwner+" -> Add "+liste.toString()+" to: "+coal.agentOwner);
														// v�rification si pas tache exclusive ayant dej� des agents affect�s
														boolean exist = false;
														
														
														for(int y=0; y<agent.tasksList.size(); y++){
															String t = (String) agent.tasksList.get(y);
															if(t.equals(task.task)){
																ArrayList exclusive = (ArrayList) agent.exclusiveTasks.get(y);
																
																if(exclusive.size()>=1)
																	for(int u=0; u<exclusive.size(); u++){
																		String t2 = (String) exclusive.get(u);
																		for(int q=0; q<agentList.size(); q++){// 	System.out.println(" 		- 2");
																			AgentModel agent2 = (AgentModel) agentList.get(q);
																			for(int g=0; g<agent2.discussionList.size(); g++){
																				Discussion d = (Discussion) agent2.discussionList.get(g);
																				for(int r=0; r<d.coalitionList.size(); r++){//	System.out.println(" 		- 4");
																					Coalition c1 = (Coalition) d.coalitionList.get(r);
																					if(c1.edgeList.contains(t2)){
																						if(c1.agentForCoalitionList.size()>=2){
																							exist = true;
																						}
																					}
																				}
																			}
																		}
																	}
															
																//	break;
															}
														}
														if(!exist){
															coal.agentForCoalitionList = liste;	
														}else{
															coal.agentForCoalitionList.add(ag);
														}
														
														//coal.agentForCoalitionList = liste;	
															
						
												}
												/*if(coal.agentForCoalitionList.size()<=0){
													coal.agentForCoalitionList.add(e)
												}*/
											}
										}
								}	
								
								
								
								
								
								
								
								
								
								
								
								
								
								
								
								
								
								
							}
							
							
											
					}  
					
					
					
							
					
					
					
					for(int h=0; h<agentList.size(); h++){// 	System.out.println(" 		- 2");
						AgentModel agent = (AgentModel) agentList.get(h);
							for(int k=0; k<agent.discussionList.size(); k++){//	System.out.println(" 		- 3");
								System.out.println("  -----> Agent: "+agent.plan.getId());
								Discussion disc = (Discussion) agent.discussionList.get(k);
								for(int m=0; m<disc.coalitionList.size(); m++){//	System.out.println(" 		- 4");
									Coalition coal = (Coalition) disc.coalitionList.get(m);
									if(coal.agentForCoalitionList.size()<=0){
										coal.agentForCoalitionList.add(Integer.parseInt(coal.agentOwner.substring(5, coal.agentOwner.length())) );
									}
								}
							}
						}	
					
					
					
					
					
					
					
					
					
			
			System.out.println("-");System.out.println("-");System.out.println("-");
			System.out.println("-");System.out.println("-");System.out.println("-");
			System.out.println("-");System.out.println("-");System.out.println("-");
			
			System.out.println(" Evaluation des couts (Step): "+step);
			
			
			//Evaluation des couts
			//--------------------
			for(int i=0; i<agentList.size(); i++){
				AgentModel agent = (AgentModel) agentList.get(i);
			
				for(int j=0; j<agent.discussionList.size(); j++){
					Discussion disc = (Discussion) agent.discussionList.get(j);
					disc.getFinalCost(agent.plan);
					System.out.println("-->> discussion id: "+disc.discussionId);
					System.out.println("-->> discussion Final Cost: "+disc.discussionFinalCost);
					
					/*
					for(int k=0; k<disc.coalitionList.size(); k++){
						Coalition c = (Coalition) disc.coalitionList.get(k);
						System.out.println("--> Coalition id is: "+c.coalitionId);
						System.out.println("--> Coaition Task list: "+c.edgeList.toString());
						System.out.println("--> Coaition Agents list: "+c.agentForCoalitionList.toString());
						System.out.println("--> Coalition Final Cost: "+c.coalitionFinalCost);
						
						
					}
					*/
				}
				
				
				System.out.println("--------------- ");
			}
			
			
			// R�initilisation des param�tres du parcour.
			// -------------------------------------------
			
			int nbrTask = 	allTasksList.size()-1;		
			indiceMin.set(nbrTask, ((int) indiceMin.get(nbrTask) + 1));
			
			
			
			int cmp = allTasksList.size()-1;
			while(cmp>0){
				if(indiceMin.get(cmp) == indiceMax.get(cmp)){
					indiceMin.set(cmp, 0);
					indiceMin.set(cmp-1, ((int)indiceMin.get(cmp-1) + 1));
				}
				cmp--;
			}
			
			step++;
			// arreter la boucle si on a fait toutes les possibilit�s
			if(indiceMin.get(0) == indiceMax.get(0)){
				end = true;
			}
			
			// Garbage collector
			System.gc();
			if(step >= limit) end = true;
		}
		
		
		
		
		System.out.println("----> Les solution s�lectionn�es ::::");
	
		for(int i=0; i<agentList.size(); i++){
			AgentModel agent = (AgentModel) agentList.get(i);
			Discussion discussion =null;
			int t=0;
			for(int j=0; j<agent.discussionList.size(); j++){
				Discussion disc1 = (Discussion) agent.discussionList.get(j);
				
				if(disc1.getTotalAgent()>=t){
					discussion = disc1;
					t = disc1.getTotalAgent();
				}
			}
			
			selectedDiscList.add(discussion);
		}
		
		
		for(int i=0; i<agentList.size(); i++){
			AgentModel agent = (AgentModel) agentList.get(i);
			System.out.println("Discussion selection�e pour l'agent "+agent.plan.getId()+" :");
			
			Discussion disc = (Discussion) selectedDiscList.get(i);
			System.out.println("  Discussion Id "+disc.discussionId);
			System.out.println("  Discussion Final Cost "+disc.discussionFinalCost);
		
			for(int j=0; j<disc.coalitionList.size(); j++){
				Coalition c = (Coalition) disc.coalitionList.get(j);
				System.out.println("  	--> Coalition Id "+c.coalitionId);
				System.out.println("  	--> Coalition task "+c.edgeList.toString());
				System.out.println("  	--> Coalition Agents "+c.agentForCoalitionList.toString());
			}
		}
		
		System.out.println("Fin .....!!");
		
		
		
		MethColl.printAgentOnGraphs(agentList, step);
		
	
		for(int i=0; i<agentList.size(); i++){
			AgentModel agent = (AgentModel) agentList.get(i);
			
			System.out.println("  	--> Agent Id "+agent.plan.getId());
			
			for(int j=0; j<agent.tasksList.size(); j++){
				String task = (String) agent.tasksList.get(j);
				ArrayList listeExclusive = (ArrayList) agent.exclusiveTasks.get(j);
				
				System.out.println("  		--> Task Id: "+task+ " has exclusive: "+listeExclusive.toString());
				
			}
			
		}
	
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

		
		System.out.println("Fin ===========================!!"+limit);
	}
	
	
	

}
