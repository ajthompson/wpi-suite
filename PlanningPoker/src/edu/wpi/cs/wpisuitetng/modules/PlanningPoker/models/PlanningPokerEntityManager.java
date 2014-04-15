/*******************************************************************************
* Copyright (c) 2012-2014 -- WPI Suite
*
* All rights reserved. This program and the accompanying materials
* are made available under the terms of the Eclipse Public License v1.0
* which accompanies this distribution, and is available at
* http://www.eclipse.org/legal/epl-v10.html
* Contributor: team struct-by-lightning
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

/**
 * @author Miguel
 * @version $Revision: 1.0 $
 */
public class PlanningPokerEntityManager implements EntityManager<PlanningPokerGame> {

	Class<PlanningPokerGame> ppg = PlanningPokerGame.class;
	Data data;

	private static final Logger logger = Logger.getLogger(PlanningPokerEntityManager.class.getName());

	/**
	 * Constructor for PlanningPokerEntityManager.
	 * @param data Data
	 */
	public PlanningPokerEntityManager(Data data) {
		this.data = data;
	}
	
	/**
	 * Method makeEntity.
	 * @param s Session
	 * @param content String
	
	
	
	
	
	 * @return PlanningPokerGame * @throws BadRequestException * @throws ConflictException * @throws WPISuiteException * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#makeEntity(Session, String) */
	@Override
	public PlanningPokerGame makeEntity(Session s, String content)
			throws BadRequestException, ConflictException, WPISuiteException {
		PlanningPokerGame p;
		p = PlanningPokerGame.fromJSON(content);

		if (getEntity(s, p.getID())[0] == null) {
			save(s, p);
		} else {
			logger.log(Level.WARNING, "Conflict Exception during PlanningPokerGame creation.");
			throw new ConflictException("A PlanningPokerGame with the given ID already exists. Entity String: " + content);
		}

		return p;
	}
	/**
	 * Method getEntity.
	 * @param s Session
	 * @param id String
	
	
	
	
	 * @return PlanningPokerGame[] * @throws NotFoundException * @throws WPISuiteException * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#getEntity(Session, String) */
	@Override
	public PlanningPokerGame[] getEntity(Session s, String id)
			throws NotFoundException, WPISuiteException {
		PlanningPokerGame[] m = new PlanningPokerGame[1];
		if(id.equals(""))
		{
			return getAll(s);
		}
		else
		{
			return data.retrieve(ppg, "gameName", id).toArray(m);
		}
	}

	/**
	 * Method getAll.
	 * @param s Session
	
	
	
	 * @return PlanningPokerGame[] * @throws WPISuiteException * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#getAll(Session) */
	@Override
	public PlanningPokerGame[] getAll(Session s) throws WPISuiteException {
		PlanningPokerGame[] ret = new PlanningPokerGame[1];
		ret = data.retrieveAll(new PlanningPokerGame(null, null, null, null, false, false, null, null, null)).toArray(ret);
		return ret;
	}

	/**
	 * Method update.
	 * @param s Session
	 * @param content String
	
	
	
	 * @return PlanningPokerGame * @throws WPISuiteException * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#update(Session, String) */
	@Override
	public PlanningPokerGame update(Session s, String content)
			throws WPISuiteException {
		PlanningPokerGame changes = PlanningPokerGame.fromJSON(content);
		if(true) { //TODO: Partial updates with nulls
			System.out.println("Started update.");
			deleteEntity(s, changes.getID());
			data.save(changes);
			System.out.println("Finished update.");
			return changes;
		}
		// currently we don't have the ability to deal with updates on more than one entry
		else
			return null;
	}

	/**
	 * Method save.
	 * @param s Session
	 * @param model PlanningPokerGame
	
	 * @throws WPISuiteException */
	@Override
	public void save(Session s, PlanningPokerGame model)
			throws WPISuiteException {
		if(data.save(model))
		{
			logger.log(Level.FINE, "PlanningPokerGame Saved :" + model);

			return ;
		}
		else
		{
			logger.log(Level.WARNING, "PlanningPokerGame Save Failure!");
			throw new DatabaseException("Save failure for PlanningPokerGame.");
		}
	}

	/**
	 * Method deleteEntity.
	 * @param s Session
	 * @param id String
	
	
	
	 * @return boolean * @throws WPISuiteException * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#deleteEntity(Session, String) */
	@Override
	public boolean deleteEntity(Session s, String id) throws WPISuiteException {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Method advancedGet.
	 * @param s Session
	 * @param args String[]
	
	
	
	 * @return String * @throws WPISuiteException * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#advancedGet(Session, String[]) */
	@Override
	public String advancedGet(Session s, String[] args)
			throws WPISuiteException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Method deleteAll.
	 * @param s Session
	
	
	 * @throws WPISuiteException * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#deleteAll(Session) */
	@Override
	public void deleteAll(Session s) throws WPISuiteException {
		logger.log(Level.INFO, "PlanningPokerEntityManager invoking DeleteAll...");
		data.deleteAll(new PlanningPokerGame(null, null, null, null, false, false, null, null, null));
	}

	/**
	 * Method Count.
	
	
	
	 * @return int * @throws WPISuiteException * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#Count() */
	@Override
	public int Count() throws WPISuiteException {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * Method advancedPut.
	 * @param s Session
	 * @param args String[]
	 * @param content String
	
	
	
	 * @return String * @throws WPISuiteException * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#advancedPut(Session, String[], String) */
	@Override
	public String advancedPut(Session s, String[] args, String content)
			throws WPISuiteException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Method advancedPost.
	 * @param s Session
	 * @param string String
	 * @param content String
	
	
	
	 * @return String * @throws WPISuiteException * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#advancedPost(Session, String, String) */
	@Override
	public String advancedPost(Session s, String string, String content)
			throws WPISuiteException {
		// TODO Auto-generated method stub
		return null;
	}

}
