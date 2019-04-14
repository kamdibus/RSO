package com.rso;

import com.rso.exceptions.InvalidPaymentIdException;
import com.rso.model.Payment;
import com.rso.repository.PaymentRepository;
import com.rso.service.ConsumerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
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
public class ConsumerServiceTests {

    @Mock
    PaymentRepository paymentRepository;

    @InjectMocks
    private ConsumerService consumerService;

	@Test
	public void contextLoads() {

	}

	@Test (expected = InvalidPaymentIdException.class)
    public void givenInvalidIdShouldThrowException() throws InvalidPaymentIdException {

	    //given
        final long invalidId = -1;

        //when
        consumerService.getPaymentForId(invalidId);
    }

    @Test
    public void givenValidIdShouldReturnPayment() throws InvalidPaymentIdException {

	    //given
        final long validId = 1;
        final Payment validPayment = new Payment(validId);
        final Optional<Payment> validPaymentOptional = Optional.of(validPayment);
        when(paymentRepository.findById(validId)).thenReturn(validPaymentOptional);

        //when
        final Payment fetchedPayment = consumerService.getPaymentForId(validId);

        //then
        assertThat(fetchedPayment.getId()).isEqualTo(validId);
    }



}
