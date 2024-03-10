package com.polarbookshop.catalogservice.domain;

import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public Iterable<Project> viewProjectList() {
        return projectRepository.findAll();
    }

    public Project viewProjectDetails(String id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new ProjectNotFoundException(id));
    }

    public Project addProjectToCatalog(Project project) {
        if (projectRepository.existsById(project.id())) {
            throw new ProjectAlreadyExistsException(project.id());
        }
        return projectRepository.save(project);
    }

    public void removeProjectFromCatalog(String id) {
        projectRepository.deleteById(id);
    }

    public Project editProjectDetails(String id, Project project) {
		return projectRepository.findById(id)
				.map(existingProject-> {
					var projectToUpdate = new Project(
							existingProject.id(),
							project.name(),
							project.description(),
                            project.createdDate());
					return projectRepository.save(projectToUpdate);
				})
				.orElseGet(() -> addProjectToCatalog(project));
    }

}
