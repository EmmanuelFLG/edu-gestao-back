package com.escola.demo.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public void salvarLote(List<Notas> notas) {
        if (notas == null || notas.isEmpty()) return;

        for (Notas nota : notas) {
            // Tenta localizar se já existe uma nota para este aluno, nesta disciplina e bimestre
            Optional<Notas> notaExistente = repository.findByMatriculaIdAndDisciplinaIdAndBimestre(
                nota.getMatricula().getId(),
                nota.getDisciplina().getId(),
                nota.getBimestre()
            );

            if (notaExistente.isPresent()) {
                // Se já existe, atualizamos o objeto do banco com as notas que vieram do front
                Notas notaDoBanco = notaExistente.get();
                notaDoBanco.setN1(nota.getN1());
                notaDoBanco.setN2(nota.getN2());
                notaDoBanco.setN3(nota.getN3());
                // Ao salvar um objeto que já tem ID, o Hibernate faz um UPDATE
                repository.save(notaDoBanco);
            } else {
                // Se não existe, salva um novo registro (INSERT)
                repository.save(nota);
            }
        }
    }
}