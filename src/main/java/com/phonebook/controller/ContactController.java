package com.phonebook.controller;

import com.phonebook.domain.Contact;
import com.phonebook.service.impl.ContactServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/contacts")
@RequiredArgsConstructor
public class ContactController {

    private final ContactServiceImpl contactService;


    @GetMapping
    public ResponseEntity<List<Contact>> contacts(@SessionAttribute Integer userId) {
        return ResponseEntity.ok(contactService.getAllByUserId(userId));
    }

    @PutMapping
    public ResponseEntity<Boolean> addContact(@RequestBody @Valid Contact contact,
                                              @SessionAttribute Integer userId) {
        return ResponseEntity.ok(contactService.addContact(contact, userId));
    }

    @PostMapping
    public ResponseEntity<Boolean> editContact(@RequestBody @Valid Contact contact) {
        return ResponseEntity.ok(contactService.updateContact(contact));
    }

    @DeleteMapping
    public ResponseEntity<Boolean> deleteContact(@RequestBody Contact contact) {
        return ResponseEntity.ok(contactService.deleteById(contact.getId()));
    }
}
