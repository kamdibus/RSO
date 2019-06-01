package com.rso.service;

import com.rso.dto.InvoiceEntityDto;
import com.rso.exceptions.InvalidInvoiceIdException;
import com.rso.model.Invoice;
import com.rso.repository.InvoiceRepository;
import com.rso.util.DtoHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;


@Component
public class InvoiceService {

    private InvoiceRepository invoiceRepository;

    private DtoHandler dtoHandler;

    @Value("${payment.api.url}")
    private String apiPaymentService;

    @Value("${user.api.url}")
    private String apiUserService;


    @Autowired
    public InvoiceService(InvoiceRepository invoiceRepository, DtoHandler dtoHandler) {
        this.invoiceRepository = invoiceRepository;
        this.dtoHandler = dtoHandler;
    }

    private <T> ResponseEntity<?> mapInvoiceToDto(Invoice invoiceForId, Class<T> dtoType) {
        if (invoiceForId != null) {
            T mappedDto = dtoHandler.mapEntityToDto(invoiceForId, dtoType);
            return new ResponseEntity<>(mappedDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> getInvoiceById(long invoiceId) {
        Invoice invoiceForId = invoiceRepository.findFirstById(invoiceId);
        return mapInvoiceToDto(invoiceForId, InvoiceEntityDto.class);
    }

    public ResponseEntity<?> getUserInvoices(long userId) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<?> remoteUserInvoices(long userId) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<?> editDetailsForUserId(InvoiceEntityDto newData, Long invoiceId) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<?> editInvoiceById(InvoiceEntityDto newData, Long invoiceId) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<?> testInvoiceService() {
        return new ResponseEntity<>("Invoice service OK", HttpStatus.OK);
    }

    public ResponseEntity<?> createNewInvoice(InvoiceEntityDto invoiceDto) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
//        return new ResponseEntity<>("Created new Invoice", HttpStatus.CREATED);
    }
}
