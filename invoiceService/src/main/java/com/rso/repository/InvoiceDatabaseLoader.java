package com.rso.repository;

import com.rso.model.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InvoiceDatabaseLoader implements CommandLineRunner {

    private final InvoiceRepository invoiceRepository;

    @Autowired
    public InvoiceDatabaseLoader(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    @Override
    public void run(String... strings) throws Exception {
        this.invoiceRepository.save(new Invoice(1, "pierwsza fakturka"));
        this.invoiceRepository.save(new Invoice(2, "druga fakturka"));
    }
}
