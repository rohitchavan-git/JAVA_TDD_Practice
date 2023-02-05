package com.rohit.umtdd.service;

import com.rohit.umtdd.domain.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {

    private List<User> users=new ArrayList<>();

    public void add(User user) {
       users.add(user);
    }

    public boolean isUsernameTaken(String username) {
        return users.stream()
                .map(User::getUsername)
                .anyMatch(username::equalsIgnoreCase);
    }

}
