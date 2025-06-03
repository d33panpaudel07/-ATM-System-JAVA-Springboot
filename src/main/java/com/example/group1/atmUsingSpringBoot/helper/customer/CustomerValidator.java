package com.example.group1.atmUsingSpringBoot.helper.customer;

import com.example.group1.atmUsingSpringBoot.dto.CustomerDto;

public class CustomerValidator {
    public static boolean validateCustomer(CustomerDto customerDto){
        if(customerDto.getName().isEmpty() || customerDto.getMobileNumber().isEmpty() || customerDto.getAccountNumber().isEmpty()){
            return false;
        }
        return true;
    }
}
