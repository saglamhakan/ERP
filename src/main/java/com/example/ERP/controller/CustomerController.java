package com.example.ERP.controller;

import com.example.ERP.dto.Request.AddCustomerRequest;
import com.example.ERP.dto.Request.UpdateCustomerRequest;
import com.example.ERP.dto.Request.UpdateProductRequest;
import com.example.ERP.dto.Response.GetAllCustomerResponse;
import com.example.ERP.entity.Customer;
import com.example.ERP.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("getAll")
    public ResponseEntity<List<GetAllCustomerResponse>> getAllCustomers() {
        return new ResponseEntity<> (customerService.getAllCustomers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        return new ResponseEntity<>(customerService.getCustomerById(id), HttpStatus.OK);
    }

    @PostMapping("create")
    public ResponseEntity<Customer> addCustomer(@RequestBody AddCustomerRequest newCustomer) {
        return new ResponseEntity<>(customerService.addCustomer(newCustomer), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody UpdateCustomerRequest updateCustomerRequest) {
        return new ResponseEntity<>(customerService.updateCustomer(id, updateCustomerRequest),HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }
}
