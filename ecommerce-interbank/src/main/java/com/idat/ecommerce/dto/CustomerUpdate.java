package com.idat.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class CustomerUpdate {
    private String name;
    private String lastName;
    private String documentType;
    private Long documentNumber;
    private String address;

}
