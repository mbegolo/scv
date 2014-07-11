import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;


public class Kakuro {		
	private Cella[][] matrice;
	private ArrayList<Bianca> celleBianche;
	private Iterator<Bianca> iter;
	private LinkedList<Nodo> albero;
	private ListIterator <Nodo> listIter;
	private Nodo nodo = null;
	private Grafica g;
	
	private void iniz_matrice(){
			matrice = new Cella[8][8];
			for (int i=0;i<8;i++) {
				for (int j=0;j<8;j++) {
					matrice[i][j] = new Bianca(matrice,i,j);
				}
				matrice [i][0] = new Nera(matrice,i,0);
				matrice [0][i] = new Nera(matrice,0,i);
			}
			
			// Setto le caselle nere
			matrice[3][1]=new Nera(matrice,3,1);
			matrice[4][1]=new Nera(matrice,4,1);
			matrice[3][2]=new Nera(matrice,3,2);
			matrice[6][3]=new Nera(matrice,6,3);
			matrice[7][3]=new Nera(matrice,7,3);
			matrice[1][4]=new Nera(matrice,1,4);
			matrice[4][4]=new Nera(matrice,4,4);
			matrice[7][4]=new Nera(matrice,7,4);
			matrice[1][5]=new Nera(matrice,1,5);
			matrice[2][5]=new Nera(matrice,2,5);
			matrice[5][6]=new Nera(matrice,5,6);
			matrice[4][7]=new Nera(matrice,4,7);
			matrice[5][7]=new Nera(matrice,5,7);
			
			// Imposto vincoli Verticali
			matrice[1][0].setVincoloVert(new Vincolo(23));
			matrice[2][0].setVincoloVert(new Vincolo(30));
			matrice[5][0].setVincoloVert(new Vincolo(27));
			matrice[6][0].setVincoloVert(new Vincolo(12));
			matrice[7][0].setVincoloVert(new Vincolo(16));
			matrice[4][1].setVincoloVert(new Vincolo(17));
			matrice[3][2].setVincoloVert(new Vincolo(15));
			matrice[6][3].setVincoloVert(new Vincolo(12));
			matrice[4][4].setVincoloVert(new Vincolo(7));
			matrice[7][4].setVincoloVert(new Vincolo(7));
			matrice[1][5].setVincoloVert(new Vincolo(11));
			matrice[2][5].setVincoloVert(new Vincolo(10));
			
			// Imposto vincoli Orizzontali
			matrice[0][1].setVincoloOriz(new Vincolo(16));
			matrice[4][1].setVincoloOriz(new Vincolo(24));
			matrice[0][2].setVincoloOriz(new Vincolo(17));
			matrice[3][2].setVincoloOriz(new Vincolo(29));
			matrice[0][3].setVincoloOriz(new Vincolo(35));
			matrice[1][4].setVincoloOriz(new Vincolo(7));
			matrice[4][4].setVincoloOriz(new Vincolo(8));
			matrice[2][5].setVincoloOriz(new Vincolo(16));
			matrice[0][6].setVincoloOriz(new Vincolo(21));
			matrice[5][6].setVincoloOriz(new Vincolo(5));
			matrice[0][7].setVincoloOriz(new Vincolo(6));
			matrice[5][7].setVincoloOriz(new Vincolo(3));
		} 
		
	private void iniz_matrice1(){
			matrice = new Cella[8][8];
			for (int i=0;i<8;i++) {
				for (int j=0;j<8;j++) {
					matrice[i][j] = new Nera(matrice,i,j);
				}
			}
			
			// Setto le caselle nere
			matrice[3][1]=new Bianca(matrice,3,1);
			matrice[4][1]=new Bianca(matrice,4,1);
			matrice[1][2]=new Bianca(matrice,1,2);
			matrice[2][2]=new Bianca(matrice,2,2);
			matrice[3][2]=new Bianca(matrice,3,2);
			matrice[4][2]=new Bianca(matrice,4,2);
			matrice[1][3]=new Bianca(matrice,1,3);
			matrice[2][3]=new Bianca(matrice,2,3);
				
			// Imposto vincoli Verticali
			matrice[3][0].setVincoloVert(new Vincolo(3));
			matrice[4][0].setVincoloVert(new Vincolo(4));
			matrice[1][1].setVincoloVert(new Vincolo(6));
			matrice[2][1].setVincoloVert(new Vincolo(3));
					
			// Imposto vincoli Orizzontali
			matrice[2][1].setVincoloOriz(new Vincolo(3));
			matrice[0][2].setVincoloOriz(new Vincolo(10));
			matrice[0][3].setVincoloOriz(new Vincolo(3));
		} 
	
	
	static class  MyComparator implements Comparator<Bianca> {
		public int compare(Bianca o1, Bianca o2) {
			// TODO Auto-generated method stub
			if (o1.getPriorita() < o2.getPriorita()) {
				return -1;
			}
			else if (o1.getPriorita() > o2.getPriorita()) {
				return 1;
			}
			else return 0;
		}
	}
		
	void risali(){
		System.out.println("Inizia Backtracking");
		//nodo precedente
		nodo=listIter.next();
		System.out.println("Elaboro cella ("+ nodo.get_cord_x()+","+ nodo.get_cord_y()+")");
		if (nodo.isSentinel()){
			// Sono tornato alla radice, quindi nessun altro valore è possibile, quindi
			// il problema non è risolvibile
			System.out.println("FALLIMENTO! Il problema non è risolvibile");
		}
		else {
			nodo.add_valori_scartati_vincolo();
			nodo.ripristina_val_vincolo();
			nodo.azzera_val_scartati();
			nodo.domCella();
		}
	}
		
