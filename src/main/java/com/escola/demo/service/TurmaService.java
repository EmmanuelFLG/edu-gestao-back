package com.escola.demo.service;

import com.escola.demo.model.Turma;
import com.escola.demo.repository.TurmaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TurmaService {

    @Autowired
    private TurmaRepository repository;

    public List<Turma> listar() {
        return repository.findAll();
    }

    public Turma buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public List<Turma> listarPorTurno(String turno) {
        return repository.findByTurno(turno);
    }

    public Turma salvar(Turma turma) {
        return repository.save(turma);
    }

    public Turma atualizar(Long id, Turma t) {
        t.setId(id);
        return repository.save(t);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
