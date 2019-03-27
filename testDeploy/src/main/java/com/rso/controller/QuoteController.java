package com.rso.controller;

import com.rso.model.Quote;
import com.rso.repository.AuthorRepository;
import com.rso.repository.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path="/quote")
public class QuoteController {
    @Autowired

    private AuthorRepository authorRepository;
    private QuoteRepository quoteRepository;

    @GetMapping(path="/add")
    public @ResponseBody String addNewQuote (@RequestParam String quote) {
        Quote q = new Quote();
        q.setQuote("New Quote");
        quoteRepository.save(q);
        return "Saved";
    }
}