package com.phonebook.service.impl;

import com.phonebook.domain.Contact;
import com.phonebook.repository.ContactRepository;
import com.phonebook.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {

    private final ContactRepository repository;
    @Override
    public Boolean addContact(Contact contact, Integer userId) {
        contact.setUserId(userId);
        try {
            repository.save(contact);
        } catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public List<Contact> getAllByUserId(Integer userId) {
        return repository.findContactsByUserId(userId);
    }

    @Override
    public Contact findById(Integer id) {
        return repository.findById(id).orElseThrow();
    }

    @Override
    public Boolean updateContact(Contact contact) {
        try {
            repository.save(contact);
        } catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public Boolean deleteById(Integer id) {
        try {
            repository.deleteById(id);
        } catch (Exception e){
            return false;
        }
        return true;
    }
}
