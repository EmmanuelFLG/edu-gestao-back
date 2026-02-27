package com.escola.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "notas", uniqueConstraints = {
    @UniqueConstraint(
        name = "unica_nota_bimestre_disciplina", 
        columnNames = {"matricula_id", "disciplina_id", "bimestre"}
    )
})
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
    // Ignora as listas que causam recursão e o caminho de volta do aluno
    @JsonIgnoreProperties({"notas", "presencas", "alocacoes", "aluno"}) 
    private Matricula matricula;

    @ManyToOne
    @JoinColumn(name = "disciplina_id")
    @JsonIgnoreProperties("alocacoes")
    private Disciplina disciplina;
}