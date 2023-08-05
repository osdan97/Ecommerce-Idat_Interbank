package com.idat.ecommerce.controller;

import com.idat.ecommerce.dto.Mensaje;
import com.idat.ecommerce.dto.OrderDto;
import com.idat.ecommerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/orders")
public class OrdersController {
    @Autowired
    private OrderService orderService;
    @PostMapping()
    public ResponseEntity<?> saveOrder(@RequestBody OrderDto orders) {
        try {
            return new ResponseEntity<>(orderService.saveOrder(orders), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity(new Mensaje(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
