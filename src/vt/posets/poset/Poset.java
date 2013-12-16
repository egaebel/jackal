package vt.posets.poset;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import vt.posets.graph.*;

/**
 * A class representing a partial order. 
 * Contains set objects that can be used to quickly check for containment of
 * edges and vertices and extends the AdjacencyMatrix class.
 * 
 * The rows are the FROM vertices, and the columns are the TO vertices. 
 * 
 * @author Ethan Gaebel (egaebel)
 *
 * @param <T> the data type that is being modeled by the partial order.
 */
public class Poset<T> extends AdjacencyMatrix<T, IntEdge<T>> {

	//~Constants----------------------------------------------

	//~Data Fields--------------------------------------------
	/**
	 * A set object used to quickly check for vertex containment on the partial order.
	 */
	private Set<T> vertexSet;
	/**
	 * A set object used to quickly check for edge containment on the partial order.
	 */
	private Set<IntEdge<T>> edgeSet;
	/**
	 * 
	 */
	private Map<Integer, Integer> curMapping;
	
	//~Constructors--------------------------------------------
	/**
	 * Creates an empty PartialOrder.
	 */
	public Poset() {
		
		super(true);
		
		edgeSet = new HashSet<IntEdge<T>>();
		vertexSet = new HashSet<T>();
	}
	
	/**
	 * Creates an empty PartialOrder with an array backing size of size.
	 * 
	 * @param capacity the initial capacity of the array backing the partial order.
	 */
	public Poset(int capacity) {
		
		super(true, capacity);
		
		edgeSet = new HashSet<IntEdge<T>>();
		vertexSet = new HashSet<T>();
	}
	
	/**
	 * Constructor that creates a new Poset holding all of the same vertices
	 * and edges as the passed in AdjacencyMatrix.
	 * 
	 * @param adjMatrix an AdjacencyMatrix to use to create this Poset.
	 */
	public Poset(AdjacencyMatrix<T, IntEdge<T>> adjMatrix) {
		
		super(true, adjMatrix.getCapacity());
		
		edgeSet = new HashSet<IntEdge<T>>();
		vertexSet = new HashSet<T>();
		
		//Copy over vertices
		for (T vertex : adjMatrix.getVertices()) {
			
			this.addVertex(vertex);
		}
		
		//Copy over edges
		for (int i = 0; i < adjMatrix.getCapacity(); i++) {
			
			for (int j = 0; j < adjMatrix.getCapacity(); j++) {
				
				matrix[i][j] = adjMatrix.getEdge(i, j);
			}
		}
	}
	
	/**
	 * Takes a List<T> that represents a linearOrder and adds
	 * all of the elements of the list to the PartialOrder as well as adding
	 * the edges from one element to another.
	 * 
	 * E.G.: If (1)->(2)->(3) is the order of the list.
	 * 			then the Poset will have edges (1, 2), (2, 3)
	 * 			and so on.
	 * 
	 * @param linearOrder a List<T> that represents a linearOrder of elements.
	 */
	public Poset(List<T> linearOrder) {

		super(true, linearOrder.size());
		
		edgeSet = new HashSet<IntEdge<T>>();
		vertexSet = new HashSet<T>();
		
		T prevElement = null;
		for (T element : linearOrder) {
			
			addVertex(element);
			
			if (prevElement != null) {
			
				addEdge(prevElement, element);
			}
			
			prevElement = element;
		}
		
		//Initialize un-set edges to be empty.
		for (int i = 0; i < capacity; i++) {
			
			for (int j = 0; j < capacity; j++) {
		
				if (!this.hasEdge(i, j)) {
				
					matrix[i][j] = empty;
				}
			}
		}
	}
	
	/**
	 * Creates a Poset containing all of the elements contained in the vertexList.
	 * DOES NOT ADD ANY EDGES. The base Poset size will be equal to size.
	 * 
	 * @param vertexList a list of elements to add to the vertices of this poset.
	 * @param size the starting size of this poset.
	 */
	public Poset(List<T> vertexList, int size) {
		
		super(true, size);
		
		edgeSet = new HashSet<IntEdge<T>>();
		vertexSet = new HashSet<T>();
		
		for (T element : vertexList) {
			
			addVertex(element);
		}
		
		//Initialize edges to be empty
		for (int i = 0; i < capacity; i++) {
			
			for (int j = 0; j < capacity; j++) {
				
				matrix[i][j] = empty;
			}
		}
	}

	//~Methods-------------------------------------------------
	@Override
	public int addVertex(T element) {
		
		curMapping = null;
		
		if (!vertexSet.contains(element)) {
			
			int elementIndex = super.addVertex(element);
			
			if (elementIndex > -1) {
				vertexSet.add(element);
			}
			
			return elementIndex;
		}
		else {
			
			return -1;
		}
	}
	
