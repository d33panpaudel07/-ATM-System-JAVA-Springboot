package com.example.group1.atmUsingSpringBoot.services;

import com.example.group1.atmUsingSpringBoot.dto.CustomerDto;
import com.example.group1.atmUsingSpringBoot.entity.Balance;

public interface BalanceService {
    Double checkBalance(String accountNumber);
    Balance updateBalance(String accountNumber, Double newBalance);
}
