/**
 * 
 */
package com.infinity.glass.manager;

/**
 * @author kbaumer
 *
 */
public final class ManagerFactory {

	/**
	 * 
	 */
	private ManagerFactory() {
		// TODO Auto-generated constructor stub
	}
	
	public static DatasetManager getDatasetManager() {
		return new FauxDatasetManager();
	}

	public static UserIdentityManager getUserIdentityManager() {
		return new FauxUserIdentityManager();
	}

}
