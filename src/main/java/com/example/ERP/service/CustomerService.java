package com.example.ERP.service;

import com.example.ERP.dataAccess.CustomerRepository;
import com.example.ERP.dto.Request.AddCustomerRequest;
import com.example.ERP.dto.Request.UpdateCustomerRequest;
import com.example.ERP.dto.Response.GetAllCustomerResponse;
import com.example.ERP.entity.Customer;
import com.example.ERP.exception.BusinessException;
import com.example.ERP.mapper.ModelMapperService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    private final ModelMapperService modelMapperService;

    public CustomerService(CustomerRepository customerRepository, ModelMapperService modelMapperService) {
        this.customerRepository = customerRepository;
        this.modelMapperService = modelMapperService;
    }

    public List<GetAllCustomerResponse> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return (List<GetAllCustomerResponse>) customers.stream().
                map(customer -> this.modelMapperService.forResponse()
                        .map(customer, GetAllCustomerResponse.class)).collect(Collectors.toList());
    }

    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Customer not found with id: " + id));
    }

    public Customer addCustomer(AddCustomerRequest newCustomer) {
        Customer customer = this.modelMapperService.forRequest().map(newCustomer, Customer.class);
        return customerRepository.save(customer);
    }

    public Customer updateCustomer(Long id, UpdateCustomerRequest updatedCustomer) {
        Customer customer = customerRepository.findById(id).orElse(null);
        if (Objects.nonNull(customer)){
            customer.setName(updatedCustomer.getName());
            customer.setEmail(updatedCustomer.getEmail());
            customer.setAddress(updatedCustomer.getAddress());

            return customerRepository.save(customer);

        }else {
            throw new BusinessException("Customer could not found");

        }

    }
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }
}
