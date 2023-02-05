package com.rohit.umtdd.service;

import com.rohit.umtdd.domain.RegistrationData;
import com.rohit.umtdd.domain.User;
import com.rohit.umtdd.exception.UserAlreadyInUseException;
import com.rohit.umtdd.util.IDGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static com.rohit.umtdd.builder.UserBuilder.aUser;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@SpringBootTest
class UserServiceShould {


    private static final String USERID = UUID.randomUUID().toString();
    private static final String USERNAME = "ROHIT";
    private static final String ABOUT = "ABOUT Rohit";
    private static final String PASSWORD = "ROHIT@!@#";
    private static final User ROHIT = aUser()
                                                .withUserid(USERID)
                                                .withUsername(USERNAME)
                                                .withAbout(ABOUT)
                                                .build();
    private static final RegistrationData REGISTRATION_DATA =
            new RegistrationData(USERNAME, PASSWORD ,ABOUT);
    @Mock
    UserRepository userRepository;


    @Mock IDGenerator idGenerator;
    private UserService userService;

    @BeforeEach
    public void initialize() {
        userService=new UserService(userRepository,idGenerator);
    }

    @Test
    public void create_a_new_user() throws UserAlreadyInUseException {

        given(idGenerator.next()).willReturn(USERID);

        User user = userService.createUser(REGISTRATION_DATA);
        verify(userRepository).add(ROHIT);

        assertThat(user.getUserid()).isEqualTo(USERID);
    }

    @Test
    public void throw_an_exception_while_creating_user_with_same_name() throws UserAlreadyInUseException {

        given(userRepository.isUsernameTaken(ROHIT.getUsername()))
                .willReturn(true);

        assertThrows(UserAlreadyInUseException.class,()->
                userService.createUser(REGISTRATION_DATA));


    }

    
    
}