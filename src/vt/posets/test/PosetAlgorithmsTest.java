package vt.posets.test;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;

import vt.posets.main.PosetAlgorithms;
import vt.posets.poset.Poset;

public class PosetAlgorithmsTest extends TestCase {

	@Test
	public void testGeneratingPosetOne() {
		
		ArrayList<ArrayList<Integer>> linearOrders = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> order1 = new ArrayList<Integer>();
		ArrayList<Integer> order2 = new ArrayList<Integer>();
		
		order1.add(1);
		order1.add(2);
		order1.add(3);
		order1.add(4);
		linearOrders.add(order1);
		
		order2.add(1);
		order2.add(3);
		order2.add(2);
		order2.add(4);
		linearOrders.add(order2);
		System.out.println(linearOrders.toString());
		
		Poset<Integer> p = PosetAlgorithms.generatingPosetOne(linearOrders, false);
	
		assertTrue(p != null);
		System.out.println("ALGORITHM RAN SUCCESSFULLY FOR KITE");
		System.out.println(p.toString());
		System.out.println(p.printCoverEdges());
	}
	
	@Test
	public void testGeneratingPosetTwo() {

		//System.out.println("KITE TEST-------------------------------------------------");
		ArrayList<ArrayList<Integer>> linearOrders = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> order1 = new ArrayList<Integer>();
		ArrayList<Integer> order2 = new ArrayList<Integer>();
		Poset<Integer> p;

		order1.add(1);
		order1.add(2);
		order1.add(3);
		order1.add(4);
		linearOrders.add(order1);
		
		order2.add(1);
		order2.add(3);
		order2.add(2);
		order2.add(4);
		linearOrders.add(order2);
		System.out.println(linearOrders.toString());
		
		p = PosetAlgorithms.generatingPosetTwo(linearOrders, false, false);
	
		assertTrue(p != null);
		System.out.println("ALGORITHM RAN SUCCESSFULLY FOR KITE");
		System.out.println(p.printVertices());
		System.out.println("");
		System.out.println(p.printEdges());
		
		
		System.out.println("ONE LINEAR ORDER TEST-----------------------------------");
		linearOrders = new ArrayList<>();
		order1 = new ArrayList<>();
		order1.add(1);
		order1.add(2);
		order1.add(4);
		order1.add(3);
		order1.add(5);
		linearOrders.add(order1);
		
		System.out.println(linearOrders.toString());
		p = PosetAlgorithms.generatingPosetTwo(linearOrders, false, false);
		assertTrue(p != null);
		System.out.println("ALGORITHM RAN SUCCESSFULLY FOR SINGLE LINEAR ORDER CASE!");
		System.out.println(p.toString());

		
		System.out.println("\n\n\n\nTHAT WEIRD 3 LINEAR ORDERS TEST-----------------------------------");
		linearOrders = new ArrayList<>();
		order1 = new ArrayList<>();
		order2 = new ArrayList<>();
		ArrayList<Integer> order3 = new ArrayList<>();
		
		order1.add(1);
		order1.add(2);
		order1.add(3);
		order1.add(4);
		order1.add(5);
		linearOrders.add(order1);
		
		order2.add(1);
		order2.add(3);
		order2.add(2);
		order2.add(4);
		order2.add(5);
		linearOrders.add(order2);
		
		order3.add(1);
		order3.add(3);
		order3.add(4);
		order3.add(2);
		order3.add(5);
		linearOrders.add(order3);
		
		System.out.println(linearOrders.toString());
		p = PosetAlgorithms.generatingPosetTwo(linearOrders, false, false);
		assertTrue(p != null);
		System.out.println("ALGORITHM RAN SUCCESSFULLY FOR that one WEIRD THREE LINEAR ORDER CASE!");
		System.out.println(p.toString());
		
		System.out.println("\n\n\n\n\n\n\n\n\n\n$$$THAT NEW WEIRD CASE TEST----------------------------------------");
		System.out.println("$$$THAT NEW WEIRD CASE TEST----------------------------------------");
		System.out.println("$$$THAT NEW WEIRD CASE TEST----------------------------------------");
		linearOrders = new ArrayList<>();
		order1 = new ArrayList<>();
		order2 = new ArrayList<>();
		order3 = new ArrayList<>();
		ArrayList<Integer> order4 = new ArrayList<>();
		ArrayList<Integer> order5 = new ArrayList<>();
		ArrayList<Integer> order6 = new ArrayList<>();
		
		order1.add(1);
		order1.add(2);
		order1.add(3);
		order1.add(4);
		linearOrders.add(order1);
		
		order2.add(1);
		order2.add(4);
		order2.add(2);
		order2.add(3);
		linearOrders.add(order2);
		
		order3.add(1);
		order3.add(2);
		order3.add(4);
		order3.add(3);
		linearOrders.add(order3);
		
		order4.add(1);
		order4.add(3);
		order4.add(2);
		order4.add(4);
		linearOrders.add(order4);
		
		order5.add(1);
		order5.add(4);
		order5.add(3);
		order5.add(2);
		linearOrders.add(order5);
		
		order6.add(1);
		order6.add(3);
		order6.add(4);
		order6.add(2);
		linearOrders.add(order6);
		
		System.out.println(linearOrders.toString());
		p = PosetAlgorithms.generatingPosetTwo(linearOrders, false, false);
		assertTrue(p != null);
		System.out.println("ALGORITHM RAN SUCCESSFULLY FOR that NEW WEIRD CASE TEST!");
		System.out.println(p.toString());
		
		
		
		System.out.println("\n\n3 5 element linear orders----------------------------------------");
		System.out.println("3 5 element linear orders----------------------------------------");
		System.out.println("3 5 element linear orders----------------------------------------");
		linearOrders = new ArrayList<>();
		order1 = new ArrayList<>();
		order2 = new ArrayList<>();
		order3 = new ArrayList<>();
		
		order1.add(1);
		order1.add(2);
		order1.add(3);
		order1.add(4);
		order1.add(5);
		linearOrders.add(order1);
		
		order2.add(1);
		order2.add(2);
		order2.add(3);
		order2.add(5);
		order2.add(4);
		linearOrders.add(order2);
		
		order3.add(1);
		order3.add(2);
		order3.add(4);
		order3.add(3);
		order3.add(5);
		linearOrders.add(order3);
		
		System.out.println(linearOrders.toString());
		p = PosetAlgorithms.generatingPosetTwo(linearOrders, false, false);
		assertTrue(p != null);
		System.out.println("ALGORITHM RAN SUCCESSFULLY FOR that NEW WEIRD CASE TEST!");
		System.out.println(p.toString());
		
		
		
		
		
		System.out.println("\n\n\n\n\n\n\n\n6 ORDERS WITH 5 ELEMENTS----------------------------------------");
		linearOrders = new ArrayList<>();
		order1 = new ArrayList<>();
		order2 = new ArrayList<>();
		order3 = new ArrayList<>();
		order4 = new ArrayList<>();
		order5 = new ArrayList<>();
		order6 = new ArrayList<>();
		
		order1.add(1);
		order1.add(2);
		order1.add(3);
		order1.add(4);
		order1.add(5);
		linearOrders.add(order1);
		
		order2.add(1);
		order2.add(2);
		order2.add(5);
		order2.add(3);
		order2.add(4);
		linearOrders.add(order2);
		
		order3.add(1);
		order3.add(2);
		order3.add(3);
		order3.add(5);
		order3.add(4);
		linearOrders.add(order3);
		
		order4.add(1);
		order4.add(2);
		order4.add(4);
		order4.add(3);
		order4.add(5);
		linearOrders.add(order4);
		
		order5.add(1);
		order5.add(2);
		order5.add(5);
		order5.add(4);
		order5.add(3);
		linearOrders.add(order5);
		
		order6.add(1);
		order6.add(2);
		order6.add(4);
		order6.add(5);
		order6.add(3);
		linearOrders.add(order6);
		
		System.out.println(linearOrders.toString());
		p = PosetAlgorithms.generatingPosetTwo(linearOrders, false, false);
		assertTrue(p != null);
		System.out.println("ALGORITHM RAN SUCCESSFULLY FOR that NEW WEIRD CASE TEST!");
		System.out.println(p.toString());
		
		
		
		
		
		//[1, 2, 3, 4, 5], [1, 2, 4, 5, 3], [1, 4, 2, 5, 3], [1, 4, 2, 3, 5], [1, 2, 4, 3, 5], [1, 3, 2, 4, 5], [1, 4, 3, 2, 5], [1, 3, 4, 2, 5]]
		//[1, 2, 3, 4, 5], [1, 2, 4, 5, 3], [1, 4, 2, 5, 3], [1, 4, 2, 3, 5], [1, 2, 4, 3, 5], [1, 3, 2, 4, 5], [1, 4, 3, 2, 5], [1, 3, 4, 2, 5]]
		System.out.println("\n\n\n\n\n\n\n\n8 ORDERS WITH 5 ELEMENTS----------------------------------------");
		linearOrders = new ArrayList<>();
		order1 = new ArrayList<>();
		order2 = new ArrayList<>();
		order3 = new ArrayList<>();
		order4 = new ArrayList<>();
		order5 = new ArrayList<>();
		order6 = new ArrayList<>();
		ArrayList<Integer> order7 = new ArrayList<>();
		ArrayList<Integer> order8 = new ArrayList<>();
		
		order1.add(1);
		order1.add(2);
		order1.add(3);
		order1.add(4);
		order1.add(5);
		linearOrders.add(order1);
		
		order2.add(1);
		order2.add(2);
		order2.add(4);
		order2.add(5);
		order2.add(3);
		linearOrders.add(order2);
		
		order3.add(1);
		order3.add(4);
		order3.add(2);
		order3.add(5);
		order3.add(3);
		linearOrders.add(order3);
		
		order4.add(1);
		order4.add(4);
		order4.add(2);
		order4.add(3);
		order4.add(5);
		linearOrders.add(order4);
		
		order5.add(1);
		order5.add(2);
		order5.add(4);
		order5.add(3);
		order5.add(5);
		linearOrders.add(order5);
		
		order6.add(1);
		order6.add(3);
		order6.add(2);
		order6.add(4);
		order6.add(5);
		linearOrders.add(order6);
		
		order7.add(1);
		order7.add(4);
		order7.add(3);
		order7.add(2);
		order7.add(5);
		linearOrders.add(order7);
		
		order8.add(1);
		order8.add(3);
		order8.add(4);
		order8.add(2);
		order8.add(5);
		linearOrders.add(order8);
		
		System.out.println(linearOrders.toString());
		p = PosetAlgorithms.generatingPosetTwo(linearOrders, false, false);
		assertTrue(p != null);
		System.out.println("ALGORITHM RAN SUCCESSFULLY FOR that NEW WEIRD CASE TEST!");
		System.out.println(p.toString());
		
		
		
		//[[1, 4, 2, 3, 5], [1, 2, 4, 3, 5], [1, 2, 3, 4, 5]]
		System.out.println("\n\n3 5 element linear orders AGAIN----------------------------------------");
		System.out.println("3 5 element linear orders AGAIN----------------------------------------");
		System.out.println("3 5 element linear orders AGAIN----------------------------------------");
		linearOrders = new ArrayList<>();
		order1 = new ArrayList<>();
		order2 = new ArrayList<>();
		order3 = new ArrayList<>();
		
		order1.add(1);
		order1.add(4);
		order1.add(2);
		order1.add(3);
		order1.add(5);
		linearOrders.add(order1);
		
		order2.add(1);
		order2.add(2);
		order2.add(4);
		order2.add(3);
		order2.add(5);
		linearOrders.add(order2);
		
		order3.add(1);
		order3.add(2);
		order3.add(3);
		order3.add(4);
		order3.add(5);
		linearOrders.add(order3);
		
		System.out.println(linearOrders.toString());
		p = PosetAlgorithms.generatingPosetTwo(linearOrders, false, false);
		assertTrue(p != null);
		System.out.println("ALGORITHM RAN SUCCESSFULLY FOR that NEW WEIRD CASE TEST!");
		System.out.println(p.toString());
	}
	
