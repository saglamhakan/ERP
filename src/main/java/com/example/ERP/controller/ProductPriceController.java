package com.example.ERP.controller;

import com.example.ERP.dto.Request.AddProductPriceRequest;
import com.example.ERP.dto.Request.UpdateProductPriceRequest;
import com.example.ERP.dto.Response.GetAllProductPriceResponse;
import com.example.ERP.entity.ProductPrice;
import com.example.ERP.service.ProductPriceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("productPrice")
public class ProductPriceController {

    private final ProductPriceService productPriceService;

    public ProductPriceController(ProductPriceService productPriceService) {
        this.productPriceService = productPriceService;
    }

    @GetMapping("getAll")
    public ResponseEntity<List<GetAllProductPriceResponse>> getAll(){
        return new ResponseEntity<>(productPriceService.getAllProductPrices(), HttpStatus.OK);
    }

    @PostMapping("add")
    public ResponseEntity<ProductPrice> addProductPrice(@RequestBody AddProductPriceRequest addProductPriceRequest){
        return new ResponseEntity<>(productPriceService.addProductPrice(addProductPriceRequest), HttpStatus.CREATED);

    }

    @GetMapping("{id}")
    public ResponseEntity<ProductPrice> getById(@PathVariable Long id){
        return new ResponseEntity<>(productPriceService.getProductPriceById(id), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<ProductPrice> updateStock(@PathVariable Long id, @RequestBody UpdateProductPriceRequest updateProductPriceRequest){
        return new ResponseEntity<>(productPriceService.updateProductPrice(id, updateProductPriceRequest), HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStock(@PathVariable Long id) {
        productPriceService.deleteProductPrice(id);
        return ResponseEntity.noContent().build();
    }
}
