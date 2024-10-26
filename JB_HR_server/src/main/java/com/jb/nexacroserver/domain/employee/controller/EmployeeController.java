package com.jb.nexacroserver.domain.employee.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jb.nexacroserver.domain.employee.dto.EmployeeData;
import com.jb.nexacroserver.domain.employee.entity.Employee;
import com.jb.nexacroserver.domain.employee.service.EmployeeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/employee")
@RequiredArgsConstructor
public class EmployeeController {

	private final EmployeeService employeeService;

	@GetMapping
	public ResponseEntity<List<EmployeeData.Search>> getEmployeeList(@RequestParam(required = false) Long departmentId) {
		return ResponseEntity.ok(employeeService.getEmployeeList(departmentId));
	}
}