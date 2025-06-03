package com.example.group1.atmUsingSpringBoot.services.impl;

import com.example.group1.atmUsingSpringBoot.dto.CustomerDto;
import com.example.group1.atmUsingSpringBoot.dto.Response;
import com.example.group1.atmUsingSpringBoot.entity.Customer;
import com.example.group1.atmUsingSpringBoot.helper.customer.CustomerValidator;
import com.example.group1.atmUsingSpringBoot.mapper.CustomerMapper;
import com.example.group1.atmUsingSpringBoot.repository.CustomerRepository;
import com.example.group1.atmUsingSpringBoot.services.CustomerService;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    CustomerRepository customerRepository;
    CustomerServiceImpl(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }


    @Override
    public Response createCustomer(CustomerDto dto) {
        if(!CustomerValidator.validateCustomer(dto)){
            return new Response(false,"Invalid customer credentials", null);
        }
        Customer customer = CustomerMapper.toEntity(dto);
        customerRepository.save(customer);
        return new Response(true, "Customer created successfully");
    }

    @Override
    public Response updateCustomer(String accountNumber, CustomerDto dto) {
        if(!CustomerValidator.validateCustomer(dto)){
            return new Response(false,"Invalid customer credentials", null);
        }
        if(customerRepository.existsByAccountNumber(dto.getAccountNumber()) == false){
            return new Response(false,"Customer doesn't exist...", null);
        }
        Customer customer = CustomerMapper.toEntity(dto);
        customerRepository.save(customer);
        return new Response(true, "Customer updated successfully...");
    }

    @Override
    public Response deleteCustomerByAccountNumber(String accountNumber) {
        if(customerRepository.existsByAccountNumber(accountNumber) == false){
            return new Response(false,"Customer doesn't exist...", null);
        }
        customerRepository.deleteByAccountNumber(accountNumber);
        return new Response(true, "Customer deleted successfully...");
    }

    @Override
    public Response deleteCustomerById(Integer id) {
        if(!customerRepository.existsById(id)){
            return new Response(false,"Customer doesn't exist...", null);
        }
        customerRepository.deleteById(id);
        return new Response(true, "Customer deleted successfully...");
    }

    @Override
    public Response getCustomerByAccountNumber(String accountNumber) {
        if(!customerRepository.existsByAccountNumber(accountNumber)){
            return null;
        }
        Customer customer = customerRepository.findByAccountNumber(accountNumber);
        return new Response(true, "Customer found successfully", CustomerMapper.toDto(customer));
    }
}
