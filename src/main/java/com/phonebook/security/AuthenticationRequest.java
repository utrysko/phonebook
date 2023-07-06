package com.phonebook.security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class represents a request with fields to authenticate user.
 *
 * @author Vasyl Utrysko
 * @version 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationRequest {

    private String email;
    private String password;

}
