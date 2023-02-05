package com.rohit.umtdd.service;

import com.rohit.umtdd.domain.RegistrationData;
import com.rohit.umtdd.domain.User;
import com.rohit.umtdd.exception.UserAlreadyInUseException;
import com.rohit.umtdd.util.IDGenerator;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final IDGenerator idGenerator;
    public UserService(UserRepository userRepository , IDGenerator idGenerator) {
        this.userRepository = userRepository;
        this.idGenerator = idGenerator;
    }

    public User createUser(RegistrationData registrationData) throws UserAlreadyInUseException  {
        validateUsername(registrationData);
        User user = userFrom(registrationData);
        userRepository.add(user);
        return user;
    }

    private void validateUsername(RegistrationData registrationData) throws UserAlreadyInUseException {
        if (userRepository.isUsernameTaken(registrationData.getUsername())) {
            throw new UserAlreadyInUseException();
        }
    }

    private User userFrom(RegistrationData registrationData) {
        return new User(idGenerator.next() , registrationData.getUsername() ,
                registrationData.getAbout());
    }
}
