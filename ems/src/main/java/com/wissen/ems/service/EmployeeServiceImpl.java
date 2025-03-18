package com.wissen.ems.service;

import org.springframework.stereotype.Service;

import com.wissen.ems.common.Constants;
import com.wissen.ems.dto.EmployeeDetailsDTO;
import com.wissen.ems.employeecreator.EmployeeCreator;
import com.wissen.ems.entity.Employee;
import com.wissen.ems.repository.EmployeeRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
	private EmployeeCreator employeeCreator;
	private EmployeeRepository employeeRepository;

	@Override
	public Employee save(EmployeeDetailsDTO employeeDetailsDTO) {
		if (!employeeCreator.supportsCreation(employeeDetailsDTO)) {
			throw new IllegalArgumentException(Constants.UNSUPPORTED_EMPLOYEE_TYPE_EXCEPTION_MESSAGE);
		}

		Employee newEmployee = employeeCreator.create(employeeDetailsDTO);

		return employeeRepository.save(newEmployee);
	}
}
