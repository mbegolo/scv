import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;


public class Nodo {
	
	private boolean sentinella;
	private int valore_cella;
	
	
	public SortedSet<Integer> dominio_cella=new TreeSet<Integer>();
	
	private int valorevincolox;
	private int valorevincoloy;
	
	private Vincolo vincolox;
	private Vincolo vincoloy;
	
	private SortedSet<Integer> dominio_vincolox=new TreeSet<Integer>();
	private SortedSet<Integer> dominio_vincoloy=new TreeSet<Integer>();
	
	private int cord_x;
	private int cord_y;
	
	
	private int num_cellex;
	private int num_celley;
	
	private SortedSet<Integer> valori_scartatix=new TreeSet<Integer>();
	private SortedSet<Integer> valori_scartatiy=new TreeSet<Integer>();
	private SortedSet<Integer> dominio_tempx=new TreeSet<Integer>();
	private SortedSet<Integer> dominio_tempy=new TreeSet<Integer>();
	
	
	public Nodo(){
		sentinella=true;
	}
	
	public Nodo(Bianca cella){
		
		sentinella=false;
		
		cord_x=cella.get_cord_x();
		cord_y=cella.get_cord_y();
		
		vincolox=cella.getVincoloOriz();
		valorevincolox=vincolox.getVal();
		
		vincoloy=cella.getVincoloVert();
		valorevincoloy=vincoloy.getVal();
		
		dominio_vincolox.addAll(vincolox.dominio);
		dominio_vincoloy.addAll(vincoloy.dominio);
		
		num_cellex=vincolox.get_num_celle();
		num_celley=vincoloy.get_num_celle();
	
	}

	public boolean isSentinel(){
		if(this.sentinella)return true;
		else return false;
	}
	
	public boolean is_vinc_vuoto(){
		if (dominio_vincolox.isEmpty() && num_cellex>0)
			return true;
		else if (dominio_vincoloy.isEmpty() && num_celley>0)
			return true;
		else return false;
	} 
	
	public void domCella(){
		valorevincolox=vincolox.getVal();
		valorevincoloy=vincoloy.getVal();
		
		System.out.println("Valore vincolo " +valorevincolox+" "+valorevincoloy);
		
		if(!dominio_cella.isEmpty()){
			dominio_cella.removeAll(dominio_cella);
		}
		
		if(!dominio_vincolox.isEmpty()){
			dominio_vincolox.removeAll(dominio_vincolox);
		}
		
		if(!dominio_vincoloy.isEmpty()){
			dominio_vincoloy.removeAll(dominio_vincoloy);
		}
		
		dominio_vincolox.addAll(vincolox.dominio);
		dominio_vincoloy.addAll(vincoloy.dominio);
		
		System.out.println("dominio vincolo x "+dominio_vincolox);
		System.out.println("dominio vincolo y "+ dominio_vincoloy);
		
		//System.out.println(dominio_cella);
		dominio_cella.addAll(dominio_vincolox);
		dominio_cella.retainAll(dominio_vincoloy);
		System.out.println("intersezione "+dominio_cella);
		
		
	}
	
	public void assegno_valore(){
			valore_cella=dominio_cella.first();
			System.out.println("VALORE CELLA "+valore_cella);
	}
	
	public void riduci_n_celle(){
	num_cellex=(vincolox.get_num_celle()-1);
	num_celley=(vincoloy.get_num_celle()-1);
	
	//aggiorno le celle del vincolo
	vincolox.set_num_celle(num_cellex);
	vincoloy.set_num_celle(num_celley);
	
	System.out.println("numero celle x " + num_cellex+" numero celle y "+num_celley);
	}

	public void riduci_val_vincolo(){
		valorevincolox=valorevincolox-valore_cella;
		valorevincoloy=valorevincoloy-valore_cella;
		
		//aggiorno il vincolo
		vincolox.set_valore(valorevincolox);
		vincoloy.set_valore(valorevincoloy);
		
		System.out.println("nuovo valore vincolo x "+ valorevincolox);		
		System.out.println("nuovo valore vincolo y "+ valorevincoloy);

	}
	
