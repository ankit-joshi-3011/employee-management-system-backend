package com.wissen.ems.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.wissen.ems.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	@Query("SELECT e FROM Employee e WHERE e.department.id = :departmentId AND (e.type = 'REGULAR_MANAGER' OR e.type = 'DEPARTMENT_HEAD') AND e.status = 'ACTIVE'")
	List<Employee> findActiveManagersByDepartment(@Param("departmentId") int departmentId);
}
