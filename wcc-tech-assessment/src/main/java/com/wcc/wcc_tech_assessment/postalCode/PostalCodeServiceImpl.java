package com.wcc.wcc_tech_assessment.postalCode;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.stereotype.Service;

import com.sun.net.httpserver.HttpExchange;

@Service 
public class PostalCodeServiceImpl {
	private static final double EARTH_RADIUS = 6371; // Radius in kilometers
    private static final Logger logger = LoggerFactory.getLogger(PostalCodeServiceImpl.class);
    private static final Marker CODE_OUTPUT_MARKER = MarkerFactory.getMarker("APILOG");
	private Map<String, PostalCodeModel> postalCodeMap = new HashMap<>();
	
	    
	public PostalCodeServiceImpl() {
		 loadPostalCodes("D:\\wcc-tech-assessment\\wcc-tech-assessment\\ukpostcodes.csv");
	}

	
    public PostalCodeResponse callCalculateDistance(String postalCode1 , String postalCode2) {
    	PostalCodeResponse response = new PostalCodeResponse();
    	Double calculatedDistance;

    		List<Double> locationResult = locationLookup(postalCode1,postalCode2);
	    	calculatedDistance = calculateDistance(locationResult.get(0),locationResult.get(1),locationResult.get(2),locationResult.get(3));
			response = new PostalCodeResponse(postalCode1,postalCode2,locationResult.get(0),locationResult.get(1),locationResult.get(2),locationResult.get(3),calculatedDistance,"km");
	    	logger.info(CODE_OUTPUT_MARKER,"===================================================================================");
	    	logger.info(CODE_OUTPUT_MARKER,"Request - PostalCodes1: "+postalCode1+", PostalCodes2: "+postalCode2);
	    	logger.info(CODE_OUTPUT_MARKER,"Response - " + response);
	    	logger.info(CODE_OUTPUT_MARKER,"===================================================================================");

    	return response;
    }

    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
	        double lat1Radians = Math.toRadians(lat1);
	        double lat2Radians = Math.toRadians(lat2);
	        double lon1Radians = Math.toRadians(lon1);
	        double lon2Radians = Math.toRadians(lon2);

	        double a = haversine(lat1Radians, lat2Radians) + Math.cos(lat1Radians) * Math.cos(lat2Radians)
	                * haversine(lon1Radians, lon2Radians);
	        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
	        return (EARTH_RADIUS * c);
	    }

    private double haversine(double deg1, double deg2) {
	        return square(Math.sin((deg1 - deg2) / 2.0));
	    }

    private double square(double x) {
	        return x * x;
	    }

    private List<Double> locationLookup(String postcode1,String postcode2){
		List<Double> latitudeLongitude = new ArrayList<Double>();
    	if(postalCodeMap.containsKey(postcode1) && postalCodeMap.containsKey(postcode2)) {
    		latitudeLongitude.add(postalCodeMap.get(postcode1).getLatitude());
    		latitudeLongitude.add(postalCodeMap.get(postcode1).getLongitude());
    		latitudeLongitude.add(postalCodeMap.get(postcode2).getLatitude());
    		latitudeLongitude.add(postalCodeMap.get(postcode2).getLongitude());
    	}	    	
    	return latitudeLongitude;
    }
    
    private void loadPostalCodes(String csvFilePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line;

            // skip the first line if it contains headers
            boolean isFirstLine = true;
            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue; // skip the header line
                }

                String[] values = line.split(",");
                String postcode = values[1].trim();
                double latitude;
                double longitude;
                
                try {
                    latitude = Double.parseDouble(values[2].trim());
                } catch (ArrayIndexOutOfBoundsException e) {
                	latitude = 0.0; 
                }
                
                try {
                    longitude = Double.parseDouble(values[3].trim());
                } catch (ArrayIndexOutOfBoundsException e) {
                	longitude = 0.0;
                }
              
                postalCodeMap.put(postcode, new PostalCodeModel(postcode, latitude, longitude));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.err.println("Error parsing number: " + e.getMessage());
        }
    }

}
