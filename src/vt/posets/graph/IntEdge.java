package vt.posets.graph;


/**
 * Standard edge holding only an int.
 * 
 * @author Ethan Gaebel (egaebel)
 *
 */
public class IntEdge<T> extends Edge<T> implements Cloneable {

    //~Data Fields--------------------------------------------
    /**
     * The weight of the edge.
     */
    private int weight;

    //~Constructors--------------------------------------------
    /**
     * Constructor taking the integer weight to set the weight to.
     * 
     */
    public IntEdge(int theWeight, T from, T to) {
        
        super(from, to);
        weight = theWeight;
    }
    
    /**
     * Constructor taking the from and to vertices, sets edge weight to 1.
     * 
     */
    public IntEdge(T from, T to) {
        
        super(from, to);
        weight = 1;
    }

    //~Methods-------------------------------------------------
    @Override
    public void setWeight(int weight) {

        this.weight = weight;
        
    }

    @Override
    public int getWeight() {

        return weight;
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
        
        return "(" + fromString + ", " + toString + ") with weight = " + String.valueOf(weight);
    }

    
    @Override
    public IntEdge<T> clone() {
        
        return new IntEdge<T>(weight, super.from, super.to);
    }
}