	@Test
	public void testParseLinearOrders() {

		//Poset<Integer> p 
	}
	
	@Test
	public void testGeneratingKitePosetCover() {
		
		System.out.println("Generating Kite Poset Cover TEST #1------------------------------------------");
		ArrayList<ArrayList<Integer>> linearOrders = new ArrayList<ArrayList<Integer>>();
		
		ArrayList<Integer> order1 = new ArrayList<Integer>();
		ArrayList<Integer> order2 = new ArrayList<Integer>();
		
		order1.add(1);
		order1.add(2);
		order1.add(3);
		order1.add(4);
		linearOrders.add(order1);
		System.out.println(order1);
		
		order2.add(1);
		order2.add(3);
		order2.add(2);
		order2.add(4);
		linearOrders.add(order2);
		System.out.println(order2);

		List<Poset<Integer>> posetCover = PosetAlgorithms.generatingKitePosetCover(linearOrders);

		for (Poset<Integer> p : posetCover) {
			
			System.out.println(p.toString());
		}
		
		System.out.println("#############################################################################");
		System.out.println("-----------------------------------------------------------------------------\n\n");
		///////////////////////////////////////////////////////////////////////////////////////////////////
		
		System.out.println("Generating Kite Poset Cover TEST #2------------------------------------------");
		linearOrders = new ArrayList<ArrayList<Integer>>();
		
		order1 = new ArrayList<Integer>();
		order2 = new ArrayList<Integer>();
		
		order1.add(1);
		order1.add(2);
		order1.add(3);
		order1.add(4);
		order1.add(5);
		linearOrders.add(order1);
		System.out.println(order1);
		
		order2.add(1);
		order2.add(3);
		order2.add(2);
		order2.add(4);
		order2.add(5);
		linearOrders.add(order2);
		System.out.println(order2);

		Poset<Integer> genPos2 = PosetAlgorithms.generatingPosetTwo(linearOrders, false, false);
		System.out.println("...........................................................................GenPos2 Poset from LinearOrders....................................");
		System.out.println(genPos2.toString());
		
		posetCover = PosetAlgorithms.generatingKitePosetCover(linearOrders);

		System.out.println("\nPosets in Poset Cover....");
		for (Poset<Integer> p : posetCover) {
			
			System.out.println(p.toString());
		}

		System.out.println("#############################################################################");
		System.out.println("-----------------------------------------------------------------------------\n\n");
		///////////////////////////////////////////////////////////////////////////////////////////////////
		
		System.out.println("Generating Kite Poset Cover TEST #3------------------------------------------");
		linearOrders = new ArrayList<ArrayList<Integer>>();
		
		order1 = new ArrayList<Integer>();
		order2 = new ArrayList<Integer>();
		ArrayList<Integer> order3 = new ArrayList<Integer>();
		ArrayList<Integer> order4 = new ArrayList<Integer>();
		
		order1.add(1);
		order1.add(2);
		order1.add(3);
		order1.add(4);
		order1.add(5);
		order1.add(6);
		order1.add(7);
		order1.add(8);
		order1.add(9);
		linearOrders.add(order1);
		System.out.println(order1);
		
		order2.add(1);
		order2.add(2);
		order2.add(3);
		order2.add(4);
		order2.add(6);
		order2.add(5);
		order2.add(7);
		order2.add(8);
		order2.add(9);
		linearOrders.add(order2);
		System.out.println(order2);
		
		order3.add(1);
		order3.add(3);
		order3.add(2);
		order3.add(4);
		order3.add(5);
		order3.add(6);
		order3.add(7);
		order3.add(8);
		order3.add(9);
		linearOrders.add(order3);
		System.out.println(order3);
		
		order4.add(1);
		order4.add(3);
		order4.add(2);
		order4.add(4);
		order4.add(6);
		order4.add(5);
		order4.add(7);
		order4.add(8);
		order4.add(9);
		linearOrders.add(order4);
		System.out.println(order4);

		posetCover = PosetAlgorithms.generatingKitePosetCover(linearOrders);

		System.out.println("PRINTING OUT POSET COVERS!");
		for (Poset<Integer> p : posetCover) {
			
			System.out.println(p.toString());
		}
		
		ArrayList<ArrayList<String>> l1 = PosetAlgorithms.runPruesseRuskey(posetCover.get(0), 9, "prTrash1");
		ArrayList<ArrayList<Integer>> l1Map = posetCover.get(0).applyMapping(l1);
		
		ArrayList<ArrayList<String>> l2 = PosetAlgorithms.runPruesseRuskey(posetCover.get(1), 9, "prTrash2");
		ArrayList<ArrayList<Integer>> l2Map = posetCover.get(1).applyMapping(l2);
		
		//ArrayList<ArrayList<String>> l3 = PosetAlgorithms.runPruesseRuskey(posetCover.get(2), 9, "prTrash3");
		//ArrayList<ArrayList<Integer>> l3Map = posetCover.get(2).applyMapping(l3);
		
		System.out.println("UNMAPPED LINEAR ORDERS----------------");
		System.out.println(l1 + "\n" + l2 + "\n"/* + l3*/);
		System.out.println("MAPPED LINEAR ORDERS----------------");
		System.out.println(l1Map + "\n" + l2Map + "\n"/* + l3Map*/);
		
		System.out.println("#############################################################################");
		System.out.println("-----------------------------------------------------------------------------\n\n");
		///////////////////////////////////////////////////////////////////////////////////////////////////
	}
	
