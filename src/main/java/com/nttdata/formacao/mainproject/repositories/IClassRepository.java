package com.nttdata.formacao.mainproject.repositories;

import com.nttdata.formacao.mainproject.entities.ClassEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IClassRepository extends JpaRepository<ClassEntity, Long> {
}
