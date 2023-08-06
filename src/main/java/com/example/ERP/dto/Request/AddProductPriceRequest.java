package com.example.ERP.dto.Request;

import com.example.ERP.entity.Product;
import lombok.Data;

@Data
public class AddProductPriceRequest {
    int price;

    private Product product;
}
