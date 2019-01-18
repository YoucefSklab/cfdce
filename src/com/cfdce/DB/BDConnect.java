
// to check if i will keep this class. or to take some good methodes

package com.cfdce.DB;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
//import java.sql.Connection;
//import java.sql.DatabaseMetaData;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

//import com.mysql.jdbc.Statement;


public class BDConnect {
	
	
		private static String driver;
		private static String url;
		private static String id;
		private static String pw;
		public static Connection con;
		private static String sgbd;
		private static String dbname;
		public static ResultSet resultat;
		private ResultSetMetaData rsmd;
		private DatabaseMetaData dm;
		public static Connection connexion;
		public static Statement instruction;
		
	//	this.forName("com.mysql.jdbc.Driver");
	//	con = DriverManager.getConnection("jdbc:mysql://localhost/applicationdb", "root","mysql");
		
		
	
	public BDConnect(){
		driver = "com.mysql.jdbc.Driver";
		url = "jdbc:mysql://localhost/db1";
		id = "root";
		pw = "ysklab";
		sgbd ="mysql";
		dbname="db1";
		
//		try {
//		Class.forName("com.mysql.jdbc.Driver");
//		con = DriverManager.getConnection("jdbc:mysql://localhost/applicationdb", "root","mysql");
//		//Statement instruction = (Statement) con.createStatement();
//		}catch (Exception e) {
//			e.printStackTrace();
//		}		
	
	}
	
