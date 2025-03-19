package com.wissen.ems.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.List;

import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.wissen.ems.dto.EmployeeDetailsDTO;
import com.wissen.ems.dto.ManagerDetailsRetrievalDTO;
import com.wissen.ems.dto.RegularEmployeeDetailsDTO;
import com.wissen.ems.employeecreator.EmployeeCreator;
import com.wissen.ems.entity.Department;
import com.wissen.ems.entity.Employee;
import com.wissen.ems.entity.EmployeeType;
import com.wissen.ems.entity.EmploymentStatus;
import com.wissen.ems.repository.EmployeeRepository;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceImplUnitTests {
	@Mock
	private EmployeeCreator employeeCreator;

	@Mock
	private EmployeeRepository employeeRepository;

	@InjectMocks
	private EmployeeServiceImpl employeeService;

	@Test
	public void testCreateNewEmployee() {
		Employee newEmployee = new Employee();

		String newEmployeeName = "TUV";
		newEmployee.setName(newEmployeeName);

		String newEmployeeJobTitle = "Senior Software Engineer";
		newEmployee.setJobTitle(newEmployeeJobTitle);

		Department newEmployeeDepartment = new Department();
		int engineeringDepartmentId = 1;
		newEmployeeDepartment.setId(engineeringDepartmentId);
		newEmployee.setDepartment(newEmployeeDepartment);

		newEmployee.setType(EmployeeType.REGULAR);
		newEmployee.setStatus(EmploymentStatus.ACTIVE);

		Employee softwareEngineeringManager = new Employee();
		int softwareEngineeringManagerEmployeeId = 5;
		softwareEngineeringManager.setId(softwareEngineeringManagerEmployeeId);
		newEmployee.setManager(softwareEngineeringManager);

		when(employeeCreator.supportsCreation(any(EmployeeDetailsDTO.class))).thenReturn(true);
		when(employeeCreator.create(any(EmployeeDetailsDTO.class))).thenReturn(newEmployee);

		int newEmployeeId = 14;
		newEmployee.setId(newEmployeeId);

		when(employeeRepository.save(any(Employee.class))).thenReturn(newEmployee);

		RegularEmployeeDetailsDTO regularEmployeeDetailsDTO = new RegularEmployeeDetailsDTO();

		regularEmployeeDetailsDTO.setName(newEmployeeName);
		regularEmployeeDetailsDTO.setJobTitle(newEmployeeJobTitle);
		regularEmployeeDetailsDTO.setDepartmentId(engineeringDepartmentId);
		regularEmployeeDetailsDTO.setEmployeeType("REGULAR");
		regularEmployeeDetailsDTO.setManagerId(softwareEngineeringManagerEmployeeId);

		Employee savedEmployee = employeeService.save(regularEmployeeDetailsDTO);

		AssertionsForClassTypes.assertThat(savedEmployee).isNotNull();
		AssertionsForClassTypes.assertThat(savedEmployee.getId()).isEqualTo(newEmployeeId);
	}

	@Test
	public void testGetActiveManagersByDepartment() {
		Employee headOfEngineering = new Employee();
		int headOfEngineeringEmployeeId = 2;
		headOfEngineering.setId(headOfEngineeringEmployeeId);

		Employee softwareEngineeringManager = new Employee();
		int softwareEngineeringManagerEmployeeId = 5;
		softwareEngineeringManager.setId(softwareEngineeringManagerEmployeeId);

		List<Employee> activeEngineeringManagers = List.of(headOfEngineering, softwareEngineeringManager);

		when(employeeRepository.findActiveManagersByDepartment(anyInt())).thenReturn(activeEngineeringManagers);

		int engineeringDepartmentId = 1;
		List<ManagerDetailsRetrievalDTO> activeEngineeringManagersDto = employeeService.getActiveManagersByDepartment(engineeringDepartmentId);

		AssertionsForClassTypes.assertThat(activeEngineeringManagersDto.size()).isEqualTo(activeEngineeringManagers.size());

		for (int i = 0; i < activeEngineeringManagersDto.size(); i++) {
			Employee activeEngineeringManager = activeEngineeringManagers.get(i);
			ManagerDetailsRetrievalDTO activeEngineeringManagerDto = activeEngineeringManagersDto.get(i);

			AssertionsForClassTypes.assertThat(activeEngineeringManagerDto.getId()).isEqualTo(activeEngineeringManager.getId());
			AssertionsForClassTypes.assertThat(activeEngineeringManagerDto.getName()).isEqualTo(activeEngineeringManager.getName());
		}
	}
}
