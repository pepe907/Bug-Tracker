package com.proyecto.bugtracker.Service;

import com.proyecto.bugtracker.Dto.UsersRequest;
import com.proyecto.bugtracker.Repository.RolRepository;
import com.proyecto.bugtracker.Repository.UsersRepository;
import com.proyecto.bugtracker.entity.Rol;
import com.proyecto.bugtracker.entity.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UsersService {

    private final UsersRepository usersRepository;
    private final RolRepository rolRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public List<Users> ver(){
        return usersRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Users verPorId(Long id){
        return usersRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }


    public Users crear(UsersRequest usersRequest){
        Rol rol = rolRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));

        String passwordHash = passwordEncoder.encode(usersRequest.getPassword());

        Users user = Users.builder()
                .email(usersRequest.getEmail())
                .password(passwordHash)
                .build();

        user.addRol(rol);
        return usersRepository.save(user);
    }


    public Users editar(Long id, UsersRequest usersRequest){
        Users encontrado = usersRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));


        if (usersRequest.getRolId() != null){
            Rol rol = rolRepository.findById(usersRequest.getRolId())
                    .orElseThrow(() -> new RuntimeException("Rol no encontrado"));
            encontrado.getRol().clear();
            encontrado.addRol(rol);
        }

        if (usersRequest.getEmail() != null){
            encontrado.setEmail(usersRequest.getEmail());
        }

        if (usersRequest.getPassword() != null){
            encontrado.setPassword(usersRequest.getPassword());
        }

        return usersRepository.save(encontrado);
    }

    public void eliminar(Long id){
        Users user = usersRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        usersRepository.deleteById(id);
    }
}
