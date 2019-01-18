package com.cfdce.Plan;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;


public class planSetOld {


//------------------------------------------------------------------------------------
//------------------------------------------------------------------------------------
//------------------------------------------------------------------------------- 
	
public Graph getPlanByNbr(int nbr){
	Graph g = null;
	
	switch(nbr){
	
	case 1:
		return getPlan1("Agent"+nbr);
	case 2 :
		return getPlan2("Agent"+nbr);
	case 3 :
		return getPlan3("Agent"+nbr);
	case 4 :
		return getPlan4("Agent"+nbr);
	case 5 :
		return getPlan5("Agent"+nbr);
	case 6 :
		return getPlan6("Agent"+nbr);
	case 7 :
		return getPlan7("Agent"+nbr);
	case 8 :
		return getPlan8("Agent"+nbr);
	case 9 :
		return getPlan9("Agent"+nbr);
	case 10 :
		return getPlan10("Agent"+nbr);
	case 11 :
		return getPlan11("Agent"+nbr);
	case 12 :
		return getPlan12("Agent"+nbr);
	case  13:
		return getPlan13("Agent"+nbr);
	case  14:
		return getPlan14("Agent"+nbr);
	case  15:
		return getPlan15("Agent"+nbr);
	case  16:
		return getPlan16("Agent"+nbr);
	case  17:
		return getPlan17("Agent"+nbr);
	case  18:
		return getPlan18("Agent"+nbr);
	case  19:
		return getPlan19("Agent"+nbr);
	case  20:
		return getPlan20("Agent"+nbr);
	case  21:
		return getPlan21("Agent"+nbr);
	case  22:
		return getPlan22("Agent"+nbr);
	case  23:
		return getPlan23("Agent"+nbr);
	case  24:
		return getPlan24("Agent"+nbr);
	case  25:
		return getPlan25("Agent"+nbr);
	case  26:
		return getPlan26("Agent"+nbr);
	case  27:
		return getPlan27("Agent"+nbr);
	case  28:
		return getPlan28("Agent"+nbr);
	case  29:
		return getPlan29("Agent"+nbr);
	case  30:
		return getPlan30("Agent"+nbr);
	case  31:
		return getPlan31("Agent"+nbr);
	case  32:
		return getPlan32("Agent"+nbr);
	case  33:
		return getPlan33("Agent"+nbr);
	case  34:
		return getPlan34("Agent"+nbr);
	case  35:
		return getPlan35("Agent"+nbr);
	case  36:
		return getPlan36("Agent"+nbr);
	case  37:
		return getPlan37("Agent"+nbr);
	case  38:
		return getPlan38("Agent"+nbr);
	case  39:
		return getPlan39("Agent"+nbr);
	case  40:
		return getPlan40("Agent"+nbr);
	case  41:
		return getPlan41("Agent"+nbr);
	case  42:
		return getPlan42("Agent"+nbr);
	case  43:
		return getPlan43("Agent"+nbr);
	case  44:
		return getPlan44("Agent"+nbr);

	case  1001:
		return getPlan1001("Agent"+nbr);
	case  1002:
		return getPlan1002("Agent"+nbr);
	case  1003:
		return getPlan1003("Agent"+nbr);
	case  1004:
		return getPlan1004("Agent"+nbr);
		
	case  101:
		return getPlan101("Agent"+nbr);
		
	case  102:
		return getPlan102("Agent"+nbr);
		
	case  103:
		return getPlan103("Agent"+nbr);
		
	case  104:
		return getPlan104("Agent"+nbr);
		
	case  105:
		return getPlan105("Agent"+nbr);
		
	case  106:
		return getPlan106("Agent"+nbr);
	
	
	}
	
	return g;
} 
	
	

	 //------------------------------------------------------------------------------- 
	 public Graph getPlan1(String pName){ 
		Graph Plan_6 = new SingleGraph("Plan_6"); 
			Plan_6.setStrict(false); 
	 		Plan_6.setAutoCreate( true ); 
	 		Plan_6.addAttribute("ui.stylesheet", "url('file:config/Modele1.css')"); 
			Plan_6.addAttribute("ui.quality"); 
			Plan_6.addAttribute("ui.antialias"); 
			
			Plan_6.addEdge("startE", "start", "E", true );
	 		Plan_6.addEdge("EJ", "E", "J", true ); 
			Plan_6.addEdge("JK", "J", "K", true ); 
			Plan_6.addEdge("KL", "K", "L", true ); 
			Plan_6.addEdge("LP", "L", "P", true ); 
			Plan_6.addEdge("PT", "P", "T", true ); 
			Plan_6.addEdge("TC_", "T", "C_", true ); 
			Plan_6.addEdge("C_D_", "C_", "D_", true ); 
			Plan_6.addEdge("A_B_", "A_", "B_", true ); 
			Plan_6.addEdge("RA_", "R", "A_", true ); 
			Plan_6.addEdge("KR", "K", "R", true ); 
			Plan_6.addEdge("FK", "F", "K", true ); 
			Plan_6.addEdge("EF", "E", "F", true ); 
			Plan_6.addEdge("FI", "F", "I", true ); 
			Plan_6.addEdge("IL", "I", "L", true ); 
			Plan_6.addEdge("LS", "L", "S", true ); 
			Plan_6.addEdge("SB_", "S", "B_", true ); 
			Plan_6.addEdge("B_C_", "B_", "C_", true ); 
			Plan_6.getNode("start").setAttribute("ui.label", "start"); 
			Plan_6.getNode("E").setAttribute("ui.label", "E"); 
			Plan_6.getNode("J").setAttribute("ui.label", "J"); 
			Plan_6.getNode("K").setAttribute("ui.label", "K"); 
			Plan_6.getNode("L").setAttribute("ui.label", "L"); 
			Plan_6.getNode("P").setAttribute("ui.label", "P"); 
			Plan_6.getNode("T").setAttribute("ui.label", "T"); 
			Plan_6.getNode("C_").setAttribute("ui.label", "C_"); 
			Plan_6.getNode("D_").setAttribute("ui.label", "D_"); 
			Plan_6.getNode("A_").setAttribute("ui.label", "A_"); 
			Plan_6.getNode("R").setAttribute("ui.label", "R"); 
			Plan_6.getNode("B_").setAttribute("ui.label", "B_"); 
			Plan_6.getNode("S").setAttribute("ui.label", "S"); 
			Plan_6.getNode("I").setAttribute("ui.label", "I"); 
			Plan_6.getNode("F").setAttribute("ui.label", "F"); 
			Plan_6.getEdge("EJ").setAttribute("ui.label", "EJ"); 
			Plan_6.getEdge("EJ").addAttribute("ui.class", "Blue"); 
			Plan_6.getEdge("JK").setAttribute("ui.label", "JK"); 
			Plan_6.getEdge("JK").addAttribute("ui.class", "Blue"); 
			Plan_6.getEdge("KL").setAttribute("ui.label", "KL"); 
			Plan_6.getEdge("KL").addAttribute("ui.class", "Blue"); 
			Plan_6.getEdge("LP").setAttribute("ui.label", "LP"); 
			Plan_6.getEdge("LP").addAttribute("ui.class", "Blue"); 
			Plan_6.getEdge("PT").setAttribute("ui.label", "PT"); 
			Plan_6.getEdge("PT").addAttribute("ui.class", "Blue"); 
			Plan_6.getEdge("TC_").setAttribute("ui.label", "TC_"); 
			Plan_6.getEdge("TC_").addAttribute("ui.class", "Blue"); 
			Plan_6.getEdge("C_D_").setAttribute("ui.label", "C_D_"); 
			Plan_6.getEdge("C_D_").addAttribute("ui.class", "Blue"); 
			Plan_6.getEdge("A_B_").setAttribute("ui.label", "A_B_"); 
			Plan_6.getEdge("A_B_").addAttribute("ui.class", "Blue"); 
			Plan_6.getEdge("RA_").setAttribute("ui.label", "RA_"); 
			Plan_6.getEdge("RA_").addAttribute("ui.class", "Blue"); 
			Plan_6.getEdge("KR").setAttribute("ui.label", "KR"); 
			Plan_6.getEdge("KR").addAttribute("ui.class", "Blue"); 
			Plan_6.getEdge("FK").setAttribute("ui.label", "FK"); 
			Plan_6.getEdge("FK").addAttribute("ui.class", "Blue"); 
			Plan_6.getEdge("EF").setAttribute("ui.label", "EF"); 
			Plan_6.getEdge("EF").addAttribute("ui.class", "Blue"); 
			Plan_6.getEdge("FI").setAttribute("ui.label", "FI"); 
			Plan_6.getEdge("FI").addAttribute("ui.class", "Blue"); 
			Plan_6.getEdge("IL").setAttribute("ui.label", "IL"); 
			Plan_6.getEdge("IL").addAttribute("ui.class", "Blue"); 
			Plan_6.getEdge("LS").setAttribute("ui.label", "LS"); 
			Plan_6.getEdge("LS").addAttribute("ui.class", "Blue"); 
			Plan_6.getEdge("SB_").setAttribute("ui.label", "SB_"); 
			Plan_6.getEdge("SB_").addAttribute("ui.class", "Blue"); 
			Plan_6.getEdge("B_C_").setAttribute("ui.label", "B_C_"); 
			Plan_6.getEdge("B_C_").addAttribute("ui.class", "Blue"); 
			
		return Plan_6; 
	} // fin de getPlan6 

	 // ------------------------------------------------------------------------------- 
	  // ------------------------------------------------------------------------------- 
	
	 
	 //------------------------------------------------------------------------------- 
	 public Graph getPlan2(String pName){ 
		Graph Plan_8 = new SingleGraph("Plan_8"); 
			Plan_8.setStrict(false); 
	 		Plan_8.setAutoCreate( true ); 
	 		Plan_8.addAttribute("ui.stylesheet", "url('file:config/Modele1.css')"); 
			Plan_8.addAttribute("ui.quality"); 
			Plan_8.addAttribute("ui.antialias"); 
			Plan_8.addEdge("startJ", "start", "J", true );
	 		Plan_8.addEdge("ST", "S", "T", true ); 
			Plan_8.addEdge("JK", "J", "K", true ); 
			Plan_8.addEdge("KL", "K", "L", true ); 
			Plan_8.addEdge("LP", "L", "P", true ); 
			Plan_8.addEdge("PT", "P", "T", true ); 
			Plan_8.addEdge("TC_", "T", "C_", true ); 
			Plan_8.addEdge("C_D_", "C_", "D_", true ); 
			Plan_8.addEdge("RS", "R", "S", true ); 
			Plan_8.addEdge("YD_", "Y", "D_", true ); 
			Plan_8.addEdge("QR", "Q", "R", true ); 
			Plan_8.addEdge("JQ", "J", "Q", true ); 
			Plan_8.addEdge("TY", "T", "Y", true ); 
			Plan_8.addEdge("LS", "L", "S", true ); 
			Plan_8.addEdge("SB_", "S", "B_", true ); 
			Plan_8.addEdge("B_C_", "B_", "C_", true ); 
			Plan_8.getNode("start").setAttribute("ui.label", "start");
			Plan_8.getNode("R").setAttribute("ui.label", "R"); 
			Plan_8.getNode("J").setAttribute("ui.label", "J"); 
			Plan_8.getNode("K").setAttribute("ui.label", "K"); 
			Plan_8.getNode("L").setAttribute("ui.label", "L"); 
			Plan_8.getNode("P").setAttribute("ui.label", "P"); 
			Plan_8.getNode("T").setAttribute("ui.label", "T"); 
			Plan_8.getNode("C_").setAttribute("ui.label", "C_"); 
			Plan_8.getNode("D_").setAttribute("ui.label", "D_"); 
			Plan_8.getNode("Q").setAttribute("ui.label", "Q"); 
			Plan_8.getNode("S").setAttribute("ui.label", "S"); 
			Plan_8.getNode("Y").setAttribute("ui.label", "Y"); 
			Plan_8.getNode("B_").setAttribute("ui.label", "B_"); 
			Plan_8.getEdge("ST").setAttribute("ui.label", "ST"); 
			Plan_8.getEdge("ST").addAttribute("ui.class", "Orange"); 
			Plan_8.getEdge("JK").setAttribute("ui.label", "JK"); 
			Plan_8.getEdge("JK").addAttribute("ui.class", "Orange"); 
			Plan_8.getEdge("KL").setAttribute("ui.label", "KL"); 
			Plan_8.getEdge("KL").addAttribute("ui.class", "Orange"); 
			Plan_8.getEdge("LP").setAttribute("ui.label", "LP"); 
			Plan_8.getEdge("LP").addAttribute("ui.class", "Orange"); 
			Plan_8.getEdge("PT").setAttribute("ui.label", "PT"); 
			Plan_8.getEdge("PT").addAttribute("ui.class", "Orange"); 
			Plan_8.getEdge("TC_").setAttribute("ui.label", "TC_"); 
			Plan_8.getEdge("TC_").addAttribute("ui.class", "Orange"); 
			Plan_8.getEdge("C_D_").setAttribute("ui.label", "C_D_"); 
			Plan_8.getEdge("C_D_").addAttribute("ui.class", "Orange"); 
			Plan_8.getEdge("RS").setAttribute("ui.label", "RS"); 
			Plan_8.getEdge("RS").addAttribute("ui.class", "Orange"); 
			Plan_8.getEdge("YD_").setAttribute("ui.label", "YD_"); 
			Plan_8.getEdge("YD_").addAttribute("ui.class", "Orange"); 
			Plan_8.getEdge("QR").setAttribute("ui.label", "QR"); 
			Plan_8.getEdge("QR").addAttribute("ui.class", "Orange"); 
			Plan_8.getEdge("JQ").setAttribute("ui.label", "JQ"); 
			Plan_8.getEdge("JQ").addAttribute("ui.class", "Orange"); 
			Plan_8.getEdge("TY").setAttribute("ui.label", "TY"); 
			Plan_8.getEdge("TY").addAttribute("ui.class", "Orange"); 
			Plan_8.getEdge("LS").setAttribute("ui.label", "LS"); 
			Plan_8.getEdge("LS").addAttribute("ui.class", "Orange"); 
			Plan_8.getEdge("SB_").setAttribute("ui.label", "SB_"); 
			Plan_8.getEdge("SB_").addAttribute("ui.class", "Orange"); 
			Plan_8.getEdge("B_C_").setAttribute("ui.label", "B_C_"); 
			Plan_8.getEdge("B_C_").addAttribute("ui.class", "Orange");
		return Plan_8; 
	} // fin de getPlan8 

	
	 //------------------------------------------------------------------------------- 
	 public Graph getPlan3(String pName){ 
		Graph Plan_19 = new SingleGraph("Plan_19"); 
			Plan_19.setStrict(false); 
	 		Plan_19.setAutoCreate( true ); 
	 		Plan_19.addAttribute("ui.stylesheet", "url('file:config/Modele1.css')"); 
			Plan_19.addAttribute("ui.quality"); 
			Plan_19.addAttribute("ui.antialias"); 
			Plan_19.addEdge("startJ", "start", "J", true );
	 		Plan_19.addEdge("PT", "P", "T", true ); 
			Plan_19.addEdge("TC_", "T", "C_", true ); 
			Plan_19.addEdge("ST", "S", "T", true ); 
			Plan_19.addEdge("LS", "L", "S", true ); 
			Plan_19.addEdge("SB_", "S", "B_", true ); 
			Plan_19.addEdge("B_C_", "B_", "C_", true ); 
			Plan_19.addEdge("KL", "K", "L", true ); 
			Plan_19.addEdge("LP", "L", "P", true ); 
			Plan_19.addEdge("JK", "J", "K", true ); 
			Plan_19.addEdge("KR", "K", "R", true ); 
			Plan_19.addEdge("RS", "R", "S", true ); 
			Plan_19.getNode("start").setAttribute("ui.label", "start");
			Plan_19.getNode("P").setAttribute("ui.label", "P"); 
			Plan_19.getNode("T").setAttribute("ui.label", "T"); 
			Plan_19.getNode("R").setAttribute("ui.label", "R"); 
			Plan_19.getNode("L").setAttribute("ui.label", "L"); 
			Plan_19.getNode("S").setAttribute("ui.label", "S"); 
			Plan_19.getNode("B_").setAttribute("ui.label", "B_"); 
			Plan_19.getNode("C_").setAttribute("ui.label", "C_"); 
			Plan_19.getNode("K").setAttribute("ui.label", "K"); 
			Plan_19.getNode("J").setAttribute("ui.label", "J"); 
			Plan_19.getEdge("PT").setAttribute("ui.label", "PT"); 
			Plan_19.getEdge("PT").addAttribute("ui.class", "Green"); 
			Plan_19.getEdge("TC_").setAttribute("ui.label", "TC_"); 
			Plan_19.getEdge("TC_").addAttribute("ui.class", "Green"); 
			Plan_19.getEdge("ST").setAttribute("ui.label", "ST"); 
			Plan_19.getEdge("ST").addAttribute("ui.class", "Green"); 
			Plan_19.getEdge("LS").setAttribute("ui.label", "LS"); 
			Plan_19.getEdge("LS").addAttribute("ui.class", "Green"); 
			Plan_19.getEdge("SB_").setAttribute("ui.label", "SB_"); 
			Plan_19.getEdge("SB_").addAttribute("ui.class", "Green"); 
			Plan_19.getEdge("B_C_").setAttribute("ui.label", "B_C_"); 
			Plan_19.getEdge("B_C_").addAttribute("ui.class", "Green"); 
			Plan_19.getEdge("KL").setAttribute("ui.label", "KL"); 
			Plan_19.getEdge("KL").addAttribute("ui.class", "Green"); 
			Plan_19.getEdge("LP").setAttribute("ui.label", "LP"); 
			Plan_19.getEdge("LP").addAttribute("ui.class", "Green"); 
			Plan_19.getEdge("JK").setAttribute("ui.label", "JK"); 
			Plan_19.getEdge("JK").addAttribute("ui.class", "Green"); 
			Plan_19.getEdge("KR").setAttribute("ui.label", "KR"); 
			Plan_19.getEdge("KR").addAttribute("ui.class", "Green"); 
			Plan_19.getEdge("RS").setAttribute("ui.label", "RS"); 
			Plan_19.getEdge("RS").addAttribute("ui.class", "Green"); 
		return Plan_19; 
	} // fin de getPlan19 

	 // ------------------------------------------------------------------------------- 

	 //------------------------------------------------------------------------------- 
	 public Graph getPlan4(String pName){ 
		Graph Plan = new SingleGraph("Plan4"); 
			Plan.setStrict(false); 
	 		Plan.setAutoCreate( true ); 
	 		Plan.addAttribute("ui.stylesheet", "url('file:config/Modele1.css')"); 
			Plan.addAttribute("ui.quality"); 
			Plan.addAttribute("ui.antialias"); 
			Plan.addEdge("startA", "start", "A", true );
			
			//----------------------------------------------------
	 		//-- A-B-C-D
			Plan.addEdge("AB", "A", "B", true ); 
			Plan.addEdge("AC", "A", "C", true ); 
			 
			Plan.addEdge("BD", "B", "D", true );
			Plan.addEdge("CD", "C", "D", true );
			
			Plan.getNode("start").setAttribute("ui.label", "start");
			Plan.getNode("A").setAttribute("ui.label", "A"); 
			Plan.getNode("B").setAttribute("ui.label", "B"); 
			Plan.getNode("C").setAttribute("ui.label", "C"); 
			Plan.getNode("D").setAttribute("ui.label", "D"); 
			
			Plan.getEdge("AB").setAttribute("ui.label", "AB"); 
			Plan.getEdge("AC").setAttribute("ui.label", "AC"); 
			 
			Plan.getEdge("BD").setAttribute("ui.label", "BD"); 
			Plan.getEdge("CD").setAttribute("ui.label", "CD"); 
			//---------------------------------------------------
			
	
			
			//-----------------------------------------------------------
			
			//---------------------------------------------------
			//E-F-G-H
			Plan.addEdge("EF", "E", "F", true ); 
			Plan.addEdge("EG", "E", "G", true ); 
			   
			Plan.addEdge("FH", "F", "H", true );
			Plan.addEdge("GH", "G", "H", true );
			
			Plan.getNode("start").setAttribute("ui.label", "start");
			Plan.getNode("E").setAttribute("ui.label", "E"); 
			Plan.getNode("F").setAttribute("ui.label", "F"); 
			Plan.getNode("G").setAttribute("ui.label", "G"); 
			Plan.getNode("H").setAttribute("ui.label", "H"); 
			
			Plan.getEdge("EF").setAttribute("ui.label", "EF"); 
			Plan.getEdge("EG").setAttribute("ui.label", "EG"); 
			  
			Plan.getEdge("FH").setAttribute("ui.label", "FH"); 
			Plan.getEdge("GH").setAttribute("ui.label", "GH"); 
			//------------------------------------------------------
			
			// I-J-K-L
			Plan.addEdge("IJ", "I", "J", true ); 
			Plan.addEdge("IK", "I", "K", true ); 
			  
			Plan.addEdge("JL", "J", "L", true );
			Plan.addEdge("KL", "K", "L", true );
			
			Plan.getNode("start").setAttribute("ui.label", "start");
			Plan.getNode("I").setAttribute("ui.label", "I"); 
			Plan.getNode("J").setAttribute("ui.label", "J"); 
			Plan.getNode("K").setAttribute("ui.label", "K"); 
			Plan.getNode("L").setAttribute("ui.label", "L"); 
			
			Plan.getEdge("IJ").setAttribute("ui.label", "IJ"); 
			Plan.getEdge("IK").setAttribute("ui.label", "IK"); 
			  
			Plan.getEdge("JL").setAttribute("ui.label", "JL"); 
			Plan.getEdge("KL").setAttribute("ui.label", "KL"); 
			
			//-----------------------------------------------------------
			
			Plan.addEdge("LZ", "L", "Z", true );
			Plan.getNode("Z").setAttribute("ui.label", "Z");
			Plan.getEdge("LZ").setAttribute("ui.label", "LZ"); 
			
			//-----------------------------------------------------------
			//connexion 
			Plan.addEdge("DU", "D", "U", true );
			Plan.getNode("U").setAttribute("ui.label", "U");
			Plan.getEdge("DU").setAttribute("ui.label", "DU"); 
			
			Plan.addEdge("UE", "U", "E", true ); 
			Plan.addEdge("UI", "U", "I", true ); 
			Plan.addEdge("DI", "D", "I", true ); 
			Plan.addEdge("HZ", "H", "Z", true ); 
			
			
		return Plan; 
	} // fin de getPlan22 
// ------------------------------------------------------------------------------- 

	
	 //------------------------------------------------------------------------------- 
	 public Graph getPlan5(String pName){ 
		 Graph Plan = new SingleGraph("Plan5"); 
			Plan.setStrict(false); 
	 		Plan.setAutoCreate( true ); 
	 		Plan.addAttribute("ui.stylesheet", "url('file:config/Modele1.css')"); 
			Plan.addAttribute("ui.quality"); 
			Plan.addAttribute("ui.antialias"); 
			Plan.addEdge("startA", "start", "A", true );
	 		
			
			//----------------------------------------------------
	 		//-- A-B-C-D
			Plan.addEdge("AB", "A", "B", true ); 
			Plan.addEdge("AC", "A", "C", true ); 
			 
			Plan.addEdge("BD", "B", "D", true );
			Plan.addEdge("CD", "C", "D", true );
			
			Plan.getNode("start").setAttribute("ui.label", "start");
			Plan.getNode("A").setAttribute("ui.label", "A"); 
			Plan.getNode("B").setAttribute("ui.label", "B"); 
			Plan.getNode("C").setAttribute("ui.label", "C"); 
			Plan.getNode("D").setAttribute("ui.label", "D"); 
			
			Plan.getEdge("AB").setAttribute("ui.label", "AB"); 
			Plan.getEdge("AC").setAttribute("ui.label", "AC"); 
			 
			Plan.getEdge("BD").setAttribute("ui.label", "BD"); 
			Plan.getEdge("CD").setAttribute("ui.label", "CD"); 
			//---------------------------------------------------
			
			//---------------------------------------------------
			//E-F-G-H
			Plan.addEdge("EF", "E", "F", true ); 
			Plan.addEdge("EG", "E", "G", true ); 
			   
			Plan.addEdge("FH", "F", "H", true );
			Plan.addEdge("GH", "G", "H", true );
			
			Plan.getNode("start").setAttribute("ui.label", "start");
			Plan.getNode("E").setAttribute("ui.label", "E"); 
			Plan.getNode("F").setAttribute("ui.label", "F"); 
			Plan.getNode("G").setAttribute("ui.label", "G"); 
			Plan.getNode("H").setAttribute("ui.label", "H"); 
			
			Plan.getEdge("EF").setAttribute("ui.label", "EF"); 
			Plan.getEdge("EG").setAttribute("ui.label", "EG"); 
			  
			Plan.getEdge("FH").setAttribute("ui.label", "FH"); 
			Plan.getEdge("GH").setAttribute("ui.label", "GH"); 
			//------------------------------------------------------
			
			//------------------------------------------------------
			// M-N-O-P
			Plan.addEdge("MN", "M", "N", true ); 
			Plan.addEdge("MO", "M", "O", true ); 
			  
			Plan.addEdge("NP", "N", "P", true );
			Plan.addEdge("OP", "O", "P", true );
			
			Plan.getNode("start").setAttribute("ui.label", "start");
			Plan.getNode("M").setAttribute("ui.label", "M"); 
			Plan.getNode("N").setAttribute("ui.label", "N"); 
			Plan.getNode("O").setAttribute("ui.label", "O"); 
			Plan.getNode("P").setAttribute("ui.label", "P"); 
			
			Plan.getEdge("MN").setAttribute("ui.label", "MN"); 
			Plan.getEdge("MO").setAttribute("ui.label", "MO"); 
			  
			Plan.getEdge("NP").setAttribute("ui.label", "NP"); 
			Plan.getEdge("OP").setAttribute("ui.label", "OP"); 
			
			//-----------------------------------------------------------
			Plan.addEdge("PY", "P", "Y", true );
			Plan.getNode("Y").setAttribute("ui.label", "Y");
			Plan.getEdge("PY").setAttribute("ui.label", "PY"); 
			
			//-----------------------------------------------------------
			//connexion 
			
			Plan.addEdge("DU", "D", "U", true );
			Plan.getNode("U").setAttribute("ui.label", "U");
			Plan.getEdge("DU").setAttribute("ui.label", "DU"); 
			
			Plan.addEdge("UE", "U", "E", true ); 
			Plan.addEdge("UM", "U", "M", true ); 
			Plan.addEdge("HY", "H", "Y", true ); 
			
						
			
		return Plan; 
	} // fin de getPlan3 

	 // ------------------------------------------------------------------------------- 
	  // ------------------------------------------------------------------------------- 
 
	 //------------------------------------------------------------------------------- 
	 public Graph getPlan6(String pName){ 
		Graph Plan = new SingleGraph("Plan6"); 
			Plan.setStrict(false); 
	 		Plan.setAutoCreate( true ); 
	 		Plan.addAttribute("ui.stylesheet", "url('file:config/Modele1.css')"); 
			Plan.addAttribute("ui.quality"); 
			Plan.addAttribute("ui.antialias");
			
			Plan.addEdge("startQ", "start", "Q", true );
	 		
			
			//---------------------------------------------------
			// Q-R-S-T
			Plan.addEdge("QR", "Q", "R", true ); 
			Plan.addEdge("QS", "Q", "S", true ); 
			   
			Plan.addEdge("RT", "R", "T", true );
			Plan.addEdge("ST", "S", "T", true );
			
			Plan.getNode("start").setAttribute("ui.label", "start");
			Plan.getNode("Q").setAttribute("ui.label", "Q"); 
			Plan.getNode("R").setAttribute("ui.label", "R"); 
			Plan.getNode("S").setAttribute("ui.label", "S"); 
			Plan.getNode("T").setAttribute("ui.label", "T"); 
			
			Plan.getEdge("QR").setAttribute("ui.label", "QR"); 
			Plan.getEdge("QS").setAttribute("ui.label", "QS"); 
			  
			Plan.getEdge("RT").setAttribute("ui.label", "RT"); 
			Plan.getEdge("ST").setAttribute("ui.label", "ST"); 
			
			//---------------------------------------------------
			//E-F-G-H
			Plan.addEdge("EF", "E", "F", true ); 
			Plan.addEdge("EG", "E", "G", true ); 
			   
			Plan.addEdge("FH", "F", "H", true );
			Plan.addEdge("GH", "G", "H", true );
			
			Plan.getNode("start").setAttribute("ui.label", "start");
			Plan.getNode("E").setAttribute("ui.label", "E"); 
			Plan.getNode("F").setAttribute("ui.label", "F"); 
			Plan.getNode("G").setAttribute("ui.label", "G"); 
			Plan.getNode("H").setAttribute("ui.label", "H"); 
			
			Plan.getEdge("EF").setAttribute("ui.label", "EF"); 
			Plan.getEdge("EG").setAttribute("ui.label", "EG"); 
			  
			Plan.getEdge("FH").setAttribute("ui.label", "FH"); 
			Plan.getEdge("GH").setAttribute("ui.label", "GH"); 
			//------------------------------------------------------
			
			//------------------------------------------------------
			// M-N-O-P
			Plan.addEdge("MN", "M", "N", true ); 
			Plan.addEdge("MO", "M", "O", true ); 
			  
			Plan.addEdge("NP", "N", "P", true );
			Plan.addEdge("OP", "O", "P", true );
			
			Plan.getNode("start").setAttribute("ui.label", "start");
			Plan.getNode("M").setAttribute("ui.label", "M"); 
			Plan.getNode("N").setAttribute("ui.label", "N"); 
			Plan.getNode("O").setAttribute("ui.label", "O"); 
			Plan.getNode("P").setAttribute("ui.label", "P"); 
			
			Plan.getEdge("MN").setAttribute("ui.label", "MN"); 
			Plan.getEdge("MO").setAttribute("ui.label", "MO"); 
			  
			Plan.getEdge("NP").setAttribute("ui.label", "NP"); 
			Plan.getEdge("OP").setAttribute("ui.label", "OP"); 
			//--------------------------------------------------
			
			Plan.addEdge("PY", "P", "Y", true );
			Plan.getNode("Y").setAttribute("ui.label", "Y");
			Plan.getEdge("PY").setAttribute("ui.label", "PY"); 
			
			Plan.addEdge("TM", "T", "M", true );
			Plan.getEdge("TM").setAttribute("ui.label", "TM"); 
		
			//-----------------------------------------------------------
			
			Plan.addEdge("TU", "T", "U", true );
			Plan.getNode("U").setAttribute("ui.label", "U");
			Plan.getEdge("TU").setAttribute("ui.label", "TU"); 
			
			Plan.addEdge("UE", "U", "E", true ); 
			Plan.addEdge("UM", "U", "M", true ); 
			Plan.addEdge("HY", "H", "Y", true ); 
			
			//connexion 
			
			
		return Plan; 
	} // fin de getPlan5 

	 // ------------------------------------------------------------------------------- 
	  // ------------------------------------------------------------------------------- 
	 
	 //------------------------------------------------------------------------------- 
	 public Graph getPlan7(String pName){ 
		Graph Plan = new SingleGraph("Plan7"); 
			Plan.setStrict(false); 
	 		Plan.setAutoCreate( true ); 
	 		Plan.addAttribute("ui.stylesheet", "url('file:config/Modele1.css')"); 
			Plan.addAttribute("ui.quality"); 
			Plan.addAttribute("ui.antialias"); 
			
			Plan.addEdge("startQ", "start", "Q", true );
			
			//---------------------------------------------------
			// Q-R-S-T
			Plan.addEdge("QR", "Q", "R", true ); 
			Plan.addEdge("QS", "Q", "S", true ); 
			   
			Plan.addEdge("RT", "R", "T", true );
			Plan.addEdge("ST", "S", "T", true );
			
			Plan.getNode("start").setAttribute("ui.label", "start");
			Plan.getNode("Q").setAttribute("ui.label", "Q"); 
			Plan.getNode("R").setAttribute("ui.label", "R"); 
			Plan.getNode("S").setAttribute("ui.label", "S"); 
			Plan.getNode("T").setAttribute("ui.label", "T"); 
			
			Plan.getEdge("QR").setAttribute("ui.label", "QR"); 
			Plan.getEdge("QS").setAttribute("ui.label", "QS"); 
			  
			Plan.getEdge("RT").setAttribute("ui.label", "RT"); 
			Plan.getEdge("ST").setAttribute("ui.label", "ST"); 
			
			
			//---------------------------------------------------
			//E-F-G-H
			Plan.addEdge("EF", "E", "F", true ); 
			Plan.addEdge("EG", "E", "G", true ); 
			   
			Plan.addEdge("FH", "F", "H", true );
			Plan.addEdge("GH", "G", "H", true );
			
			Plan.getNode("start").setAttribute("ui.label", "start");
			Plan.getNode("E").setAttribute("ui.label", "E"); 
			Plan.getNode("F").setAttribute("ui.label", "F"); 
			Plan.getNode("G").setAttribute("ui.label", "G"); 
			Plan.getNode("H").setAttribute("ui.label", "H"); 
			
			Plan.getEdge("EF").setAttribute("ui.label", "EF"); 
			Plan.getEdge("EG").setAttribute("ui.label", "EG"); 
			  
			Plan.getEdge("FH").setAttribute("ui.label", "FH"); 
			Plan.getEdge("GH").setAttribute("ui.label", "GH"); 
			//------------------------------------------------------
			
			//------------------------------------------------------
			
			// I-J-K-L
			Plan.addEdge("IJ", "I", "J", true ); 
			Plan.addEdge("IK", "I", "K", true ); 
			  
			Plan.addEdge("JL", "J", "L", true );
			Plan.addEdge("KL", "K", "L", true );
			
			Plan.getNode("start").setAttribute("ui.label", "start");
			Plan.getNode("I").setAttribute("ui.label", "I"); 
			Plan.getNode("J").setAttribute("ui.label", "J"); 
			Plan.getNode("K").setAttribute("ui.label", "K"); 
			Plan.getNode("L").setAttribute("ui.label", "L"); 
			
			Plan.getEdge("IJ").setAttribute("ui.label", "IJ"); 
			Plan.getEdge("IK").setAttribute("ui.label", "IK"); 
			  
			Plan.getEdge("JL").setAttribute("ui.label", "JL"); 
			Plan.getEdge("KL").setAttribute("ui.label", "KL"); 
			
			//-----------------------------------------------------------
			Plan.addEdge("LZ", "L", "Z", true );
			Plan.getNode("Z").setAttribute("ui.label", "Z");
			Plan.getEdge("LZ").setAttribute("ui.label", "LZ"); 
			
			//-----------------------------------------------------------
			
			Plan.addEdge("TU", "T", "U", true );
			Plan.getNode("U").setAttribute("ui.label", "U");
			Plan.getEdge("TU").setAttribute("ui.label", "TU"); 
			
			Plan.addEdge("UE", "U", "E", true ); 
			Plan.addEdge("UI", "U", "I", true ); 
			Plan.addEdge("HZ", "H", "Z", true ); 
			
			//connexion 

			
			
		return Plan; 
	} // fin de getPlan6 

