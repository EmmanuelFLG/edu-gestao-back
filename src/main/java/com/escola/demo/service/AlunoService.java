package com.escola.demo.service;

import com.escola.demo.model.Aluno;
import com.escola.demo.model.Matricula;
import com.escola.demo.repository.AlunoRepository;
import com.escola.demo.repository.MatriculaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepo;

    @Autowired
    private MatriculaRepository matriculaRepo;

    public Aluno salvar(Aluno aluno) {
        return alunoRepo.save(aluno);
    }

    public List<Aluno> listar() {
        return alunoRepo.findAll();
    }

    public Aluno buscarPorId(Long id) {
        return alunoRepo.findById(id).orElse(null);
    }

    // VIA MATRÍCULA
    public List<Aluno> listarPorTurma(Long turmaId) {
        return matriculaRepo.findByTurmaId(turmaId)
                .stream()
                .map(Matricula::getAluno)
                .toList();
    }

    public void deletar(Long id) {
        alunoRepo.deleteById(id);
    }
}
