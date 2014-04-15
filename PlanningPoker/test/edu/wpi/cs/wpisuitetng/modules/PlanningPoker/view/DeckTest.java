/*******************************************************************************
 * Copyright (c) 2013-2014 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Team Rolling Thunder, struct-by-lightning
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.PlanningPoker.view;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.google.gson.JsonElement;

import edu.wpi.cs.wpisuitetng.modules.PlanningPoker.deck.Deck;

/**
 * Tests for the Deck class
 * 
 * @author Alec Thompson - ajthompson
 * @version Apr 10, 2014
 */
public class DeckTest {

	/**
	 * Used to test exceptions being thrown
	 */
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	/**
	 * Test method for
	 * {@link edu.wpi.cs.wpisuitetng.modules.PlanningPoker.deck.Deck#Deck(java.lang.String)}
	 * .
	 */
	@Test
	public void testDeckString() {
		// set up unordered list of cards in deck
		Integer[] cards = new Integer[] { 3, 1, 13, 21, 5, 8, 2, 1 };

		Deck test = new Deck("test");

		for (Integer i : cards) {
			test.addCard(i);
		}

		// set up expected values and results
		String expectedName = "test";
		String name = test.getDeckName();
		Integer[] expected = new Integer[] { 1, 1, 2, 3, 5, 8, 13, 21 };
		Object[] deckResult = test.getCards().toArray();

		// tests
		assertTrue(expectedName.equals(name));
		// compare the arrays
		assertEquals(expected.length, deckResult.length);
		for (int i = 0; i < Math.min(expected.length, deckResult.length); i++) {
			assertEquals(expected[i], deckResult[i]);
		}
	}

	/**
	 * Test method for
	 * {@link edu.wpi.cs.wpisuitetng.modules.PlanningPoker.deck.Deck#Deck(java.lang.String, java.util.ArrayList)}
	 * .
	 */
	@Test
	public void testDeckStringArrayListOfIntegerOrdered() {
		// set up ordered list of cards in deck
		Integer[] cards = new Integer[] { 1, 1, 2, 3, 5, 8, 13, 21 };

		ArrayList<Integer> cardList = new ArrayList<Integer>();

		for (Integer i : cards) {
			cardList.add(i);
		}

		Deck test = new Deck("test", cardList);

		// set up expected values and results
		String expectedName = "test";
		String name = test.getDeckName();
		Integer[] expected = new Integer[] { 1, 1, 2, 3, 5, 8, 13, 21 };
		Object[] deckResult = test.getCards().toArray();

		// tests
		assertTrue(expectedName.equals(name));
		// compare the arrays
		assertEquals(expected.length, deckResult.length);
		for (int i = 0; i < Math.min(expected.length, deckResult.length); i++) {
			assertEquals(expected[i], deckResult[i]);
		}
	}

	/**
	 * Test method for
	 * {@link edu.wpi.cs.wpisuitetng.modules.PlanningPoker.deck.Deck#Deck(java.lang.String, java.util.ArrayList)}
	 * .
	 */
	@Test
	public void testDeckStringArrayListOfIntegerUnOrdered() {
		// set up unordered list of cards in deck
		Integer[] cards = new Integer[] { 3, 1, 13, 21, 5, 8, 2, 1 };

		ArrayList<Integer> cardList = new ArrayList<Integer>();

		for (Integer i : cards) {
			cardList.add(i);
		}

		Deck test = new Deck("test", cardList);

		// set up expected values and results
		String expectedName = "test";
		String name = test.getDeckName();
		Integer[] expected = new Integer[] { 1, 1, 2, 3, 5, 8, 13, 21 };
		Object[] deckResult = test.getCards().toArray();

		// tests
		assertTrue(expectedName.equals(name));
		// compare the arrays
		assertEquals(expected.length, deckResult.length);
		for (int i = 0; i < Math.min(expected.length, deckResult.length); i++) {
			assertEquals(expected[i], deckResult[i]);
		}
	}

