package com.jb.nexacroserver.domain.employee.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jb.nexacroserver.domain.department.entity.Department;
import com.jb.nexacroserver.domain.department.repository.DepartmentRepository;
import com.jb.nexacroserver.domain.employee.dto.EmployeeSearch;
import com.jb.nexacroserver.domain.employee.entity.Employee;
import com.jb.nexacroserver.domain.employee.entity.Position;
import com.jb.nexacroserver.domain.employee.repository.PositionRepository;
import com.jb.nexacroserver.domain.employee.repository.EmployeeRepository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeService {

	private final EmployeeRepository employeeRepository;
	private final DepartmentRepository departmentRepository;
	private final PositionRepository positionRepository;
	private final EntityManager em;

	public List<EmployeeSearch> getEmployeeList(Long departmentId) {

		if (departmentId == null || departmentId == 0) {

			return employeeRepository.findEmployees()
				.stream()
				.map(EmployeeSearch::fromEntity)
				.collect(Collectors.toList());
		}

		return employeeRepository.findEmployeesByDepartment(departmentId)
			.stream()
			.map(EmployeeSearch::fromEntity)
			.collect(Collectors.toList());
	}

	@Transactional
	public void deleteEmployee(Long employeeId) {
		employeeRepository.deleteById(employeeId);
	}

	@Transactional
	public void insertEmployee(String name, String gender, Long departmentId, Long positionId, Integer salary) {
		Department department = departmentRepository.findById(departmentId).orElseThrow(
			() -> new IllegalArgumentException("Department not found: " + departmentId)
		);

		Position position = positionRepository.findById(positionId).orElseThrow(
			() -> new IllegalArgumentException("Position not found: " + positionId)
		);

		employeeRepository.save(
			Employee.of(name, gender, department, position, salary)
		);
	}
}