	@Override
	public boolean removeVertex(T element) {
		
		curMapping = null;
		
		if (vertexSet.contains(element)) {
			
			boolean removeSuccessful = super.removeVertex(element);
			
			if (removeSuccessful) {
				
				vertexSet.remove(element);
			}
			
			return removeSuccessful;
		}
		
		return false;
	}
	
	@Override
	public boolean removeVertex(int index) {
		
		curMapping = null;
		
		T removedVertex = super.getVertex(index);
		boolean removeSuccessful = super.removeVertex(index);
		
		if (removeSuccessful) {
			
			vertexSet.remove(removedVertex);
		}
		
		return removeSuccessful;
	}
	
	/**
	 * Adds a linearOrder to the Poset.
	 * 
	 * @param linearOrder an ArrayList<T> that represents a linear order.
	 */
	public void addLinearOrder(ArrayList<T> linearOrder) {
	
		curMapping = null;
		
		if (linearOrder.size() > 0) {
			
			int index1 = -1;
			int index2 = -1;
		
			this.addVertex(linearOrder.get(0));
			
			//Iterate over edges
			for (int i = 0; i < linearOrder.size(); i++) {
				
				//Iterate over the remainder of the edges
				for(int j = i; j < linearOrder.size(); j++) {
					
					//Add vertices on first run through
					if (i == 0) {
						
						this.addVertex(linearOrder.get(j));
					}
					
					//figure out the indices for the elements
					index1 = this.findVertex(linearOrder.get(i));
					index2 = this.findVertex(linearOrder.get(j));
					
					//If the edge does not exist yet
					if(!this.hasClosureEdge(index1, index2)) {
						
						if (i + 1 == j) {
							
							this.addEdge(index1, index2);
						}
						else {
							
							this.addClosureEdge(index1, index2);
						}
					}
				}
			}
		}
	}
	
	
	@Override
	public boolean addEdge(T element1, T element2) {
		
		curMapping = null;

		return super.addEdge(element1, element2);
	}
	
    /**
     * Adds a CoverEdge between index1 and index2 of weight weight.
     * If it's directed, adds the edge from index1 to index 2. 
     * Returns true if the edge doesn't exist, (the new edge was created) and false otherwise.
     * 
     * @param index1 the number of the first vertex (the from vertex)
     * @param index2 the number of the second vertex (the to vertex)
     * @return true if an edge doesn't exist, false otherwise.
     */
	@Override
    @SuppressWarnings("unchecked")
	public boolean addEdge(int index1, int index2, int weight) {

		curMapping = null;

        if (index1 > -1 && index2 > -1 
                && index1 < this.getCapacity() && index2 < this.getCapacity()
                && (((Edge<T>) matrix[index1][index2]).getWeight() == 0 || matrix[index1][index2] instanceof ClosureEdge)) {

        	//All edge weights in the partial order are 1
            CoverEdge<T> newEdge = new CoverEdge<T>(1, (T) elements[index1], (T) elements[index2]);
            matrix[index1][index2] = newEdge;
            edgeSet.add(newEdge);
            
            return true;
        }
        
        return false;
    }
    
    /**
     * Returns true if the edge is successfully added, false if it is not.
     * 
     * @param index1 the number of the first vertex (the from vertex).
     * @param index2 the number of the second vertex (the to vertex).
     * @return true if the edge is created, false otherwise.
     */
    @Override
    public boolean addEdge(int index1, int index2, IntEdge<T> edge) {
        
    	curMapping = null;
    	
    	//All edge weights in the partial order are 1
    	if (edge.getWeight() != 1) {
    		return false;
    	}
    	
        if (index1 > -1 && index1 < this.getCapacity()
                && index2 > -1 && index2 < this.getCapacity() && edge != null) {
                    	
            matrix[index1][index2] = edge;
            edgeSet.add(edge);
            
            return true;
        }
        
        return false;
    }
	
    /**
     * Adds a Closure edge of weight 1 to the Poset that goes from the element
     * at index1 to the element at index2.
     * 
     * @param index1 the index of the element the edge starts at.
     * @param index2 the index of the element the edge ends at.
     * @return true if the edge was added successfully, false otherwise.
     */
    public boolean addClosureEdge(int index1, int index2) {
    	
    	curMapping = null;
    	
    	return addClosureEdge(index1, index2, 1);
    }

	/**
     * Adds the passed ClosureEdge between index1 and index2.
     * Returns true if the edge doesn't exist, (the new edge was added successfully) and false otherwise.
     * 
     * @param index1 the number of the first vertex (the from vertex)
     * @param index2 the number of the second vertex (the to vertex)
     * @param newEdge the edge to add
     * @return true if an edge doesn't exist, false otherwise.
     */
    @SuppressWarnings("unchecked")
	public boolean addClosureEdge(int index1, int index2, ClosureEdge<T> newEdge) {

    	curMapping = null;
    	
        if (index1 > -1 && index2 > -1 
                && index1 < this.getCapacity() && index2 < this.getCapacity()
                && ((Edge<T>) matrix[index1][index2]).getWeight() == 0
                && !edgeSet.contains(newEdge)
                && vertexSet.contains(newEdge.getFrom()) && vertexSet.contains(newEdge.getTo())) {

            matrix[index1][index2] = newEdge;
            edgeSet.add(newEdge);
            
            return true;
        }
        
        return false;
    }
    
