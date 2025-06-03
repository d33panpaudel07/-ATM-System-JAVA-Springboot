package com.example.group1.atmUsingSpringBoot.services;

import com.example.group1.atmUsingSpringBoot.dto.Response;

public interface ATMService {
    Response deposit(String accountNumber, Double depositAmount);
    Response withdraw(String accountNumber, Double withdrawAmount);
    Response transfer(String fromAccount, String toAccount, Double transferAmount);
}
