package com.wissen.ems.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wissen.ems.common.Constants;
import com.wissen.ems.dto.DepartmentDetailsDTO;
import com.wissen.ems.service.DepartmentService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(Constants.DEPARTMENT_REST_API_BASE_URI_PATH)
@AllArgsConstructor
public class DepartmentRestController {
	private DepartmentService departmentService;

	@GetMapping
	public ResponseEntity<List<DepartmentDetailsDTO>> getAllDepartments() {
		List<DepartmentDetailsDTO> departmentDetailsDto = departmentService.getAllDepartments();

		return ResponseEntity.ok(departmentDetailsDto);
	}
}
