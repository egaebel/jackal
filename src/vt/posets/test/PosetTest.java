package vt.posets.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import vt.posets.main.PosetAlgorithms;
import vt.posets.poset.Poset;

public class PosetTest {


	//~Constants----------------------------------------------

	//~Data Fields--------------------------------------------

	//~Constructors--------------------------------------------

	//~Methods-------------------------------------------------
	@Test
	public void testRemoveVertexInt() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddEdgeIntIntInt() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveEdgeIntInt() {
		fail("Not yet implemented");
	}

	@Test
	public void testPopEdgeIntInt() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsDirected() {
		fail("Not yet implemented");
	}

	@Test
	public void testPoset() {
		fail("Not yet implemented");
	}

	@Test
	public void testPosetInt() {
		fail("Not yet implemented");
	}

	@Test
	public void testPosetListOfT() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddVertexT() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveVertexT() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddEdgeIntIntIntEdgeOfT() {
		fail("Not yet implemented");
	}

	@Test
	public void testHasEdgeTT() {
		fail("Not yet implemented");
	}

	@Test
	public void testHasEdgeIntInt() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetEdgeTT() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testGetStarterVertices() {
		
		Poset<Integer> myPoset = new Poset<Integer>(4);
		myPoset.addVertex((Integer) 1);
		myPoset.addVertex((Integer) 2);
		myPoset.addVertex((Integer) 3);
		myPoset.addVertex((Integer) 4);
		myPoset.addVertex((Integer) 5);
	
		
		assertTrue(myPoset.addEdge((Integer) 1, (Integer) 1));
		assertTrue(myPoset.hasEdge((Integer) 1, (Integer) 1));
		
		assertTrue(myPoset.addEdge((Integer) 2, (Integer) 2));
		assertTrue(myPoset.hasEdge((Integer) 2, (Integer) 2));
		
		assertTrue(myPoset.addEdge((Integer) 3, (Integer) 3));
		assertTrue(myPoset.hasEdge((Integer) 3, (Integer) 3));
		
		assertTrue(myPoset.addEdge((Integer) 4, (Integer) 4));
		assertTrue(myPoset.hasEdge((Integer) 4, (Integer) 4));
		
		
		assertTrue(myPoset.addEdge((Integer) 4, (Integer) 1));
		assertTrue(myPoset.hasEdge((Integer) 4, (Integer) 1));
		
		assertTrue(myPoset.addEdge((Integer) 4, (Integer) 3));
		assertTrue(myPoset.hasEdge((Integer) 4, (Integer) 3));
		
		assertTrue(myPoset.addEdge((Integer) 2, (Integer) 3));
		assertTrue(myPoset.hasEdge((Integer) 2, (Integer) 3));
		
		assertTrue(myPoset.addEdge((Integer) 3, (Integer) 2));
		assertTrue(myPoset.hasEdge((Integer) 3, (Integer) 2));
		
		System.out.println(myPoset.printEdges());
		
		Poset<Integer> transitiveClosure = myPoset.transitiveClosure();
		System.out.println(transitiveClosure.printEdges());
		
		System.out.println("STARTER VERTICES == :\n" + myPoset.getStarterVertices());
	}

	@Test
	public void testRemoveEdgeTT() {
		fail("Not yet implemented");
	}

	@Test
	public void testPopEdgeTT() {
		fail("Not yet implemented");
	}

