package com.jb.nexacroserver.domain.employee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jb.nexacroserver.domain.employee.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	@Query("""
			SELECT e
			FROM Employee e
			JOIN FETCH e.department
			JOIN FETCH e.position
""")
	List<Employee> findEmployees();

	@Query("""
			SELECT e
			FROM Employee e
			JOIN FETCH e.department
			JOIN FETCH e.position
			WHERE e.department.id = :departmentId
			""")
	List<Employee> findEmployeesByDepartment(Long departmentId);
}
