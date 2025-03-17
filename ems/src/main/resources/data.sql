-- This file will populate the departments and employees tables
-- in the in-memory H2 database with some initial data every
-- time the application starts up. This is helpful because the
-- moment the application is shutdown, all the data in the
-- in-memory H2 database is lost.

-- Insert CEO
INSERT INTO employees (name, job_title, department_id, status, manager_id) VALUES ('ABC', 'CEO', NULL, 'ACTIVE', NULL)

-- Create First Department
INSERT INTO departments (name, head_id) VALUES ('Engineering', 1)

-- Create the Head of Engineering
INSERT INTO employees (name, job_title, department_id, status, manager_id) VALUES ('DEF', 'Head of Engineering', 1, 'ACTIVE', 1)

-- Make the above employee the Head of Engineering
UPDATE departments set head_id = 2 where id = 1

-- Create Second Department
INSERT INTO departments (name, head_id) VALUES ('HR', 1)

-- Create the Head of HR
INSERT INTO employees (name, job_title, department_id, status, manager_id) VALUES ('GHI', 'Head of HR', 2, 'ACTIVE', 1)

-- Make the above employee the Head of HR
UPDATE departments set head_id = 3 where id = 2

-- Create Third Department
INSERT INTO departments (name, head_id) VALUES ('Finance', 1)

-- Create the Head of Finance
INSERT INTO employees (name, job_title, department_id, status, manager_id) VALUES ('JKL', 'Head of Finance', 3, 'ACTIVE', 1)

-- Make the above employee the Head of Finance
UPDATE departments set head_id = 4 where id = 3

-- Insert Regular Employees
INSERT INTO employees (name, job_title, department_id, status, manager_id) VALUES ('MNO', 'Principal Software Engineer', 1, 'ACTIVE', 2)
INSERT INTO employees (name, job_title, department_id, status, manager_id) VALUES ('PQR', 'Software Engineer', 1, 'ACTIVE', 2)

INSERT INTO employees (name, job_title, department_id, status, manager_id) VALUES ('STU', 'HR Specialist', 2, 'ACTIVE', 3)
INSERT INTO employees (name, job_title, department_id, status, manager_id) VALUES ('STU', 'HR Coordinator', 2, 'ACTIVE', 3)

INSERT INTO employees (name, job_title, department_id, status, manager_id) VALUES ('VWX', 'Finance Analyst', 3, 'ACTIVE', 4)
INSERT INTO employees (name, job_title, department_id, status, manager_id) VALUES ('YZA', 'Accountant', 3, 'ACTIVE', 4)
