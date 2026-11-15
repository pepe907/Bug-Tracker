package com.proyecto.bugtracker.Controller;

import com.proyecto.bugtracker.Service.RolService;
import com.proyecto.bugtracker.entity.Rol;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rol")
@AllArgsConstructor
public class RolController {

    private final RolService rolService;

    @GetMapping("/ver")
    public List<Rol> ver(){
        return rolService.ver();
    }

    @GetMapping("/ver/{id}")
    public Rol verPorId(@PathVariable Long id){
        return rolService.verPorId(id);
    }

    @PostMapping("/crear")
    public Rol crear (Rol entidad){
        return rolService.crear(entidad);
    }

    @PutMapping("/editar")
    public Rol editar(@PathVariable Long id, @RequestBody Rol entidad){
        return rolService.editar(id,entidad);
    }
}
