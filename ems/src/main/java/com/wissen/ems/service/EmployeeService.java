package com.wissen.ems.service;

import java.util.List;

import com.wissen.ems.dto.EmployeeDetailsDTO;
import com.wissen.ems.entity.Employee;

public interface EmployeeService {
	Employee save(EmployeeDetailsDTO employeeDetailsDTO);

	List<Employee> getActiveManagersByDepartment(int departmentId);
}
