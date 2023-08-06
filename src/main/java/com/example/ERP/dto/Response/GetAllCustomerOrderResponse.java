package com.example.ERP.dto.Response;

import com.example.ERP.entity.Customer;
import com.example.ERP.entity.Product;
import com.example.ERP.enumType.OrderStatus;

import lombok.Data;


@Data
public class GetAllCustomerOrderResponse {

    OrderStatus status;

    private Customer customer;

    private Product product;
}
