package com.example.group1.atmUsingSpringBoot.controllers;

import com.example.group1.atmUsingSpringBoot.dto.AtmDto;
import com.example.group1.atmUsingSpringBoot.dto.Response;
import com.example.group1.atmUsingSpringBoot.services.ATMService;
import com.example.group1.atmUsingSpringBoot.services.BalanceService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/atm")
public class ATMController {
    @Qualifier("atmServiceImpl")
    private final ATMService atmService;
    @Qualifier("balanceServiceImpl")
    private final BalanceService balanceService;

    public ATMController(ATMService atmService, BalanceService balanceService) {
        this.atmService = atmService;
        this.balanceService = balanceService;
    }

    @PostMapping("/deposit")
    public ResponseEntity<?> deposit(@RequestBody AtmDto atmDto) {

        Response response = atmService.deposit(atmDto.getAccountNumber(), atmDto.getAmount());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/withdraw")
    public ResponseEntity<?> withdraw(@RequestBody AtmDto atmDto) {
        Response response = atmService.withdraw(atmDto.getAccountNumber(), atmDto.getAmount());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/transfer")
    public ResponseEntity<?> transfer(@RequestBody AtmDto atmDto) {
        Response response = atmService.transfer(atmDto.getFromAccount(), atmDto.getToAccount(), atmDto.getAmount());
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping("/balance/{accountNumber}")
    public ResponseEntity<?> getBalance(@PathVariable String accountNumber) {
        Double balance = balanceService.checkBalance(accountNumber);
        return new ResponseEntity<>(balance, HttpStatus.OK);
    }
}

