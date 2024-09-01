package com.wcc.wcc_tech_assessment;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.wcc.wcc_tech_assessment.postalCode.PostalCodeController;
import com.wcc.wcc_tech_assessment.postalCode.PostalCodeResponse;
import com.wcc.wcc_tech_assessment.postalCode.PostalCodeServiceImpl;

//Test controller layer only
@WebMvcTest(PostalCodeController.class)

public class TestPostalCodeController {
	@Autowired
	private MockMvc mockMvc;
	
	//mock PostalCodeServiceImpl dependency
	@MockBean
	private PostalCodeServiceImpl postalCodeServiceImpl;
	
	@Test
	public void testGetPostalCode() throws Exception{
			//mock response object with predefined expected results
		   PostalCodeResponse response = new PostalCodeResponse("AB10 1XG", "AB10 6RN", 57.144165, -2.114848, 57.13788, -2.121487, 0.8055046803242125, "km");
		   //mock dependency, after called will return response
	        when(postalCodeServiceImpl.callCalculateDistance("AB10 1XG", "AB10 6RN")).thenReturn(response);

	        //mockMvc test HTTP request
	        mockMvc.perform(get("/postalCode")
	                .param("postalCode1", "AB10 1XG") 
	                .param("postalCode2", "AB10 6RN"))
	                .andExpect(status().isOk())
	                .andExpect(jsonPath("$.postcode1").value("AB10 1XG"))
	                .andExpect(jsonPath("$.postcode2").value("AB10 6RN"))
	                .andExpect(jsonPath("$.latitude1").value(57.144165))
	                .andExpect(jsonPath("$.longitude1").value(-2.114848))
	                .andExpect(jsonPath("$.latitude2").value(57.13788))
	                .andExpect(jsonPath("$.longitude2").value(-2.121487))
	                .andExpect(jsonPath("$.distance").value(0.8055046803242125))
	                .andExpect(jsonPath("$.unit").value("km"));
	    }

	}

