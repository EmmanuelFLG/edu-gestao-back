package com.escola.demo.repository;

import com.escola.demo.model.Turma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TurmaRepository extends JpaRepository<Turma, Long> {
    List<Turma> findByTurno(String turno); // Filtra por Manhã, Tarde ou Noite
}