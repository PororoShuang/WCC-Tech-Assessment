package com.wcc.wcc_tech_assessment.postalCode;

public class PostalCodeModel {
	private String postcode;
    private double latitude;
    private double longitude;

    public PostalCodeModel(String postcode, double latitude, double longitude) {
        this.postcode = postcode;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getPostcode() {
        return postcode;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