	public void riduzione_dominio() {
		// TODO Auto-generated method stub
		
		boolean vinc_vert=true;
		boolean vinc_oriz=true;
		
		//insieme dei domini vecchi
		if (!valori_scartatix.isEmpty())
			valori_scartatix.removeAll(valori_scartatix);

		if (!valori_scartatiy.isEmpty())
			valori_scartatiy.removeAll(valori_scartatiy);
		
		//metto in valori scartati il dom dei vincoli 
		valori_scartatix.addAll(dominio_vincolox);
		valori_scartatiy.addAll(dominio_vincoloy);
		
		
		
		//svuoto dominio dei vincoli per calcolare quelli nuovi
		if (!dominio_tempx.isEmpty())
			dominio_tempx.removeAll(dominio_tempx);
		if (!dominio_tempy.isEmpty())
			dominio_tempy.removeAll(dominio_tempy);
	
		//System.out.println("SONO VUOTI?" + dominio_vincolox.isEmpty()+dominio_vincoloy.isEmpty());
		
		//calcolo domini nuovi
		vinc_oriz=riduzione_con_tabella(dominio_tempx, 0);
		vinc_vert=riduzione_con_tabella(dominio_tempy, 1);	
		
		
		System.out.println("BOOL" +vinc_oriz+ " per nuovo vincolo "+valorevincolox+" BOOL " +vinc_vert+ " per nuovo vincolo "+valorevincoloy);
		
		if (vinc_oriz && vinc_vert){
			riduzione_classic(dominio_tempx, 0);
			riduzione_classic(dominio_tempy, 1);
		}
		else if (vinc_oriz && !vinc_vert)
			riduzione_classic(dominio_tempx, 0);
		
		else if (!vinc_oriz && vinc_vert)
			riduzione_classic(dominio_tempy, 1);
		
		
		
		if(dominio_tempx.contains(valore_cella))
			dominio_tempx.remove(valore_cella);
		
		if(dominio_vincoloy.contains(valore_cella))
			dominio_tempy.remove(valore_cella);
		
		
		//calcolol i valori scartati
		valori_scartatix.removeAll(dominio_tempx);
		valori_scartatiy.removeAll(dominio_tempy);
		
		dominio_vincolox.removeAll(valori_scartatix);
		dominio_vincoloy.removeAll(valori_scartatiy);
		
		
		//aggiorno il vincolo
		vincolox.set_dominio(dominio_vincolox);
		vincoloy.set_dominio(dominio_vincoloy);
		
		
		System.out.println("Nuovo dominio vincolo x "+dominio_vincolox+ " valori scartati "+ valori_scartatix);
		System.out.println("Nuovo dominio vincolo y "+dominio_vincoloy+ " valori scartati "+ valori_scartatiy);

		
		/*
		vincolo_oriz.stampa_dominio();
		
		System.out.println("----");
		
		vincolo_vert.stampa_dominio();
		*/
		
	}

