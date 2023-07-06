package com.phonebook.controller;

import com.phonebook.domain.Contact;
import com.phonebook.dto.ContactDTO;
import com.phonebook.service.ContactService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ContactControllerTest {

    static ContactService mockService;
    static ContactController controller;
    static Contact contact;
    static ContactDTO contactDTO;

    @BeforeAll
    static public void globalSetUp(){
        mockService = mock(ContactService.class);
        controller = new ContactController(mockService);
        contact = Contact.builder()
                .name("Andrew")
                .emails(List.of("12345@gmail.com"))
                .phones(List.of("+380735558866"))
                .build();
        contactDTO = ContactDTO.builder()
                .name("Andrew")
                .emails(List.of("12345@gmail.com"))
                .phones(List.of("+380735558866"))
                .build();
    }

    @BeforeEach
    public void setUp(){
        reset(mockService);
        when(mockService.getAllByUserId(any(Integer.class), any(Pageable.class))).thenReturn(List.of(contactDTO));
        when(mockService.findByIdAndUserID(anyInt(), anyInt())).thenReturn(contact);
        when(mockService.saveContact(any(Contact.class), anyInt())).thenReturn(contact);
        doNothing().when(mockService).deleteById(anyInt());
    }


    @Test
    void contacts() {
        ResponseEntity<List<ContactDTO>> responseEntity = controller.contacts(1, Optional.empty(), Optional.empty());
        Pageable pageable = PageRequest.of(0, 5);
        assertTrue(responseEntity.getBody().contains(contactDTO));
        verify(mockService, times(1)).getAllByUserId(1, pageable);
    }

    @Test
    void contact() {
        ResponseEntity<Contact> responseEntity = controller.contact(1, 1);

        assertEquals(contact, responseEntity.getBody());
        verify(mockService, times(1)).findByIdAndUserID(1, 1);
    }

    @Test
    void addContact() throws IOException {
        ResponseEntity<Contact> responseEntity = controller.addContact(contact, null, 12);

        assertEquals(contact, responseEntity.getBody());
        verify(mockService, times(1)).saveContact(contact, 12);
    }

    @Test
    void editContact() throws IOException {
        ResponseEntity<Contact> responseEntity = controller.editContact(contact, 2, null, 12);

        verify(mockService, times(1)).saveContact(contact, 12);
        assertEquals(2, responseEntity.getBody().getId());
    }

    @Test
    void deleteContact() {
        ResponseEntity<String> responseEntity = controller.deleteContact(2);

        verify(mockService, times(1)).deleteById(2);
        assertEquals(responseEntity.getBody(), "contact deleted");
    }
}