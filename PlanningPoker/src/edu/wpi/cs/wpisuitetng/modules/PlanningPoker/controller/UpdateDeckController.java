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
package edu.wpi.cs.wpisuitetng.modules.PlanningPoker.controller;

import edu.wpi.cs.wpisuitetng.modules.PlanningPoker.deck.Deck;
import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.Request;
import edu.wpi.cs.wpisuitetng.network.models.HttpMethod;

/**
 * This controller responds when the user clicks the Update button by adding the
 * contents of the Deck class to the model Deck
 * 
 * @author Alec Thompson - ajthompson
 * @version Apr 10, 2014
 */
public class UpdateDeckController {
	/** The singleton instance of the UpdateDeckController */
	private static UpdateDeckController instance;
	/** THe observer tied to the instance of the UpdateDeckController */
	private UpdateDeckRequestObserver observer;

	/**
	 * Returns the singleton instance of the UpdateDeckController, or creates it
	 * if one does not yet exist.
	 * 
	
	 * @return the singleton instance of the UpdateDeckController */
	public static UpdateDeckController getInstance() {
		if (instance == null)
			instance = new UpdateDeckController();
		return instance;
	}

	/**
	 * Construct an UpdateDeckController for the given model
	 */
	private UpdateDeckController() {
		observer = new UpdateDeckRequestObserver(this);
	}
	
	/**
	 * Method updateDeck.
	 * @param newDeck Deck
	 */
	public void updateDeck(Deck newDeck) {
		Request request = Network.getInstance().makeRequest("planningpoker/deck", HttpMethod.POST);
		request.setBody(newDeck.toJSON()); // put the new Deck into the request
		request.addObserver(observer); // add an observer to process the response
		request.send();
	}
}
