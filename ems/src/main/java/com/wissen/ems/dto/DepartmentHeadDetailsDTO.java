package com.wissen.ems.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonTypeName;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
// When the employeeType field has a value DEPARTMENT_HEAD, instantiate this DTO
@JsonTypeName("DEPARTMENT_HEAD")
public class DepartmentHeadDetailsDTO extends EmployeeDetailsDTO {
	private int departmentId;
	private List<Integer> directReportsEmployeeIds;
}
