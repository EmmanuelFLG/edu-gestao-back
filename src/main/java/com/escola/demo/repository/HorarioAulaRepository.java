package com.escola.demo.repository;

import com.escola.demo.model.DiaSemana;
import com.escola.demo.model.HorarioAula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface HorarioAulaRepository extends JpaRepository<HorarioAula, Long> {

    List<HorarioAula> findByAlocacaoTurmaIdAndDiaSemana(
    Long turmaId,
    DiaSemana diaSemana
);


    List<HorarioAula> findByAlocacaoTurmaId(Long turmaId);
}
