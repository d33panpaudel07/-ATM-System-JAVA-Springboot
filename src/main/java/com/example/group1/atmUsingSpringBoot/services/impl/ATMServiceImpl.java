package com.example.group1.atmUsingSpringBoot.services.impl;

import com.example.group1.atmUsingSpringBoot.dto.Response;
import com.example.group1.atmUsingSpringBoot.entity.Balance;
import com.example.group1.atmUsingSpringBoot.helper.ATMValidator;
import com.example.group1.atmUsingSpringBoot.repository.CustomerRepository;
import com.example.group1.atmUsingSpringBoot.services.ATMService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("atmServiceImpl")
public class ATMServiceImpl implements ATMService {
    BalanceServiceImpl balanceService;
    CustomerServiceImpl customerService;
    ATMServiceImpl(BalanceServiceImpl balanceService, CustomerServiceImpl customerService){
        this.balanceService = balanceService;
        this.customerService = customerService;
    }

    @Override
    public Response deposit(String accountNumber, Double depositAmount) {
        if(!ATMValidator.validateAmount(depositAmount)){
            return new Response(false, "Invalid amount");
        };
        if(!customerService.customerRepository.existsByAccountNumber(accountNumber)){
            return new Response(false, "Account doesn't exist");
        }
        Balance balance = balanceService.balanceRepository.findByCustomer_AccountNumber(accountNumber).orElseThrow(()->new RuntimeException("Customer with account number: " + accountNumber + ", was not found"));
        double updatedAmount = balance.getBalance() + depositAmount;
        balanceService.updateBalance(accountNumber, updatedAmount);
        return new Response(true, "Cash deposit successful");
    }

    @Override
    public Response withdraw(String accountNumber, Double withdrawAmount) {
        if(!ATMValidator.validateAmount(withdrawAmount)){
            return new Response(false, "Invalid amount");
        };
        if(!customerService.customerRepository.existsByAccountNumber(accountNumber)){
            return new Response(false, "Account doesn't exist");
        }
        Balance balance = balanceService.balanceRepository.findByCustomer_AccountNumber(accountNumber).orElseThrow(()->new RuntimeException("Customer with account number: " + accountNumber + ", was not found"));

        double updatedAmount = balance.getBalance() - withdrawAmount;
        if(updatedAmount < 0){
            return new Response(false, "Insufficient balance in account");
        }
        balanceService.updateBalance(accountNumber, updatedAmount);
        return new Response(true, "Cash withdrawal successful");
    }

    @Override
    @Transactional
    public Response transfer(String fromAccountNumber, String toAccountNumber, Double transferAmount) {
        if(!ATMValidator.validateAmount(transferAmount)){
            return new Response(false, "Invalid amount");
        }
        if(!customerService.customerRepository.existsByAccountNumber(fromAccountNumber)
            ||
            !customerService.customerRepository.existsByAccountNumber(toAccountNumber))
        {
            return new Response(false, "Account doesn't exist");
        }
        Balance balanceOfFromAcc = balanceService.balanceRepository.findByCustomer_AccountNumber(fromAccountNumber).orElseThrow(()->new RuntimeException("Customer with account number: " + fromAccountNumber + ", was not found"));
        Balance balanceOfToAccount = balanceService.balanceRepository.findByCustomer_AccountNumber(toAccountNumber).orElseThrow(()->new RuntimeException("Customer with account number: " + fromAccountNumber + ", was not found"));

        double updatedFromBalance = balanceOfFromAcc.getBalance() - transferAmount;
        if(updatedFromBalance < 0){
            return new Response(false, "Insufficient balance in account, Transfer failed");
        }
        double updatedToBalance = balanceOfToAccount.getBalance() + transferAmount;

        balanceService.updateBalance(fromAccountNumber, updatedFromBalance);
        balanceService.updateBalance(toAccountNumber,updatedToBalance);
        return new Response(true, "Balance transfer successful");
    }
}
