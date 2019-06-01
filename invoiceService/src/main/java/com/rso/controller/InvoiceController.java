package com.rso.controller;

import com.rso.dto.InvoiceEntityDto;
import com.rso.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping(path="/api/invoices")
public class InvoiceController {

    private InvoiceService invoiceService;

    @Autowired
    public InvoiceController(InvoiceService invoiceService) { this.invoiceService = invoiceService; }

    @GetMapping(path="/{invoiceId}")
    public ResponseEntity<?> getInvoiceInfo (@PathVariable final long invoiceId) {
        return invoiceService.getInvoiceById(invoiceId);
    }

    @PostMapping(path="/")
    public ResponseEntity<?> createNewInvoice(@RequestBody InvoiceEntityDto invoiceDto) {
        return invoiceService.createNewInvoice(invoiceDto);
    }

    @GetMapping(value = "/users/{userId}")
    public ResponseEntity<?> getUserInvoices(@PathVariable final long userId) {
        return invoiceService.getUserInvoices(userId);
    }

    @DeleteMapping(value = "/users/{userId}")
    public ResponseEntity<?> removeUserInvoices(@PathVariable final long userId) {
        return invoiceService.remoteUserInvoices(userId);
    }

    @PutMapping(value = "/{invoiceId}")
    public ResponseEntity<?> editInvoiceDetails(@RequestBody final InvoiceEntityDto newData, @PathVariable final Long invoiceId) {
        return invoiceService.editInvoiceById(newData, invoiceId);
    }

    @GetMapping(value = "/test")
    public ResponseEntity<?> testInvoiceService() { return invoiceService.testInvoiceService(); }
}

