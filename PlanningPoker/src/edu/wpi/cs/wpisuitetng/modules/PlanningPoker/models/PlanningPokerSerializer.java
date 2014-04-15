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

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/**
 * @author Miguel
 * @version $Revision: 1.0 $
 */
public class PlanningPokerSerializer implements JsonSerializer<PlanningPokerGame> {

	/**
	 * Method serialize.
	 * @param m PlanningPokerGame
	 * @param t Type
	 * @param context JsonSerializationContext
	
	 * @return JsonElement */
	@Override
	public JsonElement serialize(PlanningPokerGame m, Type t, JsonSerializationContext context) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
		JsonObject deflated = new JsonObject();
		JsonArray deflatedReqs = new JsonArray();
		
		deflated.addProperty("gameName", m.getID());
		deflated.addProperty("moderator", m.getModerator());
		deflated.addProperty("description", m.getDescription());
		deflated.addProperty("deckType", m.getDeckType());
		for(Integer id : m.getRequirementIds()) {
			deflatedReqs.add(new JsonPrimitive(id));
		}
		deflated.add("requirements", deflatedReqs);
		deflated.addProperty("isFinished", m.isFinished());
		deflated.addProperty("isLive", m.isLive());
		deflated.addProperty("startDate", df.format(m.getStartDate().getTime()));
		deflated.addProperty("endDate", df.format(m.getEndDate().getTime()));
		return deflated;
	}

}
