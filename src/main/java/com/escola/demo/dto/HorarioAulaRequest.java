package com.escola.demo.dto;

import lombok.Data;

@Data
public class HorarioAulaRequest {
    private Long alocacaoId;
    private String diaSemana;
    private String horaInicio; // "HH:mm"
    private String horaFim;    // "HH:mm"
    private String sala;
}