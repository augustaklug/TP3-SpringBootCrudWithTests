package com.klug.tp3crud.presentation.controller;

import com.klug.tp3crud.domain.model.Pessoa;
import com.klug.tp3crud.domain.repository.PessoaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

public abstract class PessoaController<T extends Pessoa> {

    private final PessoaRepository<T> repository;

    public PessoaController(PessoaRepository<T> repository) {
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<List<T>> getAll() {
        List<T> entities = repository.findAll();
        return new ResponseEntity<>(entities, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<T> create(@RequestBody T entity) {
        T createdEntity = repository.save(entity);
        return new ResponseEntity<>(createdEntity, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<T> update(@PathVariable Long id, @RequestBody T entityDetails) {
        T entity = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity not found for this id :: " + id));

        entity.setNome(entityDetails.getNome());
        entity.setEmail(entityDetails.getEmail());

        final T updatedEntity = repository.save(entity);
        return new ResponseEntity<>(updatedEntity, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        T entity = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity not found for this id :: " + id));

        repository.delete(entity);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}