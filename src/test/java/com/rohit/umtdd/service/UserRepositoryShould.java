package com.rohit.umtdd.service;

import com.rohit.umtdd.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static com.rohit.umtdd.builder.UserBuilder.aUser;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class UserRepositoryShould {


    private static final User ROHIT = aUser()
                                        .withUsername("rohit")
                                        .build();
    private static final User URMILA = aUser()
                                        .withUsername("urmila")
                                        .build();
    private static final User UNKNOWN = aUser()
                                        .withUsername("unknown")
                                        .build();
    private UserRepository userRepository;

    @BeforeEach
    public void initialize() {
        userRepository=new UserRepository();
    }

    @Test
    public void inform_username_is_already_taken() {

        userRepository.add(ROHIT);
        userRepository.add(URMILA);

        assertThat(userRepository.isUsernameTaken(ROHIT.getUsername())).isTrue();
        assertThat(userRepository.isUsernameTaken(URMILA.getUsername())).isTrue();
        assertThat(userRepository.isUsernameTaken(UNKNOWN.getUsername())).isFalse();
    }
}