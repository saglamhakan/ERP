package com.example.ERP.service;

import com.example.ERP.dataAccess.ProductPriceRepository;
import com.example.ERP.dto.Request.AddProductPriceRequest;
import com.example.ERP.dto.Request.UpdateProductPriceRequest;
import com.example.ERP.dto.Response.GetAllProductPriceResponse;
import com.example.ERP.entity.ProductPrice;
import com.example.ERP.exception.BusinessException;
import com.example.ERP.mapper.ModelMapperService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ProductPriceService {

    private final ProductPriceRepository productPriceRepository;

    private final ModelMapperService modelMapperService;

    public ProductPriceService(ProductPriceRepository productPriceRepository, ModelMapperService modelMapperService) {
        this.productPriceRepository = productPriceRepository;
        this.modelMapperService = modelMapperService;
    }

    public List<GetAllProductPriceResponse> getAllProductPrices() {
        List<ProductPrice> productPrices = productPriceRepository.findAll();
        return (List<GetAllProductPriceResponse>) productPrices.stream().
                map(productPrice -> this.modelMapperService.forResponse()
                        .map(productPrice, GetAllProductPriceResponse.class)).collect(Collectors.toList());
    }

    public ProductPrice getProductPriceById(Long id) {
        return productPriceRepository.findById(id)
                .orElseThrow(() -> new BusinessException("ProductPrice not found with id: " + id));
    }

    public ProductPrice addProductPrice(AddProductPriceRequest newProductPrice) {
        ProductPrice productPrice = this.modelMapperService.forRequest().map(newProductPrice, ProductPrice.class);
        return productPriceRepository.save(productPrice);
    }

    public ProductPrice updateProductPrice(Long id, UpdateProductPriceRequest updatedProductPrice) {
        ProductPrice productPrice = productPriceRepository.findById(id).orElse(null);

        if (Objects.nonNull(productPrice)){
            productPrice.setPrice(updatedProductPrice.getPrice());

            return productPriceRepository.save(productPrice);
        }else{
            throw new BusinessException("ProductPrice not found");
        }


    }

    public void deleteProductPrice(Long id) {
        productPriceRepository.deleteById(id);
    }
}
