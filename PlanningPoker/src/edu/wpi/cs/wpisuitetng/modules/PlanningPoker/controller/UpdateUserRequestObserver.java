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

import edu.wpi.cs.wpisuitetng.modules.core.models.User;
import edu.wpi.cs.wpisuitetng.network.RequestObserver;
import edu.wpi.cs.wpisuitetng.network.models.IRequest;
import edu.wpi.cs.wpisuitetng.network.models.ResponseModel;

/**
 * Description
 * 
 * @author Alec Thompson - ajthompson
 * @version Apr 8, 2014
 */
public class UpdateUserRequestObserver implements RequestObserver {

	private final UpdateUserController controller;

	/**
	 * Contsructs the observer given an UpdateUserController
	 * 
	 * @param controller
	 *            the controller used to update Users
	 */
	public UpdateUserRequestObserver(UpdateUserController controller) {
		this.controller = controller;
	}

	/**
	 * Parse the User that was received from the server then pass it to the
	 * controller.
	 * 
	 * @param iReq
	 *            the request that was sent.
	 * 
	
	 * @see edu.wpi.cs.wpisuitetng.network.RequestObserver#responseSuccess(edu.wpi.cs.wpisuitetng.network.models.IRequest) */
	@Override
	public void responseSuccess(IRequest iReq) {
		// Get the response to the given request
		final ResponseModel response = iReq.getResponse();

		// parse the requirement out of the response body
		final User user = User.fromJSON(response.getBody());
	}

	/**
	 * Outputs that the request failed if the response results in an error.
	 * 
	 * @param iReq
	 *            the request that was made
	 * 
	
	 * @see edu.wpi.cs.wpisuitetng.network.RequestObserver#responseError(edu.wpi.cs.wpisuitetng.network.models.IRequest) */
	@Override
	public void responseError(IRequest iReq) {
		System.err.println(iReq.getResponse().getStatusMessage());
		System.err.println("The request to update a User failed.");
	}

	/**
	 * Outputs that the request failed if the response fails
	 * 
	 * @param iReq
	 *            the request that was made to the server
	 * @param exception
	 *            the exception that occurred.
	 * 
	
	 * @see edu.wpi.cs.wpisuitetng.network.RequestObserver#fail(edu.wpi.cs.wpisuitetng.network.models.IRequest,
	 *      java.lang.Exception) */
	@Override
	public void fail(IRequest iReq, Exception exception) {
		System.err.println("The request to update a requirement failed.");
	}
}
