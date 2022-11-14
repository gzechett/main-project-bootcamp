package com.nttdata.formacao.mainproject.repositories;

import com.nttdata.formacao.mainproject.entities.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IStudentRepository extends JpaRepository<StudentEntity, Long> {
}
