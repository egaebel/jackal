package vt.posets.graph;

/**
 * An abstract Edge class which has the abstract methods
 * getWeight()
 * and
 * setWeight(int weight).
 * Also contains clearly defined methods to get/set fields
 * indicating the vertices that this edge goes to/from.
 * Used for a Graph data structure to abstract
 * out the edges.
 * 
 * @author Ethan Gaebel (egaebel)
 *
 */
public abstract class Edge<T> implements Comparable<Edge<T>>, Cloneable {

    //~Data Fields------------------------------------------------
    /**
     * The vertex that this edge comes from.
     */
    protected T from;
    
    /**
     * The vertex that this edge goes to.
     */
    protected T to;
    
    /**
     * Constructor.
     * 
     * @param newFrom the vertex this edge comes from.
     * @param newTo the vertex that this edge goes to.
     */
    public Edge(T newFrom, T newTo) {
        
        from = newFrom;
        to = newTo;
    }
    
    //~Methods-----------------------------------------------------
    /**
     * Sets the weight of the edge to the passed in weight.
     * 
     * @param weight the weight of the edge.
     */
    public abstract void setWeight(int weight);
    
    /**
     * Gets the weight of the edge.
     * 
     * @return the edge weight.
     */
    public abstract int getWeight();
    
    /**
     * Gets the vertex that this edge comes from.
     * 
     * @return from the vertex that this edge originates from.
     */
    public T getFrom() {
        
        return from;
    }
    
    /**
     * Gets the vertex that this edge goes to.
     * 
     * @return to the vertex that this edge goes to.
     */
    public T getTo() {
        
        return to;
    }
    
    /**
     * Sets the vertex that this edge comes from.
     * 
     * @param newFrom the new vertex that this edge comes from.
     */
    protected void setFrom(T newFrom) {
        
        from = newFrom;
    }
    
    /**
     * Sets the vertex that this edge goes to.
     * 
     * @param newTo the new vertex that this edge goes to.
     */
    protected void setTo(T newTo) {
        
        to = newTo;
    }
    
    /**
     * Sets the vertex that this edge comes from and that this edge goes to. 
     * 
     * @param newFrom the new vertex that this edge comes from.
     * @param newTo the new vertex that this edge goes to.
     */
    protected void setFromAndTo(T newFrom, T newTo) {
        
        from = newFrom;
        to = newTo;
    }
    
    /**
     * Used to compare the weights of edges.
     * 
     * @param edge the Edge<T> object to compare to this one's weight.
     * @return 0 for edge == this, 1 for edge > this, -1 for edge < this.
     */
    @Override
    public int compareTo(Edge<T> edge) {

        if (edge.getWeight() == this.getWeight()) {
        
            return 0;
        }
        else if (edge.getWeight() < this.getWeight()) {
            
            return 1;
        }
        else {
            
            return -1;
        }
    }
    
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
        
        if (o instanceof Edge) {
            
            @SuppressWarnings("unchecked")
            Edge<T> oCast = (Edge<T>) o;
            //A null from and null to
            if (this.getFrom() == null && this.getTo() == null) {
                
                if (oCast.getFrom() == null && oCast.getTo() == null
                        && oCast.getWeight() == this.getWeight()) {
                    
                    return true;
                }
            }
            //A null from and NOT a null to
            else if (this.getFrom() == null && this.getTo() != null) {
                
                if (oCast.getFrom() == null && oCast.getTo().equals(this.getTo())
                        && oCast.getWeight() == this.getWeight()) {
                    
                    return true;
                }
            }
            //A NOT null from and a null to
            else if (this.getFrom() != null && this.getTo() == null) {
                
                if (oCast.getFrom().equals(this.getFrom()) && oCast.getTo() == null 
                        && oCast.getWeight() == this.getWeight()) {
                    
                    return true;
                }
            }
            //All non-null elements
            else if (oCast.getFrom().equals(from) && oCast.getTo().equals(to) && oCast.getWeight() == this.getWeight()) {
                
                return true;
            }
        }
        
        return false;
    }
    
    @Override
    public int hashCode() {
        
        int fromHashCode = 4500;
        int toHashCode = 7000;
        
        if (this.getFrom() != null) {
            fromHashCode = this.getFrom().hashCode();
        }
        if (this.getTo() != null) {
            toHashCode = this.getTo().hashCode();
        }
        
        return this.getWeight() + (fromHashCode * 1000) + (toHashCode);
    }
    
    /**
     * Returns a String of the weight of this edge.
     * 
     * @return a String of the weight of this Edge object.
     */
    public String printEdgeWeight() {
        
        return String.valueOf(this.getWeight());
    }
    
    @Override
    public String toString() {
        
        return "(" + from.toString() + ", " + to.toString() + ") with weight = " + String.valueOf(this.getWeight());
    }
    
    /**
     * Makes a copy of this Edge object and returns it.
     * 
     * @return a copy of this Edge<T> object.
     */
    public abstract Edge<T> clone();
}