package com.escola.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.escola.demo.model.Matricula;

@Repository
public interface MatriculaRepository extends JpaRepository<Matricula, Long> {

    List<Matricula> findByTurmaId(Long turmaId);

    List<Matricula> findByAlunoId(Long alunoId);

    Optional<Matricula> findByAlunoIdAndAnoLetivo(Long alunoId, String anoLetivo);
}

