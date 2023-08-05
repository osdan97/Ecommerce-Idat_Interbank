package com.idat.ecommerce.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import jakarta.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "ORDERDETAILS")
public class OrderDetails implements Serializable {
    @Id
    @Column(name = "orderdetail_uuid")
    private String orderDetailUuid;
    @ManyToOne
    @JoinColumn(name = "product_uuid")
    private Product product;
    @Column(name = "quantity")
    private int quantity;
    @Column(name = "price")
    private Double price;
    @Column(name = "total")
    private Double total;
}
