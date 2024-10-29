package com.jb.nexacroserver.domain.department.dto;

import com.jb.nexacroserver.domain.department.entity.Department;

import lombok.Data;


@Data
public class DepartmentSearch {
	private Long departmentId;
	private String departmentName;

	public static DepartmentSearch fromEntity(Department department) {
		DepartmentSearch departmentSearch = new DepartmentSearch();
		departmentSearch.departmentId = department.getId();
		departmentSearch.departmentName = department.getDepartmentName();
		return departmentSearch;
	}
}