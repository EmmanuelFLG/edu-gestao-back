package com.escola.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnore; // Importante
import java.util.List;

@Entity
@Table(name = "matriculas")
@Data
public class Matricula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String anoLetivo;
    private Boolean ativa;

    @ManyToOne
    @JoinColumn(name = "aluno_id")
    @JsonIgnoreProperties({"matriculas", "usuario", "senha"})
    private Aluno aluno;

    @ManyToOne
    @JoinColumn(name = "turma_id")
    @JsonIgnoreProperties({"matriculas", "alocacoes"})
    private Turma turma;

    @OneToMany(mappedBy = "matricula")
    @JsonIgnore // CORTA O LOOP: Evita carregar notas infinitamente
    private List<Notas> notas;

    @OneToMany(mappedBy = "matricula")
    @JsonIgnore // CORTA O LOOP: Evita carregar presenças infinitamente
    private List<Presenca> presencas;
}