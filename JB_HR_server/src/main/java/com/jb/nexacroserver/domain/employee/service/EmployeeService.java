package com.jb.nexacroserver.domain.employee.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.jb.nexacroserver.domain.employee.dto.EmployeeData;
import com.jb.nexacroserver.domain.employee.repository.EmployeeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeService {

	private final EmployeeRepository employeeRepository;

	public List<EmployeeData.Search> getEmployeeList(Long departmentId) {

		if (departmentId == null || departmentId == 0) {

			return employeeRepository.findEmployees()
				.stream()
				.map(EmployeeData.Search::fromEntity)
				.collect(Collectors.toList());
		}

		return employeeRepository.findEmployeesByDepartment(departmentId)
			.stream()
			.map(EmployeeData.Search::fromEntity)
			.collect(Collectors.toList());
	}



}
