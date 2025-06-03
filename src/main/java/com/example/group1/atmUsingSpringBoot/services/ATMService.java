package com.example.group1.atmUsingSpringBoot.services;

public interface ATMService {
    Boolean deposit(String accountNumber, Double depositAmount);
    Boolean withdraw(String accountNumber, Double withdrawAmount);
    Boolean transfer(String fromAccount, String toAccount, Double transferAmount);
}
