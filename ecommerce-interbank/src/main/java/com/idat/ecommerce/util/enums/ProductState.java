package com.idat.ecommerce.util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum ProductState {
    UNAVAILABLE,
    WARNING,
    AVAILABLE;

    private String product;
}
