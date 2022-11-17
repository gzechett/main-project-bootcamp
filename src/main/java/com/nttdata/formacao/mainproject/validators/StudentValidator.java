package com.nttdata.formacao.mainproject.validators;

import com.nttdata.formacao.mainproject.entities.StudentEntity;
import com.nttdata.formacao.mainproject.repositories.IStudentRepository;
import com.nttdata.formacao.mainproject.services.interfaces.IStudentService;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Component
public class StudentValidator {

    private IStudentService studentService;

    public void validateSchoolYear(StudentEntity student) {

        WebClient webClient = WebClient.create("http://localhost:8082");
        List<StudentEntity> studentList = webClient.get().uri("/validate/educational-year/" + student.getAge()).retrieve().bodyToFlux(StudentEntity.class).collectList().block();


        if(studentList.stream().anyMatch(student1 -> student1.getSchoolYear().equalsIgnoreCase(student.getSchoolYear()))) {
            System.out.println("Aprovado o ano");
        } else {
            System.out.println("Reprovado o ano");
        }
    }
}
