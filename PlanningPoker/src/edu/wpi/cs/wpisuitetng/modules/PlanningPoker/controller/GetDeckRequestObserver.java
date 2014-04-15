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

/**
 * This observer handles responses to requests to retrieve Decks
 * 
 * @author Alec Thompson - ajthompson
 * @version Apr 10, 2014
 **/
public class GetDeckRequestObserver implements RequestObserver {

	/**
	 * The controller linked to this RequestObserver *
	 **/
	private GetDeckController controller;

	/**
	 * Constructs the observer given the instance of the GetDeckController
	 * 
	 * @param controller
	 *            the controller used to retrieve Decks
	 **/
	public GetDeckRequestObserver(GetDeckController controller) {
		this.controller = controller;
	}

	/**
	 * Parse the Decks out of the response body and pass the reconstructed Decks
	 * to the controller
	 * 
	 * @see edu.wpi.cs.wpisuitetng.network.RequestObserver#responseSuccess(IRequest)
	 **/
	@Override
	public void responseSuccess(IRequest iReq) {
		// convert the JASON array of Decks to a Deck array
		Deck[] decks = Deck.fromJsonArray(iReq.getResponse().getBody());

		// Pass these Decks to the controller
		controller.receivedDecks(decks);
	}

	/**
	 * Prints a message that the retrieval encountered an error if an error
	 * message is retrieved.
	 * 
	 * @param iReq
	 *            the Request that was made to the server
	 * 
	 * 
	 * @see edu.wpi.cs.wpisuitetng.network.RequestObserver#responseError(edu.wpi.cs.wpisuitetng.network.models.IRequest)
	 **/
	@Override
	public void responseError(IRequest iReq) {
		System.err
				.println("The retrieval has encountered an error and had to close.");
	}

	/**
	 * Prints a message that the retrieval fails if a failure message is
	 * retrieved.
	 * 
	 * @param iReq
	 *            the Request that was made to the server
	 * @param exception
	 *            the Exception received by the observer
	 * 
	 * 
	 * @see edu.wpi.cs.wpisuitetng.network.RequestObserver#fail(edu.wpi.cs.wpisuitetng.network.models.IRequest,
	 *      java.lang.Exception)
	 **/
	@Override
	public void fail(IRequest iReq, Exception exception) {
		System.err.println("The attempt to retrieve a Deck has failed.");
	}
}
