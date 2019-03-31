package com.rso.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.rso.model.Author;

public interface AuthorRepository extends MongoRepository<Author, Long> {
    
}