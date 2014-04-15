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

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

/**
 * This class is a JPanel. It contains the actual post board, a text field
 * for entering a new message, and a submit button for submitting
 * a new message.
 * 
 * 
 * @author Batyrlan Nurbekov
 * @author Sam Mailand
 * @author Zachary Zapatka
 * @author Miguel Mora
 * 
 *
 * @version $Revision: 1.0 $
 */
@SuppressWarnings({"serial", "rawtypes", "unchecked" })
public class StoriesBoardPanel extends JPanel {

    /** A list box to display all the message on the board */
    private final JList lstBoard;

    /** A text field where the user can enter a new message */
    private final JTextField txtNewMessage;

    /** A button for submitting new messages */
    private final JButton btnSubmit;

    /**
     * This is a model for the lstBoard component. Basically it
     * contains the data to be displayed in the list box.
     */
    private final DefaultListModel lstBoardModel;
    private JList list;

    /**
     * Construct the panel, the three components, and add the
     * three components to the panel.
     */
    public StoriesBoardPanel() {

        // Construct the list box model
        lstBoardModel = new DefaultListModel();
        lstBoardModel.add(0, "hello");
        lstBoardModel.add(0, "world");

        // Construct the components to be displayed
        lstBoard = new JList(lstBoardModel);
        txtNewMessage = new JTextField("Enter a message here.");
        btnSubmit = new JButton("Submit");

        // Set the layout manager of this panel that controls the positions of the components
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS)); // components will  be arranged vertically

        // Put the listbox in a scroll pane
        JScrollPane lstScrollPane = new JScrollPane(lstBoard);
        lstScrollPane.setPreferredSize(new Dimension(150, 400));

        // Clear the contents of the text field when the user clicks on it
        txtNewMessage.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                txtNewMessage.setText("");
            }
        });

        // Adjust sizes and alignments
        btnSubmit.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add the components to the panel
        add(Box.createVerticalStrut(20)); // leave a 20 pixel gap
        add(lstScrollPane);
        
        list = new JList();
        lstScrollPane.setRowHeaderView(list);
        add(Box.createVerticalStrut(20));
        add(txtNewMessage);
        add(Box.createVerticalStrut(20));
        add(btnSubmit);
    }
}
