package com.escola.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "disciplinas")
@Data
public class Disciplina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private Integer cargaHoraria;

    private Boolean ativa = true;

    @OneToMany(mappedBy = "disciplina")
    @JsonIgnoreProperties("disciplina")
    private List<Alocacao> alocacoes;
}
