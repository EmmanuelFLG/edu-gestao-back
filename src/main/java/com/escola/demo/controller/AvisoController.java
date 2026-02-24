package com.escola.demo.controller;

import com.escola.demo.model.Aviso;
import com.escola.demo.service.AvisoService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/avisos")
@CrossOrigin("*")
public class AvisoController {

    private final AvisoService service;

    public AvisoController(AvisoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Aviso> listar() {
        return service.listarTodos();
    }

    @GetMapping("/ativos")
    public List<Aviso> ativos() {
        return service.listarAtivos();
    }

    @GetMapping("/turma/{turmaId}")
    public List<Aviso> porTurma(@PathVariable Long turmaId) {
        return service.listarPorTurma(turmaId);
    }

    @PostMapping
    public ResponseEntity<Aviso> criar(@RequestBody Aviso aviso) {
        return ResponseEntity.status(201)
                .body(service.salvar(aviso));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Aviso> atualizar(
            @PathVariable Long id,
            @RequestBody Aviso aviso) {

        return ResponseEntity.ok(service.atualizar(id, aviso));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
