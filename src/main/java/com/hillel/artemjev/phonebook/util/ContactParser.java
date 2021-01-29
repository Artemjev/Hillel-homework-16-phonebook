package com.hillel.artemjev.phonebook.util;

import com.hillel.artemjev.phonebook.contact.Contact;
import com.hillel.artemjev.phonebook.contact.ContactType;

import java.util.LinkedList;
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

    public String toString(Contact contact) {
        return String.format("%s[%s:%s]", contact.getName(), contact.getType(), contact.getContact());
    }

    public List<Contact> parseList(String contactsString, String separator) {
        List<Contact> contactList = new LinkedList<>();
        List<String> contactStrList = getContactStringsList(contactsString, separator);
        for (String s : contactStrList) {
            contactList.add(parse(s));
        }
        return contactList;
    }

    //--------------------------------------------------------------------------------
    private boolean validateUserString(String userStr) {
        return userStr.matches(CONTACT_REGEX_PATTERN);
    }

    private List<String> getContactStringsList(String contactsStr, String separator) {
        String[] contactStringsArray = contactsStr.split(separator);
        List<String> contactStringsList = new LinkedList<>();
        for (String str : contactStringsArray) {
            str = str.trim();
            if (str.length() > 0) {
                contactStringsList.add(str);
            }
        }
        return contactStringsList;
    }
}
