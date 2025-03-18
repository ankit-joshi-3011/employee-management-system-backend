package com.wissen.ems.service;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.wissen.ems.common.Constants;
import com.wissen.ems.dto.EmployeeDetailsDTO;
import com.wissen.ems.dto.RegularEmployeeDetailsDTO;
import com.wissen.ems.entity.Employee;
import com.wissen.ems.entity.EmployeeType;
import com.wissen.ems.entity.EmploymentStatus;

// Load the entire application context, so the real database & services are used
@SpringBootTest
// Ensure that database changes are rolled back after each test execution
@Transactional
public class EmployeeServiceImplIntegrationTests {
	private EmployeeServiceImpl employeeService;

	private class UnsupportedEmployeeDetailsDto extends EmployeeDetailsDTO {
	}

	@Autowired
	public EmployeeServiceImplIntegrationTests(EmployeeServiceImpl employeeService) {
		this.employeeService = employeeService;
	}

	@Test
	public void testCreateRegularEmployeeSuccessfully() {
		RegularEmployeeDetailsDTO regularEmployeeDetailsDTO = new RegularEmployeeDetailsDTO();

		String newEmployeeName = "WXY";
		regularEmployeeDetailsDTO.setName(newEmployeeName);

		String newEmployeeJobTitle = "Financial Management Associate";
		regularEmployeeDetailsDTO.setJobTitle(newEmployeeJobTitle);

		int financeDepartmentId = 3;
		regularEmployeeDetailsDTO.setDepartmentId(financeDepartmentId);

		regularEmployeeDetailsDTO.setEmployeeType("REGULAR");

		int financeManagerEmployeeId = 11;
		regularEmployeeDetailsDTO.setManagerId(financeManagerEmployeeId);

		Employee createdEmployee = employeeService.save(regularEmployeeDetailsDTO);

		AssertionsForClassTypes.assertThat(createdEmployee.getId()).isGreaterThan(0);
		AssertionsForClassTypes.assertThat(createdEmployee.getName()).isEqualTo(newEmployeeName);
		AssertionsForClassTypes.assertThat(createdEmployee.getJobTitle()).isEqualTo(newEmployeeJobTitle);
		AssertionsForClassTypes.assertThat(createdEmployee.getDepartment().getId()).isEqualTo(financeDepartmentId);
		AssertionsForClassTypes.assertThat(createdEmployee.getType()).isEqualTo(EmployeeType.REGULAR);
		AssertionsForClassTypes.assertThat(createdEmployee.getStatus()).isEqualTo(EmploymentStatus.ACTIVE);
		AssertionsForClassTypes.assertThat(createdEmployee.getManager().getId()).isEqualTo(financeManagerEmployeeId);
		AssertionsForClassTypes.assertThat(createdEmployee.getDirectReports()).isNull();
	}

	@Test
	public void testCreateEmployeeWithUnsupportedType() {
		UnsupportedEmployeeDetailsDto unsupportedEmployeeDetailsDTO = new UnsupportedEmployeeDetailsDto();

		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> employeeService.save(unsupportedEmployeeDetailsDTO));

		AssertionsForClassTypes.assertThat(exception.getMessage()).isEqualTo(Constants.UNSUPPORTED_EMPLOYEE_TYPE_EXCEPTION_MESSAGE);
	}
}
