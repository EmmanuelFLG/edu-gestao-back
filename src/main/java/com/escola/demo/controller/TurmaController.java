package com.escola.demo.controller;

import com.escola.demo.model.Turma;
import com.escola.demo.repository.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/turmas")
@CrossOrigin("*")
public class TurmaController {

    @Autowired
    private TurmaRepository repo;

    @GetMapping("/professor/{id}")
    public ResponseEntity<List<Map<String, Object>>> listarPorProfessor(@PathVariable Long id) {
        return ResponseEntity.ok(repo.findTurmasPorProfessor(id));
    }

    @GetMapping("/alocacao/{alocacaoId}/alunos")
    public ResponseEntity<List<Map<String, Object>>> listarAlunos(@PathVariable Long alocacaoId) {
        return ResponseEntity.ok(repo.findAlunosPorAlocacao(alocacaoId));
    }

    @GetMapping
    public List<Turma> listar() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Turma> buscar(@PathVariable Long id) {
        return repo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Turma> criar(@RequestBody Turma t) {
        return ResponseEntity.status(201).body(repo.save(t));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Turma> atualizar(@PathVariable Long id, @RequestBody Turma t) {
        if (!repo.existsById(id)) return ResponseEntity.notFound().build();
        t.setId(id);
        return ResponseEntity.ok(repo.save(t));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}