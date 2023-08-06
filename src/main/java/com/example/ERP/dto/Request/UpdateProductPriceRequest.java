package com.example.ERP.dto.Request;

import com.example.ERP.entity.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateProductPriceRequest {
    int price;
    private Product product;
}

