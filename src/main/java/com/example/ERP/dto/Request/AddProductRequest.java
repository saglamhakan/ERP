package com.example.ERP.dto.Request;
import lombok.Data;



@Data
public class AddProductRequest {
    private String name;
    private int stock;
    private int price;
    private boolean isKdvApplied;


}
