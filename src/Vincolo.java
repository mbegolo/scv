import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

public class Vincolo {
	private int valore;
	private SortedSet<Integer> dominio = new TreeSet<Integer>();
	private Iterator it = dominio.iterator();
	
	public Vincolo(int v) {
		valore = v;
		dominio.add(1);
		dominio.add(2);
		dominio.add(3);
		dominio.add(4);
		dominio.add(5);
		dominio.add(6);
		dominio.add(7);
		dominio.add(8);
		dominio.add(9);
	}
	
	public int getVal() {
		return valore;
	}
}
