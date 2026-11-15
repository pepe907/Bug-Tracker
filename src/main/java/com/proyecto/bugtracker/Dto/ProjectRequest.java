package com.proyecto.bugtracker.Dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProjectRequest {
    private Long idUsers;
    private String name;
    private String description;
    private String state;
    private LocalDateTime startTime;
}
