package com.hillel.artemjev.phonebook.dto;

import com.hillel.artemjev.phonebook.domain.User;
import lombok.Data;

import java.util.List;

@Data
public class UserResponse {
    private String status;
    private List<User> users;
    private String error;
}
