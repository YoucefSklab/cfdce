package com.cfdce.mainPkg;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;

import com.cfdce.Plan.planSet;

public class ComputeIndices {

	private static planSet pSet = new planSet();
	
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int nbr =0;
		ArrayList agentList = new ArrayList();
		
		
		for(int i=1; i<=900; i++){
			Graph planNbrTasks = null;
			try { 	planNbrTasks = pSet.getPlanByNbr(i);	} catch (FileNotFoundException e1) { e1.printStackTrace(); }
			
			if(planNbrTasks.getEdgeCount()>18){
			//if(i==i){
				Graph planA = null;
				try { 	planA = pSet.getPlanByNbr(i);	} catch (FileNotFoundException e1) { e1.printStackTrace(); }	
				
				ArrayList listeDesAgent = new ArrayList();
				for(int j=1; j<=900; j++){
					if(i!=j){							
							Graph planB = null;
							try { 	planB = pSet.getPlanByNbr(j);	} catch (FileNotFoundException e1) { e1.printStackTrace(); }
							
							float freq = 0;
							float freq1 = 0;
							ArrayList unionTask = new ArrayList();
							float freq2 = 0;
							
							//-- Calculer Indice --------------------------
							//---------------------------------------------
							for(Edge ed:planA.getEdgeSet()){
								if(!ed.getId().contains("start")){
									if(!unionTask.contains(ed.getId())){
										unionTask.add(ed.getId());
									}
									
									for(Edge ed2:planB.getEdgeSet()){
										if(!ed2.getId().contains("start")){
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
							
							//-- Fin calcul Indice ---------------------
							//------------------------------------------
							
							if(unionTask.size()>0){
								freq1 = (float) freq2 / unionTask.size();  
							}		
							
							
							
							
			
								if((freq1<=0.91) && (freq1>=0.60) ){
									
								//	System.out.println(nbr+" --> Agent: "+i+" and  Agent: "+j+"  :  "+freq1 ); 
									agentList.add(j);
									if(listeDesAgent.size()<=18)
									listeDesAgent.add(j);
									if(!agentList.contains(i)) agentList.add(i);
								}
						}
					}
				
				if(listeDesAgent.size()==19){
					
					nbr++;
					System.out.println(" " );
					
					System.out.println(nbr+" --> Agent: "+i );
					
					listeDesAgent.add(i);
					for(int i1= 0 ; i1<listeDesAgent.size(); i1++){
							System.out.println("listAgent.add("+(int) listeDesAgent.get(i1)+");");
					}
				}
						
					
				}
			
		
		}
		
		System.out.println(" -> fin");
		/*
		for(int i= 0 ; i<agentList.size(); i++){
			System.out.println("listAgent.add("+(int) agentList.get(i)+");");
		}
		*/

		

	}

}
