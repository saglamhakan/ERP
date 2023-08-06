package com.example.ERP.entity;

import com.example.ERP.util.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "productPrice")
public class ProductPrice extends BaseEntity {

    int price;



}
