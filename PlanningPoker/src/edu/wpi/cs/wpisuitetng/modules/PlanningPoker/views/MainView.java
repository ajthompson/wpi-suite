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

import java.awt.Color;

import javax.swing.tree.DefaultMutableTreeNode;

import edu.wpi.cs.wpisuitetng.modules.PlanningPoker.controllers.MainViewController;
import edu.wpi.cs.wpisuitetng.modules.PlanningPoker.view.ToolbarView;


/**
 * @author Miguel
 * @version $Revision: 1.0 $
 */
public class MainView {
	
	/**
	 * Static members.
	 */
	
	private static MainView singleInstance;
	
	/**
	 * Method getSingleInstance.
	
	 * @return MainView */
	private static MainView getSingleInstance() {
		if (MainView.singleInstance == null) {
			MainView.singleInstance = new MainView();
		}
		
		return MainView.singleInstance;
	}
	
	/**
	 * Method getController.
	
	 * @return MainViewController */
	public static MainViewController getController() {
		return MainView.getSingleInstance().controller;
	}
	
	/**
	 * Instance members.
	 */
 	
	private MainViewController controller;
	
	private MainView() {
		initComponents();
		this.controller = new MainViewController(this.gameTree, 
													this.mainComponent, 
													this.toolbarComponent);
	}

