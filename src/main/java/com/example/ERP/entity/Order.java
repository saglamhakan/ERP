package com.example.ERP.entity;

import com.example.ERP.enumType.OrderStatus;
import com.example.ERP.util.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "customerOrder")
public class Order extends BaseEntity {

    @Enumerated(EnumType.STRING)
    OrderStatus status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToMany
    private List<Product> products;
}
