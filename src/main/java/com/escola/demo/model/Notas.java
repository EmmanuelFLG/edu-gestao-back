package com.escola.demo.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "notas")
@Data
public class Notas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer bimestre;
    private Double n1 = 0.0;
    private Double n2 = 0.0;
    private Double n3 = 0.0;

    @ManyToOne
    @JoinColumn(name = "matricula_id")
    private Matricula matricula;

    @ManyToOne
    @JoinColumn(name = "disciplina_id")
    private Disciplina disciplina;
}
