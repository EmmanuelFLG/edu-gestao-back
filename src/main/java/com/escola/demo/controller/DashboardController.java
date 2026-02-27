package com.escola.demo.controller;

import com.escola.demo.repository.*;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/dashboard")
@CrossOrigin("*")
public class DashboardController {

    private final AlunoRepository alunoRepository;
    private final ProfessorRepository professorRepository;
    private final TurmaRepository turmaRepository;
    private final DisciplinaRepository disciplinaRepository;

    public DashboardController(
            AlunoRepository alunoRepository,
            ProfessorRepository professorRepository,
            TurmaRepository turmaRepository,
            DisciplinaRepository disciplinaRepository) {

        this.alunoRepository = alunoRepository;
        this.professorRepository = professorRepository;
        this.turmaRepository = turmaRepository;
        this.disciplinaRepository = disciplinaRepository;
    }

    @GetMapping("/resumo")
    public Map<String, Long> resumo() {

        Map<String, Long> dados = new HashMap<>();

        dados.put("totalAlunos", alunoRepository.count());
        dados.put("totalProfessores", professorRepository.count());
        dados.put("totalTurmas", turmaRepository.count());
        dados.put("totalDisciplinas", disciplinaRepository.count());

        return dados;
    }
}
