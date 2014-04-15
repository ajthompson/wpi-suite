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
public class UpdateDeckRequestObserver implements RequestObserver {

	/** The controller this request observer was created with */
	private final UpdateDeckController controller;

	/**
	 * Constructs the observer given an UpdateDeckController
	 * 
	 * @param controller
	 *            the controller used to update Decks
	 */
	public UpdateDeckRequestObserver(UpdateDeckController controller) {
		this.controller = controller;
	}

	/**
	 * Parse the Deck that was received from the server and then pass it to the
	 * controller
	 * 
	 * @param iReq
	 *            the request that was made to the server
	 * 
	
	 * @see edu.wpi.cs.wpisuitetng.network.RequestObserver#responseSuccess(edu.wpi
	 *      .cs.wpisuitetng.network.models.IRequest) */
	@Override
	public void responseSuccess(IRequest iReq) {
		// Get the response to the given request
		final ResponseModel response = iReq.getResponse();

		// Parse the Deck out of the response body
		final Deck deck = Deck.fromJSON(response.getBody());
	}

	/**
	 * Prints out an error message if the response results in an error
	 * 
	 * @param iReq
	 *            the request sent to the server
	 * 
	
	 * @see edu.wpi.cs.wpisuitetng.network.RequestObserver#responseError(edu.wpi.
	 *      cs.wpisuitetng.network.models.IRequest) */
	@Override
	public void responseError(IRequest iReq) {
		System.err.println(iReq.getResponse().getStatusMessage());
		System.err
				.println("The request to update a Deck has encountered an error and had to quit.");
	}

	/**
	 * Prints out a failure message if the response fails
	 * 
	 * @param iReq
	 *            the request sent to the server
	 * @param exception
	 *            the Exception that was thrown
	 * 
	
	 * @see edu.wpi.cs.wpisuitetng.network.RequestObserver#fail(edu.wpi.cs.wpisuitetng
	 *      .network.models.IRequest, java.lang.Exception) */
	@Override
	public void fail(IRequest iReq, Exception exception) {
		System.err.println("The request to update a Deck failed.");
	}

}
