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
import java.util.List;
import java.util.Scanner;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import edu.wpi.cs.wpisuitetng.modules.RegularAbstractModel;

/**
 * @author rbkillea
 * @author cgwalker
 * @author lhnguyenduc
 * @author bbiletch
 * @version $Revision: 1.0 $
 */

public class PlanningPokerVote extends RegularAbstractModel<PlanningPokerVote>{
	String gameName;
	String userName;
	int vote;
	/**
	 * Method getVote.
	
	 * @return int */
	public int getVote() {
		return vote;
	}
	/**
	 * Method setVote.
	 * @param vote int
	 */
	public void setVote(int vote) {
		this.vote = vote;
	}

	int requirementID;
	
	/**
	 * Constructor for PlanningPokerVote.
	 * @param gameName String
	 * @param userName String
	 * @param vote int
	 * @param requirementID int
	 */
	public PlanningPokerVote(String gameName, String userName, int vote, int requirementID) {
		if(gameName != null){
			this.gameName = gameName.replace(':', ';');
		}
		
		else{
			this.gameName = null;
		}
		if(userName != null){
			this.userName = userName.toLowerCase();
		}
		else{
			this.userName = null;
		}
		this.vote = vote;
		this.requirementID = requirementID;
	}
	/**
	 * in ppvotemodel, this does not extend easily to the standard one given to us by a gson object
	
	
	 * @return the JSON string with id being the primary key concat of gamename and username as well as vote * @see edu.wpi.cs.wpisuitetng.modules.Model#toJSON() * @see edu.wpi.cs.wpisuitetng.modules.Model#toJSON()
	 */
	@Override
	public String toJSON() {
		// TODO Auto-generated method stub
		return "{\"id\":\"" + gameName + ":" + userName + ":" + requirementID + "\",\"vote\":\"" + vote + "\"}";
	}
	/**
	 * gives the cannonical styling (as would appear in the JSON) of the primary key
	
	 * @return the string gameName:userName */
	@Override
	public String getID() {
		// TODO Auto-generated method stub
		return gameName + ":" + userName + ":" + requirementID;
	}
	
	/**
	 * Method setID.
	 * @param toSet String
	 */
	@Override
	public void setID(String toSet) {
		Scanner scTemp = new Scanner(toSet);
		scTemp.useDelimiter("\\s*:\\s*");
		gameName = scTemp.next();
		userName = scTemp.next();
		requirementID = scTemp.nextInt();
	}
	
	/**
	 * Method getPrimaryKey.
	
	 * @return String */
	@Override
	public String getPrimaryKey() {
		return "id";
	}
	
	/**
	 * Method getUserName.
	
	 * @return String */
	public String getUserName() {
		return userName;
	}
	
	/**
	 * Method setUserName.
	 * @param toSet String
	 */
	public void setUserName(String toSet) {
		userName = toSet.toLowerCase();
	}
	/**
	 * This method makes a JSON string into a votemodel.
	 * It is currently fairly brittle, but should work if the standards for usernames and gamenames don't change.
	 * @param json assumes a JSON string generated from a model of this type
	
	 * @return the object form of the JSON */
	public static PlanningPokerVote fromJSON(String json) {
		Scanner scTemp = new Scanner(json);
		System.out.println(json);
		// skip the boilerplate
		scTemp.useDelimiter("\\\"?[:,{}]\\\"?");
		scTemp.next();
		// get the userName
		String retUserName = scTemp.next();
		// get the gameName
		String retGameName = scTemp.next();
		// check if the gameName is null
		if(retGameName.equals("null"))
			retGameName = null;
		
		// get the requirement ID
		Integer retRequirementID = Integer.parseInt(scTemp.next());
		// check if the userName is null
		if(retUserName.equals("null"))
			retUserName = null;
		scTemp.next();
		// get and format the vote
		int retVote = Integer.parseInt(scTemp.next());
		
		return new PlanningPokerVote(retGameName, retUserName, retVote, retRequirementID);
	}
	
	/**
	 * Reconstruct an array of PlanningPokerVotes from a JSON array
	 * 
	 * @param jsonArr
	 *            The JSON array to use
	
	 * @return An array of reconstructed PlanningPokerGames */
	public static PlanningPokerVote[] fromJsonArray(String jsonArr) {
		PlanningPokerVoteDeserializer ppd = new PlanningPokerVoteDeserializer();
		JsonArray array = new JsonParser().parse(jsonArr).getAsJsonArray();
		List<PlanningPokerVote> ppvs = new ArrayList<PlanningPokerVote>();

		for (JsonElement json : array) {
			ppvs.add(ppd.deserialize(json, null, null));
		}

		return ppvs.toArray(new PlanningPokerVote[0]);
	}
}
