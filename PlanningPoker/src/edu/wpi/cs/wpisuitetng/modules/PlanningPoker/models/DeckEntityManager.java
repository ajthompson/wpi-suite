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

import java.util.logging.Level;
import java.util.logging.Logger;

import edu.wpi.cs.wpisuitetng.Session;
import edu.wpi.cs.wpisuitetng.database.Data;
import edu.wpi.cs.wpisuitetng.exceptions.BadRequestException;
import edu.wpi.cs.wpisuitetng.exceptions.ConflictException;
import edu.wpi.cs.wpisuitetng.exceptions.DatabaseException;
import edu.wpi.cs.wpisuitetng.exceptions.NotFoundException;
import edu.wpi.cs.wpisuitetng.exceptions.WPISuiteException;
import edu.wpi.cs.wpisuitetng.modules.EntityManager;
import edu.wpi.cs.wpisuitetng.modules.Model;
import edu.wpi.cs.wpisuitetng.modules.PlanningPoker.deck.Deck;

/**
 * Description
 * 
 * @author Alec Thompson - ajthompson
 * @version Apr 13, 2014
 */
public class DeckEntityManager implements EntityManager<Deck> {
	/** The class field of Deck */
	Class<Deck> d = Deck.class;
	/** The database */
	Data data;

	private static final Logger logger = Logger
			.getLogger(DeckEntityManager.class.getName());

	/**
	 * Constructor for DeckEntityManager.
	 * @param data Data
	 */
	public DeckEntityManager(Data data) {
		this.data = data;
	}

	/**
	 * Method makeEntity.
	 * @param s Session
	 * @param content String
	
	
	
	
	
	 * @return Deck * @throws BadRequestException * @throws ConflictException * @throws WPISuiteException * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#makeEntity(Session, String) */
	@Override
	public Deck makeEntity(Session s, String content)
			throws BadRequestException, ConflictException, WPISuiteException {
		Deck d;
		d = Deck.fromJSON(content);

		if (getEntity(s, d.getDeckName())[0] == null) {
			save(s, d);
		} else {
			logger.log(Level.WARNING, "Conflict Exception during Deck creation");
			throw new ConflictException(
					"A Deck with the given name already exists.");
		}

		return d;
	}

	/**
	 * Method getEntity.
	 * @param s Session
	 * @param id String
	
	
	
	
	 * @return Deck[] * @throws NotFoundException * @throws WPISuiteException * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#getEntity(Session, String) */
	@Override
	public Deck[] getEntity(Session s, String id) throws NotFoundException,
			WPISuiteException {
		Deck[] m = new Deck[1];
		if (id.equals("")) {
			return getAll(s);
		} else {
			return data.retrieve(d, "deckName", id).toArray(m);
		}
	}

	/**
	 * Method getAll.
	 * @param s Session
	
	
	
	 * @return Deck[] * @throws WPISuiteException * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#getAll(Session) */
	@Override
	public Deck[] getAll(Session s) throws WPISuiteException {
		Deck[] ret = new Deck[1];
		ret = data.retrieveAll(new Deck(null, null)).toArray(ret);
		return ret;
	}

	/**
	 * Method update.
	 * @param s Session
	 * @param content String
	
	
	
	 * @return Deck * @throws WPISuiteException * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#update(Session, String) */
	@Override
	public Deck update(Session s, String content) throws WPISuiteException {
		Deck changes = Deck.fromJSON(content);
		if (true) {
			System.out.println("Started update.");
			deleteEntity(s, changes.getDeckName());
			data.save(changes);
			System.out.println("Finished update.");
			return changes;
		} else {
			return null;
		}
	}

	/**
	 * Method save.
	 * @param s Session
	 * @param model Deck
	
	 * @throws WPISuiteException */
	@Override
	public void save(Session s, Deck model) throws WPISuiteException {
		if (data.save(model)) {
			logger.log(Level.FINE, "Deck Saved: " + model);
			return;
		} else {
			logger.log(Level.WARNING, "Deck Save Faulute!");
			throw new DatabaseException("Save failure for Deck");
		}
	}

	/**
	 * Method deleteEntity.
	 * @param s Session
	 * @param id String
	
	
	
	 * @return boolean * @throws WPISuiteException * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#deleteEntity(Session, String) */
	@Override
	public boolean deleteEntity(Session s, String id) throws WPISuiteException {
		Model m = data.delete(data.retrieve(d, "deckName", id).get(0));
		logger.log(Level.INFO, "DeckEntityManager deleting deck < " + id + " >");
		return (m != null) ? true : false;
	}
	
	/**
	 * Method deleteAll.
	 * @param s Session
	
	 * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#deleteAll(Session) */
	@Override
	public void deleteAll(Session s) {
		logger.log(Level.INFO, "DeckEntityManager involking DeleteAll...");
		data.deleteAll(new Deck(null, null));
	}
	
	/**
	 * Method Count.
	
	
	 * @return int * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#Count() */
	@Override
	public int Count() {
		// TODO pending on get all
		return 0;
	}

	/*
	 * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#advancedGet(edu.wpi.cs.wpisuitetng.Session, java.lang.String[])
	 */
	@Override
	public String advancedGet(Session s, String[] args)
			throws WPISuiteException {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#advancedPut(edu.wpi.cs.wpisuitetng.Session, java.lang.String[], java.lang.String)
	 */
	@Override
	public String advancedPut(Session s, String[] args, String content)
			throws WPISuiteException {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#advancedPost(edu.wpi.cs.wpisuitetng.Session, java.lang.String, java.lang.String)
	 */
	@Override
	public String advancedPost(Session s, String string, String content)
			throws WPISuiteException {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
