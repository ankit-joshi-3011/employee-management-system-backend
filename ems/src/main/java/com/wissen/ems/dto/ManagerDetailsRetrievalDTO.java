package com.wissen.ems.dto;

import com.wissen.ems.entity.Employee;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ManagerDetailsRetrievalDTO {
	private int id;
	private String name;

	public ManagerDetailsRetrievalDTO(Employee employee) {
		id = employee.getId();
		name = employee.getName();
	}
}
