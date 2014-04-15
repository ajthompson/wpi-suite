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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.AbstractListModel;

import edu.wpi.cs.wpisuitetng.modules.core.models.User;

/**
 * List of users pulled from the server.
 * 
 * Add functions only add users to the local instance, as the users already
 * exist on the server
 * 
 * @author Alec Thompson
 * 
 * @version $Revision: 1.0 $
 */
public class UserModel extends AbstractListModel<User> {
	/**
	 * The list in which all the Users for a single project are contained
	 */
	private List<User> Users;

	/**
	 * The static object to allow the user model to exist
	 */
	private static UserModel instance;

	/**
	 * Constructs an empty list of users for the project
	 */
	private UserModel() {
		Users = new ArrayList<User>();
	}

	/**
	
	 * @return the instance of the User model singleton */
	public static UserModel getInstance() {
		if (instance == null) {
			instance = new UserModel();
		}

		return instance;
	}

	/**
	 * Adds a single User to the list of users for the project
	 * 
	 * @param newUser
	 *            The User to be added to the list of Users in the project
	 */
	public void addUser(User newUser) {
		Users.add(newUser);
	}

	/**
	 * Gets the User with the given ID
	 * 
	 * @param id
	 *            the ID number of the User to be returned
	
	 * @return the User for the ID or null if the User is not found */
	public User getUser(int id) {
		// iterate through the list of Users until id is found
		for (int i = 0; i < this.Users.size(); i++) {
			if (Users.get(i).getIdNum() == id)
				return Users.get(i);
		}
		return null;
	}

	/**
	 * Removes the user with the given id
	 * 
	 * @param id
	 *            The id number of the user to be removed from the list of
	 *            users.
	 */
	public void removeUser(int id) {
		// iterate through the list of Users until id is found
		for (int i = 0; i < this.Users.size(); i++) {
			if (Users.get(i).getIdNum() == id) {
				Users.remove(i);
				break;
			}
		}
	}

	/**
	 * Provides the number of elements in the list of Users for the project.
	 * 
	
	
	 * @return the number of Users in the project * @see javax.swing.ListModel#getSize() * @see javax.swing.ListModel#getSize()
	 */
	public int getSize() {
		return Users.size();
	}

	/**
	 * This function takes an index and finds the User in the list of Users for
	 * the project.
	 * 
	
	
	 * @param index int
	 * @return the User associated with the provided index * @see javax.swing.ListModel@getElementAt(int) * @see javax.swing.ListModel#getElementAt(int)
	 */
	public User getElementAt(int index) {
		return Users.get(Users.size() - 1 - index);
	}

	/**
	 * Removes all Users from this model
	 * 
	 * NOTE: Once does not simply construct a new instance of the model. Other
	 * classes reference it, so we must manually remove each User from the
	 * model.
	 */
	public void emptyModel() {
		int oldSize = getSize();
		Iterator<User> iterator = Users.iterator();
		while (iterator.hasNext()) {
			iterator.next();
			iterator.remove();
		}
		this.fireIntervalRemoved(this, 0, Math.max(oldSize - 1, 0));
	}
	
	/**
	 * Adds the given array of users to the list
	 *
	 * @param Users the array of Users to add
	 */
	public void addUsers(User[] Users) {
		for (User u : Users) {
			this.Users.add(u);
		}
		this.fireIntervalAdded(this, 0, Math.max(getSize() - 1, 0));
	}
	
	/**
	 * Returns the list of Users
	 *
	
	 * @return the Users held within the UserModel */
	public List<User> getUsers() {
		return Users;
	}
}
