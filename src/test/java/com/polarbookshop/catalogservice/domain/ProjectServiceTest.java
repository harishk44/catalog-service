package com.polarbookshop.catalogservice.domain;

import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProjectServiceTest {

    @Mock
    private ProjectRepository projectRepository;

    @InjectMocks
    private ProjectService projectService;

    @Test
    void whenProjectToCreateAlreadyExistsThenThrows() {
        var projectId = "1234561232";
        var projectToCreate = new Project(projectId, "Title", "Author", new Date());
        when(projectRepository.existsById(projectId)).thenReturn(true);
        assertThatThrownBy(() -> projectService.addProjectToCatalog(projectToCreate))
                .isInstanceOf(ProjectAlreadyExistsException.class)
                .hasMessage("The project with Id " + projectId + " already exists.");
    }

    @Test
    void whenProjectToReadDoesNotExistThenThrows() {
        var projectId = "012345612324";
        when(projectRepository.findById(projectId)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> projectService.viewProjectDetails(projectId))
                .isInstanceOf(ProjectNotFoundException.class)
                .hasMessage("The project with Id " + projectId + " was not found.");
    }

}
