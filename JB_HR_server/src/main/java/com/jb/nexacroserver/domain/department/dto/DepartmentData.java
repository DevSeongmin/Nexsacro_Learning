package com.jb.nexacroserver.domain.department.dto;

import com.jb.nexacroserver.domain.department.entity.Department;

import lombok.Data;

public class DepartmentData {
	@Data
	public static class Search {
		private Long departmentId;
		private String departmentName;

		public static Search fromEntity(Department department) {
			Search search = new Search();
			search.departmentId = department.getId();
			search.departmentName = department.getDepartmentName();
			return search;
		}
	}
}
