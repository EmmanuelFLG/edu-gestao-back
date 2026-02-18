package com.escola.demo.service;

import com.escola.demo.model.Aviso;
import com.escola.demo.repository.AvisoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AvisoService {

    @Autowired
    private AvisoRepository repository;

    public List<Aviso> listarTodos() {
        return repository.findAll();
    }

    public List<Aviso> listarAtivos() {
        return repository.findByAtivoTrue();
    }

    public List<Aviso> listarPorTurma(Long turmaId) {
        return repository.findByTurmaId(turmaId);
    }

    public List<Aviso> listarParaAluno(Long turmaId) {
        return repository
            .findByAtivoTrueAndTurmaIdOrTurmaIsNull(turmaId);
    }

    public Aviso salvar(Aviso aviso) {
        return repository.save(aviso);
    }

    public Aviso atualizar(Long id, Aviso aviso) {
        aviso.setId(id);
        return repository.save(aviso);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
