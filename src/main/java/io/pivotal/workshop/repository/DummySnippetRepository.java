package io.pivotal.workshop.repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import io.pivotal.workshop.domain.Snippet;


@Repository
@Profile("dummy")
public class DummySnippetRepository implements SnippetRepository {

    private List<Snippet> snippets = new ArrayList<Snippet>() {{
            add(new Snippet("JavaScript: Hello World", "console.log('Hello World!');"));
            add(new Snippet("HTML: Hello World", "<html><body><h1>Hello World</h1></body></html>"));
            add(new Snippet("Bash: Hello World", "echo \"Hello World\""));
            add(new Snippet("Python: Hello World", "print \"Hello World\""));
    }};

    @Override
    public Snippet save(Snippet snippet) {
        assert snippet.getTitle() != null;
        assert snippet.getCode() != null;
        if (snippet.getId() != null) {
            return update(snippet);
        }

        Snippet savedSnippet = new Snippet(snippet.getTitle(), snippet.getCode());
        this.snippets.add(savedSnippet);

        return savedSnippet;
    }

    @Override
    public List<Snippet> findAll() {
        return snippets;
    }

    @Override
    public Snippet findById(String id) {
        return snippets.stream()
                .filter(snippet -> snippet.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Snippet findOne(String id) {
        return findById(id);
    }

    public Snippet update(Snippet snippet) {
        Optional<Snippet> opt = snippets.stream().filter(s -> s.getId().equals(snippet.getId())).findFirst();
        if (!opt.isPresent()) {
            return null;
        }
        Snippet snip = opt.get();
        snip.setTitle(snippet.getTitle());
        snip.setCode(snippet.getCode());

        Iterator<Snippet> iter = snippets.iterator();
        while (iter.hasNext()) {
            if (iter.next().getId().equals(snippet.getId())) {
                iter.remove();
            }
        }

        snippets.add(snip);

        return snip;
    }
}
