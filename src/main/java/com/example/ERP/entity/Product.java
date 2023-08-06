package com.example.ERP.entity;

import com.example.ERP.util.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "product")

public class Product extends BaseEntity {

    private String name;
    private int stock;
    private int price;
    private boolean isKdvApplied;

    @ManyToMany
    List<Order> orders;
}
