package com.wissen.ems.repository;

import java.util.Optional;

import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.wissen.ems.entity.Department;
import com.wissen.ems.entity.Employee;

@DataJpaTest
public class DepartmentRepositoryTests {
	private DepartmentRepository departmentRepository;
	private TestEntityManager entityManager;

	@Autowired
	public DepartmentRepositoryTests(DepartmentRepository departmentRepository, TestEntityManager entityManager) {
		this.departmentRepository = departmentRepository;
		this.entityManager = entityManager;
	}

	@Test
	public void testCreateSalesDepartment() {
		Department newDepartment = new Department();

		newDepartment.setName("Sales");

		int ceoEmployeeId = 1;
		Employee ceo = entityManager.find(Employee.class, ceoEmployeeId);
		newDepartment.setDepartmentHead(ceo);

		newDepartment = departmentRepository.save(newDepartment);

		AssertionsForClassTypes.assertThat(newDepartment.getId()).isGreaterThan(0);
	}

	@Test
	public void testRetrieveEngineeringDepartment() {
		int engineeringDepartmentId = 1;
		Optional<Department> engineering = departmentRepository.findById(engineeringDepartmentId);

		AssertionsForClassTypes.assertThat(engineering.isPresent()).isTrue();
	}

	@Test
	public void testUpdateNameOfHrDepartment() {
		int idOfDepartmentToUpdate = 2;
		Department departmentToUpdate = entityManager.find(Department.class, idOfDepartmentToUpdate);

		String updatedDepartmentName = "Marketing";
		departmentToUpdate.setName(updatedDepartmentName);

		departmentToUpdate = departmentRepository.save(departmentToUpdate);

		AssertionsForClassTypes.assertThat(departmentToUpdate.getName()).isEqualTo(updatedDepartmentName);
	}

	@Test
	public void testDeleteFinanceDepartment() {
		int financeDepartmentId = 3;
		departmentRepository.deleteById(financeDepartmentId);

		Department finance = entityManager.find(Department.class, financeDepartmentId);

		AssertionsForClassTypes.assertThat(finance).isNull();
	}
}
