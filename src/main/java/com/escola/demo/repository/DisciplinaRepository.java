package com.escola.demo.repository;

import com.escola.demo.model.Disciplina;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DisciplinaRepository extends JpaRepository<Disciplina, Long> {

    List<Disciplina> findByNomeContainingIgnoreCase(String nome);

    List<Disciplina> findByCargaHorariaGreaterThan(Integer cargaHoraria);

    List<Disciplina> findByAtivaTrue();
}

