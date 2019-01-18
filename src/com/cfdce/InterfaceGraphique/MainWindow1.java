package com.cfdce.InterfaceGraphique;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MainWindow1 extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow1 frame = new MainWindow1();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainWindow1() {
		setTitle("Gestion des Plans");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 552, 408);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmApperuDesPlans = new JMenuItem("Apper\u00E7u des Plans");
		mntmApperuDesPlans.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//JFrame appercuPlanFrame = new appercuPlan1(); 
				//appercuPlanFrame.setVisible(true);
			}
		});
		mnFile.add(mntmApperuDesPlans);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Superposer les plans");
		mnFile.add(mntmNewMenuItem);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panelHaut = new JPanel();
		contentPane.add(panelHaut, BorderLayout.NORTH);
		GroupLayout gl_panelHaut = new GroupLayout(panelHaut);
		gl_panelHaut.setHorizontalGroup(
			gl_panelHaut.createParallelGroup(Alignment.LEADING)
				.addGap(0, 526, Short.MAX_VALUE)
		);
		gl_panelHaut.setVerticalGroup(
			gl_panelHaut.createParallelGroup(Alignment.LEADING)
				.addGap(0, 32, Short.MAX_VALUE)
		);
		panelHaut.setLayout(gl_panelHaut);
		
		JPanel panelDroit = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelDroit.getLayout();
		flowLayout.setHgap(10);
		contentPane.add(panelDroit, BorderLayout.WEST);
		
		JPanel panelGauche = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panelGauche.getLayout();
		flowLayout_1.setHgap(10);
		contentPane.add(panelGauche, BorderLayout.EAST);
		
		JPanel panelBas = new JPanel();
		contentPane.add(panelBas, BorderLayout.SOUTH);
		
		JButton btnFermerPrincipale = new JButton("Close");
		btnFermerPrincipale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		GroupLayout gl_panelBas = new GroupLayout(panelBas);
		gl_panelBas.setHorizontalGroup(
			gl_panelBas.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panelBas.createSequentialGroup()
					.addContainerGap(435, Short.MAX_VALUE)
					.addComponent(btnFermerPrincipale))
		);
		gl_panelBas.setVerticalGroup(
			gl_panelBas.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panelBas.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(btnFermerPrincipale))
		);
		panelBas.setLayout(gl_panelBas);
		
		JPanel panelCentre = new JPanel();
		contentPane.add(panelCentre, BorderLayout.CENTER);
	}
}
