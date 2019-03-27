package com.rso.repository;

import org.springframework.data.repository.CrudRepository;

import com.rso.model.Quote;

public interface QuoteRepository extends CrudRepository<Quote, Long> {

}