	/**
     * Adds a ClosureEdge between index1 and index2 of weight weight.
     * If it's directed, adds the edge from index1 to index 2. 
     * Returns true if the edge doesn't exist, (the new edge was created) and false otherwise.
     * 
     * @param index1 the number of the first vertex (the from vertex)
     * @param index2 the number of the second vertex (the to vertex)
     * @return true if an edge doesn't exist, false otherwise.
     */
    @SuppressWarnings("unchecked")
	public boolean addClosureEdge(int index1, int index2, int weight) {

    	curMapping = null;
    	
        if (index1 > -1 && index2 > -1 
                && index1 < this.getCapacity() && index2 < this.getCapacity()
                && ((Edge<T>) matrix[index1][index2]).getWeight() == 0) {

        	//All edge weights in the partial order are 1
            ClosureEdge<T> newEdge = new ClosureEdge<T>(1, (T) elements[index1], (T) elements[index2]);
            matrix[index1][index2] = newEdge;
            edgeSet.add(newEdge);
            
            return true;
        }
        
        return false;
    }
    
    /**
     * Adds a closure edge from the element denoted by from to the element denoted by to.
     * 
     * @param from the element the edge starts at.
     * @param to the element the edge goes to.
     * @return true if the edge was added successfully, false otherwise.
     */
    public boolean addClosureEdge(T from, T to) {
    	
    	curMapping = null;
    	
    	if (from != null && to != null) {
    	
    		int index1 = findVertex(from);
    		int index2 = findVertex(to);
    		
    		addClosureEdge(index1, index2);
    	}
    	
    	return false;
    }
    
    /**
     * Returns whether there exists a CoverEdge that goes from element1 to element2.
     * 
     * @param element1 the vertex the edge comes from.
     * @param element2 the vertex the edge goes to.
     * @return true if the edge exists, false otherwise.
     */
    public boolean hasEdge(T element1, T element2) {

    	return edgeSet.contains(new CoverEdge<T>(1, element1, element2));
    }
    
    /**
     * Returns whether there exists a ClosureEdge that goes from element1 to element2.
     * 
     * @param element1 the vertex the edge comes from.
     * @param element2 the vertex the edge goes to.
     * @return true if the edge exists, false otherwise.
     */
    public boolean hasClosureEdge(T element1, T element2) {

    	return edgeSet.contains(new ClosureEdge<T>(1, element1, element2));
    }
    
    /**
     * Returns whether there exists a CoverEdge that goes from the element at index1 to
     * the element at index2.
     * 
     * @param index1 index of the from vertex.
     * @param index2 index of the to vertex.
     * @return true if the edge from index1 to index2 exists, false otherwise.
     */
    public boolean hasEdge(int index1, int index2) {
    	
    	if (index1 >= 0 && index1 < capacity && index2 >= 0 && index2 < capacity) {
    		return matrix[index1][index2] instanceof CoverEdge;
    	}
    	
    	return false;
    }
    
    /**
     * Returns whether there exists a ClosureEdge that goes from the element at index1 to
     * the element at index2.
     * 
     * @param index1 index of the from vertex.
     * @param index2 index of the to vertex.
     * @return true if the edge from index1 to index2 exists, false otherwise.
     */
    public boolean hasClosureEdge(int index1, int index2) {
    	
    	if (index1 >= 0 && index1 < capacity && index2 >= 0 && index2 < capacity) {
    		return matrix[index1][index2] instanceof ClosureEdge;
    	}
    	
    	return false;
    }
    
    /**
     * Gets a List<T> of all of the elements that the passed element
     * has a closure edge to.
     * 
     * i.e. edges e from element TO v.
     * 
     * @param element the element to find the edges for.
     * @return the List of vertices that element has edges TO
     */
    @SuppressWarnings("unchecked")
    public List<T> getClosureEdgesTo(T element) {

        int index = findVertex(element);
        
        List<T> closureEdges = new LinkedList<T>();
        
        //if the passed element exists
        if (index != -1) {
            
            //Loop over all possible edges and if there isn't a 0
                //adds them to the edges List
            for (int i = 0; i < capacity; i++) {
                
                //if the edge HAS a weight
            	if (((Edge<T>) matrix[index][i]).getWeight() != 0 
                		&& matrix[index][i] instanceof ClosureEdge<?>) { 
                 
                    closureEdges.add(elements[i]);
                }
            }
        }
        
        return closureEdges;
    }
    
