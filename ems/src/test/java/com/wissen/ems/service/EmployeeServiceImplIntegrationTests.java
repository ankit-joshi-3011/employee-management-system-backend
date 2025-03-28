package com.wissen.ems.service;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.transaction.annotation.Transactional;

import com.wissen.ems.common.Constants.ExceptionMessages;
import com.wissen.ems.common.Utility;
import com.wissen.ems.dto.ManagerDetailsRetrievalDTO;
import com.wissen.ems.dto.RegularEmployeeDetailsDTO;
import com.wissen.ems.dto.UnsupportedEmployeeDetailsDto;
import com.wissen.ems.entity.Employee;
import com.wissen.ems.entity.EmployeeType;
import com.wissen.ems.entity.EmploymentStatus;
import com.wissen.ems.exception.BusinessRuleViolationException;

// Start the full Spring Boot application but with a random port instead of the fixed
// port 8080. Using a random port allows tests to run in parallel without port conflicts.
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
// Ensure that database changes are rolled back after each test execution
@Transactional
public class EmployeeServiceImplIntegrationTests {
	private EmployeeServiceImpl employeeService;

	@Autowired
	public EmployeeServiceImplIntegrationTests(EmployeeServiceImpl employeeService) {
		this.employeeService = employeeService;
	}

	@Test
	public void testCreateRegularEmployeeSuccessfully() {
		RegularEmployeeDetailsDTO regularEmployeeDetailsDTO = Utility.getValidRegularEmployeeDetailsDto();

		Employee createdEmployee = employeeService.save(regularEmployeeDetailsDTO);

		AssertionsForClassTypes.assertThat(createdEmployee.getId()).isGreaterThan(0);
		AssertionsForClassTypes.assertThat(createdEmployee.getName()).isEqualTo(regularEmployeeDetailsDTO.getName());
		AssertionsForClassTypes.assertThat(createdEmployee.getJobTitle()).isEqualTo(regularEmployeeDetailsDTO.getJobTitle());
		AssertionsForClassTypes.assertThat(createdEmployee.getDepartment().getId()).isEqualTo(regularEmployeeDetailsDTO.getDepartmentId());
		AssertionsForClassTypes.assertThat(createdEmployee.getType()).isEqualTo(EmployeeType.REGULAR);
		AssertionsForClassTypes.assertThat(createdEmployee.getStatus()).isEqualTo(EmploymentStatus.ACTIVE);
		AssertionsForClassTypes.assertThat(createdEmployee.getManager().getId()).isEqualTo(regularEmployeeDetailsDTO.getManagerId());
		AssertionsForClassTypes.assertThat(createdEmployee.getDirectReports()).isNull();
	}

	@Test
	public void testCreateEmployeeWithUnsupportedType() {
		UnsupportedEmployeeDetailsDto unsupportedEmployeeDetailsDTO = new UnsupportedEmployeeDetailsDto();

		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> employeeService.save(unsupportedEmployeeDetailsDTO));

		AssertionsForClassTypes.assertThat(exception.getMessage()).isEqualTo(ExceptionMessages.UNSUPPORTED_EMPLOYEE_TYPE);
	}

	@Test
	public void testCreateRegularEmployeeWithNullName() {
		RegularEmployeeDetailsDTO regularEmployeeDetailsDTO = Utility.getRegularEmployeeDetailsDtoWithNullEmployeeName();

		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> employeeService.save(regularEmployeeDetailsDTO));

		AssertionsForClassTypes.assertThat(exception.getMessage()).isEqualTo(ExceptionMessages.EMPLOYEE_NAME_NULL_OR_EMPTY);
	}

	@Test
	public void testCreateRegularEmployeeWithEmptyJobTitle() {
		RegularEmployeeDetailsDTO regularEmployeeDetailsDTO = Utility.getRegularEmployeeDetailsDtoWithEmptyJobTitle();

		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> employeeService.save(regularEmployeeDetailsDTO));

		AssertionsForClassTypes.assertThat(exception.getMessage()).isEqualTo(ExceptionMessages.EMPLOYEE_JOB_TITLE_NULL_OR_EMPTY);
	}

	@Test
	public void testCreateRegularEmployeeWithInvalidDepartment() {
		RegularEmployeeDetailsDTO regularEmployeeDetailsDTO = Utility.getRegularEmployeeDetailsDtoWithInvalidDepartmentId();

		BusinessRuleViolationException exception = assertThrows(BusinessRuleViolationException.class, () -> employeeService.save(regularEmployeeDetailsDTO));

		AssertionsForClassTypes.assertThat(exception.getMessage()).isEqualTo(ExceptionMessages.INVALID_DEPARTMENT_ID);
	}

	@Test
	public void testCreateRegularEmployeeWithInvalidManager() {
		RegularEmployeeDetailsDTO regularEmployeeDetailsDTO = Utility.getRegularEmployeeDetailsDtoWithInvalidManagerId();

		BusinessRuleViolationException exception = assertThrows(BusinessRuleViolationException.class, () -> employeeService.save(regularEmployeeDetailsDTO));

		AssertionsForClassTypes.assertThat(exception.getMessage()).isEqualTo(ExceptionMessages.INVALID_MANAGER_ID);
	}

	@Test
	public void testCreateRegularEmployeeReportingDirectlyToTheCeo() {
		RegularEmployeeDetailsDTO regularEmployeeDetailsDTO = Utility.getRegularEmployeeDetailsDtoWithEmployeeReportingToCeo();

		BusinessRuleViolationException exception = assertThrows(BusinessRuleViolationException.class, () -> employeeService.save(regularEmployeeDetailsDTO));

		AssertionsForClassTypes.assertThat(exception.getMessage()).isEqualTo(ExceptionMessages.REGULAR_EMPLOYEE_REPORTING_TO_REGULAR_EMPLOYEE_OR_CEO);
	}

	@Test
	public void testCreateRegularEmployeeReportingToManagerInOtherDepartment() {
		RegularEmployeeDetailsDTO regularEmployeeDetailsDTO = Utility.getRegularEmployeeDetailsDtoWithEmployeeReportingToManagerInDifferentDepartment();

		BusinessRuleViolationException exception = assertThrows(BusinessRuleViolationException.class, () -> employeeService.save(regularEmployeeDetailsDTO));

		AssertionsForClassTypes.assertThat(exception.getMessage()).isEqualTo(ExceptionMessages.EMPLOYEE_REPORTING_TO_MANAGER_IN_ANOTHER_DEPARTMENT);
	}

	@Test
	public void testGetActiveManagersByDepartment() {
		int engineeringDepartmentId = 1;
		List<ManagerDetailsRetrievalDTO> activeEngineeringManagersDto = employeeService.getActiveManagersByDepartment(engineeringDepartmentId);

		int[] activeEngineeringManagerIds = new int[] { 2, 5 };
		String[] activeEngineeringManagerNames = new String[] { "DEF", "MNO" };

		AssertionsForClassTypes.assertThat(activeEngineeringManagersDto.size()).isEqualTo(activeEngineeringManagerIds.length);

		for (int i = 0; i < activeEngineeringManagersDto.size(); i++) {
			ManagerDetailsRetrievalDTO activeEngineeringManagerDto = activeEngineeringManagersDto.get(i);

			AssertionsForClassTypes.assertThat(activeEngineeringManagerDto.getId()).isEqualTo(activeEngineeringManagerIds[i]);
			AssertionsForClassTypes.assertThat(activeEngineeringManagerDto.getName()).isEqualTo(activeEngineeringManagerNames[i]);
		}
	}
}
