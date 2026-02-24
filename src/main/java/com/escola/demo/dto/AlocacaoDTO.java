package com.escola.demo.dto;

import com.escola.demo.model.Professor;
import com.escola.demo.model.Disciplina;
import com.escola.demo.model.Turma;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data               // Gera getters, setters, toString, hashCode e equals
@NoArgsConstructor  // Construtor vazio
@AllArgsConstructor // Construtor com todos os campos
public class AlocacaoDTO {
    private Long id;
    private Professor professor;
    private Disciplina disciplina;
    private Turma turma;
}