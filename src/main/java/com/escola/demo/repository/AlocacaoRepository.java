package com.escola.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.escola.demo.model.Alocacao;

@Repository
public interface AlocacaoRepository extends JpaRepository<Alocacao, Long> {

    List<Alocacao> findByProfessorId(Long professorId);

    List<Alocacao> findByTurmaId(Long turmaId);
}

