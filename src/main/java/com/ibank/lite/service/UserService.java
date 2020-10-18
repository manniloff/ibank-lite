package com.ibank.lite.service;

import com.ibank.lite.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAll();

    Optional<User> findById(long id);

    long create(User newUser);

    Optional<User> update(User newUser, long id);

    void deleteById(long id);
}
