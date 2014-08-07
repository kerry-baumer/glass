/**
 * 
 */
package com.infinity.glass.manager;

import javax.servlet.http.HttpServletRequest;

import com.infinity.glass.model.UserIdentity;

/**
 * @author kbaumer
 *
 */
public class FauxUserIdentityManager implements UserIdentityManager {

	/**
	 * 
	 */
	public FauxUserIdentityManager() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public UserIdentity getUserIdentity(HttpServletRequest req) {
		UserIdentity result = new UserIdentity();
		result.setEmailAddress("j.doe@navy.mil");
		result.setName("John Doe");
		result.setUserId(String.valueOf(System.currentTimeMillis()));
		return result;
	}

}
