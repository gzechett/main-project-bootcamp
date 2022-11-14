package com.nttdata.formacao.mainproject.services.interfaces;

import com.nttdata.formacao.mainproject.entities.StudentEntity;

import java.util.List;

public interface IStudentService {

    //Basic operations to Student

    StudentEntity getStudent(long id);

    List<StudentEntity> getAllStudents();

    void addStudent(StudentEntity student);

    void delete(StudentEntity student);
}
