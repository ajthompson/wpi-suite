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

import edu.wpi.cs.wpisuitetng.modules.PlanningPoker.models.PlanningPokerGame;
import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.Request;
import edu.wpi.cs.wpisuitetng.network.models.HttpMethod;

/**
 * This controller responds by adding a new planning poker game.
 * 
 * @version $Revision: 1.0 $
 * @author justinhess
 */
public class AddPlanningPokerGameController {

	private static AddPlanningPokerGameController instance;
	private AddPlanningPokerGameRequestObserver observer;

	/**
	 * Construct an AddPlanningPokerGameController for the given model, view
	 * pair
	 */
	private AddPlanningPokerGameController() {
		observer = new AddPlanningPokerGameRequestObserver(this);
	}

	/**
	 * 
	 * @return the instance of the AddPlanningPokerGameController or creates one
	 *         if it does not exist.
	 */
	public static AddPlanningPokerGameController getInstance() {
		if (instance == null) {
			instance = new AddPlanningPokerGameController();
		}

		return instance;
	}

	/**
	 * This method adds a PlanningPokerGame to the server.
	 * 
	 * @param newPlanningPokerGame
	 *            is the PlanningPokerGame to be added to the server.
	 */
	public void addPlanningPokerGame(PlanningPokerGame newPlanningPokerGame) {
		final Request request = Network.getInstance().makeRequest(
				"planningpoker/planningpokergame", HttpMethod.PUT); // PUT ==
																	// create
		request.setBody(newPlanningPokerGame.toJSON()); // put the new
														// PlanningPokerGame in
														// the body of the
														// request
		request.addObserver(observer); // add an observer to process the
										// response
		request.send();
	}
}
