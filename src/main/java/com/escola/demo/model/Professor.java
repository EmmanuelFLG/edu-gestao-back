package com.escola.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "professores")
@Data
public class Professor extends Usuario {

    @OneToMany(mappedBy = "professor")
    @JsonIgnoreProperties("professor") // CORTA O LOOP: A alocação não deve carregar o professor de novo
    private List<Alocacao> alocacoes;
}