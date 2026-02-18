package com.escola.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Table(name = "roles")
@Data
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nome; // ROLE_ADMIN, ROLE_PROFESSOR...

    private String descricao;

    @ManyToMany(mappedBy = "roles")
    private List<Usuario> usuarios;
}
