package com.escola.demo.repository;

import com.escola.demo.model.Turma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Map;

public interface TurmaRepository extends JpaRepository<Turma, Long> {

    // ESTE É O MÉTODO QUE ESTAVA FALTANDO PARA O SEU SERVICE FUNCIONAR:
    List<Turma> findByTurno(String turno);

    // Query para o professor ver os cards das turmas dele
    @Query(value = """
        SELECT 
            a.id as alocacao_id, 
            t.nome as turma, 
            t.serie as serie, 
            d.nome as disciplina, 
            t.turno as turno
        FROM alocacoes a
        JOIN turmas t ON t.id = a.turma_id
        JOIN disciplinas d ON d.id = a.disciplina_id
        WHERE a.professor_id = :professorId
        """, nativeQuery = true)
    List<Map<String, Object>> findTurmasPorProfessor(@Param("professorId") Long professorId);

    // Query para listar os alunos de uma alocação específica
    @Query(value = """
        SELECT 
            u.id as id, 
            u.nome as nome, 
            al.matricula as ra, 
            m.id as matricula_id
        FROM alocacoes alc
        JOIN matriculas m ON m.turma_id = alc.turma_id
        JOIN alunos al ON al.id = m.aluno_id
        JOIN usuarios u ON u.id = al.id
        WHERE alc.id = :alocacaoId AND m.ativa = true
        ORDER BY u.nome ASC
        """, nativeQuery = true)
    List<Map<String, Object>> findAlunosPorAlocacao(@Param("alocacaoId") Long alocacaoId);
}