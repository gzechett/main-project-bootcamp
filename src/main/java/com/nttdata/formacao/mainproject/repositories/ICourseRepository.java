package com.nttdata.formacao.mainproject.repositories;

import com.nttdata.formacao.mainproject.entities.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICourseRepository extends JpaRepository<CourseEntity, Long> {
}