    /**
     * Gets a List<T> of all of the elements that the passed element
     * has a cover edge to.
     * 
     * i.e. edges e from element TO v.
     * 
     * @param element the element to find the edges for.
     * @return the List of vertices that element has edges TO
     */
    @SuppressWarnings("unchecked")
    public List<T> getCoverEdgesTo(T element) {

        int index = findVertex(element);
        
        List<T> coverEdges = new LinkedList<T>();
        
        //if the passed element exists
        if (index != -1) {
            
            //Loop over all possible edges and if there isn't a 0
                //adds them to the edges List
            for (int i = 0; i < capacity; i++) {
                
                //if the edge HAS a weight and is a cover edge
                if (((Edge<T>) matrix[index][i]).getWeight() != 0 
                		&& matrix[index][i] instanceof CoverEdge<?>) { 
                 
                    coverEdges.add(elements[i]);
                }
            }
        }
        
        return coverEdges;
    }
    
    /**
     * Gets a List<T> of all of the elements that the passed element
     * has cover edges from.
     * 
     * i.e. edges e from v to element.
     * 
     * @param element the element to find edges from.
     * @return the List of vertices that element has edges FROM.
     */
    public List<T> getCoverEdgesFrom(T element) {

        int index = findVertex(element);
        
        List<T> edges = new LinkedList<T>();
        
        //if the passed element exists
        if (index != -1) {
        	
            //Loop over all possible edges and if there isn't a 0
                //adds them to the edges List
            for (int i = 0; i < capacity; i++) {
                
                //if the edge is a cover edge
                if (matrix[i][index] instanceof CoverEdge<?>) { 
                 
                    edges.add(elements[i]);
                }
            }
        }
        
        return edges;
    }
    
    /**
     * Gets a List<T> of all of the elements that the passed element
     * has clousre edges from.
     * 
     * i.e. edges e from v to element.
     * 
     * @param element the element to find edges from.
     * @return the List of vertices that element has edges FROM.
     */
    public List<T> getClosureEdgesFrom(T element) {

        int index = findVertex(element);
        
        return getClosureEdgesFrom(index);
    }
    
    /**
     * Gets a List<T> of all of the elements that the element at the passed index
     * has clousre edges from.
     * 
     * i.e. edges e from v to element.
     * 
     * @param index the index that the element is at
     * @return the List of vertices that element has edges FROM.
     */
    @SuppressWarnings("unchecked")
	public List<T> getClosureEdgesFrom(int index) {
    	
    	List<T> edges = new LinkedList<T>();
    	
    	//if the passed element exists
        if (index != -1) {
            //Loop over all possible edges and if there isn't a 0
                //adds them to the edges List
            for (int i = 0; i < capacity; i++) {
                
                //if the edge HAS a weight
                if (((Edge<T>) matrix[i][index]).getWeight() != 0
                		&& matrix[i][index] instanceof ClosureEdge<?>) {
                 
                    edges.add(elements[i]);
                }
            }
        }
        
        return edges;
    }
    
    /**
     * Retrieves the CoverEdge that goes from element1 to element2.
     * If no such edge exists, return null.
     * 
     * @param element1 the vertex the edge comes from.
     * @param element2 the vertex the edge goes to.
     * @return the edge that goes from element1 to element2.
     * 			If no such edge exists, return null.
     */
    @Override
    public Edge<T> getEdge(T element1, T element2) {
    	
    	CoverEdge<T> theEdge = new CoverEdge<T>(1, element1, element2);
    	if (edgeSet.contains(theEdge)) {
    		
    		return theEdge;
    	}
    	else {
    		
    		return null;
    	}
    }
    
    /**
     * Retrieves the CoverEdge or ClosureEdge that goes from element1 to element2.
     * (since a CoverEdge IS a ClosureEdge)
     * If no such edge exists, return null.
     * 
     * @param element1 the vertex the edge comes from.
     * @param element2 the vertex the edge goes to.
     * @return the edge that goes from element1 to element2.
     * 			If no such edge exists, return null.
     */
    public ClosureEdge<T> getClosureEdge(T element1, T element2) {
    	
    	ClosureEdge<T> theClosureEdge = new ClosureEdge<T>(1, element1, element2);
    	if (edgeSet.contains(theClosureEdge)) {
    		
    		return theClosureEdge;
    	}
    	else {
    		
    		return null;
    	}
    }
    
    /**
     * Gets a list of all the edges of type CoverEdge.
     * 
     * @return List of all existant edges in this adjacency matrix.
     */
    @SuppressWarnings("unchecked")
    public List<CoverEdge<T>> getCoverEdges() {
        
        List<CoverEdge<T>> edges = new LinkedList<CoverEdge<T>>();
        
        for (int i = 0; i < capacity; i++) {
            
            for (int j = 0; j < capacity; j++) {

                if ((matrix[i][j] instanceof CoverEdge<?>)) {
                    
                    edges.add((CoverEdge<T>) matrix[i][j]);
                }
            }
        }
        
        return edges;
    }

