package com.polarbookshop.catalogservice.persistence;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import com.polarbookshop.catalogservice.domain.Project;
import com.polarbookshop.catalogservice.domain.ProjectRepository;

import org.springframework.stereotype.Repository;

@Repository
public class InMemoryProjectRepository implements ProjectRepository {

	private static final Map<String, Project> projects = new ConcurrentHashMap<>();

	@Override
	public Iterable<Project> findAll() {
		return projects.values();
	}

	@Override
	public Optional<Project> findById(String isbn) {
		return existsById(isbn) ? Optional.of(projects.get(isbn)) : Optional.empty();
	}

	@Override
	public boolean existsById(String isbn) {
		return projects.get(isbn) != null;
	}

	@Override
	public Project save(Project book) {
		projects.put(book.id(), book);
		return book;
	}

	@Override
	public void deleteById(String isbn) {
		projects.remove(isbn);
	}

}
