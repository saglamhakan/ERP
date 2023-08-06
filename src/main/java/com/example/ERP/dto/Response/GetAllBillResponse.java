package com.example.ERP.dto.Response;

import com.example.ERP.entity.Order;

import lombok.Data;
@Data
public class GetAllBillResponse {
    private double totalPriceWithoutKdv;
    private double totalKdv;
    private double totalPriceWithKdv;
    private Order order;
}
