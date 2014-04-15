/*******************************************************************************
* Copyright (c) 2012-2014 -- WPI Suite
*
* All rights reserved. This program and the accompanying materials
* are made available under the terms of the Eclipse Public License v1.0
* which accompanies this distribution, and is available at
* http://www.eclipse.org/legal/epl-v10.html
* Contributor: team struct-by-lightning
*******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.PlanningPoker.views;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.Test;

import edu.wpi.cs.wpisuitetng.modules.PlanningPoker.views.Statistics;

/**
 * @author friscis, swconley
 *
 */
public class StatisticsTester {
	@Test
	public void testAll1() {
		double[] nums = {1.0, 2.0, 3.0, 4.0, 5.0};
		assertEquals(3.0, Statistics.mean(nums), .001);
		assertEquals(3.0, Statistics.median(nums), .001);
		assertEquals(1.0, Statistics.mode(nums), .001);
		assertEquals(1.581, Statistics.StdDev(nums), .001);
	}
	@Test
	public void testAll2() {
		double[] nums = {48.6, 20.9, 8, 20.9, 11.40, 4.26, 71.45, 85.99, 12};
		assertEquals(31.5, Statistics.mean(nums), .001);
		assertEquals(20.9, Statistics.median(nums), .001);
		assertEquals(20.9, Statistics.mode(nums), .001);
		assertEquals(29.918, Statistics.StdDev(nums), .001);
	}
}
