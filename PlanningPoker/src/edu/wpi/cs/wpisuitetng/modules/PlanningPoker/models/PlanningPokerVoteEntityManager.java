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

import java.util.List;
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

/**
 * @author Miguel
 * @version $Revision: 1.0 $
 */
public class PlanningPokerVoteEntityManager implements EntityManager<PlanningPokerVote> {

	Class<PlanningPokerVote> ppg = PlanningPokerVote.class;
	Data data;

	private static final Logger logger = Logger.getLogger(PlanningPokerEntityManager.class.getName());

	/**
	 * Constructor for PlanningPokerVoteEntityManager.
	 * @param data Data
	 */
	public PlanningPokerVoteEntityManager(Data data) {
		this.data = data;
	}
	
	/**
	 * Method makeEntity.
	 * @param s Session
	 * @param content String
	
	
	
	
	
	 * @return PlanningPokerVote * @throws BadRequestException * @throws ConflictException * @throws WPISuiteException * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#makeEntity(Session, String) */
	@Override
	public PlanningPokerVote makeEntity(Session s, String content)
			throws BadRequestException, ConflictException, WPISuiteException {
		PlanningPokerVote p = PlanningPokerVote.fromJSON(content);

		if (getEntity(s, p.getID())[0] == null) {
			save(s, p);
		} else {
			logger.log(Level.WARNING, "Conflict Exception during PlanningPokerVoteModel creation.");
			throw new ConflictException("A PlanningPokerVoteModel with the given ID already exists. Entity String: " + content);
		}

		return p;
	}
	/**
	 * Method getEntity.
	 * @param s Session
	 * @param id String
	
	
	
	
	 * @return PlanningPokerVote[] * @throws NotFoundException * @throws WPISuiteException * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#getEntity(Session, String) */
	@Override
	public PlanningPokerVote[] getEntity(Session s, String id)
			throws NotFoundException, WPISuiteException {
		PlanningPokerVote[] m = new PlanningPokerVote[1];
		if(id.equals(""))
		{
			return getAll(s);
		}
		else
		{
			return data.retrieve(ppg, "id", id).toArray(m);
		}
	}

	/**
	 * Method getAll.
	 * @param s Session
	
	
	
	 * @return PlanningPokerVote[] * @throws WPISuiteException * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#getAll(Session) */
	@Override
	public PlanningPokerVote[] getAll(Session s) throws WPISuiteException {
		PlanningPokerVote[] ret = new PlanningPokerVote[1];
		ret = data.retrieveAll(new PlanningPokerVote(null, null, 0, 0)).toArray(ret);
		return ret;
	}

	/**
	 * Method update.
	 * @param s Session
	 * @param content String
	
	
	
	 * @return PlanningPokerVote * @throws WPISuiteException * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#update(Session, String) */
	@Override
	public PlanningPokerVote update(Session s, String content)
			throws WPISuiteException {
		PlanningPokerVote changes = PlanningPokerVote.fromJSON(content);
		if(changes.gameName != null && changes.userName != null) {
			deleteEntity(s, changes.getID());
			data.save(changes);
			return changes;
		}
		// currently we don't have the ability to deal with updates on more than one entry
		else
			return null;
	}

	/**
	 * Method save.
	 * @param s Session
	 * @param model PlanningPokerVote
	
	 * @throws WPISuiteException */
	@Override
	public void save(Session s, PlanningPokerVote model)
			throws WPISuiteException {
		if(data.save(model))
		{
			logger.log(Level.FINE, "PlanningPokerVoteModel Saved :" + model);

			return ;
		}
		else
		{
			logger.log(Level.WARNING, "PlanningPokerVoteModel Save Failure!");
			throw new DatabaseException("Save failure for PlanningPokerVoteModel.");
		}
	}

	/**
	 * Method deleteEntity.
	 * @param s Session
	 * @param id String
	
	
	
	 * @return boolean * @throws WPISuiteException * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#deleteEntity(Session, String) */
	@Override
	public boolean deleteEntity(Session s, String id) throws WPISuiteException {
		List<Model> retrieval = data.retrieve(ppg, "id", id);
		for(Model p : retrieval)
			data.delete(p);
		return true;
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
		data.deleteAll(new PlanningPokerVote(null, null, 0, 0));
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
