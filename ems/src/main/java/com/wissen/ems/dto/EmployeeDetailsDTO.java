package com.wissen.ems.dto;

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
// 3) The mapping between the values of employeeType field and the sub-classes will be
// determined by the @JsonTypeName annotation on the sub-class.
@JsonTypeInfo(use = Id.NAME, property = "employeeType")
public abstract class EmployeeDetailsDTO {
	private String name;
	private String employeeType;
}
