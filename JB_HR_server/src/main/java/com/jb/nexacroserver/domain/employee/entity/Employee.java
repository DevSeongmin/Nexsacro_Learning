package com.jb.nexacroserver.domain.employee.entity;

import com.jb.nexacroserver.core.entity.BaseEntity;
import com.jb.nexacroserver.domain.department.entity.Department;
import com.nexacro.java.xapi.license.A.E;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Employee extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "employee_id")
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Gender gender;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "department_id", nullable = false)
	private Department department;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "position_id", nullable = false)
	private Position position;

	Integer salary;

	public static Employee of(String name, String gender, Department department, Position position, Integer salary) {
		Employee employee = new Employee();
		employee.name = name;
		employee.gender = gender.equals("M") ? Gender.M : Gender.F;
		employee.department = department;
		employee.position = position;
		employee.salary = salary;
		return employee;
	}
}

