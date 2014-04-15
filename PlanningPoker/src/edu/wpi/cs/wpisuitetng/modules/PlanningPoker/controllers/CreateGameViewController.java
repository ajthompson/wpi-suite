/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Struct-By-Lightning
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.PlanningPoker.controllers;

import edu.wpi.cs.wpisuitetng.modules.PlanningPoker.views.CreateGameView;
import edu.wpi.cs.wpisuitetng.modules.PlanningPoker.views.MainView;

/**
 * This class is the controller for the create game view window
 */
public class CreateGameViewController {

	private CreateGameView view;

	/**
	 * Constructor for CreateGameViewController.
	 * @param view CreateGameView
	 */
	public CreateGameViewController(CreateGameView view) {

		this.view = view;

	}
/**
  * This actives the view
**/
	public void activateView() {
		MainView.getController().addCloseableTab("Create Game", this.view.newCreateGamePanel());
	}

}
