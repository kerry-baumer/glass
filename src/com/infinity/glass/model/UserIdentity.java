/**
 * 
 */
package com.infinity.glass.model;

import java.io.Serializable;

/**
 * @author kerry.baumer
 *
 */
public class UserIdentity implements Serializable {

	private static final long serialVersionUID = 1L;

	private String userId;
	private String name;
	private String emailAddress;
	
	/**
	 * 
	 */
	public UserIdentity() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the emailAddress
	 */
	public String getEmailAddress() {
		return emailAddress;
	}

	/**
	 * @param emailAddress the emailAddress to set
	 */
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

}
