/**
 * 
 */
package com.infinity.glass.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;

import com.infinity.glass.manager.ManagerFactory;
import com.infinity.glass.model.DatasetSummaryBean;
import com.infinity.glass.model.UserIdentity;

/**
 * @author kerry.baumer
 *
 */
@Path("/datasets")
public class UserDatasetProvider {

	@GET
	@Produces("application/json")
	@Path("/list")
	public DatasetSummaryBean getDescribeData(final String fieldName, @Context final SecurityContext sc) {
		UserIdentity userId = ManagerFactory.getUserIdentityManager().getUserIdentity(sc);
		return ManagerFactory.getDatasetManager().getDatasetsForUser(userId);
		
	}
	
}
