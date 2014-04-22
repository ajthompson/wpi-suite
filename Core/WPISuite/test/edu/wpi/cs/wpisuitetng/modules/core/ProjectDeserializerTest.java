/*******************************************************************************
 * Copyright (c) 2012 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * 			bgaffey
 *******************************************************************************/

package edu.wpi.cs.wpisuitetng.modules.core;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;

import edu.wpi.cs.wpisuitetng.modules.core.models.Project;
import edu.wpi.cs.wpisuitetng.modules.core.models.ProjectDeserializer;
import edu.wpi.cs.wpisuitetng.modules.core.models.User;

/**
 * Tests for the UserDeserializer class.
 * 	parsePassword() Tests: evaluating the flexibility of the function to handle JSON formatting.
 * @author bgaffey
 *
 */
public class ProjectDeserializerTest {
	
	GsonBuilder gson;
	
	@Before
	public void setUp()
	{
		this.gson = new GsonBuilder();
		this.gson.registerTypeAdapter(Project.class, new ProjectDeserializer());
	}
	
	
	@Test
	/**
	 * Tests deserializing when the given JSON string has all attributes.
	 */
	public void deserializeProjectFull()
	{
		String jsonProject ="{\"name\":\"TestProj\", \"idNum\":\"1\"}";
		Gson deserializer = this.gson.create();
		
		Project inflated = deserializer.fromJson(jsonProject, Project.class);
		
		assertTrue(inflated.getName().equals("TestProj"));
		assertTrue(inflated.getIdNum().equals("1"));
	}
	
	@Test
	/**
	 * Tests User deserialization when the given string is missing fields (but has the unique identifier idNum)
	 */
	public void deserializeProjectMissingFields()
	{
		String jsonProject ="{\"name\":\"\", \"idNum\":\"2\"}";
		Gson deserializer = this.gson.create();
		
		Project inflated = deserializer.fromJson(jsonProject, Project.class);
		
		assertTrue(inflated.getIdNum().equals("2"));
		assertTrue(inflated.getName().equals(""));
	}
	
	@Test(expected=JsonParseException.class)
	/**
	 * Tests error handling in the deserializer -- an exception should be thrown if
	 * 	the user fails to include the Unique Identifier field (User->idNum)
	 */
	public void deserializeProjectMissingId()
	{
		String jsonProject ="{\"name\":\"Tester\"}";
		Gson deserializer = this.gson.create();
		
		Project inflated = deserializer.fromJson(jsonProject, Project.class); // exception expected.
		
		fail("exception not thrown");
	}
	
	@Test
	public void testProjectFromJson() {
		// A full project json
		String json = "{\"name\":\"calendar\",\"idNum\":\"proj3\",\"owner\":{\"idNum\":7,\"username\":\"jbond\",\"name\":\"James Bond\",\"role\":\"USER\",\"email\":\"jbond@test.com\"},\"supportedModules\":[\"defecttracker\",\"postboard\"],\"team\":[{\"idNum\":7,\"username\":\"jbond\",\"name\":\"James Bond\",\"role\":\"USER\",\"email\":\"jbond@test.com\"},{\"idNum\":2,\"username\":\"mpenny\",\"name\":\"Money Penny\",\"role\":\"USER\",\"email\":\"mpenny@test.com\"},{\"idNum\":1,\"username\":\"q\",\"name\":\"Q\",\"role\":\"USER\",\"email\":\"whatup\"}]}";
		Project test = Project.fromJSON(json);
		// No supportedModules
		String json2 = "{\"name\":\"calendar\",\"idNum\":\"proj3\",\"owner\":{\"idNum\":7,\"username\":\"jbond\",\"name\":\"James Bond\",\"role\":\"USER\",\"email\":\"jbond@test.com\"},\"team\":[{\"idNum\":7,\"username\":\"jbond\",\"name\":\"James Bond\",\"role\":\"USER\",\"email\":\"jbond@test.com\"},{\"idNum\":2,\"username\":\"mpenny\",\"name\":\"Money Penny\",\"role\":\"USER\",\"email\":\"mpenny@test.com\"},{\"idNum\":1,\"username\":\"q\",\"name\":\"Q\",\"role\":\"USER\",\"email\":\"whatup\"}]}";
		Project test2 = Project.fromJSON(json2);
		// No team or supportedModules
		String json3 = "{\"name\":\"calendar\",\"idNum\":\"proj3\",\"owner\":{\"idNum\":7,\"username\":\"jbond\",\"name\":\"James Bond\",\"role\":\"USER\",\"email\":\"jbond@test.com\"}}";
		Project test3 = Project.fromJSON(json2);

		// test comparisons
		assertTrue(test.getName().equals("calendar"));
		assertTrue(test.getIdNum().equals("proj3"));
		assertTrue(test.getOwner().getUsername().equals("jbond"));
		assertTrue(test.getOwner().getName().equals("James Bond"));
		assertEquals(7, test.getOwner().getIdNum());

		/**
		 * Check if each user field is not null. Throws null pointer exception
		 * if the user itself is null
		 */
		for (User u : test.getTeam()) {
			assertNotEquals(null, u.getName());
			assertNotEquals(null, u.getUsername());
		}
	}
}
