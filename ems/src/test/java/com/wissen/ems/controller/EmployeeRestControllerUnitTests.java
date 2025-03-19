package com.wissen.ems.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wissen.ems.common.Constants;
import com.wissen.ems.common.TestUtility;
import com.wissen.ems.dto.EmployeeDetailsDTO;
import com.wissen.ems.dto.ManagerDetailsRetrievalDTO;
import com.wissen.ems.dto.RegularEmployeeDetailsDTO;
import com.wissen.ems.entity.Department;
import com.wissen.ems.entity.Employee;
import com.wissen.ems.entity.EmployeeType;
import com.wissen.ems.entity.EmploymentStatus;
import com.wissen.ems.service.EmployeeService;

// Allows a fast and lightweight mechanism to unit test controllers without
// loading the entire application context and just loading the controller
// and its dependencies (like @RestControllerAdvice).
@WebMvcTest(EmployeeRestController.class)
public class EmployeeRestControllerUnitTests {
	private MockMvc mockMvc;

	private ObjectMapper objectMapper;

	@MockitoBean
	private EmployeeService employeeService;

	@Autowired
	public EmployeeRestControllerUnitTests(MockMvc mockMvc, ObjectMapper objectMapper) {
		this.mockMvc = mockMvc;
		this.objectMapper = objectMapper;
	}

	@Test
	public void testCreateRegularEmployee() throws Exception {
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

		Employee newEmployee = new Employee();

		int newEmployeeId = 15;
		newEmployee.setId(newEmployeeId);

		newEmployee.setName(newEmployeeName);
		newEmployee.setJobTitle(newEmployeeJobTitle);

		Department newEmployeeDepartment = new Department();
		newEmployeeDepartment.setId(financeDepartmentId);
		newEmployee.setDepartment(newEmployeeDepartment);

		newEmployee.setType(EmployeeType.REGULAR);
		newEmployee.setStatus(EmploymentStatus.ACTIVE);

		Employee financeManager = new Employee();
		financeManager.setId(financeManagerEmployeeId);
		newEmployee.setManager(financeManager);

		when(employeeService.save(any(EmployeeDetailsDTO.class))).thenReturn(newEmployee);

		MvcResult result = mockMvc.perform(post(Constants.EMPLOYEE_REST_API_BASE_URI_PATH)
			.contentType(MediaType.APPLICATION_JSON)
			.content(new ObjectMapper().writeValueAsString(regularEmployeeDetailsDTO)))
			.andExpect(status().isCreated())
			.andExpect(header().exists(HttpHeaders.LOCATION))
			.andReturn();

		String locationHeaderValue = result.getResponse().getHeader(HttpHeaders.LOCATION);

		AssertionsForClassTypes.assertThat(locationHeaderValue).contains(Constants.EMPLOYEE_REST_API_BASE_URI_PATH + "/" + newEmployeeId);
	}

	@Test
	public void testGetActiveManagersByDepartment() throws Exception {
		ManagerDetailsRetrievalDTO headOfEngineeringDto = new ManagerDetailsRetrievalDTO();

		int headOfEngineeringEmployeeId = 2;
		headOfEngineeringDto.setId(headOfEngineeringEmployeeId);

		String headOfEngineeringName = "DEF";
		headOfEngineeringDto.setName(headOfEngineeringName);

		ManagerDetailsRetrievalDTO softwareEngineeringManagerDto = new ManagerDetailsRetrievalDTO();

		int softwareEngineeringManagerEmployeeId = 5;
		softwareEngineeringManagerDto.setId(softwareEngineeringManagerEmployeeId);

		String softwareEngineeringManagerName = "MNO";
		softwareEngineeringManagerDto.setName(softwareEngineeringManagerName);

		List<ManagerDetailsRetrievalDTO> activeEngineeringManagersDto = List.of(headOfEngineeringDto, softwareEngineeringManagerDto);

		when(employeeService.getActiveManagersByDepartment(anyInt())).thenReturn(activeEngineeringManagersDto);

		int engineeringDepartmentId = 1;

		MvcResult result = mockMvc.perform(get(Constants.EMPLOYEE_REST_API_BASE_URI_PATH + Constants.RETRIEVE_ACTIVE_MANAGERS_BY_DEPARTMENT_API_URI_PATH)
			.param("departmentId", String.valueOf(engineeringDepartmentId))
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andReturn();

		String jsonResponse = result.getResponse().getContentAsString();
		List<ManagerDetailsRetrievalDTO> responseActiveEngineeringManagersDto = objectMapper.readValue(jsonResponse, objectMapper.getTypeFactory().constructCollectionType(List.class, ManagerDetailsRetrievalDTO.class));

		TestUtility.assertManagerDetailsRetrievalDtosAreCorrect(responseActiveEngineeringManagersDto);
	}
}
