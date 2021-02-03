package com.hillel.artemjev.phonebook.service;

import lombok.Getter;

import java.time.Duration;
import java.time.LocalDateTime;

public class AccessToken {
    @Getter
    private String token;
    private LocalDateTime time;

    public void refreshToken(String token) {
        this.token = token;
        this.time = LocalDateTime.now();
    }

    public boolean isValid() {
//        return token != null && getLifetimeInSeconds() < 55;
        return token != null && getLifetimeInSeconds() < 3555;
    }

    //----------------------------------------------------------------
    private long getLifetimeInSeconds() {
        return Duration.between(time, LocalDateTime.now())
                .getSeconds();
    }
}
