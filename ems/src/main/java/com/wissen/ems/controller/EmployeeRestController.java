package com.wissen.ems.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wissen.ems.common.Constants;
import com.wissen.ems.dto.EmployeeDetailsDTO;
import com.wissen.ems.entity.Employee;
import com.wissen.ems.service.EmployeeService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(Constants.EMPLOYEE_REST_API_BASE_URI_PATH)
@AllArgsConstructor
public class EmployeeRestController {
	private EmployeeService employeeService;

	@PostMapping
	public ResponseEntity<Employee> createEmployee(@RequestBody EmployeeDetailsDTO employeeDetailsDTO) {
		Employee createdEmployee = employeeService.save(employeeDetailsDTO);

		return ResponseEntity.status(HttpStatus.CREATED).body(createdEmployee);
	}
}
