package com.escola.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "presencas")
@Data
public class Presenca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate data;
    private Integer quantidadefaltas; 
    
    private String status;

    @ManyToOne
    @JoinColumn(name = "matricula_id")
    // Corta o caminho: Presença -> Matrícula -> (ignora volta para presenças e detalhes do aluno)
    @JsonIgnoreProperties({"presencas", "notas", "aluno.matriculas", "turma"})
    private Matricula matricula;

    @ManyToOne
    @JoinColumn(name = "alocacao_id")
    // MUITO IMPORTANTE: Corta o loop: Presença -> Alocação -> (ignora volta para presenças e notas)
    @JsonIgnoreProperties({"presencas", "notas", "turma.alocacoes", "disciplina.alocacoes"})
    private Alocacao alocacao;
}