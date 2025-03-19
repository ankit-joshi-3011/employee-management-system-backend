package com.wissen.ems.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wissen.ems.common.Constants;
import com.wissen.ems.common.TestUtility;
import com.wissen.ems.dto.DepartmentDetailsDTO;
import com.wissen.ems.service.DepartmentService;

// Allows a fast and lightweight mechanism to unit test controllers without
// loading the entire application context and just loading the controller
// and its dependencies (like @RestControllerAdvice).
@WebMvcTest(DepartmentRestController.class)
public class DepartmentRestControllerUnitTests {
	private MockMvc mockMvc;

	private ObjectMapper objectMapper;

	@MockitoBean
	private DepartmentService departmentService;

	@Autowired
	public DepartmentRestControllerUnitTests(MockMvc mockMvc, ObjectMapper objectMapper) {
		this.mockMvc = mockMvc;
		this.objectMapper = objectMapper;
	}

	@Test
	public void testGetAllDepartments() throws Exception {
		List<DepartmentDetailsDTO> allDepartmentDetailsDto = new ArrayList<>();

		int[] departmentIds = new int[] { 1, 2, 3 };
		String[] departmentNames = new String[] { "Engineering", "HR", "Finance" };

		for (int i = 0; i < departmentIds.length; i++) {
			DepartmentDetailsDTO departmentDetailsDto = new DepartmentDetailsDTO();
			departmentDetailsDto.setId(departmentIds[i]);
			departmentDetailsDto.setName(departmentNames[i]);

			allDepartmentDetailsDto.add(departmentDetailsDto);
		}

		when(departmentService.getAllDepartments()).thenReturn(allDepartmentDetailsDto);

		MvcResult result = mockMvc.perform(get(Constants.DEPARTMENT_REST_API_BASE_URI_PATH)
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andReturn();

		String jsonResponse = result.getResponse().getContentAsString();
		List<DepartmentDetailsDTO> responseAllDepartmentDetailsDto = objectMapper.readValue(jsonResponse, objectMapper.getTypeFactory().constructCollectionType(List.class, DepartmentDetailsDTO.class));

		TestUtility.assertDepartmentDetailsDtosAreCorrect(responseAllDepartmentDetailsDto);
	}
}
