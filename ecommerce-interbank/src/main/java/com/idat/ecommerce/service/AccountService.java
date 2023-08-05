package com.idat.ecommerce.service;


import com.idat.ecommerce.dto.*;
import com.idat.ecommerce.model.Account;
import com.idat.ecommerce.model.Customers;
import com.idat.ecommerce.model.Users;
import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;

import java.io.UnsupportedEncodingException;
import java.util.Optional;

public interface AccountService {
    CustomerRegistration createCustomer(Customers customers);

    UserRegistrationDto createUser(Users user);

    Optional<Account> findByEmail(String email);

    abstract Optional<Account> findByTokenPassword(String tokenPassword);

    CustomerUpdate updateCustomer (Customers customer);

    @Transactional
    boolean verifyAccount(String verificationCode);

    abstract EmailValues sendPasswordRecoveryToEmail(Account emailRecoverPass) throws MessagingException, UnsupportedEncodingException;

    Account changePassword(ChangePassword changePassword);

    Account findByUsernameReturnToken(String username);

    Optional<Customers> findByUuid(String uuid);
}

