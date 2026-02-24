package com.escola.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore; // Importante

@Entity
@Table(name = "turmas")
@Data
public class Turma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String serie;
    private String curso;
    private String turno;
    private String anoLetivo;

    @OneToMany(mappedBy = "turma")
    @JsonIgnore // CORTA O LOOP: A turma não deve listar matrículas dentro de outra chamada
    private List<Matricula> matriculas;

    @OneToMany(mappedBy = "turma")
    @JsonIgnore // CORTA O LOOP: A turma não deve listar alocacoes aqui
    private List<Alocacao> alocacoes;
}