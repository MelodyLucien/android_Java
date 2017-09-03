package oneforcopy;
import java.io.Serializable;
import java.util.LinkedList;

import org.omg.CosNaming.NamingContextExtPackage.AddressHelper;


public class ListForFile<E> implements Serializable{
	
	
	private LinkedList<E> listTops=new LinkedList<>();
	
	
	public boolean addListFromItem(E e){
		listTops.add(e);
		return false;
	}
	
	
	public boolean addListFromArray(E[] e){
		
		for (int i = 0; i < e.length; i++) {
			listTops.add((E) e);
		}
		return false;
	}
	
	
	
	public E getItem(int position){
		
			E e=listTops.get(position);
		    return e;
	}
	

}
