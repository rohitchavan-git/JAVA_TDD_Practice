package com.rohit.umtdd.builder;

import com.rohit.umtdd.domain.User;

public class UserBuilder {
    private String userid;
    private String username;
    private String about;


    public static UserBuilder  aUser() {
        return new UserBuilder();
    }

    public UserBuilder withUserid(String userid) {
        this.userid = userid;
        return this;
    }

    public UserBuilder withUsername(String username) {
        this.username = username;
        return this;
    }

    public UserBuilder withAbout(String about) {
        this.about = about;
        return this;
    }

    public User build() {
        return new User(userid , username , about);
    }
}