package com.example.ERP.dto.Response;

import com.example.ERP.entity.Product;
import lombok.Data;

@Data
public class GetAllProductPriceResponse {

    int price;
    private Product product;

}
