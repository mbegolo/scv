
public class Bianca extends Cella {
	
	//public int valore;
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
	
	public void calcPriorita(Boolean u) {
		int colonna = 0;    
		int riga = 0;
		int x = cord_x;
		int y = cord_y;
		//System.out.println("cor_x " +x+ " Cord_y " + y);
	
		while (matrice[x][cord_y].isWhite()) x--;
	
		x++;
		
		while ( x<8 && matrice[x][cord_y].isWhite() ) {
			
			riga++;
			x++;
		}
		if (u) matrice[cord_x][cord_y].getVincoloOriz().set_num_celle(riga);
		
		
		//System.out.println("numero celle riga "+matrice[cord_x][cord_y].getVincoloOriz().get_num_celle());
		
		while (matrice[cord_x][y].isWhite()) y--;
		
		y++;
		
		while ( y<8 && matrice[cord_x][y].isWhite() )
		{ 
			colonna++;
			y++;
		}
		
		
		
		if (u) matrice[cord_x][cord_y].getVincoloVert().set_num_celle(colonna);
		
		//System.out.println("numero celle colonna "+ matrice[cord_x][cord_y].getVincoloVert().get_num_celle());
		
		priorita = Math.max(riga, colonna);
		
		//System.out.println("PRIORITA "+priorita);
		
	}
	
	public int getPriorita() {
		return priorita;
	}
	
	public void riduzione_domini() {
		// TODO Auto-generated method stub
		Vincolo vincolo_oriz;
		Vincolo vincolo_vert;
		
		
		boolean vinc_vert=true;
		boolean vinc_oriz=true;
		
		vincolo_oriz = this.getVincoloOriz();
		vincolo_vert = this.getVincoloVert();
		
		
		
		vinc_oriz=riduzione_con_tabella(vincolo_oriz);
		vinc_vert=riduzione_con_tabella(vincolo_vert);	
		
		/*System.out.println("valore vincolo "+ vincolo_oriz.getVal()+" "+ " "+vincolo_vert.getVal());
		System.out.println("Bool "+ vinc_oriz+ " "+" "+ vinc_vert);
		System.out.println("Dominio variabili");
		vincolo_oriz.get_dominio();
		System.out.println("----");
		vincolo_vert.get_dominio();
		*/
		/*System.out.println("valore vincolo "+ vincolo_oriz.getVal()+" "+ " "+vincolo_vert.getVal());
		System.out.println("Riduzione classica? "+ vinc_oriz+ " "+" "+ vinc_vert);
		System.out.println("Dominio variabili");
		*/
		
		
		if (vinc_oriz && vinc_vert){
			riduzione_classic(vincolo_oriz);
			riduzione_classic(vincolo_vert);
		}
		else if (vinc_oriz && !vinc_vert)
			riduzione_classic(vincolo_oriz);
		
		else if (!vinc_oriz && vinc_vert)
			riduzione_classic(vincolo_vert);
		
		
		/*
		vincolo_oriz.stampa_dominio();
		
		System.out.println("----");
		
		vincolo_vert.stampa_dominio();
		*/
		
	}

	private void riduzione_classic(Vincolo v) {
		// TODO Auto-generated method stub
		
		int valore_vincolo;
		int numero_celle;
		int max;
		int s;
		int min;
		int i;
		
		valore_vincolo = v.getVal();
		numero_celle = v.get_num_celle();
		
		int temp=0;
	
		for (i=1; i<numero_celle;i++)
			
			temp=temp+i;
		
		max= valore_vincolo - temp;
		//System.out.println("!!!!");
		
		//System.out.println("numero celle"+ numero_celle);
		s=Math.min (max+1, 10);
		//System.out.println("temp "+temp+" max "+max+" s "+s);
		
		temp=0;
		for (i=1; i<numero_celle;i++)
			
			temp=temp+(s-i);
		
		min = valore_vincolo - temp;
		//System.out.println("temp "+temp+" s "+s);
		
		//System.out.println("min "+min);
		
		if (min>max || max<min){
			
			v.add_dominio(1);
			v.add_dominio(2);
			v.add_dominio(3);
			v.add_dominio(4);
			v.add_dominio(5);
			v.add_dominio(6);
			v.add_dominio(7);
			v.add_dominio(8);
			v.add_dominio(9);
		
		}
		else {
			
			for (i=min;i<=max;i++)
			
				if(i>0&&i<10)
				v.add_dominio(i);
		}
		
		
		//System.out.println("MIN "+ min+ " MAX "+max + " s "+ s);
		
		
		
	}