    /**
     * Gets a list of all the edges of type ClosureEdge.
     * 
     * @return List of all existant edges in this adjacency matrix.
     */
    @SuppressWarnings("unchecked")
    public List<ClosureEdge<T>> getClosureEdges() {
        
        List<ClosureEdge<T>> edges = new LinkedList<ClosureEdge<T>>();
        
        for (int i = 0; i < capacity; i++) {
            
            for (int j = 0; j < capacity; j++) {
                
                if ((matrix[i][j] instanceof ClosureEdge<?>)) {
                    
                    edges.add((ClosureEdge<T>) matrix[i][j]);
                }
            }
        }
        
        return edges;
    }
    
    /**
     * Takes an element and gets the index of it in the elements array.
     * 
     * @param element the element to find the index of.
     * @return the index of the element, or -1 if the element isn't contained here.
     */
    public int getIndexOf(T element) {
    	
    	for (int i = 0; i < capacity; i++) {
    		
    		if (elements[i] != null && element.equals(elements[i])) {
    			
    			return i;
    		}
    	}
    	
    	return -1;
    }
    
    /**
     * Gets all vertices with no edges going into them. 
     * 
     * @return A List<T> of vertices that have no edges going into them.
     */
    @SuppressWarnings("unchecked")
	public List<T> getStarterVertices() {

    	List<T> starterVertices = new ArrayList<T>();
    
    	int tos = 0; 
    	
    	for (int i = 0; i < capacity; i++) {
    		
    		for (int j = 0; j < capacity; j++) {
    			
    			if (((Edge<T>) matrix[j][i]).getWeight() != 0 
    					&& j != i) {
    			
    				tos++;
    			}
    		}
    		
    		if (tos == 0) {
    			
    			if (elements[i] != null) {
    				starterVertices.add(elements[i]);
    			}
    		}
    		
    		tos = 0;
    	}
    	
    	return starterVertices;
    }
    
    /**
     * Returns the current mapping used for translating the resulting string from the
     * Pruesse & Ruskey String method.
     * 
     * @return curMapping the mapping of the indices in the Pruesse & RUskey string to the actual indices.
     */
    public Map<Integer, Integer> getCurMapping() {
    	
    	return curMapping;
    }
    
    /**
     * Takes a List of linear orders, in the form ArrayList<ArrayList<String>> and maps them according to the
     * current mapping Map.
     * 
     * @param linearOrders the linear orders to map.
     * @return the mapped linear orders.
     */
    public ArrayList<ArrayList<T>> applyMapping(ArrayList<ArrayList<String>> linearOrders) {
    	
    	ArrayList<ArrayList<T>> mappedLinearOrders = new ArrayList<ArrayList<T>>();
    	
    	for (ArrayList<String> linearOrder : linearOrders) {
    		
    		ArrayList<T> mappedLinearOrder = new ArrayList<T>();
    		
    		//Iterate over elements
    		for (int i = 0; i < linearOrder.size(); i++) {
    			
    			int toMapIndex = Integer.parseInt(linearOrder.get(i));
    			int index = curMapping.get(toMapIndex);
    			
    			mappedLinearOrder.add(elements[index]);
    		}
    		
    		mappedLinearOrders.add(mappedLinearOrder);
    	}
    	
    	return mappedLinearOrders;
    }
    
    /**
     * Changes the edge (element1, element2) from a closure edge to a cover edge. 
     *  
     * @param element1 the element that the edge goes from.
     * @param element2 the element that the edge goes to.
     * @return true if it succeeds, false if either element doesn't exist or the edge is an empty edge.
     */
    public boolean swapEdgeToCover(T element1, T element2) {
    	
    	int index1 = getIndexOf(element1);
    	int index2 = getIndexOf(element2);
    	
    	return swapEdgeToCover(index1, index2);
    }
    
    /**
     * Changes the edge at (index1, index2) from a closure edge to a cover edge. 
     *  
     * @param index1 the index of the from vertex.
     * @param index2 the index of the to vertex.
     * @return true if it succeeds, false if either index is out of bounds or the edge is an empty edge.
     */
    public boolean swapEdgeToCover(int index1, int index2) {
    	
    	if (index1 < capacity && index2 < capacity && index1 >= 0 && index2 >= 0 
    			&& !(matrix[index1][index2] instanceof EmptyEdge<?>)) {
    		
	    	edgeSet.remove(new ClosureEdge<T>(1, elements[index1], elements[index2]));
	    	CoverEdge<T> newCoverEdge = new CoverEdge<T>(1, elements[index1], elements[index2]);
	    	edgeSet.add(newCoverEdge);
	    	matrix[index1][index2] = newCoverEdge;
	    	
	    	return true;
    	}
    	
    	return false;
    }
    
