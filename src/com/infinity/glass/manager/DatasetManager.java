/**
 * 
 */
package com.infinity.glass.manager;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.infinity.glass.model.UserDatasetBean;
import com.infinity.glass.model.DatasetSummaryBean;
import com.infinity.glass.model.UserIdentity;

/**
 * @author kbaumer
 *
 */
public interface DatasetManager {
	DatasetSummaryBean getDatasetsForUser(final UserIdentity userId);
	UserDatasetBean saveDataset(UserIdentity userIdentity, String origFileName, InputStream stream) throws IOException;
	void LoadDataset(final String userId, final String datasetId, OutputStream stream) throws IOException;
}