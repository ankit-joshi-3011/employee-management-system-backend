package com.wissen.ems.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wissen.ems.common.Constants;
import com.wissen.ems.common.TestUtility;
import com.wissen.ems.dto.DepartmentDetailsDTO;

import jakarta.transaction.Transactional;

// Start the full Spring Boot application but with a random port instead of the fixed
// port 8080. Using a random port allows tests to run in parallel without port conflicts.
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Transactional
public class DepartmentRestControllerIntegrationTests {
	private MockMvc mockMvc;
	private ObjectMapper objectMapper;

	@Autowired
	public DepartmentRestControllerIntegrationTests(MockMvc mockMvc, ObjectMapper objectMapper) {
		this.mockMvc = mockMvc;
		this.objectMapper = objectMapper;
	}

	@Test
	public void testGetAllDepartments() throws Exception {
		MvcResult result = mockMvc.perform(get(Constants.DEPARTMENT_REST_API_BASE_URI_PATH)
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andReturn();

		String jsonResponse = result.getResponse().getContentAsString();
		List<DepartmentDetailsDTO> responseAllDepartmentDetailsDto = objectMapper.readValue(jsonResponse, objectMapper.getTypeFactory().constructCollectionType(List.class, DepartmentDetailsDTO.class));

		TestUtility.assertDepartmentDetailsDtosAreCorrect(responseAllDepartmentDetailsDto);
	}
}
