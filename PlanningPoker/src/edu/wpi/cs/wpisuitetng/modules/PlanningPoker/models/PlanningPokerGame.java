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

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import edu.wpi.cs.wpisuitetng.modules.RegularAbstractModel;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.controller.GetRequirementsController;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.Requirement;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.RequirementModel;

/**
 * A model to store the information for a planning poker game
 * @author Miguel
 * @version $Revision: 1.0 $
 */
public class PlanningPokerGame extends RegularAbstractModel<PlanningPokerGame> {
	/** Name for the game (primary key) */
	private String gameName;

	/** Description for the game */
	private String description;

	/** Selected deck */
	private String deckType;

	/** Requirement IDs associated with this game */
	private List<Integer> requirementIds;

	/** Whether the game is finished */
	private boolean isFinished;

	/** Whether the game is currently active */
	private boolean isLive;

	/** The date the game was started */
	private GregorianCalendar startDate;

	/** The date the game ended or is scheduled to end */
	private GregorianCalendar endDate;
	
	/**The moderator of the planning poker game*/
	private String moderator;

	/**
	 * Constructor for the game
	 * 
	 * @param gameName
	 *            Name of the game
	 * @param description
	 *            Description for the game
	 * @param deckType
	 *            Selected deck
	 * @param requirementsIds
	 *            Requirement IDs associated with this game
	 * @param isFinished
	 *            Whether the game is finished
	 * @param isLive
	 *            Whether the game is currently active
	 * @param startDate
	 *            The date the game was started
	 * @param endDate
	 *            The date the game was started
	 * @param moderator String
	 */
	public PlanningPokerGame(String gameName, String description,
			String deckType, List<Integer> requirementsIds,
			boolean isFinished, boolean isLive, GregorianCalendar startDate,
			GregorianCalendar endDate, String moderator) {
		super();

		this.requirementIds = new ArrayList<Integer>();
		if(gameName != null)
			this.gameName = gameName.replace(';', ':');
		this.setDescription(description);
		this.setDeckType(deckType);
		if (requirementsIds != null) {
			for (Integer r : requirementsIds) {
				this.addRequirementId(r);
			}
		}
		this.setFinished(isFinished);
		this.setLive(isLive);
		this.setStartDate(startDate);
		this.setEndDate(endDate);
		this.setModerator(moderator);
	}
	
	/**
	
	 * @return A list of the requirements which are associated with this planning poker game. */
	public ArrayList<Requirement> getRequirements() {
		

		
		ArrayList<Requirement> toReturn = new ArrayList<Requirement>();

		for(int id : this.getRequirementIds()) {
			toReturn.add(RequirementModel.getInstance().getRequirement(id));
		}
		
		return toReturn;
	}
	
	/**
	 * Method getDeckValues.
	
	 * @return ArrayList<Integer> */
	public ArrayList<Integer> getDeckValues() {
		// TODO This method returns mock data, and needs to be correctly implemented.
		
        return new ArrayList<Integer>() {
            {
                add(1);
                add(1);
                add(2);
                add(3);
                add(5);
                add(8);
            }
        };
	}
	

	/**
	 * Method hasEndDate.
	
	 * @return boolean */
	public boolean hasEndDate() {
		// TODO This method returns mock data, and needs to be correctly implemented.
		if(endDate.get(GregorianCalendar.YEAR) == 9999)
			return false;
		return true;
	}
	

	/**
	 * Method getSelectedCardIndices.
	 * @param user Object
	 * @param selectedRequirement Requirement
	
	 * @return ArrayList<Integer> */
	public ArrayList<Integer> getSelectedCardIndices(Object user, Requirement selectedRequirement) {
		// TODO This method returns mock data, and needs to be correctly implemented.
		
		return new ArrayList<Integer>();
	}

	/**
	 * @see edu.wpi.cs.wpisuitetng.modules.Model#toJSON()
	 */
	@Override
	public String toJSON() {
		PlanningPokerSerializer pps = new PlanningPokerSerializer();
		return pps.serialize(this, null, null).toString();
	}

	/**
	 * Reconstruct a PlanningPokerGame from its JSON representation
	 * 
	 * @param json
	 *            The JSON to use
	
	 * @return The reconstructed PlanningPokerGame */
	public static PlanningPokerGame fromJSON(String json) {
		PlanningPokerDeserializer ppd = new PlanningPokerDeserializer();
		return ppd.deserialize(new JsonParser().parse(json), null, null);
	}

