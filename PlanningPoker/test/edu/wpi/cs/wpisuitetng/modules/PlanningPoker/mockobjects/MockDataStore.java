/*******************************************************************************
 * Copyright (c) 2012-2014 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Team Rolling Thunder
 * 				 team struct-by-lightning
 *******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.PlanningPoker.mockobjects;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import edu.wpi.cs.wpisuitetng.database.Data;
import edu.wpi.cs.wpisuitetng.exceptions.WPISuiteException;
import edu.wpi.cs.wpisuitetng.modules.Model;
import edu.wpi.cs.wpisuitetng.modules.core.models.Project;

/**
 * Mock database for testing, utilizing a Set to store all of the contained
 * objects. This approximates the function of the DB4O database used, without
 * requiring input from the networking.
 * 
 * @author Alec Thompson - ajthompson
 * @version Apr 4, 2014
 */
public class MockDataStore implements Data {

	private static MockDataStore instance = null;
	private final Set<Object> objects;

	/**
	 * Static method to create an empty MockDataStore if one does not already
	 * exist, otherwise just return the the instance
	 * 
	
	 * @return the instance of the MockDataStore if it exists, otherwise create
	 *         an empty MockDataStore */
	public static MockDataStore getInstance() {
		if (instance == null)
			instance = new MockDataStore();
		return instance;
	}

	/**
	 * Static method to retrieve the instance of a MockDataStore it it exists.
	 * If it does not, then create a new MockDataStore from the Set of Objects
	 * provided.
	 * 
	 * @param objects
	 *            Set of objects to store in the MockDataStore
	
	 * @return the instance of the MockDataStore */
	public static MockDataStore getInstance(Set<Object> objects) {
		if (instance == null)
			instance = new MockDataStore(objects);
		return instance;
	}

	/**
	 * Creates an empty MockDataStore
	 */
	private MockDataStore() {
		this.objects = null;
	}

	/**
	 * 
	 * Create a MockDataStore from a set of objects to be stored in the database
	 * 
	 * @param objects
	 *            the objects to be initially stored in the data store
	 */
	private MockDataStore(Set<Object> objects) {
		this.objects = objects;
	}

	/**
	 * Add an object to the database
	 * 
	
	 * @param aModel T
	
	 * @return boolean * @see edu.wpi.cs.wpisuitetng.database.Data#save(java.lang.Object) */
	@Override
	public <T> boolean save(T aModel) {
		objects.add(aModel);
		return true;
	}

	/**
	
	 * @param aModel T
	 * @param aProject Project
	
	 * @return boolean * @see edu.wpi.cs.wpisuitetng.database.Data#save(java.lang.Object,
	 *      edu.wpi.cs.wpisuitetng.modules.core.models.Project) */
	@Override
	public <T> boolean save(T aModel, Project aProject) {
		((Model) aModel).setProject(aProject);
		save(aModel);
		return true;
	}

