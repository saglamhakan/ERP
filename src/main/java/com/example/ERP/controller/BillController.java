package com.example.ERP.controller;

import com.example.ERP.dto.Request.AddBillRequest;
import com.example.ERP.dto.Request.UpdateBillRequest;
import com.example.ERP.dto.Response.GetAllBillResponse;
import com.example.ERP.entity.Bill;
import com.example.ERP.service.BillService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("bill")
public class BillController {

    private final BillService billService;

    public BillController(BillService billService) {
        this.billService = billService;
    }

    @GetMapping("getAll")
    public ResponseEntity<List<GetAllBillResponse>> getAllBills() {
        return new ResponseEntity<> (billService.getAllBills(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bill> getBillById(@PathVariable Long id) {
        return new ResponseEntity<>(billService.getBillById(id), HttpStatus.OK);
    }

    @PostMapping("create")
    public ResponseEntity<Bill> addBill(@RequestBody AddBillRequest newBill) {
        return new ResponseEntity<>(billService.addBill(newBill), HttpStatus.CREATED);
    }
/*
    @PutMapping("/{id}")
    public ResponseEntity<Bill> updateBill(@PathVariable Long id, @RequestBody UpdateBillRequest updateBillRequest) {
        return new ResponseEntity<>(billService.updateBill(id, updateBillRequest),HttpStatus.OK);
    }

 */


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBill(@PathVariable Long id) {
        billService.deleteBill(id);
        return ResponseEntity.noContent().build();
    }

}
