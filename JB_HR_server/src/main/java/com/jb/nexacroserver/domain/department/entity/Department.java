package com.jb.nexacroserver.domain.department.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Department {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "department_id")
	private Long id;

	@Column(nullable = false)
	private String departmentCode;

	@Column(nullable = false)
	private String departmentName;
}
