import javax.swing.*;

import java.awt.*;
import java.awt.Graphics.*;
import java.util.LinkedList;
import java.util.ListIterator;
import java.awt.event.*;
import java.awt.geom.*;

public class Grafica extends JFrame {
	private Cella[][] matr;
	private LinkedList<Nodo> albero;
	private JLabel[][] valori;
	private JRadioButton radio1, radio2, radio3;
	public int radio = 0;
	private JPanel[][] table;
	private JPanel mainPan;
	private int matrSize;
	private Kakuro puzzle;
	
	public Grafica(Cella[][] matr, LinkedList<Nodo> albero, Kakuro k) throws NullPointerException {
		super("Kakuro Solver");
		setLocation(200,50);
		setSize(500,600);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.matr = matr;
		this.albero = albero;
		this.puzzle = k;
		matrSize = matr.length;
		
		mainPan = new JPanel();
		mainPan.setLayout(new BoxLayout(mainPan, BoxLayout.Y_AXIS));
		mainPan.setPreferredSize(new Dimension(200,200));
		
		valori = new JLabel[matrSize][matrSize];
		table = new JPanel[matrSize][matrSize];
		
		JPanel tableContainer = new JPanel();
		tableContainer.setLayout(new FlowLayout());
		JPanel tableGrid = new JPanel();
		tableContainer.add(tableGrid);
		tableGrid.setLayout(new GridLayout(matrSize,matrSize,1,1));
		tableGrid.setBackground(Color.black);
		tableGrid.setPreferredSize(new Dimension(300,300));
		
		for (int i=0; i<matrSize; i++) {
			for (int j=0; j<matrSize; j++) {
				table[i][j] = new JPanel();
				table[i][j].setBackground(Color.white);
				table[i][j].setPreferredSize(new Dimension(200,200));
				table[i][j].setLayout(new GridLayout(1,1));
				tableGrid.add(table[i][j]);
			}
		}
		
		// Compongo il pannello per controllare il tipo di metodo di ordinamento
		JPanel controllerPanel = new JPanel();
		controllerPanel.setLayout(new FlowLayout());
		JButton solveButton = new JButton("Risolvi questo problema");
		JPanel methodSelector = new JPanel();
		ButtonGroup bgroup = new ButtonGroup();
		methodSelector.setLayout(new BoxLayout(methodSelector, BoxLayout.Y_AXIS));
		radio1 = new JRadioButton("Nessuno",true);
		radio2 = new JRadioButton("Solo all'inizio",false);
		radio3 = new JRadioButton("Ogni volta che viene istanziata una variabile", false);
		bgroup.add(radio1);
		bgroup.add(radio2);
		bgroup.add(radio3);
		methodSelector.add(new JLabel("Seleziona il metodo di ordinamento delle variabili:"));
		methodSelector.add(radio1);
		methodSelector.add(radio2);
		methodSelector.add(radio3);
		
		controllerPanel.add(methodSelector);
		controllerPanel.add(solveButton);
		
		mainPan.add(tableContainer);
		mainPan.add(controllerPanel);
		
		getValuesFromMatrix();
		
		add(mainPan);
		setVisible(true);
		
		// Eventi
		radio1.addActionListener(new ActionListener(){
		    public void actionPerformed(ActionEvent e) {
		    	radio = 0;
		    }
		});
		radio2.addActionListener(new ActionListener(){
		    public void actionPerformed(ActionEvent e) {
		    	radio = 1;
		    }
		});
		radio3.addActionListener(new ActionListener(){
		    public void actionPerformed(ActionEvent e) {
		    	radio = 3;
		    }
		});
		solveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				puzzle.solve();
			}
		});
	}
	
	public void getValuesFromMatrix() {
		for (int i=0; i<matrSize; i++) {
			for (int j=0; j<matrSize; j++) {
				Cella cella = matr[j][i];
				if (cella.isBlack()) {
					JPanel cellaNera = new JPanel();
					cellaNera.setLayout(new GridLayout(2,2));
					cellaNera.setBackground(Color.darkGray);
					cellaNera.setForeground(Color.white);
					cellaNera.setPreferredSize(new Dimension(200,200));
					JLabel oriz = new JLabel("");
					JLabel vert = new JLabel("");
					
					try {
						oriz = new JLabel(""+cella.getVincoloOriz().getValOrig(), SwingConstants.RIGHT);						
					}
					catch(NullPointerException e){}
					try {
						vert = new JLabel(""+cella.getVincoloVert().getValOrig());						
					}
					catch(NullPointerException e){}
					
					JPanel l1,l2;
					if ((oriz.getText() != "")||(vert.getText() != "")) {
						l1 = new JPanel(){
							public void paintComponent(Graphics g){
						        g.drawLine(0,0,100,100);
							}
						};
						l2 = new JPanel(){
							public void paintComponent(Graphics g){
						        g.drawLine(0,0,100,100);
							}
						};
					}
					else {
						l1 = new JPanel();
						l2 = new JPanel();
						l1.setBackground(Color.darkGray);
						l2.setBackground(Color.darkGray);
					}
					
					l1.setForeground(Color.lightGray);
					cellaNera.add(l1);
					
					oriz.setForeground(Color.lightGray);
					cellaNera.add(oriz);
					
					vert.setForeground(Color.lightGray);
					cellaNera.add(vert);
					
					l2.setForeground(Color.lightGray);
					cellaNera.add(l2);
					
					table[i][j].add(cellaNera);
				}
				else {
					JPanel cellaBianca = new JPanel();
					cellaBianca.setLayout(new GridLayout(1,1));
					cellaBianca.setBackground(Color.white);
					JLabel valore = new JLabel("");
					cellaBianca.add(valore);
					table[i][j].add(cellaBianca);
					valori[j][i] = valore;
				}
			}
		}
	}
	
	public void repaint() {
		Nodo n;		
		ListIterator<Nodo> listIter = albero.listIterator();
		while (listIter.hasNext()) {	
		n=listIter.next();
			if (!n.isSentinel()){
				valori[n.get_cord_x()][n.get_cord_y()].setText(""+n.getValoreCella());
			}
		}	
	}
}