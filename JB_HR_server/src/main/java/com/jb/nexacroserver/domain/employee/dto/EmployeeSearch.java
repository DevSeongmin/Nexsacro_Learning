package com.jb.nexacroserver.domain.employee.dto;

import java.time.LocalDateTime;

import com.jb.nexacroserver.domain.employee.entity.Employee;
import com.jb.nexacroserver.domain.employee.entity.Gender;

import lombok.Data;

@Data
public class EmployeeSearch {

	private Long employeeId;
	private String employeeName;
	private Gender gender;
	private String departmenCode;
	private String departmentName;
	private String positionCode;
	private String positionName;
	private Integer salary;
	private LocalDateTime hireDate;

	public static EmployeeSearch fromEntity(Employee employee) {
		EmployeeSearch search = new EmployeeSearch();
		search.employeeId = employee.getId();
		search.employeeName = employee.getName();
		search.gender = employee.getGender();
		search.departmenCode = employee.getDepartment().getDepartmentCode();
		search.departmentName = employee.getDepartment().getDepartmentName();
		search.positionCode = employee.getPosition().getPositionCode();
		search.positionName = employee.getPosition().getPositionName();
		search.salary = employee.getSalary();
		search.hireDate = employee.getHire_date();
		return search;
	}

}
