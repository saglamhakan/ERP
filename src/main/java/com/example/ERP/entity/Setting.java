package com.example.ERP.entity;

import com.example.ERP.util.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "setting")
public class Setting extends BaseEntity {

    String key;
    Double value;
}
