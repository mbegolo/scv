
public class Bianca extends Cella {
	private int valore;
	private int cord_x,cord_y;
	private int priorita;


	public Bianca (Cella[][] m, int cord_x, int cord_y){
		matrice=m;
		this.cord_x = cord_x;
		this.cord_y = cord_y;
	}

	public boolean isBlack() {
		return false;
	}
	public boolean isWhite() {
		return true;
	}
	
	public Vincolo getVincoloOriz() {
		int x = cord_x;
		while ((matrice[x][cord_y].isWhite())&&(x>0)) {
			x--;
		}
		return matrice[x][cord_y].getVincoloOriz();
	}
	
	public Vincolo getVincoloVert() {
		int y = cord_y;
		while ((matrice[cord_x][y].isWhite())&&(y>0)) {
			y--;
		}
		return matrice[cord_x][y].getVincoloVert();
	}
	
	public void calcPriorita() {
		int colonne = 0;
		int righe = 0;
		int x = cord_x;
		int y = cord_y;
		while (matrice[x][cord_y].isWhite()) x--;
		x++;
		while (matrice[x+colonne][cord_y].isWhite()) colonne++;
		while (matrice[cord_x][y].isWhite()) y--;
		y++;
		while (matrice[cord_x][y+righe].isWhite()) righe++;
		priorita = Math.max(x, y);
	}
	
	public int getPriorita() {
		return priorita;
	}








}