	public void backtraking(){
		this.risali();
		while (!nodo.assegnaValoreSuccessivo()) {
			System.out.println("Per questo nodo ho terminato i possibili valori");
			risali();
			nodo.add_num_celle();
			System.out.println("Torno indietro ad elaborare la cella di coordinate ("+ nodo.get_cord_x()+","+ nodo.get_cord_y()+")");
		}
		
		nodo.riduci_n_celle();
		nodo.riduci_val_vincoli();
		nodo.riduzione_domini();
		nodo.domCella();
		
		listIter.previous();
		nodo=listIter.previous();
	}
		
	public void forward_cheking(){
			System.out.println("Inizia ForwardChecking");
			System.out.println("Sulla cella ("+ nodo.get_cord_x()+","+ nodo.get_cord_y()+")");
			//calcolo intersezione Dx Dy
			nodo.domCella();
			while (nodo.dominio_cella.isEmpty()){
				System.out.println("Il dominio per la cella ("+ nodo.get_cord_x()+","+ nodo.get_cord_y()+") è vuoto");
				//System.out.println(listIter.next());
				backtraking();
				System.out.println("");
				System.out.println("Inizia ForwardChecking dopo aver eseguito Backtracking");
				System.out.println("Cella ("+ nodo.get_cord_x()+","+ nodo.get_cord_y()+")");
				nodo.domCella();
			}			
	
			//assegno il valore piu basso del dominio
			nodo.assegno_valore();
			//riduco il numero di celle libere nei 2 vincoli dopo assegnamento
			nodo.riduci_n_celle();
			//ricalcolo i vincoli
			nodo.riduci_val_vincoli();
			//calcolo domini nuovo vincolo
			nodo.riduzione_domini();
			
			if(listIter.hasPrevious()){
				nodo=listIter.previous();
				System.out.println("Procedo con la cella successiva, ("+ nodo.get_cord_x()+","+ nodo.get_cord_y()+")");
				forward_cheking();
			}
		
		}
		
	public Kakuro() {
		celleBianche = new ArrayList<Bianca>();
		albero = new LinkedList<Nodo>();
		
		iniz_matrice();
		//iniz_matrice1();
		
		g = new Grafica(matrice, albero, this);
		
		// Calcolo la priorità per tutte le celle bianche
		for (int i=1;i<8;i++) {
			for (int j=1;j<8;j++) {
				if (matrice[j][i].isWhite()){
					if (g.radio==1 || g.radio==2) //calcolo la priorità solo con bottone statico e dinamico
						matrice[j][i].calcPriorita(true);
					celleBianche.add( (Bianca) matrice[j][i]);
				}
			}
		}
		
		// Ordino il vettore PER LA PRIMA VOLTA in base alla priorità
		// Devo controllare le opzioni di ordinamento da interfaccia grafica
		// radio=1 dice che il vettore va ordinato solo all'inizio
		// radio=2 dice che il vettore va ordinato ad ogni nuova istanziazione di un valore (quindi anche all'inizio)
		if (g.radio==1 || g.radio==2) 	
			Collections.sort(celleBianche, new MyComparator());
		iter = celleBianche.iterator();
		
		// Se il vettore non va ordinato o va ordinato solo all'inizio
		// riduco tutti i domini
		if (g.radio==0 || g.radio==1) { //senza priorita  o priorita statica
			while(iter.hasNext()){
				Bianca variabile = iter.next();
				variabile.riduzione_domini();
			}
		}

		iter=celleBianche.iterator();
		albero.add(new Nodo());
		
		solve();
	}
	
	public void solve() {
		//fino a che ho celle bianche le estraggo e le inserisco nell'albero
		//per ogni cella bianca
		if (g.radio==0 || g.radio==1) { //senza priorita  o priorita statica
			while (iter.hasNext()) {
				albero.addFirst(new Nodo(iter.next()));
				listIter = albero.listIterator();
				nodo=listIter.next();
				listIter.previous();
				System.out.println("");
				//passo sempre l'ultimo nodo dell'albero per fare forward
				forward_cheking();
			}	
		}

		// Riordino la lista delle celle ancora da elaborare
		// (solo se è stato impostato da UI, quindi radio==2)
		if (g.radio==2) {
			while (iter.hasNext()) {
				albero.addFirst(new Nodo(iter.next()));
				listIter = albero.listIterator();
				nodo=listIter.next();
				listIter.previous();
				System.out.println("");
				//passo sempre l'ultimo nodo dell'albero per fare forward
				forward_cheking();
				iter.remove();
				System.out.println("!!!!!!!!!!!!!!!!1");
				while (iter.hasNext()){
					System.out.println("!!!!!!!!!!!!!!!!2");
					Bianca cella=iter.next();
					cella.calcPriorita(false);
				}
				Collections.sort(celleBianche, new MyComparator());
				iter=celleBianche.iterator();
			}
		}

		listIter = albero.listIterator();
		while (listIter.hasNext()) {	
		nodo=listIter.next();
			if (!nodo.isSentinel()){
				System.out.println("");
				System.out.println("NODO "+nodo.get_cord_x()+" "+nodo.get_cord_y());
				System.out.println("VALORE "+nodo.getValoreCella());
				System.out.println("");
			}
		}
		
		g = new Grafica(matrice, albero, this);
		g.repaint();
	}
	
	public static void main(String [] a) {
		Kakuro k = new Kakuro();
	}
}
