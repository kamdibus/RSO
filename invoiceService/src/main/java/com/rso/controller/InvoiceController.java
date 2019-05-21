package com.rso.controller;

import com.rso.exceptions.InvalidInvoiceIdException;
import com.rso.model.Invoice;
import com.rso.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping(path="/invoices")
public class InvoiceController {

    private InvoiceService invoiceService;

    @Autowired
    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping(path="/{id}")
    public @ResponseBody
    Invoice getInvoiceInfo (@PathVariable final long id) {
       try {
           return invoiceService.getInvoiceById(id);
       } catch (InvalidInvoiceIdException e) {
           throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No invoice for this id", e);
        }
    }
}
