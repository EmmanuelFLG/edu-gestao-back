package com.escola.demo.controller;

import com.escola.demo.model.Matricula;
import com.escola.demo.repository.MatriculaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/matriculas")
@CrossOrigin("*")
public class MatriculaController {

    @Autowired
    private MatriculaRepository repo;

    @PostMapping
    public ResponseEntity<Matricula> matricular(
            @RequestBody Matricula matricula) {

        return ResponseEntity.status(201)
                .body(repo.save(matricula));
    }

    @GetMapping("/turma/{turmaId}")
    public List<Matricula> porTurma(@PathVariable Long turmaId) {
        return repo.findByTurmaId(turmaId);
    }

    @GetMapping("/aluno/{alunoId}")
    public List<Matricula> porAluno(@PathVariable Long alunoId) {
        return repo.findByAlunoId(alunoId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
