package com.ibank.lite.service.impl;

import com.ibank.lite.auth.filter.JwtRequestFilter;
import com.ibank.lite.model.User;
import com.ibank.lite.repository.UserRepository;
import com.ibank.lite.service.UserService;
import com.ibank.lite.util.Roles;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger("repo");
    private final UserRepository userRepository;

    @Override
    public List<User> findAll() {
        LOGGER.info("Getting list of Users from db");
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(long id) {
        LOGGER.info("Getting User by Id - {} from db", id);
        return userRepository.findById(id);
    }

    @Override
    public long create(User newUser) {
        if (userRepository.findByEmail(newUser.getEmail()).isPresent()) {
            LOGGER.info("Can't create user with email {}, this email exist", newUser.getEmail());
            return 0;
        }

        LOGGER.info("Creating User with email {}", newUser.getEmail());

        User user = new User();

        user.setFirstName(newUser.getFirstName());
        user.setLastname(newUser.getLastname());
        user.setEmail(newUser.getEmail());
        user.setPassword(newUser.getPassword());
        user.setPhoneNumber(newUser.getPhoneNumber());
        user.setBirthday(newUser.getBirthday());
        user.setLocation(newUser.getLocation());
        user.setSocialState(newUser.getSocialState());
        user.setActive(true);
        user.setRoles(newUser.getRoles() == null ? Roles.USER : newUser.getRoles());

        return userRepository.save(user).getId();
    }

    @Override
    public Optional<User> update(User newUser, long id) {
        if (userRepository.findById(id).isPresent()) {
            return userRepository.findByEmail(newUser.getEmail())
                                 .map(user -> {
                                     LOGGER.info("Updating User with Id - {} and email - {}", user.getId(),
                                             user.getEmail());

                                     user.setSocialState(newUser.getSocialState());
                                     user.setLocation(newUser.getLocation());
                                     user.setBirthday(newUser.getBirthday());
                                     user.setPhoneNumber(newUser.getPhoneNumber());
                                     user.setLastname(newUser.getLastname());
                                     user.setFirstName(newUser.getFirstName());
                                     user.setPassword(newUser.getPassword());
                                     user.setActive(newUser.isActive());
                                     //user.setRoles(Roles.USER);
                                     if (JwtRequestFilter.role.equals("ADMIN")) {
                                         user.setRoles(newUser.getRoles() == null ? Roles.USER : newUser.getRoles());
                                     }
                                     userRepository.save(user);
                                     return newUser;
                                 });
        }
        return Optional.empty();
    }

    @Override
    public void deleteById(long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            LOGGER.info("Deleting User by Id - {} from db", id);
            userRepository.deleteById(id);
        }
    }
}