package com.cfdce.FormationCoalitions;

import java.io.Serializable;

public class coalitionProposalMessage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static int proposalCounter;
	public String proposalId;
	public String coalitionId;
	public int discussionId;
	public String[] agentList;
	public String[] actionList;
	
	

	
	public coalitionProposalMessage(int discId, String cId, String[] agList, String[] actList){
		proposalCounter++;
		this.proposalId = cId+"_pN_"+proposalCounter;
		this.coalitionId = cId;
		this.discussionId = discId;
		this.agentList = agList;
		this.actionList = actList;
	} // fin du constructeur. 
} // fin de la classe.






	
