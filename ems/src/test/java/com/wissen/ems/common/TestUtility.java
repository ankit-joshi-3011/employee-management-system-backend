package com.wissen.ems.common;

import java.util.List;

import org.assertj.core.api.AssertionsForClassTypes;

import com.wissen.ems.dto.DepartmentDetailsDTO;

public final class TestUtility {
	private TestUtility() {
	}

	public static void assertDepartmentDetailsDtosAreCorrect(List<DepartmentDetailsDTO> allDepartmentDetailsDto) {
		int[] departmentIds = new int[] { 1, 2, 3 };
		String[] departmentNames = new String[] { "Engineering", "HR", "Finance" };

		AssertionsForClassTypes.assertThat(allDepartmentDetailsDto.size()).isEqualTo(departmentIds.length);

		for (int i = 0; i < allDepartmentDetailsDto.size(); i++) {
			DepartmentDetailsDTO departmentDetailsDto = allDepartmentDetailsDto.get(i);

			AssertionsForClassTypes.assertThat(departmentDetailsDto.getId()).isEqualTo(departmentIds[i]);
			AssertionsForClassTypes.assertThat(departmentDetailsDto.getName()).isEqualTo(departmentNames[i]);
		}
	}
}
