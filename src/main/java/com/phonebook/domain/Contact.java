package com.phonebook.domain;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * Contact class represents contact entity.
 *
 * @author Vasyl Utrysko
 * @version 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Contact{

    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    @ElementCollection
    private Set<@Email String> emails;
    @ElementCollection
    private Set<@Pattern(regexp = "^\\+\\d{1,3}\\d{9}$") String> phones;
    private Integer userId;
    @Nullable
    private String photo;
}