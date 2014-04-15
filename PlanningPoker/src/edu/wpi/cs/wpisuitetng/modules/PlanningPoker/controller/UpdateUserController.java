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
import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.Request;
import edu.wpi.cs.wpisuitetng.network.models.HttpMethod;

/**
 * This controller responds when the user updates their information at the
 * pop-up prompt to enter an email.
 * 
 * @author Alec Thompson - ajthompson
 * @version Apr 8, 2014
 */
public class UpdateUserController {

	private static UpdateUserController instance;
	private UpdateUserRequestObserver observer;

	/**
	 * Returns the instance of the UpdateUserController, or creates one if it
	 * does not exist.
	 * 
	
	 * @return the instance of UpdateUserController */
	public static UpdateUserController getInstance() {
		if (instance == null)
			instance = new UpdateUserController();
		return instance;
	}
	
	/**
	 * Constructs an UpdateUserController
	 */
	private UpdateUserController() {
		observer = new UpdateUserRequestObserver(this);
	}
	
	/**
	 * Method update.
	 * @param newUser User
	 */
	public void update(User newUser) {
		Request request = Network.getInstance().makeRequest("planningpoker/user", HttpMethod.POST);
		request.setBody(newUser.toJSON());
		request.addObserver(observer);
		request.send();
	}
}
