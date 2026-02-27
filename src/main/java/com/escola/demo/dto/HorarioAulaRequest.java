package com.escola.demo.dto;

import lombok.Data;

@Data
public class HorarioAulaRequest {
    private Long alocacaoId;
    private String diaSemana; 
    private String horaInicio; 
    private String horaFim;    
    private String sala;
}