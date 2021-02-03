package com.hillel.artemjev.phonebook.service.contacts;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hillel.artemjev.phonebook.domain.Contact;
import com.hillel.artemjev.phonebook.domain.ContactType;
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
public class ContactsApiService implements ContactsService {
    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;
    private final AccessToken token;

    @Override
    public List<Contact> getAll() {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://mag-contacts-api.herokuapp.com/contacts"))
                .GET()
                .header("Authorization", "Bearer " + token.getToken())
                .header("Accept", "application/json")
                .build();
        //TODO: в дз  выкинуть свой runtime exception
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            ContactsResponse contactsResponse = objectMapper.readValue(response.body(), ContactsResponse.class);
            return contactsResponse.getContacts();
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Contact> searchByName(String nameToSearch) {
        try {
            String body = objectMapper.writeValueAsString(new ContactFindByNameRequest(nameToSearch));
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://mag-contacts-api.herokuapp.com/contacts/find"))
                    .header("Authorization", "Bearer " + token.getToken())
                    .header("Content-Type", "application/json")
                    .header("Accept", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(body))
                    .build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            ContactsResponse contactsResponse = objectMapper.readValue(response.body(), ContactsResponse.class);
            return contactsResponse.getContacts();
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Contact> searchByContact(String contactToSearch) {
        try {
            String body = objectMapper.writeValueAsString(new ContactFindByValueRequest(contactToSearch));
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://mag-contacts-api.herokuapp.com/contacts/find"))
                    .header("Authorization", "Bearer " + token.getToken())
                    .header("Content-Type", "application/json")
                    .header("Accept", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(body))
                    .build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            ContactsResponse contactsResponse = objectMapper.readValue(response.body(), ContactsResponse.class);
            return contactsResponse.getContacts();
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    //А если не добавило - выбрасывать свое исключение из метода и обрабатывать его в меню?! метод ничего не возвращает
    @Override
    public void add(String name, ContactType type, String contact) {
        try {
            String body = objectMapper.writeValueAsString(new ContactAddRequest(name, type.toString(), contact));
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://mag-contacts-api.herokuapp.com/contacts/add"))
                    .header("Authorization", "Bearer " + token.getToken())
                    .header("Content-Type", "application/json")
                    .header("Accept", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(body))
                    .build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response);
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean hasToken() {
        return this.token.isValid();
    }

    //------------------------------------------------------------------
    @Override
    public void remove(int index) {
        System.out.println("removed");
    }

    @Override
    public List<Contact> searchByPhonePart(String phoneToSearch) {
        System.out.println("searchByPhonePart");
        return null;
    }

    @Override
    public List<Contact> searchByNameBeginning(String nameToSearch) {
        System.out.println("searchByNameBeginning");
        return null;
    }
}
