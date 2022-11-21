package com.nttdata.formacao.mainproject.validators;

import com.nttdata.formacao.mainproject.annotations.Course;
import com.nttdata.formacao.mainproject.entities.CourseEntity;
import com.nttdata.formacao.mainproject.services.interfaces.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

@Component
public class CourseValidator implements ConstraintValidator<Course, CourseEntity>{


    @Autowired
    private ICourseService courseService;

    @Override
    public void initialize(Course constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(CourseEntity course, ConstraintValidatorContext context) {
        return validateExistingCourse(course);
    }

    public boolean validateExistingCourse(CourseEntity course) {
        WebClient webClient = WebClient.create("http://localhost:8082");
        List<CourseEntity> courses = webClient.get().uri("/courses").retrieve().bodyToFlux(CourseEntity.class).collectList().block();

        return courses.stream().anyMatch(course1 -> course1.getName().equalsIgnoreCase(course.getName()) &&  course1.getArea().equalsIgnoreCase(course.getArea()));
    }
}
