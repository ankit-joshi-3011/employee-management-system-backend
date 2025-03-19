package com.wissen.ems.service;

import java.util.List;

import com.wissen.ems.dto.EmployeeDetailsDTO;
import com.wissen.ems.dto.ManagerDetailsRetrievalDTO;
import com.wissen.ems.entity.Employee;

public interface EmployeeService {
	Employee save(EmployeeDetailsDTO employeeDetailsDTO);

	List<ManagerDetailsRetrievalDTO> getActiveManagersByDepartment(int departmentId);
}
