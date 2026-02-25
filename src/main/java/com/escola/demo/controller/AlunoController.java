package com.escola.demo.controller;

import com.escola.demo.model.Aluno;
import com.escola.demo.service.AlunoService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alunos")
@CrossOrigin("*")
public class AlunoController {

    private final AlunoService service;

    public AlunoController(AlunoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Aluno> listarTodos() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aluno> buscarPorId(@PathVariable Long id) {

        Aluno aluno = service.buscarPorId(id);

        if (aluno == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(aluno);
    }

    
    @PostMapping
    public ResponseEntity<Aluno> criar(@RequestBody Aluno aluno) {
        return ResponseEntity.status(201).body(service.salvar(aluno));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Aluno> atualizar(
            @PathVariable Long id,
            @RequestBody Aluno aluno) {

        if (service.buscarPorId(id) == null)
            return ResponseEntity.notFound().build();

        aluno.setId(id);
        return ResponseEntity.ok(service.salvar(aluno));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/turma/{turmaId}")
    public List<Aluno> listarPorTurma(@PathVariable Long turmaId) {
        return service.listarPorTurma(turmaId);
    }
}
