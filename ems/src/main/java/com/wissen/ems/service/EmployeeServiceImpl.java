package com.wissen.ems.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.wissen.ems.common.Constants.ExceptionMessages;
import com.wissen.ems.dto.EmployeeDetailsDTO;
import com.wissen.ems.dto.ManagerDetailsRetrievalDTO;
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
			throw new IllegalArgumentException(ExceptionMessages.UNSUPPORTED_EMPLOYEE_TYPE);
		}

		Employee newEmployee = employeeCreator.create(employeeDetailsDTO);

		return employeeRepository.save(newEmployee);
	}

	@Override
	public List<ManagerDetailsRetrievalDTO> getActiveManagersByDepartment(int departmentId) {
		List<Employee> activeDepartmentManagers = employeeRepository.findActiveManagersByDepartment(departmentId);

		List<ManagerDetailsRetrievalDTO> activeDepartmentManagersDto = new ArrayList<>();

		for (Employee activeDepartmentManager : activeDepartmentManagers) {
			activeDepartmentManagersDto.add(new ManagerDetailsRetrievalDTO(activeDepartmentManager));
		}

		return activeDepartmentManagersDto;
	}
}
