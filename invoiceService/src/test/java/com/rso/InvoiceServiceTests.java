package com.rso;

import com.rso.dto.InvoiceEntityDto;
import com.rso.model.Invoice;
import com.rso.repository.InvoiceRepository;
import com.rso.service.InvoiceService;
import com.rso.util.DtoHandler;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration
@TestPropertySource("/test.properties")
public class InvoiceServiceTests {

    @Mock
    InvoiceRepository invoiceRepository;

    @Mock
    DtoHandler dtoHandler;

    @InjectMocks
    private InvoiceService invoiceService;

    @Test
    public void contextLoads() {

    }

    @Test
    public void givenValidInvoiceIdShouldReturnInvoiceEntityDto() {
        //given
        final long invoiceId = 1;
        final String invoiceData = "data";
        final Invoice testInvoice = new Invoice(invoiceId, invoiceData);
        when(invoiceRepository.findFirstById(invoiceId)).thenReturn(testInvoice);
        when(dtoHandler.mapEntityToDto(testInvoice, InvoiceEntityDto.class)).thenReturn(new InvoiceEntityDto());

        //when
        final ResponseEntity<?> validResponse = invoiceService.getInvoiceById(invoiceId);
        final Object responseBody = validResponse.getBody();
        //then
        assertThat(responseBody, instanceOf(InvoiceEntityDto.class));
    }
}
