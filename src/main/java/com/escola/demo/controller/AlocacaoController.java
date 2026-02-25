package com.escola.demo.controller;

import com.escola.demo.dto.AlocacaoDTO;
import com.escola.demo.dto.AlocacaoCreateDTO;
import com.escola.demo.model.Alocacao;
import com.escola.demo.service.AlocacaoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alocacoes") 
@CrossOrigin("*")
public class AlocacaoController {

    private final AlocacaoService service;

    public AlocacaoController(AlocacaoService service) {
        this.service = service;
    }

    

    @GetMapping("/turma/{turmaId}")
    public List<Alocacao> listarPorTurma(@PathVariable Long turmaId) {
        return service.buscarPorTurma(turmaId);
    }

    @GetMapping("/professor/{professorId}")
    public List<Alocacao> listarPorProfessor(@PathVariable Long professorId) {
        return service.buscarPorProfessor(professorId);
    }

    
    @GetMapping
    public List<AlocacaoDTO> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public AlocacaoDTO buscar(@PathVariable Long id) {
        return service.listar().stream()
                .filter(a -> a.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @PostMapping
    public AlocacaoDTO criar(@RequestBody AlocacaoCreateDTO dto) {
        return service.criar(dto);
    }

    @PutMapping("/{id}")
    public AlocacaoDTO atualizar(@PathVariable Long id, @RequestBody AlocacaoCreateDTO dto) {
        return service.atualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}