	private void initComponents() {

		toolbarComponent = new ToolbarView(true);
		
		gameTree = new javax.swing.JTree(new DefaultMutableTreeNode("All Games"));
		
		treeScrollPane = new javax.swing.JScrollPane();
		treeScrollPane.setMinimumSize(new java.awt.Dimension(200, 384));
		treeScrollPane.setViewportView(gameTree);

		createGameButton = new javax.swing.JButton();
		mainComponent = new javax.swing.JTabbedPane();
		overviewTabSplitPane = new javax.swing.JSplitPane();
		
		
		infoPanel = new javax.swing.JPanel();
		left = new javax.swing.JPanel();
		whatIsTitle = new javax.swing.JLabel();
		leftHorizontalSeparator = new javax.swing.JSeparator();
		whatIsBody = new javax.swing.JLabel();
		right = new javax.swing.JPanel();
		whatIsTitle1 = new javax.swing.JLabel();
		leftHorizontalSeparator1 = new javax.swing.JSeparator();
		whatIsBody1 = new javax.swing.JLabel();
		jPanel1 = new javax.swing.JPanel();
		jTextField1 = new javax.swing.JTextField();
		jLabel1 = new javax.swing.JLabel();
		jButton2 = new javax.swing.JButton();
		jPanel2 = new javax.swing.JPanel();
		jCheckBox1 = new javax.swing.JCheckBox();
		jPanel5 = new javax.swing.JPanel();
		jCheckBox2 = new javax.swing.JCheckBox();
		jPanel3 = new javax.swing.JPanel();
		jLabel3 = new javax.swing.JLabel();
		jPanel6 = new javax.swing.JPanel();
		jLabel5 = new javax.swing.JLabel();
		jPanel10 = new javax.swing.JPanel();
		jPanel8 = new javax.swing.JPanel();
		jScrollPane2 = new javax.swing.JScrollPane();
		jList2 = new javax.swing.JList();
		jPanel13 = new javax.swing.JPanel();
		jLabel4 = new javax.swing.JLabel();
		jPanel11 = new javax.swing.JPanel();
		jPanel4 = new javax.swing.JPanel();
		jPanel9 = new javax.swing.JPanel();
		jButton3 = new javax.swing.JButton();
		jButton4 = new javax.swing.JButton();
		jButton5 = new javax.swing.JButton();
		jButton6 = new javax.swing.JButton();
		jPanel12 = new javax.swing.JPanel();
		jPanel7 = new javax.swing.JPanel();
		jLabel2 = new javax.swing.JLabel();
		jScrollPane1 = new javax.swing.JScrollPane();
		jList1 = new javax.swing.JList();
		jPanel14 = new javax.swing.JPanel();
		jComboBox1 = new javax.swing.JComboBox();
		jLabel6 = new javax.swing.JLabel();
		jPanel16 = new javax.swing.JPanel();
		jLabel8 = new javax.swing.JLabel();
		jPanel15 = new javax.swing.JPanel();
		jLabel7 = new javax.swing.JLabel();

		createGameButton.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
		createGameButton.setText("<html>Create a new game</html>");

		mainComponent.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

		

		overviewTabSplitPane.setLeftComponent(treeScrollPane);

		infoPanel.setLayout(new java.awt.GridLayout(1, 2));

		left.setBorder(javax.swing.BorderFactory
				.createLineBorder(new java.awt.Color(204, 204, 204)));

		whatIsTitle.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
		whatIsTitle.setText("<html>Getting started</html>");

		whatIsBody.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
		whatIsBody
				.setText("<html> What does it mean if you click...<br><br> <em><strong>Create New Game:</strong></em><br> This will open the window to create a new game where you can choose the user requirements for the game.<br><br> <em><strong>New folder:</strong></em><br> These games are created but have not been started yet. If you click one of the games in this folder you if you are the moderator you can start the game.<br><br> <em><strong>Open folder:</strong></em><br> These games have been created and started you can estimate each user story. After the user story is estimated it will be marked as completed.<br><br> <em><strong>Closed folder:</strong></em><br> These are closed games. By clicking on the games in this folder you will get the results from this game. If you are the moderator of this game then you should be able to edit results.<br><br> If you are looking for further information refer to Help. </html>");

		javax.swing.GroupLayout leftLayout = new javax.swing.GroupLayout(left);
		left.setLayout(leftLayout);
		leftLayout.setHorizontalGroup(leftLayout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				leftLayout
						.createSequentialGroup()
						.addContainerGap()
						.addGroup(
								leftLayout
										.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
										.addComponent(whatIsTitle)
										.addComponent(leftHorizontalSeparator)
										.addComponent(whatIsBody,
												javax.swing.GroupLayout.DEFAULT_SIZE, 674,
												Short.MAX_VALUE)).addContainerGap()));
		leftLayout.setVerticalGroup(leftLayout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				leftLayout
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(whatIsTitle, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(leftHorizontalSeparator,
								javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(18, 18, 18)
						.addComponent(whatIsBody, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap(478, Short.MAX_VALUE)));

		infoPanel.add(left);

		right.setBorder(javax.swing.BorderFactory
				.createLineBorder(new java.awt.Color(204, 204, 204)));

		whatIsTitle1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
		whatIsTitle1.setText("<html>What is Planning Poker?</html>");

		whatIsBody1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
		whatIsBody1
				.setText("<html> Planning Poker is a consensus-based tool for software developers to come together and estimate effort of development goals for the team. This is a great tool for agile teams to estimate the user stories they have for a given iteration.<br><br> The idea behind Planning Poker is that team discusses each user story and then goes into the game and then each user goes into the deck and selects the card that represents how effort he or she thinks the task will take. This process can be repeated for any number of user stories in the game.<br><br> During the game all estimates remain private until everyone has chose his or her card. After all estimates are in the Planning Poker game will calculate the Mean, Median, Mode, Minimum, Maximum, and Standard Deviation of the game. These values can be used for the team to continue the discussion and come to a consensus of what the groups estimate is for the user story.<br><br> </html>");

		javax.swing.GroupLayout rightLayout = new javax.swing.GroupLayout(right);
		right.setLayout(rightLayout);
		rightLayout.setHorizontalGroup(rightLayout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				rightLayout
						.createSequentialGroup()
						.addContainerGap()
						.addGroup(
								rightLayout
										.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
										.addComponent(whatIsTitle1,
												javax.swing.GroupLayout.DEFAULT_SIZE, 674,
												Short.MAX_VALUE)
										.addComponent(leftHorizontalSeparator1)
										.addComponent(whatIsBody1,
												javax.swing.GroupLayout.PREFERRED_SIZE, 0,
												Short.MAX_VALUE)).addContainerGap()));
		rightLayout.setVerticalGroup(rightLayout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				rightLayout
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(whatIsTitle1, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(leftHorizontalSeparator1,
								javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(18, 18, 18)
						.addComponent(whatIsBody1, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap(433, Short.MAX_VALUE)));

		infoPanel.add(right);

		overviewTabSplitPane.setRightComponent(infoPanel);

		mainComponent.addTab("Overview", overviewTabSplitPane);

		jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204,
				204)));

		jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
		jLabel1.setText("Game Name");

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				jPanel1Layout.createSequentialGroup().addContainerGap().addComponent(jLabel1)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addComponent(jTextField1).addContainerGap()));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				jPanel1Layout
						.createSequentialGroup()
						.addContainerGap()
						.addGroup(
								jPanel1Layout
										.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(jTextField1,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(jLabel1))
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		jButton2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
		jButton2.setText("CREATE");

		jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204,
				204)));

		jCheckBox1.setText("Start voting immediately");

		javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
		jPanel2.setLayout(jPanel2Layout);
		jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				javax.swing.GroupLayout.Alignment.TRAILING,
				jPanel2Layout.createSequentialGroup()
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(jCheckBox1).addContainerGap()));
		jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				jPanel2Layout
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(jCheckBox1, javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addContainerGap()));

		jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204,
				204)));
		jPanel5.setForeground(new java.awt.Color(204, 204, 204));

		jCheckBox2.setFont(new java.awt.Font("Lucida Grande", 1, 12)); // NOI18N
		jCheckBox2.setText("End voting after a deadline?");

		jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204,
				204)));

		jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
		jLabel3.setText("End time stuff");

		javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
		jPanel3.setLayout(jPanel3Layout);
		jPanel3Layout.setHorizontalGroup(jPanel3Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				jPanel3Layout.createSequentialGroup().addContainerGap().addComponent(jLabel3)
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		jPanel3Layout.setVerticalGroup(jPanel3Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				jPanel3Layout.createSequentialGroup().addContainerGap().addComponent(jLabel3)
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204,
				204)));

		jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
		jLabel5.setText("End date stuff");

		javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
		jPanel6.setLayout(jPanel6Layout);
		jPanel6Layout.setHorizontalGroup(jPanel6Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				jPanel6Layout.createSequentialGroup().addContainerGap().addComponent(jLabel5)
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		jPanel6Layout.setVerticalGroup(jPanel6Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				jPanel6Layout.createSequentialGroup().addContainerGap().addComponent(jLabel5)
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
		jPanel5.setLayout(jPanel5Layout);
		jPanel5Layout.setHorizontalGroup(jPanel5Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				javax.swing.GroupLayout.Alignment.TRAILING,
				jPanel5Layout
						.createSequentialGroup()
						.addContainerGap()
						.addGroup(
								jPanel5Layout
										.createParallelGroup(
												javax.swing.GroupLayout.Alignment.TRAILING)
										.addComponent(jCheckBox2,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addComponent(jPanel6,
												javax.swing.GroupLayout.Alignment.LEADING,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addComponent(jPanel3,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)).addContainerGap()));
		jPanel5Layout.setVerticalGroup(jPanel5Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				jPanel5Layout
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(jCheckBox2)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		jPanel10.setLayout(new java.awt.GridBagLayout());

		jList2.setModel(new javax.swing.AbstractListModel() {
			private String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };

			public int getSize() {
				return strings.length;
			}

			public Object getElementAt(int i) {
				return strings[i];
			}
		});
		jScrollPane2.setViewportView(jList2);

		jPanel13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102,
				102)));

		jLabel4.setFont(new java.awt.Font("Lucida Grande", 1, 12)); // NOI18N
		jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabel4.setText("Backlog");

		javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
		jPanel13.setLayout(jPanel13Layout);
		jPanel13Layout.setHorizontalGroup(jPanel13Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				jPanel13Layout
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 440,
								Short.MAX_VALUE).addContainerGap()));
		jPanel13Layout.setVerticalGroup(jPanel13Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				jPanel13Layout.createSequentialGroup().addContainerGap().addComponent(jLabel4)
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
		jPanel8.setLayout(jPanel8Layout);
		jPanel8Layout.setHorizontalGroup(jPanel8Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				jPanel8Layout
						.createSequentialGroup()
						.addContainerGap()
						.addGroup(
								jPanel8Layout
										.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
										.addComponent(jPanel13,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE).addComponent(jScrollPane2))
						.addContainerGap()));
		jPanel8Layout.setVerticalGroup(jPanel8Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				jPanel8Layout
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		jPanel10.add(jPanel8, new java.awt.GridBagConstraints());

		jPanel4.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 15, 5));

		jPanel9.setLayout(new java.awt.GridBagLayout());

		jButton3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
		jButton3.setText("<<");
		jButton3.setAlignmentX(0.5F);
		jButton3.setAlignmentY(0.0F);
		jPanel9.add(jButton3, new java.awt.GridBagConstraints());

		jButton4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
		jButton4.setText("<");
		jButton4.setAlignmentX(0.5F);
		jButton4.setAlignmentY(0.0F);
		jPanel9.add(jButton4, new java.awt.GridBagConstraints());

		jButton5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
		jButton5.setText(">");
		jButton5.setAlignmentX(0.5F);
		jButton5.setAlignmentY(0.0F);
		jPanel9.add(jButton5, new java.awt.GridBagConstraints());

		jButton6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
		jButton6.setText(">>");
		jButton6.setAlignmentX(0.5F);
		jButton6.setAlignmentY(0.0F);
		jPanel9.add(jButton6, new java.awt.GridBagConstraints());

		jPanel4.add(jPanel9);

		javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
		jPanel11.setLayout(jPanel11Layout);
		jPanel11Layout.setHorizontalGroup(jPanel11Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				jPanel11Layout
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		jPanel11Layout.setVerticalGroup(jPanel11Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				jPanel11Layout
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addContainerGap()));

		jPanel10.add(jPanel11, new java.awt.GridBagConstraints());

		jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102,
				102)));

		jLabel2.setFont(new java.awt.Font("Lucida Grande", 1, 12)); // NOI18N
		jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabel2.setText("This Game");

		javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
		jPanel7.setLayout(jPanel7Layout);
		jPanel7Layout.setHorizontalGroup(jPanel7Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				jPanel7Layout
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 440,
								Short.MAX_VALUE).addContainerGap()));
		jPanel7Layout.setVerticalGroup(jPanel7Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				jPanel7Layout.createSequentialGroup().addContainerGap().addComponent(jLabel2)
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		jList1.setModel(new javax.swing.AbstractListModel() {
			private String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };

			public int getSize() {
				return strings.length;
			}

			public Object getElementAt(int i) {
				return strings[i];
			}
		});
		jScrollPane1.setViewportView(jList1);

		javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
		jPanel12.setLayout(jPanel12Layout);
		jPanel12Layout.setHorizontalGroup(jPanel12Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				jPanel12Layout
						.createSequentialGroup()
						.addContainerGap()
						.addGroup(
								jPanel12Layout
										.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
										.addComponent(jPanel7,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE).addComponent(jScrollPane1))
						.addContainerGap()));
		jPanel12Layout.setVerticalGroup(jPanel12Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				jPanel12Layout
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		jPanel10.add(jPanel12, new java.awt.GridBagConstraints());

		jPanel14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204,
				204)));

		jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2",
				"Item 3", "Item 4" }));

		jLabel6.setFont(new java.awt.Font("Lucida Grande", 1, 12)); // NOI18N
		jLabel6.setText("Choose a card deck:");

		jPanel16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204,
				204)));

		jLabel8.setText("0, 1, 3, 5, 6, 7, 8, 9");

		javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
		jPanel16.setLayout(jPanel16Layout);
		jPanel16Layout.setHorizontalGroup(jPanel16Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				javax.swing.GroupLayout.Alignment.TRAILING,
				jPanel16Layout.createSequentialGroup()
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(jLabel8).addContainerGap()));
		jPanel16Layout.setVerticalGroup(jPanel16Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				jPanel16Layout.createSequentialGroup().addContainerGap().addComponent(jLabel8)
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
		jPanel14.setLayout(jPanel14Layout);
		jPanel14Layout
				.setHorizontalGroup(jPanel14Layout
						.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								javax.swing.GroupLayout.Alignment.TRAILING,
								jPanel14Layout
										.createSequentialGroup()
										.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addGroup(
												jPanel14Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.TRAILING)
														.addComponent(
																jPanel16,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addGroup(
																jPanel14Layout
																		.createSequentialGroup()
																		.addComponent(jLabel6)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				jComboBox1,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.PREFERRED_SIZE)))
										.addContainerGap()));
		jPanel14Layout.setVerticalGroup(jPanel14Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				jPanel14Layout
						.createSequentialGroup()
						.addContainerGap()
						.addGroup(
								jPanel14Layout
										.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(jComboBox1,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(jLabel6))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.PREFERRED_SIZE).addContainerGap()));

		jPanel15.setBackground(Color.white);
		jPanel15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204,
				204)));

		jLabel7.setText("Logged in as...");

		javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
		jPanel15.setLayout(jPanel15Layout);
		jPanel15Layout.setHorizontalGroup(jPanel15Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				jPanel15Layout.createSequentialGroup().addContainerGap().addComponent(jLabel7)
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		jPanel15Layout.setVerticalGroup(jPanel15Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				javax.swing.GroupLayout.Alignment.TRAILING,
				jPanel15Layout.createSequentialGroup()
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(jLabel7).addContainerGap()));

	}

	private javax.swing.JButton createGameButton;
	private javax.swing.JPanel infoPanel;
	private javax.swing.JButton jButton2;
	private javax.swing.JButton jButton3;
	private javax.swing.JButton jButton4;
	private javax.swing.JButton jButton5;
	private javax.swing.JButton jButton6;
	private javax.swing.JCheckBox jCheckBox1;
	private javax.swing.JCheckBox jCheckBox2;
	private javax.swing.JComboBox jComboBox1;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JLabel jLabel6;
	private javax.swing.JLabel jLabel7;
	private javax.swing.JLabel jLabel8;
	private javax.swing.JList jList1;
	private javax.swing.JList jList2;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel10;
	private javax.swing.JPanel jPanel11;
	private javax.swing.JPanel jPanel12;
	private javax.swing.JPanel jPanel13;
	private javax.swing.JPanel jPanel14;
	private javax.swing.JPanel jPanel15;
	private javax.swing.JPanel jPanel16;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JPanel jPanel3;
	private javax.swing.JPanel jPanel4;
	private javax.swing.JPanel jPanel5;
	private javax.swing.JPanel jPanel6;
	private javax.swing.JPanel jPanel7;
	private javax.swing.JPanel jPanel8;
	private javax.swing.JPanel jPanel9;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JScrollPane jScrollPane2;
	private javax.swing.JTextField jTextField1;
	private javax.swing.JPanel left;
	private javax.swing.JSeparator leftHorizontalSeparator;
	private javax.swing.JSeparator leftHorizontalSeparator1;
	private javax.swing.JTabbedPane mainComponent;
	private javax.swing.JPanel newGameTabPanel;
	private javax.swing.JSplitPane overviewTabSplitPane;
	private javax.swing.JPanel right;
	private ToolbarView toolbarComponent;
	private javax.swing.JTree gameTree;
	private javax.swing.JScrollPane treeScrollPane;
	private javax.swing.JLabel whatIsBody;
	private javax.swing.JLabel whatIsBody1;
	private javax.swing.JLabel whatIsTitle;
	private javax.swing.JLabel whatIsTitle1;
}
