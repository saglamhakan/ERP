package com.example.ERP.dto.Request;

import lombok.Data;

@Data
public class AddCustomerRequest {

    String name;
    String email;
    String address;
}
