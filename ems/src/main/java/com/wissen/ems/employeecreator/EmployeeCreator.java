package com.wissen.ems.employeecreator;

import com.wissen.ems.dto.EmployeeDetailsDTO;
import com.wissen.ems.entity.Employee;

public interface EmployeeCreator {
	boolean supportsCreation(EmployeeDetailsDTO employeeDetailsDto);

	Employee create(EmployeeDetailsDTO employeeDetailsDto);
}
