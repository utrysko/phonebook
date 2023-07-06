package com.phonebook.service.impl;

import com.phonebook.domain.Contact;
import com.phonebook.dto.ContactDTO;
import com.phonebook.repository.ContactRepository;
import com.phonebook.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

/**
 * Class represents implementation of ContactService interface.
 *
 * @author Vasyl Utrysko
 * @version 1.0
 */
@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {

    private final ContactRepository repository;
    @Override
    public Contact saveContact(Contact contact, Integer userId) {
        contact.setUserId(userId);
        return repository.save(contact);
    }

    @Override
    public List<ContactDTO> getAllByUserId(Integer userId, Pageable pageable) {
        return repository.findContactsByUserId(userId, pageable)
                .stream()
                .map(contact -> ContactDTO.builder()
                        .id(contact.getId())
                        .name(contact.getName())
                        .emails(contact.getEmails())
                        .phones(contact.getPhones())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public Contact findByIdAndUserID(Integer contactId, Integer userId) {
        return repository.findContactByIdAndUserId(contactId, userId)
                .orElseThrow(() -> new NoSuchElementException("No Contact with this id"));
    }

    @Override
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
}
