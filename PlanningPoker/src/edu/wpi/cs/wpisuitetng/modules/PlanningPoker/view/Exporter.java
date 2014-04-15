/*******************************************************************************
 * Copyright (c) 2012-2014 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.PlanningPoker.view;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;

import javax.swing.DefaultListModel;

import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.Requirement;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.characteristics.AcceptanceTest;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.characteristics.Note;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.characteristics.Transaction;

/**
 * @author Miguel
 * @version $Revision: 1.0 $
 */
public class Exporter {

	public Exporter() {
		
	}
	
	/**
	 * Exports the input list of selected requirements to a file as a string
	 * @param listOfRequirementsToAdd DefaultListModel<Requirement>
	 * @param filename String
	 */
	public void exportAsString(DefaultListModel<Requirement> listOfRequirementsToAdd, String filename) {
		// Prepare file for exporting
		PrintWriter out = null;
		try {
			// TODO Allow the user to select their file name/directory
			out = new PrintWriter(filename);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return;
		}
		// Iterate over the list of requirements
		for(int i = 0; i < listOfRequirementsToAdd.size(); i++) {
			// Export the current requirement
			out.println("Requirement: " + listOfRequirementsToAdd.get(i));
			out.println("Type: " + listOfRequirementsToAdd.get(i).getType().toString());
			out.println("Priority: " + listOfRequirementsToAdd.get(i).getPriority().toString());
			out.println("");
			out.println("Description:");
			out.println(listOfRequirementsToAdd.get(i).getDescription());
			out.println("");
			out.println("Notes:");
			// Iterate over notes and print all the notes
			LinkedList<Note> notes = listOfRequirementsToAdd.get(i).getNotes().getNotes();
			for(int j = 0; j < notes.size(); j++) {
				out.println(notes.get(j).getMessage());
			}
			out.println("");
			out.println("Transaction History:");
			// Iterate over transaction history and print all the history
			LinkedList<Transaction> history = listOfRequirementsToAdd.get(i).getHistory().getHistory();
			for(int j = 0; j < history.size(); j++) {
				Date date= new Date(history.get(j).getTS());
		        SimpleDateFormat df2 = new SimpleDateFormat("MM/dd/yy");
		        String dateText = df2.format(date);
				out.println(dateText + ": " + history.get(j).getMessage());
			}
			out.println("");
			out.println("Acceptance Tests:");
			// Iterate over transaction history and print all the history
			ArrayList<AcceptanceTest> tests = listOfRequirementsToAdd.get(i).getTests();
			for(int j = 0; j < tests.size(); j++) {
				out.println(tests.get(j).getDescription());
			}
			out.println("");
			out.println("");
		}
		
		// Close the file
		out.close();
	}
	
	/**
	 * Exports the input list of selected requirements to a file as a JSON
	 * @param listOfRequirementsToAdd DefaultListModel<Requirement>
	 * @param filename String
	 */
	public void exportAsJSON(DefaultListModel<Requirement> listOfRequirementsToAdd, String filename) {
		// Prepare file for exporting
		PrintWriter out = null;
		try {
			// TODO Allow the user to select their file name/directory
			out = new PrintWriter(filename);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return;
		}
		
		out.print("[");
		// Iterate over the list of requirements
		for(int i = 0; i < listOfRequirementsToAdd.size(); i++) {
			if(i != 0) {
				out.println(",");
			}
			// Export the current requirement
			out.print(listOfRequirementsToAdd.get(i).toJSON());
		}
		out.println("]");
		
		// Close the file
		out.close();
	}
}
