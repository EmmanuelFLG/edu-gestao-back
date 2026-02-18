package com.escola.demo.service;

import com.escola.demo.model.Professor;
import com.escola.demo.repository.ProfessorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository repository;

    public List<Professor> listar() {
        return repository.findAll();
    }

    public Professor buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Professor salvar(Professor professor) {
        return repository.save(professor);
    }

    public Professor atualizar(Long id, Professor p) {
        p.setId(id);
        return repository.save(p);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
