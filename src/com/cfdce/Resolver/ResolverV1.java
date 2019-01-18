/**
 * 
 */
package com.cfdce.Resolver;

import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

import com.cfdce.FormationCoalitions.Coalition;
import com.cfdce.FormationCoalitions.Discussion;

import jade.domain.FIPAException;

/**
 * @author Djo
 *
 */
public class ResolverV1 {
	
	public static ArrayList agentList = new ArrayList();
	public static ArrayList selectedDiscList = new ArrayList();
	public static ArrayList allCoalitionList = new ArrayList();
	public static MethodesCollection MethColl = new MethodesCollection();
	public static ArrayList allTasksList = new ArrayList();
	//public static int limit = ThreadLocalRandom.current().nextInt(100, 105);
	public static int limit = 10000;
	public static int sleepTime = 50;
	public static int[] agentTab = {1,2,3};
	
	/**
	 * @param args
	 * @throws IOException 
	 * @throws FIPAException 
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws IOException, FIPAException, InterruptedException {
		// TODO Auto-generated method stub

		
		// Afficher sur fichier Log
		PrintStream out = new PrintStream(new FileOutputStream("logs/Resolver_Log.txt"));
		System.setOut(out);
		
		
		
		// Charger les plans des agents
		agentList = MethColl.loadPlans(agentTab);
		
		//Thread.sleep(50000);
		
		System.out.println(" "); 
		System.out.println("----------------------");
		for(int i=0; i<agentList.size(); i++){
			AgentModel agent = (AgentModel) agentList.get(i);
			System.out.println(" Agent ID is: "+agent.plan.getId()+ " Its Alternatives Number is: "+agent.allPaths.size());
		}
		System.out.println("--------------- ");
		
		
		allTasksList = MethColl.getTasksList(agentList);
		MethColl.formCoalitionsProposal(false, agentList);
		MethColl.computeExclusiveTasks(false, agentList);
		
		System.out.println();System.out.println();System.out.println();
		
		System.out.println(" Traitement par tache:");
		
		
		// liste des indice
		ArrayList indiceMin = new ArrayList();
		ArrayList indiceMax = new ArrayList();
		
		for(int i=0; i<allTasksList.size(); i++){
			Task task = (Task) allTasksList.get(i);
			
			System.out.println(" -> The task: "+task.task);
			System.out.println(" -> The agents having the task: "+task.agentList.toString());
				
			task.combPossibilities = MethColl.formCoalitionStr(task.agentList);
		
			System.out.println(" -> Liste des possibilit�s (taille): "+task.combPossibilities.size());
			indiceMin.add(0);
			indiceMax.add(task.combPossibilities.size());
			
			for(int l=0; l<task.combPossibilities.size(); l++){
				ArrayList liste = (ArrayList) task.getPossibilitiesFromStr(l); 
				System.out.println(" 				> Liste N "+l+" : "+liste.toString());
			}
			System.out.println();System.out.println();
		}
		
		
		
		
		
		System.out.println();System.out.println();System.out.println();
		
		System.out.println(" Parcour de tous les cas possibles: ");
 
		
		boolean end = false;
		int step = 1;
		
while(!end){
			System.out.println();
			System.out.println("           >--------------------<");
			System.out.println("           >-  Etape N"+step+" -<");
			System.out.println("           >--------------------<");
			System.out.println();
			System.out.println(" -> Indice Min:  "+indiceMin.toString()+ "Step:"+step);
			System.out.println(" -> Indice Max:  "+indiceMax.toString()+ "Step:"+step);
			
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
											
			
			// Projeter sur les coalitions des agents Version 2 
			
			for(int i=0; i<allTasksList.size(); i++){//	System.out.println(" 		- 1");
				Task task = (Task) allTasksList.get(i);
				System.out.println();
				ArrayList liste = (ArrayList) task.getPossibilitiesFromStr((int)indiceMin.get(i));
			//	System.out.println("  --> The task: "+task.task+"  with selected list: "+liste.toString());	
				System.out.println("  --> The task: "+task.task+"  with selected list("+(int)indiceMin.get(i)+"): "+liste.toString()+ "  --> The task possibilites:"+ task.combPossibilities.toString());	
				
			
				// mise � jour des coalitions des agents, par ordre d'apparution dans la liste
				for(int s=0; s<liste.size();s++){
						String ag1 = "Agent"+(int)liste.get(s);
						
						for(int h=0; h<agentList.size(); h++){// 	System.out.println(" 		- 2");
							AgentModel agent = (AgentModel) agentList.get(h);
							if(ag1.equals(agent.plan.getId())){
								System.out.println();
								System.out.println("  								-<->- "+agent.plan.getId()+" -<->-");
								System.out.println();
									for(int k=0; k<agent.discussionList.size(); k++){//	System.out.println(" 		- 3");
										Discussion disc = (Discussion) agent.discussionList.get(k);
										System.out.println("  			-> Update Agent discussion: -  "+disc.discussionId+" -  for the agent: -> "+agent.plan.getId());
										for(int m=0; m<disc.coalitionList.size(); m++){//	System.out.println(" 		- 4");
											Coalition coal = (Coalition) disc.coalitionList.get(m);
											
											int ag = Integer.parseInt(coal.agentOwner.substring(5, coal.agentOwner.length()));
									
											if(coal.edgeList.contains(task.task)){
												coal.agentForCoalitionList.clear();
												
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
														if(coal.agentForCoalitionList.size()<=0){
															if(liste.size()>1){
																coal.agentForCoalitionList = (ArrayList) liste.clone();
																System.out.println("  				-> updating the coalition: "+coal.edgeList.toString()+" ... > Yes, Coalition Updated -> It will be performed by agents:  "+liste.toString());
																
															}else{																	
																coal.agentForCoalitionList.add(ag);
																System.out.println("  				-> updating the coalition: "+coal.edgeList.toString()+" ... > Yes, Coalition Updated -> It will be performed by agents:  "+ag);
																
															}
															
														}else{
															System.out.println("  				-> updating the coalition: "+coal.edgeList.toString()+" ... > Yes, but already updated. It contains: "+coal.agentForCoalitionList.toString());
														}
													}else{
														coal.agentForCoalitionList.add(ag);
														System.out.println("  				-> updating the coalition: "+coal.edgeList.toString()+" ... > Yes, but exists conflict with "+liste.toString()+" -> So, It will be performed alone..!" );
													}
													
													//coal.agentForCoalitionList = liste;	
														
					
											}else{
												// System.out.println("  				-> updating the coalition: "+coal.edgeList.toString()+" ... > Coalition not concerned");
											}
											/*if(coal.agentForCoalitionList.size()<=0){
												coal.agentForCoalitionList.add(e)
											}*/
										}
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
			if(step >= limit){
				end = true;
				System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
			}
			
			MethColl.printAgentOnGraphs(agentList, step);
			Thread.sleep(sleepTime); 
			 
			
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
