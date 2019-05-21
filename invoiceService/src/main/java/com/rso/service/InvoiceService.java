package com.rso.service;

import com.rso.exceptions.InvalidInvoiceIdException;
import com.rso.model.Invoice;
import com.rso.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class InvoiceService {

    private InvoiceRepository invoiceRepository;

    @Autowired
    public InvoiceService(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    public Invoice getInvoiceById(long id) throws InvalidInvoiceIdException {
        if (!invoiceRepository.findById(id).isPresent()) {
            throw new InvalidInvoiceIdException();
        }
        return invoiceRepository.findById(id).get();
    }
}
