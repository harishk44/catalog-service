package com.polarbookshop.catalogservice.domain;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Pattern;
//import javax.validation.constraints.Positive;

public record Project (

    @NotBlank(message = "The project id must be defined.")
    //@Pattern(regexp = "^([0-9]{10}|[0-9]{13})$", message = "The ISBN format must be valid.")
    String id,

    @NotBlank(message = "The project name must be defined.")
    String name,

    @NotBlank(message = "The description must be entered.")
    String description,

    @NotNull(message = "The Date must be defined.")
    //@Positive(message = "The book price must be greater than zero.")
    Date createdDate

){}
