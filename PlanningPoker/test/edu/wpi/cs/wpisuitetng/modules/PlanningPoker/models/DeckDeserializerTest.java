/*******************************************************************************
 * Copyright (c) 2012-2014 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: team struct-by-lightning
 *******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.PlanningPoker.models;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import edu.wpi.cs.wpisuitetng.modules.PlanningPoker.deck.Deck;
import edu.wpi.cs.wpisuitetng.modules.core.models.User;
import edu.wpi.cs.wpisuitetng.modules.core.models.UserDeserializer;

/**
 * Tests the DeckDeserializer
 * 
 * @author Alec Thompson - ajthompson
 * @version Apr 14, 2014
 */
public class DeckDeserializerTest {

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	GsonBuilder gson;

	@Before
	public void setUp() {
		this.gson = new GsonBuilder();
		this.gson.registerTypeAdapter(Deck.class, new DeckDeserializer());
	}

	/**
	 * Test method for
	 * {@link edu.wpi.cs.wpisuitetng.modules.PlanningPoker.models.DeckDeserializer#deserialize(com.google.gson.JsonElement, java.lang.reflect.Type, com.google.gson.JsonDeserializationContext)}
	 * .
	 */
	@Test
	public void testNoDeckNameDeserialize() {
		thrown.expect(JsonParseException.class);
		thrown.expectMessage("The serialized deck did not contain the required deckName field.");
		
		String jsonDeck = "{\"cards\":[1,1,2,3,5,8,13,21]}";
		Gson deserializer = this.gson.create();
		
		Deck inflated = deserializer.fromJson(jsonDeck, Deck.class);
	}
	
	@Test
	public void testEmptyJsonDeserialize() {
		thrown.expect(JsonParseException.class);
		thrown.expectMessage("The serialized deck did not contain the required deckName field.");
		
		String jsonDeck = "{}";
		Gson deserializer = this.gson.create();
		
		Deck inflated = deserializer.fromJson(jsonDeck, Deck.class);
	}
	
	@Test
	public void testNoCardsDeserializeSuccess() {
		String jsonDeck = "{\"deckName\":\"test\"}";
		Gson deserializer = this.gson.create();
		
		Deck inflated = deserializer.fromJson(jsonDeck, Deck.class);
		assertTrue("test".equals(inflated.getDeckName()));
		assertEquals(null, inflated.getCards());
	}
	
	@Test
	public void testDeserializeSuccess() {
		String jsonDeck = "{\"deckName\":\"test\",\"cards\":[1,1,2,3,5,8,13,21]}";
		Gson deserializer = this.gson.create();
		
		Deck inflated = deserializer.fromJson(jsonDeck, Deck.class);
		
		ArrayList<Integer> expected = new ArrayList<Integer>() {{
			add(1);
			add(1);
			add(2);
			add(3);
			add(5);
			add(8);
			add(13);
			add(21);
		}};
		
		assertTrue("test".equals(inflated.getDeckName()));
		assertTrue(expected.equals(inflated.getCards()));
	}
}
