/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Team Rolling Thunder
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.PlanningPoker.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import edu.wpi.cs.wpisuitetng.modules.PlanningPoker.models.PlanningPokerGame;
import edu.wpi.cs.wpisuitetng.modules.PlanningPoker.models.PlanningPokerGameModel;
import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.Request;
import edu.wpi.cs.wpisuitetng.network.models.HttpMethod;

/**
 * This controller coordinates retrieving all of the planning poker games from
 * the server.
 * 
 * @version $Revision: 1.0 $
 * @author justinhess
 **/
public class GetPlanningPokerGamesController implements ActionListener {

	private GetPlanningPokerGamesRequestObserver observer;
	private static GetPlanningPokerGamesController instance;

	/**
	 * Constructs the controller given a PlanningPokerGameModel
	 **/
	private GetPlanningPokerGamesController() {

		observer = new GetPlanningPokerGamesRequestObserver(this);
	}

	/**
	 * gets the planning poker game control
	 * 
	 * @return the instance of the GetPlanningPokerGameController or creates one
	 *         if it does not exist.
	 **/
	public static GetPlanningPokerGamesController getInstance() {
		if (instance == null) {
			instance = new GetPlanningPokerGamesController();
		}

		return instance;
	}

	/**
	 * Sends an HTTP request to store a PlanningPokerGame when the update button
	 * is pressed
	 * 
	 * @param e
	 *            ActionEvent
	 * @see java.awt.event.ActionListener#actionPerformed(ActionEvent)
	 **/
	@Override
	public void actionPerformed(ActionEvent e) {
		// Send a request to the core to save this PlanningPokerGame
		final Request request = Network.getInstance().makeRequest(
				"planningpoker/planningpokergame", HttpMethod.GET); // GET ==
																	// read
		request.addObserver(observer); // add an observer to process the
										// response
		request.send(); // send the request
	}

	/**
	 * Sends an HTTP request to retrieve all PlanningPokerGames
	 **/
	public void retrievePlanningPokerGames() {
		final Request request = Network.getInstance().makeRequest(
				"planningpoker/planningpokergame", HttpMethod.GET); // GET ==
																	// read
		request.addObserver(observer); // add an observer to process the
										// response
		request.send(); // send the request
	}

	/**
	 * Add the given PlanningPokerGames to the local model (they were received
	 * from the core). This method is called by the
	 * GetPlanningPokerGamesRequestObserver
	 * 
	 * @param PlanningPokerGames
	 *            array of PlanningPokerGames received from the server
	 **/
	public void receivedPlanningPokerGames(
			PlanningPokerGame[] PlanningPokerGames) {
		/**
		 * Empty the local model to eliminate duplications
		 **/
		PlanningPokerGameModel.emptyModel();

		/**
		 * Make sure the response was not null
		 **/
		if (PlanningPokerGames != null) {

			/**
			 * add the PlanningPokerGames to the local model
			 **/
			PlanningPokerGameModel.addPlanningPokerGames(PlanningPokerGames);
		}
	}
}
