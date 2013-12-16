package vt.posets.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import vt.posets.graph.AdjacencyMatrix;
import vt.posets.matching.LinearOrderVertex;
import vt.posets.matching.MyEdmondsMatching;
import vt.posets.matching.PosetEdge;

public class MyEdmondsMatchingTest {

	//~Constants----------------------------------------------

	//~Data Fields--------------------------------------------

	//~Constructors--------------------------------------------

	//~Methods-------------------------------------------------
	@Test
	public void testMatching() {
		

		//Setting up linear orders-------------------------------------
		ArrayList<Integer> linearOrder1 = new ArrayList<>();
		linearOrder1.add(1);
		linearOrder1.add(3);
		linearOrder1.add(2);
		linearOrder1.add(4);
		LinearOrderVertex<Integer> vertex1 = new LinearOrderVertex<Integer>(linearOrder1);
		
		ArrayList<Integer> linearOrder2 = new ArrayList<>();
		linearOrder2.add(1);
		linearOrder2.add(2);
		linearOrder2.add(3);
		linearOrder2.add(4);
		LinearOrderVertex<Integer> vertex2 = new LinearOrderVertex<Integer>(linearOrder2);
		
		//Setting up graph with vertices-------------------------------------------
		AdjacencyMatrix<LinearOrderVertex<Integer>, PosetEdge<LinearOrderVertex<Integer>, Integer>> g = new AdjacencyMatrix<>();
		g.addVertex(vertex1);
		g.addVertex(vertex2);
		
		//Setting up the ONE EDGE----------------------------------------------------
		PosetEdge<LinearOrderVertex<Integer>, Integer> edge1 = new PosetEdge<LinearOrderVertex<Integer>, Integer>(vertex1, vertex2);
		assertTrue(g.addEdge(vertex1, vertex2, edge1));
		
		//Printing Graph results============================
		System.out.println("PRINTING CONSTRUCTED GRAPH!");
		System.out.println(g.toString());
		
		//Running ALgorithm---------==================================================================================================================
		AdjacencyMatrix<LinearOrderVertex<Integer>, PosetEdge<LinearOrderVertex<Integer>, Integer>> gMatch = MyEdmondsMatching.maximumMatching(g);
		System.out.println("PRINTING THE MAX MATCHING!");
		System.out.println(gMatch.toString());
		System.out.println("printing list of edges....");
		System.out.println(gMatch.getEdges());
		
		
		
		
		System.out.println("\n\nRUN 2================================");
		AdjacencyMatrix<LinearOrderVertex<Integer>, PosetEdge<LinearOrderVertex<Integer>, Integer>> g2 = new AdjacencyMatrix<>();
		g2.addVertex(new LinearOrderVertex<Integer>(linearOrder2));
		
		g2.addEdge(1, 2);
		System.out.println(g.toString());
		
		AdjacencyMatrix<LinearOrderVertex<Integer>, PosetEdge<LinearOrderVertex<Integer>, Integer>> gMatch2 = MyEdmondsMatching.maximumMatching(g2);
		System.out.println(gMatch2.toString());
	}
}
