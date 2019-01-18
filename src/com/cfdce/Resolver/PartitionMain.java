package com.cfdce.Resolver;

import java.util.ArrayList;

public class PartitionMain {

	public static ArrayList ensembleElement = new ArrayList();
	public static ArrayList partitionList = new ArrayList();
	public static int nbrEl=9;
	
	public PartitionMain(ArrayList listeElement){
		ensembleElement = listeElement;
	}
	
	
	public static void main(String[] args){
		
		ArrayList lis = new ArrayList();
		
		for(int i=1; i<=nbrEl; i++){
			lis.add(i);
		}
		
		/*
		lis.add(11);
		lis.add(12);
		lis.add(13);
		lis.add(15);
		lis.add(16);
		lis.add(17);
		lis.add(18);
		*/
		/*
		lis.add(1);
		lis.add(2);
		lis.add(3);
		lis.add(4);
		lis.add(5);
		lis.add(6);
		lis.add(7);
	    */
		

		
		partitionList = formCoalition(lis); 
		
		displayResult();
		
		System.out.println("Fin des simulations !!");

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static void displayResult(){
		System.out.println(" ");
		System.out.println(" ");
		System.out.println(" ");
		System.out.println(" ");
		System.out.println("-------------------------- ");
		System.out.println("Nbr Elements: "+nbrEl);
		System.out.println("2 puissance "+nbrEl+" - 1 = "+(Math.pow(2, nbrEl)-1));
		System.out.println("2 puissance "+nbrEl+" - n = "+(Math.pow(2, nbrEl)- nbrEl));
		
		System.out.println("La taille (2) est de: "+partitionList.size()+ " + 1 (seul)="+(partitionList.size()+1)  );
		
		for(int o=0; o<partitionList.size(); o++){
			ArrayList listEl = (ArrayList) partitionList.get(o);
			//System.out.println("La liste N"+o+" est: "+listEl.toString());
			System.out.print("agentTab= new int[] {");
			for(int o1=0; o1<listEl.size()-1; o1++){
				int e = (int) listEl.get(o1); 
				System.out.print(e+",");
			}
			int e = (int) listEl.get(listEl.size()-1); 
			System.out.print(e+"};");
			System.out.println();
			System.out.println("listeAgent.add(agentTab);");
		
		}
	}
	
	
	
	
	public static ArrayList formCoalition(ArrayList listeElement){
		ArrayList listeCoalition = new ArrayList();
		
		if(listeElement.size()==2) {
			listeCoalition.add(listeElement);
			return listeElement;
		}
		
		int partSize=3;
		int listSize = listeElement.size();
		
		// Round 1
		for(int i=0; i<(listeElement.size()-1); i++){
			for(int j=(i+1); j<listeElement.size(); j++){
				ArrayList next = new ArrayList();
				next.add(listeElement.get(i));
				next.add(listeElement.get(j));
				listeCoalition.add(next);
			}
		}
		
		while(partSize<listSize){

			for(int o=0; o<listeCoalition.size(); o++){
				ArrayList listEl = (ArrayList) listeCoalition.get(o);
				
				int posi1 = listeElement.indexOf(listEl.get(0));
				int posi2 = listeElement.indexOf(listEl.get(listEl.size()-1));
				
				if(posi2+1<listeElement.size())
				for(int i=posi1; i<listeElement.size(); i++){
					
					if(!listEl.contains(listeElement.get(i))){
						ArrayList next = new ArrayList();
						next.addAll(listEl);
						next.add(listeElement.get(i));
							
						if(!(next.size()==listeElement.size())){
							if( (int) next.get(next.size()-1) >  (int)next.get(next.size()-2)  )
								listeCoalition.add(next);
						}else{
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
 * Former la liste de toutes les partitions possible d'un ensemble, comportant un �l�ment donn�.
 * @param fixedElement l'�l�ment en question
 * @param listeElement Liste des �l�ments de l'ensemble
 * @return Liste des partitions
 */	
	public static ArrayList formCoalition(ArrayList listeElement, int fixedElement){
		ArrayList listeCoalition = new ArrayList();
		ArrayList finalList = new ArrayList();
		
		if(listeElement.size()==2) {
			listeCoalition.add(listeElement);
			return listeElement;
		}
		
		int elePosition = listeElement.indexOf(fixedElement);
		int partSize=3;
		int listSize = listeElement.size();
		
		// Round 1
		for(int i=0; i<(listeElement.size()-1); i++){
			for(int j=(i+1); j<listeElement.size(); j++){
				ArrayList next = new ArrayList();
				next.add(listeElement.get(i));
				next.add(listeElement.get(j));
				
			
				listeCoalition.add(next);
			}
		}
		
		while(partSize<listSize){

			for(int o=0; o<listeCoalition.size(); o++){
				ArrayList listEl = (ArrayList) listeCoalition.get(o);
				
				int posi1 = listeElement.indexOf(listEl.get(0));
				int posi2 = listeElement.indexOf(listEl.get(listEl.size()-1));
				
				if(posi2+1<listeElement.size())
				for(int i=posi1; i<listeElement.size(); i++){
					
					if(!listEl.contains(listeElement.get(i))){
						ArrayList next = new ArrayList();
						next.addAll(listEl);
						next.add(listeElement.get(i));
							
						if(!(next.size()==listeElement.size())){
							if( (int) next.get(next.size()-1) >  (int)next.get(next.size()-2)  )
								listeCoalition.add(next);
						}else{
							partSize = listSize;
							break;	
						}
					}
				}
			}
			partSize++;
		}
		listeCoalition.add(listeElement);
		
		
		for(int i=0; i<listeCoalition.size(); i++){
			ArrayList li = (ArrayList) listeCoalition.get(i);
			if(li.contains(fixedElement))
				finalList.add(li);
		}
		
		
		return finalList;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