	/**
	 * Reconstruct an array of PlanningPokerGames from a JSON array
	 * 
	 * @param jsonArr
	 *            The JSON array to use
	
	 * @return An array of reconstructed PlanningPokerGames */
	public static PlanningPokerGame[] fromJsonArray(String jsonArr) {
		PlanningPokerDeserializer ppd = new PlanningPokerDeserializer();
		JsonArray array = new JsonParser().parse(jsonArr).getAsJsonArray();
		List<PlanningPokerGame> ppgs = new ArrayList<PlanningPokerGame>();

		for (JsonElement json : array) {
			ppgs.add(ppd.deserialize(json, null, null));
		}

		return ppgs.toArray(new PlanningPokerGame[0]);
	}

	/**
	 * @see edu.wpi.cs.wpisuitetng.modules.RegularAbstractModel#getID()
	 */
	@Override
	public String getID() {
		return this.gameName;
	}

	/**
	 * @see edu.wpi.cs.wpisuitetng.modules.RegularAbstractModel#setID(java.lang.String)
	 */
	@Override
	public void setID(String toSet) {
		gameName = toSet;
	}
	
	/**
	 * Method getModerator.
	
	 * @return String */
	public String getModerator() {
		return this.moderator;
	}

	/**
	 * Get the game's name
	 * 
	
	 * @return The game's name */
	public String getGameName() {
		return this.gameName;
	}

	/**
	 * Set the game's name
	 * 
	 * @param toSet
	 *            The new name for the game
	 */
	public void setGameName(String toSet) {
		gameName = toSet;
	}

	/**
	 * Get the game's description
	 * 
	
	 * @return The game's description */
	public String getDescription() {
		return description;
	}

	/**
	 * Set the game's description
	 * 
	 * @param description
	 *            The new description for the game
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @see edu.wpi.cs.wpisuitetng.modules.RegularAbstractModel#getPrimaryKey()
	 */
	@Override
	public String getPrimaryKey() {
		return "gameName";
	}

	/**
	 * Get the selected deck for the game
	 * 
	
	 * @return The selected deck */
	public String getDeckType() {
		return deckType;
	}

	/**
	 * Select a new deck for the game
	 * 
	 * @param deckType
	 *            The new deck to select
	 */
	public void setDeckType(String deckType) {
		this.deckType = deckType;
	}

	/**
	 * Get the game's associated requirement IDs
	 * 
	
	 * @return A list of the game's requirement IDs */
	public List<Integer> getRequirementIds() {
		return this.requirementIds;
	}

	/**
	 * Add a requirement to the game
	 * 
	 * @param requirementID
	 *            The requirement to add
	 */
	public void addRequirementId(Integer requirementID) {
		this.requirementIds.add(requirementID);
	}

	/**
	 * Remove a requirement from the game
	 * 
	 * @param requirementID
	 *            The requirement to remove
	
	 * @return {@code true} if the game had the specified requirement */
	public boolean removeRequirement(Integer requirementID) {
		return this.requirementIds.remove(requirementID);
	}

	/**
	 * Get whether the game is finished
	 * 
	
	 * @return {@code true} if the game is finished */
	public boolean isFinished() {
		return isFinished;
	}

	/**
	 * Set whether the game is finished
	 * 
	 * @param isFinished
	 *            {@code true} if the game should be finished
	 */
	public void setFinished(boolean isFinished) {
		this.isFinished = isFinished;
	}

	/**
	 * Get whether the game is live
	 * 
	
	 * @return {@code true} if the game is live */
	public boolean isLive() {
		return isLive;
	}
	
	/**
	 * Method setModerator.
	 * @param moderator String
	 */
	public void setModerator(String moderator) {
		this.moderator = moderator;
	}

	/**
	 * Set whether the game is live
	 * 
	 * @param isLive
	 *            {@code true} if the game should be live
	 */
	public void setLive(boolean isLive) {
		this.isLive = isLive;
	}

	/**
	 * Get the start date of the game
	 * 
	
	 * @return The start date of the game */
	public GregorianCalendar getStartDate() {
		return startDate;
	}

	/**
	 * Set the start date of the game
	 * 
	 * @param startDate
	 *            The new start date for the game
	 */
	public void setStartDate(GregorianCalendar startDate) {
		this.startDate = startDate;
	}

	/**
	 * Get the end date of the game
	 * 
	
	 * @return The end date of the game */
	public GregorianCalendar getEndDate() {
		return endDate;
	}

	/**
	 * Set the end date of the game
	 * 
	 * @param endDate
	 *            The new end date for the game
	 */
	public void setEndDate(GregorianCalendar endDate) {
		this.endDate = endDate;
	}




}
