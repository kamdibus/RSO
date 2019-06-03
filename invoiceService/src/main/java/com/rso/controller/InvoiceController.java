package com.rso.controller;

import com.auth0.exception.Auth0Exception;
import com.rso.dto.InvoiceDto;
import com.rso.model.Invoice;
import com.rso.service.InvoiceService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/api/invoices")
public class InvoiceController {

    private final InvoiceService invoiceService;

    private final ModelMapper modelMapper;

    @Autowired
    public InvoiceController(InvoiceService invoiceService, ModelMapper modelMapper) {
        this.invoiceService = invoiceService;
        this.modelMapper = modelMapper;
    }


//    // TODO: Remove
//    @GetMapping(path="/{invoiceId}")
//    public ResponseEntity<?> getInvoiceById(@PathVariable final long invoiceId) {
//        return invoiceService.getInvoiceById(invoiceId);
//    }

    @DeleteMapping(path = "/{invoiceId}")
    public ResponseEntity<?> removeInvoiceById(@PathVariable final long invoiceId) {
        return invoiceService.removeInvoiceById(invoiceId);
    }

//    // TODO: Remove
//    @PutMapping(path = "/{invoiceId}")
//    public ResponseEntity<?> editInvoiceDetails(@RequestBody final InvoiceDto newInvoiceData, @PathVariable final Long invoiceId) {
//        return invoiceService.editInvoiceById(newInvoiceData, invoiceId);
//    }

    @PostMapping(path="/")
    public ResponseEntity<?> createNewInvoice(@RequestBody InvoiceDto invoiceDto) {
        return invoiceService.createNewInvoice(invoiceDto);
    }

    @GetMapping(path="/")
    public ResponseEntity<?> getInvoicesForUser(@RequestHeader("authorization") String token) throws Auth0Exception {
        List<Invoice> invoices = invoiceService.getInvoicesForUser(token);
        if (invoices != null) {
            return new ResponseEntity<>("No invoices for user", HttpStatus.NOT_FOUND);
        }
        return invoices.
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

    @GetMapping(value = "/test")
    public ResponseEntity<?> testInvoiceService() {
        return new ResponseEntity<>("Invoice service OK", HttpStatus.OK);
    }

    private InvoiceDto convertToDto(Invoice invoice) {
        return modelMapper.map(invoice, InvoiceDto.class);
    }

    private List<Invoice>
}
