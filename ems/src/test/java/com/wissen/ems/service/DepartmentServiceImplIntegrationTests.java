package com.wissen.ems.service;

import java.util.List;

import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.transaction.annotation.Transactional;

import com.wissen.ems.dto.DepartmentDetailsDTO;

// Start the full Spring Boot application but with a random port instead of the fixed
// port 8080. Using a random port allows tests to run in parallel without port conflicts.
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
// Ensure that database changes are rolled back after each test execution
@Transactional
public class DepartmentServiceImplIntegrationTests {
	private DepartmentServiceImpl departmentService;

	@Autowired
	public DepartmentServiceImplIntegrationTests(DepartmentServiceImpl departmentService) {
		this.departmentService = departmentService;
	}

	@Test
	public void testGetAllDepartments() {
		List<DepartmentDetailsDTO> allDepartmentDetailsDto = departmentService.getAllDepartments();

		int[] departmentIds = new int[] { 1, 2, 3 };
		String[] departmentNames = new String[] { "Engineering", "HR", "Finance" };

		AssertionsForClassTypes.assertThat(allDepartmentDetailsDto.size()).isEqualTo(departmentIds.length);

		for (int i = 0; i < allDepartmentDetailsDto.size(); i++) {
			DepartmentDetailsDTO departmentDetailsDto = allDepartmentDetailsDto.get(i);

			AssertionsForClassTypes.assertThat(departmentDetailsDto.getId()).isEqualTo(departmentIds[i]);
			AssertionsForClassTypes.assertThat(departmentDetailsDto.getName()).isEqualTo(departmentNames[i]);
		}
	}
}
