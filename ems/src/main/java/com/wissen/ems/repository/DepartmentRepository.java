package com.wissen.ems.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wissen.ems.entity.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {
}
