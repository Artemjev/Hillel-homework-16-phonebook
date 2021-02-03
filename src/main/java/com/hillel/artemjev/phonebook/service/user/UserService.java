package com.hillel.artemjev.phonebook.service.user;

import com.hillel.artemjev.phonebook.domain.User;
import com.hillel.artemjev.phonebook.dto.LoginResponse;
import com.hillel.artemjev.phonebook.dto.RegistrationResponse;

import java.util.List;

public interface UserService {
    //Можно было бы сделать, что бы методы registration(..) и login(..) ничего не возвращали.
    //Возможно так было бы правильнее (рефрешить токен в самих методах сервиса, а не меню).
    //Если это критично - переделаю.
    RegistrationResponse registration(String login, String password, String dateBorn);

    LoginResponse login(String login, String password);

    List<User> getAll();

    List<User> getAllWithAuth();

    boolean hasToken();

    void refreshToken(String token);
}
