package com.wissen.ems.repository;

import java.util.Optional;

import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.wissen.ems.entity.Department;
import com.wissen.ems.entity.Employee;
import com.wissen.ems.entity.EmployeeType;
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

		newEmployee.setName("NOP");
		newEmployee.setJobTitle("Senior Software Engineer");

		int engineeringDepartmentId = 1;
		Department engineering = entityManager.find(Department.class, engineeringDepartmentId);
		newEmployee.setDepartment(engineering);

		newEmployee.setType(EmployeeType.REGULAR);
		newEmployee.setStatus(EmploymentStatus.ACTIVE);

		int softwareEngineeringManagerEmployeeId = 5;
		Employee softwareEngineeringManager = entityManager.find(Employee.class, softwareEngineeringManagerEmployeeId);
		newEmployee.setManager(softwareEngineeringManager);

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
		int financeAnalystEmployeeId = 12;
		Employee financeAnalyst = entityManager.find(Employee.class, financeAnalystEmployeeId);

		String financeAnalystUpdatedName = "QRS";
		financeAnalyst.setName(financeAnalystUpdatedName);

		financeAnalyst = employeeRepository.save(financeAnalyst);

		AssertionsForClassTypes.assertThat(financeAnalyst.getName()).isEqualTo(financeAnalystUpdatedName);
	}

	@Test
	public void deleteHrCoordinator() {
		int hrCoordinatorEmployeeId = 10;
		employeeRepository.deleteById(hrCoordinatorEmployeeId);

		Employee hrCoordinator = entityManager.find(Employee.class, hrCoordinatorEmployeeId);

		AssertionsForClassTypes.assertThat(hrCoordinator).isNull();
	}
}
