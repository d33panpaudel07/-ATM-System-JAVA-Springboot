package com.example.group1.atmUsingSpringBoot.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "balance")
@AllArgsConstructor
@NoArgsConstructor
public class Balance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_number", referencedColumnName = "account_number", nullable = false, unique = true)
    private Customer customer;

//    @Column(name = "balance", nullable = false, precision = 15, scale = 2)
    @Column(name = "balance", nullable = false)
    private Double balance;
}
