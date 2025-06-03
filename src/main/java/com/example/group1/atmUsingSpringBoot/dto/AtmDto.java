package com.example.group1.atmUsingSpringBoot.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class AtmDto {
    @NotBlank(message = "Account number is required")
//    @Pattern(regexp = "\\d{10,16}", message = "Account number must be 10 to 16 digits")
    private String accountNumber;

//    @Pattern(regexp = "\\d{10,16}", message = "From account must be 10 to 16 digits")
    private String fromAccount;

//    @Pattern(regexp = "\\d{10,16}", message = "To account must be 10 to 16 digits")
    private String toAccount;

    @NotNull(message = "Amount is required")
    @DecimalMin(value = "1.0", message = "Amount must be at least 1.0")
    private Double amount;
}
