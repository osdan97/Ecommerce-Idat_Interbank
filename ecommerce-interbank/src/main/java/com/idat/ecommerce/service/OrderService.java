package com.idat.ecommerce.service;

import com.idat.ecommerce.dto.OrderDto;
import com.idat.ecommerce.model.Orders;

public interface OrderService {
    public Orders saveOrder(OrderDto orderDto);
}