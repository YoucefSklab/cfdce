package com.cfdce.DB;


import java.sql.SQLException;
import java.sql.Statement;

public class ManipulationBD {
	
	BDConnect DB = new  BDConnect();
	
	public ManipulationBD(){
		
		
	}
	
	
	
	
	
	
	
	//Cette fonction Ajouter un enregistrement dans une Table dans la base de donn�es. avec les variables suivantes : 
	// FieldTab :  un tableau contenant les champs. 
	// ValueTab :  un Tableau contenant les valeurs des champs.
	// TableName : le nom de la table.

	public int addNewRow(String TableName, String[] FieldTab, String[] ValueTab) throws SQLException  {
		
		int ResultatRetoune = 0;
		int FieldTabLengh = 0; // la taille du tableau des champs.
		int ValueTabLengh = 0; //la taille du tableau des valeurs des champs.
		int i=0;
		String Rqt = "";
		Statement St = null;
		
		
		Rqt = " INSERT INTO `"+TableName+"` (";
		
		FieldTabLengh = FieldTab.length;
		ValueTabLengh = ValueTab.length;
		
				for (i=0; i<FieldTabLengh; i++) {
					if(i==0){
							Rqt = Rqt +FieldTab[i];
						}else {
							Rqt = Rqt + ","+FieldTab[i]+"";
						}
					}
				Rqt = Rqt + ") VALUES (";
				
				for (i=0; i<ValueTabLengh; i++) {
					if(i==0){
							Rqt = Rqt + " '"+ValueTab[i]+"'";
						}else {
							Rqt = Rqt + ",'"+ValueTab[i]+"'";
						}
					}
				
				Rqt = Rqt + ")";
					
						if(DB.Connect())
							{
								St = (Statement) DB.con.createStatement();
								ResultatRetoune = St.executeUpdate(Rqt);
							}
						
					return ResultatRetoune;
		} // fin de la fonction

	

	
            
            
            public int UpdateRow(String TableName, String[] FieldTab, String[] ValueTab) throws SQLException  {
		
		int ResultatRetoune = 0;
		int FieldTabLengh = 0; // la taille du tableau des champs.
		int ValueTabLengh = 0; //la taille du tableau des valeurs des champs.
		int i=0;
		String Rqt = "";
		Statement St = null;
		
		
		Rqt = " UPDATE "+TableName+ "SET ";
		
		FieldTabLengh = FieldTab.length;
		ValueTabLengh = ValueTab.length;
		
				for (i=0; i<FieldTabLengh; i++) {
					if(i==0){
							Rqt = Rqt +FieldTab[i];
						}else {
							Rqt = Rqt + ","+FieldTab[i]+"";
						}
					}
				Rqt = Rqt + ") VALUES (";
				
				for (i=0; i<ValueTabLengh; i++) {
					if(i==0){
							Rqt = Rqt + " '"+ValueTab[i]+"'";
						}else {
							Rqt = Rqt + ",'"+ValueTab[i]+"'";
						}
					}
				
				Rqt = Rqt + ")";
					
						if(DB.Connect())
							{
								St = (Statement) DB.con.createStatement();
								ResultatRetoune = St.executeUpdate(Rqt);
							}
						
					return ResultatRetoune;
		} // fin de la fonction

		
		
	 public int deleteRow(String TableName, String[] FieldTab, String[] ValueTab, String Condition) throws SQLException  {
		
		int ResultatRetoune = 0;
		int FieldTabLengh = 0; // la taille du tableau des champs.
		int ValueTabLengh = 0; //la taille du tableau des valeurs des champs.
		int i=0;
		String Rqt = "";
		Statement St = null;
		
		//UPDATE patient SET presence=0, Nom="TestAAAAATest"  WHERE Id = 3
		
		Rqt = " DELETE FROM "+TableName;
		
		FieldTabLengh = FieldTab.length;
		ValueTabLengh = ValueTab.length;
		
				for (i=0; i<FieldTabLengh; i++) {
					if(i==0){
							Rqt = Rqt +FieldTab[i]+" = '"+ValueTab[i]+"' ";
						}else {
							Rqt = Rqt+ ","+FieldTab[i]+" = '"+ValueTab[i]+"' ";
						}
					}
			
				
				
				Rqt = Rqt + Condition;
												
						if(DB.Connect())
							{
								St = (Statement) DB.con.createStatement();
								ResultatRetoune = St.executeUpdate(Rqt);
							}
						
					return ResultatRetoune;
				
						
					
		} // fin de la fonction
   
	
	
	
	
	public void DeleteRow(){
		
		
	}
	
	
	
	
	public void ExecuteQuery(String Query){
		
		
	}
	
	
}





