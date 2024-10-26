package com.jb.nexacroserver.domain.department.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jb.nexacroserver.domain.department.dto.DepartmentData;
import com.jb.nexacroserver.domain.department.repository.DepartmentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DepartmentService {

	private final DepartmentRepository departmentRepository;

	public List<DepartmentData.Search> getDepartmentList() {
		return departmentRepository.findAll().stream().map(DepartmentData.Search::fromEntity).toList();
	}
}
