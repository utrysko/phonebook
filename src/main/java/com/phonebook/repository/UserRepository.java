package com.phonebook.repository;

import com.phonebook.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * User repository interface to work with database.
 *
 * @author Vasyl Utrysko
 * @version 1.0
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findUserByEmail(String email);
}
