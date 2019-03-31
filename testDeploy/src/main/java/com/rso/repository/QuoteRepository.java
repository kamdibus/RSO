package com.rso.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.rso.model.Quote;

public interface QuoteRepository extends MongoRepository<Quote, Long> {

}