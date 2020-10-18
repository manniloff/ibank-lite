package com.ibank.lite.controllers;

import com.ibank.lite.dto.ResponseJsonDto;
import com.ibank.lite.model.Bank;
import com.ibank.lite.service.BankService;
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
@RequestMapping("/banks")
@RequiredArgsConstructor
public class BankController {
    private static final Logger LOGGER = LoggerFactory.getLogger("rest");
    private final BankService bankService;

    @GetMapping(value = {"", "/"}, produces = "application/json")
    ResponseEntity<?> findAll() {
        try {
            LOGGER.info("Getting list of banks!");
            return ResponseEntity.ok(bankService.findAll());
        } catch (Exception e) {
            LOGGER.error("Exception on getting list of banks: ", e);
            return new ResponseEntity<>(ResponseJsonDto.buildNoContent(), HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    ResponseEntity<?> findById(@PathVariable int id) {
        try {
            LOGGER.info("Getting bank by id");
            return ResponseEntity.ok(bankService.findById(id));
        } catch (Exception e) {
            LOGGER.error("Exception on getting bank by id: ", e);
            return new ResponseEntity<>(ResponseJsonDto.buildNoContent(), HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping(value = {"", "/"}, produces = "application/json")
    ResponseEntity<?> create(@RequestBody Bank newBank) {
        try {
            long id = bankService.create(newBank);
            if (id != 0) {
                LOGGER.info("Creating or updating a user");
                return new ResponseEntity<>("Created", HttpStatus.CREATED);
            }
            return new ResponseEntity<>(
                    "Bank with email - " + newBank.getName() + ", exists! Change data and try again.",
                    HttpStatus.CONFLICT);
        } catch (Exception e) {
            LOGGER.error("Exception on creating or updating a bank: ", e);
            return new ResponseEntity<>(ResponseJsonDto.buildNoContent(), HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping(value = "/{id}", produces = "application/json")
    ResponseEntity<?> update(@RequestBody Bank newBank, @PathVariable int id) {
        try {
            LOGGER.info("Creating or updating a bank");
            Optional<Bank> result = bankService.update(newBank, id);
            if (result.isPresent()) {
                return ResponseEntity.ok(result);
            }
            return new ResponseEntity<>("Not Modified", HttpStatus.NOT_MODIFIED);
        } catch (Exception e) {
            LOGGER.error("Exception on creating or updating a bank: ", e);
            return new ResponseEntity<>(ResponseJsonDto.buildNoContent(), HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping(value = {"/{id}"}, produces = "application/json")
    ResponseEntity<?> deleteById(@PathVariable int id) {
        try {
            LOGGER.info("Deleting bank by id");
            bankService.deleteById(id);
            return ResponseEntity.ok("Deleted bank with id - " + id);
        } catch (Exception e) {
            LOGGER.error("Exception on deleting bank by id: ", e);
            return new ResponseEntity<>(ResponseJsonDto.buildNoContent(), HttpStatus.NO_CONTENT);
        }
    }
}
