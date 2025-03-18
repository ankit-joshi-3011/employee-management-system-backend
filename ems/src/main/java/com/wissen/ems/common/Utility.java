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
}
