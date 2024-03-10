package com.polarbookshop.catalogservice.demo;

import com.polarbookshop.catalogservice.domain.Project;
import com.polarbookshop.catalogservice.domain.ProjectRepository;

import java.util.Date;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Profile("testData")
public class ProjectDataLoader {
    private final ProjectRepository projectRepository;

    public ProjectDataLoader(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
      }
    
    @EventListener(ApplicationReadyEvent.class)
    public void loadProjectTestData() {
    var project1 = new Project("1234567891", "Northern Lights","Lyra Silverstar", new Date());
    var project2 = new Project("1234567892", "Polar Journey","Iorek Polarson", new Date());
    projectRepository.save(project1);
    projectRepository.save(project2);
  }
}
