package com.phonebook.domain;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Contact {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    @ElementCollection
    private List<@Email String> emails;
    @ElementCollection
    private List<@Pattern(regexp = "^\\+\\d{1,3}\\d{9}$") String> phones;
    private Integer userId;
}