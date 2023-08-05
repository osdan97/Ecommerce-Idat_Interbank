package com.idat.ecommerce.util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum TransactionState {
    INCOMPLETO,
    COMPLETADO;
    private String value;
}
