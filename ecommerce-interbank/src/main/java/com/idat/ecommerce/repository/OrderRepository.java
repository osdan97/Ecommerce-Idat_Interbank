package com.idat.ecommerce.repository;


import com.idat.ecommerce.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Orders, String> {
}
