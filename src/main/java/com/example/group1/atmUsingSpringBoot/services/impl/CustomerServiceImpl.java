package com.example.group1.atmUsingSpringBoot.services.impl;

import com.example.group1.atmUsingSpringBoot.dto.CustomerDto;
import com.example.group1.atmUsingSpringBoot.entity.Customer;
import com.example.group1.atmUsingSpringBoot.helper.customer.CustomerValidator;
import com.example.group1.atmUsingSpringBoot.mapper.CustomerMapper;
import com.example.group1.atmUsingSpringBoot.repository.CustomerRepository;
import com.example.group1.atmUsingSpringBoot.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerRepository customerRepository;


    @Override
    public Boolean createCustomer(CustomerDto dto) {
        if(!CustomerValidator.validateCustomer(dto)){
            return false;
        }
        Customer customer = CustomerMapper.toEntity(dto);
        customerRepository.save(customer);
        return true;
    }

    @Override
    public Boolean updateCustomer(CustomerDto dto) {
        if(!CustomerValidator.validateCustomer(dto)){
            return false;
        }
        if(customerRepository.existsByAccountNumber(dto.getAccountNumber()) == false){
            return false;
        }
        Customer customer = CustomerMapper.toEntity(dto);
        customerRepository.save(customer);
        return true;
    }

    @Override
    public Boolean deleteCustomerByAccountNumber(CustomerDto dto) {
        if(!CustomerValidator.validateCustomer(dto)){
            return false;
        }
        if(customerRepository.existsByAccountNumber(dto.getAccountNumber()) == false){
            return false;
        }
        customerRepository.deleteByAccountNumber(dto.getAccountNumber());
        return true;
    }

    @Override
    public Boolean deleteCustomerById(Integer id) {
        if(!customerRepository.existsById(id)){
            return false;
        }
        customerRepository.deleteById(id);
        return true;
    }

    @Override
    public CustomerDto readCustomer(String accountNumber) {
        if(!customerRepository.existsByAccountNumber(accountNumber)){
            return null;
        }
        Customer customer = customerRepository.findByAccountNumber(accountNumber);
        return CustomerMapper.toDto(customer);
    }
}