	@Test
	public void testTransitiveClosure() {

		Poset<Integer> myPoset = new Poset<Integer>(4);
		myPoset.addVertex((Integer) 1);
		myPoset.addVertex((Integer) 2);
		myPoset.addVertex((Integer) 3);
		myPoset.addVertex((Integer) 4);
	
		
		assertTrue(myPoset.addEdge((Integer) 1, (Integer) 1));
		assertTrue(myPoset.hasEdge((Integer) 1, (Integer) 1));
		
		assertTrue(myPoset.addEdge((Integer) 2, (Integer) 2));
		assertTrue(myPoset.hasEdge((Integer) 2, (Integer) 2));
		
		assertTrue(myPoset.addEdge((Integer) 3, (Integer) 3));
		assertTrue(myPoset.hasEdge((Integer) 3, (Integer) 3));
		
		assertTrue(myPoset.addEdge((Integer) 4, (Integer) 4));
		assertTrue(myPoset.hasEdge((Integer) 4, (Integer) 4));
		
		
		assertTrue(myPoset.addEdge((Integer) 4, (Integer) 1));
		assertTrue(myPoset.hasEdge((Integer) 4, (Integer) 1));
		
		assertTrue(myPoset.addEdge((Integer) 4, (Integer) 3));
		assertTrue(myPoset.hasEdge((Integer) 4, (Integer) 3));
		
		assertTrue(myPoset.addEdge((Integer) 2, (Integer) 3));
		assertTrue(myPoset.hasEdge((Integer) 2, (Integer) 3));
		
		assertTrue(myPoset.addEdge((Integer) 3, (Integer) 2));
		assertTrue(myPoset.hasEdge((Integer) 3, (Integer) 2));
		
		assertTrue(myPoset.addEdge((Integer) 2, (Integer) 4));
		assertTrue(myPoset.hasEdge((Integer) 2, (Integer) 4));
		
		System.out.println(myPoset.printEdges());
		
		Poset<Integer> transitiveClosure = myPoset.transitiveClosure();
		System.out.println(transitiveClosure.printEdges());
	}
	
