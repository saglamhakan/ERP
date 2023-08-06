package com.example.ERP.controller;

import com.example.ERP.dto.Request.AddProductRequest;
import com.example.ERP.dto.Request.UpdateProductRequest;
import com.example.ERP.dto.Response.GetAllProductResponse;
import com.example.ERP.entity.Product;
import com.example.ERP.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<GetAllProductResponse>> getAllProducts() {
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody AddProductRequest newProduct) {
        return new ResponseEntity<>(productService.addProduct(newProduct), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
/*
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateProduct(@PathVariable Long id, @RequestBody UpdateProductRequest updateProductRequest) {
        productService.updateProduct(id, updateProductRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

 */

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}
