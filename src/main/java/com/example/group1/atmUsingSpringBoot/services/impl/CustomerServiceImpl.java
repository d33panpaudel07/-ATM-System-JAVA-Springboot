package com.example.group1.atmUsingSpringBoot.services.impl;

import com.example.group1.atmUsingSpringBoot.dto.CustomerDto;
import com.example.group1.atmUsingSpringBoot.dto.Response;
import com.example.group1.atmUsingSpringBoot.entity.Balance;
import com.example.group1.atmUsingSpringBoot.entity.Customer;
import com.example.group1.atmUsingSpringBoot.helper.customer.CustomerValidator;
import com.example.group1.atmUsingSpringBoot.mapper.CustomerMapper;
import com.example.group1.atmUsingSpringBoot.repository.BalanceRepository;
import com.example.group1.atmUsingSpringBoot.repository.CustomerRepository;
import com.example.group1.atmUsingSpringBoot.services.CustomerService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    CustomerRepository customerRepository;
    BalanceRepository balanceRepository;
    CustomerServiceImpl(CustomerRepository customerRepository, BalanceRepository balanceRepository){
        this.customerRepository = customerRepository;
        this.balanceRepository = balanceRepository;
    }


    @Override
    public Response createCustomer(CustomerDto dto) {
        if(!CustomerValidator.validateCustomer(dto)){
            return new Response(false,"Invalid customer credentials");
        }
        Customer customer = CustomerMapper.toEntity(dto);
        customerRepository.save(customer);
        Balance balance = new Balance(customer, 0.0);
        balanceRepository.save(balance);

        return new Response(true, "Customer created successfully");
    }

    @Override
    public Response updateCustomer(String accountNumber, CustomerDto dto) {
        if(!CustomerValidator.validateCustomer(dto)){
            return new Response(false,"Invalid customer credentials");
        }
        if(customerRepository.existsByAccountNumber(dto.getAccountNumber()) == false){
            return new Response(false,"Customer doesn't exist...");
        }
        Customer customer = CustomerMapper.toEntity(dto);
        customerRepository.save(customer);
        return new Response(true, "Customer updated successfully...");
    }

    @Override
    public Response deleteCustomerByAccountNumber(String accountNumber) {
        if(customerRepository.existsByAccountNumber(accountNumber) == false){
            return new Response(false,"Customer doesn't exist...");
        }
        customerRepository.deleteByAccountNumber(accountNumber);
        return new Response(true, "Customer deleted successfully...");
    }

    @Override
    public Response deleteCustomerById(Integer id) {
        if(!customerRepository.existsById(id)){
            return new Response(false,"Customer doesn't exist...");
        }
        customerRepository.deleteById(id);
        return new Response(true, "Customer deleted successfully...");
    }

    @Override
    public Response getCustomerByAccountNumber(String accountNumber) {
        if(!customerRepository.existsByAccountNumber(accountNumber)){
            return new Response(false, "Customer doesn't exist...");
        }
        Customer customer = customerRepository.findByAccountNumber(accountNumber);
        return new Response(true, "Customer found successfully", CustomerMapper.toDto(customer));
    }

    @Override
    public Response getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        List<CustomerDto> customerDtos = new ArrayList<>();
        for(Customer customer:customers){
            customerDtos.add(CustomerMapper.toDto(customer));
        }

        return new Response(true, "Customers fetched successfully", customerDtos);
    }


}