	@Test
	public void testGenerateKitePosets() {
		
		System.out.println("GenerateKitePosets test 1-----------------------------------------------------------------");
		List<Integer> elements = new ArrayList<>();
		elements.add(1);
		elements.add(2);
		elements.add(3);
		elements.add(4);
		List<Poset<Integer>> posets = PosetAlgorithms.generateKitePosets(elements, 4, 2, 2, false, false);
		
		System.out.println("Printing generated Posets:");
		int n = 1;
		for (Poset<Integer> p : posets) {
			
			System.out.println(n + "th Poset");
			System.out.println(p.toString());
			n++;
		}
		
		
		System.out.println("GenerateKitePosets test 2-----------------------------------------------------------------");
		elements = new ArrayList<>();
		elements.add(11);
		elements.add(22);
		elements.add(33);
		elements.add(44);
		posets = PosetAlgorithms.generateKitePosets(elements, 4, 2, 2, false, false);
		
		System.out.println("Printing generated Posets:");
		n = 1;
		for (Poset<Integer> p : posets) {
			
			System.out.println(n + "th Poset");
			System.out.println(p.toString());
			n++;
		}
		
		
		System.out.println("GenerateKitePosets test 3-----------------------------------------------------------------");
		elements = new ArrayList<>();
		elements.add(1);
		elements.add(2);
		elements.add(3);
		elements.add(4);
		elements.add(5);
		elements.add(6);
		elements.add(7);
		elements.add(8);
		posets = PosetAlgorithms.generateKitePosets(elements, 4, 2, 2, false, false);
		
		System.out.println("Printing generated Posets:");
		n = 1;
		for (Poset<Integer> p : posets) {
			
			System.out.println(n + "th Poset");
			System.out.println(p.toString());
			n++;
		}
		

		System.out.println("GenerateKitePosets test 4-----------------------------------------------------------------");
		elements = new ArrayList<>();
		elements.add(1);
		elements.add(2);
		elements.add(3);
		elements.add(4);
		elements.add(5);
		elements.add(6);
		elements.add(7);
		elements.add(8);
		elements.add(9);
		posets = PosetAlgorithms.generateKitePosets(elements, 4, 2, 2, false, false);
		
		System.out.println("Printing generated Posets:");
		n = 1;
		for (Poset<Integer> p : posets) {
			
			System.out.println(n + "th Poset");
			System.out.println(p.toString());
			n++;
		}
	}
	
