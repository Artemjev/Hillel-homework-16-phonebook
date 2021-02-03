package com.hillel.artemjev.phonebook.service.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hillel.artemjev.phonebook.domain.User;
import com.hillel.artemjev.phonebook.dto.*;
import com.hillel.artemjev.phonebook.service.AccessToken;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@RequiredArgsConstructor
public class UserApiService implements UserService {
    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;
    final private AccessToken token;

    @Override
    public RegistrationResponse registration(String login, String password, String dateBorn) {
        try {
            String body = objectMapper.writeValueAsString(new RegistrationRequest(login, password, dateBorn));
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://mag-contacts-api.herokuapp.com/register"))
                    .header("Content-Type", "application/json")
                    .header("Accept", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(body))
                    .build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            return objectMapper.readValue(
                    response.body(),
                    RegistrationResponse.class
            );
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public LoginResponse login(String login, String password) {
        //TODO: в дз  выкинуть свой runtime exception
        try {
            String body = objectMapper.writeValueAsString(new LoginRequest(login, password));
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://mag-contacts-api.herokuapp.com/login"))
                    .header("Content-Type", "application/json")
                    .header("Accept", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(body))
                    .build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            return objectMapper.readValue(
                    response.body(),
                    LoginResponse.class
            );
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<User> getAll() {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://mag-contacts-api.herokuapp.com/users"))
                .GET()
                .header("Accept", "application/json")
                .build();
        //TODO: в дз  выкинуть свой runtime exception
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            UserResponse userResponse = objectMapper.readValue(response.body(), UserResponse.class);
            return userResponse.getUsers();
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<User> getAllWithAuth() {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://mag-contacts-api.herokuapp.com/users2"))
                .header("Authorization", "Bearer " + token.getToken())
                .header("Accept", "application/json")
                .GET()
                .build();
        //TODO: в дз  выкинуть свой runtime exception
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            UserResponse userResponse = objectMapper.readValue(response.body(), UserResponse.class);
            return userResponse.getUsers();
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean hasToken() {
        return this.token.isValid();
    }

    @Override
    public void refreshToken(String token) {
        this.token.refreshToken(token);
    }
}
