package com.escola.demo.repository;

import com.escola.demo.model.Notas;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface NotasRepository extends JpaRepository<Notas, Long> {

    List<Notas> findByMatriculaAlunoId(Long alunoId);

    List<Notas> findByDisciplinaIdAndBimestreAndMatriculaTurmaId(
            Long disciplinaId,
            Integer bimestre,
            Long turmaId);
}
