package com.rohit.umtdd.api;

import com.rohit.umtdd.builder.UserBuilder;
import com.rohit.umtdd.domain.RegistrationData;
import com.rohit.umtdd.domain.User;
import com.rohit.umtdd.exception.UserAlreadyInUseException;
import com.rohit.umtdd.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@SpringBootTest
class UserApiShould {


    private static final String USERNAME = "Rohit";
    private static final String PASSWORD = "Rohit@123";
    private static final String ABOUT = "ABOUT Rohit";
    private static final RegistrationData REGISTRATION_DATA =
            new RegistrationData(USERNAME , PASSWORD , ABOUT);
    private static final String USERID = UUID.randomUUID().toString();
    private static final User USER = new UserBuilder().withUserid(USERID).withUsername(USERNAME).withAbout(ABOUT).build();
    @Mock HttpServletResponse response;

    @Mock UserService userService;
    private UserApi userApi;


    @BeforeEach
    public void initialize() {
        userApi=new UserApi(userService);
    }

    @Test
    public void create_a_new_user() throws UserAlreadyInUseException {

        given(userService.createUser(REGISTRATION_DATA))
                .willReturn(USER);
        userApi.createUser(REGISTRATION_DATA , response);
        verify(userService).createUser(REGISTRATION_DATA);
        verify(response).setStatus(201);
        verify(response).setContentType("application/json");
    }

    @Test
    public void return_an_error_while_creating_user_with_same_name() throws UserAlreadyInUseException {


        given(userService.createUser(REGISTRATION_DATA))
                .willThrow(UserAlreadyInUseException.class);

        String result=userApi.createUser(REGISTRATION_DATA,response);
        verify(response).setStatus(400);
        assertThat("Username already in use.")
                .isEqualTo(result);
    }




}