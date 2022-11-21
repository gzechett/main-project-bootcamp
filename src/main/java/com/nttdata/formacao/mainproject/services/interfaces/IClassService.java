package com.nttdata.formacao.mainproject.services.interfaces;

import com.nttdata.formacao.mainproject.entities.ClassEntity;

import java.util.List;

public interface IClassService {

    //Basic operations to Class

    ClassEntity getClass(long id);

    List<ClassEntity> getAllClasses();

    void addClass(ClassEntity classEntity);

    void delete(ClassEntity classEntity);
}
