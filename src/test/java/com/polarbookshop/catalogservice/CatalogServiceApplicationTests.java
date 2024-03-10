package com.polarbookshop.catalogservice;

import com.polarbookshop.catalogservice.domain.Project;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CatalogServiceApplicationTests {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void whenGetRequestWithIdThenProjectReturned() {
        var id = "1231231230";
        var projectToCreate = new Project(id, "Title", "description", new Date());
        Project expectedProject = webTestClient
                .post()
                .uri("/projects")
                .bodyValue(projectToCreate)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(Project.class).value(project -> assertThat(project).isNotNull())
                .returnResult().getResponseBody();

        webTestClient
                .get()
                .uri("/projects/" + id)
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody(Project.class).value(actualProject -> {
                    assertThat(actualProject).isNotNull();
                    assertThat(actualProject.id()).isEqualTo(expectedProject.id());
                });
    }

    @Test
    void whenPostRequestThenProjectCreated() {
        var expectedProject = new Project("1231231231", "Title", "description", new Date());

        webTestClient
                .post()
                .uri("/projects")
                .bodyValue(expectedProject)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(Project.class).value(actualProject -> {
                    assertThat(actualProject).isNotNull();
                    assertThat(actualProject.id()).isEqualTo(expectedProject.id());
                });
    }

    @Test
    void whenPutRequestThenProjectUpdated() {
        var id = "2231231232";
        var projectToCreate = new Project(id, "Title", "description", new Date());
        Project createdProject = webTestClient
                .post()
                .uri("/projects")
                .bodyValue(projectToCreate)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(Project.class).value(project -> assertThat(project).isNotNull())
                .returnResult().getResponseBody();
        var projectToUpdate = new Project(createdProject.id(), createdProject.name(), createdProject.description(), new Date());

        webTestClient
                .put()
                .uri("/projects/" + id)
                .bodyValue(projectToUpdate)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Project.class).value(actualProject -> {
                    assertThat(actualProject).isNotNull();
                    assertThat(actualProject.name()).isEqualTo(projectToUpdate.name());
                });
    }

    @Test
    void whenDeleteRequestThenProjectDeleted() {
        var id = "1231231233";
        var projectToCreate = new Project(id, "Title", "description", new Date());
        webTestClient
                .post()
                .uri("/projects")
                .bodyValue(projectToCreate)
                .exchange()
                .expectStatus().isCreated();

        webTestClient
                .delete()
                .uri("/projects/" + id)
                .exchange()
                .expectStatus().isNoContent();

        webTestClient
                .get()
                .uri("/projects/" + id)
                .exchange()
                .expectStatus().isNotFound()
                .expectBody(String.class).value(errorMessage ->
                    assertThat(errorMessage).isEqualTo("The project with Id " + id + " was not found.")
                );
    }

}