	@Test
	public void testPruesseRuskeyString() {
		
		Poset<Integer> myPoset = new Poset<Integer>(4);
		myPoset.addVertex((Integer) 1);
		myPoset.addVertex((Integer) 2);
		myPoset.addVertex((Integer) 3);
		myPoset.addVertex((Integer) 4);
		myPoset.addVertex((Integer) 5);
		myPoset.addVertex((Integer) 6);

		assertTrue(myPoset.addEdge((Integer) 4, (Integer) 2));
		assertTrue(myPoset.hasEdge((Integer) 4, (Integer) 2));
		
		assertTrue(myPoset.addEdge((Integer) 4, (Integer) 3));
		assertTrue(myPoset.hasEdge((Integer) 4, (Integer) 3));
		
		assertTrue(myPoset.addEdge((Integer) 2, (Integer) 6));
		assertTrue(myPoset.hasEdge((Integer) 2, (Integer) 6));
		
		assertTrue(myPoset.addEdge((Integer) 3, (Integer) 5));
		assertTrue(myPoset.hasEdge((Integer) 3, (Integer) 5));
		
		assertTrue(myPoset.addEdge((Integer) 6, (Integer) 1));
		assertTrue(myPoset.hasEdge((Integer) 6, (Integer) 1));
		
		assertTrue(myPoset.addEdge((Integer) 5, (Integer) 1));
		assertTrue(myPoset.hasEdge((Integer) 5, (Integer) 1));
		
		System.out.println(myPoset.toString());
		
		String prString = myPoset.pruesseRuskeyString();
		System.out.println("the pr string is::");
		System.out.println(prString);
		
		
		System.out.println("\n\n\n");
		
		
		Poset<Integer> myPoset2 = new Poset<Integer>(4);
		myPoset2.addVertex((Integer) 1);
		myPoset2.addVertex((Integer) 2);
		myPoset2.addVertex((Integer) 3);
		myPoset2.addVertex((Integer) 4);
		myPoset2.addVertex((Integer) 5);
		myPoset2.addVertex((Integer) 6);

		assertTrue(myPoset2.addEdge((Integer) 5, (Integer) 2));
		assertTrue(myPoset2.hasEdge((Integer) 5, (Integer) 2));
		
		assertTrue(myPoset2.addEdge((Integer) 5, (Integer) 6));
		assertTrue(myPoset2.hasEdge((Integer) 5, (Integer) 6));
		
		assertTrue(myPoset2.addEdge((Integer) 2, (Integer) 4));
		assertTrue(myPoset2.hasEdge((Integer) 2, (Integer) 4));
		
		assertTrue(myPoset2.addEdge((Integer) 4, (Integer) 1));
		assertTrue(myPoset2.hasEdge((Integer) 4, (Integer) 1));
		
		assertTrue(myPoset2.addEdge((Integer) 1, (Integer) 3));
		assertTrue(myPoset2.hasEdge((Integer) 1, (Integer) 3));
		
		assertTrue(myPoset2.addEdge((Integer) 6, (Integer) 3));
		assertTrue(myPoset2.hasEdge((Integer) 6, (Integer) 3));
		
		System.out.println(myPoset2.toString());
		
		String prString2 = myPoset2.pruesseRuskeyString();
		System.out.println("the pr string is::");
		System.out.println(prString2);

		
		System.out.println("\n\n\n");
		
		
		Poset<Integer> myPoset3 = new Poset<Integer>(4);
		myPoset3.addVertex((Integer) 1);
		myPoset3.addVertex((Integer) 2);
		myPoset3.addVertex((Integer) 3);
		myPoset3.addVertex((Integer) 4);
		myPoset3.addVertex((Integer) 5);
		myPoset3.addVertex((Integer) 6);
		myPoset3.addVertex((Integer) 7);

		assertTrue(myPoset3.addEdge((Integer) 6, (Integer) 1));
		assertTrue(myPoset3.hasEdge((Integer) 6, (Integer) 1));
		
		assertTrue(myPoset3.addEdge((Integer) 1, (Integer) 5));
		assertTrue(myPoset3.hasEdge((Integer) 1, (Integer) 5));
		
		assertTrue(myPoset3.addEdge((Integer) 5, (Integer) 4));
		assertTrue(myPoset3.hasEdge((Integer) 5, (Integer) 4));
		
		assertTrue(myPoset3.addEdge((Integer) 5, (Integer) 3));
		assertTrue(myPoset3.hasEdge((Integer) 5, (Integer) 3));
		
		assertTrue(myPoset3.addEdge((Integer) 4, (Integer) 2));
		assertTrue(myPoset3.hasEdge((Integer) 4, (Integer) 2));
		
		assertTrue(myPoset3.addEdge((Integer) 3, (Integer) 7));
		assertTrue(myPoset3.hasEdge((Integer) 3, (Integer) 7));
		
		assertTrue(myPoset3.addEdge((Integer) 2, (Integer) 7));
		assertTrue(myPoset3.hasEdge((Integer) 2, (Integer) 7));
		
		System.out.println(myPoset3.toString());
		
		String prString3 = myPoset3.pruesseRuskeyString();
		System.out.println("the pr string is::");
		System.out.println(prString3);
		
		
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		System.out.println("####################################################################");
		System.out.println("####################################################################");
		System.out.println("ADVANCED PRUESSE RUSKEY REP TEST------------------------------------");
		System.out.println("####################################################################");
		
		//Lists of Linear Orders
		ArrayList<ArrayList<Integer>> linearOrders = new ArrayList<>();
		ArrayList<ArrayList<Integer>> linearOrders1 = new ArrayList<>();
		ArrayList<ArrayList<Integer>> linearOrders2 = new ArrayList<>();
		ArrayList<ArrayList<Integer>> linearOrders3 = new ArrayList<>();
		ArrayList<ArrayList<Integer>> linearOrders4 = new ArrayList<>();
		ArrayList<ArrayList<Integer>> linearOrders5 = new ArrayList<>();
		ArrayList<ArrayList<Integer>> linearOrders6 = new ArrayList<>();
		
		//Linear Orders
		ArrayList<Integer> order1 = new ArrayList<Integer>();
		ArrayList<Integer> order2 = new ArrayList<Integer>();
		ArrayList<Integer> order3 = new ArrayList<Integer>();
		ArrayList<Integer> order4 = new ArrayList<Integer>();
		
		//Linear order 1
		order1.add(1);
		order1.add(2);
		order1.add(3);
		order1.add(4);
		order1.add(5);
		order1.add(6);
		order1.add(7);
		order1.add(8);
		order1.add(9);
		System.out.println(order1);
		
		//Linear order 2
		order2.add(1);
		order2.add(2);
		order2.add(3);
		order2.add(4);
		order2.add(6);
		order2.add(5);
		order2.add(7);
		order2.add(8);
		order2.add(9);
		System.out.println(order2);
		
		//Linear order 3
		order3.add(1);
		order3.add(3);
		order3.add(2);
		order3.add(4);
		order3.add(5);
		order3.add(6);
		order3.add(7);
		order3.add(8);
		order3.add(9);
		System.out.println(order3);
		
		//Linear order 4
		order4.add(1);
		order4.add(3);
		order4.add(2);
		order4.add(4);
		order4.add(6);
		order4.add(5);
		order4.add(7);
		order4.add(8);
		order4.add(9);
		System.out.println(order4);
		
		//Sets of linear orders----------
		//set 0
		linearOrders.add(order1);
		linearOrders.add(order2);
		linearOrders.add(order3);
		linearOrders.add(order4);
		
		//set 1
		linearOrders1.add(order1);
		linearOrders1.add(order2);
		
		//set 2
		linearOrders2.add(order1);
		linearOrders2.add(order3);
		
		//set 3
		linearOrders3.add(order1);
		linearOrders3.add(order4);
		
		//set 4
		linearOrders4.add(order2);
		linearOrders4.add(order3);
		
		//set 5
		linearOrders5.add(order2);
		linearOrders5.add(order4);
		
		//set 6
		linearOrders6.add(order3);
		linearOrders6.add(order4);
		
		Poset<Integer> poset = PosetAlgorithms.generatingPosetTwo(linearOrders, false, false);
		System.out.println(poset.pruesseRuskeyString());
		
		poset = PosetAlgorithms.generatingPosetTwo(linearOrders1, false, false);
		System.out.println(poset.pruesseRuskeyString());
		
		poset = PosetAlgorithms.generatingPosetTwo(linearOrders2, false, false);
		System.out.println(poset.pruesseRuskeyString());
		
		poset = PosetAlgorithms.generatingPosetTwo(linearOrders3, false, false);
		System.out.println(poset.pruesseRuskeyString());
		
		poset = PosetAlgorithms.generatingPosetTwo(linearOrders4, false, false);
		System.out.println(poset.pruesseRuskeyString());
		
		poset = PosetAlgorithms.generatingPosetTwo(linearOrders5, false, false);
		System.out.println(poset.pruesseRuskeyString());
		
		poset = PosetAlgorithms.generatingPosetTwo(linearOrders6, false, false);
		System.out.println(poset.pruesseRuskeyString());
	}
	
