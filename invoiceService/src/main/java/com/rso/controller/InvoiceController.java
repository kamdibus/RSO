package com.rso.controller;

import com.auth0.exception.Auth0Exception;
import com.rso.dto.InvoiceEntityDto;
import com.rso.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/api/invoices")
public class InvoiceController {

    private InvoiceService invoiceService;

    @Autowired
    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    // TODO: Remove
    @GetMapping(path="/{invoiceId}")
    public ResponseEntity<?> getInvoiceById(@PathVariable final long invoiceId) {
        return invoiceService.getInvoiceById(invoiceId);
    }

    @DeleteMapping(path = "/{invoiceId}")
    public ResponseEntity<?> removeInvoiceById(@PathVariable final long invoiceId) {
        return invoiceService.removeInvoiceById(invoiceId);
    }

    // TODO: Remove
    @PutMapping(path = "/{invoiceId}")
    public ResponseEntity<?> editInvoiceDetails(@RequestBody final InvoiceEntityDto newInvoiceData, @PathVariable final Long invoiceId) {
        return invoiceService.editInvoiceById(newInvoiceData, invoiceId);
    }

    @PostMapping(path="/")
    public ResponseEntity<?> createNewInvoice(@RequestBody InvoiceEntityDto invoiceDto) {
        return invoiceService.createNewInvoice(invoiceDto);
    }

    @GetMapping(path="/")
    public ResponseEntity<?> getInvoicesForUser(@RequestHeader("authorization") String token) throws Auth0Exception {
        return invoiceService.getInvoiceForUser(token);
    }

    // TODO: Remove - userId should be harvested from Auth0
    @GetMapping(path = "/users/{userId}")
    public ResponseEntity<?> getUserInvoices(@PathVariable final long userId) {
        return invoiceService.getUserInvoices(userId);
    }

    // TODO: Remove - userId should be harvested from Auth0
    @DeleteMapping(value = "/users/{userId}")
    public ResponseEntity<?> removeUserInvoices(@PathVariable final long userId) {
        return invoiceService.removeUserInvoices(userId);
    }

    @GetMapping(path = "/test")
    public ResponseEntity<?> testInvoiceService() { return invoiceService.testInvoiceService(); }
}

