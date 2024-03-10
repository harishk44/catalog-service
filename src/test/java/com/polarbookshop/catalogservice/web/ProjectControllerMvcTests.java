package com.polarbookshop.catalogservice.web;

import com.polarbookshop.catalogservice.domain.ProjectNotFoundException;
import com.polarbookshop.catalogservice.domain.ProjectService;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProjectController.class)
class ProjectControllerMvcTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProjectService projectService;

    @Test
    void whenGetProjectNotExistingThenShouldReturn404() throws Exception {
        String id = "073737313940";
        given(projectService.viewProjectDetails(id)).willThrow(ProjectNotFoundException.class);
        mockMvc
                .perform(get("/books/" + id))
                .andExpect(status().isNotFound());
    }

}
