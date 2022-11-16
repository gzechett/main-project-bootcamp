package com.nttdata.formacao.mainproject.validators;

import com.nttdata.formacao.mainproject.entities.CourseEntity;
import com.nttdata.formacao.mainproject.services.interfaces.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Component
public class CourseValidator {

    @Autowired
    private ICourseService courseService;

    public void validateCourse(CourseEntity course) {
        WebClient webClient = WebClient.create("http://localhost:8082");
        List<CourseEntity> courses = webClient.get().uri("/courses").retrieve().bodyToFlux(CourseEntity.class).collectList().block();

        System.out.println(courses.contains(course) ? "Curso Aprovado" : "Curso Reprovado");
        System.out.println(courses.stream().anyMatch(
                course1 ->
                        course1.getName().equalsIgnoreCase(course.getName()) && course1.getArea().equalsIgnoreCase(course.getArea())) ?
                "Curso Aprovado" : "Curso Reprovado");
    }
}
