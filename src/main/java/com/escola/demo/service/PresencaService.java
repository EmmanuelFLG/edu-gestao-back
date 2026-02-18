package com.escola.demo.service;

import com.escola.demo.model.Presenca;
import com.escola.demo.repository.PresencaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PresencaService {

    @Autowired
    private PresencaRepository repository;

    public Presenca salvar(Presenca presenca) {
        return repository.save(presenca);
    }

    public List<Presenca> buscarPorAluno(Long alunoId) {
        return repository.findByMatriculaAlunoId(alunoId);
    }

    public List<Presenca> buscarPorTurma(Long turmaId) {
        return repository.findByAlocacaoTurmaId(turmaId);
    }
}
