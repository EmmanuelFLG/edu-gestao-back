package com.escola.demo.controller;

import com.escola.demo.model.Presenca;
import com.escola.demo.service.PresencaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.HashMap;

@RestController
@RequestMapping("/presencas") // Mantive o /api para bater com o useDiaries
@CrossOrigin("*")
public class PresencaController {

    @Autowired
    private PresencaService service;

    @PostMapping
    public ResponseEntity<?> salvarChamada(@RequestBody List<Presenca> presencas) {
        // Chamando o novo método que usa repository.saveAll()
        service.salvarLista(presencas);
        // Retornamos um Map vazio para o React entender como sucesso (200 OK)
        return ResponseEntity.ok().body(new HashMap<>());
    }

    @GetMapping("/aluno/{alunoId}")
    public List<Presenca> listarPorAluno(@PathVariable Long alunoId) {
        return service.buscarPorAluno(alunoId);
    }
}