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
public interface UserIdentityManager {
	UserIdentity getUserIdentity(HttpServletRequest req);
	
}
