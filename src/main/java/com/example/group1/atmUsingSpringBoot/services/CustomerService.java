package com.example.group1.atmUsingSpringBoot.services;

import com.example.group1.atmUsingSpringBoot.dto.CustomerDto;
import com.example.group1.atmUsingSpringBoot.dto.Response;

public interface CustomerService {
    Response createCustomer(CustomerDto dto);
    Response updateCustomer(String accountNumber, CustomerDto dto);
    Response deleteCustomerByAccountNumber(String accountNumber);
    Response deleteCustomerById(Integer id);
    Response getCustomerByAccountNumber(String accountNumber);

    Response getAllCustomers();
}
