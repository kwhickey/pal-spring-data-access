package io.pivotal.workshop.repository;

import io.pivotal.workshop.domain.Snippet;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Profile("jpa")
@Transactional
public interface JpaSnippetRepository extends SnippetRepository, CrudRepository<Snippet, String> {
}
