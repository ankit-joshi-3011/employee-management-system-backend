package com.wissen.ems.common;

import java.util.List;

import org.assertj.core.api.AssertionsForClassTypes;

import com.wissen.ems.dto.DepartmentDetailsDTO;
import com.wissen.ems.dto.ManagerDetailsRetrievalDTO;

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

	public static void assertManagerDetailsRetrievalDtosAreCorrect(List<ManagerDetailsRetrievalDTO> responseActiveEngineeringManagersDto) {
		int[] activeEngineeringManagerIds = new int[] { 2, 5 };
		String[] activeEngineeringManagerNames = new String[] { "DEF", "MNO" };

		AssertionsForClassTypes.assertThat(responseActiveEngineeringManagersDto.size()).isEqualTo(activeEngineeringManagerIds.length);

		for (int i = 0; i < responseActiveEngineeringManagersDto.size(); i++) {
			ManagerDetailsRetrievalDTO activeEngineeringManagerDto = responseActiveEngineeringManagersDto.get(i);

			AssertionsForClassTypes.assertThat(activeEngineeringManagerDto.getId()).isEqualTo(activeEngineeringManagerIds[i]);
			AssertionsForClassTypes.assertThat(activeEngineeringManagerDto.getName()).isEqualTo(activeEngineeringManagerNames[i]);
		}
	}
}
