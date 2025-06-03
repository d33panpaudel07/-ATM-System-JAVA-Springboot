package com.example.group1.atmUsingSpringBoot.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class AtmDto {

    private String accountNumber;
    private String fromAccount;
    private String toAccount;
    private Double amount;
}
