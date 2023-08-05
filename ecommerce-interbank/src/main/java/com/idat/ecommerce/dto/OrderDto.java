package com.idat.ecommerce.dto;

import com.idat.ecommerce.model.Account;
import com.idat.ecommerce.model.OrderDetails;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {


    private Account account;

    private List<OrderDetails> orderDetailsRegistrationList;

}
