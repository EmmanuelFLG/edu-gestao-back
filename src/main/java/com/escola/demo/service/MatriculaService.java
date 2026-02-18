package com.escola.demo.service;

import com.escola.demo.model.Matricula;
import com.escola.demo.repository.MatriculaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatriculaService {

    @Autowired
    private MatriculaRepository repository;

    public Matricula matricular(Matricula matricula) {
        return repository.save(matricula);
    }

    public List<Matricula> listarPorTurma(Long turmaId) {
        return repository.findByTurmaId(turmaId);
    }

    public List<Matricula> listarPorAluno(Long alunoId) {
        return repository.findByAlunoId(alunoId);
    }

    public void remover(Long id) {
        repository.deleteById(id);
    }
}
