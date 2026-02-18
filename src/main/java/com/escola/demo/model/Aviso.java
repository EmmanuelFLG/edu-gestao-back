package com.escola.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "avisos")
@Data
public class Aviso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String mensagem;
    private String tipo;

    private LocalDate dataPostagem = LocalDate.now();
    private Boolean ativo = true;

    // null = aviso geral
    @ManyToOne
    @JoinColumn(name = "turma_id")
    @JsonIgnoreProperties("avisos")
    private Turma turma;
}
