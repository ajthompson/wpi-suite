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
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import edu.wpi.cs.wpisuitetng.modules.core.models.UserDeserializer;

/**
 * Deserialize the JSON string of Planning Poker Vote
 * @author hlong290494
 * @author cgwalker
 * @version $Revision: 1.0 $
 */
public class PlanningPokerVoteDeserializer {

	private static final Logger logger = Logger.getLogger(UserDeserializer.class.getName());

	/**
	 * Method deserialize.
	 * @param ppmElement JsonElement
	 * @param ppmType Type
	 * @param context JsonDeserializationContext
	
	
	 * @return PlanningPokerVote * @throws JsonParseException */
	public PlanningPokerVote deserialize(JsonElement ppmElement, Type ppmType,
			JsonDeserializationContext context) throws JsonParseException {
		
		JsonObject deflated = ppmElement.getAsJsonObject();

		if (!deflated.has("id")) {
			throw new JsonParseException(
					"The serialized PlanningPokerVoteModel did not contain the required id field.");
		}

		if (!deflated.has("vote")) {
			throw new JsonParseException(
					"The serialized PlanningPokerVoteModel did not contain the required vote field.");
		}
		// for all other attributes: instantiate as null, fill in if given.

		String key = deflated.get("id").getAsString();
	
		Scanner scTemp = new Scanner(key);
		
		// skip the boilerplate
		scTemp.useDelimiter("\\\"?[:,{}]\\\"?");
		String userName = scTemp.next();
		// get the gameName
		String gameName = scTemp.next();
		// get the requirement ID
		Integer id = Integer.parseInt(scTemp.next());
		
		// get the vote
		int vote = deflated.get("vote").getAsInt();

		PlanningPokerVote inflated = new PlanningPokerVote(gameName, userName, vote, id);
		
		System.out.println("DESERIALIZED: User " + userName + " voted " + vote + " for requirement" + id + " in game " + gameName);
		
		// Prevent resource leak
		scTemp.close();

		return inflated;
	}

}
