package com.escola.demo.controller;

import com.escola.demo.dto.AlocacaoDTO;
import com.escola.demo.dto.AlocacaoCreateDTO;
import com.escola.demo.service.AlocacaoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/allocations")
@CrossOrigin("*")
public class AlocacaoController {

    private final AlocacaoService service;

    public AlocacaoController(AlocacaoService service) {
        this.service = service;
    }

    // Listar todas as alocações com dados completos
    @GetMapping
    public List<AlocacaoDTO> listar() {
        return service.listar();
    }

    // Criar nova alocação e retornar dados completos
    @PostMapping
    public AlocacaoDTO criar(@RequestBody AlocacaoCreateDTO dto) {
        return service.criar(dto);
    }

    // Buscar uma alocação específica
    @GetMapping("/{id}")
    public AlocacaoDTO buscar(@PathVariable Long id) {
        return service.listar().stream()
                .filter(a -> a.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    // Atualizar alocação (retorna dados completos)
    @PutMapping("/{id}")
    public AlocacaoDTO atualizar(@PathVariable Long id, @RequestBody AlocacaoCreateDTO dto) {
        return service.atualizar(id, dto);
    }

    // Deletar alocação
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}