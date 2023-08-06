package com.example.ERP.dto.Response;

import com.example.ERP.entity.Product;
import lombok.Data;

@Data
public class GetAllStockResponse {

    int quantity;

    private Product product;
}
