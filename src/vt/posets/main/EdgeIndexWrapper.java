package vt.posets.main;

import vt.posets.poset.ClosureEdge;

/**
 * Wrapper object that holds an edge and the indices it goes from and to.
 * Used for convenience & minor speed improvements in generating a poset.
 * 
 * @author Ethan Gaebel (egaebel)
 *
 */
public class EdgeIndexWrapper<T> {

	//~Data Fields--------------------------------------------
	private int fromIndex;
	private int toIndex;
	private ClosureEdge<T> edge;
	
	//~Constructors--------------------------------------------
	/**
	 * Sets the fields, they cannot be reset.
	 */
	public EdgeIndexWrapper(int theFromIndex, int theToIndex, ClosureEdge<T> theEdge) {

		fromIndex = theFromIndex;
		toIndex = theToIndex;
		edge = theEdge;
	}

	//~Methods-------------------------------------------------
	/**
	 * @return the fromIndex
	 */
	public int getFromIndex() {
		return fromIndex;
	}

	/**
	 * @return the toIndex
	 */
	public int getToIndex() {
		return toIndex;
	}

	/**
	 * @return the edge
	 */
	public ClosureEdge<T> getEdge() {
		return edge;
	}
	
	@Override
	public String toString() {
		
		return edge.toString(); 
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object o) {
		
		if (o instanceof EdgeIndexWrapper) {
			
			
			EdgeIndexWrapper<T> e = (EdgeIndexWrapper<T>) o;
			if (e.getFromIndex() == fromIndex && e.getToIndex() == toIndex 
					/*&& ((edge == null && e.getEdge() == null) || edge.equals(e.getEdge()))*/) {
				
				return true;
			}
		}
		
		return false;
	}
	
	@Override
	public int hashCode() {
		
		return (10000 * fromIndex) + (toIndex); 
	}
}