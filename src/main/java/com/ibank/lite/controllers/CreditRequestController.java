package com.ibank.lite.controllers;

import com.ibank.lite.dto.ResponseJsonDto;
import com.ibank.lite.model.CreditRequest;
import com.ibank.lite.service.CreditRequestService;
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

@Controller
@RequestMapping("/credit/request")
@RequiredArgsConstructor
public class CreditRequestController {
    private static final Logger LOGGER = LoggerFactory.getLogger("rest");
    private final CreditRequestService creditRequestService;

    @GetMapping(value = {"", "/"}, produces = "application/json")
    ResponseEntity<?> findAll() {
        try {
            LOGGER.info("Getting list of credit requests!");
            return ResponseEntity.ok(creditRequestService.findAll());
        } catch (Exception e) {
            LOGGER.error("Exception on getting list of credit requests: ", e);
            return new ResponseEntity<>(ResponseJsonDto.buildNoContent(), HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    ResponseEntity<?> findById(@PathVariable int id) {
        try {
            LOGGER.info("Getting credit request by id");
            return ResponseEntity.ok(creditRequestService.findById(id));
        } catch (Exception e) {
            LOGGER.error("Exception on getting credit request by id: ", e);
            return new ResponseEntity<>(ResponseJsonDto.buildNoContent(), HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping(value = {"", "/"}, produces = "application/json")
    ResponseEntity<?> create(@RequestBody CreditRequest newCreditRequest) {
        try {
            long id = creditRequestService.create(newCreditRequest);
            LOGGER.info("Creating a credit request");
            return new ResponseEntity<>("Created", HttpStatus.CREATED);
        } catch (Exception e) {
            LOGGER.error("Exception on creating a credit request: ", e);
            return new ResponseEntity<>(ResponseJsonDto.buildNoContent(), HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping(value = "/{id}", produces = "application/json")
    ResponseEntity<?> update(@RequestBody CreditRequest newCreditRequest, @PathVariable long id) {
        return new ResponseEntity<>("Not Modified", HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = {"/{id}"}, produces = "application/json")
    ResponseEntity<?> deleteById(@PathVariable long id) {
        try {
            LOGGER.info("Deleting credit request by id");
            creditRequestService.deleteById(id);
            return ResponseEntity.ok("Deleted credit request with id - " + id);
        } catch (Exception e) {
            LOGGER.error("Exception on deleting credit request by id: ", e);
            return new ResponseEntity<>(ResponseJsonDto.buildNoContent(), HttpStatus.NO_CONTENT);
        }
    }
}
