package com.wissen.ems.dto;

import com.fasterxml.jackson.annotation.JsonTypeName;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
// When the employeeType field has a value REGULAR, instantiate this DTO
@JsonTypeName("REGULAR")
public class RegularEmployeeDetailsDTO extends EmployeeDetailsDTO {
	private String jobTitle;
	private int departmentId;
	private int managerId;
}
