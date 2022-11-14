package com.nttdata.formacao.mainproject.services.implementation;

import com.nttdata.formacao.mainproject.entities.StudentEntity;
import com.nttdata.formacao.mainproject.repositories.IStudentRepository;
import com.nttdata.formacao.mainproject.services.interfaces.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService implements IStudentService {

    @Autowired
    IStudentRepository studentRepository;

    @Override
    public StudentEntity getStudent(long id) {
        return studentRepository.findById(id).get();
    }

    @Override
    public List<StudentEntity> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public void addStudent(StudentEntity student) {
        studentRepository.save(student);
    }

    @Override
    public void delete(StudentEntity student) {
        studentRepository.delete(student);
    }
}
