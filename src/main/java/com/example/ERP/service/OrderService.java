package com.example.ERP.service;

import com.example.ERP.dataAccess.BillRepository;
import com.example.ERP.dataAccess.OrderRepository;
import com.example.ERP.dataAccess.ProductRepository;
import com.example.ERP.dataAccess.SettingRepository;
import com.example.ERP.dto.Request.*;
import com.example.ERP.dto.Response.GetAllCustomerOrderResponse;
import com.example.ERP.entity.Bill;
import com.example.ERP.entity.Order;
import com.example.ERP.entity.Product;
import com.example.ERP.entity.Setting;
import com.example.ERP.enumType.OrderStatus;
import com.example.ERP.exception.BusinessException;
import com.example.ERP.mapper.ModelMapperService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    private final ModelMapperService modelMapperService;

    private final ProductRepository productRepository;

    private final BillRepository billRepository;

    private final SettingRepository settingRepository;

    public OrderService(OrderRepository orderRepository, ModelMapperService modelMapperService,
                        ProductRepository productRepository, BillRepository billRepository, SettingRepository settingRepository) {
        this.orderRepository = orderRepository;
        this.modelMapperService = modelMapperService;
        this.productRepository=productRepository;
        this.billRepository = billRepository;
        this.settingRepository=settingRepository;
    }

    public List<GetAllCustomerOrderResponse> getAllCustomerOrders() {
        List<Order> orders = orderRepository.findAll();
        return (List<GetAllCustomerOrderResponse>) orders.stream().
                map(customerOrder -> this.modelMapperService.forResponse()
                        .map(customerOrder, GetAllCustomerOrderResponse.class)).collect(Collectors.toList());
    }

    public Order getCustomerOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new BusinessException("CustomerOrder not found with id: " + id));
    }


    public Order createOrder(Order newOrder) {
        for (Product product : newOrder.getProducts()) {
            Optional<Product> productInDb = productRepository.findById(product.getId());
            if (productInDb.isPresent()) {
                int newStock = productInDb.get().getStock() - 1;
                if (newStock < 0) {
                    throw new RuntimeException("Stock not sufficient for product: " + product.getId());
                }
                productInDb.get().setStock(newStock);
                productRepository.save(productInDb.get());
            }
        }
        return orderRepository.save(newOrder);
    }

    public Order updateCustomerOrder(Long id, UpdateOrderRequest updatedCustomerOrderRequest) {
        Order order = orderRepository.findById(id).orElse(null);

        if (Objects.nonNull(order)) {
            order.setCustomer(updatedCustomerOrderRequest.getCustomer());
            order.setStatus(updatedCustomerOrderRequest.getStatus());

            return orderRepository.save(order);
        }else {
            throw new BusinessException("CustomerOrder not found");
        }


    }

    public void deleteCustomerOrder(Long id) {
        orderRepository.deleteById(id);
    }

    public Order approveOrder(Long id) {
        Optional<Order> orderOpt = orderRepository.findById(id);
        if (orderOpt.isEmpty()) {
            throw new BusinessException("Order not found: " + id);
        }
        Order order = orderOpt.get();
        order.setStatus(OrderStatus.APPROVED);
        orderRepository.save(order);

        // compute KDV
        Optional<Setting> kdvSetting = settingRepository.findById(id); // assuming "kdv" is the key for KDV setting
        double kdv = kdvSetting.map(Setting::getValue).orElse(0.18); // default to 0.18 if not set
        double totalWithoutKdv = order.getProducts().stream().mapToDouble(Product::getPrice).sum();
        double totalKdv = totalWithoutKdv * kdv;
        double totalWithKdv = totalWithoutKdv + totalKdv;

        // create a Bill
        Bill bill = new Bill();
        bill.setOrder(order);
        bill.setTotalPriceWithoutKdv(totalWithoutKdv);
        bill.setTotalKdv(totalKdv);
        bill.setTotalPriceWithKdv(totalWithKdv);
        billRepository.save(bill);

        return order;
    }
}
