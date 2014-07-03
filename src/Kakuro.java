public class Kakuro {		

	 
	
	private static Cella[][] matrice;

	public static void iniz_matrice(){
		int i,j;
		matrice = new Cella[8][8];
		for (i=0;i<8;i++) {
			for (j=0;j<8;j++) {
				matrice[i][j] = new Bianca(matrice,i,j);
			}
			matrice [i][0] = new Nera(matrice,i,0);
			matrice [0][i] = new Nera(matrice,0,i);
		}
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
		
		iniz_vincoli();
				
	
	} 
	
	
	
	
	
		
	private static void iniz_vincoli() {
		// Verticali
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
		
		// Orizzontali
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






	public static void main(String [] args) {
		iniz_matrice();
		int a=2;
		int b=3;
		matrice[a][b].calcPriorita();
		System.out.println(matrice[a][b].getPriorita());
	}
	


}
