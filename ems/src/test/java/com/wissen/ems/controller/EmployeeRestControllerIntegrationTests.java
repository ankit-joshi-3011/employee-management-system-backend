package com.wissen.ems.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wissen.ems.common.Constants;
import com.wissen.ems.dto.RegularEmployeeDetailsDTO;

import jakarta.transaction.Transactional;

// Start the full Spring Boot application but with a random port instead of the fixed
// port 8080. Using a random port allows tests to run in parallel without port conflicts.
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Transactional
public class EmployeeRestControllerIntegrationTests {
	private MockMvc mockMvc;

	@Autowired
	public EmployeeRestControllerIntegrationTests(MockMvc mockMvc) {
		this.mockMvc = mockMvc;
	}

	@Test
	public void testCreateRegularEmployeeSuccessfully() throws Exception {
		RegularEmployeeDetailsDTO regularEmployeeDetailsDTO = new RegularEmployeeDetailsDTO();

		String newEmployeeName = "ABC";
		regularEmployeeDetailsDTO.setName(newEmployeeName);

		String newEmployeeJobTitle = "Financial Management Associate";
		regularEmployeeDetailsDTO.setJobTitle(newEmployeeJobTitle);

		int financeDepartmentId = 3;
		regularEmployeeDetailsDTO.setDepartmentId(financeDepartmentId);

		regularEmployeeDetailsDTO.setEmployeeType("REGULAR");

		int financeManagerEmployeeId = 11;
		regularEmployeeDetailsDTO.setManagerId(financeManagerEmployeeId);

		MvcResult result = mockMvc.perform(post(Constants.EMPLOYEE_REST_API_BASE_URI_PATH)
			.contentType(MediaType.APPLICATION_JSON)
			.content(new ObjectMapper().writeValueAsString(regularEmployeeDetailsDTO)))
			.andExpect(status().isCreated())
			.andExpect(header().exists(HttpHeaders.LOCATION))
			.andReturn();

		String locationHeaderValue = result.getResponse().getHeader(HttpHeaders.LOCATION);

		AssertionsForClassTypes.assertThat(locationHeaderValue).contains(Constants.EMPLOYEE_REST_API_BASE_URI_PATH);
	}
}
