package com.phonebook.service;

import com.phonebook.domain.Contact;

import java.util.List;

public interface ContactService {

    Boolean addContact(Contact contact, Integer userId);

    List<Contact> getAllByUserId(Integer userId);

    Contact findById(Integer id);

    Boolean updateContact(Contact contact);

    Boolean deleteById(Integer id);
}
