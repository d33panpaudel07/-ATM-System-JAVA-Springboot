package com.example.group1.atmUsingSpringBoot.repository;

import com.example.group1.atmUsingSpringBoot.entity.Balance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BalanceRepository extends JpaRepository<Balance, Integer> {

    Optional<Balance> findByCustomer_AccountNumber(String accountNumber);

}
