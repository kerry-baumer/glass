package com.infinity.glass.rest;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

import com.infinity.glass.rest.data.CompareData;
import com.infinity.glass.rest.data.DataColumn.Type;
import com.infinity.glass.rest.data.DataProvider;
import com.infinity.glass.rest.data.DoubleDataColumn;
import com.infinity.glass.rest.data.LabelLabelCompareData;
import com.infinity.glass.rest.data.LabelNumericCompareData;
import com.infinity.glass.rest.data.MatrixData;
import com.infinity.glass.rest.data.NumericNumericCompareData;
import com.infinity.glass.rest.data.StringDataColumn;

/**
 * This provider is the entry into comparing two metrics.  It will determine what types of columns are being compared
 * and give back the correct types of comparisons.
 * @author Jeffrey.Richley
 */
@Path("/compare")
public class CompareProvider extends GlassDataProvider<CompareData> {
	
//	private final CacheManager cacheManager = new HomeDirCacheManager();

	/**
	 * Create the comparison data for the two given columns.  This determines
	 * what types of columns are represented and what types of comparisons should
	 * be returned.
	 * @param firstColumnName The name of the first column to compare
	 * @param secondColumnName The name of the second column to compare
	 * @param context Information about the running environment
	 * @return Some version of the comparison data depending on what types of columns are requested
	 */
	@GET
	@Produces("application/json")
	@Path("/{firstColumnName}/{secondColumnName}/{uuid}")
	public CompareData getCompareData(@PathParam("firstColumnName")final String firstColumnName, 
										@PathParam("secondColumnName")final String secondColumnName, 
										@PathParam("uuid")final String uuid,
										@Context ServletContext context) {
		
		String id = "compare-info-"+firstColumnName+"-"+secondColumnName+"-";
		
//		CompareData data = getCachedData(firstColumnName, secondColumnName);
		CompareData data = null;
		
		if (data == null) {
			MatrixData matrix = new DataProvider().getMatrixData(context);
			
			Type firstType = matrix.getDataColumn(firstColumnName).getType();
			Type secondType = matrix.getDataColumn(secondColumnName).getType();
			
//			if (data == null && !(firstType == Type.NUMERIC && secondType == Type.NUMERIC)) {
//				data = getCachedData(secondColumnName, firstColumnName);
//				if (data != null) {
//					return data;
//				}
//			}

			if (firstType == Type.LABEL && secondType == Type.LABEL) {
				StringDataColumn firstColumn = (StringDataColumn) matrix.getDataColumn(firstColumnName);
				StringDataColumn secondColumn = (StringDataColumn) matrix.getDataColumn(secondColumnName);
				LabelLabelCompareData tmpData = new LabelLabelComparer().compare(firstColumn, secondColumn, uuid);
//				cacheManager.cache("LABEL_LABEL-"+getCacheId(id), new Gson().toJson(tmpData));
				data = tmpData;
			} else if (firstType == Type.LABEL && secondType == Type.NUMERIC) {
				StringDataColumn labelColumn = (StringDataColumn) matrix.getDataColumn(firstColumnName);
				DoubleDataColumn numericColumn = (DoubleDataColumn) matrix.getDataColumn(secondColumnName);
				LabelNumericCompareData tmpData = new LabelNumericComparer().compare(labelColumn, numericColumn, uuid);
//				cacheManager.cache("LABEL_NUMERIC-"+getCacheId(id), new Gson().toJson(tmpData));
				data = tmpData;
			} else if (firstType == Type.NUMERIC && secondType == Type.LABEL) {
				StringDataColumn labelColumn = (StringDataColumn) matrix.getDataColumn(secondColumnName);
				DoubleDataColumn numericColumn = (DoubleDataColumn) matrix.getDataColumn(firstColumnName);
				LabelNumericCompareData tmpData = new LabelNumericComparer().compare(labelColumn, numericColumn, uuid);
				String tmpId = "compare-info-"+secondColumnName+"-"+firstColumnName+"-";
//				cacheManager.cache("LABEL_NUMERIC-"+getCacheId(tmpId), new Gson().toJson(tmpData));
				data = tmpData;
			} else if (firstType == Type.NUMERIC && secondType == Type.NUMERIC) {
				DoubleDataColumn firstColumn = (DoubleDataColumn) matrix.getDataColumn(firstColumnName);
				DoubleDataColumn secondColumn = (DoubleDataColumn) matrix.getDataColumn(secondColumnName);
				NumericNumericCompareData tmpData = new NumericNumericComparer().compare(firstColumn, secondColumn, uuid);
//				cacheManager.cache("NUMERIC_NUMERIC-"+getCacheId(id), new Gson().toJson(tmpData));
				data = tmpData;
			} else {
				throw new IllegalStateException("Unable to compute metrics for " + firstColumnName + " and " + secondColumnName);
			}
		}
		
		return data;
	}

//	private CompareData getCachedData(String firstColumnName, String secondColumnName) {
//		String id = "compare-info-"+firstColumnName+"-"+secondColumnName+"-";
//		CompareData data = getCachedConfig("LABEL_LABEL-"+id, LabelLabelCompareData.class);
//		if (data == null) {
//			data = getCachedConfig("LABEL_NUMERIC-"+id, LabelNumericCompareData.class);
//		}
//		if (data == null) {
//			data = getCachedConfig("NUMERIC_NUMERIC-"+id, NumericNumericCompareData.class);
//		}
//		return data;
//	}
	
//	protected CompareData getCachedConfig(String id, Class<? extends CompareData> clazz) {
//		String cacheId = getCacheId(id);
//		String cachedData = cacheManager.getData(cacheId);
//		
//		CompareData answer = null;
//		if (cachedData != null) {
//			answer = new Gson().fromJson(cachedData, clazz);
//		}
//		
//		return answer;
//	}
	
}
