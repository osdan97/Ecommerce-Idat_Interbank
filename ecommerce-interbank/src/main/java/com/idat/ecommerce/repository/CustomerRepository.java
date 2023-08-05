package com.idat.ecommerce.repository;


import com.idat.ecommerce.model.Customers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;


public interface CustomerRepository extends JpaRepository<Customers, String> {
    Optional<Customers> findByEmail(String email);

    Optional<Customers> findByAccountUuid(String uuid);
}
