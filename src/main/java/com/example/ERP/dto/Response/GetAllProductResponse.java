package com.example.ERP.dto.Response;

import com.example.ERP.entity.ProductPrice;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class GetAllProductResponse {
    String name;
    boolean isKdvApplied;
    int nonKdvAppliedPrice;
    int kdvAppliedPrice;

    private List<ProductPrice> productPrices = new ArrayList<>();
}
