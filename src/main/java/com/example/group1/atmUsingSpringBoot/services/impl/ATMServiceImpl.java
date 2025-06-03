package com.example.group1.atmUsingSpringBoot.services.impl;

import com.example.group1.atmUsingSpringBoot.entity.Balance;
import com.example.group1.atmUsingSpringBoot.helper.ATMValidator;
import com.example.group1.atmUsingSpringBoot.repository.CustomerRepository;
import com.example.group1.atmUsingSpringBoot.services.ATMService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class ATMServiceImpl implements ATMService {
    BalanceServiceImpl balanceService;
    CustomerServiceImpl customerService;
    ATMServiceImpl(BalanceServiceImpl balanceService, CustomerServiceImpl customerService){
        this.balanceService = balanceService;
        this.customerService = customerService;
    }



    @Override
    public Boolean deposit(String accountNumber, Double depositAmount) {
        if(!ATMValidator.validateAmount(depositAmount)){
            return false;
        };
        if(!customerService.customerRepository.existsByAccountNumber(accountNumber)){
            return false;
        }
        Balance balance = balanceService.balanceRepository.findByCustomer_AccountNumber(accountNumber).orElseThrow(()->new RuntimeException("Customer with account number: " + accountNumber + ", was not found"));
 double updatedAmount = balance.getBalance() + depositAmount;
        balanceService.updateBalance(accountNumber, updatedAmount);
        return true;
    }

    @Override
    public Boolean withdraw(String accountNumber, Double withdrawAmount) {
        if(!ATMValidator.validateAmount(withdrawAmount)){
            return false;
        };
        if(!customerService.customerRepository.existsByAccountNumber(accountNumber)){
            return false;
        }
        Balance balance = balanceService.balanceRepository.findByCustomer_AccountNumber(accountNumber).orElseThrow(()->new RuntimeException("Customer with account number: " + accountNumber + ", was not found"));

        double updatedAmount = balance.getBalance() - withdrawAmount;
        if(updatedAmount < 0){
            return false;
        }
        balanceService.updateBalance(accountNumber, updatedAmount);
        return true;
    }

    @Override
    @Transactional
    public Boolean transfer(String fromAccountNumber, String toAccountNumber, Double transferAmount) {
        if(!ATMValidator.validateAmount(transferAmount)){
            return false;
        }
        if(!customerService.customerRepository.existsByAccountNumber(fromAccountNumber)
            ||
            !customerService.customerRepository.existsByAccountNumber(toAccountNumber))
        {
            return false;
        }
        Balance balanceOfFromAcc = balanceService.balanceRepository.findByCustomer_AccountNumber(fromAccountNumber).orElseThrow(()->new RuntimeException("Customer with account number: " + fromAccountNumber + ", was not found"));
        Balance balanceOfToAccount = balanceService.balanceRepository.findByCustomer_AccountNumber(toAccountNumber).orElseThrow(()->new RuntimeException("Customer with account number: " + fromAccountNumber + ", was not found"));

        double updatedFromBalance = balanceOfFromAcc.getBalance() - transferAmount;
        if(updatedFromBalance < 0){
            return false;
        }
        double updatedToBalance = balanceOfToAccount.getBalance() + transferAmount;

        balanceService.updateBalance(fromAccountNumber, updatedFromBalance);
        balanceService.updateBalance(toAccountNumber,updatedToBalance);
        return true;
    }
}
