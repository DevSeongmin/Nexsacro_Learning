package com.jb.nexacroserver.domain.department.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jb.nexacroserver.domain.department.dto.DepartmentData;
import com.jb.nexacroserver.domain.department.service.DepartmentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/department")
@RequiredArgsConstructor
public class DepartmentController {

	private final DepartmentService departmentService;
	@GetMapping
	public ResponseEntity<List<DepartmentData.Search>> getDepartmentList() {
		return ResponseEntity.ok(departmentService.getDepartmentList());
	}
}
