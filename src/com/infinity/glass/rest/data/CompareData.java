package com.infinity.glass.rest.data;


public class CompareData {

	public enum CompareType {
		LABEL_LABEL, LABEL_NUMERIC, NUMERIC_NUMERIC
	}

	private String title;
	private final String fieldOne;
	private final String fieldTwo;
	private final CompareType compareType;
	private double correlation = 0;
	private final String requestUUID;

	public CompareData(CompareType compareType, String fieldOne, String fieldTwo, String requestUUID) {
		this.compareType = compareType;
		this.fieldOne = fieldOne;
		this.fieldTwo = fieldTwo;
		this.requestUUID = requestUUID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public CompareType getCompareType() {
		return compareType;
	}

	public String getFieldOne() {
		return fieldOne;
	}

	public String getFieldTwo() {
		return fieldTwo;
	}

	public double getCorrelation() {
		return correlation;
	}

	public void setCorrelation(double correlation) {
		this.correlation = correlation;
	}

	public String getRequestUUID() {
		return requestUUID;
	}
	
}
