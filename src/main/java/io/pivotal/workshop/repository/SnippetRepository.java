package io.pivotal.workshop.repository;

import io.pivotal.workshop.domain.Snippet;

import java.util.List;

public interface SnippetRepository {
    Snippet save(Snippet snippet);

    List<Snippet> findAll();

    Snippet findById(String id);

    Snippet findOne(String id);
}