	@Test
	public void testGenerateRandomPosets() {
		
		List<Integer> elements = new ArrayList<Integer>();
		elements.add(1);
		elements.add(2);
		elements.add(3);
		elements.add(4);
		elements.add(5);
		elements.add(6);
		
		List<Poset<Integer>> posets = PosetAlgorithms.generateRandomPosets(elements, 15, 1, 1);
		
		int k = 1;
		for (Poset<Integer> poset : posets) {
			
			System.out.println("#############################################################");
			System.out.println("On the " + k + "th Poset");
			System.out.println("Size of elements: " + elements.size());
			System.out.println("The POSET is:\n" + poset.toString());
			
			//Run Pruesse & Ruskey to get LinearExtensions of Random POSET
			ArrayList<ArrayList<String>> parsedLinearOrders = PosetAlgorithms.runPruesseRuskey(poset, elements.size(), "TestRandomPosetGeneration");
			//Convert Strings to Integers
			ArrayList<ArrayList<Integer>> numericalLinearOrders = PosetAlgorithms.parsedPruesseRuskeyToInteger(parsedLinearOrders);
			
			//TEST that the linear orders generated from the generated Poset are correct.
			assertTrue(PosetAlgorithms.prLinearExtensionsCheck(numericalLinearOrders, poset, false));
			
			//TEST that the linear orders were parsed correctly
			System.out.println("Comparing linear orders: ");
			assertEquals(parsedLinearOrders.toString(), numericalLinearOrders.toString());
System.out.println(numericalLinearOrders.toString());			
			//RUN GENERATING POSET #2 ALGORITHM
			Poset<Integer> generatedPoset = PosetAlgorithms.generatingPosetTwo(numericalLinearOrders, false, false);

			//TEST that we have a valid result
			assertNotNull(generatedPoset);
			
			//Print the Original Poset in comparison to the Generated Poset
			System.out.println("A COMPARISON!!------\nGeneratedPoset (by algorithm)");
			System.out.println(generatedPoset.toString());
			System.out.println("OriginalPoset (by random generation)");
			System.out.println(poset.toString());

			System.out.println("remember, this is the " + k + "th poset");
			
			//TEST that the algorithm #2 poset (generatedPoset) is equal to the randomly generated poset (poset)
			assertEquals(generatedPoset, poset);
			
			k++;
		}
	}
}
