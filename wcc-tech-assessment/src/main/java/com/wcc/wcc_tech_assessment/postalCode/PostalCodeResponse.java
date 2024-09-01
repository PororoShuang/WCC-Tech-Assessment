package com.wcc.wcc_tech_assessment.postalCode;

public class PostalCodeResponse {
	private String postcode1;
	private double latitude1;
    private double longitude1;
    private String postcode2;
	private double latitude2;
    private double longitude2;
    private double distance;
    private String unit;
    
    public PostalCodeResponse() {
		
	}

	public PostalCodeResponse(String postcode1, String postcode2, double latitude1, double longitude1,double latitude2, double longitude2, double distance, String unit) {
		this.postcode1 = postcode1;
		this.postcode2 = postcode2;
		this.latitude1 = latitude1;
		this.longitude1 = longitude1;
		this.latitude2 = latitude2;
		this.longitude2 = longitude2;
		this.distance = distance;
		this.unit = "km";
	}
 
    public String getPostcode1() {
		return postcode1;
	}

	public void setPostcode1(String postcode1) {
		this.postcode1 = postcode1;
	}

	public String getPostcode2() {
		return postcode2;
	}

	public void setPostcode2(String postcode2) {
		this.postcode2 = postcode2;
	}

	public double getLatitude1() {
		return latitude1;
	}

	public void setLatitude1(double latitude1) {
		this.latitude1 = latitude1;
	}

	public double getLatitude2() {
		return latitude2;
	}

	public void setLatitude2(double latitude2) {
		this.latitude2 = latitude2;
	}

	public double getLongitude1() {
		return longitude1;
	}

	public void setLongitude1(double longitude1) {
		this.longitude1 = longitude1;
	}

	public double getLongitude2() {
		return longitude2;
	}

	public void setLongitude2(double longitude2) {
		this.longitude2 = longitude2;
	}
	
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	public String getUnit() {
		return unit;
	}

	@Override
	public String toString() {
		return "PostalCodeResponse [postcode1=" + postcode1 + ", latitude1=" + latitude1 + ", longitude1=" + longitude1
				+ ", postcode2=" + postcode2 + ", latitude2=" + latitude2 + ", longitude2=" + longitude2 + ", distance="
				+ distance + ", unit=" + unit + "]";
	}
	
	

}
