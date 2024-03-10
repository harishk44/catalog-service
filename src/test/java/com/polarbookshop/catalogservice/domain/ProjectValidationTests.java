package com.polarbookshop.catalogservice.domain;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ProjectValidationTests {

    private static Validator validator;

    @BeforeAll
    static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void whenAllFieldsCorrectThenValidationSucceeds() {
        var project = new Project("1234567890", "Title", "Description", new Date());
        Set<ConstraintViolation<Project>> violations = validator.validate(project);
        assertThat(violations).isEmpty();
    }

    @Test
    void whenIdNotDefinedThenValidationFails() {
        var project = new Project("", "Title", "Description", new Date());
        Set<ConstraintViolation<Project>> violations = validator.validate(project);
        assertThat(violations).hasSize(1);
        List<String> constraintViolationMessages = violations.stream()
                .map(ConstraintViolation::getMessage).collect(Collectors.toList());
        assertThat(constraintViolationMessages)
                .contains("The project id must be defined.");
    }

    /*@Test
    void whenIdDefinedButIncorrectThenValidationFails() {
        var book = new Project("a234567890", "Title", "Author", new Date());
        Set<ConstraintViolation<Project>> violations = validator.validate(book);
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage())
                .isEqualTo("The ISBN format must be valid.");
    }*/

    @Test
    void whenProjectNameIsNotDefinedThenValidationFails() {
        var book = new Project("1234567890", "", "Description", new Date());
        Set<ConstraintViolation<Project>> violations = validator.validate(book);
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage())
                .isEqualTo("The project name must be defined.");
    }

    @Test
    void whenDescriptionIsNotDefinedThenValidationFails() {
        var book = new Project("1234567890", "Title", "", new Date());
        Set<ConstraintViolation<Project>> violations = validator.validate(book);
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage())
                .isEqualTo("The description must be entered.");
    }

    @Test
    void whenDateIsNotDefinedThenValidationFails() {
        var book = new Project("1234567890", "Title", "Author", null);
        Set<ConstraintViolation<Project>> violations = validator.validate(book);
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage())
                .isEqualTo("The Date must be defined.");
    }

    /*@Test
    void whenPriceDefinedButZeroThenValidationFails() {
        var book = new Project("1234567890", "Title", "Author", 0.0);
        Set<ConstraintViolation<Project>> violations = validator.validate(book);
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage())
                .isEqualTo("The book price must be greater than zero.");
    }

    @Test
    void whenPriceDefinedButNegativeThenValidationFails() {
        var book = new Project("1234567890", "Title", "Author", -9.90);
        Set<ConstraintViolation<Project>> violations = validator.validate(book);
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage())
                .isEqualTo("The book price must be greater than zero.");
    }*/

}
