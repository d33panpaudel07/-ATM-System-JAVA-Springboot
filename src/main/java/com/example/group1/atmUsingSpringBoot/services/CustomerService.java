package com.example.group1.atmUsingSpringBoot.services;

import com.example.group1.atmUsingSpringBoot.dto.CustomerDto;
import org.springframework.stereotype.Service;

public interface CustomerService {
    Boolean createCustomer(CustomerDto dto);
    Boolean updateCustomer(CustomerDto dto);
    Boolean deleteCustomer(CustomerDto dto);
    CustomerDto readCustomer(String accountNumber);
}
