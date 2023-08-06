package com.example.ERP.dto.Request;

import com.example.ERP.entity.Product;
import lombok.Data;

@Data
public class UpdateStockRequest {

    int quantity;
    private Product product;
}
