/**
 * 
 */
package com.infinity.glass.manager;

/**
 * @author kerry.baumer
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
		return new UserIdentityManager();
	}
	
	public static UserPersistence getUserPersistence() {
		return new FauxUserPersistence();
	}
}
