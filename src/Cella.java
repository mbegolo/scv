
public abstract class Cella {
	public Cella[][] matrice;
	
	public abstract boolean isBlack();
	public abstract boolean isWhite();

	void setVincoloVert(Vincolo vert) {
		if (this.isBlack()) this.setVincoloVert(vert);
	}
	
	void setVincoloOriz(Vincolo oriz) {
		if (this.isBlack()) this.setVincoloOriz(oriz);
	}
	
	void setVincoli(Vincolo vert, Vincolo oriz) {
		if (this.isBlack()) this.setVincoli(vert,oriz);
	}
	
	public abstract Vincolo getVincoloOriz();
	
	public abstract Vincolo getVincoloVert();
	
	public abstract void calcPriorita();
	
	public abstract int getPriorita();
	
	
	
}