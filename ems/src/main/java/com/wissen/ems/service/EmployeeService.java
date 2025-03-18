package com.wissen.ems.service;

import com.wissen.ems.dto.EmployeeDetailsDTO;
import com.wissen.ems.entity.Employee;

public interface EmployeeService {
	Employee save(EmployeeDetailsDTO employeeDetailsDTO);
}
