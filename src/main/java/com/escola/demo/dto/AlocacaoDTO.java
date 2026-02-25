package com.escola.demo.dto;

import com.escola.demo.model.Professor;
import com.escola.demo.model.Disciplina;
import com.escola.demo.model.Turma;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data               
@NoArgsConstructor  
@AllArgsConstructor 
public class AlocacaoDTO {
    private Long id;
    private Professor professor;
    private Disciplina disciplina;
    private Turma turma;
}