package com.phonebook.service;

import com.phonebook.domain.Contact;
import com.phonebook.dto.ContactDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Interface represents method to work with contacts.
 *
 * @author Vasyl Utrysko
 * @version 1.0
 */
public interface ContactService {

    /**
     * Method to save contact in database.
     */
    Contact saveContact(Contact contact, Integer userId);

    /**
     * Method to getting contacts from db.
     */
    List<ContactDTO> getAllByUserId(Integer userId, Pageable pageable);

    /**
     * Method to get contact with specific id from db.
     */
    Contact findById(Integer id);

    /**
     * Method to delete contact with specific id from db.
     */
    void deleteById(Integer id);
}
