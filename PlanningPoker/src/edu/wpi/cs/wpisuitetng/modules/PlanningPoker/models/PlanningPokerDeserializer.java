/*******************************************************************************
* Copyright (c) 2012-2014 -- WPI Suite
*
* All rights reserved. This program and the accompanying materials
* are made available under the terms of the Eclipse Public License v1.0
* which accompanies this distribution, and is available at
* http://www.eclipse.org/legal/epl-v10.html
* Contributor: team struct-by-lightning
*******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.PlanningPoker.models;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import edu.wpi.cs.wpisuitetng.modules.core.models.UserDeserializer;

/**
 * @author Miguel
 * @version $Revision: 1.0 $
 */
public class PlanningPokerDeserializer implements JsonDeserializer<PlanningPokerGame> {

	private static final Logger logger = Logger
			.getLogger(UserDeserializer.class.getName());

	/**
	 * Method deserialize.
	 * @param ppmElement JsonElement
	 * @param ppmType Type
	 * @param context JsonDeserializationContext
	
	
	
	 * @return PlanningPokerGame * @throws JsonParseException * @see com.google.gson.JsonDeserializer#deserialize(JsonElement, Type, JsonDeserializationContext) */
	@Override
	public PlanningPokerGame deserialize(JsonElement ppmElement, Type ppmType,
			JsonDeserializationContext context) throws JsonParseException {
		JsonObject deflated = ppmElement.getAsJsonObject();

		if (!deflated.has("gameName")) {
			throw new JsonParseException(
					"The serialized PlanningPokerModel did not contain the required gameName field.");
		}

		if (!deflated.has("requirements")) {
			throw new JsonParseException(
					"The serialized PlanningPokerModel did not contain the required requirements field.");
		}
		
		if (!deflated.has("isFinished")) {
			throw new JsonParseException(
					"The serialized PlanningPokerModel did not contain the required isFinished field.");
		}
		
		if (!deflated.has("isLive")) {
			throw new JsonParseException(
					"The serialized PlanningPokerModel did not contain the required isLive field.");
		}
		
		if (!deflated.has("startDate")) {
			throw new JsonParseException(
					"The serialized PlanningPokerModel did not contain the required startDate field.");
		}
		
		if (!deflated.has("endDate")) {
			throw new JsonParseException(
					"The serialized PlanningPokerModel did not contain the required endData field.");
		}
		
		if (!deflated.has("moderator")) {
			throw new JsonParseException(
					"The serialized PlanningPokerModel did not contain the required endData field.");
		}
		// for all other attributes: instantiate as null, fill in if given.

		String gameName = deflated.get("gameName").getAsString();
		String description = null;
		String deckType = null;
		List<Integer> requirements = new ArrayList<Integer>();
		boolean isFinished = false;
		boolean isLive = false;
		GregorianCalendar startDate = null;
		GregorianCalendar endDate = null;
		String moderator = null;

		if (deflated.has("deckType")
				&& !deflated.get("deckType").getAsString().equals("")) {
			deckType = deflated.get("deckType").getAsString();
		}
		
		JsonArray jsonRequirements = null;
		
		try {
			jsonRequirements = deflated.get("requirements").getAsJsonArray();
		} catch (java.lang.ClassCastException e) {
			logger.log(Level.FINER,
					"PlanningPokerModel transmitted with non-array in requirements field");
		}

		for(JsonElement jsonRequirement : jsonRequirements) {
			requirements.add(jsonRequirement.getAsInt());
		}
		
		try {
			isFinished = deflated.get("isFinished").getAsBoolean();
		} catch (java.lang.ClassCastException e) {
			logger.log(Level.FINER,
					"PlanningPokerModel transmitted with String in isFinished field");
		}
		
		try {
			isLive = deflated.get("isLive").getAsBoolean();
		} catch (java.lang.ClassCastException e) {
			logger.log(Level.FINER,
					"PlanningPokerModel transmitted with String in isLive field");
		}

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");

		try {
			Date date = df.parse(deflated.get("startDate").getAsString());
			startDate = new GregorianCalendar();
			startDate.setTime(date);
		} catch (java.text.ParseException e) {
			logger.log(Level.FINER,
					"PlanningPokerModel transmitted with String in startDate field");
			startDate = null;
		}
		
		try {
			Date date = df.parse(deflated.get("endDate").getAsString());
			endDate = new GregorianCalendar();
			endDate.setTime(date);
		} catch (java.text.ParseException e) {
			logger.log(Level.FINER,
					"PlanningPokerModel transmitted with String in endDate field");
			endDate = null;
		}
		
		moderator = deflated.get("moderator").getAsString();
		
		
		PlanningPokerGame inflated = new PlanningPokerGame(gameName, description,
				deckType, requirements, isFinished, isLive, startDate, endDate, moderator);

		return inflated;
	}

}
