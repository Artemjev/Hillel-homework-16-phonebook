package com.hillel.artemjev.phonebook.service;

import com.hillel.artemjev.phonebook.contact.Contact;
import com.hillel.artemjev.phonebook.contact.ContactParser;
import com.hillel.artemjev.phonebook.contact.ContactType;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.stream.Collectors;

public class ContactsNioService implements ContactsService {
    private Path path;
    private ContactParser parser;
    private ByteBuffer buffer;

    public ContactsNioService(Path path, ContactParser parser, ByteBuffer buffer) {
        this.path = path;
        this.parser = parser;
        this.buffer = buffer;
        try {
            Files.writeString(path, "", StandardOpenOption.CREATE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ContactsNioService(String pathString, ContactParser parser, ByteBuffer buffer) {
        this(Path.of(pathString), parser, buffer);
    }

    //    TODO: хорошобы доработать (не хватило ни времени, ни мозгов).
    //     Читать не весь файл в одну строку, а вычитывать размер буфера. Проверять есть ли в считаном перевод строки.
    //     Если - есть парсить то, что до перевода строки и добовлять в возращаемый список контактов
    //     (остаток строки сохранять и добовлять его к следующему считыванию).
    @Override
    public List<Contact> getAll() {
        String text = "";
        try (FileChannel channel = (FileChannel) Files.newByteChannel(path)) {
            while (channel.read(buffer) != -1) {
                buffer.flip();
                text += new String(buffer.array(), 0, buffer.limit());
                buffer.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return parser.parseList(text, "\n");
    }

    //TODO: было несколько вариантов, как реализовать этот метод. Выбрал самый простой сугубо из-за нехватки времени.
    @Override
    public void remove(int index) {
        List<Contact> contactList = getAll();
        contactList.remove(index);
        try (FileChannel channel = (FileChannel) Files.newByteChannel(path, StandardOpenOption.WRITE,
                StandardOpenOption.APPEND)) {
            Files.writeString(path, "", StandardOpenOption.TRUNCATE_EXISTING);
            for (Contact contact : contactList) {
                String contactStr = parser.toString(contact) + "\n";
                buffer.put(contactStr.getBytes(), 0, contactStr.length());
                buffer.flip();
                channel.write(buffer);
                buffer.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void add(String name, ContactType type, String contact) {
        try (FileChannel channel = (FileChannel) Files.newByteChannel(path, StandardOpenOption.WRITE,
                StandardOpenOption.APPEND)) {
            String contactStr = parser.toString(new Contact(name, type, contact)) + "\n";
            //TODO: предусмотреть случай, когда добовляемая строка не влезет в буфер.
            buffer.put(contactStr.getBytes(), 0, contactStr.length());
            buffer.flip();
            channel.write(buffer);
            buffer.clear();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //    TODO: анадогично getAll(). Хорошо бы не считывать все контакты из файла сразу, а вычитывать размер буфера.
    //     Проверять есть ли в считаном перевод строки. Если - есть парсить то, что до перевода строки,
    //     проверять соответствует ли контакт условию поиска. Если да - добовлять в возращаемый список контактов
    @Override
    public List<Contact> searchByPhonePart(String phoneToSearch) {
        return getAll().stream()
                .filter(contact -> contact.getContact().contains(phoneToSearch))
                .collect(Collectors.toList());
    }

    @Override
    public List<Contact> searchByNameBeginning(String nameToSearch) {
        return getAll().stream()
                .filter(contact -> contact.getName().toUpperCase().startsWith(nameToSearch.toUpperCase()))
                .collect(Collectors.toList());
    }
}
