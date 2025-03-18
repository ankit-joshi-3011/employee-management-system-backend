package com.wissen.ems.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegularManagerDetailsDTO extends EmployeeDetailsDTO {
	private String jobTitle;
	private int departmentId;
	private int managerId;
	private List<Integer> directReportsEmployeeIds;
}
