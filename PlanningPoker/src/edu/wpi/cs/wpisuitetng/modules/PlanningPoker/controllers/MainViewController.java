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

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import edu.wpi.cs.wpisuitetng.janeway.config.ConfigManager;
import edu.wpi.cs.wpisuitetng.modules.PlanningPoker.PlanningPoker;
import edu.wpi.cs.wpisuitetng.modules.PlanningPoker.controller.GetPlanningPokerGamesController;
import edu.wpi.cs.wpisuitetng.modules.PlanningPoker.models.PlanningPokerGame;
import edu.wpi.cs.wpisuitetng.modules.PlanningPoker.models.PlanningPokerGameModel;
import edu.wpi.cs.wpisuitetng.modules.PlanningPoker.view.ClosableTabComponent;
import edu.wpi.cs.wpisuitetng.modules.PlanningPoker.views.ClosedGameView;
import edu.wpi.cs.wpisuitetng.modules.PlanningPoker.views.CreateGameView;
import edu.wpi.cs.wpisuitetng.modules.PlanningPoker.views.OpenGameView;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.controller.GetRequirementsController;

/**
 * This class provides the interface with the main overview tab of the planning
 * poker module.
 * 
 * @author arose
 * @version $Revision: 1.0 $
 */
public class MainViewController {

	public static PlanningPokerGame activeGame;

	private JToolBar toolbar;
	private JTabbedPane tabPane;
	private JTree gameTree;

	/**
	 * Constructor for MainViewController.
	 * @param gameTree JTree 
	 * @param tabPane JTabbedPane 
	 * @param toolbar JToolBar
	 */
	public MainViewController(JTree gameTree, JTabbedPane tabPane, JToolBar toolbar) {
		this.gameTree = gameTree;
		this.tabPane = tabPane;
		this.toolbar = toolbar;

		this.gameTree.addAncestorListener(new AncestorListener() {
			public void ancestorAdded(AncestorEvent event) {
				refreshGameTree();
			}

			public void ancestorMoved(AncestorEvent event) {
				refreshGameTree();
			}

			public void ancestorRemoved(AncestorEvent event) {
				refreshGameTree();
			}
		});

		this.gameTree.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				int selRow = getGameTree().getRowForLocation(e.getX(), e.getY());
				TreePath selPath = getGameTree().getPathForLocation(e.getX(), e.getY());
				if (selRow != -1) {
					if (e.getClickCount() == 2) {
						DefaultMutableTreeNode node = (DefaultMutableTreeNode) selPath
								.getLastPathComponent();
						String gameName = (String) node.getUserObject();
						GetPlanningPokerGamesController.getInstance().retrievePlanningPokerGames();

						gameWasDoubleClicked(PlanningPokerGameModel.getPlanningPokerGame(gameName));
					}
				}
			}
		});
	}

	public void activateView() {
		PlanningPoker.updateComponents(this.toolbar, this.tabPane);
	}

	/**
	 * Method addCloseableTab.
	 * @param tabName String
	 * @param tabPanel JPanel
	 */
	public void addCloseableTab(String tabName, JPanel tabPanel) {
		this.tabPane.addTab(tabName, tabPanel);
		this.tabPane.setTabComponentAt(this.tabPane.indexOfComponent(tabPanel),
				new ClosableTabComponent(this.tabPane));
		this.tabPane.setSelectedComponent(tabPanel);
	}


	public void removeClosableTab(){
		Component selected = tabPane.getSelectedComponent();
		if(selected != null)
			this.tabPane.remove(selected);
	}
	

	public void createGameButtonClicked() {
		CreateGameView.getController().activateView();
	}

	public void refreshGameTree() {
		DefaultTreeModel model = (DefaultTreeModel) this.gameTree.getModel();
		DefaultMutableTreeNode root = (DefaultMutableTreeNode) model.getRoot();

		DefaultMutableTreeNode newGames = new DefaultMutableTreeNode("New");
		DefaultMutableTreeNode openGames = new DefaultMutableTreeNode("Open");
		DefaultMutableTreeNode finishedGames = new DefaultMutableTreeNode("Finished");

		GetRequirementsController.getInstance().retrieveRequirements();
		GetPlanningPokerGamesController.getInstance().retrievePlanningPokerGames();
		
		for (PlanningPokerGame game : PlanningPokerGameModel.getPlanningPokerGames()) {
			DefaultMutableTreeNode nodeToAdd = new DefaultMutableTreeNode(game.getGameName());

			// Has the game ended?
			if (game.isFinished()) {
				finishedGames.add(nodeToAdd);
			}

			// Has the game started voting?
			else if (game.isLive()) {
				openGames.add(nodeToAdd);
			}

			else if (game.getModerator().equals(ConfigManager.getConfig().getUserName())) {
				// The game must be new.
				newGames.add(nodeToAdd);
			}

		}

		root.removeAllChildren();

		if (!newGames.isLeaf()) {
			root.add(newGames);
		}

		if (!openGames.isLeaf()) {
			root.add(openGames);
		}

		if (!finishedGames.isLeaf()) {
			root.add(finishedGames);
		}

		model.reload(root);

		for (int i = 0; i < this.gameTree.getRowCount(); i++) {
			this.gameTree.expandRow(i);
		}
	}

	/**
	 * Method gameWasDoubleClicked.
	 * @param game PlanningPokerGame
	 */
	public void gameWasDoubleClicked(PlanningPokerGame game) {
		
		MainViewController.activeGame = game;

		if (game.isLive() && !game.isFinished()) {
			OpenGameView.open(game);
		}
		if(game.isFinished()) {
			ClosedGameView.open(game);
		}
	}

	/**
	 * Method getGameTree.
	
	 * @return JTree */
	private JTree getGameTree() {
		return this.gameTree;
	}

}
