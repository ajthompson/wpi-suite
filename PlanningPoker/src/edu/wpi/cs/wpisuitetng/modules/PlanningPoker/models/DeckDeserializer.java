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

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import edu.wpi.cs.wpisuitetng.modules.PlanningPoker.deck.Deck;

/**
 * Deserializes a JSON string of a deck and constructs the Deck locally.
 * 
 * @author Alec Thompson - ajthompson
 * @version Apr 10, 2014
 */
public class DeckDeserializer implements JsonDeserializer<Deck> {
	private static final Logger logger = Logger
			.getLogger(DeckDeserializer.class.getName());

	/**
	 * Deserializes a JSON serialized Deck
	 * 
	 * @param dElement
	 *            the JsonElement being deserialized
	 * @param dType
	 *            the class to be deserialized into
	 * @param context
	 *            the JSON deserialization context
	 * 
	
	
	
	 * @return the deflated Deck * @throws JsonParseException * @throws JsonParseException
	 * @see com.google.gson.JsonDeserializer#deserialize(JsonElement, Type, JsonDeserializationContext) */
	@Override
	public Deck deserialize(JsonElement dElement, Type dType,
			JsonDeserializationContext context) throws JsonParseException {
		JsonObject deflated = dElement.getAsJsonObject();

		if (!deflated.has("deckName")) {
			throw new JsonParseException(
					"The serialized deck did not contain the required deckName field.");
		}

		String deckName = deflated.get("deckName").getAsString();
		List<Integer> cards = null;

		JsonArray jsonCards = new JsonArray();

		if (deflated.has("cards")) {
			try {
				jsonCards = deflated.get("cards").getAsJsonArray();
			} catch (java.lang.ClassCastException e) {
				logger.log(Level.FINER,
						"DeckModel transmitted with non-array in cards field");
			}

			if (jsonCards != null) {
				for (JsonElement jsonCard : jsonCards) {
					cards.add(jsonCard.getAsInt());
				}
			}
		}

		Deck inflated = new Deck(deckName, cards);

		return inflated;
	}
}