	private void riduzione_classic(SortedSet<Integer> v, int p) {
		// TODO Auto-generated method stub
		
		int valore_vincolo;
		int numero_celle;
		int max;
		int s;
		int min;
		int i;
		
		if(p==1){
		
			valore_vincolo = valorevincoloy;
			numero_celle = num_celley;
		}
		else
		{
			valore_vincolo = valorevincolox;
			numero_celle=num_cellex;
		}
		
		
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
			
			v.add(1);
			v.add(2);
			v.add(3);
			v.add(4);
			v.add(5);
			v.add(6);
			v.add(7);
			v.add(8);
			v.add(9);
		
		}
		else {
			
			for (i=min;i<=max;i++)
			
				if(i>0&&i<10)
				v.add(i);
		}
		
		
		//System.out.println("MIN "+ min+ " MAX "+max + " s "+ s);
		
		
		
	}

	private boolean riduzione_con_tabella(SortedSet<Integer> v, int p) {
		// TODO Auto-generated method stub
		boolean flag=true;
		int valore_vincolo;
		int numero_celle;
	
		
		if(p==1){
			
			valore_vincolo = valorevincoloy;
			numero_celle = num_celley;
		}
		else
		{
			valore_vincolo = valorevincolox;
			numero_celle=num_cellex;
		}
		
	switch (valore_vincolo){
		
		case 1: 	
			
			if (numero_celle==1){
			v.add(1);
			flag=false;
		}
			break;
	
			
		case 2: 	
			
			if (numero_celle==1){
			v.add(2);
			flag=false;
		}
			break;
		
		case 3: 
			if (numero_celle==2){
				v.add(1);
				v.add(2);
				flag=false;
			}else if(numero_celle==1){
				
				v.add(3);
				flag=false;
			}
				
				
			break;
				
				
		case 4: 
				if (numero_celle==2){
				v.add(1);
				v.add(3);
				flag=false;
				}
				else if(numero_celle==1){
					
					v.add(4);
					flag=false;
				}
		break;
		
		
		case 5: 	
			
			if (numero_celle==1){
			v.add(5);
			flag=false;
		}
			break;
				
		case 16: 
				
				if (numero_celle==2){	
				 v.add(7);
				 v.add(9);
				 flag=false;
				}
				
			
				else if (numero_celle==5){
				
				v.add(1);
				v.add(2);
				v.add(3);
				v.add(4);
				v.add(6);
				flag=false;
				}
		
			break;
		
		case 17:
				if (numero_celle==2){
				v.add(8);
				v.add(9);
				flag=false;
				}
		break;
		
		case 6: if (numero_celle==3){
			
				v.add(1);
				v.add(2);
				v.add(3);
				flag=false;
				}else if(numero_celle==1){
					
					v.add(6);
					flag=false;
				}
				
		break;
		
		case 7: 
				
			if (numero_celle==3){
				
				v.add(1);
				v.add(2);
				v.add(4);
				flag=false;
			}else if(numero_celle==1){
				
				v.add(7);
				flag=false;
			}
		break;
		
		
		case 8: 	
			
			if (numero_celle==1){
			v.add(8);
			flag=false;
		}
			break;
			
		case 9: 	
			
			if (numero_celle==1){
			v.add(9);
			flag=false;
		}
			break;
		
		case 23: 
			
			if (numero_celle==3){
				
				v.add(6);
				v.add(8);
				v.add(9);
				flag=false;
			}
		break;
		
		case 24: 
				
			if (numero_celle==3){
				
				v.add(7);
				v.add(8);
				v.add(9);
				flag=false;
			}
		break;
			
				
		
		case 10: 
			if (numero_celle==4){
				
				v.add(1);
				v.add(2);
				v.add(3);
				v.add(4);
				flag=false;
			}
		break;
		
		case 11: 
			
			if (numero_celle==4){
				
				v.add(1);
				v.add(2);
				v.add(3);
				v.add(5);
				flag=false;
				
			}
				
		break;
		
		case 29:
				if (numero_celle==4){
				
				v.add(5);
				v.add(7);
				v.add(8);
				v.add(9);
				flag=false;
				
				}
				
		break;
		
		case 30: 
			
			if (numero_celle==4){
				
				v.add(6);
				v.add(7);
				v.add(8);
				v.add(9);
				flag=false;
			}
		break;
		
		case 15: 
			
			if (numero_celle==5){
				v.add(1);
				v.add(2);
				v.add(3);
				v.add(4);
				v.add(5);
				flag=false;
			}
		
		break;
		
		
		
		case 34: 
			
			if (numero_celle==5){
				v.add(4);
				v.add(6);
				v.add(7);
				v.add(8);
				v.add(9);
				flag=false;
			}
		
		break;
		
		case 35: 
			
			if (numero_celle==5)
			{
				v.add(5);
				v.add(6);
				v.add(7);
				v.add(8);
				v.add(9);
				flag=false;
			}
				
		break;
		
		case 21: 
			if (numero_celle==6)
			{
				v.add(1);
				v.add(2);
				v.add(3);
				v.add(4);	
				v.add(5);
				v.add(6);
				flag=false;
			}
		break;
		
		case 22: 
			if (numero_celle==6)
			{
			
				v.add(1);
				v.add(2);
				v.add(3);
				v.add(4);
				v.add(5);
				v.add(7);
				flag=false;
			}	
				
		break;
		
		case 38: 
			if (numero_celle==6)
			{
		
				v.add(3);
				v.add(5);
				v.add(6);
				v.add(7);
				v.add(8);
				v.add(9);
				flag=false;
			}		
				
		break;
		
		case 39: 
			
			if (numero_celle==6)
			{
			v.add(4);
			v.add(5);
			v.add(6);
			v.add(7);
			v.add(8);
			v.add(9);
			flag=false;
			}
				
		break;
		
		case 41: 
			
			if (numero_celle==7)
			{
				v.add(2);
				v.add(4);
				v.add(5);
				v.add(6);
				v.add(7);
				v.add(8);
				v.add(9);
				flag=false;
			}	
				
				
				break;
		
		case 42: 
		
			if (numero_celle==7){
				
				v.add(3);
				v.add(4);
				v.add(5);
				v.add(6);
				v.add(7);
				v.add(8);
				v.add(9);
				flag=false;
			}
				
		break;

		
		
		}
	
		
		return flag;
	
	
	
	
	}
	
	public void add_valori_scartati_vincolo() {
		dominio_vincolox.addAll(valori_scartatix);
		dominio_vincoloy.addAll(valori_scartatiy);
		System.out.println("Reintegrazione vincoli "+ dominio_vincolox +"  "+ dominio_vincoloy);
		
		//aggiorno il vincolo
		vincolox.set_dominio(dominio_vincolox);
		vincoloy.set_dominio(dominio_vincoloy);

		
	}
	
	public void add_num_celle() {
		num_cellex++;
		num_celley++;
		
		//aggiorno le celle del vincolo
		vincolox.set_num_celle(num_cellex);
		vincoloy.set_num_celle(num_celley);
		
		System.out.println("numero celle x " + num_cellex+" numero celle y "+num_celley);
	
	}

	public void ripristina_val_vincolo(){
		
		valorevincolox=valorevincolox+valore_cella;
		valorevincoloy=valorevincoloy+valore_cella;
		
		//aggiorno il vincolo
		vincolox.set_valore(valorevincolox);
		vincoloy.set_valore(valorevincoloy);
		
		System.out.println("nuovo valore vincolo x  "+ valorevincolox);		
		System.out.println("nuovo valore vincolo y  "+ valorevincoloy);

	}
	

	public void azzera_val_scartati(){
		if (!valori_scartatix.isEmpty())
			valori_scartatix.removeAll(valori_scartatix);

		if (!valori_scartatiy.isEmpty())
			valori_scartatiy.removeAll(valori_scartatiy);
	}

	public boolean assegno_val_maggiore(){
		//ritorna sottoinsieme strettamente maggiore al valore cella
		 SortedSet<Integer> temp=new TreeSet<Integer>();
		 int temp1;
		temp.addAll(dominio_cella);
		 
		System.out.println("Assegno valore maggiore pescando da qui "+temp+" la cella prima aveva valore "+valore_cella);
		temp1=valore_cella;
		temp1++;
		temp=temp.tailSet(temp1);
		System.out.println("Dunque pesco il primo valore da qui "+temp);
		
		if(temp.isEmpty())
		
			return false;
		
		else{
			
			valore_cella=temp.first();
			System.out.println("VALORE CELLA "+valore_cella);
			System.out.println("");
			return true;
		}

	}
	
	public int get_cord_x() {
		return cord_x;
	}

	public int get_cord_y() {
		return cord_y;
	}
	
	public int getValoreCella() {
		return valore_cella;
	}
	
}
