package com.cfdce.Agents;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.cfdce.Plan.planSet;

import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;

public class MainAgent4 {
	
	
private static JPanel contentPane;
	
	private JFrame maFenetre = new JFrame(); 
	
	public static ArrayList listPanel = new ArrayList();
	
	static JPanel panel = new JPanel();
	static JPanel panel_1 = new JPanel();
	static JPanel panel_2 = new JPanel();
	static JPanel panel_3 = new JPanel();
	static JPanel panel_4 = new JPanel();
	static JPanel panel_5 = new JPanel();
	static JPanel panel_6 = new JPanel();
	static JPanel panel_7 = new JPanel();
	static JPanel panel_8 = new JPanel();
	static JPanel panel_9 = new JPanel();
	static JPanel panel_10 = new JPanel();
	static JPanel panel_11 = new JPanel();
	
	
		
		
		
	

	public static void main(String[] args) throws StaleProxyException, InterruptedException {
		
		
		listPanel.add(panel);
		listPanel.add(panel_1);
		listPanel.add(panel_2);
		listPanel.add(panel_3);
		listPanel.add(panel_4);
		listPanel.add(panel_5);
		listPanel.add(panel_6);
		listPanel.add(panel_7);
		listPanel.add(panel_8);
		listPanel.add(panel_9);
		listPanel.add(panel_10);
		listPanel.add(panel_11);
		
		
		try {
			JFrame maFenetre = new JFrame();
			AgentPrincipal2(maFenetre);
			maFenetre.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}

		
	// Pr�paration du plan de l'agent
		
		planSet pSet = new planSet();
	
		ArrayList listAgent = new ArrayList();
		int nbrAgent = 0;
		int iterations = 3;
		int GlobalStep = 1;
		int agNbr = 1;

		try { GlobalStep = loadGlobalStep(); } catch (FileNotFoundException e1) {e1.printStackTrace();}
		
		System.out.println("La Liste est vide, Alors, Lancement de nouveaux agents !!!! ");
		System.out.println("");
		
		System.out.println("-->  GlobalStep : "+GlobalStep);
		//System.out.println("-->  Strategy : "+strategy);
		System.out.println("-->  Number of Agents to run : "+nbrAgent);
		int totalWatingTime = 800 * nbrAgent;
		long currentTime = (long) System.currentTimeMillis();
		long lastTime = 0;
		int poucentage = ThreadLocalRandom.current().nextInt(40, 60);
		
		System.out.println("- Limite budget : "+poucentage);
		
	
		PrintWriter f = null;
		try {
			f = new PrintWriter(new FileWriter("logs/"+(GlobalStep)+"_AgentsList.txt"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//f.println(" ");
		
		
		
		

		
		//nbrAgent  =  ThreadLocalRandom.current().nextInt(4, 6);
		nbrAgent = ThreadLocalRandom.current().nextInt(6, 13);
		nbrAgent =7;
		listAgent.clear();
		for(int j=0; j<nbrAgent; j++){
			int var = ThreadLocalRandom.current().nextInt(2, 100);
			if(!listAgent.contains(var)) {
				listAgent.add(var);
			}else{
				j--;
			}
		}
		listAgent.add(1);
		
		
		
		
		
		
		
		//f.println(" ");
		
		
		for(int i=0; i<listAgent.size(); i++){
			agNbr = (int) listAgent.get(i);
			try {
				
				currentTime = (long) System.currentTimeMillis();
				//newAgent2("Agent"+agNbr, agNbr, totalWatingTime, poucentage);
				System.out.println("Le temps "+ (800-(System.currentTimeMillis() - currentTime)));
				Thread.sleep(800-(System.currentTimeMillis() - currentTime));
				totalWatingTime-=800;
				f.println("Agent"+agNbr+"\n");
				
				
				Runtime runtime=Runtime.instance();
				ProfileImpl profileImpl=new ProfileImpl(false);
				profileImpl.setParameter(ProfileImpl.MAIN_HOST,"localhost");
				AgentContainer agentContainer=runtime.createAgentContainer(profileImpl);
				
				
				AgentController agentController;
				agentController = agentContainer.createNewAgent("Agent"+agNbr, "Agents.Agent1", new Object[]{"Agent"+agNbr, ""+agNbr, ""+totalWatingTime, ""+poucentage, ""+1, (JPanel) listPanel.get(i) });
				agentController.start();
				
				
				
			//     Process p2  = runtime.exec(new String[]{"cmd.exe","/c","start  java jade.Boot -agents "+agentName+":Agents.Agent1("+agentName+","+agNbr+","+watingTime+","+poucentage+") -container -host localhost"});
			 	
				
			} catch (InterruptedException e) { e.printStackTrace(); }
			
		} // fin de for
		
		f.close();
		System.out.println("-->  : "+listAgent.toString());
		
		
		
		
	/*	
		
		for (int i = 1; i <= 12; i++) {		
			Runtime runtime=Runtime.instance();
			ProfileImpl profileImpl=new ProfileImpl(false);
			profileImpl.setParameter(ProfileImpl.MAIN_HOST,"localhost");
			AgentContainer agentContainer=runtime.createAgentContainer(profileImpl);
			
			
			AgentController agentController;
			agentController = agentContainer.createNewAgent("Agent"+i, "Agents.Agent1", new Object[]{""+i});
			agentController.start();
			//AgentThread thread = new AgentThread(i, agentContainer);
			//thread.start();
		}
		
		*/
		
		
		// Mise � jour devariable  GlobalStep et  strategy
		try { 
			GlobalStep++;
			Thread.sleep(2000);
			writeGlobalStep(GlobalStep);
			//writeStrategy(ThreadLocalRandom.current().nextInt(1, 5));
			//writeStrategy(strategy);
		}catch(IOException e1){e1.printStackTrace();} 
		
	

			
		
	}
	
	
	
	
	/**
	 * Sauvegarde du compteur des �tapes globales.
	 * @param GlobalStep Le compteur sur l'�tape globale
	 * @throws IOException
	 */
	public static void  writeGlobalStep(int GlobalStep) throws IOException{
			PrintWriter f = new PrintWriter(new FileWriter("config/GlobalStep.txt"));
			f.write(GlobalStep+"");
			f.close();

	} // fin de la M�thode
	
	
	/**
	 * Chargement du compteur sur l'�tape  local � un agent
	 * @return un nembre entier (le compteur) 
	 * @throws FileNotFoundException
	 */
	public static int loadGlobalStep() throws FileNotFoundException{
		int GlobalStep = 0;
		File f = new File("config/GlobalStep.txt");
		Scanner sc = new Scanner(f);
		while(sc.hasNextInt()) {
			GlobalStep = sc.nextInt();
			}
		
		return GlobalStep;
	} // fin de la M�thode
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


/**
 * Create the frame.
 * @return 
 * @return 
 */
public static void AgentPrincipal2(JFrame maFenetre ) {
	
	maFenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	maFenetre.setBounds(100, 100, 1555, 1005);
	contentPane = new JPanel();
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	maFenetre.setContentPane(contentPane);
	GridBagLayout gbl_contentPane = new GridBagLayout();
	gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0};
	gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0};
	gbl_contentPane.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
	gbl_contentPane.rowWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
	contentPane.setLayout(gbl_contentPane);
	
	
	panel_9.setBorder(new LineBorder(new Color(0, 0, 0)));
	GridBagConstraints gbc_panel_9 = new GridBagConstraints();
	gbc_panel_9.insets = new Insets(0, 0, 5, 5);
	gbc_panel_9.fill = GridBagConstraints.BOTH;
	gbc_panel_9.gridx = 0;
	gbc_panel_9.gridy = 0;
	contentPane.add(panel_9, gbc_panel_9);
	
	
	panel_6.setBorder(new LineBorder(new Color(0, 0, 0), 3));
	GridBagConstraints gbc_panel_6 = new GridBagConstraints();
	gbc_panel_6.insets = new Insets(0, 0, 5, 5);
	gbc_panel_6.fill = GridBagConstraints.BOTH;
	gbc_panel_6.gridx = 1;
	gbc_panel_6.gridy = 0;
	contentPane.add(panel_6, gbc_panel_6);
	

	panel_7.setBorder(new LineBorder(new Color(0, 0, 0), 3));
	GridBagConstraints gbc_panel_7 = new GridBagConstraints();
	gbc_panel_7.insets = new Insets(0, 0, 5, 5);
	gbc_panel_7.fill = GridBagConstraints.BOTH;
	gbc_panel_7.gridx = 2;
	gbc_panel_7.gridy = 0;
	contentPane.add(panel_7, gbc_panel_7);
	

	panel_8.setBorder(new LineBorder(new Color(0, 0, 0), 3));
	GridBagConstraints gbc_panel_8 = new GridBagConstraints();
	gbc_panel_8.insets = new Insets(0, 0, 5, 0);
	gbc_panel_8.fill = GridBagConstraints.BOTH;
	gbc_panel_8.gridx = 3;
	gbc_panel_8.gridy = 0;
	contentPane.add(panel_8, gbc_panel_8);
	

	panel_11.setBorder(new LineBorder(new Color(0, 0, 0)));
	GridBagConstraints gbc_panel_11 = new GridBagConstraints();
	gbc_panel_11.insets = new Insets(0, 0, 5, 5);
	gbc_panel_11.fill = GridBagConstraints.BOTH;
	gbc_panel_11.gridx = 0;
	gbc_panel_11.gridy = 1;
	contentPane.add(panel_11, gbc_panel_11);
	

	panel_3.setBorder(new LineBorder(new Color(0, 0, 0), 3));
	GridBagConstraints gbc_panel_3 = new GridBagConstraints();
	gbc_panel_3.insets = new Insets(0, 0, 5, 5);
	gbc_panel_3.fill = GridBagConstraints.BOTH;
	gbc_panel_3.gridx = 1;
	gbc_panel_3.gridy = 1;
	contentPane.add(panel_3, gbc_panel_3);
	

	panel_2.setBorder(new LineBorder(new Color(0, 0, 0), 3));
	GridBagConstraints gbc_panel_2 = new GridBagConstraints();
	gbc_panel_2.insets = new Insets(0, 0, 5, 5);
	gbc_panel_2.fill = GridBagConstraints.BOTH;
	gbc_panel_2.gridx = 2;
	gbc_panel_2.gridy = 1;
	contentPane.add(panel_2, gbc_panel_2);
	

	panel_5.setBorder(new LineBorder(new Color(0, 0, 0), 3));
	GridBagConstraints gbc_panel_5 = new GridBagConstraints();
	gbc_panel_5.insets = new Insets(0, 0, 5, 0);
	gbc_panel_5.fill = GridBagConstraints.BOTH;
	gbc_panel_5.gridx = 3;
	gbc_panel_5.gridy = 1;
	contentPane.add(panel_5, gbc_panel_5);
	

	panel_10.setBorder(new LineBorder(new Color(0, 0, 0)));
	GridBagConstraints gbc_panel_10 = new GridBagConstraints();
	gbc_panel_10.insets = new Insets(0, 0, 0, 5);
	gbc_panel_10.fill = GridBagConstraints.BOTH;
	gbc_panel_10.gridx = 0;
	gbc_panel_10.gridy = 2;
	contentPane.add(panel_10, gbc_panel_10);
	

	panel_4.setBorder(new LineBorder(new Color(0, 0, 0), 3));
	GridBagConstraints gbc_panel_4 = new GridBagConstraints();
	gbc_panel_4.insets = new Insets(0, 0, 0, 5);
	gbc_panel_4.fill = GridBagConstraints.BOTH;
	gbc_panel_4.gridx = 1;
	gbc_panel_4.gridy = 2;
	contentPane.add(panel_4, gbc_panel_4);
	

	panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 3));
	GridBagConstraints gbc_panel_1 = new GridBagConstraints();
	gbc_panel_1.insets = new Insets(0, 0, 0, 5);
	gbc_panel_1.fill = GridBagConstraints.BOTH;
	gbc_panel_1.gridx = 2;
	gbc_panel_1.gridy = 2;
	contentPane.add(panel_1, gbc_panel_1);
	

	panel.setBorder(new LineBorder(new Color(0, 0, 0), 3));
	GridBagConstraints gbc_panel = new GridBagConstraints();
	gbc_panel.fill = GridBagConstraints.BOTH;
	gbc_panel.gridx = 3;
	gbc_panel.gridy = 2;
	contentPane.add(panel, gbc_panel);
}
	
} //  fin de la classe
