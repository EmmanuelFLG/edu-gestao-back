package com.escola.demo.repository;

import com.escola.demo.model.Presenca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PresencaRepository extends JpaRepository<Presenca, Long> {

    List<Presenca> findByMatriculaAlunoId(Long alunoId);

    List<Presenca> findByAlocacaoTurmaId(Long turmaId);
}
