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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import edu.wpi.cs.wpisuitetng.modules.PlanningPoker.deck.Deck;
import edu.wpi.cs.wpisuitetng.modules.PlanningPoker.models.DeckModel;
import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.Request;
import edu.wpi.cs.wpisuitetng.network.models.HttpMethod;

/**
 * The controller coordinates retrieving all of the decks from the server.
 * 
 * @author Alec Thompson - ajthompson
 * @version Apr 10, 2014
 */
public class GetDeckController implements ActionListener {
	/** The singleton instance of the GetDeckController */
	private static GetDeckController instance;
	/** the observer tied to this controller */
	private GetDeckRequestObserver observer;

	/**
	 * Retrieves the singleton instance of the DeckController, or creates it if
	 * it does not yet exist.
	 * 
	 * 
	 * @return the singleton instance of the DeckController
	 */
	public static GetDeckController getInstance() {
		if (instance == null)
			instance = new GetDeckController();
		return instance;
	}

	/**
	 * Constructs the controller
	 */
	private GetDeckController() {
		observer = new GetDeckRequestObserver(this);
	}

	/**
	 * Sends an HTTP request to get a Deck when the update button is pressed
	 * 
	 * @param e
	 *            the ActionEvent that occurs
	 * 
	 * 
	 * @see java.awt.event.ActionListener#actionPerformed(ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// send a request to the core to save this Deck
		final Request request = Network.getInstance().makeRequest(
				"planningpoker/deck", HttpMethod.GET);
		request.addObserver(observer);
		request.send(); // send the request
	}

	/**
	 * Sends an HTTP request to retrieve all Decks
	 */
	public void retrieveDeck() {
		final Request request = Network.getInstance().makeRequest(
				"planningpoker/deck", HttpMethod.GET);
		request.addObserver(observer);
		request.send();
	}

	/**
	 * Adds the given Decks to the local model after they are received from the
	 * core. This model is called by the GetDeckRequestObserver
	 * 
	 * @param decks
	 *            array of Decks received from the server
	 */
	public void receivedDecks(Deck[] decks) {
		// empty the local model to eliminate duplications
		DeckModel.getInstance().emptyModel();

		// make sure the response was not null
		if (decks != null)
			DeckModel.getInstance().addDeck(decks);
	}
}
