package com.proyecto.bugtracker.Controller;

import com.proyecto.bugtracker.Dto.ProjectRequest;
import com.proyecto.bugtracker.Service.ProjectService;
import com.proyecto.bugtracker.entity.Project;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/projects")
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;

    @GetMapping("/ver")
    public List<Project> verProyectos(){
        return projectService.ver();
    }

    @GetMapping("/ver/{id}")
    public Project verPorId(@PathVariable Long id){
        return projectService.verPorId(id);
    }

    @PostMapping("/crear")
    public Project crearProyecto(@RequestBody ProjectRequest projectRequest){
        return projectService.crearProyecto(projectRequest);
    }

    @PutMapping("/editar/{id}")
    public Project edirtarProyecto(@PathVariable Long id, @RequestBody ProjectRequest projectRequest){
        return projectService.editarProyecto(id, projectRequest);
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminarProyecto(Long id ){
        projectService.eliminarProyecto(id);
    }
}
