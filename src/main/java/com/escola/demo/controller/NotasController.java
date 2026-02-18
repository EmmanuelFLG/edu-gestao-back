package com.escola.demo.controller;

import com.escola.demo.model.Notas;
import com.escola.demo.service.NotasService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notas")
@CrossOrigin("*")
public class NotasController {

    @Autowired
    private NotasService service;

    @GetMapping("/turma/{turmaId}/disciplina/{disciplinaId}")
    public List<Notas> listarParaLancamento(
            @PathVariable Long turmaId,
            @PathVariable Long disciplinaId,
            @RequestParam Integer bimestre) {

        return service.buscarNotasParaLancamento(
                turmaId,
                disciplinaId,
                bimestre);
    }

    @GetMapping("/aluno/{alunoId}")
    public List<Notas> listarPorAluno(@PathVariable Long alunoId) {
        return service.buscarPorAluno(alunoId);
    }

    @PostMapping("/bulk-update")
    public void salvarLote(@RequestBody List<Notas> notas) {
        service.salvarLote(notas);
    }
}
