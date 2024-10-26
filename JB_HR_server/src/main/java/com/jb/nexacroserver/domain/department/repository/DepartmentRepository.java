package com.jb.nexacroserver.domain.department.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jb.nexacroserver.domain.department.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
