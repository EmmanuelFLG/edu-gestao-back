package com.escola.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "horarios")
@Data
public class HorarioAula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private DiaSemana diaSemana;

    private LocalTime horaInicio;
    private LocalTime horaFim;

    private String sala;

    private Integer aulaNumero;

    private Boolean ativo = true;

    @ManyToOne
    @JoinColumn(name = "alocacao_id")
    @JsonIgnoreProperties("horarios")
    private Alocacao alocacao;
}
