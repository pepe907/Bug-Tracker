package com.proyecto.bugtracker.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "rol")
@Data
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 150)
    private String name;

    @ManyToMany(mappedBy = "rol", fetch = FetchType.LAZY)
    @ToString.Exclude
    private Set<Users> usuarios = new HashSet<>();
}