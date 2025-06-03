package com.example.group1.atmUsingSpringBoot.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {
    private String name;
    private String accountNumber;
    private String mobileNumber;
}
