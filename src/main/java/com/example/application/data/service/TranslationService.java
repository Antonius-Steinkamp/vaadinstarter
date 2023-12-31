package com.example.application.data.service;

import com.example.application.data.entity.Translation;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class TranslationService {

    private final TranslationRepository repository;

    public TranslationService(TranslationRepository repository) {
        this.repository = repository;
    }

    public Optional<Translation> get(Long id) {
        return repository.findById(id);
    }

    public Translation update(Translation entity) {
        return repository.save(entity);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Page<Translation> list(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Page<Translation> list(Pageable pageable, Specification<Translation> filter) {
        return repository.findAll(filter, pageable);
    }

    public int count() {
        return (int) repository.count();
    }

}
