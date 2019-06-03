package com.rso.service;

import com.rso.dto.InvoiceEntityDto;
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

    @Value("${payments.api.url}")
    private String apiPaymentService;

    @Value("${users.api.url}")
    private String apiUserService;

    @Value("${offers.api.url}")
    private String apiOfferService;

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

    public ResponseEntity<?> testInvoiceService() {
        return new ResponseEntity<>("Invoice service OK", HttpStatus.OK);
    }

    // TODO: Optional
    public ResponseEntity<?> getInvoiceById(long invoiceId) {
        Invoice invoiceForId = invoiceRepository.findFirstById(invoiceId);
        return mapInvoiceToDto(invoiceForId, InvoiceEntityDto.class);
    }

    public ResponseEntity<?> removeInvoiceById(long invoiceId) {
        invoiceRepository.deleteById(invoiceId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // TODO: Optional
    public ResponseEntity<?> editInvoiceById(InvoiceEntityDto newInvoiceData, Long invoiceId) {
        Invoice invoiceToUpdate = invoiceRepository.findById(invoiceId).orElse(null);
        if (invoiceToUpdate != null) {
            invoiceToUpdate.setDate(newInvoiceData.getDate());
            invoiceToUpdate.setSupplierId(newInvoiceData.getSupplierId());
            invoiceToUpdate.setConsumerId(newInvoiceData.getConsumerId());
            invoiceToUpdate.setAmount(newInvoiceData.getAmount());
            invoiceToUpdate.setOtherData(newInvoiceData.getOtherData());
            invoiceRepository.save(invoiceToUpdate);
            return new ResponseEntity<>(HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<?> getUserInvoices(long userId) {
        invoiceRepository.deleteBySupplierIdOrConsumerId(userId, userId);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<?> removeUserInvoices(long userId) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }


    public ResponseEntity<?> createNewInvoice(InvoiceEntityDto invoiceDto) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
//        return new ResponseEntity<>("Created new Invoice", HttpStatus.CREATED);
    }

    public ResponseEntity<?> getInvoiceForUser(String token) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
