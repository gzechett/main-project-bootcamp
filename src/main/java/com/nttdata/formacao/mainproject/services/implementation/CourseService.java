package com.nttdata.formacao.mainproject.services.implementation;

import com.nttdata.formacao.mainproject.entities.CourseEntity;
import com.nttdata.formacao.mainproject.repositories.ICourseRepository;
import com.nttdata.formacao.mainproject.services.interfaces.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService implements ICourseService {

    @Autowired
    private ICourseRepository courseRepository;

    @Override
    public CourseEntity getCourse(long id) {
        return courseRepository.findById(id).get();
    }

    @Override
    public List<CourseEntity> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public void addCourse(CourseEntity course) {
        courseRepository.save(course);
    }

    @Override
    public void delete(CourseEntity course) {
        courseRepository.delete(course);
    }
}
