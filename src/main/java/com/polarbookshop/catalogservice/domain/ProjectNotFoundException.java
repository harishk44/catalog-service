package com.polarbookshop.catalogservice.domain;

public class ProjectNotFoundException extends RuntimeException {

    public ProjectNotFoundException(String id) {
        super("The project with Id " + id + " was not found.");
    }

}
