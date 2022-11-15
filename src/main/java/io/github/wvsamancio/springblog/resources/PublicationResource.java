package io.github.wvsamancio.springblog.resources;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.github.wvsamancio.springblog.entities.Publication;
import io.github.wvsamancio.springblog.repositories.PublicationRepository;

@RestController
@RequestMapping(value = "/publications")
public class PublicationResource {

    @Autowired
    private PublicationRepository repository;

    @GetMapping
    public ResponseEntity<List<Publication>> findAll() {
        List<Publication> list = repository.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Publication> findById(@PathVariable Long id) {
        Optional<Publication> publication = repository.findById(id);
        if (publication.isPresent()) {
            return ResponseEntity.ok(publication.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<Publication> create(@RequestBody Publication publication) {
        Publication newPublication = repository.save(publication);
        return ResponseEntity.ok(newPublication);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Publication> delete(@PathVariable Long id) {
        Optional<Publication> publication = repository.findById(id);
        if (publication.isPresent()) {
            repository.delete(publication.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Publication> update(@PathVariable Long id,
            @RequestBody Publication newPublication) {
        Optional<Publication> oldPublicaton = repository.findById(id);
        if (oldPublicaton.isPresent()) {
            Publication publication = oldPublicaton.get();
            publication.setTitle(newPublication.getTitle());
            publication.setContent(newPublication.getContent());
            repository.save(publication);
            return ResponseEntity.ok(publication);
        }
        return ResponseEntity.notFound().build();
    }

}
