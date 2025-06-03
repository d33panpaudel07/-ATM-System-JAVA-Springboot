package com.example.group1.atmUsingSpringBoot.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

@Entity
@Data
@Table(name = "customer")
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "account_number", unique = true, nullable = false, length = 20)
    private String accountNumber;

    @Column(name = "mobile_number", nullable = false, length = 15)
    private String mobileNumber;

    public Customer(String name, String accountNumber, String mobileNumber) {
        this.name = name;
        this.accountNumber = accountNumber;
        this.mobileNumber = mobileNumber;
    }
}
