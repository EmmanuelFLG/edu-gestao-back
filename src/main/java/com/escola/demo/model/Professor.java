package com.escola.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Table(name = "professores")
@Data
public class Professor extends Usuario {

    @OneToMany(mappedBy = "professor")
    private List<Alocacao> alocacoes;
}
