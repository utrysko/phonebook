package com.phonebook.service.impl;

import com.phonebook.domain.Contact;
import com.phonebook.dto.ContactDTO;
import com.phonebook.repository.ContactRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

class ContactServiceImplTest {

    static ContactRepository mockRepository;
    static Contact contact;
    static ContactDTO contactDTO;
    static ContactServiceImpl service;

    @BeforeAll
    static public void globalSetUp(){
        mockRepository = mock(ContactRepository.class);
        service = new ContactServiceImpl(mockRepository);
        contact = Contact.builder()
                .name("Andrew")
                .emails(Set.of("12345@gmail.com"))
                .phones(Set.of("+380735558866"))
                .build();
        contactDTO = ContactDTO.builder()
                .name("Andrew")
                .emails(Set.of("12345@gmail.com"))
                .phones(Set.of("+380735558866"))
                .build();
    }

    @BeforeEach
    public void setUp(){
        reset(mockRepository);
        when(mockRepository.save(any(Contact.class))).thenReturn(contact);
        when(mockRepository.findContactsByUserId(anyInt(), any(Pageable.class))).thenReturn(List.of(contact));
        when(mockRepository.findContactByIdAndUserId(anyInt(), anyInt())).thenReturn(Optional.of(contact));
        doNothing().when(mockRepository).deleteById(anyInt());
    }

    @Test
    void saveContact() {
        Contact savedContact = service.saveContact(contact, 12);

        assertEquals(12, savedContact.getUserId());
        verify(mockRepository, times(1)).save(any(Contact.class));
    }

    @Test
    void getAllByUserId() {
        List<ContactDTO> contactsDTO = service.getAllByUserId(12, Pageable.unpaged());

        assertTrue(contactsDTO.contains(contactDTO));
        verify(mockRepository, times(1)).findContactsByUserId(anyInt(), any(Pageable.class));
    }

    @Test
    void findByIdAndUserID() {
        Contact contactFromDB = service.findByIdAndUserID(1, 12);

        assertEquals(contact, contactFromDB);
        verify(mockRepository, times(1)).findContactByIdAndUserId(1, 12);
    }

    @Test
    void deleteById() {
        service.deleteById(12);

        verify(mockRepository, times(1)).deleteById(12);
    }
}