    @Override
    public boolean removeEdge(T element1, T element2) {

    	curMapping = null;
    	
    	if (vertexSet.contains(element1) && vertexSet.contains(element2)) {
	        
    		int index1 = findVertex(element1);
	        int index2 = findVertex(element2);
	        
	        if (index1 != -1 && index2 != -1) {
	            
	            return removeEdge(index1, index2);
	        }
	        
	        return false;
    	}
    	else {
    		
    		return false;
    	}
    }
    
    /**
     * Takes two vertex numbers and removes the edge.
     * If it's directed, removes the edge from index1 to index 2.
     * Returns true if the edge exists, false otherwise.
     * 
     * @param index1 the first vertex number.
     * @param index2 the second vertex number.
     * @return true if the edge exists, false otherwise.
     */
    @SuppressWarnings("unchecked")
    public boolean removeEdge(int index1, int index2) {
//System.out.println("==================removeEdge called on=================(" + index1 + ", " + index2 + ")");
    	curMapping = null;
    	
		if (((Edge<T>) matrix[index1][index2]).getWeight() != 0 
                && index1 > -1 && index1 < this.capacity && index2 > -1 
                && index2 < capacity) {

			//if removing a True CoverEdge
			if (matrix[index1][index2] instanceof CoverEdge<?>) {

				List<T> coversFromCovers = this.getCoverEdgesFrom(elements[index1]);

				//Iterate over all elements that m has a cover edge from, they will always have a closure edge to w, make it a cover edge
				for (int i = 0; i < coversFromCovers.size(); i++) {

					int indexOfElement = this.getIndexOf(coversFromCovers.get(i));

					if (indexOfElement != index1 && matrix[indexOfElement][index2] instanceof ClosureEdge<?>) {

						ClosureEdge<T> tempClosure = (ClosureEdge<T>) matrix[indexOfElement][index2];
						CoverEdge<T> tempCover = new CoverEdge<T>(1, tempClosure.getFrom(), tempClosure.getTo());
						
						edgeSet.remove(tempClosure);
System.out.println("-------after recursive removeEdge THE FIRST OPTION.........................................................");
						matrix[indexOfElement][index2] = tempCover; 
						edgeSet.add(tempCover);
					}
				}
				
				
				//Iterate over all elements that w has a closure edge to, if 
				List<T> coversToCovers = this.getCoverEdgesTo(elements[index2]);
				for (int j = 0; j < coversToCovers.size(); j++) {
					
					int indexOfElement = this.getIndexOf(coversToCovers.get(j));
					
					if (indexOfElement != index2 && matrix[index1][indexOfElement] instanceof ClosureEdge<?>) {
						
						ClosureEdge<T> tempClosure = (ClosureEdge<T>) matrix[index1][indexOfElement];
						CoverEdge<T> tempCover = new CoverEdge<T>(1, tempClosure.getFrom(), tempClosure.getTo());

						edgeSet.remove(tempClosure);
System.out.println("-------after recursive removeEdge THE SECOND OPTION.........................................................");
System.out.println("adding edge to:" + indexOfElement + "  " + index2);
						matrix[index1][indexOfElement] = tempCover;
						edgeSet.add(tempCover);
					}
				}
			}
			
			
			edgeSet.remove(matrix[index1][index2]);
            matrix[index1][index2] = this.empty;
System.out.println("================================================================================");
System.out.println(this.toString());
System.out.println(this.printCoverEdges());
            return true;
        }
System.out.println("================================================================================");
System.out.println(this.toString());
System.out.println(this.printCoverEdges());
        return false;
    }
    
    /**
     * Takes two elements and if an edge exists, removes it and returns the weight.
     * If it doesn't exist, returns -1.
     * 
     * @param element1 the first element with a vertex.
     * @param element2 the second element with a vertex.
     * @return the weight of the edge being popped.
     */
    public int popEdge(T element1, T element2) {
        
    	curMapping = null;
    	
    	if (edgeSet.contains(element1) && edgeSet.contains(element2)) {
	     
    		int index1 = findVertex(element1);
	        int index2 = findVertex(element2);
	        
	        if (index1 != -1 && index2 != -1) {
	            
	            return popEdge(index1, index2);
	        }
    	}
        
        return -1;
    }
    
    /**
     * Takes two vertex numbers and removes the edge between them, returns the weight of the edge 
     * (returning 0 if no edge).
     * If it's a directed graph, then returns the weight of the edge from index1 to index2
     * Returns -1 if the vertex numbers are invalid.
     * 
     * @param index1 the number of vertex1.
     * @param index2 the number of vertex2.
     * @return the weight of the edge that got removed.
     */
    @SuppressWarnings("unchecked")
    public int popEdge(int index1, int index2) {
        
    	curMapping = null;
    	
        if ((index1 > -1 && index1 < this.capacity) && (index2 > -1 && index2 < this.capacity)) {

            int weight = ((Edge<T>) matrix[index1][index2]).getWeight();
            
            removeEdge(index1, index2);
            
            return weight;
        }
        
        return -1;
    }
    
