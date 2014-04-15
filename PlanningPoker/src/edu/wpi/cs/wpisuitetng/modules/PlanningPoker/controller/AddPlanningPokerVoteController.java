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

import edu.wpi.cs.wpisuitetng.modules.PlanningPoker.models.PlanningPokerVote;
import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.Request;
import edu.wpi.cs.wpisuitetng.network.models.HttpMethod;

/**
 * This controller responds by adding a new planning poker vote.
 * 
 * @version $Revision: 1.0 $
 * @author justinhess
 */
public class AddPlanningPokerVoteController {

	private static AddPlanningPokerVoteController instance;
	private AddPlanningPokerVoteRequestObserver observer;

	/**
	 * Construct an AddPlanningPokerVoteController for the given model, view
	 * pair
	 */
	private AddPlanningPokerVoteController() {
		observer = new AddPlanningPokerVoteRequestObserver(this);
	}

	/**
	 * 
	 * @return the instance of the AddPlanningPokerVoteController or creates one
	 *         if it does not exist.
	 */
	public static AddPlanningPokerVoteController getInstance() {
		if (instance == null) {
			instance = new AddPlanningPokerVoteController();
		}

		return instance;
	}

	/**
	 * This method adds a PlanningPokerVote to the server.
	 * 
	 * @param newPlanningPokerVote
	 *            is the PlanningPokerVote to be added to the server.
	 */
	public void addPlanningPokerVote(PlanningPokerVote newPlanningPokerVote) {
		final Request request = Network.getInstance().makeRequest(
				"planningpoker/planningpokervote", HttpMethod.POST); // PUT ==
																		// create
		request.setBody(newPlanningPokerVote.toJSON()); // put the new
														// PlanningPokerVote in
														// the body of the
														// request
		request.addObserver(observer); // add an observer to process the
										// response
		request.send();
	}
}
