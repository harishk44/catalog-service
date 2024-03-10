package com.polarbookshop.catalogservice.domain;

public class ProjectAlreadyExistsException extends RuntimeException {

    public ProjectAlreadyExistsException(String id) {
        super("The project with Id " + id + " already exists.");
    }

}
