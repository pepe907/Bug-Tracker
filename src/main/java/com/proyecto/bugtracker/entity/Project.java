package com.proyecto.bugtracker.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "project")
@Getter
@Setter
@Builder
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_project")
    private Long id;


    @Column(name = "name", nullable = false, length = 150)
    private String name;

    @Column(name = "description", nullable = false, length = 250)
    private String description;

    @Column(name = "state", nullable = false, length = 150)
    private String state;

    @CreationTimestamp
    @Column(name = "start_time", updatable = false)
    private LocalDateTime startTime;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_users", nullable = false)
    private Users users;
}
