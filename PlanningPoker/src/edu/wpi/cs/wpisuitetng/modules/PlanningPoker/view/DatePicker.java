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
/**
 * @author friscis
 * 
 * Allows an instance of a calendar that is not a pop-up to be added to any JPanel
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * @author Miguel (Code), Christian (Comments)
 *
 * This class contains an interactive calendar to select when a Planning Poker session is to begin and end.
 * @version $Revision: 1.0 $
 */

public class DatePicker {
	int month = java.util.Calendar.getInstance().get(java.util.Calendar.MONTH);
	int year = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
	JLabel l = new JLabel("", JLabel.CENTER);
	String day = "";
	JButton[] button = new JButton[49];
	JPanel p1, p2, top;
	JFrame f;
	JTextField txt;
	
	/**
	 * Sets up a blank calendar from today's current date.
	 * Sets up action listeners that for a user to click one of the dates, and then displays it.
	 *  Advances calendar pages by months with user input.
	 * @param box The panel on which the calendar is displayed
	 * @param c The set of constraints that define the layout of the calendar in a grid
	 * @param text The text field for the date.
	 */
	
	public DatePicker(JPanel box, GridBagConstraints c, JTextField text) {
		
		top = box;
		txt = text;
		String[] header = { "SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT" }; // shorter version

		p1 = new JPanel(new GridLayout(7, 7));
		p1.setPreferredSize(new Dimension(430, 400));

		for (int x = 0; x < button.length; x++) {
			final int selection = x;
			button[x] = new JButton();
			button[x].setMargin(new Insets(0, 0, 0, 0));
			button[x].setFocusPainted(false);
			button[x].setBackground(Color.white);
			if (x > 6)
				button[x].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent ae) {
						day = button[selection].getActionCommand();
						if(!day.equals(""))
								txt.setText(formatPickedDate());
						}
				});
			if (x < 7) {
				button[x].setFont(new Font("Default", Font.PLAIN, 14));
				button[x].setText(header[x]);
				button[x].setForeground(Color.red);
			}
			p1.add(button[x]);
		}
		p2 = new JPanel(new GridLayout(1, 3));
		p1.setPreferredSize(new Dimension(430, 60));
		JButton previous = new JButton("<< Previous");
		previous.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				month--;
				displayDate();
			}
		});
		p2.add(previous);
		p2.add(l);
		JButton next = new JButton("Next >>");
		next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				month++;
				displayDate();
			}
		});
		p2.add(next);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 3;
		c.gridx = 0;
		c.gridy = 0;
		top.add(p2, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 3;
		c.gridx = 0;
		c.gridy = 1;
		c.weighty = 1;
		top.add(p1, c);
		displayDate();
	}
	/*
	 * 
	 */

	/**
	 * Displays a date on-screen corresponding to the date the user has selected. 
	 * Ensures that the date of the planning poker session is in the future.
	 */
	
	public void displayDate() {
		for (int x = 7; x < button.length; x++) {
			button[x].setText("");
			button[x].setEnabled(false);
		}
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
				"MMMM yyyy");
		java.util.Calendar cal = java.util.Calendar.getInstance();
		java.util.Calendar current = java.util.Calendar.getInstance();
		cal.set(year, month, 1);
		int dayOfWeek = cal.get(java.util.Calendar.DAY_OF_WEEK);
		int daysInMonth = cal.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);
		for (int x = 6 + dayOfWeek, day = 1; day <= daysInMonth; x ++, day++) {
			button[x].setFont(new Font("Default", Font.PLAIN, 14));
			cal.set(year,  month, day);
			if (cal.before(current))
				button[x].setEnabled(false);
			else
				button[x].setEnabled(true);
			button[x].setText("" + day);
			if(day == daysInMonth) {
				for(int y = x+1; y < button.length; y++) {
					button[y].setEnabled(false);
				}
			}
		}
		l.setText(sdf.format(cal.getTime()));
	}
	/**
	 * Formats the user picked date as Day/Month/Year with the time.
	
	 * @return String */

	public String formatPickedDate() {
		if (day.equals(""))
			return day;
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
				"dd-MM-yyyy");
		java.util.Calendar cal = java.util.Calendar.getInstance();
		cal.set(year, month, Integer.parseInt(day)); 
		return sdf.format(cal.getTime());
	}
	
	public void close() {
		top.remove(p1);
		top.remove(p2);
		top.repaint();
	}
}