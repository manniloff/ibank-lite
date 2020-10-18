package com.ibank.lite.controllers;

import com.ibank.lite.auth.model.AuthRequest;
import com.ibank.lite.auth.model.AuthResponse;
import com.ibank.lite.auth.service.LoginDetailsService;
import com.ibank.lite.auth.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
@RequiredArgsConstructor
public class AuthController {
    private static final Logger LOGGER = LoggerFactory.getLogger("rest");
    private final AuthenticationManager authenticationManager;
    private final LoginDetailsService loginDetailsService;
    private final JwtUtil jwtUtil;


    @PostMapping(value = {"", "/"}, produces = "application/json")
    public ResponseEntity<?> createAuthToken(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword())
            );
            LOGGER.info("Verification of auth data with login - {} and password {}", authRequest.getEmail(), authRequest.getPassword());
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect login or password");
        }

        final UserDetails loginDetails = loginDetailsService
                .loadUserByUsername(authRequest.getEmail());

        final String token = jwtUtil.generateToken(loginDetails);

        LOGGER.info("Return token for user - {}", loginDetails.getUsername());
        return ResponseEntity.ok(new AuthResponse(token));
    }
}
