package com.escola.demo.controller;

import com.escola.demo.model.Presenca;
import com.escola.demo.service.PresencaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/presencas")
@CrossOrigin("*")
public class PresencaController {

    @Autowired
    private PresencaService service;

    @PostMapping
    public Presenca salvar(@RequestBody Presenca presenca) {
        return service.salvar(presenca);
    }

    @GetMapping("/aluno/{alunoId}")
    public List<Presenca> listarPorAluno(@PathVariable Long alunoId) {
        return service.buscarPorAluno(alunoId);
    }
}
