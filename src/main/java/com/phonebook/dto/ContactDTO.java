package com.phonebook.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * ContactDTO class. Fields are similar to Contact entity, but without userId and photo.
 *
 * @author Vasyl Utrysko
 * @version 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContactDTO {
    private Integer id;
    private String name;
    private Set<String> emails;
    private Set<String> phones;
}
