package com.example.ERP.dto.Request;

import com.example.ERP.entity.Order;
import com.example.ERP.entity.Product;
import lombok.Data;

import java.util.List;

@Data
public class AddBillRequest {


    int totalKdv;
    int totalPrice;
    int totalNonKdvPrice;
    private Order order;
    private List<Product> products;
}
