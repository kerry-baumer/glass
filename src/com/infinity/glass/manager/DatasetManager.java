/**
 * 
 */
package com.infinity.glass.manager;

import java.io.InputStream;
import java.io.OutputStream;

import com.infinity.glass.model.DatasetBean;
import com.infinity.glass.model.DatasetSummaryBean;
import com.infinity.glass.model.UserIdentity;

/**
 * @author kbaumer
 *
 */
public interface DatasetManager {
	DatasetSummaryBean getDatasetsForUser(final UserIdentity userId);
	DatasetBean SaveDataset(UserIdentity userIdentity, String origFileName, OutputStream stream);
	void LoadDataset(final String userId, final String datasetId, InputStream stream);
}
