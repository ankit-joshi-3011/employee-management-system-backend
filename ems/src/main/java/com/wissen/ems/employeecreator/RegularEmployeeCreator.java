package com.wissen.ems.employeecreator;

import org.springframework.stereotype.Component;

import com.wissen.ems.common.Constants.ExceptionMessages;
import com.wissen.ems.dto.EmployeeDetailsDTO;
import com.wissen.ems.dto.RegularEmployeeDetailsDTO;
import com.wissen.ems.entity.Department;
import com.wissen.ems.entity.Employee;
import com.wissen.ems.entity.EmployeeType;
import com.wissen.ems.entity.EmploymentStatus;
import com.wissen.ems.exception.BusinessRuleViolationException;
import com.wissen.ems.repository.DepartmentRepository;
import com.wissen.ems.repository.EmployeeRepository;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class RegularEmployeeCreator implements EmployeeCreator {
	private DepartmentRepository departmentRepository;
	private EmployeeRepository employeeRepository;

	@Override
	public boolean supportsCreation(EmployeeDetailsDTO employeeDetailsDto) {
		return employeeDetailsDto instanceof RegularEmployeeDetailsDTO;
	}

	@Override
	public Employee create(EmployeeDetailsDTO employeeDetailsDto) {
		if (!supportsCreation(employeeDetailsDto)) {
			throw new IllegalArgumentException("Incorrect argument passed to creator. Expected: RegularEmployeeDetailsDTO, Found: " + employeeDetailsDto.getClass().getSimpleName());
		}

		RegularEmployeeDetailsDTO regularEmployeeDetailsDTO = (RegularEmployeeDetailsDTO) employeeDetailsDto;

		String name = regularEmployeeDetailsDTO.getName();

		if (name == null || name.isEmpty() || name.isBlank()) {
			throw new IllegalArgumentException(ExceptionMessages.EMPLOYEE_NAME_NULL_OR_EMPTY);
		}

		String jobTitle = regularEmployeeDetailsDTO.getJobTitle();

		if (jobTitle == null || jobTitle.isEmpty() || jobTitle.isBlank()) {
			throw new IllegalArgumentException(ExceptionMessages.EMPLOYEE_JOB_TITLE_NULL_OR_EMPTY);
		}

		Department department = departmentRepository.findById(regularEmployeeDetailsDTO.getDepartmentId())
			.orElseThrow(() -> new BusinessRuleViolationException(ExceptionMessages.INVALID_DEPARTMENT_ID));

		Employee manager = employeeRepository.findById(regularEmployeeDetailsDTO.getManagerId())
			.orElseThrow(() -> new BusinessRuleViolationException(ExceptionMessages.INVALID_MANAGER_ID));

		EmployeeType managerType = manager.getType();

		if (managerType == EmployeeType.REGULAR || managerType == EmployeeType.CEO) {
			throw new BusinessRuleViolationException(ExceptionMessages.REGULAR_EMPLOYEE_REPORTING_TO_REGULAR_EMPLOYEE_OR_CEO);
		}

		if (manager.getDepartment().getId() != department.getId()) {
			throw new BusinessRuleViolationException(ExceptionMessages.EMPLOYEE_REPORTING_TO_MANAGER_IN_ANOTHER_DEPARTMENT);
		}

		Employee newRegularEmployee = new Employee();

		newRegularEmployee.setName(name);
		newRegularEmployee.setJobTitle(jobTitle);
		newRegularEmployee.setDepartment(department);
		newRegularEmployee.setType(EmployeeType.REGULAR);
		newRegularEmployee.setStatus(EmploymentStatus.ACTIVE);
		newRegularEmployee.setManager(manager);

		return newRegularEmployee;
	}
}
