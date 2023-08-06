package com.example.ERP.service;

import com.example.ERP.dataAccess.StockRepository;
import com.example.ERP.dto.Request.AddStockRequest;
import com.example.ERP.dto.Request.UpdateStockRequest;
import com.example.ERP.dto.Response.GetAllStockResponse;
import com.example.ERP.entity.Stock;
import com.example.ERP.exception.BusinessException;
import com.example.ERP.mapper.ModelMapperService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class StockService {

    private final StockRepository stockRepository;

    private final ModelMapperService modelMapperService;

    public StockService(StockRepository stockRepository, ModelMapperService modelMapperService) {
        this.stockRepository = stockRepository;
        this.modelMapperService = modelMapperService;
    }

    public List<GetAllStockResponse> getAllStocks() {
        List<Stock> stocks = stockRepository.findAll();
        return (List<GetAllStockResponse>) stocks.stream().
                map(stock -> this.modelMapperService.forResponse()
                        .map(stock, GetAllStockResponse.class)).collect(Collectors.toList());
    }

    public Stock getStockById(Long id) {
        return stockRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Stock not found with id: " + id));
    }

    public Stock addStock(AddStockRequest newStock) {
        Stock stock = this.modelMapperService.forRequest().map(newStock, Stock.class);
        return stockRepository.save(stock);
    }

    public Stock updateStock(Long id, UpdateStockRequest updatedStock) {
        Stock stock = stockRepository.findById(id).orElse(null);

        if (Objects.nonNull(stock)) {
            stock.setQuantity(updatedStock.getQuantity());

           return stockRepository.save(stock);
        } else {
            throw new BusinessException("Stock not found");

        }

    }

    public void deleteStock(Long id) {
        stockRepository.deleteById(id);
    }
}
