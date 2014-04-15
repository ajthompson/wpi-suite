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
package edu.wpi.cs.wpisuitetng.modules.PlanningPoker.mockobjects;

import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.Request;
import edu.wpi.cs.wpisuitetng.network.models.HttpMethod;

/**
 * Description
 * 
 * @author Alec Thompson - ajthompson
 * @version Apr 6, 2014
 */
public class MockNetwork extends Network {
	protected MockRequest lastRequest = null;

	/**
	 * Method makeRequest.
	 * @param path String
	 * @param requestMethod HttpMethod
	
	 * @return Request */
	@Override
	public Request makeRequest(String path, HttpMethod requestMethod) {
		if (requestMethod == null) {
			throw new NullPointerException("requestMethot may not be null");
		}

		lastRequest = new MockRequest(defaultNetworkConfiguration, path,
				requestMethod);

		return lastRequest;
	}

	/**
	
	 * @return the last MockRequest made */
	public MockRequest getLastRequest() {
		return lastRequest;
	}
}
