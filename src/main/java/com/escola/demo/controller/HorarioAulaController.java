package com.escola.demo.controller;

import com.escola.demo.dto.HorarioAulaDTO;
import com.escola.demo.dto.HorarioAulaRequest;
import com.escola.demo.model.HorarioAula;
import com.escola.demo.service.HorarioAulaService;
import jakarta.validation.Valid;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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

    // --- ROTA COM CACHE PARA O PORTAL DO ALUNO ---
    // A key="#turmaId" garante que o cache do 9º ano não se misture com o do 8º ano.
    @GetMapping("/turma/{turmaId}")
    @Cacheable(value = "horarios_turma", key = "#turmaId")
    public ResponseEntity<List<HorarioAula>> listarPorTurma(@PathVariable Long turmaId) {
        // Esse log só aparecerá no console se o dado NÃO estiver no Redis
        System.out.println("===> BUSCANDO HORARIOS DA TURMA " + turmaId + " NO POSTGRESQL...");
        return ResponseEntity.ok(service.buscarPorTurma(turmaId));
    }

    // --- ROTAS ADMINISTRATIVAS ---

    @GetMapping
    @Cacheable(value = "horarios_geral")
    public ResponseEntity<List<HorarioAulaDTO>> listarTodos() {
        return ResponseEntity.ok(service.listarTodosDTO());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HorarioAulaDTO> buscarPorId(@PathVariable Long id) {
        return service.buscarPorIdDTO(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // --- ROTAS QUE LIMPAM O CACHE (IMPORTANTE) ---
    // Sempre que criamos, editamos ou deletamos o cache limpa
    // O @CacheEvict com allEntries=true limpa e le dnv

    @PostMapping
    @CacheEvict(value = {"horarios_turma", "horarios_geral"}, allEntries = true)
    public ResponseEntity<HorarioAulaDTO> criar(@RequestBody @Valid HorarioAulaRequest request) {
        HorarioAulaDTO dtoSalvo = service.salvarDTO(request);
        return ResponseEntity.status(201).body(dtoSalvo);
    }

    @PutMapping("/{id}")
    @CacheEvict(value = {"horarios_turma", "horarios_geral"}, allEntries = true)
    public ResponseEntity<HorarioAulaDTO> atualizar(@PathVariable Long id, @RequestBody @Valid HorarioAulaRequest request) {
        HorarioAulaDTO dtoAtualizado = service.atualizarDTO(id, request);
        return ResponseEntity.ok(dtoAtualizado);
    }

    @DeleteMapping("/{id}")
    @CacheEvict(value = {"horarios_turma", "horarios_geral"}, allEntries = true)
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}