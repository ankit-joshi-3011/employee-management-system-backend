package com.wissen.ems.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
// The @JsonTypeInfo attribute tells Jackson (the JSON processing library) the following:
// 1) Expect a field named employeeType in the JSON request.
// 2) Use the value of the employeeType field to determine which sub-class of the
// EmployeeDetailsDTO to instantiate.
@JsonTypeInfo(use = Id.NAME, property = "employeeType")
// The @JsonSubTypes annotation defines the mapping between the value of the employeeType
// field and the sub-class of the EmployeeDetailsDTO to instantiate.
@JsonSubTypes({
	@JsonSubTypes.Type(name = "REGULAR", value = RegularEmployeeDetailsDTO.class),
	@JsonSubTypes.Type(name = "REGULAR_MANAGER", value = RegularManagerDetailsDTO.class),
	@JsonSubTypes.Type(name = "DEPARTMENT_HEAD", value = DepartmentHeadDetailsDTO.class),
	@JsonSubTypes.Type(name = "CEO", value = CeoDetailsDTO.class)
})
public abstract class EmployeeDetailsDTO {
	private String name;
	private String employeeType;
}
