/**
 * 
 */
package com.infinity.glass.manager;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.UUID;

import com.infinity.glass.model.DatasetBean;
import com.infinity.glass.model.DatasetSummaryBean;
import com.infinity.glass.model.UserIdentity;

/**
 * @author kbaumer
 *
 */
public class FauxDatasetManager implements DatasetManager {

	/**
	 * 
	 */
	public FauxDatasetManager() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see com.infinity.glass.manager.DatasetManager#getDatasetsForUser(java.lang.String)
	 */
	@Override
	public DatasetSummaryBean getDatasetsForUser(final UserIdentity userId) {
		DatasetSummaryBean result = new DatasetSummaryBean();
		
		return result;
	}

	/* (non-Javadoc)
	 * @see com.infinity.glass.manager.DatasetManager#SaveDataset(java.io.OutputStream)
	 */
	@Override
	public DatasetBean SaveDataset(UserIdentity userIdentity, String origFileName, OutputStream stream) {
		
		DatasetBean result = new DatasetBean();
		result.setDatasetId(UUID.randomUUID().toString());
		result.setLastSaved(new Date(System.currentTimeMillis()));
		result.setOriginalFileName(origFileName);
		result.setSize(0);
		result.setUser(userIdentity);
		return result;
	}

	/* (non-Javadoc)
	 * @see com.infinity.glass.manager.DatasetManager#LoadDataset(java.lang.String, java.lang.String, java.io.InputStream)
	 */
	@Override
	public void LoadDataset(String userId, String datasetId, InputStream stream) {
		// TODO Auto-generated method stub

	}

}