package com.ibank.lite.service.impl;

import com.ibank.lite.auth.filter.JwtRequestFilter;
import com.ibank.lite.model.CreditRequest;
import com.ibank.lite.model.User;
import com.ibank.lite.repository.CreditRequestRepository;
import com.ibank.lite.repository.UserRepository;
import com.ibank.lite.service.CreditRequestService;
import com.ibank.lite.util.RequestStatus;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CreditRequestServiceImpl implements CreditRequestService {
    private static final Logger LOGGER = LoggerFactory.getLogger("repo");
    private final CreditRequestRepository creditRequestRepository;
    private final UserRepository userRepository;

    @Override
    public List<CreditRequest> findAll() {
        LOGGER.info("Find list of credit requests");
        return creditRequestRepository.findAll();
    }

    @Override
    public Optional<CreditRequest> findById(long id) {
        LOGGER.info("Find credit request with id - {}", id);
        return creditRequestRepository.findById(id);
    }

    @Override
    public long create(CreditRequest newCreditRequest) {
        CreditRequest creditRequest = new CreditRequest();

        Optional<User> user = userRepository.findById(JwtRequestFilter.id);

        if (user.isPresent()) {
            User currentUser = user.get();
            creditRequest.setCreditSum(newCreditRequest.getCreditSum());
            creditRequest.setNumberOfChildren(newCreditRequest.getNumberOfChildren());
            creditRequest.setMaritalStatus(newCreditRequest.getMaritalStatus());
            creditRequest.setRequestStatus(RequestStatus.NEW);
            creditRequest.setEmail(currentUser.getEmail());
            creditRequest.setFirstName(currentUser.getFirstName());
            creditRequest.setLastName(currentUser.getLastName());
            creditRequest.setPhoneNumber(currentUser.getPhoneNumber());
            creditRequest.setUser(new User(currentUser.getId()));
            int brDate = currentUser.getBirthday().getWeekYear();
            int curDate = Calendar.getInstance().getWeekYear();
            creditRequest.setAge(curDate - brDate);
            LOGGER.info("Create credit request by user - {} {}", currentUser.getFirstName(), currentUser.getLastName());
        }
        return creditRequestRepository.save(creditRequest).getId();
    }

    @Override
    public Optional<CreditRequest> update(CreditRequest newCreditRequest, long id) {
        return Optional.empty();
    }

    @Override
    public void deleteById(long id) {
        Optional<CreditRequest> creditRequest = creditRequestRepository.findById(id);
        if (creditRequest.isPresent()) {
            LOGGER.info("Deleting credit request by Id - {} from db", id);
            userRepository.deleteById(id);
        }
    }

    @Override
    public List<CreditRequest> findAllByUserId(long userId) {
        return creditRequestRepository.findAllByUserId(userId);
    }
}
