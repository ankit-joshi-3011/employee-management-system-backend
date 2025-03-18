package com.wissen.ems.employeecreator;

import org.springframework.stereotype.Component;

import com.wissen.ems.common.Constants;
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
			throw new IllegalArgumentException(Constants.EMPLOYEE_NAME_NULL_OR_EMPTY_EXCEPTION_MESSAGE);
		}

		String jobTitle = regularEmployeeDetailsDTO.getJobTitle();

		if (jobTitle == null || jobTitle.isEmpty() || jobTitle.isBlank()) {
			throw new IllegalArgumentException("Employee's job title cannot be null or empty");
		}

		Department department = departmentRepository.findById(regularEmployeeDetailsDTO.getDepartmentId())
			.orElseThrow(() -> new BusinessRuleViolationException("A regular employee should belong to an existing department"));

		Employee manager = employeeRepository.findById(regularEmployeeDetailsDTO.getManagerId())
			.orElseThrow(() -> new BusinessRuleViolationException("A regular employee should report to an existing manager"));

		EmployeeType managerType = manager.getType();

		if (managerType == EmployeeType.REGULAR || managerType == EmployeeType.CEO) {
			throw new BusinessRuleViolationException("A regular employee cannot report to another regular employee or directly to the CEO");
		}

		if (manager.getDepartment().getId() != department.getId()) {
			throw new BusinessRuleViolationException("A regular employee cannot report to a manager in another department");
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
