package com.escola.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore; // Importe o JsonIgnore

@Entity
@Table(name = "roles")
@Data
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nome;

    private String descricao;

    @ManyToMany(mappedBy = "roles")
    @JsonIgnore // ESSENCIAL: Impede que a Role tente listar todos os usuários de volta
    private List<Usuario> usuarios;
}