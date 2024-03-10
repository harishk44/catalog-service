package com.polarbookshop.catalogservice.web;

import javax.validation.Valid;

import com.polarbookshop.catalogservice.domain.Project;
import com.polarbookshop.catalogservice.domain.ProjectService;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("projects")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    public Iterable<Project> get() {
        return projectService.viewProjectList();
    }

    @GetMapping("{id}")
    public Project getById(@PathVariable String id) {
        return projectService.viewProjectDetails(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Project post(@Valid @RequestBody Project project) {
        return projectService.addProjectToCatalog(project);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        projectService.removeProjectFromCatalog(id);
    }

    @PutMapping("{id}")
    public Project put(@PathVariable String id, @Valid @RequestBody Project project) {
        return projectService.editProjectDetails(id, project);
    }

}
