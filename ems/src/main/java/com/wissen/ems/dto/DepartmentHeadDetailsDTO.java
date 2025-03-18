package com.wissen.ems.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepartmentHeadDetailsDTO extends EmployeeDetailsDTO {
	private int departmentId;
	private List<Integer> directReportsEmployeeIds;
}
