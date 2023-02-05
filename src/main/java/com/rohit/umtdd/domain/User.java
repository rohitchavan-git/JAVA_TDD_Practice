package com.rohit.umtdd.domain;

import java.util.Objects;

public class User {

    private final String userid;
    private final String username;
    private final String about;

    public User(String userid , String username , String about) {
        this.userid = userid;
        this.username = username;
        this.about = about;
    }

    public String getUsername() {
        return username;
    }

    public String getUserid() {
        return userid;
    }

    public String getAbout() {
        return about;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return Objects.equals(getUsername() , user.getUsername()) && Objects.equals(getAbout() , user.getAbout());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername() , getAbout());
    }
}
