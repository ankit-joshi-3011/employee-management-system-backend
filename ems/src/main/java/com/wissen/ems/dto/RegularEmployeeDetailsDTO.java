package com.wissen.ems.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegularEmployeeDetailsDTO extends EmployeeDetailsDTO {
	private String jobTitle;
	private int departmentId;
	private int managerId;
}