	public void setDriver(String driver) {
		this.driver = driver;
	}
	
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	
	public String getUrl() {
		return this.url;
	}
	
	
	public void setId(String id) {
		this.id = id;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	
	
	public void setSGBD(String sgbd) {
		this.sgbd = sgbd;
	}
	
	
	public String getSGBD() {
		return this.sgbd;
	}
	
	
	public void setDBName(String dbname) {
		this.dbname = dbname;
	}
	
	
	public String getDBName() {
		return this.dbname;
	}
	
	
	public boolean Connect() {
		boolean isCon = false;
		if (driver == "" && url == "")
		JOptionPane.showMessageDialog(null,
		"Connexion impossible, car driver ou url non renseigner",
		"Connexion", JOptionPane.ERROR_MESSAGE);
		else {
			try {
				Class.forName(driver);
				
				
				con = DriverManager.getConnection(url, id, pw);
				//con = DriverManager.getConnection("jdbc:mysql://localhost/applicationdb","root","mysql"); 
			
				isCon = true;
				
				
			} catch (ClassNotFoundException e) {
				JOptionPane.showMessageDialog(null,
				"Probl�me avec le driver ODBC", "Connexion",
				JOptionPane.ERROR_MESSAGE);
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null,
				"Impossible de se connecter � la base", "Connexion",
				JOptionPane.ERROR_MESSAGE);
			}
		}
		return isCon;
	}
	
	
	
	
	public String[] getTables() {
		DatabaseMetaData metaData;
		ResultSet bd;
		String[] tb;
		List<String> tbf = new ArrayList<String>();
		int i = 0;
		try {
			metaData = con.getMetaData();
			bd = metaData.getTables(con.getCatalog(), null, "%", null);
			while (bd.next()) {
				if (bd.getString(4).equals("TABLE"))
				tbf.add(bd.getString(3));
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Connexion",
			JOptionPane.ERROR_MESSAGE);
		}
		tb = new String[tbf.size()];
		for (i = 0; i < tb.length; i++)
		tb[i] = tbf.get(i).toString();
		return tb;
	}
	
	
	public String[] getChamp(String tab) {
		DatabaseMetaData metaData;
		ResultSet tbl;
		String[] tb;
		List<String> tbf = new ArrayList<String>();
		int i = 0;
		try {
			metaData = con.getMetaData();
			tbl = metaData.getColumns(con.getCatalog(), null, tab, "%");
			while (tbl.next())
			tbf.add(tbl.getString(4));
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Connexion",
			JOptionPane.ERROR_MESSAGE);
		}
		tb = new String[tbf.size()];
		for (i = 0; i < tb.length; i++)
		tb[i] = tbf.get(i).toString();
		return tb;
	}
	
	
	public String[] getChpInfo(String tab, String chp) {
		DatabaseMetaData metaData;
		ResultSet tbl;
		String res[] = new String[2];
		;
		try {
			metaData = con.getMetaData();
			tbl = metaData.getColumns(con.getCatalog(), null, tab, "%");
			while (tbl.next())
			if (tbl.getString(4).equals(chp)) {
				res[0] = tbl.getString(6);
				res[1] = (tbl.getInt(11) == 1) ? "Oui" : "Non";
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Connexion",
			JOptionPane.ERROR_MESSAGE);
		}
		return res;
	}
	public void Close() {
		try {
			con.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Connexion",
			JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	
	
	
	
	
	// Cette fonction verifie l'utilisateur connect� et renvoie ses informations dans un tableau avec les variables suivantes :  
	// UserName : le Nome d'utilisateur Introduit dans le formulaire.
	// Password : le mot de passe introduit dans le formulaire.
	// 
		// a voir
		
		public String[] UserConnecionInfo(String UserName, String Password) throws SQLException{
			
		String UserTabInfo[]=null;
		Statement instruction = (Statement) connexion.createStatement();
		ResultSet resultat = instruction.executeQuery("SELECT * FROM user");
		resultat.getInt(1);
	
		
			return UserTabInfo;
		} // fin de la fonction
		
		
		
		
		
		
		
		
		
		
		// Cette fonction renvoie l'identificateur de l'utilisateur connect� si le username et le password sont correcte, sinon, elle renvoie un 0.
		// avec les param�tres suivants : 
		// UserName :  le nom utilisateur.
		// Password : le mot de passe utilisateur.
		// 
		public String UserConnectionId(String UserName, String Password) throws SQLException {
			
			String  UserIdInfo = "";
			
			 Statement instruction = (Statement) con.createStatement();
			 resultat = instruction.executeQuery("SELECT * FROM user WHERE UserName='"+UserName+"'");
			
			//resultat.getInt(1);
			//UserIdInfo = resultat.getString("Id");
			//UserIdInfo = resultat.getInt("Id");
			//System.out.println("ooooooksdfsdfsdf");
			
			if (resultat.first() && resultat.getString("UserName").equals(UserName) && resultat.getString("Password").equals(Password) ){
		  //	 if (resultat.first() && resultat.getString("UserName").equals(UserName)){
			  
			  //	System.out.println(resultat.getString("Nom"));
			  //	System.out.println("Identificateur= "+resultat.getString("Id"));
				UserIdInfo = resultat.getString("Id");
				}else{
					UserIdInfo = "0";
				//	System.out.println("User ID == "+UserIdInfo);
				}
			
				return UserIdInfo;
			} // fin de la fonction\
		
	
	
	// Cette fonction renvoie un attribut d'une base donn�es. avec les variables suivantes : 
	// Table :  la table concern�e
	// Id :  l'identificateur de l'enregistrement
	// Attribute : l'attribut recherch�.


public String getAttribut(String Table, String  Id, int IdValue ,String Attribute) throws SQLException {
		
		String  AttributeValue = null;
		
		 Statement instruction = (Statement) con.createStatement();
		 resultat = instruction.executeQuery("SELECT "+Attribute+" FROM "+Table+" WHERE "+Id+"="+IdValue);
		
		if (resultat.first()){
			AttributeValue = resultat.getString(Attribute);
			}else{
				AttributeValue = null;
			}
		
			return AttributeValue;
		} // fin de la fonction
                
public String getAttribut(String Table, String Id, String IdValue, String Attribute) throws SQLException {
		
		String  AttributeValue = null;
		
                 System.out.println(" SELECT "+Attribute+" FROM "+Table+" WHERE "+Id+"='"+IdValue+"' ");
		
                 Statement instruction = (Statement) con.createStatement();
                
		 resultat = instruction.executeQuery(" SELECT "+Attribute+" FROM "+Table+" WHERE "+Id+"='"+IdValue+"' ");
		
		if (resultat.first()){
			AttributeValue = resultat.getString(Attribute);
			}else{
				AttributeValue = null;
			}
		
			return AttributeValue;
		} // fin de la fonction

                
public ArrayList getTableIdList(String Table, String Id) throws SQLException {
		
		ArrayList ListID = new ArrayList();
		
               Statement instruction = (Statement) con.createStatement();
                
                resultat = instruction.executeQuery(" SELECT "+Id+" FROM "+Table+" ");
		
                 
		while(resultat.next()){
			ListID.add(resultat.getString(Id));
			}
             
                        return ListID;
		} // fin de la fonction



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
												
						St = (Statement) con.createStatement();
						ResultatRetoune = St.executeUpdate(Rqt);
					System.out.println(Rqt);		
						
					return ResultatRetoune;
		} // fin de la fonction
        
        
        
        
        
        
        
       
        
        
        
      
	
	
	
    //Cette fonction met � jout un enregistrement dans une Table dans la base de donn�es. avec les variables suivantes : 
	// FieldTab :  un tableau contenant les champs. 
	// ValueTab :  un Tableau contenant les valeurs des champs.
	// TableName : le nom de la table.

	public int updateRow(String TableName, String[] FieldTab, String[] ValueTab, String Condition) throws SQLException  {
		
		int ResultatRetoune = 0;
		int FieldTabLengh = 0; // la taille du tableau des champs.
		int ValueTabLengh = 0; //la taille du tableau des valeurs des champs.
		int i=0;
		String Rqt = "";
		Statement St = null;
		
		//UPDATE patient SET presence=0, Nom="TestAAAAATest"  WHERE Id = 3
		
		Rqt = " UPDATE "+TableName+" SET ";
		
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
												
						St = (Statement) con.createStatement();
						ResultatRetoune = St.executeUpdate(Rqt);
				System.out.println(Rqt);	
						
					return ResultatRetoune;
		} // fin de la fonction
   
        ////////////////////////////////////////////////////////::::::
        
       // fin de la fonction
        
        
        //////////////////////////////:essai
        public int deleteRow(String TableName, String Condition) throws SQLException  {
		
		int ResultatRetoune = 0;
		int FieldTabLengh = 0; // la taille du tableau des champs.
		int ValueTabLengh = 0; //la taille du tableau des valeurs des champs.
		int i=0;
		String Rqt = "";
		Statement St = null;
		
		//UPDATE patient SET presence=0, Nom="TestAAAAATest"  WHERE Id = 3
		
		Rqt = " DELETE FROM "+TableName;
		
		
			
				
				
				Rqt = Rqt + Condition;
												
						St = (Statement) con.createStatement();
						ResultatRetoune = St.executeUpdate(Rqt);
				System.out.println(Rqt);	
						
					return ResultatRetoune;
		} // fin de la fonction
   
        ////////////////////////////////////////////////////////::::::
        
       // fin de la fonction
       
      
 
 
        
		

}  // fin de la classe

