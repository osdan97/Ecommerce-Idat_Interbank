package com.idat.ecommerce.controller;


import com.idat.ecommerce.dto.ChangePassword;
import com.idat.ecommerce.dto.CustomerLoginResponse;
import com.idat.ecommerce.model.Account;
import com.idat.ecommerce.model.Customers;
import com.idat.ecommerce.repository.CustomerRepository;
import com.idat.ecommerce.security.UserPrincipal;
import com.idat.ecommerce.service.AccountService;
import com.idat.ecommerce.service.AuthenticationService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.List;

@RestController
@RequestMapping("api/authentication")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private CustomerRepository customerRepository;

    @PostMapping("sign-up")
    public ResponseEntity<?> signUp(@RequestBody Customers customer){
        if (customer.getEmail() == null || customer.getEmail().isEmpty()) {
            return new ResponseEntity<>("Email can't be empty", HttpStatus.BAD_REQUEST);
        }
        if (customer.getPassword() == null || customer.getPassword().isEmpty()) {
            return new ResponseEntity<>("Password can't be empty", HttpStatus.BAD_REQUEST);
        }
        if (customer.getName() == null || customer.getName().isEmpty()) {
            return new ResponseEntity<>("Name can't be empty", HttpStatus.BAD_REQUEST);
        }
        if (customer.getLastName() == null || customer.getLastName().isEmpty()) {
            return new ResponseEntity<>("Lastname can't be empty", HttpStatus.BAD_REQUEST);
        }
        if (accountService.findByEmail(customer.getEmail()).isPresent()) {
            return new ResponseEntity<>("This account already exists", HttpStatus.CONFLICT);
        } else {
            return new ResponseEntity<>(accountService.createCustomer(customer), HttpStatus.CREATED);
        }
    }

    @PostMapping("sign-in")
    public ResponseEntity<?> signIn(@RequestBody Account account){
        CustomerLoginResponse signInAccount = authenticationService.signInAndReturnJWT(account);

        if(!signInAccount.isActive()){
            return new ResponseEntity<>("Account is not active", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(signInAccount, HttpStatus.OK);
    }

    @GetMapping("verify/{verificationCode}")
    public ResponseEntity<?> verifyAccount(@PathVariable String verificationCode) {
        try {
            if (verificationCode == null) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Verification Failed");
            } else {
                boolean verified = accountService.verifyAccount(verificationCode);
                if (verified) {
                    return ResponseEntity.ok("Verification Succeeded");
                } else {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Verification Failed");
                }
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An error occurred while verify the account" + e.getMessage());
        }
    }
    @PostMapping("forgot-password")
    public ResponseEntity<?> sendEmailForgotPassword(@RequestBody Account account) throws MessagingException, UnsupportedEncodingException {
        return new ResponseEntity<>(accountService.sendPasswordRecoveryToEmail(account), HttpStatus.OK);
    }

    @PostMapping("change-password")
    public ResponseEntity<?> changePassword(@Valid @RequestBody ChangePassword changePassword, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Misplaced fields");
        }
        if (!changePassword.getPassword().equals(changePassword.getConfirmPassword())){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Passwords do not match");
        }

        return new ResponseEntity<>(accountService.changePassword(changePassword), HttpStatus.CREATED);
    }
    @GetMapping()
    public ResponseEntity<?> getCurrentUser(@AuthenticationPrincipal UserPrincipal userPrincipal){
        return new ResponseEntity<>(accountService.findByUsernameReturnToken(userPrincipal.getUsername()), HttpStatus.OK);
    }

    @PutMapping("/update/{uuid}")
    public ResponseEntity<?> updateCustomer(@PathVariable String uuid, @RequestBody Customers customer){
        customer.setAccountUuid(uuid);
        return new ResponseEntity<>(accountService.updateCustomer(customer), HttpStatus.OK);
    }
    @GetMapping("/list")
    public ResponseEntity<?>listCustomer(){
        List<Customers> list=customerRepository.findAll();
        return new ResponseEntity(list,HttpStatus.OK);
    }
}