package com.cfdce.InterfaceGraphique;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.view.View;
import org.graphstream.ui.view.Viewer;

import com.cfdce.Plan.PlanManagment;
import com.cfdce.Plan.planSet;

public class appercuPlan1 extends JFrame {

	private JPanel contentPane;
	private Graph g ;
	private JFrame frame;
	private planSet pSet = new planSet();
	private Viewer viewer;
	private View view;
	JLabel indice = new JLabel("New label");
	JLabel altNbr = new JLabel("label");
	JLabel GammaPl = new JLabel("GammaPl");
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					appercuPlan1 frame = new appercuPlan1();
					frame.setVisible(true);
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	
	} // fin de main 

	/**
	 * Create the frame.
	 * @throws FileNotFoundException 
	 */
	public appercuPlan1() throws FileNotFoundException {
		setTitle("Apper\u00E7u des Plans");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 802, 675);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panelGauche = new JPanel();
		contentPane.add(panelGauche, BorderLayout.WEST);
		
		JLabel lblListeDesPlans = new JLabel("Plan 1 :");
		
		final JComboBox comboBox = new JComboBox();
		comboBox.setMaximumRowCount(40);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				int planId = (int)  Integer.parseInt((String) comboBox.getSelectedItem());
				Graph g2 = null;
							try { 	g2 = pSet.getPlanByNbr(planId);	} catch (FileNotFoundException e1) { e1.printStackTrace(); }	
				
					
			
				
			
				
				g.clear();
				for(Edge ed:g2.getEachEdge()){
					g.addEdge(ed.getId(), ed.getSourceNode().getId(), ed.getTargetNode().getId(), true );
					
					g.getEdge(ed.getId()).addAttribute("ui.class", ed.getAttribute("ui.class"));
					
					g.getNode(ed.getSourceNode().getId()).addAttribute("ui.label", ed.getSourceNode().getId());
					g.getNode(ed.getTargetNode().getId()).addAttribute("ui.label", ed.getTargetNode().getId());
				}
				
				Node start = getRoot(g);
				start.setAttribute("xyz", 250,450,0);
				
				g.setStrict(false);
				g.setAutoCreate(true); // cr�ation automatique des noeuds.
				g.addAttribute("ui.stylesheet", "url('file:C:\\Planning/Modele1.css')"); 
				g.addAttribute("ui.quality"); 
				g.addAttribute("ui.antialias");
				//getRoot(g).setAttribute("ui.label", "start"); 
	
				int taskNbr = g.getEdgeCount();
				taskNbr--;
				int alternativeNbr = 0;
				// calculer les alternatives 
				//--------------------------
				// Calculer les Alternatives
				PlanManagment planMgmt = new PlanManagment(""); // Classe gestion des plan
				ArrayList allPaths = new ArrayList(); 	// le nombre de paths dans le plan local : 
				planMgmt.getAllPaths(g, new SingleGraph("PathByStart_1"), allPaths, planMgmt.getRoot(g),  planMgmt.getGoal(g));
				
				alternativeNbr = allPaths.size();
				altNbr.setText(" = "+allPaths.size());
				
				
				//-----------------------------------------------
				// Calcul du gamma d'un plan
				//----------------------------------------------
				float gamma = 0;
				
				for(Edge ed:g.getEachEdge()){
					Node n = ed.getSourceNode();
					if(!n.getId().contains("start")){ // faut pas traiter le cas des premi�res taches ajout�es par moi meme
						float taskGamma = 0;
						int nbrPaths = 0;
						
						for(int i=0; i<allPaths.size(); i++){
							Graph path = (Graph) allPaths.get(i);
								
							for(Edge task:path.getEachEdge()){
								if(task.getId().equals(ed.getId())){
									nbrPaths++;
									break;
								}
							}
						
						}
						
						gamma+=(float) ((float)1/(float)nbrPaths);
					}
				}
				
			//	float ga = (float) (gamma / taskNbr);
				float ga = (float) (gamma / (float)( (float) alternativeNbr / (float) taskNbr ));
				
				ga = (float) ga / (float)taskNbr;
				
				//gamma= (float) ((gamma ) / (taskNbr) );
				gamma= (float) ((alternativeNbr * gamma ) / (taskNbr * taskNbr) );
				
				GammaPl.setText(gamma+"               - |T|: "+taskNbr+"  ga: "+ ga);
			
				
				// ...
		//		view = viewer.addDefaultView(false);   // false indicates "no JFrame".
				// ...
			//	contentPane.remove((Component) view);
			//	contentPane.add((Component) view);
				view.getCamera().resetView();
			}
		});
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31","32","33","34","35","36","37","38","39","40","41","42","43","44","45","46","47","48","49","50","51","52","53","54","55","56","57","58","59","60","61","62","63","64","65","66","67","68","69","70","71","72","73","74","75","76","77","78","79","80","81","82","83","84","85","86","87","88","89","90","91","92","93","94","95","96","97","98","99","100","101","102","103","104","105","106","107","108","109","110","111","112","113","114","115","116","117","118","119","120","121","122","123","124","125","126","127","128","129","130","131","132","133","134","135","136","137","138","139","140","141","142","143","144","145","146","147","148","149","150","151","152","153","154","155","156","157","158","159","160","161","162","163","164","165","166","167","168","169","170","171","172","173","174","175","176","177","178","179","180","181","182","183","184","185","186","187","188","189","190","191","192","193","194","195","196","197","198","199","200","201","202","203","204","205","206","207","208","209","210","211","212","213","214","215","216","217","218","219","220","221","222","223","224","225","226","227","228","229","230","231","232","233","234","235","236","237","238","239","240","241","242","243","244","245","246","247","248","249","250","251","252","253","254","255","256","257","258","259","260","261","262","263","264","265","266","267","268","269","270","271","272","273","274","275","276","277","278","279","280","281","282","283","284","285","286","287","288","289","290","291","292","293","294","295","296","297","298","299","300","301","302","303","304","305","306","307","308","309","310","311","312","313","314","315","316","317","318","319","320","321","322","323","324","325","326","327","328","329","330","331","332","333","334","335","336","337","338","339","340","341","342","343","344","345","346","347","348","349","350","351","352","353","354","355","356","357","358","359","360","361","362","363","364","365","366","367","368","369","370","371","372","373","374","375","376","377","378","379","380","381","382","383","384","385","386","387","388","389","390","391","392","393","394","395","396","397","398","399","400","401","402","403","404","405","406","407","408","409","410","411","412","413","414","415","416","417","418","419","420","421","422","423","424","425","426","427","428","429","430","431","432","433","434","435","436","437","438","439","440","441","442","443","444","445","446","447","448","449","450","451","452","453","454","455","456","457","458","459","460","461","462","463","464","465","466","467","468","469","470","471","472","473","474","475","476","477","478","479","480","481","482","483","484","485","486","487","488","489","490","491","492","493","494","495","496","497","498","499","500","501","502","503","504","505","506","507","508","509","510","511","512","513","514","515","516","517","518","519","520","521","522","523","524","525","526","527","528","529","530","531","532","533","534","535","536","537","538","539","540","541","542","543","544","545","546","547","548","549","550","551","552","553","554","555","556","557","558","559","560","561","562","563","564","565","566","567","568","569","570","571","572","573","574","575","576","577","578","579","580","581","582","583","584","585","586","587","588","589","590","591","592","593","594","595","596","597","598","599","600","601","602","603","604","605","606","607","608","609","610","611","612","613","614","615","616","617","618","619","620","621","622","623","624","625","626","627","628","629","630","631","632","633","634","635","636","637","638","639","640","641","642","643","644","645","646","647","648","649","650","651","652","653","654","655","656","657","658","659","660","661","662","663","664","665","666","667","668","669","670","671","672","673","674","675","676","677","678","679","680","681","682","683","684","685","686","687","688","689","690","691","692","693","694","695","696","697","698","699","700","701","702","703","704","705","706","707","708","709","710","711","712","713","714","715","716","717","718","719","720","721","722","723","724","725","726","727","728","729","730","731","732","733","734","735","736","737","738","739","740","741","742","743","744","745","746","747","748","749","750","751","752","753","754","755","756","757","758","759","760","761","762","763","764","765","766","767","768","769","770","771","772","773","774","775","776","777","778","779","780","781","782","783","784","785","786","787","788","789","790","791","792","793","794","795","796","797","798","799","800","801","802","803","804","805","806","807","808","809","810","811","812","813","814","815","816","817","818","819","820","821","822","823","824","825","826","827","828","829","830","831","832","833","834","835","836","837","838","839","840","841","842","843","844","845","846","847","848","849","850","851","852","853","854","855","856","857","858","859","860","861","862","863","864","865","866","867","868","869","870","871","872","873","874","875","876","877","878","879","880","881","882","883","884","885","886","887","888","889","890","891","892","893","894","895","896","897","898","899","900","901","902","903","904","905","906","907","908","909","910","911","912","913","914","915","916","917","918","919","920","921","922","923","924","925","926","927","928","929","930","931","932","933","934","935","936","937","938","939","940","941","942","943","944","945","946","947","948","949","950","951","952","953","954","955","956","957","958","959","960","961","962","963","964","965","966","967","968","969","970","971","972","973","974","975","976","977","978","979","980","981","982","983","984","985","986","987","988","989","990","991","992","993","994","995","996","997","998","999","1000"}));
		
		final JComboBox comboBox_1 = new JComboBox();
		comboBox_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					actionOnComboBox(comboBox);
				} catch (FileNotFoundException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} // r�initialier le premier plan du premier comboBox
	
				Graph g2 = null;
				
				int planId = (int)  Integer.parseInt((String) comboBox_1.getSelectedItem());
				try { 	g2 = pSet.getPlanByNbr(planId);	} catch (FileNotFoundException e1) { e1.printStackTrace(); }	
				
				
					
	
				
				
				// calculer les alternatives 
				//--------------------------
				// Calculer les Alternatives
				PlanManagment planMgmt = new PlanManagment(""); // Classe gestion des plan
				ArrayList allPaths = new ArrayList(); 	// le nombre de paths dans le plan local : 
				planMgmt.getAllPaths(g, new SingleGraph("PathByStart_1"), allPaths, planMgmt.getRoot(g),  planMgmt.getGoal(g));
				
				altNbr.setText(" "+allPaths.size());
				
				Node start = getRoot(g2);
				start.setAttribute("ui.label", "Plan2_"+start.getId());
				String newStart = "Plan2_"+start.getId();
				String firstEdge ="";
				String secondNode ="";
				
				Node start2 = getRoot(g);
				start2.setAttribute("xyz", 250,450,0);
				
				//--------------------------------------------------------------------------
				//--------------------------------------------------------------------------
				System.out.println("-----> Calcul de l'indice de concordance .... ");
				
						
				

				float freq = 0;
				float freq1 = 0;
				ArrayList unionTask = new ArrayList();
				float freq2 = 0;
				
				
				for(Edge ed:g.getEdgeSet()){
					if(!ed.getId().contains("start")){
						if(!unionTask.contains(ed.getId())){
							unionTask.add(ed.getId());
						}
						
						for(Edge ed2:g2.getEdgeSet()){
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
					
				System.out.println("");
				System.out.println("");
				System.out.println("");
				System.out.println("");
				
				System.out.println("========================================================================");
				System.out.println("");
				System.out.println("-----> Total Tasks g size : "+g.getEdgeSet().size());
				System.out.println("-----> Total Tasks g2 size : "+g2.getEdgeSet().size());
				
			
				System.out.println("-----> Total Tasks : "+unionTask.size());
				System.out.println("-----> Common Tasks : "+freq2);
				if(unionTask.size()>0){
					freq1 = (float) freq2 / unionTask.size();  
				}			
				System.out.println("-----> Distance entre les plans : "+freq1);
				
				start2.setAttribute("ui.label", start2.getAttribute("ui.label")+" indice = "+freq1);
		
			
				//---------------------------------------------------------------------------------
			
				
			
			
				
				boolean secondNodeFound = false;
				for(Edge ed1:g2.getEachEdge()){
					boolean exist = false;
				
					if(!secondNodeFound)
					if(ed1.getSourceNode().getId().equals("start")){
							secondNode = ed1.getTargetNode().getId();
							secondNodeFound = true;
					}
		
					for(Edge ed2:g.getEachEdge()){
						if(ed1.getId().equals(ed2.getId())){
							if(!ed2.getSourceNode().getId().equals("start"))
							g.getEdge(ed1.getId()).addAttribute("ui.class", "shared");
							exist = true;
						} 
					} // fin de for
					if(!exist){
						g.addEdge(ed1.getId(), ed1.getSourceNode().getId(), ed1.getTargetNode().getId(), true );
						g.getEdge(ed1.getId()).addAttribute("ui.class", ed1.getAttribute("ui.class"));
						g.getNode(ed1.getSourceNode().getId()).addAttribute("ui.label", ed1.getSourceNode().getId());
						g.getNode(ed1.getTargetNode().getId()).addAttribute("ui.label", ed1.getTargetNode().getId());
					}
					
				} // fin de for
			
				//g.addNode(newStart);
				g.addEdge(newStart+secondNode, start.getId(), secondNode, true );
				//g.getEdge(newStart+secondNode).addAttribute("ui.class", "shared");
			
				start2.setAttribute("ui.label", start2.getAttribute("ui.label")+" indice = "+freq1);
				indice.setText("Indice 1= "+freq1);
				view.getCamera().resetView();
				
				
				
				
				
				//-------------------------------------------------------------------------------
				
				
				
			}
		});
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31","32","33","34","35","36","37","38","39","40","41","42","43","44","45","46","47","48","49","50","51","52","53","54","55","56","57","58","59","60","61","62","63","64","65","66","67","68","69","70","71","72","73","74","75","76","77","78","79","80","81","82","83","84","85","86","87","88","89","90","91","92","93","94","95","96","97","98","99","100","101","102","103","104","105","106","107","108","109","110","111","112","113","114","115","116","117","118","119","120","121","122","123","124","125","126","127","128","129","130","131","132","133","134","135","136","137","138","139","140","141","142","143","144","145","146","147","148","149","150","151","152","153","154","155","156","157","158","159","160","161","162","163","164","165","166","167","168","169","170","171","172","173","174","175","176","177","178","179","180","181","182","183","184","185","186","187","188","189","190","191","192","193","194","195","196","197","198","199","200","201","202","203","204","205","206","207","208","209","210","211","212","213","214","215","216","217","218","219","220","221","222","223","224","225","226","227","228","229","230","231","232","233","234","235","236","237","238","239","240","241","242","243","244","245","246","247","248","249","250","251","252","253","254","255","256","257","258","259","260","261","262","263","264","265","266","267","268","269","270","271","272","273","274","275","276","277","278","279","280","281","282","283","284","285","286","287","288","289","290","291","292","293","294","295","296","297","298","299","300","301","302","303","304","305","306","307","308","309","310","311","312","313","314","315","316","317","318","319","320","321","322","323","324","325","326","327","328","329","330","331","332","333","334","335","336","337","338","339","340","341","342","343","344","345","346","347","348","349","350","351","352","353","354","355","356","357","358","359","360","361","362","363","364","365","366","367","368","369","370","371","372","373","374","375","376","377","378","379","380","381","382","383","384","385","386","387","388","389","390","391","392","393","394","395","396","397","398","399","400","401","402","403","404","405","406","407","408","409","410","411","412","413","414","415","416","417","418","419","420","421","422","423","424","425","426","427","428","429","430","431","432","433","434","435","436","437","438","439","440","441","442","443","444","445","446","447","448","449","450","451","452","453","454","455","456","457","458","459","460","461","462","463","464","465","466","467","468","469","470","471","472","473","474","475","476","477","478","479","480","481","482","483","484","485","486","487","488","489","490","491","492","493","494","495","496","497","498","499","500","501","502","503","504","505","506","507","508","509","510","511","512","513","514","515","516","517","518","519","520","521","522","523","524","525","526","527","528","529","530","531","532","533","534","535","536","537","538","539","540","541","542","543","544","545","546","547","548","549","550","551","552","553","554","555","556","557","558","559","560","561","562","563","564","565","566","567","568","569","570","571","572","573","574","575","576","577","578","579","580","581","582","583","584","585","586","587","588","589","590","591","592","593","594","595","596","597","598","599","600","601","602","603","604","605","606","607","608","609","610","611","612","613","614","615","616","617","618","619","620","621","622","623","624","625","626","627","628","629","630","631","632","633","634","635","636","637","638","639","640","641","642","643","644","645","646","647","648","649","650","651","652","653","654","655","656","657","658","659","660","661","662","663","664","665","666","667","668","669","670","671","672","673","674","675","676","677","678","679","680","681","682","683","684","685","686","687","688","689","690","691","692","693","694","695","696","697","698","699","700","701","702","703","704","705","706","707","708","709","710","711","712","713","714","715","716","717","718","719","720","721","722","723","724","725","726","727","728","729","730","731","732","733","734","735","736","737","738","739","740","741","742","743","744","745","746","747","748","749","750","751","752","753","754","755","756","757","758","759","760","761","762","763","764","765","766","767","768","769","770","771","772","773","774","775","776","777","778","779","780","781","782","783","784","785","786","787","788","789","790","791","792","793","794","795","796","797","798","799","800","801","802","803","804","805","806","807","808","809","810","811","812","813","814","815","816","817","818","819","820","821","822","823","824","825","826","827","828","829","830","831","832","833","834","835","836","837","838","839","840","841","842","843","844","845","846","847","848","849","850","851","852","853","854","855","856","857","858","859","860","861","862","863","864","865","866","867","868","869","870","871","872","873","874","875","876","877","878","879","880","881","882","883","884","885","886","887","888","889","890","891","892","893","894","895","896","897","898","899","900","901","902","903","904","905","906","907","908","909","910","911","912","913","914","915","916","917","918","919","920","921","922","923","924","925","926","927","928","929","930","931","932","933","934","935","936","937","938","939","940","941","942","943","944","945","946","947","948","949","950","951","952","953","954","955","956","957","958","959","960","961","962","963","964","965","966","967","968","969","970","971","972","973","974","975","976","977","978","979","980","981","982","983","984","985","986","987","988","989","990","991","992","993","994","995","996","997","998","999","1000"}));
		comboBox_1.setMaximumRowCount(40);
		
		JLabel lblPlan = new JLabel("Plan 2 :");
		GroupLayout gl_panelGauche = new GroupLayout(panelGauche);
		gl_panelGauche.setHorizontalGroup(
			gl_panelGauche.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelGauche.createSequentialGroup()
					.addGroup(gl_panelGauche.createParallelGroup(Alignment.LEADING)
						.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblListeDesPlans, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPlan, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_panelGauche.setVerticalGroup(
			gl_panelGauche.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelGauche.createSequentialGroup()
					.addGap(24)
					.addComponent(lblListeDesPlans)
					.addGap(27)
					.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(20)
					.addComponent(lblPlan)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(271, Short.MAX_VALUE))
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
		
		
		indice.setForeground(Color.RED);
		indice.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JLabel lblAlternatives = new JLabel("Alt : ");
		
		
		altNbr.setFont(new Font("Tahoma", Font.BOLD, 14));
		altNbr.setForeground(Color.BLUE);
		
		JLabel lblGammaPl = new JLabel("Gamma Pl:");
		
		
		GammaPl.setFont(new Font("Tahoma", Font.BOLD, 14));
		GammaPl.setForeground(Color.MAGENTA);
		GroupLayout gl_panelBas = new GroupLayout(panelBas);
		gl_panelBas.setHorizontalGroup(
			gl_panelBas.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_panelBas.createSequentialGroup()
					.addGap(141)
					.addGroup(gl_panelBas.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelBas.createSequentialGroup()
							.addComponent(lblAlternatives)
							.addContainerGap())
						.addGroup(gl_panelBas.createSequentialGroup()
							.addGroup(gl_panelBas.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panelBas.createSequentialGroup()
									.addComponent(lblGammaPl)
									.addGap(10)
									.addGroup(gl_panelBas.createParallelGroup(Alignment.LEADING)
										.addComponent(GammaPl)
										.addComponent(altNbr)))
								.addComponent(indice, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED, 410, Short.MAX_VALUE)
							.addComponent(btnNewButton))))
		);
		gl_panelBas.setVerticalGroup(
			gl_panelBas.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelBas.createSequentialGroup()
					.addContainerGap(40, Short.MAX_VALUE)
					.addComponent(btnNewButton))
				.addGroup(gl_panelBas.createSequentialGroup()
					.addGroup(gl_panelBas.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblGammaPl)
						.addComponent(GammaPl))
					.addGap(2)
					.addGroup(gl_panelBas.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAlternatives)
						.addComponent(altNbr))
					.addGap(2)
					.addComponent(indice, GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
					.addGap(1))
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
		
		 addGraph(contentPane);
	}
	
