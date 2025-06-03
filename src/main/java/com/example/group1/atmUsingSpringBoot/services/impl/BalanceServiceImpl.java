package com.example.group1.atmUsingSpringBoot.services.impl;

import com.example.group1.atmUsingSpringBoot.entity.Balance;
import com.example.group1.atmUsingSpringBoot.repository.BalanceRepository;
import com.example.group1.atmUsingSpringBoot.services.BalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("balanceService")
public class BalanceServiceImpl implements BalanceService {

    BalanceRepository balanceRepository;
    BalanceServiceImpl(BalanceRepository balanceRepository){
        this.balanceRepository = balanceRepository;
    }

    @Override
    public Double checkBalance(String accountNumber) {
        Balance balance = balanceRepository.findByCustomer_AccountNumber(accountNumber)
                .orElseThrow(()->new RuntimeException("Balance record not found for account number: " + accountNumber));
        return balance.getBalance();
    }

    @Override
    public Balance updateBalance(String accountNumber, Double newBalance) {
        Balance balance = balanceRepository.findByCustomer_AccountNumber(accountNumber)
                .orElseThrow(()->new RuntimeException("Balance record not found for account number: " + accountNumber));

        balance.setBalance(newBalance);
        return balanceRepository.save(balance);
    }
}
