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

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map.Entry;

/**
 * @author Legion
 *
 */
public class Statistics {
	public static double mean(double[] m) {
	    double sum = 0;
	    for (int i = 0; i < m.length; i++) {
	        sum += m[i];
	    }
	    return sum / m.length;
	}
	
	public static double median(double[] m) {
		Arrays.sort(m);
	    int middle = m.length/2;
	    if (m.length%2 == 1) {
	        return m[middle];
	    } else {
	        return (m[middle-1] + m[middle]) / 2.0;
	    }
	}
	
	public static double mode(double a[]) {
	    int maxCount = 0;
	    double  maxValue = 0;
	    
	    for (int i = 0; i < a.length; ++i) {
	        int count = 0;
	        for (int j = 0; j < a.length; ++j) {
	            if (a[j] == a[i]) ++count;
	        }
	        if (count > maxCount) {
	            maxCount = count;
	            maxValue = a[i];
	        }
	    }

	    return maxValue;
	}
	
	public static double StdDev(double[] m) {
		double avg = mean(m);
		double variance = 0;
		for (int i=0; i<m.length; i++)
		{
		    variance = variance + Math.pow(m[i] - avg, 2);
		}
		return Math.sqrt(variance/(m.length-1));
	}
	
	public static double max(double[] m) {
		double max = m[0];
		for(int i = 0; i<m.length; i++) {
			if(m[i] > max) {
				max = m[i];
			}
		}
		return max;
	}
	
	public static double min(double[] m) {
		double min = m[0];
		for(int i = 0; i<m.length; i++) {
			if(m[i] < min) {
				min = m[i];
			}
		}
		return min;
	}
}
