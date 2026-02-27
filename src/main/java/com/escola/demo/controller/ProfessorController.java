package com.escola.demo.controller;

import com.escola.demo.model.Professor;
import com.escola.demo.repository.ProfessorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/professores")
@CrossOrigin("*")
public class ProfessorController {

    @Autowired
    private ProfessorRepository repo;

    @GetMapping
    public List<Professor> listar() {
        return repo.findAll();
    }

    @PostMapping
    public ResponseEntity<Professor> criar(@RequestBody Professor p) {
        return ResponseEntity.status(201).body(repo.save(p));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Professor> buscar(@PathVariable Long id) {
        return repo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Professor> atualizar(
            @PathVariable Long id,
            @RequestBody Professor p) {

        if (!repo.existsById(id))
            return ResponseEntity.notFound().build();

        p.setId(id);
        return ResponseEntity.ok(repo.save(p));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
