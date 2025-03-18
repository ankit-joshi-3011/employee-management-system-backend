## Assumptions:
1. The following code describes the Department entity. The purpose of each annotation and attribute is described in the comment associated with it:
```
public class Department {
	@Id // The ID of a department has to be its primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // We will auto-generate the ID in a way that makes it unique
	private int id;

	@Column(nullable = false, unique = true) // The name of a department has to be unique and it cannot be null
	private String name;

	@OneToOne // Each department has exactly one department head
	// The foreign key column name is head_id and it references the id column (primary key) of the employees table.
	@JoinColumn(name = "head_id", referencedColumnName = "id", nullable = false)
	private Employee departmentHead;

	// One department can have multiple employees. This relationship is managed by
	// the department field inside the employees table. With mappedBy, JPA stores
	// the foreign key directly in the employees table instead of creating another
	// department-employees table.
	@OneToMany(mappedBy = "department")
	private List<Employee> employees;
}
```
2. The following code describes the Employee entity. The purpose of each annotation and attribute is described in the comment associated with it:
```
public class Employee {
	@Id // The ID of an employee has to be its primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // We will auto-generate the ID in a way that makes it unique
	private int id;

	@Column(nullable = false) // The name of an employee cannot be null
	private String name;

	@Column(nullable = false) // The job title of an employee cannot be null
	private String jobTitle;

	@ManyToOne // Many employees can belong to one department
	// Each employee must belong to a department except the CEO (The CEO oversees all departments and therefore
	// technically they don't belong to any department). The foreign key column name is department_id and it
	// references the id column (primary key) of the department table.
	@JoinColumn(name = "department_id", referencedColumnName = "id")
	private Department department;

	@Enumerated(EnumType.STRING) // Store the employee type of an employee ('REGULAR', 'REGULAR_MANAGER', 'DEPARTMENT_HEAD', 'CEO') as a string
	@Column(nullable = false) // The employee has to have a type
	private EmployeeType type;

	@Enumerated(EnumType.STRING) // Store the employment status of an employee ('ACTIVE', 'RETIRED', 'TERMINATED') as a string
	@Column(nullable = false) // The employee has to have an employment status
	private EmploymentStatus status;

	@ManyToOne // Many employees can have the same manager
	// Each employee will have a manager except the CEO (It is logically incorrect
	// for a CEO to be their own manager as a manager should oversee someone else).
	// The foreign key column name is manager_id and it references the id column
	// (primary key) of the employees table.
	@JoinColumn(name = "manager_id", referencedColumnName = "id")
	private Employee manager;

	// An employee who is a manager can have multiple direct reports. This
	// relationship is managed by the manager field inside the employees table.
	@OneToMany(mappedBy = "manager")
	private List<Employee> directReports;
}
```
3. Even though the CEO is an employee of the organization, they should not have a manager. This is because a manager should oversee someone else. No employee of the company is overseeing the CEO and it is seems incorrect to state that the CEO is overseeing themselves.
4. Overseeing an entire department like Engineering or Product Management is a huge task. Therefore, the department heads of each department are different employees.
5. Even though the CEO is an employee of the organization, they don't belong to a department. This is because they oversee all departments.
6. When a new department is created, the CEO signs up as that department's head until they can find an employee who can take over that responsibility.

## Steps required to run tests
To run the JUnit tests included with the project, follow the below mentioned steps:
 * Expand the ems project folder
 * Navigate to the src/test/java folder
 * Open the com.wissen.ems.repository package
 * Double click on the DepartmentRepositoryTests.java class to open it
 * Right click anywhere in the file
 * Select the Run As -> JUnit Test option
 * Once the tests run the output would be visible in the JUnit tab
 * Similar steps can be used to run the following test classes:
   * EmployeeManagementSystemApplicationTests.java
   * EmployeeRepositoryTests.java

## Steps required to create the application JAR:
To create a JAR file for the application in Spring Tool Suite, follow the below mentioned steps:
 * Right click on the ems project folder
 * Navigate to Run As -> Maven build...
 * Add the name ems-build-jar for the run configuration
 * In the Goals field, add ```clean package```
 * Apply the changes
 * Run the configuration
 * Once the build is successful the output JAR file (ems-0.0.1-SNAPSHOT.jar) will be in the ems\target directory

## Steps required to run the application JAR via the command-line:
1. To use the development Spring profile (which uses the H2 in-memory database), run the following command: ```java -jar target\ems-0.0.1-SNAPSHOT.jar --spring.profiles.active=dev```
2. To use the production Spring profile (which uses the MySQL database), run the following command: ```java -jar target\ems-0.0.1-SNAPSHOT.jar --spring.profiles.active=prod```