// M�thode pour ajouter le graphe � la fen�tre : 
//----------------------------------------------
	public void addGraph(JPanel panel) throws FileNotFoundException{
		//g = new MultiGraph("embedded");
		g = pSet.getPlanByNbr(1);
		System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
		g.addAttribute("ui.stylesheet", "url('file:C:\\Planning/Modele1.css')");
		
		Node start = getRoot(g);
		start.setAttribute("xyz", 250,450,0);
		
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
	
	
	
	public void actionOnComboBox(JComboBox  comboBox) throws FileNotFoundException {
		
		int planId = (int)  Integer.parseInt((String) comboBox.getSelectedItem());
		Graph g2 = null;
	
			
		try { 	g2 = pSet.getPlanByNbr(planId);	} catch (FileNotFoundException e1) { e1.printStackTrace(); }	
				
		
		
	
		
		g.clear();
		for(Edge ed:g2.getEachEdge()){
			g.addEdge(ed.getId(), ed.getSourceNode().getId(), ed.getTargetNode().getId(), true );
			
			g.getEdge(ed.getId()).addAttribute("ui.class", ed.getAttribute("ui.class"));
			
			g.getNode(ed.getSourceNode().getId()).addAttribute("ui.label", ed.getSourceNode().getId());
			g.getNode(ed.getTargetNode().getId()).addAttribute("ui.label", ed.getTargetNode().getId());
		}
		
		Node start = getRoot(g);
		start.setAttribute("xyz", 250,450,0);
		start.setAttribute("ui.label", "11111");
		g.setStrict(false);
		g.setAutoCreate( true ); // cr�ation automatique des noeuds.
		g.addAttribute("ui.stylesheet", "url('file:C:\\Planning/Modele1.css')"); 
		g.addAttribute("ui.quality"); 
		g.addAttribute("ui.antialias");
		//g.getNode("start").setAttribute("ui.label", "start"); 

		
		
	
		
		// ...
//		view = viewer.addDefaultView(false);   // false indicates "no JFrame".
		// ...
	//	contentPane.remove((Component) view);
	//	contentPane.add((Component) view);
		view.getCamera().resetView();
	}
	public JScrollPane getScrollPane() {
		return getScrollPane();
	}
	
	
	
	


	//----------------------------------------------------------
	//- collecter la tache debut  
	public Node getRoot(Graph g){
				
		Node root = null;
		
		for(Node n:g) {
			if(n.getInDegree()==0){
				root = n;
				return n;
			}
		}
		 
		return root;
	} // fin de getRoot
	//----------------------------------------------------------


	//----------------------------------------------------------
	//- collecter la tache but  
	public Node getGoal(Graph g){
				
		Node root = null;
		
		for(Node n:g) {
			if(n.getOutDegree()==0){
				root = n;
				return n;
			}
		}
		 
		return root;
	} // fin de getGoal
	//----------------------------------------------------------
}
