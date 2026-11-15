package com.proyecto.bugtracker.Service;

import com.proyecto.bugtracker.Dto.ProjectRequest;
import com.proyecto.bugtracker.Repository.ProjectRepository;
import com.proyecto.bugtracker.Repository.UsersRepository;
import com.proyecto.bugtracker.entity.Project;
import com.proyecto.bugtracker.entity.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final UsersRepository usersRepository;

    @Transactional(readOnly = true)
    public List<Project> ver(){
        return projectRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Project verPorId(Long id){
        return projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Proyecto no encontrado"));
    }

    public Project crearProyecto(ProjectRequest projectRequest){
        Users users = usersRepository.findById(projectRequest.getIdUsers())
                .orElseThrow(() -> new RuntimeException("Error con el usuario"));

        Project project = Project.builder()
                .users(users)
                .name(projectRequest.getName())
                .description(projectRequest.getDescription())
                .state(projectRequest.getState())
                .startTime(projectRequest.getStartTime())
                .build();

        return projectRepository.save(project);
    }

    public Project editarProyecto(Long id, ProjectRequest projectRequest){
        Project encontrado = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Error con el usuario"));

        if (projectRequest.getIdUsers() != null){
            Users users = usersRepository.findById(projectRequest.getIdUsers())
                    .orElseThrow(() -> new RuntimeException("Error con el usuario"));
            encontrado.setUsers(users);
        }
        if (projectRequest.getName() != null){
            encontrado.setName(projectRequest.getName());
        }
        if (projectRequest.getDescription() != null){
            encontrado.setDescription(projectRequest.getDescription());
        }
        if (projectRequest.getState() != null){
            encontrado.setState(projectRequest.getState());
        }
        if (projectRequest.getStartTime() != null){
            encontrado.setStartTime(projectRequest.getStartTime());
        }
        return projectRepository.save(encontrado);
    }

    public void eliminarProyecto(Long id){
        projectRepository.deleteById(id);
    }
}