	/**
	 * Tests both addCard and sortDeck, as addCard calls sortDeck to sort the
	 * new item into the correct place
	 * 
	 * Test method for
	 * {@link edu.wpi.cs.wpisuitetng.modules.PlanningPoker.deck.Deck#addCard(java.lang.Integer)}
	 * {@link edu.wpi.cs.wpisuitetng.modules.PlanningPoker.deck.Deck#sortDeck()}
	 */
	@Test
	public void testAddCard() {
		// set up ordered list of cards in deck
		Integer[] cards = new Integer[] { 1, 1, 2, 3, 5, 8, 13, 21 };

		ArrayList<Integer> cardList = new ArrayList<Integer>();

		for (Integer i : cards) {
			cardList.add(i);
		}

		Deck test = new Deck("test", cardList);

		// add a value to the list
		test.addCard(11);

		// set up expected values and results
		Integer[] expected = new Integer[] { 1, 1, 2, 3, 5, 8, 11, 13, 21 };
		Object[] deckResult = test.getCards().toArray();

		// compare the arrays
		assertEquals(expected.length, deckResult.length);
		for (int i = 0; i < Math.min(expected.length, deckResult.length); i++) {
			assertEquals(expected[i], deckResult[i]);
		}
	}

	/**
	 * Test method for
	 * {@link edu.wpi.cs.wpisuitetng.modules.PlanningPoker.deck.Deck#removeCard(java.lang.Integer)}
	 * .
	 */
	@Test
	public void testRemoveCard() {
		// set up ordered list of cards in deck
		Integer[] cards = new Integer[] { 1, 1, 2, 3, 5, 8, 13, 21 };

		ArrayList<Integer> cardList = new ArrayList<Integer>();

		for (Integer i : cards) {
			cardList.add(i);
		}

		Deck test = new Deck("test", cardList);

		// remove a value to the list
		test.removeCard(8);

		// set up expected values and results
		Integer[] expected = new Integer[] { 1, 1, 2, 3, 5, 13, 21 };
		Object[] deckResult = test.getCards().toArray();

		// compare the arrays
		assertEquals(expected.length, deckResult.length);
		for (int i = 0; i < Math.min(expected.length, deckResult.length); i++) {
			assertEquals(expected[i], deckResult[i]);
		}
	}

	/**
	 * Test method for
	 * {@link edu.wpi.cs.wpisuitetng.modules.PlanningPoker.deck.Deck#getDeckName()}
	 * .
	 */
	@Test
	public void testGetDeckName() {
		// set up ordered list of cards in deck
		Integer[] cards = new Integer[] { 1, 1, 2, 3, 5, 8, 13, 21 };

		ArrayList<Integer> cardList = new ArrayList<Integer>();

		for (Integer i : cards) {
			cardList.add(i);
		}

		Deck test = new Deck("test", cardList);

		// set up expected values and results
		String expectedName = "test";
		String name = test.getDeckName();

		// tests
		assertTrue(expectedName.equals(name));
	}

	/**
	 * Test method for
	 * {@link edu.wpi.cs.wpisuitetng.modules.PlanningPoker.deck.Deck#getCards()}
	 * .
	 */
	@Test
	public void testGetCards() {
		// set up ordered list of cards in deck
		Integer[] cards = new Integer[] { 1, 1, 2, 3, 5, 8, 13, 21 };

		ArrayList<Integer> cardList = new ArrayList<Integer>();

		for (Integer i : cards) {
			cardList.add(i);
		}

		Deck test = new Deck("test", cardList);

		// set up expected values and results
		Integer[] expected = new Integer[] { 1, 1, 2, 3, 5, 8, 13, 21 };
		Object[] deckResult = test.getCards().toArray();

		// compare the arrays
		assertEquals(expected.length, deckResult.length);
		for (int i = 0; i < Math.min(expected.length, deckResult.length); i++) {
			assertEquals(expected[i], deckResult[i]);
		}
	}

