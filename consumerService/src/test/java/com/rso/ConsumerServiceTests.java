package com.rso;

import com.rso.dto.UserEntityDto;
import com.rso.model.User;
import com.rso.model.UserStatus;
import com.rso.repository.UserRepository;
import com.rso.service.UserService;
import com.rso.util.DtoHandler;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration
@TestPropertySource("/test.properties")
public class ConsumerServiceTests {

    @Mock
    UserRepository userRepository;

    @Mock
    DtoHandler dtoHandler;

    @InjectMocks
    private UserService userService;

	@Test
	public void contextLoads() {

	}

    @Test
    public void givenValidNipNumberShouldReturnUserEntityDto() {
	    //given
        final String validNip = "1";
        final User testUser = new User(validNip);
        when(userRepository.findFirstByNip(validNip)).thenReturn(testUser);
        when(dtoHandler.mapEntityToDto(testUser, UserEntityDto.class)).thenReturn(new UserEntityDto());

        //when

        final ResponseEntity<?> validResponse = userService.getCompanyDetailsForNip(validNip);
        final Object responseBody = validResponse.getBody();
        //then
        assertThat(responseBody, instanceOf(UserEntityDto.class));
    }

    @Test
    public void givenInvalidNipShouldReturnNotFound(){
	    //given
	    final String invalidNip = "0";

	    //when
	    when(userRepository.findFirstByNip(invalidNip)).thenReturn(null);
	    final ResponseEntity<?> invalidNipResponse = userService.getCompanyDetailsForNip(invalidNip);
	    HttpStatus invalidNipResponseCode = invalidNipResponse.getStatusCode();

	    //then
        assertEquals(HttpStatus.NOT_FOUND, invalidNipResponseCode);
    }

    @Test
    public void givenValidIdShouldDeleteUserAccountCorrectly() {
	    //given
        User userToDelete = new User();
        userToDelete.setUserStatus(UserStatus.ACCEPTED);
        when(userRepository.findById(1L)).thenReturn(Optional.of(userToDelete));

        //when
        userService.deleteAccountForUserId(1L);

        //then
        assertEquals(UserStatus.DELETED, userToDelete.getUserStatus());
    }

    @Test
    public void givenIdOfDeletedAccountShouldntAllowForDataEdit() {
	    //given
        User uneditableUser = new User();
        uneditableUser.setUserStatus(UserStatus.DELETED);
        when(userRepository.findById(1L)).thenReturn(Optional.of(uneditableUser));

        //when
        ResponseEntity response = userService.editDetailsForUserId(new UserEntityDto(), 1L);

        //then
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }
}
