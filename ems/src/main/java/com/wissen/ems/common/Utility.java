package com.wissen.ems.common;

import com.wissen.ems.dto.RegularEmployeeDetailsDTO;

public final class Utility {
	private Utility() {
	}

	public static String assertNonNullEmptyOrBlank(String input, String exceptionMessage) {
		if (input == null || input.isEmpty() || input.isBlank()) {
			throw new IllegalArgumentException(exceptionMessage);
		}

		return input;
	}

	public static RegularEmployeeDetailsDTO getValidRegularEmployeeDetailsDto() {
		RegularEmployeeDetailsDTO regularEmployeeDetailsDTO = new RegularEmployeeDetailsDTO();

		String newEmployeeName = "WXY";
		regularEmployeeDetailsDTO.setName(newEmployeeName);

		String newEmployeeJobTitle = "Financial Management Associate";
		regularEmployeeDetailsDTO.setJobTitle(newEmployeeJobTitle);

		int financeDepartmentId = 3;
		regularEmployeeDetailsDTO.setDepartmentId(financeDepartmentId);

		regularEmployeeDetailsDTO.setEmployeeType("REGULAR");

		int financeManagerEmployeeId = 11;
		regularEmployeeDetailsDTO.setManagerId(financeManagerEmployeeId);

		return regularEmployeeDetailsDTO;
	}

	public static RegularEmployeeDetailsDTO getRegularEmployeeDetailsDtoWithNullEmployeeName() {
		RegularEmployeeDetailsDTO regularEmployeeDetailsDTO = new RegularEmployeeDetailsDTO();

		regularEmployeeDetailsDTO.setName(null);

		return regularEmployeeDetailsDTO;
	}

	public static RegularEmployeeDetailsDTO getRegularEmployeeDetailsDtoWithEmptyJobTitle() {
		RegularEmployeeDetailsDTO regularEmployeeDetailsDTO = new RegularEmployeeDetailsDTO();

		regularEmployeeDetailsDTO.setName("ZAB");
		regularEmployeeDetailsDTO.setJobTitle("");

		return regularEmployeeDetailsDTO;
	}

	public static RegularEmployeeDetailsDTO getRegularEmployeeDetailsDtoWithInvalidDepartmentId() {
		RegularEmployeeDetailsDTO regularEmployeeDetailsDTO = new RegularEmployeeDetailsDTO();

		regularEmployeeDetailsDTO.setName("ZAB");
		regularEmployeeDetailsDTO.setJobTitle("Marketing Manager");
		regularEmployeeDetailsDTO.setDepartmentId(4);

		return regularEmployeeDetailsDTO;
	}

	public static RegularEmployeeDetailsDTO getRegularEmployeeDetailsDtoWithInvalidManagerId() {
		RegularEmployeeDetailsDTO regularEmployeeDetailsDTO = new RegularEmployeeDetailsDTO();

		regularEmployeeDetailsDTO.setName("CDE");
		regularEmployeeDetailsDTO.setJobTitle("Principal Software Engineer");
		regularEmployeeDetailsDTO.setDepartmentId(1);
		regularEmployeeDetailsDTO.setManagerId(20);

		return regularEmployeeDetailsDTO;
	}

	public static RegularEmployeeDetailsDTO getRegularEmployeeDetailsDtoWithEmployeeReportingToCeo() {
		RegularEmployeeDetailsDTO regularEmployeeDetailsDTO = new RegularEmployeeDetailsDTO();

		regularEmployeeDetailsDTO.setName("CDE");
		regularEmployeeDetailsDTO.setJobTitle("Principal Software Engineer");
		regularEmployeeDetailsDTO.setDepartmentId(1);
		regularEmployeeDetailsDTO.setManagerId(1);

		return regularEmployeeDetailsDTO;
	}

	public static RegularEmployeeDetailsDTO getRegularEmployeeDetailsDtoWithEmployeeReportingToManagerInDifferentDepartment() {
		RegularEmployeeDetailsDTO regularEmployeeDetailsDTO = new RegularEmployeeDetailsDTO();

		regularEmployeeDetailsDTO.setName("CDE");
		regularEmployeeDetailsDTO.setJobTitle("Principal Software Engineer");
		regularEmployeeDetailsDTO.setDepartmentId(1);
		regularEmployeeDetailsDTO.setManagerId(3);

		return regularEmployeeDetailsDTO;
	}
}
