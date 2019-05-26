package com.rso.repository;

import com.rso.model.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InvoiceRepositoryLoader implements CommandLineRunner {

    private final InvoiceRepository invoiceRepository;

    @Autowired
    public InvoiceRepositoryLoader(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    @Override
    public void run(String... strings) throws Exception {
        this.invoiceRepository.save(new Invoice(1, "first invoice"));
        this.invoiceRepository.save(new Invoice(2, "second invoice"));
        this.invoiceRepository.save(new Invoice(3, "third invoice"));
    }
}
