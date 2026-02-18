package com.escola.demo.service;

import com.escola.demo.model.Disciplina;
import com.escola.demo.repository.DisciplinaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DisciplinaService {

    @Autowired
    private DisciplinaRepository repository;

    public List<Disciplina> listar() {
        return repository.findAll();
    }

    public Disciplina buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }


    public Disciplina salvar(Disciplina disciplina) {
        return repository.save(disciplina);
    }

    public Disciplina atualizar(Long id, Disciplina d) {
        d.setId(id);
        return repository.save(d);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
