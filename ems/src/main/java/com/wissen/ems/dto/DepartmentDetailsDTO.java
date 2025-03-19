package com.wissen.ems.dto;

import com.wissen.ems.entity.Department;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepartmentDetailsDTO {
	private int id;
	private String name;

	public DepartmentDetailsDTO(Department department) {
		id = department.getId();
		name = department.getName();
	}
}
