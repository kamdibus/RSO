package com.rso;

import com.rso.exceptions.InvalidInvoiceIdException;
import com.rso.model.Invoice;
import com.rso.repository.InvoiceRepository;
import com.rso.service.InvoiceService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration
@TestPropertySource("/test.properties")
public class InvoiceServiceTests {

    @Mock
    InvoiceRepository invoiceRepository;

    @InjectMocks
    private InvoiceService invoiceService;

	@Test
	public void contextLoads() {

	}

	@Test (expected = InvalidInvoiceIdException.class)
    public void givenInvalidIdShouldThrowException() throws InvalidInvoiceIdException {

	    //given
        final long invalidId = -1;

        //when
        invoiceService.getInvoiceById(invalidId);
    }

    @Test
    public void givenValidIdShouldReturnInvoice() throws InvalidInvoiceIdException {

	    //given
        final long validId = 1;
        final String data = "DATA";
        final Invoice validInvoice = new Invoice(validId, data);
        final Optional<Invoice> validInvoiceOptional = Optional.of(validInvoice);
        when(invoiceRepository.findById(validId)).thenReturn(validInvoiceOptional);

        //when
        final Invoice fetchedInvoice = invoiceService.getInvoiceById(validId);

        //then
        assertThat(fetchedInvoice.getId()).isEqualTo(validId);
    }



}
