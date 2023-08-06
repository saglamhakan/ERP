package com.example.ERP.entity;

import com.example.ERP.util.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "customer")
public class Customer extends BaseEntity {

    String name;
    String email;
    String address;


}
