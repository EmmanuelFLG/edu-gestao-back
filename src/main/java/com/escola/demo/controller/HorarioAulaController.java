package com.escola.demo.controller;

import com.escola.demo.dto.HorarioAulaDTO;
import com.escola.demo.dto.HorarioAulaRequest;
import com.escola.demo.service.HorarioAulaService;
import jakarta.validation.Valid;
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

    // Listar todos
    @GetMapping
    public ResponseEntity<List<HorarioAulaDTO>> listarTodos() {
        return ResponseEntity.ok(service.listarTodosDTO());
    }

    // Buscar por id
    @GetMapping("/{id}")
    public ResponseEntity<HorarioAulaDTO> buscarPorId(@PathVariable Long id) {
        return service.buscarPorIdDTO(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Criar novo horário
    @PostMapping
    public ResponseEntity<HorarioAulaDTO> criar(@RequestBody @Valid HorarioAulaRequest request) {
        HorarioAulaDTO dtoSalvo = service.salvarDTO(request);
        return ResponseEntity.status(201).body(dtoSalvo);
    }

    // Atualizar horário
    @PutMapping("/{id}")
    public ResponseEntity<HorarioAulaDTO> atualizar(@PathVariable Long id,
                                                    @RequestBody @Valid HorarioAulaRequest request) {
        HorarioAulaDTO dtoAtualizado = service.atualizarDTO(id, request);
        return ResponseEntity.ok(dtoAtualizado);
    }

    // Deletar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}