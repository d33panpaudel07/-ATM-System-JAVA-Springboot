package com.example.group1.atmUsingSpringBoot.services;

import com.example.group1.atmUsingSpringBoot.dto.CustomerDto;
import org.springframework.stereotype.Service;

public interface CustomerService {
    Boolean createCustomer(CustomerDto dto);
    Boolean updateCustomer(CustomerDto dto);
    Boolean deleteCustomerByAccountNumber(CustomerDto dto);
    Boolean deleteCustomerById(Integer id);
    CustomerDto readCustomer(String accountNumber);
}
