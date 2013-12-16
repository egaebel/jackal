package vt.posets.graph;

/**
 * An Edge object which represents an edge with 0 weight.
 * The setWeight method does nothing for this edge. 
 * 
 * @author Ethan Gaebel (egaebel)
 *
 */
public class EmptyEdge<T> extends Edge<T> implements Cloneable {

    //~Constructors--------------------------------------------
    /**
     * Default constructor.
     */
    public EmptyEdge() {

        super(null, null);
    }

    //~Methods-------------------------------------------------
    @Override
    public void setWeight(int weight) {}

    @Override
    public int getWeight() {
        return 0;
    }
    
    @Override
    public String toString() {
        
        return "0";
    }
    
    /**
     * Returns a new EmptyEdge<T> object since they are all identical.
     */
    @Override
    public Edge<T> clone() {
        
        return new EmptyEdge<T>();
    }
}
