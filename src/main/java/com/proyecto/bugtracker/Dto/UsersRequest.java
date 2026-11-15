package com.proyecto.bugtracker.Dto;

import lombok.Data;

@Data
public class UsersRequest {
    private Long rolId;
    private String email;
    private String password;
}