package com.wissen.ems.dto;

import com.fasterxml.jackson.annotation.JsonTypeName;

// When the employeeType field has a value CEO, instantiate this DTO
@JsonTypeName("CEO")
public class CeoDetailsDTO extends EmployeeDetailsDTO {
}
