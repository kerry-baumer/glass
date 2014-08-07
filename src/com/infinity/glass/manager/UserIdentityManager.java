/**
 * 
 */
package com.infinity.glass.manager;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.SecurityContext;

import com.infinity.glass.model.UserIdentity;

/**
 * @author kbaumer
 *
 */
public interface UserIdentityManager {
	UserIdentity getUserIdentity(HttpServletRequest req);
	UserIdentity getUserIdentity(SecurityContext sc);
	
}
