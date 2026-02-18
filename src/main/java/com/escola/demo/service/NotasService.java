package com.escola.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.escola.demo.model.Notas;
import com.escola.demo.repository.NotasRepository;

@Service
public class NotasService {

    @Autowired
    private NotasRepository repository;

    public List<Notas> buscarNotasParaLancamento(
            Long turmaId,
            Long disciplinaId,
            Integer bimestre) {

        return repository
            .findByDisciplinaIdAndBimestreAndMatriculaTurmaId(
                disciplinaId,
                bimestre,
                turmaId);
    }

    public List<Notas> buscarPorAluno(Long alunoId) {
        return repository.findByMatriculaAlunoId(alunoId);
    }

    public void salvarLote(List<Notas> notas) {
        if (notas != null && !notas.isEmpty()) {
            repository.saveAll(notas);
        }
    }
}
