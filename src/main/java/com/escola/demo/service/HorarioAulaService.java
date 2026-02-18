package com.escola.demo.service;

import com.escola.demo.model.HorarioAula;
import com.escola.demo.repository.HorarioAulaRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HorarioAulaService {

    private final HorarioAulaRepository repository;

    public HorarioAulaService(HorarioAulaRepository repository) {
        this.repository = repository;
    }

    public HorarioAula salvar(HorarioAula horario) {
        return repository.save(horario);
    }

    public List<HorarioAula> listarTodos() {
        return repository.findAll();
    }

    public Optional<HorarioAula> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public HorarioAula atualizar(Long id, HorarioAula horario) {
        horario.setId(id);
        return repository.save(horario);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
