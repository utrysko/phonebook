package com.phonebook.repository;

import com.phonebook.domain.Contact;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Contact repository interface to work with database.
 *
 * @author Vasyl Utrysko
 * @version 1.0
 */
@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer> {

    List<Contact> findContactsByUserId(Integer userId, Pageable pageable);
}
