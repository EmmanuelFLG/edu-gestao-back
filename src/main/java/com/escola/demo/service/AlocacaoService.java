package com.escola.demo.service;

import com.escola.demo.dto.AlocacaoDTO;
import com.escola.demo.dto.AlocacaoCreateDTO;
import com.escola.demo.model.Alocacao;
import com.escola.demo.model.Professor;
import com.escola.demo.model.Disciplina;
import com.escola.demo.model.Turma;
import com.escola.demo.repository.AlocacaoRepository;
import com.escola.demo.repository.ProfessorRepository;
import com.escola.demo.repository.DisciplinaRepository;
import com.escola.demo.repository.TurmaRepository;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlocacaoService {

    private final AlocacaoRepository repo;
    private final ProfessorRepository professorRepo;
    private final DisciplinaRepository disciplinaRepo;
    private final TurmaRepository turmaRepo;

    public AlocacaoService(AlocacaoRepository repo,
                           ProfessorRepository professorRepo,
                           DisciplinaRepository disciplinaRepo,
                           TurmaRepository turmaRepo) {
        this.repo = repo;
        this.professorRepo = professorRepo;
        this.disciplinaRepo = disciplinaRepo;
        this.turmaRepo = turmaRepo;
    }

    // =========================================
    // MÉTODOS DE CONSULTA (COM CACHE)
    // =========================================

    @Cacheable(value = "alocacoesPorTurma", key = "#turmaId")
    public List<Alocacao> buscarPorTurma(Long turmaId) {
        return repo.findByTurmaId(turmaId);
    }

    @Cacheable(value = "alocacoesPorProfessor", key = "#professorId")
    public List<Alocacao> buscarPorProfessor(Long professorId) {
        return repo.findByProfessorId(professorId);
    }

    @Cacheable(value = "todasAlocacoes")
    public List<Alocacao> listarTodas() {
        return repo.findAll();
    }

    @Cacheable(value = "alocacoesDTO")
    public List<AlocacaoDTO> listar() {
        return repo.findAll().stream()
                .map(a -> new AlocacaoDTO(
                        a.getId(),
                        a.getProfessor(),
                        a.getDisciplina(),
                        a.getTurma()))
                .collect(Collectors.toList());
    }

    // =========================================
    // MÉTODOS DE ESCRITA (LIMPA CACHE)
    // =========================================

    @Transactional
    @CacheEvict(value = {
            "alocacoesPorTurma",
            "alocacoesPorProfessor",
            "todasAlocacoes",
            "alocacoesDTO"
    }, allEntries = true)
    public AlocacaoDTO criar(AlocacaoCreateDTO dto) {

        Professor prof = professorRepo.findById(dto.getProfessorId())
                .orElseThrow(() -> new RuntimeException("Professor não encontrado"));

        Disciplina disc = disciplinaRepo.findById(dto.getDisciplinaId())
                .orElseThrow(() -> new RuntimeException("Disciplina não encontrada"));

        Turma turma = turmaRepo.findById(dto.getTurmaId())
                .orElseThrow(() -> new RuntimeException("Turma não encontrada"));

        Alocacao alocacao = new Alocacao();
        alocacao.setProfessor(prof);
        alocacao.setDisciplina(disc);
        alocacao.setTurma(turma);

        Alocacao saved = repo.save(alocacao);

        return new AlocacaoDTO(
                saved.getId(),
                saved.getProfessor(),
                saved.getDisciplina(),
                saved.getTurma());
    }

    @Transactional
    @CacheEvict(value = {
            "alocacoesPorTurma",
            "alocacoesPorProfessor",
            "todasAlocacoes",
            "alocacoesDTO"
    }, allEntries = true)
    public AlocacaoDTO atualizar(Long id, AlocacaoCreateDTO dto) {

        Alocacao alocacao = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Alocação não encontrada"));

        Professor prof = professorRepo.findById(dto.getProfessorId())
                .orElseThrow(() -> new RuntimeException("Professor não encontrado"));

        Disciplina disc = disciplinaRepo.findById(dto.getDisciplinaId())
                .orElseThrow(() -> new RuntimeException("Disciplina não encontrada"));

        Turma turma = turmaRepo.findById(dto.getTurmaId())
                .orElseThrow(() -> new RuntimeException("Turma não encontrada"));

        alocacao.setProfessor(prof);
        alocacao.setDisciplina(disc);
        alocacao.setTurma(turma);

        Alocacao saved = repo.save(alocacao);

        return new AlocacaoDTO(
                saved.getId(),
                saved.getProfessor(),
                saved.getDisciplina(),
                saved.getTurma());
    }

    @CacheEvict(value = {
            "alocacoesPorTurma",
            "alocacoesPorProfessor",
            "todasAlocacoes",
            "alocacoesDTO"
    }, allEntries = true)
    public void deletar(Long id) {
        repo.deleteById(id);
    }

    @CacheEvict(value = {
            "alocacoesPorTurma",
            "alocacoesPorProfessor",
            "todasAlocacoes",
            "alocacoesDTO"
    }, allEntries = true)
    public Alocacao salvar(Alocacao alocacao) {
        return repo.save(alocacao);
    }
}