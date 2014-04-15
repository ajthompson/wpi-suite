/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Struct-By-Lightning
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.PlanningPoker.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SpinnerDateModel;
import javax.swing.border.LineBorder;

import edu.wpi.cs.wpisuitetng.janeway.config.ConfigManager;
import edu.wpi.cs.wpisuitetng.modules.PlanningPoker.controller.AddPlanningPokerGameController;
import edu.wpi.cs.wpisuitetng.modules.PlanningPoker.controller.GetUserController;
import edu.wpi.cs.wpisuitetng.modules.PlanningPoker.controllers.CreateGameViewController;

import edu.wpi.cs.wpisuitetng.modules.PlanningPoker.email.Mailer;
import edu.wpi.cs.wpisuitetng.modules.PlanningPoker.models.PlanningPokerGame;
import edu.wpi.cs.wpisuitetng.modules.PlanningPoker.models.UserModel;
import edu.wpi.cs.wpisuitetng.modules.PlanningPoker.view.ClosableTabComponent;
import edu.wpi.cs.wpisuitetng.modules.PlanningPoker.view.DatePicker;
import edu.wpi.cs.wpisuitetng.modules.PlanningPoker.view.Exporter;
import edu.wpi.cs.wpisuitetng.modules.PlanningPoker.view.NewGameTab;
import edu.wpi.cs.wpisuitetng.modules.core.models.User;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.controller.GetRequirementsController;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.Requirement;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.RequirementModel;
/**
 * CreateGameView creates the game view for a new game 
 *
 * @author Miguel
 * @version $Revision: 1.0 $
 */
public class CreateGameView {
	
	/**
	 * Static members.
	 */
	
	private static CreateGameView singleInstance;

	
	/**
	 * Method getSingleInstance.
	
	 * @return CreateGameView */
	private static CreateGameView getSingleInstance() {
		if (CreateGameView.singleInstance == null) {
			CreateGameView.singleInstance = new CreateGameView();
		}
		
		return CreateGameView.singleInstance;
	}
	
	/**
	 * Method getController.
	
	 * @return CreateGameViewController */
	public static CreateGameViewController getController() {
		return CreateGameView.getSingleInstance().controller;
	}
	
	/**
	 * Instance members.
	 */
 	
	private CreateGameViewController controller;
	
