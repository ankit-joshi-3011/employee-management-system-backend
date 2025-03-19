package com.wissen.ems.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.wissen.ems.dto.DepartmentDetailsDTO;
import com.wissen.ems.entity.Department;
import com.wissen.ems.repository.DepartmentRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
	private DepartmentRepository departmentRepository;

	@Override
	public List<DepartmentDetailsDTO> getAllDepartments() {
		List<DepartmentDetailsDTO> departmentDetailsDto = new ArrayList<>();

		for (Department department : departmentRepository.findAll()) {
			departmentDetailsDto.add(new DepartmentDetailsDTO(department));
		}

		return departmentDetailsDto;
	}
}
