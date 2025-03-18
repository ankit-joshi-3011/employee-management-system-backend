package com.wissen.ems.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonTypeName;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
// When the employeeType field has a value REGULAR_MANAGER, instantiate this DTO
@JsonTypeName("REGULAR_MANAGER")
public class RegularManagerDetailsDTO extends EmployeeDetailsDTO {
	private String jobTitle;
	private int departmentId;
	private int managerId;
	private List<Integer> directReportsEmployeeIds;
}
