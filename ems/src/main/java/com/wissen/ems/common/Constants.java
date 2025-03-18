package com.wissen.ems.common;

public final class Constants {
	private Constants() {
	}

	public static final String UNSUPPORTED_EMPLOYEE_TYPE_EXCEPTION_MESSAGE = "Unsupported employee type";
	public static final String EMPLOYEE_NAME_NULL_OR_EMPTY_EXCEPTION_MESSAGE = "Employee's name cannot be null or empty";
	public static final String EMPLOYEE_JOB_TITLE_NULL_OR_EMPTY_EXCEPTION_MESSAGE = "Employee's job title cannot be null or empty";
	public static final String INVALID_DEPARTMENT_ID_EXCEPTION_MESSAGE = "A regular employee should belong to an existing department";
	public static final String INVALID_MANAGER_ID_EXCEPTION_MESSAGE = "A regular employee should report to an existing manager";
}
