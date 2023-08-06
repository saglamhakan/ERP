package com.example.ERP.dto.Request;

import com.example.ERP.entity.Customer;
import com.example.ERP.enumType.OrderStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateOrderRequest {

    private OrderStatus status;

    private Customer customer;
}
