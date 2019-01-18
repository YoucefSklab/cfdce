package com.cfdce.Plan;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;


public class planSet {

//------------------------------------------------------------------------------------
//------------------------------------------------------------------------------------
//------------------------------------------------------------------------------- 
	
	
public Graph constructPlan(int planId) throws FileNotFoundException{
	Graph Plan = new SingleGraph("Agent"+planId); 
	Plan.setStrict(false); 
	Plan.setAutoCreate( true ); 
	Plan.addAttribute("ui.stylesheet", "url('file:config/Modele1.css')"); 
	Plan.addAttribute("ui.quality"); 
	Plan.addAttribute("ui.antialias"); 
	
	ArrayList addedNodes = new ArrayList();
	
	File f1 = new File("AgentsPlans/Plan_Agent_"+planId+".txt");
	
	if ( f1.exists() ) {
		
		Scanner sc = new Scanner(f1);
		while(sc.hasNextLine()){
			String text = sc.nextLine();
			String N1 = "";
			String N2 = "";
			String costV="";
			boolean edge1 = true;
			boolean cost = false;
			boolean escape = false;
			
			for(int i=0; i<text.length(); i++){
				char c = text.charAt(i);
				
				if(c=='/'){
					escape = true;
					break;
				}
				
				if(c==','){
					edge1 = false;
				}
				
				if(c==';'){
					cost = true;
				}
				
				
				if((c!=',') && (c!=';')){
					if(edge1){
						N1+=c;
					}else
				 		if(!cost){
						N2+=c;
						}
					if(cost){
				 		costV+=c;
							}
					
				}
				
		
			 
				
			}
			
			if(!escape){
					if(!addedNodes.contains(N1)) {
						Plan.addNode(N1);
						addedNodes.add(N1);
					}
					
					if(!addedNodes.contains(N2)) {
						Plan.addNode(N2);
						addedNodes.add(N2);
					}
					
					Plan.addEdge(N1+N2+"", N1, N2, true ); 
					//Plan.addEdge(N1+N2+"", N1, N2); 
					
					//System.out.println("++++++++> Adding edge: "+N1+N2+"");
					
					Edge e = Plan.getEdge(N1+N2+"");
					e.addAttribute("extraCost", "0"+costV);
					e.setAttribute("extraCost", "0"+costV);
			}
		}
				
				for(Node n:Plan.getEachNode()){
					n.setAttribute("ui.label", n.getId());
				//	System.out.println("-----------> Node Label: "+n.getId());
				}
				
				for(Edge e:Plan.getEachEdge()){
					e.setAttribute("ui.label", e.getId()); 
				//	System.out.println("-----------> Edge Label: "+e.getId());
					
				}
				
		
	}
	
	return Plan;
}
	
public Graph getPlanByNbr(int nbr) throws FileNotFoundException{
	return constructPlan(nbr);
} 



// ------------------------------------------------------------------------------- 
 // ------------------------------------------------------------------------------- 






} // fin de planSet
//************************