	/**
	 * Return a list of all models that match the class, and value of the given
	 * field name. For example, with the input of User.class(), "name", "Joe", a
	 * list of all users of name Joe would be returned.
	 * 
	 * @param anObjectQueried
	 *            the class of the object to be retrieved
	 * @param aFieldName
	 *            the field name by which the object will be selected
	 * @param theGivenValue
	 *            the value to filter by
	 * 
	
	
	
	 * @return List<Model> * @throws WPISuiteException * @see edu.wpi.cs.wpisuitetng.database.Data#retrieve(java.lang.Class,
	 *      java.lang.String, java.lang.Object) */
	@Override
	public List<Model> retrieve(Class anObjectQueried, String aFieldName,
			Object theGivenValue) throws WPISuiteException {
		List<Model> returnVal = new ArrayList<Model>();
		for (Object o : objects) {
			if (!anObjectQueried.isInstance(o)) {
				continue; // skip the rest if this is not the object you are
							// looking for
			}
			/*
			 * get all of the objects methods and then find the getter for
			 * aFieldName
			 */
			Method[] methods = o.getClass().getMethods();
			for (Method m : methods) {
				if (m.getName().equalsIgnoreCase("get" + aFieldName)) {
					/*
					 * the method name (ignoring the get) matches aFieldName.
					 * AKA, the method is get(aFieldName)
					 */
					try {
						if (m.invoke(o).equals(theGivenValue)) {
							/*
							 * the value retrieved by the getter matches the
							 * given value
							 */
							returnVal.add((Model) o);
						}
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return returnVal;
	}

	/**
	 * @see edu.wpi.cs.wpisuitetng.database.Data#retrieve(java.lang.Class,
	 *      java.lang.String, java.lang.Object,
	 *      edu.wpi.cs.wpisuitetng.modules.core.models.Project)
	 */
	@Override
	public List<Model> retrieve(Class anObjectQueried, String aFieldName,
			Object theGivenValue, Project theProject) throws WPISuiteException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Deletes an object from the MockDataStore and returns it
	 * 
	
	
	 * @param aTNG T
	 * @return the deleted object * @see edu.wpi.cs.wpisuitetng.database.Data#delete(java.lang.Object) * @see edu.wpi.cs.wpisuitetng.database.Data#delete(T)
	 */
	@Override
	public <T> T delete(T aTNG) {
		if (objects.contains(aTNG)) {
			objects.remove(aTNG);
			return aTNG;
		}
		return null;
	}

	/**
	 * @see edu.wpi.cs.wpisuitetng.database.Data#update(java.lang.Class,
	 *      java.lang.String, java.lang.Object, java.lang.String,
	 *      java.lang.Object)
	 */
	@Override
	public void update(Class anObjectToBeModified, String fieldName,
			Object uniqueID, String changeField, Object changeValue)
			throws WPISuiteException {
		// TODO Auto-generated method stub

	}

	/**
	 * Retrieves all of the objects of a given class from a sample object
	 * 
	 * @param aSample
	 *            the object of the same type as those to be retrieved.
	
	
	 * @return List<T> * @see edu.wpi.cs.wpisuitetng.database.Data#retrieveAll(java.lang.Object) */
	@Override
	public <T> List<T> retrieveAll(T aSample) {
		List<T> all = new ArrayList<T>();
		for (Object o : objects) {
			if (aSample.getClass().isInstance(o))
				all.add((T) o);
		}
		return all;
	}

	/**
	
	 * @param aSample T
	 * @param aProject Project
	
	 * @return List<Model> * @see edu.wpi.cs.wpisuitetng.database.Data#retrieveAll(java.lang.Object,
	 *      edu.wpi.cs.wpisuitetng.modules.core.models.Project) */
	@Override
	public <T> List<Model> retrieveAll(T aSample, Project aProject) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Deletes all models of a given class from the MockDataStore
	 * 
	
	
	 * @param aSample T
	 * @return List of all objects deleted * @see edu.wpi.cs.wpisuitetng.database.Data#deleteAll(java.lang.Object) * @see edu.wpi.cs.wpisuitetng.database.Data#deleteAll(T)
	 */
	@Override
	public <T> List<T> deleteAll(T aSample) {
		List<T> deleted = new ArrayList<T>();
		for (Object o : objects) {
			if (aSample.getClass().isInstance(o))
				deleted.add((T) o);
		}
		// can't remove in the loop, otherwise you get an exception
		objects.removeAll(deleted);
		return deleted;
	}

	/**
	
	 * @param aSample T
	 * @param aProject Project
	
	 * @return List<Model> * @see edu.wpi.cs.wpisuitetng.database.Data#deleteAll(java.lang.Object,
	 *      edu.wpi.cs.wpisuitetng.modules.core.models.Project) */
	@Override
	public <T> List<Model> deleteAll(T aSample, Project aProject) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see edu.wpi.cs.wpisuitetng.database.Data#andRetrieve(java.lang.Class,
	 *      java.lang.String[], java.util.List)
	 */
	@Override
	public List<Model> andRetrieve(Class anObjectQueried,
			String[] aFieldNameList, List<Object> theGivenValueList)
			throws WPISuiteException, IllegalArgumentException,
			IllegalAccessException, InvocationTargetException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see edu.wpi.cs.wpisuitetng.database.Data#orRetrieve(java.lang.Class,
	 *      java.lang.String[], java.util.List)
	 */
	@Override
	public List<Model> orRetrieve(Class anObjectQueried,
			String[] aFieldNameList, List<Object> theGivenValueList)
			throws WPISuiteException, IllegalAccessException,
			InvocationTargetException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see edu.wpi.cs.wpisuitetng.database.Data#complexRetrieve(java.lang.Class,
	 *      java.lang.String[], java.util.List, java.lang.Class,
	 *      java.lang.String[], java.util.List)
	 */
	public List<Model> complexRetrieve(Class andanObjectQueried,
			String[] andFieldNameList, List<Object> andGivenValueList,
			Class orAnObjectQueried, String[] orFieldNameList,
			List<Object> orGivenValueList) throws WPISuiteException,
			IllegalArgumentException, IllegalAccessException,
			InvocationTargetException {
		// TODO Auto-generated method stub
		return null;
	}

}
