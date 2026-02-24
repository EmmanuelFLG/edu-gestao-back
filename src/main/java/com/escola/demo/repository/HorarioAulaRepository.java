package com.escola.demo.repository;

import com.escola.demo.model.DiaSemana;
import com.escola.demo.model.HorarioAula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface HorarioAulaRepository extends JpaRepository<HorarioAula, Long> {

    // Para buscar o horário completo da turma (usado no useSchedule)
    List<HorarioAula> findByAlocacaoTurmaId(Long turmaId);

    // Caso queira filtrar apenas as aulas de hoje
    List<HorarioAula> findByAlocacaoTurmaIdAndDiaSemana(Long turmaId, DiaSemana diaSemana);
}