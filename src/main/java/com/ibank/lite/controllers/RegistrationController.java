package com.ibank.lite.controllers;

import com.ibank.lite.dto.ResponseJsonDto;
import com.ibank.lite.model.User;
import com.ibank.lite.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
@RequiredArgsConstructor
public class RegistrationController {
    private final Logger LOGGER = LoggerFactory.getLogger("rest");
    private final UserService userService;

    @PostMapping(value = {"", "/"}, produces = "application/json")
    ResponseEntity<?> createUser(@RequestBody User newUser) {
        try {
            long id = userService.create(newUser);
            if (id != 0) {
                LOGGER.info("Registration new user");
                return new ResponseEntity<>("Created", HttpStatus.CREATED);
            }
            return new ResponseEntity<>("User with email - " + newUser.getEmail() + " exists! Change email and try again.", HttpStatus.CONFLICT);
        } catch (Exception e) {
            LOGGER.error("Exception on registration an user!", e);
            return new ResponseEntity<>(ResponseJsonDto.buildNoContent(), HttpStatus.NO_CONTENT);
        }
    }
}
