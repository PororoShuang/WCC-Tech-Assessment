package com.wcc.wcc_tech_assessment.postalCode;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("postalCode")
public class PostalCodeController {
	private final PostalCodeServiceImpl postalCodeServiceImpl;
	private static final Logger logger = LoggerFactory.getLogger(PostalCodeController.class);
	private static final Marker CODE_OUTPUT_MARKER = MarkerFactory.getMarker("APILOG");

	@Autowired
	public PostalCodeController(PostalCodeServiceImpl postalCodeServiceImpl) {
		//autowire
		this.postalCodeServiceImpl = postalCodeServiceImpl;
	}

	//GET
	@GetMapping
	public ResponseEntity<PostalCodeResponse> getPostalCode(@RequestParam String postalCode1,@RequestParam String postalCode2){
		try {
		return new ResponseEntity<>(postalCodeServiceImpl.callCalculateDistance(postalCode1,postalCode2),HttpStatus.OK);
		}catch (IndexOutOfBoundsException exc) { 
			  logger.info(CODE_OUTPUT_MARKER,"GET Postal Code API called failed");
	         throw new ResponseStatusException(HttpStatus.NOT_FOUND, exc.getMessage() , exc); 	       
		  }
	}
	

}
