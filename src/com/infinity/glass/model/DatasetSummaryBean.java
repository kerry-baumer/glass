/**
 * 
 */
package com.infinity.glass.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * @author kbaumer
 *
 */
public class DatasetSummaryBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<DatasetBean> personalData = new LinkedList<DatasetBean>();
	private List<DatasetBean> publicData = new LinkedList<DatasetBean>();
	
	/**
	 * 
	 */
	public DatasetSummaryBean() {
		// TODO Auto-generated constructor stub
	}

	public void addPersonalData(final DatasetBean bean) {
		personalData.add(bean);
	}
	
	public List<DatasetBean> getPersonalData() {
		return personalData;
	}

	public void setPersonalData(List<DatasetBean> personalData) {
		this.personalData = personalData;
	}

	public void addPublicData(final DatasetBean bean) {
		publicData.add(bean);
	}
	
	public List<DatasetBean> getPublicData() {
		return publicData;
	}

	public void setPublicData(List<DatasetBean> publicData) {
		this.publicData = publicData;
	}

}
