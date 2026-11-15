package com.proyecto.bugtracker.Service;

import com.proyecto.bugtracker.Repository.RolRepository;
import com.proyecto.bugtracker.entity.Rol;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class RolService {

    private final RolRepository rolRepository;

    @Transactional(readOnly = true)
    public List<Rol> ver(){
        return rolRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Rol verPorId(Long id){
        return rolRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Rol no encontrado"));
    }

    public Rol crear(Rol entidad){
        return rolRepository.save(entidad);
    }

    public Rol editar(Long id, Rol entidad){
        Rol encontado = rolRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));

        if (entidad.getName() != null){
            encontado.setName(entidad.getName());
        }

        return rolRepository.save(encontado);
    }

    public void eliminar(Long id){
        rolRepository.deleteById(id);
    }
}
