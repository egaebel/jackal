package vt.posets.graph;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * An Adjacency Matrix style graph which can be used to either represent a directed graph
 * or an undirected graph. Undirected by default. (default constructor)  
 * 
 * If the graph is Directed, the rows are the FROM vertices, and the columns are the TO vertices.
 * 
 * @author Ethan Gaebel (egaebel)
 *
 * @param <T> The type of vertices held.
 * @param <E extends Edge<T>> The type of edges being held.
 */
public class AdjacencyMatrix<T, E extends Edge<T> & Cloneable> 
        implements Iterable<T> {

    //~Constants----------------------------------------------
    protected static final int DEFAULT_SIZE = 10;

    //~Data Fields--------------------------------------------
    /**
     * Int matrix that holds edge weights in weighted graphs. 
     * A 1 in a directed graph indicates an edge, a 0 indicates no edge.
     */
    protected Object[][] matrix;

    /**
     * One object that represents all of the empty (0 weight) edges in the graph.
     */
    protected EmptyEdge<T> empty;
    
    /**
     * Array of elements contained in the graph.
     * Elements correspond to the same indices as they do in the adjacency matrix of edges.
     * 
     * i.e. matrix[4][5] is an edge from 4 to 5, 
     *  elements[4] is the element at 4, elements[5] is the element at 5
     */
    protected T[] elements;
    
    /**
     * The maximum number of vertices in the adjacency matrix.
     */
    protected int capacity;
    
    /**
     * The current number of vertices in the graph.
     */
    protected int size;
    
    /**
     * Indicates whether the graph is directed or not. True if directed, false otherwise.
     */
    private boolean directed;

    //~Constructors--------------------------------------------
    /**
     * Initializes the adjacency matrix to a size of 10.
     * Which means there are 10 vertices in the graph.
     */
    public AdjacencyMatrix() {
       
        this(DEFAULT_SIZE);
    }
    
    /**
     * Initializes the adjacency matrix to a size of 10. There will be 10 vertices in the graph.
     * 
     * @param directed true if the graph is to be a directed graph, false otherwise.
     */
    public AdjacencyMatrix(boolean directed) {
        
        this();
        this.directed = directed;
    }
    
    /**
     * Initializes the adjacency matrix to a size of size.
     * There will be a maximum size of *size* vertices in the graph
     * 
     * @param size the size of the adjacency matrix.
     */
    @SuppressWarnings("unchecked")
    public AdjacencyMatrix(int size) {
        
        elements = (T[]) new Object[size];
        matrix = new Object[size][size];
        
        this.capacity = size;
        this.size = 0;
        directed = false;
        
        empty = new EmptyEdge<T>();
        
        for (int i = 0; i < size; i++) {
            
            for (int j = 0; j < size; j++) {
                
                matrix[i][j] = empty;
            }
        }
    }
    
    /**
     * Initializes the adjacency matrix to a size of size.
     * There will be a maximum size of *size* vertices in the graph.
     * 
     * @param directed indicates whether the graph should be directed or not.
     * @param size the size of the adjacency matrix.
     */
    public AdjacencyMatrix(boolean directed, int size) {
        
        this(size);
        this.directed = directed;
    }

    //~Methods-------------------------------------------------
    /**
     * Totally randomizes the graph.
     */
    @SuppressWarnings("unchecked")
    public void makeGraphRandom() {
        
        Random rand = new Random();
        int weight;
        
        for (int i = 0; i < capacity; i++) {
            
            for (int j = 0; j < capacity; j++) {
                
                weight = rand.nextInt();
                if (weight < 0) {
                    weight = 0;
                }
                
                ((Edge<T>) matrix[i][j]).setWeight(weight);
                
                if (!directed) {
                    
                    ((Edge<T>) matrix[j][i]).setWeight(weight);
                }
            }
        }
    }
    
    /**
     * Makes a copy of the graph and returns it. The copy is to
     * be COMPLETELY separate from the original, all GRAPH data is independent.
     * (although it cannot be ensured that the data WITHIN the graph will not be
     * connected).
     * 
     * ex. vertex or edge changes are independent, but changes to the data in a vertex is NOT.
     * 
     * @return the copy of the graph.
     */
    @SuppressWarnings("unchecked")
	public AdjacencyMatrix<T, E> makeCopy() {
        
        AdjacencyMatrix<T, E> copy = new AdjacencyMatrix<T, E>(directed);
        
        //copy vertices
        for(int i = 0; i < capacity; i++) {
            
            copy.addVertex(elements[i]);
        }
        
        //copy edges
        for (int i = 0; i < capacity; i++) {
            
            for (int j = 0; j < capacity; j++) {
                
                copy.addEdge(i, j, (E) matrix[i][j]);
            }
        }
        
        return copy;
    }
    
    /**
     * Adds a vertex to the graph.
     * 
     * @param element the element to add to the graph.
     */
    public int addVertex(T element) {

        if (this.size == capacity) {
            
            resize();
        }
        
        for (int i = 0; i < capacity; i++) {
            
            if (elements[i] == null) {
                
                elements[i] = element;
                this.size++;
                return i;
            }
        }
        
        return -1;
    }
    
    @SuppressWarnings("unchecked")
    private void resize() {
        
        int oldCapacity = capacity;
        capacity *= 2;
        
        T[] temp = (T[]) new Object[capacity];
        Object[][] matrixTemp = new Object[capacity][capacity];
        
        //Insert dummy elements
        for (int i = 0; i < capacity; i++) {

            temp[i] = null;
            
            for (int j = 0; j < capacity; j++) {
                
                matrixTemp[i][j] = empty;
            }
        }
        
        //Copy over elements
        for (int i = 0; i < oldCapacity; i++) {
        
            temp[i] = elements[i];
            
            for (int j = 0; j < oldCapacity; j++) {
             
                matrixTemp[i][j] = (Edge<T>) matrix[i][j];
            }
        }
        
        elements = temp;
        matrix = matrixTemp;
    }

    /**
     * Removes the vertex holding the specified element from the graph.
     * Returns true if it was present, false otherwise. 
     * 
     * @param element the element to remove from the graph.
     * @return true if the element was present, false otherwise.
     */
    public boolean removeVertex(T element) {

        int index = findVertex(element);
        
        if (index != -1) {
            
            return removeVertex(index);
        }
        
        return false;
    }
    
    /**
     * Takes an element and finds it's index in the array of elements.
     * 
     * @param element the element to find in the array.
     * @return the index if the element is present, -1 otherwise.
     */
    public int findVertex(T element) {
        
        if (element != null) {
         
            for (int i = 0; i < elements.length; i++) {
                
                if (element.equals(elements[i])) {
                    
                    return i;
                }
            }
        }
        
        return -1;
    }
    
    /**
     * Takes a vertex number and gets the element stored there.
     * Returns null if the number is invalid, or an element of that number doesn't exist.
     * 
     * @param number the number of the vertex to get.
     * @return the element corresponding to the passed vertex number.
     */
    public T getVertex(int number) {
        
        if (number < capacity && number > -1 && elements[number] != null) {
            
            return elements[number];
        }
        
        return null;
    }

    /**
     * Removes the vertex with the specified number from the graph.
     * Returns true if the number has a vertex, false otherwise.
     * 
     * @param number the number of the vertex in the adjacency matrix to
     *          remove from the graph.
     * @return true if the number maps to a vertex, false otherwise.
     */
    public boolean removeVertex(int index) {

        if (index > -1 && index < capacity && elements[index] != null) {
            
            elements[index] = null;
            
            //set rows and columns of index to 0
            for (int i = 0; i < capacity; i++) {
                
                matrix[index][i] = empty;
                matrix[i][index] = empty;
            }
            
            this.size--;
            
            return true;
        }

        return false;
    }

    /**
     * Removes the vertex holding the specified element from the Graph, returns
     * the element if successful, returns null otherwise.
     *  
     * @param element the element to remove from the graph.
     * @return the element which was removed from the graph, or null if it wasn't present.
     */
    public T popVertex(T element) {

        int index = findVertex(element);
        
        if (index != -1) {
            
            return popVertex(index);
        }
        
        return null;
    }

    /**
     * Removes the vertex mapping to the passed number from the graph and returns
     * the element contained in the vertex, or null if the number didn't map to a vertex.
     * 
     * @param number the number mapping to the vertex in the graph to be removed.
     * @return the element which was removed from the graph, or null if it wasn't present.
     */
    public T popVertex(int index) {

        if (index > -1 && index < capacity) {
            
            T element = elements[index];
            
            elements[index] = null;
            
            //set rows and columns of index to 0
            for (int i = 0; i < capacity; i++) {
                
                matrix[index][i] = empty;
                matrix[i][index] = empty;
            }
            
            this.size--;
            
            return element;
        }
        
        return null;
    }
    
    /**
     * Get a List<T> of all of the vertices in the graph. By Vertices here I am referring to 
     * the elements which are stored in the graph, not some vertex object.
     * 
     * @return a List<T> of all of the elements in the graph.
     */
    public List<T> getVertices() {
        
        List<T> list = new LinkedList<T>();
        
        for (int i = 0; i < capacity; i++) {
            
            T el = elements[i];
            if (el != null) {
                list.add(el);
            }
        }
        
        return list;
    }

    /**
     * Tells the size of the graph (that is, the number of vertices).
     * 
     * @return the number of vertices in the graph.
     */
    public int size() {

        return this.size;
    }
    
    /**
     * Checks if this adjacnecny Matrix has any elements in it.
     * 
     * @return true if the matrix is empty, false otherwise.
     */
    public boolean isEmpty() {
        
        return size == 0;
    }
    
    /**
     * Gets the current maximum number of vertices that can be stored.
     * 
     * @return the maxmium number of vertices that can be stored before resizing.
     */
    public int getCapacity() {
        
        return capacity;
    }

    /**
     * Adds an edge between vertex1 and vertex2.
     * If it's directed, adds the edge from index1 to index 2. 
     * Returns true if the edge doesn't exist, false otherwise.
     * 
     * @param element1 the first vertex element (element held in the vertex).
     * @param element2 the second vertex element.
     * @return true if an edge doesn't exist, false otherwise.
     */
    public boolean addEdge(T element1, T element2) {
        
        int index1 = findVertex(element1);
        int index2 = findVertex(element2);

        if (index1 != -1 && index2 != -1) {
            
            return addEdge(index1, index2);
        }
        
        return false;
    }
    
    /**
     * Adds an edge between the two passed in vertex numbers of weight 1.
     * If it's directed, adds the edge from index1 to index 2.
     * 
     * @param index1 the first vertex number.
     * @param index2 the second vertex number.
     * @return true if indices are valid, and if the edge does not already exist.
     */
    public boolean addEdge(int index1, int index2) {

        return addEdge(index1, index2, 1);
    }
    
    /**
     * Adds an edge between element1 and element2 of weight weight.
     * If it's directed, adds the edge from element1 to element2. 
     * Returns true if the edge doesn't exist, (the new edge was created) and false otherwise.
     * 
     * @param element1 the first element (or starting element) to put the edge at.
     * @param element2 the second element, that the edge goes to.
     * @param weight the weight of the edge.
     * @return true if an edge doesn't ALREADY exist, false otherwise.
     */
    public boolean addEdge(T element1, T element2, int weight) {
        
        int index1 = findVertex(element1);
        int index2 = findVertex(element2);
        
        if (index1 != -1 && index2 != -1) {
            
            return addEdge(index1, index2, weight);
        }
        
        return false;
    }
    
    /**
     * Adds an edge between index1 and index2 of weight weight.
     * If it's directed, adds the edge from index1 to index 2. 
     * Returns true if the edge doesn't exist, (the new edge was created) and false otherwise.
     * 
     * @param index1 the number of the first vertex (the from vertex)
     * @param index2 the number of the second vertex (the to vertex)
     * @param weight the weight of the edge.
     * @return true if an edge doesn't exist, false otherwise.
     */
    @SuppressWarnings("unchecked")
    public boolean addEdge(int index1, int index2, int weight) {
    
        if (index1 > -1 && index2 > -1 
                && index1 < capacity && index2 < capacity
                && ((Edge<T>) matrix[index1][index2]).getWeight() == 0) {

            Edge<T> newEdge = new IntEdge<T>(weight, elements[index1], elements[index2]);
            matrix[index1][index2] = newEdge;
            
            if (!directed) {
                matrix[index2][index1] = newEdge;    
            }
            
            return true;
        }
        
        return false;
    }
    
    /**
     * Adds an the Edge, edge, between element1 and element2.
     * 
     * @param element1 the element the edge goes from.
     * @param element2 the element the edge goes to.
     * @param edge the Edge object between the elements.
     * @return true if element1 and element2 are successful and edge != null, 
     *          false otherwise.
     */
    public boolean addEdge(T element1, T element2, E edge) {
        
        int index1 = findVertex(element1);
        int index2 = findVertex(element2);
        
        if (index1 != -1 && index2 != -1 && edge != null) {
        
            return addEdge(index1, index2, edge);
        }
        
        return false;
    }
    
    /**
     * Adds the Edge edge to the adjacency matrix at index1, index2.
     * 
     * @param index1 the index of the from vertex.
     * @param index2 the index of the to vertex.
     * @param edge the Edge object to add to the matrix.
     * @return true if successful, false if index1 or index2 is out of bounds
     *          or if edge == null
     */
    @SuppressWarnings("unchecked")
    public boolean addEdge(int index1, int index2, E edge) {
        
        if (index1 > -1 && index1 < capacity 
                && index2 > -1 && index2 < capacity && edge != null) {
            
            matrix[index1][index2] = edge;
            
            if (!directed) {
                E secondEdge = (E) edge.clone();
                secondEdge.setFromAndTo(secondEdge.getTo(), secondEdge.getFrom());
                matrix[index2][index1] = secondEdge;
            }
            
            return true;
        }
        
        return false;
    }

    /**
     * Gets the edge weight of the edge going from the vertex
     * numbered by fromVertex, to the vertex numbered by toVertex.
     * 
     * @param fromVertex the vertex number that the edge goes from.
     * @param toVertex the vertex number that the edge goes to.
     * @return the weight of the edge. -1 if invalid parameters are passed,
     *          however edge weights COULD also be -1.....
     */
    @SuppressWarnings("unchecked")
    public int getEdgeWeight(int fromVertex, int toVertex) {
        
        if (fromVertex > -1 && toVertex > -1 
                && fromVertex < capacity && toVertex < capacity) {

            return ((Edge<T>) matrix[fromVertex][toVertex]).getWeight();
        }
        
        return -1;
    }
    
    /**
     * Gets the Edge<T> going from the vertex
     * numbered by fromVertex, to the vertex numbered by toVertex.
     * 
     * @param fromVertex the vertex number that the edge goes from.
     * @param toVertex the vertex number that the edge goes to.
     * @return the Edge. null if invalid parameters are passed.
     */
    @SuppressWarnings("unchecked")
    public Edge<T> getEdge(int fromVertex, int toVertex) {
        
        if (fromVertex > -1 && toVertex > -1 
                && fromVertex < capacity && toVertex < capacity) {
            
            return ((Edge<T>) matrix[fromVertex][toVertex]);
        }
        
        return null;
    }
    
    /**
     * Gets the edge from fromVertex to toVertex. 
     * Returns null if either of the vertices don't exist.
     * 
     * @param fromVertex the vertex the edge comes from.
     * @param toVertex the vertex the edge goes to.
     * @return the edge from fromVertex to toVertex (may be an empty edge)
     *          Returns null if either vertex DNE.
     */
    public Edge<T> getEdge(T fromVertex, T toVertex) {
        
        int index1 = findVertex(fromVertex);
        int index2 = findVertex(toVertex);
        
        if (index1 != -1 && index2 != -1) {
            
            return getEdge(index1, index2);
        }
        
        return null;
    }
    
    /**
     * Gets a list of all the edges, excluding the EmptyEdge objects.
     * 
     * @return List of all existant edges in this adjacency matrix.
     */
    @SuppressWarnings("unchecked")
    public List<Edge<T>> getEdges() {
        
        List<Edge<T>> edges = new LinkedList<Edge<T>>();
        
        for (int i = 0; i < capacity; i++) {
            
            for (int j = 0; j < capacity; j++) {
                
                if (!(matrix[i][j] instanceof EmptyEdge<?>)) {
                    
                    edges.add((Edge<T>)matrix[i][j]);
                }
            }
        }
        
        return edges;
    }
    
    /**
     * Gets a List<T> of all of the elements that the passed element
     * has an edge to.
     * 
     * i.e. edges e from element TO v.
     * 
     * @param element the element to find the edges for.
     * @return the List of vertices that element has edges TO
     */
    @SuppressWarnings("unchecked")
    public List<T> getEdgesTo(T element) {

        int index = findVertex(element);
        
        List<T> edges = new LinkedList<T>();
        
        //if the passed element exists
        if (index != -1) {
            
            //Loop over all possible edges and if there isn't a 0
                //adds them to the edges List
            for (int i = 0; i < capacity; i++) {
                
                //if the edge HAS a weight
                if (((Edge<T>) matrix[index][i]).getWeight() != 0) { 
                 
                    edges.add(elements[i]);
                }
            }
        }
        
        return edges;
    }
    
    /**
     * Gets a List<T> of all of the elements that the passed element
     * has edges from.
     * 
     * i.e. edges e from v to element.
     * 
     * @param element the element to find edges from.
     * @return the List of vertices that element has edges FROM.
     */
    @SuppressWarnings("unchecked")
    public List<T> getEdgesFrom(T element) {

        int index = findVertex(element);
        
        List<T> edges = new LinkedList<T>();
        
        //if the passed element exists
        if (index != -1) {
            //Loop over all possible edges and if there isn't a 0
                //adds them to the edges List
            for (int i = 0; i < capacity; i++) {
                
                //if the edge HAS a weight
                if (((Edge<T>) matrix[i][index]).getWeight() != 0) { 
                 
                    edges.add(elements[i]);
                }
            }
        }
        
        return edges;
    }

    /**
     * Removes the edge between two vertices.
     * If it's directed, removes the edge from index1 to index 2.
     * Returns true if the edge exists, false otherwise.
     * 
     * @param element1 the first vertex element.
     * @param element2 the second vertex element.
     * @return true if the edge exists, false otherwise.
     */
    public boolean removeEdge(T element1, T element2) {

        int index1 = findVertex(element1);
        int index2 = findVertex(element2);
        
        if (index1 != -1 && index2 != -1) {
            
            return removeEdge(index1, index2);
        }
        
        return false;
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
        
        if (((Edge<T>) matrix[index1][index2]).getWeight() != 0 
                && index1 > -1 && index1 < capacity && index2 > -1 
                && index2 < capacity) {
            
            matrix[index1][index2] = empty; 
            
            if (!directed) {
                matrix[index2][index1] = empty;
            }
                    
            return true;
        }
        
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
        
        int index1 = findVertex(element1);
        int index2 = findVertex(element2);
        
        if (index1 != -1 && index2 != -1) {
            
            return popEdge(index1, index2);
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
        
        
        if ((index1 > -1 && index1 < capacity) && (index2 > -1 && index2 < capacity)) {

            int weight = ((Edge<T>) matrix[index1][index2]).getWeight();
            
            matrix[index1][index2] = empty;
            
            if (!directed) {
                matrix[index2][index1] = empty;
            }
                    
            return weight;
        }
        
        return -1;
    }
    
    /**
     * Returns true if the graph is directed, false otherwise.
     * 
     * @return true if the graph is directed, false otherwise.
     */
    public boolean isDirected() {
        
        return directed;
    }
    
    /**
     * Takes the transitive closure of this AdjacencyMatrix and returns it as another AdjacencyMatrix.
     * 
     * @return an AdjacencyMatrix<T, E> that represents the transitive closure of this AdjacencyMatrix.
     */
    @SuppressWarnings("unchecked")
    public AdjacencyMatrix<T, E> transitiveClosure() {
        
        AdjacencyMatrix<T, E> adjMatrix = new AdjacencyMatrix<T, E>();
        for (int i = 0; i < elements.length; i++) {
            
            adjMatrix.addVertex(elements[i]);
        }
        
        for (int i = 0; i < elements.length; i++) {
        
            for (int j = 0; j < elements.length; j++) {
                
                if (i == j || ((Edge<T>) this.matrix[i][j]).getWeight() != 0) {
       
                    adjMatrix.addEdge(i, j);
                }
            }
        }
        
        AdjacencyMatrix<T, E> newAdjMatrix;
        for (int k = 0; k < elements.length; k++) {
            
            newAdjMatrix = new AdjacencyMatrix<T, E>();
            for (int i = 0; i < elements.length; i++) {
                
                for (int j = 0; j < elements.length; j++) {
                    
                    
                    if (adjMatrix.getEdgeWeight(i, j) != 0 
                            || (adjMatrix.getEdgeWeight(i, k) != 0 && adjMatrix.getEdgeWeight(k, j) != 0)) {
                        
                        newAdjMatrix.addEdge(i, j);
                    }
                }
            }
            adjMatrix = newAdjMatrix;
        }
        
        return adjMatrix;
    }
    
    /**
     * Returns a string representation of the edges in the graph. 
     * 
     * @return string representation of the edges in the graph.
     */
    @SuppressWarnings("unchecked")
    public String printEdges() {
        
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

                build.append("|").append(((Edge<T>)matrix[i][j]).printEdgeWeight());
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
     * Returns a string representation of the vertices of the graph. Whatever that may be.
     * 
     * @return the String representation of the vertices of the graph.
     */
    public String printVertices() {
 
        StringBuilder build = new StringBuilder();
        
        for (int i = 0; i < capacity && elements[i] != null; i++) {
            
            build.append("|").append(elements[i]);
        }
        
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
    
    @Override
    public Iterator<T> iterator() {

        return new AdjacencyMatrixIterator();
    }
    
    public class AdjacencyMatrixIterator implements Iterator<T> {

        /**
         * The index of the current element in this iterator. 
         */
        private int curIndex;
        
        /**
         * Constructor.
         */
        public AdjacencyMatrixIterator () {
            
            curIndex = -1;
        }
        
        @Override
        public boolean hasNext() {

            if (curIndex < capacity) {
                
                for (int i = curIndex + 1; i < capacity; i++) {
                    
                    if (elements[i] != null) {
                        
                        return true;
                    }
                }
            }
            
            return false;
        }

        @Override
        public T next() {

            if (curIndex < capacity) {
                
                for (curIndex += 1; curIndex < capacity; curIndex++) {
                    
                    if (elements[curIndex] != null) {
                        
                        return elements[curIndex];
                    }
                }
            }
            
            return null;
        }

        @Override
        public void remove() {

            // TODO Auto-generated method stub
            //DO THIS LATER!
        }
    }
}