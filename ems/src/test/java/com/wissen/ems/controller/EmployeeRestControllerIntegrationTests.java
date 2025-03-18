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
import com.wissen.ems.dto.UnsupportedEmployeeDetailsDto;

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

	@Test
	public void testCreateEmployeeWithUnsupportedType() throws Exception {
		UnsupportedEmployeeDetailsDto unsupportedEmployeeDetailsDTO = new UnsupportedEmployeeDetailsDto();

		mockMvc.perform(post(Constants.EMPLOYEE_REST_API_BASE_URI_PATH)
			.contentType(MediaType.APPLICATION_JSON)
			.content(new ObjectMapper().writeValueAsString(unsupportedEmployeeDetailsDTO)))
			.andExpect(status().isBadRequest());
	}

	@Test
	public void testCreateRegularEmployeeWithNullName() throws Exception {
		RegularEmployeeDetailsDTO regularEmployeeDetailsDTO = new RegularEmployeeDetailsDTO();

		regularEmployeeDetailsDTO.setName(null);

		MvcResult result = mockMvc.perform(post(Constants.EMPLOYEE_REST_API_BASE_URI_PATH)
			.contentType(MediaType.APPLICATION_JSON)
			.content(new ObjectMapper().writeValueAsString(regularEmployeeDetailsDTO)))
			.andExpect(status().isBadRequest())
			.andReturn();

		String responseBody = result.getResponse().getContentAsString();

		AssertionsForClassTypes.assertThat(responseBody).isEqualTo(Constants.EMPLOYEE_NAME_NULL_OR_EMPTY_EXCEPTION_MESSAGE);
	}

	@Test
	public void testCreateRegularEmployeeWithEmptyJobTitle() throws Exception {
		RegularEmployeeDetailsDTO regularEmployeeDetailsDTO = new RegularEmployeeDetailsDTO();

		regularEmployeeDetailsDTO.setName("ZAB");
		regularEmployeeDetailsDTO.setJobTitle("");

		MvcResult result = mockMvc.perform(post(Constants.EMPLOYEE_REST_API_BASE_URI_PATH)
			.contentType(MediaType.APPLICATION_JSON)
			.content(new ObjectMapper().writeValueAsString(regularEmployeeDetailsDTO)))
			.andExpect(status().isBadRequest())
			.andReturn();

		String responseBody = result.getResponse().getContentAsString();

		AssertionsForClassTypes.assertThat(responseBody).isEqualTo(Constants.EMPLOYEE_JOB_TITLE_NULL_OR_EMPTY_EXCEPTION_MESSAGE);
	}

	@Test
	public void testCreateRegularEmployeeWithInvalidDepartment() throws Exception {
		RegularEmployeeDetailsDTO regularEmployeeDetailsDTO = new RegularEmployeeDetailsDTO();

		regularEmployeeDetailsDTO.setName("ZAB");
		regularEmployeeDetailsDTO.setJobTitle("Marketing Manager");
		regularEmployeeDetailsDTO.setDepartmentId(4);

		MvcResult result = mockMvc.perform(post(Constants.EMPLOYEE_REST_API_BASE_URI_PATH)
			.contentType(MediaType.APPLICATION_JSON)
			.content(new ObjectMapper().writeValueAsString(regularEmployeeDetailsDTO)))
			.andExpect(status().isUnprocessableEntity())
			.andReturn();

		String responseBody = result.getResponse().getContentAsString();

		AssertionsForClassTypes.assertThat(responseBody).isEqualTo(Constants.INVALID_DEPARTMENT_ID_EXCEPTION_MESSAGE);
	}

	@Test
	public void testCreateRegularEmployeeWithInvalidManager() throws Exception {
		RegularEmployeeDetailsDTO regularEmployeeDetailsDTO = new RegularEmployeeDetailsDTO();

		regularEmployeeDetailsDTO.setName("CDE");
		regularEmployeeDetailsDTO.setJobTitle("Principal Software Engineer");
		regularEmployeeDetailsDTO.setDepartmentId(1);
		regularEmployeeDetailsDTO.setManagerId(20);

		MvcResult result = mockMvc.perform(post(Constants.EMPLOYEE_REST_API_BASE_URI_PATH)
			.contentType(MediaType.APPLICATION_JSON)
			.content(new ObjectMapper().writeValueAsString(regularEmployeeDetailsDTO)))
			.andExpect(status().isUnprocessableEntity())
			.andReturn();

		String responseBody = result.getResponse().getContentAsString();

		AssertionsForClassTypes.assertThat(responseBody).isEqualTo(Constants.INVALID_MANAGER_ID_EXCEPTION_MESSAGE);
	}

	@Test
	public void testCreateRegularEmployeeReportingDirectlyToTheCeo() throws Exception {
		RegularEmployeeDetailsDTO regularEmployeeDetailsDTO = new RegularEmployeeDetailsDTO();

		regularEmployeeDetailsDTO.setName("CDE");
		regularEmployeeDetailsDTO.setJobTitle("Principal Software Engineer");
		regularEmployeeDetailsDTO.setDepartmentId(1);
		regularEmployeeDetailsDTO.setManagerId(1);

		MvcResult result = mockMvc.perform(post(Constants.EMPLOYEE_REST_API_BASE_URI_PATH)
			.contentType(MediaType.APPLICATION_JSON)
			.content(new ObjectMapper().writeValueAsString(regularEmployeeDetailsDTO)))
			.andExpect(status().isUnprocessableEntity())
			.andReturn();

		String responseBody = result.getResponse().getContentAsString();

		AssertionsForClassTypes.assertThat(responseBody).isEqualTo(Constants.REGULAR_EMPLOYEE_REPORTING_TO_REGULAR_EMPLOYEE_OR_CEO_EXCEPTION_MESSAGE);
	}

	@Test
	public void testCreateRegularEmployeeReportingToManagerInOtherDepartment() throws Exception {
		RegularEmployeeDetailsDTO regularEmployeeDetailsDTO = new RegularEmployeeDetailsDTO();

		regularEmployeeDetailsDTO.setName("CDE");
		regularEmployeeDetailsDTO.setJobTitle("Principal Software Engineer");
		regularEmployeeDetailsDTO.setDepartmentId(1);
		regularEmployeeDetailsDTO.setManagerId(3);

		MvcResult result = mockMvc.perform(post(Constants.EMPLOYEE_REST_API_BASE_URI_PATH)
			.contentType(MediaType.APPLICATION_JSON)
			.content(new ObjectMapper().writeValueAsString(regularEmployeeDetailsDTO)))
			.andExpect(status().isUnprocessableEntity())
			.andReturn();

		String responseBody = result.getResponse().getContentAsString();

		AssertionsForClassTypes.assertThat(responseBody).isEqualTo(Constants.EMPLOYEE_REPORTING_TO_MANAGER_IN_ANOTHER_DEPARTMENT_EXCEPTION_MESSAGE);
	}
}
