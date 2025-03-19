package com.wissen.ems.dto;

import com.wissen.ems.entity.Department;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DepartmentDetailsDTO {
	private int id;
	private String name;

	public DepartmentDetailsDTO(Department department) {
		id = department.getId();
		name = department.getName();
	}
}
