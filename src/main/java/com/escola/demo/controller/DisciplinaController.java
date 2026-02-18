package com.escola.demo.controller;

import com.escola.demo.model.Disciplina;
import com.escola.demo.service.DisciplinaService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/disciplinas")
@CrossOrigin("*")
public class DisciplinaController {

    private final DisciplinaService service;

    public DisciplinaController(DisciplinaService service) {
        this.service = service;
    }

    @GetMapping
    public List<Disciplina> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Disciplina> buscar(@PathVariable Long id) {
        Disciplina d = service.buscarPorId(id);

        if (d == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(d);
    }

    @PostMapping
    public ResponseEntity<Disciplina> criar(@RequestBody Disciplina d) {
        return ResponseEntity.status(201)
                .body(service.salvar(d));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Disciplina> atualizar(
            @PathVariable Long id,
            @RequestBody Disciplina d) {

        return ResponseEntity.ok(service.atualizar(id, d));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
