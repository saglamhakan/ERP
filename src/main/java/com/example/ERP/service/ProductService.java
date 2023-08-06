package com.example.ERP.service;

import com.example.ERP.dataAccess.ProductRepository;
import com.example.ERP.dto.Request.AddProductRequest;
import com.example.ERP.dto.Request.UpdateProductRequest;
import com.example.ERP.dto.Response.GetAllProductResponse;
import com.example.ERP.entity.Product;
import com.example.ERP.exception.BusinessException;
import com.example.ERP.mapper.ModelMapperService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    private final ModelMapperService modelMapperService;


    public ProductService(ProductRepository productRepository, ModelMapperService modelMapperService) {
        this.productRepository = productRepository;
        this.modelMapperService = modelMapperService;
    }



    public List<GetAllProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return (List<GetAllProductResponse>) products.stream().
                map(product -> this.modelMapperService.forResponse()
                        .map(product, GetAllProductResponse.class)).collect(Collectors.toList());
    }

    public Product addProduct(AddProductRequest newproduct) {
        Product product = this.modelMapperService.forRequest().map(newproduct, Product.class);
        return productRepository.save(product);
    }
/*
   public Product updateProduct(Long id, UpdateProductRequest updateProductRequest){
        Product product = productRepository.findById(id).orElse(null);

        if (Objects.nonNull(product)){
            product.setName(updateProductRequest.getName());
            product.setProductPrices(updateProductRequest.getProductPrices());
            product.setStocks(updateProductRequest.getStocks());
            product.setKdvApplied(updateProductRequest.isKdvApplied());
            product.setKdvAppliedPrice(updateProductRequest.getKdvAppliedPrice());
            product.setNonKdvAppliedPrice(updateProductRequest.getNonKdvAppliedPrice());

            return productRepository.save(product);
        }
        else {
            throw new BusinessException("Product could not found");
        }


   }

 */

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new BusinessException("Product not found with id: " + id));
    }


}
