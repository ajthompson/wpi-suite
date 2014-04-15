/*******************************************************************************
 * Copyright (c) 2012-2014 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.PlanningPoker;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JComponent;

import edu.wpi.cs.wpisuitetng.janeway.modules.IJanewayModule;
import edu.wpi.cs.wpisuitetng.janeway.modules.JanewayTabModel;
import edu.wpi.cs.wpisuitetng.modules.PlanningPoker.views.MainView;

/**
 * This is the planning poker module. It initializes the tabs.
 */
public class PlanningPoker implements IJanewayModule {

	private List<JanewayTabModel> tabs;

	public static JComponent toolbarComponent;
	public static JComponent mainComponent;

	public PlanningPoker() {

		// Initialize the list of tabs (however, this module has only one tab).
		tabs = new ArrayList<JanewayTabModel>();

		// TODO: If user has not provided contact info, show splash view
		// instead.
		MainView.getController().activateView();

		// Create a tab model that contains the toolbar panel and the main
		// content panel
		JanewayTabModel tab = new JanewayTabModel(this.getName(),
				new ImageIcon(), PlanningPoker.toolbarComponent,
				PlanningPoker.mainComponent);

		// Add the tab to the list of tabs owned by this module
		tabs.add(tab);
	}

	/**
	 * Method updateComponents.
	 * 
	 * @param toolbarComponent
	 *            JComponent
	 * @param mainComponent
	 *            JComponent
	 */
	public static void updateComponents(JComponent toolbarComponent,
			JComponent mainComponent) {
		PlanningPoker.toolbarComponent = toolbarComponent;
		PlanningPoker.mainComponent = mainComponent;
	}

	/*
	 * @see edu.wpi.cs.wpisuitetng.janeway.modules.IJanewayModule#getName()
	 */
	@Override
	public String getName() {
		return "PlanningPoker";
	}

	/*
	 * @see edu.wpi.cs.wpisuitetng.janeway.modules.IJanewayModule#getTabs()
	 */
	@Override
	public List<JanewayTabModel> getTabs() {
		return tabs;
	}

}
