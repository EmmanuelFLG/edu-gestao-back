package com.escola.demo.controller;

import com.escola.demo.dto.AlocacaoDTO;
import com.escola.demo.dto.AlocacaoCreateDTO;
import com.escola.demo.model.Alocacao;
import com.escola.demo.service.AlocacaoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alocacoes") // Mantendo o padrao em portugues para o frontend
@CrossOrigin("*")
public class AlocacaoController {

    private final AlocacaoService service;

    public AlocacaoController(AlocacaoService service) {
        this.service = service;
    }

    // --- ROTAS PARA O DASHBOARD E CONSULTAS ---

    // Rota que o Dashboard do Aluno usa para calcular carga horaria e faltas
    @GetMapping("/turma/{turmaId}")
    public List<Alocacao> listarPorTurma(@PathVariable Long turmaId) {
        return service.buscarPorTurma(turmaId);
    }

    // Rota para o Dashboard do Professor ver suas materias
    @GetMapping("/professor/{professorId}")
    public List<Alocacao> listarPorProfessor(@PathVariable Long professorId) {
        return service.buscarPorProfessor(professorId);
    }

    // --- ROTAS ADMINISTRATIVAS (CRUD) ---

    // Listar todas as alocacoes com dados completos (DTO)
    @GetMapping
    public List<AlocacaoDTO> listar() {
        return service.listar();
    }

    // Buscar uma alocacao especifica por ID
    @GetMapping("/{id}")
    public AlocacaoDTO buscar(@PathVariable Long id) {
        // Busca na lista do service a alocacao correspondente
        return service.listar().stream()
                .filter(a -> a.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    // Criar nova alocacao e retornar dados completos
    @PostMapping
    public AlocacaoDTO criar(@RequestBody AlocacaoCreateDTO dto) {
        return service.criar(dto);
    }

    // Atualizar alocacao existente
    @PutMapping("/{id}")
    public AlocacaoDTO atualizar(@PathVariable Long id, @RequestBody AlocacaoCreateDTO dto) {
        return service.atualizar(id, dto);
    }

    // Deletar alocacao
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}