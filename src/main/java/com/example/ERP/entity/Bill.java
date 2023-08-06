package com.example.ERP.entity;

import com.example.ERP.util.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "bill")
public class Bill extends BaseEntity {
    private double totalPriceWithoutKdv;
    private double totalKdv;
    private double totalPriceWithKdv;

    @OneToOne
    @JoinColumn(name = "customer_order_id")
    private Order order;

    /*
    @ManyToMany
    @JoinTable(name = "bill_product",
            joinColumns = @JoinColumn(name = "bill_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products = new ArrayList<>();

     */
}
