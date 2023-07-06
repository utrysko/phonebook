package com.phonebook.controller;

import com.phonebook.security.AuthenticationRequest;
import com.phonebook.security.AuthenticationResponse;
import com.phonebook.security.AuthenticationService;
import com.phonebook.security.RegisterRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Class represents Controller for user.
 *
 * @author Vasyl Utrysko.
 * @version 1.0
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class UserController {

    private final AuthenticationService service;

    /**
     * A method to retrieve user contacts
     *
     * @param request pass in service to register new user
     * @return ResponseEntity with AuthenticationResponse that contains token
     */
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(service.register(request));
    }

    /**
     * A method to retrieve user contacts
     *
     * @param request that contains info about user
     * @param session to set up info about user to the session
     * @return ResponseEntity with AuthenticationResponse that contains token
     */
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request,
                                                           HttpSession session) {
        return ResponseEntity.ok(service.authenticate(session, request));
    }
}