	@Test
	public void testTransitiveReduction() {
		
		Poset<Integer> myPoset = new Poset<Integer>(4);
		myPoset.addVertex((Integer) 1);
		myPoset.addVertex((Integer) 2);
		myPoset.addVertex((Integer) 3);
		myPoset.addVertex((Integer) 4);
	
		
		//Self Referential edges
		assertTrue(myPoset.addEdge((Integer) 1, (Integer) 1));
		assertTrue(myPoset.hasEdge((Integer) 1, (Integer) 1));
		
		assertTrue(myPoset.addEdge((Integer) 2, (Integer) 2));
		assertTrue(myPoset.hasEdge((Integer) 2, (Integer) 2));
		
		assertTrue(myPoset.addEdge((Integer) 3, (Integer) 3));
		assertTrue(myPoset.hasEdge((Integer) 3, (Integer) 3));
		
		assertTrue(myPoset.addEdge((Integer) 4, (Integer) 4));
		assertTrue(myPoset.hasEdge((Integer) 4, (Integer) 4));
		
		
		//Cover Edges
		assertTrue(myPoset.addEdge((Integer) 1, (Integer) 2));
		assertTrue(myPoset.hasEdge((Integer) 1, (Integer) 2));
		
		assertTrue(myPoset.addEdge((Integer) 1, (Integer) 3));
		assertTrue(myPoset.hasEdge((Integer) 1, (Integer) 3));
		
		assertTrue(myPoset.addEdge((Integer) 2, (Integer) 4));
		assertTrue(myPoset.hasEdge((Integer) 2, (Integer) 4));
		
		assertTrue(myPoset.addEdge((Integer) 3, (Integer) 4));
		assertTrue(myPoset.hasEdge((Integer) 3, (Integer) 4));
		
		
		//"Closure Edges"
		assertTrue(myPoset.addEdge((Integer) 1, (Integer) 4));
		assertTrue(myPoset.hasEdge((Integer) 1, (Integer) 4));
		
		
		System.out.println("BEFORE poset acted on!");
		System.out.println(myPoset.toString());
		
		System.out.println("TRANSITIVE REDUCTION");
		Poset<Integer> transitveReduction = myPoset.transitiveReduction();
		System.out.println(transitveReduction.toString());
	}
}