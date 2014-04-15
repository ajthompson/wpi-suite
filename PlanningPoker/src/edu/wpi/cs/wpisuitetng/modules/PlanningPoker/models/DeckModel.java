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
package edu.wpi.cs.wpisuitetng.modules.PlanningPoker.models;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.AbstractListModel;

import edu.wpi.cs.wpisuitetng.modules.PlanningPoker.controller.AddDeckController;
import edu.wpi.cs.wpisuitetng.modules.PlanningPoker.deck.Deck;

/**
 * List of Decks pulled from the server.
 * 
 * @author Alec Thompson - ajthompson
 * @version Apr 10, 2014
 */
public class DeckModel extends AbstractListModel<Deck> {
	/** The list in which all the Decks for a single project are contained */
	private List<Deck> Decks;
	/** The singleton instance of the DeckModel */
	private static DeckModel instance;

	/**
	 * Retrieves the singleton instance of the DeckModel, or creates it if it
	 * does not yet exist.
	 * 
	
	 * @return the singleton instance of the DeckModel */
	public static DeckModel getInstance() {
		if (instance == null)
			instance = new DeckModel();
		return instance;
	}

	/** Constructs an empty list of Decks for the project */
	private DeckModel() {
		Decks = new ArrayList<Deck>();
	}

	/**
	 * Adds a single Deck to the list of decks for the project, and add the Deck
	 * to the database.
	 * 
	 * @param newDeck
	 *            the new Deck to be added.
	 */
	public void addDeck(Deck newDeck) {
		Decks.add(newDeck);
		AddDeckController.getInstance().addDeck(newDeck);
	}

	/**
	 * Gets the deck with the given name
	 * 
	 * @param name
	 *            the name of the Deck
	
	 * @return the Deck with the given name, or null if the Deck does not exist */
	public Deck getDeck(String name) {
		// iterate through the list of decks until the name is found
		for (Deck d : Decks) {
			if (name.equals(d.getDeckName())) {
				return d;
			}
		}
		return null;
	}

	/**
	 * Removes the Decks with the given name from the list of Decks.
	 * 
	 * @param name
	 *            the name of the Deck to be removed
	 */
	public void removeDeck(String name) {
		// iterate through the list of decks until the name is found
		for (Deck d : Decks) {
			if (name.equals(d.getDeckName())) {
				Decks.remove(d);
				break;
			}
		}
	}

	/**
	 * Provides the number of elements in the list of Decks for the project.
	 * 
	
	
	 * @return the number of Decks in the project * @see javax.swing.ListModel#getSize() * @see javax.swing.ListModel#getSize()
	 */
	public int getSize() {
		return Decks.size();
	}

	/**
	 * Retrieves the Deck from the given index of the list of Decks
	 * 
	 * @param index
	 *            the index from which you want to retrieve a Deck
	 * 
	
	
	 * @return the Deck at the given index * @see javax.swing.ListModel#getElementAt(int) * @see javax.swing.ListModel#getElementAt(int)
	 */
	@Override
	public Deck getElementAt(int index) {
		return Decks.get(Decks.size() - 1 - index);
	}

	/**
	 * Removes all Users from this model
	 * 
	 * NOTE: One does not simply walk into Mor- I mean construct a new instance
	 * of the model. Other classes reference it, so we must manually remove each
	 * User from the model.
	 */
	public void emptyModel() {
		int oldSize = getSize();
		Iterator<Deck> iterator = Decks.iterator();
		while (iterator.hasNext()) {
			iterator.next();
			iterator.remove();
		}
		this.fireIntervalRemoved(this, 0, Math.max(oldSize - 1, 0));
	}

	/**
	 * Adds an array of Decks to the list of decks for the project, and add the
	 * Decks to the database.
	 * 
	 * @param newDecks
	 *            the array of new Decks to be added.
	 */
	public void addDeck(Deck[] newDecks) {
		for (Deck d : newDecks) {
			this.addDeck(d);
		}
		this.fireIntervalAdded(this, 0, Math.max(getSize() - 1, 0));
	}

	/**
	 * Returns the list of Decks
	 * 
	
	 * @return the Decks held within the DeckModel */
	public List<Deck> getDecks() {
		return Decks;
	}
}
