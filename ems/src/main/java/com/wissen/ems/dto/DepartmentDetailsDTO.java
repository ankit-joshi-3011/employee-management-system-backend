package com.wissen.ems.dto;

import com.wissen.ems.entity.Department;

import lombok.Getter;

@Getter
public class DepartmentDetailsDTO {
	private final int id;
	private final String name;

	public DepartmentDetailsDTO(Department department) {
		id = department.getId();
		name = department.getName();
	}
}
