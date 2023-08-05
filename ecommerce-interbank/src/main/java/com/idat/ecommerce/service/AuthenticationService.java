package com.idat.ecommerce.service;


import com.idat.ecommerce.dto.CustomerLoginResponse;
import com.idat.ecommerce.model.Account;

public interface AuthenticationService {
    CustomerLoginResponse signInAndReturnJWT(Account signInRequest);
}
