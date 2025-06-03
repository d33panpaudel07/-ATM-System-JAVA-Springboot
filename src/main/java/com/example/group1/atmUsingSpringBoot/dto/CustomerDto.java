package com.example.group1.atmUsingSpringBoot.dto;

import jakarta.persistence.Column;
import org.springframework.stereotype.Component;

@Component
public class CustomerDto {
    private String name;
    private String accountNumber;
    private String mobileNumber;
}
