package com.jb.nexacroserver.domain.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jb.nexacroserver.domain.employee.entity.Position;

public interface PositionRepository extends JpaRepository<Position, Long> {
}