    @Override
    public boolean isDirected() {
    	
    	return true;
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public Poset<T> transitiveClosure() {
        
    	curMapping = null;
    	
        Poset<T> adjMatrix = new Poset<T>(this.getVertices(), capacity);
        
        //Copy over Poset, and add edges to each vertex's self
        for (int i = 0; i < capacity; i++) {
        
            for (int j = 0; j < capacity; j++) {
                
            	//Add a Closure Edge
                if (i == j && elements[i] != null) {
                	
                	adjMatrix.addClosureEdge(i, j);
                }
                //Copy the edge from this matrix
                else if (((Edge<T>) this.matrix[i][j]).getWeight() != 0) {
                	
                    adjMatrix.addEdge(i, j, (IntEdge<T>) this.matrix[i][j]);
                }
            }
        }
        
        Poset<T> newAdjMatrix;
        
        //Iterate over each element
        for (int k = 0; k < elements.length; k++) {
            
        	//Create new poset
            newAdjMatrix = new Poset<T>(this.getVertices(), capacity);

            //iterate over matrix--------------------------------
            //Over rows
            for (int i = 0; i < capacity; i++) {
                
            	//Over columns
                for (int j = 0; j < capacity; j++) {

                	//If prev matrix has an edge
                    if (adjMatrix.getEdgeWeight(i, j) != 0) {

                    	newAdjMatrix.addEdge(i, j, (IntEdge<T>) adjMatrix.getEdge(i, j));
                    }
                    //If prev matrix has an edge by the transitive property
                    else if (adjMatrix.getEdgeWeight(i, k) != 0 && adjMatrix.getEdgeWeight(k, j) != 0) {
                        
                        newAdjMatrix.addClosureEdge(i, j);
                    }
                }
            }
            adjMatrix = newAdjMatrix;
        }
        
        return adjMatrix;
    }
    
    /**
     * Outputs a String that can be used as input to the Pruesse & Ruskey program.
     * 
     * @return a String that can be used as input to the Pruesse & Ruskey program.
     */
    public String pruesseRuskeyString() {

    	StringBuilder build = new StringBuilder();
    	
    	build.append(size);
    	build.append("\n");
    
    	Map<Integer, Integer> mapping = new HashMap<Integer, Integer>();
    	Map<Integer, Integer> reverseMapping = new HashMap<Integer, Integer>();
    	Set<String> presentEdges = new HashSet<String>();
    	
		Queue<T> q = new LinkedList<T>();
		q.addAll(this.getStarterVertices());

		T curEl;
		int i = 1;

		//Breadth first search
		while(!q.isEmpty()) {
			
			curEl = q.remove();
	
			//Iterate over all vertices with an edge from the current one
			for (T adjacentEls : this.getEdgesTo(curEl)) {

				//If the vertices aren't equal and the edge is a cover edge
				if (!curEl.equals(adjacentEls) && this.getEdge(curEl, adjacentEls) != null) { 

					Integer curIndex = reverseMapping.get(getIndexOf(curEl));
					Integer adjIndex = reverseMapping.get(getIndexOf(adjacentEls));
					
					//The curEl is not contained in reverseMapping
					if (curIndex == null) {
						
						curIndex = i;
						i++;
					}
					
					//The adjEls is not contained in reverseMapping
					if (adjIndex == null) {

						adjIndex = i;
						i++;
					}

					String edgeString = curIndex + " " + adjIndex;
					
					//If the edge isn't a repeat
					if (!presentEdges.contains(edgeString)) {

						//Append 
						build.append(edgeString).append("\n");
						presentEdges.add(edgeString);
						
						//Add adjacent el to the queue
						q.add(adjacentEls);
					}
					
					//Add curIndex & adjIndex to mapping 
					//to map to the index of curEl & adjacentEls
					mapping.put(curIndex, getIndexOf(curEl));
					mapping.put(adjIndex, getIndexOf(adjacentEls));
					
					//Add curEl & adjacentEls indices to mapping
					//to map to the curIndex & adjIndex
					reverseMapping.put(getIndexOf(curEl), curIndex);
					reverseMapping.put(getIndexOf(adjacentEls), adjIndex);
				}
			}
		}
		
		build.append("0 0");
		
		curMapping = mapping;

    	return build.toString();
    }
    
    @SuppressWarnings("unchecked")
	@Override
    public boolean equals(Object obj) {
    	
    	if (obj instanceof Poset) {
    		
    		Poset<T> castPoset = (Poset<T>) obj;
    		if (castPoset.size() == size && castPoset.getCapacity() == capacity) {
    			
    			//Check Elements
    			for (int i = 0; i < capacity; i++) {
    				
    				if ((castPoset.getVertex(i) == null && this.getVertex(i) != null) 
    						|| (castPoset.getVertex(i) != null && !castPoset.getVertex(i).equals(elements[i]))) {
    					
    					return false;
    				}
    			}
    			
    			//Check Edges
    			for (int i = 0; i < capacity; i++) {
    				
    				for (int j = 0; j < capacity; j++) {
    					
    					if(castPoset.hasClosureEdge(i, j) != hasClosureEdge(i, j)) {
    						
    						return false;
    					}
    				}
    			}
    			
    			//EQUALITY!
    			return true;
    		}
    	}
    	
    	return false;
    }
    
    /**
     * Prints all of the edges that are cover edges.
     * 
     * @return String holding the string of all cover edges.
     */
    @SuppressWarnings("unchecked")
	public String printClosureEdges() {
        
        StringBuilder build = new StringBuilder();
        
        //iterate to create the first row with the column titles
        build.append("-");
        for (int i = 0; i < capacity; i++) {
            
            build.append("|").append(i);
        }
        build.append("|").append("\n");
        
        //iterate to create the boundary row between the column names
        for (int i = 0; i < capacity; i++) {
            
            build.append("-|");
        }
        build.append("-|").append("\n");
        
        //Iterate over actual edge elements
        for (int i = 0; i < capacity; i++) {
                
            build.append(i);
            
            for (int j = 0; j < capacity; j++) {

            	if (matrix[i][j] instanceof ClosureEdge<?>) {
            		build.append("|").append(((Edge<T>)matrix[i][j]).printEdgeWeight());
            	}
            	else {
            		build.append("|").append(0);
            	}
            }
            build.append("|").append("\n");
        }
        
        //iterate to create the bottom boundary
        for (int i = 0; i < capacity; i++) {
            
            build.append("-|");
        }
        build.append("-|").append("\n");
        
        return build.toString();
    }
    
    /**
     * Prints all of the edges that are cover edges.
     * 
     * @return String holding the string of all cover edges.
     */
    @SuppressWarnings("unchecked")
	public String printCoverEdges() {
        
        StringBuilder build = new StringBuilder();
        
        //iterate to create the first row with the column titles
        build.append("-");
        for (int i = 0; i < capacity; i++) {
            
            build.append("|").append(i);
        }
        build.append("|").append("\n");
        
        //iterate to create the boundary row between the column names
        for (int i = 0; i < capacity; i++) {
            
            build.append("-|");
        }
        build.append("-|").append("\n");
        
        //Iterate over actual edge elements
        for (int i = 0; i < capacity; i++) {
                
            build.append(i);
            
            for (int j = 0; j < capacity; j++) {

            	if (matrix[i][j] instanceof CoverEdge<?>) {
            		build.append("|").append(((Edge<T>)matrix[i][j]).printEdgeWeight());
            	}
            	else {
            		build.append("|").append(0);
            	}
            }
            build.append("|").append("\n");
        }
        
        //iterate to create the bottom boundary
        for (int i = 0; i < capacity; i++) {
            
            build.append("-|");
        }
        build.append("-|").append("\n");
        
        return build.toString();
    }
    
    @Override
    public String toString() {
    	
    	StringBuilder build = new StringBuilder();
    	
    	build.append(this.printVertices());
    	build.append("\n");
    	build.append(this.printEdges());
    	build.append("\n\n");
    	
    	return build.toString();
    }
    
    /**
     * Takes the transitive reduction of this poset and returns a new Poset representing it.
     * 
     * @return p a new Poset<T> that is the transitive reduction of this one.
     */
	public Poset<T> transitiveReduction() {
    	
        Poset<T> p = new Poset<T>(this.getVertices(), capacity);
        
        boolean notCoverEdge = false;
        
        //Iterate over each Node, k
        for (int k = 0; k < capacity; k++) {
        	
        	if (elements[k] != null) {
        	
        		//Iterate all vertices k goes to, j
	        	for (int j = 0; j < capacity; j++) {
	        		
	        		//If there is an edge from k to j
	        		if (!(matrix[k][j] instanceof EmptyEdge<?>) && k != j) {
		        	
	        			//Iterate over all vertices j goes to, i
	        			for (int i = 0; i < capacity; i++) {
		        			
	        				//If there is an edge from i to j AND an edge from k to i
	        				if (!(matrix[i][j] instanceof EmptyEdge<?>) && !(matrix[k][i] instanceof EmptyEdge<?>) && i != j && i != k) {
	        					 
	        					notCoverEdge = true;
	        				}
		        		}
	        			
	        			//If this is a cover edge
	        			if (!notCoverEdge) {
	        				
	        				//Add it
	        				p.addEdge(k, j, 1);
	        			}
	        			notCoverEdge = false;
	        		}
	        	}
        	}
        }
        
        return p;
    }
}