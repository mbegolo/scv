//import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

public class Vincolo {
	
	private int valore;
	private int valoreOriginale;
	private int num_celle;
	public SortedSet<Integer> dominio = new TreeSet<Integer>();
	//private Iterator it = dominio.iterator();
	
	public Vincolo(int v) {
		valore = v;
		valoreOriginale = v;
	}
	
	public int getVal() {
		return valore;
	}
	
	public int getValOrig() {
		return valoreOriginale;
	}
	
	public void set_num_celle(int x) {
		num_celle=x;
	}
	
	public void set_valore(int x) {
		valore=x;
	}
	public int get_num_celle() {

		return num_celle;
	}
	
	
	public void add_dominio (int x){
		
		dominio.add(x);
		
		
	}
	
	
	public void stampa_dominio(){
		
		for (int s :dominio)
			System.out.println(s);
		
	}

	public SortedSet<Integer> get_dominio() {
		// TODO Auto-generated method stub
		return dominio;
	}

	public void set_dominio(SortedSet<Integer> dominio_vincolo) {
		// TODO Auto-generated method stub
		dominio.removeAll(dominio);
		dominio.addAll(dominio_vincolo);
	}
	
}
