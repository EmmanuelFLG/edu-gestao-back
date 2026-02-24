package com.escola.demo.dto;

import lombok.Data;

@Data
public class AlocacaoCreateDTO {
    private Long professorId;
    private Long disciplinaId;
    private Long turmaId;
}