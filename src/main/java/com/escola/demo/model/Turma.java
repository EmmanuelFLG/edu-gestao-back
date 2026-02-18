package com.escola.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Table(name = "turmas")
@Data
public class Turma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;        // Ex: 1º A
    private String serie;       // Ex: 1º Ano
    private String curso;       // Ex: Ensino Médio
    private String turno;       // Manhã / Tarde
    private String anoLetivo;   // 2026

    @OneToMany(mappedBy = "turma")
    private List<Matricula> matriculas;

    @OneToMany(mappedBy = "turma")
    private List<Alocacao> alocacoes;
}
