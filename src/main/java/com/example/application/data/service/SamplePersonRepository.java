package com.example.application.data.service;

import com.example.application.data.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SamplePersonRepository
        extends
            JpaRepository<Person, Long>,
            JpaSpecificationExecutor<Person> {

}
