package com.example.group1.atmUsingSpringBoot.repository;

import com.example.group1.atmUsingSpringBoot.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
//    @Query("SELECT c FROM Customer c WHERE c.account_number = :acc_number")
//    Optional<Customer> getCustomerByAccountNumber(@Param("acc_number") String accountNumber);
//
//    @Query("SELECT c FROM Customer c WHERE c.id = :id")
//    Optional<Customer> getCustomerById(@Param("id") Integer id);
//
//    @Query("SELECT c FROM Customer c WHERE c.mobile_number = :number")
//    Optional<Customer> getCustomerByMobileNumber(@Param("number") String mobileNumber);

    Optional<Customer> findByAccountNumber(String accountNumber);
    Optional<Customer> findByMobileNumber(String mobileNumber);
    List<Customer> findByNameContainingIgnoreCase(String name);
}
