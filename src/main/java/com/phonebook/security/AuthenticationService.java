package com.phonebook.security;

import com.phonebook.domain.Role;
import com.phonebook.domain.User;
import com.phonebook.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Class represents an authentication service.
 *
 * @author Vasyl Utrysko
 * @version 1.0
 */
@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    /**
     * Method try to register a new user
     *
     * @param request RegisterRequest with all needed fields
     * @return AuthenticationResponse with token
     */
    public AuthenticationResponse register(RegisterRequest request) {
        User user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        repository.save(user);
        String jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    /**
     * Method try to authenticate a user
     *
     * @param request RegisterRequest with all needed fields
     * @return AuthenticationResponse with token
     */
    public AuthenticationResponse authenticate(HttpSession session, AuthenticationRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()
        ));
        User user = repository.findUserByEmail(request.getEmail()).orElseThrow();
        session.setAttribute("userId", user.getId());
        session.setAttribute("userEmail", user.getEmail());
        String jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
