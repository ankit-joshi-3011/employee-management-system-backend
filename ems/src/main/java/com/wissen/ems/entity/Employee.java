package com.wissen.ems.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "employees")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String jobTitle;

	// Many employees can belong to one department. Each employee must belong to a
	// department except the CEO (The CEO oversees all departments and therefore
	// technically they don't belong to any department). The foreign key column name
	// is department_id and it references the id column (primary key) of the
	// department table.
	@ManyToOne
	@JoinColumn(name = "department_id", referencedColumnName = "id")
	private Department department;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private EmployeeType type;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private EmploymentStatus status;

	// Each employee will have a manager except the CEO (It is logically incorrect
	// for a CEO to be their own manager as a manager should oversee someone else).
	// Many employees can have the same manager. The foreign key column name is
	// manager_id and it references the id column (primary key) of the employees
	// table.
	@ManyToOne
	@JoinColumn(name = "manager_id", referencedColumnName = "id")
	private Employee manager;

	// An employee who is a manager can have multiple direct reports. This
	// relationship is managed by the manager field inside the employees table.
	@OneToMany(mappedBy = "manager")
	private List<Employee> directReports;
}
