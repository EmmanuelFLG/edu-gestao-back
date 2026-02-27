package com.escola.demo.dto;

import lombok.Data;

@Data
public class HorarioAulaDTO {
    private Long id;
    private String diaSemana;
    private String horaInicio;
    private String horaFim;
    private String sala;

    private Integer aulaNumero;

    private Long alocacaoId;
    private String professorNome;
    private String disciplinaNome;
    private String turmaNome;
}