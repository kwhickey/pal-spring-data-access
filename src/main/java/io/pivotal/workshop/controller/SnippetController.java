package io.pivotal.workshop.controller;

import io.pivotal.workshop.domain.Snippet;
import io.pivotal.workshop.repository.SnippetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class SnippetController {

    @Autowired
    SnippetRepository snippetRepository;

    @GetMapping("/snippets")
    public List<Snippet> snippets() {
        return snippetRepository.findAll();
    }

    @GetMapping("/snippets/{id}")
    public Snippet snippet(@PathVariable("id") String id) {
        return snippetRepository.findById(id);
    }

    @PostMapping("/snippets")
    public ResponseEntity<?> add(@RequestBody Snippet snippet) {
        Snippet savedSnippet = snippetRepository.save(snippet);
        assert savedSnippet != null;

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(ServletUriComponentsBuilder
                .fromCurrentRequest().path("/" + savedSnippet.getId())
                .buildAndExpand().toUri());

        return new ResponseEntity<>(savedSnippet, httpHeaders, HttpStatus.CREATED);
    }

    @PutMapping("/snippets")
    public ResponseEntity<?> update(@RequestBody Snippet snippet) {
        Snippet updatedSnippet = snippetRepository.save(snippet);
        if (updatedSnippet == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(ServletUriComponentsBuilder
                .fromCurrentRequest().path("/" + updatedSnippet.getId())
                .buildAndExpand().toUri());

        return new ResponseEntity<>(updatedSnippet, httpHeaders, HttpStatus.OK);
    }
}
