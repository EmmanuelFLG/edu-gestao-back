package com.escola.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties; // Adicione este import

@Entity
@Table(name = "alocacoes")
@Data
public class Alocacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "professor_id")
    @JsonIgnoreProperties({"alocacoes", "senha", "email"}) // Não precisamos dos detalhes do professor aqui
    private Professor professor;

    @ManyToOne
    @JoinColumn(name = "disciplina_id")
    @JsonIgnoreProperties("alocacoes") // ESSENCIAL: Impede que a disciplina tente listar alocacoes de volta
    private Disciplina disciplina;

    @ManyToOne
    @JoinColumn(name = "turma_id")
    @JsonIgnoreProperties("alocacoes") // Impede o loop pela turma
    private Turma turma;

    @OneToMany(mappedBy = "alocacao")
    @JsonIgnoreProperties("alocacao")
    private List<HorarioAula> horarios;
}