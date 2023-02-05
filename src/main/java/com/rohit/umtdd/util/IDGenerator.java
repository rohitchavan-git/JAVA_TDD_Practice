package com.rohit.umtdd.util;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class IDGenerator {


    public String next() {
        return UUID.randomUUID().toString();
    }
}
