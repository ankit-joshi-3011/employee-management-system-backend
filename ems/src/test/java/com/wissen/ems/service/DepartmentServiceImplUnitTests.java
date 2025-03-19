package com.wissen.ems.service;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.wissen.ems.common.TestUtility;
import com.wissen.ems.dto.DepartmentDetailsDTO;
import com.wissen.ems.entity.Department;
import com.wissen.ems.repository.DepartmentRepository;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceImplUnitTests {
	@Mock
	private DepartmentRepository departmentRepository;

	@InjectMocks
	private DepartmentServiceImpl departmentService;

	@Test
	public void testGetAllDepartments() {
		List<Department> allDepartments = new ArrayList<>();

		int[] departmentIds = new int[] { 1, 2, 3 };
		String[] departmentNames = new String[] { "Engineering", "HR", "Finance" };

		for (int i = 0; i < departmentIds.length; i++) {
			Department department = new Department();
			department.setId(departmentIds[i]);
			department.setName(departmentNames[i]);

			allDepartments.add(department);
		}

		when(departmentRepository.findAll()).thenReturn(allDepartments);

		List<DepartmentDetailsDTO> allDepartmentDetailsDto = departmentService.getAllDepartments();

		TestUtility.assertDepartmentDetailsDtosAreCorrect(allDepartmentDetailsDto);
	}
}
