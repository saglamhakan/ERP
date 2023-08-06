package com.example.ERP.controller;

import com.example.ERP.dto.Request.AddStockRequest;
import com.example.ERP.dto.Request.UpdateStockRequest;
import com.example.ERP.dto.Response.GetAllStockResponse;
import com.example.ERP.entity.Stock;
import com.example.ERP.service.StockService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("stock")
public class StockController {

    private final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping("getAll")
    public ResponseEntity<List<GetAllStockResponse>> getAll(){
        return new ResponseEntity<>(stockService.getAllStocks(), HttpStatus.OK);
    }

    @PostMapping("add")
    public ResponseEntity<Stock> addStock(@RequestBody AddStockRequest addStockRequest){
        return new ResponseEntity<>(stockService.addStock(addStockRequest), HttpStatus.CREATED);

    }

    @GetMapping("{id}")
    public ResponseEntity<Stock> getById(@PathVariable Long id){
        return new ResponseEntity<>(stockService.getStockById(id), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<Stock> updateStock(@PathVariable Long id, @RequestBody UpdateStockRequest updateStockRequest){
        return new ResponseEntity<>(stockService.updateStock(id, updateStockRequest), HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStock(@PathVariable Long id) {
        stockService.deleteStock(id);
        return ResponseEntity.noContent().build();
    }
}
