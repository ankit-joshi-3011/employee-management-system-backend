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

		String newEmployeeName = "WXY";
		regularEmployeeDetailsDTO.setName(newEmployeeName);

		String newEmployeeJobTitle = "";
		regularEmployeeDetailsDTO.setJobTitle(newEmployeeJobTitle);

		return regularEmployeeDetailsDTO;
	}

	public static RegularEmployeeDetailsDTO getRegularEmployeeDetailsDtoWithInvalidDepartmentId() {
		RegularEmployeeDetailsDTO regularEmployeeDetailsDTO = new RegularEmployeeDetailsDTO();

		String newEmployeeName = "ZAB";
		regularEmployeeDetailsDTO.setName(newEmployeeName);

		String newEmployeeJobTitle = "Marketing Manager";
		regularEmployeeDetailsDTO.setJobTitle(newEmployeeJobTitle);

		int nonExistentDepartmentId = 4;
		regularEmployeeDetailsDTO.setDepartmentId(nonExistentDepartmentId);

		return regularEmployeeDetailsDTO;
	}

	public static RegularEmployeeDetailsDTO getRegularEmployeeDetailsDtoWithInvalidManagerId() {
		RegularEmployeeDetailsDTO regularEmployeeDetailsDTO = new RegularEmployeeDetailsDTO();

		String newEmployeeName = "WXY";
		regularEmployeeDetailsDTO.setName(newEmployeeName);

		String newEmployeeJobTitle = "Financial Management Associate";
		regularEmployeeDetailsDTO.setJobTitle(newEmployeeJobTitle);

		int financeDepartmentId = 3;
		regularEmployeeDetailsDTO.setDepartmentId(financeDepartmentId);

		int nonExistentManagerId = 20;
		regularEmployeeDetailsDTO.setManagerId(nonExistentManagerId);

		return regularEmployeeDetailsDTO;
	}

	public static RegularEmployeeDetailsDTO getRegularEmployeeDetailsDtoWithEmployeeReportingToCeo() {
		RegularEmployeeDetailsDTO regularEmployeeDetailsDTO = new RegularEmployeeDetailsDTO();

		String newEmployeeName = "WXY";
		regularEmployeeDetailsDTO.setName(newEmployeeName);

		String newEmployeeJobTitle = "Financial Management Associate";
		regularEmployeeDetailsDTO.setJobTitle(newEmployeeJobTitle);

		int financeDepartmentId = 3;
		regularEmployeeDetailsDTO.setDepartmentId(financeDepartmentId);

		int ceoId = 1;
		regularEmployeeDetailsDTO.setManagerId(ceoId);

		return regularEmployeeDetailsDTO;
	}

	public static RegularEmployeeDetailsDTO getRegularEmployeeDetailsDtoWithEmployeeReportingToManagerInDifferentDepartment() {
		RegularEmployeeDetailsDTO regularEmployeeDetailsDTO = new RegularEmployeeDetailsDTO();

		String newEmployeeName = "WXY";
		regularEmployeeDetailsDTO.setName(newEmployeeName);

		String newEmployeeJobTitle = "Financial Management Associate";
		regularEmployeeDetailsDTO.setJobTitle(newEmployeeJobTitle);

		int financeDepartmentId = 3;
		regularEmployeeDetailsDTO.setDepartmentId(financeDepartmentId);

		int headOfEngineeringEmployeeId = 2;
		regularEmployeeDetailsDTO.setManagerId(headOfEngineeringEmployeeId);

		return regularEmployeeDetailsDTO;
	}
}
