package com.example.ERP.dto.Request;

import com.example.ERP.entity.ProductPrice;
import com.example.ERP.entity.Stock;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UpdateProductRequest {

    String name;
    boolean isKdvApplied;
    int nonKdvAppliedPrice;
    int kdvAppliedPrice;

    private List<Stock> stocks;
    private List<ProductPrice> productPrices;
}
