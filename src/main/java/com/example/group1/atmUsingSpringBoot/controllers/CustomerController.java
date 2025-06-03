package com.example.group1.atmUsingSpringBoot.controllers;

import com.example.group1.atmUsingSpringBoot.dto.CustomerDto;
import com.example.group1.atmUsingSpringBoot.dto.Response;
import com.example.group1.atmUsingSpringBoot.services.impl.BalanceServiceImpl;
import com.example.group1.atmUsingSpringBoot.services.impl.CustomerServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/customers")
public class CustomerController {
    private final CustomerServiceImpl customerService;

    CustomerController(CustomerServiceImpl customerService){
        this.customerService = customerService;
    }

    @GetMapping("/test")
    public String testing(){
        return "Works";
    }

    @PostMapping()
    public ResponseEntity<Response> createCustomer(@RequestBody CustomerDto customerDto){
        Response response = customerService.createCustomer(customerDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @GetMapping("/{accountNumber}")
    public ResponseEntity<Response> getCustomerByAccountNumber(@PathVariable String accountNumber){
        Response response = customerService.getCustomerByAccountNumber(accountNumber);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PostMapping("/update/{accountNumber}")
    public ResponseEntity<Response> updateCustomer(@PathVariable String accountNumber, @RequestBody CustomerDto customerDto){
        Response response = customerService.updateCustomer(accountNumber, customerDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PostMapping("/delete/{accountNumber}")
    public ResponseEntity<Response> deleteCustomer(@PathVariable String accountNumber){
        Response response = customerService.deleteCustomerByAccountNumber(accountNumber);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/getAll")
    public ResponseEntity<Response> getAllCustomers(){
        Response response = customerService.getAllCustomers();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
