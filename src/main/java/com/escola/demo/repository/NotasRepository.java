package com.escola.demo.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.escola.demo.model.Notas;

@Repository
public interface NotasRepository extends JpaRepository<Notas, Long> {

    List<Notas> findByDisciplinaIdAndBimestreAndMatriculaTurmaId(Long disciplinaId, Integer bimestre, Long turmaId);

    List<Notas> findByMatriculaAlunoId(Long alunoId);

    // ADICIONE ESTE MÉTODO:
    Optional<Notas> findByMatriculaIdAndDisciplinaIdAndBimestre(Long matriculaId, Long disciplinaId, Integer bimestre);
}