	/**
	 * Test method for
	 * {@link edu.wpi.cs.wpisuitetng.modules.PlanningPoker.deck.Deck#setDeckName(java.lang.String)}
	 * .
	 */
	@Test
	public void testSetDeckName() {
		// set up ordered list of cards in deck
		Integer[] cards = new Integer[] { 1, 1, 2, 3, 5, 8, 13, 21 };

		ArrayList<Integer> cardList = new ArrayList<Integer>();

		for (Integer i : cards) {
			cardList.add(i);
		}

		Deck test = new Deck("test", cardList);

		// set up expected values and results
		String expectedName = "test";
		String name = test.getDeckName();

		// tests
		assertTrue(expectedName.equals(name));

		test.setDeckName("different");

		assertTrue("different".equals(test.getDeckName()));
	}

	/**
	 * Test method for
	 * {@link edu.wpi.cs.wpisuitetng.modules.PlanningPoker.deck.Deck#setCards(java.util.ArrayList)}
	 * .
	 */
	@Test
	public void testSetCards() {
		// set up ordered list of cards in deck
		Integer[] cards = new Integer[] { 1, 1, 2, 3, 5, 8, 13, 21 };

		ArrayList<Integer> cardList = new ArrayList<Integer>();

		for (Integer i : cards) {
			cardList.add(i);
		}

		Deck test = new Deck("test");

		test.setCards(cardList);

		// set up expected values and results
		String expectedName = "test";
		String name = test.getDeckName();
		Integer[] expected = new Integer[] { 1, 1, 2, 3, 5, 8, 13, 21 };
		Object[] deckResult = test.getCards().toArray();

		// tests
		assertTrue(expectedName.equals(name));
		// compare the arrays
		assertEquals(expected.length, deckResult.length);
		for (int i = 0; i < Math.min(expected.length, deckResult.length); i++) {
			assertEquals(expected[i], deckResult[i]);
		}
	}

	@Test
	public void testSerialize() {
		// set up ordered list of cards in deck
		Integer[] cards = new Integer[] { 1, 1, 2, 3, 5, 8, 13, 21 };

		ArrayList<Integer> cardList = new ArrayList<Integer>();

		for (Integer i : cards) {
			cardList.add(i);
		}

		Deck test = new Deck("test", cardList);
		String serialized = test.toJSON();
		
		System.out.println(serialized);
		
		assertTrue(serialized.contains("deckName"));
		assertTrue(serialized.contains("test"));
		assertTrue(serialized.contains("cards"));
		assertTrue(serialized.contains("[1,1,2,3,5,8,13,21]"));
	}

	@Test
	public void testSerializeArray() {
		// set up ordered list of cards in deck
		Integer[] cards = new Integer[] { 1, 1, 2, 3, 5, 8, 13, 21 };
		Integer[] cards2 = new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

		ArrayList<Integer> cardList = new ArrayList<Integer>();
		ArrayList<Integer> cardList2 = new ArrayList<Integer>();

		for (Integer i : cards) {
			cardList.add(i);
		}
		
		for (Integer i : cards2) {
			cardList2.add(i);
		}

		Deck test = new Deck("test", cardList);
		Deck test2 = new Deck("test2", cardList2);
		
		String serialized = test.toJSON();
		String serialized2 = test2.toJSON();
		
		assertTrue(serialized.contains("deckName"));
		assertTrue(serialized.contains("test"));
		assertTrue(serialized.contains("cards"));
		assertTrue(serialized.contains("[1,1,2,3,5,8,13,21]"));
		assertTrue(serialized2.contains("deckName"));
		assertTrue(serialized2.contains("test2"));
		assertTrue(serialized2.contains("cards"));
		assertTrue(serialized2.contains("[1,2,3,4,5,6,7,8,9,10]"));
	}
	
	@Test
	public void constructorNullName() { 
		thrown.expect(NullPointerException.class);
		thrown.expectMessage("DeckName must not be null");
		Deck test = new Deck(null, null);
	}
	
	@Test
	public void serializeNullCards() {
		Deck test1 = new Deck("test1");
		Deck test2 = new Deck("test2", null);
		
		String serialized1 = test1.toJSON();
		String serialized2 = test2.toJSON();
		
		assertTrue(serialized1.contains("deckName"));
		assertTrue(serialized2.contains("deckName"));
		assertTrue(serialized1.contains("test1"));
		assertTrue(serialized2.contains("test2"));
		assertFalse(serialized1.contains("cards"));
		assertFalse(serialized2.contains("cards"));
	}
}
