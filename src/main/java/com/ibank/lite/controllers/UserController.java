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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger("rest");
    private final UserService userService;

    @GetMapping(value = {"", "/"}, produces = "application/json")
    ResponseEntity<?> findAll() {
        try {
            LOGGER.info("Getting list of users!");
            return ResponseEntity.ok(userService.findAll());
        } catch (Exception e) {
            LOGGER.error("Exception on getting list of users: ", e);
            return new ResponseEntity<>(ResponseJsonDto.buildNoContent(), HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    ResponseEntity<?> findById(@PathVariable int id) {
        try {
            LOGGER.info("Getting user by id");
            return ResponseEntity.ok(userService.findById(id));
        } catch (Exception e) {
            LOGGER.error("Exception on getting user by id: ", e);
            return new ResponseEntity<>(ResponseJsonDto.buildNoContent(), HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping(value = {"", "/"}, produces = "application/json")
    ResponseEntity<?> create(@RequestBody User newUser) {
        try {
            long id = userService.create(newUser);
            if (id != 0) {
                LOGGER.info("Creating or updating a user");
                return new ResponseEntity<>("Created", HttpStatus.CREATED);
            }
            return new ResponseEntity<>("User with email - " + newUser.getEmail() + ", exists! Change login and try again.", HttpStatus.CONFLICT);
        } catch (Exception e) {
            LOGGER.error("Exception on creating or updating an user: ", e);
            return new ResponseEntity<>(ResponseJsonDto.buildNoContent(), HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping(value = "/{id}", produces = "application/json")
    ResponseEntity<?> update(@RequestBody User newUser,@PathVariable long id) {
        try {
            LOGGER.info("Creating or updating a user");
            Optional<User> result = userService.update(newUser,id);
            if(result.isPresent()){
                return ResponseEntity.ok(result);
            }
            return new ResponseEntity<>("Not Modified", HttpStatus.NOT_MODIFIED);
        } catch (Exception e) {
            LOGGER.error("Exception on creating or updating an user: ", e);
            return new ResponseEntity<>(ResponseJsonDto.buildNoContent(), HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping(value = {"/{id}"}, produces = "application/json")
    ResponseEntity<?> deleteById(@PathVariable long id) {
        try {
            LOGGER.info("Deleting user by id");
            userService.deleteById(id);
            return ResponseEntity.ok("Deleted user with id - " + id);
        } catch (Exception e) {
            LOGGER.error("Exception on deleting user by id: ", e);
            return new ResponseEntity<>(ResponseJsonDto.buildNoContent(), HttpStatus.NO_CONTENT);
        }
    }
}
