package com.example.ERP.dto.Request;

import com.example.ERP.entity.Customer;
import com.example.ERP.entity.Product;
import com.example.ERP.enumType.OrderStatus;
import lombok.Data;

@Data
public class AddOrderRequest {


    private OrderStatus status;

    private Long customerId;

    private Long productId;



}
