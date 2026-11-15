package com.proyecto.bugtracker.Controller;

import com.proyecto.bugtracker.Dto.UsersRequest;
import com.proyecto.bugtracker.Service.UsersService;
import com.proyecto.bugtracker.entity.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UsersController {

    private final UsersService usersService;

    @GetMapping("/ver")
    public List<Users> ver(){
        return usersService.ver();
    }

    @GetMapping("/ver/{id}")
    private Users verPorId(@PathVariable Long id){
        return usersService.verPorId(id);
    }

    @PostMapping("/crear")
    public Users crear(UsersRequest usersRequest){
        return usersService.crear(usersRequest);
    }

    @PutMapping("/editar/{id}")
    public Users editar(Long id, UsersRequest usersRequest){
        return usersService.editar(id, usersRequest);
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminar(Long id){
        usersService.eliminar(id);
    }
}
