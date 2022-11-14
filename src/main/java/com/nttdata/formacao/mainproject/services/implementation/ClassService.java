package com.nttdata.formacao.mainproject.services.implementation;

import com.nttdata.formacao.mainproject.entities.ClassEntity;
import com.nttdata.formacao.mainproject.repositories.IClassRepository;
import com.nttdata.formacao.mainproject.services.interfaces.IClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassService implements IClassService {

    @Autowired
    private IClassRepository classRepository;

    @Override
    public ClassEntity getClass(long id) {
        return classRepository.findById(id).get();
    }

    @Override
    public List<ClassEntity> getAllClasses() {
        return classRepository.findAll();
    }

    @Override
    public void addClass(ClassEntity classEntity) {
        classRepository.save(classEntity);
    }

    @Override
    public void delete(ClassEntity classEntity) {
        classRepository.delete(classEntity);
    }
}
