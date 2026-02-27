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

    // Salva uma única presença (útil se precisar de ajustes individuais)
    public Presenca salvar(Presenca presenca) {
        return repository.save(presenca);
    }

    // NOVO MÉTODO: Salva a lista completa (Bulk Save igual ao de Notas)
    public void salvarLista(List<Presenca> presencas) {
        if (presencas != null && !presencas.isEmpty()) {
            repository.saveAll(presencas);
        }
    }

    public List<Presenca> buscarPorAluno(Long alunoId) {
        return repository.findByMatriculaAlunoId(alunoId);
    }

    public List<Presenca> buscarPorTurma(Long turmaId) {
        return repository.findByAlocacaoTurmaId(turmaId);
    }
}