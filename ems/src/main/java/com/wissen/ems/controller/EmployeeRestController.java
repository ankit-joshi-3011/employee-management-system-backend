package com.wissen.ems.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.wissen.ems.common.Constants;
import com.wissen.ems.dto.EmployeeDetailsDTO;
import com.wissen.ems.dto.ManagerDetailsRetrievalDTO;
import com.wissen.ems.entity.Employee;
import com.wissen.ems.service.EmployeeService;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping(Constants.EMPLOYEE_REST_API_BASE_URI_PATH)
@AllArgsConstructor
public class EmployeeRestController {
	private EmployeeService employeeService;

	@PostMapping
	public ResponseEntity<Void> createEmployee(@RequestBody EmployeeDetailsDTO employeeDetailsDTO) {
		Employee createdEmployee = employeeService.save(employeeDetailsDTO);

		URI createdEmployeeLocation = ServletUriComponentsBuilder.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(createdEmployee.getId())
			.toUri();

		return ResponseEntity.created(createdEmployeeLocation).build();
	}

	@GetMapping(Constants.RETRIEVE_ACTIVE_MANAGERS_BY_DEPARTMENT_API_URI_PATH)
	public ResponseEntity<List<ManagerDetailsRetrievalDTO>> getActiveManagersByDepartment(@RequestParam int departmentId) {
		List<ManagerDetailsRetrievalDTO> activeDepartmentManagersDto = employeeService.getActiveManagersByDepartment(departmentId);

		return ResponseEntity.ok(activeDepartmentManagersDto);
	}
}
