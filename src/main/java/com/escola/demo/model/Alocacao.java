package com.escola.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties; 

@Entity
@Table(name = "alocacoes")
@Data
public class Alocacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "professor_id")
    @JsonIgnoreProperties({"alocacoes", "senha", "email"}) 
    private Professor professor;

    @ManyToOne
    @JoinColumn(name = "disciplina_id")
    @JsonIgnoreProperties("alocacoes") 
    private Disciplina disciplina;

    @ManyToOne
    @JoinColumn(name = "turma_id")
    @JsonIgnoreProperties("alocacoes") 
    private Turma turma;

    @OneToMany(mappedBy = "alocacao")
    @JsonIgnoreProperties("alocacao")
    private List<HorarioAula> horarios;
}