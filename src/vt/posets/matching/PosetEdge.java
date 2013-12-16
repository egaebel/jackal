package vt.posets.matching;

import java.util.ArrayList;
import java.util.List;

import vt.posets.graph.IntEdge;
import vt.posets.main.PosetAlgorithms;
import vt.posets.poset.Poset;

/**
 * An edge from one LinearOrderVertex to another that holds a Poset that generates both of the linear orders.
 * @author Ethan Gaebel (egaebel)
 *
 * @param <T> The type of LinearOrder wrapper.
 * @param <V> The type held in the LinearOrders held in the type T.
 */
public class PosetEdge<T extends LinearOrderVertex<V>, V extends Comparable<V>> extends IntEdge<T> implements Cloneable {

	//~Constants----------------------------------------------

	//~Data Fields--------------------------------------------
	
	/**
	 * The poset that generates the two linear orders this edge joins.
	 */
	private Poset<V> generatingPoset;

	//~Constructors--------------------------------------------
	/**
	 * Takes two linear orders and sets the contained vertices to them.
	 * 
	 * @param newLinearOrder1 the new LinearOrderVertex to store.
	 * @param newLinearOrder2 the new LinearOrderVertex to store.
	 */
	public PosetEdge(T newLinearOrder1, T newLinearOrder2) {
		
		super(newLinearOrder1, newLinearOrder2);
		
		List<ArrayList<V>> linearOrders = new ArrayList<ArrayList<V>>();
		linearOrders.add(newLinearOrder1.getLinearOrder());
		linearOrders.add(newLinearOrder2.getLinearOrder());
		
		//Suppress error output, because it's fine if it fails here, that case is handled
		generatingPoset = PosetAlgorithms.generatingPosetTwo(linearOrders, false, true);
	}

	//~Methods-------------------------------------------------
	/**
	 * @return the generatingPoset
	 */
	public Poset<V> getGeneratingPoset() {
		return generatingPoset;
	}

	@Override
	public void setWeight(int weight) {
		//Do nothing, if edge exists, it is 1.
	}

	@Override
	public int getWeight() {
		return 1;
	}

	public PosetEdge<T, V> clone() {
		return new PosetEdge<T, V>(this.getFrom(), this.getTo());
	}
	
	@Override
	public int hashCode() {
		
		return 2 * this.getFrom().hashCode() + 13 * this.getTo().hashCode();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object o) {
		
		if (o instanceof PosetEdge<?, ?>) {
			
			T oFrom = ((PosetEdge<T, V>) o).getFrom();
			T oTo = ((PosetEdge<T, V>) o).getTo();

			if (oFrom.equals(this.getFrom()) && oTo.equals(this.getTo())) {
			
				return true;
			}
		}
		
		return false;
	}
}