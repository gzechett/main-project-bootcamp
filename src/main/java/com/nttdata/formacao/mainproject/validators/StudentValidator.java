package com.nttdata.formacao.mainproject.validators;

import com.nttdata.formacao.mainproject.annotations.Student;
import com.nttdata.formacao.mainproject.dto.SchoolYearDTO;
import com.nttdata.formacao.mainproject.entities.StudentEntity;
import org.springframework.web.reactive.function.client.WebClient;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class StudentValidator implements ConstraintValidator<Student, StudentEntity> {

    @Override
    public void initialize(Student constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(StudentEntity value, ConstraintValidatorContext context) {

        return validateSchoolYear(value.getAge(),value.getSchoolYear());
    }

    public boolean validateSchoolYear(int age, String schoolYear) {
        WebClient webClient = WebClient.create("http://localhost:8082");
        List<SchoolYearDTO> studentList = webClient.get().uri("/validate/educational-year/" + age).retrieve().bodyToFlux(SchoolYearDTO.class).collectList().block();

        return studentList.stream().anyMatch(student1 -> student1.getSchoolYear().equalsIgnoreCase(schoolYear));
    }
}
