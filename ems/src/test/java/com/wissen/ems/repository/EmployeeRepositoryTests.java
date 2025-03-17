package com.wissen.ems.repository;

import java.util.Optional;

import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.wissen.ems.entity.Department;
import com.wissen.ems.entity.Employee;
import com.wissen.ems.entity.EmploymentStatus;

@DataJpaTest
public class EmployeeRepositoryTests {
	private EmployeeRepository employeeRepository;
	private TestEntityManager entityManager;

	@Autowired
	public EmployeeRepositoryTests(EmployeeRepository employeeRepository, TestEntityManager entityManager) {
		this.employeeRepository = employeeRepository;
		this.entityManager = entityManager;
	}

	@Test
	public void createNewEmployee() {
		Employee newEmployee = new Employee();

		newEmployee.setName("ABC");
		newEmployee.setJobTitle("Senior Software Engineer");

		int engineeringDepartmentId = 1;
		Department engineering = entityManager.find(Department.class, engineeringDepartmentId);
		newEmployee.setDepartment(engineering);

		newEmployee.setStatus(EmploymentStatus.ACTIVE);

		int headOfEngineeringEmployeeId = 2;
		Employee headOfEngineering = entityManager.find(Employee.class, headOfEngineeringEmployeeId);
		newEmployee.setManager(headOfEngineering);

		newEmployee = employeeRepository.save(newEmployee);

		AssertionsForClassTypes.assertThat(newEmployee.getId()).isGreaterThan(0);
	}

	@Test
	public void retrieveHeadOfHr() {
		int headOfHrEmployeeId = 3;
		Optional<Employee> headOfHr = employeeRepository.findById(headOfHrEmployeeId);

		AssertionsForClassTypes.assertThat(headOfHr.isPresent()).isTrue();
	}

	@Test
	public void updateNameOfFinanceAnalyst() {
		int financeAnalystEmployeeId = 9;

		Employee financeAnalyst = entityManager.find(Employee.class, financeAnalystEmployeeId);

		String financeAnalystUpdatedName = "BCD";
		financeAnalyst.setName(financeAnalystUpdatedName);

		financeAnalyst = employeeRepository.save(financeAnalyst);

		AssertionsForClassTypes.assertThat(financeAnalyst.getName()).isEqualTo(financeAnalystUpdatedName);
	}

	@Test
	public void deleteHrCoordinator() {
		int hrCoordinatorEmployeeId = 8;

		employeeRepository.deleteById(hrCoordinatorEmployeeId);

		Employee hrCoordinator = entityManager.find(Employee.class, hrCoordinatorEmployeeId);

		AssertionsForClassTypes.assertThat(hrCoordinator).isNull();
	}
}
