package com.polarbookshop.catalogservice.web;

import com.polarbookshop.catalogservice.domain.Project;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import static org.assertj.core.api.Assertions.assertThat;

import java.text.SimpleDateFormat;
//import java.util.Date;

@JsonTest
class ProjectJsonTests {

    @Autowired
    private JacksonTester<Project> json;

    @Test
    void testSerialize() throws Exception {
        var project = new Project("1234567890", "Title", "description", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").parse("2024-01-01T00:00:00Z"));
        var jsonContent = json.write(project);
        assertThat(jsonContent).extractingJsonPathStringValue("@.id")
                .isEqualTo(project.id());
        assertThat(jsonContent).extractingJsonPathStringValue("@.name")
                .isEqualTo(project.name());
        assertThat(jsonContent).extractingJsonPathStringValue("@.description")
                .isEqualTo(project.description());
        //assertThat(jsonContent).extractingJsonPathValue("@.createdDate")
        //        .isEqualTo(project.createdDate());
    }

    /*@Test
    void testDeserialize() throws Exception {
        var content = """
                {
                    "id": "1234567890",
                    "name": "Title",
                    "description": "description",
                    "createdDate": "2024-01-01T00:00:00Z"
                }
                """;
        assertThat(json.parse(content))
                .usingRecursiveComparison()
                .isEqualTo(new Project("1234567890", "Title", "description", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").parse("2024-01-01T00:00:00Z")));
    }*/

}
