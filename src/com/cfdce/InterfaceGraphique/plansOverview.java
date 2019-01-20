package com.cfdce.InterfaceGraphique;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.ui.view.View;
import org.graphstream.ui.view.Viewer;

import com.cfdce.Plan.planSet;

public class plansOverview extends JFrame {


	private JPanel contentPane;
	private Graph g ;
	private JFrame frame;
	private planSet pSet = new planSet();
	private Viewer viewer;
	private View view;
	
	public static void main(final Graph[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					plansOverview frame = new plansOverview(args);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	
	} // fin de main 

	/**
	 * Create the frame.
	 */
	public plansOverview(Graph[] args) {
		setTitle("Apper\u00E7u des Plans");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 752, 535);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panelGauche = new JPanel();
		contentPane.add(panelGauche, BorderLayout.WEST);
		GroupLayout gl_panelGauche = new GroupLayout(panelGauche);
		gl_panelGauche.setHorizontalGroup(
			gl_panelGauche.createParallelGroup(Alignment.LEADING)
				.addGap(0, 101, Short.MAX_VALUE)
		);
		gl_panelGauche.setVerticalGroup(
			gl_panelGauche.createParallelGroup(Alignment.LEADING)
				.addGap(0, 421, Short.MAX_VALUE)
		);
		panelGauche.setLayout(gl_panelGauche);
		
		JPanel panelDroit = new JPanel();
		contentPane.add(panelDroit, BorderLayout.EAST);
		
		JPanel panelBas = new JPanel();
		contentPane.add(panelBas, BorderLayout.SOUTH);
		
		JButton btnNewButton = new JButton("Close");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			dispose();
			}
		});
		GroupLayout gl_panelBas = new GroupLayout(panelBas);
		gl_panelBas.setHorizontalGroup(
			gl_panelBas.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelBas.createSequentialGroup()
					.addContainerGap(667, Short.MAX_VALUE)
					.addComponent(btnNewButton))
		);
		gl_panelBas.setVerticalGroup(
			gl_panelBas.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelBas.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(btnNewButton))
		);
		panelBas.setLayout(gl_panelBas);
		
		JPanel panelHaut = new JPanel();
		contentPane.add(panelHaut, BorderLayout.NORTH);
		GroupLayout gl_panelHaut = new GroupLayout(panelHaut);
		gl_panelHaut.setHorizontalGroup(
			gl_panelHaut.createParallelGroup(Alignment.LEADING)
				.addGap(0, 726, Short.MAX_VALUE)
		);
		gl_panelHaut.setVerticalGroup(
			gl_panelHaut.createParallelGroup(Alignment.LEADING)
				.addGap(0, 31, Short.MAX_VALUE)
		);
		panelHaut.setLayout(gl_panelHaut);
		
		JPanel panelCentre = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelCentre.getLayout();
		flowLayout.setVgap(300);
		flowLayout.setHgap(300);
		contentPane.add(panelCentre, BorderLayout.CENTER);
		
		 addGraph(args,contentPane);
	}
	
// M�thode pour ajouter le graphe � la fen�tre : 
//----------------------------------------------
	public void addGraph(Graph[] args, JPanel panel){
		
	 //	Graph g2 = args[0];
		g = args[0];
	
		System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
		g.addAttribute("ui.stylesheet", "url('file:config/Modele1.css')");
		g.addAttribute("ui.quality");
		g.addAttribute("ui.antialias");
		g.setStrict(false);
		g.setAutoCreate( true ); // cr�ation automatique des noeuds.
		/*
		for(Edge ed:g2.getEachEdge()){
			g.addEdge(ed.getId(), ed.getSourceNode().getId(), ed.getTargetNode().getId(), true );
		}
		*/
		Node start = g.getNode("start");
		start.setAttribute("xyz", 250,450,0);
		
		
		for(int i=1; i<args.length; i++){
			Graph plan =  args[i];
			
			Node start1 = plan.getNode("start");
			start1.setAttribute("ui.label", plan+"_"+start.getId());
			
			String newStart = plan+"_"+start.getId();
			String firstEdge ="";
			String secondNode ="";
			
			Node start2 = g.getNode("start");
			start2.setAttribute("xyz", 250,450,0);
			
			//-----------------------------------------------------------
			boolean secondNodeFound = false;
			for(Edge ed1:plan.getEachEdge()){
				boolean exist = false;
			
				if(!secondNodeFound)
				if(ed1.getSourceNode().getId().equals("start")){
						secondNode = ed1.getTargetNode().getId();
						secondNodeFound = true;
				}
	
				for(Edge ed2:g.getEachEdge()){
					if(ed1.getId().equals(ed2.getId())){
						if(!ed2.getSourceNode().getId().equals("start"))
					//	g.getEdge(ed1.getId()).addAttribute("ui.class", "shared");
						exist = true;
					} 
				} // fin de for
				
				if(!ed1.getSourceNode().getId().equals("start"))
				if(!exist){
					g.addEdge(ed1.getId(), ed1.getSourceNode().getId(), ed1.getTargetNode().getId(), true );
					g.getEdge(ed1.getId()).addAttribute("ui.class", (String)  ed1.getAttribute("ui.class"));
					g.getNode(ed1.getSourceNode().getId()).addAttribute("ui.label", ed1.getSourceNode().getId());
					g.getNode(ed1.getTargetNode().getId()).addAttribute("ui.label", ed1.getTargetNode().getId());
				}
				
			} // fin de for
			
			
		
			g.addNode(newStart);
			Node no = g.getNode(newStart);
			no.addAttribute("ui.label", newStart);
			g.addEdge(newStart+secondNode, newStart, secondNode, true );
			
			//g.addEdge(newStart+secondNode, start.getId(), secondNode, true );
			//g.getEdge(newStart+secondNode).addAttribute("ui.class", "shared");
			
			
			
			
			//-----------------------------------------------------------
			
			
		}

		
		
		
		
		//viewer = new Viewer(g, Viewer.ThreadingModel.GRAPH_IN_SWING_THREAD);
		viewer = new Viewer(g, Viewer.ThreadingModel.GRAPH_IN_GUI_THREAD);
		viewer.enableAutoLayout();
		// ...
		view = viewer.addDefaultView(false);   // false indicates "no JFrame".
		
		view.getCamera().setViewCenter(2, 3, 4);

		view.getCamera().setViewPercent(0.5);
		
		view.getCamera().resetView();



		// ...
	 	contentPane.add((Component) view);
	 
	//	panel.add((Component) view);
	}
	
	
	
}
