package com.escola.demo.controller;

import com.escola.demo.model.HorarioAula;
import com.escola.demo.service.HorarioAulaService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/horarios")
@CrossOrigin("*")
public class HorarioAulaController {

    private final HorarioAulaService service;

    public HorarioAulaController(HorarioAulaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<HorarioAula> criar(@RequestBody HorarioAula horario) {
        HorarioAula novo = service.salvar(horario);
        return ResponseEntity.status(201).body(novo);
    }

    @GetMapping
    public ResponseEntity<List<HorarioAula>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HorarioAula> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<HorarioAula> atualizar(
            @PathVariable Long id,
            @RequestBody HorarioAula horario) {

        return ResponseEntity.ok(service.atualizar(id, horario));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
