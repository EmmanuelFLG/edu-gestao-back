package com.escola.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "alunos")
@Data
public class Aluno extends Usuario {

    private String matricula; 

    @OneToMany(mappedBy = "aluno")
    @JsonIgnore
    private List<Matricula> matriculas;
}
