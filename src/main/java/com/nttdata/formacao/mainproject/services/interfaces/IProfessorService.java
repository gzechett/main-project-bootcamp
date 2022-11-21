package com.nttdata.formacao.mainproject.services.interfaces;

import com.nttdata.formacao.mainproject.entities.ProfessorEntity;

import java.util.List;

public interface IProfessorService {

    //Basic operations to Professor

    ProfessorEntity getProfessor(long id);

    List<ProfessorEntity> getAllProfessors();

    void addProfessor(ProfessorEntity professor);

    void delete(ProfessorEntity professor);
}
