package com.idat.ecommerce.model;

import com.idat.ecommerce.util.enums.TransactionState;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "ORDERS")
public class Orders {
    @Id
    @Column(name = "order_uuid",nullable = false,unique = true)
    private String orderUuid;

    @Column(name = "sub_total")
    private Double subTotal;

    @Column(name = "igv")
    private Double igv;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "account_uuid", nullable = true)
    private Account account;

    @Column(name = "total")
    private Double total;

    @Column(name = "created_date", nullable = false, updatable = false)
    private LocalDateTime createdDate;
    @Enumerated(EnumType.STRING)
    @Column(name = "state", nullable = false)
    private TransactionState transactionState;

    @Column(name="transaction_type",nullable = false)
    public String transaction_type;

    @OneToMany(targetEntity = OrderDetails.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "order_uuid", referencedColumnName = "order_uuid")
    private List<OrderDetails> orderDetailsList;
}
