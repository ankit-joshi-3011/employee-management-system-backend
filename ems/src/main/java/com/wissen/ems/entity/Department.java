package com.wissen.ems.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "departments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Department {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false, unique = true)
	private String name;

	// Each department has exactly one department head. The foreign key column name
	// is head_id and it references the id column (primary key) of the employees
	// table.
	@OneToOne
	@JoinColumn(name = "head_id", referencedColumnName = "id", nullable = false)
	private Employee departmentHead;

	// One department can have multiple employees. This relationship is managed by
	// the department field inside the employees table. With mappedBy, JPA stores
	// the foreign key directly in the employees table instead of creating another
	// department-employees table.
	@OneToMany(mappedBy = "department")
	private List<Employee> employees;
}