	 // ------------------------------------------------------------------------------- 
	  // ------------------------------------------------------------------------------- 
		 // ------------------------------------------------------------------------------- 
	  // ------------------------------------------------------------------------------- 
	 
	 //------------------------------------------------------------------------------- 
	 public Graph getPlan8(String pName){ 
		Graph Plan = new SingleGraph("Plan8"); 
		 
			Plan.setStrict(false); 
	 		Plan.setAutoCreate( true ); 
	 		Plan.addAttribute("ui.stylesheet", "url('file:config/Modele1.css')"); 
			Plan.addAttribute("ui.quality"); 
			Plan.addAttribute("ui.antialias"); 
			Plan.addEdge("startV", "start", "V", true );
	 		
	 		
			Plan.addEdge("VU", "V", "U", true ); 
			Plan.addEdge("VD", "V", "D", true ); 
			Plan.addEdge("VT", "V", "T", true ); 
			Plan.addEdge("UE", "U", "E", true ); 
			
			//---------------------------------------------------
			//E-F-G-H
			Plan.addEdge("EF", "E", "F", true ); 
			Plan.addEdge("EG", "E", "G", true ); 
			Plan.addEdge("FH", "F", "H", true );
			Plan.addEdge("GH", "G", "H", true );
			
			Plan.getNode("start").setAttribute("ui.label", "start");
			Plan.getNode("V").setAttribute("ui.label", "V"); 
			Plan.getNode("E").setAttribute("ui.label", "E"); 
			Plan.getNode("F").setAttribute("ui.label", "F"); 
			Plan.getNode("G").setAttribute("ui.label", "G"); 
			Plan.getNode("H").setAttribute("ui.label", "H"); 
			Plan.getNode("D").setAttribute("ui.label", "D"); 
			Plan.getNode("T").setAttribute("ui.label", "T"); 
			
			Plan.getEdge("VU").setAttribute("ui.label", "VU"); 
			Plan.getEdge("VD").setAttribute("ui.label", "VD"); 
			Plan.getEdge("VT").setAttribute("ui.label", "VT"); 
			Plan.getEdge("UE").setAttribute("ui.label", "UE"); 
			
			
			
			Plan.getEdge("EF").setAttribute("ui.label", "EF"); 
			Plan.getEdge("EG").setAttribute("ui.label", "EG"); 
			Plan.getEdge("FH").setAttribute("ui.label", "FH"); 
			Plan.getEdge("GH").setAttribute("ui.label", "GH"); 
			
			Plan.addEdge("HX", "H", "X", true ); 
			Plan.getEdge("HX").setAttribute("ui.label", "HX"); 
			Plan.getNode("X").setAttribute("ui.label", "X"); 
			
			
			Plan.addEdge("IJ", "I", "J", true ); 
			Plan.addEdge("IK", "I", "K", true ); 
			Plan.addEdge("JL", "J", "L", true );
			Plan.addEdge("KL", "K", "L", true );
			
			Plan.getNode("I").setAttribute("ui.label", "I"); 
			Plan.getNode("J").setAttribute("ui.label", "J"); 
			Plan.getNode("K").setAttribute("ui.label", "K"); 
			Plan.getNode("L").setAttribute("ui.label", "L"); 
			
			Plan.getEdge("IJ").setAttribute("ui.label", "IJ"); 
			Plan.getEdge("IK").setAttribute("ui.label", "IK"); 
			Plan.getEdge("JL").setAttribute("ui.label", "JL"); 
			Plan.getEdge("KL").setAttribute("ui.label", "KL"); 
			
			Plan.addEdge("DI", "D", "I", true );
			Plan.getEdge("DI").setAttribute("ui.label", "DI"); 
			
			Plan.addEdge("LH", "L", "H", true );
			Plan.getEdge("LH").setAttribute("ui.label", "LH"); 
			
			
			
			//------------------------------------------------------
			// M-N-O-P
			Plan.addEdge("MN", "M", "N", true ); 
			Plan.addEdge("MO", "M", "O", true ); 
			  
			Plan.addEdge("NP", "N", "P", true );
			Plan.addEdge("OP", "O", "P", true );
			
			Plan.getNode("start").setAttribute("ui.label", "start");
			Plan.getNode("M").setAttribute("ui.label", "M"); 
			Plan.getNode("N").setAttribute("ui.label", "N"); 
			Plan.getNode("O").setAttribute("ui.label", "O"); 
			Plan.getNode("P").setAttribute("ui.label", "P"); 
			
			Plan.getEdge("MN").setAttribute("ui.label", "MN"); 
			Plan.getEdge("MO").setAttribute("ui.label", "MO"); 
			  
			Plan.getEdge("NP").setAttribute("ui.label", "NP"); 
			Plan.getEdge("OP").setAttribute("ui.label", "OP"); 
			//--------------------------------------------------
			
			Plan.addEdge("TM", "T", "M", true );
			Plan.getEdge("TM").setAttribute("ui.label", "TM"); 
		
			
			
			Plan.addEdge("PH", "P", "H", true );
			Plan.getEdge("PH").setAttribute("ui.label", "PH"); 
			//------------------------------------------------------
		return Plan; 
	} // fin de getPlan9 

	 // ------------------------------------------------------------------------------- 
	  // ------------------------------------------------------------------------------- 
	 
	 //------------------------------------------------------------------------------- 
	 public Graph getPlan9(String pName){ 
		Graph Plan = new SingleGraph("Plan9"); 
			Plan.setStrict(false); 
	 		Plan.setAutoCreate( true ); 
	 		Plan.addAttribute("ui.stylesheet", "url('file:config/Modele1.css')"); 
			Plan.addAttribute("ui.quality"); 
			Plan.addAttribute("ui.antialias"); 
			
			Plan.addEdge("startB", "start", "B", true );
			
			
			//---------------------------------------------------
			//E-F-G-H
			Plan.addEdge("EF", "E", "F", true ); 
			Plan.addEdge("EG", "E", "G", true ); 
			Plan.addEdge("EH", "E", "H", true ); 
			Plan.addEdge("FH", "F", "H", true );
			Plan.addEdge("GH", "G", "H", true );
			
			Plan.getNode("start").setAttribute("ui.label", "start");
			Plan.getNode("E").setAttribute("ui.label", "E"); 
			Plan.getNode("F").setAttribute("ui.label", "F"); 
			Plan.getNode("G").setAttribute("ui.label", "G"); 
			Plan.getNode("H").setAttribute("ui.label", "H"); 
			
			Plan.getEdge("EF").setAttribute("ui.label", "EF"); 
			Plan.getEdge("EG").setAttribute("ui.label", "EG"); 
			Plan.getEdge("EH").setAttribute("ui.label", "EH"); 
			Plan.getEdge("FH").setAttribute("ui.label", "FH"); 
			Plan.getEdge("GH").setAttribute("ui.label", "GH"); 
			//------------------------------------------------------
			
			
		return Plan; 
	} // fin de getPlan10 

	 // ------------------------------------------------------------------------------- 
	  // ------------------------------------------------------------------------------- 
	 //------------------------------------------------------------------------------- 
	 public Graph getPlan10(String pName){ 
		Graph Plan = new SingleGraph("Plan10"); 
			Plan.setStrict(false); 
	 		Plan.setAutoCreate( true ); 
	 		Plan.addAttribute("ui.stylesheet", "url('file:config/Modele1.css')"); 
			Plan.addAttribute("ui.quality"); 
			Plan.addAttribute("ui.antialias"); 
			
			Plan.addEdge("startB", "start", "B", true );
			
			
			//---------------------------------------------------
			//E-F-G-H
			Plan.addEdge("EF", "E", "F", true ); 
			Plan.addEdge("EG", "E", "G", true ); 
			Plan.addEdge("EH", "E", "H", true ); 
			Plan.addEdge("FH", "F", "H", true );
			Plan.addEdge("GH", "G", "H", true );
			
			Plan.getNode("start").setAttribute("ui.label", "start");
			Plan.getNode("E").setAttribute("ui.label", "E"); 
			Plan.getNode("F").setAttribute("ui.label", "F"); 
			Plan.getNode("G").setAttribute("ui.label", "G"); 
			Plan.getNode("H").setAttribute("ui.label", "H"); 
			
			Plan.getEdge("EF").setAttribute("ui.label", "EF"); 
			Plan.getEdge("EG").setAttribute("ui.label", "EG"); 
			Plan.getEdge("EH").setAttribute("ui.label", "EH"); 
			Plan.getEdge("FH").setAttribute("ui.label", "FH"); 
			Plan.getEdge("GH").setAttribute("ui.label", "GH"); 
			//------------------------------------------------------
			
			
			
		return Plan; 
	} // fin de getPlan19 

	 // ------------------------------------------------------------------------------- 
	 
	 //------------------------------------------------------------------------------- 
	 public Graph getPlan11(String pName){ 
		Graph Plan_23 = new SingleGraph("Plan_23"); 
			Plan_23.setStrict(false); 
	 		Plan_23.setAutoCreate( true ); 
	 		Plan_23.addAttribute("ui.stylesheet", "url('file:config/Modele1.css')"); 
			Plan_23.addAttribute("ui.quality"); 
			Plan_23.addAttribute("ui.antialias"); 
			
			Plan_23.addEdge("startD", "start", "D", true );
	 		Plan_23.addEdge("TY", "T", "Y", true ); 
			Plan_23.addEdge("IL", "I", "L", true ); 
			Plan_23.addEdge("DH", "D", "H", true ); 
			Plan_23.addEdge("HI", "H", "I", true ); 
			Plan_23.addEdge("LP", "L", "P", true ); 
			Plan_23.addEdge("FI", "F", "I", true ); 
			Plan_23.addEdge("DF", "D", "F", true ); 
			Plan_23.addEdge("IO", "I", "O", true ); 
			Plan_23.addEdge("PT", "P", "T", true ); 
			Plan_23.addEdge("OP", "O", "P", true ); 
			
			Plan_23.getNode("start").setAttribute("ui.label", "start");
			Plan_23.getNode("P").setAttribute("ui.label", "P"); 
			Plan_23.getNode("L").setAttribute("ui.label", "L"); 
			Plan_23.getNode("I").setAttribute("ui.label", "I"); 
			Plan_23.getNode("D").setAttribute("ui.label", "D"); 
			Plan_23.getNode("H").setAttribute("ui.label", "H"); 
			Plan_23.getNode("F").setAttribute("ui.label", "F"); 
			Plan_23.getNode("Y").setAttribute("ui.label", "Y"); 
			Plan_23.getNode("T").setAttribute("ui.label", "T"); 
			Plan_23.getNode("O").setAttribute("ui.label", "O"); 
			Plan_23.getEdge("TY").setAttribute("ui.label", "TY"); 
			Plan_23.getEdge("TY").addAttribute("ui.class", "Lavender"); 
			Plan_23.getEdge("IL").setAttribute("ui.label", "IL"); 
			Plan_23.getEdge("IL").addAttribute("ui.class", "Lavender"); 
			Plan_23.getEdge("DH").setAttribute("ui.label", "DH"); 
			Plan_23.getEdge("DH").addAttribute("ui.class", "Lavender"); 
			Plan_23.getEdge("HI").setAttribute("ui.label", "HI"); 
			Plan_23.getEdge("HI").addAttribute("ui.class", "Lavender"); 
			Plan_23.getEdge("LP").setAttribute("ui.label", "LP"); 
			Plan_23.getEdge("LP").addAttribute("ui.class", "Lavender"); 
			Plan_23.getEdge("FI").setAttribute("ui.label", "FI"); 
			Plan_23.getEdge("FI").addAttribute("ui.class", "Lavender"); 
			Plan_23.getEdge("DF").setAttribute("ui.label", "DF"); 
			Plan_23.getEdge("DF").addAttribute("ui.class", "Lavender"); 
			Plan_23.getEdge("IO").setAttribute("ui.label", "IO"); 
			Plan_23.getEdge("IO").addAttribute("ui.class", "Lavender"); 
			Plan_23.getEdge("PT").setAttribute("ui.label", "PT"); 
			Plan_23.getEdge("PT").addAttribute("ui.class", "Lavender"); 
			Plan_23.getEdge("OP").setAttribute("ui.label", "OP"); 
			Plan_23.getEdge("OP").addAttribute("ui.class", "Lavender"); 
		return Plan_23; 
	} // fin de getPlan23 
	

	 //------------------------------------------------------------------------------- 
	 public Graph getPlan12(String pName){ 
		 Graph Plan = new SingleGraph("Plan12"); 
			Plan.setStrict(false); 
	 		Plan.setAutoCreate( true ); 
	 		Plan.addAttribute("ui.stylesheet", "url('file:config/Modele1.css')"); 
			Plan.addAttribute("ui.quality"); 
			Plan.addAttribute("ui.antialias"); 
			
			Plan.addEdge("startK", "start", "K", true );
	 		Plan.addEdge("KL", "K", "L", true ); 
			Plan.addEdge("LS", "L", "S", true ); 
			Plan.addEdge("SB_", "S", "B_", true ); 
			Plan.addEdge("B_C_", "B_", "C_", true ); 
			Plan.addEdge("C_D_", "C_", "D_", true ); 
			Plan.addEdge("LP", "L", "P", true ); 
			Plan.addEdge("PX", "P", "X", true ); 
			Plan.addEdge("XY", "X", "Y", true ); 
			Plan.addEdge("YD_", "Y", "D_", true ); 
			Plan.addEdge("ST", "S", "T", true ); 
			Plan.addEdge("TY", "T", "Y", true ); 
			Plan.addEdge("PT", "P", "T", true ); 
			Plan.addEdge("TC_", "T", "C_", true ); 
			Plan.addEdge("KR", "K", "R", true ); 
			Plan.addEdge("RS", "R", "S", true ); 
			Plan.addEdge("RA_", "R", "A_", true ); 
			Plan.addEdge("A_B_", "A_", "B_", true ); 
			
			Plan.getNode("start").setAttribute("ui.label", "start");
			Plan.getNode("K").setAttribute("ui.label", "K"); 
			Plan.getNode("L").setAttribute("ui.label", "L"); 
			Plan.getNode("S").setAttribute("ui.label", "S"); 
			Plan.getNode("B_").setAttribute("ui.label", "B_"); 
			Plan.getNode("C_").setAttribute("ui.label", "C_"); 
			Plan.getNode("D_").setAttribute("ui.label", "D_"); 
			Plan.getNode("P").setAttribute("ui.label", "P"); 
			Plan.getNode("X").setAttribute("ui.label", "X"); 
			Plan.getNode("Y").setAttribute("ui.label", "Y"); 
			Plan.getNode("T").setAttribute("ui.label", "T"); 
			Plan.getNode("R").setAttribute("ui.label", "R"); 
			Plan.getNode("A_").setAttribute("ui.label", "A_"); 
			Plan.getEdge("KL").setAttribute("ui.label", "KL"); 
			Plan.getEdge("KL").addAttribute("ui.class", "Blue"); 
			Plan.getEdge("LS").setAttribute("ui.label", "LS"); 
			Plan.getEdge("LS").addAttribute("ui.class", "Blue"); 
			Plan.getEdge("SB_").setAttribute("ui.label", "SB_"); 
			Plan.getEdge("SB_").addAttribute("ui.class", "Blue"); 
			Plan.getEdge("B_C_").setAttribute("ui.label", "B_C_"); 
			Plan.getEdge("B_C_").addAttribute("ui.class", "Blue"); 
			Plan.getEdge("C_D_").setAttribute("ui.label", "C_D_"); 
			Plan.getEdge("C_D_").addAttribute("ui.class", "Blue"); 
			Plan.getEdge("LP").setAttribute("ui.label", "LP"); 
			Plan.getEdge("LP").addAttribute("ui.class", "Blue"); 
			Plan.getEdge("PX").setAttribute("ui.label", "PX"); 
			Plan.getEdge("PX").addAttribute("ui.class", "Blue"); 
			Plan.getEdge("XY").setAttribute("ui.label", "XY"); 
			Plan.getEdge("XY").addAttribute("ui.class", "Blue"); 
			Plan.getEdge("YD_").setAttribute("ui.label", "YD_"); 
			Plan.getEdge("YD_").addAttribute("ui.class", "Blue"); 
			Plan.getEdge("ST").setAttribute("ui.label", "ST"); 
			Plan.getEdge("ST").addAttribute("ui.class", "Blue"); 
			Plan.getEdge("TY").setAttribute("ui.label", "TY"); 
			Plan.getEdge("TY").addAttribute("ui.class", "Blue"); 
			Plan.getEdge("PT").setAttribute("ui.label", "PT"); 
			Plan.getEdge("PT").addAttribute("ui.class", "Blue"); 
			Plan.getEdge("TC_").setAttribute("ui.label", "TC_"); 
			Plan.getEdge("TC_").addAttribute("ui.class", "Blue"); 
			Plan.getEdge("KR").setAttribute("ui.label", "KR"); 
			Plan.getEdge("KR").addAttribute("ui.class", "Blue"); 
			Plan.getEdge("RS").setAttribute("ui.label", "RS"); 
			Plan.getEdge("RS").addAttribute("ui.class", "Blue"); 
			Plan.getEdge("RA_").setAttribute("ui.label", "RA_"); 
			Plan.getEdge("RA_").addAttribute("ui.class", "Blue"); 
			Plan.getEdge("A_B_").setAttribute("ui.label", "A_B_"); 
			Plan.getEdge("A_B_").addAttribute("ui.class", "Blue"); 
		return Plan; 
	} // fin de getPlan12 

	 
	 
	 
	 
	 
 //------------------------------------------------------------------------------- 
	 public Graph getPlan13(String pName){ 
		Graph Plan_5 = new SingleGraph("Plan_5"); 
			Plan_5.setStrict(false); 
	 		Plan_5.setAutoCreate( true ); 
	 		Plan_5.addAttribute("ui.stylesheet", "url('file:config/Modele1.css')"); 
			Plan_5.addAttribute("ui.quality"); 
			Plan_5.addAttribute("ui.antialias"); 
			
			Plan_5.addEdge("startC", "start", "C", true );
	 		Plan_5.addEdge("HI", "H", "I", true ); 
			Plan_5.addEdge("IL", "I", "L", true ); 
			Plan_5.addEdge("LP", "L", "P", true ); 
			Plan_5.addEdge("PT", "P", "T", true ); 
			Plan_5.addEdge("TY", "T", "Y", true ); 
			Plan_5.addEdge("WX", "W", "X", true ); 
			Plan_5.addEdge("VW", "V", "W", true ); 
			Plan_5.addEdge("NV", "N", "V", true ); 
			Plan_5.addEdge("HN", "H", "N", true ); 
			Plan_5.addEdge("XY", "X", "Y", true ); 
			Plan_5.addEdge("PX", "P", "X", true ); 
			Plan_5.addEdge("OP", "O", "P", true ); 
			Plan_5.addEdge("CG", "C", "G", true ); 
			Plan_5.addEdge("GH", "G", "H", true ); 
			Plan_5.addEdge("IO", "I", "O", true ); 
			
			Plan_5.getNode("start").setAttribute("ui.label", "start");
			Plan_5.getNode("H").setAttribute("ui.label", "H"); 
			Plan_5.getNode("I").setAttribute("ui.label", "I"); 
			Plan_5.getNode("L").setAttribute("ui.label", "L"); 
			Plan_5.getNode("P").setAttribute("ui.label", "P"); 
			Plan_5.getNode("T").setAttribute("ui.label", "T"); 
			Plan_5.getNode("Y").setAttribute("ui.label", "Y"); 
			Plan_5.getNode("W").setAttribute("ui.label", "W"); 
			Plan_5.getNode("V").setAttribute("ui.label", "V"); 
			Plan_5.getNode("N").setAttribute("ui.label", "N"); 
			Plan_5.getNode("X").setAttribute("ui.label", "X"); 
			Plan_5.getNode("O").setAttribute("ui.label", "O"); 
			Plan_5.getNode("G").setAttribute("ui.label", "G"); 
			Plan_5.getNode("C").setAttribute("ui.label", "C"); 
			Plan_5.getEdge("HI").setAttribute("ui.label", "HI"); 
			Plan_5.getEdge("HI").addAttribute("ui.class", "Cyan"); 
			Plan_5.getEdge("IL").setAttribute("ui.label", "IL"); 
			Plan_5.getEdge("IL").addAttribute("ui.class", "Cyan"); 
			Plan_5.getEdge("LP").setAttribute("ui.label", "LP"); 
			Plan_5.getEdge("LP").addAttribute("ui.class", "Cyan"); 
			Plan_5.getEdge("PT").setAttribute("ui.label", "PT"); 
			Plan_5.getEdge("PT").addAttribute("ui.class", "Cyan"); 
			Plan_5.getEdge("TY").setAttribute("ui.label", "TY"); 
			Plan_5.getEdge("TY").addAttribute("ui.class", "Cyan"); 
			Plan_5.getEdge("WX").setAttribute("ui.label", "WX"); 
			Plan_5.getEdge("WX").addAttribute("ui.class", "Cyan"); 
			Plan_5.getEdge("VW").setAttribute("ui.label", "VW"); 
			Plan_5.getEdge("VW").addAttribute("ui.class", "Cyan"); 
			Plan_5.getEdge("NV").setAttribute("ui.label", "NV"); 
			Plan_5.getEdge("NV").addAttribute("ui.class", "Cyan"); 
			Plan_5.getEdge("HN").setAttribute("ui.label", "HN"); 
			Plan_5.getEdge("HN").addAttribute("ui.class", "Cyan"); 
			Plan_5.getEdge("XY").setAttribute("ui.label", "XY"); 
			Plan_5.getEdge("XY").addAttribute("ui.class", "Cyan"); 
			Plan_5.getEdge("PX").setAttribute("ui.label", "PX"); 
			Plan_5.getEdge("PX").addAttribute("ui.class", "Cyan"); 
			Plan_5.getEdge("OP").setAttribute("ui.label", "OP"); 
			Plan_5.getEdge("OP").addAttribute("ui.class", "Cyan"); 
			Plan_5.getEdge("CG").setAttribute("ui.label", "CG"); 
			Plan_5.getEdge("CG").addAttribute("ui.class", "Cyan"); 
			Plan_5.getEdge("GH").setAttribute("ui.label", "GH"); 
			Plan_5.getEdge("GH").addAttribute("ui.class", "Cyan"); 
			Plan_5.getEdge("IO").setAttribute("ui.label", "IO"); 
			Plan_5.getEdge("IO").addAttribute("ui.class", "Cyan"); 
		return Plan_5; 
	} // fin de getPlan5 

	 
//------------------------------------------------------------------------------- 
	 public Graph getPlan14(String pName){ 
		Graph Plan_6 = new SingleGraph("Plan_6"); 
			Plan_6.setStrict(false); 
	 		Plan_6.setAutoCreate( true ); 
	 		Plan_6.addAttribute("ui.stylesheet", "url('file:config/Modele1.css')"); 
			Plan_6.addAttribute("ui.quality"); 
			Plan_6.addAttribute("ui.antialias"); 
			
			Plan_6.addEdge("startC", "start", "C", true );
	 		Plan_6.addEdge("CD", "C", "D", true ); 
			Plan_6.addEdge("DF", "D", "F", true ); 
			Plan_6.addEdge("FI", "F", "I", true ); 
			Plan_6.addEdge("IO", "I", "O", true ); 
			Plan_6.addEdge("OP", "O", "P", true ); 
			Plan_6.addEdge("PT", "P", "T", true ); 
			Plan_6.addEdge("KR", "K", "R", true ); 
			Plan_6.addEdge("DH", "D", "H", true ); 
			Plan_6.addEdge("HN", "H", "N", true ); 
			Plan_6.addEdge("NO", "N", "O", true ); 
			Plan_6.addEdge("LS", "L", "S", true ); 
			Plan_6.addEdge("ST", "S", "T", true ); 
			Plan_6.addEdge("TC_", "T", "C_", true ); 
			Plan_6.addEdge("IL", "I", "L", true ); 
			Plan_6.addEdge("LP", "L", "P", true ); 
			Plan_6.addEdge("RS", "R", "S", true ); 
			Plan_6.addEdge("KL", "K", "L", true ); 
			Plan_6.addEdge("FK", "F", "K", true ); 
			
			Plan_6.getNode("start").setAttribute("ui.label", "start");
			Plan_6.getNode("C").setAttribute("ui.label", "C"); 
			Plan_6.getNode("D").setAttribute("ui.label", "D"); 
			Plan_6.getNode("F").setAttribute("ui.label", "F"); 
			Plan_6.getNode("I").setAttribute("ui.label", "I"); 
			Plan_6.getNode("O").setAttribute("ui.label", "O"); 
			Plan_6.getNode("P").setAttribute("ui.label", "P"); 
			Plan_6.getNode("T").setAttribute("ui.label", "T"); 
			Plan_6.getNode("C_").setAttribute("ui.label", "C_"); 
			Plan_6.getNode("H").setAttribute("ui.label", "H"); 
			Plan_6.getNode("N").setAttribute("ui.label", "N"); 
			Plan_6.getNode("K").setAttribute("ui.label", "K"); 
			Plan_6.getNode("S").setAttribute("ui.label", "S"); 
			Plan_6.getNode("L").setAttribute("ui.label", "L"); 
			Plan_6.getNode("R").setAttribute("ui.label", "R"); 
			Plan_6.getEdge("CD").setAttribute("ui.label", "CD"); 
			Plan_6.getEdge("CD").addAttribute("ui.class", "Yellow"); 
			Plan_6.getEdge("DF").setAttribute("ui.label", "DF"); 
			Plan_6.getEdge("DF").addAttribute("ui.class", "Yellow"); 
			Plan_6.getEdge("FI").setAttribute("ui.label", "FI"); 
			Plan_6.getEdge("FI").addAttribute("ui.class", "Yellow"); 
			Plan_6.getEdge("IO").setAttribute("ui.label", "IO"); 
			Plan_6.getEdge("IO").addAttribute("ui.class", "Yellow"); 
			Plan_6.getEdge("OP").setAttribute("ui.label", "OP"); 
			Plan_6.getEdge("OP").addAttribute("ui.class", "Yellow"); 
			Plan_6.getEdge("PT").setAttribute("ui.label", "PT"); 
			Plan_6.getEdge("PT").addAttribute("ui.class", "Yellow"); 
			Plan_6.getEdge("KR").setAttribute("ui.label", "KR"); 
			Plan_6.getEdge("KR").addAttribute("ui.class", "Yellow"); 
			Plan_6.getEdge("DH").setAttribute("ui.label", "DH"); 
			Plan_6.getEdge("DH").addAttribute("ui.class", "Yellow"); 
			Plan_6.getEdge("HN").setAttribute("ui.label", "HN"); 
			Plan_6.getEdge("HN").addAttribute("ui.class", "Yellow"); 
			Plan_6.getEdge("NO").setAttribute("ui.label", "NO"); 
			Plan_6.getEdge("NO").addAttribute("ui.class", "Yellow"); 
			Plan_6.getEdge("LS").setAttribute("ui.label", "LS"); 
			Plan_6.getEdge("LS").addAttribute("ui.class", "Yellow"); 
			Plan_6.getEdge("ST").setAttribute("ui.label", "ST"); 
			Plan_6.getEdge("ST").addAttribute("ui.class", "Yellow"); 
			Plan_6.getEdge("TC_").setAttribute("ui.label", "TC_"); 
			Plan_6.getEdge("TC_").addAttribute("ui.class", "Yellow"); 
			Plan_6.getEdge("IL").setAttribute("ui.label", "IL"); 
			Plan_6.getEdge("IL").addAttribute("ui.class", "Yellow"); 
			Plan_6.getEdge("LP").setAttribute("ui.label", "LP"); 
			Plan_6.getEdge("LP").addAttribute("ui.class", "Yellow"); 
			Plan_6.getEdge("RS").setAttribute("ui.label", "RS"); 
			Plan_6.getEdge("RS").addAttribute("ui.class", "Yellow"); 
			Plan_6.getEdge("KL").setAttribute("ui.label", "KL"); 
			Plan_6.getEdge("KL").addAttribute("ui.class", "Yellow"); 
			Plan_6.getEdge("FK").setAttribute("ui.label", "FK"); 
			Plan_6.getEdge("FK").addAttribute("ui.class", "Yellow"); 
		return Plan_6; 
	} // fin de getPlan6 

	 
  // ------------------------------------------------------------------------------- 
	 
 //------------------------------------------------------------------------------- 
public Graph getPlan15(String pName){ 
		Graph Plan_8 = new SingleGraph("Plan_8"); 
			Plan_8.setStrict(false); 
	 		Plan_8.setAutoCreate( true ); 
	 		Plan_8.addAttribute("ui.stylesheet", "url('file:config/Modele1.css')"); 
			Plan_8.addAttribute("ui.quality"); 
			Plan_8.addAttribute("ui.antialias"); 
			
			Plan_8.addEdge("startF", "start", "F", true );
	 		Plan_8.addEdge("FK", "F", "K", true ); 
			Plan_8.addEdge("KR", "K", "R", true ); 
			Plan_8.addEdge("RS", "R", "S", true ); 
			Plan_8.addEdge("ST", "S", "T", true ); 
			Plan_8.addEdge("TC_", "T", "C_", true ); 
			Plan_8.addEdge("C_D_", "C_", "D_", true ); 
			Plan_8.addEdge("LS", "L", "S", true ); 
			Plan_8.addEdge("SB_", "S", "B_", true ); 
			Plan_8.addEdge("B_C_", "B_", "C_", true ); 
			Plan_8.addEdge("KL", "K", "L", true ); 
			
			Plan_8.getNode("start").setAttribute("ui.label", "start");
			Plan_8.getNode("F").setAttribute("ui.label", "F"); 
			Plan_8.getNode("K").setAttribute("ui.label", "K"); 
			Plan_8.getNode("R").setAttribute("ui.label", "R"); 
			Plan_8.getNode("S").setAttribute("ui.label", "S"); 
			Plan_8.getNode("T").setAttribute("ui.label", "T"); 
			Plan_8.getNode("C_").setAttribute("ui.label", "C_"); 
			Plan_8.getNode("D_").setAttribute("ui.label", "D_"); 
			Plan_8.getNode("B_").setAttribute("ui.label", "B_"); 
			Plan_8.getNode("L").setAttribute("ui.label", "L"); 
			Plan_8.getEdge("FK").setAttribute("ui.label", "FK"); 
			Plan_8.getEdge("FK").addAttribute("ui.class", "Salmon"); 
			Plan_8.getEdge("KR").setAttribute("ui.label", "KR"); 
			Plan_8.getEdge("KR").addAttribute("ui.class", "Salmon"); 
			Plan_8.getEdge("RS").setAttribute("ui.label", "RS"); 
			Plan_8.getEdge("RS").addAttribute("ui.class", "Salmon"); 
			Plan_8.getEdge("ST").setAttribute("ui.label", "ST"); 
			Plan_8.getEdge("ST").addAttribute("ui.class", "Salmon"); 
			Plan_8.getEdge("TC_").setAttribute("ui.label", "TC_"); 
			Plan_8.getEdge("TC_").addAttribute("ui.class", "Salmon"); 
			Plan_8.getEdge("C_D_").setAttribute("ui.label", "C_D_"); 
			Plan_8.getEdge("C_D_").addAttribute("ui.class", "Salmon"); 
			Plan_8.getEdge("LS").setAttribute("ui.label", "LS"); 
			Plan_8.getEdge("LS").addAttribute("ui.class", "Salmon"); 
			Plan_8.getEdge("SB_").setAttribute("ui.label", "SB_"); 
			Plan_8.getEdge("SB_").addAttribute("ui.class", "Salmon"); 
			Plan_8.getEdge("B_C_").setAttribute("ui.label", "B_C_"); 
			Plan_8.getEdge("B_C_").addAttribute("ui.class", "Salmon"); 
			Plan_8.getEdge("KL").setAttribute("ui.label", "KL"); 
			Plan_8.getEdge("KL").addAttribute("ui.class", "Salmon"); 
		return Plan_8; 
	} // fin de getPlan8 

