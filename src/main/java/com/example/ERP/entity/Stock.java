package com.example.ERP.entity;

import com.example.ERP.util.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "stock")
public class Stock extends BaseEntity {
    int quantity;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

}
