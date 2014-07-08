
public class Nera extends Cella {

	private int cord_x,cord_y;
	private Vincolo vert; 
	private Vincolo oriz;
	
	
	
	public Nera(Cella[][] m, int x, int y){
		matrice = m;
		this.cord_x = x;
		this.cord_y = y;
		this.vert=null;
		this.oriz=null;
		
		
	}
	
	public boolean isBlack() {
		return true;
	}
	public boolean isWhite() {
		return false;
	}
	
	public void setVincoloVert(Vincolo vert){
		this.vert=vert;
	}
	public void setVincoloOriz(Vincolo oriz){
		this.oriz=oriz;
	}
	
	public void setVincoli(Vincolo vert, Vincolo oriz){
		this.vert=vert;
		this.oriz=oriz;
	}
	
	public Vincolo getVincoloOriz() {
		return oriz;
	}
	
	public Vincolo getVincoloVert() {
		return vert;
	}
	
	public int getX(){
		return cord_x;
	}
	
	public int getY(){
		return cord_y;
	}
	
	public void calcPriorita(Boolean u){	}
	
	public int getPriorita() {
		return 0;
	}
	
}