	private CreateGameView() {
		this.controller = new CreateGameViewController(this);
	}
	
	
/** 
 * creates the view for the create new game window


 * @return JPanel */
	public JPanel newCreateGamePanel() {
		
		
		listOfRequirementsForReset= new DefaultListModel<Requirement>();
		listOfRequirementsToAdd= new DefaultListModel<Requirement>();
		listOfAllRequirements= new DefaultListModel<Requirement>();
		
		panel = new JPanel();
		isTabEditedByUser = false;

		panel.setBorder(new LineBorder(Color.DARK_GRAY));
		panel.setLayout(new BorderLayout(0, 0));

		JPanel titlePanel = new JPanel();
		titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.X_AXIS));
		titlePanel.setBorder(new LineBorder(Color.LIGHT_GRAY));
		panel.add(titlePanel, BorderLayout.NORTH);

		JPanel namePane = new JPanel();
		namePane.setLayout(new BoxLayout(namePane, BoxLayout.X_AXIS));
		titlePanel.add(namePane);

		JLabel lblName = new JLabel("Name:");
		namePane.add(lblName);
		lblName.setFont(new Font("Tahoma", Font.BOLD, 14));

		sessionName = new JTextField();
		namePane.add(sessionName);
		sessionName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		final DateFormat dateFormat = new SimpleDateFormat(
				"MM/dd/yyyy HH:mm:ss");
		final Date date = new Date();
		sessionName.setText(dateFormat.format(date));
		sessionName.setColumns(50);

		JPanel createGamePane = new JPanel();
		titlePanel.add(createGamePane);
		createGamePane.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));

		btnCreateGame = new JButton("Create");
		createGamePane.add(btnCreateGame);
		btnCreateGame.setEnabled(false);

		JButton btnResetGame = new JButton("Reset");
		createGamePane.add(btnResetGame);

		JButton btnExport = new JButton("Export requirements");
		createGamePane.add(btnExport);

		final JCheckBox startNow = new JCheckBox("Start Game Now?");
		createGamePane.add(startNow);

		createGameErrorText = new JLabel("");
		titlePanel.add(createGameErrorText);

		JLabel label = new JLabel("");
		titlePanel.add(label);

		lblGameCreated = new JLabel("Session Created!");
		titlePanel.add(lblGameCreated);
		lblGameCreated.setVisible(false);

		JPanel settingsPanel = new JPanel();
		settingsPanel.setBorder(new LineBorder(Color.LIGHT_GRAY));
		panel.add(settingsPanel, BorderLayout.SOUTH);
		settingsPanel.setLayout(new BorderLayout(0, 0));

		JPanel configPanel = new JPanel();
		configPanel.setBorder(new LineBorder(Color.DARK_GRAY));
		settingsPanel.add(configPanel, BorderLayout.NORTH);

		JLabel lblConfigureGameSettings = new JLabel("Configure game settings");
		configPanel.add(lblConfigureGameSettings);

		JPanel calendarOverview = new JPanel();
		calendarOverview.setBorder(new LineBorder(Color.LIGHT_GRAY));
		settingsPanel.add(calendarOverview, BorderLayout.CENTER);
		calendarOverview.setLayout(new GridLayout(1, 2, 0, 0));

		SpinnerDateModel model_2 = new SpinnerDateModel();
		model_2.setCalendarField(Calendar.MINUTE);

		JPanel calendar = new JPanel();
		calendarOverview.add(calendar);

		JLabel lblEndDate = new JLabel("End Date:");

		final JTextField endDateText = new JTextField(13);
		endDateText.setText(defaultCalendarText);
		endDateText.setEditable(false);
		endDateText.setMinimumSize(new Dimension(
				endDateText.getPreferredSize().width, endDateText
						.getPreferredSize().height));
		final JButton calendarButton_2 = new JButton("Calendar");
		final JPanel endPanel = new JPanel(new GridBagLayout());
		endPanel.setPreferredSize(new Dimension(350, 220));

		final GridBagConstraints constraints1 = new GridBagConstraints();
		constraints1.insets = new Insets(0, 0, 5, 5);
		constraints1.fill = GridBagConstraints.HORIZONTAL;
		constraints1.gridx = 1;
		constraints1.gridy = 0;
		constraints1.gridwidth = 3;
		constraints1.anchor = GridBagConstraints.LINE_START;
		final JCheckBox deadline = new JCheckBox("Have a Deadline?");
		endPanel.add(deadline, constraints1);

		deadline.addActionListener(new ActionListener() {
			private boolean checked = false;

			public void actionPerformed(ActionEvent ae) {
				isTabEditedByUser = true;
				if (!checked) {
					calendarButton_2.setEnabled(true);
					endTime.setEnabled(true);
					checked = true;
				} else {
					if (calendarOpen) {
						calendarButton_2.doClick();
					}
					calendarButton_2.setEnabled(false);
					endTime.setEnabled(false);
					checked = false;
				}
			}
		});

		final GridBagConstraints constraints8 = new GridBagConstraints();
		constraints8.insets = new Insets(0, 0, 5, 5);
		constraints8.fill = GridBagConstraints.HORIZONTAL;
		constraints8.gridx = 0;
		constraints8.gridy = 1;
		constraints8.anchor = GridBagConstraints.LINE_START;
		endPanel.add(new JLabel("End Time:"), constraints8);
		final GridBagConstraints constraints9 = new GridBagConstraints();
		constraints9.insets = new Insets(0, 0, 5, 5);
		constraints9.fill = GridBagConstraints.HORIZONTAL;
		constraints9.gridx = 1;
		constraints9.gridy = 1;
		endTime = new JSpinner();
		endTime.setEnabled(false);
		endTime.setModel(model_2);
		endTime.setEditor(new JSpinner.DateEditor(endTime, "h:mm a"));
		endPanel.add(endTime, constraints9);

		JLabel lblrequired2 = new JLabel("*");
		GridBagConstraints gbc_lblrequired2 = new GridBagConstraints();
		gbc_lblrequired2.insets = new Insets(0, 0, 5, 0);
		gbc_lblrequired2.anchor = GridBagConstraints.LINE_START;
		gbc_lblrequired2.gridx = 2;
		gbc_lblrequired2.gridy = 1;
		endPanel.add(lblrequired2, gbc_lblrequired2);
		final GridBagConstraints constraints10 = new GridBagConstraints();
		constraints10.insets = new Insets(0, 0, 5, 5);
		constraints10.fill = GridBagConstraints.HORIZONTAL;
		constraints10.gridx = 0;
		constraints10.gridy = 2;
		constraints10.weightx = 0;
		constraints10.anchor = GridBagConstraints.LINE_START;
		endPanel.add(lblEndDate, constraints10);
		final GridBagConstraints constraints11 = new GridBagConstraints();
		constraints11.insets = new Insets(0, 0, 5, 5);
		constraints11.fill = GridBagConstraints.HORIZONTAL;
		constraints11.weightx = 1;
		constraints11.gridx = 1;
		constraints11.gridy = 2;
		endPanel.add(endDateText, constraints11);
		final GridBagConstraints constraints12 = new GridBagConstraints();
		constraints12.insets = new Insets(0, 0, 5, 0);
		constraints12.fill = GridBagConstraints.HORIZONTAL;
		constraints12.weightx = 0;
		constraints12.gridx = 2;
		constraints12.gridy = 2;
		calendarButton_2.setEnabled(false);
		endPanel.add(calendarButton_2, constraints12);
		final GridBagConstraints constraints13 = new GridBagConstraints();
		constraints13.insets = new Insets(0, 0, 5, 5);
		constraints13.gridx = 0;
		constraints13.gridy = 3;
		constraints13.weightx = 1;
		constraints13.weighty = 1;
		endPanel.add(new JLabel(), constraints13);
		final GridBagConstraints constraints14 = new GridBagConstraints();
		constraints14.insets = new Insets(0, 0, 0, 5);
		constraints14.gridx = 0;
		constraints14.gridy = 4;
		constraints14.weightx = 1;
		constraints14.weighty = 1;
		endPanel.add(new JLabel(), constraints14);
		constraints14.weightx = 0;
		constraints14.weighty = 0;

		JPanel calendarHandler = new JPanel();
		final JPanel calendarPanel = new JPanel(new GridBagLayout());
		calendarPanel.setPreferredSize(new Dimension(350, 220));
		calendarHandler.add(calendarPanel);
		calendarOverview.add(calendarHandler);
		calendarButton_2.addActionListener(new ActionListener() {
			private boolean open = false;
			private DatePicker dp;
/**
 * action for using the calendar method for enabling it and selecting
 * a date
 */
			public void actionPerformed(ActionEvent ae) {
				isTabEditedByUser = true;
				if (!open) {
					dp = new DatePicker(calendarPanel, constraints14,
							endDateText);
					open = true;
				} else {
					dp.close();
					open = false;
				}
				calendarOpen = open;
			}
		});

		calendar.add(endPanel);

		JPanel cardPanel = new JPanel(new GridBagLayout());
		JPanel deckPanel = new JPanel();
		JPanel cardDeckPane = new JPanel();

		calendarOverview.add(deckPanel);
		

		JLabel lblCardDeck = new JLabel("Card deck:");
		cardDeckPane.add(lblCardDeck);

		deckType.setModel(new DefaultComboBoxModel<String>(new String[] {"Default", "Lightning Deck", "No Deck"}));
	
		cardDeckPane.add(deckType);
		final JTextField deckOverview = new JTextField();
		
		deckOverview.setText("1, 1, 2, 3, 5, 8, 13, 0?");

		deckOverview.setEditable(false);

		cardPanel.setPreferredSize(new Dimension(350, 220));
		final GridBagConstraints constraints15 = new GridBagConstraints();
		constraints15.fill = GridBagConstraints.HORIZONTAL;
		constraints15.gridx = 0;
		constraints15.gridy = 0;
		cardPanel.add(cardDeckPane, constraints15);
		final GridBagConstraints constraints16 = new GridBagConstraints();
		constraints16.fill = GridBagConstraints.HORIZONTAL;
		constraints16.anchor = GridBagConstraints.LINE_END;
		constraints16.gridx = 0;
		constraints16.gridy = 1;
		cardPanel.add(deckOverview, constraints16);
		final GridBagConstraints constraints17 = new GridBagConstraints();
		constraints17.gridx = 0;
		constraints17.gridy = 2;
		constraints17.weightx = 1;
		constraints17.weighty = 1;
		cardPanel.add(new JLabel(), constraints17);
		deckPanel.add(cardPanel);

		/**
		* Handles the combo box listener for deck selection and displays the deck type as a string
		*/
		deckType.addActionListener(new ActionListener () {
		   public void actionPerformed(ActionEvent e) {
			isTabEditedByUser = true;
		    JComboBox combo = (JComboBox)e.getSource();
		    String selection = (String)combo.getSelectedItem();
		    	if(selection.contentEquals("Default")){
		    		// Replace this with button contents		               
		    		deckOverview.setText("1, 1, 2, 3, 5, 8, 13, 0?");                
		   		}
		   
		   		else if(selection.contentEquals("Lighning Deck")){
		   			//Replace this with button contents
		   			deckOverview.setText("0, 0.5, 1, 2, 3, 5, 8, 13, 20 40, 100");
		   		}
		   		else if(selection.contentEquals("No Deck")){
		   			deckOverview.setText("User will be able to enter their own estimation");
		   		}
		   		
		   }

		});

		
		JPanel requirementsPanel = new JPanel();
		requirementsPanel.setBorder(new LineBorder(Color.LIGHT_GRAY));
		panel.add(requirementsPanel, BorderLayout.CENTER);
		requirementsPanel.setLayout(new BorderLayout(0, 0));

		JPanel requirementsHeader = new JPanel();
		requirementsHeader.setBorder(new LineBorder(Color.DARK_GRAY));
		requirementsPanel.add(requirementsHeader, BorderLayout.NORTH);

		JLabel lblChooseRequirementsTo = new JLabel(
				"Choose requirements to estimate");
		lblChooseRequirementsTo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		requirementsHeader.add(lblChooseRequirementsTo);

		JPanel requirementsSelector = new JPanel();
		requirementsPanel.add(requirementsSelector, BorderLayout.CENTER);
		requirementsSelector.setLayout(new BoxLayout(requirementsSelector,
				BoxLayout.X_AXIS));

		JPanel projectRequirements = new JPanel();
		projectRequirements.setBorder(new LineBorder(Color.LIGHT_GRAY));
		requirementsSelector.add(projectRequirements);
		projectRequirements.setLayout(new BorderLayout(0, 0));

		JPanel projectHeader = new JPanel();
		projectHeader.setBorder(new LineBorder(Color.LIGHT_GRAY));
		projectRequirements.add(projectHeader, BorderLayout.NORTH);

		JLabel lblAllProjectRequirements = new JLabel(
				"All project requirements");
		projectHeader.add(lblAllProjectRequirements);

		JPanel projectList = new JPanel();
		projectList.setBorder(new LineBorder(Color.LIGHT_GRAY));
		JScrollPane scroller = new JScrollPane(projectList);
		projectRequirements.add(scroller, BorderLayout.CENTER);
		projectList.setLayout(new BorderLayout(0, 0));

		projectList.add(allRequirements);

		JPanel addRemPanel = new JPanel();
		addRemPanel.setBorder(new LineBorder(Color.LIGHT_GRAY, 0));
		requirementsSelector.add(addRemPanel);

		JPanel topSpacer = new JPanel();

		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new GridLayout(4, 1, 4, 4));

		JPanel topmostButton = new JPanel();
		buttonsPanel.add(topmostButton);
		topmostButton.setLayout(new BorderLayout(0, 0));

		final JButton btn_addAll = new JButton(">>");
		topmostButton.add(btn_addAll, BorderLayout.CENTER);

		JPanel topButton = new JPanel();
		buttonsPanel.add(topButton);
		topButton.setLayout(new BorderLayout(0, 0));

		final JButton btn_addToGame = new JButton(">");
		btn_addToGame.setEnabled(false);
		btn_addToGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

			}
			
		});
		
		
		topButton.add(btn_addToGame, BorderLayout.CENTER);

		JPanel bottomButton = new JPanel();
		buttonsPanel.add(bottomButton);
		bottomButton.setLayout(new BorderLayout(0, 0));

		final JButton btn_removeFromGame = new JButton("<");
		bottomButton.add(btn_removeFromGame, BorderLayout.CENTER);

		btn_removeFromGame.setEnabled(false);

		JPanel bottommostButton = new JPanel();
		buttonsPanel.add(bottommostButton);
		bottommostButton.setLayout(new BorderLayout(0, 0));

		btn_removeAll = new JButton("<<");
		bottommostButton.add(btn_removeAll);
		btn_removeAll.setEnabled(false);

		JPanel bottomSpacer = new JPanel();
		GroupLayout gl_addRemPanel = new GroupLayout(addRemPanel);
		gl_addRemPanel.setHorizontalGroup(
			gl_addRemPanel.createParallelGroup(Alignment.LEADING)
				.addComponent(topSpacer, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
				.addComponent(buttonsPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addComponent(bottomSpacer, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
		);
		gl_addRemPanel.setVerticalGroup(
			gl_addRemPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_addRemPanel.createSequentialGroup()
					.addComponent(topSpacer, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
					.addGap(3)
					.addComponent(buttonsPanel, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
					.addGap(3)
					.addComponent(bottomSpacer, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
		);
		addRemPanel.setLayout(gl_addRemPanel);

		JPanel gameRequirements = new JPanel();
		gameRequirements.setBorder(new LineBorder(Color.LIGHT_GRAY));
		requirementsSelector.add(gameRequirements);
		gameRequirements.setLayout(new BorderLayout(0, 0));

		JPanel gameHeader = new JPanel();
		gameHeader.setBorder(new LineBorder(Color.LIGHT_GRAY));
		gameRequirements.add(gameHeader, BorderLayout.NORTH);

		JLabel lblRequirementsToEstimate = new JLabel(
				"Requirements to estimate");
		gameHeader.add(lblRequirementsToEstimate);

		JPanel gameList = new JPanel();
		gameList.setBorder(new LineBorder(Color.LIGHT_GRAY));
		JScrollPane scroller_2 = new JScrollPane(gameList);
		gameRequirements.add(scroller_2, BorderLayout.CENTER);
		gameList.setLayout(new BorderLayout(0, 0));

		JTextPane txtpnLoggedInAs = new JTextPane();
		txtpnLoggedInAs.setText("Logged in as: "
				+ ConfigManager.getConfig().getUserName());
		txtpnLoggedInAs.setFocusable(false);
		settingsPanel.add(txtpnLoggedInAs, BorderLayout.SOUTH);

		/**
		 * Listens to the session name field and disables "Create Game" button
		 * if the field is empty
		 */
		sessionName.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent arg0) {

			}
/**
 * Enables create button key to work if all fields are properly filled out
 * else tells user what is still needed
 */
			@Override
			public void keyReleased(KeyEvent arg0) {
				isTabEditedByUser = true;
				String currentText = sessionName.getText();
				if (currentText.equals("")) {

					btnCreateGame.setEnabled(false);
					createGameErrorText.setText("Session needs a name");
				} else {
					if (listOfRequirementsToAdd.size() == 0) {
						btnCreateGame.setEnabled(false);
					}
					else{
						btnCreateGame.setEnabled(true);
						createGameErrorText.setText("");
					}

				}
			}

			@Override
			public void keyPressed(KeyEvent arg0) {

			}
		});

		selectedRequirements.setModel(listOfRequirementsToAdd);

		GetRequirementsController.getInstance().retrieveRequirements();

		try { // We need to sleep for the requirement request to be in
			Thread.sleep(150);
		} catch (InterruptedException e1) {
		}

		/**
		 * Adds list of current requirements in requirement model to the list
		 * that will be added to the JList that will hold the requirements to be
		 * added to the game
		 */
		final List<Requirement> requirements = RequirementModel.getInstance()
				.getRequirements();
		// We iterate through the requirements list and add to that JList.
		for (int i = 0; i < requirements.size(); i++) {
			Requirement req = requirements.get(i);
			listOfAllRequirements.addElement(req);
			if(req.getIteration().equals("Backlog")){
				listOfRequirementsForReset.addElement(req);
			}
		}

		allRequirements.setModel(listOfAllRequirements);

		allRequirements.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				btn_addToGame.setEnabled(true);
				btn_addAll.setEnabled(true);
				btn_removeFromGame.setEnabled(false);
			}
		});
		
		selectedRequirements.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				btn_addToGame.setEnabled(false);
				btn_removeFromGame.setEnabled(true);
				btn_removeAll.setEnabled(true);
			}
		});
		gameList.add(selectedRequirements);

		// Get and add the list of emails to the mailer
		GetUserController.getInstance().retrieveUser();

		try {
			Thread.sleep(150);
		} catch (InterruptedException e2) {
		}

		userList = UserModel.getInstance().getUsers();
		mailer.addEmailFromUsers(userList);

		/**
		 * Saves data entered about the game when 'Create Game' button is
		 * pressed
		 */
		btnCreateGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isTabEditedByUser = false;

				enteredName = sessionName.getText();
				selectedDeckType = (String)deckType.getSelectedItem();
				GregorianCalendar startCal, endCal;

				// Checks to see if the user set the date to something other
				// than default text
				if (endDateText.getText().equals("Click Calendar to set date")
						&& deadline.isSelected()) {
					System.out.println("Please enter a valid date");
				} else {
					
					String[] endDate = endDateText.getText().split("-");

					Date endVal = (Date) endTime.getValue();

					/**
					 * Gregorian Calendars save month values starting at 0, so
					 * the months in both of the below calendar has has 1
					 * subtracted from it, as the values are being pulled from
					 * the text field, which does not start at zero
					 */
					startCal = new GregorianCalendar();
					if (deadline.isSelected()) {
						endCal = new GregorianCalendar(Integer
								.parseInt(endDate[2]), Integer
								.parseInt(endDate[1]) - 1, Integer
								.parseInt(endDate[0]), endVal.getHours(),
								endVal.getMinutes());
					} else {
						endCal = new GregorianCalendar(9999, 11, 18);
					}
					System.out.println(startCal.toString() + "\n"
							+ endCal.toString());
					System.out.println(enteredName);
					System.out.println(selectedDeckType);
						
					gameRequirementIDsList.clear();
					
					for (int i = 0; i < listOfRequirementsToAdd.size(); i++) {
						gameRequirementIDsList.add(listOfRequirementsToAdd
								.getElementAt(i).getId());
						System.out.println("Requirement Name: "
								+ listOfRequirementsToAdd.get(i));
					}

					System.out.println(gameRequirementIDsList.size());

					if (startCal.before(endCal)) {

						PlanningPokerGame game;
						if (startNow.isSelected()) {
							game = new PlanningPokerGame(enteredName,
									"Default description",

									(String)deckType.getSelectedItem(), gameRequirementIDsList,
									false, true, startCal, endCal, ConfigManager.getConfig().getUserName());
						} else {
							game = new PlanningPokerGame(enteredName,
									"Default description", (String)deckType.getSelectedItem(),
									gameRequirementIDsList, false, false,
									startCal, endCal, ConfigManager.getConfig().getUserName());

						}
						System.out.println("User Moderator: "
								+ ConfigManager.getConfig().getUserName());
						System.out.println("Planning Poker Live: "
								+ game.isLive());
						AddPlanningPokerGameController.getInstance()
								.addPlanningPokerGame(game);
						lblGameCreated.setVisible(true);
						btnCreateGame.setEnabled(false);
						mailer.send();
					}
					else{
						// Error message when the session name is empty
						if (sessionName.getText().isEmpty()) {
							btnCreateGame.setEnabled(false);
							JOptionPane emptyNameErrorPanel = new JOptionPane(
									"You must enter the session name",
									JOptionPane.ERROR_MESSAGE);
							JDialog errorDialog = emptyNameErrorPanel
									.createDialog(null);
							errorDialog.setLocation(thisPanel.getWidth() / 2,
									thisPanel.getHeight() / 2);
							errorDialog.setVisible(true);
							btnCreateGame.setEnabled(false);
						}
						System.out.println("Start date is after the end date.");
						
					}
				}
	
				MainView.getController().refreshGameTree();
				MainView.getController().removeClosableTab();
			}
			
		});

		/**
		 * Removes all items from box of all requirements and adds them to the
		 * box of requirements that will be used in the session
		 */
		btn_addAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isTabEditedByUser = true;

				while (listOfAllRequirements.getSize() > 0) {
					System.out.println(listOfAllRequirements.elementAt(0));
					listOfRequirementsToAdd.addElement(listOfAllRequirements
							.remove(0));
				}

				selectedRequirements.setModel(listOfRequirementsToAdd);
				allRequirements.setModel(listOfAllRequirements);

		
				btn_removeAll.setEnabled(true);
			
				
				btn_addAll.setEnabled(false);
				btn_addToGame.setEnabled(false);
				btnCreateGame.setEnabled(true);
				
			}
		});

		/**
		 * Removes selected item from box of all requirements and adds it to the
		 * box of requirements that will be used in the session
		 */
		btn_addToGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isTabEditedByUser = true;
				
				for(Requirement req: allRequirements.getSelectedValuesList()){

					System.out.println("Added "
							+ req
							+ "to selected requirements");

					listOfRequirementsToAdd.addElement(req);
					listOfAllRequirements.removeElement(req);
					selectedRequirements.setModel(listOfRequirementsToAdd);
					allRequirements.setModel(listOfAllRequirements);
					
					btn_addToGame.setEnabled(false);
					btn_removeAll.setEnabled(true);

					if (listOfAllRequirements.size() == 0) {
						btn_addToGame.setEnabled(false);
						btn_addAll.setEnabled(false);
						btnCreateGame.setEnabled(false);
					}

				}
				
				btnCreateGame.setEnabled(true);
			}
		});

		/**
		 * Removes selected item from box of selected requirements for session
		 * and adds it back to the total list of requirements
		 */
		btn_removeFromGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isTabEditedByUser = true;
				
				for(Requirement req: selectedRequirements.getSelectedValuesList()){

					System.out.println("Added "
							+ req
							+ "to selected requirements");

					listOfAllRequirements.addElement(req);
					listOfRequirementsToAdd.removeElement(req);
					allRequirements.setModel(listOfAllRequirements);
					selectedRequirements.setModel(listOfRequirementsToAdd);
					btn_removeFromGame.setEnabled(false);
					btn_addToGame.setEnabled(false);
					btn_addAll.setEnabled(false);
					
					if (listOfRequirementsToAdd.size() == 0) {
						btn_removeFromGame.setEnabled(false);
						btn_removeAll.setEnabled(false);
					}
				}
				
				if(listOfRequirementsToAdd.size() == 0){
					btnCreateGame.setEnabled(false);
				}
				
				btn_addAll.setEnabled(true);
				

			}
		});

		/**
		 * Removes selected item from box of selected requirements for session
		 * and adds it back to the total list of requirements
		 */
		btn_removeAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isTabEditedByUser = true;
				btnCreateGame.setEnabled(false);
				
				while (listOfRequirementsToAdd.getSize() > 0) {
					System.out.println(listOfRequirementsToAdd.elementAt(0));
					listOfAllRequirements.addElement(listOfRequirementsToAdd
							.remove(0));
				}

				selectedRequirements.setModel(listOfRequirementsToAdd);
				allRequirements.setModel(listOfAllRequirements);

				btn_addToGame.setEnabled(false);
				btn_addAll.setEnabled(true);
				btn_removeFromGame.setEnabled(false);
				btn_removeAll.setEnabled(false);
				
				
			}
		});

		/**
		 * Reset the input field after the user changed the data.
		 */

		btnResetGame.addActionListener(new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	isTabEditedByUser = true;
		    	// Reset game name
		    	sessionName.setText(dateFormat.format(date));

		    	// Reset start and end date
		    	//startDateText.setText(defaultCalendarText);
		    	endDateText.setText(defaultCalendarText);
		    	btnCreateGame.setEnabled(true);
	        	createGameErrorText.setText("");
		    	// Reset start and end time
		    	endTime.setEditor(new JSpinner.DateEditor(endTime, "h:mm a"));
		    	// Reset the requirements boxes

		    	isTabEditedByUser = true;

		    	while(listOfRequirementsToAdd.getSize() > 0){
		    		System.out.println(listOfRequirementsToAdd.elementAt(0));
		    		listOfAllRequirements.addElement(listOfRequirementsToAdd.remove(0));
		    	}

		    	selectedRequirements.setModel(listOfRequirementsToAdd);
		    	allRequirements.setModel(listOfAllRequirements);



		    	btn_removeFromGame.setEnabled(false);
	    		btn_removeAll.setEnabled(false);
		    }
		});

		/**
		 * Exports the list of selected requirements to a file when btnExport is
		 * pressed
		 */
		btnExport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isTabEditedByUser = true;
				//Create a file chooser
				final JFileChooser fc = new JFileChooser();
				//In response to a button click:
				int returnVal = fc.showSaveDialog(panel);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					// Create exporter
					Exporter ex = new Exporter();
					// Export requirements
					ex.exportAsJSON(listOfRequirementsToAdd, fc.getSelectedFile().getAbsolutePath());
					System.out.println("Exported all selected requirements\n");
				}
			}
		});
		return panel;
	}

	
	private JPanel panel;
	private JTextField sessionName;
	private NewGameTab thisPanel;
	final String defaultCalendarText = "Click Calendar to set date";
	/**
	 * Error label that will show the reason why a game cannot be created
	 */
	JLabel createGameErrorText;

	/**
	 *  indicates whether the user edited the new game tab
	 */
	public boolean isTabEditedByUser;

	/**
	 * A dropdown box that contains the default deck to choose.
	 */
	JComboBox<String> deckType = new JComboBox<String>();
	String selectedDeckType = new String();
	/**
	 * A list contains of available requirements to add to the session
	 */
	JList<Requirement> selectedRequirements = new JList<Requirement>();

	/**
	 * A JList that contains the requirements that are to be estimated in the
	 * current planning poker session
	 */
	JList<Requirement> allRequirements = new JList<Requirement>();

	/**
	 * These two lists contain the lists representing what the user has selected
	 * These lists are then put into the JList swing component, which uses them
	 * to display what the user will see in the GUI
	 *
	 * listOfRequirementsToAdd --> the list of requirements that the user wants
	 * added to the game
	 *
	 * listOfAllRequirements -> the list of all the requirements in the requirement manager
	 *
	 * listOfRequirementsForReset --> the full list of requirements. This is never edited
	 * 								  and is only used for reseting the requirements when
	 * 								  the reset button is pressed
	 */

	DefaultListModel<Requirement> listOfRequirementsToAdd= new DefaultListModel<Requirement>();
	DefaultListModel<Requirement> listOfAllRequirements= new DefaultListModel<Requirement>();
	DefaultListModel<Requirement> listOfRequirementsForReset = new DefaultListModel<Requirement>();


	/**
	 * The list of requirement IDs that will actually be saved to the game will
	 * be the same as 'listOfRequirementsToAdd' once the game is in the process
	 * of being created
	 */
	List<Integer> gameRequirementIDsList = new ArrayList<Integer>();

	/**
	 * The list of users to whom emails will be sent (assuming their email
	 * address has been added to the server)
	 */
	List<User> userList = new ArrayList<User>();

	/**
	 * The mailer which will send emails to all users with emails in their account
	 */
	Mailer mailer = new Mailer();

	JSpinner endTime;
	String enteredName = new String();
	GregorianCalendar startCalendar;
	GregorianCalendar endCalendar;

	JButton btnCreateGame;
	JLabel lblGameCreated;
	JButton btn_removeFromGame;
	JButton btn_addToGame;
	JButton btn_removeAll;
	boolean calendarOpen = false;
}
