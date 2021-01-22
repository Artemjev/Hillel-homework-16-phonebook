package com.hillel.artemjev.phonebook.contact;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ContactParser {
    private static final String CONTACT_REGEX_PATTERN = "(.+)\\[(.+):(.+)\\]";
    private Pattern pattern;

    public ContactParser() {
        this.pattern = Pattern.compile(CONTACT_REGEX_PATTERN);
    }

    public Contact parse(String contactStr) {
        Contact contact = null;

        if (validateUserString(contactStr)) {
            Matcher matcher = pattern.matcher(contactStr);
            if ((matcher.find())) {
                String name = matcher.group(1);
                String type = matcher.group(2);
                String cont = matcher.group(3);
                contact = new Contact(name, ContactType.valueOf(type.toUpperCase()), cont);
            }
        }
        return contact;
    }
    //--------------------------------------------------------------------------------
    private boolean validateUserString(String userStr) {
        return userStr.matches(CONTACT_REGEX_PATTERN);
    }
}
