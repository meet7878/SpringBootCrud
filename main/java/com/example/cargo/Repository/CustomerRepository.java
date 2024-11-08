package com.example.cargo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.cargo.Entity.Customers;

@Repository
public interface CustomerRepository extends JpaRepository<Customers, Integer> {

}
