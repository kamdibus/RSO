package com.rso.service;

import com.auth0.client.auth.AuthAPI;
import com.auth0.exception.Auth0Exception;
import com.auth0.json.auth.UserInfo;
import com.auth0.net.Request;
import com.rso.model.Invoice;
import com.rso.repository.InvoiceRepository;
import com.rso.util.MongoSequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;


@Component
public class InvoiceService {

    private InvoiceRepository invoiceRepository;

    private MongoSequenceGeneratorService mongoSequenceGeneratorService;

    @Value("${payment.api.url}")
    private String apiPaymentService;

    @Value("${user.api.url}")
    private String apiUserService;

    @Value("${auth0.domain}")
    private String domain;

    @Value("${auth0.clientId}")
    private String clientId;

    @Value("${auth0.clientSecret}")
    private String clientSecret;

    @Value("${auth0.userMetadataUrl}")
    private String metaUrl;


    @Autowired
    public InvoiceService(InvoiceRepository invoiceRepository,
                          MongoSequenceGeneratorService mongoSequenceGeneratorService) {
        this.invoiceRepository = invoiceRepository;
        this.mongoSequenceGeneratorService = mongoSequenceGeneratorService;
    }

//
//    public ResponseEntity<?> remoteUserInvoices(long userId) {
//        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
//    }
//
//    public ResponseEntity<?> editDetailsForUserId(InvoiceDto newData, Long invoiceId) {
//        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
//    }

    public Invoice getInvoiceById(long invoiceId) {
        return invoiceRepository.findFirstById(invoiceId);
    }

//    public ResponseEntity<?> removeInvoiceById(long invoiceId) {
//        invoiceRepository.deleteById(invoiceId);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }

//    // TODO: Optional
//    public ResponseEntity<?> editInvoiceById(InvoiceDto newInvoiceData, Long invoiceId) {
//        Invoice invoiceToUpdate = invoiceRepository.findById(invoiceId).orElse(null);
//        if (invoiceToUpdate != null) {
//            invoiceToUpdate.setDate(newInvoiceData.getDate());
//            invoiceToUpdate.setSupplierId(newInvoiceData.getSupplierId());
//            invoiceToUpdate.setConsumerId(newInvoiceData.getConsumerId());
//            invoiceToUpdate.setAmount(newInvoiceData.getAmount());
//            invoiceToUpdate.setOtherData(newInvoiceData.getOtherData());
//            invoiceRepository.save(invoiceToUpdate);
//            return new ResponseEntity<>(HttpStatus.OK);
//        } else
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }
//
//    public ResponseEntity<?> getUserInvoices(long userId) {
//        invoiceRepository.deleteBySupplierIdOrConsumerId(userId, userId);
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }
//
//    public ResponseEntity<?> removeUserInvoices(long userId) {
//        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
//    }


    public Invoice createNewInvoice(Invoice invoiceToCreate) {
        invoiceToCreate.setId(mongoSequenceGeneratorService.generateSequence(Invoice.SEQUENCE_NAME));
        invoiceRepository.save(invoiceToCreate);
        return invoiceToCreate;
    }

    private Long extractUserIdFromAuth0(String token) {
        AuthAPI auth = new AuthAPI(domain, clientId, clientSecret);
        Request<UserInfo> request2 = auth.userInfo(token.replace("Bearer ", ""));
        UserInfo info;
        try {
            info = request2.execute();
        } catch (Auth0Exception exception) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        HashMap userMeta = (HashMap) info.getValues().get(metaUrl);
        return Long.parseLong((String) userMeta.get("nip"));
    }

//    public List<Invoice> getInvoicesForUser(String token) {
//        long userId = extractUserIdFromAuth0(token);
//        return invoiceRepository.findBySupplierIdOrConsumerId(userId, userId);
//    }
}