	private boolean riduzione_con_tabella(Vincolo v) {
		// TODO Auto-generated method stub
		boolean flag=true;
		int valore_vincolo;
		int numero_celle;
	
		
		valore_vincolo = v.getVal();
		numero_celle = v.get_num_celle();
		
		
	switch (valore_vincolo){
		
		case 1: 	
			
			if (numero_celle==1){
			v.add_dominio(1);
			flag=false;
		}
			break;
	
			
		case 2: 	
			
			if (numero_celle==1){
			v.add_dominio(2);
			flag=false;
		}
			break;
		
		case 3: 
			if (numero_celle==2){
				v.add_dominio(1);
				v.add_dominio(2);
				flag=false;
			}else if(numero_celle==1){
				
				v.add_dominio(3);
				flag=false;
			}
				
				
			break;
				
				
		case 4: 
				if (numero_celle==2){
				v.add_dominio(1);
				v.add_dominio(3);
				flag=false;
				}
				else if(numero_celle==1){
					
					v.add_dominio(4);
					flag=false;
				}
		break;
		
		
		case 5: 	
			
			if (numero_celle==1){
			v.add_dominio(5);
			flag=false;
		}
			break;
				
		case 16: 
				
				if (numero_celle==2){	
				 v.add_dominio(7);
				 v.add_dominio(9);
				 flag=false;
				}
				
			
				else if (numero_celle==5){
				
				v.add_dominio(1);
				v.add_dominio(2);
				v.add_dominio(3);
				v.add_dominio(4);
				v.add_dominio(6);
				flag=false;
				}
		
			break;
		
		case 17:
				if (numero_celle==2){
				v.add_dominio(8);
				v.add_dominio(9);
				flag=false;
				}
		break;
		
		case 6: if (numero_celle==3){
			
				v.add_dominio(1);
				v.add_dominio(2);
				v.add_dominio(3);
				flag=false;
				}else if(numero_celle==1){
					
					v.add_dominio(6);
					flag=false;
				}
				
		break;
		
		case 7: 
				
			if (numero_celle==3){
				
				v.add_dominio(1);
				v.add_dominio(2);
				v.add_dominio(4);
				flag=false;
			}else if(numero_celle==1){
				
				v.add_dominio(7);
				flag=false;
			}
		break;
		
		
		case 8: 	
			
			if (numero_celle==1){
			v.add_dominio(8);
			flag=false;
		}
			break;
			
		case 9: 	
			
			if (numero_celle==1){
			v.add_dominio(9);
			flag=false;
		}
			break;
		
		case 23: 
			
			if (numero_celle==3){
				
				v.add_dominio(6);
				v.add_dominio(8);
				v.add_dominio(9);
				flag=false;
			}
		break;
		
		case 24: 
				
			if (numero_celle==3){
				
				v.add_dominio(7);
				v.add_dominio(8);
				v.add_dominio(9);
				flag=false;
			}
		break;
			
				
		
		case 10: 
			if (numero_celle==4){
				
				v.add_dominio(1);
				v.add_dominio(2);
				v.add_dominio(3);
				v.add_dominio(4);
				flag=false;
			}
		break;
		
		case 11: 
			
			if (numero_celle==4){
				
				v.add_dominio(1);
				v.add_dominio(2);
				v.add_dominio(3);
				v.add_dominio(5);
				flag=false;
				
			}
				
		break;
		
		case 29:
				if (numero_celle==4){
				
				v.add_dominio(5);
				v.add_dominio(7);
				v.add_dominio(8);
				v.add_dominio(9);
				flag=false;
				
				}
				
		break;
		
		case 30: 
			
			if (numero_celle==4){
				
				v.add_dominio(6);
				v.add_dominio(7);
				v.add_dominio(8);
				v.add_dominio(9);
				flag=false;
			}
		break;
		
		case 15: 
			
			if (numero_celle==5){
				v.add_dominio(1);
				v.add_dominio(2);
				v.add_dominio(3);
				v.add_dominio(4);
				v.add_dominio(5);
				flag=false;
			}
		
		break;
		
		
		
		case 34: 
			
			if (numero_celle==5){
				v.add_dominio(4);
				v.add_dominio(6);
				v.add_dominio(7);
				v.add_dominio(8);
				v.add_dominio(9);
				flag=false;
			}
		
		break;
		
		case 35: 
			
			if (numero_celle==5)
			{
				v.add_dominio(5);
				v.add_dominio(6);
				v.add_dominio(7);
				v.add_dominio(8);
				v.add_dominio(9);
				flag=false;
			}
				
		break;
		
		case 21: 
			if (numero_celle==6)
			{
				v.add_dominio(1);
				v.add_dominio(2);
				v.add_dominio(3);
				v.add_dominio(4);	
				v.add_dominio(5);
				v.add_dominio(6);
				flag=false;
			}
		break;
		
		case 22: 
			if (numero_celle==6)
			{
			
				v.add_dominio(1);
				v.add_dominio(2);
				v.add_dominio(3);
				v.add_dominio(4);
				v.add_dominio(5);
				v.add_dominio(7);
				flag=false;
			}	
				
		break;
		
		case 38: 
			if (numero_celle==6)
			{
		
				v.add_dominio(3);
				v.add_dominio(5);
				v.add_dominio(6);
				v.add_dominio(7);
				v.add_dominio(8);
				v.add_dominio(9);
				flag=false;
			}		
				
		break;
		
		case 39: 
			
			if (numero_celle==6)
			{
			v.add_dominio(4);
			v.add_dominio(5);
			v.add_dominio(6);
			v.add_dominio(7);
			v.add_dominio(8);
			v.add_dominio(9);
			flag=false;
			}
				
		break;
		
		case 41: 
			
			if (numero_celle==7)
			{
				v.add_dominio(2);
				v.add_dominio(4);
				v.add_dominio(5);
				v.add_dominio(6);
				v.add_dominio(7);
				v.add_dominio(8);
				v.add_dominio(9);
				flag=false;
			}	
				
				
				break;
		
		case 42: 
		
			if (numero_celle==7){
				
				v.add_dominio(3);
				v.add_dominio(4);
				v.add_dominio(5);
				v.add_dominio(6);
				v.add_dominio(7);
				v.add_dominio(8);
				v.add_dominio(9);
				flag=false;
			}
				
		break;

		
		
		}
	
	
		
		return flag;
	
	
	
	
	}
	
	public int get_cord_x() {
		return cord_x;
	}

	public int get_cord_y() {
		return cord_y;
	}







}
