package com.idat.ecommerce.repository;

import com.idat.ecommerce.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users,String> {
    Optional<Users> findByEmail(String email);
}