	 // ------------------------------------------------------------------------------- 
	  // ------------------------------------------------------------------------------- 

//------------------------------------------------------------------------------- 
public Graph getPlan16(String pName){ 
	Graph Plan_11 = new SingleGraph("Plan_11"); 
		Plan_11.setStrict(false); 
		Plan_11.setAutoCreate( true ); 
		Plan_11.addAttribute("ui.stylesheet", "url('file:config/Modele1.css')"); 
		Plan_11.addAttribute("ui.quality"); 
		Plan_11.addAttribute("ui.antialias"); 
		
		Plan_11.addEdge("startC", "start", "C", true );
		Plan_11.addEdge("CG", "C", "G", true ); 
		Plan_11.addEdge("GH", "G", "H", true ); 
		Plan_11.addEdge("HN", "H", "N", true ); 
		Plan_11.addEdge("NO", "N", "O", true ); 
		Plan_11.addEdge("OP", "O", "P", true ); 
		Plan_11.addEdge("PX", "P", "X", true ); 
		Plan_11.addEdge("HI", "H", "I", true ); 
		Plan_11.addEdge("IO", "I", "O", true ); 
		Plan_11.addEdge("OW", "O", "W", true ); 
		Plan_11.addEdge("WX", "W", "X", true ); 
		Plan_11.getNode("start").setAttribute("ui.label", "start");
		Plan_11.getNode("C").setAttribute("ui.label", "C"); 
		Plan_11.getNode("G").setAttribute("ui.label", "G"); 
		Plan_11.getNode("H").setAttribute("ui.label", "H"); 
		Plan_11.getNode("N").setAttribute("ui.label", "N"); 
		Plan_11.getNode("O").setAttribute("ui.label", "O"); 
		Plan_11.getNode("P").setAttribute("ui.label", "P"); 
		Plan_11.getNode("X").setAttribute("ui.label", "X"); 
		Plan_11.getNode("I").setAttribute("ui.label", "I"); 
		Plan_11.getNode("W").setAttribute("ui.label", "W"); 
		Plan_11.getEdge("CG").setAttribute("ui.label", "CG"); 
		Plan_11.getEdge("CG").addAttribute("ui.class", "Pink"); 
		Plan_11.getEdge("GH").setAttribute("ui.label", "GH"); 
		Plan_11.getEdge("GH").addAttribute("ui.class", "Pink"); 
		Plan_11.getEdge("HN").setAttribute("ui.label", "HN"); 
		Plan_11.getEdge("HN").addAttribute("ui.class", "Pink"); 
		Plan_11.getEdge("NO").setAttribute("ui.label", "NO"); 
		Plan_11.getEdge("NO").addAttribute("ui.class", "Pink"); 
		Plan_11.getEdge("OP").setAttribute("ui.label", "OP"); 
		Plan_11.getEdge("OP").addAttribute("ui.class", "Pink"); 
		Plan_11.getEdge("PX").setAttribute("ui.label", "PX"); 
		Plan_11.getEdge("PX").addAttribute("ui.class", "Pink"); 
		Plan_11.getEdge("HI").setAttribute("ui.label", "HI"); 
		Plan_11.getEdge("HI").addAttribute("ui.class", "Pink"); 
		Plan_11.getEdge("IO").setAttribute("ui.label", "IO"); 
		Plan_11.getEdge("IO").addAttribute("ui.class", "Pink"); 
		Plan_11.getEdge("OW").setAttribute("ui.label", "OW"); 
		Plan_11.getEdge("OW").addAttribute("ui.class", "Pink"); 
		Plan_11.getEdge("WX").setAttribute("ui.label", "WX"); 
		Plan_11.getEdge("WX").addAttribute("ui.class", "Pink"); 
	return Plan_11; 
} // fin de getPlan11 

// ------------------------------------------------------------------------------- 

//------------------------------------------------------------------------------- 
public Graph getPlan17(String pName){ 
	Graph Plan_1 = new SingleGraph("Plan_1"); 
		Plan_1.setStrict(false); 
		Plan_1.setAutoCreate( true ); 
		Plan_1.addAttribute("ui.stylesheet", "url('file:config/Modele1.css')"); 
		Plan_1.addAttribute("ui.quality"); 
		Plan_1.addAttribute("ui.antialias"); 
		
		Plan_1.addEdge("startH", "start", "H", true );
		Plan_1.addEdge("HN", "H", "N", true ); 
		Plan_1.addEdge("NO", "N", "O", true ); 
		Plan_1.addEdge("OP", "O", "P", true ); 
		Plan_1.addEdge("PX", "P", "X", true ); 
		Plan_1.addEdge("XY", "X", "Y", true ); 
		Plan_1.addEdge("YD_", "Y", "D_", true ); 
		Plan_1.addEdge("HI", "H", "I", true ); 
		Plan_1.addEdge("IO", "I", "O", true ); 
		Plan_1.addEdge("OW", "O", "W", true ); 
		Plan_1.addEdge("WX", "W", "X", true ); 
		Plan_1.addEdge("IL", "I", "L", true ); 
		Plan_1.addEdge("LS", "L", "S", true ); 
		Plan_1.addEdge("ST", "S", "T", true ); 
		Plan_1.addEdge("TC_", "T", "C_", true ); 
		Plan_1.addEdge("C_D_", "C_", "D_", true ); 
		
		Plan_1.getNode("start").setAttribute("ui.label", "start");
		Plan_1.getNode("H").setAttribute("ui.label", "H"); 
		Plan_1.getNode("N").setAttribute("ui.label", "N"); 
		Plan_1.getNode("O").setAttribute("ui.label", "O"); 
		Plan_1.getNode("P").setAttribute("ui.label", "P"); 
		Plan_1.getNode("X").setAttribute("ui.label", "X"); 
		Plan_1.getNode("Y").setAttribute("ui.label", "Y"); 
		Plan_1.getNode("D_").setAttribute("ui.label", "D_"); 
		Plan_1.getNode("I").setAttribute("ui.label", "I"); 
		Plan_1.getNode("W").setAttribute("ui.label", "W"); 
		Plan_1.getNode("L").setAttribute("ui.label", "L"); 
		Plan_1.getNode("S").setAttribute("ui.label", "S"); 
		Plan_1.getNode("T").setAttribute("ui.label", "T"); 
		Plan_1.getNode("C_").setAttribute("ui.label", "C_"); 
		Plan_1.getEdge("HN").setAttribute("ui.label", "HN"); 
		Plan_1.getEdge("HN").addAttribute("ui.class", "Blue"); 
		Plan_1.getEdge("NO").setAttribute("ui.label", "NO"); 
		Plan_1.getEdge("NO").addAttribute("ui.class", "Blue"); 
		Plan_1.getEdge("OP").setAttribute("ui.label", "OP"); 
		Plan_1.getEdge("OP").addAttribute("ui.class", "Blue"); 
		Plan_1.getEdge("PX").setAttribute("ui.label", "PX"); 
		Plan_1.getEdge("PX").addAttribute("ui.class", "Blue"); 
		Plan_1.getEdge("XY").setAttribute("ui.label", "XY"); 
		Plan_1.getEdge("XY").addAttribute("ui.class", "Blue"); 
		Plan_1.getEdge("YD_").setAttribute("ui.label", "YD_"); 
		Plan_1.getEdge("YD_").addAttribute("ui.class", "Blue"); 
		Plan_1.getEdge("HI").setAttribute("ui.label", "HI"); 
		Plan_1.getEdge("HI").addAttribute("ui.class", "Blue"); 
		Plan_1.getEdge("IO").setAttribute("ui.label", "IO"); 
		Plan_1.getEdge("IO").addAttribute("ui.class", "Blue"); 
		Plan_1.getEdge("OW").setAttribute("ui.label", "OW"); 
		Plan_1.getEdge("OW").addAttribute("ui.class", "Blue"); 
		Plan_1.getEdge("WX").setAttribute("ui.label", "WX"); 
		Plan_1.getEdge("WX").addAttribute("ui.class", "Blue"); 
		Plan_1.getEdge("IL").setAttribute("ui.label", "IL"); 
		Plan_1.getEdge("IL").addAttribute("ui.class", "Blue"); 
		Plan_1.getEdge("LS").setAttribute("ui.label", "LS"); 
		Plan_1.getEdge("LS").addAttribute("ui.class", "Blue"); 
		Plan_1.getEdge("ST").setAttribute("ui.label", "ST"); 
		Plan_1.getEdge("ST").addAttribute("ui.class", "Blue"); 
		Plan_1.getEdge("TC_").setAttribute("ui.label", "TC_"); 
		Plan_1.getEdge("TC_").addAttribute("ui.class", "Blue"); 
		Plan_1.getEdge("C_D_").setAttribute("ui.label", "C_D_"); 
		Plan_1.getEdge("C_D_").addAttribute("ui.class", "Blue"); 
	return Plan_1; 
} // fin de getPlan1 

// ------------------------------------------------------------------------------- 


//------------------------------------------------------------------------------- 
public Graph getPlan18(String pName){ 
	Graph Plan = new SingleGraph("Plan1001"); 
	Plan.setStrict(false); 
	Plan.setAutoCreate( true ); 
	Plan.addAttribute("ui.stylesheet", "url('file:config/Modele1.css')"); 
	Plan.addAttribute("ui.quality"); 
	Plan.addAttribute("ui.antialias"); 
	
	Plan.addEdge("startK", "start", "K", true );
	Plan.addEdge("KL", "K", "L", true ); 
	Plan.addEdge("LS", "L", "S", true ); 
	Plan.addEdge("SB_", "S", "B_", true ); 
	Plan.addEdge("B_C_", "B_", "C_", true ); 
	Plan.addEdge("C_D_", "C_", "D_", true ); 
	Plan.addEdge("LP", "L", "P", true ); 
	Plan.addEdge("PX", "P", "X", true ); 
	Plan.addEdge("XY", "X", "Y", true ); 
	Plan.addEdge("YD_", "Y", "D_", true ); 
	Plan.addEdge("ST", "S", "T", true ); 
	Plan.addEdge("TY", "T", "Y", true ); 
	Plan.addEdge("PT", "P", "T", true ); 
	Plan.addEdge("TC_", "T", "C_", true ); 
	Plan.addEdge("KR", "K", "R", true ); 
	Plan.addEdge("RS", "R", "S", true ); 
	//Plan.addEdge("RA_", "R", "A_", true ); 
	//Plan.addEdge("A_B_", "A_", "B_", true ); 
	
	Plan.getNode("start").setAttribute("ui.label", "start");
	Plan.getNode("K").setAttribute("ui.label", "K"); 
	Plan.getNode("L").setAttribute("ui.label", "L"); 
	Plan.getNode("S").setAttribute("ui.label", "S"); 
	Plan.getNode("B_").setAttribute("ui.label", "B_"); 
	Plan.getNode("C_").setAttribute("ui.label", "C_"); 
	Plan.getNode("D_").setAttribute("ui.label", "D_"); 
	Plan.getNode("P").setAttribute("ui.label", "P"); 
	Plan.getNode("X").setAttribute("ui.label", "X"); 
	Plan.getNode("Y").setAttribute("ui.label", "Y"); 
	Plan.getNode("T").setAttribute("ui.label", "T"); 
	Plan.getNode("R").setAttribute("ui.label", "R"); 
	//Plan.getNode("A_").setAttribute("ui.label", "A_"); 
	Plan.getEdge("KL").setAttribute("ui.label", "KL"); 
	Plan.getEdge("KL").addAttribute("ui.class", "Blue"); 
	Plan.getEdge("LS").setAttribute("ui.label", "LS"); 
	Plan.getEdge("LS").addAttribute("ui.class", "Blue"); 
	Plan.getEdge("SB_").setAttribute("ui.label", "SB_"); 
	Plan.getEdge("SB_").addAttribute("ui.class", "Blue"); 
	Plan.getEdge("B_C_").setAttribute("ui.label", "B_C_"); 
	Plan.getEdge("B_C_").addAttribute("ui.class", "Blue"); 
	Plan.getEdge("C_D_").setAttribute("ui.label", "C_D_"); 
	Plan.getEdge("C_D_").addAttribute("ui.class", "Blue"); 
	Plan.getEdge("LP").setAttribute("ui.label", "LP"); 
	Plan.getEdge("LP").addAttribute("ui.class", "Blue"); 
	Plan.getEdge("PX").setAttribute("ui.label", "PX"); 
	Plan.getEdge("PX").addAttribute("ui.class", "Blue"); 
	Plan.getEdge("XY").setAttribute("ui.label", "XY"); 
	Plan.getEdge("XY").addAttribute("ui.class", "Blue"); 
	Plan.getEdge("YD_").setAttribute("ui.label", "YD_"); 
	Plan.getEdge("YD_").addAttribute("ui.class", "Blue"); 
	Plan.getEdge("ST").setAttribute("ui.label", "ST"); 
	Plan.getEdge("ST").addAttribute("ui.class", "Blue"); 
	Plan.getEdge("TY").setAttribute("ui.label", "TY"); 
	Plan.getEdge("TY").addAttribute("ui.class", "Blue"); 
	Plan.getEdge("PT").setAttribute("ui.label", "PT"); 
	Plan.getEdge("PT").addAttribute("ui.class", "Blue"); 
	Plan.getEdge("TC_").setAttribute("ui.label", "TC_"); 
	Plan.getEdge("TC_").addAttribute("ui.class", "Blue"); 
	Plan.getEdge("KR").setAttribute("ui.label", "KR"); 
	Plan.getEdge("KR").addAttribute("ui.class", "Blue"); 
	Plan.getEdge("RS").setAttribute("ui.label", "RS"); 
	Plan.getEdge("RS").addAttribute("ui.class", "Blue"); 
	return Plan; 
} // fin de getPlan4 

// ------------------------------------------------------------------------------- 
 // ------------------------------------------------------------------------------- 


//------------------------------------------------------------------------------- 
public Graph getPlan19(String pName){ 
	Graph Plan_5 = new SingleGraph("Plan_5"); 
		Plan_5.setStrict(false); 
		Plan_5.setAutoCreate( true ); 
		Plan_5.addAttribute("ui.stylesheet", "url('file:config/Modele1.css')"); 
		Plan_5.addAttribute("ui.quality"); 
		Plan_5.addAttribute("ui.antialias"); 
		
		Plan_5.addEdge("startF", "start", "F", true );
		Plan_5.addEdge("YD_", "Y", "D_", true ); 
		Plan_5.addEdge("TY", "T", "Y", true ); 
		Plan_5.addEdge("LS", "L", "S", true ); 
		Plan_5.addEdge("ST", "S", "T", true ); 
		Plan_5.addEdge("C_D_", "C_", "D_", true ); 
		Plan_5.addEdge("FK", "F", "K", true ); 
		Plan_5.addEdge("KL", "K", "L", true ); 
		Plan_5.addEdge("LP", "L", "P", true ); 
		Plan_5.addEdge("PT", "P", "T", true ); 
		Plan_5.addEdge("TC_", "T", "C_", true ); 
		Plan_5.getNode("start").setAttribute("ui.label", "start");
		Plan_5.getNode("Y").setAttribute("ui.label", "Y"); 
		Plan_5.getNode("S").setAttribute("ui.label", "S"); 
		Plan_5.getNode("D_").setAttribute("ui.label", "D_"); 
		Plan_5.getNode("C_").setAttribute("ui.label", "C_"); 
		Plan_5.getNode("T").setAttribute("ui.label", "T"); 
		Plan_5.getNode("P").setAttribute("ui.label", "P"); 
		Plan_5.getNode("F").setAttribute("ui.label", "F"); 
		Plan_5.getNode("K").setAttribute("ui.label", "K"); 
		Plan_5.getNode("L").setAttribute("ui.label", "L"); 
		Plan_5.getEdge("YD_").setAttribute("ui.label", "YD_"); 
		Plan_5.getEdge("YD_").addAttribute("ui.class", "Cyan"); 
		Plan_5.getEdge("TY").setAttribute("ui.label", "TY"); 
		Plan_5.getEdge("TY").addAttribute("ui.class", "Cyan"); 
		Plan_5.getEdge("LS").setAttribute("ui.label", "LS"); 
		Plan_5.getEdge("LS").addAttribute("ui.class", "Cyan"); 
		Plan_5.getEdge("ST").setAttribute("ui.label", "ST"); 
		Plan_5.getEdge("ST").addAttribute("ui.class", "Cyan"); 
		Plan_5.getEdge("C_D_").setAttribute("ui.label", "C_D_"); 
		Plan_5.getEdge("C_D_").addAttribute("ui.class", "Cyan"); 
		Plan_5.getEdge("FK").setAttribute("ui.label", "FK"); 
		Plan_5.getEdge("FK").addAttribute("ui.class", "Cyan"); 
		Plan_5.getEdge("KL").setAttribute("ui.label", "KL"); 
		Plan_5.getEdge("KL").addAttribute("ui.class", "Cyan"); 
		Plan_5.getEdge("LP").setAttribute("ui.label", "LP"); 
		Plan_5.getEdge("LP").addAttribute("ui.class", "Cyan"); 
		Plan_5.getEdge("PT").setAttribute("ui.label", "PT"); 
		Plan_5.getEdge("PT").addAttribute("ui.class", "Cyan"); 
		Plan_5.getEdge("TC_").setAttribute("ui.label", "TC_"); 
		Plan_5.getEdge("TC_").addAttribute("ui.class", "Cyan"); 
	return Plan_5; 
} // fin de getPlan5 

// ------------------------------------------------------------------------------- 
 // ------------------------------------------------------------------------------- 

//------------------------------------------------------------------------------- 
public Graph getPlan20(String pName){ 
	Graph Plan_6 = new SingleGraph("Plan_6"); 
		Plan_6.setStrict(false); 
		Plan_6.setAutoCreate( true ); 
		Plan_6.addAttribute("ui.stylesheet", "url('file:config/Modele1.css')"); 
		Plan_6.addAttribute("ui.quality"); 
		Plan_6.addAttribute("ui.antialias"); 
		
		Plan_6.addEdge("startE", "start", "E", true );
		Plan_6.addEdge("FK", "F", "K", true ); 
		Plan_6.addEdge("KL", "K", "L", true ); 
		Plan_6.addEdge("PX", "P", "X", true ); 
		Plan_6.addEdge("LP", "L", "P", true ); 
		Plan_6.addEdge("FI", "F", "I", true ); 
		Plan_6.addEdge("IL", "I", "L", true ); 
		Plan_6.addEdge("EF", "E", "F", true ); 
		Plan_6.addEdge("WX", "W", "X", true ); 
		Plan_6.addEdge("IO", "I", "O", true ); 
		Plan_6.addEdge("OW", "O", "W", true ); 
		
		Plan_6.getNode("start").setAttribute("ui.label", "start");
		Plan_6.getNode("F").setAttribute("ui.label", "F"); 
		Plan_6.getNode("K").setAttribute("ui.label", "K"); 
		Plan_6.getNode("W").setAttribute("ui.label", "W"); 
		Plan_6.getNode("X").setAttribute("ui.label", "X"); 
		Plan_6.getNode("P").setAttribute("ui.label", "P"); 
		Plan_6.getNode("E").setAttribute("ui.label", "E"); 
		Plan_6.getNode("L").setAttribute("ui.label", "L"); 
		Plan_6.getNode("O").setAttribute("ui.label", "O"); 
		Plan_6.getNode("I").setAttribute("ui.label", "I"); 
		Plan_6.getEdge("FK").setAttribute("ui.label", "FK"); 
		Plan_6.getEdge("FK").addAttribute("ui.class", "Coral"); 
		Plan_6.getEdge("KL").setAttribute("ui.label", "KL"); 
		Plan_6.getEdge("KL").addAttribute("ui.class", "Coral"); 
		Plan_6.getEdge("PX").setAttribute("ui.label", "PX"); 
		Plan_6.getEdge("PX").addAttribute("ui.class", "Coral"); 
		Plan_6.getEdge("LP").setAttribute("ui.label", "LP"); 
		Plan_6.getEdge("LP").addAttribute("ui.class", "Coral"); 
		Plan_6.getEdge("FI").setAttribute("ui.label", "FI"); 
		Plan_6.getEdge("FI").addAttribute("ui.class", "Coral"); 
		Plan_6.getEdge("IL").setAttribute("ui.label", "IL"); 
		Plan_6.getEdge("IL").addAttribute("ui.class", "Coral"); 
		Plan_6.getEdge("EF").setAttribute("ui.label", "EF"); 
		Plan_6.getEdge("EF").addAttribute("ui.class", "Coral"); 
		Plan_6.getEdge("WX").setAttribute("ui.label", "WX"); 
		Plan_6.getEdge("WX").addAttribute("ui.class", "Coral"); 
		Plan_6.getEdge("IO").setAttribute("ui.label", "IO"); 
		Plan_6.getEdge("IO").addAttribute("ui.class", "Coral"); 
		Plan_6.getEdge("OW").setAttribute("ui.label", "OW"); 
		Plan_6.getEdge("OW").addAttribute("ui.class", "Coral"); 
	return Plan_6; 
} // fin de getPlan6 

// ------------------------------------------------------------------------------- 
 // ------------------------------------------------------------------------------- 

//------------------------------------------------------------------------------- 
public Graph getPlan21(String pName){ 
	Graph Plan = new SingleGraph("Plan1001"); 
	Plan.setStrict(false); 
	Plan.setAutoCreate( true ); 
	Plan.addAttribute("ui.stylesheet", "url('file:config/Modele1.css')"); 
	Plan.addAttribute("ui.quality"); 
	Plan.addAttribute("ui.antialias"); 
	
	Plan.addEdge("startK", "start", "K", true );
	Plan.addEdge("KL", "K", "L", true ); 
	Plan.addEdge("LS", "L", "S", true ); 
	Plan.addEdge("SB_", "S", "B_", true ); 
	Plan.addEdge("B_C_", "B_", "C_", true ); 
	Plan.addEdge("C_D_", "C_", "D_", true ); 
	Plan.addEdge("LP", "L", "P", true ); 
	Plan.addEdge("PX", "P", "X", true ); 
	Plan.addEdge("XY", "X", "Y", true ); 
	Plan.addEdge("YD_", "Y", "D_", true ); 
	Plan.addEdge("ST", "S", "T", true ); 
	Plan.addEdge("TY", "T", "Y", true ); 
	Plan.addEdge("PT", "P", "T", true ); 
	Plan.addEdge("TC_", "T", "C_", true ); 
	Plan.addEdge("KR", "K", "R", true ); 
	Plan.addEdge("RS", "R", "S", true ); 
	//Plan.addEdge("RA_", "R", "A_", true ); 
	//Plan.addEdge("A_B_", "A_", "B_", true ); 
	
	Plan.getNode("start").setAttribute("ui.label", "start");
	Plan.getNode("K").setAttribute("ui.label", "K"); 
	Plan.getNode("L").setAttribute("ui.label", "L"); 
	Plan.getNode("S").setAttribute("ui.label", "S"); 
	Plan.getNode("B_").setAttribute("ui.label", "B_"); 
	Plan.getNode("C_").setAttribute("ui.label", "C_"); 
	Plan.getNode("D_").setAttribute("ui.label", "D_"); 
	Plan.getNode("P").setAttribute("ui.label", "P"); 
	Plan.getNode("X").setAttribute("ui.label", "X"); 
	Plan.getNode("Y").setAttribute("ui.label", "Y"); 
	Plan.getNode("T").setAttribute("ui.label", "T"); 
	Plan.getNode("R").setAttribute("ui.label", "R"); 
	//Plan.getNode("A_").setAttribute("ui.label", "A_"); 
	Plan.getEdge("KL").setAttribute("ui.label", "KL"); 
	Plan.getEdge("KL").addAttribute("ui.class", "Blue"); 
	Plan.getEdge("LS").setAttribute("ui.label", "LS"); 
	Plan.getEdge("LS").addAttribute("ui.class", "Blue"); 
	Plan.getEdge("SB_").setAttribute("ui.label", "SB_"); 
	Plan.getEdge("SB_").addAttribute("ui.class", "Blue"); 
	Plan.getEdge("B_C_").setAttribute("ui.label", "B_C_"); 
	Plan.getEdge("B_C_").addAttribute("ui.class", "Blue"); 
	Plan.getEdge("C_D_").setAttribute("ui.label", "C_D_"); 
	Plan.getEdge("C_D_").addAttribute("ui.class", "Blue"); 
	Plan.getEdge("LP").setAttribute("ui.label", "LP"); 
	Plan.getEdge("LP").addAttribute("ui.class", "Blue"); 
	Plan.getEdge("PX").setAttribute("ui.label", "PX"); 
	Plan.getEdge("PX").addAttribute("ui.class", "Blue"); 
	Plan.getEdge("XY").setAttribute("ui.label", "XY"); 
	Plan.getEdge("XY").addAttribute("ui.class", "Blue"); 
	Plan.getEdge("YD_").setAttribute("ui.label", "YD_"); 
	Plan.getEdge("YD_").addAttribute("ui.class", "Blue"); 
	Plan.getEdge("ST").setAttribute("ui.label", "ST"); 
	Plan.getEdge("ST").addAttribute("ui.class", "Blue"); 
	Plan.getEdge("TY").setAttribute("ui.label", "TY"); 
	Plan.getEdge("TY").addAttribute("ui.class", "Blue"); 
	Plan.getEdge("PT").setAttribute("ui.label", "PT"); 
	Plan.getEdge("PT").addAttribute("ui.class", "Blue"); 
	Plan.getEdge("TC_").setAttribute("ui.label", "TC_"); 
	Plan.getEdge("TC_").addAttribute("ui.class", "Blue"); 
	Plan.getEdge("KR").setAttribute("ui.label", "KR"); 
	Plan.getEdge("KR").addAttribute("ui.class", "Blue"); 
	Plan.getEdge("RS").setAttribute("ui.label", "RS"); 
	Plan.getEdge("RS").addAttribute("ui.class", "Blue"); 
	//Plan.getEdge("RA_").setAttribute("ui.label", "RA_"); 
	//Plan.getEdge("RA_").addAttribute("ui.class", "Blue"); 
	//Plan.getEdge("A_B_").setAttribute("ui.label", "A_B_"); 
	//Plan.getEdge("A_B_").addAttribute("ui.class", "Blue"); 
	return Plan; 
} // fin de getPlan10 

// ------------------------------------------------------------------------------- 
 // ------------------------------------------------------------------------------- 

//------------------------------------------------------------------------------- 
public Graph getPlan22(String pName){ 
	Graph Plan_12 = new SingleGraph("Plan_12"); 
		Plan_12.setStrict(false); 
		Plan_12.setAutoCreate( true ); 
		Plan_12.addAttribute("ui.stylesheet", "url('file:config/Modele1.css')"); 
		Plan_12.addAttribute("ui.quality"); 
		Plan_12.addAttribute("ui.antialias"); 
		
		Plan_12.addEdge("startF", "start", "F", true );
		Plan_12.addEdge("RS", "R", "S", true ); 
		Plan_12.addEdge("RA_", "R", "A_", true ); 
		Plan_12.addEdge("A_B_", "A_", "B_", true ); 
		Plan_12.addEdge("FI", "F", "I", true ); 
		Plan_12.addEdge("SB_", "S", "B_", true ); 
		Plan_12.addEdge("KR", "K", "R", true ); 
		Plan_12.addEdge("LS", "L", "S", true ); 
		Plan_12.addEdge("IL", "I", "L", true ); 
		Plan_12.addEdge("FK", "F", "K", true ); 
		
		Plan_12.getNode("start").setAttribute("ui.label", "start");
		Plan_12.getNode("L").setAttribute("ui.label", "L"); 
		Plan_12.getNode("R").setAttribute("ui.label", "R"); 
		Plan_12.getNode("I").setAttribute("ui.label", "I"); 
		Plan_12.getNode("A_").setAttribute("ui.label", "A_"); 
		Plan_12.getNode("K").setAttribute("ui.label", "K"); 
		Plan_12.getNode("B_").setAttribute("ui.label", "B_"); 
		Plan_12.getNode("S").setAttribute("ui.label", "S"); 
		Plan_12.getNode("F").setAttribute("ui.label", "F"); 
		Plan_12.getEdge("RS").setAttribute("ui.label", "RS"); 
		Plan_12.getEdge("RS").addAttribute("ui.class", "Purple"); 
		Plan_12.getEdge("RA_").setAttribute("ui.label", "RA_"); 
		Plan_12.getEdge("RA_").addAttribute("ui.class", "Purple"); 
		Plan_12.getEdge("A_B_").setAttribute("ui.label", "A_B_"); 
		Plan_12.getEdge("A_B_").addAttribute("ui.class", "Purple"); 
		Plan_12.getEdge("FI").setAttribute("ui.label", "FI"); 
		Plan_12.getEdge("FI").addAttribute("ui.class", "Purple"); 
		Plan_12.getEdge("SB_").setAttribute("ui.label", "SB_"); 
		Plan_12.getEdge("SB_").addAttribute("ui.class", "Purple"); 
		Plan_12.getEdge("KR").setAttribute("ui.label", "KR"); 
		Plan_12.getEdge("KR").addAttribute("ui.class", "Purple"); 
		Plan_12.getEdge("LS").setAttribute("ui.label", "LS"); 
		Plan_12.getEdge("LS").addAttribute("ui.class", "Purple"); 
		Plan_12.getEdge("IL").setAttribute("ui.label", "IL"); 
		Plan_12.getEdge("IL").addAttribute("ui.class", "Purple"); 
		Plan_12.getEdge("FK").setAttribute("ui.label", "FK"); 
		Plan_12.getEdge("FK").addAttribute("ui.class", "Purple"); 
	return Plan_12; 
} // fin de getPlan12 


//------------------------------------------------------------------------------------
// ------------------------------------------------------------------------------- 
// ------------------------------------------------------------------------------- 

//------------------------------------------------------------------------------- 
public Graph getPlan23(String pName){ 
	Graph Plan_14 = new SingleGraph("Plan_14"); 
		Plan_14.setStrict(false); 
		Plan_14.setAutoCreate( true ); 
		Plan_14.addAttribute("ui.stylesheet", "url('file:config/Modele1.css')"); 
		Plan_14.addAttribute("ui.quality"); 
		Plan_14.addAttribute("ui.antialias"); 
		
		Plan_14.addEdge("startG", "start", "G", true );
		Plan_14.addEdge("C_D_", "C_", "D_", true ); 
		Plan_14.addEdge("GM", "G", "M", true ); 
		Plan_14.addEdge("MN", "M", "N", true ); 
		Plan_14.addEdge("NO", "N", "O", true ); 
		Plan_14.addEdge("OP", "O", "P", true ); 
		Plan_14.addEdge("PX", "P", "X", true ); 
		Plan_14.addEdge("XY", "X", "Y", true ); 
		Plan_14.addEdge("YD_", "Y", "D_", true ); 
		Plan_14.addEdge("TC_", "T", "C_", true ); 
		Plan_14.addEdge("PT", "P", "T", true ); 
		Plan_14.addEdge("GH", "G", "H", true ); 
		Plan_14.addEdge("HN", "H", "N", true ); 
		Plan_14.addEdge("OW", "O", "W", true ); 
		Plan_14.addEdge("WX", "W", "X", true ); 
		
		Plan_14.getNode("start").setAttribute("ui.label", "start");
		Plan_14.getNode("C_").setAttribute("ui.label", "C_"); 
		Plan_14.getNode("G").setAttribute("ui.label", "G"); 
		Plan_14.getNode("M").setAttribute("ui.label", "M"); 
		Plan_14.getNode("N").setAttribute("ui.label", "N"); 
		Plan_14.getNode("O").setAttribute("ui.label", "O"); 
		Plan_14.getNode("P").setAttribute("ui.label", "P"); 
		Plan_14.getNode("X").setAttribute("ui.label", "X"); 
		Plan_14.getNode("Y").setAttribute("ui.label", "Y"); 
		Plan_14.getNode("D_").setAttribute("ui.label", "D_"); 
		Plan_14.getNode("T").setAttribute("ui.label", "T"); 
		Plan_14.getNode("H").setAttribute("ui.label", "H"); 
		Plan_14.getNode("W").setAttribute("ui.label", "W"); 
		Plan_14.getEdge("C_D_").setAttribute("ui.label", "C_D_"); 
		Plan_14.getEdge("C_D_").addAttribute("ui.class", "Coral"); 
		Plan_14.getEdge("GM").setAttribute("ui.label", "GM"); 
		Plan_14.getEdge("GM").addAttribute("ui.class", "Coral"); 
		Plan_14.getEdge("MN").setAttribute("ui.label", "MN"); 
		Plan_14.getEdge("MN").addAttribute("ui.class", "Coral"); 
		Plan_14.getEdge("NO").setAttribute("ui.label", "NO"); 
		Plan_14.getEdge("NO").addAttribute("ui.class", "Coral"); 
		Plan_14.getEdge("OP").setAttribute("ui.label", "OP"); 
		Plan_14.getEdge("OP").addAttribute("ui.class", "Coral"); 
		Plan_14.getEdge("PX").setAttribute("ui.label", "PX"); 
		Plan_14.getEdge("PX").addAttribute("ui.class", "Coral"); 
		Plan_14.getEdge("XY").setAttribute("ui.label", "XY"); 
		Plan_14.getEdge("XY").addAttribute("ui.class", "Coral"); 
		Plan_14.getEdge("YD_").setAttribute("ui.label", "YD_"); 
		Plan_14.getEdge("YD_").addAttribute("ui.class", "Coral"); 
		Plan_14.getEdge("TC_").setAttribute("ui.label", "TC_"); 
		Plan_14.getEdge("TC_").addAttribute("ui.class", "Coral"); 
		Plan_14.getEdge("PT").setAttribute("ui.label", "PT"); 
		Plan_14.getEdge("PT").addAttribute("ui.class", "Coral"); 
		Plan_14.getEdge("GH").setAttribute("ui.label", "GH"); 
		Plan_14.getEdge("GH").addAttribute("ui.class", "Coral"); 
		Plan_14.getEdge("HN").setAttribute("ui.label", "HN"); 
		Plan_14.getEdge("HN").addAttribute("ui.class", "Coral"); 
		Plan_14.getEdge("OW").setAttribute("ui.label", "OW"); 
		Plan_14.getEdge("OW").addAttribute("ui.class", "Coral"); 
		Plan_14.getEdge("WX").setAttribute("ui.label", "WX"); 
		Plan_14.getEdge("WX").addAttribute("ui.class", "Coral"); 
	return Plan_14; 
} // fin de getPlan14 

// ------------------------------------------------------------------------------- 
// ------------------------------------------------------------------------------- 



//------------------------------------------------------------------------------- 
public Graph getPlan24(String pName){ 
	Graph Plan_18 = new SingleGraph("Plan_18"); 
		Plan_18.setStrict(false); 
		Plan_18.setAutoCreate( true ); 
		Plan_18.addAttribute("ui.stylesheet", "url('file:config/Modele1.css')"); 
		Plan_18.addAttribute("ui.quality"); 
		Plan_18.addAttribute("ui.antialias"); 
		
		Plan_18.addEdge("startB", "start", "B", true );
		Plan_18.addEdge("YD_", "Y", "D_", true ); 
		Plan_18.addEdge("HI", "H", "I", true ); 
		Plan_18.addEdge("IL", "I", "L", true ); 
		Plan_18.addEdge("LP", "L", "P", true ); 
		Plan_18.addEdge("PT", "P", "T", true ); 
		Plan_18.addEdge("TC_", "T", "C_", true ); 
		Plan_18.addEdge("C_D_", "C_", "D_", true ); 
		Plan_18.addEdge("BD", "B", "D", true ); 
		Plan_18.addEdge("DH", "D", "H", true ); 
		Plan_18.addEdge("TY", "T", "Y", true ); 
		Plan_18.addEdge("IO", "I", "O", true ); 
		Plan_18.addEdge("OP", "O", "P", true ); 
		Plan_18.addEdge("BE", "B", "E", true ); 
		Plan_18.addEdge("EJ", "E", "J", true ); 
		Plan_18.addEdge("JK", "J", "K", true ); 
		Plan_18.addEdge("KL", "K", "L", true ); 
		
		Plan_18.getNode("start").setAttribute("ui.label", "start");
		Plan_18.getNode("Y").setAttribute("ui.label", "Y"); 
		Plan_18.getNode("H").setAttribute("ui.label", "H"); 
		Plan_18.getNode("I").setAttribute("ui.label", "I"); 
		Plan_18.getNode("L").setAttribute("ui.label", "L"); 
		Plan_18.getNode("P").setAttribute("ui.label", "P"); 
		Plan_18.getNode("T").setAttribute("ui.label", "T"); 
		Plan_18.getNode("C_").setAttribute("ui.label", "C_"); 
		Plan_18.getNode("D_").setAttribute("ui.label", "D_"); 
		Plan_18.getNode("B").setAttribute("ui.label", "B"); 
		Plan_18.getNode("D").setAttribute("ui.label", "D"); 
		Plan_18.getNode("K").setAttribute("ui.label", "K"); 
		Plan_18.getNode("O").setAttribute("ui.label", "O"); 
		Plan_18.getNode("E").setAttribute("ui.label", "E"); 
		Plan_18.getNode("J").setAttribute("ui.label", "J"); 
		Plan_18.getEdge("YD_").setAttribute("ui.label", "YD_"); 
		Plan_18.getEdge("YD_").addAttribute("ui.class", "Pink"); 
		Plan_18.getEdge("HI").setAttribute("ui.label", "HI"); 
		Plan_18.getEdge("HI").addAttribute("ui.class", "Pink"); 
		Plan_18.getEdge("IL").setAttribute("ui.label", "IL"); 
		Plan_18.getEdge("IL").addAttribute("ui.class", "Pink"); 
		Plan_18.getEdge("LP").setAttribute("ui.label", "LP"); 
		Plan_18.getEdge("LP").addAttribute("ui.class", "Pink"); 
		Plan_18.getEdge("PT").setAttribute("ui.label", "PT"); 
		Plan_18.getEdge("PT").addAttribute("ui.class", "Pink"); 
		Plan_18.getEdge("TC_").setAttribute("ui.label", "TC_"); 
		Plan_18.getEdge("TC_").addAttribute("ui.class", "Pink"); 
		Plan_18.getEdge("C_D_").setAttribute("ui.label", "C_D_"); 
		Plan_18.getEdge("C_D_").addAttribute("ui.class", "Pink"); 
		Plan_18.getEdge("BD").setAttribute("ui.label", "BD"); 
		Plan_18.getEdge("BD").addAttribute("ui.class", "Pink"); 
		Plan_18.getEdge("DH").setAttribute("ui.label", "DH"); 
		Plan_18.getEdge("DH").addAttribute("ui.class", "Pink"); 
		Plan_18.getEdge("TY").setAttribute("ui.label", "TY"); 
		Plan_18.getEdge("TY").addAttribute("ui.class", "Pink"); 
		Plan_18.getEdge("IO").setAttribute("ui.label", "IO"); 
		Plan_18.getEdge("IO").addAttribute("ui.class", "Pink"); 
		Plan_18.getEdge("OP").setAttribute("ui.label", "OP"); 
		Plan_18.getEdge("OP").addAttribute("ui.class", "Pink"); 
		Plan_18.getEdge("BE").setAttribute("ui.label", "BE"); 
		Plan_18.getEdge("BE").addAttribute("ui.class", "Pink"); 
		Plan_18.getEdge("EJ").setAttribute("ui.label", "EJ"); 
		Plan_18.getEdge("EJ").addAttribute("ui.class", "Pink"); 
		Plan_18.getEdge("JK").setAttribute("ui.label", "JK"); 
		Plan_18.getEdge("JK").addAttribute("ui.class", "Pink"); 
		Plan_18.getEdge("KL").setAttribute("ui.label", "KL"); 
		Plan_18.getEdge("KL").addAttribute("ui.class", "Pink"); 
	return Plan_18; 
} // fin de getPlan18 

// ------------------------------------------------------------------------------- 
 // ------------------------------------------------------------------------------- 


//------------------------------------------------------------------------------- 
public Graph getPlan25(String pName){ 
	Graph Plan_4 = new SingleGraph("Plan_4"); 
		Plan_4.setStrict(false); 
		Plan_4.setAutoCreate( true ); 
		Plan_4.addAttribute("ui.stylesheet", "url('file:config/Modele1.css')"); 
		Plan_4.addAttribute("ui.quality"); 
		Plan_4.addAttribute("ui.antialias"); 
		
		Plan_4.addEdge("startC", "start", "C", true );
		Plan_4.addEdge("CD", "C", "D", true ); 
		Plan_4.addEdge("DF", "D", "F", true ); 
		Plan_4.addEdge("FK", "F", "K", true ); 
		Plan_4.addEdge("KR", "K", "R", true ); 
		Plan_4.addEdge("RS", "R", "S", true ); 
		Plan_4.addEdge("ST", "S", "T", true ); 
		Plan_4.addEdge("TC_", "T", "C_", true ); 
		Plan_4.addEdge("C_D_", "C_", "D_", true ); 
		Plan_4.addEdge("LP", "L", "P", true ); 
		Plan_4.addEdge("IL", "I", "L", true ); 
		Plan_4.addEdge("FI", "F", "I", true ); 
		Plan_4.addEdge("TY", "T", "Y", true ); 
		Plan_4.addEdge("PT", "P", "T", true ); 
		Plan_4.addEdge("YD_", "Y", "D_", true ); 
		Plan_4.addEdge("DH", "D", "H", true ); 
		Plan_4.addEdge("HN", "H", "N", true ); 
		Plan_4.addEdge("NO", "N", "O", true ); 
		Plan_4.addEdge("OP", "O", "P", true ); 
		Plan_4.addEdge("PX", "P", "X", true ); 
		Plan_4.addEdge("XY", "X", "Y", true ); 
		
		Plan_4.getNode("start").setAttribute("ui.label", "start");
		Plan_4.getNode("C").setAttribute("ui.label", "C"); 
		Plan_4.getNode("D").setAttribute("ui.label", "D"); 
		Plan_4.getNode("F").setAttribute("ui.label", "F"); 
		Plan_4.getNode("K").setAttribute("ui.label", "K"); 
		Plan_4.getNode("R").setAttribute("ui.label", "R"); 
		Plan_4.getNode("S").setAttribute("ui.label", "S"); 
		Plan_4.getNode("T").setAttribute("ui.label", "T"); 
		Plan_4.getNode("C_").setAttribute("ui.label", "C_"); 
		Plan_4.getNode("D_").setAttribute("ui.label", "D_"); 
		Plan_4.getNode("L").setAttribute("ui.label", "L"); 
		Plan_4.getNode("I").setAttribute("ui.label", "I"); 
		Plan_4.getNode("Y").setAttribute("ui.label", "Y"); 
		Plan_4.getNode("X").setAttribute("ui.label", "X"); 
		Plan_4.getNode("P").setAttribute("ui.label", "P"); 
		Plan_4.getNode("O").setAttribute("ui.label", "O"); 
		Plan_4.getNode("H").setAttribute("ui.label", "H"); 
		Plan_4.getNode("N").setAttribute("ui.label", "N"); 
		Plan_4.getEdge("CD").setAttribute("ui.label", "CD"); 
		Plan_4.getEdge("CD").addAttribute("ui.class", "Red"); 
		Plan_4.getEdge("DF").setAttribute("ui.label", "DF"); 
		Plan_4.getEdge("DF").addAttribute("ui.class", "Red"); 
		Plan_4.getEdge("FK").setAttribute("ui.label", "FK"); 
		Plan_4.getEdge("FK").addAttribute("ui.class", "Red"); 
		Plan_4.getEdge("KR").setAttribute("ui.label", "KR"); 
		Plan_4.getEdge("KR").addAttribute("ui.class", "Red"); 
		Plan_4.getEdge("RS").setAttribute("ui.label", "RS"); 
		Plan_4.getEdge("RS").addAttribute("ui.class", "Red"); 
		Plan_4.getEdge("ST").setAttribute("ui.label", "ST"); 
		Plan_4.getEdge("ST").addAttribute("ui.class", "Red"); 
		Plan_4.getEdge("TC_").setAttribute("ui.label", "TC_"); 
		Plan_4.getEdge("TC_").addAttribute("ui.class", "Red"); 
		Plan_4.getEdge("C_D_").setAttribute("ui.label", "C_D_"); 
		Plan_4.getEdge("C_D_").addAttribute("ui.class", "Red"); 
		Plan_4.getEdge("LP").setAttribute("ui.label", "LP"); 
		Plan_4.getEdge("LP").addAttribute("ui.class", "Red"); 
		Plan_4.getEdge("IL").setAttribute("ui.label", "IL"); 
		Plan_4.getEdge("IL").addAttribute("ui.class", "Red"); 
		Plan_4.getEdge("FI").setAttribute("ui.label", "FI"); 
		Plan_4.getEdge("FI").addAttribute("ui.class", "Red"); 
		Plan_4.getEdge("TY").setAttribute("ui.label", "TY"); 
		Plan_4.getEdge("TY").addAttribute("ui.class", "Red"); 
		Plan_4.getEdge("PT").setAttribute("ui.label", "PT"); 
		Plan_4.getEdge("PT").addAttribute("ui.class", "Red"); 
		Plan_4.getEdge("YD_").setAttribute("ui.label", "YD_"); 
		Plan_4.getEdge("YD_").addAttribute("ui.class", "Red"); 
		Plan_4.getEdge("DH").setAttribute("ui.label", "DH"); 
		Plan_4.getEdge("DH").addAttribute("ui.class", "Red"); 
		Plan_4.getEdge("HN").setAttribute("ui.label", "HN"); 
		Plan_4.getEdge("HN").addAttribute("ui.class", "Red"); 
		Plan_4.getEdge("NO").setAttribute("ui.label", "NO"); 
		Plan_4.getEdge("NO").addAttribute("ui.class", "Red"); 
		Plan_4.getEdge("OP").setAttribute("ui.label", "OP"); 
		Plan_4.getEdge("OP").addAttribute("ui.class", "Red"); 
		Plan_4.getEdge("PX").setAttribute("ui.label", "PX"); 
		Plan_4.getEdge("PX").addAttribute("ui.class", "Red"); 
		Plan_4.getEdge("XY").setAttribute("ui.label", "XY"); 
		Plan_4.getEdge("XY").addAttribute("ui.class", "Red"); 
	return Plan_4; 
} // fin de getPlan4 

// ------------------------------------------------------------------------------- 
 // ------------------------------------------------------------------------------- 

// ------------------------------------------------------------------------------- 
// ------------------------------------------------------------------------------- 

//------------------------------------------------------------------------------- 
public Graph getPlan26(String pName){ 
	Graph Plan_6 = new SingleGraph("Plan_6"); 
		Plan_6.setStrict(false); 
		Plan_6.setAutoCreate( true ); 
		Plan_6.addAttribute("ui.stylesheet", "url('file:config/Modele1.css')"); 
		Plan_6.addAttribute("ui.quality"); 
		Plan_6.addAttribute("ui.antialias"); 
		
		Plan_6.addEdge("startD", "start", "D", true );
		Plan_6.addEdge("FI", "F", "I", true ); 
		Plan_6.addEdge("DH", "D", "H", true ); 
		Plan_6.addEdge("HI", "H", "I", true ); 
		Plan_6.addEdge("IL", "I", "L", true ); 
		Plan_6.addEdge("LS", "L", "S", true ); 
		Plan_6.addEdge("SB_", "S", "B_", true ); 
		Plan_6.addEdge("B_C_", "B_", "C_", true ); 
		Plan_6.addEdge("ST", "S", "T", true ); 
		Plan_6.addEdge("TC_", "T", "C_", true ); 
		Plan_6.addEdge("LP", "L", "P", true ); 
		Plan_6.addEdge("NO", "N", "O", true ); 
		Plan_6.addEdge("OP", "O", "P", true ); 
		Plan_6.addEdge("PT", "P", "T", true ); 
		Plan_6.addEdge("DF", "D", "F", true ); 
		Plan_6.addEdge("IO", "I", "O", true ); 
		Plan_6.addEdge("HN", "H", "N", true ); 
		
		Plan_6.getNode("start").setAttribute("ui.label", "start");
		Plan_6.getNode("F").setAttribute("ui.label", "F"); 
		Plan_6.getNode("T").setAttribute("ui.label", "T"); 
		Plan_6.getNode("H").setAttribute("ui.label", "H"); 
		Plan_6.getNode("I").setAttribute("ui.label", "I"); 
		Plan_6.getNode("L").setAttribute("ui.label", "L"); 
		Plan_6.getNode("S").setAttribute("ui.label", "S"); 
		Plan_6.getNode("B_").setAttribute("ui.label", "B_"); 
		Plan_6.getNode("C_").setAttribute("ui.label", "C_"); 
		Plan_6.getNode("D").setAttribute("ui.label", "D"); 
		Plan_6.getNode("P").setAttribute("ui.label", "P"); 
		Plan_6.getNode("N").setAttribute("ui.label", "N"); 
		Plan_6.getNode("O").setAttribute("ui.label", "O"); 
		Plan_6.getEdge("FI").setAttribute("ui.label", "FI"); 
		Plan_6.getEdge("FI").addAttribute("ui.class", "Coral"); 
		Plan_6.getEdge("DH").setAttribute("ui.label", "DH"); 
		Plan_6.getEdge("DH").addAttribute("ui.class", "Coral"); 
		Plan_6.getEdge("HI").setAttribute("ui.label", "HI"); 
		Plan_6.getEdge("HI").addAttribute("ui.class", "Coral"); 
		Plan_6.getEdge("IL").setAttribute("ui.label", "IL"); 
		Plan_6.getEdge("IL").addAttribute("ui.class", "Coral"); 
		Plan_6.getEdge("LS").setAttribute("ui.label", "LS"); 
		Plan_6.getEdge("LS").addAttribute("ui.class", "Coral"); 
		Plan_6.getEdge("SB_").setAttribute("ui.label", "SB_"); 
		Plan_6.getEdge("SB_").addAttribute("ui.class", "Coral"); 
		Plan_6.getEdge("B_C_").setAttribute("ui.label", "B_C_"); 
		Plan_6.getEdge("B_C_").addAttribute("ui.class", "Coral"); 
		Plan_6.getEdge("ST").setAttribute("ui.label", "ST"); 
		Plan_6.getEdge("ST").addAttribute("ui.class", "Coral"); 
		Plan_6.getEdge("TC_").setAttribute("ui.label", "TC_"); 
		Plan_6.getEdge("TC_").addAttribute("ui.class", "Coral"); 
		Plan_6.getEdge("LP").setAttribute("ui.label", "LP"); 
		Plan_6.getEdge("LP").addAttribute("ui.class", "Coral"); 
		Plan_6.getEdge("NO").setAttribute("ui.label", "NO"); 
		Plan_6.getEdge("NO").addAttribute("ui.class", "Coral"); 
		Plan_6.getEdge("OP").setAttribute("ui.label", "OP"); 
		Plan_6.getEdge("OP").addAttribute("ui.class", "Coral"); 
		Plan_6.getEdge("PT").setAttribute("ui.label", "PT"); 
		Plan_6.getEdge("PT").addAttribute("ui.class", "Coral"); 
		Plan_6.getEdge("DF").setAttribute("ui.label", "DF"); 
		Plan_6.getEdge("DF").addAttribute("ui.class", "Coral"); 
		Plan_6.getEdge("IO").setAttribute("ui.label", "IO"); 
		Plan_6.getEdge("IO").addAttribute("ui.class", "Coral"); 
		Plan_6.getEdge("HN").setAttribute("ui.label", "HN"); 
		Plan_6.getEdge("HN").addAttribute("ui.class", "Coral"); 
	return Plan_6; 
} // fin de getPlan6 

// ------------------------------------------------------------------------------- 
// ------------------------------------------------------------------------------- 

//------------------------------------------------------------------------------- 
// ------------------------------------------------------------------------------- 

//------------------------------------------------------------------------------- 
public Graph getPlan27(String pName){ 
	Graph Plan_9 = new SingleGraph("Plan_9"); 
		Plan_9.setStrict(false); 
		Plan_9.setAutoCreate( true ); 
		Plan_9.addAttribute("ui.stylesheet", "url('file:config/Modele1.css')"); 
		Plan_9.addAttribute("ui.quality"); 
		Plan_9.addAttribute("ui.antialias"); 
		
		Plan_9.addEdge("startB", "start", "B", true );
		Plan_9.addEdge("BE", "B", "E", true ); 
		Plan_9.addEdge("EF", "E", "F", true ); 
		Plan_9.addEdge("FI", "F", "I", true ); 
		Plan_9.addEdge("IL", "I", "L", true ); 
		Plan_9.addEdge("LS", "L", "S", true ); 
		Plan_9.addEdge("SB_", "S", "B_", true ); 
		Plan_9.addEdge("B_C_", "B_", "C_", true ); 
		Plan_9.addEdge("C_D_", "C_", "D_", true ); 
		Plan_9.addEdge("BD", "B", "D", true ); 
		Plan_9.addEdge("DF", "D", "F", true ); 
		Plan_9.addEdge("LP", "L", "P", true ); 
		Plan_9.addEdge("PT", "P", "T", true ); 
		Plan_9.addEdge("TC_", "T", "C_", true ); 
		
		Plan_9.getNode("start").setAttribute("ui.label", "start");
		Plan_9.getNode("B").setAttribute("ui.label", "B"); 
		Plan_9.getNode("E").setAttribute("ui.label", "E"); 
		Plan_9.getNode("F").setAttribute("ui.label", "F"); 
		Plan_9.getNode("I").setAttribute("ui.label", "I"); 
		Plan_9.getNode("L").setAttribute("ui.label", "L"); 
		Plan_9.getNode("S").setAttribute("ui.label", "S"); 
		Plan_9.getNode("B_").setAttribute("ui.label", "B_"); 
		Plan_9.getNode("C_").setAttribute("ui.label", "C_"); 
		Plan_9.getNode("D_").setAttribute("ui.label", "D_"); 
		Plan_9.getNode("D").setAttribute("ui.label", "D"); 
		Plan_9.getNode("P").setAttribute("ui.label", "P"); 
		Plan_9.getNode("T").setAttribute("ui.label", "T"); 
		Plan_9.getEdge("BE").setAttribute("ui.label", "BE"); 
		Plan_9.getEdge("BE").addAttribute("ui.class", "Purple"); 
		Plan_9.getEdge("EF").setAttribute("ui.label", "EF"); 
		Plan_9.getEdge("EF").addAttribute("ui.class", "Purple"); 
		Plan_9.getEdge("FI").setAttribute("ui.label", "FI"); 
		Plan_9.getEdge("FI").addAttribute("ui.class", "Purple"); 
		Plan_9.getEdge("IL").setAttribute("ui.label", "IL"); 
		Plan_9.getEdge("IL").addAttribute("ui.class", "Purple"); 
		Plan_9.getEdge("LS").setAttribute("ui.label", "LS"); 
		Plan_9.getEdge("LS").addAttribute("ui.class", "Purple"); 
		Plan_9.getEdge("SB_").setAttribute("ui.label", "SB_"); 
		Plan_9.getEdge("SB_").addAttribute("ui.class", "Purple"); 
		Plan_9.getEdge("B_C_").setAttribute("ui.label", "B_C_"); 
		Plan_9.getEdge("B_C_").addAttribute("ui.class", "Purple"); 
		Plan_9.getEdge("C_D_").setAttribute("ui.label", "C_D_"); 
		Plan_9.getEdge("C_D_").addAttribute("ui.class", "Purple"); 
		Plan_9.getEdge("BD").setAttribute("ui.label", "BD"); 
		Plan_9.getEdge("BD").addAttribute("ui.class", "Purple"); 
		Plan_9.getEdge("DF").setAttribute("ui.label", "DF"); 
		Plan_9.getEdge("DF").addAttribute("ui.class", "Purple"); 
		Plan_9.getEdge("LP").setAttribute("ui.label", "LP"); 
		Plan_9.getEdge("LP").addAttribute("ui.class", "Purple"); 
		Plan_9.getEdge("PT").setAttribute("ui.label", "PT"); 
		Plan_9.getEdge("PT").addAttribute("ui.class", "Purple"); 
		Plan_9.getEdge("TC_").setAttribute("ui.label", "TC_"); 
		Plan_9.getEdge("TC_").addAttribute("ui.class", "Purple"); 
	return Plan_9; 
} // fin de getPlan9 

// ------------------------------------------------------------------------------- 
// ------------------------------------------------------------------------------- 

// ------------------------------------------------------------------------------- 
// ------------------------------------------------------------------------------- 

//------------------------------------------------------------------------------- 
public Graph getPlan28(String pName){ 
	Graph Plan_15 = new SingleGraph("Plan_15"); 
		Plan_15.setStrict(false); 
		Plan_15.setAutoCreate( true ); 
		Plan_15.addAttribute("ui.stylesheet", "url('file:config/Modele1.css')"); 
		Plan_15.addAttribute("ui.quality"); 
		Plan_15.addAttribute("ui.antialias"); 
		
		Plan_15.addEdge("startD", "start", "D", true );
		Plan_15.addEdge("LS", "L", "S", true ); 
		Plan_15.addEdge("DH", "D", "H", true ); 
		Plan_15.addEdge("HI", "H", "I", true ); 
		Plan_15.addEdge("IO", "I", "O", true ); 
		Plan_15.addEdge("OP", "O", "P", true ); 
		Plan_15.addEdge("PT", "P", "T", true ); 
		Plan_15.addEdge("ST", "S", "T", true ); 
		Plan_15.addEdge("IL", "I", "L", true ); 
		Plan_15.addEdge("HN", "H", "N", true ); 
		Plan_15.addEdge("NO", "N", "O", true ); 
		Plan_15.addEdge("TC_", "T", "C_", true ); 
		
		Plan_15.getNode("start").setAttribute("ui.label", "start");
		Plan_15.getNode("L").setAttribute("ui.label", "L"); 
		Plan_15.getNode("D").setAttribute("ui.label", "D"); 
		Plan_15.getNode("H").setAttribute("ui.label", "H"); 
		Plan_15.getNode("I").setAttribute("ui.label", "I"); 
		Plan_15.getNode("O").setAttribute("ui.label", "O"); 
		Plan_15.getNode("P").setAttribute("ui.label", "P"); 
		Plan_15.getNode("T").setAttribute("ui.label", "T"); 
		Plan_15.getNode("C_").setAttribute("ui.label", "C_"); 
		Plan_15.getNode("S").setAttribute("ui.label", "S"); 
		Plan_15.getNode("N").setAttribute("ui.label", "N"); 
		Plan_15.getEdge("LS").setAttribute("ui.label", "LS"); 
		Plan_15.getEdge("LS").addAttribute("ui.class", "Aqua"); 
		Plan_15.getEdge("DH").setAttribute("ui.label", "DH"); 
		Plan_15.getEdge("DH").addAttribute("ui.class", "Ocher"); 
		Plan_15.getEdge("HI").setAttribute("ui.label", "HI"); 
		Plan_15.getEdge("HI").addAttribute("ui.class", "Ocher"); 
		Plan_15.getEdge("IO").setAttribute("ui.label", "IO"); 
		Plan_15.getEdge("IO").addAttribute("ui.class", "Ocher"); 
		Plan_15.getEdge("OP").setAttribute("ui.label", "OP"); 
		Plan_15.getEdge("OP").addAttribute("ui.class", "Ocher"); 
		Plan_15.getEdge("PT").setAttribute("ui.label", "PT"); 
		Plan_15.getEdge("PT").addAttribute("ui.class", "Ocher"); 
		Plan_15.getEdge("ST").setAttribute("ui.label", "ST"); 
		Plan_15.getEdge("ST").addAttribute("ui.class", "Ocher"); 
		Plan_15.getEdge("IL").setAttribute("ui.label", "IL"); 
		Plan_15.getEdge("IL").addAttribute("ui.class", "Ocher"); 
		Plan_15.getEdge("HN").setAttribute("ui.label", "HN"); 
		Plan_15.getEdge("HN").addAttribute("ui.class", "Ocher"); 
		Plan_15.getEdge("NO").setAttribute("ui.label", "NO"); 
		Plan_15.getEdge("NO").addAttribute("ui.class", "Ocher"); 
		Plan_15.getEdge("TC_").setAttribute("ui.label", "TC_"); 
		Plan_15.getEdge("TC_").addAttribute("ui.class", "Ocher"); 
	return Plan_15; 
} // fin de getPlan15 

// ------------------------------------------------------------------------------- 
// ------------------------------------------------------------------------------- 


//------------------------------------------------------------------------------- 
public Graph getPlan29(String pName){ 
	Graph Plan_5 = new SingleGraph("Plan_29"); 
		Plan_5.setStrict(false); 
		Plan_5.setAutoCreate( true ); 
		Plan_5.addAttribute("ui.stylesheet", "url('file:config/Modele1.css')"); 
		Plan_5.addAttribute("ui.quality"); 
		Plan_5.addAttribute("ui.antialias"); 
		Plan_5.addEdge("startI", "start", "I", true ); 
		Plan_5.addEdge("H_I_", "H_", "I_", true ); 
		Plan_5.addEdge("ST", "S", "T", true ); 
		Plan_5.addEdge("TC_", "T", "C_", true ); 
		Plan_5.addEdge("C_D_", "C_", "D_", true ); 
		Plan_5.addEdge("D_O_", "D_", "O_", true ); 
		Plan_5.addEdge("O_P_", "O_", "P_", true ); 
		Plan_5.addEdge("D_J_", "D_", "J_", true ); 
		Plan_5.addEdge("IL", "I", "L", true ); 
		Plan_5.addEdge("LS", "L", "S", true ); 
		Plan_5.addEdge("J_V_", "J_", "V_", true ); 
		Plan_5.addEdge("V_W_", "V_", "W_", true ); 
		Plan_5.addEdge("XH_", "X", "H_", true ); 
		Plan_5.addEdge("PX", "P", "X", true ); 
		Plan_5.addEdge("LP", "L", "P", true ); 
		Plan_5.addEdge("XY", "X", "Y", true ); 
		Plan_5.addEdge("YI_", "Y", "I_", true ); 
		Plan_5.addEdge("I_J_", "I_", "J_", true ); 
		Plan_5.addEdge("SB_", "S", "B_", true ); 
		Plan_5.addEdge("B_C_", "B_", "C_", true ); 
		Plan_5.addEdge("J_P_", "J_", "P_", true ); 
		Plan_5.addEdge("P_W_", "P_", "W_", true ); 
		//Plan_5.addEdge("startH_", "start", "H_", true ); 
		Plan_5.getNode("start").setAttribute("ui.label", "start"); 
		Plan_5.getNode("H_").setAttribute("ui.label", "H_"); 
		Plan_5.getNode("S").setAttribute("ui.label", "S"); 
		Plan_5.getNode("T").setAttribute("ui.label", "T"); 
		Plan_5.getNode("C_").setAttribute("ui.label", "C_"); 
		Plan_5.getNode("D_").setAttribute("ui.label", "D_"); 
		Plan_5.getNode("O_").setAttribute("ui.label", "O_"); 
		Plan_5.getNode("P_").setAttribute("ui.label", "P_"); 
		Plan_5.getNode("J_").setAttribute("ui.label", "J_"); 
		Plan_5.getNode("I").setAttribute("ui.label", "I"); 
		Plan_5.getNode("L").setAttribute("ui.label", "L"); 
		Plan_5.getNode("V_").setAttribute("ui.label", "V_"); 
		Plan_5.getNode("W_").setAttribute("ui.label", "W_"); 
		Plan_5.getNode("P").setAttribute("ui.label", "P"); 
		Plan_5.getNode("B_").setAttribute("ui.label", "B_"); 
		Plan_5.getNode("I_").setAttribute("ui.label", "I_"); 
		Plan_5.getNode("X").setAttribute("ui.label", "X"); 
		Plan_5.getNode("Y").setAttribute("ui.label", "Y"); 
		Plan_5.getEdge("H_I_").setAttribute("ui.label", "H_I_"); 
		Plan_5.getEdge("H_I_").addAttribute("ui.class", "Teal"); 
		Plan_5.getEdge("ST").setAttribute("ui.label", "ST"); 
		Plan_5.getEdge("ST").addAttribute("ui.class", "Teal"); 
		Plan_5.getEdge("TC_").setAttribute("ui.label", "TC_"); 
		Plan_5.getEdge("TC_").addAttribute("ui.class", "Teal"); 
		Plan_5.getEdge("C_D_").setAttribute("ui.label", "C_D_"); 
		Plan_5.getEdge("C_D_").addAttribute("ui.class", "Teal"); 
		Plan_5.getEdge("D_O_").setAttribute("ui.label", "D_O_"); 
		Plan_5.getEdge("D_O_").addAttribute("ui.class", "Teal"); 
		Plan_5.getEdge("O_P_").setAttribute("ui.label", "O_P_"); 
		Plan_5.getEdge("O_P_").addAttribute("ui.class", "Teal"); 
		Plan_5.getEdge("D_J_").setAttribute("ui.label", "D_J_"); 
		Plan_5.getEdge("D_J_").addAttribute("ui.class", "Teal"); 
		Plan_5.getEdge("IL").setAttribute("ui.label", "IL"); 
		Plan_5.getEdge("IL").addAttribute("ui.class", "Teal"); 
		Plan_5.getEdge("LS").setAttribute("ui.label", "LS"); 
		Plan_5.getEdge("LS").addAttribute("ui.class", "Teal"); 
		Plan_5.getEdge("J_V_").setAttribute("ui.label", "J_V_"); 
		Plan_5.getEdge("J_V_").addAttribute("ui.class", "Teal"); 
		Plan_5.getEdge("V_W_").setAttribute("ui.label", "V_W_"); 
		Plan_5.getEdge("V_W_").addAttribute("ui.class", "Teal"); 
		Plan_5.getEdge("XH_").setAttribute("ui.label", "XH_"); 
		Plan_5.getEdge("XH_").addAttribute("ui.class", "Teal"); 
		Plan_5.getEdge("PX").setAttribute("ui.label", "PX"); 
		Plan_5.getEdge("PX").addAttribute("ui.class", "Teal"); 
		Plan_5.getEdge("LP").setAttribute("ui.label", "LP"); 
		Plan_5.getEdge("LP").addAttribute("ui.class", "Teal"); 
		Plan_5.getEdge("XY").setAttribute("ui.label", "XY"); 
		Plan_5.getEdge("XY").addAttribute("ui.class", "Teal"); 
		Plan_5.getEdge("YI_").setAttribute("ui.label", "YI_"); 
		Plan_5.getEdge("YI_").addAttribute("ui.class", "Teal"); 
		Plan_5.getEdge("I_J_").setAttribute("ui.label", "I_J_"); 
		Plan_5.getEdge("I_J_").addAttribute("ui.class", "Teal"); 
		Plan_5.getEdge("SB_").setAttribute("ui.label", "SB_"); 
		Plan_5.getEdge("SB_").addAttribute("ui.class", "Teal"); 
		Plan_5.getEdge("B_C_").setAttribute("ui.label", "B_C_"); 
		Plan_5.getEdge("B_C_").addAttribute("ui.class", "Teal"); 
		Plan_5.getEdge("J_P_").setAttribute("ui.label", "J_P_"); 
		Plan_5.getEdge("J_P_").addAttribute("ui.class", "Teal"); 
		Plan_5.getEdge("P_W_").setAttribute("ui.label", "P_W_"); 
		Plan_5.getEdge("P_W_").addAttribute("ui.class", "Teal"); 
	return Plan_5; 
} // fin de getPlan5 

// ------------------------------------------------------------------------------- 
 // ------------------------------------------------------------------------------- 


//------------------------------------------------------------------------------- 
public Graph getPlan30(String pName){ 
	Graph Plan = new SingleGraph("Plan_33"); 
	Plan.setStrict(false); 
	Plan.setAutoCreate( true ); 
	Plan.addAttribute("ui.stylesheet", "url('file:config/Modele1.css')"); 
	Plan.addAttribute("ui.quality"); 
	Plan.addAttribute("ui.antialias"); 
	Plan.addEdge("startF", "start", "F", true ); 
	Plan.addEdge("XY", "X", "Y", true ); 
	Plan.addEdge("FK", "F", "K", true ); 
	Plan.addEdge("KR", "K", "R", true ); 
	Plan.addEdge("RS", "R", "S", true ); 
	Plan.addEdge("SB_", "S", "B_", true ); 
	Plan.addEdge("B_C_", "B_", "C_", true ); 
	Plan.addEdge("C_D_", "C_", "D_", true ); 
	Plan.addEdge("ST", "S", "T", true ); 
	Plan.addEdge("LP", "L", "P", true ); 
	Plan.addEdge("FI", "F", "I", true ); 
	Plan.addEdge("IO", "I", "O", true ); 
	Plan.addEdge("OP", "O", "P", true ); 
	Plan.addEdge("PT", "P", "T", true ); 
	Plan.addEdge("TY", "T", "Y", true ); 
	Plan.addEdge("YD_", "Y", "D_", true ); 
	Plan.addEdge("PX", "P", "X", true ); 
	Plan.addEdge("TC_", "T", "C_", true ); 
	Plan.addEdge("KL", "K", "L", true ); 
	Plan.addEdge("LS", "L", "S", true ); 
	//Plan.addEdge("start", "start", "L", true ); 
	Plan.getNode("start").setAttribute("ui.label", "start"); 
	Plan.getNode("L").setAttribute("ui.label", "L"); 
	Plan.getNode("F").setAttribute("ui.label", "F"); 
	Plan.getNode("K").setAttribute("ui.label", "K"); 
	Plan.getNode("R").setAttribute("ui.label", "R"); 
	Plan.getNode("S").setAttribute("ui.label", "S"); 
	Plan.getNode("B_").setAttribute("ui.label", "B_"); 
	Plan.getNode("C_").setAttribute("ui.label", "C_"); 
	Plan.getNode("D_").setAttribute("ui.label", "D_"); 
	Plan.getNode("Y").setAttribute("ui.label", "Y"); 
	Plan.getNode("X").setAttribute("ui.label", "X"); 
	Plan.getNode("I").setAttribute("ui.label", "I"); 
	Plan.getNode("O").setAttribute("ui.label", "O"); 
	Plan.getNode("P").setAttribute("ui.label", "P"); 
	Plan.getNode("T").setAttribute("ui.label", "T"); 
	Plan.getEdge("XY").setAttribute("ui.label", "XY"); 
	Plan.getEdge("XY").addAttribute("ui.class", "Ocher"); 
	Plan.getEdge("FK").setAttribute("ui.label", "FK"); 
	Plan.getEdge("FK").addAttribute("ui.class", "Ocher"); 
	Plan.getEdge("KR").setAttribute("ui.label", "KR"); 
	Plan.getEdge("KR").addAttribute("ui.class", "Ocher"); 
	Plan.getEdge("RS").setAttribute("ui.label", "RS"); 
	Plan.getEdge("RS").addAttribute("ui.class", "Ocher"); 
	Plan.getEdge("SB_").setAttribute("ui.label", "SB_"); 
	Plan.getEdge("SB_").addAttribute("ui.class", "Ocher"); 
	Plan.getEdge("B_C_").setAttribute("ui.label", "B_C_"); 
	Plan.getEdge("B_C_").addAttribute("ui.class", "Ocher"); 
	Plan.getEdge("C_D_").setAttribute("ui.label", "C_D_"); 
	Plan.getEdge("C_D_").addAttribute("ui.class", "Ocher"); 
	Plan.getEdge("ST").setAttribute("ui.label", "ST"); 
	Plan.getEdge("ST").addAttribute("ui.class", "Ocher"); 
	Plan.getEdge("LP").setAttribute("ui.label", "LP"); 
	Plan.getEdge("LP").addAttribute("ui.class", "Ocher"); 
	Plan.getEdge("FI").setAttribute("ui.label", "FI"); 
	Plan.getEdge("FI").addAttribute("ui.class", "Ocher"); 
	Plan.getEdge("IO").setAttribute("ui.label", "IO"); 
	Plan.getEdge("IO").addAttribute("ui.class", "Ocher"); 
	Plan.getEdge("OP").setAttribute("ui.label", "OP"); 
	Plan.getEdge("OP").addAttribute("ui.class", "Ocher"); 
	Plan.getEdge("PT").setAttribute("ui.label", "PT"); 
	Plan.getEdge("PT").addAttribute("ui.class", "Ocher"); 
	Plan.getEdge("TY").setAttribute("ui.label", "TY"); 
	Plan.getEdge("TY").addAttribute("ui.class", "Ocher"); 
	Plan.getEdge("YD_").setAttribute("ui.label", "YD_"); 
	Plan.getEdge("YD_").addAttribute("ui.class", "Ocher"); 
	Plan.getEdge("PX").setAttribute("ui.label", "PX"); 
	Plan.getEdge("PX").addAttribute("ui.class", "Ocher"); 
	Plan.getEdge("TC_").setAttribute("ui.label", "TC_"); 
	Plan.getEdge("TC_").addAttribute("ui.class", "Ocher"); 
	Plan.getEdge("KL").setAttribute("ui.label", "KL"); 
	Plan.getEdge("KL").addAttribute("ui.class", "Ocher"); 
	Plan.getEdge("LS").setAttribute("ui.label", "LS"); 
	Plan.getEdge("LS").addAttribute("ui.class", "Ocher");  
	return Plan; 
} // fin de getPlan6 

// ------------------------------------------------------------------------------- 
 // ------------------------------------------------------------------------------- 

//------------------------------------------------------------------------------- 
public Graph getPlan31(String pName){ 
	Graph Plan_7 = new SingleGraph("Plan_31"); 
		Plan_7.setStrict(false); 
		Plan_7.setAutoCreate( true ); 
		Plan_7.addAttribute("ui.stylesheet", "url('file:config/Modele1.css')"); 
		Plan_7.addAttribute("ui.quality"); 
		Plan_7.addAttribute("ui.antialias"); 
		Plan_7.addEdge("startC", "start", "C", true ); 
		Plan_7.addEdge("IO", "I", "O", true ); 
		Plan_7.addEdge("OW", "O", "W", true ); 
		Plan_7.addEdge("WG_", "W", "G_", true ); 
		Plan_7.addEdge("G_H_", "G_", "H_", true ); 
		Plan_7.addEdge("H_I_", "H_", "I_", true ); 
		Plan_7.addEdge("OP", "O", "P", true ); 
		Plan_7.addEdge("CD", "C", "D", true ); 
		Plan_7.addEdge("FI", "F", "I", true ); 
		Plan_7.addEdge("CG", "C", "G", true ); 
		Plan_7.addEdge("GH", "G", "H", true ); 
		Plan_7.addEdge("HI", "H", "I", true ); 
		Plan_7.addEdge("YI_", "Y", "I_", true ); 
		Plan_7.addEdge("XY", "X", "Y", true ); 
		Plan_7.addEdge("DF", "D", "F", true ); 
		Plan_7.addEdge("WX", "W", "X", true ); 
		Plan_7.addEdge("IL", "I", "L", true ); 
		Plan_7.addEdge("LP", "L", "P", true ); 
		Plan_7.addEdge("PX", "P", "X", true ); 
		Plan_7.addEdge("XH_", "X", "H_", true ); 
		//Plan_7.addEdge("start", "start", "I", true ); 
		Plan_7.getNode("start").setAttribute("ui.label", "start"); 
		Plan_7.getNode("I").setAttribute("ui.label", "I"); 
		Plan_7.getNode("O").setAttribute("ui.label", "O"); 
		Plan_7.getNode("W").setAttribute("ui.label", "W"); 
		Plan_7.getNode("G_").setAttribute("ui.label", "G_"); 
		Plan_7.getNode("H_").setAttribute("ui.label", "H_"); 
		Plan_7.getNode("I_").setAttribute("ui.label", "I_"); 
		Plan_7.getNode("L").setAttribute("ui.label", "L"); 
		Plan_7.getNode("D").setAttribute("ui.label", "D"); 
		Plan_7.getNode("F").setAttribute("ui.label", "F"); 
		Plan_7.getNode("C").setAttribute("ui.label", "C"); 
		Plan_7.getNode("G").setAttribute("ui.label", "G"); 
		Plan_7.getNode("H").setAttribute("ui.label", "H"); 
		Plan_7.getNode("Y").setAttribute("ui.label", "Y"); 
		Plan_7.getNode("X").setAttribute("ui.label", "X"); 
		Plan_7.getNode("P").setAttribute("ui.label", "P"); 
		Plan_7.getEdge("IO").setAttribute("ui.label", "IO"); 
		Plan_7.getEdge("IO").addAttribute("ui.class", "Ruby"); 
		Plan_7.getEdge("OW").setAttribute("ui.label", "OW"); 
		Plan_7.getEdge("OW").addAttribute("ui.class", "Ruby"); 
		Plan_7.getEdge("WG_").setAttribute("ui.label", "WG_"); 
		Plan_7.getEdge("WG_").addAttribute("ui.class", "Ruby"); 
		Plan_7.getEdge("G_H_").setAttribute("ui.label", "G_H_"); 
		Plan_7.getEdge("G_H_").addAttribute("ui.class", "Ruby"); 
		Plan_7.getEdge("H_I_").setAttribute("ui.label", "H_I_"); 
		Plan_7.getEdge("H_I_").addAttribute("ui.class", "Ruby"); 
		Plan_7.getEdge("OP").setAttribute("ui.label", "OP"); 
		Plan_7.getEdge("OP").addAttribute("ui.class", "Ruby"); 
		Plan_7.getEdge("CD").setAttribute("ui.label", "CD"); 
		Plan_7.getEdge("CD").addAttribute("ui.class", "Ruby"); 
		Plan_7.getEdge("FI").setAttribute("ui.label", "FI"); 
		Plan_7.getEdge("FI").addAttribute("ui.class", "Ruby"); 
		Plan_7.getEdge("CG").setAttribute("ui.label", "CG"); 
		Plan_7.getEdge("CG").addAttribute("ui.class", "Ruby"); 
		Plan_7.getEdge("GH").setAttribute("ui.label", "GH"); 
		Plan_7.getEdge("GH").addAttribute("ui.class", "Ruby"); 
		Plan_7.getEdge("HI").setAttribute("ui.label", "HI"); 
		Plan_7.getEdge("HI").addAttribute("ui.class", "Ruby"); 
		Plan_7.getEdge("YI_").setAttribute("ui.label", "YI_"); 
		Plan_7.getEdge("YI_").addAttribute("ui.class", "Ruby"); 
		Plan_7.getEdge("XY").setAttribute("ui.label", "XY"); 
		Plan_7.getEdge("XY").addAttribute("ui.class", "Ruby"); 
		Plan_7.getEdge("DF").setAttribute("ui.label", "DF"); 
		Plan_7.getEdge("DF").addAttribute("ui.class", "Ruby"); 
		Plan_7.getEdge("WX").setAttribute("ui.label", "WX"); 
		Plan_7.getEdge("WX").addAttribute("ui.class", "Ruby"); 
		Plan_7.getEdge("IL").setAttribute("ui.label", "IL"); 
		Plan_7.getEdge("IL").addAttribute("ui.class", "Ruby"); 
		Plan_7.getEdge("LP").setAttribute("ui.label", "LP"); 
		Plan_7.getEdge("LP").addAttribute("ui.class", "Ruby"); 
		Plan_7.getEdge("PX").setAttribute("ui.label", "PX"); 
		Plan_7.getEdge("PX").addAttribute("ui.class", "Ruby"); 
		Plan_7.getEdge("XH_").setAttribute("ui.label", "XH_"); 
		Plan_7.getEdge("XH_").addAttribute("ui.class", "Ruby"); 
	return Plan_7; 
} // fin de getPlan7 

// ------------------------------------------------------------------------------- 
 // ------------------------------------------------------------------------------- 

//------------------------------------------------------------------------------- 
public Graph getPlan32(String pName){ 
	Graph Plan_14 = new SingleGraph("Plan_32"); 
		Plan_14.setStrict(false); 
		Plan_14.setAutoCreate( true ); 
		Plan_14.addAttribute("ui.stylesheet", "url('file:config/Modele1.css')"); 
		Plan_14.addAttribute("ui.quality"); 
		Plan_14.addAttribute("ui.antialias"); 
		Plan_14.addEdge("startE", "start", "E", true ); 
		Plan_14.addEdge("EF", "E", "F", true ); 
		Plan_14.addEdge("FK", "F", "K", true ); 
		Plan_14.addEdge("KL", "K", "L", true ); 
		Plan_14.addEdge("LP", "L", "P", true ); 
		Plan_14.addEdge("PT", "P", "T", true ); 
		Plan_14.addEdge("TC_", "T", "C_", true ); 
		Plan_14.addEdge("C_D_", "C_", "D_", true ); 
		Plan_14.addEdge("D_J_", "D_", "J_", true ); 
		Plan_14.addEdge("J_P_", "J_", "P_", true ); 
		Plan_14.addEdge("EJ", "E", "J", true ); 
		Plan_14.addEdge("JK", "J", "K", true ); 
		Plan_14.addEdge("LS", "L", "S", true ); 
		Plan_14.addEdge("SB_", "S", "B_", true ); 
		Plan_14.addEdge("B_C_", "B_", "C_", true ); 
		Plan_14.addEdge("FI", "F", "I", true ); 
		Plan_14.addEdge("IL", "I", "L", true ); 
		Plan_14.addEdge("ST", "S", "T", true ); 
		Plan_14.addEdge("TY", "T", "Y", true ); 
		Plan_14.addEdge("YD_", "Y", "D_", true ); 
		//Plan_14.addEdge("start", "start", "E", true ); 
		Plan_14.getNode("start").setAttribute("ui.label", "start"); 
		Plan_14.getNode("E").setAttribute("ui.label", "E"); 
		Plan_14.getNode("F").setAttribute("ui.label", "F"); 
		Plan_14.getNode("K").setAttribute("ui.label", "K"); 
		Plan_14.getNode("L").setAttribute("ui.label", "L"); 
		Plan_14.getNode("P").setAttribute("ui.label", "P"); 
		Plan_14.getNode("T").setAttribute("ui.label", "T"); 
		Plan_14.getNode("C_").setAttribute("ui.label", "C_"); 
		Plan_14.getNode("D_").setAttribute("ui.label", "D_"); 
		Plan_14.getNode("J_").setAttribute("ui.label", "J_"); 
		Plan_14.getNode("P_").setAttribute("ui.label", "P_"); 
		Plan_14.getNode("J").setAttribute("ui.label", "J"); 
		Plan_14.getNode("S").setAttribute("ui.label", "S"); 
		Plan_14.getNode("B_").setAttribute("ui.label", "B_"); 
		Plan_14.getNode("I").setAttribute("ui.label", "I"); 
		Plan_14.getNode("Y").setAttribute("ui.label", "Y"); 
		Plan_14.getEdge("EF").setAttribute("ui.label", "EF"); 
		Plan_14.getEdge("EF").addAttribute("ui.class", "Olive"); 
		Plan_14.getEdge("FK").setAttribute("ui.label", "FK"); 
		Plan_14.getEdge("FK").addAttribute("ui.class", "Olive"); 
		Plan_14.getEdge("KL").setAttribute("ui.label", "KL"); 
		Plan_14.getEdge("KL").addAttribute("ui.class", "Olive"); 
		Plan_14.getEdge("LP").setAttribute("ui.label", "LP"); 
		Plan_14.getEdge("LP").addAttribute("ui.class", "Olive"); 
		Plan_14.getEdge("PT").setAttribute("ui.label", "PT"); 
		Plan_14.getEdge("PT").addAttribute("ui.class", "Olive"); 
		Plan_14.getEdge("TC_").setAttribute("ui.label", "TC_"); 
		Plan_14.getEdge("TC_").addAttribute("ui.class", "Olive"); 
		Plan_14.getEdge("C_D_").setAttribute("ui.label", "C_D_"); 
		Plan_14.getEdge("C_D_").addAttribute("ui.class", "Olive"); 
		Plan_14.getEdge("D_J_").setAttribute("ui.label", "D_J_"); 
		Plan_14.getEdge("D_J_").addAttribute("ui.class", "Olive"); 
		Plan_14.getEdge("J_P_").setAttribute("ui.label", "J_P_"); 
		Plan_14.getEdge("J_P_").addAttribute("ui.class", "Olive"); 
		Plan_14.getEdge("EJ").setAttribute("ui.label", "EJ"); 
		Plan_14.getEdge("EJ").addAttribute("ui.class", "Olive"); 
		Plan_14.getEdge("JK").setAttribute("ui.label", "JK"); 
		Plan_14.getEdge("JK").addAttribute("ui.class", "Olive"); 
		Plan_14.getEdge("LS").setAttribute("ui.label", "LS"); 
		Plan_14.getEdge("LS").addAttribute("ui.class", "Olive"); 
		Plan_14.getEdge("SB_").setAttribute("ui.label", "SB_"); 
		Plan_14.getEdge("SB_").addAttribute("ui.class", "Olive"); 
		Plan_14.getEdge("B_C_").setAttribute("ui.label", "B_C_"); 
		Plan_14.getEdge("B_C_").addAttribute("ui.class", "Olive"); 
		Plan_14.getEdge("FI").setAttribute("ui.label", "FI"); 
		Plan_14.getEdge("FI").addAttribute("ui.class", "Olive"); 
		Plan_14.getEdge("IL").setAttribute("ui.label", "IL"); 
		Plan_14.getEdge("IL").addAttribute("ui.class", "Olive"); 
		Plan_14.getEdge("ST").setAttribute("ui.label", "ST"); 
		Plan_14.getEdge("ST").addAttribute("ui.class", "Olive"); 
		Plan_14.getEdge("TY").setAttribute("ui.label", "TY"); 
		Plan_14.getEdge("TY").addAttribute("ui.class", "Olive"); 
		Plan_14.getEdge("YD_").setAttribute("ui.label", "YD_"); 
		Plan_14.getEdge("YD_").addAttribute("ui.class", "Olive"); 
	return Plan_14; 
} // fin de getPlan14 

// ------------------------------------------------------------------------------- 
// ------------------------------------------------------------------------------- 

//------------------------------------------------------------------------------- 
public Graph getPlan33(String pName){ 
	Graph Plan_15 = new SingleGraph("Plan_33"); 
		Plan_15.setStrict(false); 
		Plan_15.setAutoCreate( true ); 
		Plan_15.addAttribute("ui.stylesheet", "url('file:config/Modele1.css')"); 
		Plan_15.addAttribute("ui.quality"); 
		Plan_15.addAttribute("ui.antialias"); 
		Plan_15.addEdge("startF", "start", "F", true ); 
		Plan_15.addEdge("XY", "X", "Y", true ); 
		Plan_15.addEdge("FK", "F", "K", true ); 
		Plan_15.addEdge("KR", "K", "R", true ); 
		Plan_15.addEdge("RS", "R", "S", true ); 
		Plan_15.addEdge("SB_", "S", "B_", true ); 
		Plan_15.addEdge("B_C_", "B_", "C_", true ); 
		Plan_15.addEdge("C_D_", "C_", "D_", true ); 
		Plan_15.addEdge("ST", "S", "T", true ); 
		Plan_15.addEdge("LP", "L", "P", true ); 
		Plan_15.addEdge("FI", "F", "I", true ); 
		Plan_15.addEdge("IO", "I", "O", true ); 
		Plan_15.addEdge("OP", "O", "P", true ); 
		Plan_15.addEdge("PT", "P", "T", true ); 
		Plan_15.addEdge("TY", "T", "Y", true ); 
		Plan_15.addEdge("YD_", "Y", "D_", true ); 
		Plan_15.addEdge("PX", "P", "X", true ); 
		Plan_15.addEdge("TC_", "T", "C_", true ); 
		Plan_15.addEdge("KL", "K", "L", true ); 
		Plan_15.addEdge("LS", "L", "S", true ); 
		//Plan_15.addEdge("start", "start", "L", true ); 
		Plan_15.getNode("start").setAttribute("ui.label", "start"); 
		Plan_15.getNode("L").setAttribute("ui.label", "L"); 
		Plan_15.getNode("F").setAttribute("ui.label", "F"); 
		Plan_15.getNode("K").setAttribute("ui.label", "K"); 
		Plan_15.getNode("R").setAttribute("ui.label", "R"); 
		Plan_15.getNode("S").setAttribute("ui.label", "S"); 
		Plan_15.getNode("B_").setAttribute("ui.label", "B_"); 
		Plan_15.getNode("C_").setAttribute("ui.label", "C_"); 
		Plan_15.getNode("D_").setAttribute("ui.label", "D_"); 
		Plan_15.getNode("Y").setAttribute("ui.label", "Y"); 
		Plan_15.getNode("X").setAttribute("ui.label", "X"); 
		Plan_15.getNode("I").setAttribute("ui.label", "I"); 
		Plan_15.getNode("O").setAttribute("ui.label", "O"); 
		Plan_15.getNode("P").setAttribute("ui.label", "P"); 
		Plan_15.getNode("T").setAttribute("ui.label", "T"); 
		Plan_15.getEdge("XY").setAttribute("ui.label", "XY"); 
		Plan_15.getEdge("XY").addAttribute("ui.class", "Ocher"); 
		Plan_15.getEdge("FK").setAttribute("ui.label", "FK"); 
		Plan_15.getEdge("FK").addAttribute("ui.class", "Ocher"); 
		Plan_15.getEdge("KR").setAttribute("ui.label", "KR"); 
		Plan_15.getEdge("KR").addAttribute("ui.class", "Ocher"); 
		Plan_15.getEdge("RS").setAttribute("ui.label", "RS"); 
		Plan_15.getEdge("RS").addAttribute("ui.class", "Ocher"); 
		Plan_15.getEdge("SB_").setAttribute("ui.label", "SB_"); 
		Plan_15.getEdge("SB_").addAttribute("ui.class", "Ocher"); 
		Plan_15.getEdge("B_C_").setAttribute("ui.label", "B_C_"); 
		Plan_15.getEdge("B_C_").addAttribute("ui.class", "Ocher"); 
		Plan_15.getEdge("C_D_").setAttribute("ui.label", "C_D_"); 
		Plan_15.getEdge("C_D_").addAttribute("ui.class", "Ocher"); 
		Plan_15.getEdge("ST").setAttribute("ui.label", "ST"); 
		Plan_15.getEdge("ST").addAttribute("ui.class", "Ocher"); 
		Plan_15.getEdge("LP").setAttribute("ui.label", "LP"); 
		Plan_15.getEdge("LP").addAttribute("ui.class", "Ocher"); 
		Plan_15.getEdge("FI").setAttribute("ui.label", "FI"); 
		Plan_15.getEdge("FI").addAttribute("ui.class", "Ocher"); 
		Plan_15.getEdge("IO").setAttribute("ui.label", "IO"); 
		Plan_15.getEdge("IO").addAttribute("ui.class", "Ocher"); 
		Plan_15.getEdge("OP").setAttribute("ui.label", "OP"); 
		Plan_15.getEdge("OP").addAttribute("ui.class", "Ocher"); 
		Plan_15.getEdge("PT").setAttribute("ui.label", "PT"); 
		Plan_15.getEdge("PT").addAttribute("ui.class", "Ocher"); 
		Plan_15.getEdge("TY").setAttribute("ui.label", "TY"); 
		Plan_15.getEdge("TY").addAttribute("ui.class", "Ocher"); 
		Plan_15.getEdge("YD_").setAttribute("ui.label", "YD_"); 
		Plan_15.getEdge("YD_").addAttribute("ui.class", "Ocher"); 
		Plan_15.getEdge("PX").setAttribute("ui.label", "PX"); 
		Plan_15.getEdge("PX").addAttribute("ui.class", "Ocher"); 
		Plan_15.getEdge("TC_").setAttribute("ui.label", "TC_"); 
		Plan_15.getEdge("TC_").addAttribute("ui.class", "Ocher"); 
		Plan_15.getEdge("KL").setAttribute("ui.label", "KL"); 
		Plan_15.getEdge("KL").addAttribute("ui.class", "Ocher"); 
		Plan_15.getEdge("LS").setAttribute("ui.label", "LS"); 
		Plan_15.getEdge("LS").addAttribute("ui.class", "Ocher"); 
	return Plan_15; 
} // fin de getPlan15 

// ------------------------------------------------------------------------------- 

//------------------------------------------------------------------------------- 
public Graph getPlan34(String pName){ 
	Graph Plan_18 = new SingleGraph("Plan_34"); 
		Plan_18.setStrict(false); 
		Plan_18.setAutoCreate( true ); 
		Plan_18.addAttribute("ui.stylesheet", "url('file:config/Modele1.css')"); 
		Plan_18.addAttribute("ui.quality"); 
		Plan_18.addAttribute("ui.antialias"); 
		Plan_18.addEdge("startQ", "start", "Q", true ); 
		Plan_18.addEdge("C_N_", "C_", "N_", true ); 
		Plan_18.addEdge("RS", "R", "S", true ); 
		Plan_18.addEdge("ST", "S", "T", true ); 
		Plan_18.addEdge("TC_", "T", "C_", true ); 
		Plan_18.addEdge("C_D_", "C_", "D_", true ); 
		Plan_18.addEdge("D_J_", "D_", "J_", true ); 
		Plan_18.addEdge("J_P_", "J_", "P_", true ); 
		Plan_18.addEdge("L_M_", "L_", "M_", true ); 
		Plan_18.addEdge("QR", "Q", "R", true ); 
		Plan_18.addEdge("QZ", "Q", "Z", true ); 
		Plan_18.addEdge("K_L_", "K_", "L_", true ); 
		Plan_18.addEdge("ZK_", "Z", "K_", true ); 
		Plan_18.addEdge("O_P_", "O_", "P_", true ); 
		Plan_18.addEdge("N_O_", "N_", "O_", true ); 
		Plan_18.addEdge("M_N_", "M_", "N_", true ); 
		Plan_18.addEdge("SB_", "S", "B_", true ); 
		Plan_18.addEdge("B_M_", "B_", "M_", true ); 
	//	Plan_18.addEdge("start", "start", "K_", true ); 
		Plan_18.getNode("start").setAttribute("ui.label", "start"); 
		Plan_18.getNode("K_").setAttribute("ui.label", "K_"); 
		Plan_18.getNode("R").setAttribute("ui.label", "R"); 
		Plan_18.getNode("S").setAttribute("ui.label", "S"); 
		Plan_18.getNode("T").setAttribute("ui.label", "T"); 
		Plan_18.getNode("C_").setAttribute("ui.label", "C_"); 
		Plan_18.getNode("D_").setAttribute("ui.label", "D_"); 
		Plan_18.getNode("J_").setAttribute("ui.label", "J_"); 
		Plan_18.getNode("P_").setAttribute("ui.label", "P_"); 
		Plan_18.getNode("Z").setAttribute("ui.label", "Z"); 
		Plan_18.getNode("N_").setAttribute("ui.label", "N_"); 
		Plan_18.getNode("L_").setAttribute("ui.label", "L_"); 
		Plan_18.getNode("Q").setAttribute("ui.label", "Q"); 
		Plan_18.getNode("O_").setAttribute("ui.label", "O_"); 
		Plan_18.getNode("M_").setAttribute("ui.label", "M_"); 
		Plan_18.getNode("B_").setAttribute("ui.label", "B_"); 
		Plan_18.getEdge("C_N_").setAttribute("ui.label", "C_N_"); 
		Plan_18.getEdge("C_N_").addAttribute("ui.class", "Lavender"); 
		Plan_18.getEdge("RS").setAttribute("ui.label", "RS"); 
		Plan_18.getEdge("RS").addAttribute("ui.class", "Lavender"); 
		Plan_18.getEdge("ST").setAttribute("ui.label", "ST"); 
		Plan_18.getEdge("ST").addAttribute("ui.class", "Lavender"); 
		Plan_18.getEdge("TC_").setAttribute("ui.label", "TC_"); 
		Plan_18.getEdge("TC_").addAttribute("ui.class", "Lavender"); 
		Plan_18.getEdge("C_D_").setAttribute("ui.label", "C_D_"); 
		Plan_18.getEdge("C_D_").addAttribute("ui.class", "Lavender"); 
		Plan_18.getEdge("D_J_").setAttribute("ui.label", "D_J_"); 
		Plan_18.getEdge("D_J_").addAttribute("ui.class", "Lavender"); 
		Plan_18.getEdge("J_P_").setAttribute("ui.label", "J_P_"); 
		Plan_18.getEdge("J_P_").addAttribute("ui.class", "Lavender"); 
		Plan_18.getEdge("L_M_").setAttribute("ui.label", "L_M_"); 
		Plan_18.getEdge("L_M_").addAttribute("ui.class", "Lavender"); 
		Plan_18.getEdge("QR").setAttribute("ui.label", "QR"); 
		Plan_18.getEdge("QR").addAttribute("ui.class", "Lavender"); 
		Plan_18.getEdge("QZ").setAttribute("ui.label", "QZ"); 
		Plan_18.getEdge("QZ").addAttribute("ui.class", "Lavender"); 
		Plan_18.getEdge("K_L_").setAttribute("ui.label", "K_L_"); 
		Plan_18.getEdge("K_L_").addAttribute("ui.class", "Lavender"); 
		Plan_18.getEdge("ZK_").setAttribute("ui.label", "ZK_"); 
		Plan_18.getEdge("ZK_").addAttribute("ui.class", "Lavender"); 
		Plan_18.getEdge("O_P_").setAttribute("ui.label", "O_P_"); 
		Plan_18.getEdge("O_P_").addAttribute("ui.class", "Lavender"); 
		Plan_18.getEdge("N_O_").setAttribute("ui.label", "N_O_"); 
		Plan_18.getEdge("N_O_").addAttribute("ui.class", "Lavender"); 
		Plan_18.getEdge("M_N_").setAttribute("ui.label", "M_N_"); 
		Plan_18.getEdge("M_N_").addAttribute("ui.class", "Lavender"); 
		Plan_18.getEdge("SB_").setAttribute("ui.label", "SB_"); 
		Plan_18.getEdge("SB_").addAttribute("ui.class", "Lavender"); 
		Plan_18.getEdge("B_M_").setAttribute("ui.label", "B_M_"); 
		Plan_18.getEdge("B_M_").addAttribute("ui.class", "Lavender"); 
	return Plan_18; 
} // fin de getPlan18 


//------------------------------------------------------------------------------------

//------------------------------------------------------------------------------- 
public Graph getPlan35(String pName){ 
	Graph Plan_1 = new SingleGraph("Plan_35"); 
		Plan_1.setStrict(false); 
		Plan_1.setAutoCreate( true ); 
		Plan_1.addAttribute("ui.stylesheet", "url('file:config/Modele1.css')"); 
		Plan_1.addAttribute("ui.quality"); 
		Plan_1.addAttribute("ui.antialias"); 
		Plan_1.addEdge("startH", "start", "H", true ); 
		Plan_1.addEdge("HN", "H", "N", true ); 
		Plan_1.addEdge("NO", "N", "O", true ); 
		Plan_1.addEdge("OP", "O", "P", true ); 
		Plan_1.addEdge("PT", "P", "T", true ); 
		Plan_1.addEdge("TY", "T", "Y", true ); 
		Plan_1.addEdge("YD_", "Y", "D_", true ); 
		Plan_1.addEdge("D_O_", "D_", "O_", true ); 
		Plan_1.addEdge("O_P_", "O_", "P_", true ); 
		Plan_1.addEdge("TC_", "T", "C_", true ); 
		Plan_1.addEdge("C_N_", "C_", "N_", true ); 
		Plan_1.addEdge("N_O_", "N_", "O_", true ); 
		Plan_1.addEdge("HI", "H", "I", true ); 
		Plan_1.addEdge("IL", "I", "L", true ); 
		Plan_1.addEdge("LS", "L", "S", true ); 
		Plan_1.addEdge("ST", "S", "T", true ); 
		Plan_1.addEdge("IO", "I", "O", true ); 
		Plan_1.addEdge("PX", "P", "X", true ); 
		Plan_1.addEdge("XY", "X", "Y", true ); 
		Plan_1.addEdge("D_J_", "D_", "J_", true ); 
		Plan_1.addEdge("J_P_", "J_", "P_", true ); 
		Plan_1.addEdge("SB_", "S", "B_", true ); 
		Plan_1.addEdge("B_M_", "B_", "M_", true ); 
		Plan_1.addEdge("M_N_", "M_", "N_", true ); 
	//	Plan_1.addEdge("start", "start", "H", true ); 
		Plan_1.getNode("start").setAttribute("ui.label", "start"); 
		Plan_1.getNode("H").setAttribute("ui.label", "H"); 
		Plan_1.getNode("N").setAttribute("ui.label", "N"); 
		Plan_1.getNode("O").setAttribute("ui.label", "O"); 
		Plan_1.getNode("P").setAttribute("ui.label", "P"); 
		Plan_1.getNode("T").setAttribute("ui.label", "T"); 
		Plan_1.getNode("Y").setAttribute("ui.label", "Y"); 
		Plan_1.getNode("D_").setAttribute("ui.label", "D_"); 
		Plan_1.getNode("O_").setAttribute("ui.label", "O_"); 
		Plan_1.getNode("P_").setAttribute("ui.label", "P_"); 
		Plan_1.getNode("C_").setAttribute("ui.label", "C_"); 
		Plan_1.getNode("N_").setAttribute("ui.label", "N_"); 
		Plan_1.getNode("I").setAttribute("ui.label", "I"); 
		Plan_1.getNode("L").setAttribute("ui.label", "L"); 
		Plan_1.getNode("S").setAttribute("ui.label", "S"); 
		Plan_1.getNode("X").setAttribute("ui.label", "X"); 
		Plan_1.getNode("J_").setAttribute("ui.label", "J_"); 
		Plan_1.getNode("B_").setAttribute("ui.label", "B_"); 
		Plan_1.getNode("M_").setAttribute("ui.label", "M_"); 
		Plan_1.getEdge("HN").setAttribute("ui.label", "HN"); 
		Plan_1.getEdge("HN").addAttribute("ui.class", "Blue"); 
		Plan_1.getEdge("NO").setAttribute("ui.label", "NO"); 
		Plan_1.getEdge("NO").addAttribute("ui.class", "Blue"); 
		Plan_1.getEdge("OP").setAttribute("ui.label", "OP"); 
		Plan_1.getEdge("OP").addAttribute("ui.class", "Blue"); 
		Plan_1.getEdge("PT").setAttribute("ui.label", "PT"); 
		Plan_1.getEdge("PT").addAttribute("ui.class", "Blue"); 
		Plan_1.getEdge("TY").setAttribute("ui.label", "TY"); 
		Plan_1.getEdge("TY").addAttribute("ui.class", "Blue"); 
		Plan_1.getEdge("YD_").setAttribute("ui.label", "YD_"); 
		Plan_1.getEdge("YD_").addAttribute("ui.class", "Blue"); 
		Plan_1.getEdge("D_O_").setAttribute("ui.label", "D_O_"); 
		Plan_1.getEdge("D_O_").addAttribute("ui.class", "Blue"); 
		Plan_1.getEdge("O_P_").setAttribute("ui.label", "O_P_"); 
		Plan_1.getEdge("O_P_").addAttribute("ui.class", "Blue"); 
		Plan_1.getEdge("TC_").setAttribute("ui.label", "TC_"); 
		Plan_1.getEdge("TC_").addAttribute("ui.class", "Blue"); 
		Plan_1.getEdge("C_N_").setAttribute("ui.label", "C_N_"); 
		Plan_1.getEdge("C_N_").addAttribute("ui.class", "Blue"); 
		Plan_1.getEdge("N_O_").setAttribute("ui.label", "N_O_"); 
		Plan_1.getEdge("N_O_").addAttribute("ui.class", "Blue"); 
		Plan_1.getEdge("HI").setAttribute("ui.label", "HI"); 
		Plan_1.getEdge("HI").addAttribute("ui.class", "Blue"); 
		Plan_1.getEdge("IL").setAttribute("ui.label", "IL"); 
		Plan_1.getEdge("IL").addAttribute("ui.class", "Blue"); 
		Plan_1.getEdge("LS").setAttribute("ui.label", "LS"); 
		Plan_1.getEdge("LS").addAttribute("ui.class", "Blue"); 
		Plan_1.getEdge("ST").setAttribute("ui.label", "ST"); 
		Plan_1.getEdge("ST").addAttribute("ui.class", "Blue"); 
		Plan_1.getEdge("IO").setAttribute("ui.label", "IO"); 
		Plan_1.getEdge("IO").addAttribute("ui.class", "Blue"); 
		Plan_1.getEdge("PX").setAttribute("ui.label", "PX"); 
		Plan_1.getEdge("PX").addAttribute("ui.class", "Blue"); 
		Plan_1.getEdge("XY").setAttribute("ui.label", "XY"); 
		Plan_1.getEdge("XY").addAttribute("ui.class", "Blue"); 
		Plan_1.getEdge("D_J_").setAttribute("ui.label", "D_J_"); 
		Plan_1.getEdge("D_J_").addAttribute("ui.class", "Blue"); 
		Plan_1.getEdge("J_P_").setAttribute("ui.label", "J_P_"); 
		Plan_1.getEdge("J_P_").addAttribute("ui.class", "Blue"); 
		Plan_1.getEdge("SB_").setAttribute("ui.label", "SB_"); 
		Plan_1.getEdge("SB_").addAttribute("ui.class", "Blue"); 
		Plan_1.getEdge("B_M_").setAttribute("ui.label", "B_M_"); 
		Plan_1.getEdge("B_M_").addAttribute("ui.class", "Blue"); 
		Plan_1.getEdge("M_N_").setAttribute("ui.label", "M_N_"); 
		Plan_1.getEdge("M_N_").addAttribute("ui.class", "Blue"); 
	return Plan_1; 
} // fin de getPlan1 

// ------------------------------------------------------------------------------- 
// ------------------------------------------------------------------------------- 


//------------------------------------------------------------------------------- 
public Graph getPlan36(String pName){ 
	Graph Plan_5 = new SingleGraph("Plan_36"); 
		Plan_5.setStrict(false); 
		Plan_5.setAutoCreate( true ); 
		Plan_5.addAttribute("ui.stylesheet", "url('file:config/Modele1.css')"); 
		Plan_5.addAttribute("ui.quality"); 
		Plan_5.addAttribute("ui.antialias"); 
		Plan_5.addEdge("startI", "start", "I", true ); 
		Plan_5.addEdge("YI_", "Y", "I_", true ); 
		Plan_5.addEdge("IL", "I", "L", true ); 
		Plan_5.addEdge("LP", "L", "P", true ); 
		Plan_5.addEdge("PT", "P", "T", true ); 
		Plan_5.addEdge("TY", "T", "Y", true ); 
		Plan_5.addEdge("YD_", "Y", "D_", true ); 
		Plan_5.addEdge("D_O_", "D_", "O_", true ); 
		Plan_5.addEdge("O_P_", "O_", "P_", true ); 
		Plan_5.addEdge("I_J_", "I_", "J_", true ); 
		Plan_5.addEdge("WX", "W", "X", true ); 
		Plan_5.addEdge("OW", "O", "W", true ); 
		Plan_5.addEdge("PX", "P", "X", true ); 
		Plan_5.addEdge("XH_", "X", "H_", true ); 
		Plan_5.addEdge("H_I_", "H_", "I_", true ); 
		Plan_5.addEdge("XY", "X", "Y", true ); 
		Plan_5.addEdge("IO", "I", "O", true ); 
		Plan_5.addEdge("OP", "O", "P", true ); 
		Plan_5.addEdge("TC_", "T", "C_", true ); 
		Plan_5.addEdge("C_D_", "C_", "D_", true ); 
		Plan_5.addEdge("D_J_", "D_", "J_", true ); 
		Plan_5.addEdge("J_P_", "J_", "P_", true ); 
	//	Plan_5.addEdge("start", "start", "J_", true ); 
		Plan_5.getNode("start").setAttribute("ui.label", "start"); 
		Plan_5.getNode("J_").setAttribute("ui.label", "J_"); 
		Plan_5.getNode("I").setAttribute("ui.label", "I"); 
		Plan_5.getNode("L").setAttribute("ui.label", "L"); 
		Plan_5.getNode("P").setAttribute("ui.label", "P"); 
		Plan_5.getNode("T").setAttribute("ui.label", "T"); 
		Plan_5.getNode("Y").setAttribute("ui.label", "Y"); 
		Plan_5.getNode("D_").setAttribute("ui.label", "D_"); 
		Plan_5.getNode("O_").setAttribute("ui.label", "O_"); 
		Plan_5.getNode("P_").setAttribute("ui.label", "P_"); 
		Plan_5.getNode("W").setAttribute("ui.label", "W"); 
		Plan_5.getNode("O").setAttribute("ui.label", "O"); 
		Plan_5.getNode("C_").setAttribute("ui.label", "C_"); 
		Plan_5.getNode("X").setAttribute("ui.label", "X"); 
		Plan_5.getNode("H_").setAttribute("ui.label", "H_"); 
		Plan_5.getNode("I_").setAttribute("ui.label", "I_"); 
		Plan_5.getEdge("YI_").setAttribute("ui.label", "YI_"); 
		Plan_5.getEdge("YI_").addAttribute("ui.class", "Teal"); 
		Plan_5.getEdge("IL").setAttribute("ui.label", "IL"); 
		Plan_5.getEdge("IL").addAttribute("ui.class", "Teal"); 
		Plan_5.getEdge("LP").setAttribute("ui.label", "LP"); 
		Plan_5.getEdge("LP").addAttribute("ui.class", "Teal"); 
		Plan_5.getEdge("PT").setAttribute("ui.label", "PT"); 
		Plan_5.getEdge("PT").addAttribute("ui.class", "Teal"); 
		Plan_5.getEdge("TY").setAttribute("ui.label", "TY"); 
		Plan_5.getEdge("TY").addAttribute("ui.class", "Teal"); 
		Plan_5.getEdge("YD_").setAttribute("ui.label", "YD_"); 
		Plan_5.getEdge("YD_").addAttribute("ui.class", "Teal"); 
		Plan_5.getEdge("D_O_").setAttribute("ui.label", "D_O_"); 
		Plan_5.getEdge("D_O_").addAttribute("ui.class", "Teal"); 
		Plan_5.getEdge("O_P_").setAttribute("ui.label", "O_P_"); 
		Plan_5.getEdge("O_P_").addAttribute("ui.class", "Teal"); 
		Plan_5.getEdge("I_J_").setAttribute("ui.label", "I_J_"); 
		Plan_5.getEdge("I_J_").addAttribute("ui.class", "Teal"); 
		Plan_5.getEdge("WX").setAttribute("ui.label", "WX"); 
		Plan_5.getEdge("WX").addAttribute("ui.class", "Teal"); 
		Plan_5.getEdge("OW").setAttribute("ui.label", "OW"); 
		Plan_5.getEdge("OW").addAttribute("ui.class", "Teal"); 
		Plan_5.getEdge("PX").setAttribute("ui.label", "PX"); 
		Plan_5.getEdge("PX").addAttribute("ui.class", "Teal"); 
		Plan_5.getEdge("XH_").setAttribute("ui.label", "XH_"); 
		Plan_5.getEdge("XH_").addAttribute("ui.class", "Teal"); 
		Plan_5.getEdge("H_I_").setAttribute("ui.label", "H_I_"); 
		Plan_5.getEdge("H_I_").addAttribute("ui.class", "Teal"); 
		Plan_5.getEdge("XY").setAttribute("ui.label", "XY"); 
		Plan_5.getEdge("XY").addAttribute("ui.class", "Teal"); 
		Plan_5.getEdge("IO").setAttribute("ui.label", "IO"); 
		Plan_5.getEdge("IO").addAttribute("ui.class", "Teal"); 
		Plan_5.getEdge("OP").setAttribute("ui.label", "OP"); 
		Plan_5.getEdge("OP").addAttribute("ui.class", "Teal"); 
		Plan_5.getEdge("TC_").setAttribute("ui.label", "TC_"); 
		Plan_5.getEdge("TC_").addAttribute("ui.class", "Teal"); 
		Plan_5.getEdge("C_D_").setAttribute("ui.label", "C_D_"); 
		Plan_5.getEdge("C_D_").addAttribute("ui.class", "Teal"); 
		Plan_5.getEdge("D_J_").setAttribute("ui.label", "D_J_"); 
		Plan_5.getEdge("D_J_").addAttribute("ui.class", "Teal"); 
		Plan_5.getEdge("J_P_").setAttribute("ui.label", "J_P_"); 
		Plan_5.getEdge("J_P_").addAttribute("ui.class", "Teal"); 
	return Plan_5; 
} // fin de getPlan5 

// ------------------------------------------------------------------------------- 
 // ------------------------------------------------------------------------------- 
//------------------------------------------------------------------------------- 
public Graph getPlan37(String pName){ 
	Graph Plan_6 = new SingleGraph("Plan_37"); 
		Plan_6.setStrict(false); 
		Plan_6.setAutoCreate( true ); 
		Plan_6.addAttribute("ui.stylesheet", "url('file:config/Modele1.css')"); 
		Plan_6.addAttribute("ui.quality"); 
		Plan_6.addAttribute("ui.antialias"); 
		Plan_6.addEdge("startE", "start", "E", true ); 
		Plan_6.addEdge("I_J_", "I_", "J_", true ); 
		Plan_6.addEdge("TY", "T", "Y", true ); 
		Plan_6.addEdge("FI", "F", "I", true ); 
		Plan_6.addEdge("XH_", "X", "H_", true ); 
		Plan_6.addEdge("IO", "I", "O", true ); 
		Plan_6.addEdge("OW", "O", "W", true ); 
		Plan_6.addEdge("WX", "W", "X", true ); 
		Plan_6.addEdge("XY", "X", "Y", true ); 
		Plan_6.addEdge("YD_", "Y", "D_", true ); 
		Plan_6.addEdge("D_J_", "D_", "J_", true ); 
		Plan_6.addEdge("EF", "E", "F", true ); 
		Plan_6.addEdge("YI_", "Y", "I_", true ); 
		Plan_6.addEdge("WG_", "W", "G_", true ); 
		Plan_6.addEdge("G_H_", "G_", "H_", true ); 
		Plan_6.addEdge("H_I_", "H_", "I_", true ); 
		Plan_6.addEdge("OP", "O", "P", true ); 
		Plan_6.addEdge("PT", "P", "T", true ); 
		//Plan_6.addEdge("start", "start", "P", true ); 
		Plan_6.getNode("start").setAttribute("ui.label", "start"); 
		Plan_6.getNode("P").setAttribute("ui.label", "P"); 
		Plan_6.getNode("T").setAttribute("ui.label", "T"); 
		Plan_6.getNode("I_").setAttribute("ui.label", "I_"); 
		Plan_6.getNode("G_").setAttribute("ui.label", "G_"); 
		Plan_6.getNode("E").setAttribute("ui.label", "E"); 
		Plan_6.getNode("I").setAttribute("ui.label", "I"); 
		Plan_6.getNode("O").setAttribute("ui.label", "O"); 
		Plan_6.getNode("W").setAttribute("ui.label", "W"); 
		Plan_6.getNode("X").setAttribute("ui.label", "X"); 
		Plan_6.getNode("Y").setAttribute("ui.label", "Y"); 
		Plan_6.getNode("D_").setAttribute("ui.label", "D_"); 
		Plan_6.getNode("J_").setAttribute("ui.label", "J_"); 
		Plan_6.getNode("H_").setAttribute("ui.label", "H_"); 
		Plan_6.getNode("F").setAttribute("ui.label", "F"); 
		Plan_6.getEdge("I_J_").setAttribute("ui.label", "I_J_"); 
		Plan_6.getEdge("I_J_").addAttribute("ui.class", "Sangria"); 
		Plan_6.getEdge("TY").setAttribute("ui.label", "TY"); 
		Plan_6.getEdge("TY").addAttribute("ui.class", "Sangria"); 
		Plan_6.getEdge("FI").setAttribute("ui.label", "FI"); 
		Plan_6.getEdge("FI").addAttribute("ui.class", "Sangria"); 
		Plan_6.getEdge("XH_").setAttribute("ui.label", "XH_"); 
		Plan_6.getEdge("XH_").addAttribute("ui.class", "Sangria"); 
		Plan_6.getEdge("IO").setAttribute("ui.label", "IO"); 
		Plan_6.getEdge("IO").addAttribute("ui.class", "Sangria"); 
		Plan_6.getEdge("OW").setAttribute("ui.label", "OW"); 
		Plan_6.getEdge("OW").addAttribute("ui.class", "Sangria"); 
		Plan_6.getEdge("WX").setAttribute("ui.label", "WX"); 
		Plan_6.getEdge("WX").addAttribute("ui.class", "Sangria"); 
		Plan_6.getEdge("XY").setAttribute("ui.label", "XY"); 
		Plan_6.getEdge("XY").addAttribute("ui.class", "Sangria"); 
		Plan_6.getEdge("YD_").setAttribute("ui.label", "YD_"); 
		Plan_6.getEdge("YD_").addAttribute("ui.class", "Sangria"); 
		Plan_6.getEdge("D_J_").setAttribute("ui.label", "D_J_"); 
		Plan_6.getEdge("D_J_").addAttribute("ui.class", "Sangria"); 
		Plan_6.getEdge("EF").setAttribute("ui.label", "EF"); 
		Plan_6.getEdge("EF").addAttribute("ui.class", "Sangria"); 
		Plan_6.getEdge("YI_").setAttribute("ui.label", "YI_"); 
		Plan_6.getEdge("YI_").addAttribute("ui.class", "Sangria"); 
		Plan_6.getEdge("WG_").setAttribute("ui.label", "WG_"); 
		Plan_6.getEdge("WG_").addAttribute("ui.class", "Sangria"); 
		Plan_6.getEdge("G_H_").setAttribute("ui.label", "G_H_"); 
		Plan_6.getEdge("G_H_").addAttribute("ui.class", "Sangria"); 
		Plan_6.getEdge("H_I_").setAttribute("ui.label", "H_I_"); 
		Plan_6.getEdge("H_I_").addAttribute("ui.class", "Sangria"); 
		Plan_6.getEdge("OP").setAttribute("ui.label", "OP"); 
		Plan_6.getEdge("OP").addAttribute("ui.class", "Sangria"); 
		Plan_6.getEdge("PT").setAttribute("ui.label", "PT"); 
		Plan_6.getEdge("PT").addAttribute("ui.class", "Sangria"); 
	return Plan_6; 
} // fin de getPlan6 

// ------------------------------------------------------------------------------- 
// ------------------------------------------------------------------------------- 

//------------------------------------------------------------------------------- 
public Graph getPlan38(String pName){ 
	Graph Plan_7 = new SingleGraph("Plan_38"); 
		Plan_7.setStrict(false); 
		Plan_7.setAutoCreate( true ); 
		Plan_7.addAttribute("ui.stylesheet", "url('file:config/Modele1.css')"); 
		Plan_7.addAttribute("ui.quality"); 
		Plan_7.addAttribute("ui.antialias"); 
		Plan_7.addEdge("startD", "start", "D", true ); 
		Plan_7.addEdge("PT", "P", "T", true ); 
		Plan_7.addEdge("NO", "N", "O", true ); 
		Plan_7.addEdge("ST", "S", "T", true ); 
		Plan_7.addEdge("TC_", "T", "C_", true ); 
		Plan_7.addEdge("C_D_", "C_", "D_", true ); 
		Plan_7.addEdge("D_O_", "D_", "O_", true ); 
		Plan_7.addEdge("HN", "H", "N", true ); 
		Plan_7.addEdge("LS", "L", "S", true ); 
		Plan_7.addEdge("DH", "D", "H", true ); 
		Plan_7.addEdge("HI", "H", "I", true ); 
		Plan_7.addEdge("IO", "I", "O", true ); 
		Plan_7.addEdge("OP", "O", "P", true ); 
		Plan_7.addEdge("IL", "I", "L", true ); 
		//Plan_7.addEdge("start", "start", "N", true ); 
		Plan_7.getNode("start").setAttribute("ui.label", "start"); 
		Plan_7.getNode("N").setAttribute("ui.label", "N"); 
		Plan_7.getNode("O_").setAttribute("ui.label", "O_"); 
		Plan_7.getNode("S").setAttribute("ui.label", "S"); 
		Plan_7.getNode("T").setAttribute("ui.label", "T"); 
		Plan_7.getNode("C_").setAttribute("ui.label", "C_"); 
		Plan_7.getNode("D_").setAttribute("ui.label", "D_"); 
		Plan_7.getNode("P").setAttribute("ui.label", "P"); 
		Plan_7.getNode("L").setAttribute("ui.label", "L"); 
		Plan_7.getNode("O").setAttribute("ui.label", "O"); 
		Plan_7.getNode("D").setAttribute("ui.label", "D"); 
		Plan_7.getNode("H").setAttribute("ui.label", "H"); 
		Plan_7.getNode("I").setAttribute("ui.label", "I"); 
		Plan_7.getEdge("PT").setAttribute("ui.label", "PT"); 
		Plan_7.getEdge("PT").addAttribute("ui.class", "Ruby"); 
		Plan_7.getEdge("NO").setAttribute("ui.label", "NO"); 
		Plan_7.getEdge("NO").addAttribute("ui.class", "Ruby"); 
		Plan_7.getEdge("ST").setAttribute("ui.label", "ST"); 
		Plan_7.getEdge("ST").addAttribute("ui.class", "Ruby"); 
		Plan_7.getEdge("TC_").setAttribute("ui.label", "TC_"); 
		Plan_7.getEdge("TC_").addAttribute("ui.class", "Ruby"); 
		Plan_7.getEdge("C_D_").setAttribute("ui.label", "C_D_"); 
		Plan_7.getEdge("C_D_").addAttribute("ui.class", "Ruby"); 
		Plan_7.getEdge("D_O_").setAttribute("ui.label", "D_O_"); 
		Plan_7.getEdge("D_O_").addAttribute("ui.class", "Ruby"); 
		Plan_7.getEdge("HN").setAttribute("ui.label", "HN"); 
		Plan_7.getEdge("HN").addAttribute("ui.class", "Ruby"); 
		Plan_7.getEdge("LS").setAttribute("ui.label", "LS"); 
		Plan_7.getEdge("LS").addAttribute("ui.class", "Ruby"); 
		Plan_7.getEdge("DH").setAttribute("ui.label", "DH"); 
		Plan_7.getEdge("DH").addAttribute("ui.class", "Ruby"); 
		Plan_7.getEdge("HI").setAttribute("ui.label", "HI"); 
		Plan_7.getEdge("HI").addAttribute("ui.class", "Ruby"); 
		Plan_7.getEdge("IO").setAttribute("ui.label", "IO"); 
		Plan_7.getEdge("IO").addAttribute("ui.class", "Ruby"); 
		Plan_7.getEdge("OP").setAttribute("ui.label", "OP"); 
		Plan_7.getEdge("OP").addAttribute("ui.class", "Ruby"); 
		Plan_7.getEdge("IL").setAttribute("ui.label", "IL"); 
		Plan_7.getEdge("IL").addAttribute("ui.class", "Ruby"); 
	return Plan_7; 
} // fin de getPlan7 

// ------------------------------------------------------------------------------- 
// ------------------------------------------------------------------------------- 
//------------------------------------------------------------------------------- 
public Graph getPlan39(String pName){ 
	Graph Plan_10 = new SingleGraph("Plan_39"); 
		Plan_10.setStrict(false); 
		Plan_10.setAutoCreate( true ); 
		Plan_10.addAttribute("ui.stylesheet", "url('file:config/Modele1.css')"); 
		Plan_10.addAttribute("ui.quality"); 
		Plan_10.addAttribute("ui.antialias"); 
		Plan_10.addEdge("startC", "start", "C", true ); 
		Plan_10.addEdge("TY", "T", "Y", true ); 
		Plan_10.addEdge("PT", "P", "T", true ); 
		Plan_10.addEdge("FI", "F", "I", true ); 
		Plan_10.addEdge("IO", "I", "O", true ); 
		Plan_10.addEdge("OP", "O", "P", true ); 
		Plan_10.addEdge("PX", "P", "X", true ); 
		Plan_10.addEdge("LP", "L", "P", true ); 
		Plan_10.addEdge("KL", "K", "L", true ); 
		Plan_10.addEdge("HI", "H", "I", true ); 
		Plan_10.addEdge("FK", "F", "K", true ); 
		Plan_10.addEdge("TC_", "T", "C_", true ); 
		Plan_10.addEdge("C_D_", "C_", "D_", true ); 
		Plan_10.addEdge("D_O_", "D_", "O_", true ); 
		Plan_10.addEdge("DF", "D", "F", true ); 
		Plan_10.addEdge("YD_", "Y", "D_", true ); 
		Plan_10.addEdge("XY", "X", "Y", true ); 
		Plan_10.addEdge("CD", "C", "D", true ); 
		Plan_10.addEdge("DH", "D", "H", true ); 
		Plan_10.addEdge("HN", "H", "N", true ); 
		Plan_10.addEdge("NO", "N", "O", true ); 
		Plan_10.addEdge("OW", "O", "W", true ); 
		Plan_10.addEdge("WX", "W", "X", true ); 
		//Plan_10.addEdge("start", "start", "L", true ); 
		Plan_10.getNode("start").setAttribute("ui.label", "start"); 
		Plan_10.getNode("L").setAttribute("ui.label", "L"); 
		Plan_10.getNode("K").setAttribute("ui.label", "K"); 
		Plan_10.getNode("F").setAttribute("ui.label", "F"); 
		Plan_10.getNode("I").setAttribute("ui.label", "I"); 
		Plan_10.getNode("O").setAttribute("ui.label", "O"); 
		Plan_10.getNode("P").setAttribute("ui.label", "P"); 
		Plan_10.getNode("X").setAttribute("ui.label", "X"); 
		Plan_10.getNode("Y").setAttribute("ui.label", "Y"); 
		Plan_10.getNode("W").setAttribute("ui.label", "W"); 
		Plan_10.getNode("N").setAttribute("ui.label", "N"); 
		Plan_10.getNode("H").setAttribute("ui.label", "H"); 
		Plan_10.getNode("T").setAttribute("ui.label", "T"); 
		Plan_10.getNode("C_").setAttribute("ui.label", "C_"); 
		Plan_10.getNode("D_").setAttribute("ui.label", "D_"); 
		Plan_10.getNode("O_").setAttribute("ui.label", "O_"); 
		Plan_10.getNode("D").setAttribute("ui.label", "D"); 
		Plan_10.getNode("C").setAttribute("ui.label", "C"); 
		Plan_10.getEdge("TY").setAttribute("ui.label", "TY"); 
		Plan_10.getEdge("TY").addAttribute("ui.class", "Plum"); 
		Plan_10.getEdge("PT").setAttribute("ui.label", "PT"); 
		Plan_10.getEdge("PT").addAttribute("ui.class", "Plum"); 
		Plan_10.getEdge("FI").setAttribute("ui.label", "FI"); 
		Plan_10.getEdge("FI").addAttribute("ui.class", "Plum"); 
		Plan_10.getEdge("IO").setAttribute("ui.label", "IO"); 
		Plan_10.getEdge("IO").addAttribute("ui.class", "Plum"); 
		Plan_10.getEdge("OP").setAttribute("ui.label", "OP"); 
		Plan_10.getEdge("OP").addAttribute("ui.class", "Plum"); 
		Plan_10.getEdge("PX").setAttribute("ui.label", "PX"); 
		Plan_10.getEdge("PX").addAttribute("ui.class", "Plum"); 
		Plan_10.getEdge("LP").setAttribute("ui.label", "LP"); 
		Plan_10.getEdge("LP").addAttribute("ui.class", "Plum"); 
		Plan_10.getEdge("KL").setAttribute("ui.label", "KL"); 
		Plan_10.getEdge("KL").addAttribute("ui.class", "Plum"); 
		Plan_10.getEdge("HI").setAttribute("ui.label", "HI"); 
		Plan_10.getEdge("HI").addAttribute("ui.class", "Plum"); 
		Plan_10.getEdge("FK").setAttribute("ui.label", "FK"); 
		Plan_10.getEdge("FK").addAttribute("ui.class", "Plum"); 
		Plan_10.getEdge("TC_").setAttribute("ui.label", "TC_"); 
		Plan_10.getEdge("TC_").addAttribute("ui.class", "Plum"); 
		Plan_10.getEdge("C_D_").setAttribute("ui.label", "C_D_"); 
		Plan_10.getEdge("C_D_").addAttribute("ui.class", "Plum"); 
		Plan_10.getEdge("D_O_").setAttribute("ui.label", "D_O_"); 
		Plan_10.getEdge("D_O_").addAttribute("ui.class", "Plum"); 
		Plan_10.getEdge("DF").setAttribute("ui.label", "DF"); 
		Plan_10.getEdge("DF").addAttribute("ui.class", "Plum"); 
		Plan_10.getEdge("YD_").setAttribute("ui.label", "YD_"); 
		Plan_10.getEdge("YD_").addAttribute("ui.class", "Plum"); 
		Plan_10.getEdge("XY").setAttribute("ui.label", "XY"); 
		Plan_10.getEdge("XY").addAttribute("ui.class", "Plum"); 
		Plan_10.getEdge("CD").setAttribute("ui.label", "CD"); 
		Plan_10.getEdge("CD").addAttribute("ui.class", "Plum"); 
		Plan_10.getEdge("DH").setAttribute("ui.label", "DH"); 
		Plan_10.getEdge("DH").addAttribute("ui.class", "Plum"); 
		Plan_10.getEdge("HN").setAttribute("ui.label", "HN"); 
		Plan_10.getEdge("HN").addAttribute("ui.class", "Plum"); 
		Plan_10.getEdge("NO").setAttribute("ui.label", "NO"); 
		Plan_10.getEdge("NO").addAttribute("ui.class", "Plum"); 
		Plan_10.getEdge("OW").setAttribute("ui.label", "OW"); 
		Plan_10.getEdge("OW").addAttribute("ui.class", "Plum"); 
		Plan_10.getEdge("WX").setAttribute("ui.label", "WX"); 
		Plan_10.getEdge("WX").addAttribute("ui.class", "Plum"); 
	return Plan_10; 
} // fin de getPlan10 

// ------------------------------------------------------------------------------- 
// ------------------------------------------------------------------------------- 

//------------------------------------------------------------------------------- 
public Graph getPlan40(String pName){ 
	Graph Plan_18 = new SingleGraph("Plan_40"); 
		Plan_18.setStrict(false); 
		Plan_18.setAutoCreate( true ); 
		Plan_18.addAttribute("ui.stylesheet", "url('file:config/Modele1.css')"); 
		Plan_18.addAttribute("ui.quality"); 
		Plan_18.addAttribute("ui.antialias"); 
		Plan_18.addEdge("startB", "start", "B", true ); 
		Plan_18.addEdge("TC_", "T", "C_", true ); 
		Plan_18.addEdge("LP", "L", "P", true ); 
		Plan_18.addEdge("ST", "S", "T", true ); 
		Plan_18.addEdge("LS", "L", "S", true ); 
		Plan_18.addEdge("IL", "I", "L", true ); 
		Plan_18.addEdge("SB_", "S", "B_", true ); 
		Plan_18.addEdge("B_C_", "B_", "C_", true ); 
		Plan_18.addEdge("PT", "P", "T", true ); 
		Plan_18.addEdge("BD", "B", "D", true ); 
		Plan_18.addEdge("DH", "D", "H", true ); 
		Plan_18.addEdge("HI", "H", "I", true ); 
		//Plan_18.addEdge("start", "start", "S", true ); 
		Plan_18.getNode("start").setAttribute("ui.label", "start"); 
		Plan_18.getNode("S").setAttribute("ui.label", "S"); 
		Plan_18.getNode("L").setAttribute("ui.label", "L"); 
		Plan_18.getNode("P").setAttribute("ui.label", "P"); 
		Plan_18.getNode("D").setAttribute("ui.label", "D"); 
		Plan_18.getNode("C_").setAttribute("ui.label", "C_"); 
		Plan_18.getNode("T").setAttribute("ui.label", "T"); 
		Plan_18.getNode("B_").setAttribute("ui.label", "B_"); 
		Plan_18.getNode("I").setAttribute("ui.label", "I"); 
		Plan_18.getNode("H").setAttribute("ui.label", "H"); 
		Plan_18.getNode("B").setAttribute("ui.label", "B"); 
		Plan_18.getEdge("TC_").setAttribute("ui.label", "TC_"); 
		Plan_18.getEdge("TC_").addAttribute("ui.class", "Lavender"); 
		Plan_18.getEdge("LP").setAttribute("ui.label", "LP"); 
		Plan_18.getEdge("LP").addAttribute("ui.class", "Lavender"); 
		Plan_18.getEdge("ST").setAttribute("ui.label", "ST"); 
		Plan_18.getEdge("ST").addAttribute("ui.class", "Lavender"); 
		Plan_18.getEdge("LS").setAttribute("ui.label", "LS"); 
		Plan_18.getEdge("LS").addAttribute("ui.class", "Lavender"); 
		Plan_18.getEdge("IL").setAttribute("ui.label", "IL"); 
		Plan_18.getEdge("IL").addAttribute("ui.class", "Lavender"); 
		Plan_18.getEdge("SB_").setAttribute("ui.label", "SB_"); 
		Plan_18.getEdge("SB_").addAttribute("ui.class", "Lavender"); 
		Plan_18.getEdge("B_C_").setAttribute("ui.label", "B_C_"); 
		Plan_18.getEdge("B_C_").addAttribute("ui.class", "Lavender"); 
		Plan_18.getEdge("PT").setAttribute("ui.label", "PT"); 
		Plan_18.getEdge("PT").addAttribute("ui.class", "Lavender"); 
		Plan_18.getEdge("BD").setAttribute("ui.label", "BD"); 
		Plan_18.getEdge("BD").addAttribute("ui.class", "Lavender"); 
		Plan_18.getEdge("DH").setAttribute("ui.label", "DH"); 
		Plan_18.getEdge("DH").addAttribute("ui.class", "Lavender"); 
		Plan_18.getEdge("HI").setAttribute("ui.label", "HI"); 
		Plan_18.getEdge("HI").addAttribute("ui.class", "Lavender"); 
	return Plan_18; 
} // fin de getPlan18 



//------------------------------------------------------------------------------- 
public Graph getPlan41(String pName){ 
	Graph Plan_41 = new SingleGraph("Plan_40"); 
	Plan_41.setStrict(false); 
	Plan_41.setAutoCreate( true ); 
	Plan_41.addAttribute("ui.stylesheet", "url('file:config/Modele1.css')"); 
	Plan_41.addAttribute("ui.quality"); 
	Plan_41.addAttribute("ui.antialias"); 
	Plan_41.addEdge("startA", "start", "A", true ); 
	Plan_41.addEdge("AI", "A", "I", true ); Plan_41.getEdge("AI").addAttribute("ui.class", "Blue");
	Plan_41.addEdge("IJ", "I", "J", true ); Plan_41.getEdge("IJ").addAttribute("ui.class", "Blue");
	Plan_41.addEdge("AD", "A", "D", true ); Plan_41.getEdge("AD").addAttribute("ui.class", "Blue");
	Plan_41.addEdge("DG", "D", "G", true ); Plan_41.getEdge("DG").addAttribute("ui.class", "Blue");
	Plan_41.addEdge("GI", "G", "I", true ); Plan_41.getEdge("GI").addAttribute("ui.class", "Blue");
	Plan_41.addEdge("GH", "G", "H", true ); Plan_41.getEdge("GH").addAttribute("ui.class", "Blue");
	Plan_41.addEdge("HI", "H", "I", true ); Plan_41.getEdge("HI").addAttribute("ui.class", "Blue");

		
	for (Node n : Plan_41)
		n.addAttribute("label", n.getId());
	
	return Plan_41; 
} // fin de getPlan18 

public Graph getPlan42(String pName){ 
	Graph Plan_42 = new SingleGraph("Plan_40"); 
	Plan_42.setStrict(false); 
	Plan_42.setAutoCreate( true ); 
	Plan_42.addAttribute("ui.stylesheet", "url('file:config/Modele1.css')"); 
	Plan_42.addAttribute("ui.quality"); 
	Plan_42.addAttribute("ui.antialias"); 
	Plan_42.addEdge("startA", "start", "A", true ); 

	Plan_42.addEdge("AD", "A", "D", true ); Plan_42.getEdge("AD").addAttribute("ui.class", "Orange");
	Plan_42.addEdge("DG", "D", "G", true ); Plan_42.getEdge("DG").addAttribute("ui.class", "Orange");
	Plan_42.addEdge("GH", "G", "H", true ); Plan_42.getEdge("GH").addAttribute("ui.class", "Orange");
	Plan_42.addEdge("HK", "H", "K", true ); Plan_42.getEdge("HK").addAttribute("ui.class", "Orange");
	Plan_42.addEdge("DF", "D", "F", true ); Plan_42.getEdge("DF").addAttribute("ui.class", "Orange");
	Plan_42.addEdge("FK", "F", "K", true ); Plan_42.getEdge("FK").addAttribute("ui.class", "Orange");
	
	
	
	for (Node n : Plan_42)
		n.addAttribute("label", n.getId());
		
	return Plan_42; 
} // fin de getPlan18 

public Graph getPlan43(String pName){ 
	Graph Plan_43 = new SingleGraph("Plan_40"); 
	Plan_43.setStrict(false); 
	Plan_43.setAutoCreate( true ); 
	Plan_43.addAttribute("ui.stylesheet", "url('file:config/Modele1.css')"); 
	Plan_43.addAttribute("ui.quality"); 
	Plan_43.addAttribute("ui.antialias"); 
	Plan_43.addEdge("startB", "start", "B", true ); 
	
	Plan_43.addEdge("BE", "B", "E", true ); Plan_43.getEdge("BE").addAttribute("ui.class", "Green");
	Plan_43.addEdge("EG", "E", "G", true ); Plan_43.getEdge("EG").addAttribute("ui.class", "Green");
	Plan_43.addEdge("GI", "G", "I", true ); Plan_43.getEdge("GI").addAttribute("ui.class", "Green");
	Plan_43.addEdge("IJ", "I", "J", true ); Plan_43.getEdge("IJ").addAttribute("ui.class", "Green");
	
	
	for (Node n : Plan_43)
		n.addAttribute("label", n.getId());
		
	return Plan_43; 
} // fin de getPlan18 

public Graph getPlan44(String pName){ 
	Graph Plan_44 = new SingleGraph("Plan_40"); 
	Plan_44.setStrict(false); 
	Plan_44.setAutoCreate( true ); 
	Plan_44.addAttribute("ui.stylesheet", "url('file:config/Modele1.css')"); 
	Plan_44.addAttribute("ui.quality"); 
	Plan_44.addAttribute("ui.antialias"); 
	Plan_44.addEdge("startC", "start", "C", true ); 
	
	Plan_44.addEdge("CE", "C", "E", true ); Plan_44.getEdge("CE").addAttribute("ui.class", "Chocolate");
	Plan_44.addEdge("EG", "E", "G", true ); Plan_44.getEdge("EG").addAttribute("ui.class", "Chocolate");
	Plan_44.addEdge("GI", "G", "I", true ); Plan_44.getEdge("GI").addAttribute("ui.class", "Chocolate");
	Plan_44.addEdge("IK", "I", "K", true ); Plan_44.getEdge("IK").addAttribute("ui.class", "Chocolate");
	Plan_44.addEdge("CF", "C", "F", true ); Plan_44.getEdge("CF").addAttribute("ui.class", "Chocolate");
	Plan_44.addEdge("FE", "F", "E", true ); Plan_44.getEdge("FE").addAttribute("ui.class", "Chocolate");
	Plan_44.addEdge("GH", "G", "H", true ); Plan_44.getEdge("GH").addAttribute("ui.class", "Chocolate");
	Plan_44.addEdge("HK", "H", "K", true ); Plan_44.getEdge("HK").addAttribute("ui.class", "Chocolate");
	
	
	for (Node n : Plan_44)
		n.addAttribute("label", n.getId());
		
	return Plan_44; 
} // fin de getPlan18 









































public Graph getPlan1001(String pName){ 
	Graph Plan = new SingleGraph("Plan1001"); 
	Plan.setStrict(false); 
	Plan.setAutoCreate( true ); 
	Plan.addAttribute("ui.stylesheet", "url('file:config/Modele1.css')"); 
	Plan.addAttribute("ui.quality"); 
	Plan.addAttribute("ui.antialias"); 
	
	Plan.addEdge("startK", "start", "K", true );
	Plan.addEdge("KL", "K", "L", true ); 
	Plan.addEdge("LS", "L", "S", true ); 
	Plan.addEdge("SB_", "S", "B_", true ); 
	Plan.addEdge("B_C_", "B_", "C_", true ); 
	Plan.addEdge("C_D_", "C_", "D_", true ); 
	Plan.addEdge("LP", "L", "P", true ); 
	Plan.addEdge("PX", "P", "X", true ); 
	Plan.addEdge("XY", "X", "Y", true ); 
	Plan.addEdge("YD_", "Y", "D_", true ); 
	Plan.addEdge("ST", "S", "T", true ); 
	Plan.addEdge("TY", "T", "Y", true ); 
	Plan.addEdge("PT", "P", "T", true ); 
	Plan.addEdge("TC_", "T", "C_", true ); 
	Plan.addEdge("KR", "K", "R", true ); 
	Plan.addEdge("RS", "R", "S", true ); 
	//Plan.addEdge("RA_", "R", "A_", true ); 
	//Plan.addEdge("A_B_", "A_", "B_", true ); 
	
	Plan.getNode("start").setAttribute("ui.label", "start");
	Plan.getNode("K").setAttribute("ui.label", "K"); 
	Plan.getNode("L").setAttribute("ui.label", "L"); 
	Plan.getNode("S").setAttribute("ui.label", "S"); 
	Plan.getNode("B_").setAttribute("ui.label", "B_"); 
	Plan.getNode("C_").setAttribute("ui.label", "C_"); 
	Plan.getNode("D_").setAttribute("ui.label", "D_"); 
	Plan.getNode("P").setAttribute("ui.label", "P"); 
	Plan.getNode("X").setAttribute("ui.label", "X"); 
	Plan.getNode("Y").setAttribute("ui.label", "Y"); 
	Plan.getNode("T").setAttribute("ui.label", "T"); 
	Plan.getNode("R").setAttribute("ui.label", "R"); 
	//Plan.getNode("A_").setAttribute("ui.label", "A_"); 
	Plan.getEdge("KL").setAttribute("ui.label", "KL"); 
	Plan.getEdge("KL").addAttribute("ui.class", "Blue"); 
	Plan.getEdge("LS").setAttribute("ui.label", "LS"); 
	Plan.getEdge("LS").addAttribute("ui.class", "Blue"); 
	Plan.getEdge("SB_").setAttribute("ui.label", "SB_"); 
	Plan.getEdge("SB_").addAttribute("ui.class", "Blue"); 
	Plan.getEdge("B_C_").setAttribute("ui.label", "B_C_"); 
	Plan.getEdge("B_C_").addAttribute("ui.class", "Blue"); 
	Plan.getEdge("C_D_").setAttribute("ui.label", "C_D_"); 
	Plan.getEdge("C_D_").addAttribute("ui.class", "Blue"); 
	Plan.getEdge("LP").setAttribute("ui.label", "LP"); 
	Plan.getEdge("LP").addAttribute("ui.class", "Blue"); 
	Plan.getEdge("PX").setAttribute("ui.label", "PX"); 
	Plan.getEdge("PX").addAttribute("ui.class", "Blue"); 
	Plan.getEdge("XY").setAttribute("ui.label", "XY"); 
	Plan.getEdge("XY").addAttribute("ui.class", "Blue"); 
	Plan.getEdge("YD_").setAttribute("ui.label", "YD_"); 
	Plan.getEdge("YD_").addAttribute("ui.class", "Blue"); 
	Plan.getEdge("ST").setAttribute("ui.label", "ST"); 
	Plan.getEdge("ST").addAttribute("ui.class", "Blue"); 
	Plan.getEdge("TY").setAttribute("ui.label", "TY"); 
	Plan.getEdge("TY").addAttribute("ui.class", "Blue"); 
	Plan.getEdge("PT").setAttribute("ui.label", "PT"); 
	Plan.getEdge("PT").addAttribute("ui.class", "Blue"); 
	Plan.getEdge("TC_").setAttribute("ui.label", "TC_"); 
	Plan.getEdge("TC_").addAttribute("ui.class", "Blue"); 
	Plan.getEdge("KR").setAttribute("ui.label", "KR"); 
	Plan.getEdge("KR").addAttribute("ui.class", "Blue"); 
	Plan.getEdge("RS").setAttribute("ui.label", "RS"); 
	Plan.getEdge("RS").addAttribute("ui.class", "Blue"); 
	//Plan.getEdge("RA_").setAttribute("ui.label", "RA_"); 
	//Plan.getEdge("RA_").addAttribute("ui.class", "Blue"); 
	//Plan.getEdge("A_B_").setAttribute("ui.label", "A_B_"); 
	//Plan.getEdge("A_B_").addAttribute("ui.class", "Blue"); 
return Plan; 
} // fin de getPlan1001 



public Graph getPlan1002(String pName){ 
	Graph Plan = new SingleGraph("Plan1002"); 
	Plan.setStrict(false); 
		Plan.setAutoCreate( true ); 
		Plan.addAttribute("ui.stylesheet", "url('file:config/Modele1.css')"); 
	Plan.addAttribute("ui.quality"); 
	Plan.addAttribute("ui.antialias"); 
	
	Plan.addEdge("startK", "start", "K", true );
		Plan.addEdge("KL", "K", "L", true ); 
	Plan.addEdge("LS", "L", "S", true ); 
	Plan.addEdge("SB_", "S", "B_", true ); 
	Plan.addEdge("B_C_", "B_", "C_", true ); 
	Plan.addEdge("C_D_", "C_", "D_", true ); 
	Plan.addEdge("LP", "L", "P", true ); 
	Plan.addEdge("PX", "P", "X", true ); 
	Plan.addEdge("XY", "X", "Y", true ); 
	Plan.addEdge("YD_", "Y", "D_", true ); 
	Plan.addEdge("ST", "S", "T", true ); 
	Plan.addEdge("TY", "T", "Y", true ); 
	Plan.addEdge("PT", "P", "T", true ); 
	Plan.addEdge("TC_", "T", "C_", true ); 
	Plan.addEdge("KR", "K", "R", true ); 
	Plan.addEdge("RS", "R", "S", true ); 
	//Plan.addEdge("RA_", "R", "A_", true ); 
	//Plan.addEdge("A_B_", "A_", "B_", true ); 
	
	Plan.getNode("start").setAttribute("ui.label", "start");
	Plan.getNode("K").setAttribute("ui.label", "K"); 
	Plan.getNode("L").setAttribute("ui.label", "L"); 
	Plan.getNode("S").setAttribute("ui.label", "S"); 
	Plan.getNode("B_").setAttribute("ui.label", "B_"); 
	Plan.getNode("C_").setAttribute("ui.label", "C_"); 
	Plan.getNode("D_").setAttribute("ui.label", "D_"); 
	Plan.getNode("P").setAttribute("ui.label", "P"); 
	Plan.getNode("X").setAttribute("ui.label", "X"); 
	Plan.getNode("Y").setAttribute("ui.label", "Y"); 
	Plan.getNode("T").setAttribute("ui.label", "T"); 
	Plan.getNode("R").setAttribute("ui.label", "R"); 
	//Plan.getNode("A_").setAttribute("ui.label", "A_"); 
	Plan.getEdge("KL").setAttribute("ui.label", "KL"); 
	Plan.getEdge("KL").addAttribute("ui.class", "Blue"); 
	Plan.getEdge("LS").setAttribute("ui.label", "LS"); 
	Plan.getEdge("LS").addAttribute("ui.class", "Blue"); 
	Plan.getEdge("SB_").setAttribute("ui.label", "SB_"); 
	Plan.getEdge("SB_").addAttribute("ui.class", "Blue"); 
	Plan.getEdge("B_C_").setAttribute("ui.label", "B_C_"); 
	Plan.getEdge("B_C_").addAttribute("ui.class", "Blue"); 
	Plan.getEdge("C_D_").setAttribute("ui.label", "C_D_"); 
	Plan.getEdge("C_D_").addAttribute("ui.class", "Blue"); 
	Plan.getEdge("LP").setAttribute("ui.label", "LP"); 
	Plan.getEdge("LP").addAttribute("ui.class", "Blue"); 
	Plan.getEdge("PX").setAttribute("ui.label", "PX"); 
	Plan.getEdge("PX").addAttribute("ui.class", "Blue"); 
	Plan.getEdge("XY").setAttribute("ui.label", "XY"); 
	Plan.getEdge("XY").addAttribute("ui.class", "Blue"); 
	Plan.getEdge("YD_").setAttribute("ui.label", "YD_"); 
	Plan.getEdge("YD_").addAttribute("ui.class", "Blue"); 
	Plan.getEdge("ST").setAttribute("ui.label", "ST"); 
	Plan.getEdge("ST").addAttribute("ui.class", "Blue"); 
	Plan.getEdge("TY").setAttribute("ui.label", "TY"); 
	Plan.getEdge("TY").addAttribute("ui.class", "Blue"); 
	Plan.getEdge("PT").setAttribute("ui.label", "PT"); 
	Plan.getEdge("PT").addAttribute("ui.class", "Blue"); 
	Plan.getEdge("TC_").setAttribute("ui.label", "TC_"); 
	Plan.getEdge("TC_").addAttribute("ui.class", "Blue"); 
	Plan.getEdge("KR").setAttribute("ui.label", "KR"); 
	Plan.getEdge("KR").addAttribute("ui.class", "Blue"); 
	Plan.getEdge("RS").setAttribute("ui.label", "RS"); 
	Plan.getEdge("RS").addAttribute("ui.class", "Blue"); 
	//Plan.getEdge("RA_").setAttribute("ui.label", "RA_"); 
	//Plan.getEdge("RA_").addAttribute("ui.class", "Blue"); 
	//Plan.getEdge("A_B_").setAttribute("ui.label", "A_B_"); 
	//Plan.getEdge("A_B_").addAttribute("ui.class", "Blue"); 
return Plan; 
} // fin de getPlan1002 



public Graph getPlan1003(String pName){ 
	Graph Plan = new SingleGraph("Plan1003"); 
	Plan.setStrict(false); 
		Plan.setAutoCreate( true ); 
		Plan.addAttribute("ui.stylesheet", "url('file:config/Modele1.css')"); 
	Plan.addAttribute("ui.quality"); 
	Plan.addAttribute("ui.antialias"); 
	
	Plan.addEdge("startK", "start", "K", true );
		Plan.addEdge("KL", "K", "L", true ); 
	Plan.addEdge("LS", "L", "S", true ); 
	Plan.addEdge("SB_", "S", "B_", true ); 
	Plan.addEdge("B_C_", "B_", "C_", true ); 
	Plan.addEdge("C_D_", "C_", "D_", true ); 
	Plan.addEdge("LP", "L", "P", true ); 
	Plan.addEdge("PX", "P", "X", true ); 
	Plan.addEdge("XY", "X", "Y", true ); 
	Plan.addEdge("YD_", "Y", "D_", true ); 
	Plan.addEdge("ST", "S", "T", true ); 
	Plan.addEdge("TY", "T", "Y", true ); 
	Plan.addEdge("PT", "P", "T", true ); 
	Plan.addEdge("TC_", "T", "C_", true ); 
	Plan.addEdge("KR", "K", "R", true ); 
	Plan.addEdge("RS", "R", "S", true ); 
	//Plan.addEdge("RA_", "R", "A_", true ); 
	//Plan.addEdge("A_B_", "A_", "B_", true ); 
	
	Plan.getNode("start").setAttribute("ui.label", "start");
	Plan.getNode("K").setAttribute("ui.label", "K"); 
	Plan.getNode("L").setAttribute("ui.label", "L"); 
	Plan.getNode("S").setAttribute("ui.label", "S"); 
	Plan.getNode("B_").setAttribute("ui.label", "B_"); 
	Plan.getNode("C_").setAttribute("ui.label", "C_"); 
	Plan.getNode("D_").setAttribute("ui.label", "D_"); 
	Plan.getNode("P").setAttribute("ui.label", "P"); 
	Plan.getNode("X").setAttribute("ui.label", "X"); 
	Plan.getNode("Y").setAttribute("ui.label", "Y"); 
	Plan.getNode("T").setAttribute("ui.label", "T"); 
	Plan.getNode("R").setAttribute("ui.label", "R"); 
	//Plan.getNode("A_").setAttribute("ui.label", "A_"); 
	Plan.getEdge("KL").setAttribute("ui.label", "KL"); 
	Plan.getEdge("KL").addAttribute("ui.class", "Blue"); 
	Plan.getEdge("LS").setAttribute("ui.label", "LS"); 
	Plan.getEdge("LS").addAttribute("ui.class", "Blue"); 
	Plan.getEdge("SB_").setAttribute("ui.label", "SB_"); 
	Plan.getEdge("SB_").addAttribute("ui.class", "Blue"); 
	Plan.getEdge("B_C_").setAttribute("ui.label", "B_C_"); 
	Plan.getEdge("B_C_").addAttribute("ui.class", "Blue"); 
	Plan.getEdge("C_D_").setAttribute("ui.label", "C_D_"); 
	Plan.getEdge("C_D_").addAttribute("ui.class", "Blue"); 
	Plan.getEdge("LP").setAttribute("ui.label", "LP"); 
	Plan.getEdge("LP").addAttribute("ui.class", "Blue"); 
	Plan.getEdge("PX").setAttribute("ui.label", "PX"); 
	Plan.getEdge("PX").addAttribute("ui.class", "Blue"); 
	Plan.getEdge("XY").setAttribute("ui.label", "XY"); 
	Plan.getEdge("XY").addAttribute("ui.class", "Blue"); 
	Plan.getEdge("YD_").setAttribute("ui.label", "YD_"); 
	Plan.getEdge("YD_").addAttribute("ui.class", "Blue"); 
	Plan.getEdge("ST").setAttribute("ui.label", "ST"); 
	Plan.getEdge("ST").addAttribute("ui.class", "Blue"); 
	Plan.getEdge("TY").setAttribute("ui.label", "TY"); 
	Plan.getEdge("TY").addAttribute("ui.class", "Blue"); 
	Plan.getEdge("PT").setAttribute("ui.label", "PT"); 
	Plan.getEdge("PT").addAttribute("ui.class", "Blue"); 
	Plan.getEdge("TC_").setAttribute("ui.label", "TC_"); 
	Plan.getEdge("TC_").addAttribute("ui.class", "Blue"); 
	Plan.getEdge("KR").setAttribute("ui.label", "KR"); 
	Plan.getEdge("KR").addAttribute("ui.class", "Blue"); 
	Plan.getEdge("RS").setAttribute("ui.label", "RS"); 
	Plan.getEdge("RS").addAttribute("ui.class", "Blue"); 
	//Plan.getEdge("RA_").setAttribute("ui.label", "RA_"); 
	//Plan.getEdge("RA_").addAttribute("ui.class", "Blue"); 
	//Plan.getEdge("A_B_").setAttribute("ui.label", "A_B_"); 
	//Plan.getEdge("A_B_").addAttribute("ui.class", "Blue"); 
return Plan; 
} // fin de getPlan1003 



public Graph getPlan1004(String pName){ 
	Graph Plan = new SingleGraph("Plan1004"); 
	Plan.setStrict(false); 
		Plan.setAutoCreate( true ); 
		Plan.addAttribute("ui.stylesheet", "url('file:config/Modele1.css')"); 
	Plan.addAttribute("ui.quality"); 
	Plan.addAttribute("ui.antialias"); 
	
	Plan.addEdge("startK", "start", "K", true );
		Plan.addEdge("KL", "K", "L", true ); 
	Plan.addEdge("LS", "L", "S", true ); 
	Plan.addEdge("SB_", "S", "B_", true ); 
	Plan.addEdge("B_C_", "B_", "C_", true ); 
	Plan.addEdge("C_D_", "C_", "D_", true ); 
	Plan.addEdge("LP", "L", "P", true ); 
	Plan.addEdge("PX", "P", "X", true ); 
	Plan.addEdge("XY", "X", "Y", true ); 
	Plan.addEdge("YD_", "Y", "D_", true ); 
	Plan.addEdge("ST", "S", "T", true ); 
	Plan.addEdge("TY", "T", "Y", true ); 
	Plan.addEdge("PT", "P", "T", true ); 
	Plan.addEdge("TC_", "T", "C_", true ); 
	Plan.addEdge("KR", "K", "R", true ); 
	Plan.addEdge("RS", "R", "S", true ); 
	//Plan.addEdge("RA_", "R", "A_", true ); 
	//Plan.addEdge("A_B_", "A_", "B_", true ); 
	
	Plan.getNode("start").setAttribute("ui.label", "start");
	Plan.getNode("K").setAttribute("ui.label", "K"); 
	Plan.getNode("L").setAttribute("ui.label", "L"); 
	Plan.getNode("S").setAttribute("ui.label", "S"); 
	Plan.getNode("B_").setAttribute("ui.label", "B_"); 
	Plan.getNode("C_").setAttribute("ui.label", "C_"); 
	Plan.getNode("D_").setAttribute("ui.label", "D_"); 
	Plan.getNode("P").setAttribute("ui.label", "P"); 
	Plan.getNode("X").setAttribute("ui.label", "X"); 
	Plan.getNode("Y").setAttribute("ui.label", "Y"); 
	Plan.getNode("T").setAttribute("ui.label", "T"); 
	Plan.getNode("R").setAttribute("ui.label", "R"); 
	//Plan.getNode("A_").setAttribute("ui.label", "A_"); 
	Plan.getEdge("KL").setAttribute("ui.label", "KL"); 
	Plan.getEdge("KL").addAttribute("ui.class", "Blue"); 
	Plan.getEdge("LS").setAttribute("ui.label", "LS"); 
	Plan.getEdge("LS").addAttribute("ui.class", "Blue"); 
	Plan.getEdge("SB_").setAttribute("ui.label", "SB_"); 
	Plan.getEdge("SB_").addAttribute("ui.class", "Blue"); 
	Plan.getEdge("B_C_").setAttribute("ui.label", "B_C_"); 
	Plan.getEdge("B_C_").addAttribute("ui.class", "Blue"); 
	Plan.getEdge("C_D_").setAttribute("ui.label", "C_D_"); 
	Plan.getEdge("C_D_").addAttribute("ui.class", "Blue"); 
	Plan.getEdge("LP").setAttribute("ui.label", "LP"); 
	Plan.getEdge("LP").addAttribute("ui.class", "Blue"); 
	Plan.getEdge("PX").setAttribute("ui.label", "PX"); 
	Plan.getEdge("PX").addAttribute("ui.class", "Blue"); 
	Plan.getEdge("XY").setAttribute("ui.label", "XY"); 
	Plan.getEdge("XY").addAttribute("ui.class", "Blue"); 
	Plan.getEdge("YD_").setAttribute("ui.label", "YD_"); 
	Plan.getEdge("YD_").addAttribute("ui.class", "Blue"); 
	Plan.getEdge("ST").setAttribute("ui.label", "ST"); 
	Plan.getEdge("ST").addAttribute("ui.class", "Blue"); 
	Plan.getEdge("TY").setAttribute("ui.label", "TY"); 
	Plan.getEdge("TY").addAttribute("ui.class", "Blue"); 
	Plan.getEdge("PT").setAttribute("ui.label", "PT"); 
	Plan.getEdge("PT").addAttribute("ui.class", "Blue"); 
	Plan.getEdge("TC_").setAttribute("ui.label", "TC_"); 
	Plan.getEdge("TC_").addAttribute("ui.class", "Blue"); 
	Plan.getEdge("KR").setAttribute("ui.label", "KR"); 
	Plan.getEdge("KR").addAttribute("ui.class", "Blue"); 
	Plan.getEdge("RS").setAttribute("ui.label", "RS"); 
	Plan.getEdge("RS").addAttribute("ui.class", "Blue"); 
	//Plan.getEdge("RA_").setAttribute("ui.label", "RA_"); 
	//Plan.getEdge("RA_").addAttribute("ui.class", "Blue"); 
	//Plan.getEdge("A_B_").setAttribute("ui.label", "A_B_"); 
	//Plan.getEdge("A_B_").addAttribute("ui.class", "Blue"); 
return Plan; 
} // fin de getPlan1004 



/*
//------------------------------------------------------------------------------- 
public Graph getPlan1001(String pName){ 
	Graph Plan_1001 = new SingleGraph("Plan_1001"); 
		Plan_1001.setStrict(false); 
		Plan_1001.setAutoCreate( true ); 
		Plan_1001.addAttribute("ui.stylesheet", "url('file:config/Modele1.css')"); 
		Plan_1001.addAttribute("ui.quality"); 
		Plan_1001.addAttribute("ui.antialias"); 
		Plan_1001.addEdge("startJ", "start", "J", true );
		Plan_1001.addEdge("PT", "P", "T", true ); 
		Plan_1001.addEdge("TC_", "T", "C_", true ); 
		//Plan_1001.addEdge("ST", "S", "T", true ); 
		Plan_1001.addEdge("LS", "L", "S", true ); 
		Plan_1001.addEdge("SB_", "S", "B_", true ); 
		Plan_1001.addEdge("B_C_", "B_", "C_", true ); 
		Plan_1001.addEdge("KL", "K", "L", true ); 
		Plan_1001.addEdge("LP", "L", "P", true ); 
		Plan_1001.addEdge("JK", "J", "K", true ); 
		Plan_1001.addEdge("KR", "K", "R", true ); 
		Plan_1001.addEdge("RS", "R", "S", true ); 
		Plan_1001.getNode("start").setAttribute("ui.label", "start");
		Plan_1001.getNode("P").setAttribute("ui.label", "P"); 
		Plan_1001.getNode("T").setAttribute("ui.label", "T"); 
		Plan_1001.getNode("R").setAttribute("ui.label", "R"); 
		Plan_1001.getNode("L").setAttribute("ui.label", "L"); 
		Plan_1001.getNode("S").setAttribute("ui.label", "S"); 
		Plan_1001.getNode("B_").setAttribute("ui.label", "B_"); 
		Plan_1001.getNode("C_").setAttribute("ui.label", "C_"); 
		Plan_1001.getNode("K").setAttribute("ui.label", "K"); 
		Plan_1001.getNode("J").setAttribute("ui.label", "J"); 
		Plan_1001.getEdge("PT").setAttribute("ui.label", "PT"); 
		Plan_1001.getEdge("PT").addAttribute("ui.class", "Green"); 
		Plan_1001.getEdge("TC_").setAttribute("ui.label", "TC_"); 
		Plan_1001.getEdge("TC_").addAttribute("ui.class", "Green"); 
		//Plan_1001.getEdge("ST").setAttribute("ui.label", "ST"); 
		//Plan_1001.getEdge("ST").addAttribute("ui.class", "Green"); 
		Plan_1001.getEdge("LS").setAttribute("ui.label", "LS"); 
		Plan_1001.getEdge("LS").addAttribute("ui.class", "Green"); 
		Plan_1001.getEdge("SB_").setAttribute("ui.label", "SB_"); 
		Plan_1001.getEdge("SB_").addAttribute("ui.class", "Green"); 
		Plan_1001.getEdge("B_C_").setAttribute("ui.label", "B_C_"); 
		Plan_1001.getEdge("B_C_").addAttribute("ui.class", "Green"); 
		Plan_1001.getEdge("KL").setAttribute("ui.label", "KL"); 
		Plan_1001.getEdge("KL").addAttribute("ui.class", "Green"); 
		Plan_1001.getEdge("LP").setAttribute("ui.label", "LP"); 
		Plan_1001.getEdge("LP").addAttribute("ui.class", "Green"); 
		Plan_1001.getEdge("JK").setAttribute("ui.label", "JK"); 
		Plan_1001.getEdge("JK").addAttribute("ui.class", "Green"); 
		Plan_1001.getEdge("KR").setAttribute("ui.label", "KR"); 
		Plan_1001.getEdge("KR").addAttribute("ui.class", "Green"); 
		Plan_1001.getEdge("RS").setAttribute("ui.label", "RS"); 
		Plan_1001.getEdge("RS").addAttribute("ui.class", "Green"); 
	return Plan_1001; 
} // fin de getPlan19 

// ------------------------------------------------------------------------------- 

//------------------------------------------------------------------------------- 
public Graph getPlan1002(String pName){ 
	Graph Plan_1002 = new SingleGraph("Plan_1002"); 
		Plan_1002.setStrict(false); 
		Plan_1002.setAutoCreate( true ); 
		Plan_1002.addAttribute("ui.stylesheet", "url('file:config/Modele1.css')"); 
		Plan_1002.addAttribute("ui.quality"); 
		Plan_1002.addAttribute("ui.antialias"); 
		Plan_1002.addEdge("startC", "start", "D", true );
		Plan_1002.addEdge("A_B_", "A_", "B_", true ); 
		Plan_1002.addEdge("FI", "F", "I", true ); 
		Plan_1002.addEdge("KR", "K", "R", true ); 
		Plan_1002.addEdge("RS", "R", "S", true ); 
		Plan_1002.addEdge("SB_", "S", "B_", true ); 
		Plan_1002.addEdge("IL", "I", "L", true ); 
		Plan_1002.addEdge("LS", "L", "S", true ); 
		//Plan_1002.addEdge("CD", "C", "D", true ); 
		Plan_1002.addEdge("DF", "D", "F", true ); 
		Plan_1002.addEdge("FK", "F", "K", true ); 
		Plan_1002.addEdge("RA_", "R", "A_", true ); 
		Plan_1002.getNode("start").setAttribute("ui.label", "start");
		Plan_1002.getNode("A_").setAttribute("ui.label", "A_"); 
		Plan_1002.getNode("L").setAttribute("ui.label", "L"); 
		Plan_1002.getNode("K").setAttribute("ui.label", "K"); 
		Plan_1002.getNode("R").setAttribute("ui.label", "R"); 
		Plan_1002.getNode("S").setAttribute("ui.label", "S"); 
		Plan_1002.getNode("B_").setAttribute("ui.label", "B_"); 
		Plan_1002.getNode("F").setAttribute("ui.label", "F"); 
		Plan_1002.getNode("I").setAttribute("ui.label", "I"); 
		//Plan_1002.getNode("C").setAttribute("ui.label", "C"); 
		Plan_1002.getNode("D").setAttribute("ui.label", "D"); 
		Plan_1002.getEdge("A_B_").setAttribute("ui.label", "A_B_"); 
		Plan_1002.getEdge("A_B_").addAttribute("ui.class", "Chocolate"); 
		Plan_1002.getEdge("FI").setAttribute("ui.label", "FI"); 
		Plan_1002.getEdge("FI").addAttribute("ui.class", "Chocolate"); 
		Plan_1002.getEdge("KR").setAttribute("ui.label", "KR"); 
		Plan_1002.getEdge("KR").addAttribute("ui.class", "Chocolate"); 
		Plan_1002.getEdge("RS").setAttribute("ui.label", "RS"); 
		Plan_1002.getEdge("RS").addAttribute("ui.class", "Chocolate"); 
		Plan_1002.getEdge("SB_").setAttribute("ui.label", "SB_"); 
		Plan_1002.getEdge("SB_").addAttribute("ui.class", "Chocolate"); 
		Plan_1002.getEdge("IL").setAttribute("ui.label", "IL"); 
		Plan_1002.getEdge("IL").addAttribute("ui.class", "Chocolate"); 
		Plan_1002.getEdge("LS").setAttribute("ui.label", "LS"); 
		Plan_1002.getEdge("LS").addAttribute("ui.class", "Chocolate"); 
		//Plan_1002.getEdge("CD").setAttribute("ui.label", "CD"); 
		//Plan_1002.getEdge("CD").addAttribute("ui.class", "Chocolate"); 
		Plan_1002.getEdge("DF").setAttribute("ui.label", "DF"); 
		Plan_1002.getEdge("DF").addAttribute("ui.class", "Chocolate"); 
		Plan_1002.getEdge("FK").setAttribute("ui.label", "FK"); 
		Plan_1002.getEdge("FK").addAttribute("ui.class", "Chocolate"); 
		Plan_1002.getEdge("RA_").setAttribute("ui.label", "RA_"); 
		Plan_1002.getEdge("RA_").addAttribute("ui.class", "Chocolate"); 
	return Plan_1002; 
} // fin de getPlan1002 
//------------------------------------------------------------------------------- 



//------------------------------------------------------------------------------- 
public Graph getPlan1003(String pName){ 
	Graph Plan_1003 = new SingleGraph("Plan_1003"); 
		Plan_1003.setStrict(false); 
		Plan_1003.setAutoCreate( true ); 
		Plan_1003.addAttribute("ui.stylesheet", "url('file:config/Modele1.css')"); 
		Plan_1003.addAttribute("ui.quality"); 
		Plan_1003.addAttribute("ui.antialias"); 
		
		Plan_1003.addEdge("startD", "start", "D", true );
		Plan_1003.addEdge("DH", "D", "H", true ); 
		Plan_1003.addEdge("HI", "H", "I", true ); 
		Plan_1003.addEdge("IL", "I", "L", true ); 
		Plan_1003.addEdge("LS", "L", "S", true ); 
	//	Plan_1003.addEdge("ST", "S", "T", true ); 
		Plan_1003.addEdge("TC_", "T", "C_", true ); 
		//Plan_1003.addEdge("C_D_", "C_", "D_", true ); 
		Plan_1003.addEdge("B_C_", "B_", "C_", true ); 
		Plan_1003.addEdge("KL", "K", "L", true ); 
		Plan_1003.addEdge("LP", "L", "P", true ); 
		Plan_1003.addEdge("PT", "P", "T", true ); 
		Plan_1003.addEdge("DF", "D", "F", true ); 
		Plan_1003.addEdge("FK", "F", "K", true ); 
		Plan_1003.addEdge("SB_", "S", "B_", true ); 
		Plan_1003.getNode("start").setAttribute("ui.label", "start");
		Plan_1003.getNode("D").setAttribute("ui.label", "D"); 
		Plan_1003.getNode("H").setAttribute("ui.label", "H"); 
		Plan_1003.getNode("I").setAttribute("ui.label", "I"); 
		Plan_1003.getNode("L").setAttribute("ui.label", "L"); 
		Plan_1003.getNode("S").setAttribute("ui.label", "S"); 
		Plan_1003.getNode("T").setAttribute("ui.label", "T"); 
		Plan_1003.getNode("C_").setAttribute("ui.label", "C_"); 
		//Plan_1003.getNode("D_").setAttribute("ui.label", "D_"); 
		Plan_1003.getNode("B_").setAttribute("ui.label", "B_"); 
		Plan_1003.getNode("K").setAttribute("ui.label", "K"); 
		Plan_1003.getNode("P").setAttribute("ui.label", "P"); 
		Plan_1003.getNode("F").setAttribute("ui.label", "F"); 
		Plan_1003.getEdge("DH").setAttribute("ui.label", "DH"); 
		Plan_1003.getEdge("DH").addAttribute("ui.class", "Purple"); 
		Plan_1003.getEdge("HI").setAttribute("ui.label", "HI"); 
		Plan_1003.getEdge("HI").addAttribute("ui.class", "Purple"); 
		Plan_1003.getEdge("IL").setAttribute("ui.label", "IL"); 
		Plan_1003.getEdge("IL").addAttribute("ui.class", "Purple"); 
		Plan_1003.getEdge("LS").setAttribute("ui.label", "LS"); 
		Plan_1003.getEdge("LS").addAttribute("ui.class", "Purple"); 
		//Plan_1003.getEdge("ST").setAttribute("ui.label", "ST"); 
		//Plan_1003.getEdge("ST").addAttribute("ui.class", "Purple"); 
		Plan_1003.getEdge("TC_").setAttribute("ui.label", "TC_"); 
		Plan_1003.getEdge("TC_").addAttribute("ui.class", "Purple"); 
		//Plan_1003.getEdge("C_D_").setAttribute("ui.label", "C_D_"); 
		//Plan_1003.getEdge("C_D_").addAttribute("ui.class", "Purple"); 
		Plan_1003.getEdge("B_C_").setAttribute("ui.label", "B_C_"); 
		Plan_1003.getEdge("B_C_").addAttribute("ui.class", "Purple"); 
		Plan_1003.getEdge("KL").setAttribute("ui.label", "KL"); 
		Plan_1003.getEdge("KL").addAttribute("ui.class", "Purple"); 
		Plan_1003.getEdge("LP").setAttribute("ui.label", "LP"); 
		Plan_1003.getEdge("LP").addAttribute("ui.class", "Purple"); 
		Plan_1003.getEdge("PT").setAttribute("ui.label", "PT"); 
		Plan_1003.getEdge("PT").addAttribute("ui.class", "Purple"); 
		Plan_1003.getEdge("DF").setAttribute("ui.label", "DF"); 
		Plan_1003.getEdge("DF").addAttribute("ui.class", "Purple"); 
		Plan_1003.getEdge("FK").setAttribute("ui.label", "FK"); 
		Plan_1003.getEdge("FK").addAttribute("ui.class", "Purple"); 
		Plan_1003.getEdge("SB_").setAttribute("ui.label", "SB_"); 
		Plan_1003.getEdge("SB_").addAttribute("ui.class", "Purple"); 
	return Plan_1003; 
} // fin de getPlan9 


public Graph getPlan1004(String pName){ 
	Graph Plan_1004 = new SingleGraph("Plan_1004"); 
		Plan_1004.setStrict(false); 
		Plan_1004.setAutoCreate( true ); 
		Plan_1004.addAttribute("ui.stylesheet", "url('file:config/Modele1.css')"); 
		Plan_1004.addAttribute("ui.quality"); 
		Plan_1004.addAttribute("ui.antialias");
		
		Plan_1004.addEdge("startE", "start", "J", true );
		//Plan_1004.addEdge("C_D_", "C_", "D_", true ); 
		//Plan_1004.addEdge("EJ", "E", "J", true ); 
		Plan_1004.addEdge("JK", "J", "K", true ); 
		Plan_1004.addEdge("KL", "K", "L", true ); 
		//Plan_1004.addEdge("B_C_", "B_", "C_", true ); 
		Plan_1004.addEdge("SB_", "S", "B_", true ); 
		Plan_1004.addEdge("LS", "L", "S", true ); 
		Plan_1004.addEdge("A_B_", "A_", "B_", true ); 
		Plan_1004.addEdge("RA_", "R", "A_", true ); 
		Plan_1004.addEdge("RS", "R", "S", true ); 
		Plan_1004.addEdge("KR", "K", "R", true ); 
		
		Plan_1004.getNode("start").setAttribute("ui.label", "start");
		//Plan_1004.getNode("D_").setAttribute("ui.label", "D_"); 
		Plan_1004.getNode("J").setAttribute("ui.label", "J"); 
		//Plan_1004.getNode("E").setAttribute("ui.label", "E"); 
		Plan_1004.getNode("L").setAttribute("ui.label", "L"); 
		//Plan_1004.getNode("C_").setAttribute("ui.label", "C_"); 
		Plan_1004.getNode("S").setAttribute("ui.label", "S"); 
		Plan_1004.getNode("B_").setAttribute("ui.label", "B_"); 
		Plan_1004.getNode("A_").setAttribute("ui.label", "A_"); 
		Plan_1004.getNode("R").setAttribute("ui.label", "R"); 
		Plan_1004.getNode("K").setAttribute("ui.label", "K"); 
		//Plan_1004.getEdge("C_D_").setAttribute("ui.label", "C_D_"); 
		//Plan_1004.getEdge("C_D_").addAttribute("ui.class", "Plum"); 
		//Plan_1004.getEdge("EJ").setAttribute("ui.label", "EJ"); 
		//Plan_1004.getEdge("EJ").addAttribute("ui.class", "Plum"); 
		Plan_1004.getEdge("JK").setAttribute("ui.label", "JK"); 
		Plan_1004.getEdge("JK").addAttribute("ui.class", "Plum"); 
		Plan_1004.getEdge("KL").setAttribute("ui.label", "KL"); 
		Plan_1004.getEdge("KL").addAttribute("ui.class", "Plum"); 
		//Plan_1004.getEdge("B_C_").setAttribute("ui.label", "B_C_"); 
		//Plan_1004.getEdge("B_C_").addAttribute("ui.class", "Plum"); 
		Plan_1004.getEdge("SB_").setAttribute("ui.label", "SB_"); 
		Plan_1004.getEdge("SB_").addAttribute("ui.class", "Plum");
		Plan_1004.getEdge("RS").setAttribute("ui.label", "RS"); 
		Plan_1004.getEdge("RS").addAttribute("ui.class", "Plum");
		Plan_1004.getEdge("LS").setAttribute("ui.label", "LS"); 
		Plan_1004.getEdge("LS").addAttribute("ui.class", "Plum"); 
		Plan_1004.getEdge("A_B_").setAttribute("ui.label", "A_B_"); 
		Plan_1004.getEdge("A_B_").addAttribute("ui.class", "Plum"); 
		Plan_1004.getEdge("RA_").setAttribute("ui.label", "RA_"); 
		Plan_1004.getEdge("RA_").addAttribute("ui.class", "Plum"); 
		Plan_1004.getEdge("KR").setAttribute("ui.label", "KR"); 
		Plan_1004.getEdge("KR").addAttribute("ui.class", "Plum"); 
	return Plan_1004; 
} // fin de getPlan1004

*/

// ------------------------------------------------------------------------------- 
 // ------------------------------------------------------------------------------- 

public Graph getPlan101(String pName){ 
		Graph Plan = new SingleGraph("Plan_101"); 
		Plan.setStrict(false); 
		Plan.setAutoCreate( true ); 
		Plan.addAttribute("ui.stylesheet", "url('file:config/Modele1.css')"); 
		Plan.addAttribute("ui.quality"); 
		Plan.addAttribute("ui.antialias");
		
		Plan.addEdge("startH", "start", "H", true );
		Plan.addEdge("HK", "H", "K", true ); 
		Plan.addEdge("HJ", "H", "J", true ); 
		Plan.addEdge("KL", "K", "L", true );
		Plan.addEdge("LM", "L", "M", true ); 
		Plan.addEdge("JM", "J", "M", true ); 
		
		Plan.getNode("start").setAttribute("ui.label", "start");
		Plan.getNode("H").setAttribute("ui.label", "H");
		Plan.getNode("J").setAttribute("ui.label", "J");
		Plan.getNode("K").setAttribute("ui.label", "K");
		Plan.getNode("L").setAttribute("ui.label", "L");
		Plan.getNode("M").setAttribute("ui.label", "M");
		
		Plan.getEdge("HK").setAttribute("ui.label", "HK"); 
		Plan.getEdge("KL").setAttribute("ui.label", "KL");
		Plan.getEdge("LM").setAttribute("ui.label", "LM");
		Plan.getEdge("HJ").setAttribute("ui.label", "HJ");
		Plan.getEdge("JM").setAttribute("ui.label", "JM"); 
	return Plan; 
} // fin de getPlan101

public Graph getPlan102(String pName){ 
	Graph Plan = new SingleGraph("Plan_102"); 
	Plan.setStrict(false); 
	Plan.setAutoCreate( true ); 
	Plan.addAttribute("ui.stylesheet", "url('file:config/Modele1.css')"); 
	Plan.addAttribute("ui.quality"); 
	Plan.addAttribute("ui.antialias");
	
	Plan.addEdge("startW", "start", "W", true );
	Plan.addEdge("WH", "W", "H", true ); 
	Plan.addEdge("HV", "H", "V", true ); 
	Plan.addEdge("HJ", "H", "J", true ); 
	Plan.addEdge("JV", "J", "V", true ); 
	Plan.addEdge("JO", "J", "O", true ); 
	Plan.addEdge("VI", "V", "I", true ); 
	Plan.addEdge("OI", "O", "I", true ); 
	
	
	Plan.getNode("start").setAttribute("ui.label", "start");
	Plan.getNode("W").setAttribute("ui.label", "W");
	Plan.getNode("H").setAttribute("ui.label", "H");
	Plan.getNode("V").setAttribute("ui.label", "V");
	Plan.getNode("J").setAttribute("ui.label", "J");
	Plan.getNode("O").setAttribute("ui.label", "O");
	Plan.getNode("I").setAttribute("ui.label", "I");

	Plan.getEdge("WH").setAttribute("ui.label", "WH");
	Plan.getEdge("HV").setAttribute("ui.label", "HV");
	Plan.getEdge("HJ").setAttribute("ui.label", "HJ");
	Plan.getEdge("JV").setAttribute("ui.label", "JV");
	Plan.getEdge("JO").setAttribute("ui.label", "JO");
	Plan.getEdge("VI").setAttribute("ui.label", "VI");
	Plan.getEdge("OI").setAttribute("ui.label", "OI");
	
return Plan; 
} // fin de getPlan102

public Graph getPlan103(String pName){ 
	Graph Plan = new SingleGraph("Plan_103"); 
	Plan.setStrict(false); 
	Plan.setAutoCreate( true ); 
	Plan.addAttribute("ui.stylesheet", "url('file:config/Modele1.css')"); 
	Plan.addAttribute("ui.quality"); 
	Plan.addAttribute("ui.antialias");
	
	Plan.addEdge("startE", "start", "E", true );
	Plan.addEdge("EC", "E", "C", true ); 
	Plan.addEdge("CD", "C", "D", true ); 
	Plan.addEdge("CK", "C", "K", true ); 
	Plan.addEdge("DK", "D", "K", true ); 
	Plan.addEdge("KL", "K", "L", true ); 
	Plan.addEdge("LG", "L", "G", true ); 
	//Plan.addEdge("EF", "E", "F", true );
	//Plan.addEdge("FG", "F", "G", true );
	Plan.addEdge("EG", "E", "G", true );
	
	Plan.getNode("start").setAttribute("ui.label", "start");
	Plan.getNode("E").setAttribute("ui.label", "E"); 
	Plan.getNode("C").setAttribute("ui.label", "C"); 
	Plan.getNode("D").setAttribute("ui.label", "D"); 
	Plan.getNode("K").setAttribute("ui.label", "K"); 
	Plan.getNode("L").setAttribute("ui.label", "L");
	//Plan.getNode("F").setAttribute("ui.label", "F"); 
	Plan.getNode("G").setAttribute("ui.label", "G"); 
	
	
	Plan.getEdge("EC").setAttribute("ui.label", "EC"); 
	Plan.getEdge("CD").setAttribute("ui.label", "CD"); 
	Plan.getEdge("CK").setAttribute("ui.label", "CK"); 
	Plan.getEdge("DK").setAttribute("ui.label", "DK"); 
	Plan.getEdge("KL").setAttribute("ui.label", "KL"); 
	Plan.getEdge("LG").setAttribute("ui.label", "LG");
	
	//Version 2 E-->F-->G : E-->G
	//Plan.getEdge("EF").setAttribute("ui.label", "EF");
	//Plan.getEdge("FG").setAttribute("ui.label", "FG"); 
	Plan.getEdge("EG").setAttribute("ui.label", "EG");
	
return Plan; 
} // fin de getPlan103

public Graph getPlan104(String pName){ 
	Graph Plan = new SingleGraph("Plan_104"); 
	Plan.setStrict(false); 
	Plan.setAutoCreate( true ); 
	Plan.addAttribute("ui.stylesheet", "url('file:config/Modele1.css')"); 
	Plan.addAttribute("ui.quality"); 
	Plan.addAttribute("ui.antialias");
	
	Plan.addEdge("startL", "start", "L", true );
	Plan.addEdge("QR", "Q", "R", true );
	
	// Version 2  L-->N-->Q ===>>   L-->Q
	//Plan.addEdge("LN", "L", "N", true ); 
	//Plan.addEdge("NQ", "N", "Q", true ); 
	Plan.addEdge("LQ", "L", "Q", true ); 
		
	Plan.getNode("start").setAttribute("ui.label", "start");
	Plan.getNode("L").setAttribute("ui.label", "L"); 
	//Plan.getNode("N").setAttribute("ui.label", "N");
	Plan.getNode("Q").setAttribute("ui.label", "Q");
	Plan.getNode("R").setAttribute("ui.label", "R");
	
	//Plan.getEdge("LN").setAttribute("ui.label", "LN");
	//Plan.getEdge("NQ").setAttribute("ui.label", "NQ");
	Plan.getEdge("LQ").setAttribute("ui.label", "LQ");
	Plan.getEdge("QR").setAttribute("ui.label", "QR");
	
return Plan; 
} // fin de getPlan104

public Graph getPlan105(String pName){ 
	Graph Plan = new SingleGraph("Plan_105"); 
	Plan.setStrict(false); 
	Plan.setAutoCreate( true ); 
	Plan.addAttribute("ui.stylesheet", "url('file:config/Modele1.css')"); 
	Plan.addAttribute("ui.quality"); 
	Plan.addAttribute("ui.antialias");
	
	Plan.addEdge("startJ", "start", "J", true );
	Plan.addEdge("JM", "J", "M", true ); 
	Plan.addEdge("JO", "J", "O", true ); 
	Plan.addEdge("MP", "M", "P", true ); 
	Plan.addEdge("MR", "M", "R", true ); 
	Plan.addEdge("PQ", "P", "Q", true ); 
	Plan.addEdge("QR", "Q", "R", true ); 
	Plan.addEdge("OR", "O", "R", true ); 
	Plan.addEdge("RT", "R", "T", true ); 
	
	Plan.getNode("start").setAttribute("ui.label", "start");
	Plan.getNode("J").setAttribute("ui.label", "J"); 
	Plan.getNode("O").setAttribute("ui.label", "O");
	Plan.getNode("P").setAttribute("ui.label", "P");
	Plan.getNode("M").setAttribute("ui.label", "M");
	Plan.getNode("Q").setAttribute("ui.label", "Q");
	Plan.getNode("R").setAttribute("ui.label", "R");
	Plan.getNode("T").setAttribute("ui.label", "T");
	
	
	Plan.getEdge("JM").setAttribute("ui.label", "JM");
	Plan.getEdge("JO").setAttribute("ui.label", "JO");
	Plan.getEdge("MP").setAttribute("ui.label", "MP");
	Plan.getEdge("MR").setAttribute("ui.label", "MR");
	Plan.getEdge("PQ").setAttribute("ui.label", "PQ");
	Plan.getEdge("QR").setAttribute("ui.label", "QR");
	Plan.getEdge("OR").setAttribute("ui.label", "OR");
	Plan.getEdge("RT").setAttribute("ui.label", "RT");
	
return Plan; 
} // fin de getPlan105

public Graph getPlan106(String pName){ 
	Graph Plan = new SingleGraph("Plan_106"); 
	Plan.setStrict(false); 
	Plan.setAutoCreate( true ); 
	Plan.addAttribute("ui.stylesheet", "url('file:config/Modele1.css')"); 
	Plan.addAttribute("ui.quality"); 
	Plan.addAttribute("ui.antialias");
	
	Plan.addEdge("startA", "start", "A", true );
	 
	Plan.addEdge("HV", "H", "V", true ); 
	
	Plan.addEdge("WH", "W", "H", true );
	Plan.addEdge("DH", "D", "H", true ); 
	Plan.addEdge("CD", "C", "D", true ); 
	Plan.addEdge("AC", "A", "C", true ); 
	Plan.addEdge("AW", "A", "W", true ); 
	
	// Version 2  A-->B-->V ===>>   A-->V
	//Plan.addEdge("BV", "B", "V", true ); 
	//Plan.addEdge("AB", "A", "B", true );
	Plan.addEdge("AV", "A", "V", true );
	
	Plan.getNode("start").setAttribute("ui.label", "start");
	Plan.getNode("A").setAttribute("ui.label", "A"); 
	//Plan.getNode("B").setAttribute("ui.label", "B");
	Plan.getNode("W").setAttribute("ui.label", "W");
	Plan.getNode("C").setAttribute("ui.label", "C");
	Plan.getNode("D").setAttribute("ui.label", "D");
	Plan.getNode("H").setAttribute("ui.label", "H");
	Plan.getNode("V").setAttribute("ui.label", "V");
	
	Plan.getEdge("AC").setAttribute("ui.label", "AC"); 
	Plan.getEdge("AW").setAttribute("ui.label", "AW");
	Plan.getEdge("CD").setAttribute("ui.label", "CD");
	Plan.getEdge("DH").setAttribute("ui.label", "DH");
	Plan.getEdge("HV").setAttribute("ui.label", "HV");
	Plan.getEdge("WH").setAttribute("ui.label", "WH");
	
	
	// Version 2  A-->B-->V ===>>   A-->V
	//Plan.getEdge("AB").setAttribute("ui.label", "AB");
	//Plan.getEdge("BV").setAttribute("ui.label", "BV");
	Plan.getEdge("AV").setAttribute("ui.label", "AV");
	
return Plan; 
} // fin de getPlan106






public Graph getPlan2001(String pName){ 
	Graph Plan = new SingleGraph("Plan2001"); 
	Plan.setStrict(false); 
	Plan.setAutoCreate( true ); 
	Plan.addAttribute("ui.stylesheet", "url('file:config/Modele1.css')"); 
	Plan.addAttribute("ui.quality"); 
	Plan.addAttribute("ui.antialias"); 
	
	Plan.addEdge("startA", "start", "A", true );
	Plan.addEdge("AB", "A", "B", true ); 
	Plan.addEdge("AC", "A", "C", true ); 
	Plan.addEdge("BD", "B", "D", true ); 
	Plan.addEdge("BE", "B", "E", true ); 
	Plan.addEdge("CE", "C", "E", true ); 
	Plan.addEdge("CF", "C", "F", true ); 
	Plan.addEdge("DG", "D", "G", true ); 
	Plan.addEdge("EG", "E", "G", true ); 
	Plan.addEdge("EH", "E", "H", true ); 
	Plan.addEdge("CF", "C", "F", true ); 
	Plan.addEdge("FH", "F", "H", true ); 
	Plan.addEdge("HI", "H", "I", true ); 
	Plan.addEdge("GI", "G", "I", true ); ; 

		
	Plan.getNode("start").setAttribute("ui.label", "start");
	Plan.getNode("A").setAttribute("ui.label", "A"); 
	Plan.getNode("B").setAttribute("ui.label", "B"); 
	Plan.getNode("C").setAttribute("ui.label", "C"); 
	Plan.getNode("D").setAttribute("ui.label", "D"); 
	Plan.getNode("E").setAttribute("ui.label", "E"); 
	Plan.getNode("F").setAttribute("ui.label", "F"); 
	Plan.getNode("G").setAttribute("ui.label", "G"); 
	Plan.getNode("H").setAttribute("ui.label", "H"); 
	Plan.getNode("I").setAttribute("ui.label", "I"); 
	
	
	Plan.getEdge("AB").setAttribute("ui.label", "AB"); 
	Plan.getEdge("AB").addAttribute("ui.class", "Blue"); 
	Plan.getEdge("AC").setAttribute("ui.label", "AC"); 
	Plan.getEdge("AC").addAttribute("ui.class", "Blue"); 
	Plan.getEdge("BD").setAttribute("ui.label", "BD"); 
	Plan.getEdge("BD").addAttribute("ui.class", "Blue"); 
	Plan.getEdge("BE").setAttribute("ui.label", "BE"); 
	Plan.getEdge("BE").addAttribute("ui.class", "Blue"); 
	Plan.getEdge("CE").setAttribute("ui.label", "CE"); 
	Plan.getEdge("CE").addAttribute("ui.class", "Blue"); 
	Plan.getEdge("CF").setAttribute("ui.label", "CF"); 
	Plan.getEdge("CF").addAttribute("ui.class", "Blue"); 
	Plan.getEdge("DG").setAttribute("ui.label", "DG"); 
	Plan.getEdge("DG").addAttribute("ui.class", "Blue"); 
	Plan.getEdge("EG").setAttribute("ui.label", "EG"); 
	Plan.getEdge("EG").addAttribute("ui.class", "Blue"); 
	Plan.getEdge("EH").setAttribute("ui.label", "EH"); 
	Plan.getEdge("EH").addAttribute("ui.class", "Blue"); 
	Plan.getEdge("CF").setAttribute("ui.label", "CF"); 
	Plan.getEdge("CF").addAttribute("ui.class", "Blue"); 
	Plan.getEdge("FH").setAttribute("ui.label", "FH"); 
	Plan.getEdge("FH").addAttribute("ui.class", "Blue"); 
	Plan.getEdge("HI").setAttribute("ui.label", "HI"); 
	Plan.getEdge("HI").addAttribute("ui.class", "Blue"); 
	Plan.getEdge("GI").setAttribute("ui.label", "GI"); 
	Plan.getEdge("GI").addAttribute("ui.class", "Blue"); 
	
return Plan; 
} // fin de getPlan2001 



public Graph getPlan2002(String pName){ 
	Graph Plan = new SingleGraph("Plan2002"); 
	Plan.setStrict(false); 
	Plan.setAutoCreate( true ); 
	Plan.addAttribute("ui.stylesheet", "url('file:config/Modele1.css')"); 
	Plan.addAttribute("ui.quality"); 
	Plan.addAttribute("ui.antialias"); 
	
	Plan.addEdge("startA", "start", "A", true );
	Plan.addEdge("AB", "A", "B", true ); 
	Plan.addEdge("AC", "A", "C", true ); 
	Plan.addEdge("BD", "B", "D", true ); 
	Plan.addEdge("BE", "B", "E", true ); 
	Plan.addEdge("CE", "C", "E", true ); 
	Plan.addEdge("CF", "C", "F", true ); 
	Plan.addEdge("DG", "D", "G", true ); 
	Plan.addEdge("EG", "E", "G", true ); 
	Plan.addEdge("EH", "E", "H", true ); 
	Plan.addEdge("CF", "C", "F", true ); 
	Plan.addEdge("FH", "F", "H", true ); 
	Plan.addEdge("HI", "H", "I", true ); 
	Plan.addEdge("GI", "G", "I", true ); ; 

		
	Plan.getNode("start").setAttribute("ui.label", "start");
	Plan.getNode("A").setAttribute("ui.label", "A"); 
	Plan.getNode("B").setAttribute("ui.label", "B"); 
	Plan.getNode("C").setAttribute("ui.label", "C"); 
	Plan.getNode("D").setAttribute("ui.label", "D"); 
	Plan.getNode("E").setAttribute("ui.label", "E"); 
	Plan.getNode("F").setAttribute("ui.label", "F"); 
	Plan.getNode("G").setAttribute("ui.label", "G"); 
	Plan.getNode("H").setAttribute("ui.label", "H"); 
	Plan.getNode("I").setAttribute("ui.label", "I"); 
	
	
	Plan.getEdge("AB").setAttribute("ui.label", "AB"); 
	Plan.getEdge("AB").addAttribute("ui.class", "Blue"); 
	Plan.getEdge("AC").setAttribute("ui.label", "AC"); 
	Plan.getEdge("AC").addAttribute("ui.class", "Blue"); 
	Plan.getEdge("BD").setAttribute("ui.label", "BD"); 
	Plan.getEdge("BD").addAttribute("ui.class", "Blue"); 
	Plan.getEdge("BE").setAttribute("ui.label", "BE"); 
	Plan.getEdge("BE").addAttribute("ui.class", "Blue"); 
	Plan.getEdge("CE").setAttribute("ui.label", "CE"); 
	Plan.getEdge("CE").addAttribute("ui.class", "Blue"); 
	Plan.getEdge("CF").setAttribute("ui.label", "CF"); 
	Plan.getEdge("CF").addAttribute("ui.class", "Blue"); 
	Plan.getEdge("DG").setAttribute("ui.label", "DG"); 
	Plan.getEdge("DG").addAttribute("ui.class", "Blue"); 
	Plan.getEdge("EG").setAttribute("ui.label", "EG"); 
	Plan.getEdge("EG").addAttribute("ui.class", "Blue"); 
	Plan.getEdge("EH").setAttribute("ui.label", "EH"); 
	Plan.getEdge("EH").addAttribute("ui.class", "Blue"); 
	Plan.getEdge("CF").setAttribute("ui.label", "CF"); 
	Plan.getEdge("CF").addAttribute("ui.class", "Blue"); 
	Plan.getEdge("FH").setAttribute("ui.label", "FH"); 
	Plan.getEdge("FH").addAttribute("ui.class", "Blue"); 
	Plan.getEdge("HI").setAttribute("ui.label", "HI"); 
	Plan.getEdge("HI").addAttribute("ui.class", "Blue"); 
	Plan.getEdge("GI").setAttribute("ui.label", "GI"); 
	Plan.getEdge("GI").addAttribute("ui.class", "Blue"); 
return Plan; 
} // fin de getPlan2002 



public Graph getPlan2003(String pName){ 
	Graph Plan = new SingleGraph("Plan2003"); 
	Plan.setStrict(false); 
	Plan.setAutoCreate( true ); 
	Plan.addAttribute("ui.stylesheet", "url('file:config/Modele1.css')"); 
	Plan.addAttribute("ui.quality"); 
	Plan.addAttribute("ui.antialias"); 
	
	Plan.addEdge("startA", "start", "A", true );
	Plan.addEdge("AB", "A", "B", true ); 
	Plan.addEdge("AC", "A", "C", true ); 
	Plan.addEdge("BD", "B", "D", true ); 
	Plan.addEdge("BE", "B", "E", true ); 
	Plan.addEdge("CE", "C", "E", true ); 
	Plan.addEdge("CF", "C", "F", true ); 
	Plan.addEdge("DG", "D", "G", true ); 
	Plan.addEdge("EG", "E", "G", true ); 
	Plan.addEdge("EH", "E", "H", true ); 
	Plan.addEdge("CF", "C", "F", true ); 
	Plan.addEdge("FH", "F", "H", true ); 
	Plan.addEdge("HI", "H", "I", true ); 
	Plan.addEdge("GI", "G", "I", true ); ; 

		
	Plan.getNode("start").setAttribute("ui.label", "start");
	Plan.getNode("A").setAttribute("ui.label", "A"); 
	Plan.getNode("B").setAttribute("ui.label", "B"); 
	Plan.getNode("C").setAttribute("ui.label", "C"); 
	Plan.getNode("D").setAttribute("ui.label", "D"); 
	Plan.getNode("E").setAttribute("ui.label", "E"); 
	Plan.getNode("F").setAttribute("ui.label", "F"); 
	Plan.getNode("G").setAttribute("ui.label", "G"); 
	Plan.getNode("H").setAttribute("ui.label", "H"); 
	Plan.getNode("I").setAttribute("ui.label", "I"); 
	
	
	Plan.getEdge("AB").setAttribute("ui.label", "AB"); 
	Plan.getEdge("AB").addAttribute("ui.class", "Blue"); 
	Plan.getEdge("AC").setAttribute("ui.label", "AC"); 
	Plan.getEdge("AC").addAttribute("ui.class", "Blue"); 
	Plan.getEdge("BD").setAttribute("ui.label", "BD"); 
	Plan.getEdge("BD").addAttribute("ui.class", "Blue"); 
	Plan.getEdge("BE").setAttribute("ui.label", "BE"); 
	Plan.getEdge("BE").addAttribute("ui.class", "Blue"); 
	Plan.getEdge("CE").setAttribute("ui.label", "CE"); 
	Plan.getEdge("CE").addAttribute("ui.class", "Blue"); 
	Plan.getEdge("CF").setAttribute("ui.label", "CF"); 
	Plan.getEdge("CF").addAttribute("ui.class", "Blue"); 
	Plan.getEdge("DG").setAttribute("ui.label", "DG"); 
	Plan.getEdge("DG").addAttribute("ui.class", "Blue"); 
	Plan.getEdge("EG").setAttribute("ui.label", "EG"); 
	Plan.getEdge("EG").addAttribute("ui.class", "Blue"); 
	Plan.getEdge("EH").setAttribute("ui.label", "EH"); 
	Plan.getEdge("EH").addAttribute("ui.class", "Blue"); 
	Plan.getEdge("CF").setAttribute("ui.label", "CF"); 
	Plan.getEdge("CF").addAttribute("ui.class", "Blue"); 
	Plan.getEdge("FH").setAttribute("ui.label", "FH"); 
	Plan.getEdge("FH").addAttribute("ui.class", "Blue"); 
	Plan.getEdge("HI").setAttribute("ui.label", "HI"); 
	Plan.getEdge("HI").addAttribute("ui.class", "Blue"); 
	Plan.getEdge("GI").setAttribute("ui.label", "GI"); 
	Plan.getEdge("GI").addAttribute("ui.class", "Blue"); 
return Plan; 
} // fin de getPlan2003 



public Graph getPlan2004(String pName){ 
	Graph Plan = new SingleGraph("Plan2004"); 
	Plan.setStrict(false); 
	Plan.setAutoCreate( true ); 
	Plan.addAttribute("ui.stylesheet", "url('file:config/Modele1.css')"); 
	Plan.addAttribute("ui.quality"); 
	Plan.addAttribute("ui.antialias"); 
	
	Plan.addEdge("startA", "start", "A", true );
	Plan.addEdge("AB", "A", "B", true ); 
	Plan.addEdge("AC", "A", "C", true ); 
	Plan.addEdge("BD", "B", "D", true ); 
	Plan.addEdge("BE", "B", "E", true ); 
	Plan.addEdge("CE", "C", "E", true ); 
	Plan.addEdge("CF", "C", "F", true ); 
	Plan.addEdge("DG", "D", "G", true ); 
	Plan.addEdge("EG", "E", "G", true ); 
	Plan.addEdge("EH", "E", "H", true ); 
	Plan.addEdge("CF", "C", "F", true ); 
	Plan.addEdge("FH", "F", "H", true ); 
	Plan.addEdge("HI", "H", "I", true ); 
	Plan.addEdge("GI", "G", "I", true ); ; 

		
	Plan.getNode("start").setAttribute("ui.label", "start");
	Plan.getNode("A").setAttribute("ui.label", "A"); 
	Plan.getNode("B").setAttribute("ui.label", "B"); 
	Plan.getNode("C").setAttribute("ui.label", "C"); 
	Plan.getNode("D").setAttribute("ui.label", "D"); 
	Plan.getNode("E").setAttribute("ui.label", "E"); 
	Plan.getNode("F").setAttribute("ui.label", "F"); 
	Plan.getNode("G").setAttribute("ui.label", "G"); 
	Plan.getNode("H").setAttribute("ui.label", "H"); 
	Plan.getNode("I").setAttribute("ui.label", "I"); 
	
	
	Plan.getEdge("AB").setAttribute("ui.label", "AB"); 
	Plan.getEdge("AB").addAttribute("ui.class", "Blue"); 
	Plan.getEdge("AC").setAttribute("ui.label", "AC"); 
	Plan.getEdge("AC").addAttribute("ui.class", "Blue"); 
	Plan.getEdge("BD").setAttribute("ui.label", "BD"); 
	Plan.getEdge("BD").addAttribute("ui.class", "Blue"); 
	Plan.getEdge("BE").setAttribute("ui.label", "BE"); 
	Plan.getEdge("BE").addAttribute("ui.class", "Blue"); 
	Plan.getEdge("CE").setAttribute("ui.label", "CE"); 
	Plan.getEdge("CE").addAttribute("ui.class", "Blue"); 
	Plan.getEdge("CF").setAttribute("ui.label", "CF"); 
	Plan.getEdge("CF").addAttribute("ui.class", "Blue"); 
	Plan.getEdge("DG").setAttribute("ui.label", "DG"); 
	Plan.getEdge("DG").addAttribute("ui.class", "Blue"); 
	Plan.getEdge("EG").setAttribute("ui.label", "EG"); 
	Plan.getEdge("EG").addAttribute("ui.class", "Blue"); 
	Plan.getEdge("EH").setAttribute("ui.label", "EH"); 
	Plan.getEdge("EH").addAttribute("ui.class", "Blue"); 
	Plan.getEdge("CF").setAttribute("ui.label", "CF"); 
	Plan.getEdge("CF").addAttribute("ui.class", "Blue"); 
	Plan.getEdge("FH").setAttribute("ui.label", "FH"); 
	Plan.getEdge("FH").addAttribute("ui.class", "Blue"); 
	Plan.getEdge("HI").setAttribute("ui.label", "HI"); 
	Plan.getEdge("HI").addAttribute("ui.class", "Blue"); 
	Plan.getEdge("GI").setAttribute("ui.label", "GI"); 
	Plan.getEdge("GI").addAttribute("ui.class", "Blue"); 
 
	

return Plan; 
} // fin de getPlan2004 


} // fin de planSet
//************************
