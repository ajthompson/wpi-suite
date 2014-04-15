/*******************************************************************************
 * Copyright (c) 2012-2014 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * Contributor: team struct-by-lightning
 *******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.PlanningPoker.view;

import edu.wpi.cs.wpisuitetng.janeway.gui.container.toolbar.DefaultToolbarView;
import edu.wpi.cs.wpisuitetng.modules.PlanningPoker.buttons.PlanningPokerButtonsPanel;

/**
 * @author Miguel
 * @version $Revision: 1.0 $
 */
@SuppressWarnings("serial")
public class ToolbarPanel extends DefaultToolbarView {

	PlanningPokerButtonsPanel btnsPanel = new PlanningPokerButtonsPanel();

	public ToolbarPanel() {

		// Make this panel transparent, we want to see the JToolbar gradient
		// beneath it
		this.setOpaque(false);

		addGroup(btnsPanel);
	}

	/**
	 * Method getButton.
	
	 * @return PlanningPokerButtonsPanel */
	public PlanningPokerButtonsPanel getButton() {
		return btnsPanel;
	}
}