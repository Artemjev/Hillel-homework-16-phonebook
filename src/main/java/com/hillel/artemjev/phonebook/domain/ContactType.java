package com.hillel.artemjev.phonebook.domain;

public enum ContactType {
    PHONE,
    EMAIL,
    //email, phone - костыльные значения.
// Не получалось. Для устранения потенциальных проблем в сущности Contact поле type сделал cтринговым.
//...ну и насоздовал контактов с невалидными значениями этого поля. Удаления то нет пока))
    email,
    phone
}
