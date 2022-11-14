package com.nttdata.formacao.mainproject.repositories;

import com.nttdata.formacao.mainproject.entities.ProfessorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProfessorRepository extends JpaRepository<ProfessorEntity, Long> {
}
