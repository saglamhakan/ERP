package com.example.ERP.service;

import com.example.ERP.dataAccess.BillRepository;
import com.example.ERP.dto.Request.AddBillRequest;
import com.example.ERP.dto.Request.UpdateBillRequest;
import com.example.ERP.dto.Response.GetAllBillResponse;
import com.example.ERP.entity.Bill;
import com.example.ERP.exception.BusinessException;
import com.example.ERP.mapper.ModelMapperService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class BillService {

    private final BillRepository billRepository;

    private final ModelMapperService modelMapperService;


    public BillService(BillRepository billRepository, ModelMapperService modelMapperService) {
        this.billRepository = billRepository;
        this.modelMapperService = modelMapperService;
    }

    public List<GetAllBillResponse> getAllBills() {
        List<Bill> bills = billRepository.findAll();
        return (List<GetAllBillResponse>) bills.stream().
                map(bill -> this.modelMapperService.forResponse()
                        .map(bill, GetAllBillResponse.class)).collect(Collectors.toList());
    }

    public Bill getBillById(Long id) {
        return billRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Bill not found with id: " + id));
    }

    public Bill addBill(AddBillRequest newBill) {
        Bill bill = this.modelMapperService.forRequest().map(newBill, Bill.class);
        return billRepository.save(bill);
    }
/*
    public Bill updateBill(Long id, UpdateBillRequest updatedBill) {
        Bill bill = billRepository.findById(id).orElse(null);

        if (Objects.nonNull(bill)){
            bill.setOrder(updatedBill.getOrder());
            bill.setTotalPrice(updatedBill.getTotalPrice());
            bill.setTotalKdv(updatedBill.getTotalKdv());
            bill.setTotalNonKdvPrice(updatedBill.getTotalNonKdvPrice());
            bill.setProducts(updatedBill.getProducts());

           return billRepository.save(bill);
        }else {
            throw new BusinessException("Bill not found");
        }


    }

 */

    public void deleteBill(Long id) {
        billRepository.deleteById(id);
    }


}
