package com.nttdata.formacao.mainproject.services.implementation;

import com.nttdata.formacao.mainproject.entities.ProfessorEntity;
import com.nttdata.formacao.mainproject.repositories.IProfessorRepository;
import com.nttdata.formacao.mainproject.services.interfaces.IProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessorService implements IProfessorService {

    @Autowired
    private IProfessorRepository professorRepository;

    @Override
    public ProfessorEntity getProfessor(long id) {
        return professorRepository.findById(id).get();
    }

    @Override
    public List<ProfessorEntity> getAllProfessors() {
        return professorRepository.findAll();
    }

    @Override
    public void addProfessor(ProfessorEntity professor) {
        professorRepository.save(professor);
    }

    @Override
    public void delete(ProfessorEntity professor) {
        professorRepository.delete(professor);
    }
}
