package com.example.ERP.controller;

import com.example.ERP.dto.Request.AddOrderRequest;
import com.example.ERP.dto.Request.UpdateOrderRequest;
import com.example.ERP.dto.Response.GetAllCustomerOrderResponse;
import com.example.ERP.entity.Order;
import com.example.ERP.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("getAll")
    public ResponseEntity<List<GetAllCustomerOrderResponse>> getAllCustomerOrders() {
        return new ResponseEntity<>(orderService.getAllCustomerOrders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getCustomerOrderById(@PathVariable Long id) {
        return new ResponseEntity<>(orderService.getCustomerOrderById(id), HttpStatus.OK);
    }

    @PostMapping("create")
    public ResponseEntity<Order> addCustomer(@RequestBody Order newCustomerOrder) {
        return new ResponseEntity<>(orderService.createOrder(newCustomerOrder), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Order> updateCustomer(@PathVariable Long id, @RequestBody UpdateOrderRequest updateOrderRequest) {
        return new ResponseEntity<>(orderService.updateCustomerOrder(id, updateOrderRequest), HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        orderService.deleteCustomerOrder(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/approve")
    public ResponseEntity<Order> approveOrder(@PathVariable Long id) {
        return new ResponseEntity<>(orderService.approveOrder(id), HttpStatus.OK);
    }
}