import javax.swing.*;

import java.awt.*;
import java.awt.Graphics.*;
import java.util.LinkedList;
import java.util.ListIterator;

public class Grafica extends JFrame {
	private Cella[][] matr;
	private LinkedList<Nodo> albero;
	private JLabel[][] valori = new JLabel[8][8];
	JPanel table;
	JPanel mainPan;
	
	public Grafica(Cella[][] matr, LinkedList<Nodo> albero) throws NullPointerException {
		super("Kakuro Solver");
		mainPan = new JPanel();
		add(mainPan);
		mainPan.setPreferredSize(new Dimension(400, 420));
		setLocation(300,300);
		this.matr = matr;
		this.albero = albero;
		
		setLayout(new GridLayout(2,1,30,30));
		
		table = new JPanel();
		table.setLayout(new GridLayout(8,8,3,3));
		table.setBackground(Color.black);
		JButton solveButton = new JButton("Solve this puzzle");
		
		solveButton.setPreferredSize(new Dimension(400, 20));
		table.setPreferredSize(new Dimension(400,400));
		// Inverto righe e colonne!!!!
		for (int i=0; i<8; i++) {
			for (int j=0; j<8; j++) {
				Cella cella = matr[j][i];
				JPanel c;
				if (cella.isBlack()) {
					JPanel cellaNera = new JPanel();
					cellaNera.setLayout(new GridLayout(2,2));
					cellaNera.setBackground(Color.darkGray);
					cellaNera.setForeground(Color.white);
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
					
					JLabel l1 = new JLabel("\\");
					JLabel l2 = new JLabel("\\");
					if ((oriz.getText() == "")&&(vert.getText() == "")) {
						l1.setText("");
						l2.setText("");
					}
					
					l1.setForeground(Color.lightGray);
					cellaNera.add(l1);
					
					oriz.setForeground(Color.lightGray);
					cellaNera.add(oriz);
					
					vert.setForeground(Color.lightGray);
					cellaNera.add(vert);
					
					l2.setForeground(Color.lightGray);
					cellaNera.add(l2);
					
					c=cellaNera;
				}
				else {
					JPanel cellaBianca = new JPanel();
					cellaBianca.setLayout(new GridLayout(1,1));
					cellaBianca.setBackground(Color.white);
					JLabel valore = new JLabel("this cell value");
					cellaBianca.add(valore);
					c=cellaBianca;
					valori[j][i] = valore;
				}
				table.add(c);
			}
		}
		
		
		add(table);
		add(solveButton);
		pack();
		setVisible(true);
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