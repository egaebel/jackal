package vt.posets.matching;

import java.util.ArrayList;

public class LinearOrderVertex<V> {

	//~Constants----------------------------------------------

	//~Data Fields--------------------------------------------
	/**
	 * The linear order stored in this vertex.
	 */
	private ArrayList<V> linearOrder;

	//~Constructors--------------------------------------------
	/**
	 * Takes a List<V> representing a linearOrder and adds it as the element.
	 * 
	 * @param linearOrder the linearOrder to store in this vertex.
	 */
	public LinearOrderVertex (ArrayList<V> newLinearOrder) {
		
		linearOrder = newLinearOrder;
	}

	//~Methods-------------------------------------------------
	/**
	 * @return the linearOrder.
	 */
	public ArrayList<V> getLinearOrder() {
		return linearOrder;
	}
	
	@Override
	public int hashCode() {
		
		int hash = 13;
		
		for (int i = 0; i < linearOrder.size(); i++) {
			
			if (Integer.MAX_VALUE - (3 * i * linearOrder.get(i).hashCode()) > hash) {
				
				hash -= (47 * i * linearOrder.get(i).hashCode());
			}
			else {
				
				hash += (3 * i * linearOrder.get(i).hashCode());
			}
		}
		
		return hash;
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if (obj != null && obj instanceof LinearOrderVertex) {
			
			@SuppressWarnings("unchecked")
			ArrayList<V> objLinearOrder = ((LinearOrderVertex<V>)obj).getLinearOrder();
			if (objLinearOrder.size() == linearOrder.size()) {
				
				for (int i = 0; i < linearOrder.size(); i++) {
					
					if (!linearOrder.get(i).equals(objLinearOrder.get(i))) {
						
						return false;
					}
				}
				
				return true;
			}
		}
		
		return false;
	}
	
	@Override
	public String toString() {
		
		StringBuilder build = new StringBuilder();
		
		build.append("(");
		for (V v : linearOrder) {
			
			build.append(v);
			if (!linearOrder.get(linearOrder.size() - 1).equals(v)) {
				
				build.append(" ");
			}
		}
		build.append(")");
		
		return build.toString();
	}
}
