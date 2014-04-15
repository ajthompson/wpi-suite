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
import edu.wpi.cs.wpisuitetng.network.RequestObserver;
import edu.wpi.cs.wpisuitetng.network.models.IRequest;
import edu.wpi.cs.wpisuitetng.network.models.ResponseModel;

/**
 * This observer is called when a response is received from a request to the
 * server to add a Deck
 * 
 * @author Alec Thompson - ajthompson
 * @version Apr 10, 2014
 */
public class AddDeckRequestObserver implements RequestObserver {

	/** The controller the request observer is associated with */
	private AddDeckController controller;

	/**
	 * Constructs the observer given the AddDeckController
	 * 
	 * @param controller
	 *            the controller used to add Decks
	 */
	public AddDeckRequestObserver(AddDeckController controller) {
		this.controller = controller;
	}

	/**
	 * Parse the Deck that was received from the server and pass it to the
	 * controller
	 * 
	 * @param iReq
	 *            the request that was made to the controller
	 * 
	 * 
	 * @see edu.wpi.cs.wpisuitetng.network.RequestObserver#responseSuccess(edu.wpi
	 *      .cs.wpisuitetng.network.models.IRequest)
	 */
	@Override
	public void responseSuccess(IRequest iReq) {
		// Get the response to the given request
		final ResponseModel response = iReq.getResponse();

		// Parse the Deck out of the response body
		final Deck deck = Deck.fromJSON(response.getBody());
	}

	/**
	 * Prints out that an error has occurred if an error occurs with the
	 * request.
	 * 
	 * @param iReq
	 *            the request that was made
	 * 
	 * 
	 * @see edu.wpi.cs.wpisuitetng.network.RequestObserver#responseError(edu.wpi.
	 *      cs.wpisuitetng.network.models.IRequest)
	 */
	@Override
	public void responseError(IRequest iReq) {
		System.err
				.println("An error has occurred and AddDeck has had to quit.");

	}

	/**
	 * Prints that the request has failed if it fails.
	 * 
	 * @param iReq
	 *            the request that was made
	 * @param exception
	 *            the exception that occurred
	 * 
	 * 
	 * @see edu.wpi.cs.wpisuitetng.network.RequestObserver#fail(edu.wpi.cs.wpisuitetng
	 *      .network.models.IRequest, java.lang.Exception)
	 */
	@Override
	public void fail(IRequest iReq, Exception exception) {
		System.err.println("The request to add a Deck failed.");
	}

}
