/**
 * 
 */
package com.infinity.glass.manager;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.SecurityContext;

import com.infinity.glass.model.UserIdentity;

/**
 * @author kbaumer
 *
 */
public class UserIdentityManager {
	
	public UserIdentity getUserIdentity(HttpServletRequest req) {
		final UserPersistence up = ManagerFactory.getUserPersistence();
		HttpSession session = req.getSession();
		UserIdentity ui = (UserIdentity)session.getAttribute("glass.user.identity");
		if(ui == null) {
			if(req.isUserInRole("GUEST")) {
				ui = createGuestIdentity();
				session.setAttribute("glass.user.identity", ui);
			} else {
				ui = up.retrieve(req.getUserPrincipal().getName());
				session.setAttribute("glass.user.identity", ui);
			}
		}
		return ui;
	}
	
	private UserIdentity createGuestIdentity() {
		UserIdentity result = new UserIdentity();
		result.setUserId("Guest" + String.valueOf(System.currentTimeMillis()));
		result.setUserName("guest");
		result.setUserName("Guest User");
		result.setGuest(true);
		result.setCreated(new Date());
		result.setLastLogin(new Date());
		return result;
	}

	public UserIdentity getUserIdentity(SecurityContext sc) {
		UserIdentity result = new UserIdentity();
		result.setEmailAddress("j.doe@navy.mil");
		result.setUserName("John Doe");
		result.setUserId(String.valueOf(System.currentTimeMillis()));
		return result;

	}
	
}
