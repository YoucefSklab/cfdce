package com.cfdce.mainPkg;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class CollectResults {
	
	public static ArrayList listAgent = new ArrayList(); 
	public static ArrayList poucentageList = new ArrayList(); 

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		
		System.out.println("Hello gents, this is the main program to collect experimental evaluation results from files !!");
		
		
		poucentageList.add(100);
		poucentageList.add(95);
		poucentageList.add(90);
		poucentageList.add(85);
		poucentageList.add(80);
		poucentageList.add(75);
		poucentageList.add(70);
		poucentageList.add(65);
		poucentageList.add(60);
		poucentageList.add(55);
		
		
		
		
		
	
	
	// set 5, 6
		
	
	int experimentNbr = 1;
	int GlobalStep = 1;
	int totalFiles = 1;
	while(experimentNbr <=10){		
			System.out.println("-------------------------------------------------------------------------------------------------");
			System.out.println("-------------------------------------------------------------------------------------------------");
			System.out.println("---> Agent Set is: "+experimentNbr);
			System.out.println("-------------------------------------------------------------------------------------------------");
			System.out.println("-------------------------------------------------------------------------------------------------");
			
			
			PrintWriter f = null;
			try {
				f = new PrintWriter(new FileWriter("logs/CollectedResultsAgentSet"+experimentNbr+".txt"));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			f.println(" ");
			f.println("Agent, ref Cost (First), Final Cost, Indice");
			
			
			listAgent.clear();
			 //indice >=0.72
			/*

					if(experimentNbr  == 1){ 
						 //   1
							listAgent.add(32);
							listAgent.add(140);
							listAgent.add(230);
							listAgent.add(331);
							listAgent.add(416);
							listAgent.add(430);
							listAgent.add(872);
							listAgent.add(888);
							listAgent.add(891);
							listAgent.add(9);
						 }
						 if(experimentNbr  == 2){
						 		//   20
							listAgent.add(126);
							listAgent.add(240);
							listAgent.add(278);
							listAgent.add(302);
							listAgent.add(310);
							listAgent.add(370);
							listAgent.add(474);
							listAgent.add(561);
							listAgent.add(766);
							listAgent.add(785);
						 }
						 if(experimentNbr  == 3){
								
							listAgent.add(102);
							listAgent.add(242);
							listAgent.add(297);
							listAgent.add(358);
							listAgent.add(376);
							listAgent.add(397);
							listAgent.add(630);
							listAgent.add(641);
							listAgent.add(649);
							listAgent.add(740);
				 
						 }
						 if(experimentNbr  == 4){
							 
							listAgent.add(15);
							listAgent.add(30);
							listAgent.add(92);
							listAgent.add(93);
							listAgent.add(174);
							listAgent.add(179);
							listAgent.add(193);
							listAgent.add(226);
							listAgent.add(649);
							listAgent.add(765);
					 
						 }
						 if(experimentNbr  == 5){
						 
							 		//   50
							listAgent.add(132);
							listAgent.add(153);
							listAgent.add(214);
							listAgent.add(309);
							listAgent.add(425);
							listAgent.add(467);
							listAgent.add(538);
							listAgent.add(776);
							listAgent.add(811);
							listAgent.add(823);
					 
						 }
						 if(experimentNbr  == 6){			  
						 //   6

							listAgent.add(273);
							listAgent.add(279);
							listAgent.add(287);
							listAgent.add(345);
							listAgent.add(352);
							listAgent.add(388);
							listAgent.add(414);
							listAgent.add(421);
							listAgent.add(434);
							listAgent.add(565);

						 
						 }
						 if(experimentNbr  == 7){			  
						 //   7
							listAgent.add(47);
							listAgent.add(80);
							listAgent.add(167);
							listAgent.add(305);
							listAgent.add(354);
							listAgent.add(606);
							listAgent.add(660);
							listAgent.add(731);
							listAgent.add(875);
							listAgent.add(160);

						 }
						 if(experimentNbr  == 8){			  
						 //   8
							listAgent.add(167);
							listAgent.add(310);
							listAgent.add(364);
							listAgent.add(370);
							listAgent.add(474);
							listAgent.add(601);
							listAgent.add(615);
							listAgent.add(785);
							listAgent.add(791);
							listAgent.add(173);

						 
						 }
						 if(experimentNbr  == 9){			  
						 //   9
							listAgent.add(111);
							listAgent.add(115);
							listAgent.add(278);
							listAgent.add(302);
							listAgent.add(370);
							listAgent.add(561);
							listAgent.add(766);
							listAgent.add(785);
							listAgent.add(864);
							listAgent.add(240);
						 
						 }
						 
						 if(experimentNbr == 10){			  
						 //   10
							listAgent.add(388);
							listAgent.add(406);
							listAgent.add(414);
							listAgent.add(434);
							listAgent.add(565);
							listAgent.add(704);
							listAgent.add(705);
							listAgent.add(739);
							listAgent.add(893);
							listAgent.add(273);
						 } 
							 
				*/	
						 
			// Indice <=0.71 et >=0.62
				//---------------------------
				
				 
				// Indice <=0.61 et >=0.52
				//---------------------------
			 
			 
			 
				// Indice <=0.51 et >=0.42
				//---------------------------
			 
			 
				// Indice <=0.41 et >=0.32
				//---------------------------				 
			 
			 
				// Indice <=0.31 et >=0.22
				//---------------------------
			 
			 
				// Indice <=0.21 et >=0.12
				//---------------------------		
			 

			if(experimentNbr  == 1){
			listAgent.add(12);
			listAgent.add(23);
			listAgent.add(46);
			listAgent.add(80);
			listAgent.add(94);
			listAgent.add(120);
			listAgent.add(122);
			listAgent.add(146);
			listAgent.add(166);
			listAgent.add(1);
			}

			if(experimentNbr  == 2){
			listAgent.add(15);
			listAgent.add(23);
			listAgent.add(31);
			listAgent.add(45);
			listAgent.add(46);
			listAgent.add(67);
			listAgent.add(80);
			listAgent.add(94);
			listAgent.add(101);
			listAgent.add(2);
			}

				if(experimentNbr  == 3){
				listAgent.add(26);
				listAgent.add(83);
				listAgent.add(263);
				listAgent.add(299);
				listAgent.add(307);
				listAgent.add(365);
				listAgent.add(507);
				listAgent.add(530);
				listAgent.add(534);
				listAgent.add(3);
				}

			if(experimentNbr  == 4){
			listAgent.add(6);
			listAgent.add(8);
			listAgent.add(12);
			listAgent.add(21);
			listAgent.add(22);
			listAgent.add(36);
			listAgent.add(43);
			listAgent.add(63);
			listAgent.add(68);
			listAgent.add(4);
			}
			 
			if(experimentNbr  == 5){
			listAgent.add(13);
			listAgent.add(68);
			listAgent.add(111);
			listAgent.add(144);
			listAgent.add(157);
			listAgent.add(158);
			listAgent.add(162);
			listAgent.add(198);
			listAgent.add(221);
			listAgent.add(5);
			}

			if(experimentNbr  == 6){
			listAgent.add(4);
			listAgent.add(23);
			listAgent.add(35);
			listAgent.add(67);
			listAgent.add(89);
			listAgent.add(146);
			listAgent.add(178);
			listAgent.add(215);
			listAgent.add(314);
			listAgent.add(6);
			}

			if(experimentNbr  == 7){
			listAgent.add(9);
			listAgent.add(11);
			listAgent.add(17);
			listAgent.add(26);
			listAgent.add(31);
			listAgent.add(32);
			listAgent.add(63);
			listAgent.add(76);
			listAgent.add(110);
			listAgent.add(7);
			}
			 
			if(experimentNbr  == 8){
			listAgent.add(4);
			listAgent.add(23);
			listAgent.add(29);
			listAgent.add(35);
			listAgent.add(47);
			listAgent.add(62);
			listAgent.add(83);
			listAgent.add(89);
			listAgent.add(96);
			listAgent.add(8);
			}
			 
			if(experimentNbr  == 9){
			listAgent.add(7);
			listAgent.add(35);
			listAgent.add(42);
			listAgent.add(45);
			listAgent.add(51);
			listAgent.add(93);
			listAgent.add(112);
			listAgent.add(122);
			listAgent.add(144);
			listAgent.add(9);
			}
			 
			if(experimentNbr  == 10){
			listAgent.add(20);
			listAgent.add(25);
			listAgent.add(41);
			listAgent.add(49);
			listAgent.add(55);
			listAgent.add(81);
			listAgent.add(91);
			listAgent.add(98);
			listAgent.add(141);
			listAgent.add(10);
			}
						 
				// Indice <=0.11 et >=0.02
				//---------------------------	
			experimentNbr++;
			
			f.println(" Agent Set:"+ listAgent.toString());
			
			for(int stepCounter=1; stepCounter<=10; stepCounter++){
				
				//int poucentage = (int) poucentageList.get(stepCounter);
				
				
				System.out.println("---> Agent Set Step: "+stepCounter);
				f.println(" Step :"+ stepCounter);
				f.println(" Poucentage :"+ (int) poucentageList.get(stepCounter-1));
				f.println(" --------------------------");
				
					for(int k=0; k<listAgent.size(); k++){
						int ag = (int) listAgent.get(k);
						
						
						int fileTest = 29;
						
						while(fileTest!=0){
							fileTest--;
							
							
							File f1 = new File("logs/"+GlobalStep+"_Agent"+ag+"_"+fileTest+"_discussionsProbability.txt");
								
								if ( f1.exists() ) {
									System.out.println(totalFiles+  " -> "+GlobalStep+"_Agent"+ag+"_"+fileTest+"_discussionsProbability.txt");
									Scanner sc = new Scanner(f1);
									if(sc.hasNextLine()){
										String text = sc.nextLine();
										f.println("Agent"+ag+","+text);
									} 
									totalFiles++;
									break;
								}
							
							
							
							
						}
								
						
					}
					GlobalStep++;
			}
			
			f.close();
		}
		
	
			
			
			
			
			
			
				
	
		
			
			
			
	
		
		
		
		System.out.println("End of the program !!");
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

	}

}
