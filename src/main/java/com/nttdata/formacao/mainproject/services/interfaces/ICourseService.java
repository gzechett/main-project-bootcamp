package com.nttdata.formacao.mainproject.services.interfaces;

import com.nttdata.formacao.mainproject.entities.CourseEntity;
import com.nttdata.formacao.mainproject.entities.StudentEntity;

import java.util.List;

public interface ICourseService {

    //Basic operations to Course

    CourseEntity getCourse(long id);

    List<CourseEntity> getAllCourses();

    void addCourse(CourseEntity course);

    void delete(CourseEntity course);
}
