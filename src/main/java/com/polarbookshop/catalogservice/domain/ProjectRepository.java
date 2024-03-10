package com.polarbookshop.catalogservice.domain;

import java.util.Optional;

public interface ProjectRepository {

	Iterable<Project> findAll();
	Optional<Project> findById(String id);
	boolean existsById(String id);
	Project save(Project project);
	void deleteById(String id);

}
