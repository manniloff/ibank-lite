package com.ibank.lite.controllers;

import com.ibank.lite.auth.filter.JwtRequestFilter;
import com.ibank.lite.model.CreditRequest;
import com.ibank.lite.model.User;
import com.ibank.lite.service.CreditRequestService;
import com.ibank.lite.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AccountController.class);
    private final UserService userService;
    private final CreditRequestService creditRequestService;

    @GetMapping(value = {"","/"}, produces = "application/json")
    ResponseEntity<?> findUserInfo() {
        try{
            LOGGER.info("Getting user by id");
            return ResponseEntity.ok(userService.findById(JwtRequestFilter.id));
        } catch (Exception e) {
            LOGGER.error("Error with getting user by id!", e);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping(value = {"", "/"}, produces = "application/json")
    ResponseEntity<?> updateUser(@RequestBody User newUser) {
        try {
            LOGGER.info("Updating an user");
            userService.update(newUser, newUser.getId());
            return new ResponseEntity<>("Updated", HttpStatus.CREATED);
        } catch (Exception e) {
            LOGGER.error("Error with updating an user!", e);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping(value = "/requests", produces = "application/json")
    ResponseEntity<?> findAllCreditRequests() {
        try {
            LOGGER.info("Getting list of credit requests for user - {} ", JwtRequestFilter.id);
            return ResponseEntity.ok(creditRequestService.findAllByUserId(JwtRequestFilter.id));
        } catch (Exception e) {
            LOGGER.error("Error with getting list of credit requests by id!", e);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
