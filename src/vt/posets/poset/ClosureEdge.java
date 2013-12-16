/**
 * 
 */
package vt.posets.poset;

import vt.posets.graph.IntEdge;

/**
 * @author Ethan Gaebel (egaebel)
 *
 */
public class ClosureEdge<T> extends IntEdge<T> {

	//~Constructors--------------------------------------------
	/**
	 * Constructor that sets the weight and the elements that this edge goes from and to.
	 * 
	 * @param theWeight the weight of this edge.
	 * @param from the vertex that this edge comes from.
	 * @param to the vertex that this edge goes to.
	 */
	public ClosureEdge(int theWeight, T from, T to) {
		
		super(theWeight, from, to);
	}

	//~Methods-------------------------------------------------
    /**
     * Compare for equality based on the from/to vertices and weight.
     * If the passed object goes to/from the same vertices as this edge,
     * and has the same weight, then the two objects are considered equal.
     * 
     * @param o the object to compare to this Edge.
     * @return true if the passed object goes to/from the same vertices as this edge,
     *          and has the same weight. False otherwise. 
     */
    @Override
    public boolean equals(Object o) {
        
        if (o instanceof ClosureEdge || o instanceof CoverEdge) {
            
            @SuppressWarnings("unchecked")
            ClosureEdge<T> oCast = (ClosureEdge<T>) o;
            //A null from and null to
            if (this.getFrom() == null && this.getTo() == null) {
                
                if (oCast.getFrom() == null && oCast.getTo() == null
                        && oCast.getWeight() == this.getWeight()) {
                    
                    return true;
                }
            }
            //A null from and NOT a null to
            else if (this.getFrom() == null && this.getTo() != null) {
                
                if (oCast.getFrom() == null && this.getTo().equals(oCast.getTo())
                        && oCast.getWeight() == this.getWeight()) {
                    
                    return true;
                }
            }
            //A NOT null from and a null to
            else if (this.getFrom() != null && this.getTo() == null) {
                
                if (this.getFrom().equals(oCast.getFrom()) && oCast.getTo() == null 
                        && oCast.getWeight() == this.getWeight()) {
                    
                    return true;
                }
            }
            //All non-null elements
            else if (from.equals(oCast.getFrom()) && to.equals(oCast.getTo()) && oCast.getWeight() == this.getWeight()) {
                
                return true;
            }
        }
        
        return false;
    }
    
    @Override
    public int hashCode() {
    
    	return 999999 + this.getFrom().hashCode() * 7 + this.getTo().hashCode() * 3 + this.getWeight();
    }
    
    @Override
    public String toString() {
    	
    	String fromString = "null";
        String toString = "null";
        if (from != null) {
            fromString = from.toString();
        }
        if (to != null) {
            toString = to.toString();
        }
        
        return "Closure: (" + fromString + ", " + toString + ")";
    }
}
