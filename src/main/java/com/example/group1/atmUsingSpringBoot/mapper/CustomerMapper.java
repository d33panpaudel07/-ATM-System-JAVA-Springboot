package com.example.group1.atmUsingSpringBoot.mapper;

import com.example.group1.atmUsingSpringBoot.dto.CustomerDto;
import com.example.group1.atmUsingSpringBoot.entity.Customer;

public class CustomerMapper {
    public static CustomerDto toDto(Customer customer){
        return new CustomerDto(customer.getName(), customer.getAccountNumber(), customer.getMobileNumber());
    }
    public static Customer toEntity(CustomerDto customerDto){
        return new Customer(customerDto.getName(), customerDto.getAccountNumber(), customerDto.getMobileNumber());
    }
}
